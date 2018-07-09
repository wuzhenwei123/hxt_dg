package com.hxt.hProvince.controller;

import java.util.Date;
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

import com.hxt.hProvince.model.HProvince;
import com.hxt.hProvince.service.HProvinceService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	zhangyiyang
 * @time	2016年08月02日 22:11:34
 */
@Controller
@RequestMapping("/hProvince")
public class HProvinceController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HProvinceController.class); //Logger
	
	@Autowired
	private HProvinceService hprovinceService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hProvince/hProvinceIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hProvince/hProvinceAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HProvince hprovince1 = new HProvince();
		hprovince1.setId(id);
		HProvince hprovince = hprovinceService.getHProvince(hprovince1);
		model.addAttribute("hprovince", hprovince);
		
		return "/hProvince/hProvinceUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHProvinceList", method = RequestMethod.GET)
	public String getHProvinceList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HProvince hprovince = new HProvince();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hprovince.setId(id);
			
			String provinceCode = RequestHandler.getString(request, "provinceCode");
			hprovince.setProvinceCode(provinceCode);
			
			String provinceName = RequestHandler.getString(request, "provinceName");
			hprovince.setProvinceName(provinceName);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hprovince.setRowStart(from);
			hprovince.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hprovince.setSortColumn(sortColumn);
			
			int hprovinceListCount = hprovinceService.getHProvinceListCount(hprovince);
			ResponseList<HProvince> hprovinceList = null;
			if ( hprovinceListCount > 0 )
			{
				hprovinceList = hprovinceService.getHProvinceList(hprovince);
			} else
			{
				hprovinceList = new ResponseList<HProvince>();
			}
			// 设置数据总数
			hprovinceList.setTotalResults(hprovinceListCount);
			
			writeSuccessMsg("ok", hprovinceList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/getHProvinceJson", method = RequestMethod.GET)
	public String getHProvinceJson(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HProvince hprovince = new HProvince();
			Integer id = RequestHandler.getInteger(request, "id");
			hprovince.setId(id);
			List<HProvince> hprovinceList = hprovinceService.getHProvinceBaseList(hprovince);
			writeSuccessMsg("ok", hprovinceList, response);
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/getHProvinceBaseList", method = RequestMethod.GET)
	public String getHProvinceBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HProvince hprovince = new HProvince();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hprovince.setId(id);
			
			String provinceCode = RequestHandler.getString(request, "provinceCode");
			hprovince.setProvinceCode(provinceCode);
			
			String provinceName = RequestHandler.getString(request, "provinceName");
			hprovince.setProvinceName(provinceName);
			
			List<HProvince> hprovinceList = hprovinceService.getHProvinceBaseList(hprovince);
		
			writeSuccessMsg("ok", hprovinceList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHProvince", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HProvince hprovince = new HProvince();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hprovince.setId(id);
			String provinceCode = RequestHandler.getString(request, "provinceCode");
			hprovince.setProvinceCode(provinceCode);
			String provinceName = RequestHandler.getString(request, "provinceName");
			hprovince.setProvinceName(provinceName);
				
			hprovinceService.insertHProvince(hprovince);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHProvince", method = RequestMethod.POST)
	public String updateHProvince(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HProvince hprovince = new HProvince();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hprovince.setId(id);
			
			String provinceCode = RequestHandler.getString(request, "provinceCode");
			hprovince.setProvinceCode(provinceCode);
			
			String provinceName = RequestHandler.getString(request, "provinceName");
			hprovince.setProvinceName(provinceName);
			

			hprovinceService.updateHProvince(hprovince);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHProvince", method = RequestMethod.POST)
	public String removeHProvince(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HProvince hprovince = new HProvince();
			Integer id = RequestHandler.getInteger(request, "id");
			hprovince.setId(id);

			hprovinceService.removeHProvince(hprovince);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHProvince", method = RequestMethod.POST)
	public String removeAllHProvince(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HProvince hProvince = new HProvince();
						hProvince.setId(Integer.valueOf(id));
						hprovinceService.removeHProvince(hProvince);
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
