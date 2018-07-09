package com.hxt.hAddress.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.base.controller.BaseController;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SessionName;
import com.hxt.hAddress.model.HAddress;
import com.hxt.hAddress.service.HAddressService;
/**
 * @author	zhanglibo
 * @time	2015年09月20日 11:57:00
 */
@Controller
@RequestMapping("/hAddress")
public class HAddressController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HAddressController.class); //Logger
	
	@Autowired
	private HAddressService haddressService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		HAddress haddress = new HAddress();
		int haddressListCount = haddressService.getHAddressListCount(haddress);
		model.addAttribute("count", haddressListCount);
		return "/hAddress/hAddressIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hAddress/hAddressAdd";
	}
	@RequestMapping(value = "/toShow/{id}", method = RequestMethod.GET)
	public String toShow(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HAddress haddress1 = new HAddress();
		haddress1.setId(id);
		HAddress haddress = haddressService.getHAddress(haddress1);
		model.addAttribute("haddress", haddress);
		
		return "/hAddress/show";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HAddress haddress1 = new HAddress();
		haddress1.setId(id);
		HAddress haddress = haddressService.getHAddress(haddress1);
		model.addAttribute("haddress", haddress);
		
		return "/hAddress/hAddressUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHAddressList", method = RequestMethod.GET)
	public String getHAddressList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAddress haddress = new HAddress();
			
			Integer id = RequestHandler.getInteger(request, "id");
			haddress.setId(id);
			
			String content = RequestHandler.getString(request, "content");
			haddress.setContent(content);
			
			Integer state = RequestHandler.getInteger(request, "state");
			haddress.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			haddress.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			haddress.setCreateId(createId);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			haddress.setRowStart(from);
			haddress.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			haddress.setSortColumn(sortColumn);
			
			int haddressListCount = haddressService.getHAddressListCount(haddress);
			ResponseList<HAddress> haddressList = null;
			if ( haddressListCount > 0 )
			{
				haddressList = haddressService.getHAddressList(haddress);
			} else
			{
				haddressList = new ResponseList<HAddress>();
			}
			// 设置数据总数
			haddressList.setTotalResults(haddressListCount);
			
			writeSuccessMsg("ok", haddressList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHAddressBaseList", method = RequestMethod.GET)
	public String getHAddressBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAddress haddress = new HAddress();
			
			Integer id = RequestHandler.getInteger(request, "id");
			haddress.setId(id);
			
			String content = RequestHandler.getString(request, "content");
			haddress.setContent(content);
			
			Integer state = RequestHandler.getInteger(request, "state");
			haddress.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			haddress.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			haddress.setCreateId(createId);
			
			List<HAddress> haddressList = haddressService.getHAddressBaseList(haddress);
		
			writeSuccessMsg("ok", haddressList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHAddress", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HAddress haddress = new HAddress();
			
			Integer id = RequestHandler.getInteger(request, "id");
			haddress.setId(id);
			String content = RequestHandler.getString(request, "content");
			haddress.setContent(content);
			Integer state = RequestHandler.getInteger(request, "state");
			haddress.setState(state);
			Date createTime = RequestHandler.getDate(request, "createTime");
			haddress.setCreateTime(new Date());
			Object adminUserId = getSession(request, SessionName.ADMIN_USER_ID);
			haddress.setCreateId(Integer.valueOf(adminUserId.toString()));
				
			haddressService.insertHAddress(haddress);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHAddress", method = RequestMethod.POST)
	public String updateHAddress(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAddress haddress = new HAddress();
			
			Integer id = RequestHandler.getInteger(request, "id");
			haddress.setId(id);
			
			String content = RequestHandler.getString(request, "content");
			haddress.setContent(content);
			
			Integer state = RequestHandler.getInteger(request, "state");
			haddress.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			haddress.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			haddress.setCreateId(createId);
			

			haddressService.updateHAddress(haddress);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHAddress", method = RequestMethod.POST)
	public String removeHAddress(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAddress haddress = new HAddress();
			Integer id = RequestHandler.getInteger(request, "id");
			haddress.setId(id);

			haddressService.removeHAddress(haddress);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHAddress", method = RequestMethod.POST)
	public String removeAllHAddress(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HAddress hAddress = new HAddress();
						hAddress.setId(Integer.valueOf(id));
						haddressService.removeHAddress(hAddress);
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
	
	@RequestMapping(value = "/toWuYe", method = RequestMethod.GET)
	public String toWuYe(HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HAddress haddress1 = new HAddress();
		haddress1.setState(1);
		HAddress haddress = haddressService.getHAddress(haddress1);
		model.addAttribute("haddress", haddress);
		
		return "/front/wuYe";
	}
}
