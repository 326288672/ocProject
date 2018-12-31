package com.online.college.opt.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.common.page.TailPage;
import com.online.college.common.web.JsonView;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.auth.service.IAuthUserService;

/**
 * 用户权限校验controller层
 * @author 江龙
 * @date 2018-12-31 17:35
 */
@Controller
@RequestMapping("/user")
public class AuthUserController{

	@Autowired
	private IAuthUserService entityService;

	@RequestMapping(value = "/getById")
	@ResponseBody
	public String getById(Long id){
		AuthUser user = entityService.getById(id);
		return JsonView.render(user);
	}

	/**
	 * 实现查询分页
	 */
	@RequestMapping(value = "/userPageList")
	public  ModelAndView queryPage(AuthUser queryEntity , TailPage<AuthUser> page){
		ModelAndView mv = new ModelAndView("cms/user/userPageList");
		mv.addObject("curNav", "user");
		
		if(StringUtils.isNotEmpty(queryEntity.getUsername())){
			queryEntity.setUsername(queryEntity.getUsername().trim());
		}else{
			queryEntity.setUsername(null);
		}
		if(Integer.valueOf(-1).equals(queryEntity.getStatus())){
			queryEntity.setStatus(null);
		}
		page = entityService.queryPage(queryEntity,page);
		mv.addObject("page",page);
		mv.addObject("queryEntity",queryEntity);
		return mv;
	}
	
	@RequestMapping(value = "/doMerge")
	@ResponseBody
	public String doMerge(AuthUser entity){
		//不更新用户名
		//entity.setUsername(null);
		//不更新
		//entity.setRealname(null);
		entityService.updateSelectivity(entity);
		return new JsonView(0).toString();
	}
	
}

