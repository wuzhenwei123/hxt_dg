package com.hxt.hRegGuliSend.controller;

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

import com.hxt.hRegGuliSend.model.HRegGuliSend;
import com.hxt.hRegGuliSend.service.HRegGuliSendService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2017年04月06日 18:01:33
 */
@Controller
@RequestMapping("/hRegGuliSend")
public class HRegGuliSendController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HRegGuliSendController.class); //Logger
	
	@Autowired
	private HRegGuliSendService hreggulisendService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hRegGuliSend/hRegGuliSendIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hRegGuliSend/hRegGuliSendAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HRegGuliSend hreggulisend1 = new HRegGuliSend();
		hreggulisend1.setId(id);
		HRegGuliSend hreggulisend = hreggulisendService.getHRegGuliSend(hreggulisend1);
		model.addAttribute("hreggulisend", hreggulisend);
		
		return "/hRegGuliSend/hRegGuliSendUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHRegGuliSendList", method = RequestMethod.GET)
	public String getHRegGuliSendList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HRegGuliSend hreggulisend = new HRegGuliSend();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hreggulisend.setId(id);
			
			Date sendTime = RequestHandler.getDate(request, "sendTime");
			hreggulisend.setSendTime(sendTime);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hreggulisend.setCreateTime(createTime);
			
			Integer companyId = RequestHandler.getInteger(request, "companyId");
			hreggulisend.setCompanyId(companyId);
			
			String companyName = RequestHandler.getString(request, "companyName");
			hreggulisend.setCompanyName(companyName);
			
			String ammeter = RequestHandler.getString(request, "ammeter");
			hreggulisend.setAmmeter(ammeter);
			
			Integer agentTwoId = RequestHandler.getInteger(request, "agentTwoId");
			hreggulisend.setAgentTwoId(agentTwoId);
			
			String agentTwoName = RequestHandler.getString(request, "agentTwoName");
			hreggulisend.setAgentTwoName(agentTwoName);
			
			Integer agentId = RequestHandler.getInteger(request, "agentId");
			hreggulisend.setAgentId(agentId);
			
			String agentName = RequestHandler.getString(request, "agentName");
			hreggulisend.setAgentName(agentName);
			
			Integer guliId = RequestHandler.getInteger(request, "guliId");
			hreggulisend.setGuliId(guliId);
			
			Double fee = RequestHandler.getDouble(request, "fee");
			hreggulisend.setFee(fee);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hreggulisend.setState(state);
			
			String info = RequestHandler.getString(request, "info");
			hreggulisend.setInfo(info);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hreggulisend.setRowStart(from);
			hreggulisend.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hreggulisend.setSortColumn(sortColumn);
			
			int hreggulisendListCount = hreggulisendService.getHRegGuliSendListCount(hreggulisend);
			ResponseList<HRegGuliSend> hreggulisendList = null;
			if ( hreggulisendListCount > 0 )
			{
				hreggulisendList = hreggulisendService.getHRegGuliSendList(hreggulisend);
			} else
			{
				hreggulisendList = new ResponseList<HRegGuliSend>();
			}
			// 设置数据总数
			hreggulisendList.setTotalResults(hreggulisendListCount);
			
			writeSuccessMsg("ok", hreggulisendList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHRegGuliSendBaseList", method = RequestMethod.GET)
	public String getHRegGuliSendBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HRegGuliSend hreggulisend = new HRegGuliSend();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hreggulisend.setId(id);
			
			Date sendTime = RequestHandler.getDate(request, "sendTime");
			hreggulisend.setSendTime(sendTime);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hreggulisend.setCreateTime(createTime);
			
			Integer companyId = RequestHandler.getInteger(request, "companyId");
			hreggulisend.setCompanyId(companyId);
			
			String companyName = RequestHandler.getString(request, "companyName");
			hreggulisend.setCompanyName(companyName);
			
			String ammeter = RequestHandler.getString(request, "ammeter");
			hreggulisend.setAmmeter(ammeter);
			
			Integer agentTwoId = RequestHandler.getInteger(request, "agentTwoId");
			hreggulisend.setAgentTwoId(agentTwoId);
			
			String agentTwoName = RequestHandler.getString(request, "agentTwoName");
			hreggulisend.setAgentTwoName(agentTwoName);
			
			Integer agentId = RequestHandler.getInteger(request, "agentId");
			hreggulisend.setAgentId(agentId);
			
			String agentName = RequestHandler.getString(request, "agentName");
			hreggulisend.setAgentName(agentName);
			
			Integer guliId = RequestHandler.getInteger(request, "guliId");
			hreggulisend.setGuliId(guliId);
			
			Double fee = RequestHandler.getDouble(request, "fee");
			hreggulisend.setFee(fee);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hreggulisend.setState(state);
			
			String info = RequestHandler.getString(request, "info");
			hreggulisend.setInfo(info);
			
			List<HRegGuliSend> hreggulisendList = hreggulisendService.getHRegGuliSendBaseList(hreggulisend);
		
			writeSuccessMsg("ok", hreggulisendList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHRegGuliSend", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HRegGuliSend hreggulisend = new HRegGuliSend();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hreggulisend.setId(id);
			Date sendTime = RequestHandler.getDate(request, "sendTime");
			hreggulisend.setSendTime(sendTime);
			Date createTime = RequestHandler.getDate(request, "createTime");
			hreggulisend.setCreateTime(new Date());
			Integer companyId = RequestHandler.getInteger(request, "companyId");
			hreggulisend.setCompanyId(companyId);
			String companyName = RequestHandler.getString(request, "companyName");
			hreggulisend.setCompanyName(companyName);
			String ammeter = RequestHandler.getString(request, "ammeter");
			hreggulisend.setAmmeter(ammeter);
			Integer agentTwoId = RequestHandler.getInteger(request, "agentTwoId");
			hreggulisend.setAgentTwoId(agentTwoId);
			String agentTwoName = RequestHandler.getString(request, "agentTwoName");
			hreggulisend.setAgentTwoName(agentTwoName);
			Integer agentId = RequestHandler.getInteger(request, "agentId");
			hreggulisend.setAgentId(agentId);
			String agentName = RequestHandler.getString(request, "agentName");
			hreggulisend.setAgentName(agentName);
			Integer guliId = RequestHandler.getInteger(request, "guliId");
			hreggulisend.setGuliId(guliId);
			Double fee = RequestHandler.getDouble(request, "fee");
			hreggulisend.setFee(fee);
			Integer state = RequestHandler.getInteger(request, "state");
			hreggulisend.setState(state);
			String info = RequestHandler.getString(request, "info");
			hreggulisend.setInfo(info);
				
			hreggulisendService.insertHRegGuliSend(hreggulisend);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHRegGuliSend", method = RequestMethod.POST)
	public String updateHRegGuliSend(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HRegGuliSend hreggulisend = new HRegGuliSend();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hreggulisend.setId(id);
			
			Date sendTime = RequestHandler.getDate(request, "sendTime");
			hreggulisend.setSendTime(sendTime);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hreggulisend.setCreateTime(createTime);
			
			Integer companyId = RequestHandler.getInteger(request, "companyId");
			hreggulisend.setCompanyId(companyId);
			
			String companyName = RequestHandler.getString(request, "companyName");
			hreggulisend.setCompanyName(companyName);
			
			String ammeter = RequestHandler.getString(request, "ammeter");
			hreggulisend.setAmmeter(ammeter);
			
			Integer agentTwoId = RequestHandler.getInteger(request, "agentTwoId");
			hreggulisend.setAgentTwoId(agentTwoId);
			
			String agentTwoName = RequestHandler.getString(request, "agentTwoName");
			hreggulisend.setAgentTwoName(agentTwoName);
			
			Integer agentId = RequestHandler.getInteger(request, "agentId");
			hreggulisend.setAgentId(agentId);
			
			String agentName = RequestHandler.getString(request, "agentName");
			hreggulisend.setAgentName(agentName);
			
			Integer guliId = RequestHandler.getInteger(request, "guliId");
			hreggulisend.setGuliId(guliId);
			
			Double fee = RequestHandler.getDouble(request, "fee");
			hreggulisend.setFee(fee);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hreggulisend.setState(state);
			
			String info = RequestHandler.getString(request, "info");
			hreggulisend.setInfo(info);
			

			hreggulisendService.updateHRegGuliSend(hreggulisend);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHRegGuliSend", method = RequestMethod.POST)
	public String removeHRegGuliSend(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HRegGuliSend hreggulisend = new HRegGuliSend();
			Integer id = RequestHandler.getInteger(request, "id");
			hreggulisend.setId(id);

			hreggulisendService.removeHRegGuliSend(hreggulisend);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHRegGuliSend", method = RequestMethod.POST)
	public String removeAllHRegGuliSend(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HRegGuliSend hRegGuliSend = new HRegGuliSend();
						hRegGuliSend.setId(Integer.valueOf(id));
						hreggulisendService.removeHRegGuliSend(hRegGuliSend);
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
