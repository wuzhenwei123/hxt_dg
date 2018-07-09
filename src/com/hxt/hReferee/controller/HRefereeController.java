package com.hxt.hReferee.controller;

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

import com.hxt.hReferee.model.HReferee;
import com.hxt.hReferee.service.HRefereeService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2015年09月04日 14:33:54
 */
@Controller
@RequestMapping("/hReferee")
public class HRefereeController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HRefereeController.class); //Logger
	
	@Autowired
	private HRefereeService hrefereeService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hReferee/hRefereeIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hReferee/hRefereeAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HReferee hreferee1 = new HReferee();
		hreferee1.setId(id);
		HReferee hreferee = hrefereeService.getHReferee(hreferee1);
		model.addAttribute("hreferee", hreferee);
		
		return "/hReferee/hRefereeUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHRefereeList", method = RequestMethod.GET)
	public String getHRefereeList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HReferee hreferee = new HReferee();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hreferee.setId(id);
			
			String tjrId = RequestHandler.getString(request, "tjrId");
			hreferee.setTjrId(tjrId);
			
			String beituijianId = RequestHandler.getString(request, "beituijianId");
			hreferee.setBeituijianId(beituijianId);
			
			String ticket = RequestHandler.getString(request, "ticket");
			hreferee.setTicket(ticket);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hreferee.setCreateTime(createTime);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hreferee.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hreferee.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hreferee.setRemark3(remark3);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hreferee.setRowStart(from);
			hreferee.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hreferee.setSortColumn(sortColumn);
			
			int hrefereeListCount = hrefereeService.getHRefereeListCount(hreferee);
			ResponseList<HReferee> hrefereeList = null;
			if ( hrefereeListCount > 0 )
			{
				hrefereeList = hrefereeService.getHRefereeList(hreferee);
			} else
			{
				hrefereeList = new ResponseList<HReferee>();
			}
			// 设置数据总数
			hrefereeList.setTotalResults(hrefereeListCount);
			
			writeSuccessMsg("ok", hrefereeList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHRefereeBaseList", method = RequestMethod.GET)
	public String getHRefereeBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HReferee hreferee = new HReferee();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hreferee.setId(id);
			
			String tjrId = RequestHandler.getString(request, "tjrId");
			hreferee.setTjrId(tjrId);
			
			String beituijianId = RequestHandler.getString(request, "beituijianId");
			hreferee.setBeituijianId(beituijianId);
			
			String ticket = RequestHandler.getString(request, "ticket");
			hreferee.setTicket(ticket);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hreferee.setCreateTime(createTime);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hreferee.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hreferee.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hreferee.setRemark3(remark3);
			
			List<HReferee> hrefereeList = hrefereeService.getHRefereeBaseList(hreferee);
		
			writeSuccessMsg("ok", hrefereeList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHReferee", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HReferee hreferee = new HReferee();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hreferee.setId(id);
			String tjrId = RequestHandler.getString(request, "tjrId");
			hreferee.setTjrId(tjrId);
			String beituijianId = RequestHandler.getString(request, "beituijianId");
			hreferee.setBeituijianId(beituijianId);
			String ticket = RequestHandler.getString(request, "ticket");
			hreferee.setTicket(ticket);
			Date createTime = RequestHandler.getDate(request, "createTime");
			hreferee.setCreateTime(new Date());
			String remark1 = RequestHandler.getString(request, "remark1");
			hreferee.setRemark1(remark1);
			String remark2 = RequestHandler.getString(request, "remark2");
			hreferee.setRemark2(remark2);
			String remark3 = RequestHandler.getString(request, "remark3");
			hreferee.setRemark3(remark3);
				
			hrefereeService.insertHReferee(hreferee);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHReferee", method = RequestMethod.POST)
	public String updateHReferee(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HReferee hreferee = new HReferee();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hreferee.setId(id);
			
			String tjrId = RequestHandler.getString(request, "tjrId");
			hreferee.setTjrId(tjrId);
			
			String beituijianId = RequestHandler.getString(request, "beituijianId");
			hreferee.setBeituijianId(beituijianId);
			
			String ticket = RequestHandler.getString(request, "ticket");
			hreferee.setTicket(ticket);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hreferee.setCreateTime(createTime);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hreferee.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hreferee.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hreferee.setRemark3(remark3);
			

			hrefereeService.updateHReferee(hreferee);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHReferee", method = RequestMethod.POST)
	public String removeHReferee(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HReferee hreferee = new HReferee();
			Integer id = RequestHandler.getInteger(request, "id");
			hreferee.setId(id);

			hrefereeService.removeHReferee(hreferee);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHReferee", method = RequestMethod.POST)
	public String removeAllHReferee(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HReferee hReferee = new HReferee();
						hReferee.setId(Integer.valueOf(id));
						hrefereeService.removeHReferee(hReferee);
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
