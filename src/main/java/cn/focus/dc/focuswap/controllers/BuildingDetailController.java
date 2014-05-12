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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;

@Path("{cityName:[a-zA-Z]{2,}}/loupan")
@AccessLogRequired
public class BuildingDetailController {

    private static Log logger = LogFactory.getLog(BuildingDetailController.class);


    @Autowired
    private CityService cityService;


    @Autowired
    private BuildingService buildingService;

    /**
     * 楼盘详细信息
     * 
     * @param inv
     * @param cityName
     * @param groupId
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws MalformedURLException 
     */
    @Get("/{groupId:[0-9]+}/xiangxi/")
    public String buildingInfo(Device device, Invocation inv,
            @Param("cityName") @DefValue("bj") String cityName, @Param("groupId") @DefValue("0") int groupId)
            throws IllegalAccessException, InvocationTargetException, MalformedURLException {
        BuildingInfo buildingInfo = buildingService.getBuildingDetailInfo(groupId);
        if (buildingInfo == null) {
            return "e:404";
        }
        buildingInfo.collateForDetailPage();

        DictCity city = null;
        try {
            city = cityService.getCity(buildingInfo.getCityId());
        } catch (Exception e) {
            logger.error("", e);
        }
        City cityInfo = new City();
        BeanUtils.copyProperties(cityInfo, city);
        cityInfo.setCityStatus(AppConstants.CityPageStatus.getCityStatus(cityInfo.getCityName()));
        cityInfo.setEsfUrl("http://" + city.getCityPinyinAbbr() + "." + CityPageStatus.getEsfUrl());
        cityInfo.setXinfangUrl("/" + city.getCityPinyinAbbr() + "/search/list/");
        cityInfo.setSuggestUrl("/" + city.getCityPinyinAbbr() + "/search/suggest/");
        inv.addModel("cityStr", JSONObject.toJSONString(cityInfo));
        inv.addModel("_city", cityInfo);
        int phone400 = 0;
        if (null != buildingInfo) {
            phone400 = buildingInfo.getPhone400();
        }
        inv.addModel("phone400", phone400);
        inv.addModel("groupId", groupId);
        inv.addModel("detail", buildingInfo);
        String whichDevice = DeviceUtils.device(inv, device);

        String buildingName = buildingInfo.getProjName();
        if (buildingName.length() >= 5 && ("phone".equals(whichDevice))) {
            buildingName = buildingName.substring(0, 4) + "...";
        }
        //返回标志 0表示返回历史记录 1 表示返回首页
        int returnFlag = Utils.getBackStatus(inv.getRequest());
        inv.addModel("returnFlag", returnFlag);
        inv.addModel("buildName", buildingName);

        return whichDevice + "/buildingdetail";
    }

}
