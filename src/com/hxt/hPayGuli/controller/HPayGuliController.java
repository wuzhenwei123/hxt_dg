package com.hxt.hPayGuli.controller;

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

import com.hxt.hAgent.model.HAgent;
import com.hxt.hAgent.service.HAgentService;
import com.hxt.hAgentTwo.model.HAgentTwo;
import com.hxt.hAgentTwo.service.HAgentTwoService;
import com.hxt.hPayGuli.model.HPayGuli;
import com.hxt.hPayGuli.service.HPayGuliService;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.sys.manageAdminUser.service.ManageAdminUserService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SessionName;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2017年04月08日 17:00:50
 */
@Controller
@RequestMapping("/hPayGuli")
public class HPayGuliController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HPayGuliController.class); //Logger
	
	@Autowired
	private HPayGuliService hpayguliService = null;
	@Autowired
	private HAgentTwoService hagenttwoService = null;
	@Autowired
	private HAgentService hagentService = null;
	@Autowired
	private ManageAdminUserService manageadminuserService = null;// 用户
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hPayGuli/hPayGuliIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hPayGuli/hPayGuliAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HPayGuli hpayguli1 = new HPayGuli();
		hpayguli1.setId(id);
		HPayGuli hpayguli = hpayguliService.getHPayGuli(hpayguli1);
		model.addAttribute("hpayguli", hpayguli);
		
		return "/hPayGuli/hPayGuliUpdate";
	}
	
	@RequestMapping(value = "/index1", method = RequestMethod.GET)
	public String init1(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hPayGuli1/hPayGuliIndex";
	}
	@RequestMapping(value = "/toAdd1", method = RequestMethod.GET)
	public String toAdd1(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hPayGuli1/hPayGuliAdd";
	}
	@RequestMapping(value = "/toUpdate1/{id}", method = RequestMethod.GET)
	public String toUpdate1(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HPayGuli hpayguli1 = new HPayGuli();
		hpayguli1.setId(id);
		HPayGuli hpayguli = hpayguliService.getHPayGuli(hpayguli1);
		model.addAttribute("hpayguli", hpayguli);
		
		return "/hPayGuli1/hPayGuliUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHPayGuliList", method = RequestMethod.GET)
	public String getHPayGuliList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
			
			HPayGuli hpayguli = new HPayGuli();
			
			Integer id = RequestHandler.getInteger(request, "id");
			Integer opObject = RequestHandler.getInteger(request, "opObject");
			hpayguli.setId(id);
			hpayguli.setOpObject(opObject);
			String trade_code = RequestHandler.getString(request, "trade_code");
			hpayguli.setTrade_code(trade_code);
			
			String name = RequestHandler.getString(request, "name");
			hpayguli.setName(name);
			
			Integer style = RequestHandler.getInteger(request, "style");
			hpayguli.setStyle(style);
			
			Date startTime = RequestHandler.getDate(request, "startTime");
			if(startTime!=null){
				String startTimestr = sf1.format(startTime) + " 00:00:00";
				hpayguli.setStartTime(sf.parse(startTimestr));
				hpayguli.setFlag(1);
			}
			
			
			Date endTime = RequestHandler.getDate(request, "endTime");
			if(endTime!=null){
				String endTimestr = sf1.format(endTime) + " 23:59:59";
				hpayguli.setEndTime(sf.parse(endTimestr));
				hpayguli.setFlag(2);
			}
			
			
			if(startTime!=null&&endTime!=null){
				hpayguli.setFlag(3);
			}
			
			Integer type = RequestHandler.getInteger(request, "type");
			hpayguli.setType(type);
			
			Double fee = RequestHandler.getDouble(request, "fee");
			hpayguli.setFee(fee);
			
			Double rate = RequestHandler.getDouble(request, "rate");
			hpayguli.setRate(rate);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hpayguli.setState(state);
			
			Integer is_default = RequestHandler.getInteger(request, "is_default");
			hpayguli.setIs_default(is_default);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hpayguli.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hpayguli.setCreateId(createId);
			
			Date updateTime = RequestHandler.getDate(request, "updateTime");
			hpayguli.setUpdateTime(updateTime);
			
			Integer updateId = RequestHandler.getInteger(request, "updateId");
			hpayguli.setUpdateId(updateId);
			
			Date stopTime = RequestHandler.getDate(request, "stopTime");
			hpayguli.setStopTime(stopTime);
			
			String info = RequestHandler.getString(request, "info");
			hpayguli.setInfo(info);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hpayguli.setRowStart(from);
			hpayguli.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hpayguli.setSortColumn(sortColumn);
			
			int hpayguliListCount = hpayguliService.getHPayGuliListCount(hpayguli);
			ResponseList<HPayGuli> hpayguliList = null;
			if ( hpayguliListCount > 0 )
			{
				hpayguliList = hpayguliService.getHPayGuliList(hpayguli);
			} else
			{
				hpayguliList = new ResponseList<HPayGuli>();
			}
			// 设置数据总数
			hpayguliList.setTotalResults(hpayguliListCount);
			
			writeSuccessMsg("ok", hpayguliList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHPayGuliBaseList", method = RequestMethod.GET)
	public String getHPayGuliBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HPayGuli hpayguli = new HPayGuli();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hpayguli.setId(id);
			
			String trade_code = RequestHandler.getString(request, "trade_code");
			hpayguli.setTrade_code(trade_code);
			
			String name = RequestHandler.getString(request, "name");
			hpayguli.setName(name);
			
			Integer style = RequestHandler.getInteger(request, "style");
			hpayguli.setStyle(style);
			
			Date startTime = RequestHandler.getDate(request, "startTime");
			hpayguli.setStartTime(startTime);
			
			Date endTime = RequestHandler.getDate(request, "endTime");
			hpayguli.setEndTime(endTime);
			
			Integer type = RequestHandler.getInteger(request, "type");
			hpayguli.setType(type);
			
			Double fee = RequestHandler.getDouble(request, "fee");
			hpayguli.setFee(fee);
			
			Double rate = RequestHandler.getDouble(request, "rate");
			hpayguli.setRate(rate);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hpayguli.setState(state);
			
			Integer is_default = RequestHandler.getInteger(request, "is_default");
			hpayguli.setIs_default(is_default);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hpayguli.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hpayguli.setCreateId(createId);
			
			Date updateTime = RequestHandler.getDate(request, "updateTime");
			hpayguli.setUpdateTime(updateTime);
			
			Integer updateId = RequestHandler.getInteger(request, "updateId");
			hpayguli.setUpdateId(updateId);
			
			Date stopTime = RequestHandler.getDate(request, "stopTime");
			hpayguli.setStopTime(stopTime);
			
			String info = RequestHandler.getString(request, "info");
			hpayguli.setInfo(info);
			
			List<HPayGuli> hpayguliList = hpayguliService.getHPayGuliBaseList(hpayguli);
		
			writeSuccessMsg("ok", hpayguliList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHPayGuli", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ManageAdminUser loginUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			
			HPayGuli hpayguli = new HPayGuli();
			
			Integer id = RequestHandler.getInteger(request, "id");
			Integer opObject = RequestHandler.getInteger(request, "opObject");
			hpayguli.setId(id);
			hpayguli.setOpObject(opObject);
			String trade_code = RequestHandler.getString(request, "trade_code");
			hpayguli.setTrade_code(trade_code);
			String name = RequestHandler.getString(request, "name");
			hpayguli.setName(name);
			Integer style = RequestHandler.getInteger(request, "style");
			hpayguli.setStyle(style);
			String startTime = RequestHandler.getString(request, "startTime");
			if(StringUtils.isNotBlank(startTime)){
				startTime = startTime + " 00:00:00";
				hpayguli.setStartTime(sf.parse(startTime));
			}
			
			String endTime = RequestHandler.getString(request, "endTime");
			
			if(StringUtils.isNotBlank(endTime)){
				endTime = endTime + " 23:59:59";
				hpayguli.setEndTime(sf.parse(endTime));
			}
			Integer type = RequestHandler.getInteger(request, "type");
			hpayguli.setType(type);
			Double fee = RequestHandler.getDouble(request, "fee");
			if(fee!=null){
				BigDecimal bg11xx = new BigDecimal(fee);
		        double f21xx1 = bg11xx.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		        hpayguli.setFee(f21xx1);
			}
			Double rate = RequestHandler.getDouble(request, "rate");
			if(rate!=null){
				BigDecimal bg11xx = new BigDecimal(rate);
		        double f21xx1 = bg11xx.setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
		        hpayguli.setRate(f21xx1);
			}
			Integer state = RequestHandler.getInteger(request, "state");
			hpayguli.setState(state);
			Integer is_default = RequestHandler.getInteger(request, "is_default");
			hpayguli.setIs_default(is_default);
			Date createTime = RequestHandler.getDate(request, "createTime");
			hpayguli.setCreateTime(new Date());
			Integer createId = RequestHandler.getInteger(request, "createId");
			hpayguli.setCreateId(loginUser.getAdminId());
			Date updateTime = RequestHandler.getDate(request, "updateTime");
			hpayguli.setUpdateTime(updateTime);
			hpayguli.setCreateName(loginUser.getAdminName());
			Integer updateId = RequestHandler.getInteger(request, "updateId");
			hpayguli.setUpdateId(updateId);
			Date stopTime = RequestHandler.getDate(request, "stopTime");
			hpayguli.setStopTime(stopTime);
			String info = RequestHandler.getString(request, "info");
			hpayguli.setInfo(info);
			
			HPayGuli hpayguli1 = new HPayGuli();
			hpayguli1.setStyle(style);
			hpayguli1.setOpObject(opObject);
			hpayguli1.setIs_default(1);
			hpayguli1 = hpayguliService.getHPayGuli(hpayguli1);
				
			int idss = hpayguliService.insertHPayGuli(hpayguli);
			
			if(idss>0){
				if(hpayguli1!=null&&hpayguli.getIs_default()==1){
					hpayguli1.setIs_default(0);
					hpayguliService.updateHPayGuli(hpayguli1);
					
					if(style==2){
						HAgentTwo hAgentTwo = new HAgentTwo();
						hAgentTwo.setGl_sj_id(idss);
						hAgentTwo.setGl_sj_id1(hpayguli1.getId());
						hagenttwoService.updateDefaultSJGl(hAgentTwo);
						
						HAgent hAgent = new HAgent();
						hAgent.setGl_sj_id(idss);
						hAgent.setGl_sj_id1(hpayguli1.getId());
						hagentService.updateDefaultSJGl(hAgent);
						
						ManageAdminUser manageAdminUser = new ManageAdminUser();
						manageAdminUser.setGl_sj_id(idss);
						manageAdminUser.setGl_sj_id1(hpayguli1.getId());
						manageadminuserService.updateDefaultSJGl(manageAdminUser);
					}
					
					if(style==1){
						HAgentTwo hAgentTwo1 = new HAgentTwo();
						hAgentTwo1.setGl_yl_id(idss);
						hAgentTwo1.setGl_yl_id1(hpayguli1.getId());
						hagenttwoService.updateDefaultYLGl(hAgentTwo1);
						
						HAgent hAgent = new HAgent();
						hAgent.setGl_yl_id(idss);
						hAgent.setGl_yl_id1(hpayguli1.getId());
						hagentService.updateDefaultYLGl(hAgent);
						
						ManageAdminUser manageAdminUser = new ManageAdminUser();
						manageAdminUser.setGl_yl_id(idss);
						manageAdminUser.setGl_yl_id1(hpayguli1.getId());
						manageadminuserService.updateDefaultYLGl(manageAdminUser);
					}
					
					
				}
			}
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHPayGuli", method = RequestMethod.POST)
	public String updateHPayGuli(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ManageAdminUser loginUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			
			HPayGuli hpayguli = new HPayGuli();
			
			Integer opObject = RequestHandler.getInteger(request, "opObject");
			
			Integer id = RequestHandler.getInteger(request, "id");
			hpayguli.setId(id);
			hpayguli.setOpObject(opObject);
			String trade_code = RequestHandler.getString(request, "trade_code");
			hpayguli.setTrade_code(trade_code);
			
			String name = RequestHandler.getString(request, "name");
			hpayguli.setName(name);
			
			Integer style = RequestHandler.getInteger(request, "style");
			hpayguli.setStyle(style);
			
			String startTime = RequestHandler.getString(request, "startTime");
			if(StringUtils.isNotBlank(startTime)){
				startTime = startTime + " 00:00:00";
				hpayguli.setStartTime(sf.parse(startTime));
			}
			
			String endTime = RequestHandler.getString(request, "endTime");
			
			if(StringUtils.isNotBlank(endTime)){
				endTime = endTime + " 23:59:59";
				hpayguli.setEndTime(sf.parse(endTime));
			}
			Integer type = RequestHandler.getInteger(request, "type");
			hpayguli.setType(type);
			Double fee = RequestHandler.getDouble(request, "fee");
			if(fee!=null){
				BigDecimal bg11xx = new BigDecimal(fee);
		        double f21xx1 = bg11xx.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		        hpayguli.setFee(f21xx1);
			}
			Double rate = RequestHandler.getDouble(request, "rate");
			if(rate!=null){
				BigDecimal bg11xx = new BigDecimal(rate);
		        double f21xx1 = bg11xx.setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
		        hpayguli.setRate(f21xx1);
			}
			
			Integer state = RequestHandler.getInteger(request, "state");
			hpayguli.setState(state);
			
			Integer is_default = RequestHandler.getInteger(request, "is_default");
			hpayguli.setIs_default(is_default);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hpayguli.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hpayguli.setCreateId(createId);
			
			Date updateTime = RequestHandler.getDate(request, "updateTime");
			hpayguli.setUpdateTime(new Date());
			
			Integer updateId = RequestHandler.getInteger(request, "updateId");
			hpayguli.setUpdateId(loginUser.getAdminId());
			hpayguli.setUpdateName(loginUser.getAdminName());
			
			Date stopTime = RequestHandler.getDate(request, "stopTime");
			if(state==0){
				hpayguli.setStopTime(new Date());
			}
			
			
			String info = RequestHandler.getString(request, "info");
			hpayguli.setInfo(info);
			
			HPayGuli hpayguli1 = new HPayGuli();
			hpayguli1.setStyle(style);
			hpayguli1.setOpObject(opObject);
			hpayguli1.setIs_default(1);
			hpayguli1 = hpayguliService.getHPayGuli(hpayguli1);
			
			int idss = hpayguliService.updateHPayGuli1(hpayguli);
			if(idss>0){
				if(hpayguli1!=null&&hpayguli.getIs_default()==1&&!hpayguli1.getId().equals(id)){
					hpayguli1.setIs_default(0);
					hpayguliService.updateHPayGuli(hpayguli1);
					
					if(style==2){
						HAgentTwo hAgentTwo = new HAgentTwo();
						hAgentTwo.setGl_sj_id(id);
						hAgentTwo.setGl_sj_id1(hpayguli1.getId());
						hagenttwoService.updateDefaultSJGl(hAgentTwo);
						
						HAgent hAgent = new HAgent();
						hAgent.setGl_sj_id(id);
						hAgent.setGl_sj_id1(hpayguli1.getId());
						hagentService.updateDefaultSJGl(hAgent);
						
						ManageAdminUser manageAdminUser = new ManageAdminUser();
						manageAdminUser.setGl_sj_id(id);
						manageAdminUser.setGl_sj_id1(hpayguli1.getId());
						manageadminuserService.updateDefaultSJGl(manageAdminUser);
					}
					
					if(style==1){
						HAgentTwo hAgentTwo1 = new HAgentTwo();
						hAgentTwo1.setGl_yl_id(id);
						hAgentTwo1.setGl_yl_id1(hpayguli1.getId());
						hagenttwoService.updateDefaultYLGl(hAgentTwo1);
						
						HAgent hAgent = new HAgent();
						hAgent.setGl_yl_id(id);
						hAgent.setGl_yl_id1(hpayguli1.getId());
						hagentService.updateDefaultYLGl(hAgent);
						
						ManageAdminUser manageAdminUser = new ManageAdminUser();
						manageAdminUser.setGl_yl_id(id);
						manageAdminUser.setGl_yl_id1(hpayguli1.getId());
						manageadminuserService.updateDefaultYLGl(manageAdminUser);
					}
					
					
				}
			}
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHPayGuli", method = RequestMethod.POST)
	public String removeHPayGuli(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HPayGuli hpayguli = new HPayGuli();
			Integer id = RequestHandler.getInteger(request, "id");
			hpayguli.setId(id);

			hpayguliService.removeHPayGuli(hpayguli);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHPayGuli", method = RequestMethod.POST)
	public String removeAllHPayGuli(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HPayGuli hPayGuli = new HPayGuli();
						hPayGuli.setId(Integer.valueOf(id));
						hpayguliService.removeHPayGuli(hPayGuli);
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
			HPayGuli hpayguli = new HPayGuli();
			Integer id = RequestHandler.getInteger(request, "id");
			Integer style = RequestHandler.getInteger(request, "style");
			Integer opObject = RequestHandler.getInteger(request, "opObject");
			hpayguli.setId(id);
			hpayguli.setStyle(style);
			hpayguli.setIs_default(1);
			hpayguli.setOpObject(opObject);
			int count = hpayguliService.checkDefault(hpayguli);
			if(count==0){
				writeSuccessMsg("ok", null, response);
			}else{
				writeErrorMsg("exit", null, response);
			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg("error", null, response);
		}
		return null;
	}
}
