package cn.focus.dc.focuswap.controllers;

import cn.focus.dc.focuswap.annotation.AccessLogRequired;
import cn.focus.dc.focuswap.model.BuildingInfo;
import cn.focus.dc.focuswap.model.DictCity;
import cn.focus.dc.focuswap.model.LineInfo;
import cn.focus.dc.focuswap.model.LineRelatedProject;
import cn.focus.dc.focuswap.model.TipsInfo;
import cn.focus.dc.focuswap.service.BuildingService;
import cn.focus.dc.focuswap.service.CityService;
import cn.focus.dc.focuswap.service.PaFangTuanService;

import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.DefValue;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.common.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;


@Path("{cityName:[a-zA-Z]{2,}}/pafangtuan")
@AccessLogRequired
public class PaFangTuanController {
    
    @Autowired
    private CityService cityService;
    
    @Autowired
    private PaFangTuanService paFangTuanService;
    
    @Autowired
    private BuildingService buildingService;
    
    private Log logger = LogFactory.getLog(PaFangTuanController.class);
    
    
    /**
     * 
     * @desc 爬房团
     * @url m.focus.cn/bj/pafangtuan/3067/
     * @time 2013.12.16
     * @param climbId
     * @return
     */
    @Get("{lineId:[0-9]+}/")
    public String climb(@Param("cityName") @DefValue("bj") String cityName,@Param("lineId") Integer lineId,
            @DefValue("0") Integer test, Invocation inv) {
        //获取城市信息
        DictCity city = cityService.getCityByPinYinIgnoringStatus(cityName);
        if (null == city) {
            return "e:404";
        }
        inv.addModel("_city", city);
        //标识该城市是否是在wap站的城市列表里
        boolean outOfCity = false;
        if (city.getWapStatus() != 1) {
            outOfCity = true;
        }
        //outOfCity = true;
        inv.addModel("outOfCity", outOfCity);
        //获取楼盘信息
        LineInfo lineInfo = paFangTuanService.getLineInfo(city.getCityId(), lineId);
        if (null == lineInfo) {
            return "e:404";
        }
        
        StringBuilder signupUrl = new StringBuilder("/").append(city.getCityPinyinAbbr()).append("/pafangtuan/")
                .append(lineInfo.getLine_id()).append("/enroll/");
        inv.addModel("signupurl",signupUrl.toString());
        
        //格式化时间字段
        String sendDateStr = lineInfo.getSend_date();
        if (StringUtils.isNotBlank(sendDateStr)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat sdf2 = new SimpleDateFormat("MM月dd日");
                Date sendDate = sdf.parse(sendDateStr);
                String formattedSendDate = sdf2.format(sendDate);
                
                DateTime date = new DateTime(sendDate.getTime());
                date = date.minusDays(1);
                Date closeDate = new Date(date.getMillis());
                String formattedCloseDate = sdf2.format(closeDate);
                
                inv.addModel("sendDate",formattedSendDate);
                inv.addModel("closeDate", formattedCloseDate);
                
            } catch (Exception e) {
                logger.error("", e);
            }
        }
        
        //获取相关楼盘信息用于地图显示
        List<LineRelatedProject> projects = lineInfo.getProject();
        StringBuilder groupIds = new StringBuilder();
        StringBuilder groupNames = new StringBuilder();
        int projLoopCount = projects.size() - 1;
        for (int i = 0; i < projLoopCount; i ++){
            LineRelatedProject project = projects.get(i);
            groupIds.append(project.getGroup_id()).append(",");
            groupNames.append(project.getName()).append(";");
        }
        LineRelatedProject lastProject = projects.get(projLoopCount);
        groupIds.append(lastProject.getGroup_id());
        groupNames.append(lastProject.getName());
        inv.addModel("projNames", groupNames.toString());
        
        List<BuildingInfo> relatedBuildingInfoList = null;
        relatedBuildingInfoList = buildingService.getBuildingInfoList(groupIds.toString());
        if (null != relatedBuildingInfoList && relatedBuildingInfoList.size() > 0) {
            StringBuilder lnglatStr = new StringBuilder();
            int loopSize = relatedBuildingInfoList.size() - 1;
            for (int i = 0; i < loopSize; i ++){
                BuildingInfo info = relatedBuildingInfoList.get(i);
                lnglatStr.append(info.getLongitude()).append(",").append(info.getLatitude()).append(";");
            }
            BuildingInfo info = relatedBuildingInfoList.get(loopSize);
            lnglatStr.append(info.getLongitude()).append(",").append(info.getLatitude());
            inv.addModel("lnglats", lnglatStr.toString());
            
        }
        
        inv.addModel("lineInfo", lineInfo);
        
        //获取小贴士（缓存）
        List<TipsInfo> tips = paFangTuanService.getTips(city.getCityId());
        inv.addModel("tips", tips);
        
        return "climbInfo";
    }
    
    /**
     * @desc m.focus.cn/bj/pafangtuan/3067/enroll/
     * @param cityName
     * @param climbId
     * @param inv
     * @param name
     * @param phone
     * @return
     */
    @Post("{lineId:[0-9]+}/enroll/")
    public Object enroll(@Param("cityName") @DefValue("bj") String cityName,
            @Param("lineId") Integer lineId,Invocation inv, @Param("name") String name,
            @Param("phone") String phone){
      //获取城市信息
        DictCity city = cityService.getCityByPinYinIgnoringStatus(cityName);
        if (null == city) {
            return "e:404";
        }
        inv.addModel("_city", city);
        //调用php的insert接口
        JSONObject ret = paFangTuanService.signup(city.getCityId(), lineId, name, phone);
        /*JSONObject ret = new JSONObject();
        ret.put("errorCode", 0);
        ret.put("errorMsg", "报名成功");*/
        return ret;
    }
}
