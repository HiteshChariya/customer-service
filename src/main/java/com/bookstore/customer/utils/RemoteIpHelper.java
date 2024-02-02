package com.bookstore.customer.utils;

import javax.servlet.http.HttpServletRequest;



public class RemoteIpHelper {

    private static final String UNKNOWN = "unknown";

    public static String getRemoteIpFromReq(HttpServletRequest request) {
        String ip = null;
        int tryCount = 1;

        while (!isIpFound(ip) && tryCount <= 6) {
            switch (tryCount) {
                case 1:
                    ip = request.getHeader("X-Forwarded-For");
                    break;
                case 2:
                    ip = request.getHeader("Proxy-Client-IP");
                    break;
                case 3:
                    ip = request.getHeader("WL-Proxy-Client-IP");
                    break;
                case 4:
                    ip = request.getHeader("HTTP_CLIENT_IP");
                    break;
                case 5:
                    ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                    break;
                default:
                    ip = request.getRemoteAddr();
            }

            tryCount++;
        }

        return ip;
    }

    private static boolean isIpFound(String ip) {
        return ip != null && ip.length() > 0 && !UNKNOWN.equalsIgnoreCase(ip);
    }
}