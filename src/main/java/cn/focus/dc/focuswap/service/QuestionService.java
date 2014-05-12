package cn.focus.dc.focuswap.service;

import static cn.focus.dc.focuswap.config.AppConstants.DEFUALT_ANONYMOUS_USER;
import static cn.focus.dc.focuswap.config.AppConstants.DEFUALT_CITY;
import static cn.focus.dc.focuswap.config.AppConstants.QA_PUBLIC_UID;
import static cn.focus.dc.focuswap.config.AppConstants.QA_PUBLIC_USER_NAME;

import cn.focus.dc.building.model.ProjInfo;
import cn.focus.dc.focuswap.config.QuestionStatusConstants;
import cn.focus.dc.focuswap.dao.QuestionDAO;
import cn.focus.dc.focuswap.dao.UserDetailDAO;
import cn.focus.dc.focuswap.model.CMSManager;
import cn.focus.dc.focuswap.model.DictCity;
import cn.focus.dc.focuswap.model.Question;
import cn.focus.dc.focuswap.model.QuestionInfo;
import cn.focus.dc.focuswap.model.UserDetail;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author rogantian
 */
@Service
public class QuestionService {

    private static Log logger = LogFactory.getLog(QuestionService.class);

    @Autowired
    private QuestionDAO questionDao;

    @Autowired
    private CMSManagerService cmsManagerService;

    @Autowired
    private CityService cityService;

    @Autowired
    private UserDetailDAO userDetailDao;

    
    /**
     * 根据id查询问题的详细内容
     * 
     * @param id
     * @return
     */
    public QuestionInfo findById(int id) {
        return questionDao.findById(id);
    }

    public List<QuestionInfo> getQuestionInfoList(int groupId, int pageNo, int pageSize) {
        return questionDao.getList(groupId, pageNo, pageSize);
    }
    
    public List<Question> getBuildingQuestion(int groupId) {
        if (groupId <= 0) {
            return Collections.emptyList();
        }
        
        return questionDao.getQuestionsByCityIdAndProjId(groupId);
    }

    public void decorate(QuestionInfo questionInfo, ProjInfo projInfo) throws ParseException {
        // Answer
        short questionStatus = questionInfo.getStatus();
        if ((questionStatus != QuestionStatusConstants.QA_Q_UN_PASS_AUDIT 
                && questionStatus != QuestionStatusConstants.QA_PASS_AUDIT)
                || StringUtils.isBlank(questionInfo.getAnswer())) {
            questionInfo.setAnswer(" 小编正在查询信息，请稍候 ");
        }

        Integer cityId = questionInfo.getCityId();

        // build对象
        String buildName = null;
        int groupId = -1;
        try {
            if (null != projInfo) {
                buildName = projInfo.getProjName();
                groupId = projInfo.getGroupId();
                StringBuilder sb = new StringBuilder();
                sb.append("楼盘：").append(buildName);
                questionInfo.setBuildName(sb.toString());
                questionInfo.setGroupId(groupId);
            }

        } catch (Exception e) {
            logger.error("", e);
        }

        // cityName
        if (null != cityId) {
            DictCity buildCity = cityService.getCity(cityId);
            if (null != buildCity) {
                String cityName = buildCity.getCityName();
                if (null != cityName) {
                    questionInfo.setCityName(cityName);
                } else {
                    questionInfo.setCityName(DEFUALT_CITY);
                }
            }
        }

        // Editor
        StringBuilder editorDesc = new StringBuilder();
        Integer editorId = questionInfo.getEditorId();
        if (editorId != null) {
            
            CMSManager cmsManager = cmsManagerService.getCMSManager(editorId);
            if (cmsManager != null) {
                DictCity editorCity = cityService.getCity(cmsManager.getCityId());
                if (editorCity != null) {
                    editorDesc.append(editorCity.getCityName()).append(" ");
                } else {
                    editorDesc.append(DEFUALT_CITY).append("站 ");
                }
                editorDesc.append(cmsManager.getPosition()).append(" ").append(cmsManager.getName());
            }
        }
        questionInfo.setEditorDesc(editorDesc.toString());

        // User
        String userName = StringUtils.EMPTY;
        Integer uid = questionInfo.getUid();
        boolean isAnonymous = questionInfo.getIsAnonymous() == 1;

        if (null == uid || uid == QA_PUBLIC_UID) {
            userName = QA_PUBLIC_USER_NAME;
        } else if (!isAnonymous) {
            UserDetail userDetail = userDetailDao.get(uid, uid);
            if (userDetail != null) {
                userName = userDetail.getNickName();
            }
        } else {
            userName = DEFUALT_ANONYMOUS_USER;
        }
        questionInfo.setUserName(userName);
        // SimpleDateFormat sf = new SimpleDateFormat("yyyy年M月d日");
        // String daCR = sf.format(questionInfo.getCreateTime());
        // questionInfo.setCreateTime(sf.parse(daCR));
        // String daUP = sf.format(questionInfo.getUpdateTime());
        // questionInfo.setUpdateTime(sf.parse(daUP));
    }
}
