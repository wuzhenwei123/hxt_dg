package com.hxt.hHelp.controller;

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

import com.hxt.hHelp.model.HHelp;
import com.hxt.hHelp.service.HHelpService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SessionName;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2015年09月19日 18:16:53
 */
@Controller
@RequestMapping("/hHelp")
public class HHelpController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HHelpController.class); //Logger
	
	@Autowired
	private HHelpService hhelpService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		HHelp hhelp = new HHelp();
		int hhelpListCount = hhelpService.getHHelpListCount(hhelp);
		model.addAttribute("hhelpListCount", hhelpListCount);
		return "/hHelp/hHelpIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hHelp/hHelpAdd";
	}
	@RequestMapping(value = "/toShow/{id}", method = RequestMethod.GET)
	public String toShow(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HHelp hhelp1 = new HHelp();
		hhelp1.setId(id);
		HHelp hhelp = hhelpService.getHHelp(hhelp1);
		model.addAttribute("hhelp", hhelp);
		
		return "/hHelp/show";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HHelp hhelp1 = new HHelp();
		hhelp1.setId(id);
		HHelp hhelp = hhelpService.getHHelp(hhelp1);
		if(hhelp!=null){
			String str = hhelp.getContent();
			hhelp.setContent(str.replaceAll("\n", "\\n"));
		}
		model.addAttribute("hhelp", hhelp);
		
		return "/hHelp/hHelpUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHHelpList", method = RequestMethod.GET)
	public String getHHelpList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HHelp hhelp = new HHelp();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hhelp.setId(id);
			
			String content = RequestHandler.getString(request, "content");
			hhelp.setContent(content);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hhelp.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hhelp.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hhelp.setCreateId(createId);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hhelp.setRowStart(from);
			hhelp.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hhelp.setSortColumn(sortColumn);
			
			int hhelpListCount = hhelpService.getHHelpListCount(hhelp);
			ResponseList<HHelp> hhelpList = null;
			if ( hhelpListCount > 0 )
			{
				hhelpList = hhelpService.getHHelpList(hhelp);
			} else
			{
				hhelpList = new ResponseList<HHelp>();
			}
			// 设置数据总数
			hhelpList.setTotalResults(hhelpListCount);
			
			writeSuccessMsg("ok", hhelpList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHHelpBaseList", method = RequestMethod.GET)
	public String getHHelpBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HHelp hhelp = new HHelp();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hhelp.setId(id);
			
			String content = RequestHandler.getString(request, "content");
			hhelp.setContent(content);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hhelp.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hhelp.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hhelp.setCreateId(createId);
			
			List<HHelp> hhelpList = hhelpService.getHHelpBaseList(hhelp);
		
			writeSuccessMsg("ok", hhelpList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHHelp", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HHelp hhelp = new HHelp();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hhelp.setId(id);
			String content = RequestHandler.getString(request, "content");
			hhelp.setContent(content);
			Integer state = RequestHandler.getInteger(request, "state");
			hhelp.setState(state);
			Date createTime = RequestHandler.getDate(request, "createTime");
			hhelp.setCreateTime(new Date());
			Integer createId = RequestHandler.getInteger(request, "createId");

			Object adminUserId = getSession(request, SessionName.ADMIN_USER_ID);
			hhelp.setCreateId(Integer.valueOf(adminUserId.toString()));
			
			hhelpService.insertHHelp(hhelp);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHHelp", method = RequestMethod.POST)
	public String updateHHelp(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HHelp hhelp = new HHelp();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hhelp.setId(id);
			
			String content = RequestHandler.getString(request, "content");
			hhelp.setContent(content);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hhelp.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hhelp.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hhelp.setCreateId(createId);
			

			hhelpService.updateHHelp(hhelp);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHHelp", method = RequestMethod.POST)
	public String removeHHelp(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HHelp hhelp = new HHelp();
			Integer id = RequestHandler.getInteger(request, "id");
			hhelp.setId(id);

			hhelpService.removeHHelp(hhelp);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHHelp", method = RequestMethod.POST)
	public String removeAllHHelp(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HHelp hHelp = new HHelp();
						hHelp.setId(Integer.valueOf(id));
						hhelpService.removeHHelp(hHelp);
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
	
	@RequestMapping(value = "/toHelp", method = RequestMethod.GET)
	public String toHelp(HttpServletRequest request, HttpServletResponse response, Model model){
		try{
			HHelp hhelp1 = new HHelp();
			hhelp1.setState(1);
			HHelp hhelp = hhelpService.getHHelp(hhelp1);
			model.addAttribute("hhelp", hhelp);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/public/help";
	}
}
