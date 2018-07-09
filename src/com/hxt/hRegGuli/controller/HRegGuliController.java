package com.hxt.hRegGuli.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
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

import com.hxt.hAgentTwo.model.HAgentTwo;
import com.hxt.hAgentTwo.service.HAgentTwoService;
import com.hxt.hRegGuli.model.HRegGuli;
import com.hxt.hRegGuli.service.HRegGuliService;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SessionName;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2017年04月06日 17:50:28
 */
@Controller
@RequestMapping("/hRegGuli")
public class HRegGuliController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HRegGuliController.class); //Logger
	
	@Autowired
	private HRegGuliService hregguliService = null;
	@Autowired
	private HAgentTwoService hagenttwoService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hRegGuli/hRegGuliIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hRegGuli/hRegGuliAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HRegGuli hregguli1 = new HRegGuli();
		hregguli1.setId(id);
		HRegGuli hregguli = hregguliService.getHRegGuli(hregguli1);
		model.addAttribute("hregguli", hregguli);
		
		return "/hRegGuli/hRegGuliUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHRegGuliList", method = RequestMethod.GET)
	public String getHRegGuliList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
			HRegGuli hregguli = new HRegGuli();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hregguli.setId(id);
			
			String name = RequestHandler.getString(request, "name");
			hregguli.setName(name);
			
			Date startTime = RequestHandler.getDate(request, "startTime");
			
			if(startTime!=null){
				String startTimestr = sf1.format(startTime) + " 00:00:00";
				hregguli.setStartTime(sf.parse(startTimestr));
				hregguli.setFlag(1);
			}
			
			
			Date endTime = RequestHandler.getDate(request, "endTime");
			if(endTime!=null){
				String endTimestr = sf1.format(endTime) + " 23:59:59";
				hregguli.setEndTime(sf.parse(endTimestr));
				hregguli.setFlag(2);
			}
			
			
			if(startTime!=null&&endTime!=null){
				hregguli.setFlag(3);
			}
			
			Double fee = RequestHandler.getDouble(request, "fee");
			hregguli.setFee(fee);
			
			Integer isDefault = RequestHandler.getInteger(request, "isDefault");
			hregguli.setIsDefault(isDefault);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hregguli.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			
			hregguli.setCreateTime(createTime);
			
			Date updateTime = RequestHandler.getDate(request, "updateTime");
			hregguli.setUpdateTime(updateTime);
			
			Date stopTime = RequestHandler.getDate(request, "stopTime");
			hregguli.setStopTime(stopTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hregguli.setCreateId(createId);
			
			Integer updateId = RequestHandler.getInteger(request, "updateId");
			hregguli.setUpdateId(updateId);
			
			String info = RequestHandler.getString(request, "info");
			hregguli.setInfo(info);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hregguli.setRowStart(from);
			hregguli.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hregguli.setSortColumn(sortColumn);
			
			int hregguliListCount = hregguliService.getHRegGuliListCount(hregguli);
			ResponseList<HRegGuli> hregguliList = null;
			if ( hregguliListCount > 0 )
			{
				hregguliList = hregguliService.getHRegGuliList(hregguli);
			} else
			{
				hregguliList = new ResponseList<HRegGuli>();
			}
			// 设置数据总数
			hregguliList.setTotalResults(hregguliListCount);
			
			writeSuccessMsg("ok", hregguliList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHRegGuliBaseList", method = RequestMethod.GET)
	public String getHRegGuliBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HRegGuli hregguli = new HRegGuli();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hregguli.setId(id);
			
			String name = RequestHandler.getString(request, "name");
			hregguli.setName(name);
			
			Date startTime = RequestHandler.getDate(request, "startTime");
			hregguli.setStartTime(startTime);
			
			Date endTime = RequestHandler.getDate(request, "endTime");
			hregguli.setEndTime(endTime);
			
			Double fee = RequestHandler.getDouble(request, "fee");
			hregguli.setFee(fee);
			
			Integer isDefault = RequestHandler.getInteger(request, "isDefault");
			hregguli.setIsDefault(isDefault);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hregguli.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hregguli.setCreateTime(createTime);
			
			Date updateTime = RequestHandler.getDate(request, "updateTime");
			hregguli.setUpdateTime(updateTime);
			
			Date stopTime = RequestHandler.getDate(request, "stopTime");
			hregguli.setStopTime(stopTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hregguli.setCreateId(createId);
			
			Integer updateId = RequestHandler.getInteger(request, "updateId");
			hregguli.setUpdateId(updateId);
			
			String info = RequestHandler.getString(request, "info");
			hregguli.setInfo(info);
			
			List<HRegGuli> hregguliList = hregguliService.getHRegGuliBaseList(hregguli);
		
			writeSuccessMsg("ok", hregguliList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHRegGuli", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ManageAdminUser loginUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			
			HRegGuli hregguli = new HRegGuli();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hregguli.setId(id);
			String name = RequestHandler.getString(request, "name");
			hregguli.setName(name);
			String startTime = RequestHandler.getString(request, "startTime");
			if(StringUtils.isNotBlank(startTime)){
				startTime = startTime + " 00:00:00";
				hregguli.setStartTime(sf.parse(startTime));
			}
			
			String endTime = RequestHandler.getString(request, "endTime");
			
			if(StringUtils.isNotBlank(endTime)){
				endTime = endTime + " 23:59:59";
				hregguli.setEndTime(sf.parse(endTime));
			}
			Double fee = RequestHandler.getDouble(request, "fee");
			
			BigDecimal bg11xx = new BigDecimal(fee);
	        double f21xx1 = bg11xx.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			
			hregguli.setFee(f21xx1);
			Integer isDefault = RequestHandler.getInteger(request, "isDefault");
			hregguli.setIsDefault(isDefault);
			Integer state = RequestHandler.getInteger(request, "state");
			hregguli.setState(state);
			Date createTime = RequestHandler.getDate(request, "createTime");
			hregguli.setCreateTime(new Date());
			Date updateTime = RequestHandler.getDate(request, "updateTime");
			hregguli.setUpdateTime(updateTime);
			Date stopTime = RequestHandler.getDate(request, "stopTime");
			hregguli.setStopTime(stopTime);
			Integer createId = RequestHandler.getInteger(request, "createId");
			hregguli.setCreateId(loginUser.getAdminId());
			Integer updateId = RequestHandler.getInteger(request, "updateId");
			hregguli.setUpdateId(updateId);
			String info = RequestHandler.getString(request, "info");
			hregguli.setInfo(info);
			hregguli.setCreateName(loginUser.getAdminName());
			
			HRegGuli hregguli1 = new HRegGuli();
			hregguli1.setIsDefault(1);
			hregguli1 = hregguliService.getHRegGuli(hregguli1);
			
			int idss = hregguliService.insertHRegGuli(hregguli);
			
			if(idss>0){
				
				
				if(hregguli1!=null){
					if(hregguli.getIsDefault()==1){
						hregguli1.setIsDefault(0);
						hregguliService.updateHRegGuli(hregguli1);
						
						HAgentTwo hAgentTwo = new HAgentTwo();
						hAgentTwo.setReg_gl_id(idss);
						hAgentTwo.setReg_gl_id1(hregguli1.getId());
						hagenttwoService.updateDefaultRegGl(hAgentTwo);
						
					}
				}
			}
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHRegGuli", method = RequestMethod.POST)
	public String updateHRegGuli(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			ManageAdminUser loginUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			HRegGuli hregguli = new HRegGuli();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hregguli.setId(id);
			
			String name = RequestHandler.getString(request, "name");
			hregguli.setName(name);
			
			String startTime = RequestHandler.getString(request, "startTime");
			if(StringUtils.isNotBlank(startTime)){
				startTime = startTime + " 00:00:00";
				hregguli.setStartTime(sf.parse(startTime));
			}
			
			String endTime = RequestHandler.getString(request, "endTime");
			
			if(StringUtils.isNotBlank(endTime)){
				endTime = endTime + " 23:59:59";
				hregguli.setEndTime(sf.parse(endTime));
			}
			
			Double fee = RequestHandler.getDouble(request, "fee");
			
			BigDecimal bg11xx = new BigDecimal(fee);
	        double f21xx1 = bg11xx.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			
			hregguli.setFee(f21xx1);
			
			Integer isDefault = RequestHandler.getInteger(request, "isDefault");
			hregguli.setIsDefault(isDefault);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hregguli.setState(state);
			
			if(state==0){
				hregguli.setStopTime(new Date());
			}
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hregguli.setCreateTime(createTime);
			
			Date updateTime = RequestHandler.getDate(request, "updateTime");
			hregguli.setUpdateTime(new Date());
			
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hregguli.setCreateId(createId);
			
			Integer updateId = RequestHandler.getInteger(request, "updateId");
			hregguli.setUpdateId(loginUser.getAdminId());
			hregguli.setUpdateName(loginUser.getAdminName());
			
			String info = RequestHandler.getString(request, "info");
			hregguli.setInfo(info);
			
			HRegGuli hregguli1 = new HRegGuli();
			hregguli1.setIsDefault(1);
			hregguli1 = hregguliService.getHRegGuli(hregguli1);

			int idss = hregguliService.updateHRegGuli(hregguli);
			if(idss>0){
				
				
				if(hregguli1!=null){
					if(!hregguli1.getId().equals(id)&&hregguli.getIsDefault()==1){
						hregguli1.setIsDefault(0);
						hregguliService.updateHRegGuli(hregguli1);
						
						HAgentTwo hAgentTwo = new HAgentTwo();
						hAgentTwo.setReg_gl_id(id);
						hAgentTwo.setReg_gl_id1(hregguli1.getId());
						hagenttwoService.updateDefaultRegGl(hAgentTwo);
						
					}
				}
			}
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHRegGuli", method = RequestMethod.POST)
	public String removeHRegGuli(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HRegGuli hregguli = new HRegGuli();
			Integer id = RequestHandler.getInteger(request, "id");
			hregguli.setId(id);

			hregguliService.removeHRegGuli(hregguli);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHRegGuli", method = RequestMethod.POST)
	public String removeAllHRegGuli(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HRegGuli hRegGuli = new HRegGuli();
						hRegGuli.setId(Integer.valueOf(id));
						hregguliService.removeHRegGuli(hRegGuli);
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
	
	@RequestMapping(value = "/checkDefault", method = RequestMethod.POST)
	public String checkDefault(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			HRegGuli hregguli = new HRegGuli();
			Integer id = RequestHandler.getInteger(request, "id");
			hregguli.setId(id);
			hregguli.setIsDefault(1);
			int count = hregguliService.checkDefault(hregguli);
			if(count==0){
				writeSuccessMsg("ok", null, response);
			}else{
				writeErrorMsg("exit", null, response);
			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
