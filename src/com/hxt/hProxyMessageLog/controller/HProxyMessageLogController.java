package com.hxt.hProxyMessageLog.controller;

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

import com.hxt.hProxyMessageLog.model.HProxyMessageLog;
import com.hxt.hProxyMessageLog.service.HProxyMessageLogService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2016年11月03日 09:29:08
 */
@Controller
@RequestMapping("/hProxyMessageLog")
public class HProxyMessageLogController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HProxyMessageLogController.class); //Logger
	
	@Autowired
	private HProxyMessageLogService hproxymessagelogService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hProxyMessageLog/hProxyMessageLogIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hProxyMessageLog/hProxyMessageLogAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HProxyMessageLog hproxymessagelog1 = new HProxyMessageLog();
		hproxymessagelog1.setId(id);
		HProxyMessageLog hproxymessagelog = hproxymessagelogService.getHProxyMessageLog(hproxymessagelog1);
		model.addAttribute("hproxymessagelog", hproxymessagelog);
		
		return "/hProxyMessageLog/hProxyMessageLogUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHProxyMessageLogList", method = RequestMethod.GET)
	public String getHProxyMessageLogList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HProxyMessageLog hproxymessagelog = new HProxyMessageLog();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hproxymessagelog.setId(id);
			
			String proxyName = RequestHandler.getString(request, "proxyName");
			hproxymessagelog.setProxyName(proxyName);
			
			String proxyPhone = RequestHandler.getString(request, "proxyPhone");
			hproxymessagelog.setProxyPhone(proxyPhone);
			
			Date createYime = RequestHandler.getDate(request, "createYime");
			hproxymessagelog.setCreateYime(createYime);
			
			String contractNumber = RequestHandler.getString(request, "contractNumber");
			hproxymessagelog.setContractNumber(contractNumber);
			
			Date contractStartTime = RequestHandler.getDate(request, "contractStartTime");
			hproxymessagelog.setContractStartTime(contractStartTime);
			
			Date contractEndTime = RequestHandler.getDate(request, "contractEndTime");
			hproxymessagelog.setContractEndTime(contractEndTime);
			
			Integer remindStartDate = RequestHandler.getInteger(request, "remindStartDate");
			hproxymessagelog.setRemindStartDate(remindStartDate);
			
			Integer remindEndDate = RequestHandler.getInteger(request, "remindEndDate");
			hproxymessagelog.setRemindEndDate(remindEndDate);
			
			String bank_number = RequestHandler.getString(request, "bank_number");
			hproxymessagelog.setBank_number(bank_number);
			
			String info = RequestHandler.getString(request, "info");
			hproxymessagelog.setInfo(info);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hproxymessagelog.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hproxymessagelog.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hproxymessagelog.setRemark3(remark3);
			
			Integer cId = RequestHandler.getInteger(request, "cId");
			hproxymessagelog.setCId(cId);
			
			Integer userId = RequestHandler.getInteger(request, "userId");
			hproxymessagelog.setUserId(userId);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hproxymessagelog.setState(state);
			
			String userNumber = RequestHandler.getString(request, "userNumber");
			hproxymessagelog.setUserNumber(userNumber);
			
			String proxyAddress = RequestHandler.getString(request, "proxyAddress");
			hproxymessagelog.setProxyAddress(proxyAddress);
			
			String proxyCode = RequestHandler.getString(request, "proxyCode");
			hproxymessagelog.setProxyCode(proxyCode);
			
			String payBankNumber = RequestHandler.getString(request, "payBankNumber");
			hproxymessagelog.setPayBankNumber(payBankNumber);
			
			String payName = RequestHandler.getString(request, "payName");
			hproxymessagelog.setPayName(payName);
			
			Integer payCardStyle = RequestHandler.getInteger(request, "payCardStyle");
			hproxymessagelog.setPayCardStyle(payCardStyle);
			
			String payCard = RequestHandler.getString(request, "payCard");
			hproxymessagelog.setPayCard(payCard);
			
			String payPhoneNumber = RequestHandler.getString(request, "payPhoneNumber");
			hproxymessagelog.setPayPhoneNumber(payPhoneNumber);
			
			String payMail = RequestHandler.getString(request, "payMail");
			hproxymessagelog.setPayMail(payMail);
			
			Integer sex = RequestHandler.getInteger(request, "sex");
			hproxymessagelog.setSex(sex);
			
			Integer checkState = RequestHandler.getInteger(request, "checkState");
			hproxymessagelog.setCheckState(checkState);
			
			String hetongUrl = RequestHandler.getString(request, "hetongUrl");
			hproxymessagelog.setHetongUrl(hetongUrl);
			
			String chexiaoUrl = RequestHandler.getString(request, "chexiaoUrl");
			hproxymessagelog.setChexiaoUrl(chexiaoUrl);
			
			String msg = RequestHandler.getString(request, "msg");
			hproxymessagelog.setMsg(msg);
			
			String bank_name = RequestHandler.getString(request, "bank_name");
			hproxymessagelog.setBank_name(bank_name);
			
			String payAccountName = RequestHandler.getString(request, "payAccountName");
			hproxymessagelog.setPayAccountName(payAccountName);
			
			String biangengUrl = RequestHandler.getString(request, "biangengUrl");
			hproxymessagelog.setBiangengUrl(biangengUrl);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hproxymessagelog.setRowStart(from);
			hproxymessagelog.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hproxymessagelog.setSortColumn(sortColumn);
			
			int hproxymessagelogListCount = hproxymessagelogService.getHProxyMessageLogListCount(hproxymessagelog);
			ResponseList<HProxyMessageLog> hproxymessagelogList = null;
			if ( hproxymessagelogListCount > 0 )
			{
				hproxymessagelogList = hproxymessagelogService.getHProxyMessageLogList(hproxymessagelog);
			} else
			{
				hproxymessagelogList = new ResponseList<HProxyMessageLog>();
			}
			// 设置数据总数
			hproxymessagelogList.setTotalResults(hproxymessagelogListCount);
			
			writeSuccessMsg("ok", hproxymessagelogList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHProxyMessageLogBaseList", method = RequestMethod.GET)
	public String getHProxyMessageLogBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HProxyMessageLog hproxymessagelog = new HProxyMessageLog();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hproxymessagelog.setId(id);
			
			String proxyName = RequestHandler.getString(request, "proxyName");
			hproxymessagelog.setProxyName(proxyName);
			
			String proxyPhone = RequestHandler.getString(request, "proxyPhone");
			hproxymessagelog.setProxyPhone(proxyPhone);
			
			Date createYime = RequestHandler.getDate(request, "createYime");
			hproxymessagelog.setCreateYime(createYime);
			
			String contractNumber = RequestHandler.getString(request, "contractNumber");
			hproxymessagelog.setContractNumber(contractNumber);
			
			Date contractStartTime = RequestHandler.getDate(request, "contractStartTime");
			hproxymessagelog.setContractStartTime(contractStartTime);
			
			Date contractEndTime = RequestHandler.getDate(request, "contractEndTime");
			hproxymessagelog.setContractEndTime(contractEndTime);
			
			Integer remindStartDate = RequestHandler.getInteger(request, "remindStartDate");
			hproxymessagelog.setRemindStartDate(remindStartDate);
			
			Integer remindEndDate = RequestHandler.getInteger(request, "remindEndDate");
			hproxymessagelog.setRemindEndDate(remindEndDate);
			
			String bank_number = RequestHandler.getString(request, "bank_number");
			hproxymessagelog.setBank_number(bank_number);
			
			String info = RequestHandler.getString(request, "info");
			hproxymessagelog.setInfo(info);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hproxymessagelog.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hproxymessagelog.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hproxymessagelog.setRemark3(remark3);
			
			Integer cId = RequestHandler.getInteger(request, "cId");
			hproxymessagelog.setCId(cId);
			
			Integer userId = RequestHandler.getInteger(request, "userId");
			hproxymessagelog.setUserId(userId);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hproxymessagelog.setState(state);
			
			String userNumber = RequestHandler.getString(request, "userNumber");
			hproxymessagelog.setUserNumber(userNumber);
			
			String proxyAddress = RequestHandler.getString(request, "proxyAddress");
			hproxymessagelog.setProxyAddress(proxyAddress);
			
			String proxyCode = RequestHandler.getString(request, "proxyCode");
			hproxymessagelog.setProxyCode(proxyCode);
			
			String payBankNumber = RequestHandler.getString(request, "payBankNumber");
			hproxymessagelog.setPayBankNumber(payBankNumber);
			
			String payName = RequestHandler.getString(request, "payName");
			hproxymessagelog.setPayName(payName);
			
			Integer payCardStyle = RequestHandler.getInteger(request, "payCardStyle");
			hproxymessagelog.setPayCardStyle(payCardStyle);
			
			String payCard = RequestHandler.getString(request, "payCard");
			hproxymessagelog.setPayCard(payCard);
			
			String payPhoneNumber = RequestHandler.getString(request, "payPhoneNumber");
			hproxymessagelog.setPayPhoneNumber(payPhoneNumber);
			
			String payMail = RequestHandler.getString(request, "payMail");
			hproxymessagelog.setPayMail(payMail);
			
			Integer sex = RequestHandler.getInteger(request, "sex");
			hproxymessagelog.setSex(sex);
			
			Integer checkState = RequestHandler.getInteger(request, "checkState");
			hproxymessagelog.setCheckState(checkState);
			
			String hetongUrl = RequestHandler.getString(request, "hetongUrl");
			hproxymessagelog.setHetongUrl(hetongUrl);
			
			String chexiaoUrl = RequestHandler.getString(request, "chexiaoUrl");
			hproxymessagelog.setChexiaoUrl(chexiaoUrl);
			
			String msg = RequestHandler.getString(request, "msg");
			hproxymessagelog.setMsg(msg);
			
			String bank_name = RequestHandler.getString(request, "bank_name");
			hproxymessagelog.setBank_name(bank_name);
			
			String payAccountName = RequestHandler.getString(request, "payAccountName");
			hproxymessagelog.setPayAccountName(payAccountName);
			
			String biangengUrl = RequestHandler.getString(request, "biangengUrl");
			hproxymessagelog.setBiangengUrl(biangengUrl);
			
			List<HProxyMessageLog> hproxymessagelogList = hproxymessagelogService.getHProxyMessageLogBaseList(hproxymessagelog);
		
			writeSuccessMsg("ok", hproxymessagelogList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHProxyMessageLog", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HProxyMessageLog hproxymessagelog = new HProxyMessageLog();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hproxymessagelog.setId(id);
			String proxyName = RequestHandler.getString(request, "proxyName");
			hproxymessagelog.setProxyName(proxyName);
			String proxyPhone = RequestHandler.getString(request, "proxyPhone");
			hproxymessagelog.setProxyPhone(proxyPhone);
			Date createYime = RequestHandler.getDate(request, "createYime");
			hproxymessagelog.setCreateYime(createYime);
			String contractNumber = RequestHandler.getString(request, "contractNumber");
			hproxymessagelog.setContractNumber(contractNumber);
			Date contractStartTime = RequestHandler.getDate(request, "contractStartTime");
			hproxymessagelog.setContractStartTime(contractStartTime);
			Date contractEndTime = RequestHandler.getDate(request, "contractEndTime");
			hproxymessagelog.setContractEndTime(contractEndTime);
			Integer remindStartDate = RequestHandler.getInteger(request, "remindStartDate");
			hproxymessagelog.setRemindStartDate(remindStartDate);
			Integer remindEndDate = RequestHandler.getInteger(request, "remindEndDate");
			hproxymessagelog.setRemindEndDate(remindEndDate);
			String bank_number = RequestHandler.getString(request, "bank_number");
			hproxymessagelog.setBank_number(bank_number);
			String info = RequestHandler.getString(request, "info");
			hproxymessagelog.setInfo(info);
			String remark1 = RequestHandler.getString(request, "remark1");
			hproxymessagelog.setRemark1(remark1);
			String remark2 = RequestHandler.getString(request, "remark2");
			hproxymessagelog.setRemark2(remark2);
			String remark3 = RequestHandler.getString(request, "remark3");
			hproxymessagelog.setRemark3(remark3);
			Integer cId = RequestHandler.getInteger(request, "cId");
			hproxymessagelog.setCId(cId);
			Integer userId = RequestHandler.getInteger(request, "userId");
			hproxymessagelog.setUserId(userId);
			Integer state = RequestHandler.getInteger(request, "state");
			hproxymessagelog.setState(state);
			String userNumber = RequestHandler.getString(request, "userNumber");
			hproxymessagelog.setUserNumber(userNumber);
			String proxyAddress = RequestHandler.getString(request, "proxyAddress");
			hproxymessagelog.setProxyAddress(proxyAddress);
			String proxyCode = RequestHandler.getString(request, "proxyCode");
			hproxymessagelog.setProxyCode(proxyCode);
			String payBankNumber = RequestHandler.getString(request, "payBankNumber");
			hproxymessagelog.setPayBankNumber(payBankNumber);
			String payName = RequestHandler.getString(request, "payName");
			hproxymessagelog.setPayName(payName);
			Integer payCardStyle = RequestHandler.getInteger(request, "payCardStyle");
			hproxymessagelog.setPayCardStyle(payCardStyle);
			String payCard = RequestHandler.getString(request, "payCard");
			hproxymessagelog.setPayCard(payCard);
			String payPhoneNumber = RequestHandler.getString(request, "payPhoneNumber");
			hproxymessagelog.setPayPhoneNumber(payPhoneNumber);
			String payMail = RequestHandler.getString(request, "payMail");
			hproxymessagelog.setPayMail(payMail);
			Integer sex = RequestHandler.getInteger(request, "sex");
			hproxymessagelog.setSex(sex);
			Integer checkState = RequestHandler.getInteger(request, "checkState");
			hproxymessagelog.setCheckState(checkState);
			String hetongUrl = RequestHandler.getString(request, "hetongUrl");
			hproxymessagelog.setHetongUrl(hetongUrl);
			String chexiaoUrl = RequestHandler.getString(request, "chexiaoUrl");
			hproxymessagelog.setChexiaoUrl(chexiaoUrl);
			String msg = RequestHandler.getString(request, "msg");
			hproxymessagelog.setMsg(msg);
			String bank_name = RequestHandler.getString(request, "bank_name");
			hproxymessagelog.setBank_name(bank_name);
			String payAccountName = RequestHandler.getString(request, "payAccountName");
			hproxymessagelog.setPayAccountName(payAccountName);
			String biangengUrl = RequestHandler.getString(request, "biangengUrl");
			hproxymessagelog.setBiangengUrl(biangengUrl);
				
			hproxymessagelogService.insertHProxyMessageLog(hproxymessagelog);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHProxyMessageLog", method = RequestMethod.POST)
	public String updateHProxyMessageLog(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HProxyMessageLog hproxymessagelog = new HProxyMessageLog();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hproxymessagelog.setId(id);
			
			String proxyName = RequestHandler.getString(request, "proxyName");
			hproxymessagelog.setProxyName(proxyName);
			
			String proxyPhone = RequestHandler.getString(request, "proxyPhone");
			hproxymessagelog.setProxyPhone(proxyPhone);
			
			Date createYime = RequestHandler.getDate(request, "createYime");
			hproxymessagelog.setCreateYime(createYime);
			
			String contractNumber = RequestHandler.getString(request, "contractNumber");
			hproxymessagelog.setContractNumber(contractNumber);
			
			Date contractStartTime = RequestHandler.getDate(request, "contractStartTime");
			hproxymessagelog.setContractStartTime(contractStartTime);
			
			Date contractEndTime = RequestHandler.getDate(request, "contractEndTime");
			hproxymessagelog.setContractEndTime(contractEndTime);
			
			Integer remindStartDate = RequestHandler.getInteger(request, "remindStartDate");
			hproxymessagelog.setRemindStartDate(remindStartDate);
			
			Integer remindEndDate = RequestHandler.getInteger(request, "remindEndDate");
			hproxymessagelog.setRemindEndDate(remindEndDate);
			
			String bank_number = RequestHandler.getString(request, "bank_number");
			hproxymessagelog.setBank_number(bank_number);
			
			String info = RequestHandler.getString(request, "info");
			hproxymessagelog.setInfo(info);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hproxymessagelog.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hproxymessagelog.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hproxymessagelog.setRemark3(remark3);
			
			Integer cId = RequestHandler.getInteger(request, "cId");
			hproxymessagelog.setCId(cId);
			
			Integer userId = RequestHandler.getInteger(request, "userId");
			hproxymessagelog.setUserId(userId);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hproxymessagelog.setState(state);
			
			String userNumber = RequestHandler.getString(request, "userNumber");
			hproxymessagelog.setUserNumber(userNumber);
			
			String proxyAddress = RequestHandler.getString(request, "proxyAddress");
			hproxymessagelog.setProxyAddress(proxyAddress);
			
			String proxyCode = RequestHandler.getString(request, "proxyCode");
			hproxymessagelog.setProxyCode(proxyCode);
			
			String payBankNumber = RequestHandler.getString(request, "payBankNumber");
			hproxymessagelog.setPayBankNumber(payBankNumber);
			
			String payName = RequestHandler.getString(request, "payName");
			hproxymessagelog.setPayName(payName);
			
			Integer payCardStyle = RequestHandler.getInteger(request, "payCardStyle");
			hproxymessagelog.setPayCardStyle(payCardStyle);
			
			String payCard = RequestHandler.getString(request, "payCard");
			hproxymessagelog.setPayCard(payCard);
			
			String payPhoneNumber = RequestHandler.getString(request, "payPhoneNumber");
			hproxymessagelog.setPayPhoneNumber(payPhoneNumber);
			
			String payMail = RequestHandler.getString(request, "payMail");
			hproxymessagelog.setPayMail(payMail);
			
			Integer sex = RequestHandler.getInteger(request, "sex");
			hproxymessagelog.setSex(sex);
			
			Integer checkState = RequestHandler.getInteger(request, "checkState");
			hproxymessagelog.setCheckState(checkState);
			
			String hetongUrl = RequestHandler.getString(request, "hetongUrl");
			hproxymessagelog.setHetongUrl(hetongUrl);
			
			String chexiaoUrl = RequestHandler.getString(request, "chexiaoUrl");
			hproxymessagelog.setChexiaoUrl(chexiaoUrl);
			
			String msg = RequestHandler.getString(request, "msg");
			hproxymessagelog.setMsg(msg);
			
			String bank_name = RequestHandler.getString(request, "bank_name");
			hproxymessagelog.setBank_name(bank_name);
			
			String payAccountName = RequestHandler.getString(request, "payAccountName");
			hproxymessagelog.setPayAccountName(payAccountName);
			
			String biangengUrl = RequestHandler.getString(request, "biangengUrl");
			hproxymessagelog.setBiangengUrl(biangengUrl);
			

			hproxymessagelogService.updateHProxyMessageLog(hproxymessagelog);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHProxyMessageLog", method = RequestMethod.POST)
	public String removeHProxyMessageLog(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HProxyMessageLog hproxymessagelog = new HProxyMessageLog();
			Integer id = RequestHandler.getInteger(request, "id");
			hproxymessagelog.setId(id);

			hproxymessagelogService.removeHProxyMessageLog(hproxymessagelog);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHProxyMessageLog", method = RequestMethod.POST)
	public String removeAllHProxyMessageLog(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HProxyMessageLog hProxyMessageLog = new HProxyMessageLog();
						hProxyMessageLog.setId(Integer.valueOf(id));
						hproxymessagelogService.removeHProxyMessageLog(hProxyMessageLog);
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
