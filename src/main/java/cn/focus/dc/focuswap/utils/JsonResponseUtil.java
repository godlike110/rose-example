package cn.focus.dc.focuswap.utils;

import com.alibaba.fastjson.JSONObject;

import com.google.common.collect.ImmutableMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * JsonResponse class.
 * </p>
 * 
 * @author xingtaoshi@gmail.com json工具类
 * @version $Id: $Id
 */
public final class JsonResponseUtil {

    private static final String JSON_ERRORCODE_NAME = "errorCode";

    private static final String JSON_ERRORMSG_NAME = "errorMessage";

    private static final String JSON_DATA_NAME = "data";

    private JsonResponseUtil() {

    }

    /**
     * <p>
     * badResult.
     * </p>
     * 
     * @param cause a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public static String badResult(String cause) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 1);
        result.put(JSON_ERRORMSG_NAME, cause);
        return "@" + result.toJSONString();
    }

    /**
     * <p>
     * emptyResult.
     * </p>
     * 
     * @param cause a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public static String emptyResult(String cause) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 2);
        result.put(JSON_ERRORMSG_NAME, cause);
        return "@" + result.toJSONString();
    }

    /**
     * jsonp请求失败
     * 
     * @param cause a {@link java.lang.String} object.
     * @param callback a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public static String badResult(String cause, String callback) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 1);
        result.put(JSON_ERRORMSG_NAME, cause);
        return "@" + callback + "(" + result.toJSONString() + ")";
    }

    /**
     * <p>
     * badResult.
     * </p>
     * 
     * @param cause a {@link java.util.Map} object.
     * @return a {@link java.lang.String} object.
     */
    public static String badResult(Map<String, String> cause) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 1);
        result.put(JSON_ERRORMSG_NAME, cause);
        return "@" + result.toJSONString();
    }

    /**
     * 区分错误类型
     * 
     * @param type a int.
     * @param cause a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public static String badResult(int type, String cause) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 1);
        result.put(JSON_ERRORMSG_NAME, ImmutableMap.of("type", type, "msg", cause));
        return "@" + result.toJSONString();
    }

    /**
     * <p>
     * ok.
     * </p>
     * 
     * @return a {@link java.lang.String} object.
     */
    public static String ok() {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 0);
        result.put(JSON_DATA_NAME, "成功");
        return "@" + result.toString();
    }

    /**
     * <p>
     * okWithEmpty.
     * </p>
     * 
     * @return a {@link java.lang.String} object.
     */
    public static String okWithEmpty() {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 0);
        result.put(JSON_DATA_NAME, Collections.emptyList());
        return "@" + result.toString();
    }

    /**
     * <p>
     * ok.
     * </p>
     * 
     * @param key a {@link java.lang.String} object.
     * @param value a {@link java.lang.Object} object.
     * @return a {@link java.lang.String} object.
     */
    public static String ok(String key, Object value) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 0);
        result.put(JSON_DATA_NAME, ImmutableMap.of(key, value));
        return "@" + result.toJSONString();
    }

    /**
     * <p>
     * ok.
     * </p>
     * 
     * @param object a {@link java.lang.Object} object.
     * @return a {@link java.lang.String} object.
     */
    public static String ok(Object object) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 0);
        result.put(JSON_DATA_NAME, object);
        return "@" + result.toJSONString();
    }

    /**
     * @Title ok
     * @param object a {@link java.lang.Object} object.
     * @param params a {@link java.util.Map} object.
     * @return String(返回类型)
     */
    public static String ok(Object object, Map<String, Object> params) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 0);
        result.put(JSON_DATA_NAME, object);
        if (params != null && params.size() > 0) {
            Iterator<Entry<String, Object>> keys = params.entrySet().iterator();
            while (keys.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry<String, Object>) keys.next();
                result.put(String.valueOf(entry.getKey()), entry.getValue());
            }
        }
        return "@" + result.toJSONString();
    }

    /**
     * 返回分页结果json字串
     * 
     * @param object 数据对象
     * @param pageTotal 结果列表总共多少页
     * @param pageSize 每页多少条记录
     * @param pageNo 页号
     * @return json 字串
     */
    public static String okWithPaginate(Object object, int pageTotal, int pageSize, int pageNo) {
        JSONObject result = new JSONObject();
        result.put("pageTotal", pageTotal);
        result.put("pageSize", pageSize);
        result.put("pageNo", pageNo);
        result.put(JSON_DATA_NAME, object);
        result.put(JSON_ERRORCODE_NAME, 0);
        return "@" + result.toJSONString();
    }

    /**
     * 返回list对象
     * 
     * @param list
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String ok(List list) {
        JSONObject result = new JSONObject();
        result.put(JSON_DATA_NAME, list);
        result.put(JSON_ERRORCODE_NAME, 0);
        return "@" + result.toJSONString();
    }

    /**
     * <p>
     * jsonp.
     * </p>
     * 
     * @param object a {@link java.lang.Object} object.
     * @param callback a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public static String jsonp(Object object, String callback) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 0);
        result.put(JSON_DATA_NAME, object);
        if(StringUtils.isBlank(callback)){
            return "@" + result.toJSONString();
        }
        return "@" + callback + "(" + result.toJSONString() + ")";
    }

    /**
     * 可以通过map形式传递参数，但是如果是ok，msg这两个参数的值会被覆盖掉
     * 
     * @param params a {@link java.util.Map} object.
     * @return a {@link java.lang.String} object.
     */
    public static String ok(Map<String, Object> params) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 0);
        result.put(JSON_DATA_NAME, params);
        return "@" + result.toJSONString();
    }

    /**
     * 可以通过map形式传递参数，但是如果是ok，msg这两个参数的值会被覆盖掉
     * 
     * @param params a {@link com.google.common.collect.ImmutableMap} object.
     * @return a {@link java.lang.String} object.
     */
    public static String ok(ImmutableMap<String, Object> params) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 0);
        result.put(JSON_DATA_NAME, params);
        return "@" + result.toJSONString();
    }

    /**
     * 返回分页结果json字串
     * 
     * @param list
     * @param pageTotal
     * @param pageSize
     * @param pageNo
     * @param <T>
     * @return json 字串
     */
    public static <T> String okWithPaginate(List<T> list, int pageTotal, int pageSize, int pageNo) {
        JSONObject result = new JSONObject();
        result.put("pageTotal", pageTotal);
        result.put("pageSize", pageSize);
        result.put("pageNo", pageNo);
        result.put(JSON_ERRORCODE_NAME, 0);
        result.put(JSON_DATA_NAME, list);
        return "@" + result.toJSONString();
    }

    /**
     * 返回分页结果的json
     * 
     * @param params
     * @param pageTotal
     * @param pageSize
     * @param pageNo
     * @return json 字串
     */
    public static String okWithPaginate(Map<String, Object> params, int pageTotal, int pageSize, int pageNo) {
        JSONObject result = new JSONObject();
        result.put("pageTotal", pageTotal);
        result.put("pageSize", pageSize);
        result.put("pageNo", pageNo);
        result.put(JSON_ERRORCODE_NAME, 0);
        result.put(JSON_DATA_NAME, params);
        return "@" + result.toJSONString();
    }

    public static String okSupportJSONP(Object object, String fun) {
        if (StringUtils.isBlank(fun)) {
            return ok(object);
        }
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 0);
        result.put(JSON_DATA_NAME, object);
        return "@" + fun + "(" + result.toJSONString() + ")";
    }

    /**
     * 根据条件返回分页结果的jsonp
     * 
     * @param list
     * @param callback
     * @param params
     * @param <T>
     * @return
     */
    public static <T> String okSupportJSONPWithPaginate(List<T> list, String callback, Map<String, Object> params) {
       
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 0);
        result.put(JSON_DATA_NAME, list);

        if (params != null && params.size() > 0) {
            Iterator<Map.Entry<String, Object>> keys = params.entrySet().iterator();
            while (keys.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry<String, Object>) keys.next();
                result.put(String.valueOf(entry.getKey()), entry.getValue());
            }
        }
        if (StringUtils.isBlank(callback)) {
            return "@" + result.toJSONString();
        }
        return "@" + callback + "(" + result.toJSONString() + ")";
    }

    public static String badResultSupportJSONP(String cause, String fun) {
        if (StringUtils.isBlank(fun)) {
            return badResult(cause);
        }
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 1);
        result.put(JSON_ERRORMSG_NAME, cause);
        return "@" + fun + "(" + result.toJSONString() + ")";
    }

    public static String okSupportEmptyWithJSONP(String fun) {
        if (StringUtils.isBlank(fun)) {
            return okWithEmpty();
        }
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, 0);
        result.put(JSON_ERRORMSG_NAME, "success");
        return "@" + fun + "(" + result.toString() + ")";
    }

    /**
     * <p>
     * main.
     * </p>
     * 
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("test", 1);
        params.put("test1", "ok");
    }
}
