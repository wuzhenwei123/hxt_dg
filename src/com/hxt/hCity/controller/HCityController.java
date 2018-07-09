package com.hxt.hCity.controller;

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

import com.hxt.hCity.model.HCity;
import com.hxt.hCity.service.HCityService;
import com.hxt.hProvince.model.HProvince;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	zhangyiyang
 * @time	2016年08月02日 22:11:23
 */
@Controller
@RequestMapping("/hCity")
public class HCityController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HCityController.class); //Logger
	
	@Autowired
	private HCityService hcityService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hCity/hCityIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hCity/hCityAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HCity hcity1 = new HCity();
		hcity1.setId(id);
		HCity hcity = hcityService.getHCity(hcity1);
		model.addAttribute("hcity", hcity);
		
		return "/hCity/hCityUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHCityList", method = RequestMethod.GET)
	public String getHCityList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HCity hcity = new HCity();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hcity.setId(id);
			
			String cityCode = RequestHandler.getString(request, "cityCode");
			hcity.setCityCode(cityCode);
			
			String cityName = RequestHandler.getString(request, "cityName");
			hcity.setCityName(cityName);
			
			String provinceCode = RequestHandler.getString(request, "provinceCode");
			hcity.setProvinceCode(provinceCode);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hcity.setRowStart(from);
			hcity.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hcity.setSortColumn(sortColumn);
			
			int hcityListCount = hcityService.getHCityListCount(hcity);
			ResponseList<HCity> hcityList = null;
			if ( hcityListCount > 0 )
			{
				hcityList = hcityService.getHCityList(hcity);
			} else
			{
				hcityList = new ResponseList<HCity>();
			}
			// 设置数据总数
			hcityList.setTotalResults(hcityListCount);
			
			writeSuccessMsg("ok", hcityList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/getHCityJson", method = RequestMethod.GET)
	public String getHCityJson(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HCity hcity = new HCity();
			String proCode = RequestHandler.getString(request, "provinceCode");
			if(StringUtils.isNotBlank(proCode)){
				hcity.setProvinceCode(proCode);
			}else{
				hcity.setProvinceCode("a");
			}
			List<HCity> hprovinceList = hcityService.getHCityBaseList(hcity);
			writeSuccessMsg("ok", hprovinceList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/getHCityBaseList", method = RequestMethod.GET)
	public String getHCityBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HCity hcity = new HCity();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hcity.setId(id);
			
			String cityCode = RequestHandler.getString(request, "cityCode");
			hcity.setCityCode(cityCode);
			
			String cityName = RequestHandler.getString(request, "cityName");
			hcity.setCityName(cityName);
			
			String provinceCode = RequestHandler.getString(request, "provinceCode");
			hcity.setProvinceCode(provinceCode);
			
			List<HCity> hcityList = hcityService.getHCityBaseList(hcity);
		
			writeSuccessMsg("ok", hcityList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHCity", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HCity hcity = new HCity();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hcity.setId(id);
			String cityCode = RequestHandler.getString(request, "cityCode");
			hcity.setCityCode(cityCode);
			String cityName = RequestHandler.getString(request, "cityName");
			hcity.setCityName(cityName);
			String provinceCode = RequestHandler.getString(request, "provinceCode");
			hcity.setProvinceCode(provinceCode);
				
			hcityService.insertHCity(hcity);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHCity", method = RequestMethod.POST)
	public String updateHCity(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HCity hcity = new HCity();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hcity.setId(id);
			
			String cityCode = RequestHandler.getString(request, "cityCode");
			hcity.setCityCode(cityCode);
			
			String cityName = RequestHandler.getString(request, "cityName");
			hcity.setCityName(cityName);
			
			String provinceCode = RequestHandler.getString(request, "provinceCode");
			hcity.setProvinceCode(provinceCode);
			

			hcityService.updateHCity(hcity);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHCity", method = RequestMethod.POST)
	public String removeHCity(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HCity hcity = new HCity();
			Integer id = RequestHandler.getInteger(request, "id");
			hcity.setId(id);

			hcityService.removeHCity(hcity);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHCity", method = RequestMethod.POST)
	public String removeAllHCity(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HCity hCity = new HCity();
						hCity.setId(Integer.valueOf(id));
						hcityService.removeHCity(hCity);
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
