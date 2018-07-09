package com.hxt.hAmmeterQueryLog.controller;

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

import com.hxt.hAmmeterQueryLog.model.HAmmeterQueryLog;
import com.hxt.hAmmeterQueryLog.service.HAmmeterQueryLogService;
import com.hxt.hCommon.service.HCommonService;
import com.hxt.hCompany.model.HCompany;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2016年09月20日 12:15:05
 */
@Controller
@RequestMapping("/hAmmeterQueryLog")
public class HAmmeterQueryLogController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HAmmeterQueryLogController.class); //Logger
	
	@Autowired
	private HAmmeterQueryLogService hammeterquerylogService = null;
	@Autowired
	private HCommonService hCommonService = null;
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hAmmeterQueryLog/hAmmeterQueryLogIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hAmmeterQueryLog/hAmmeterQueryLogAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HAmmeterQueryLog hammeterquerylog1 = new HAmmeterQueryLog();
		hammeterquerylog1.setId(id);
		HAmmeterQueryLog hammeterquerylog = hammeterquerylogService.getHAmmeterQueryLog(hammeterquerylog1);
		model.addAttribute("hammeterquerylog", hammeterquerylog);
		
		return "/hAmmeterQueryLog/hAmmeterQueryLogUpdate";
	}

	/************* Public Methods *************/
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	public String exportExcel(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			HAmmeterQueryLog hammeterquerylog = new HAmmeterQueryLog();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hammeterquerylog.setId(id);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hammeterquerylog.setCreateTime(createTime);
			Date createTime1 = RequestHandler.getDate(request, "createTime1");
			Date createTime2 = RequestHandler.getDate(request, "createTime2");
			hammeterquerylog.setCreateTime1(createTime1);
			hammeterquerylog.setCreateTime2(createTime2);
			
			String ammeterNo = RequestHandler.getString(request, "ammeterNo");
			hammeterquerylog.setAmmeterNo(ammeterNo);
			
			String phone = RequestHandler.getString(request, "phone");
			hammeterquerylog.setPhone(phone);
			
			String ip = RequestHandler.getString(request, "ip");
			hammeterquerylog.setIp(ip);
			
			String ammeter_address = RequestHandler.getString(request, "ammeter_address");
			hammeterquerylog.setAmmeter_address(ammeter_address);
			
			String ammeter_name = RequestHandler.getString(request, "ammeter_name");
			hammeterquerylog.setAmmeter_name(ammeter_name);
			
			String fee = RequestHandler.getString(request, "fee");
			hammeterquerylog.setFee(fee);
			
			String znFee = RequestHandler.getString(request, "znFee");
			hammeterquerylog.setZnFee(znFee);
			
			String totalFee = RequestHandler.getString(request, "totalFee");
			hammeterquerylog.setTotalFee(totalFee);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hammeterquerylog.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hammeterquerylog.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hammeterquerylog.setRemark3(remark3);
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hammeterquerylog.setSortColumn(sortColumn);
			
			int hammeterquerylogListCount = hammeterquerylogService.getHAmmeterQueryLogListCount(hammeterquerylog);
			if ( hammeterquerylogListCount > 0 )
			{
				List<HAmmeterQueryLog> ammeterQueryLogs = hammeterquerylogService.getHAmmeterQueryLogBaseList(hammeterquerylog);
				LinkedList list = new LinkedList();
				list.addAll(ammeterQueryLogs);
				LinkedList fields = new LinkedList();
				fields.add("ammeterNo");
				fields.add("phone");
				fields.add("ip");
				fields.add("ammeter_address");
				fields.add("ammeter_name");
				fields.add("fee");
				fields.add("znFee");
				fields.add("totalFee");
				fields.add("createTime");
				
				LinkedList titles = new LinkedList();
				titles.add("缴费号");
				titles.add("手机");
				titles.add("IP");
				titles.add("客户地址");
				titles.add("客户名称");
				titles.add("欠费金额");
				titles.add("滞纳金");
				titles.add("欠费总金额");
				titles.add("查询时间");
				
				String path = "";
				path = hCommonService.excleExport(list, fields, titles, HAmmeterQueryLog.class, "查询电费记录",request);
				writeSuccessMsg("成功", path, response);
			} else
			{
				writeErrorMsg("无可导出内容", null, response);
			}
		} catch (Exception e) {
			writeErrorMsg("系统错误", null, response);
		}
		return null;
	}
	@RequestMapping(value = "/getHAmmeterQueryLogList", method = RequestMethod.GET)
	public String getHAmmeterQueryLogList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAmmeterQueryLog hammeterquerylog = new HAmmeterQueryLog();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hammeterquerylog.setId(id);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hammeterquerylog.setCreateTime(createTime);
			Date createTime1 = RequestHandler.getDate(request, "createTime1");
			Date createTime2 = RequestHandler.getDate(request, "createTime2");
			hammeterquerylog.setCreateTime1(createTime1);
			hammeterquerylog.setCreateTime2(createTime2);
			
			String ammeterNo = RequestHandler.getString(request, "ammeterNo");
			hammeterquerylog.setAmmeterNo(ammeterNo);
			
			String phone = RequestHandler.getString(request, "phone");
			hammeterquerylog.setPhone(phone);
			
			String ip = RequestHandler.getString(request, "ip");
			hammeterquerylog.setIp(ip);
			
			String ammeter_address = RequestHandler.getString(request, "ammeter_address");
			hammeterquerylog.setAmmeter_address(ammeter_address);
			
			String ammeter_name = RequestHandler.getString(request, "ammeter_name");
			hammeterquerylog.setAmmeter_name(ammeter_name);
			
			String fee = RequestHandler.getString(request, "fee");
			hammeterquerylog.setFee(fee);
			
			String znFee = RequestHandler.getString(request, "znFee");
			hammeterquerylog.setZnFee(znFee);
			
			String totalFee = RequestHandler.getString(request, "totalFee");
			hammeterquerylog.setTotalFee(totalFee);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hammeterquerylog.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hammeterquerylog.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hammeterquerylog.setRemark3(remark3);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hammeterquerylog.setRowStart(from);
			hammeterquerylog.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hammeterquerylog.setSortColumn(sortColumn);
			
			int hammeterquerylogListCount = hammeterquerylogService.getHAmmeterQueryLogListCount(hammeterquerylog);
			ResponseList<HAmmeterQueryLog> hammeterquerylogList = null;
			if ( hammeterquerylogListCount > 0 )
			{
				hammeterquerylogList = hammeterquerylogService.getHAmmeterQueryLogList(hammeterquerylog);
			} else
			{
				hammeterquerylogList = new ResponseList<HAmmeterQueryLog>();
			}
			// 设置数据总数
			hammeterquerylogList.setTotalResults(hammeterquerylogListCount);
			
			writeSuccessMsg("ok", hammeterquerylogList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHAmmeterQueryLogBaseList", method = RequestMethod.GET)
	public String getHAmmeterQueryLogBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAmmeterQueryLog hammeterquerylog = new HAmmeterQueryLog();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hammeterquerylog.setId(id);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hammeterquerylog.setCreateTime(createTime);
			
			String ammeterNo = RequestHandler.getString(request, "ammeterNo");
			hammeterquerylog.setAmmeterNo(ammeterNo);
			
			String phone = RequestHandler.getString(request, "phone");
			hammeterquerylog.setPhone(phone);
			
			String ip = RequestHandler.getString(request, "ip");
			hammeterquerylog.setIp(ip);
			
			String ammeter_address = RequestHandler.getString(request, "ammeter_address");
			hammeterquerylog.setAmmeter_address(ammeter_address);
			
			String ammeter_name = RequestHandler.getString(request, "ammeter_name");
			hammeterquerylog.setAmmeter_name(ammeter_name);
			
			String fee = RequestHandler.getString(request, "fee");
			hammeterquerylog.setFee(fee);
			
			String znFee = RequestHandler.getString(request, "znFee");
			hammeterquerylog.setZnFee(znFee);
			
			String totalFee = RequestHandler.getString(request, "totalFee");
			hammeterquerylog.setTotalFee(totalFee);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hammeterquerylog.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hammeterquerylog.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hammeterquerylog.setRemark3(remark3);
			
			List<HAmmeterQueryLog> hammeterquerylogList = hammeterquerylogService.getHAmmeterQueryLogBaseList(hammeterquerylog);
		
			writeSuccessMsg("ok", hammeterquerylogList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHAmmeterQueryLog", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HAmmeterQueryLog hammeterquerylog = new HAmmeterQueryLog();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hammeterquerylog.setId(id);
			Date createTime = RequestHandler.getDate(request, "createTime");
			hammeterquerylog.setCreateTime(new Date());
			String ammeterNo = RequestHandler.getString(request, "ammeterNo");
			hammeterquerylog.setAmmeterNo(ammeterNo);
			String phone = RequestHandler.getString(request, "phone");
			hammeterquerylog.setPhone(phone);
			String ip = RequestHandler.getString(request, "ip");
			hammeterquerylog.setIp(ip);
			String ammeter_address = RequestHandler.getString(request, "ammeter_address");
			hammeterquerylog.setAmmeter_address(ammeter_address);
			String ammeter_name = RequestHandler.getString(request, "ammeter_name");
			hammeterquerylog.setAmmeter_name(ammeter_name);
			String fee = RequestHandler.getString(request, "fee");
			hammeterquerylog.setFee(fee);
			
			String znFee = RequestHandler.getString(request, "znFee");
			hammeterquerylog.setZnFee(znFee);
			
			String totalFee = RequestHandler.getString(request, "totalFee");
			hammeterquerylog.setTotalFee(totalFee);
			String remark1 = RequestHandler.getString(request, "remark1");
			hammeterquerylog.setRemark1(remark1);
			String remark2 = RequestHandler.getString(request, "remark2");
			hammeterquerylog.setRemark2(remark2);
			String remark3 = RequestHandler.getString(request, "remark3");
			hammeterquerylog.setRemark3(remark3);
				
			hammeterquerylogService.insertHAmmeterQueryLog(hammeterquerylog);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHAmmeterQueryLog", method = RequestMethod.POST)
	public String updateHAmmeterQueryLog(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAmmeterQueryLog hammeterquerylog = new HAmmeterQueryLog();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hammeterquerylog.setId(id);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hammeterquerylog.setCreateTime(createTime);
			
			String ammeterNo = RequestHandler.getString(request, "ammeterNo");
			hammeterquerylog.setAmmeterNo(ammeterNo);
			
			String phone = RequestHandler.getString(request, "phone");
			hammeterquerylog.setPhone(phone);
			
			String ip = RequestHandler.getString(request, "ip");
			hammeterquerylog.setIp(ip);
			
			String ammeter_address = RequestHandler.getString(request, "ammeter_address");
			hammeterquerylog.setAmmeter_address(ammeter_address);
			
			String ammeter_name = RequestHandler.getString(request, "ammeter_name");
			hammeterquerylog.setAmmeter_name(ammeter_name);
			
			String fee = RequestHandler.getString(request, "fee");
			hammeterquerylog.setFee(fee);
			
			String znFee = RequestHandler.getString(request, "znFee");
			hammeterquerylog.setZnFee(znFee);
			
			String totalFee = RequestHandler.getString(request, "totalFee");
			hammeterquerylog.setTotalFee(totalFee);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hammeterquerylog.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hammeterquerylog.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hammeterquerylog.setRemark3(remark3);
			

			hammeterquerylogService.updateHAmmeterQueryLog(hammeterquerylog);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHAmmeterQueryLog", method = RequestMethod.POST)
	public String removeHAmmeterQueryLog(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAmmeterQueryLog hammeterquerylog = new HAmmeterQueryLog();
			Integer id = RequestHandler.getInteger(request, "id");
			hammeterquerylog.setId(id);

			hammeterquerylogService.removeHAmmeterQueryLog(hammeterquerylog);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHAmmeterQueryLog", method = RequestMethod.POST)
	public String removeAllHAmmeterQueryLog(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HAmmeterQueryLog hAmmeterQueryLog = new HAmmeterQueryLog();
						hAmmeterQueryLog.setId(Integer.valueOf(id));
						hammeterquerylogService.removeHAmmeterQueryLog(hAmmeterQueryLog);
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
