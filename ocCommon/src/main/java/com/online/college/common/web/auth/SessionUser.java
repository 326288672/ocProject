package com.online.college.common.web.auth;

import java.util.Set;


/**
 * 创建权限用户类
 */
public interface SessionUser {
	
	String getUsername();
	
	Long getUserId();
	
	Set<String> getPermissions();
	
}
