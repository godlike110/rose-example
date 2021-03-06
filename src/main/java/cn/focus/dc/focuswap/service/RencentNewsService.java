/**
 *
 */
package cn.focus.dc.focuswap.service;

import cn.focus.dc.focuswap.model.RecentNews;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static cn.focus.dc.focuswap.config.AppConstants.*;

/**
 * @author yunguangwang
 * @2013-8-27 @下午5:00:51
 */
@Component
public class RencentNewsService {

	private static final String BUILD_INFO = "buildinfo";

	private static Log logger = LogFactory.getLog(RencentNewsService.class);

	@Autowired
	private InterfaceService interfaceService;

	@Autowired
	private CacheHandlerService cacheHandler;

	/**
	 * 最新动态列表
	 * 
	 * @param buildId
	 * @param cityId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> getRecentNewsList(Integer buildId,
			Integer cityId, Integer pageNo, Integer pageSize) {
		StringBuffer newsSB = new StringBuffer();
		StringBuffer totalSB = new StringBuffer();
		newsSB.append(CK_RECENTNEWS_LIST).append(buildId).append(":")
				.append(cityId).append(":").append(pageNo).append(":")
				.append(pageSize);
		totalSB.append(CK_RECENTNEWS_LIST).append(buildId).append(":")
				.append(cityId).append(":").append(pageNo).append(":")
				.append(pageSize).append(":total");

		Map<String, Object> recentNewsResult = new HashMap<String, Object>();
		List<RecentNews> recentNewsList = null;
		Integer total = 0;

		// 从缓存获取最新动态列表
		recentNewsList = cacheHandler.getCacheValue(newsSB.toString(),
				List.class);
		total = cacheHandler.getCacheValue(totalSB.toString(), Integer.class);

		if (recentNewsList != null && total != null) {
			recentNewsResult.put("recentNewsList", recentNewsList);
			recentNewsResult.put("total", total);
		}

		if (recentNewsList == null || recentNewsList.size() == 0) {
			recentNewsResult = getRecentNewsListToCache(buildId, cityId,
					pageNo, pageSize);
		}

		return recentNewsResult;
	}

	private Map<String, Object> getRecentNewsListToCache(Integer buildId,
			Integer cityId, Integer pageNo, Integer pageSize) {
		StringBuffer newsSB = new StringBuffer();
		StringBuffer totalSB = new StringBuffer();
		newsSB.append(CK_RECENTNEWS_LIST).append(buildId).append(":")
				.append(cityId).append(":").append(pageNo).append(":")
				.append(pageSize);
		totalSB.append(CK_RECENTNEWS_LIST).append(buildId).append(":")
				.append(cityId).append(":").append(pageNo).append(":")
				.append(pageSize).append(":total");

		Map<String, Object> resultMap = new HashMap<String, Object>(2);
		List<RecentNews> recentNewsList = new ArrayList<RecentNews>();
		Integer total = -1;
		JSONObject jsonResult = getRecentNewsFromPHP(buildId, cityId, pageNo,
				pageSize);

		if (jsonResult != null && jsonResult.get(BUILD_INFO) != null) {

			JSONArray jsonArray = (JSONArray) jsonResult.get(BUILD_INFO);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				RecentNews recentNews = null;
				try {
					recentNews = JSON
							.toJavaObject(jsonObject, RecentNews.class);
				} catch (Exception e) {
					logger.error("", e);
				}
				if (recentNews != null) {
					recentNewsList.add(recentNews);
					try {
						// 添加最新动态item缓存
						// cacheHandler.setCache(CK_CENTENTNEW_ITEM +
						// recentNews.getInfo_id(), CE_RECENTNEWS_TIME,
						// recentNews);
					} catch (Exception e) {
						logger.error("", e);
					}

				}

			}

			// 添加最新动态列表缓存
			try {
				if (recentNewsList.size() > 0) {
					cacheHandler.setCache(newsSB.toString(),
							CE_RECENTNEWS_TIME, recentNewsList);
				}

				total = jsonResult.getInteger("total");
				if (total == null) {
					total = -1;
				}
				cacheHandler.setCache(totalSB.toString(), CE_RECENTNEWS_TIME,
						total);
			} catch (Exception e) {
				logger.error("", e);
			}

		}
		resultMap.put("recentNewsList", recentNewsList);
		resultMap.put("total", total);
		return resultMap;
	}

	/**
	 * 从接口获区楼盘动态
	 * 
	 * @author kanezheng
	 * @param itemId
	 * @return
	 * @throws MemcachedException
	 * @throws InterruptedException
	 * @throws TimeoutException
	 */
	private RecentNews getRecentNewsToCache(Integer itemId, Integer cityId)
			throws TimeoutException, InterruptedException, MemcachedException {
		RecentNews recentNews = null;
		JSONObject jsonResult = getRecentNewsFromPHP(itemId, cityId);
		if (jsonResult != null && jsonResult.get("data") != null) {
			JSONArray jsonArray = jsonResult.getJSONArray("data");
			// 返回值是个数组???
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				recentNews = JSON.toJavaObject(jsonObject, RecentNews.class);
				if (recentNews != null) {
					// 添加最新动态item缓存
					cacheHandler.setCache(
							CK_CENTENTNEW_ITEM + recentNews.getInfo_id(),
							CE_RECENTNEWS_TIME, recentNews);
				} else {
					recentNews = new RecentNews();
				}
			}
		}
		return recentNews;
	}

	public JSONObject getRecentNewsFromPHP(Integer buildId, Integer cityId,
			Integer pageNo, Integer pageSize) {
		Map<String, Object> urlVariables = new HashMap<String, Object>();
		urlVariables.put("func", PHP_FUNC_HOUSE_NEWS_LIST);
		urlVariables.put("buildid", buildId);
		urlVariables.put("cityid", cityId);
		urlVariables.put("pageindex", pageNo);
		urlVariables.put("pagesize", pageSize);

		return interfaceService.getJSONFromInterface(PHP_LOUPAN_MOBILE_URL,
				urlVariables);
	}

	public JSONObject getRecentNewsFromPHP(Integer infoId, Integer cityId) {
		JSONObject ob = null;
		Map<String, Object> urlVariables = new HashMap<String, Object>();
		urlVariables.put("infoId", infoId);
		urlVariables.put("cityId", cityId);
		String jsonStr = interfaceService.getStringFromInterface(
				PHP_LOUPAN_NEW_MOBILE_URL, urlVariables);
		try {
			if (StringUtils.isNotBlank(jsonStr)) {
				ob = JSONObject.parseObject(jsonStr);
			}
		} catch (Exception e) {
			//logger.error(" ",e);
			// 因为获取html后有些字符不满足json格式,所以抱错后需要重新获取纯文本
			ob = interfaceService.getJSONFromInterface(
					PHP_LOUPAN_DETAIL_MOBILE_URL, urlVariables);
		}
		return ob;
	}

	public RecentNews getRecentNews(Integer itemId, Integer cityId)
			throws TimeoutException, InterruptedException, MemcachedException {
		StringBuffer sb = new StringBuffer();
		sb.append(CK_CENTENTNEW_ITEM).append(itemId);
		RecentNews recentNews = null;
		recentNews = cacheHandler
				.getCacheValue(sb.toString(), RecentNews.class);
		if (null == recentNews) {
			recentNews = getRecentNewsToCache(itemId, cityId);
		}
		return recentNews;
	}
}
