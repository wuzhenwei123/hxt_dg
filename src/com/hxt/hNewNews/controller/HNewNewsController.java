package com.hxt.hNewNews.controller;

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
import com.hxt.hNewNews.service.HNewNewsService;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SessionName;
import com.base.controller.BaseController;
/**
 * @author	zhangyiyang
 * @time	2016年08月01日 21:15:25
 */
@Controller
@RequestMapping("/hNewNews")
public class HNewNewsController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HNewNewsController.class); //Logger
	
	@Autowired
	private HNewNewsService hnewnewsService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hNewNews/hNewNewsIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hNewNews/hNewNewsAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HNewNews hnewnews1 = new HNewNews();
		hnewnews1.setId(id);
		HNewNews hnewnews = hnewnewsService.getHNewNews(hnewnews1);
		model.addAttribute("hnewnews", hnewnews);
		
		return "/hNewNews/hNewNewsUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHNewNewsList", method = RequestMethod.GET)
	public String getHNewNewsList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HNewNews hnewnews = new HNewNews();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hnewnews.setId(id);
			
			String title = RequestHandler.getString(request, "title");
			hnewnews.setTitle(title);
			
			String content = RequestHandler.getString(request, "content");
			hnewnews.setContent(content);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hnewnews.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hnewnews.setCreateId(createId);
			Integer sortId = RequestHandler.getInteger(request, "sortId");
			hnewnews.setSortId(sortId);
			Integer state = RequestHandler.getInteger(request, "state");
			hnewnews.setState(state);
			String imgUrl = RequestHandler.getString(request, "imgUrl");
			hnewnews.setImgUrl(imgUrl);
			
			String source = RequestHandler.getString(request, "source");
			hnewnews.setSource(source);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hnewnews.setRowStart(from);
			hnewnews.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hnewnews.setSortColumn(sortColumn);
			
			int hnewnewsListCount = hnewnewsService.getHNewNewsListCount(hnewnews);
			ResponseList<HNewNews> hnewnewsList = null;
			if ( hnewnewsListCount > 0 )
			{
				hnewnewsList = hnewnewsService.getHNewNewsList(hnewnews);
			} else
			{
				hnewnewsList = new ResponseList<HNewNews>();
			}
			// 设置数据总数
			hnewnewsList.setTotalResults(hnewnewsListCount);
			
			writeSuccessMsg("ok", hnewnewsList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHNewNewsBaseList", method = RequestMethod.GET)
	public String getHNewNewsBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HNewNews hnewnews = new HNewNews();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hnewnews.setId(id);
			
			String title = RequestHandler.getString(request, "title");
			hnewnews.setTitle(title);
			
			String content = RequestHandler.getString(request, "content");
			hnewnews.setContent(content);
			Integer sortId = RequestHandler.getInteger(request, "sortId");
			hnewnews.setSortId(sortId);
			Date createTime = RequestHandler.getDate(request, "createTime");
			hnewnews.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hnewnews.setCreateId(createId);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hnewnews.setState(state);
			
			String source = RequestHandler.getString(request, "source");
			hnewnews.setSource(source);
			String imgUrl = RequestHandler.getString(request, "imgUrl");
			hnewnews.setImgUrl(imgUrl);
			
			List<HNewNews> hnewnewsList = hnewnewsService.getHNewNewsBaseList(hnewnews);
		
			writeSuccessMsg("ok", hnewnewsList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHNewNews", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HNewNews hnewnews = new HNewNews();
			
			String title = RequestHandler.getString(request, "title");
			hnewnews.setTitle(title);
			String content = RequestHandler.getString(request, "content");
			hnewnews.setContent(content);
			hnewnews.setCreateTime(new Date());
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			hnewnews.setCreateId(adminUser.getAdminId());
			Integer state = RequestHandler.getInteger(request, "state");
			hnewnews.setState(state);
			Integer sortId = RequestHandler.getInteger(request, "sortId");
			hnewnews.setSortId(sortId);
			String imgUrl = RequestHandler.getString(request, "imgUrl");
			hnewnews.setImgUrl(imgUrl);
			hnewnewsService.insertHNewNews(hnewnews);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHNewNews", method = RequestMethod.POST)
	public String updateHNewNews(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HNewNews hnewnews = new HNewNews();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hnewnews.setId(id);
			
			String title = RequestHandler.getString(request, "title");
			hnewnews.setTitle(title);
			
			String content = RequestHandler.getString(request, "content");
			hnewnews.setContent(content);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hnewnews.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hnewnews.setCreateId(createId);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hnewnews.setState(state);
			Integer sortId = RequestHandler.getInteger(request, "sortId");
			hnewnews.setSortId(sortId);
			String source = RequestHandler.getString(request, "source");
			hnewnews.setSource(source);
			String imgUrl = RequestHandler.getString(request, "imgUrl");
			hnewnews.setImgUrl(imgUrl);
			

			hnewnewsService.updateHNewNews(hnewnews);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHNewNews", method = RequestMethod.POST)
	public String removeHNewNews(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HNewNews hnewnews = new HNewNews();
			Integer id = RequestHandler.getInteger(request, "id");
			hnewnews.setId(id);

			hnewnewsService.removeHNewNews(hnewnews);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHNewNews", method = RequestMethod.POST)
	public String removeAllHNewNews(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HNewNews hNewNews = new HNewNews();
						hNewNews.setId(Integer.valueOf(id));
						hnewnewsService.removeHNewNews(hNewNews);
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
