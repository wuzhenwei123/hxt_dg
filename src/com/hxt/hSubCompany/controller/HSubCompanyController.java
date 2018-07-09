package com.hxt.hSubCompany.controller;

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

import com.alibaba.fastjson.JSONObject;
import com.base.controller.BaseController;
import com.base.utils.FileUploadConstants;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SessionName;
import com.hxt.hAmmeterInfo.model.HAmmeterInfo;
import com.hxt.hAmmeterInfo.service.HAmmeterInfoService;
import com.hxt.hCommon.service.HCommonService;
import com.hxt.hCompany.model.HCompany;
import com.hxt.hCompany.service.HCompanyService;
import com.hxt.hSubCompany.model.HSubCompany;
import com.hxt.hSubCompany.service.HSubCompanyService;
import com.sys.manageAdminUser.model.ManageAdminUser;
/**
 * @author	wuzhenwei
 * @time	2015年11月24日 10:42:55
 */
@Controller
@RequestMapping("/hSubCompany")
public class HSubCompanyController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HSubCompanyController.class); //Logger
	
	@Autowired
	private HSubCompanyService hsubcompanyService = null;
	@Autowired
	private HAmmeterInfoService hammeterinfoService = null;
	@Autowired
	private HCommonService hCommonService = null;
	@Autowired
	private HCompanyService hcompanyService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		
		//查询登陆用户角色
		if(adminUser.getRoleType()==1){//超管
//			hsubcompany.setOneAgentOpenId(oneAgentOpenId);
//			hsubcompany.setTwoAgentOpenID(twoAgentOpenID);
			model.addAttribute("roleType", "1");
		}else{
			if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID))){
//				hsubcompany.setOneAgentOpenId(adminUser.getOneAgentOpenId());
//				hsubcompany.setTwoAgentOpenID(twoAgentOpenID);
				model.addAttribute("roleType", "2");
				model.addAttribute("oneAgentOpenId", adminUser.getOneAgentOpenId());
				model.addAttribute("oneAgentOpenName", adminUser.getOneAgentName());
			}else if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID))){
				model.addAttribute("roleType", "3");
//				hsubcompany.setOneAgentOpenId(adminUser.getOneAgentOpenId());
//				hsubcompany.setTwoAgentOpenID(adminUser.getTwoAgentOpenId());
				model.addAttribute("oneAgentOpenId", adminUser.getOneAgentOpenId());
				model.addAttribute("twoAgentOpenId", adminUser.getTwoAgentOpenId());
				model.addAttribute("twoAgentOpenName", adminUser.getTwoAgentName());
				model.addAttribute("oneAgentOpenName", adminUser.getOneAgentName());
			}else if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID))){
				model.addAttribute("roleType", "4");
//				hsubcompany.setServicerId(adminUser.getAdminId());
				model.addAttribute("serviceId", adminUser.getAdminId());
				model.addAttribute("serviceName", adminUser.getAdminName());
			}
		}
		return "/hSubCompany/hSubCompanyIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hSubCompany/hSubCompanyAdd";
	}
	@RequestMapping(value = "/toUpdate/{s_id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer s_id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HSubCompany hsubcompany1 = new HSubCompany();
		hsubcompany1.setS_id(s_id);
		HSubCompany hsubcompany = hsubcompanyService.getHSubCompany(hsubcompany1);
		model.addAttribute("hsubcompany", hsubcompany);
		
		return "/hSubCompany/hSubCompanyUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHSubCompanyList", method = RequestMethod.GET)
	public String getHSubCompanyList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HSubCompany hsubcompany = new HSubCompany();
			
			Integer s_id = RequestHandler.getInteger(request, "s_id");
			hsubcompany.setS_id(s_id);
			
			Integer c_id = RequestHandler.getInteger(request, "c_id");
			hsubcompany.setC_id(c_id);
			
			String sub_name = RequestHandler.getString(request, "sub_name");
			hsubcompany.setSub_name(sub_name);
			
			String contact_phone = RequestHandler.getString(request, "contact_phone");
			hsubcompany.setContact_phone(contact_phone);
			
			String invoice_title = RequestHandler.getString(request, "invoice_title");
			hsubcompany.setInvoice_title(invoice_title);
			
			String consignee = RequestHandler.getString(request, "consignee");
			hsubcompany.setConsignee(consignee);
			
			String consignee_phone = RequestHandler.getString(request, "consignee_phone");
			hsubcompany.setConsignee_phone(consignee_phone);
			
			String consignee_tel1 = RequestHandler.getString(request, "consignee_tel1");
			hsubcompany.setConsignee_tel1(consignee_tel1);
			
			String consignee_tel2 = RequestHandler.getString(request, "consignee_tel2");
			hsubcompany.setConsignee_tel2(consignee_tel2);
			
			String consignee_address = RequestHandler.getString(request, "consignee_address");
			hsubcompany.setConsignee_address(consignee_address);
			String oneAgentOpenId = RequestHandler.getString(request, "oneAgentOpenId");
			String twoAgentOpenID = RequestHandler.getString(request, "twoAgentOpenID");
			hsubcompany.setOneAgentOpenId(oneAgentOpenId);
			hsubcompany.setTwoAgentOpenID(twoAgentOpenID);
			
			String c_name = RequestHandler.getString(request, "c_name");
			hsubcompany.setC_name(c_name);
			String servicerName = RequestHandler.getString(request, "servicerName");
			hsubcompany.setServicerName(servicerName);
			Integer servicerId = RequestHandler.getInteger(request, "servicerId");
			hsubcompany.setServicerId(servicerId);
			
			String ammeter_no = RequestHandler.getString(request, "ammeter_no");
			hsubcompany.setAmmeter_no(ammeter_no);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hsubcompany.setCreate_time(create_time);
			Date create_time1 = RequestHandler.getDate(request, "create_time1");
			hsubcompany.setCreate_time1(create_time1);
			Date create_time2 = RequestHandler.getDate(request, "create_time2");
			hsubcompany.setCreate_time2(create_time2);
			
			Date update_time = RequestHandler.getDate(request, "update_time");
			hsubcompany.setUpdate_time(update_time);
			//新增2016-8-9 22:50:17
			String zip_code = RequestHandler.getString(request, "zip_code");
			hsubcompany.setZip_code(zip_code);
			
			String province_code = RequestHandler.getString(request, "province_code");
			hsubcompany.setProvince_code(province_code);
			String city_code = RequestHandler.getString(request, "city_code");
			hsubcompany.setCity_code(city_code);
			String area_code = RequestHandler.getString(request, "area_code");
			hsubcompany.setArea_code(area_code);

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hsubcompany.setRowStart(from);
			hsubcompany.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hsubcompany.setSortColumn(sortColumn);
			
//			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
//			if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.YWY_ROLE_ID))){
//				hsubcompany.setYw_id(adminUser.getAdminId());
//			}
			
			int hsubcompanyListCount = hsubcompanyService.getHSubCompanyListCount(hsubcompany);
			ResponseList<HSubCompany> hsubcompanyList = null;
			if ( hsubcompanyListCount > 0 )
			{
				hsubcompanyList = hsubcompanyService.getHSubCompanyList(hsubcompany);
			} else
			{
				hsubcompanyList = new ResponseList<HSubCompany>();
			}
			// 设置数据总数
			hsubcompanyList.setTotalResults(hsubcompanyListCount);
			
			writeSuccessMsg("ok", hsubcompanyList, response);
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHSubCompanyBaseList", method = RequestMethod.GET)
	public String getHSubCompanyBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HSubCompany hsubcompany = new HSubCompany();
			
			Integer s_id = RequestHandler.getInteger(request, "s_id");
			hsubcompany.setS_id(s_id);
			
			Integer c_id = RequestHandler.getInteger(request, "c_id");
			hsubcompany.setC_id(c_id);
			
			String sub_name = RequestHandler.getString(request, "sub_name");
			hsubcompany.setSub_name(sub_name);
			
			String invoice_title = RequestHandler.getString(request, "invoice_title");
			hsubcompany.setInvoice_title(invoice_title);
			
			String consignee = RequestHandler.getString(request, "consignee");
			hsubcompany.setConsignee(consignee);
			
			String consignee_phone = RequestHandler.getString(request, "consignee_phone");
			hsubcompany.setConsignee_phone(consignee_phone);
			
			String consignee_tel1 = RequestHandler.getString(request, "consignee_tel1");
			hsubcompany.setConsignee_tel1(consignee_tel1);
			
			String consignee_tel2 = RequestHandler.getString(request, "consignee_tel2");
			hsubcompany.setConsignee_tel2(consignee_tel2);
			
			String consignee_address = RequestHandler.getString(request, "consignee_address");
			hsubcompany.setConsignee_address(consignee_address);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hsubcompany.setCreate_time(create_time);
			
			Date update_time = RequestHandler.getDate(request, "update_time");
			hsubcompany.setUpdate_time(update_time);
			//新增2016-8-9 22:50:47
			String zip_code = RequestHandler.getString(request, "zip_code");
			hsubcompany.setZip_code(zip_code);
			
			String province_code = RequestHandler.getString(request, "province_code");
			hsubcompany.setProvince_code(province_code);
			String city_code = RequestHandler.getString(request, "city_code");
			hsubcompany.setCity_code(city_code);
			String area_code = RequestHandler.getString(request, "area_code");
			hsubcompany.setArea_code(area_code);

			List<HSubCompany> hsubcompanyList = hsubcompanyService.getHSubCompanyBaseList(hsubcompany);
		
			writeSuccessMsg("ok", hsubcompanyList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHSubCompany", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HSubCompany hsubcompany = new HSubCompany();
			
			Integer s_id = RequestHandler.getInteger(request, "s_id");
			hsubcompany.setS_id(s_id);
			Integer c_id = RequestHandler.getInteger(request, "c_id");
			hsubcompany.setC_id(c_id);
			String sub_name = RequestHandler.getString(request, "sub_name");
			hsubcompany.setSub_name(sub_name);
			String invoice_title = RequestHandler.getString(request, "invoice_title");
			hsubcompany.setInvoice_title(invoice_title);
			String consignee = RequestHandler.getString(request, "consignee");
			hsubcompany.setConsignee(consignee);
			String consignee_phone = RequestHandler.getString(request, "consignee_phone");
			hsubcompany.setConsignee_phone(consignee_phone);
			String consignee_tel1 = RequestHandler.getString(request, "consignee_tel1");
			hsubcompany.setConsignee_tel1(consignee_tel1);
			String consignee_tel2 = RequestHandler.getString(request, "consignee_tel2");
			hsubcompany.setConsignee_tel2(consignee_tel2);
			String consignee_address = RequestHandler.getString(request, "consignee_address");
			hsubcompany.setConsignee_address(consignee_address);
			Date create_time = RequestHandler.getDate(request, "create_time");
			hsubcompany.setCreate_time(create_time);
			Date update_time = RequestHandler.getDate(request, "update_time");
			hsubcompany.setUpdate_time(update_time);
			//新增 2016-8-9 22:50:59
			String zip_code = RequestHandler.getString(request, "zip_code");
			hsubcompany.setZip_code(zip_code);
			
			String province_code = RequestHandler.getString(request, "province_code");
			hsubcompany.setProvince_code(province_code);
			String city_code = RequestHandler.getString(request, "city_code");
			hsubcompany.setCity_code(city_code);
			String area_code = RequestHandler.getString(request, "area_code");
			hsubcompany.setArea_code(area_code);

			hsubcompanyService.insertHSubCompany(hsubcompany);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHSubCompany", method = RequestMethod.POST)
	public String updateHSubCompany(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HSubCompany hsubcompany = new HSubCompany();
			
			Integer s_id = RequestHandler.getInteger(request, "s_id");
			hsubcompany.setS_id(s_id);
			
			Integer c_id = RequestHandler.getInteger(request, "c_id");
			hsubcompany.setC_id(c_id);
			
			String sub_name = RequestHandler.getString(request, "sub_name");
			hsubcompany.setSub_name(sub_name);
			
			String invoice_title = RequestHandler.getString(request, "invoice_title");
			hsubcompany.setInvoice_title(invoice_title);
			
			String consignee = RequestHandler.getString(request, "consignee");
			hsubcompany.setConsignee(consignee);
			
			String consignee_phone = RequestHandler.getString(request, "consignee_phone");
			hsubcompany.setConsignee_phone(consignee_phone);
			
			String consignee_tel1 = RequestHandler.getString(request, "consignee_tel1");
			hsubcompany.setConsignee_tel1(consignee_tel1);
			
			String consignee_tel2 = RequestHandler.getString(request, "consignee_tel2");
			hsubcompany.setConsignee_tel2(consignee_tel2);
			
			String consignee_address = RequestHandler.getString(request, "consignee_address");
			hsubcompany.setConsignee_address(consignee_address);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hsubcompany.setCreate_time(create_time);
			
			Date update_time = RequestHandler.getDate(request, "update_time");
			hsubcompany.setUpdate_time(update_time);
			//新增2016-8-9 22:53:32
			String zip_code = RequestHandler.getString(request, "zip_code");
			hsubcompany.setZip_code(zip_code);
			
			String province_code = RequestHandler.getString(request, "province_code");
			hsubcompany.setProvince_code(province_code);
			String city_code = RequestHandler.getString(request, "city_code");
			hsubcompany.setCity_code(city_code);
			String area_code = RequestHandler.getString(request, "area_code");
			hsubcompany.setArea_code(area_code);

			hsubcompanyService.updateHSubCompany(hsubcompany);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHSubCompany", method = RequestMethod.POST)
	public String removeHSubCompany(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HSubCompany hsubcompany = new HSubCompany();
			Integer s_id = RequestHandler.getInteger(request, "s_id");
			hsubcompany.setS_id(s_id);

			hsubcompanyService.removeHSubCompany(hsubcompany);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHSubCompany", method = RequestMethod.POST)
	public String removeAllHSubCompany(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String s_ids = RequestHandler.getString(request, "s_ids");
			if(s_ids != null){
				String[] s_idStr = s_ids.split(",");
				if(s_idStr != null && s_idStr.length != 0){
					for (String s_id : s_idStr) {
						HSubCompany hSubCompany = new HSubCompany();
						hSubCompany.setS_id(Integer.valueOf(s_id));
						hsubcompanyService.removeHSubCompany(hSubCompany);
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
	
	@RequestMapping(value = "/toShowDB/{s_id}")
	public String toShowDB(@PathVariable Integer s_id,HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("s_id", s_id);
		return "/hSubCompany/showDB";
	}
	
	@RequestMapping(value = "/showDB")
	public String showDB(HttpServletRequest request, HttpServletResponse response, Model model){
		try{
			Integer s_id =  RequestHandler.getInteger(request, "s_id");
			HAmmeterInfo hammeterinfo = new HAmmeterInfo();
			hammeterinfo.setS_id(s_id);
			hammeterinfo.setDelete_state(1);
			int hammeterinfoListCount = hammeterinfoService.getHAmmeterInfoListCount(hammeterinfo);
			
			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hammeterinfo.setRowStart(from);
			hammeterinfo.setRowCount(rowCount);
			
			ResponseList<HAmmeterInfo> hammeterinfoList = null;
			if ( hammeterinfoListCount > 0 )
			{
				hammeterinfoList = hammeterinfoService.getHAmmeterInfoList(hammeterinfo);
				for(Object am : hammeterinfoList){
					HAmmeterInfo tmp = (HAmmeterInfo)am;
					JSONObject json = hCommonService.hXTServiceQuery(tmp.getAmmeter_no(),this.getIpAddr(request));
					if("00".equals(json.getString("resultCode"))){
						tmp.setElectric_address(json.getJSONObject("resultInfo").getString("address"));
						tmp.setNow_totalFee(json.getString("totalFee"));
						tmp.setAmmeter_name(json.getJSONObject("resultInfo").getString("accountName"));
						am = tmp;
					}
				}
			} else
			{
				hammeterinfoList = new ResponseList<HAmmeterInfo>();
			}
			// 设置数据总数
			hammeterinfoList.setTotalResults(hammeterinfoListCount);
			
			writeSuccessMsg("ok", hammeterinfoList, response);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取登陆这的IP地址
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }
	
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	public String exportExcel(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			HSubCompany hsubcompany = new HSubCompany();
			
			Integer s_id = RequestHandler.getInteger(request, "s_id");
			hsubcompany.setS_id(s_id);
			
			Integer c_id = RequestHandler.getInteger(request, "c_id");
			hsubcompany.setC_id(c_id);
			
			String contact_phone = RequestHandler.getString(request, "contact_phone");
			hsubcompany.setContact_phone(contact_phone);
			
			String sub_name = RequestHandler.getString(request, "sub_name");
			hsubcompany.setSub_name(sub_name);
			
			String invoice_title = RequestHandler.getString(request, "invoice_title");
			hsubcompany.setInvoice_title(invoice_title);
			
			String consignee = RequestHandler.getString(request, "consignee");
			hsubcompany.setConsignee(consignee);
			
			String consignee_phone = RequestHandler.getString(request, "consignee_phone");
			hsubcompany.setConsignee_phone(consignee_phone);
			
			String consignee_tel1 = RequestHandler.getString(request, "consignee_tel1");
			hsubcompany.setConsignee_tel1(consignee_tel1);
			
			String consignee_tel2 = RequestHandler.getString(request, "consignee_tel2");
			hsubcompany.setConsignee_tel2(consignee_tel2);
			
			String consignee_address = RequestHandler.getString(request, "consignee_address");
			hsubcompany.setConsignee_address(consignee_address);
			
			String c_name = RequestHandler.getString(request, "c_name");
			hsubcompany.setC_name(c_name);
			
			String ammeter_no = RequestHandler.getString(request, "ammeter_no");
			hsubcompany.setAmmeter_no(ammeter_no);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hsubcompany.setCreate_time(create_time);
			
			Date update_time = RequestHandler.getDate(request, "update_time");
			hsubcompany.setUpdate_time(update_time);
			//新增 2016-8-9 22:53:52
			String zip_code = RequestHandler.getString(request, "zip_code");
			hsubcompany.setZip_code(zip_code);
			
			String province_code = RequestHandler.getString(request, "province_code");
			hsubcompany.setProvince_code(province_code);
			String city_code = RequestHandler.getString(request, "city_code");
			hsubcompany.setCity_code(city_code);
			String area_code = RequestHandler.getString(request, "area_code");
			hsubcompany.setArea_code(area_code);


			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hsubcompany.setSortColumn(sortColumn);
			
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.YWY_ROLE_ID))){
				hsubcompany.setYw_id(Integer.valueOf(FileUploadConstants.YWY_ROLE_ID));
			}
			
			int hsubcompanyListCount = hsubcompanyService.getHSubCompanyListCount(hsubcompany);
			if ( hsubcompanyListCount > 0 )
			{
				List manageadminuserList = hsubcompanyService.getHSubCompanyBaseList(hsubcompany);
				LinkedList list = new LinkedList();
				list.addAll(manageadminuserList);
				LinkedList fields = new LinkedList();
				fields.add("c_name");
				fields.add("c_id");
				fields.add("contact_phone");
				fields.add("contact_name");
				fields.add("sub_name");
				fields.add("invoice_title");
				fields.add("consignee");
				fields.add("consignee_phone");
				fields.add("consignee_tel1");
				fields.add("consignee_tel2");
				fields.add("consignee_address");
				fields.add("zip_code");
				fields.add("province_name");
				fields.add("city_name");
				fields.add("area_name");
				fields.add("oneName");
				fields.add("twoName");
				fields.add("servicerName");
				
				fields.add("create_time");
				LinkedList titles = new LinkedList();
				titles.add("单位名称");
				titles.add("单位ID");
				titles.add("单位联系人手机");
				titles.add("单位联系人姓名");
				titles.add("子单位名称");
				titles.add("发票抬头");
				titles.add("收件人名称");
				titles.add("收件人手机");
				titles.add("收件人座机");
				titles.add("座机分机号");
				titles.add("收件人地址");
				titles.add("邮编");
				titles.add("省");
				titles.add("市");
				titles.add("区");
				titles.add("客服经理");
				titles.add("代理");
				titles.add("后台支持");
				
				titles.add("添加时间");
				String path = hCommonService.excleExport(list, fields, titles, HSubCompany.class, "子单位管理", request);
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
