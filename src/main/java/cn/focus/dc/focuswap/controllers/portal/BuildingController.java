package cn.focus.dc.focuswap.controllers.portal;


import cn.focus.dc.focuswap.config.AppConstants.Layout;
import cn.focus.dc.focuswap.model.BuildingAroundInfo;
import cn.focus.dc.focuswap.model.BuildingLayoutPhotoes;
import cn.focus.dc.focuswap.model.ProjPhotoesExt;
import cn.focus.dc.focuswap.model.Question;
import cn.focus.dc.focuswap.model.RecentNews;
import cn.focus.dc.focuswap.service.BuildingService;
import cn.focus.dc.focuswap.service.QuestionService;
import cn.focus.dc.focuswap.service.RencentNewsService;

import java.util.List;
import java.util.Map;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Path("building")
public class BuildingController {

    private static Logger logger = LoggerFactory.getLogger(BuildingController.class);

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private QuestionService questionService;
    
    @Autowired
    private RencentNewsService recentNewsService;

    @Get("test")
    public String test() {
        return "@test";
    }

    /**
     * 最新动态
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    @Get("hotnews")
    public String getHotNews(Invocation inv, @Param("projId") int projId, @Param("cityId") int cityId,
            @Param("pageNo") int pageNo, @Param("pageSize") int pageSize, @Param("device") String device) {
        try {
            //最新动态
            Map<String, Object> recentNewsResult = recentNewsService.getRecentNewsList(projId,
                    cityId, pageNo, pageSize);
            List<RecentNews> recentNewsList = null;
            if (null != recentNewsResult) {
                Object recentNewsObject = recentNewsResult.get("recentNewsList");
                if (null != recentNewsObject) {
                    recentNewsList = (List<RecentNews>) recentNewsObject;
                    inv.addModel("recentNews", recentNewsList);
                    inv.addModel("qcount", recentNewsList.size());
                }
            }
            return "buildinghome/" + device + "/news";
        } catch (Exception e) {
            logger.info("", e.getMessage());
            return null;
        }
    }

    /**
     * 热门资讯
     * 
     * @return
     */
    @Get("questions")
    public String getQuestion(Invocation inv, @Param("groupId") int groupId,@Param("device") String device) {
        try {

            // 热门资讯
            List<Question> questions = questionService.getBuildingQuestion(groupId);
            inv.addModel("questions", questions);
            inv.addModel("qcount", questions.size());
            return "buildinghome/" + device + "/question";
        } catch (Exception e) {
            logger.info("", e.getMessage());
            return null;
        }
    }

    /**
     * 周边楼盘
     * 
     * @return
     */
    @Get("arroundbuilding")
    public String getArroundBuilding(Invocation inv, @Param("groupId") int groupId, @Param("limit") int limit,
            @Param("device") String device) {
        try {

            List<BuildingAroundInfo> aroundBuildings = buildingService.getAroundBuildings(groupId, limit);
            aroundBuildings = buildingService.setArroundBuildingListPrice(aroundBuildings);
            if("phone".equals(device)) {
            for(BuildingAroundInfo info:aroundBuildings) {
                if(info.getProjName().length()>8) {
                    String name = info.getProjName();
                    name = name.substring(0, 7) + "...";
                    info.setProjName(name);
                }
            }
            }
            inv.addModel("arroundbuilding", aroundBuildings);
            return "buildinghome/" + device + "/arroundbuilding";
        } catch (Exception e) {
            logger.info("", e.getMessage());
            return null;
        }
    }
    
    /**
     * 户型图
     * @param inv
     * @param groupId
     * @param cityId
     * @param device
     * @return
     */
    @Get("layouts")
    public String getLayout(Invocation inv, @Param("groupId") int groupId, @Param("cityId") int cityId,
            @Param("device") String device) {
        int layoutCount = 0;
        try {
            List<BuildingLayoutPhotoes> buildingLayoutPhotoes = buildingService.getBuildingPicInfo(groupId);
            layoutCount = buildingLayoutPhotoes.size();            
            if (null != buildingLayoutPhotoes && layoutCount > 0) {
                for (BuildingLayoutPhotoes photos : buildingLayoutPhotoes) {
    
                    List<ProjPhotoesExt> list = photos.getPhotoes();
                    float min = list.get(0).getBuildArea()==null?0:list.get(0).getBuildArea();
                    float max = list.get(0).getBuildArea()==null?0:list.get(0).getBuildArea();
                    for(ProjPhotoesExt photo:list) {
                        if(photo.getBuildArea()==null) {
                            photo.setBuildArea((float) 0);
                        }
                        if(photo.getBuildArea()>max) {
                            max=photo.getBuildArea();
                        } 
                        if(photo.getBuildArea()<min&&photo.getBuildArea()>0) {
                            min = photo.getBuildArea();   
                        }
                        if(min<=1.0 && photo.getBuildArea()<max) {
                            min = photo.getBuildArea();
                        }
                    }
                    int a = (int) Math.ceil(min);
                    int b = (int) Math.ceil(max);
                    if(b==0) {
                        photos.setMinMaxArea(Layout.getLayoutName(photos.getType().toString()));
                    } else if(a==b || a==0) {
                        photos.setMinMaxArea(Layout.getLayoutName(photos.getType().toString()) + "  " + b + "平米");
                    } else {
                    photos.setMinMaxArea(Layout.getLayoutName(photos.getType().toString())+"  " + a + "--" + b + "平米");
                    }
                }
                inv.addModel("layouts", buildingLayoutPhotoes);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        inv.addModel("layoutCount", layoutCount);
        return "buildinghome/" + device + "/layout";
    }
    
    /**
     * 获取楼盘图片的张数
     * @param inv
     * @param groupId
     * @return
     */
    @Get("buildingpiccount")
    public String countBuildingPics(Invocation inv, @Param("groupId") int groupId, @Param("device") String device) {
        try {
            List<ProjPhotoesExt> projPhotoes = buildingService.getBuildingPic(groupId);
            if (null != projPhotoes && projPhotoes.size() > 0) {
                inv.addModel("photocounts", projPhotoes.size());
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return "buildinghome/" + device + "/photocount";
    }

}
