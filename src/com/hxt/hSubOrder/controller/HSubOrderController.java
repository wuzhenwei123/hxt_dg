package com.hxt.hSubOrder.controller;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
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

import com.hxt.hPayOrder.model.HPayOrder;
import com.hxt.hPayOrder.service.HPayOrderService;
import com.hxt.hSubCompany.model.HSubCompany;
import com.hxt.hSubCompany.service.HSubCompanyService;
import com.hxt.hSubOrder.model.HSubOrder;
import com.hxt.hSubOrder.service.HSubOrderService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2015年12月02日 00:01:04
 */
@Controller
@RequestMapping("/hSubOrder")
public class HSubOrderController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HSubOrderController.class); //Logger
	
	@Autowired
	private HSubOrderService hsuborderService = null;
	@Autowired
	private HSubCompanyService hsubcompanyService = null;
	@Autowired
	private HPayOrderService hpayorderService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hSubOrder/hSubOrderIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hSubOrder/hSubOrderAdd";
	}
	@RequestMapping(value = "/toUpdate/{o_sub_id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer o_sub_id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HSubOrder hsuborder1 = new HSubOrder();
		hsuborder1.setO_sub_id(o_sub_id);
		HSubOrder hsuborder = hsuborderService.getHSubOrder(hsuborder1);
		model.addAttribute("hsuborder", hsuborder);
		
		return "/hSubOrder/hSubOrderUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHSubOrderList", method = RequestMethod.GET)
	public String getHSubOrderList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HSubOrder hsuborder = new HSubOrder();
			
			Integer o_sub_id = RequestHandler.getInteger(request, "o_sub_id");
			hsuborder.setO_sub_id(o_sub_id);
			
			String o_id = RequestHandler.getString(request, "o_id");
			hsuborder.setO_id(o_id);
			
			Integer c_id = RequestHandler.getInteger(request, "c_id");
			hsuborder.setC_id(c_id);
			
			Integer sub_id = RequestHandler.getInteger(request, "sub_id");
			hsuborder.setSub_id(sub_id);
			
			Integer amount = RequestHandler.getInteger(request, "amount");
			hsuborder.setAmount(amount);
			
			String electric = RequestHandler.getString(request, "electric");
			hsuborder.setElectric(electric);
			
			String pay_status = RequestHandler.getString(request, "pay_status");
			hsuborder.setPay_status(pay_status);
			
			Date pay_time = RequestHandler.getDate(request, "pay_time");
			hsuborder.setPay_time(pay_time);
			
			String tick_off_status = RequestHandler.getString(request, "tick_off_status");
			hsuborder.setTick_off_status(tick_off_status);
			
			Date tick_off_time = RequestHandler.getDate(request, "tick_off_time");
			hsuborder.setTick_off_time(tick_off_time);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hsuborder.setCreate_time(create_time);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hsuborder.setRowStart(from);
			hsuborder.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hsuborder.setSortColumn(sortColumn);
			
			int hsuborderListCount = hsuborderService.getHSubOrderListCount(hsuborder);
			ResponseList<HSubOrder> hsuborderList = null;
			if ( hsuborderListCount > 0 )
			{
				hsuborderList = hsuborderService.getHSubOrderList(hsuborder);
			} else
			{
				hsuborderList = new ResponseList<HSubOrder>();
			}
			// 设置数据总数
			hsuborderList.setTotalResults(hsuborderListCount);
			
			writeSuccessMsg("ok", hsuborderList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHSubOrderBaseList", method = RequestMethod.GET)
	public String getHSubOrderBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HSubOrder hsuborder = new HSubOrder();
			
			Integer o_sub_id = RequestHandler.getInteger(request, "o_sub_id");
			hsuborder.setO_sub_id(o_sub_id);
			
			String o_id = RequestHandler.getString(request, "o_id");
			hsuborder.setO_id(o_id);
			
			Integer c_id = RequestHandler.getInteger(request, "c_id");
			hsuborder.setC_id(c_id);
			
			Integer sub_id = RequestHandler.getInteger(request, "sub_id");
			hsuborder.setSub_id(sub_id);
			
			Integer amount = RequestHandler.getInteger(request, "amount");
			hsuborder.setAmount(amount);
			
			String electric = RequestHandler.getString(request, "electric");
			hsuborder.setElectric(electric);
			
			String pay_status = RequestHandler.getString(request, "pay_status");
			hsuborder.setPay_status(pay_status);
			
			Date pay_time = RequestHandler.getDate(request, "pay_time");
			hsuborder.setPay_time(pay_time);
			
			String tick_off_status = RequestHandler.getString(request, "tick_off_status");
			hsuborder.setTick_off_status(tick_off_status);
			
			Date tick_off_time = RequestHandler.getDate(request, "tick_off_time");
			hsuborder.setTick_off_time(tick_off_time);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hsuborder.setCreate_time(create_time);
			
			List<HSubOrder> hsuborderList = hsuborderService.getHSubOrderBaseList(hsuborder);
		
			writeSuccessMsg("ok", hsuborderList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHSubOrder", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HSubOrder hsuborder = new HSubOrder();
			
			Integer o_sub_id = RequestHandler.getInteger(request, "o_sub_id");
			hsuborder.setO_sub_id(o_sub_id);
			String o_id = RequestHandler.getString(request, "o_id");
			hsuborder.setO_id(o_id);
			Integer c_id = RequestHandler.getInteger(request, "c_id");
			hsuborder.setC_id(c_id);
			Integer sub_id = RequestHandler.getInteger(request, "sub_id");
			hsuborder.setSub_id(sub_id);
			Integer amount = RequestHandler.getInteger(request, "amount");
			hsuborder.setAmount(amount);
			String electric = RequestHandler.getString(request, "electric");
			hsuborder.setElectric(electric);
			String pay_status = RequestHandler.getString(request, "pay_status");
			hsuborder.setPay_status(pay_status);
			Date pay_time = RequestHandler.getDate(request, "pay_time");
			hsuborder.setPay_time(pay_time);
			String tick_off_status = RequestHandler.getString(request, "tick_off_status");
			hsuborder.setTick_off_status(tick_off_status);
			Date tick_off_time = RequestHandler.getDate(request, "tick_off_time");
			hsuborder.setTick_off_time(tick_off_time);
			Date create_time = RequestHandler.getDate(request, "create_time");
			hsuborder.setCreate_time(create_time);
				
			hsuborderService.insertHSubOrder(hsuborder);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHSubOrder", method = RequestMethod.POST)
	public String updateHSubOrder(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HSubOrder hsuborder = new HSubOrder();
			
			Integer o_sub_id = RequestHandler.getInteger(request, "o_sub_id");
			hsuborder.setO_sub_id(o_sub_id);
			
			String o_id = RequestHandler.getString(request, "o_id");
			hsuborder.setO_id(o_id);
			
			Integer c_id = RequestHandler.getInteger(request, "c_id");
			hsuborder.setC_id(c_id);
			
			Integer sub_id = RequestHandler.getInteger(request, "sub_id");
			hsuborder.setSub_id(sub_id);
			
			Integer amount = RequestHandler.getInteger(request, "amount");
			hsuborder.setAmount(amount);
			
			String electric = RequestHandler.getString(request, "electric");
			hsuborder.setElectric(electric);
			
			String pay_status = RequestHandler.getString(request, "pay_status");
			hsuborder.setPay_status(pay_status);
			
			Date pay_time = RequestHandler.getDate(request, "pay_time");
			hsuborder.setPay_time(pay_time);
			
			String tick_off_status = RequestHandler.getString(request, "tick_off_status");
			hsuborder.setTick_off_status(tick_off_status);
			
			Date tick_off_time = RequestHandler.getDate(request, "tick_off_time");
			hsuborder.setTick_off_time(tick_off_time);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hsuborder.setCreate_time(create_time);
			

			hsuborderService.updateHSubOrder(hsuborder);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHSubOrder", method = RequestMethod.POST)
	public String removeHSubOrder(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HSubOrder hsuborder = new HSubOrder();
			Integer o_sub_id = RequestHandler.getInteger(request, "o_sub_id");
			hsuborder.setO_sub_id(o_sub_id);

			hsuborderService.removeHSubOrder(hsuborder);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHSubOrder", method = RequestMethod.POST)
	public String removeAllHSubOrder(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String o_sub_ids = RequestHandler.getString(request, "o_sub_ids");
			if(o_sub_ids != null){
				String[] o_sub_idStr = o_sub_ids.split(",");
				if(o_sub_idStr != null && o_sub_idStr.length != 0){
					for (String o_sub_id : o_sub_idStr) {
						HSubOrder hSubOrder = new HSubOrder();
						hSubOrder.setO_sub_id(Integer.valueOf(o_sub_id));
						hsuborderService.removeHSubOrder(hSubOrder);
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
	
	/**
	 * 数据处理
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/taskSubOrder", method = RequestMethod.GET)
	public String taskSubOrder(HttpServletRequest request, HttpServletResponse response, Model model){
		try{
			HSubOrder hsuborder1 = new HSubOrder();
			List<HSubOrder> list = hsuborderService.getHSubOrderBaseList(hsuborder1);
			if(list!=null&&list.size()>0){
				for(HSubOrder hSubOrder:list){
					//查询分组信息
					HSubCompany subcom = new HSubCompany();
					subcom.setS_id(hSubOrder.getSub_id());
					subcom = hsubcompanyService.getHSubCompany(subcom);
					if(subcom!=null){
						  hSubOrder.setSub_name(subcom.getSub_name());
	                  	  hSubOrder.setConsignee(subcom.getConsignee());
	                  	  String adress = "";
	                  	  if(StringUtils.isNotBlank(subcom.getProvince_name())){
	                  		  adress = adress + subcom.getProvince_name();
	                  	  }
	                  	  
	                  	  if(StringUtils.isNotBlank(subcom.getCity_name())){
	                  		  adress = adress + subcom.getCity_name();
	                  	  }
	                  	  
	                  	  if(StringUtils.isNotBlank(subcom.getArea_name())){
	                  		  adress = adress + subcom.getArea_name();
	                  	  }
	                  	  
	                  	  if(StringUtils.isNotBlank(subcom.getConsignee_address())){
	                  		  adress = adress + subcom.getConsignee_address();
	                  	  }
	                  	  hSubOrder.setConsignee_address(adress);
	                  	  hSubOrder.setConsignee_phone(subcom.getConsignee_phone());
	                  	hsuborderService.updateHSubOrder(hSubOrder);
					}
					//查询订单信息
					HPayOrder hPayOrder = new HPayOrder();
					hPayOrder.setO_no(hSubOrder.getO_id());
					hPayOrder = hpayorderService.getHPayOrder(hPayOrder);
					if(hPayOrder!=null){
						if(StringUtils.isNotBlank(hPayOrder.getTick_off_status())&&hPayOrder.getTick_off_time()!=null){
							hSubOrder.setTick_off_status(hPayOrder.getTick_off_status());
							hSubOrder.setTick_off_time(hPayOrder.getTick_off_time());
							hsuborderService.updateHSubOrder(hSubOrder);
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
