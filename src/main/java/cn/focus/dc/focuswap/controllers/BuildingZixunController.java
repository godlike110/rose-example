package cn.focus.dc.focuswap.controllers;

import cn.focus.dc.focuswap.annotation.AccessLogRequired;
import cn.focus.dc.focuswap.model.BuildingInfo;
import cn.focus.dc.focuswap.model.DictCity;
import cn.focus.dc.focuswap.model.QuestionInfo;
import cn.focus.dc.focuswap.service.BuildingService;
import cn.focus.dc.focuswap.service.CityService;
import cn.focus.dc.focuswap.service.QuestionService;
import cn.focus.dc.focuswap.utils.DeviceUtils;
import cn.focus.dc.focuswap.utils.HtmlDigestUtil;
import cn.focus.dc.focuswap.utils.Utils;

import com.alibaba.fastjson.JSONObject;

import java.net.MalformedURLException;
import java.text.ParseException;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;



@Path("{cityName:[a-zA-Z]{2,}}/loupan")
@AccessLogRequired
public class BuildingZixunController {

    @Autowired
    private CityService cityService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private QuestionService questionService;


    /**
     * @author kanezheng
     * @desc 咨询正文页
     * @url m.focus.cn/bj/loupan/7675/zixun/3901/
     * @time 2013.12.4
     * @param inv
     * @param questionId
     * @param groupId * @param device
     * @return
     * @throws ParseException
     * @throws MalformedURLException 
     */
    @Get("{groupId:[0-9]+}/zixun/{questionId:[0-9]+}/")
    public String getQuizInfo(Invocation inv, Device device, @Param("groupId") Integer groupId,
            @Param("questionId") int questionId, @Param("con") String con, @Param("from") String from)
            throws ParseException, MalformedURLException {
        QuestionInfo questionInfo = questionService.findById(questionId);
        if (null != questionInfo) {
            // ProjInfo projInfo = buildingService.getBuilding(questionInfo.getCityId(), questionInfo.getBuildId());
            BuildingInfo build = buildingService.getBuilding(groupId);
            if(build != null){
                buildingService.genRealPhotoPath(build, true);
                if (StringUtils.isNotBlank(build.getProjAddress()) && build.getProjAddress().length() > 12) {
                    build.setProjAddress(build.getProjAddress().substring(0, 10) + "...");
                }
                // 打折信息
                if ("待定".equals(build.getDiscount().trim()) || "暂无".equals(build.getDiscount().trim())
                        || "无".equals(build.getDiscount().trim()) || "0".equals(build.getDiscount().trim())) {
                    build.setDiscount("");
                }
                buildingService.setBuildingPrice(build);
                
                questionService.decorate(questionInfo, build);
            }
            inv.addModel(questionInfo);
            String answerDigest = StringUtils.EMPTY;
            if (StringUtils.isNotBlank(questionInfo.getAnswer())) {
                answerDigest = HtmlDigestUtil.getDigest(questionInfo.getAnswer(), false);
            }
            DictCity city = cityService.getCity(questionInfo.getCityId());
            JSONObject cityJson = (JSONObject)JSONObject.toJSON(city);
            inv.addModel("city", cityJson);
            inv.addModel("_city", city);
            inv.addModel("bl", build);
            inv.addModel("summary", answerDigest);
            inv.addModel("con", con);
            int returnFlag = Utils.getBackStatus(inv.getRequest());
            inv.addModel("returnFlag", returnFlag);
            if ("share".equals(from)) {
                return DeviceUtils.device(inv, device) + "/askShare";
            } else {
                return DeviceUtils.device(inv, device) + "/ask";
            }
        } else {
            return "e:404";
        }
    }
	
}
