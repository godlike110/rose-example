package cn.focus.dc.focuswap.controllers;

import cn.focus.dc.focuswap.annotation.AccessLogRequired;
import cn.focus.dc.focuswap.config.AppConstants;
import cn.focus.dc.focuswap.config.AppConstants.CityPageStatus;
import cn.focus.dc.focuswap.model.BuildingInfo;
import cn.focus.dc.focuswap.model.City;
import cn.focus.dc.focuswap.model.DictCity;
import cn.focus.dc.focuswap.service.BuildingService;
import cn.focus.dc.focuswap.service.CityService;
import cn.focus.dc.focuswap.utils.DeviceUtils;
import cn.focus.dc.focuswap.utils.Utils;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.DefValue;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.portal.Portal;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;



@Path("{cityName:[a-zA-Z]{2,}}/loupan")
@AccessLogRequired
public class BuildingHomeController {

    @Autowired
    private CityService cityService;

    @Autowired
    private BuildingService buildingService;

    /**
     * 楼盘详情页
     * 
     * @param inv
     * @param cityName 城市名或者pinyin
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws MalformedURLException 
     */
    @Get({ "/{groupId:[0-9]+}/{param:k\\S*}/", "/{groupId:[0-9]+}/" })
    public String index(Device device, Portal portal, Invocation inv,
            @Param("cityName") @DefValue("bj") String cityName, @Param("groupId") @DefValue("0") int groupId)
            throws IllegalAccessException, InvocationTargetException, MalformedURLException {
        // 楼盘信息
        BuildingInfo buildingInfo = buildingService.getBuildingShowInfo(groupId);

        if (null == buildingInfo) {
            return "e:404";
        }
        buildingService.genBuildingLog(buildingInfo, false);
        buildingInfo.collateFowShowPage();
        int cityId = buildingInfo.getCityId();
        DictCity city = cityService.getCity(cityId);

        //Condition dirParam = new Condition(param);
        //返回标志 0表示返回历史记录 1 表示返回首页
        int returnFlag = Utils.getBackStatus(inv.getRequest());
        inv.addModel("returnFlag", returnFlag);
        

        // 获取活动信息
        // String activeInf = interfaceService.getActiveInfo(city.getCityId(), groupId);
        // JSONObject activeJson = (JSONObject) JSONObject.parse(activeInf);
        // JSONArray activeData = activeJson.getJSONArray("data");
        String active = buildingInfo.getDiscount();
        if (null == active || StringUtils.isBlank(active) || "待定".equals(active) || "暂无".equals(active)
                || "无".equals(active) || "0".equals(active)) {
            active = null;
        }

        // 判断楼盘经纬度合法性
        if (null != buildingInfo.getLongitude() && null != buildingInfo.getLatitude()) {
            float longitude = Float.parseFloat(buildingInfo.getLongitude().isEmpty() ? "0" : buildingInfo
                    .getLongitude());
            float latitude = Float.parseFloat(buildingInfo.getLatitude().isEmpty() ? "0" : buildingInfo
                    .getLatitude());
            if (longitude < AppConstants.longtitudeMin || longitude > AppConstants.longtitudeMax
                    || latitude < AppConstants.latitudeMin || latitude > AppConstants.latitudeMax) {
                inv.addModel("ditu", "false");
            }
        } else {
            inv.addModel("ditu", "false");
        }

        
        int phone400 = 0;
        if (null != buildingInfo) {
            phone400 = buildingInfo.getPhone400();
        }
        City cityInfo = new City();
        BeanUtils.copyProperties(cityInfo, city);
        cityInfo.setCityStatus(AppConstants.CityPageStatus.getCityStatus(cityInfo.getCityName()));
        cityInfo.setEsfUrl("http://" + city.getCityPinyinAbbr() + "." + CityPageStatus.getEsfUrl());
        cityInfo.setXinfangUrl("/" + city.getCityPinyinAbbr() + "/search/list/");
        cityInfo.setSuggestUrl("/" + city.getCityPinyinAbbr() + "/search/suggest/");
        inv.addModel("cityStr", JSONObject.toJSONString(cityInfo));
        inv.addModel("groupId", groupId);
        inv.addModel("phone400", phone400);
        inv.addModel("_city", city);
        inv.addModel("activeInfo", active);
        inv.addModel("info", buildingInfo);

        String reason = buildingService.getBuildingSpreadWords(buildingInfo.getCityId(),
                buildingInfo.getProjId());
        inv.addModel("reason", reason);
        String whichDevice = DeviceUtils.device(inv, device);
        String buildingName = buildingInfo.getProjName();
        if (buildingName.length() >= 8 && ("phone".equals(whichDevice))) {
            buildingName = buildingName.substring(0, 7) + "...";
        }
        inv.addModel("buildName", buildingName);
        // 户型图
        portal.addWindow("layouts",
                "/portal/building/layouts?groupId=" + groupId + "&cityId=" + buildingInfo.getCityId()
                        + "&device=" + whichDevice);

        // 最新动态
        portal.addWindow("hotnews", "/portal/building/hotnews?projId=" + buildingInfo.getProjId()
                + "&cityId=" + buildingInfo.getCityId() + "&pageNo=0&pageSize=3" + "&device=" + whichDevice);

        // 热门资讯
        portal.addWindow("questions", "/portal/building/questions?cityId=" + buildingInfo.getCityId()
                + "&projId=" + buildingInfo.getProjId() + "&device=" + whichDevice);

        // 周边楼盘
        portal.addWindow("arroundbuilding", "/portal/building/arroundbuilding?groupId=" + groupId
                + "&limit=5" + "&device=" + whichDevice);

        portal.addWindow("buildingpiccount", "/portal/building/buildingpiccount?groupId=" + groupId
                + "&device=" + whichDevice);

        return whichDevice + "/buildinghome";
    }

	
}
