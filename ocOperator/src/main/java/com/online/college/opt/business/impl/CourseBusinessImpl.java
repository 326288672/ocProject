package com.online.college.opt.business.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.college.core.consts.CourseEnum;
import com.online.college.core.course.domain.CourseSection;
import com.online.college.core.course.service.ICourseSectionService;
import com.online.college.opt.business.ICourseBusiness;
import com.online.college.opt.vo.CourseSectionVO;

/**
 * 课程业务层
 */
@Service
public class CourseBusinessImpl implements ICourseBusiness {
	
	@Autowired
	private ICourseSectionService courseSectionService;
	
	/**
	 * 获取课程章节
	 */
	@Override
	public List<CourseSectionVO> queryCourseSection(Long courseId){
		List<CourseSectionVO> resultList = new ArrayList();
		CourseSection queryEntity = new CourseSection();
		queryEntity.setCourseId(courseId);
		/**
		 * 课程上架
		 */
		queryEntity.setOnsale(CourseEnum.ONSALE.value());
		
		Map<Long,CourseSectionVO> tmpMap = new LinkedHashMap();
		Iterator<CourseSection> it = courseSectionService.queryAll(queryEntity).iterator();
		while(it.hasNext()){
			CourseSection item = it.next();
			/**
			 * 章
			 */
			if(Long.valueOf(0).equals(item.getParentId())){
				CourseSectionVO vo = new CourseSectionVO();
				BeanUtils.copyProperties(item, vo);
				tmpMap.put(vo.getId(), vo);
			}else{
				/**
				 * 小节添加到大章中
				 */
				tmpMap.get(item.getParentId()).getSections().add(item);
			}
		}
		for(CourseSectionVO vo : tmpMap.values()){
			resultList.add(vo);
		}
		return resultList;
	}
	
}
