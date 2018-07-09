package com.hxt.hRecRecord.controller;

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

import com.hxt.hRecRecord.model.HRecRecord;
import com.hxt.hRecRecord.service.HRecRecordService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2016年09月05日 17:15:23
 */
@Controller
@RequestMapping("/hRecRecord")
public class HRecRecordController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HRecRecordController.class); //Logger
	
	@Autowired
	private HRecRecordService hrecrecordService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hRecRecord/hRecRecordIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hRecRecord/hRecRecordAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HRecRecord hrecrecord1 = new HRecRecord();
		hrecrecord1.setId(id);
		HRecRecord hrecrecord = hrecrecordService.getHRecRecord(hrecrecord1);
		model.addAttribute("hrecrecord", hrecrecord);
		
		return "/hRecRecord/hRecRecordUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHRecRecordList", method = RequestMethod.GET)
	public String getHRecRecordList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HRecRecord hrecrecord = new HRecRecord();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hrecrecord.setId(id);
			
			String ammeter_no = RequestHandler.getString(request, "ammeter_no");
			hrecrecord.setAmmeter_no(ammeter_no);
			String contact_phone = RequestHandler.getString(request, "contact_phone");
			hrecrecord.setContact_phone(contact_phone);
			
			String ammeter_name = RequestHandler.getString(request, "ammeter_name");
			hrecrecord.setAmmeter_name(ammeter_name);
			
			String ammeter_type = RequestHandler.getString(request, "ammeter_type");
			hrecrecord.setAmmeter_type(ammeter_type);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hrecrecord.setCreate_time(create_time);
			
			String ammeter_address = RequestHandler.getString(request, "ammeter_address");
			hrecrecord.setAmmeter_address(ammeter_address);
			
			Integer ammeterinfo_type = RequestHandler.getInteger(request, "ammeterinfo_type");
			hrecrecord.setAmmeterinfo_type(ammeterinfo_type);
			
			String rec_phone = RequestHandler.getString(request, "rec_phone");
			hrecrecord.setRec_phone(rec_phone);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hrecrecord.setRowStart(from);
			hrecrecord.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hrecrecord.setSortColumn(sortColumn);
			
			int hrecrecordListCount = hrecrecordService.getHRecRecordListCount(hrecrecord);
			ResponseList<HRecRecord> hrecrecordList = null;
			if ( hrecrecordListCount > 0 )
			{
				hrecrecordList = hrecrecordService.getHRecRecordList(hrecrecord);
			} else
			{
				hrecrecordList = new ResponseList<HRecRecord>();
			}
			// 设置数据总数
			hrecrecordList.setTotalResults(hrecrecordListCount);
			
			writeSuccessMsg("ok", hrecrecordList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHRecRecordBaseList", method = RequestMethod.GET)
	public String getHRecRecordBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HRecRecord hrecrecord = new HRecRecord();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hrecrecord.setId(id);
			
			String ammeter_no = RequestHandler.getString(request, "ammeter_no");
			hrecrecord.setAmmeter_no(ammeter_no);
			
			String ammeter_name = RequestHandler.getString(request, "ammeter_name");
			hrecrecord.setAmmeter_name(ammeter_name);
			
			String ammeter_type = RequestHandler.getString(request, "ammeter_type");
			hrecrecord.setAmmeter_type(ammeter_type);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hrecrecord.setCreate_time(create_time);
			
			String ammeter_address = RequestHandler.getString(request, "ammeter_address");
			hrecrecord.setAmmeter_address(ammeter_address);
			
			Integer ammeterinfo_type = RequestHandler.getInteger(request, "ammeterinfo_type");
			hrecrecord.setAmmeterinfo_type(ammeterinfo_type);
			
			String rec_phone = RequestHandler.getString(request, "rec_phone");
			hrecrecord.setRec_phone(rec_phone);
			
			List<HRecRecord> hrecrecordList = hrecrecordService.getHRecRecordBaseList(hrecrecord);
		
			writeSuccessMsg("ok", hrecrecordList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHRecRecord", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HRecRecord hrecrecord = new HRecRecord();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hrecrecord.setId(id);
			String ammeter_no = RequestHandler.getString(request, "ammeter_no");
			hrecrecord.setAmmeter_no(ammeter_no);
			String ammeter_name = RequestHandler.getString(request, "ammeter_name");
			hrecrecord.setAmmeter_name(ammeter_name);
			String ammeter_type = RequestHandler.getString(request, "ammeter_type");
			hrecrecord.setAmmeter_type(ammeter_type);
			Date create_time = RequestHandler.getDate(request, "create_time");
			hrecrecord.setCreate_time(create_time);
			String ammeter_address = RequestHandler.getString(request, "ammeter_address");
			hrecrecord.setAmmeter_address(ammeter_address);
			Integer ammeterinfo_type = RequestHandler.getInteger(request, "ammeterinfo_type");
			hrecrecord.setAmmeterinfo_type(ammeterinfo_type);
			String rec_phone = RequestHandler.getString(request, "rec_phone");
			hrecrecord.setRec_phone(rec_phone);
				
			hrecrecordService.insertHRecRecord(hrecrecord);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHRecRecord", method = RequestMethod.POST)
	public String updateHRecRecord(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HRecRecord hrecrecord = new HRecRecord();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hrecrecord.setId(id);
			
			String ammeter_no = RequestHandler.getString(request, "ammeter_no");
			hrecrecord.setAmmeter_no(ammeter_no);
			
			String ammeter_name = RequestHandler.getString(request, "ammeter_name");
			hrecrecord.setAmmeter_name(ammeter_name);
			
			String ammeter_type = RequestHandler.getString(request, "ammeter_type");
			hrecrecord.setAmmeter_type(ammeter_type);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hrecrecord.setCreate_time(create_time);
			
			String ammeter_address = RequestHandler.getString(request, "ammeter_address");
			hrecrecord.setAmmeter_address(ammeter_address);
			
			Integer ammeterinfo_type = RequestHandler.getInteger(request, "ammeterinfo_type");
			hrecrecord.setAmmeterinfo_type(ammeterinfo_type);
			
			String rec_phone = RequestHandler.getString(request, "rec_phone");
			hrecrecord.setRec_phone(rec_phone);
			

			hrecrecordService.updateHRecRecord(hrecrecord);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHRecRecord", method = RequestMethod.POST)
	public String removeHRecRecord(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HRecRecord hrecrecord = new HRecRecord();
			Integer id = RequestHandler.getInteger(request, "id");
			hrecrecord.setId(id);

			hrecrecordService.removeHRecRecord(hrecrecord);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHRecRecord", method = RequestMethod.POST)
	public String removeAllHRecRecord(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HRecRecord hRecRecord = new HRecRecord();
						hRecRecord.setId(Integer.valueOf(id));
						hrecrecordService.removeHRecRecord(hRecRecord);
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
