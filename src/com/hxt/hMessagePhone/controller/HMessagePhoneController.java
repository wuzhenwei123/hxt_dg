package com.hxt.hMessagePhone.controller;

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

import com.hxt.hMessagePhone.model.HMessagePhone;
import com.hxt.hMessagePhone.service.HMessagePhoneService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2017年06月22日 14:48:12
 */
@Controller
@RequestMapping("/hMessagePhone")
public class HMessagePhoneController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HMessagePhoneController.class); //Logger
	
	@Autowired
	private HMessagePhoneService hmessagephoneService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		HMessagePhone hmessagephone = new HMessagePhone();
		int hmessagephoneListCount = hmessagephoneService.getHMessagePhoneListCount(hmessagephone);
		model.addAttribute("hmessagephoneListCount", hmessagephoneListCount);
		return "/hMessagePhone/hMessagePhoneIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hMessagePhone/hMessagePhoneAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HMessagePhone hmessagephone1 = new HMessagePhone();
		hmessagephone1.setId(id);
		HMessagePhone hmessagephone = hmessagephoneService.getHMessagePhone(hmessagephone1);
		model.addAttribute("hmessagephone", hmessagephone);
		
		return "/hMessagePhone/hMessagePhoneUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHMessagePhoneList", method = RequestMethod.GET)
	public String getHMessagePhoneList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HMessagePhone hmessagephone = new HMessagePhone();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hmessagephone.setId(id);
			
			String phone = RequestHandler.getString(request, "phone");
			hmessagephone.setPhone(phone);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hmessagephone.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hmessagephone.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hmessagephone.setRemark3(remark3);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hmessagephone.setRowStart(from);
			hmessagephone.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hmessagephone.setSortColumn(sortColumn);
			
			int hmessagephoneListCount = hmessagephoneService.getHMessagePhoneListCount(hmessagephone);
			ResponseList<HMessagePhone> hmessagephoneList = null;
			if ( hmessagephoneListCount > 0 )
			{
				hmessagephoneList = hmessagephoneService.getHMessagePhoneList(hmessagephone);
			} else
			{
				hmessagephoneList = new ResponseList<HMessagePhone>();
			}
			// 设置数据总数
			hmessagephoneList.setTotalResults(hmessagephoneListCount);
			
			writeSuccessMsg("ok", hmessagephoneList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHMessagePhoneBaseList", method = RequestMethod.GET)
	public String getHMessagePhoneBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HMessagePhone hmessagephone = new HMessagePhone();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hmessagephone.setId(id);
			
			String phone = RequestHandler.getString(request, "phone");
			hmessagephone.setPhone(phone);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hmessagephone.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hmessagephone.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hmessagephone.setRemark3(remark3);
			
			List<HMessagePhone> hmessagephoneList = hmessagephoneService.getHMessagePhoneBaseList(hmessagephone);
		
			writeSuccessMsg("ok", hmessagephoneList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHMessagePhone", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HMessagePhone hmessagephone = new HMessagePhone();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hmessagephone.setId(id);
			String phone = RequestHandler.getString(request, "phone");
			hmessagephone.setPhone(phone);
			String remark1 = RequestHandler.getString(request, "remark1");
			hmessagephone.setRemark1(remark1);
			String remark2 = RequestHandler.getString(request, "remark2");
			hmessagephone.setRemark2(remark2);
			String remark3 = RequestHandler.getString(request, "remark3");
			hmessagephone.setRemark3(remark3);
				
			hmessagephoneService.insertHMessagePhone(hmessagephone);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHMessagePhone", method = RequestMethod.POST)
	public String updateHMessagePhone(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HMessagePhone hmessagephone = new HMessagePhone();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hmessagephone.setId(id);
			
			String phone = RequestHandler.getString(request, "phone");
			hmessagephone.setPhone(phone);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hmessagephone.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hmessagephone.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hmessagephone.setRemark3(remark3);
			

			hmessagephoneService.updateHMessagePhone(hmessagephone);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHMessagePhone", method = RequestMethod.POST)
	public String removeHMessagePhone(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HMessagePhone hmessagephone = new HMessagePhone();
			Integer id = RequestHandler.getInteger(request, "id");
			hmessagephone.setId(id);

			hmessagephoneService.removeHMessagePhone(hmessagephone);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHMessagePhone", method = RequestMethod.POST)
	public String removeAllHMessagePhone(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HMessagePhone hMessagePhone = new HMessagePhone();
						hMessagePhone.setId(Integer.valueOf(id));
						hmessagephoneService.removeHMessagePhone(hMessagePhone);
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
