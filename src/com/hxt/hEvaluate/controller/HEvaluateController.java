package com.hxt.hEvaluate.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import com.hxt.hEvaluate.model.HEvaluate;
import com.hxt.hEvaluate.service.HEvaluateService;
import com.hxt.hPayOrder.model.HPayOrder;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	zhangyiyang
 * @time	2016年08月22日 10:55:38
 */
@Controller
@RequestMapping("/hEvaluate")
public class HEvaluateController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HEvaluateController.class); //Logger
	
	@Autowired
	private HEvaluateService hevaluateService = null;
	@Autowired
	private HCommonService hCommonService;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hEvaluate/hEvaluateIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hEvaluate/hEvaluateAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HEvaluate hevaluate1 = new HEvaluate();
		hevaluate1.setId(id);
		HEvaluate hevaluate = hevaluateService.getHEvaluate(hevaluate1);
		model.addAttribute("hevaluate", hevaluate);
		
		return "/hEvaluate/hEvaluateUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHEvaluateList", method = RequestMethod.GET)
	public String getHEvaluateList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HEvaluate hevaluate = new HEvaluate();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hevaluate.setId(id);
			
			String orderNo = RequestHandler.getString(request, "orderNo");
			hevaluate.setOrderNo(orderNo);
			
			Integer star = RequestHandler.getInteger(request, "star");
			hevaluate.setStar(star);
			
			String content = RequestHandler.getString(request, "content");
			hevaluate.setContent(content);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hevaluate.setCreateTime(createTime);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hevaluate.setRowStart(from);
			hevaluate.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hevaluate.setSortColumn(sortColumn);
			
			int hevaluateListCount = hevaluateService.getHEvaluateListCount(hevaluate);
			ResponseList<HEvaluate> hevaluateList = null;
			if ( hevaluateListCount > 0 )
			{
				hevaluateList = hevaluateService.getHEvaluateList(hevaluate);
				Iterator<Object> it = hevaluateList.iterator();
				while(it.hasNext()){
					HEvaluate obj = (HEvaluate)it.next();
					obj.setAmountStr(super.getMoney(obj.getAmount()));
				}
			} else
			{
				hevaluateList = new ResponseList<HEvaluate>();
			}
			// 设置数据总数
			hevaluateList.setTotalResults(hevaluateListCount);
			
			writeSuccessMsg("ok", hevaluateList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHEvaluateBaseList", method = RequestMethod.GET)
	public String getHEvaluateBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HEvaluate hevaluate = new HEvaluate();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hevaluate.setId(id);
			
			String orderNo = RequestHandler.getString(request, "orderNo");
			hevaluate.setOrderNo(orderNo);
			
			Integer star = RequestHandler.getInteger(request, "star");
			hevaluate.setStar(star);
			
			String content = RequestHandler.getString(request, "content");
			hevaluate.setContent(content);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hevaluate.setCreateTime(createTime);
			
			List<HEvaluate> hevaluateList = hevaluateService.getHEvaluateBaseList(hevaluate);
		
			writeSuccessMsg("ok", hevaluateList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHEvaluate", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HEvaluate hevaluate = new HEvaluate();
			
			String orderNo = RequestHandler.getString(request, "orderNo");
			hevaluate.setOrderNo(orderNo);
			int evaluateCount = hevaluateService.getHEvaluateListCount(hevaluate);
			if(evaluateCount>0){
				writeErrorMsg("一个订单只允许评价一次！", null, response);
			}else{
				Integer id = RequestHandler.getInteger(request, "id");
				hevaluate.setId(id);
				Integer star = RequestHandler.getInteger(request, "star");
				hevaluate.setStar(star);
				String content = RequestHandler.getString(request, "content");
				hevaluate.setContent(content);
				Date createTime = RequestHandler.getDate(request, "createTime");
				hevaluate.setCreateTime(new Date());
				
				hevaluateService.insertHEvaluate(hevaluate);
				
				writeSuccessMsg("添加成功", null, response);
			}
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHEvaluate", method = RequestMethod.POST)
	public String updateHEvaluate(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HEvaluate hevaluate = new HEvaluate();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hevaluate.setId(id);
			
			String orderNo = RequestHandler.getString(request, "orderNo");
			hevaluate.setOrderNo(orderNo);
			
			Integer star = RequestHandler.getInteger(request, "star");
			hevaluate.setStar(star);
			
			String content = RequestHandler.getString(request, "content");
			hevaluate.setContent(content);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hevaluate.setCreateTime(createTime);
			

			hevaluateService.updateHEvaluate(hevaluate);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHEvaluate", method = RequestMethod.POST)
	public String removeHEvaluate(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HEvaluate hevaluate = new HEvaluate();
			Integer id = RequestHandler.getInteger(request, "id");
			hevaluate.setId(id);

			hevaluateService.removeHEvaluate(hevaluate);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHEvaluate", method = RequestMethod.POST)
	public String removeAllHEvaluate(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HEvaluate hEvaluate = new HEvaluate();
						hEvaluate.setId(Integer.valueOf(id));
						hevaluateService.removeHEvaluate(hEvaluate);
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
	
	
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	public String exportExcel(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HEvaluate hevaluate = new HEvaluate();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hevaluate.setId(id);
			
			String orderNo = RequestHandler.getString(request, "orderNo");
			hevaluate.setOrderNo(orderNo);
			
			Integer star = RequestHandler.getInteger(request, "star");
			hevaluate.setStar(star);
			
			String content = RequestHandler.getString(request, "content");
			hevaluate.setContent(content);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hevaluate.setCreateTime(createTime);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hevaluate.setRowStart(from);
			hevaluate.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hevaluate.setSortColumn(sortColumn);
			
			int hevaluateListCount = hevaluateService.getHEvaluateListCount(hevaluate);
			if ( hevaluateListCount > 0 )
			{
				ResponseList<HEvaluate> hevaluateList = hevaluateService.getHEvaluateList(hevaluate);
				Iterator<Object> it = hevaluateList.iterator();
				List<HEvaluate> listObj = new ArrayList<HEvaluate>();
				while(it.hasNext()){
					HEvaluate obj = (HEvaluate)it.next();
					obj.setAmountStr(super.getMoney(obj.getAmount()));
					listObj.add(obj);
				}
				LinkedList list = new LinkedList();
				list.addAll(listObj);
				LinkedList fields = new LinkedList();
				fields.add("orderNo");
				fields.add("star");
				fields.add("content");
				fields.add("createTime");
				fields.add("c_name");
				fields.add("contact_name");
				fields.add("contact_phone");
				fields.add("oneAgentName");
				fields.add("twoAgentName");
				fields.add("servicerName");
				fields.add("electric_number");
				fields.add("amountStr");
				LinkedList titles = new LinkedList();
				titles.add("订单号");
				titles.add("星级");
				titles.add("评价内容");
				titles.add("评价时间");
				titles.add("单位名称");
				titles.add("客户联系人姓名");
				titles.add("客户联系人手机");
				titles.add("客户经");
				titles.add("代理");
				titles.add("服务人员");
				titles.add("缴费号");
				titles.add("金额");
				String path = hCommonService.excleExport(list, fields, titles, HEvaluate.class, "订单评价",request);
				writeSuccessMsg("成功", path, response);
			} else
			{
				writeErrorMsg("无可导出内容", null, response);
			}
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
}
