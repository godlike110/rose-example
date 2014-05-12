package cn.focus.dc.focuswap.controllers;

import cn.focus.dc.focuswap.annotation.AccessLogRequired;
import cn.focus.dc.focuswap.config.AppConstants;
import cn.focus.dc.focuswap.config.AppConstants.CityPageStatus;
import cn.focus.dc.focuswap.model.City;
import cn.focus.dc.focuswap.model.DictCity;
import cn.focus.dc.focuswap.model.DictCityPriceExt;
import cn.focus.dc.focuswap.model.DictDistrictExt;
import cn.focus.dc.focuswap.service.BuildingSearchService;
import cn.focus.dc.focuswap.service.CityService;
import cn.focus.dc.focuswap.service.SearchService.SearchType;
import cn.focus.dc.focuswap.utils.DeviceUtils;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.DefValue;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;
import net.paoding.rose.web.portal.Portal;
import net.paoding.rose.web.portal.PortalSetting;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;

/**
 * SEO优化跳转后的首页
 * 
 * @author zhiweiwen 2013-12-3 下午4:20:11
 */
@Path("/")
@AccessLogRequired
public class IndexController {

    // 打折楼盘
    public static final String DZLP = "DZLP";

    // 小户型
    public static final String XHX = "XHX";

    // 最新开盘
    public static final String ZXKP = "ZXKP";

    @Autowired
    private CityService cityService;

    @Autowired
    private BuildingSearchService buildingSearchService;

    @SuppressWarnings("unchecked")
    @Post({ "/{cityName:[a-z]{2,}}/", "/home/", "/home/city/{cityId:[0-9]+}" })
    @Get({ "/{cityName:[a-z]{2,}}/", "/home/", "/home/city/{cityId:[0-9]+}" })
    @PortalSetting(timeout=2000)
    public String homeIndex(Device device, Invocation inv, Portal portal, @Param("cityId") Integer cityId,
            @Param("cityName") @DefValue("") String cityName, @Param("group_name") String search)
            throws IllegalAccessException,
            InvocationTargetException {
        DictCity city = null;
        if(null!=cityName&&!("".equals(cityName))) {
           city = cityService.getCityByNameOrPinYin(cityName);
           if(null==city) {
               return "e:404";
           }
        }
        if (null != cityId) {
            city = cityService.getCityWithDefault(cityId);
            cityService.setCityLocated(inv, city);
        } else {
            if (null == city) {
                city = cityService.getCityLocatedFromCookie(inv);
                if (null == city) {
                    // 页面使用默认城市
                    city = cityService.getDefaultCity();
                    inv.addModel("needLBS", true);
                }
            }
        }
        City cityInfo = new City();
        BeanUtils.copyProperties(cityInfo, city);
        String whichDevice = DeviceUtils.device(inv, device);
        
        //城市自住房状态
        int zzfStatus = AppConstants.ZzfCityStatus.getCityZzfStatus(cityInfo.getCityPinyinAbbr());
        inv.addModel("zzfstatus", zzfStatus);
        
        // 主编推荐
        cityInfo.setCityStatus(AppConstants.CityPageStatus.getCityStatus(cityInfo.getCityName()));
        cityInfo.setEsfUrl("http://" + city.getCityPinyinAbbr() + "." + CityPageStatus.getEsfUrl());
        cityInfo.setXinfangUrl("/" + city.getCityPinyinAbbr() + "/search/list/");
        cityInfo.setSuggestUrl("/" + city.getCityPinyinAbbr() + "/search/suggest/");

        inv.addModel("_city", cityInfo);
     //   portal.addWindow("recommend", "/portal/home/recommend?cityId=" + cityInfo.getCityId() + "&device="
     //           + whichDevice);
        // 购房宝典
        portal.addWindow("baodian", "/portal/home/baodian?cityId=" + cityInfo.getCityId() + "&device="
                + whichDevice);
        // 新闻资讯
        portal.addWindow("news", "/portal/home/news?cityId=" + cityInfo.getCityId() + "&device="
                + whichDevice);
        // 打折楼盘
        portal.addWindow("dazheloupan", "/portal/home/dazheloupan?cityId=" + cityInfo.getCityId()
                + "&device=" + whichDevice);
        // 最新开盘
        portal.addWindow("zuixinkaipan", "/portal/home/zuixinkaipan?cityId=" + cityInfo.getCityId()
                + "&device=" + whichDevice);
        // 小户型
        portal.addWindow("xiaohuxing", "/portal/home/xiaohuxing?cityId=" + cityInfo.getCityId() + "&device="
                + whichDevice);
                
        // 导购
        portal.addWindow("daogou", "/portal/home/daogou?cityId=" + cityInfo.getCityId() + "&device="
                + whichDevice);

        inv.addModel("cityStr", JSONObject.toJSONString(cityInfo));
        // 只有二手房城市重定向(沈阳 濮阳)
        if (CityPageStatus.getCityStatus(city.getCityName()) == CityPageStatus.ESF) {
            return "r:http://" + city.getCityPinyinAbbr() + "." + CityPageStatus.getEsfUrl();
        }
        Map<SearchType, Object> conditionMap = buildingSearchService.getConditions(city.getCityId(), false);
        // 获取价格条件
        List<DictCityPriceExt> priceList = (List<DictCityPriceExt>) conditionMap.get(SearchType.PRICE);

        inv.addModel("priceList", priceList);
        // 获取区域条件
        List<DictDistrictExt> distList = (List<DictDistrictExt>) conditionMap.get(SearchType.DISTRICT);
        inv.addModel("hotList", distList);
        inv.addModel("mobile", device.isMobile());
        if (StringUtils.isNotBlank(search)) {
            return "f:/" + city.getCityPinyinAbbr() + "/search/list/?group_name=" + search;
        }
        return whichDevice + "/home";
    }

    @Get("setdevice/{cityName:[a-z]+}/{type:[a-z]+}/")
    public String setDevice(Invocation inv, @Param("type") String type, @Param("cityName") String cityName) {
        return "r:/" + cityName + "/";
    }

}
