package com.hxt.hFp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import com.hxt.hAmmeterInfo.model.HAmmeterInfo;
import com.hxt.hAmmeterInfo.service.HAmmeterInfoService;
import com.hxt.hCommon.service.HCommonService;
import com.hxt.hFp.model.HFp;
import com.hxt.hFp.service.HFpService;
import com.hxt.hFpOrder.model.HFpOrder;
import com.hxt.hFpOrder.service.HFpOrderService;
import com.hxt.hPayOrder.model.HPayOrder;
import com.hxt.hPayOrder.service.HPayOrderService;
import com.sys.manageAdminUser.model.ManageAdminUser;
/**
 * @author	yy
 * @time	2015年11月17日 23:58:31
 */
@Controller
@RequestMapping("/hFp")
public class HFpController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HFpController.class); //Logger
	
	@Autowired
	private HFpService hfpService = null;
	@Autowired
	private HPayOrderService hPayOrderService = null;
	@Autowired
	private HFpOrderService hfporderService = null;
	@Autowired
	private HAmmeterInfoService hammeterinfoService = null;
	@Autowired
	private HCommonService hCommonService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.YWY_ROLE_ID))){
			model.addAttribute("ywyadmin", adminUser.getAdminId());
		}
		return "/hFp/hFpIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		String pid = RequestHandler.getString(request, "pid");
		model.addAttribute("pid", pid);
		return "/hFp/hFpAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HFp hfp1 = new HFp();
		hfp1.setId(id);
		HFp hfp = hfpService.getHFp(hfp1);
		
		String totalFeeStr1 = null;
		if(hfp.getMoney()>0){
			if(String.valueOf(hfp.getMoney()).length()>2){
				int fen = hfp.getMoney()%100;
				if(fen>0&&fen<10){
					totalFeeStr1 = hfp.getMoney()/100 + ".0" + fen;
				}else if(fen>=10){
					totalFeeStr1 = hfp.getMoney()/100 + "." + fen;
				}else{
					totalFeeStr1 = hfp.getMoney()/100 + ".00";
				}
				hfp.setTotalFeeStr(totalFeeStr1);
			}else if(String.valueOf(hfp.getMoney()).length()==1){
				hfp.setTotalFeeStr("0.0"+hfp.getMoney());
			}else{
				hfp.setTotalFeeStr("0."+hfp.getMoney());
			}
		}else{
			hfp.setTotalFeeStr("0.00");
		}
		
		model.addAttribute("hfp", hfp);
		
		return "/hFp/hFpUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHFpList", method = RequestMethod.GET)
	public String getHFpList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HFp hfp = new HFp();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hfp.setId(id);
			
			String orderNumber = RequestHandler.getString(request, "orderNumber");
			hfp.setOrderNumber(orderNumber);
			
			Double money = RequestHandler.getDouble(request, "money");
			if(money!=null){
				money = money*100;
				hfp.setMoney(money.intValue());
			}
			
			String title = RequestHandler.getString(request, "title");
			hfp.setTitle(title);
			
			String userName = RequestHandler.getString(request, "userName");
			hfp.setUserName(userName);
			
			String phone = RequestHandler.getString(request, "phone");
			hfp.setPhone(phone);
			
			String address = RequestHandler.getString(request, "address");
			hfp.setAddress(address);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hfp.setCreateTime(createTime);
			
			String express_no = RequestHandler.getString(request, "express_no");
			hfp.setExpress_no(express_no);
			
			Integer express_status = RequestHandler.getInteger(request, "express_status");
			hfp.setExpress_status(express_status);
			
			String express_name = RequestHandler.getString(request, "express_name");
			hfp.setExpress_name(express_name);
			
			Integer mailType = RequestHandler.getInteger(request, "mailType");
			hfp.setMailType(mailType);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hfp.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hfp.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hfp.setRemark3(remark3);
			
			Integer queryType = RequestHandler.getInteger(request, "queryType");
			hfp.setQueryType(queryType);
			
			Integer ywyId = RequestHandler.getInteger(request, "ywyId");
			hfp.setYwyId(ywyId);
			
			Integer fp_style = RequestHandler.getInteger(request, "fp_style");
			hfp.setFp_style(fp_style);
			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hfp.setRowStart(from);
			hfp.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hfp.setSortColumn(sortColumn);
			
			String oneAgentOpenId = RequestHandler.getString(request, "oneAgentOpenId");
			String twoAgentOpenID = RequestHandler.getString(request, "twoAgentOpenID");
			
			
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			
			//查询登陆用户角色
			if(adminUser.getRoleType()==1){//超管
				hfp.setOneAgentOpenId(oneAgentOpenId);
				hfp.setTwoAgentOpenID(twoAgentOpenID);
				model.addAttribute("roleType", "1");
			}else{
				if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID))){
					hfp.setOneAgentOpenId(adminUser.getOneAgentOpenId());
					hfp.setTwoAgentOpenID(twoAgentOpenID);
					model.addAttribute("roleType", "2");
					model.addAttribute("oneAgentOpenId", adminUser.getOneAgentOpenId());
					model.addAttribute("oneAgentOpenName", adminUser.getOneAgentName());
				}else if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID))){
					model.addAttribute("roleType", "3");
					hfp.setOneAgentOpenId(adminUser.getOneAgentOpenId());
					hfp.setTwoAgentOpenID(adminUser.getTwoAgentOpenId());
					model.addAttribute("oneAgentOpenId", adminUser.getOneAgentOpenId());
					model.addAttribute("twoAgentOpenId", adminUser.getTwoAgentOpenId());
					model.addAttribute("twoAgentOpenName", adminUser.getTwoAgentName());
					model.addAttribute("oneAgentOpenName", adminUser.getOneAgentName());
				}else if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID))){
					model.addAttribute("roleType", "4");
					hfp.setServicerId(adminUser.getAdminId());
					model.addAttribute("serviceId", adminUser.getAdminId());
					model.addAttribute("serviceName", adminUser.getAdminName());
				}
			}
			
			int hfpListCount = hfpService.getHFpListCount(hfp);
			ResponseList<HFp> hfpList = null;
			if ( hfpListCount > 0 )
			{
				hfpList = hfpService.getHFpList(hfp);
				Iterator<Object> it = hfpList.iterator();
				while(it.hasNext()){
					HFp hFp = (HFp)it.next();
					String totalFeeStr1 = null;
					if(hFp.getMoney()>0){
						if(String.valueOf(hFp.getMoney()).length()>2){
							int fen = hFp.getMoney()%100;
							if(fen>0&&fen<10){
								totalFeeStr1 = hFp.getMoney()/100 + ".0" + fen;
							}else if(fen>=10){
								totalFeeStr1 = hFp.getMoney()/100 + "." + fen;
							}else{
								totalFeeStr1 = hFp.getMoney()/100 + ".00";
							}
							hFp.setTotalFeeStr(totalFeeStr1);
						}else if(String.valueOf(hFp.getMoney()).length()==1){
							hFp.setTotalFeeStr("0.0"+hFp.getMoney());
						}else{
							hFp.setTotalFeeStr("0."+hFp.getMoney());
						}
					}else{
						hFp.setTotalFeeStr("0.00");
					}
				}
			} else
			{
				hfpList = new ResponseList<HFp>();
			}
			// 设置数据总数
			hfpList.setTotalResults(hfpListCount);
			
			writeSuccessMsg("ok", hfpList, response);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/getHFpBaseList", method = RequestMethod.GET)
	public String getHFpBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HFp hfp = new HFp();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hfp.setId(id);
			
			String orderNumber = RequestHandler.getString(request, "orderNumber");
			hfp.setOrderNumber(orderNumber);
			
			Integer money = RequestHandler.getInteger(request, "money");
			hfp.setMoney(money);
			
			String title = RequestHandler.getString(request, "title");
			hfp.setTitle(title);
			
			String userName = RequestHandler.getString(request, "userName");
			hfp.setUserName(userName);
			
			String phone = RequestHandler.getString(request, "phone");
			hfp.setPhone(phone);
			
			String address = RequestHandler.getString(request, "address");
			hfp.setAddress(address);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hfp.setCreateTime(createTime);
			
			String express_no = RequestHandler.getString(request, "express_no");
			hfp.setExpress_no(express_no);
			
			Integer express_status = RequestHandler.getInteger(request, "express_status");
			hfp.setExpress_status(express_status);
			
			String express_name = RequestHandler.getString(request, "express_name");
			hfp.setExpress_name(express_name);
			
			Integer mailType = RequestHandler.getInteger(request, "mailType");
			hfp.setMailType(mailType);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hfp.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hfp.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hfp.setRemark3(remark3);
			
			Integer ywyId = RequestHandler.getInteger(request, "ywyId");
			hfp.setYwyId(ywyId);
			
			Integer queryType = RequestHandler.getInteger(request, "queryType");
			hfp.setQueryType(queryType);
			List<HFp> hfpList = hfpService.getHFpBaseList(hfp);
		
			writeSuccessMsg("ok", hfpList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHFp", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HFp hfp = new HFp();
			boolean flag = true;
			StringBuffer errorMsg = new StringBuffer();
			String orderNumber = RequestHandler.getString(request, "orderNumber");
			Integer cid = 0;
			List<HFpOrder> fplist = new ArrayList<HFpOrder>();
			if(StringUtils.isNotBlank(orderNumber)){
				errorMsg.append("以下订单号不正确或不属于同一公司:");
				String[] nos = orderNumber.split(",");
				for(int i = 0;i<nos.length;i++){
					String oid = nos[i];
					HPayOrder or = new HPayOrder();
					or.setO_no(oid);
					List<HPayOrder> tmp = hPayOrderService.getHPayOrderBaseList(or);
					if(tmp!=null&&tmp.size()>0){
						if(i==0){
							cid = tmp.get(0).getC_id();
							HFpOrder fp = new HFpOrder();
							fp.setOrderId(tmp.get(0).getO_id());
							fp.setStatus(1);
							fp.setCreateTime(new Date());
							fplist.add(fp);
							hfp.setComId(cid);
						}else{
							if(cid.equals(tmp.get(0).getC_id())){
								//同公司
								HFpOrder fp = new HFpOrder();
								fp.setOrderId(tmp.get(0).getO_id());
								fp.setStatus(1);
								fp.setCreateTime(new Date());
								fplist.add(fp);
							}else{
								flag=false;
								errorMsg.append(oid+",");
							}
						}
						//订单存在
					}else{
						flag=false;
						errorMsg.append(oid+",");
					}
				}
			}else{
				flag=false;
				errorMsg.append("订单号不能为空");
			}
			/*HPayOrder or = new HPayOrder();
			or.setO_no(orderNumber);
			List<HPayOrder> tmp = hPayOrderService.getHPayOrderBaseList(or);
			if(tmp!=null&&tmp.size()>0){
				hfp.setMoney(tmp.get(0).getAmount());
			}else{
				errorMsg.append("订单号不正确");
				flag=false;
			}
			hfp.setOrderNumber(orderNumber);*/
			hfp.setOrderNumber(orderNumber);
			Integer money = RequestHandler.getInteger(request, "money");
			hfp.setMoney(money);
			String title = RequestHandler.getString(request, "title");
			hfp.setTitle(title);
			String userName = RequestHandler.getString(request, "userName");
			hfp.setUserName(userName);
			String phone = RequestHandler.getString(request, "phone");
			hfp.setPhone(phone);
			String address = RequestHandler.getString(request, "address");
			hfp.setAddress(address);
			hfp.setCreateTime(new Date());
			String express_no = RequestHandler.getString(request, "express_no");
			hfp.setExpress_no(express_no);
			Integer express_status = RequestHandler.getInteger(request, "express_status");
			hfp.setExpress_status(express_status);
			String express_name = RequestHandler.getString(request, "express_name");
			hfp.setExpress_name(express_name);
			Integer mailType = RequestHandler.getInteger(request, "mailType");
			hfp.setMailType(mailType);
			String remark1 = RequestHandler.getString(request, "remark1");
			hfp.setRemark1(remark1);
			String remark2 = RequestHandler.getString(request, "remark2");
			hfp.setRemark2(remark2);
			String remark3 = RequestHandler.getString(request, "remark3");
			hfp.setRemark3(remark3);
			Integer sub_id = RequestHandler.getInteger(request, "sub_id");
			if(flag){
				if(StringUtils.isNotBlank(remark2)&&sub_id!=null){
					HAmmeterInfo hAmmeterInfo = new HAmmeterInfo();
					hAmmeterInfo.setS_id(sub_id);
					hAmmeterInfo.setAmmeter_no(remark2);
					hAmmeterInfo.setDelete_state(1);
					hAmmeterInfo = hammeterinfoService.getHAmmeterInfo(hAmmeterInfo);
					if(hAmmeterInfo!=null){
						hfp.setFp_style(hAmmeterInfo.getFp_style());
					}
				}
				int fpid = hfpService.insertHFp(hfp);
//				if(fplist!=null&&fplist.size()>0){
//					for(HFpOrder fp:fplist){
//						fp.setFpId(fpid);
//					}
//					hfporderService.insertHFpOrder(fplist);
//				}
				if(fpid>0){
					HFpOrder hFpOrder = new HFpOrder();
					hFpOrder.setFpId(fpid);
					hFpOrder.setOrderId(Integer.valueOf(remark3));
					hFpOrder.setCreateTime(new Date());
					hFpOrder.setStatus(1);
					hfporderService.insertHFpOrder(hFpOrder);
					
					HPayOrder hPayOrder = new HPayOrder();
					hPayOrder.setO_no(orderNumber);
					hPayOrder = hPayOrderService.getHPayOrder(hPayOrder);
					hPayOrder.setIs_fp(1);
					hPayOrderService.updateHPayOrder(hPayOrder);
				}
				writeSuccessMsg("添加成功", null, response);
			}else{
				writeErrorMsg(errorMsg.toString(), null, response);
			}
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHFp", method = RequestMethod.POST)
	public String updateHFp(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			
			HFp hfp = new HFp();
			boolean flag = true;
			Integer cid = 0;
			StringBuffer errorMsg = new StringBuffer();
			List<HFpOrder> fplist = new ArrayList<HFpOrder>();
			String orderNumber = RequestHandler.getString(request, "orderNumber");
			if(StringUtils.isNotBlank(orderNumber)){
				errorMsg.append("以下订单号不正确:");
				String[] nos = orderNumber.split(",");
				for(int i = 0;i<nos.length;i++){
					String oid = nos[i];
					HPayOrder or = new HPayOrder();
					or.setO_no(oid);
					or.setPay_status("1");
					List<HPayOrder> tmp = hPayOrderService.getHPayOrderBaseList(or);
					if(tmp!=null&&tmp.size()>0){
						if(i==0){
							cid = tmp.get(0).getC_id();
							HFpOrder fp = new HFpOrder();
							fp.setOrderId(tmp.get(0).getO_id());
							fp.setStatus(1);
							fp.setCreateTime(new Date());
							fplist.add(fp);
							hfp.setComId(cid);
						}else{
							if(cid.equals(tmp.get(0).getC_id())){
								//同公司
								HFpOrder fp = new HFpOrder();
								fp.setOrderId(tmp.get(0).getO_id());
								fp.setStatus(1);
								fp.setCreateTime(new Date());
								fplist.add(fp);
							}else{
								flag=false;
								errorMsg.append(oid+",");
							}
						}
						//订单存在
					}else{
						flag=false;
						errorMsg.append(oid+",");
					}
				}
				hfp.setOrderNumber(orderNumber);
			}else{
				flag=false;
				errorMsg.append("订单号不能为空");
			}
		/*	HPayOrder or = new HPayOrder();
			or.setO_no(orderNumber);
			List<HPayOrder> tmp = hPayOrderService.getHPayOrderBaseList(or);
			if(tmp!=null&&tmp.size()>0){
				hfp.setMoney(tmp.get(0).getAmount());
			}else{
				errorMsg.append("订单号不正确");
				flag=false;
			}
			hfp.setOrderNumber(orderNumber);*/
			
			Integer id = RequestHandler.getInteger(request, "id");
			hfp.setId(id);
			Integer money = RequestHandler.getInteger(request, "money");
			hfp.setMoney(money);
			String title = RequestHandler.getString(request, "title");
			hfp.setTitle(title);
			
			String userName = RequestHandler.getString(request, "userName");
			hfp.setUserName(userName);
			
			String phone = RequestHandler.getString(request, "phone");
			hfp.setPhone(phone);
			
			String address = RequestHandler.getString(request, "address");
			hfp.setAddress(address);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hfp.setCreateTime(createTime);
			
			String express_no = RequestHandler.getString(request, "express_no");
			hfp.setExpress_no(express_no);
			
			Integer express_status = RequestHandler.getInteger(request, "express_status");
			hfp.setExpress_status(express_status);
			
			String express_name = RequestHandler.getString(request, "express_name");
			hfp.setExpress_name(express_name);
			
			Integer mailType = RequestHandler.getInteger(request, "mailType");
			hfp.setMailType(mailType);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hfp.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hfp.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hfp.setRemark3(remark3);
			
			if(flag){
				int fpid = hfpService.updateHFp(hfp);
//				if(fplist!=null&&fplist.size()>0){
//					for(HFpOrder fp:fplist){
//						fp.setFpId(fpid);
//					}
//					hfporderService.insertHFpOrder(fplist);
//				}
				writeSuccessMsg("修改成功", null, response);
			}else{
				writeErrorMsg(errorMsg.toString(), null, response);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHFp", method = RequestMethod.POST)
	public String removeHFp(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HFp hfp = new HFp();
			Integer id = RequestHandler.getInteger(request, "id");
			hfp.setId(id);

			hfpService.removeHFp(hfp);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHFp", method = RequestMethod.POST)
	public String removeAllHFp(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HFp hFp = new HFp();
						hFp.setId(Integer.valueOf(id));
						hfpService.removeHFp(hFp);
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
	@RequestMapping(value = "/toSend", method = RequestMethod.POST)
	public String toSend(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String id = RequestHandler.getString(request, "id");
			if(StringUtils.isNotBlank(id)){
				HFp hFp = new HFp();
				hFp.setId(Integer.valueOf(id));
				List<HFp> list = hfpService.getHFpBaseList(hFp);
				if(list!=null&&list.size()>0){
					hFp = list.get(0);
					hFp.setExpress_status(1);
					hfpService.updateHFp(hFp);
					writeSuccessMsg("发送成功", null, response);
				}else{
					writeErrorMsg("发票不存在", null, response);
				}
			}else{
				writeErrorMsg("程序异常", null, response);
			}
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/toShowAdd", method = RequestMethod.GET)
	public String toShowAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HFp hfp = new HFp();
			
			String id = RequestHandler.getString(request, "id");
			hfp.setRemark3(id);
			hfp.setQueryType(2);
			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hfp.setRowStart(from);
			hfp.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hfp.setSortColumn(sortColumn);
			
			int hfpListCount = hfpService.getHFpListCount(hfp);
			ResponseList<HFp> hfpList = null;
			if ( hfpListCount > 0 )
			{
				hfpList = hfpService.getHFpList(hfp);
			} else
			{
				hfpList = new ResponseList<HFp>();
			}
			// 设置数据总数
			hfpList.setTotalResults(hfpListCount);
			
			writeSuccessMsg("ok", hfpList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	public String exportExcel(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			HFp hfp = new HFp();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hfp.setId(id);
			
			String orderNumber = RequestHandler.getString(request, "orderNumber");
			hfp.setOrderNumber(orderNumber);
			
			Integer money = RequestHandler.getInteger(request, "money");
			hfp.setMoney(money);
			
			String title = RequestHandler.getString(request, "title");
			hfp.setTitle(title);
			
			String userName = RequestHandler.getString(request, "userName");
			hfp.setUserName(userName);
			
			String phone = RequestHandler.getString(request, "phone");
			hfp.setPhone(phone);
			
			String address = RequestHandler.getString(request, "address");
			hfp.setAddress(address);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hfp.setCreateTime(createTime);
			
			String express_no = RequestHandler.getString(request, "express_no");
			hfp.setExpress_no(express_no);
			
			Integer express_status = RequestHandler.getInteger(request, "express_status");
			hfp.setExpress_status(express_status);
			
			String express_name = RequestHandler.getString(request, "express_name");
			hfp.setExpress_name(express_name);
			
			Integer mailType = RequestHandler.getInteger(request, "mailType");
			hfp.setMailType(mailType);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hfp.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hfp.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hfp.setRemark3(remark3);
			
			Integer queryType = RequestHandler.getInteger(request, "queryType");
			hfp.setQueryType(queryType);
			
			Integer ywyId = RequestHandler.getInteger(request, "ywyId");
			hfp.setYwyId(ywyId);
			
			Integer fp_style = RequestHandler.getInteger(request, "fp_style");
			hfp.setFp_style(fp_style);
			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hfp.setRowStart(from);
			hfp.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hfp.setSortColumn(sortColumn);
			
			int hfpListCount = hfpService.getHFpListCount(hfp);
			if ( hfpListCount > 0 )
			{
				List manageadminuserList = hfpService.getHFpBaseList(hfp);
				LinkedList list = new LinkedList();
				list.addAll(manageadminuserList);
				LinkedList fields = new LinkedList();
				fields.add("orderNumber");
				fields.add("remark2");
				fields.add("money");
				fields.add("title");
				fields.add("userName");
				fields.add("phone");
				fields.add("address");
				fields.add("createTime");
				fields.add("express_no");
				fields.add("express_status");
				fields.add("express_name");
				fields.add("mailType");
				fields.add("fp_style");
				LinkedList titles = new LinkedList();
				titles.add("订单号");
				titles.add("电表号");
				titles.add("发票金额");
				titles.add("发票抬头");
				titles.add("收件人姓名");
				titles.add("收件人电话");
				titles.add("收件人地址");
				titles.add("申请时间");
				titles.add("快递单号");
				titles.add("快递状态");
				titles.add("快递名称");
				titles.add("邮寄方式");
				titles.add("发票类型");
				String path = hCommonService.excleExport(list, fields, titles, HFp.class, "发票管理",request);
				writeSuccessMsg("成功", path, response);
			} else
			{
				writeErrorMsg("无可导出内容", null, response);
			}
		} catch (Exception e) {
			writeErrorMsg("系统错误", null, response);
		}
		return null;
	}
	@RequestMapping(value = "/exportExcelTag", method = RequestMethod.GET)
	public String exportExcelTag(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			HFp hfp = new HFp();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hfp.setId(id);
			
			String orderNumber = RequestHandler.getString(request, "orderNumber");
			hfp.setOrderNumber(orderNumber);
			
			Integer money = RequestHandler.getInteger(request, "money");
			hfp.setMoney(money);
			
			String title = RequestHandler.getString(request, "title");
			hfp.setTitle(title);
			
			String userName = RequestHandler.getString(request, "userName");
			hfp.setUserName(userName);
			
			String phone = RequestHandler.getString(request, "phone");
			hfp.setPhone(phone);
			
			String address = RequestHandler.getString(request, "address");
			hfp.setAddress(address);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hfp.setCreateTime(createTime);
			
			String express_no = RequestHandler.getString(request, "express_no");
			hfp.setExpress_no(express_no);
			
			Integer express_status = RequestHandler.getInteger(request, "express_status");
			hfp.setExpress_status(express_status);
			
			String express_name = RequestHandler.getString(request, "express_name");
			hfp.setExpress_name(express_name);
			
			Integer mailType = RequestHandler.getInteger(request, "mailType");
			hfp.setMailType(mailType);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hfp.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hfp.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hfp.setRemark3(remark3);
			
			Integer queryType = RequestHandler.getInteger(request, "queryType");
			hfp.setQueryType(queryType);
			
			Integer ywyId = RequestHandler.getInteger(request, "ywyId");
			hfp.setYwyId(ywyId);
			
			Integer fp_style = RequestHandler.getInteger(request, "fp_style");
			hfp.setFp_style(fp_style);
			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hfp.setRowStart(from);
			hfp.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hfp.setSortColumn(sortColumn);
			
			int hfpListCount = hfpService.getHFpListCount(hfp);
			if ( hfpListCount > 0 )
			{
				//处理序号
				List<HFp> manageadminuserList = hfpService.getHFpTagList(hfp);
				HFp last = null;
				int index = 1;
				for(HFp fp : manageadminuserList){
					System.out.println(fp.getFpCount());
					if(last!=null){
						if(last.getOrderNumber().equals(fp.getOrderNumber())){
							fp.setSerialNum(++index);
						}else{
							fp.setSerialNum(1);
							 index = 1;
						}
						last = fp;
					}else{
						fp.setSerialNum(1);
						index = 1;
						last = fp;
					}
				}
				LinkedList list = new LinkedList();
				list.addAll(manageadminuserList);
				LinkedList fields = new LinkedList();
				fields.add("companyName");
				fields.add("userName");
				fields.add("phone");
				fields.add("address");
				fields.add("areaName");
				fields.add("fp_style");
				fields.add("fpCount");
				fields.add("serialNum");
				fields.add("orderNo");
				fields.add("ammeterNo");
				fields.add("fpExportTime");
				LinkedList titles = new LinkedList();
				titles.add("单位名称");
				titles.add("收件人");
				titles.add("电话");
				titles.add("送票地址");
				titles.add("换票地");
				titles.add("发票类型");
				titles.add("张数");
				titles.add("当前记录发票序号");
				titles.add("订单号");
				titles.add("缴费单号");
				titles.add("导出日期");
				String path = hCommonService.excleExport(list, fields, titles, HFp.class, "发票标签",request);
				writeSuccessMsg("成功", path, response);
			} else
			{
				writeErrorMsg("无可导出内容", null, response);
			}
		} catch (Exception e) {
			writeErrorMsg("系统错误", null, response);
		}
		return null;
	}
}
