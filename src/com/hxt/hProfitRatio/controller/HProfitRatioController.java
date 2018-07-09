package com.hxt.hProfitRatio.controller;

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

import com.hxt.hProfitRatio.model.HProfitRatio;
import com.hxt.hProfitRatio.service.HProfitRatioService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	zlb
 * @time	2016年08月04日 21:43:45
 */
@Controller
@RequestMapping("/hProfitRatio")
public class HProfitRatioController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HProfitRatioController.class); //Logger
	
	@Autowired
	private HProfitRatioService hprofitratioService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hProfitRatio/hProfitRatioIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hProfitRatio/hProfitRatioAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HProfitRatio hprofitratio1 = new HProfitRatio();
		hprofitratio1.setId(id);
		HProfitRatio hprofitratio = hprofitratioService.getHProfitRatio(hprofitratio1);
		model.addAttribute("hprofitratio", hprofitratio);
		
		return "/hProfitRatio/hProfitRatioUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHProfitRatioList", method = RequestMethod.GET)
	public String getHProfitRatioList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HProfitRatio hprofitratio = new HProfitRatio();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hprofitratio.setId(id);
			
			String name = RequestHandler.getString(request, "name");
			hprofitratio.setName(name);
			
			String manager_ratio = RequestHandler.getString(request, "manager_ratio");
			hprofitratio.setManager_ratio(manager_ratio);
			
			String ont_agent_ratio = RequestHandler.getString(request, "ont_agent_ratio");
			hprofitratio.setOnt_agent_ratio(ont_agent_ratio);
			
			String two_agent_ratio = RequestHandler.getString(request, "two_agent_ratio");
			hprofitratio.setTwo_agent_ratio(two_agent_ratio);
			
			String personal_ratio = RequestHandler.getString(request, "personal_ratio");
			hprofitratio.setPersonal_ratio(personal_ratio);
			
			Integer is_default = RequestHandler.getInteger(request, "is_default");
			hprofitratio.setIs_default(is_default);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hprofitratio.setState(state);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hprofitratio.setRowStart(from);
			hprofitratio.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hprofitratio.setSortColumn(sortColumn);
			
			int hprofitratioListCount = hprofitratioService.getHProfitRatioListCount(hprofitratio);
			ResponseList<HProfitRatio> hprofitratioList = null;
			if ( hprofitratioListCount > 0 )
			{
				hprofitratioList = hprofitratioService.getHProfitRatioList(hprofitratio);
			} else
			{
				hprofitratioList = new ResponseList<HProfitRatio>();
			}
			// 设置数据总数
			hprofitratioList.setTotalResults(hprofitratioListCount);
			
			writeSuccessMsg("ok", hprofitratioList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHProfitRatioBaseList", method = RequestMethod.GET)
	public String getHProfitRatioBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HProfitRatio hprofitratio = new HProfitRatio();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hprofitratio.setId(id);
			
			String name = RequestHandler.getString(request, "name");
			hprofitratio.setName(name);
			
			String manager_ratio = RequestHandler.getString(request, "manager_ratio");
			hprofitratio.setManager_ratio(manager_ratio);
			
			String ont_agent_ratio = RequestHandler.getString(request, "ont_agent_ratio");
			hprofitratio.setOnt_agent_ratio(ont_agent_ratio);
			
			String two_agent_ratio = RequestHandler.getString(request, "two_agent_ratio");
			hprofitratio.setTwo_agent_ratio(two_agent_ratio);
			
			String personal_ratio = RequestHandler.getString(request, "personal_ratio");
			hprofitratio.setPersonal_ratio(personal_ratio);
			
			Integer is_default = RequestHandler.getInteger(request, "is_default");
			hprofitratio.setIs_default(is_default);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hprofitratio.setState(state);
			
			List<HProfitRatio> hprofitratioList = hprofitratioService.getHProfitRatioBaseList(hprofitratio);
		
			writeSuccessMsg("ok", hprofitratioList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHProfitRatio", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HProfitRatio hprofitratio = new HProfitRatio();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hprofitratio.setId(id);
			String name = RequestHandler.getString(request, "name");
			hprofitratio.setName(name);
			String manager_ratio = RequestHandler.getString(request, "manager_ratio");
			hprofitratio.setManager_ratio(manager_ratio);
			String ont_agent_ratio = RequestHandler.getString(request, "ont_agent_ratio");
			hprofitratio.setOnt_agent_ratio(ont_agent_ratio);
			String two_agent_ratio = RequestHandler.getString(request, "two_agent_ratio");
			hprofitratio.setTwo_agent_ratio(two_agent_ratio);
			String personal_ratio = RequestHandler.getString(request, "personal_ratio");
			hprofitratio.setPersonal_ratio(personal_ratio);
			Integer is_default = RequestHandler.getInteger(request, "is_default");
			hprofitratio.setIs_default(is_default);
			Integer state = RequestHandler.getInteger(request, "state");
			hprofitratio.setState(state);
				
			hprofitratioService.insertHProfitRatio(hprofitratio);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHProfitRatio", method = RequestMethod.POST)
	public String updateHProfitRatio(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HProfitRatio hprofitratio = new HProfitRatio();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hprofitratio.setId(id);
			
			String name = RequestHandler.getString(request, "name");
			hprofitratio.setName(name);
			
			String manager_ratio = RequestHandler.getString(request, "manager_ratio");
			hprofitratio.setManager_ratio(manager_ratio);
			
			String ont_agent_ratio = RequestHandler.getString(request, "ont_agent_ratio");
			hprofitratio.setOnt_agent_ratio(ont_agent_ratio);
			
			String two_agent_ratio = RequestHandler.getString(request, "two_agent_ratio");
			hprofitratio.setTwo_agent_ratio(two_agent_ratio);
			
			String personal_ratio = RequestHandler.getString(request, "personal_ratio");
			hprofitratio.setPersonal_ratio(personal_ratio);
			
			Integer is_default = RequestHandler.getInteger(request, "is_default");
			hprofitratio.setIs_default(is_default);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hprofitratio.setState(state);
			

			hprofitratioService.updateHProfitRatio(hprofitratio);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHProfitRatio", method = RequestMethod.POST)
	public String removeHProfitRatio(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HProfitRatio hprofitratio = new HProfitRatio();
			Integer id = RequestHandler.getInteger(request, "id");
			hprofitratio.setId(id);

			hprofitratioService.removeHProfitRatio(hprofitratio);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHProfitRatio", method = RequestMethod.POST)
	public String removeAllHProfitRatio(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HProfitRatio hProfitRatio = new HProfitRatio();
						hProfitRatio.setId(Integer.valueOf(id));
						hprofitratioService.removeHProfitRatio(hProfitRatio);
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
