/**
 *
 *JOJO
 * 
 */
package com.jojo.util.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author finley.yao
 * @version $Id: CookieUtil.java, v 0.1 2013-6-9 下午3:39:07 finley.yao Exp $
 */
public class CookieUtil {

    private final HttpServletRequest  request;
    private final HttpServletResponse response;
    private static final String       PATH = "/";

    public CookieUtil(final HttpServletRequest request, final HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public String getCookie(String name) {
        return getCookie(name, true);
    }

    public String getCookie(String name, boolean decode) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                String value = cookie.getValue();
                return decode ? decode(value) : value;
            }
        }

        return null;
    }

    public void setCookie(String name, String value) {
        setCookie(name, value, PATH);
    }

    public void setCookie(String name, String value, String path) {
        setCookie(name, value, path, true);
    }

    public void setCookie(String name, String value, int expiry) {
        setCookie(name, value, PATH, expiry, true);
    }

    public void setCookie(String name, String value, String path, int expiry) {
        setCookie(name, value, path, expiry, true);
    }

    public void setCookie(String name, String value, String path, boolean encode) {
        setCookie(name, value, path, -1, encode);
    }

    public void setCookie(String name, String value, String path, int expiry, boolean encode) {
        Cookie cookie = new Cookie(name, encode ? encode(value) : value);
        cookie.setPath(path);
        cookie.setMaxAge(expiry);
        response.addCookie(cookie);
    }

    public void removeCokie(String name) {
        setCookie(name, null, 0);
    }

    private String encode(String value) {
        return value == null ? null : new String(Base64.encodeBase64(value.getBytes()));
    }

    private String decode(String value) {
        return value == null ? null : new String(Base64.decodeBase64(value.getBytes()));
    }

}
