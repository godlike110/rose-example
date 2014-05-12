package cn.focus.dc.focuswap.controllers.portal;

import cn.focus.dc.focuswap.model.Information;
import cn.focus.dc.focuswap.service.NewsService;

import java.util.List;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import org.springframework.beans.factory.annotation.Autowired;

@Path("info")
public class RecentInfoController {
    
    @Autowired
    private NewsService newsService;
    
    /**
     * @desc  相关新闻的portal
     * @param inv
     * @param infoId
     * @param pageNo
     * @return
     */
    @Get("infos")
    public String getInfos(@Param("device") String device, Invocation inv, @Param("infoId") int infoId,
            @Param("pageNo") int pageNo, @Param("cityName") String cityName) {
        List<Information> relate_news = null;
        try {
            relate_news = newsService.getRelateNews(infoId, pageNo);
            inv.addModel("relate_news", relate_news);
            inv.addModel("cityName", cityName);
            return "info/" + device + "/infos";
        } catch (Exception e) {
            return "";
        }
    }
}
