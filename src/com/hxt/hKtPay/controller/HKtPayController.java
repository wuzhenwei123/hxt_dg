package com.hxt.hKtPay.controller;

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

import com.hxt.hKtPay.model.HKtPay;
import com.hxt.hKtPay.service.HKtPayService;
import com.hxt.hPay.model.HPay;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2016年11月28日 18:52:45
 */
@Controller
@RequestMapping("/hKtPay")
public class HKtPayController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HKtPayController.class); //Logger
	
	@Autowired
	private HKtPayService hktpayService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hKtPay/hKtPayIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hKtPay/hKtPayAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HKtPay hktpay1 = new HKtPay();
		hktpay1.setId(id);
		HKtPay hktpay = hktpayService.getHKtPay(hktpay1);
		model.addAttribute("hktpay", hktpay);
		
		return "/hKtPay/hKtPayUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHKtPayList", method = RequestMethod.GET)
	public String getHKtPayList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HKtPay hktpay = new HKtPay();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hktpay.setId(id);
			
			String content = RequestHandler.getString(request, "content");
			hktpay.setContent(content);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hktpay.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hktpay.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hktpay.setCreateId(createId);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hktpay.setRowStart(from);
			hktpay.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hktpay.setSortColumn(sortColumn);
			
			int hktpayListCount = hktpayService.getHKtPayListCount(hktpay);
			ResponseList<HKtPay> hktpayList = null;
			if ( hktpayListCount > 0 )
			{
				hktpayList = hktpayService.getHKtPayList(hktpay);
			} else
			{
				hktpayList = new ResponseList<HKtPay>();
			}
			// 设置数据总数
			hktpayList.setTotalResults(hktpayListCount);
			
			writeSuccessMsg("ok", hktpayList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHKtPayBaseList", method = RequestMethod.GET)
	public String getHKtPayBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HKtPay hktpay = new HKtPay();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hktpay.setId(id);
			
			String content = RequestHandler.getString(request, "content");
			hktpay.setContent(content);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hktpay.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hktpay.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hktpay.setCreateId(createId);
			
			List<HKtPay> hktpayList = hktpayService.getHKtPayBaseList(hktpay);
		
			writeSuccessMsg("ok", hktpayList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHKtPay", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HKtPay hktpay = new HKtPay();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hktpay.setId(id);
			String content = RequestHandler.getString(request, "content");
			hktpay.setContent(content);
			Integer state = RequestHandler.getInteger(request, "state");
			hktpay.setState(state);
			Date createTime = RequestHandler.getDate(request, "createTime");
			hktpay.setCreateTime(new Date());
			Integer createId = RequestHandler.getInteger(request, "createId");
			hktpay.setCreateId(createId);
				
			hktpayService.insertHKtPay(hktpay);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHKtPay", method = RequestMethod.POST)
	public String updateHKtPay(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HKtPay hktpay = new HKtPay();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hktpay.setId(id);
			
			String content = RequestHandler.getString(request, "content");
			hktpay.setContent(content);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hktpay.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hktpay.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hktpay.setCreateId(createId);
			

			hktpayService.updateHKtPay(hktpay);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHKtPay", method = RequestMethod.POST)
	public String removeHKtPay(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HKtPay hktpay = new HKtPay();
			Integer id = RequestHandler.getInteger(request, "id");
			hktpay.setId(id);

			hktpayService.removeHKtPay(hktpay);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHKtPay", method = RequestMethod.POST)
	public String removeAllHKtPay(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HKtPay hKtPay = new HKtPay();
						hKtPay.setId(Integer.valueOf(id));
						hktpayService.removeHKtPay(hKtPay);
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
	
	@RequestMapping(value = "/toShow/{id}", method = RequestMethod.GET)
	public String toShow(@PathVariable Integer id,HttpServletRequest request, HttpServletResponse response, Model model)
	{
		HKtPay hKtPay = new HKtPay();
		hKtPay.setId(id);
		HKtPay hKtPay1 = hktpayService.getHKtPay(hKtPay);
		model.addAttribute("hKtPay", hKtPay1);
		return "/hKtPay/show";
	}
}
