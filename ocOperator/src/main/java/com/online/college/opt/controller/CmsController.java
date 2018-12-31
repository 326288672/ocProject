package com.online.college.opt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.common.web.SessionContext;

/**
 * 后台管理Controller层
 * @author 江龙
 * @date
 * @email jianglong_wang@sui.com
 */
@Controller
@RequestMapping()
public class CmsController {
	
	/**
	 * 首页
	 */
	@RequestMapping("/index")
	public ModelAndView index(){
		if(SessionContext.isLogin()){
			ModelAndView mv = new ModelAndView("cms/index");
			mv.addObject("curNav", "home");
			return mv;
		}else{
			return new ModelAndView("auth/login");
		}
	}
	
}

