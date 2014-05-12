package cn.focus.dc.focuswap.service;

import static cn.focus.dc.focuswap.config.AppConstants.CE_BUILDING_SEARCH_TIME;
import static cn.focus.dc.focuswap.config.AppConstants.CK_BUILDING_SEARCH_KEY;
import static cn.focus.dc.focuswap.config.AppConstants.DATA;
import static cn.focus.dc.focuswap.config.AppConstants.DATACENTER_BUILDING_NAME_BUY_CONDITION;
import static cn.focus.dc.focuswap.config.AppConstants.ERRORCODE;
import static cn.focus.dc.focuswap.config.AppConstants.XINFANG_TYPE_ID_RELATION_URL;

import cn.focus.dc.focuswap.model.DictAreaExt;
import cn.focus.dc.focuswap.model.DictCity;
import cn.focus.dc.focuswap.model.DictCityPriceExt;
import cn.focus.dc.focuswap.model.DictDistrictExt;
import cn.focus.dc.focuswap.model.DictProjTypeExt;
import cn.focus.dc.focuswap.model.DictProjTypeExtNew;
import cn.focus.dc.focuswap.model.DictSpecialBuilding;
import cn.focus.dc.focuswap.model.DictTrafficLineExt;
import cn.focus.dc.focuswap.model.SearchCondition;
import cn.focus.dc.focuswap.service.SearchService.HouseType;
import cn.focus.dc.focuswap.service.SearchService.SearchType;
import cn.focus.dc.focuswap.service.SearchService.SearchTypeSpecial;
import cn.focus.dc.focuswap.utils.Utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 楼盘筛选服务类，主要提供筛选条件和根据筛选条件筛出楼盘两种服务</br> 获取筛选条件：getCondition(int, boolean)</br>
 * 根据筛选条件筛选出楼盘：filterBuilding(SearchCondition)</br>
 * 
 * @author rogantian
 * @date 2013-11-15
 * @email rogantianwz@gmail.com
 */
@Service
public class BuildingSearchService {

    protected Log logger = LogFactory.getLog(BuildingSearchService.class);

    @Autowired
    public InterfaceService interfaceService;

    @Autowired
    private CacheHandlerService cacheHandler;

    @Autowired
    private SearchService searchService;

    /**
     * 因为special的对勾要变化,不能放入类变量里面,所以每次调用都需要new
     * @return
     */
    private List<DictSpecialBuilding> buildSpecial(){
        List<DictSpecialBuilding> specialBuildingCondtions = new ArrayList<DictSpecialBuilding>();
        specialBuildingCondtions.add(new DictSpecialBuilding(SearchTypeSpecial.DZLP.name(), "打折楼盘"));
        specialBuildingCondtions.add(new DictSpecialBuilding(SearchTypeSpecial.ZXKP.name(), "最新开盘"));
        specialBuildingCondtions.add(new DictSpecialBuilding(SearchTypeSpecial.XHX.name(), "小户型"));
        specialBuildingCondtions.add(new DictSpecialBuilding(SearchTypeSpecial.XF.name(), "现房"));
        return specialBuildingCondtions;
    }
    
    /**
     * 获取楼盘的筛选条件:先从cache中取，取不到的话就调楼盘中心的接口获取并且将获取的结果存入cache中
     * 
     * @param cityId
     * @param needSpecialSearch 是否需要返回特殊的搜索条件（打折、本月开盘、小户型和现房）
     * @return
     */
    public Map<SearchType, Object> getConditions(int cityId, boolean needSpecialSearch) {
        Map<SearchType, Object> ret = null;
        String cacheKey = CK_BUILDING_SEARCH_KEY + cityId;
        //cacheHandler.removeCache(CK_BUILDING_SEARCH_KEY + cityId);
        ret = cacheHandler.getCacheValue(cacheKey, Map.class);
        if (null == ret || ret.size() == 0) {
            ret = getConditionsFromInterface(cityId);
            if (null != ret && ret.size() > 0) {
                cacheHandler.setCache(cacheKey, CE_BUILDING_SEARCH_TIME, ret);
                logger.debug("城市:"+cityId+"setcache success");
            } else {
                ret = Collections.emptyMap();
            }
        }else{
            logger.debug("城市:"+cityId+"getcache success");
        }

        if (needSpecialSearch) {
            ret.put(SearchType.SPECIAL, buildSpecial());
        }
        return ret;
    }

    /**
     * 获取被选中的值以及面包屑显示的值
     * @param allConditions
     * @param c
     * @param city
     * @param con
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String,Object> getChosenCondition(Map<SearchType, Object> allConditions,DictCity city,String con){
        if(StringUtils.isNotBlank(con)){
            con +="/";            
        }else{
            con="k______/";
        }
        int index = -1; //条件索引值
        String[] sp = con.split("_");
        String tdk = "";
        Map<String,Object> result = new HashMap<String,Object>();
        List<Object> showCondition = new ArrayList<Object>();
        StringBuilder linkUrl = new StringBuilder("/"+city.getCityPinyinAbbr()+"/loupan/");
        for(SearchType q : allConditions.keySet()){
            switch (q) {
                case DISTRICT:
                    List<DictDistrictExt> dde = (List<DictDistrictExt>) allConditions.get(SearchType.DISTRICT); 
                    index = 0; 
                    String[] arr0 = sp.clone();
                    for (DictDistrictExt dd : dde) {
                        arr0[index] = dd.getCondValue();
                        dd.setLinkUrl(linkUrl.toString()+"k"+StringUtils.join(arr0,"_"));
                        //获取选中项
                        if(StringUtils.isNotBlank(sp[index]) && sp[index].equals("k"+dd.getCondValue())){
                            //获取面包屑
                            result.put("bread", dd.getCondName());                                                        
                            dd.setSelect("selected");
                            showCondition.add(dd.clone(index));
                            tdk += dd.getCondName();
                        }
                    }
                    break;
                case TYPE:
                    List<DictProjTypeExtNew> dpt = (List<DictProjTypeExtNew>) allConditions.get(SearchType.TYPE); 
                    index = 1; 
                    String[] arr1 = sp.clone();
                    for (DictProjTypeExtNew dd : dpt) {
                        arr1[1] = dd.getCondValue();
                        dd.setLinkUrl(linkUrl.toString()+StringUtils.join(arr1,"_"));
                        //获取选中项
                        if(StringUtils.isNotBlank(sp[index]) && sp[index].equals(dd.getCondValue())){
                            dd.setSelect("selected");
                            showCondition.add(dd.clone(index));
                            tdk += dd.getCondName();
                        }
                    }
                    break;
                case PRICE:
                    List<DictCityPriceExt> dcp = (List<DictCityPriceExt>) allConditions.get(SearchType.PRICE);
                    index = 2; 
                    String[] arr2 = sp.clone();
                    for (DictCityPriceExt dd : dcp) {                             
                        arr2[2] = dd.getCondValue();
                        dd.setLinkUrl(linkUrl.toString()+StringUtils.join(arr2,"_"));
                        //获取选中项
                        if(StringUtils.isNotBlank(sp[index]) && sp[index].equals(dd.getCondValue())){
                            dd.setSelect("selected");
                            showCondition.add(dd.clone(index));
                            tdk += dd.getCondName();
                        }
                    }
                    break;
                case HOT:
                    List<DictAreaExt> dae = (List<DictAreaExt>) allConditions.get(SearchType.HOT); 
                    index = 3; 
                    String[] arr3 = sp.clone();
                    for (DictAreaExt dd : dae) {     
                        arr3[3] = dd.getCondValue();
                        dd.setLinkUrl(linkUrl.toString()+StringUtils.join(arr3,"_"));
                        //获取选中项
                        if(StringUtils.isNotBlank(sp[index]) && sp[index].equals(dd.getCondValue())){
                            dd.setSelect("selected");
                            showCondition.add(dd.clone(index));
                            tdk += dd.getCondName();
                        }
                    }
                    break;
                case SUBWAY:
                    List<DictTrafficLineExt> dtl = (List<DictTrafficLineExt>) allConditions.get(SearchType.SUBWAY); 
                    index = 4; 
                    String[] arr4 = sp.clone();
                    for (DictTrafficLineExt dd : dtl) {
                        arr4[4] = dd.getCondValue();
                        dd.setLinkUrl(linkUrl.toString()+StringUtils.join(arr4,"_"));
                        //获取选中项
                        if(StringUtils.isNotBlank(sp[index]) && sp[index].equals(dd.getCondValue())){
                            dd.setSelect("selected");
                            showCondition.add(dd.clone(index));
                            tdk += dd.getCondName();
                        }
                    }
                    break;
                case SPECIAL:
                    List<DictSpecialBuilding> dsb = (List<DictSpecialBuilding>) allConditions.get(SearchType.SPECIAL); 
                    index = 5; 
                    String[] arr5 = sp.clone();
                    for (DictSpecialBuilding dd : dsb) {
                        arr5[5] = dd.getCondValue();
                        dd.setLinkUrl(linkUrl.toString()+StringUtils.join(arr5,"_"));
                        //获取选中项
                        if (StringUtils.isNotBlank(sp[index]) && sp[index].equals(dd.getCondValue())) {                 
                            dd.setSelect("selected");
                            showCondition.add(dd.clone(index));
                            tdk += dd.getCondName();
                        }
                    }
                    break;
                default:
                    break;
                }
            }            
        result.put("chosen", showCondition);
        result.put("tdk", tdk);
        return result;
    }
    
    
    /**
     * 根据筛选条件筛选出楼盘,参考SearchService.projSearch(JSONObject sCond)
     * 
     * @param searchConditions
     * @return
     */
    public Map<String, Object> filterBuilding(SearchCondition searchConditions) {
        if (null == searchConditions) {
            return Collections.emptyMap();
        }
            return searchService.projSearch(searchConditions);
    }

    /**
     * 从楼盘中心接口获取城市的楼盘筛选条件，参考wiki：http://10.10.90.156/wiki/index.php/Dict/search/tab</br>
     * 其中的价格条件已经处理过了，详细见decoratePriceCondition(List..)方法</br> 返回结果:</br>
     * <table>
     * <tr>
     * <td>key</td>
     * <td>value</td>
     * </tr>
     * <tr>
     * <td>SearchType.SUBWAY</td>
     * <td>List&lt;DictTrafficLineExt&gt;</td>
     * </tr>
     * <tr>
     * <td>SearchType.DISTRICT</td>
     * <td>List&lt;DictDistrictExt&gt;</td>
     * </tr>
     * <tr>
     * <td>SearchType.TYPE</td>
     * <td>List&lt;DictProjTypeExt&gt;</td>
     * </tr>
     * <tr>
     * <td>SearchType.HOT</td>
     * <td>Map&lt;String, List&lt;DictAreaExt&gt;&gt;</td>
     * </tr>
     * <tr>
     * <td>SearchType.PRICE</td>
     * <td>Map&lt;String, List&lt;DictCityPriceExt&gt;&gt;</td>
     * </tr>
     * </table>
     * 
     * @param cityId
     * @return
     */
    public Map<SearchType, Object> getConditionsFromInterface(int cityId) {
        Map<SearchType, Object> ret = new HashMap<SearchType, Object>();

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("cityId", cityId);
        // JSONObject jsonObject =
        // interfaceService.getJSONFromInterface("http://wap-building.apps.sohuno.com/dict/search/tab?cityId={cityId}",
        // variables);
        JSONObject jsonObject = interfaceService
                .getJSONFromInterface(DATACENTER_BUILDING_NAME_BUY_CONDITION, variables);
        String data = extractJSONData(jsonObject);

        if (null != data) {

            Map<String, Object> conditions = JSONObject.parseObject(data, new TypeReference<Map<String, Object>>() {
            });
            JSONArray lineArray = (JSONArray) conditions.get("lines");
            List<DictTrafficLineExt> lineList = parseConditionArray(lineArray, DictTrafficLineExt.class);
            if(!lineList.isEmpty()){
                ret.put(SearchType.SUBWAY, lineList);
            }

            Object districtArray = conditions.get("districts");
            String districtStr = districtArray.toString();
            List<DictDistrictExt> districtList = JSONObject.parseArray(districtStr, DictDistrictExt.class);
            ret.put(SearchType.DISTRICT, districtList);

            List<DictProjTypeExt> typeList = new ArrayList<DictProjTypeExt>();
            JSONObject types = interfaceService.getJSONFromInterface(XINFANG_TYPE_ID_RELATION_URL,variables);
            
            if(types != null && types.get("data") != null){
                for(HouseType ht : HouseType.values()){
                    DictProjTypeExtNew type= new DictProjTypeExtNew();
                    type.setTypeName(ht.name());
                    type.setTypeId(ht.ordinal()+1);
                    
                    //通过新的id获取关联的旧ids
                    //List<DictProjTypeExtNew> types = conditionDAO.getOldIds(cityId,type.getTypeId().toString());
                    JSONArray ja = (JSONArray) types.get("data");
                    //Integer[] args = new Integer[ja.size()];
                    List<Integer> args = new ArrayList<Integer>();
                    for (Object ob : ja) {
                        JSONObject jo = (JSONObject) ob;
                        if (type.getTypeId() == jo.get("typeIdNew")) {
                            args.add((Integer) jo.get("typeIdOld"));
                        }
                    }
                    type.setOldIds(args);
                    typeList.add(type);
                }
            }        
            ret.put(SearchType.TYPE, typeList);

            //Map<String, List<DictAreaExt>> areaMap = new HashMap<String, List<DictAreaExt>>();
            JSONObject areaObject = (JSONObject) conditions.get("areas");
            ///*2次注释,因为产品不需要热点了 2014.1.6
            JSONArray areaArray = areaObject.getJSONArray("-1");
            if(areaArray == null){
                areaArray = areaObject.getJSONArray("0");
            }
            if(areaArray != null && !areaArray.isEmpty()){
                List<DictAreaExt> areaList = parseConditionArray(areaArray, DictAreaExt.class);
                ret.put(SearchType.HOT, areaList);
            }
            JSONObject priceObject = (JSONObject) conditions.get("prices");
            JSONArray priceArray = priceObject.getJSONArray("0");
            List<DictCityPriceExt> priceList = parseConditionArray(priceArray, DictCityPriceExt.class);
            decoratePriceCondition(priceList);
            ret.put(SearchType.PRICE, priceList);
        }

        return ret;
    }

    /**
     * 将“价格”转换成适合的 名称和值</br> 如:将10000一下转换成 1万一下(名称)和 0-10000(值)</br>
     * 
     * @param priceMap
     */
    protected void decoratePriceCondition(List<DictCityPriceExt> priceList) {
        if (null == priceList) {
            return;
        }

        Iterator<DictCityPriceExt> it = priceList.iterator();
        while (it.hasNext()) {
            DictCityPriceExt priceExt = it.next();
            String price = priceExt.getPrice();
            if (StringUtils.equals("0", price)) {
                it.remove();
                continue;
            }
            String condName = Utils.stringPattern(price);
            if (StringUtils.isBlank(condName)) {
                it.remove();
                continue;
            }

            String condValue = Utils.stringPatternId(price);
            if (StringUtils.isBlank(condValue)) {
                it.remove();
                continue;
            }

            priceExt.setCondName(condName);
            priceExt.setCondValue(condValue);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private <T> List<T> parseConditionArray(JSONArray conditionArray, Class T){
        String arrStr = conditionArray.toJSONString();
        return JSONObject.parseArray(arrStr, T);
    }

    /**
     * 从标准的接口返回值中提取所需数据
     * 
     * @param jsonObject
     * @return
     */
    private String extractJSONData(JSONObject jsonObject) {
        if (null != jsonObject) {
            Integer errorCode = jsonObject.getInteger(ERRORCODE);
            if (null != errorCode && errorCode == 0) {
                return jsonObject.getString(DATA);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        final RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory(){
            private static final String DEFAULT_SIGN_NAME = "sign";

            @Override
            protected void postProcessHttpRequest(HttpUriRequest request) {
                if (StringUtils.equals(HttpGet.METHOD_NAME, request.getMethod())) {
                    URI url = request.getURI();
                    String query = url.getQuery();
                    if (null != query) {
                        String sign = genSignParam(query);
                        request.addHeader(DEFAULT_SIGN_NAME, (String) sign);
                    }
                }

                super.postProcessHttpRequest(request);

            }

            private String genSignParam(String queryStr) {
                if (StringUtils.isBlank(queryStr)) {
                    return DigestUtils.md5Hex("e0cd45bf9ee7773cc9b72bd824f3b35c");
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(StringUtils.chomp(queryStr, "&")).append("e0cd45bf9ee7773cc9b72bd824f3b35c");
                    return DigestUtils.md5Hex(sb.toString());
                }

            }
        });
        InterfaceService interfaceService = new InterfaceService() {
            public JSONObject getJSONFromInterface(String url, Map<String, Object> params) {
                JSONObject ret = null;
                try {
                    String jsonStr = template.getForObject(url, String.class, params);
                    if (StringUtils.isNotBlank(jsonStr)) {
                        ret = JSONObject.parseObject(jsonStr);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return ret;
            }
        };

        BuildingSearchService buildingService = new BuildingSearchService();
        buildingService.interfaceService = interfaceService;
    }

}
