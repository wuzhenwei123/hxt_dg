package com.hxt.hPublic.controller;

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
@RequestMapping("/public")
public class NoticeController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HNewNoticeController.class); //Logger
	
	@Autowired
	private HNewNoticeService hnewnoticeService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/notice/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HNewNotice hnewnotice1 = new HNewNotice();
		hnewnotice1.setId(id);
		HNewNotice hnewnotice = hnewnoticeService.getHNewNotice(hnewnotice1);
		model.addAttribute("notice", hnewnotice);
		
		return "/public/notice";
	}
	@RequestMapping(value = "/noticelist", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model,Integer p)
	{
		if( p == null){
			p = 1;
		}
		
		HNewNotice hnewnotice = new HNewNotice();
		hnewnotice.setState(1);
		// 分页开始
		Integer rowCount = 10;
		
		int from = RequestHandler.getFromByPage(p, rowCount);
		hnewnotice.setRowStart(from);
		hnewnotice.setRowCount(rowCount);
		// 分页结束
		// 排序
		String sortColumn = RequestHandler.getString(request, "sortColumn");
		hnewnotice.setSortColumn(sortColumn);
		
		hnewnotice.setSortColumn("a.sortId desc ,a.createTime desc ");
		
		int hnewnewsListCount = hnewnoticeService.getHNewNoticeListCount(hnewnotice);
		ResponseList<HNewNotice> hnewnewsList = null;
		if ( hnewnewsListCount > 0 )
		{
			hnewnewsList = hnewnoticeService.getHNewNoticeList(hnewnotice);
		} else
		{
			hnewnewsList = new ResponseList<HNewNotice>();
		}
		// 设置数据总数
		hnewnewsList.setTotalResults(hnewnewsListCount);
		
		model.addAttribute("newsList", hnewnewsList);
		model.addAttribute("newsCount", hnewnewsListCount);
		model.addAttribute("p", p);
		model.addAttribute("nav", "notice");
		return "/public/noticelist";
	}
}
