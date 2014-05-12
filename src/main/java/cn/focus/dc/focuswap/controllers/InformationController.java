package cn.focus.dc.focuswap.controllers;

import cn.focus.dc.focuswap.annotation.AccessLogRequired;
import cn.focus.dc.focuswap.model.DictCity;
import cn.focus.dc.focuswap.model.Information;
import cn.focus.dc.focuswap.model.News;
import cn.focus.dc.focuswap.service.CityService;
import cn.focus.dc.focuswap.service.NewsService;
import cn.focus.dc.focuswap.utils.DateUtils;
import cn.focus.dc.focuswap.utils.DeviceUtils;

import com.alibaba.fastjson.JSONObject;

import java.text.ParseException;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.DefValue;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.portal.Portal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;


@Path("{cityName:[a-zA-Z]{2,}}/zixun")
@AccessLogRequired
public class InformationController {

    private static Log logger = LogFactory.getLog(InformationController.class);

    @Autowired
    private NewsService newsService;
    
    @Autowired
    private CityService cityService;
    /**
     * @author kanezheng
     * @url  local.focus.cn:8080/bj/zixun/4002710/
     * @desc 资讯正文页
     * @url 2013-10-24 @下午14:10:33
     * @time 2013-10-24 @下午14:10:33
     * @param inv
     * @return
     */
    @Get({ "{infoId:[0-9]+}/{pageNo:[0-9]+}/", "{infoId:[0-9]+}/" })
    public String getNews(Device device, Invocation inv, Portal portal,
            @Param("cityName") @DefValue("bj") String cityName, @Param("infoId") Integer infoId,
            @Param("pageNo") @DefValue("1") Integer pageNo) {
        DictCity city = cityService.getCityByNameOrPinYin(cityName);
        // 为了拦截不再获取第二次
        inv.addModel("_city", city);    
        JSONObject cityJson = (JSONObject)JSONObject.toJSON(city);
        inv.addModel("city", cityJson);
        Information news = newsService.getNews(infoId, city.getCityId(),pageNo,cityName);
        if (news == null) {
            return "e:404";
        }
        decorate(news);
        if(news.getPageContent() != null){
            String content = news.getPageContent().getContent();
            String []pContent = content.split("</p>|</P>");
            String frontContent = "";
            String backContent = "";
            for(int i=0;i<pContent.length;i++){
                if(i < 5){
                    frontContent += pContent[i]+"</p>";
                }else{
                    backContent += pContent[i]+"</p>";
                }
            }
            inv.addModel("news", news);
            inv.addModel("frontContent", frontContent);
            inv.addModel("backContent", backContent);
        }else{
            logger.error("error! there is not content,please check newsCenterInterface and newsId = " + infoId
                    + "and cityName=" + cityName);
        }
        
       
        String whichDevice = DeviceUtils.device(inv, device);
        //relate news
        portal.addWindow("infos", "/portal/info/infos?infoId=" + infoId + "&pageNo=" + pageNo + "&device="
                + whichDevice + "&cityName=" + city.getCityPinyinAbbr());
         
        inv.addModel("mobile", device.isMobile());
        return DeviceUtils.device(inv, device) + "/information";
    }  

    private static void decorate(News news){
        try{
            news.setTime(DateUtils.stringPatternThrow(news.getTime(), "yyyy年MM月dd", "yyyy年M月d日"));
        }catch(ParseException e){
            news.setTime(DateUtils.stringPattern(news.getTime(), "yyyy-MM-dd", "yyyy年M月d日"));
        }
    }
}
