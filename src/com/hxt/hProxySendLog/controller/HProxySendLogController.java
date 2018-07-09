package com.hxt.hProxySendLog.controller;

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

import com.hxt.hProxySendLog.model.HProxySendLog;
import com.hxt.hProxySendLog.service.HProxySendLogService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2016年11月17日 07:55:46
 */
@Controller
@RequestMapping("/hProxySendLog")
public class HProxySendLogController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HProxySendLogController.class); //Logger
	
	@Autowired
	private HProxySendLogService hproxysendlogService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hProxySendLog/hProxySendLogIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hProxySendLog/hProxySendLogAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HProxySendLog hproxysendlog1 = new HProxySendLog();
		hproxysendlog1.setId(id);
		HProxySendLog hproxysendlog = hproxysendlogService.getHProxySendLog(hproxysendlog1);
		model.addAttribute("hproxysendlog", hproxysendlog);
		
		return "/hProxySendLog/hProxySendLogUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHProxySendLogList", method = RequestMethod.GET)
	public String getHProxySendLogList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HProxySendLog hproxysendlog = new HProxySendLog();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hproxysendlog.setId(id);
			
			Integer style = RequestHandler.getInteger(request, "style");
			hproxysendlog.setStyle(style);
			
			String contractNumber = RequestHandler.getString(request, "contractNumber");
			hproxysendlog.setContractNumber(contractNumber);
			
			String userNumber = RequestHandler.getString(request, "userNumber");
			hproxysendlog.setUserNumber(userNumber);
			
			String content = RequestHandler.getString(request, "content");
			hproxysendlog.setContent(content);
			
			String bank_number = RequestHandler.getString(request, "bank_number");
			hproxysendlog.setBank_number(bank_number);
			
			String payBankNumber = RequestHandler.getString(request, "payBankNumber");
			hproxysendlog.setPayBankNumber(payBankNumber);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hproxysendlog.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hproxysendlog.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hproxysendlog.setRemark3(remark3);
			
			Integer sendStyle = RequestHandler.getInteger(request, "sendStyle");
			hproxysendlog.setSendStyle(sendStyle);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hproxysendlog.setRowStart(from);
			hproxysendlog.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hproxysendlog.setSortColumn(sortColumn);
			
			int hproxysendlogListCount = hproxysendlogService.getHProxySendLogListCount(hproxysendlog);
			ResponseList<HProxySendLog> hproxysendlogList = null;
			if ( hproxysendlogListCount > 0 )
			{
				hproxysendlogList = hproxysendlogService.getHProxySendLogList(hproxysendlog);
			} else
			{
				hproxysendlogList = new ResponseList<HProxySendLog>();
			}
			// 设置数据总数
			hproxysendlogList.setTotalResults(hproxysendlogListCount);
			
			writeSuccessMsg("ok", hproxysendlogList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHProxySendLogBaseList", method = RequestMethod.GET)
	public String getHProxySendLogBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HProxySendLog hproxysendlog = new HProxySendLog();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hproxysendlog.setId(id);
			
			Integer style = RequestHandler.getInteger(request, "style");
			hproxysendlog.setStyle(style);
			
			String contractNumber = RequestHandler.getString(request, "contractNumber");
			hproxysendlog.setContractNumber(contractNumber);
			
			String userNumber = RequestHandler.getString(request, "userNumber");
			hproxysendlog.setUserNumber(userNumber);
			
			String content = RequestHandler.getString(request, "content");
			hproxysendlog.setContent(content);
			
			String bank_number = RequestHandler.getString(request, "bank_number");
			hproxysendlog.setBank_number(bank_number);
			
			String payBankNumber = RequestHandler.getString(request, "payBankNumber");
			hproxysendlog.setPayBankNumber(payBankNumber);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hproxysendlog.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hproxysendlog.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hproxysendlog.setRemark3(remark3);
			
			Integer sendStyle = RequestHandler.getInteger(request, "sendStyle");
			hproxysendlog.setSendStyle(sendStyle);
			
			List<HProxySendLog> hproxysendlogList = hproxysendlogService.getHProxySendLogBaseList(hproxysendlog);
		
			writeSuccessMsg("ok", hproxysendlogList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHProxySendLog", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HProxySendLog hproxysendlog = new HProxySendLog();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hproxysendlog.setId(id);
			Integer style = RequestHandler.getInteger(request, "style");
			hproxysendlog.setStyle(style);
			String contractNumber = RequestHandler.getString(request, "contractNumber");
			hproxysendlog.setContractNumber(contractNumber);
			String userNumber = RequestHandler.getString(request, "userNumber");
			hproxysendlog.setUserNumber(userNumber);
			String content = RequestHandler.getString(request, "content");
			hproxysendlog.setContent(content);
			String bank_number = RequestHandler.getString(request, "bank_number");
			hproxysendlog.setBank_number(bank_number);
			String payBankNumber = RequestHandler.getString(request, "payBankNumber");
			hproxysendlog.setPayBankNumber(payBankNumber);
			String remark1 = RequestHandler.getString(request, "remark1");
			hproxysendlog.setRemark1(remark1);
			String remark2 = RequestHandler.getString(request, "remark2");
			hproxysendlog.setRemark2(remark2);
			String remark3 = RequestHandler.getString(request, "remark3");
			hproxysendlog.setRemark3(remark3);
			Integer sendStyle = RequestHandler.getInteger(request, "sendStyle");
			hproxysendlog.setSendStyle(sendStyle);
				
			hproxysendlogService.insertHProxySendLog(hproxysendlog);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHProxySendLog", method = RequestMethod.POST)
	public String updateHProxySendLog(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HProxySendLog hproxysendlog = new HProxySendLog();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hproxysendlog.setId(id);
			
			Integer style = RequestHandler.getInteger(request, "style");
			hproxysendlog.setStyle(style);
			
			String contractNumber = RequestHandler.getString(request, "contractNumber");
			hproxysendlog.setContractNumber(contractNumber);
			
			String userNumber = RequestHandler.getString(request, "userNumber");
			hproxysendlog.setUserNumber(userNumber);
			
			String content = RequestHandler.getString(request, "content");
			hproxysendlog.setContent(content);
			
			String bank_number = RequestHandler.getString(request, "bank_number");
			hproxysendlog.setBank_number(bank_number);
			
			String payBankNumber = RequestHandler.getString(request, "payBankNumber");
			hproxysendlog.setPayBankNumber(payBankNumber);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hproxysendlog.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hproxysendlog.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hproxysendlog.setRemark3(remark3);
			
			Integer sendStyle = RequestHandler.getInteger(request, "sendStyle");
			hproxysendlog.setSendStyle(sendStyle);
			

			hproxysendlogService.updateHProxySendLog(hproxysendlog);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHProxySendLog", method = RequestMethod.POST)
	public String removeHProxySendLog(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HProxySendLog hproxysendlog = new HProxySendLog();
			Integer id = RequestHandler.getInteger(request, "id");
			hproxysendlog.setId(id);

			hproxysendlogService.removeHProxySendLog(hproxysendlog);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHProxySendLog", method = RequestMethod.POST)
	public String removeAllHProxySendLog(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HProxySendLog hProxySendLog = new HProxySendLog();
						hProxySendLog.setId(Integer.valueOf(id));
						hproxysendlogService.removeHProxySendLog(hProxySendLog);
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
