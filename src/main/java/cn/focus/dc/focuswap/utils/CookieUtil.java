package cn.focus.dc.focuswap.utils;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.paoding.rose.web.Invocation;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class CookieUtil {

	private static Log logger = LogFactory.getLog(CookieUtil.class);
	private static final Integer SEARCH_COOKIE_MAX_AGE = 3600 * 24 * 90;
	private static final String UTF8 = "UTF-8";

	private CookieUtil() {
	}

	/**
	 * 添加cookie
	 * 
	 * @param response
	 *            response相应对象
	 * @param name
	 *            cookie的名字
	 * @param value
	 *            cookie的值
	 * @param maxAge
	 *            cookie的生存期 ，秒为单位
	 */
	public static void addCookie(HttpServletRequest request,
			HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		String domain = request.getServerName();
		cookie.setDomain(domain);
		if (maxAge > 0) {
			cookie.setMaxAge(maxAge);
		}
		response.addCookie(cookie);
	}

	/**
	 * 获取cookie
	 * 
	 * @param request
	 *            request对象
	 * @param name
	 *            cookie的值
	 * @return 获取的cookie对象
	 */
	public static String getCookieValueByName(HttpServletRequest request,
			String name) {
		Map<String, Cookie> cookieMap = readCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie.getValue();
		} else {
			return null;
		}
	}

	private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = new Cookie[] {};
		try {
			cookies = request.getCookies();
		} catch (Exception ignored) {
			logger.error("", ignored);
		}
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

	// search history save
	public static void saveCookieBySearch(Invocation inv, String name,
			String value) {
		HttpServletResponse response = inv.getResponse();
		HttpServletRequest request = inv.getRequest();
		String historyTotal = getCookieValueByName(request, name);
		String cookie = "";

		try {
			value = value.trim();
			value = Base64.encodeBase64String(value.getBytes(UTF8));
			value = URLEncoder.encode(value, UTF8);
			if (value.indexOf("\r\n") != -1) {
				value = value.replace("\r\n", "");
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		if (historyTotal == null) {
			cookie = value;
		} else {
			cookie = historyTotal + "~~" + value;
		}
		addCookie(inv.getRequest(), response, name, cookie, SEARCH_COOKIE_MAX_AGE);
	}
}
