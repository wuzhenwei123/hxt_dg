package com.hxt.hXieyi.controller;

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

import com.hxt.hXieyi.model.HXieyi;
import com.hxt.hXieyi.service.HXieyiService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SessionName;
import com.base.controller.BaseController;
/**
 * @author	zhanglibo
 * @time	2016年08月29日 10:31:37
 */
@Controller
@RequestMapping("/hXieyi")
public class HXieyiController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HXieyiController.class); //Logger
	
	@Autowired
	private HXieyiService hxieyiService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		int hxieyiListCount = hxieyiService.getHXieyiListCount(new HXieyi());
		model.addAttribute("hxieyiListCount", hxieyiListCount);
		return "/hXieyi/hXieyiIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hXieyi/hXieyiAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HXieyi hxieyi1 = new HXieyi();
		hxieyi1.setId(id);
		HXieyi hxieyi = hxieyiService.getHXieyi(hxieyi1);
		model.addAttribute("hxieyi", hxieyi);
		
		return "/hXieyi/hXieyiUpdate";
	}
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HXieyi hxieyi1 = new HXieyi();
		hxieyi1.setId(id);
		HXieyi hxieyi = hxieyiService.getHXieyi(hxieyi1);
		model.addAttribute("hxieyi", hxieyi);
		
		return "/hXieyi/show";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHXieyiList", method = RequestMethod.GET)
	public String getHXieyiList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HXieyi hxieyi = new HXieyi();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hxieyi.setId(id);
			
			String content = RequestHandler.getString(request, "content");
			hxieyi.setContent(content);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hxieyi.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hxieyi.setCreateId(createId);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hxieyi.setRowStart(from);
			hxieyi.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hxieyi.setSortColumn(sortColumn);
			
			int hxieyiListCount = hxieyiService.getHXieyiListCount(hxieyi);
			ResponseList<HXieyi> hxieyiList = null;
			if ( hxieyiListCount > 0 )
			{
				hxieyiList = hxieyiService.getHXieyiList(hxieyi);
			} else
			{
				hxieyiList = new ResponseList<HXieyi>();
			}
			// 设置数据总数
			hxieyiList.setTotalResults(hxieyiListCount);
			
			writeSuccessMsg("ok", hxieyiList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHXieyiBaseList", method = RequestMethod.GET)
	public String getHXieyiBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HXieyi hxieyi = new HXieyi();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hxieyi.setId(id);
			
			String content = RequestHandler.getString(request, "content");
			hxieyi.setContent(content);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hxieyi.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hxieyi.setCreateId(createId);
			
			List<HXieyi> hxieyiList = hxieyiService.getHXieyiBaseList(hxieyi);
		
			writeSuccessMsg("ok", hxieyiList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHXieyi", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HXieyi hxieyi = new HXieyi();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hxieyi.setId(id);
			String content = RequestHandler.getString(request, "content");
			hxieyi.setContent(content);
			hxieyi.setCreateTime(new Date());
			hxieyi.setCreateId(Integer.valueOf(getSession(request, SessionName.ADMIN_USER_ID).toString()));
				
			hxieyiService.insertHXieyi(hxieyi);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHXieyi", method = RequestMethod.POST)
	public String updateHXieyi(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HXieyi hxieyi = new HXieyi();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hxieyi.setId(id);
			
			String content = RequestHandler.getString(request, "content");
			hxieyi.setContent(content);

			hxieyiService.updateHXieyi(hxieyi);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHXieyi", method = RequestMethod.POST)
	public String removeHXieyi(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HXieyi hxieyi = new HXieyi();
			Integer id = RequestHandler.getInteger(request, "id");
			hxieyi.setId(id);

			hxieyiService.removeHXieyi(hxieyi);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHXieyi", method = RequestMethod.POST)
	public String removeAllHXieyi(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HXieyi hXieyi = new HXieyi();
						hXieyi.setId(Integer.valueOf(id));
						hxieyiService.removeHXieyi(hXieyi);
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
