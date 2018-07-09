package com.hxt.hPay.controller;

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

import com.hxt.hPay.model.HPay;
import com.hxt.hPay.service.HPayService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SessionName;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2016年10月20日 09:13:54
 */
@Controller
@RequestMapping("/hPay")
public class HPayController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HPayController.class); //Logger
	
	@Autowired
	private HPayService hpayService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hPay/hPayIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hPay/hPayAdd";
	}
	@RequestMapping(value = "/toShow/{id}", method = RequestMethod.GET)
	public String toShow(@PathVariable Integer id,HttpServletRequest request, HttpServletResponse response, Model model)
	{
		HPay hpay1 = new HPay();
		hpay1.setId(id);
		HPay hpay = hpayService.getHPay(hpay1);
		model.addAttribute("hpay", hpay);
		return "/hPay/show";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HPay hpay1 = new HPay();
		hpay1.setId(id);
		HPay hpay = hpayService.getHPay(hpay1);
		model.addAttribute("hpay", hpay);
		
		return "/hPay/hPayUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHPayList", method = RequestMethod.GET)
	public String getHPayList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HPay hpay = new HPay();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hpay.setId(id);
			
			String content = RequestHandler.getString(request, "content");
			hpay.setContent(content);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hpay.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hpay.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hpay.setCreateId(createId);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hpay.setRowStart(from);
			hpay.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hpay.setSortColumn(sortColumn);
			
			int hpayListCount = hpayService.getHPayListCount(hpay);
			ResponseList<HPay> hpayList = null;
			if ( hpayListCount > 0 )
			{
				hpayList = hpayService.getHPayList(hpay);
			} else
			{
				hpayList = new ResponseList<HPay>();
			}
			// 设置数据总数
			hpayList.setTotalResults(hpayListCount);
			
			writeSuccessMsg("ok", hpayList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHPayBaseList", method = RequestMethod.GET)
	public String getHPayBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HPay hpay = new HPay();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hpay.setId(id);
			
			String content = RequestHandler.getString(request, "content");
			hpay.setContent(content);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hpay.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hpay.setCreateTime(createTime);
			
			Object adminUserId = getSession(request, SessionName.ADMIN_USER_ID);
			hpay.setCreateId(Integer.valueOf(adminUserId.toString()));
			
			List<HPay> hpayList = hpayService.getHPayBaseList(hpay);
		
			writeSuccessMsg("ok", hpayList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHPay", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HPay hpay = new HPay();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hpay.setId(id);
			String content = RequestHandler.getString(request, "content");
			hpay.setContent(content);
			Integer state = RequestHandler.getInteger(request, "state");
			hpay.setState(state);
			Date createTime = RequestHandler.getDate(request, "createTime");
			hpay.setCreateTime(new Date());
			Object adminUserId = getSession(request, SessionName.ADMIN_USER_ID);
			hpay.setCreateId(Integer.valueOf(adminUserId.toString()));
				
			hpayService.insertHPay(hpay);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHPay", method = RequestMethod.POST)
	public String updateHPay(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HPay hpay = new HPay();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hpay.setId(id);
			
			String content = RequestHandler.getString(request, "content");
			hpay.setContent(content);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hpay.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hpay.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hpay.setCreateId(createId);
			

			hpayService.updateHPay(hpay);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHPay", method = RequestMethod.POST)
	public String removeHPay(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HPay hpay = new HPay();
			Integer id = RequestHandler.getInteger(request, "id");
			hpay.setId(id);

			hpayService.removeHPay(hpay);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHPay", method = RequestMethod.POST)
	public String removeAllHPay(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HPay hPay = new HPay();
						hPay.setId(Integer.valueOf(id));
						hpayService.removeHPay(hPay);
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
