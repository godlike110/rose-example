package cn.focus.dc.focuswap.controllers;

import cn.focus.dc.focuswap.annotation.AccessLogRequired;
import cn.focus.dc.focuswap.model.BuildingInfo;
import cn.focus.dc.focuswap.model.DictCity;
import cn.focus.dc.focuswap.model.RecentNews;
import cn.focus.dc.focuswap.service.BuildingService;
import cn.focus.dc.focuswap.service.CityService;
import cn.focus.dc.focuswap.service.NewsService;
import cn.focus.dc.focuswap.service.RencentNewsService;
import cn.focus.dc.focuswap.utils.DateUtils;
import cn.focus.dc.focuswap.utils.DeviceUtils;
import cn.focus.dc.focuswap.utils.Utils;

import com.alibaba.fastjson.JSONObject;
import com.sohu.sce.repackaged.net.rubyeye.xmemcached.exception.MemcachedException;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;

import net.paoding.rose.web.annotation.DefValue;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.Invocation;

@Path("{cityName:[a-zA-Z]{2,}}/loupan")
@AccessLogRequired
public class BuildingDongtaiController {

	
    @Autowired
    private CityService cityService;


    @Autowired
    private BuildingService buildingService;

    @Autowired
    private RencentNewsService rencentNewsService;

    @Autowired
    private NewsService newsService;
    
	
    /**
     * @author kanezheng
     * @desc 动态详情页
     * @url m.focus.cn/bj/loupan/7675/dongtai/2578/
     * @param inv
     * @param itemId
     * @param device
     * @return
     * @throws net.rubyeye.xmemcached.exception.MemcachedException
     * @throws MalformedURLException 
     */
    @Get("{groupId:[0-9]+}/dongtai/{itemId:[0-9]+}/")
    public String getDongTai(Invocation inv, Device device, @Param("groupId") Integer groupId,
            @Param("itemId") Integer itemId, @Param("cityName") @DefValue("bj") String cityName,
            @Param("con") String con) throws InterruptedException, MemcachedException, TimeoutException,
            net.rubyeye.xmemcached.exception.MemcachedException, MalformedURLException {
        DictCity city = cityService.getCityByNameOrPinYin(cityName);
        RecentNews recentNews = rencentNewsService.getRecentNews(itemId, city.getCityId());
        int phone400 = -1;
        String groupName = "";
        if (recentNews == null) {
            return "e:404";
        }
        List<BuildingInfo> pros = new ArrayList<BuildingInfo>();
        // 判断动态是否有关联楼盘
        if (recentNews.getGroup_ids() != null) {
            for (Integer gId : recentNews.getGroup_ids()) {
                BuildingInfo proj = buildingService.getBuilding(gId);
                if(proj != null){
                    // 修改实体里面图片url
                    buildingService.genRealPhotoPath(proj, true);
                    if (StringUtils.isNotBlank(proj.getProjAddress()) && proj.getProjAddress().length() > 12) {
                        proj.setProjAddress(proj.getProjAddress().substring(0, 10) + "...");
                    }
                    // 打折信息
                    
                    if ("待定".equals(proj.getDiscount().trim()) || "暂无".equals(proj.getDiscount().trim())
                            || "无".equals(proj.getDiscount().trim()) || "0".equals(proj.getDiscount().trim())) {
                        proj.setDiscount("");
                    }
                    pros.add(proj);
                    // 获取第一个楼盘的客服电话
                    // if (phone400 == -1) {
                    // phone400 = proj.getPhone400() == null ? 0 : proj.getPhone400();
                    // }
                    //groupName = proj.getProjName();
                    buildingService.setBuildingPrice(proj);
                }
            }

        }
        // else防止php接口有问题

        BuildingInfo proj = buildingService.getBuilding(groupId);
        if(proj != null){
            //buildingService.genRealPhotoPath(proj, true);
            phone400 = proj.getPhone400();
            groupName = proj.getProjName();
        }
        recentNews.setPros(pros);
        String content = Utils.encodeContent(recentNews.getContent());
        content = newsService.filterTagDongtai(content);
        recentNews.setContent(content);
        try {
            recentNews.setInfotime(DateUtils.stringPatternThrow(recentNews.getInfotime(), "yyyy年MM月dd",
                    "yyyy年M月d日"));
        } catch (ParseException e) {
            recentNews.setInfotime(DateUtils.stringPattern(recentNews.getInfotime(), "yyyy-MM-dd",
                    "yyyy年M月d日"));
        }

        // 为了拦截不再获取第二次
        inv.addModel("_city", city);
        JSONObject cityJson = (JSONObject)JSONObject.toJSON(city);
        inv.addModel("city", cityJson);
        inv.addModel("phone400", phone400);
        inv.addModel("groupName", groupName);
        inv.addModel("group_id", groupId);
        inv.addModel("recentNews", recentNews);
        inv.addModel("con", con);
        int returnFlag = Utils.getBackStatus(inv.getRequest());
        inv.addModel("returnFlag", returnFlag);
        return DeviceUtils.device(inv, device) + "/dynamic";
    }
	
	
}
