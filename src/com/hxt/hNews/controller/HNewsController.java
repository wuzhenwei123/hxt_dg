package com.hxt.hNews.controller;

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
import com.hxt.hNews.model.HNews;
import com.hxt.hNews.service.HNewsService;
/**
 * @author	zlb
 * @time	2015年09月19日 19:45:50
 */
@Controller
@RequestMapping("/hNews")
public class HNewsController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HNewsController.class); //Logger
	
	@Autowired
	private HNewsService hnewsService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hNews/hNewsIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hNews/hNewsAdd";
	}
	@RequestMapping(value = "/toShow/{id}", method = RequestMethod.GET)
	public String toShow(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HNews hnews1 = new HNews();
		hnews1.setId(id);
		HNews hnews = hnewsService.getHNews(hnews1);
		model.addAttribute("hnews", hnews);
		
		return "/hNews/show";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HNews hnews1 = new HNews();
		hnews1.setId(id);
		HNews hnews = hnewsService.getHNews(hnews1);
		model.addAttribute("hnews", hnews);
		
		return "/hNews/hNewsUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHNewsList", method = RequestMethod.GET)
	public String getHNewsList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HNews hnews = new HNews();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hnews.setId(id);
			
			String title = RequestHandler.getString(request, "title");
			hnews.setTitle(title);
			
			String content = RequestHandler.getString(request, "content");
			hnews.setContent(content);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hnews.setCreateTime(createTime);
			
			String source = RequestHandler.getString(request, "source");
			hnews.setSource(source);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hnews.setCreateId(createId);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hnews.setState(state);
			
			hnews.setSortColumn("a.createTime desc");
			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hnews.setRowStart(from);
			hnews.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hnews.setSortColumn(sortColumn);
			
			int hnewsListCount = hnewsService.getHNewsListCount(hnews);
			ResponseList<HNews> hnewsList = null;
			if ( hnewsListCount > 0 )
			{
				hnewsList = hnewsService.getHNewsList(hnews);
			} else
			{
				hnewsList = new ResponseList<HNews>();
			}
			// 设置数据总数
			hnewsList.setTotalResults(hnewsListCount);
			
			writeSuccessMsg("ok", hnewsList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHNewsBaseList", method = RequestMethod.GET)
	public String getHNewsBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HNews hnews = new HNews();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hnews.setId(id);
			
			String title = RequestHandler.getString(request, "title");
			hnews.setTitle(title);
			
			String content = RequestHandler.getString(request, "content");
			hnews.setContent(content);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hnews.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hnews.setCreateId(createId);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hnews.setState(state);
			
			List<HNews> hnewsList = hnewsService.getHNewsBaseList(hnews);
		
			writeSuccessMsg("ok", hnewsList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHNews", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HNews hnews = new HNews();
			String source = RequestHandler.getString(request, "source");
			hnews.setSource(source);
			Integer id = RequestHandler.getInteger(request, "id");
			hnews.setId(id);
			String title = RequestHandler.getString(request, "title");
			hnews.setTitle(title);
			String content = RequestHandler.getString(request, "content");
			hnews.setContent(content);
			Date createTime = RequestHandler.getDate(request, "createTime");
			hnews.setCreateTime(new Date());
			Object adminUserId = getSession(request, SessionName.ADMIN_USER_ID);
			hnews.setCreateId(Integer.valueOf(adminUserId.toString()));
			Integer state = RequestHandler.getInteger(request, "state");
			hnews.setState(state);
				
			hnewsService.insertHNews(hnews);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHNews", method = RequestMethod.POST)
	public String updateHNews(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HNews hnews = new HNews();
			String source = RequestHandler.getString(request, "source");
			hnews.setSource(source);
			
			Integer id = RequestHandler.getInteger(request, "id");
			hnews.setId(id);
			
			String title = RequestHandler.getString(request, "title");
			hnews.setTitle(title);
			
			String content = RequestHandler.getString(request, "content");
			hnews.setContent(content);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hnews.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hnews.setCreateId(createId);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hnews.setState(state);
			

			hnewsService.updateHNews(hnews);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHNews", method = RequestMethod.POST)
	public String removeHNews(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HNews hnews = new HNews();
			Integer id = RequestHandler.getInteger(request, "id");
			hnews.setId(id);

			hnewsService.removeHNews(hnews);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHNews", method = RequestMethod.POST)
	public String removeAllHNews(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HNews hNews = new HNews();
						hNews.setId(Integer.valueOf(id));
						hnewsService.removeHNews(hNews);
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
