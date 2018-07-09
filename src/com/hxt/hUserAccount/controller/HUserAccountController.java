package com.hxt.hUserAccount.controller;

import java.math.BigDecimal;
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

import com.hxt.hUserAccount.model.HUserAccount;
import com.hxt.hUserAccount.service.HUserAccountService;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.base.utils.FileUploadConstants;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SessionName;
import com.base.controller.BaseController;
/**
 * @author	zlb
 * @time	2016年08月16日 10:58:51
 */
@Controller
@RequestMapping("/hUserAccount")
public class HUserAccountController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HUserAccountController.class); //Logger
	
	@Autowired
	private HUserAccountService huseraccountService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hUserAccount/hUserAccountIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hUserAccount/hUserAccountAdd";
	}
	@RequestMapping(value = "/showDetail", method = RequestMethod.GET)
	public String showDetail(HttpServletRequest request, HttpServletResponse response, Model model){
		String id = RequestHandler.getString(request, "id");
		model.addAttribute("id", id);
		return "/hUserAccount/showDetail";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HUserAccount huseraccount1 = new HUserAccount();
		huseraccount1.setId(id);
		HUserAccount huseraccount = huseraccountService.getHUserAccount(huseraccount1);
		model.addAttribute("huseraccount", huseraccount);
		
		return "/hUserAccount/hUserAccountUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHUserAccountList", method = RequestMethod.GET)
	public String getHUserAccountList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HUserAccount huseraccount = new HUserAccount();
			
			Integer id = RequestHandler.getInteger(request, "id");
			huseraccount.setId(id);
			
			String openId = RequestHandler.getString(request, "openId");
			huseraccount.setOpenId(openId);
			
			BigDecimal totalFee = RequestHandler.getBigDecimal(request, "totalFee");
			huseraccount.setTotalFee(totalFee);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			huseraccount.setCreateTime(createTime);
			
			Integer status = RequestHandler.getInteger(request, "status");
			huseraccount.setStatus(status);
			
			String oneAgentOpenId = RequestHandler.getString(request, "oneAgentOpenId");
			huseraccount.setOneAgentOpenId(oneAgentOpenId);
			
			String twoAgentOpenId = RequestHandler.getString(request, "twoAgentOpenId");
			huseraccount.setTwoAgentOpenId(twoAgentOpenId);
			
			String oneAgentName = RequestHandler.getString(request, "oneAgentName");
			huseraccount.setOneAgentName(oneAgentName);
			
			String twoAgentName = RequestHandler.getString(request, "twoAgentName");
			huseraccount.setTwoAgentName(twoAgentName);
			
			String nickName = RequestHandler.getString(request, "nickName");
			huseraccount.setNickName(nickName);
			
			String phone = RequestHandler.getString(request, "phone");
			huseraccount.setPhone(phone);
			
			String mobile = RequestHandler.getString(request, "mobile");
			huseraccount.setMobile(mobile);
			
			String company_name = RequestHandler.getString(request, "company_name");
			huseraccount.setCompany_name(company_name);
			
			String servicerName = RequestHandler.getString(request, "servicerName");
			huseraccount.setServicerName(servicerName);
			
			Integer role_id = RequestHandler.getInteger(request, "role_id");
			huseraccount.setRole_id(role_id);
			
			Integer fp_state = RequestHandler.getInteger(request, "fp_state");
			huseraccount.setFp_state(fp_state);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			huseraccount.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			huseraccount.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			huseraccount.setRemark3(remark3);
			
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			
			//查询登陆用户角色
			if(adminUser.getRoleType()==1){//超管
				huseraccount.setOneAgentOpenId(oneAgentOpenId);
				
				huseraccount.setTwoAgentOpenId(twoAgentOpenId);
				model.addAttribute("roleType", "1");
			}else{
				if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID))){
					
					huseraccount.setOneAgentOpenId(adminUser.getOneAgentOpenId());
					
					huseraccount.setTwoAgentOpenId(twoAgentOpenId);
					model.addAttribute("roleType", "2");
					model.addAttribute("oneAgentOpenId", adminUser.getOneAgentOpenId());
				}else if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID))){
					model.addAttribute("roleType", "3");
					huseraccount.setOneAgentOpenId(adminUser.getOneAgentOpenId());
					huseraccount.setTwoAgentOpenId(adminUser.getTwoAgentOpenId());
					
				}else if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID))){
					model.addAttribute("roleType", "4");
					huseraccount.setServicerId(adminUser.getAdminId());
					
				}
			}

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			huseraccount.setRowStart(from);
			huseraccount.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			huseraccount.setSortColumn(sortColumn);
			
			int huseraccountListCount = huseraccountService.getHUserAccountListCount(huseraccount);
			ResponseList<HUserAccount> huseraccountList = null;
			if ( huseraccountListCount > 0 )
			{
				huseraccountList = huseraccountService.getHUserAccountList(huseraccount);
			} else
			{
				huseraccountList = new ResponseList<HUserAccount>();
			}
			// 设置数据总数
			huseraccountList.setTotalResults(huseraccountListCount);
			
			writeSuccessMsg("ok", huseraccountList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHUserAccountBaseList", method = RequestMethod.GET)
	public String getHUserAccountBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HUserAccount huseraccount = new HUserAccount();
			
			Integer id = RequestHandler.getInteger(request, "id");
			huseraccount.setId(id);
			
			String openId = RequestHandler.getString(request, "openId");
			huseraccount.setOpenId(openId);
			
			BigDecimal totalFee = RequestHandler.getBigDecimal(request, "totalFee");
			huseraccount.setTotalFee(totalFee);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			huseraccount.setCreateTime(createTime);
			
			Integer status = RequestHandler.getInteger(request, "status");
			huseraccount.setStatus(status);
			
			String oneAgentOpenId = RequestHandler.getString(request, "oneAgentOpenId");
			huseraccount.setOneAgentOpenId(oneAgentOpenId);
			
			String twoAgentOpenId = RequestHandler.getString(request, "twoAgentOpenId");
			huseraccount.setTwoAgentOpenId(twoAgentOpenId);
			
			String oneAgentName = RequestHandler.getString(request, "oneAgentName");
			huseraccount.setOneAgentName(oneAgentName);
			
			String twoAgentName = RequestHandler.getString(request, "twoAgentName");
			huseraccount.setTwoAgentName(twoAgentName);
			
			String nickName = RequestHandler.getString(request, "nickName");
			huseraccount.setNickName(nickName);
			
			String phone = RequestHandler.getString(request, "phone");
			huseraccount.setPhone(phone);
			
			String mobile = RequestHandler.getString(request, "mobile");
			huseraccount.setMobile(mobile);
			
			String company_name = RequestHandler.getString(request, "company_name");
			huseraccount.setCompany_name(company_name);
			
			Integer role_id = RequestHandler.getInteger(request, "role_id");
			huseraccount.setRole_id(role_id);
			
			Integer fp_state = RequestHandler.getInteger(request, "fp_state");
			huseraccount.setFp_state(fp_state);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			huseraccount.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			huseraccount.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			huseraccount.setRemark3(remark3);
			
			List<HUserAccount> huseraccountList = huseraccountService.getHUserAccountBaseList(huseraccount);
		
			writeSuccessMsg("ok", huseraccountList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHUserAccount", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HUserAccount huseraccount = new HUserAccount();
			
			Integer id = RequestHandler.getInteger(request, "id");
			huseraccount.setId(id);
			String openId = RequestHandler.getString(request, "openId");
			huseraccount.setOpenId(openId);
			BigDecimal totalFee = RequestHandler.getBigDecimal(request, "totalFee");
			huseraccount.setTotalFee(totalFee);
			Date createTime = RequestHandler.getDate(request, "createTime");
			huseraccount.setCreateTime(new Date());
			Integer status = RequestHandler.getInteger(request, "status");
			huseraccount.setStatus(status);
			String oneAgentOpenId = RequestHandler.getString(request, "oneAgentOpenId");
			huseraccount.setOneAgentOpenId(oneAgentOpenId);
			String twoAgentOpenId = RequestHandler.getString(request, "twoAgentOpenId");
			huseraccount.setTwoAgentOpenId(twoAgentOpenId);
			String oneAgentName = RequestHandler.getString(request, "oneAgentName");
			huseraccount.setOneAgentName(oneAgentName);
			String twoAgentName = RequestHandler.getString(request, "twoAgentName");
			huseraccount.setTwoAgentName(twoAgentName);
			String nickName = RequestHandler.getString(request, "nickName");
			huseraccount.setNickName(nickName);
			String phone = RequestHandler.getString(request, "phone");
			huseraccount.setPhone(phone);
			String mobile = RequestHandler.getString(request, "mobile");
			huseraccount.setMobile(mobile);
			String company_name = RequestHandler.getString(request, "company_name");
			huseraccount.setCompany_name(company_name);
			Integer role_id = RequestHandler.getInteger(request, "role_id");
			huseraccount.setRole_id(role_id);
			Integer fp_state = RequestHandler.getInteger(request, "fp_state");
			huseraccount.setFp_state(fp_state);
			String remark1 = RequestHandler.getString(request, "remark1");
			huseraccount.setRemark1(remark1);
			String remark2 = RequestHandler.getString(request, "remark2");
			huseraccount.setRemark2(remark2);
			String remark3 = RequestHandler.getString(request, "remark3");
			huseraccount.setRemark3(remark3);
				
			huseraccountService.insertHUserAccount(huseraccount);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHUserAccount", method = RequestMethod.POST)
	public String updateHUserAccount(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HUserAccount huseraccount = new HUserAccount();
			
			Integer id = RequestHandler.getInteger(request, "id");
			huseraccount.setId(id);
			
			String openId = RequestHandler.getString(request, "openId");
			huseraccount.setOpenId(openId);
			
			BigDecimal totalFee = RequestHandler.getBigDecimal(request, "totalFee");
			huseraccount.setTotalFee(totalFee);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			huseraccount.setCreateTime(createTime);
			
			Integer status = RequestHandler.getInteger(request, "status");
			huseraccount.setStatus(status);
			
			String oneAgentOpenId = RequestHandler.getString(request, "oneAgentOpenId");
			huseraccount.setOneAgentOpenId(oneAgentOpenId);
			
			String twoAgentOpenId = RequestHandler.getString(request, "twoAgentOpenId");
			huseraccount.setTwoAgentOpenId(twoAgentOpenId);
			
			String oneAgentName = RequestHandler.getString(request, "oneAgentName");
			huseraccount.setOneAgentName(oneAgentName);
			
			String twoAgentName = RequestHandler.getString(request, "twoAgentName");
			huseraccount.setTwoAgentName(twoAgentName);
			
			String nickName = RequestHandler.getString(request, "nickName");
			huseraccount.setNickName(nickName);
			
			String phone = RequestHandler.getString(request, "phone");
			huseraccount.setPhone(phone);
			
			String mobile = RequestHandler.getString(request, "mobile");
			huseraccount.setMobile(mobile);
			
			String company_name = RequestHandler.getString(request, "company_name");
			huseraccount.setCompany_name(company_name);
			
			Integer role_id = RequestHandler.getInteger(request, "role_id");
			huseraccount.setRole_id(role_id);
			
			Integer fp_state = RequestHandler.getInteger(request, "fp_state");
			huseraccount.setFp_state(fp_state);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			huseraccount.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			huseraccount.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			huseraccount.setRemark3(remark3);
			

			huseraccountService.updateHUserAccount(huseraccount);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHUserAccount", method = RequestMethod.POST)
	public String removeHUserAccount(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HUserAccount huseraccount = new HUserAccount();
			Integer id = RequestHandler.getInteger(request, "id");
			huseraccount.setId(id);

			huseraccountService.removeHUserAccount(huseraccount);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHUserAccount", method = RequestMethod.POST)
	public String removeAllHUserAccount(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HUserAccount hUserAccount = new HUserAccount();
						hUserAccount.setId(Integer.valueOf(id));
						huseraccountService.removeHUserAccount(hUserAccount);
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
