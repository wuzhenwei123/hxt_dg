package com.hxt.hPresentApply.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.base.controller.BaseController;
import com.base.utils.FileUploadConstants;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SessionName;
import com.hxt.hCommon.service.HCommonService;
import com.hxt.hPayOrder.model.HPayOrder;
import com.hxt.hPayOrder.service.HPayOrderService;
import com.hxt.hPresentApply.model.HPresentApply;
import com.hxt.hPresentApply.service.HPresentApplyService;
import com.hxt.hUserAccount.model.HUserAccount;
import com.hxt.hUserAccount.service.HUserAccountService;
import com.hxt.hUserAccountDetail.model.HUserAccountDetail;
import com.hxt.hUserAccountDetail.service.HUserAccountDetailService;
import com.sys.manageAdminUser.model.ManageAdminUser;
/**
 * @author	zhangyiyang
 * @time	2016年08月29日 23:04:34
 */
@Controller
@RequestMapping("/hPresentApply")
public class HPresentApplyController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HPresentApplyController.class); //Logger
	
	@Autowired
	private HPresentApplyService hpresentapplyService = null;
	@Autowired
	private HUserAccountDetailService hUserAccountDetailService = null;
	@Autowired
	private HUserAccountService huserAccountService = null;
	@Autowired
	private HPayOrderService hpayOrderService = null;
	@Autowired
	private HCommonService hCommonService;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hPresentApply/hPresentApplyIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hPresentApply/hPresentApplyAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HPresentApply hpresentapply1 = new HPresentApply();
		hpresentapply1.setId(id);
		HPresentApply hpresentapply = hpresentapplyService.getHPresentApply(hpresentapply1);
		model.addAttribute("hpresentapply", hpresentapply);
		
		return "/hPresentApply/hPresentApplyUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHPresentApplyList", method = RequestMethod.GET)
	public String getHPresentApplyList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HPresentApply hpresentapply = new HPresentApply();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hpresentapply.setId(id);
			
			String openId = RequestHandler.getString(request, "openId");
			hpresentapply.setOpenId(openId);
			
			BigDecimal totalFee = RequestHandler.getBigDecimal(request, "totalFee");
			hpresentapply.setTotalFee(totalFee);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hpresentapply.setCreateTime(createTime);
			
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String checkTime_start = RequestHandler.getString(request, "checkTime_start");
			if(StringUtils.isNotBlank(checkTime_start)){
				checkTime_start = checkTime_start + " 00:00:00";
				hpresentapply.setCheckTime_start(sf.parse(checkTime_start));
			}
			String checkTime_end = RequestHandler.getString(request, "checkTime_end");
			if(StringUtils.isNotBlank(checkTime_end)){
				checkTime_end = checkTime_end + " 23:59:59";
				hpresentapply.setCheckTime_end(sf.parse(checkTime_end));
			}
			String createTime_start = RequestHandler.getString(request, "createTime_start");
			if(StringUtils.isNotBlank(createTime_start)){
				createTime_start = createTime_start + " 00:00:00";
				hpresentapply.setCreateTime_start(sf.parse(createTime_start));
			}
			String createTime_end = RequestHandler.getString(request, "createTime_end");
			if(StringUtils.isNotBlank(createTime_end)){
				createTime_end = createTime_end + " 23:59:59";
				hpresentapply.setCreateTime_end(sf.parse(createTime_end));
			}
			
			
			Integer status = RequestHandler.getInteger(request, "status");
			hpresentapply.setStatus(status);
			
			String oneAgentOpenId = RequestHandler.getString(request, "oneAgentOpenId");
			hpresentapply.setOneAgentOpenId(oneAgentOpenId);
			
			Integer agentTwoStyle = RequestHandler.getInteger(request, "agentTwoStyle");
			hpresentapply.setAgentTwoStyle(agentTwoStyle);
			
			String oneAgentName = RequestHandler.getString(request, "oneAgentName");
			hpresentapply.setOneAgentName(oneAgentName);
			
			Integer role_id = RequestHandler.getInteger(request, "role_id");
			hpresentapply.setRole_id(role_id);
			
			String twoAgentOpenId = RequestHandler.getString(request, "twoAgentOpenId");
			hpresentapply.setTwoAgentOpenId(twoAgentOpenId);
			
			String twoAgentName = RequestHandler.getString(request, "twoAgentName");
			hpresentapply.setTwoAgentName(twoAgentName);
			
			Integer accountDetailId = RequestHandler.getInteger(request, "accountDetailId");
			hpresentapply.setAccountDetailId(accountDetailId);
			
			String nickName = RequestHandler.getString(request, "nickName");
			hpresentapply.setNickName(nickName);
			
			String batchCode = RequestHandler.getString(request, "batchCode");
			hpresentapply.setBatchCode(batchCode);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hpresentapply.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hpresentapply.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hpresentapply.setRemark3(remark3);
			
			Integer servicerId = RequestHandler.getInteger(request, "servicerId");
			hpresentapply.setServicerId(servicerId);
			
			String servicerName = RequestHandler.getString(request, "servicerName");
			hpresentapply.setServicerName(servicerName);
			
			String taxRate = RequestHandler.getString(request, "taxRate");
			hpresentapply.setTaxRate(taxRate);
			
			BigDecimal taxFee = RequestHandler.getBigDecimal(request, "taxFee");
			hpresentapply.setTaxFee(taxFee);
			
			BigDecimal allFee = RequestHandler.getBigDecimal(request, "allFee");
			hpresentapply.setAllFee(allFee);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hpresentapply.setRowStart(from);
			hpresentapply.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hpresentapply.setSortColumn(sortColumn);
			
			int hpresentapplyListCount = hpresentapplyService.getHPresentApplyListCount(hpresentapply);
			ResponseList<HPresentApply> hpresentapplyList = null;
			if ( hpresentapplyListCount > 0 )
			{
				hpresentapplyList = hpresentapplyService.getHPresentApplyList(hpresentapply);
			} else
			{
				hpresentapplyList = new ResponseList<HPresentApply>();
			}
			// 设置数据总数
			hpresentapplyList.setTotalResults(hpresentapplyListCount);
			
			writeSuccessMsg("ok", hpresentapplyList, response);
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHPresentApplyBaseList", method = RequestMethod.GET)
	public String getHPresentApplyBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HPresentApply hpresentapply = new HPresentApply();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hpresentapply.setId(id);
			
			String openId = RequestHandler.getString(request, "openId");
			hpresentapply.setOpenId(openId);
			
			BigDecimal totalFee = RequestHandler.getBigDecimal(request, "totalFee");
			hpresentapply.setTotalFee(totalFee);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hpresentapply.setCreateTime(createTime);
			
			Integer status = RequestHandler.getInteger(request, "status");
			hpresentapply.setStatus(status);
			
			String oneAgentOpenId = RequestHandler.getString(request, "oneAgentOpenId");
			hpresentapply.setOneAgentOpenId(oneAgentOpenId);
			
			String oneAgentName = RequestHandler.getString(request, "oneAgentName");
			hpresentapply.setOneAgentName(oneAgentName);
			
			String twoAgentOpenId = RequestHandler.getString(request, "twoAgentOpenId");
			hpresentapply.setTwoAgentOpenId(twoAgentOpenId);
			
			String twoAgentName = RequestHandler.getString(request, "twoAgentName");
			hpresentapply.setTwoAgentName(twoAgentName);
			
			Integer accountDetailId = RequestHandler.getInteger(request, "accountDetailId");
			hpresentapply.setAccountDetailId(accountDetailId);
			
			String nickName = RequestHandler.getString(request, "nickName");
			hpresentapply.setNickName(nickName);
			
			String batchCode = RequestHandler.getString(request, "batchCode");
			hpresentapply.setBatchCode(batchCode);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hpresentapply.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hpresentapply.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hpresentapply.setRemark3(remark3);
			
			Integer servicerId = RequestHandler.getInteger(request, "servicerId");
			hpresentapply.setServicerId(servicerId);
			
			String servicerName = RequestHandler.getString(request, "servicerName");
			hpresentapply.setServicerName(servicerName);
			
			String taxRate = RequestHandler.getString(request, "taxRate");
			hpresentapply.setTaxRate(taxRate);
			
			BigDecimal taxFee = RequestHandler.getBigDecimal(request, "taxFee");
			hpresentapply.setTaxFee(taxFee);
			
			BigDecimal allFee = RequestHandler.getBigDecimal(request, "allFee");
			hpresentapply.setAllFee(allFee);
			
			List<HPresentApply> hpresentapplyList = hpresentapplyService.getHPresentApplyBaseList(hpresentapply);
		
			writeSuccessMsg("ok", hpresentapplyList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHPresentApply", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HPresentApply hpresentapply = new HPresentApply();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hpresentapply.setId(id);
			String openId = RequestHandler.getString(request, "openId");
			hpresentapply.setOpenId(openId);
			BigDecimal totalFee = RequestHandler.getBigDecimal(request, "totalFee");
			hpresentapply.setTotalFee(totalFee);
			Date createTime = RequestHandler.getDate(request, "createTime");
			hpresentapply.setCreateTime(new Date());
			Integer status = RequestHandler.getInteger(request, "status");
			hpresentapply.setStatus(status);
			String oneAgentOpenId = RequestHandler.getString(request, "oneAgentOpenId");
			hpresentapply.setOneAgentOpenId(oneAgentOpenId);
			String oneAgentName = RequestHandler.getString(request, "oneAgentName");
			hpresentapply.setOneAgentName(oneAgentName);
			String twoAgentOpenId = RequestHandler.getString(request, "twoAgentOpenId");
			hpresentapply.setTwoAgentOpenId(twoAgentOpenId);
			String twoAgentName = RequestHandler.getString(request, "twoAgentName");
			hpresentapply.setTwoAgentName(twoAgentName);
			Integer accountDetailId = RequestHandler.getInteger(request, "accountDetailId");
			hpresentapply.setAccountDetailId(accountDetailId);
			String nickName = RequestHandler.getString(request, "nickName");
			hpresentapply.setNickName(nickName);
			String batchCode = RequestHandler.getString(request, "batchCode");
			hpresentapply.setBatchCode(batchCode);
			String remark1 = RequestHandler.getString(request, "remark1");
			hpresentapply.setRemark1(remark1);
			String remark2 = RequestHandler.getString(request, "remark2");
			hpresentapply.setRemark2(remark2);
			String remark3 = RequestHandler.getString(request, "remark3");
			hpresentapply.setRemark3(remark3);
			Integer servicerId = RequestHandler.getInteger(request, "servicerId");
			hpresentapply.setServicerId(servicerId);
			String servicerName = RequestHandler.getString(request, "servicerName");
			hpresentapply.setServicerName(servicerName);
			String taxRate = RequestHandler.getString(request, "taxRate");
			hpresentapply.setTaxRate(taxRate);
			BigDecimal taxFee = RequestHandler.getBigDecimal(request, "taxFee");
			hpresentapply.setTaxFee(taxFee);
			BigDecimal allFee = RequestHandler.getBigDecimal(request, "allFee");
			hpresentapply.setAllFee(allFee);
				
			hpresentapplyService.insertHPresentApply(hpresentapply);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHPresentApply", method = RequestMethod.POST)
	public String updateHPresentApply(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HPresentApply hpresentapply = new HPresentApply();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hpresentapply.setId(id);
			
			String openId = RequestHandler.getString(request, "openId");
			hpresentapply.setOpenId(openId);
			
			BigDecimal totalFee = RequestHandler.getBigDecimal(request, "totalFee");
			hpresentapply.setTotalFee(totalFee);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hpresentapply.setCreateTime(createTime);
			
			Integer status = RequestHandler.getInteger(request, "status");
			hpresentapply.setStatus(status);
			
			String oneAgentOpenId = RequestHandler.getString(request, "oneAgentOpenId");
			hpresentapply.setOneAgentOpenId(oneAgentOpenId);
			
			String oneAgentName = RequestHandler.getString(request, "oneAgentName");
			hpresentapply.setOneAgentName(oneAgentName);
			
			String twoAgentOpenId = RequestHandler.getString(request, "twoAgentOpenId");
			hpresentapply.setTwoAgentOpenId(twoAgentOpenId);
			
			String twoAgentName = RequestHandler.getString(request, "twoAgentName");
			hpresentapply.setTwoAgentName(twoAgentName);
			
			Integer accountDetailId = RequestHandler.getInteger(request, "accountDetailId");
			hpresentapply.setAccountDetailId(accountDetailId);
			
			String nickName = RequestHandler.getString(request, "nickName");
			hpresentapply.setNickName(nickName);
			
			String batchCode = RequestHandler.getString(request, "batchCode");
			hpresentapply.setBatchCode(batchCode);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hpresentapply.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hpresentapply.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hpresentapply.setRemark3(remark3);
			
			Integer servicerId = RequestHandler.getInteger(request, "servicerId");
			hpresentapply.setServicerId(servicerId);
			
			String servicerName = RequestHandler.getString(request, "servicerName");
			hpresentapply.setServicerName(servicerName);
			
			String taxRate = RequestHandler.getString(request, "taxRate");
			hpresentapply.setTaxRate(taxRate);
			
			BigDecimal taxFee = RequestHandler.getBigDecimal(request, "taxFee");
			hpresentapply.setTaxFee(taxFee);
			
			BigDecimal allFee = RequestHandler.getBigDecimal(request, "allFee");
			hpresentapply.setAllFee(allFee);
			

			hpresentapplyService.updateHPresentApply(hpresentapply);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHPresentApply", method = RequestMethod.POST)
	public String removeHPresentApply(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HPresentApply hpresentapply = new HPresentApply();
			Integer id = RequestHandler.getInteger(request, "id");
			hpresentapply.setId(id);

			hpresentapplyService.removeHPresentApply(hpresentapply);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHPresentApply", method = RequestMethod.POST)
	public String removeAllHPresentApply(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HPresentApply hPresentApply = new HPresentApply();
						hPresentApply.setId(Integer.valueOf(id));
						hpresentapplyService.removeHPresentApply(hPresentApply);
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
	@RequestMapping(value = "/review", method = RequestMethod.POST)
	public String review(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		try
		{	
			String id = RequestHandler.getString(request, "id");
			if(StringUtils.isNotBlank(id)){
				HPresentApply hPresentApply = new HPresentApply();
				hPresentApply.setId(Integer.valueOf(id));
				hPresentApply = hpresentapplyService.getHPresentApply(hPresentApply);
				if(hPresentApply!=null){
					hPresentApply.setStatus(1);
					hPresentApply.setCheckTime(new Date());
					hpresentapplyService.updateHPresentApply(hPresentApply);//更改提现状态
					//账户
					HUserAccount account = new HUserAccount();
//					account.setOpenId(hPresentApply.getOpenId());
					account.setStatus(1);
					if(StringUtils.isNotBlank(hPresentApply.getTwoAgentOpenId())){
//						account.setOneAgentOpenId(hPresentApply.getOneAgentOpenId());
						account.setTwoAgentOpenId(hPresentApply.getTwoAgentOpenId());
						account.setRole_id(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
					}else if(hPresentApply.getServicerId()!=null){
						account.setServicerId(hPresentApply.getServicerId());
						account.setRole_id(Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID));
					}else if(StringUtils.isNotBlank(hPresentApply.getOneAgentOpenId())&&!StringUtils.isNotBlank(hPresentApply.getTwoAgentOpenId())){
						account.setOneAgentOpenId(hPresentApply.getOneAgentOpenId());
						account.setRole_id(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
					}
					account = huserAccountService.getHUserAccount(account);
					if(account !=null){
						//插入账户明细
						HUserAccountDetail detail = new HUserAccountDetail();
						detail.setUserAccountId(account.getId());
						detail.setType(2);
						detail.setTotalFee(hPresentApply.getAllFee());
						detail.setRealFee(hPresentApply.getTotalFee());
						detail.setCreateTime(new Date());
						detail.setOperaterId(adminUser.getAdminId());
						if(hPresentApply.getTaxRate()!=null){
							detail.setTaxRate(new BigDecimal(hPresentApply.getTaxRate()));
						}else{
							detail.setTaxRate(new BigDecimal(0.00));
						}
						detail.setTaxMoney(hPresentApply.getTaxFee());
						detail.setRemark1(id);
						int detailId = hUserAccountDetailService.insertHUserAccountDetail(detail);
						hPresentApply.setAccountDetailId(detailId);
						hpresentapplyService.updateHPresentApply(hPresentApply);//更改账户详情ID
						//更新账户总额
						BigDecimal total = account.getTotalFee();
						BigDecimal applyfee = detail.getTotalFee();
						if(total!=null&&applyfee!=null){
							total = total.subtract(applyfee);
							account.setTotalFee(total);
							huserAccountService.updateHUserAccount(account);
						}
						//更新提现订单
//						HPayOrder order = new HPayOrder();
//						order.setApply_id(Integer.valueOf(id));
//						order = hpayOrderService.getHPayOrder(order);
//						if(order!=null){
//							order.setTick_off_status("1");
//							order.setPay_status("1");
//							order.setPay_time(new Date());
//							hpayOrderService.updateHPayOrder(order);
//						}
					}
				}
			}
			writeSuccessMsg("审核成功", null, response);
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/exportExport", method = RequestMethod.GET)
	public String exportExport(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HPresentApply hpresentapply = new HPresentApply();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hpresentapply.setId(id);
			
			String openId = RequestHandler.getString(request, "openId");
			hpresentapply.setOpenId(openId);
			
			BigDecimal totalFee = RequestHandler.getBigDecimal(request, "totalFee");
			hpresentapply.setTotalFee(totalFee);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hpresentapply.setCreateTime(createTime);
			
			Integer status = RequestHandler.getInteger(request, "status");
			hpresentapply.setStatus(status);
			
			String oneAgentOpenId = RequestHandler.getString(request, "oneAgentOpenId");
			hpresentapply.setOneAgentOpenId(oneAgentOpenId);
			
			String oneAgentName = RequestHandler.getString(request, "oneAgentName");
			hpresentapply.setOneAgentName(oneAgentName);
			
			String twoAgentOpenId = RequestHandler.getString(request, "twoAgentOpenId");
			hpresentapply.setTwoAgentOpenId(twoAgentOpenId);
			
			String twoAgentName = RequestHandler.getString(request, "twoAgentName");
			hpresentapply.setTwoAgentName(twoAgentName);
			
			Integer accountDetailId = RequestHandler.getInteger(request, "accountDetailId");
			hpresentapply.setAccountDetailId(accountDetailId);
			
			String nickName = RequestHandler.getString(request, "nickName");
			hpresentapply.setNickName(nickName);
			
			String batchCode = RequestHandler.getString(request, "batchCode");
			hpresentapply.setBatchCode(batchCode);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hpresentapply.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hpresentapply.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hpresentapply.setRemark3(remark3);
			
			Integer servicerId = RequestHandler.getInteger(request, "servicerId");
			hpresentapply.setServicerId(servicerId);
			
			Integer role_id = RequestHandler.getInteger(request, "role_id");
			hpresentapply.setRole_id(role_id);
			
			Integer agentTwoStyle = RequestHandler.getInteger(request, "agentTwoStyle");
			hpresentapply.setAgentTwoStyle(agentTwoStyle);
			
			String servicerName = RequestHandler.getString(request, "servicerName");
			hpresentapply.setServicerName(servicerName);
			
			String taxRate = RequestHandler.getString(request, "taxRate");
			hpresentapply.setTaxRate(taxRate);
			
			BigDecimal taxFee = RequestHandler.getBigDecimal(request, "taxFee");
			hpresentapply.setTaxFee(taxFee);
			
			BigDecimal allFee = RequestHandler.getBigDecimal(request, "allFee");
			hpresentapply.setAllFee(allFee);
			
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String checkTime_start = RequestHandler.getString(request, "checkTime_start");
			if(StringUtils.isNotBlank(checkTime_start)){
				checkTime_start = checkTime_start + " 00:00:00";
				hpresentapply.setCheckTime_start(sf.parse(checkTime_start));
			}
			String checkTime_end = RequestHandler.getString(request, "checkTime_end");
			if(StringUtils.isNotBlank(checkTime_end)){
				checkTime_end = checkTime_end + " 23:59:59";
				hpresentapply.setCheckTime_end(sf.parse(checkTime_end));
			}
			String createTime_start = RequestHandler.getString(request, "createTime_start");
			if(StringUtils.isNotBlank(createTime_start)){
				createTime_start = createTime_start + " 00:00:00";
				hpresentapply.setCreateTime_start(sf.parse(createTime_start));
			}
			String createTime_end = RequestHandler.getString(request, "createTime_end");
			if(StringUtils.isNotBlank(createTime_end)){
				createTime_end = createTime_end + " 23:59:59";
				hpresentapply.setCreateTime_end(sf.parse(createTime_end));
			}

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hpresentapply.setRowStart(from);
			hpresentapply.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hpresentapply.setSortColumn(sortColumn);
			
			List<HPresentApply> list1 = hpresentapplyService.getHPresentApplyBaseList(hpresentapply);
			String basePath = request.getSession().getServletContext().getRealPath("/");
			if(list1!=null&&list1.size()>0){
				String path = hpresentapplyService.exportExecl(basePath,list1);
				writeSuccessMsg("成功", path, response);
			}else{
				writeErrorMsg("nodata", null, response);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
}
