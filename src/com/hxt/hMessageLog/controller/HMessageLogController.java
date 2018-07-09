package com.hxt.hMessageLog.controller;

import java.util.Calendar;
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

import com.hxt.hMessageLog.model.HMessageLog;
import com.hxt.hMessageLog.service.HMessageLogService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	zhangyiyang
 * @time	2016年08月23日 22:01:10
 */
@Controller
@RequestMapping("/hMessageLog")
public class HMessageLogController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HMessageLogController.class); //Logger
	
	@Autowired
	private HMessageLogService hmessagelogService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hMessageLog/hMessageLogIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hMessageLog/hMessageLogAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HMessageLog hmessagelog1 = new HMessageLog();
		hmessagelog1.setId(id);
		HMessageLog hmessagelog = hmessagelogService.getHMessageLog(hmessagelog1);
		model.addAttribute("hmessagelog", hmessagelog);
		
		return "/hMessageLog/hMessageLogUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHMessageLogList", method = RequestMethod.GET)
	public String getHMessageLogList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HMessageLog hmessagelog = new HMessageLog();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hmessagelog.setId(id);
			
			String phone = RequestHandler.getString(request, "phone");
			hmessagelog.setPhone(phone);
			
			String content = RequestHandler.getString(request, "content");
			hmessagelog.setContent(content);
			
			String ip = RequestHandler.getString(request, "ip");
			hmessagelog.setIp(ip);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hmessagelog.setCreateTime(createTime);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hmessagelog.setRowStart(from);
			hmessagelog.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hmessagelog.setSortColumn(sortColumn);
			
			int hmessagelogListCount = hmessagelogService.getHMessageLogListCount(hmessagelog);
			ResponseList<HMessageLog> hmessagelogList = null;
			if ( hmessagelogListCount > 0 )
			{
				hmessagelogList = hmessagelogService.getHMessageLogList(hmessagelog);
			} else
			{
				hmessagelogList = new ResponseList<HMessageLog>();
			}
			// 设置数据总数
			hmessagelogList.setTotalResults(hmessagelogListCount);
			
			writeSuccessMsg("ok", hmessagelogList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHMessageLogBaseList", method = RequestMethod.GET)
	public String getHMessageLogBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HMessageLog hmessagelog = new HMessageLog();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hmessagelog.setId(id);
			
			String phone = RequestHandler.getString(request, "phone");
			hmessagelog.setPhone(phone);
			
			String content = RequestHandler.getString(request, "content");
			hmessagelog.setContent(content);
			
			String ip = RequestHandler.getString(request, "ip");
			hmessagelog.setIp(ip);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hmessagelog.setCreateTime(createTime);
			
			List<HMessageLog> hmessagelogList = hmessagelogService.getHMessageLogBaseList(hmessagelog);
		
			writeSuccessMsg("ok", hmessagelogList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHMessageLog", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HMessageLog hmessagelog = new HMessageLog();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hmessagelog.setId(id);
			String phone = RequestHandler.getString(request, "phone");
			hmessagelog.setPhone(phone);
			String content = RequestHandler.getString(request, "content");
			hmessagelog.setContent(content);
			String ip = RequestHandler.getString(request, "ip");
			hmessagelog.setIp(ip);
			Date createTime = RequestHandler.getDate(request, "createTime");
			hmessagelog.setCreateTime(new Date());
				
			hmessagelogService.insertHMessageLog(hmessagelog);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHMessageLog", method = RequestMethod.POST)
	public String updateHMessageLog(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HMessageLog hmessagelog = new HMessageLog();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hmessagelog.setId(id);
			
			String phone = RequestHandler.getString(request, "phone");
			hmessagelog.setPhone(phone);
			
			String content = RequestHandler.getString(request, "content");
			hmessagelog.setContent(content);
			
			String ip = RequestHandler.getString(request, "ip");
			hmessagelog.setIp(ip);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hmessagelog.setCreateTime(createTime);
			

			hmessagelogService.updateHMessageLog(hmessagelog);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHMessageLog", method = RequestMethod.POST)
	public String removeHMessageLog(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HMessageLog hmessagelog = new HMessageLog();
			Integer id = RequestHandler.getInteger(request, "id");
			hmessagelog.setId(id);

			hmessagelogService.removeHMessageLog(hmessagelog);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHMessageLog", method = RequestMethod.POST)
	public String removeAllHMessageLog(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HMessageLog hMessageLog = new HMessageLog();
						hMessageLog.setId(Integer.valueOf(id));
						hmessagelogService.removeHMessageLog(hMessageLog);
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
	
	@RequestMapping(value = "/getMessageLogCount", method = RequestMethod.GET)
	public String getMessageLogCount(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HMessageLog hmessagelog = new HMessageLog();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hmessagelog.setId(id);
			
			String phone = RequestHandler.getString(request, "phone");
			hmessagelog.setPhone(phone);
			
			String content = RequestHandler.getString(request, "content");
			hmessagelog.setContent(content);
			
			String ip = RequestHandler.getString(request, "ip");
			hmessagelog.setIp(ip);
			
			hmessagelog.setEndTime(new Date());
			Calendar calendar = Calendar.getInstance();
	        Date date = new Date(System.currentTimeMillis());
	        calendar.setTime(date);
	        calendar.add(Calendar.HOUR_OF_DAY, -1);
			hmessagelog.setStartTime(calendar.getTime());
			
			int count = hmessagelogService.getMessageLogCount(hmessagelog);
			if(count>4){
				writeSuccessMsg("more", null, response);
			}else{
				writeSuccessMsg("ok", null, response);
			}
		
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
}
