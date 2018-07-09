package com.hxt.hNewNotice.controller;

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

import com.hxt.hNewNews.model.HNewNews;
import com.hxt.hNewNotice.model.HNewNotice;
import com.hxt.hNewNotice.service.HNewNoticeService;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SessionName;
import com.base.controller.BaseController;
/**
 * @author	zhangyiyang
 * @time	2016年08月01日 21:15:51
 */
@Controller
@RequestMapping("/hNewNotice")
public class HNewNoticeController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HNewNoticeController.class); //Logger
	
	@Autowired
	private HNewNoticeService hnewnoticeService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hNewNotice/hNewNoticeIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hNewNotice/hNewNoticeAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HNewNotice hnewnotice1 = new HNewNotice();
		hnewnotice1.setId(id);
		HNewNotice hnewnotice = hnewnoticeService.getHNewNotice(hnewnotice1);
		model.addAttribute("hnewnotice", hnewnotice);
		
		return "/hNewNotice/hNewNoticeUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHNewNoticeList", method = RequestMethod.GET)
	public String getHNewNoticeList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HNewNotice hnewnotice = new HNewNotice();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hnewnotice.setId(id);
			
			String title = RequestHandler.getString(request, "title");
			hnewnotice.setTitle(title);
			
			String content = RequestHandler.getString(request, "content");
			hnewnotice.setContent(content);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hnewnotice.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hnewnotice.setCreateId(createId);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hnewnotice.setState(state);
			
			String source = RequestHandler.getString(request, "source");
			hnewnotice.setSource(source);
			Integer sortId = RequestHandler.getInteger(request, "sortId");
			hnewnotice.setSortId(sortId);

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hnewnotice.setRowStart(from);
			hnewnotice.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hnewnotice.setSortColumn(sortColumn);
			
			int hnewnoticeListCount = hnewnoticeService.getHNewNoticeListCount(hnewnotice);
			ResponseList<HNewNotice> hnewnoticeList = null;
			if ( hnewnoticeListCount > 0 )
			{
				hnewnoticeList = hnewnoticeService.getHNewNoticeList(hnewnotice);
			} else
			{
				hnewnoticeList = new ResponseList<HNewNotice>();
			}
			// 设置数据总数
			hnewnoticeList.setTotalResults(hnewnoticeListCount);
			
			writeSuccessMsg("ok", hnewnoticeList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHNewNoticeBaseList", method = RequestMethod.GET)
	public String getHNewNoticeBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HNewNotice hnewnotice = new HNewNotice();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hnewnotice.setId(id);
			
			String title = RequestHandler.getString(request, "title");
			hnewnotice.setTitle(title);
			
			String content = RequestHandler.getString(request, "content");
			hnewnotice.setContent(content);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hnewnotice.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hnewnotice.setCreateId(createId);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hnewnotice.setState(state);
			
			String source = RequestHandler.getString(request, "source");
			hnewnotice.setSource(source);
			Integer sortId = RequestHandler.getInteger(request, "sortId");
			hnewnotice.setSortId(sortId);
			List<HNewNotice> hnewnoticeList = hnewnoticeService.getHNewNoticeBaseList(hnewnotice);
		
			writeSuccessMsg("ok", hnewnoticeList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHNewNotice", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HNewNotice hnewnotice = new HNewNotice();
			
			String title = RequestHandler.getString(request, "title");
			hnewnotice.setTitle(title);
			String content = RequestHandler.getString(request, "content");
			hnewnotice.setContent(content);
			hnewnotice.setCreateTime(new Date());
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			hnewnotice.setCreateId(adminUser.getAdminId());
			Integer state = RequestHandler.getInteger(request, "state");
			hnewnotice.setState(state);
			Integer sortId = RequestHandler.getInteger(request, "sortId");
			hnewnotice.setSortId(sortId);
			hnewnoticeService.insertHNewNotice(hnewnotice);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHNewNotice", method = RequestMethod.POST)
	public String updateHNewNotice(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HNewNotice hnewnotice = new HNewNotice();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hnewnotice.setId(id);
			
			String title = RequestHandler.getString(request, "title");
			hnewnotice.setTitle(title);
			
			String content = RequestHandler.getString(request, "content");
			hnewnotice.setContent(content);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hnewnotice.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hnewnotice.setCreateId(createId);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hnewnotice.setState(state);
			
			String source = RequestHandler.getString(request, "source");
			hnewnotice.setSource(source);
			Integer sortId = RequestHandler.getInteger(request, "sortId");
			hnewnotice.setSortId(sortId);

			hnewnoticeService.updateHNewNotice(hnewnotice);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHNewNotice", method = RequestMethod.POST)
	public String removeHNewNotice(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HNewNotice hnewnotice = new HNewNotice();
			Integer id = RequestHandler.getInteger(request, "id");
			hnewnotice.setId(id);

			hnewnoticeService.removeHNewNotice(hnewnotice);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHNewNotice", method = RequestMethod.POST)
	public String removeAllHNewNotice(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HNewNotice hNewNotice = new HNewNotice();
						hNewNotice.setId(Integer.valueOf(id));
						hnewnoticeService.removeHNewNotice(hNewNotice);
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
