package com.hxt.hFpOrder.controller;

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

import com.hxt.hFpOrder.model.HFpOrder;
import com.hxt.hFpOrder.service.HFpOrderService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2016年01月12日 22:02:33
 */
@Controller
@RequestMapping("/hFpOrder")
public class HFpOrderController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HFpOrderController.class); //Logger
	
	@Autowired
	private HFpOrderService hfporderService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hFpOrder/hFpOrderIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hFpOrder/hFpOrderAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HFpOrder hfporder1 = new HFpOrder();
		hfporder1.setId(id);
		HFpOrder hfporder = hfporderService.getHFpOrder(hfporder1);
		model.addAttribute("hfporder", hfporder);
		
		return "/hFpOrder/hFpOrderUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHFpOrderList", method = RequestMethod.GET)
	public String getHFpOrderList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HFpOrder hfporder = new HFpOrder();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hfporder.setId(id);
			
			Integer fpId = RequestHandler.getInteger(request, "fpId");
			hfporder.setFpId(fpId);
			
			Integer orderId = RequestHandler.getInteger(request, "orderId");
			hfporder.setOrderId(orderId);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hfporder.setCreateTime(createTime);
			
			Integer status = RequestHandler.getInteger(request, "status");
			hfporder.setStatus(status);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hfporder.setRowStart(from);
			hfporder.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hfporder.setSortColumn(sortColumn);
			
			int hfporderListCount = hfporderService.getHFpOrderListCount(hfporder);
			ResponseList<HFpOrder> hfporderList = null;
			if ( hfporderListCount > 0 )
			{
				hfporderList = hfporderService.getHFpOrderList(hfporder);
			} else
			{
				hfporderList = new ResponseList<HFpOrder>();
			}
			// 设置数据总数
			hfporderList.setTotalResults(hfporderListCount);
			
			writeSuccessMsg("ok", hfporderList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHFpOrderBaseList", method = RequestMethod.GET)
	public String getHFpOrderBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HFpOrder hfporder = new HFpOrder();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hfporder.setId(id);
			
			Integer fpId = RequestHandler.getInteger(request, "fpId");
			hfporder.setFpId(fpId);
			
			Integer orderId = RequestHandler.getInteger(request, "orderId");
			hfporder.setOrderId(orderId);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hfporder.setCreateTime(createTime);
			
			Integer status = RequestHandler.getInteger(request, "status");
			hfporder.setStatus(status);
			
			List<HFpOrder> hfporderList = hfporderService.getHFpOrderBaseList(hfporder);
		
			writeSuccessMsg("ok", hfporderList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHFpOrder", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HFpOrder hfporder = new HFpOrder();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hfporder.setId(id);
			Integer fpId = RequestHandler.getInteger(request, "fpId");
			hfporder.setFpId(fpId);
			Integer orderId = RequestHandler.getInteger(request, "orderId");
			hfporder.setOrderId(orderId);
			Date createTime = RequestHandler.getDate(request, "createTime");
			hfporder.setCreateTime(new Date());
			Integer status = RequestHandler.getInteger(request, "status");
			hfporder.setStatus(status);
				
			hfporderService.insertHFpOrder(hfporder);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHFpOrder", method = RequestMethod.POST)
	public String updateHFpOrder(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HFpOrder hfporder = new HFpOrder();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hfporder.setId(id);
			
			Integer fpId = RequestHandler.getInteger(request, "fpId");
			hfporder.setFpId(fpId);
			
			Integer orderId = RequestHandler.getInteger(request, "orderId");
			hfporder.setOrderId(orderId);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hfporder.setCreateTime(createTime);
			
			Integer status = RequestHandler.getInteger(request, "status");
			hfporder.setStatus(status);
			

			hfporderService.updateHFpOrder(hfporder);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHFpOrder", method = RequestMethod.POST)
	public String removeHFpOrder(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HFpOrder hfporder = new HFpOrder();
			Integer id = RequestHandler.getInteger(request, "id");
			hfporder.setId(id);

			hfporderService.removeHFpOrder(hfporder);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHFpOrder", method = RequestMethod.POST)
	public String removeAllHFpOrder(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HFpOrder hFpOrder = new HFpOrder();
						hFpOrder.setId(Integer.valueOf(id));
						hfporderService.removeHFpOrder(hFpOrder);
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
