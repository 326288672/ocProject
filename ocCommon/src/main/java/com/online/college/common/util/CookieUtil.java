package com.online.college.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 江龙
 * @date 2018-12-30 23:47
 */
public class CookieUtil {
	
	public static final String WEIXIN_OPENID = "_weixin_openid_";

    /**
     * 添加cookie
     * @param response 对象
     * @param cookieName  名称
     * @param value 传值
     * @param maxAge 最大年龄
     * @param domain 域
     * @return 返回cookie
     */
	public static Cookie addCookie(HttpServletResponse response,String cookieName,String value,Integer maxAge,String domain) {  
        return addCookie(response, cookieName, value, maxAge, domain, "/");
    }

    /**
     * 添加cookie
     * @param response 对象
     * @param cookieName  名称
     * @param value 传值
     * @param maxAge 最大年龄
     * @param domain 域
     * @param path 路径
     * @return 返回cookie
     */
	public static Cookie addCookie(HttpServletResponse response,String cookieName,String value,Integer maxAge,String domain,String path) {  
        Cookie cookie = new Cookie(cookieName,value); 
        cookie.setMaxAge(maxAge);
        cookie.setDomain(domain);
        cookie.setPath(path);
        response.addCookie(cookie);
        return cookie;  
    }

    /**
     *  获取 cookie
     * @param request request对象
     * @param cookieName cookie名称
     * @return
     */
    public static String getCookie(HttpServletRequest request, String cookieName) {  
        Cookie[] cookies = request.getCookies();  
        if (cookies != null) {  
            for (Cookie cookie : cookies) {  
                if (cookieName.equalsIgnoreCase(cookie.getName())) {  
                    return cookie.getValue();  
                }  
            }  
        }  
        return null;
    }

    /**
     *  删除 cookie
     * @param response 对象
     * @param cookieName 名称
     */
    public static void delCookie(HttpServletResponse response, String cookieName) {  
    	Cookie cookie = new Cookie(cookieName,null); 
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}

