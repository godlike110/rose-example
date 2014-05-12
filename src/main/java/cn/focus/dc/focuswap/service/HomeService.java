package cn.focus.dc.focuswap.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.search.client.SearchClient;
import com.common.search.client.base.Building;

import cn.focus.dc.building.model.es.EsProjInfo;
import cn.focus.dc.focuswap.config.AppConstants;
import cn.focus.dc.focuswap.dao.RecommendHouseDAO;
import cn.focus.dc.focuswap.model.DictCity;
import cn.focus.dc.focuswap.model.EsProjInfoChild;
import cn.focus.dc.focuswap.model.RecommendHouse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.rubyeye.xmemcached.MemcachedClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.common.joda.time.DateTime;
import org.elasticsearch.common.joda.time.Period;
import org.elasticsearch.common.joda.time.PeriodType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static cn.focus.dc.focuswap.config.AppConstants.*;

@Component
public class HomeService {

    private static Log logger = LogFactory.getLog(HomeService.class);

    @Autowired
    private RecommendHouseDAO recommendDao;

    @Autowired
    private SearchService searchService;

    @Autowired
    private MemcachedClient cacheClient;

    @Autowired
    private SearchClient suggestSearchClient;
    
    @Autowired
    private InterfaceService interfaceService;
    
    @Autowired
    private BuyHouseService buyHouseService;

    /**
     * 获取首页主编推荐楼盘列表
     */
    public Map<String,Object> getRecommendHouseList(DictCity city) {
        List<EsProjInfoChild> ret = Collections.emptyList();
        Map<String,Object> result= new HashMap<String,Object>();

        String cacheKey = new StringBuilder(CK_HOME_RECOMMENDLIST).append(city.getCityId()).toString();

        // 首先从缓存获取
        try {
            ret = cacheClient.get(cacheKey);
            if (null != ret && ret.size() > 0) {
                result.put("list", ret);
                return result;
            }
        } catch (Exception e) {
            logger.error("", e);
        }

        // 缓存获取不到，从接口和数据库获取
        Date beginDate = recommendDao.getRecommendStartDate(city.getCityId());
        DateTime now = new DateTime();
        DateTime beginDateJoda = new DateTime(beginDate.getTime());

        // 获取当前时间到开始时间的天数
        Period p = new Period(beginDateJoda, now, PeriodType.days());
        int pDays = p.getDays();

        // 获取总共的期数
        int pCounts = (pDays / 7) + 1;

        // 获取本期开始的第一天
        DateTime startDate = beginDateJoda.plusDays((pCounts-1) * 7);
        DateTime endDate = startDate.plusDays(7);

        // 获取主编推荐楼盘ID
        List<RecommendHouse> rhList = recommendDao.getRecommendIdList(city.getCityId(), 0, 5, startDate.getMillis() / 1000L,
                endDate.getMillis() / 1000L);
        Map<Integer,String> urlMap = new HashMap<Integer,String>();
        List<Integer> groupIds = new ArrayList<Integer>();
        for(RecommendHouse rh:rhList) {
            urlMap.put(rh.getGroupId(), rh.getPicUrl());
            groupIds.add(rh.getGroupId());
        }
        ret = searchService.projGroupIdSearch(groupIds);
        ret = buyHouseService.switchBuildingParameter(ret,
                city,"k______");
        Date recommendTime = new Date(rhList.get(0).getRecommendDate()*1000L);
        for(EsProjInfoChild ec:ret) {
            ec.setUrl(urlMap.get(ec.getGroupId()));
            ec.setRecommendDate(recommendTime);
            ec.setRecommendPeriods(pCounts);
        }
        // 将结果放入缓存
        if (ret != null && ret.size() > 0) {
            try {
                cacheClient.set(cacheKey, CE_HOME_RECOMMENDLIST_TIME, ret);
            } catch (Exception e) {
                logger.error("", e);
            }
        }
        result.put("list", ret);
        return result;
    }

    /**
     * 楼盘搜索input框 auto-complete功能
     */
    public List<EsProjInfo> getSearchSuggestList(String keywords, int cityId) {
        List<EsProjInfo> ret = Collections.emptyList();
        ret = searchService.projNameSearch(keywords, cityId, 10);
        return ret;
    }

    /**
     * 楼盘搜索inupt框auto-complete功能支持，支持拼音搜索
     * 
     * @param keywords
     * @param cityId
     * @param type 1 新房suggest 2 二手房suggest
     * @return
     */
    public JSONArray getSearchSuggestListSupportPY(String keywords, DictCity city, int type) {
        JSONArray ret = new JSONArray();
        try {
            if (type == AppConstants.SEARCH_TYPE_XINFANG) {
                Map<Integer, Building> searchResult = suggestSearchClient.search(keywords, city.getCityId(),
                        10);
                if (null != searchResult) {
                    for (Entry<Integer, Building> entry : searchResult.entrySet()) {
                        JSONObject jo = new JSONObject();
                        jo.put("projName", entry.getValue().getName());
                        jo.put("groupId", entry.getKey());
                        jo.put("linkUrl", "/"+city.getCityPinyinAbbr() + "/loupan/" + entry.getKey() + "/k______" + keywords + "/");
                        ret.add(jo);
                    }
                }
            } else {
                //获取二手房suggest数据
                ret = interfaceService.getSuggestJsonFromEsf(keywords, city);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return ret;
    }

    /**
     * 楼盘搜索调用lucene，支持拼音搜索,目前先不上线
     * bykane 
     * @param keywords
     * @param city
     * @param type 1 新房suggest 2 二手房suggest
     * @return
     */
    public JSONArray getSearchSuggestListSupportLucene(String keywords, DictCity city, int type) {
        JSONArray ret = new JSONArray();
        try {
            if (type == AppConstants.SEARCH_TYPE_XINFANG) {
                Map<String, Object> urlVariables = new HashMap<String, Object>();
                urlVariables.put("q", keywords);
                urlVariables.put("c", city.getCityId());
                urlVariables.put("n", 10);
                JSONObject jo = interfaceService.getJSONFromInterface("http://10.10.44.133/search?q={q}&c={c}&n={n}", urlVariables);
                
                if(jo != null){
                    ret = (JSONArray)jo.get("list");
                    for(Object ob : ret){
                        JSONObject j = (JSONObject)ob;
                        j.put("linkUrl", "/"+city.getCityPinyinAbbr() + "/loupan/" + j.getString("groupId") + "/k______" + keywords + "/");
                    }
                } 
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return ret;
    }
    
}
