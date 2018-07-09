package com.hxt.hArea.controller;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hxt.hArea.model.HArea;
import com.hxt.hArea.service.HAreaService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	zhangyiyang
 * @time	2016年08月02日 22:11:11
 */
@Controller
@RequestMapping("/hArea")
public class HAreaController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HAreaController.class); //Logger
	
	@Autowired
	private HAreaService hareaService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hArea/hAreaIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hArea/hAreaAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HArea harea1 = new HArea();
		harea1.setId(id);
		HArea harea = hareaService.getHArea(harea1);
		model.addAttribute("harea", harea);
		
		return "/hArea/hAreaUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHAreaList", method = RequestMethod.GET)
	public String getHAreaList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HArea harea = new HArea();
			
			Integer id = RequestHandler.getInteger(request, "id");
			harea.setId(id);
			
			String areaCode = RequestHandler.getString(request, "areaCode");
			harea.setAreaCode(areaCode);
			
			String areaName = RequestHandler.getString(request, "areaName");
			harea.setAreaName(areaName);
			
			String cityCode = RequestHandler.getString(request, "cityCode");
			harea.setCityCode(cityCode);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			harea.setRowStart(from);
			harea.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			harea.setSortColumn(sortColumn);
			
			int hareaListCount = hareaService.getHAreaListCount(harea);
			ResponseList<HArea> hareaList = null;
			if ( hareaListCount > 0 )
			{
				hareaList = hareaService.getHAreaList(harea);
			} else
			{
				hareaList = new ResponseList<HArea>();
			}
			// 设置数据总数
			hareaList.setTotalResults(hareaListCount);
			
			writeSuccessMsg("ok", hareaList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/getHAreaJson", method = RequestMethod.GET)
	public String getHAreaJson(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HArea harea = new HArea();
			String cityCode = RequestHandler.getString(request, "cityCode");
			if(StringUtils.isNotBlank(cityCode)){
				harea.setCityCode(cityCode);
			}else{
				harea.setCityCode("a");//结果为空
			}
			writeSuccessMsg("ok",  hareaService.getHAreaBaseList(harea), response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/getHAreaBaseList", method = RequestMethod.GET)
	public String getHAreaBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HArea harea = new HArea();
			
			Integer id = RequestHandler.getInteger(request, "id");
			harea.setId(id);
			
			String areaCode = RequestHandler.getString(request, "areaCode");
			harea.setAreaCode(areaCode);
			
			String areaName = RequestHandler.getString(request, "areaName");
			harea.setAreaName(areaName);
			
			String cityCode = RequestHandler.getString(request, "cityCode");
			harea.setCityCode(cityCode);
			
			List<HArea> hareaList = hareaService.getHAreaBaseList(harea);
		
			writeSuccessMsg("ok", hareaList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHArea", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HArea harea = new HArea();
			
			Integer id = RequestHandler.getInteger(request, "id");
			harea.setId(id);
			String areaCode = RequestHandler.getString(request, "areaCode");
			harea.setAreaCode(areaCode);
			String areaName = RequestHandler.getString(request, "areaName");
			harea.setAreaName(areaName);
			String cityCode = RequestHandler.getString(request, "cityCode");
			harea.setCityCode(cityCode);
				
			hareaService.insertHArea(harea);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHArea", method = RequestMethod.POST)
	public String updateHArea(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HArea harea = new HArea();
			
			Integer id = RequestHandler.getInteger(request, "id");
			harea.setId(id);
			
			String areaCode = RequestHandler.getString(request, "areaCode");
			harea.setAreaCode(areaCode);
			
			String areaName = RequestHandler.getString(request, "areaName");
			harea.setAreaName(areaName);
			
			String cityCode = RequestHandler.getString(request, "cityCode");
			harea.setCityCode(cityCode);
			

			hareaService.updateHArea(harea);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHArea", method = RequestMethod.POST)
	public String removeHArea(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HArea harea = new HArea();
			Integer id = RequestHandler.getInteger(request, "id");
			harea.setId(id);

			hareaService.removeHArea(harea);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHArea", method = RequestMethod.POST)
	public String removeAllHArea(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HArea hArea = new HArea();
						hArea.setId(Integer.valueOf(id));
						hareaService.removeHArea(hArea);
					}
				}
			}
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
}
