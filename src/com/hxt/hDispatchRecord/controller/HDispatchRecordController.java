package com.hxt.hDispatchRecord.controller;

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

import com.hxt.hDispatchRecord.model.HDispatchRecord;
import com.hxt.hDispatchRecord.service.HDispatchRecordService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2015年11月18日 22:41:22
 */
@Controller
@RequestMapping("/hDispatchRecord")
public class HDispatchRecordController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HDispatchRecordController.class); //Logger
	
	@Autowired
	private HDispatchRecordService hdispatchrecordService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hDispatchRecord/hDispatchRecordIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hDispatchRecord/hDispatchRecordAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HDispatchRecord hdispatchrecord1 = new HDispatchRecord();
		hdispatchrecord1.setId(id);
		HDispatchRecord hdispatchrecord = hdispatchrecordService.getHDispatchRecord(hdispatchrecord1);
		model.addAttribute("hdispatchrecord", hdispatchrecord);
		
		return "/hDispatchRecord/hDispatchRecordUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHDispatchRecordList", method = RequestMethod.GET)
	public String getHDispatchRecordList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HDispatchRecord hdispatchrecord = new HDispatchRecord();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hdispatchrecord.setId(id);
			
			String ammeter_no = RequestHandler.getString(request, "ammeter_no");
			hdispatchrecord.setAmmeter_no(ammeter_no);
			
			Integer totalFee = RequestHandler.getInteger(request, "totalFee");
			hdispatchrecord.setTotalFee(totalFee);
			
			String accountName = RequestHandler.getString(request, "accountName");
			hdispatchrecord.setAccountName(accountName);
			
			String address = RequestHandler.getString(request, "address");
			hdispatchrecord.setAddress(address);
			
			Integer status = RequestHandler.getInteger(request, "status");
			hdispatchrecord.setStatus(status);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hdispatchrecord.setCreateTime(createTime);
			
			Date updateTime = RequestHandler.getDate(request, "updateTime");
			hdispatchrecord.setUpdateTime(updateTime);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hdispatchrecord.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hdispatchrecord.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hdispatchrecord.setRemark3(remark3);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hdispatchrecord.setRowStart(from);
			hdispatchrecord.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hdispatchrecord.setSortColumn(sortColumn);
			
			int hdispatchrecordListCount = hdispatchrecordService.getHDispatchRecordListCount(hdispatchrecord);
			ResponseList<HDispatchRecord> hdispatchrecordList = null;
			if ( hdispatchrecordListCount > 0 )
			{
				hdispatchrecordList = hdispatchrecordService.getHDispatchRecordList(hdispatchrecord);
			} else
			{
				hdispatchrecordList = new ResponseList<HDispatchRecord>();
			}
			// 设置数据总数
			hdispatchrecordList.setTotalResults(hdispatchrecordListCount);
			
			writeSuccessMsg("ok", hdispatchrecordList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHDispatchRecordBaseList", method = RequestMethod.GET)
	public String getHDispatchRecordBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HDispatchRecord hdispatchrecord = new HDispatchRecord();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hdispatchrecord.setId(id);
			
			String ammeter_no = RequestHandler.getString(request, "ammeter_no");
			hdispatchrecord.setAmmeter_no(ammeter_no);
			
			Integer totalFee = RequestHandler.getInteger(request, "totalFee");
			hdispatchrecord.setTotalFee(totalFee);
			
			String accountName = RequestHandler.getString(request, "accountName");
			hdispatchrecord.setAccountName(accountName);
			
			String address = RequestHandler.getString(request, "address");
			hdispatchrecord.setAddress(address);
			
			Integer status = RequestHandler.getInteger(request, "status");
			hdispatchrecord.setStatus(status);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hdispatchrecord.setCreateTime(createTime);
			
			Date updateTime = RequestHandler.getDate(request, "updateTime");
			hdispatchrecord.setUpdateTime(updateTime);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hdispatchrecord.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hdispatchrecord.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hdispatchrecord.setRemark3(remark3);
			
			List<HDispatchRecord> hdispatchrecordList = hdispatchrecordService.getHDispatchRecordBaseList(hdispatchrecord);
		
			writeSuccessMsg("ok", hdispatchrecordList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHDispatchRecord", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HDispatchRecord hdispatchrecord = new HDispatchRecord();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hdispatchrecord.setId(id);
			String ammeter_no = RequestHandler.getString(request, "ammeter_no");
			hdispatchrecord.setAmmeter_no(ammeter_no);
			Integer totalFee = RequestHandler.getInteger(request, "totalFee");
			hdispatchrecord.setTotalFee(totalFee);
			String accountName = RequestHandler.getString(request, "accountName");
			hdispatchrecord.setAccountName(accountName);
			String address = RequestHandler.getString(request, "address");
			hdispatchrecord.setAddress(address);
			Integer status = RequestHandler.getInteger(request, "status");
			hdispatchrecord.setStatus(status);
			Date createTime = RequestHandler.getDate(request, "createTime");
			hdispatchrecord.setCreateTime(new Date());
			Date updateTime = RequestHandler.getDate(request, "updateTime");
			hdispatchrecord.setUpdateTime(updateTime);
			String remark1 = RequestHandler.getString(request, "remark1");
			hdispatchrecord.setRemark1(remark1);
			String remark2 = RequestHandler.getString(request, "remark2");
			hdispatchrecord.setRemark2(remark2);
			String remark3 = RequestHandler.getString(request, "remark3");
			hdispatchrecord.setRemark3(remark3);
				
			hdispatchrecordService.insertHDispatchRecord(hdispatchrecord);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHDispatchRecord", method = RequestMethod.POST)
	public String updateHDispatchRecord(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HDispatchRecord hdispatchrecord = new HDispatchRecord();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hdispatchrecord.setId(id);
			
			String ammeter_no = RequestHandler.getString(request, "ammeter_no");
			hdispatchrecord.setAmmeter_no(ammeter_no);
			
			Integer totalFee = RequestHandler.getInteger(request, "totalFee");
			hdispatchrecord.setTotalFee(totalFee);
			
			String accountName = RequestHandler.getString(request, "accountName");
			hdispatchrecord.setAccountName(accountName);
			
			String address = RequestHandler.getString(request, "address");
			hdispatchrecord.setAddress(address);
			
			Integer status = RequestHandler.getInteger(request, "status");
			hdispatchrecord.setStatus(status);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hdispatchrecord.setCreateTime(createTime);
			
			Date updateTime = RequestHandler.getDate(request, "updateTime");
			hdispatchrecord.setUpdateTime(updateTime);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hdispatchrecord.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hdispatchrecord.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hdispatchrecord.setRemark3(remark3);
			

			hdispatchrecordService.updateHDispatchRecord(hdispatchrecord);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHDispatchRecord", method = RequestMethod.POST)
	public String removeHDispatchRecord(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HDispatchRecord hdispatchrecord = new HDispatchRecord();
			Integer id = RequestHandler.getInteger(request, "id");
			hdispatchrecord.setId(id);

			hdispatchrecordService.removeHDispatchRecord(hdispatchrecord);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHDispatchRecord", method = RequestMethod.POST)
	public String removeAllHDispatchRecord(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HDispatchRecord hDispatchRecord = new HDispatchRecord();
						hDispatchRecord.setId(Integer.valueOf(id));
						hdispatchrecordService.removeHDispatchRecord(hDispatchRecord);
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
