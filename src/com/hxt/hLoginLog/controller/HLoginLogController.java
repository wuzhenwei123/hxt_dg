package com.hxt.hLoginLog.controller;

import java.util.Date;
import java.util.LinkedList;

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

import com.hxt.hCommon.service.HCommonService;
import com.hxt.hLoginLog.model.HLoginLog;
import com.hxt.hLoginLog.service.HLoginLogService;
import com.hxt.hPayOrder.model.HPayOrder;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2016年09月19日 11:08:42
 */
@Controller
@RequestMapping("/hLoginLog")
public class HLoginLogController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HLoginLogController.class); //Logger
	
	@Autowired
	private HLoginLogService hloginlogService = null;
	@Autowired
	private HCommonService hCommonService;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hLoginLog/hLoginLogIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hLoginLog/hLoginLogAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HLoginLog hloginlog1 = new HLoginLog();
		hloginlog1.setId(id);
		HLoginLog hloginlog = hloginlogService.getHLoginLog(hloginlog1);
		model.addAttribute("hloginlog", hloginlog);
		
		return "/hLoginLog/hLoginLogUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHLoginLogList", method = RequestMethod.GET)
	public String getHLoginLogList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HLoginLog hloginlog = new HLoginLog();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hloginlog.setId(id);
			
			String adminName = RequestHandler.getString(request, "adminName");
			hloginlog.setAdminName(adminName);
			
			Date loginTIme = RequestHandler.getDate(request, "loginTIme");
			hloginlog.setLoginTIme(loginTIme);
			
			String loginIp = RequestHandler.getString(request, "loginIp");
			hloginlog.setLoginIp(loginIp);
			
			Integer deviceType = RequestHandler.getInteger(request, "deviceType");
			hloginlog.setDeviceType(deviceType);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hloginlog.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hloginlog.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hloginlog.setRemark3(remark3);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hloginlog.setRowStart(from);
			hloginlog.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hloginlog.setSortColumn(sortColumn);
			
			int hloginlogListCount = hloginlogService.getHLoginLogListCount(hloginlog);
			ResponseList<HLoginLog> hloginlogList = null;
			if ( hloginlogListCount > 0 )
			{
				hloginlogList = hloginlogService.getHLoginLogList(hloginlog);
			} else
			{
				hloginlogList = new ResponseList<HLoginLog>();
			}
			// 设置数据总数
			hloginlogList.setTotalResults(hloginlogListCount);
			
			writeSuccessMsg("ok", hloginlogList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHLoginLogBaseList", method = RequestMethod.GET)
	public String getHLoginLogBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HLoginLog hloginlog = new HLoginLog();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hloginlog.setId(id);
			
			String adminName = RequestHandler.getString(request, "adminName");
			hloginlog.setAdminName(adminName);
			
			Date loginTIme = RequestHandler.getDate(request, "loginTIme");
			hloginlog.setLoginTIme(loginTIme);
			
			String loginIp = RequestHandler.getString(request, "loginIp");
			hloginlog.setLoginIp(loginIp);
			
			Integer deviceType = RequestHandler.getInteger(request, "deviceType");
			hloginlog.setDeviceType(deviceType);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hloginlog.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hloginlog.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hloginlog.setRemark3(remark3);
			
			List<HLoginLog> hloginlogList = hloginlogService.getHLoginLogBaseList(hloginlog);
		
			writeSuccessMsg("ok", hloginlogList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	public String exportExcel(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HLoginLog hloginlog = new HLoginLog();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hloginlog.setId(id);
			
			String adminName = RequestHandler.getString(request, "adminName");
			hloginlog.setAdminName(adminName);
			
			Date loginTIme = RequestHandler.getDate(request, "loginTIme");
			hloginlog.setLoginTIme(loginTIme);
			
			String loginIp = RequestHandler.getString(request, "loginIp");
			hloginlog.setLoginIp(loginIp);
			
			Integer deviceType = RequestHandler.getInteger(request, "deviceType");
			hloginlog.setDeviceType(deviceType);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hloginlog.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hloginlog.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hloginlog.setRemark3(remark3);
			
			List<HLoginLog> hloginlogList = hloginlogService.getHLoginLogBaseList(hloginlog);
			if(hloginlogList!=null&&hloginlogList.size()>0){
				LinkedList list = new LinkedList();
				list.addAll(hloginlogList);
				LinkedList fields = new LinkedList();
				fields.add("adminName");
				fields.add("loginTIme");
				fields.add("loginIp");
				fields.add("remark1");
				LinkedList titles = new LinkedList();
				titles.add("登录账户");
				titles.add("登录时间");
				titles.add("登录ID");
				titles.add("登录设备类型");
				String path = hCommonService.excleExport(list, fields, titles, HLoginLog.class, "登录日志",request);
				writeSuccessMsg("成功", path, response);
			}else{
				writeErrorMsg("无可导出内容", null, response);
			}
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHLoginLog", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HLoginLog hloginlog = new HLoginLog();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hloginlog.setId(id);
			String adminName = RequestHandler.getString(request, "adminName");
			hloginlog.setAdminName(adminName);
			Date loginTIme = RequestHandler.getDate(request, "loginTIme");
			hloginlog.setLoginTIme(loginTIme);
			String loginIp = RequestHandler.getString(request, "loginIp");
			hloginlog.setLoginIp(loginIp);
			Integer deviceType = RequestHandler.getInteger(request, "deviceType");
			hloginlog.setDeviceType(deviceType);
			String remark1 = RequestHandler.getString(request, "remark1");
			if(deviceType!=null&&deviceType==1){
				hloginlog.setRemark1("微信");
			}else{
				hloginlog.setRemark1("PC");
			}
			String remark2 = RequestHandler.getString(request, "remark2");
			hloginlog.setRemark2(remark2);
			String remark3 = RequestHandler.getString(request, "remark3");
			hloginlog.setRemark3(remark3);
				
			hloginlogService.insertHLoginLog(hloginlog);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHLoginLog", method = RequestMethod.POST)
	public String updateHLoginLog(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HLoginLog hloginlog = new HLoginLog();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hloginlog.setId(id);
			
			String adminName = RequestHandler.getString(request, "adminName");
			hloginlog.setAdminName(adminName);
			
			Date loginTIme = RequestHandler.getDate(request, "loginTIme");
			hloginlog.setLoginTIme(loginTIme);
			
			String loginIp = RequestHandler.getString(request, "loginIp");
			hloginlog.setLoginIp(loginIp);
			
			Integer deviceType = RequestHandler.getInteger(request, "deviceType");
			hloginlog.setDeviceType(deviceType);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hloginlog.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hloginlog.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hloginlog.setRemark3(remark3);
			

			hloginlogService.updateHLoginLog(hloginlog);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHLoginLog", method = RequestMethod.POST)
	public String removeHLoginLog(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HLoginLog hloginlog = new HLoginLog();
			Integer id = RequestHandler.getInteger(request, "id");
			hloginlog.setId(id);

			hloginlogService.removeHLoginLog(hloginlog);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHLoginLog", method = RequestMethod.POST)
	public String removeAllHLoginLog(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HLoginLog hLoginLog = new HLoginLog();
						hLoginLog.setId(Integer.valueOf(id));
						hloginlogService.removeHLoginLog(hLoginLog);
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
