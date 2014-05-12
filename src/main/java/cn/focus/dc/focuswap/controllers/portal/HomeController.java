package cn.focus.dc.focuswap.controllers.portal;

import static cn.focus.dc.focuswap.config.AppConstants.HOME_BUILDING_LIST_PAGENO;
import static cn.focus.dc.focuswap.config.AppConstants.HOME_BUILDING_LIST_PAGESIZE;


import cn.focus.dc.focuswap.config.AppConstants;
import cn.focus.dc.focuswap.model.BuildingPropose;
import cn.focus.dc.focuswap.model.DictCity;
import cn.focus.dc.focuswap.model.EsProjInfoChild;
import cn.focus.dc.focuswap.model.HousingGuide;
import cn.focus.dc.focuswap.model.News;
import cn.focus.dc.focuswap.service.BuildingProposeService;
import cn.focus.dc.focuswap.service.BuildingSearchService;
import cn.focus.dc.focuswap.service.BuildingService;
import cn.focus.dc.focuswap.service.BuyHouseService;
import cn.focus.dc.focuswap.service.CityService;
import cn.focus.dc.focuswap.service.HousingGuideService;
import cn.focus.dc.focuswap.service.NewsService;

import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 将home页面拆封成多个子窗口模块
 * 
 * @author zhiweiwen 2013-12-2 上午10:44:13
 */
@Path("home")
public class HomeController {

    // 打折楼盘
    public static final String DZLP = "DZLP";

    // 小户型
    public static final String XHX = "XHX";

    // 最新开盘
    public static final String ZXKP = "ZXKP";

    // 前端截取字数
    public static final int DEFAULTCOUNTS = 12;
    
    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private HousingGuideService housingGuideService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private CityService cityService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private BuyHouseService buyHouseService;

    @Autowired
    private BuildingSearchService buildingSearchService;
    
    @Autowired
    private BuildingProposeService buildingProposeService;

    @Get("test")
    public String test() {
        return "@test";
    }

    /**
     * 获取宝典
     * 
     * @param inv
     * @param cityId
     * @return
     */
    @Get("baodian")
    public String getBaoDian(Invocation inv, @Param("cityId") int cityId, @Param("device") String device) {
        try {
            // 获取购房宝典 默认显示5条
            List<HousingGuide> guideList = housingGuideService.getHousingGuideList(0, 5);
            // guideList = setTime(guideList);
            inv.addModel("guideList", guideList);
            return "home/" + device + "/hbaodian";
        } catch (Exception e) {
            logger.info("", e.getMessage());
            return null;
        }
    }
    
    /**
     * 获取导购
     * 
     * @param inv
     * @param cityId
     * @return
     */
    @Get("daogou")
    public String getDaogouList(Invocation inv,@Param("cityId") int cityId, @Param("device") String device) {
        try {
              JSONObject json = buildingProposeService.getProposeList(cityId, 1, 5);
            List<BuildingPropose> list = buildingProposeService.decorateProposeList(json,
                    AppConstants.Focus_img_width.widthMap.get(AppConstants.DAOGOULIST_FOCUS_IMG_WIDTH + device));
              if(list.size()==0) {
                  return null;
              }
              inv.addModel("list", list);
              inv.addModel("count", list.size());
            return "home/" + device + "/hdaogou";
        } catch (Exception e) {
            logger.info("", e.getMessage());
            return null;
        }
    }

    /**
     * 获取新闻
     * 
     * @param inv
     * @param cityId
     * @return
     */
    @Get("news")
    public String getNews(Invocation inv, @Param("cityId") int cityId, @Param("device") String device) {
        try {
            // 获取资讯新闻列表
            List<News> newsList = newsService.getNewsList(1);
            List<News> newsListReal = new ArrayList<News>();
            for (int i = 0; i < 5; i++) {
                News news = newsList.get(i);
                if(StringUtils.isNotBlank(news.getDescription()) && news.getDescription().length()>23){
                	news.setDescription(news.getDescription().substring(0,23)+"...");
                }
                decorateNews(news);
                newsListReal.add(news);
            }
            inv.addModel("newsList", newsListReal);
            return "home/" + device + "/hnews";
        } catch (Exception e) {
            logger.info("", e.getMessage());
            return null;
        }
    }

    private void decorateNews(News news) {
        try {
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            SimpleDateFormat sdf3 = new SimpleDateFormat("M月d日");
            Date d = sdf2.parse(news.getTime());
            news.setTime(sdf3.format(d));
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    /**
     * 获取打折楼盘
     * 
     * @param inv
     * @param cityId
     * @return
     */
    @SuppressWarnings("unchecked")
    @Get("dazheloupan")
    public String getDZLP(Invocation inv, @Param("cityId") int cityId, @Param("device") String device) {
        try {
            DictCity city = cityService.getCity(cityId);
            // 获取打折楼盘列表 默认3条
            cn.focus.dc.focuswap.model.SearchCondition condition = buyHouseService.getHomeHouseCondition(
                    city.getCityId(), HOME_BUILDING_LIST_PAGENO, HOME_BUILDING_LIST_PAGESIZE, DZLP, "");
            Map<String, Object> disCountHouseMap = buildingSearchService.filterBuilding(condition);
            List<EsProjInfoChild> houseList = (List<EsProjInfoChild>) disCountHouseMap.get("buildingList");
            houseList = buyHouseService.switchBuildingParameter(houseList, city, "k_____DZLP_");
            houseList = buildingService.setBuildingListPrice(houseList);
            if ("pad".equals(device)) {
                houseList = setAddress(houseList);
            }
            inv.addModel("disCouentHouse", houseList);
            inv.addModel("nums", houseList.size());
            if (null == houseList || houseList.size() == 0) {
                return null;
            }
            return "home/" + device + "/hdazhe";
        } catch (Exception e) {
            logger.info("", e.getMessage());
            return null;
        }
    }

    /**
     * 获取最新开盘
     * 
     * @param inv
     * @param cityId
     * @return
     */
    @SuppressWarnings("unchecked")
    @Get("zuixinkaipan")
    public String getZXKP(Invocation inv, @Param("cityId") int cityId, @Param("device") String device) {
        try {
            DictCity city = cityService.getCity(cityId);
            // 获取最新开盘列表 默认3条
            cn.focus.dc.focuswap.model.SearchCondition condition = buyHouseService.getHomeHouseCondition(
                    city.getCityId(), HOME_BUILDING_LIST_PAGENO, HOME_BUILDING_LIST_PAGESIZE, ZXKP, "");
            Map<String, Object> newHouseMap = buildingSearchService.filterBuilding(condition);
            List<EsProjInfoChild> houseList = (List<EsProjInfoChild>) newHouseMap.get("buildingList");
            houseList = buyHouseService.switchBuildingParameter(houseList, city, "k_____ZXKP_");
            houseList = buildingService.setBuildingListPrice(houseList); 
            if ("pad".equals(device)) {
                houseList = setAddress(houseList);
            }
            inv.addModel("newHouse", houseList);
            inv.addModel("nums", houseList.size());
            if (null == houseList || houseList.size() == 0) {
                return null;
            }
            return "home/" + device + "/hzuixin";
        } catch (Exception e) {
            logger.info("", e.getMessage());
            return null;
        }
    }

    /**
     * 获取小户型
     * 
     * @param inv
     * @param cityId
     * @return
     */
    @SuppressWarnings("unchecked")
    @Get("xiaohuxing")
    public String getXHX(Invocation inv, @Param("cityId") int cityId, @Param("device") String device) {
        try {
            DictCity city = cityService.getCity(cityId);
            // 获取小户型列表 默认3条
            cn.focus.dc.focuswap.model.SearchCondition condition = buyHouseService.getHomeHouseCondition(
                    city.getCityId(), HOME_BUILDING_LIST_PAGENO, HOME_BUILDING_LIST_PAGESIZE, XHX, "");
            Map<String, Object> littleHouseMap = buildingSearchService.filterBuilding(condition);
            List<EsProjInfoChild> houseList = (List<EsProjInfoChild>) littleHouseMap.get("buildingList");
            houseList = buyHouseService.switchBuildingParameter(houseList, city, "k_____XHX_");
            houseList = buildingService.setBuildingListPrice(houseList);
            if ("pad".equals(device)) {
                houseList = setAddress(houseList);
            }
            inv.addModel("littleHouse", houseList);
            inv.addModel("nums", houseList.size());
            if (null == houseList || houseList.size() == 0) {
                return null;
            }
            return "home/" + device + "/hxiaohuxing";
        } catch (Exception e) {
            logger.info("", e.getMessage());
            return null;
        }
    }

    /**
     * 楼盘地址截字
     * 
     * @param houseList
     * @return
     */
    private List<EsProjInfoChild> setAddress(List<EsProjInfoChild> houseList) {
        for (EsProjInfoChild info : houseList) {
            String address = info.getProjAddress();
            if (address.length() > DEFAULTCOUNTS) {
                address = address.substring(0, DEFAULTCOUNTS) + "...";
                info.setProjAddress(address);
            }
        }
        return houseList;
    }

}
