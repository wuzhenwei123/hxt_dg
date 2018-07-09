package com.hxt.hPayurlCheck.controller;

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

import com.hxt.hPayurlCheck.model.HPayurlCheck;
import com.hxt.hPayurlCheck.service.HPayurlCheckService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2015年11月28日 22:43:25
 */
@Controller
@RequestMapping("/hPayurlCheck")
public class HPayurlCheckController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HPayurlCheckController.class); //Logger
	
	@Autowired
	private HPayurlCheckService hpayurlcheckService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hPayurlCheck/hPayurlCheckIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hPayurlCheck/hPayurlCheckAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HPayurlCheck hpayurlcheck1 = new HPayurlCheck();
		hpayurlcheck1.setId(id);
		HPayurlCheck hpayurlcheck = hpayurlcheckService.getHPayurlCheck(hpayurlcheck1);
		model.addAttribute("hpayurlcheck", hpayurlcheck);
		
		return "/hPayurlCheck/hPayurlCheckUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHPayurlCheckList", method = RequestMethod.GET)
	public String getHPayurlCheckList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HPayurlCheck hpayurlcheck = new HPayurlCheck();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hpayurlcheck.setId(id);
			
			Integer c_id = RequestHandler.getInteger(request, "c_id");
			hpayurlcheck.setC_id(c_id);
			
			String check_no = RequestHandler.getString(request, "check_no");
			hpayurlcheck.setCheck_no(check_no);
			
			String check_url = RequestHandler.getString(request, "check_url");
			hpayurlcheck.setCheck_url(check_url);
			
			String pay_url = RequestHandler.getString(request, "pay_url");
			hpayurlcheck.setPay_url(pay_url);
			
			String status = RequestHandler.getString(request, "status");
			hpayurlcheck.setStatus(status);
			
			Date create_date = RequestHandler.getDate(request, "create_date");
			hpayurlcheck.setCreate_date(create_date);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hpayurlcheck.setCreate_time(create_time);
			
			Date check_time = RequestHandler.getDate(request, "check_time");
			hpayurlcheck.setCheck_time(check_time);
			
			String open_ip = RequestHandler.getString(request, "open_ip");
			hpayurlcheck.setOpen_ip(open_ip);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hpayurlcheck.setRowStart(from);
			hpayurlcheck.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hpayurlcheck.setSortColumn(sortColumn);
			
			int hpayurlcheckListCount = hpayurlcheckService.getHPayurlCheckListCount(hpayurlcheck);
			ResponseList<HPayurlCheck> hpayurlcheckList = null;
			if ( hpayurlcheckListCount > 0 )
			{
				hpayurlcheckList = hpayurlcheckService.getHPayurlCheckList(hpayurlcheck);
			} else
			{
				hpayurlcheckList = new ResponseList<HPayurlCheck>();
			}
			// 设置数据总数
			hpayurlcheckList.setTotalResults(hpayurlcheckListCount);
			
			writeSuccessMsg("ok", hpayurlcheckList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHPayurlCheckBaseList", method = RequestMethod.GET)
	public String getHPayurlCheckBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HPayurlCheck hpayurlcheck = new HPayurlCheck();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hpayurlcheck.setId(id);
			
			Integer c_id = RequestHandler.getInteger(request, "c_id");
			hpayurlcheck.setC_id(c_id);
			
			String check_no = RequestHandler.getString(request, "check_no");
			hpayurlcheck.setCheck_no(check_no);
			
			String check_url = RequestHandler.getString(request, "check_url");
			hpayurlcheck.setCheck_url(check_url);
			
			String pay_url = RequestHandler.getString(request, "pay_url");
			hpayurlcheck.setPay_url(pay_url);
			
			String status = RequestHandler.getString(request, "status");
			hpayurlcheck.setStatus(status);
			
			Date create_date = RequestHandler.getDate(request, "create_date");
			hpayurlcheck.setCreate_date(create_date);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hpayurlcheck.setCreate_time(create_time);
			
			Date check_time = RequestHandler.getDate(request, "check_time");
			hpayurlcheck.setCheck_time(check_time);
			
			String open_ip = RequestHandler.getString(request, "open_ip");
			hpayurlcheck.setOpen_ip(open_ip);
			
			List<HPayurlCheck> hpayurlcheckList = hpayurlcheckService.getHPayurlCheckBaseList(hpayurlcheck);
		
			writeSuccessMsg("ok", hpayurlcheckList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHPayurlCheck", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HPayurlCheck hpayurlcheck = new HPayurlCheck();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hpayurlcheck.setId(id);
			Integer c_id = RequestHandler.getInteger(request, "c_id");
			hpayurlcheck.setC_id(c_id);
			String check_no = RequestHandler.getString(request, "check_no");
			hpayurlcheck.setCheck_no(check_no);
			String check_url = RequestHandler.getString(request, "check_url");
			hpayurlcheck.setCheck_url(check_url);
			String pay_url = RequestHandler.getString(request, "pay_url");
			hpayurlcheck.setPay_url(pay_url);
			String status = RequestHandler.getString(request, "status");
			hpayurlcheck.setStatus(status);
			Date create_date = RequestHandler.getDate(request, "create_date");
			hpayurlcheck.setCreate_date(create_date);
			Date create_time = RequestHandler.getDate(request, "create_time");
			hpayurlcheck.setCreate_time(create_time);
			Date check_time = RequestHandler.getDate(request, "check_time");
			hpayurlcheck.setCheck_time(check_time);
			String open_ip = RequestHandler.getString(request, "open_ip");
			hpayurlcheck.setOpen_ip(open_ip);
				
			hpayurlcheckService.insertHPayurlCheck(hpayurlcheck);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHPayurlCheck", method = RequestMethod.POST)
	public String updateHPayurlCheck(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HPayurlCheck hpayurlcheck = new HPayurlCheck();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hpayurlcheck.setId(id);
			
			Integer c_id = RequestHandler.getInteger(request, "c_id");
			hpayurlcheck.setC_id(c_id);
			
			String check_no = RequestHandler.getString(request, "check_no");
			hpayurlcheck.setCheck_no(check_no);
			
			String check_url = RequestHandler.getString(request, "check_url");
			hpayurlcheck.setCheck_url(check_url);
			
			String pay_url = RequestHandler.getString(request, "pay_url");
			hpayurlcheck.setPay_url(pay_url);
			
			String status = RequestHandler.getString(request, "status");
			hpayurlcheck.setStatus(status);
			
			Date create_date = RequestHandler.getDate(request, "create_date");
			hpayurlcheck.setCreate_date(create_date);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hpayurlcheck.setCreate_time(create_time);
			
			Date check_time = RequestHandler.getDate(request, "check_time");
			hpayurlcheck.setCheck_time(check_time);
			
			String open_ip = RequestHandler.getString(request, "open_ip");
			hpayurlcheck.setOpen_ip(open_ip);
			

			hpayurlcheckService.updateHPayurlCheck(hpayurlcheck);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHPayurlCheck", method = RequestMethod.POST)
	public String removeHPayurlCheck(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HPayurlCheck hpayurlcheck = new HPayurlCheck();
			Integer id = RequestHandler.getInteger(request, "id");
			hpayurlcheck.setId(id);

			hpayurlcheckService.removeHPayurlCheck(hpayurlcheck);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHPayurlCheck", method = RequestMethod.POST)
	public String removeAllHPayurlCheck(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HPayurlCheck hPayurlCheck = new HPayurlCheck();
						hPayurlCheck.setId(Integer.valueOf(id));
						hpayurlcheckService.removeHPayurlCheck(hPayurlCheck);
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
