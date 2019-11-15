package com.usman.contollers;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.ws.rs.core.NewCookie;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CookieUtil {

	public static String prepareCookieHeader(String cookieName, String cookieValue) {
		Cookie cookie = CookieUtil.createCookie(cookieName, cookieValue);
		return getHttpOnlyCookieHeader(cookie);
	}

	public static Cookie createCookie(String name, String value) {
		Cookie cookie = null;
		try {
			cookie = new Cookie(name, URLEncoder.encode(value, "UTF-8"));
			cookie.setPath("/");
			cookie.setSecure(true);
		} catch (Exception ex) {
			log.debug("Exception occured while creating a cookie", ex);
		}
		return cookie;
	}

	private static String getHttpOnlyCookieHeader(Cookie cookie) {
		NewCookie newCookie = new NewCookie(cookie.getName(), cookie.getValue(), cookie.getPath(), cookie.getDomain(),
				cookie.getVersion(), cookie.getComment(), cookie.getMaxAge(), cookie.getSecure());
		return newCookie + "; HttpOnly";
	}
	
}
