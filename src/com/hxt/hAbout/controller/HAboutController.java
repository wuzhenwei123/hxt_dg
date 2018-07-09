package com.hxt.hAbout.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.base.controller.BaseController;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SessionName;
import com.hxt.hAbout.model.HAbout;
import com.hxt.hAbout.service.HAboutService;
/**
 * @author	keeny
 * @time	2015年08月21日 13:26:17
 */
@Controller
@RequestMapping("/hAbout")
public class HAboutController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HAboutController.class); //Logger
	
	@Autowired
	private HAboutService haboutService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hAbout/hAboutIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hAbout/hAboutAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HAbout habout1 = new HAbout();
		habout1.setId(id);
		HAbout habout = haboutService.getHAbout(habout1);
		model.addAttribute("habout", habout);
		
		return "/hAbout/hAboutUpdate";
	}
	@RequestMapping(value = "/toShow/{id}", method = RequestMethod.GET)
	public String toShow(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HAbout habout1 = new HAbout();
		habout1.setId(id);
		HAbout habout = haboutService.getHAbout(habout1);
		model.addAttribute("habout", habout);
		
		return "/hAbout/show";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHAboutList", method = RequestMethod.GET)
	public String getHAboutList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAbout habout = new HAbout();
			
			Integer id = RequestHandler.getInteger(request, "id");
			habout.setId(id);
			
			String content = RequestHandler.getString(request, "content");
			habout.setContent(content);
			
			Integer state = RequestHandler.getInteger(request, "state");
			habout.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			habout.setCreateTime(createTime);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			habout.setRowStart(from);
			habout.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			habout.setSortColumn(sortColumn);
			
			int haboutListCount = haboutService.getHAboutListCount(habout);
			ResponseList<HAbout> haboutList = null;
			if ( haboutListCount > 0 )
			{
				haboutList = haboutService.getHAboutList(habout);
			} else
			{
				haboutList = new ResponseList<HAbout>();
			}
			// 设置数据总数
			haboutList.setTotalResults(haboutListCount);
			
			writeSuccessMsg("ok", haboutList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHAboutBaseList", method = RequestMethod.GET)
	public String getHAboutBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAbout habout = new HAbout();
			
			Integer id = RequestHandler.getInteger(request, "id");
			habout.setId(id);
			
			String content = RequestHandler.getString(request, "content");
			habout.setContent(content);
			
			Integer state = RequestHandler.getInteger(request, "state");
			habout.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			habout.setCreateTime(createTime);
			
			List<HAbout> haboutList = haboutService.getHAboutBaseList(habout);
		
			writeSuccessMsg("ok", haboutList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHAbout", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HAbout habout = new HAbout();
			
			Integer id = RequestHandler.getInteger(request, "id");
			habout.setId(id);
			String content = RequestHandler.getString(request, "content");
			habout.setContent(content);
			Integer state = RequestHandler.getInteger(request, "state");
			habout.setState(state);
			Date createTime = RequestHandler.getDate(request, "createTime");
			habout.setCreateTime(new Date());
			habout.setCreateId(Integer.valueOf(getSession(request, SessionName.ADMIN_USER_ID).toString()));
				
			haboutService.insertHAbout(habout);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHAbout", method = RequestMethod.POST)
	public String updateHAbout(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAbout habout = new HAbout();
			
			Integer id = RequestHandler.getInteger(request, "id");
			habout.setId(id);
			
			String content = RequestHandler.getString(request, "content");
			habout.setContent(content);
			
			Integer state = RequestHandler.getInteger(request, "state");
			habout.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			habout.setCreateTime(createTime);
			

			haboutService.updateHAbout(habout);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHAbout", method = RequestMethod.POST)
	public String removeHAbout(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAbout habout = new HAbout();
			Integer id = RequestHandler.getInteger(request, "id");
			habout.setId(id);

			haboutService.removeHAbout(habout);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHAbout", method = RequestMethod.POST)
	public String removeAllHAbout(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HAbout hAbout = new HAbout();
						hAbout.setId(Integer.valueOf(id));
						haboutService.removeHAbout(hAbout);
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
	
	@RequestMapping(value = "/toAboutUs", method = RequestMethod.GET)
	public String toAboutUs(HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HAbout habout1 = new HAbout();
		habout1.setState(1);
		HAbout habout = haboutService.getHAbout(habout1);
		model.addAttribute("habout", habout);
		
		return "/public/aboutUs";
	}
}
