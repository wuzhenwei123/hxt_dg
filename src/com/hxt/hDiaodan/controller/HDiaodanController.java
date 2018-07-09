package com.hxt.hDiaodan.controller;

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

import com.hxt.hDiaodan.model.HDiaodan;
import com.hxt.hDiaodan.service.HDiaodanService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2016年09月22日 15:07:03
 */
@Controller
@RequestMapping("/hDiaodan")
public class HDiaodanController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HDiaodanController.class); //Logger
	
	@Autowired
	private HDiaodanService hdiaodanService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hDiaodan/hDiaodanIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hDiaodan/hDiaodanAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HDiaodan hdiaodan1 = new HDiaodan();
		hdiaodan1.setId(id);
		HDiaodan hdiaodan = hdiaodanService.getHDiaodan(hdiaodan1);
		model.addAttribute("hdiaodan", hdiaodan);
		
		return "/hDiaodan/hDiaodanUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHDiaodanList", method = RequestMethod.GET)
	public String getHDiaodanList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HDiaodan hdiaodan = new HDiaodan();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hdiaodan.setId(id);
			
			String electricNum = RequestHandler.getString(request, "electricNum");
			hdiaodan.setElectricNum(electricNum);
			
			Integer style = RequestHandler.getInteger(request, "style");
			hdiaodan.setStyle(style);
			
			String content = RequestHandler.getString(request, "content");
			hdiaodan.setContent(content);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hdiaodan.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hdiaodan.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hdiaodan.setRemark3(remark3);
			
			String remark4 = RequestHandler.getString(request, "remark4");
			hdiaodan.setRemark4(remark4);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hdiaodan.setCreateTime(createTime);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hdiaodan.setRowStart(from);
			hdiaodan.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hdiaodan.setSortColumn(sortColumn);
			
			int hdiaodanListCount = hdiaodanService.getHDiaodanListCount(hdiaodan);
			ResponseList<HDiaodan> hdiaodanList = null;
			if ( hdiaodanListCount > 0 )
			{
				hdiaodanList = hdiaodanService.getHDiaodanList(hdiaodan);
			} else
			{
				hdiaodanList = new ResponseList<HDiaodan>();
			}
			// 设置数据总数
			hdiaodanList.setTotalResults(hdiaodanListCount);
			
			writeSuccessMsg("ok", hdiaodanList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHDiaodanBaseList", method = RequestMethod.GET)
	public String getHDiaodanBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HDiaodan hdiaodan = new HDiaodan();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hdiaodan.setId(id);
			
			String electricNum = RequestHandler.getString(request, "electricNum");
			hdiaodan.setElectricNum(electricNum);
			
			Integer style = RequestHandler.getInteger(request, "style");
			hdiaodan.setStyle(style);
			
			String content = RequestHandler.getString(request, "content");
			hdiaodan.setContent(content);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hdiaodan.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hdiaodan.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hdiaodan.setRemark3(remark3);
			
			String remark4 = RequestHandler.getString(request, "remark4");
			hdiaodan.setRemark4(remark4);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hdiaodan.setCreateTime(createTime);
			
			List<HDiaodan> hdiaodanList = hdiaodanService.getHDiaodanBaseList(hdiaodan);
		
			writeSuccessMsg("ok", hdiaodanList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHDiaodan", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HDiaodan hdiaodan = new HDiaodan();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hdiaodan.setId(id);
			String electricNum = RequestHandler.getString(request, "electricNum");
			hdiaodan.setElectricNum(electricNum);
			Integer style = RequestHandler.getInteger(request, "style");
			hdiaodan.setStyle(style);
			String content = RequestHandler.getString(request, "content");
			hdiaodan.setContent(content);
			String remark1 = RequestHandler.getString(request, "remark1");
			hdiaodan.setRemark1(remark1);
			String remark2 = RequestHandler.getString(request, "remark2");
			hdiaodan.setRemark2(remark2);
			String remark3 = RequestHandler.getString(request, "remark3");
			hdiaodan.setRemark3(remark3);
			String remark4 = RequestHandler.getString(request, "remark4");
			hdiaodan.setRemark4(remark4);
			Date createTime = RequestHandler.getDate(request, "createTime");
			hdiaodan.setCreateTime(new Date());
				
			hdiaodanService.insertHDiaodan(hdiaodan);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHDiaodan", method = RequestMethod.POST)
	public String updateHDiaodan(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HDiaodan hdiaodan = new HDiaodan();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hdiaodan.setId(id);
			
			String electricNum = RequestHandler.getString(request, "electricNum");
			hdiaodan.setElectricNum(electricNum);
			
			Integer style = RequestHandler.getInteger(request, "style");
			hdiaodan.setStyle(style);
			
			String content = RequestHandler.getString(request, "content");
			hdiaodan.setContent(content);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hdiaodan.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hdiaodan.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hdiaodan.setRemark3(remark3);
			
			String remark4 = RequestHandler.getString(request, "remark4");
			hdiaodan.setRemark4(remark4);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hdiaodan.setCreateTime(createTime);
			

			hdiaodanService.updateHDiaodan(hdiaodan);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHDiaodan", method = RequestMethod.POST)
	public String removeHDiaodan(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HDiaodan hdiaodan = new HDiaodan();
			Integer id = RequestHandler.getInteger(request, "id");
			hdiaodan.setId(id);

			hdiaodanService.removeHDiaodan(hdiaodan);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHDiaodan", method = RequestMethod.POST)
	public String removeAllHDiaodan(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HDiaodan hDiaodan = new HDiaodan();
						hDiaodan.setId(Integer.valueOf(id));
						hdiaodanService.removeHDiaodan(hDiaodan);
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
