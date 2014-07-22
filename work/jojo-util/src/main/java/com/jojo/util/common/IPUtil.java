/**
 *
 *JOJO
 *
 */
package com.jojo.util.common;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author finley.yao
 * @version $Id: IPUtil.java, v 0.1 2013-8-29 下午5:19:42 finley.yao Exp $
 */
public class IPUtil {

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

}
