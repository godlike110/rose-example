package cn.focus.dc.focuswap.service;

import static cn.focus.dc.focuswap.config.AppConstants.*;

import cn.focus.dc.focuswap.model.LineInfo;
import cn.focus.dc.focuswap.model.TipsInfo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.rubyeye.xmemcached.MemcachedClient;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 爬房团数据相关service
 * @author rogantian
 * @date 2013-12-17
 * @email rogantianwz@gmail.com
 */
@Service
public class PaFangTuanService {

    protected Log logger = LogFactory.getLog(PaFangTuanService.class);
    
    @Autowired
    private MemcachedClient cacheClient;
    
    @Autowired
    private InterfaceService interfaceService;
    
    /**
     * 获取爬房团线路信息
     * @param cityId
     * @param lineId
     * @return
     */
    public LineInfo getLineInfo(Integer cityId, Integer lineId) {
        if (null == cityId || null == lineId) {
            return null;
        }
        
        try {            
            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("_t", System.currentTimeMillis()/1000);
            variables.put("_v", 1);
            variables.put("appid", 2);
            variables.put("city", cityId);
            variables.put("line_id", lineId);
            variables.put("token", generateToken(variables));
            JSONObject ret = interfaceService.getJSONFromInterface(PAFANGTUAN_LINE_INFO_URL, variables);
            logger.info(new StringBuilder("cityId:").append(cityId).append(" lineId:").append(lineId)
                    .append("获取爬房团信息为：").append(ret).toString());
            if (null != ret) {
                Integer errorCode = ret.getInteger("e");
                if (null != errorCode && 9999 == errorCode) {
                    String lineData = ret.getString("data");
                    if (null != lineData) {
                        return JSON.parseObject(lineData, LineInfo.class);
                    } else {
                        logger.error(new StringBuilder("cityId:").append(cityId).append(" lineId:").append(lineId)
                                .append("获取爬房团信息data出错").append(ret).toString());
                    }
                } else {
                    logger.error(new StringBuilder("cityId:").append(cityId).append(" lineId:").append(lineId)
                            .append("获取爬房团信息errorCode出错：").append(ret).toString());
                }
            } else {
                logger.error(new StringBuilder("cityId:").append(cityId).append(" lineId:").append(lineId)
                        .append("获取不到爬房团信息").toString());
            }
        } catch (Exception e) {
            logger.error(
                    new StringBuilder("cityId:").append(cityId).append(" lineId:").append(lineId).append("获取爬房团信息接口异常")
                            .toString(), e);
        }
        return null;
    }
    
    /**
     * 获取爬房团小贴士，该部分先从缓存取，如果缓存没有则从接口调用。
     * @param cityId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<TipsInfo> getTips(Integer cityId) {
        if (null == cityId) {
            return null;
        }
        Object tips = null;
        StringBuilder cacheKeyBuilder = new StringBuilder(CK_PAFANGTUAN_TIPS_LIST).append(":").append(cityId);
        String cacheKey = cacheKeyBuilder.toString();
        try {
            tips = cacheClient.get(cacheKey);
        } catch (Exception e) {
            logger.error("", e);
        }
        
        if (null != tips) {
            return (List<TipsInfo>) tips;
        }
        
        try {
            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("_t", System.currentTimeMillis()/1000);
            variables.put("city", cityId);
            variables.put("_v", 1);
            variables.put("appid", 2);
            variables.put("token", generateToken(variables));
            JSONObject ret = interfaceService.getJSONFromInterface(PAFANGTUAN_TIPS_URL, variables);
            logger.info(new StringBuilder("cityId:").append(cityId).append("获取tipsInfo信息为：").append(ret).toString());
            if (null != ret) {
                Integer errorCode = ret.getInteger("e");
                if (null != errorCode && 9999 == errorCode) {
                    JSONObject dataWrap = ret.getJSONObject("data");
                    if (null != dataWrap) {
                        String tipsData = dataWrap.getString("data");
                        List<TipsInfo> tipsRet = JSONObject.parseArray(tipsData, TipsInfo.class);
                        cacheClient.setWithNoReply(cacheKey, CE_PAFANGTUAN_TIPS_LIST, tipsRet);
                        return tipsRet;
                    } else {
                        logger.error(new StringBuilder("cityId:").append(cityId).append("获取tipsInfo的data出错：")
                                .append(ret).toString());
                    }
                } else {
                    logger.error(new StringBuilder("cityId:").append(cityId).append("获取tipsInfo的errorCode出错：")
                            .append(ret).toString());
                }
            } else {
               logger.error(new StringBuilder("cityId:").append(cityId).append("获取不到tipsInfo").toString()); 
            }
        } catch (Exception e) {
            logger.error(new StringBuilder("cityId:").append(cityId).append("获取tipsInfo接口异常：").toString());
        }
        return null;
    }
    
    /**
     * 调用php接口报名爬房团
     * @param cityId
     * @param lineId
     * @param name
     * @param mobile
     * @return
     */
    public JSONObject signup(Integer cityId, Integer lineId, String name, String mobile) {
        JSONObject ret = null;
        if (null == cityId || null == lineId || StringUtils.isBlank(name) 
                || StringUtils.isBlank(mobile)) {
            ret = new JSONObject();
            ret.put("errorCode", -1);
            ret.put("errorMsg", "报名失败");
        }
        
        Map<String, Object> variables = new HashMap<String, Object>();
       
        try {
            variables.put("_t", System.currentTimeMillis()/1000);
            variables.put("line_id", lineId);
            //variables.put("real_name", URLEncoder.encode(name, "UTF-8"));
            variables.put("mobile", mobile);
            variables.put("city", cityId);
            variables.put("_v", 1);
            variables.put("appid", 2);
            variables.put("real_name", name);
            variables.put("token", generateToken(variables));
            ret = interfaceService.getJSONFromInterfaceByPost(PAFANGTUAN_SIGNUP_URL, variables);
            logger.info(new StringBuilder("爬房团用户报名[lineId:").append(lineId).append(" cityId:").append(cityId)
                    .append(" name:").append(name)
                    .append(" mobile:").append(mobile).append("]返回值为").append(ret));
            if (null == ret) {
                throw new Exception("接口返回值为空");
            }
            Integer errorCode = ret.getInteger("e");
            ret = new JSONObject();
            if (9999 == errorCode) {
                ret.put("errorCode", 0);
                ret.put("errorMsg", "报名成功");
            } else if (3008 == errorCode || 3009 == errorCode) {
                ret.put("errorCode", -2);
                ret.put("errorMsg", "这个爬房团已下线，请换一个");
            } else if (2117 == errorCode) {
                ret.put("errorCode", -3);
                ret.put("errorMsg", "您的手机号已经在电脑上登录，请在电脑上报名");
            } else if (2104 == errorCode) {
                ret.put("errorCode", -4);
                ret.put("errorMsg", "请输入正确的姓名");
            } else if (2105 == errorCode) {
                ret.put("errorCode", -5);
                ret.put("errorMsg", "请输入正确的手机号");
            } else {
                ret.put("errorCode", -6);
                ret.put("errorMsg", "报名失败，请稍后重试");
            }
        } catch (Exception e) {
            logger.error("", e);
            ret = new JSONObject();
            ret.put("errorCode", -1);
            ret.put("errorMsg", "报名失败，请稍后重试");
        }
        
        return ret;
    }
    
    private String generateToken(Map<String, Object> variables) {
        TreeMap<String,Object> tmpMap = new TreeMap<String, Object>(variables);
        StringBuilder sb = new StringBuilder("_k=");
        sb.append(PAFANGTUAN_SECRETKEY);
        
        if (null == variables) {
            return DigestUtils.md5Hex(sb.toString());
        } else {            
            for (String key : tmpMap.keySet()) {
                sb.append(key).append("=").append(tmpMap.get(key)).append("&");
            }
            return DigestUtils.md5Hex(StringUtils.chomp(sb.toString(), "&"));
        }
    }
    
}
