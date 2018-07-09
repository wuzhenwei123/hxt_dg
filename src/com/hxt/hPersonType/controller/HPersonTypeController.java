package com.hxt.hPersonType.controller;

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

import com.hxt.hPersonType.model.HPersonType;
import com.hxt.hPersonType.service.HPersonTypeService;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SessionName;
import com.base.controller.BaseController;
/**
 * @author	zlb
 * @time	2016年08月06日 14:03:07
 */
@Controller
@RequestMapping("/hPersonType")
public class HPersonTypeController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HPersonTypeController.class); //Logger
	
	@Autowired
	private HPersonTypeService hpersontypeService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hPersonType/hPersonTypeIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hPersonType/hPersonTypeAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HPersonType hpersontype1 = new HPersonType();
		hpersontype1.setId(id);
		HPersonType hpersontype = hpersontypeService.getHPersonType(hpersontype1);
		model.addAttribute("hpersontype", hpersontype);
		
		return "/hPersonType/hPersonTypeUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHPersonTypeList", method = RequestMethod.GET)
	public String getHPersonTypeList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HPersonType hpersontype = new HPersonType();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hpersontype.setId(id);
			
			String name = RequestHandler.getString(request, "name");
			hpersontype.setName(name);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hpersontype.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hpersontype.setCreateTime(createTime);
			
			Integer adminId = RequestHandler.getInteger(request, "adminId");
			hpersontype.setAdminId(adminId);
			
			Date lastTime = RequestHandler.getDate(request, "lastTime");
			hpersontype.setLastTime(lastTime);
			
			Integer lastAdminId = RequestHandler.getInteger(request, "lastAdminId");
			hpersontype.setLastAdminId(lastAdminId);
			
			String remark = RequestHandler.getString(request, "remark");
			hpersontype.setRemark(remark);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hpersontype.setRowStart(from);
			hpersontype.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hpersontype.setSortColumn(sortColumn);
			
			int hpersontypeListCount = hpersontypeService.getHPersonTypeListCount(hpersontype);
			ResponseList<HPersonType> hpersontypeList = null;
			if ( hpersontypeListCount > 0 )
			{
				hpersontypeList = hpersontypeService.getHPersonTypeList(hpersontype);
			} else
			{
				hpersontypeList = new ResponseList<HPersonType>();
			}
			// 设置数据总数
			hpersontypeList.setTotalResults(hpersontypeListCount);
			
			writeSuccessMsg("ok", hpersontypeList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHPersonTypeBaseList", method = RequestMethod.GET)
	public String getHPersonTypeBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HPersonType hpersontype = new HPersonType();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hpersontype.setId(id);
			
			String name = RequestHandler.getString(request, "name");
			hpersontype.setName(name);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hpersontype.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hpersontype.setCreateTime(createTime);
			
			Integer adminId = RequestHandler.getInteger(request, "adminId");
			hpersontype.setAdminId(adminId);
			
			Date lastTime = RequestHandler.getDate(request, "lastTime");
			hpersontype.setLastTime(lastTime);
			
			Integer lastAdminId = RequestHandler.getInteger(request, "lastAdminId");
			hpersontype.setLastAdminId(lastAdminId);
			
			String remark = RequestHandler.getString(request, "remark");
			hpersontype.setRemark(remark);
			
			List<HPersonType> hpersontypeList = hpersontypeService.getHPersonTypeBaseList(hpersontype);
		
			writeSuccessMsg("ok", hpersontypeList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHPersonType", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HPersonType hpersontype = new HPersonType();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hpersontype.setId(id);
			String name = RequestHandler.getString(request, "name");
			hpersontype.setName(name);
			Integer state = RequestHandler.getInteger(request, "state");
			hpersontype.setState(state);
			
			hpersontype.setCreateTime(new Date());
			Integer adminId = Integer.valueOf(getSession(request, SessionName.ADMIN_USER_ID).toString());
			hpersontype.setAdminId(adminId);
			hpersontype.setLastTime(new Date());
			hpersontype.setLastAdminId(adminId);
			
			String adminname = getSession(request, SessionName.ADMIN_USER_NAME).toString();
			hpersontype.setAdminName(adminname);
			hpersontype.setLastAdminName(adminname);
			
			String remark = RequestHandler.getString(request, "remark");
			hpersontype.setRemark(remark);
				
			hpersontypeService.insertHPersonType(hpersontype);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHPersonType", method = RequestMethod.POST)
	public String updateHPersonType(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HPersonType hpersontype = new HPersonType();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hpersontype.setId(id);
			
			String name = RequestHandler.getString(request, "name");
			hpersontype.setName(name);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hpersontype.setState(state);
			
			Integer adminId = Integer.valueOf(getSession(request, SessionName.ADMIN_USER_ID).toString());
			hpersontype.setLastTime(new Date());
			hpersontype.setLastAdminId(adminId);
			String adminname = getSession(request, SessionName.ADMIN_USER_NAME).toString();
			hpersontype.setLastAdminName(adminname);
			
			String remark = RequestHandler.getString(request, "remark");
			hpersontype.setRemark(remark);
			

			hpersontypeService.updateHPersonType(hpersontype);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHPersonType", method = RequestMethod.POST)
	public String removeHPersonType(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HPersonType hpersontype = new HPersonType();
			Integer id = RequestHandler.getInteger(request, "id");
			hpersontype.setId(id);

			hpersontypeService.removeHPersonType(hpersontype);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHPersonType", method = RequestMethod.POST)
	public String removeAllHPersonType(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HPersonType hPersonType = new HPersonType();
						hPersonType.setId(Integer.valueOf(id));
						hpersontypeService.removeHPersonType(hPersonType);
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
