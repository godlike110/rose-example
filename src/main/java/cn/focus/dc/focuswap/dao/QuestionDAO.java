package cn.focus.dc.focuswap.dao;

import cn.focus.dc.focuswap.config.QuestionStatusConstants;
import cn.focus.dc.focuswap.model.Question;
import cn.focus.dc.focuswap.model.QuestionInfo;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

@DAO(catalog = "xinfang")
public interface QuestionDAO {

    @SQL("SELECT id, uid, question, answer, city_id, build_id,group_id, editor_id, status, is_answered, "
            + "is_anonymous, useful_count,collections_count, create_time, update_time "
            + " FROM question WHERE id = :1")
    QuestionInfo findById(long questionId);

    /**
     * Description:根据城市id和楼盘id获取热点问题列表,status>=3表示问题已审查通过，回答包括未审核，审核通过和审核未通过
     * 
     * @author qiaowang
     * @param cityId
     * @param projId
     * @return List<Question>
     */
    @SQL("SELECT id, uid, question, answer, city_id, build_id,group_id, editor_id, status, is_answered,"
            + " is_anonymous, useful_count, collections_count, create_time, update_time "
            + "FROM question WHERE group_id = :1 AND status >= "
            + QuestionStatusConstants.QA_Q_PASS_NO_ANSWER + " order by useful_count desc limit 3")
    List<Question> getQuestionsByCityIdAndProjId(int groupId);

    /**
     * 分页根据城市id和楼盘id获取热点问题列表 status>=3表示问题已审查通过，回答包括未审核，审核通过和审核未通过
     * 
     * @param buildId
     * @param cityId
     * @param index
     * @param size
     * @return
     */
    @SQL("SELECT id, uid, question, answer, city_id, build_id,group_id, editor_id, status, is_answered,"
            + " is_anonymous, useful_count, collections_count, create_time, update_time"
            + " FROM question WHERE group_id=:1 and status>="
            + QuestionStatusConstants.QA_Q_PASS_NO_ANSWER + " order by useful_count desc LIMIT :2,:3")
    List<QuestionInfo> getList(int groupId, int index, int size);
}
