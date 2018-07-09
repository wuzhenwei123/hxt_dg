package com.hxt.hVerificate.controller;

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

import com.hxt.hVerificate.model.HVerificate;
import com.hxt.hVerificate.service.HVerificateService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2015年09月30日 14:11:20
 */
@Controller
@RequestMapping("/hVerificate")
public class HVerificateController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HVerificateController.class); //Logger
	
	@Autowired
	private HVerificateService hverificateService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hVerificate/hVerificateIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hVerificate/hVerificateAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HVerificate hverificate1 = new HVerificate();
		hverificate1.setId(id);
		HVerificate hverificate = hverificateService.getHVerificate(hverificate1);
		model.addAttribute("hverificate", hverificate);
		
		return "/hVerificate/hVerificateUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHVerificateList", method = RequestMethod.GET)
	public String getHVerificateList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HVerificate hverificate = new HVerificate();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hverificate.setId(id);
			
			String openId = RequestHandler.getString(request, "openId");
			hverificate.setOpenId(openId);
			
			String agentOpenId = RequestHandler.getString(request, "agentOpenId");
			hverificate.setAgentOpenId(agentOpenId);
			
			String phone = RequestHandler.getString(request, "phone");
			hverificate.setPhone(phone);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hverificate.setState(state);
			
			Integer level = RequestHandler.getInteger(request, "level");
			hverificate.setLevel(level);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hverificate.setCreateTime(createTime);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hverificate.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hverificate.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hverificate.setRemark3(remark3);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hverificate.setRowStart(from);
			hverificate.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hverificate.setSortColumn(sortColumn);
			
			int hverificateListCount = hverificateService.getHVerificateListCount(hverificate);
			ResponseList<HVerificate> hverificateList = null;
			if ( hverificateListCount > 0 )
			{
				hverificateList = hverificateService.getHVerificateList(hverificate);
			} else
			{
				hverificateList = new ResponseList<HVerificate>();
			}
			// 设置数据总数
			hverificateList.setTotalResults(hverificateListCount);
			
			writeSuccessMsg("ok", hverificateList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHVerificateBaseList", method = RequestMethod.GET)
	public String getHVerificateBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HVerificate hverificate = new HVerificate();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hverificate.setId(id);
			
			String openId = RequestHandler.getString(request, "openId");
			hverificate.setOpenId(openId);
			
			String agentOpenId = RequestHandler.getString(request, "agentOpenId");
			hverificate.setAgentOpenId(agentOpenId);
			
			String phone = RequestHandler.getString(request, "phone");
			hverificate.setPhone(phone);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hverificate.setState(state);
			
			Integer level = RequestHandler.getInteger(request, "level");
			hverificate.setLevel(level);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hverificate.setCreateTime(createTime);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hverificate.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hverificate.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hverificate.setRemark3(remark3);
			
			List<HVerificate> hverificateList = hverificateService.getHVerificateBaseList(hverificate);
		
			writeSuccessMsg("ok", hverificateList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHVerificate", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HVerificate hverificate = new HVerificate();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hverificate.setId(id);
			String openId = RequestHandler.getString(request, "openId");
			hverificate.setOpenId(openId);
			String agentOpenId = RequestHandler.getString(request, "agentOpenId");
			hverificate.setAgentOpenId(agentOpenId);
			String phone = RequestHandler.getString(request, "phone");
			hverificate.setPhone(phone);
			Integer state = RequestHandler.getInteger(request, "state");
			hverificate.setState(state);
			Integer level = RequestHandler.getInteger(request, "level");
			hverificate.setLevel(level);
			Date createTime = RequestHandler.getDate(request, "createTime");
			hverificate.setCreateTime(new Date());
			String remark1 = RequestHandler.getString(request, "remark1");
			hverificate.setRemark1(remark1);
			String remark2 = RequestHandler.getString(request, "remark2");
			hverificate.setRemark2(remark2);
			String remark3 = RequestHandler.getString(request, "remark3");
			hverificate.setRemark3(remark3);
				
			hverificateService.insertHVerificate(hverificate);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHVerificate", method = RequestMethod.POST)
	public String updateHVerificate(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HVerificate hverificate = new HVerificate();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hverificate.setId(id);
			
			String openId = RequestHandler.getString(request, "openId");
			hverificate.setOpenId(openId);
			
			String agentOpenId = RequestHandler.getString(request, "agentOpenId");
			hverificate.setAgentOpenId(agentOpenId);
			
			String phone = RequestHandler.getString(request, "phone");
			hverificate.setPhone(phone);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hverificate.setState(state);
			
			Integer level = RequestHandler.getInteger(request, "level");
			hverificate.setLevel(level);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hverificate.setCreateTime(createTime);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hverificate.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hverificate.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hverificate.setRemark3(remark3);
			

			hverificateService.updateHVerificate(hverificate);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHVerificate", method = RequestMethod.POST)
	public String removeHVerificate(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HVerificate hverificate = new HVerificate();
			Integer id = RequestHandler.getInteger(request, "id");
			hverificate.setId(id);

			hverificateService.removeHVerificate(hverificate);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHVerificate", method = RequestMethod.POST)
	public String removeAllHVerificate(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HVerificate hVerificate = new HVerificate();
						hVerificate.setId(Integer.valueOf(id));
						hverificateService.removeHVerificate(hVerificate);
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
