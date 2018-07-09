package com.hxt.hDispatchRecordC.controller;

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

import com.hxt.hDispatchRecordC.model.HDispatchRecordC;
import com.hxt.hDispatchRecordC.service.HDispatchRecordCService;
import com.hxt.task.TaskJob;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2016年07月21日 11:17:00
 */
@Controller
@RequestMapping("/hDispatchRecordC")
public class HDispatchRecordCController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HDispatchRecordCController.class); //Logger
	
	@Autowired
	private HDispatchRecordCService hdispatchrecordcService = null;
	@Autowired
	private TaskJob job = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hDispatchRecordC/hDispatchRecordCIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hDispatchRecordC/hDispatchRecordCAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HDispatchRecordC hdispatchrecordc1 = new HDispatchRecordC();
		hdispatchrecordc1.setId(id);
		HDispatchRecordC hdispatchrecordc = hdispatchrecordcService.getHDispatchRecordC(hdispatchrecordc1);
		model.addAttribute("hdispatchrecordc", hdispatchrecordc);
		
		return "/hDispatchRecordC/hDispatchRecordCUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHDispatchRecordCList", method = RequestMethod.GET)
	public String getHDispatchRecordCList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HDispatchRecordC hdispatchrecordc = new HDispatchRecordC();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hdispatchrecordc.setId(id);
			
			String contact_phone = RequestHandler.getString(request, "contact_phone");
			hdispatchrecordc.setContact_phone(contact_phone);
			
			String ammeter_no = RequestHandler.getString(request, "ammeter_no");
			hdispatchrecordc.setAmmeter_no(ammeter_no);
			
			Long totalFee = RequestHandler.getLong(request, "totalFee");
			hdispatchrecordc.setTotalFee(totalFee);
			
			Integer status = RequestHandler.getInteger(request, "status");
			hdispatchrecordc.setStatus(status);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hdispatchrecordc.setCreateTime(createTime);
			
			Date updateTime = RequestHandler.getDate(request, "updateTime");
			hdispatchrecordc.setUpdateTime(updateTime);
			
			String content = RequestHandler.getString(request, "content");
			hdispatchrecordc.setContent(content);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hdispatchrecordc.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hdispatchrecordc.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hdispatchrecordc.setRemark3(remark3);
			
			Integer c_id = RequestHandler.getInteger(request, "c_id");
			hdispatchrecordc.setC_id(c_id);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hdispatchrecordc.setRowStart(from);
			hdispatchrecordc.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hdispatchrecordc.setSortColumn(sortColumn);
			
			int hdispatchrecordcListCount = hdispatchrecordcService.getHDispatchRecordCListCount(hdispatchrecordc);
			ResponseList<HDispatchRecordC> hdispatchrecordcList = null;
			if ( hdispatchrecordcListCount > 0 )
			{
				hdispatchrecordcList = hdispatchrecordcService.getHDispatchRecordCList(hdispatchrecordc);
			} else
			{
				hdispatchrecordcList = new ResponseList<HDispatchRecordC>();
			}
			// 设置数据总数
			hdispatchrecordcList.setTotalResults(hdispatchrecordcListCount);
			
			writeSuccessMsg("ok", hdispatchrecordcList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHDispatchRecordCBaseList", method = RequestMethod.GET)
	public String getHDispatchRecordCBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HDispatchRecordC hdispatchrecordc = new HDispatchRecordC();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hdispatchrecordc.setId(id);
			
			String contact_phone = RequestHandler.getString(request, "contact_phone");
			hdispatchrecordc.setContact_phone(contact_phone);
			
			String ammeter_no = RequestHandler.getString(request, "ammeter_no");
			hdispatchrecordc.setAmmeter_no(ammeter_no);
			
			Long totalFee = RequestHandler.getLong(request, "totalFee");
			hdispatchrecordc.setTotalFee(totalFee);
			
			Integer status = RequestHandler.getInteger(request, "status");
			hdispatchrecordc.setStatus(status);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hdispatchrecordc.setCreateTime(createTime);
			
			Date updateTime = RequestHandler.getDate(request, "updateTime");
			hdispatchrecordc.setUpdateTime(updateTime);
			
			String content = RequestHandler.getString(request, "content");
			hdispatchrecordc.setContent(content);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hdispatchrecordc.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hdispatchrecordc.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hdispatchrecordc.setRemark3(remark3);
			
			Integer c_id = RequestHandler.getInteger(request, "c_id");
			hdispatchrecordc.setC_id(c_id);
			
			List<HDispatchRecordC> hdispatchrecordcList = hdispatchrecordcService.getHDispatchRecordCBaseList(hdispatchrecordc);
		
			writeSuccessMsg("ok", hdispatchrecordcList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHDispatchRecordC", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HDispatchRecordC hdispatchrecordc = new HDispatchRecordC();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hdispatchrecordc.setId(id);
			String contact_phone = RequestHandler.getString(request, "contact_phone");
			hdispatchrecordc.setContact_phone(contact_phone);
			String ammeter_no = RequestHandler.getString(request, "ammeter_no");
			hdispatchrecordc.setAmmeter_no(ammeter_no);
			Long totalFee = RequestHandler.getLong(request, "totalFee");
			hdispatchrecordc.setTotalFee(totalFee);
			Integer status = RequestHandler.getInteger(request, "status");
			hdispatchrecordc.setStatus(status);
			Date createTime = RequestHandler.getDate(request, "createTime");
			hdispatchrecordc.setCreateTime(new Date());
			Date updateTime = RequestHandler.getDate(request, "updateTime");
			hdispatchrecordc.setUpdateTime(updateTime);
			String content = RequestHandler.getString(request, "content");
			hdispatchrecordc.setContent(content);
			String remark1 = RequestHandler.getString(request, "remark1");
			hdispatchrecordc.setRemark1(remark1);
			String remark2 = RequestHandler.getString(request, "remark2");
			hdispatchrecordc.setRemark2(remark2);
			String remark3 = RequestHandler.getString(request, "remark3");
			hdispatchrecordc.setRemark3(remark3);
			Integer c_id = RequestHandler.getInteger(request, "c_id");
			hdispatchrecordc.setC_id(c_id);
				
			hdispatchrecordcService.insertHDispatchRecordC(hdispatchrecordc);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHDispatchRecordC", method = RequestMethod.POST)
	public String updateHDispatchRecordC(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HDispatchRecordC hdispatchrecordc = new HDispatchRecordC();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hdispatchrecordc.setId(id);
			
			String contact_phone = RequestHandler.getString(request, "contact_phone");
			hdispatchrecordc.setContact_phone(contact_phone);
			
			String ammeter_no = RequestHandler.getString(request, "ammeter_no");
			hdispatchrecordc.setAmmeter_no(ammeter_no);
			
			Long totalFee = RequestHandler.getLong(request, "totalFee");
			hdispatchrecordc.setTotalFee(totalFee);
			
			Integer status = RequestHandler.getInteger(request, "status");
			hdispatchrecordc.setStatus(status);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hdispatchrecordc.setCreateTime(createTime);
			
			Date updateTime = RequestHandler.getDate(request, "updateTime");
			hdispatchrecordc.setUpdateTime(updateTime);
			
			String content = RequestHandler.getString(request, "content");
			hdispatchrecordc.setContent(content);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hdispatchrecordc.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hdispatchrecordc.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hdispatchrecordc.setRemark3(remark3);
			
			Integer c_id = RequestHandler.getInteger(request, "c_id");
			hdispatchrecordc.setC_id(c_id);
			

			hdispatchrecordcService.updateHDispatchRecordC(hdispatchrecordc);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHDispatchRecordC", method = RequestMethod.POST)
	public String removeHDispatchRecordC(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HDispatchRecordC hdispatchrecordc = new HDispatchRecordC();
			Integer id = RequestHandler.getInteger(request, "id");
			hdispatchrecordc.setId(id);

			hdispatchrecordcService.removeHDispatchRecordC(hdispatchrecordc);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHDispatchRecordC", method = RequestMethod.POST)
	public String removeAllHDispatchRecordC(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HDispatchRecordC hDispatchRecordC = new HDispatchRecordC();
						hDispatchRecordC.setId(Integer.valueOf(id));
						hdispatchrecordcService.removeHDispatchRecordC(hDispatchRecordC);
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
	
	@RequestMapping(value = "/task", method = RequestMethod.GET)
	public String task(HttpServletRequest request, HttpServletResponse response, Model model){
		job.job1();
		return null;
	}
}
