package com.hxt.hUserAccountDetail.controller;

import java.math.BigDecimal;
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

import com.hxt.hPayOrder.model.HPayOrder;
import com.hxt.hPayOrder.service.HPayOrderService;
import com.hxt.hUserAccountDetail.model.HUserAccountDetail;
import com.hxt.hUserAccountDetail.service.HUserAccountDetailService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	zlb
 * @time	2016年08月16日 10:58:42
 */
@Controller
@RequestMapping("/hUserAccountDetail")
public class HUserAccountDetailController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HUserAccountDetailController.class); //Logger
	
	@Autowired
	private HUserAccountDetailService huseraccountdetailService = null;
	@Autowired
	private HPayOrderService hpayorderService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hUserAccountDetail/hUserAccountDetailIndex";
	}
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try{
			Integer userAccountId = RequestHandler.getInteger(request, "userAccountId");
			if(userAccountId!=null){
				HUserAccountDetail hUserAccountDetail = new HUserAccountDetail();
				hUserAccountDetail.setUserAccountId(userAccountId);
				List<HUserAccountDetail> list = huseraccountdetailService.getHUserAccountDetailBaseList(hUserAccountDetail);
				if(list!=null&&list.size()>0){
					for(HUserAccountDetail ad:list){
						HPayOrder hPayOrder = new HPayOrder();
						hPayOrder.setO_id(ad.getOrderId());
						hPayOrder = hpayorderService.getHPayOrder(hPayOrder);
						if(hPayOrder!=null&&hPayOrder.getO_id()>0){
							String money = super.getMoney(hPayOrder.getAmount());
							BigDecimal b = new BigDecimal(Double.valueOf(money));
							ad.setOrderDetailMoney(b);
							ad.setAmmeterNum(hPayOrder.getElectric_number());
							huseraccountdetailService.updateHUserAccountDetail(hUserAccountDetail);
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hUserAccountDetail/hUserAccountDetailAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HUserAccountDetail huseraccountdetail1 = new HUserAccountDetail();
		huseraccountdetail1.setId(id);
		HUserAccountDetail huseraccountdetail = huseraccountdetailService.getHUserAccountDetail(huseraccountdetail1);
		model.addAttribute("huseraccountdetail", huseraccountdetail);
		
		return "/hUserAccountDetail/hUserAccountDetailUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHUserAccountDetailList", method = RequestMethod.GET)
	public String getHUserAccountDetailList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HUserAccountDetail huseraccountdetail = new HUserAccountDetail();
			
			Integer id = RequestHandler.getInteger(request, "id");
			huseraccountdetail.setId(id);
			
			Integer userAccountId = RequestHandler.getInteger(request, "userAccountId");
			huseraccountdetail.setUserAccountId(userAccountId);
			
			Integer type = RequestHandler.getInteger(request, "type");
			huseraccountdetail.setType(type);
			
			BigDecimal totalFee = RequestHandler.getBigDecimal(request, "totalFee");
			huseraccountdetail.setTotalFee(totalFee);
			
			BigDecimal realFee = RequestHandler.getBigDecimal(request, "realFee");
			huseraccountdetail.setRealFee(realFee);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			huseraccountdetail.setCreateTime(createTime);
			
			Integer operaterId = RequestHandler.getInteger(request, "operaterId");
			huseraccountdetail.setOperaterId(operaterId);
			
			Float rate = RequestHandler.getFloat(request, "rate");
			huseraccountdetail.setRate(rate);
			
			Integer orderId = RequestHandler.getInteger(request, "orderId");
			huseraccountdetail.setOrderId(orderId);
			
			String ammeterNum = RequestHandler.getString(request, "ammeterNum");
			huseraccountdetail.setAmmeterNum(ammeterNum);
			
			Integer orderDetailId = RequestHandler.getInteger(request, "orderDetailId");
			huseraccountdetail.setOrderDetailId(orderDetailId);
			
			BigDecimal orderDetailMoney = RequestHandler.getBigDecimal(request, "orderDetailMoney");
			huseraccountdetail.setOrderDetailMoney(orderDetailMoney);
			
			BigDecimal taxRate = RequestHandler.getBigDecimal(request, "taxRate");
			huseraccountdetail.setTaxRate(taxRate);
			
			BigDecimal taxMoney = RequestHandler.getBigDecimal(request, "taxMoney");
			huseraccountdetail.setTaxMoney(taxMoney);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			huseraccountdetail.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			huseraccountdetail.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			huseraccountdetail.setRemark3(remark3);
			
			String orderNo = RequestHandler.getString(request, "orderNo");
			huseraccountdetail.setO_id(orderNo);
			String ammeterNo = RequestHandler.getString(request, "ammeterNo");
			huseraccountdetail.setAmmeterNum(ammeterNo);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			huseraccountdetail.setRowStart(from);
			huseraccountdetail.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			huseraccountdetail.setSortColumn(sortColumn);
			
			int huseraccountdetailListCount = huseraccountdetailService.getHUserAccountDetailListCount(huseraccountdetail);
			ResponseList<HUserAccountDetail> huseraccountdetailList = null;
			if ( huseraccountdetailListCount > 0 )
			{
				huseraccountdetailList = huseraccountdetailService.getHUserAccountDetailList(huseraccountdetail);
			} else
			{
				huseraccountdetailList = new ResponseList<HUserAccountDetail>();
			}
			// 设置数据总数
			huseraccountdetailList.setTotalResults(huseraccountdetailListCount);
			
			writeSuccessMsg("ok", huseraccountdetailList, response);
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHUserAccountDetailBaseList", method = RequestMethod.GET)
	public String getHUserAccountDetailBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HUserAccountDetail huseraccountdetail = new HUserAccountDetail();
			
			Integer id = RequestHandler.getInteger(request, "id");
			huseraccountdetail.setId(id);
			
			Integer userAccountId = RequestHandler.getInteger(request, "userAccountId");
			huseraccountdetail.setUserAccountId(userAccountId);
			
			Integer type = RequestHandler.getInteger(request, "type");
			huseraccountdetail.setType(type);
			
			BigDecimal totalFee = RequestHandler.getBigDecimal(request, "totalFee");
			huseraccountdetail.setTotalFee(totalFee);
			
			BigDecimal realFee = RequestHandler.getBigDecimal(request, "realFee");
			huseraccountdetail.setRealFee(realFee);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			huseraccountdetail.setCreateTime(createTime);
			
			Integer operaterId = RequestHandler.getInteger(request, "operaterId");
			huseraccountdetail.setOperaterId(operaterId);
			
			Float rate = RequestHandler.getFloat(request, "rate");
			huseraccountdetail.setRate(rate);
			
			Integer orderId = RequestHandler.getInteger(request, "orderId");
			huseraccountdetail.setOrderId(orderId);
			
			String ammeterNum = RequestHandler.getString(request, "ammeterNum");
			huseraccountdetail.setAmmeterNum(ammeterNum);
			
			Integer orderDetailId = RequestHandler.getInteger(request, "orderDetailId");
			huseraccountdetail.setOrderDetailId(orderDetailId);
			
			BigDecimal orderDetailMoney = RequestHandler.getBigDecimal(request, "orderDetailMoney");
			huseraccountdetail.setOrderDetailMoney(orderDetailMoney);
			
			BigDecimal taxRate = RequestHandler.getBigDecimal(request, "taxRate");
			huseraccountdetail.setTaxRate(taxRate);
			
			BigDecimal taxMoney = RequestHandler.getBigDecimal(request, "taxMoney");
			huseraccountdetail.setTaxMoney(taxMoney);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			huseraccountdetail.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			huseraccountdetail.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			huseraccountdetail.setRemark3(remark3);
			
			List<HUserAccountDetail> huseraccountdetailList = huseraccountdetailService.getHUserAccountDetailBaseList(huseraccountdetail);
		
			writeSuccessMsg("ok", huseraccountdetailList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHUserAccountDetail", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HUserAccountDetail huseraccountdetail = new HUserAccountDetail();
			
			Integer id = RequestHandler.getInteger(request, "id");
			huseraccountdetail.setId(id);
			Integer userAccountId = RequestHandler.getInteger(request, "userAccountId");
			huseraccountdetail.setUserAccountId(userAccountId);
			Integer type = RequestHandler.getInteger(request, "type");
			huseraccountdetail.setType(type);
			BigDecimal totalFee = RequestHandler.getBigDecimal(request, "totalFee");
			huseraccountdetail.setTotalFee(totalFee);
			BigDecimal realFee = RequestHandler.getBigDecimal(request, "realFee");
			huseraccountdetail.setRealFee(realFee);
			Date createTime = RequestHandler.getDate(request, "createTime");
			huseraccountdetail.setCreateTime(new Date());
			Integer operaterId = RequestHandler.getInteger(request, "operaterId");
			huseraccountdetail.setOperaterId(operaterId);
			Float rate = RequestHandler.getFloat(request, "rate");
			huseraccountdetail.setRate(rate);
			Integer orderId = RequestHandler.getInteger(request, "orderId");
			huseraccountdetail.setOrderId(orderId);
			String ammeterNum = RequestHandler.getString(request, "ammeterNum");
			huseraccountdetail.setAmmeterNum(ammeterNum);
			Integer orderDetailId = RequestHandler.getInteger(request, "orderDetailId");
			huseraccountdetail.setOrderDetailId(orderDetailId);
			BigDecimal orderDetailMoney = RequestHandler.getBigDecimal(request, "orderDetailMoney");
			huseraccountdetail.setOrderDetailMoney(orderDetailMoney);
			BigDecimal taxRate = RequestHandler.getBigDecimal(request, "taxRate");
			huseraccountdetail.setTaxRate(taxRate);
			BigDecimal taxMoney = RequestHandler.getBigDecimal(request, "taxMoney");
			huseraccountdetail.setTaxMoney(taxMoney);
			String remark1 = RequestHandler.getString(request, "remark1");
			huseraccountdetail.setRemark1(remark1);
			String remark2 = RequestHandler.getString(request, "remark2");
			huseraccountdetail.setRemark2(remark2);
			String remark3 = RequestHandler.getString(request, "remark3");
			huseraccountdetail.setRemark3(remark3);
				
			huseraccountdetailService.insertHUserAccountDetail(huseraccountdetail);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHUserAccountDetail", method = RequestMethod.POST)
	public String updateHUserAccountDetail(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HUserAccountDetail huseraccountdetail = new HUserAccountDetail();
			
			Integer id = RequestHandler.getInteger(request, "id");
			huseraccountdetail.setId(id);
			
			Integer userAccountId = RequestHandler.getInteger(request, "userAccountId");
			huseraccountdetail.setUserAccountId(userAccountId);
			
			Integer type = RequestHandler.getInteger(request, "type");
			huseraccountdetail.setType(type);
			
			BigDecimal totalFee = RequestHandler.getBigDecimal(request, "totalFee");
			huseraccountdetail.setTotalFee(totalFee);
			
			BigDecimal realFee = RequestHandler.getBigDecimal(request, "realFee");
			huseraccountdetail.setRealFee(realFee);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			huseraccountdetail.setCreateTime(createTime);
			
			Integer operaterId = RequestHandler.getInteger(request, "operaterId");
			huseraccountdetail.setOperaterId(operaterId);
			
			Float rate = RequestHandler.getFloat(request, "rate");
			huseraccountdetail.setRate(rate);
			
			Integer orderId = RequestHandler.getInteger(request, "orderId");
			huseraccountdetail.setOrderId(orderId);
			
			String ammeterNum = RequestHandler.getString(request, "ammeterNum");
			huseraccountdetail.setAmmeterNum(ammeterNum);
			
			Integer orderDetailId = RequestHandler.getInteger(request, "orderDetailId");
			huseraccountdetail.setOrderDetailId(orderDetailId);
			
			BigDecimal orderDetailMoney = RequestHandler.getBigDecimal(request, "orderDetailMoney");
			huseraccountdetail.setOrderDetailMoney(orderDetailMoney);
			
			BigDecimal taxRate = RequestHandler.getBigDecimal(request, "taxRate");
			huseraccountdetail.setTaxRate(taxRate);
			
			BigDecimal taxMoney = RequestHandler.getBigDecimal(request, "taxMoney");
			huseraccountdetail.setTaxMoney(taxMoney);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			huseraccountdetail.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			huseraccountdetail.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			huseraccountdetail.setRemark3(remark3);
			

			huseraccountdetailService.updateHUserAccountDetail(huseraccountdetail);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHUserAccountDetail", method = RequestMethod.POST)
	public String removeHUserAccountDetail(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HUserAccountDetail huseraccountdetail = new HUserAccountDetail();
			Integer id = RequestHandler.getInteger(request, "id");
			huseraccountdetail.setId(id);

			huseraccountdetailService.removeHUserAccountDetail(huseraccountdetail);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHUserAccountDetail", method = RequestMethod.POST)
	public String removeAllHUserAccountDetail(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HUserAccountDetail hUserAccountDetail = new HUserAccountDetail();
						hUserAccountDetail.setId(Integer.valueOf(id));
						huseraccountdetailService.removeHUserAccountDetail(hUserAccountDetail);
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
