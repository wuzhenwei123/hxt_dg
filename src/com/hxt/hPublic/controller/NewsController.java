package com.hxt.hPublic.controller;

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
import com.hxt.hNewNews.model.HNewNews;
import com.hxt.hNewNews.service.HNewNewsService;
import com.hxt.hProvince.model.HProvince;
import com.hxt.hProvince.service.HProvinceService;
/**
 * @author	zhangyiyang
 * @time	2016年08月02日 22:11:34
 */
@Controller
@RequestMapping("/public")
public class NewsController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HProvinceController.class); //Logger
	@Autowired
	private HNewNewsService hnewnewsService = null;
	/*****************页面中转*********************/
	@RequestMapping(value = "/newslist", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model,Integer p)
	{
		if( p == null){
			p = 1;
		}
		
		HNewNews hnewnews = new HNewNews();
		hnewnews.setState(1);
		// 分页开始
		Integer rowCount = 10;
		
		int from = RequestHandler.getFromByPage(p, rowCount);
		hnewnews.setRowStart(from);
		hnewnews.setRowCount(rowCount);
		// 分页结束
		// 排序
		String sortColumn = RequestHandler.getString(request, "sortColumn");
		hnewnews.setSortColumn("a.sortId desc ,a.createTime desc ");
		
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
		
		model.addAttribute("newsList", hnewnewsList);
		model.addAttribute("newsCount", hnewnewsListCount);
		model.addAttribute("p", p);
		model.addAttribute("nav", "news");
		return "/public/newslist";
	}
	@RequestMapping(value = "/news/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		HNewNews hNewNews = new HNewNews();
		hNewNews.setId(id);
		HNewNews hNewNews1 = hnewnewsService.getHNewNews(hNewNews);
		
		model.addAttribute("news", hNewNews1);
		model.addAttribute("nav", "news");
		return "/public/news";
	}

}
