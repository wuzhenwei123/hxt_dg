package com.hxt.hBankInfo.controller;

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

import com.hxt.hBankInfo.model.HBankInfo;
import com.hxt.hBankInfo.service.HBankInfoService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2016年10月20日 17:50:29
 */
@Controller
@RequestMapping("/hBankInfo")
public class HBankInfoController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HBankInfoController.class); //Logger
	
	@Autowired
	private HBankInfoService hbankinfoService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hBankInfo/hBankInfoIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hBankInfo/hBankInfoAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HBankInfo hbankinfo1 = new HBankInfo();
		hbankinfo1.setId(id);
		HBankInfo hbankinfo = hbankinfoService.getHBankInfo(hbankinfo1);
		model.addAttribute("hbankinfo", hbankinfo);
		
		return "/hBankInfo/hBankInfoUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHBankInfoList", method = RequestMethod.GET)
	public String getHBankInfoList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HBankInfo hbankinfo = new HBankInfo();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hbankinfo.setId(id);
			
			String name = RequestHandler.getString(request, "name");
			hbankinfo.setName(name);
			
			String code = RequestHandler.getString(request, "code");
			hbankinfo.setCode(code);
			
			String bankNum = RequestHandler.getString(request, "bankNum");
			hbankinfo.setBankNum(bankNum);
			
			String clearBankNum = RequestHandler.getString(request, "clearBankNum");
			hbankinfo.setClearBankNum(clearBankNum);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hbankinfo.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hbankinfo.setRemark2(remark2);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hbankinfo.setRowStart(from);
			hbankinfo.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hbankinfo.setSortColumn(sortColumn);
			
			int hbankinfoListCount = hbankinfoService.getHBankInfoListCount(hbankinfo);
			ResponseList<HBankInfo> hbankinfoList = null;
			if ( hbankinfoListCount > 0 )
			{
				hbankinfoList = hbankinfoService.getHBankInfoList(hbankinfo);
			} else
			{
				hbankinfoList = new ResponseList<HBankInfo>();
			}
			// 设置数据总数
			hbankinfoList.setTotalResults(hbankinfoListCount);
			
			writeSuccessMsg("ok", hbankinfoList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHBankInfoBaseList", method = RequestMethod.GET)
	public String getHBankInfoBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HBankInfo hbankinfo = new HBankInfo();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hbankinfo.setId(id);
			
			String name = RequestHandler.getString(request, "name");
			hbankinfo.setName(name);
			
			String code = RequestHandler.getString(request, "code");
			hbankinfo.setCode(code);
			
			String bankNum = RequestHandler.getString(request, "bankNum");
			hbankinfo.setBankNum(bankNum);
			
			String clearBankNum = RequestHandler.getString(request, "clearBankNum");
			hbankinfo.setClearBankNum(clearBankNum);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hbankinfo.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hbankinfo.setRemark2(remark2);
			
			List<HBankInfo> hbankinfoList = hbankinfoService.getHBankInfoBaseList(hbankinfo);
		
			writeSuccessMsg("ok", hbankinfoList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHBankInfo", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HBankInfo hbankinfo = new HBankInfo();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hbankinfo.setId(id);
			String name = RequestHandler.getString(request, "name");
			hbankinfo.setName(name);
			String code = RequestHandler.getString(request, "code");
			hbankinfo.setCode(code);
			String bankNum = RequestHandler.getString(request, "bankNum");
			hbankinfo.setBankNum(bankNum);
			String clearBankNum = RequestHandler.getString(request, "clearBankNum");
			hbankinfo.setClearBankNum(clearBankNum);
			String remark1 = RequestHandler.getString(request, "remark1");
			hbankinfo.setRemark1(remark1);
			String remark2 = RequestHandler.getString(request, "remark2");
			hbankinfo.setRemark2(remark2);
				
			hbankinfoService.insertHBankInfo(hbankinfo);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHBankInfo", method = RequestMethod.POST)
	public String updateHBankInfo(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HBankInfo hbankinfo = new HBankInfo();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hbankinfo.setId(id);
			
			String name = RequestHandler.getString(request, "name");
			hbankinfo.setName(name);
			
			String code = RequestHandler.getString(request, "code");
			hbankinfo.setCode(code);
			
			String bankNum = RequestHandler.getString(request, "bankNum");
			hbankinfo.setBankNum(bankNum);
			
			String clearBankNum = RequestHandler.getString(request, "clearBankNum");
			hbankinfo.setClearBankNum(clearBankNum);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hbankinfo.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hbankinfo.setRemark2(remark2);
			

			hbankinfoService.updateHBankInfo(hbankinfo);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHBankInfo", method = RequestMethod.POST)
	public String removeHBankInfo(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HBankInfo hbankinfo = new HBankInfo();
			Integer id = RequestHandler.getInteger(request, "id");
			hbankinfo.setId(id);

			hbankinfoService.removeHBankInfo(hbankinfo);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHBankInfo", method = RequestMethod.POST)
	public String removeAllHBankInfo(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HBankInfo hBankInfo = new HBankInfo();
						hBankInfo.setId(Integer.valueOf(id));
						hbankinfoService.removeHBankInfo(hBankInfo);
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
