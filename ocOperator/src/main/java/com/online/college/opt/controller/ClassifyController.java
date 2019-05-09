package com.online.college.opt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.common.page.TailPage;
import com.online.college.common.web.JsonView;
import com.online.college.core.consts.domain.ConstsClassify;
import com.online.college.core.consts.service.IConstsClassifyService;
import com.online.college.opt.business.IPortalBusiness;
import com.online.college.opt.vo.ConstsClassifyVO;

/**
 * 实现课程分类管理类
 * @author 江龙
 * @date 2019-01-01
 * @email jianglong_wang@sui.com
 */
@Controller
@RequestMapping("/classify")
public class ClassifyController{

	@Autowired
	private IConstsClassifyService entityService;
	@Autowired
	private IPortalBusiness portalBusiness;
	@RequestMapping(value = "/getById")
	@ResponseBody
	public String getById(Long id){
		return JsonView.render(entityService.getById(id));
	}

	/**
	 * 请求一、二级数据
	 * @param queryEntity
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/index")
	public  ModelAndView classifyIndex(ConstsClassify queryEntity , TailPage<ConstsClassify> page){
		ModelAndView mv = new ModelAndView("cms/classify/classifyIndex");
		mv.addObject("curNav", "classify");
		Map<String,ConstsClassifyVO> classifyMap = portalBusiness.queryAllClassifyMap();
		//所有一级分类
		List<ConstsClassifyVO> classifysList = new ArrayList<ConstsClassifyVO>();
		for(ConstsClassifyVO vo : classifyMap.values()){
			classifysList.add(vo);
		}
		mv.addObject("classifys", classifysList);
		List<ConstsClassify> subClassifys = new ArrayList<ConstsClassify>();
		for(ConstsClassifyVO vo : classifyMap.values()){
			subClassifys.addAll(vo.getSubClassifyList());
		}
		//所有二级分类
		mv.addObject("subClassifys", subClassifys);
		return mv;
	}

	/**
	 * 添加分类
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/doMerge")
	@ResponseBody
	public String doMerge(ConstsClassify entity){
		if(entity.getId() == null){
			ConstsClassify tmpEntity = entityService.getByCode(entity.getCode());
			if(tmpEntity != null){
				return JsonView.render(1, "此编码已存在");
			}
			entityService.createSelectivity(entity);
		}else{
			entityService.updateSelectivity(entity);
		}
		return new JsonView().toString();
	}

	/**
	 * 删除一、二级分类
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/deleteLogic")
	@ResponseBody
	public String deleteLogic(ConstsClassify entity){
		entityService.deleteLogic(entity);
		return new JsonView().toString();
	}
}

