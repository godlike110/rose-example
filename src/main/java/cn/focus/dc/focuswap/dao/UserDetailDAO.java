package cn.focus.dc.focuswap.dao;

import cn.focus.dc.focuswap.model.UserDetail;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.ShardBy;

@DAO(catalog = "xinfang")
public interface UserDetailDAO {

    @SQL("SELECT  uid, app_id, nick_name, head_img, age, down_payment, is_accepted, income, city, "
            + "target_area, follow_count, favorite_count, quiz_count, update_time "
            + "FROM $user_detail WHERE uid = :1")
        UserDetail get(Integer id  ,@ShardBy int uid);
}
