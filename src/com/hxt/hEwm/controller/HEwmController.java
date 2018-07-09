package com.hxt.hEwm.controller;

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

import com.hxt.hEwm.model.HEwm;
import com.hxt.hEwm.service.HEwmService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2015年08月31日 19:04:33
 */
@Controller
@RequestMapping("/hEwm")
public class HEwmController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HEwmController.class); //Logger
	
	@Autowired
	private HEwmService hewmService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hEwm/hEwmIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hEwm/hEwmAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HEwm hewm1 = new HEwm();
		hewm1.setId(id);
		HEwm hewm = hewmService.getHEwm(hewm1);
		model.addAttribute("hewm", hewm);
		
		return "/hEwm/hEwmUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHEwmList", method = RequestMethod.GET)
	public String getHEwmList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HEwm hewm = new HEwm();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hewm.setId(id);
			
			String scene_id = RequestHandler.getString(request, "scene_id");
			hewm.setScene_id(scene_id);
			
			String imgUrl = RequestHandler.getString(request, "imgUrl");
			hewm.setImgUrl(imgUrl);
			
			Integer userId = RequestHandler.getInteger(request, "userId");
			hewm.setUserId(userId);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hewm.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hewm.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hewm.setRemark3(remark3);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hewm.setRowStart(from);
			hewm.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hewm.setSortColumn(sortColumn);
			
			int hewmListCount = hewmService.getHEwmListCount(hewm);
			ResponseList<HEwm> hewmList = null;
			if ( hewmListCount > 0 )
			{
				hewmList = hewmService.getHEwmList(hewm);
			} else
			{
				hewmList = new ResponseList<HEwm>();
			}
			// 设置数据总数
			hewmList.setTotalResults(hewmListCount);
			
			writeSuccessMsg("ok", hewmList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHEwmBaseList", method = RequestMethod.GET)
	public String getHEwmBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HEwm hewm = new HEwm();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hewm.setId(id);
			
			String scene_id = RequestHandler.getString(request, "scene_id");
			hewm.setScene_id(scene_id);
			
			String imgUrl = RequestHandler.getString(request, "imgUrl");
			hewm.setImgUrl(imgUrl);
			
			Integer userId = RequestHandler.getInteger(request, "userId");
			hewm.setUserId(userId);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hewm.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hewm.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hewm.setRemark3(remark3);
			
			List<HEwm> hewmList = hewmService.getHEwmBaseList(hewm);
		
			writeSuccessMsg("ok", hewmList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHEwm", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HEwm hewm = new HEwm();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hewm.setId(id);
			String scene_id = RequestHandler.getString(request, "scene_id");
			hewm.setScene_id(scene_id);
			String imgUrl = RequestHandler.getString(request, "imgUrl");
			hewm.setImgUrl(imgUrl);
			Integer userId = RequestHandler.getInteger(request, "userId");
			hewm.setUserId(userId);
			String remark1 = RequestHandler.getString(request, "remark1");
			hewm.setRemark1(remark1);
			String remark2 = RequestHandler.getString(request, "remark2");
			hewm.setRemark2(remark2);
			String remark3 = RequestHandler.getString(request, "remark3");
			hewm.setRemark3(remark3);
				
			hewmService.insertHEwm(hewm);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHEwm", method = RequestMethod.POST)
	public String updateHEwm(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HEwm hewm = new HEwm();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hewm.setId(id);
			
			String scene_id = RequestHandler.getString(request, "scene_id");
			hewm.setScene_id(scene_id);
			
			String imgUrl = RequestHandler.getString(request, "imgUrl");
			hewm.setImgUrl(imgUrl);
			
			Integer userId = RequestHandler.getInteger(request, "userId");
			hewm.setUserId(userId);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hewm.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hewm.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hewm.setRemark3(remark3);
			

			hewmService.updateHEwm(hewm);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHEwm", method = RequestMethod.POST)
	public String removeHEwm(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HEwm hewm = new HEwm();
			Integer id = RequestHandler.getInteger(request, "id");
			hewm.setId(id);

			hewmService.removeHEwm(hewm);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHEwm", method = RequestMethod.POST)
	public String removeAllHEwm(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HEwm hEwm = new HEwm();
						hEwm.setId(Integer.valueOf(id));
						hewmService.removeHEwm(hEwm);
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
