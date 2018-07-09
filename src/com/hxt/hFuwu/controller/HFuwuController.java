package com.hxt.hFuwu.controller;

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

import com.hxt.hFuwu.model.HFuwu;
import com.hxt.hFuwu.service.HFuwuService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SessionName;
import com.base.controller.BaseController;
/**
 * @author	zlb
 * @time	2015年09月19日 19:01:41
 */
@Controller
@RequestMapping("/hFuwu")
public class HFuwuController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HFuwuController.class); //Logger
	
	@Autowired
	private HFuwuService hfuwuService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		HFuwu hfuwu = new HFuwu();
		int hfuwuListCount = hfuwuService.getHFuwuListCount(hfuwu);
		model.addAttribute("hfuwuListCount", hfuwuListCount);
		return "/hFuwu/hFuwuIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hFuwu/hFuwuAdd";
	}
	@RequestMapping(value = "/toShow/{id}", method = RequestMethod.GET)
	public String toShow(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HFuwu hfuwu1 = new HFuwu();
		hfuwu1.setId(id);
		HFuwu hfuwu = hfuwuService.getHFuwu(hfuwu1);
		model.addAttribute("hfuwu", hfuwu);
		
		return "/hFuwu/show";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HFuwu hfuwu1 = new HFuwu();
		hfuwu1.setId(id);
		HFuwu hfuwu = hfuwuService.getHFuwu(hfuwu1);
		model.addAttribute("hfuwu", hfuwu);
		
		return "/hFuwu/hFuwuUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHFuwuList", method = RequestMethod.GET)
	public String getHFuwuList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HFuwu hfuwu = new HFuwu();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hfuwu.setId(id);
			
			String content = RequestHandler.getString(request, "content");
			hfuwu.setContent(content);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hfuwu.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hfuwu.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hfuwu.setCreateId(createId);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hfuwu.setRowStart(from);
			hfuwu.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hfuwu.setSortColumn(sortColumn);
			
			int hfuwuListCount = hfuwuService.getHFuwuListCount(hfuwu);
			ResponseList<HFuwu> hfuwuList = null;
			if ( hfuwuListCount > 0 )
			{
				hfuwuList = hfuwuService.getHFuwuList(hfuwu);
			} else
			{
				hfuwuList = new ResponseList<HFuwu>();
			}
			// 设置数据总数
			hfuwuList.setTotalResults(hfuwuListCount);
			
			writeSuccessMsg("ok", hfuwuList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHFuwuBaseList", method = RequestMethod.GET)
	public String getHFuwuBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HFuwu hfuwu = new HFuwu();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hfuwu.setId(id);
			
			String content = RequestHandler.getString(request, "content");
			hfuwu.setContent(content);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hfuwu.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hfuwu.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hfuwu.setCreateId(createId);
			
			List<HFuwu> hfuwuList = hfuwuService.getHFuwuBaseList(hfuwu);
		
			writeSuccessMsg("ok", hfuwuList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHFuwu", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HFuwu hfuwu = new HFuwu();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hfuwu.setId(id);
			String content = RequestHandler.getString(request, "content");
			hfuwu.setContent(content);
			Integer state = RequestHandler.getInteger(request, "state");
			hfuwu.setState(state);
			Date createTime = RequestHandler.getDate(request, "createTime");
			hfuwu.setCreateTime(new Date());
			Object adminUserId = getSession(request, SessionName.ADMIN_USER_ID);
			hfuwu.setCreateId(Integer.valueOf(adminUserId.toString()));
				
			hfuwuService.insertHFuwu(hfuwu);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHFuwu", method = RequestMethod.POST)
	public String updateHFuwu(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HFuwu hfuwu = new HFuwu();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hfuwu.setId(id);
			
			String content = RequestHandler.getString(request, "content");
			hfuwu.setContent(content);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hfuwu.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hfuwu.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hfuwu.setCreateId(createId);
			

			hfuwuService.updateHFuwu(hfuwu);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHFuwu", method = RequestMethod.POST)
	public String removeHFuwu(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HFuwu hfuwu = new HFuwu();
			Integer id = RequestHandler.getInteger(request, "id");
			hfuwu.setId(id);

			hfuwuService.removeHFuwu(hfuwu);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHFuwu", method = RequestMethod.POST)
	public String removeAllHFuwu(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HFuwu hFuwu = new HFuwu();
						hFuwu.setId(Integer.valueOf(id));
						hfuwuService.removeHFuwu(hFuwu);
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
	
	@RequestMapping(value = "/toTuoShou", method = RequestMethod.GET)
	public String toTuoShou(HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HFuwu hfuwu1 = new HFuwu();
		hfuwu1.setState(1);
		HFuwu hfuwu = hfuwuService.getHFuwu(hfuwu1);
		model.addAttribute("hfuwu", hfuwu);
		
		return "/front/tuoShou";
	}
}
