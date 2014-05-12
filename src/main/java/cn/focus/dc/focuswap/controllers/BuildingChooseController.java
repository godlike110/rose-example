package cn.focus.dc.focuswap.controllers;

import cn.focus.dc.focuswap.annotation.AccessLogRequired;
import cn.focus.dc.focuswap.config.AppConstants;
import cn.focus.dc.focuswap.filter.ChooseSubJsonFilter;
import cn.focus.dc.focuswap.model.Condition;
import cn.focus.dc.focuswap.model.DictCity;
import cn.focus.dc.focuswap.model.EsProjInfoChild;
import cn.focus.dc.focuswap.model.QueryData;
import cn.focus.dc.focuswap.model.SearchCondition;
import cn.focus.dc.focuswap.service.BuildingProposeService;
import cn.focus.dc.focuswap.service.BuildingSearchService;
import cn.focus.dc.focuswap.service.BuyHouseService;
import cn.focus.dc.focuswap.service.CityService;
import cn.focus.dc.focuswap.service.SearchService.SearchType;
import cn.focus.dc.focuswap.utils.DeviceUtils;
import cn.focus.dc.focuswap.utils.JsonResponseUtil;
import cn.focus.dc.focuswap.utils.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.List;
import java.util.Map;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.DefValue;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;


@Path("{cityName:[a-zA-Z]{2,}}/loupan")
@AccessLogRequired
public class BuildingChooseController {
    private static final int PAGE_SIZE_NUMBER = 10;

	@Autowired
	private CityService cityService;
	
	@Autowired
	private BuildingSearchService buildingSearchService;
	
	@Autowired
	private BuyHouseService buyHouseService;
	
	@Autowired
	private BuildingProposeService buildingProposeService;
	
	
    /**
     * @author kanezheng
     * @desc 楼盘筛选页(synchronization)
     * @url m.focus.cn/bj/loupan/
     * @time 2013.11.22
     * @param inv
     * @param device
     * @param con 格式k开头 kdi_ty_pr_ho_su_sp_se
     * @return
     */
    @SuppressWarnings("unchecked")
    @Get({ "/", "{con:^k.+}/" })
    public String loupan(Device device, @Param("cityName") @DefValue("bj") String cityName,
            @DefValue("") @Param("con") String con, Invocation inv) {
        DictCity city = cityService.getCityByNameOrPinYin(cityName);
        con = Condition.verify(con);
        List<QueryData> list = null;
        if(con != null){
            Condition chosen = new Condition(con);
            list = chosen.combined();
        }
        SearchCondition searchConditions = setPara(list, 1, PAGE_SIZE_NUMBER + 1, city.getCityId());
        Map<String, Object> buildingList = buildingSearchService.filterBuilding(searchConditions);
        int total = (Integer)buildingList.get("total");
        List<EsProjInfoChild> epj = (List<EsProjInfoChild>) buildingList.get("buildingList");
        epj = buyHouseService.switchBuildingParameter(epj, city, con);
        inv.addModel("buildingList", epj);
        inv.addModel("total", total);
        // 为了拦截不再获取第二次
        inv.addModel("_city", city);

        StringBuilder sb = new StringBuilder("/loupan/filterajax/"+(con==null?"":con));
        //StringBuilder single = new StringBuilder("/loupan/");
        inv.addModel("city", Utils.putAjaxUrl(city, sb, null));
        Map<SearchType, Object> conditions = buildingSearchService.getConditions(city.getCityId(), true);

        //获取面包屑,以及搜索条件,并且赋予每个条件url
        Map<String, Object> chosenTkd_bread = buildingSearchService.getChosenCondition(conditions,city,con);
        List<Object> chosenList = (List<Object>)chosenTkd_bread.get("chosen");
        String tdk  = (String)chosenTkd_bread.get("tdk");
        inv.addModel("chosen", chosenList);
        inv.addModel("districtName", chosenTkd_bread.get("bread"));
        inv.addModel("chosenTkd", tdk);
        //判断导购标签是否有值
        int status = buildingProposeService.getProposeStatus(city.getCityId());
        inv.addModel("totalDaogou", status);
        inv.addModel("mobile", device.isMobile());
       
        inv.addModel("conditions", conditions);
        if (epj != null && epj.size() > PAGE_SIZE_NUMBER) {
            inv.addModel("hasNext", true);
            epj.remove(PAGE_SIZE_NUMBER);
        }else{
            inv.addModel("hasNext", false);
        }
        String fold = Utils.getFold(inv.getRequest(), con);
        inv.addModel("fold", fold);
        return DeviceUtils.device(inv, device) + "/filterBuilding";
    }
    
    /**
     * @author kanezheng
     * @desc 根据不同条件获取楼盘列表(楼盘筛选页,nonsynchronous)
     * @time 2013.11.22
     * @param inv
     * @param PAGE_NO
     * @param PAGE_SIZE
     * @param con 格式k开头 kdi_ty_pr_ho_su_sp_se
     * @return
     */

    @SuppressWarnings("unchecked")
    @Get({ "filterajax/", "filterajax/{con:^k.+}/" })
    public String filterList(Invocation inv, @Param("cityName") @DefValue("bj") String cityName,
        @Param("con") String con, @Param("callback") String callback,
        @Param(AppConstants.PAGE_NO) @DefValue("1") Integer pageNo,
        @Param(AppConstants.PAGE_SIZE) @DefValue("10") Integer pageSize) {
        DictCity city = cityService.getCityByNameOrPinYin(cityName);
        con = Condition.verify(con);
        List<QueryData> list = null;
        if(con != null){
            Condition chosen = new Condition(con);
            list = chosen.combined();
        }
        SearchCondition searchConditions = setPara(list, pageNo, pageSize, city.getCityId());
        Map<String, Object> buildingList = buildingSearchService.filterBuilding(searchConditions);
        List<EsProjInfoChild> epj = (List<EsProjInfoChild>) buildingList.get("buildingList");
        epj = buyHouseService.switchBuildingParameter(epj, city, con);
        //减少输出项
        String sub = JSON.toJSONString(epj, ChooseSubJsonFilter.getInstance());
        JSONArray json = JSONArray.parseArray(sub);
        buildingList.put("buildingList", json);
        return JsonResponseUtil.jsonp(buildingList, callback);
    }
    
    private SearchCondition setPara(List<QueryData> list, int pageNo, int pageSize, int cityId) {
        return new SearchCondition(list, pageNo, pageSize, cityId);
    }
    
//    @Get({ "test" })
//    public String test(Invocation inv){
//        List<DictCity> cityList = cityService.getCityList();
//        int i = 0;
//        for(DictCity d : cityList){
//            SearchCondition searchConditions = setPara(null, 0, 10000, d.getCityId());
//            searchConditions.setSaleStatus(SaleStatus.ONSALE);
//            Map<String, Object> buildingList = buildingSearchService.filterBuilding(searchConditions);
//            List<EsProjInfoChild> epj = (List<EsProjInfoChild>) buildingList.get("buildingList");
//            for(EsProjInfoChild c : epj){
//                if(c.getPhone400() != null && c.getPhone400() !=0){
//                    i++;
//                    System.out.println(d.getCityName()+"," + c.getProjName()+","+ "http://m.focus.cn/"+d.getCityPinyinAbbr()+"/loupan/"+c.getGroupId()+"/?channelId=405"+","+c.getDistrictName()+","+c.getPhone400());
//                }
//            }
//        }
//        System.out.println("total " + i);
//        return null;
//    }
//	
}
