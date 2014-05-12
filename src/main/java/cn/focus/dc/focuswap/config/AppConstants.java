package cn.focus.dc.focuswap.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class AppConstants {
    /**
     * 户型映射
     * 
     * @author rogantian
     */
    public static class Layout {
        private static String layout0Id = "-1";

        private static String layout0Desc = "主力户型";

        private static String layout1Id = "1";

        private static String layout1Desc = "一居";

        private static String layout2Id = "2";

        private static String layout2Desc = "两居";

        private static String layout3Id = "3";

        private static String layout3Desc = "三居";

        private static String layout4Id = "4";

        private static String layout4Desc = "四居";

        private static String layout5Id = "5";

        private static String layout5Desc = "五居";

        private static String layout6Id = "6";

        private static String layout6Desc = "六居";

        private static String layout7Id = "7";

        private static String layout7Desc = "七居";

        private static String layout8Id = "8";

        private static String layout8Desc = "八居";

        private static String layout9Id = "9";

        private static String layout9Desc = "九居";

        private static String layout10Id = "10";

        private static String layout10Desc = "十居";

        private static String layoutKaiJianId = "kaijian";

        private static String layoutKaiJianDesc = "开间";

        private static String layoutFuShiId = "fushi";

        private static String layoutFuShiDesc = "复式";

        private static String layoutDuDongId = "dudong";

        private static String layoutDuDongDesc = "独栋";

        private static String layoutLianPaiId = "lianpai";

        private static String layoutLianPaiDesc = "联排";

        private static String layoutDiePinId = "diepin";

        private static String layoutDiePinDesc = "叠拼";

        private static String layoutOthersId = "99";

        private static String layoutOthersDesc = "其它";

        private static String layoutAllId = "all";

        private static String layoutAllDesc = "总面积";

        // 楼盘图片
        private static String louzuoPicId = "-10";

        private static String louzuoPicDesc = "楼座图";

        private static String yangbanPicId = "-12";

        private static String yangbanPicDesc = "样板图";

        private static String zbptPicId = "-13";

        private static String zbptPicDesc = "周边配套";

        private static String jiaotongPicId = "-14";

        private static String jiaotongPicDesc = "交通图";

        private static String xiaoguoPicId = "-16";

        private static String xiaoguoPicDesc = "效果图";

        private static String jinduPicId = "-17";

        private static String jinduPicDesc = "施工进度图";

        private static String shijingPicId = "-18";

        private static String shijingPicDesc = "实景图";

        private static String sougouVR = "-20";

        private static String sougouVRDesc = "搜狗VR图 ";

        private static Map<String, String> layoutMap = new HashMap<String, String>();

        static {
            layoutMap.put(layout0Id, layout0Desc);
            layoutMap.put(layout1Id, layout1Desc);
            layoutMap.put(layout2Id, layout2Desc);
            layoutMap.put(layout3Id, layout3Desc);
            layoutMap.put(layout4Id, layout4Desc);
            layoutMap.put(layout5Id, layout5Desc);
            layoutMap.put(layout6Id, layout6Desc);
            layoutMap.put(layout7Id, layout7Desc);
            layoutMap.put(layout8Id, layout8Desc);
            layoutMap.put(layout9Id, layout9Desc);
            layoutMap.put(layout10Id, layout10Desc);
            layoutMap.put(layoutKaiJianId, layoutKaiJianDesc);
            layoutMap.put(layoutFuShiId, layoutFuShiDesc);
            layoutMap.put(layoutDuDongId, layoutDuDongDesc);
            layoutMap.put(layoutLianPaiId, layoutLianPaiDesc);
            layoutMap.put(layoutDiePinId, layoutDiePinDesc);
            layoutMap.put(layoutOthersId, layoutOthersDesc);
            layoutMap.put(layoutAllId, layoutAllDesc);
            layoutMap.put(louzuoPicId, louzuoPicDesc);
            layoutMap.put(yangbanPicId, yangbanPicDesc);
            layoutMap.put(zbptPicId, zbptPicDesc);
            layoutMap.put(jiaotongPicId, jiaotongPicDesc);
            layoutMap.put(xiaoguoPicId, xiaoguoPicDesc);
            layoutMap.put(jinduPicId, jinduPicDesc);
            layoutMap.put(shijingPicId, shijingPicDesc);
            layoutMap.put(sougouVR, sougouVRDesc);
        }

        public static String getLayoutName(String layoutType) {
            return layoutMap.get(layoutType);
        }

        public static String getOtherLayoutId() {
            return layoutOthersId;
        }

        public static String getLayoutAllId() {
            return layoutAllId;
        }
    }

    /**
     * 城市页面映射
     * 
     * @author zhiweiwen 2013-11-15 下午5:02:55
     */
    public static class CityPageStatus {
        
        // 有新房和二手房
        public static int XF_AND_ESF = 1;

        // 新房
        public static int XF = 2;

        // 二手房
        public static int ESF = 3;
        
        private static Map<String, Integer> cityPageMap = new HashMap<String, Integer>();

        private static String ESF_URL = "esf.focus.cn/s/";

        private static String ESF_SUGGEST_URL = "esf.focus.cn/ajax/wj31_get_suggest_house.php?q={q}";
    
        static {
            cityPageMap.put("北京", XF_AND_ESF);
            cityPageMap.put("上海", XF_AND_ESF);
            cityPageMap.put("广州", XF_AND_ESF);
            cityPageMap.put("深圳", XF_AND_ESF);
            cityPageMap.put("天津", XF_AND_ESF);
            cityPageMap.put("成都", XF_AND_ESF);
            cityPageMap.put("重庆", XF_AND_ESF);
            cityPageMap.put("石家庄", XF_AND_ESF);
            // cityPageMap.put("西安", XF);
            // cityPageMap.put("南京", XF);
            // cityPageMap.put("武汉", XF);
            // cityPageMap.put("杭州", XF);
            // cityPageMap.put("长沙", XF);
            // cityPageMap.put("哈尔滨", XF);
            // cityPageMap.put("昆明", XF);
            // cityPageMap.put("海南", XF);
            // cityPageMap.put("青岛", XF);
            // cityPageMap.put("大连", XF);
            // cityPageMap.put("无锡", XF);
            // cityPageMap.put("宁波", XF);
            // cityPageMap.put("南宁", XF);
            // cityPageMap.put("常州", XF);
            cityPageMap.put("沈阳", XF_AND_ESF);
            cityPageMap.put("濮阳", XF_AND_ESF);

        }

        public static int getCityStatus(String cityName) {
            if (null == cityPageMap.get(cityName)) {
                return XF;
            }
            return cityPageMap.get(cityName);
        }

        public static String getEsfUrl() {
            return ESF_URL;
        }

        public static String getEsfSuggestUrl() {
            return ESF_SUGGEST_URL;
        }
    }

    /**
     * 楼盘价格
     * @author zhiweiwen
     * 2014-2-17
     * 上午11:29:16
     */
    public static class BuldingPrice {
        // 均价
        public static int AVGPRICE = 1;

        // 最低价
        public static int MINPRICE = 2;

        // 最高价
        public static int MAXPRICE = 3;

        // 总价
        public static int ALLPRICE = 4;

        public static String getPriceForShow(int price, int type) {
            switch (type) {
            case 1:
                return "均价  <em>" + price + "</em>元/平米";
            case 2:
                return "最低  <em>" + price + "</em>元/平米";
            case 3:
                return "最高  <em>" + price + "</em>元/平米";
            case 4:
                return "总价  <em>" + price + "</em>万/套";
            case -2:
                return "<em>价格待定</em>";
            default:
                return "参考价格 : <em>待定</em>";
            }
        }

    }
    
    /**
     * 内容分享
     * @author zhiweiwen
     * 2014-2-17
     * 上午11:41:34
     */
    public static class ContentShare {
        private static String SWB_URL = "http://service.weibo.com/share/share.php?appkey=1429547852&count=n&ralateUid=3687486675";
        private static String TWB_URL = "http://share.v.t.qq.com/index.php?c=share&a=index&f=f1&bm=11";
        private static String QZONE_URL = "http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?site=";
        
        private static String SWB_TYPE = "sWeibo";
        private static String QZONE_YTPE = "qZone";
        private static String TWB_TYPE = "tWeibo";
        
        private static Map<String,String> urlMap = new HashMap<String,String>();
        
        static {
            urlMap.put(SWB_TYPE, SWB_URL);
            urlMap.put(TWB_TYPE, TWB_URL);
            urlMap.put(QZONE_YTPE, QZONE_URL);
        }
        
        public static String getShareUrl(String type) {
            return urlMap.get(type);
        }
    }
    
    /**
     * 自住房城市信息
     * @author zhiweiwen
     *
     */
    public static class ZzfCityStatus {
    	
    	private static Map<String, Integer> cityStatusMap = new HashMap<String, Integer>();
    	private static int HAS_ZZF = 1;
    	private static int HASNO_ZZF = 0;
    	
        static {
        	cityStatusMap.put("bj", HAS_ZZF);
        }
        
        public static int getCityZzfStatus(String cityPingYinabbr) {
        	Integer status = cityStatusMap.get(cityPingYinabbr);
        	if(null != status) {
        		return status;
        	} 
        	return HASNO_ZZF;
        }
    	
    }

    /**
     * 装修级别映射
     * 
     * @author rogantian
     */
    public static class DecorateLevel {
        private static int level1 = 1;

        private static String level1Desc = "精装";

        private static int level2 = 2;

        private static String level2Desc = "简装";

        private static int level3 = 3;

        private static String level3Desc = "毛坯";

        private static int level4 = 4;

        private static String level4Desc = "豪华";

        private static Map<Integer, String> levelMap = new HashMap<Integer, String>();

        static {
            levelMap.put(level1, level1Desc);
            levelMap.put(level2, level2Desc);
            levelMap.put(level3, level3Desc);
            levelMap.put(level4, level4Desc);
        }

        public static String getLevelName(int level) {
            return levelMap.get(level);
        }
    }

    /**
     * 中国地图上的经纬度坐标范围
     */
    // 经度
    public static long longtitudeMin = 73;

    public static long longtitudeMax = 136;

    // 纬度
    public static long latitudeMin = 18;

    public static long latitudeMax = 53;

    /***************************** 缓存相关BEGIN **********************************/
    
    // 各城市站是否有导购的状态标志过期时间(秒)
    public static final int CE_WAP_PROPOSE_TIME = 1800;

    // 各城市站是否有导购的状态标志key
    public static final String CK_WAP_PROPOSE_STATUS = "wap:home:propose:status:";

    // 首页主编推荐楼盘列表缓存过期时间(秒)
    public static final int CE_HOME_RECOMMENDLIST_TIME = 3600;

    // 首页主编推荐楼盘列表缓存key
    public static final String CK_HOME_RECOMMENDLIST = "wap:home:recommendlist:";

    // 买房搜索条件缓存失效时间（秒）
    public static final int DATE_CENTER_INTERFACE_EXPIRE = 2 * 3600;

    // 买房搜索条件缓存key
    public static final String BUY_BUILD_CONDITION_KEY = "wap:buy:building:condition";

    // 楼盘搜索条件缓存时间
    public static final int CE_BUILDING_SEARCH_TIME = 2 * 3600;

    // 楼盘搜素条件缓存key
    public static final String CK_BUILDING_SEARCH_KEY = "wap:building:search:";

    // 最新动态列表放入缓存的有效期10分钟
    public static final int CE_RECENTNEWS_TIME = 10 * 60;

    // 最新动态缓存
    public static final String CK_RECENTNEWS_LIST = "wap:recentnews:list:";

    // 最新动态详细页缓存
    public static final String CK_CENTENTNEW_ITEM = "wap:recentnew:item:";

    // 新闻列表放入缓存的有效期一天
    public static final int CE_NEWS_TIME = 60 * 60 * 24;

    // 新闻列表缓存
    public static final String CK_NEWS_LIST = "wap:news:list:";

    // 新闻详细页缓存
    public static final String CK_NEW_ITEM = "wap:new:item:";

    // 条件缓存
    public static final String CK_CONDITION = "condition:";

    public static final int CK_CONDITION_TIME = 60;

    // 爬房团小贴士列表缓存key
    public static final String CK_PAFANGTUAN_TIPS_LIST = "wap:pafangtuan:tips:list:";

    // 爬房团小贴士列表缓存过期时间(秒)
    public static final int CE_PAFANGTUAN_TIPS_LIST = 2 * 60 * 60;

    /***************************** 缓存相关END ************************************/

    /**
     * 电商活动状态映射
     * 
     * @author zhiweiwen 2013-11-27 上午9:55:55
     */
    public static class ActiveStatus {

        // 接口返回报名成功
        private static int INTERFACE_SUCCESS = 0;

        // 接口返回重复报名
        private static int INTERFACE_REPEAT = 1;

        // 接口返回活动结束
        private static int INTERFACE_END = 2;

        // 接口返回未知错误
        private static int INTERFACE_ERR = 99;

        // 输出报名成功
        private static int SUCCESS = 1;

        // 输出报名失败
        private static int FAILED = 0;

        // 输出重复报名
        private static int REPEAT = 2;

        private static Map<Integer, Integer> statusMap = new HashMap<Integer, Integer>();

        static {
            statusMap.put(INTERFACE_SUCCESS, SUCCESS);
            statusMap.put(INTERFACE_REPEAT, REPEAT);
            statusMap.put(INTERFACE_END, FAILED);
            statusMap.put(INTERFACE_ERR, FAILED);
        }

        public static int getResult(int status) {
            return statusMap.get(status);
        }

    }

    /***************************** 接口BEGIN *************************************/

    @ConfigProperty("interface.news_center.normal_news_digest.url")
    @Secretkey("secretkey.news_center")
    public static String NEWS_CENTER_NORMAL_NEWS_DIGEST_URL;

    @ConfigProperty("interface.news_center.normal_news_content.url")
    @Secretkey("secretkey.news_center")
    public static String NEWS_CENTER_NORMAL_NEWS_CONTENT_URL;

    @ConfigProperty("interface.news_center.related_news.url")
    @Secretkey("secretkey.news_center")
    public static String NEWS_CENTER_RELATED_NEWS;

    @ConfigProperty("interface.news_center.secretkey")
    @Secretkey("secretkey.news_center")
    public static String NEWS_CENTER_SECRETKEY;

    // 数据中心楼盘详情接口
    @ConfigProperty("interface.building.building_info.url")
    @Secretkey("secretkey.building")
    public static String DATACENTER_BUILDING_INFO_URL;

    // 数据中心楼盘详细信息接口
    @ConfigProperty("interface.building.building_detail.url")
    @Secretkey("secretkey.building")
    public static String DATACENTER_BUILDING_DETAIL_URL;

    // 数据中心楼盘户型图接口
    @ConfigProperty("interface.building.building_layout_pic.url")
    @Secretkey("secretkey.building")
    public static String DATACENTER_BUILDING_LAYOUT_PIC__URL;

    // 数据中心楼盘接口
    @Secretkey("secretkey.building")
    @ConfigProperty("interface.building.building_pic_list.url")
    public static String DATACENTER_BUILDING_PIC_URL;

    // 数据中心根据楼盘的城市ID查询城市信息
    @Secretkey("secretkey.building")
    @ConfigProperty("interface.building.building_city.url")
    public static String DATACENTER_BUILDING_CITY_URL;

    // 数据中心根据id获取楼盘列表接口
    @ConfigProperty("interface.building.building_ids_list.url")
    @Secretkey("secretkey.building")
    public static String DATACENTER_BUILDING_LIST_BY_IDS_URL;

    // 数据中心查询楼盘基本信息接口
    @ConfigProperty("interface.building.building_base.url")
    @Secretkey("secretkey.building")
    public static String DATACENTER_BUILDING_BASE_QUERY_URL;

    // 数据中心查询楼盘搜索条件接口
    @ConfigProperty("interface.building.building_search_con.url")
    @Secretkey("secretkey.building")
    public static String DATACENTER_BUILDING_NAME_BUY_CONDITION;

    // 数据中心查询楼盘周边信息
    @ConfigProperty("interface.building.building_around.url")
    @Secretkey("secretkey.building")
    public static String DATACENTER_BUILDING_AROUND;

    // 数据中心楼盘信息查询使用的secretkey
    @ConfigProperty("interface.building.secretkey")
    public static String DATACENTER_BUILDING_SECRETKEY;

    // 楼盘动态接口(from PHP)
    @ConfigProperty("interface.php_interface.loupan")
    public static String PHP_LOUPAN_MOBILE_URL;

    // 楼盘动态接口(from PHP)
    @ConfigProperty("interface.php_interface.loupan_detail")
    public static String PHP_LOUPAN_DETAIL_MOBILE_URL;

    // 楼盘动态接口html(from PHP)
    @ConfigProperty("interface.php_interface.loupan_detail_html")
    public static String PHP_LOUPAN_NEW_MOBILE_URL;

    // 新闻资讯接口(from PHP)
    @ConfigProperty("interface.news.url")
    public static String CHANNEL_NEWS_QUERY_URL;

    // 获取电商活动信息接口
    @ConfigProperty("interface.active.info.url")
    public static String ACTIVE_INFO_URL;

    // 电商报名接口
    @ConfigProperty("interface.active.regist.url")
    public static String ACTIVE_REGIST_URL;

    // 获取爬房团线路信息接口
    @ConfigProperty("interface.pafangtuan.line_info.url")
    public static String PAFANGTUAN_LINE_INFO_URL;

    // 获取爬房团小贴士接口
    @ConfigProperty("interface.pafangtuan.tips.url")
    public static String PAFANGTUAN_TIPS_URL;

    // 获取爬房团报名接口
    @ConfigProperty("interface.pafangtuan.signup.url")
    public static String PAFANGTUAN_SIGNUP_URL;

    // 获取爬房团接口secretkey
    @ConfigProperty("interface.pafangtuan.secretkey")
    public static String PAFANGTUAN_SECRETKEY;

    
    // 获取新房typeid映射关系
    @ConfigProperty("interface.xinfang.type_id_relation.url")
    public static String XINFANG_TYPE_ID_RELATION_URL;
    
    // 分享参数验证控制开关
    @ConfigProperty("interface.daogou.controller")
    public static String SHARE_AUTH_OPEN;
    
    // 获取新房导购列表
    @ConfigProperty("interface.xinfang.daogou_list.url")
    public static String XINFANG_TYPE_DAOGOU_LIST_URL;
    
    // 获取新房导购详情
    @ConfigProperty("interface.xinfang.daogou_content.url")
    public static String XINFANG_TYPE_DAOGOU_CONTENT_URL;
    
    // 高德逆地理编码url
    @ConfigProperty("interface.city.location.url")
    public static String CITY_LOCATION_URL;
    
    // 获取新房PC导购id转换为wap导购id
    @ConfigProperty("interface.xinfang.daogou_pc2mb.url")
    public static String XINFANG_DAOGOU_PC2MB_URL;

    /***************************** 接口END ***************************************/

    /**************************** 字符串常量BEGIN ********************************/

    /** 城市对应新房二手房状态 */
    public static Map<String, Integer> cityStatusMap;

    public static final String PAGE_NO = "pageNo";

    public static final String PAGE_SIZE = "pageSize";

    public static final String CITY_ID = "cityid";

    public static final String GROUP_ID = "groupId";

    public static final String BUILD_ID = "buildid";

    public static final String HASNEXT = "hasNext";

    public static final String CHANNEL_ID = "channelId";

    public static final String LIMIT = "limit";

    public static final String ERRORCODE = "errorCode";

    public static final String DATA = "data";

    public static final String DEFUALT_CITY = "全国";

    public static final String DEFUALT_ANONYMOUS_USER = "匿名";

    /**************************** 字符串常量END **********************************/

    /**************************** 城市常量END **********************************/

    // 沈阳城市ID
    public static final int CITYID_SY = 999;

    public static final String CITYNAME_SY = "沈阳";

    public static final String CITYPINYIN_SY = "sy";

    // 濮阳城市ID
    public static final int CITYID_PY = 888;

    public static final String CITYNAME_PY = "濮阳";

    public static final String CITYPINYIN_PY = "puyang";

    /**************************** 城市常量END **********************************/

    // 搜索条件
    public static int CONDITION_DISTRICTS = 1;

    public static int CONDITION_TYPES = 2;

    public static int CONDITION_PRICES = 3;

    public static int CONDITION_AREAS = 4;

    public static int CONDITION_LINES = 5;

    public static int CONDITION_RECOMENDS = 6;

    // 搜索条件索引
    public static int CONDITION_DISTRICTS_INDEX = 3;

    public static int CONDITION_TYPES_INDEX = 4;

    public static int CONDITON_PRICES_INDEX = 0;

    public static int CONDITION_AREAS_INDEX = 1;

    public static int CONDITION_LINES_INDEX = 5;

    public static int CONDITIO_RECONENTDS_INDEX = 2;

    // 搜索类型
    public static int SEARCH_TYPE_XINFANG = 1;

    public static int SEARCH_TYPE_ESF = 2;
    
    // 个城市导购存在状态
    public static int NO_PROPOSE = 0;
    
    public static int HAS_PROPOSE = 1;

    // 搜索条件名称
    public static String CONDITION_DISTRICTS_NAME = "热点版块";

    public static String CONDITION_TYPES_NAME = "类型";

    public static String CONDITION_PRICES_NAME = "价格";

    public static String CONDITION_AREAS_NAME = "区域";

    public static String CONDITION_LINES_NAME = "轨道交通";

    public static String CONDITION_RECOMENDS_NAME = "特色楼盘";

    // PHP func的名称
    public static final String PHP_FUNC_HOUSE_NEWS_LIST = "getinfolist";

    // 默认空白列表页背景图片地址
    public static String DEFAULT_PIC_URL = "http://a1.itc.cn/sceapp/focus_static/wap/images/house_img_bg.png";

    // 默认空白详细页图片地址
    public static String DEFAULT_DETAIL_PIC_URL = "http://a1.itc.cn/sceapp/focus_static/wap/images/loupan_default.jpg";

    // 刷新全部缓存访问地址的认证字串
    public static final String FLUSH_CACHE_AUTH = "cef75f381b87b191bfe48dfd8b76ca54";

    // 公共问答账号ID
    public static final int QA_PUBLIC_UID = 1010;

    // 公共问答账号NAME
    public static final String QA_PUBLIC_USER_NAME = "搜狐购房小助手";

    // 城市选择列表，默认城市
    public static final String DEFAULT_SELECT_CITY = "北京";

    // 搜索历史COOKIE KEY
    public static final String COOKIE_SEARCH_HISTORY = "focus_wap_searchHistory";

    // 首页打折楼盘,最新开盘,小户型模块数据默认显示查询页码
    public static final int HOME_BUILDING_LIST_PAGENO = 1;

    // 首页打折楼盘,最新开盘,小户型模块数据默认显示条数
    public static final int HOME_BUILDING_LIST_PAGESIZE = 3;

    // 站点url
    public static final String DOMAIL = "m.focus.cn/";

    // 日志线程数
    @ConfigProperty("log_thread_count")
    public static int LOG_THREAD_COUNT;

    // 日志区分test和product环境
    @ConfigProperty("log_open")
    public static Boolean LOG_OPEN;

    /**************************** 导购图片width常量BEGIN ******************************/
    public static class Focus_img_width {
        public static Map<String, String> widthMap = new HashMap<String, String>();
        static{
            widthMap.put(DAOGOULIST_FOCUS_IMG_WIDTH+"pad", "600");
            widthMap.put(DAOGOULIST_FOCUS_IMG_WIDTH+"phone", "600");
            widthMap.put(INDEX_FOCUS_IMG_WIDTH+"pad", "360");
            widthMap.put(INDEX_FOCUS_IMG_WIDTH+"phone", "240");
        }
    }
    public static final String DAOGOULIST_FOCUS_IMG_WIDTH = "daogou_list_";
       
    public static final String INDEX_FOCUS_IMG_WIDTH = "index_list_";
    
    public static final String FOCUS_IMG_SET = "c_zoom";
    
    /**************************** 导购图片width常量END ********************************/
    
    //密钥map
    public static Map<String, String> secretKeyMap = new HashMap<String, String>();
    
    
    /**************************** SCE常量BEGIN ******************************/
    @ConfigProperty("sce.appid")
    public static String SCE_APP_ID;
    
    @ConfigProperty("sce.secret")
    public static String SCE_APP_SECRET;
    
    public static final String SCE_API_ENDPOINT = "http://sceapi.apps.sohuno.com/";
    
    /**************************** SCE常量END ********************************/
}
