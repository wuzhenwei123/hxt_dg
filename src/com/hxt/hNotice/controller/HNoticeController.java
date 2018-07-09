package com.hxt.hNotice.controller;

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

import com.hxt.hNotice.model.HNotice;
import com.hxt.hNotice.service.HNoticeService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SessionName;
import com.base.controller.BaseController;
/**
 * @author	zlb
 * @time	2015年09月19日 20:15:21
 */
@Controller
@RequestMapping("/hNotice")
public class HNoticeController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HNoticeController.class); //Logger
	
	@Autowired
	private HNoticeService hnoticeService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hNotice/hNoticeIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hNotice/hNoticeAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HNotice hnotice1 = new HNotice();
		hnotice1.setId(id);
		HNotice hnotice = hnoticeService.getHNotice(hnotice1);
		model.addAttribute("hnotice", hnotice);
		
		return "/hNotice/hNoticeUpdate";
	}
	@RequestMapping(value = "/toShow/{id}", method = RequestMethod.GET)
	public String toShow(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HNotice hnotice1 = new HNotice();
		hnotice1.setId(id);
		HNotice hnotice = hnoticeService.getHNotice(hnotice1);
		model.addAttribute("hnotice", hnotice);
		
		return "/hNotice/show";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHNoticeList", method = RequestMethod.GET)
	public String getHNoticeList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HNotice hnotice = new HNotice();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hnotice.setId(id);
			
			String content = RequestHandler.getString(request, "content");
			hnotice.setContent(content);
			
			String title = RequestHandler.getString(request, "title");
			hnotice.setTitle(title);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hnotice.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hnotice.setCreateId(createId);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hnotice.setState(state);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hnotice.setRowStart(from);
			hnotice.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hnotice.setSortColumn(sortColumn);
			
			int hnoticeListCount = hnoticeService.getHNoticeListCount(hnotice);
			ResponseList<HNotice> hnoticeList = null;
			if ( hnoticeListCount > 0 )
			{
				hnoticeList = hnoticeService.getHNoticeList(hnotice);
			} else
			{
				hnoticeList = new ResponseList<HNotice>();
			}
			// 设置数据总数
			hnoticeList.setTotalResults(hnoticeListCount);
			
			writeSuccessMsg("ok", hnoticeList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHNoticeBaseList", method = RequestMethod.GET)
	public String getHNoticeBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HNotice hnotice = new HNotice();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hnotice.setId(id);
			
			String content = RequestHandler.getString(request, "content");
			hnotice.setContent(content);
			
			String title = RequestHandler.getString(request, "title");
			hnotice.setTitle(title);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hnotice.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hnotice.setCreateId(createId);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hnotice.setState(state);
			
			List<HNotice> hnoticeList = hnoticeService.getHNoticeBaseList(hnotice);
		
			writeSuccessMsg("ok", hnoticeList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHNotice", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HNotice hnotice = new HNotice();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hnotice.setId(id);
			String content = RequestHandler.getString(request, "content");
			hnotice.setContent(content);
			String title = RequestHandler.getString(request, "title");
			hnotice.setTitle(title);
			Date createTime = RequestHandler.getDate(request, "createTime");
			hnotice.setCreateTime(new Date());
			Object adminUserId = getSession(request, SessionName.ADMIN_USER_ID);
			hnotice.setCreateId(Integer.valueOf(adminUserId.toString()));
			Integer state = RequestHandler.getInteger(request, "state");
			hnotice.setState(state);
				
			hnoticeService.insertHNotice(hnotice);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHNotice", method = RequestMethod.POST)
	public String updateHNotice(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HNotice hnotice = new HNotice();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hnotice.setId(id);
			
			String content = RequestHandler.getString(request, "content");
			hnotice.setContent(content);
			
			String title = RequestHandler.getString(request, "title");
			hnotice.setTitle(title);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hnotice.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hnotice.setCreateId(createId);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hnotice.setState(state);
			

			hnoticeService.updateHNotice(hnotice);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHNotice", method = RequestMethod.POST)
	public String removeHNotice(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HNotice hnotice = new HNotice();
			Integer id = RequestHandler.getInteger(request, "id");
			hnotice.setId(id);

			hnoticeService.removeHNotice(hnotice);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHNotice", method = RequestMethod.POST)
	public String removeAllHNotice(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HNotice hNotice = new HNotice();
						hNotice.setId(Integer.valueOf(id));
						hnoticeService.removeHNotice(hNotice);
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
