package cn.focus.dc.focuswap.service;

import net.rubyeye.xmemcached.MemcachedClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheHandlerService {

	protected Log logger = LogFactory.getLog(CacheHandlerService.class);

	private boolean isOpenCache = true;

	@Autowired
	private MemcachedClient cacheClient;

	public void removeCache(String key) {
		if (isOpenCache) {
			try {
				cacheClient.deleteWithNoReply(key);
			} catch (Exception e) {
				logger.error("", e);
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> T getCacheValue(String key, Class T) {
		if (isOpenCache) {
			try {
				return (T) cacheClient.get(key);
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		return null;
	}

	public void setCache(String key, int exp, Object value) {
		if (value == null || "".equals(value.toString())) {
			return;
		}
		if (isOpenCache) {
			try {
				cacheClient.setWithNoReply(key, exp, value);
			} catch (Exception e) {
				logger.error("", e);
			}
		}
	}
}
