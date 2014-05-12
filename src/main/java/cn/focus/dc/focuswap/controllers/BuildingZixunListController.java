package cn.focus.dc.focuswap.controllers;

import static cn.focus.dc.focuswap.config.AppConstants.HASNEXT;
import static cn.focus.dc.focuswap.config.AppConstants.PAGE_NO;
import static cn.focus.dc.focuswap.config.AppConstants.PAGE_SIZE;

import cn.focus.dc.focuswap.annotation.AccessLogRequired;
import cn.focus.dc.focuswap.model.BuildingInfo;
import cn.focus.dc.focuswap.model.DictCity;
import cn.focus.dc.focuswap.model.QuestionInfo;
import cn.focus.dc.focuswap.service.BuildingService;
import cn.focus.dc.focuswap.service.CityService;
import cn.focus.dc.focuswap.service.QuestionService;
import cn.focus.dc.focuswap.utils.DeviceUtils;
import cn.focus.dc.focuswap.utils.JsonResponseUtil;
import cn.focus.dc.focuswap.utils.Utils;

import java.net.MalformedURLException;
import java.text.ParseException;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.DefValue;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;


import java.util.HashMap;
import java.util.List;
import java.util.Map;




@Path("{cityName:[a-zA-Z]{2,}}/loupan")
@AccessLogRequired
public class BuildingZixunListController {


    private static final int PAGE_SIZE_NUMBER = 10;
	
    @Autowired
    private CityService cityService;

    @Autowired
    private BuildingService buildingService;
    
    @Autowired
    private QuestionService questionService;


    /**
     * @author kanezheng
     * @desc 咨询列表
     * @url m.focus.cn/bj/loupan/7675/zixun/
     * @time 2013.12.4
     * @param inv
     * @param device
     * @param groupId
     * @return
     * @throws ParseException
     * @throws MalformedURLException 
     */
    @Get("{groupId:[0-9]+}/zixun/")
    public String getQuizlist(Invocation inv, Device device,
            @Param("cityName") @DefValue("bj") String cityName, @Param("groupId") int groupId,
            @Param("con") String con) throws ParseException, MalformedURLException {
        DictCity city = cityService.getCityByNameOrPinYin(cityName);
        inv.addModel("_city", city);
        inv.addModel("con", con);
        BuildingInfo build = buildingService.getBuilding(groupId);
        inv.addModel("bl", build);
        List<QuestionInfo> questionList = questionService.getQuestionInfoList(groupId, 0,
                PAGE_SIZE_NUMBER + 1);
        for (QuestionInfo questionInfo : questionList) {
            questionService.decorate(questionInfo, build);
        }
        inv.addModel("questionList", questionList);
        StringBuilder sb = new StringBuilder("/loupan/").append(groupId + "").append("/zixunajax/");
        StringBuilder single = new StringBuilder("/loupan/").append(groupId + "").append("/zixun/");
        inv.addModel("city", Utils.putAjaxUrl(city, sb, single));
        if (questionList != null && questionList.size() > PAGE_SIZE_NUMBER) {
            inv.addModel("hasNext", true);
            questionList.remove(PAGE_SIZE_NUMBER);
        }
        int returnFlag = Utils.getBackStatus(inv.getRequest());
        inv.addModel("returnFlag", returnFlag);
        return DeviceUtils.device(inv, device) + "/askList";
    }
    

    /**
     * 咨询列表，ajax请求分页
     * 
     * @param inv
     * @param pageNo
     * @param pageSize
     * @param buildId
     * @param cityId
     * @return
     * @throws ParseException
     */
    @Get("{groupId:[0-9]+}/zixunajax/")
    public String getQuizListAjax(Invocation inv, @Param(PAGE_NO) @DefValue("2") int pageNo,
            @Param(PAGE_SIZE) @DefValue("10") int pageSize, @Param("groupId") int groupId,
            @Param("cityName") @DefValue("bj") String cityName, @Param("callback") String fun)
            throws ParseException {

        // if (pageNo <= 0 || pageSize <= 0 || buildId <= 0 || cityId <= 0) {
        // return JsonResponseUtil.badResult("输入参数有误");
        // }
        BuildingInfo build = buildingService.getBuilding(groupId);
        // DictCity city = cityService.getCityByNameOrPinYin(cityName);
        List<QuestionInfo> questionList = questionService.getQuestionInfoList(groupId, (pageNo - 1)
                * pageSize, pageSize + 1);
        for (QuestionInfo questionInfo : questionList) {
            questionService.decorate(questionInfo, build);
        }
        Map<String, Object> conditions = new HashMap<String, Object>();
        boolean hasNext = false;
        if (questionList.size() > pageSize) {
            questionList.remove(pageSize);
            hasNext = true;
        }
        conditions.put(PAGE_NO, pageNo);
        conditions.put(PAGE_SIZE, pageSize);
        conditions.put(HASNEXT, hasNext);

        return JsonResponseUtil.okSupportJSONPWithPaginate(questionList, fun, conditions);

    }
	
}
