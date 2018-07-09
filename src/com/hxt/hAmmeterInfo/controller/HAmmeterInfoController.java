package com.hxt.hAmmeterInfo.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.base.controller.BaseController;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SessionName;
import com.hxt.hAmmeterInfo.model.HAmmeterInfo;
import com.hxt.hAmmeterInfo.service.HAmmeterInfoService;
import com.hxt.hArea.service.HAreaService;
import com.hxt.hCommon.service.HCommonService;
import com.hxt.hPayOrder.model.HPayOrder;
import com.sys.manageAdminUser.model.ManageAdminUser;
/**
 * @author	zhanglibo
 * @time	2015年11月18日 00:55:00
 */
@Controller
@RequestMapping("/hAmmeterInfo")
public class HAmmeterInfoController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HAmmeterInfoController.class); //Logger
	
	@Autowired
	private HAmmeterInfoService hammeterinfoService = null;
	@Autowired
	private HCommonService hcommonservice = null;
	@Autowired
	private HAreaService hareaService = null;
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hAmmeterInfo/hAmmeterInfoIndex";
	}
	@RequestMapping(value = "/toShouJi", method = RequestMethod.GET)
	public String toShouJi(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hAmmeterInfo/shouji";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hAmmeterInfo/hAmmeterInfoAdd";
	}
	@RequestMapping(value = "/toProfitList", method = RequestMethod.GET)
	public String toProfitList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hAmmeterInfo/profitList";
	}
	@RequestMapping(value = "/toUpdate/{a_id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer a_id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		HAmmeterInfo hammeterinfo1 = new HAmmeterInfo();
		hammeterinfo1.setA_id(a_id);
		List<HAmmeterInfo> hammeterinfo = hammeterinfoService.getHAmmeterInfoListSql(hammeterinfo1);
		if(hammeterinfo!=null&&hammeterinfo.size()>0){
			//调单
			JSONObject result = hcommonservice.hXTServiceQuery(hammeterinfo.get(0).getAmmeter_no(), super.getIpAddr(request));
			if("00".equals(result.get("resultCode"))){
				HAmmeterInfo hAmmeterInfo = hammeterinfo.get(0);
				hAmmeterInfo.setAmmeter_address(result.getJSONObject("resultInfo").getString("address"));
				hAmmeterInfo.setAmmeter_name(result.getJSONObject("resultInfo").getString("accountName"));
				model.addAttribute("hammeterinfo", hAmmeterInfo);
			}else{
				model.addAttribute("message", "缴费号已经不存在");
			}
		}
		return "/hAmmeterInfo/hAmmeterInfoUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHAmmeterInfoList", method = RequestMethod.GET)
	public String getHAmmeterInfoList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAmmeterInfo hammeterinfo = new HAmmeterInfo();
			
			Integer a_id = RequestHandler.getInteger(request, "a_id");
			hammeterinfo.setA_id(a_id);
			
			Integer c_id = RequestHandler.getInteger(request, "c_id");
			hammeterinfo.setC_id(c_id);
			
			Integer s_id = RequestHandler.getInteger(request, "s_id");
			hammeterinfo.setS_id(s_id);
			
			String ammeter_no = RequestHandler.getString(request, "ammeter_no");
			hammeterinfo.setAmmeter_no(ammeter_no);
			
			String adminName = RequestHandler.getString(request, "adminName");
			hammeterinfo.setAdminName(adminName);
			
			String contact_phone = RequestHandler.getString(request, "contact_phone");
			hammeterinfo.setContact_phone(contact_phone);
			
			String ammeter_type = RequestHandler.getString(request, "ammeter_type");
			hammeterinfo.setAmmeter_type(ammeter_type);

			String ammeter_name = RequestHandler.getString(request, "ammeter_name");
			hammeterinfo.setAmmeter_name(ammeter_name);
			
			String ammeter_address = RequestHandler.getString(request, "ammeter_address");
			hammeterinfo.setAmmeter_address(ammeter_address);
			
			Integer last_pay_day = RequestHandler.getInteger(request, "last_pay_day");
			hammeterinfo.setLast_pay_day(last_pay_day);
			
			Integer fp_style = RequestHandler.getInteger(request, "fp_style");
			hammeterinfo.setFp_style(fp_style);
			
			String pay_status = RequestHandler.getString(request, "pay_status");
			hammeterinfo.setPay_status(pay_status);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hammeterinfo.setCreate_time(create_time);
			
			Date update_time = RequestHandler.getDate(request, "update_time");
			hammeterinfo.setUpdate_time(update_time);
			String proCode = RequestHandler.getString(request, "province_code");
			hammeterinfo.setProvince_code(proCode);
			String cityCode = RequestHandler.getString(request, "city_code");
			hammeterinfo.setCity_code(cityCode);
			String areaCode = RequestHandler.getString(request, "area_code");
			hammeterinfo.setArea_code(areaCode);
			Integer delState = RequestHandler.getInteger(request, "delete_state");
			hammeterinfo.setDelete_state(delState);
			Integer proxy_flag = RequestHandler.getInteger(request, "proxy_flag");
			hammeterinfo.setProxy_flag(proxy_flag);
			
			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hammeterinfo.setRowStart(from);
			hammeterinfo.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hammeterinfo.setSortColumn(sortColumn);
			
			int hammeterinfoListCount = hammeterinfoService.getHAmmeterInfoListCount(hammeterinfo);
			ResponseList<HAmmeterInfo> hammeterinfoList = null;
			if ( hammeterinfoListCount > 0 )
			{
				hammeterinfoList = hammeterinfoService.getHAmmeterInfoList(hammeterinfo);
			} else
			{
				hammeterinfoList = new ResponseList<HAmmeterInfo>();
			}
			// 设置数据总数
			hammeterinfoList.setTotalResults(hammeterinfoListCount);
			
			writeSuccessMsg("ok", hammeterinfoList, response);
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/shoujiList", method = RequestMethod.GET)
	public String shoujiList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAmmeterInfo hammeterinfo = new HAmmeterInfo();
			
			Integer a_id = RequestHandler.getInteger(request, "a_id");
			hammeterinfo.setA_id(a_id);
			
			Integer c_id = RequestHandler.getInteger(request, "c_id");
			hammeterinfo.setC_id(c_id);
			
			Integer s_id = RequestHandler.getInteger(request, "s_id");
			hammeterinfo.setS_id(s_id);
			
			String ammeter_no = RequestHandler.getString(request, "ammeter_no");
			hammeterinfo.setAmmeter_no(ammeter_no);
			
			String adminName = RequestHandler.getString(request, "adminName");
			hammeterinfo.setAdminName(adminName);
			
			String contact_phone = RequestHandler.getString(request, "contact_phone");
			hammeterinfo.setContact_phone(contact_phone);
			
			String ammeter_type = RequestHandler.getString(request, "ammeter_type");
			hammeterinfo.setAmmeter_type(ammeter_type);
			
			Integer last_pay_day = RequestHandler.getInteger(request, "last_pay_day");
			hammeterinfo.setLast_pay_day(last_pay_day);
			
			String pay_status = RequestHandler.getString(request, "pay_status");
			hammeterinfo.setPay_status(pay_status);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hammeterinfo.setCreate_time(create_time);
			
			Date update_time = RequestHandler.getDate(request, "update_time");
			hammeterinfo.setUpdate_time(update_time);
			String proCode = RequestHandler.getString(request, "province_code");
			hammeterinfo.setProvince_code(proCode);
			String cityCode = RequestHandler.getString(request, "city_code");
			hammeterinfo.setCity_code(cityCode);
			String areaCode = RequestHandler.getString(request, "area_code");
			hammeterinfo.setArea_code(areaCode);
			Integer delState = RequestHandler.getInteger(request, "delete_state");
			hammeterinfo.setDelete_state(delState);
			Integer proxy_flag = RequestHandler.getInteger(request, "proxy_flag");
			hammeterinfo.setProxy_flag(proxy_flag);
			
			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hammeterinfo.setRowStart(from);
			hammeterinfo.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hammeterinfo.setSortColumn(sortColumn);
			
			int hammeterinfoListCount = hammeterinfoService.getHAmmeterInfoListCount1(hammeterinfo);
			ResponseList<HAmmeterInfo> hammeterinfoList = null;
			if ( hammeterinfoListCount > 0 )
			{
				hammeterinfoList = hammeterinfoService.getHAmmeterInfoList1(hammeterinfo);
			} else
			{
				hammeterinfoList = new ResponseList<HAmmeterInfo>();
			}
			// 设置数据总数
			hammeterinfoList.setTotalResults(hammeterinfoListCount);
			
			writeSuccessMsg("ok", hammeterinfoList, response);
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHAmmeterInfoBaseList", method = RequestMethod.GET)
	public String getHAmmeterInfoBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAmmeterInfo hammeterinfo = new HAmmeterInfo();
			
			Integer a_id = RequestHandler.getInteger(request, "a_id");
			hammeterinfo.setA_id(a_id);
			
			Integer c_id = RequestHandler.getInteger(request, "c_id");
			hammeterinfo.setC_id(c_id);
			
			Integer s_id = RequestHandler.getInteger(request, "s_id");
			hammeterinfo.setS_id(s_id);
			
			String ammeter_no = RequestHandler.getString(request, "ammeter_no");
			hammeterinfo.setAmmeter_no(ammeter_no);
			
			String ammeter_type = RequestHandler.getString(request, "ammeter_type");
			hammeterinfo.setAmmeter_type(ammeter_type);
			
			Integer last_pay_day = RequestHandler.getInteger(request, "last_pay_day");
			hammeterinfo.setLast_pay_day(last_pay_day);
			
			String pay_status = RequestHandler.getString(request, "pay_status");
			hammeterinfo.setPay_status(pay_status);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hammeterinfo.setCreate_time(create_time);
			
			Date update_time = RequestHandler.getDate(request, "update_time");
			hammeterinfo.setUpdate_time(update_time);
			
			List<HAmmeterInfo> hammeterinfoList = hammeterinfoService.getHAmmeterInfoBaseList(hammeterinfo);
		
			writeSuccessMsg("ok", hammeterinfoList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getAmmeterInfo", method = RequestMethod.POST)
	public String getAmmeterInfo(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			String number = RequestHandler.getString(request, "ammeterNum");
			String ip = this.getIpAddr(request);
			JSONObject json = new JSONObject();
			if(StringUtils.isNotBlank(number))
				json = hcommonservice.hXTServiceQuery(number, ip);
			writeSuccessMsg("添加成功", json, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHAmmeterInfo", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			/*a_id : a_id,
    		last_pay_day : last_pay_day,
    		fp_style : fp_style,
    		ammeter_address : ammeter_address,
    		province_code : province_code,
    		city_code : city_code,
    		area_code : area_code,
    		province_name : province_name,
    		city_name : city_name,
    		area_name : area_name,
    		profit_id : profit_id,*/
			HAmmeterInfo hammeterinfo = new HAmmeterInfo();
			
			Integer a_id = RequestHandler.getInteger(request, "a_id");
			hammeterinfo.setA_id(a_id);
			Integer c_id = RequestHandler.getInteger(request, "c_id");
			hammeterinfo.setC_id(c_id);
			Integer s_id = RequestHandler.getInteger(request, "s_id");
			hammeterinfo.setS_id(s_id);
			String ammeter_no = RequestHandler.getString(request, "ammeter_no");
			hammeterinfo.setAmmeter_no(ammeter_no);
			String ammeter_type = RequestHandler.getString(request, "ammeter_type");
			hammeterinfo.setAmmeter_type(ammeter_type);
			Integer last_pay_day = RequestHandler.getInteger(request, "last_pay_day");
			hammeterinfo.setLast_pay_day(last_pay_day);
			String pay_status = RequestHandler.getString(request, "pay_status");
			hammeterinfo.setPay_status(pay_status);
			hammeterinfo.setCreate_time(new Date());
			String ammeter_name = RequestHandler.getString(request, "ammeter_name");
			hammeterinfo.setAmmeter_name(ammeter_name);
			ManageAdminUser adminUser = (ManageAdminUser) getSession(request, SessionName.ADMIN_USER);
			hammeterinfo.setOperator_id(adminUser.getAdminId());
			Integer fp_style = RequestHandler.getInteger(request, "fp_style");
			hammeterinfo.setFp_style(fp_style);
			String ammeter_address = RequestHandler.getString(request, "ammeter_address");
			hammeterinfo.setAmmeter_address(ammeter_address);
			String province_code = RequestHandler.getString(request, "province_code");
			hammeterinfo.setProvince_code(province_code);
			String city_code = RequestHandler.getString(request, "city_code");
			hammeterinfo.setCity_code(city_code);
			String area_code = RequestHandler.getString(request, "area_code");
			hammeterinfo.setArea_code(area_code);
			String province_name = RequestHandler.getString(request, "province_name");
			String city_name = RequestHandler.getString(request, "city_name");
			String area_name = RequestHandler.getString(request, "province_name");
			Integer profit_id= RequestHandler.getInteger(request, "profit_id");
			Integer proxy_flag= RequestHandler.getInteger(request, "proxy_flag");
			hammeterinfo.setProvince_name(province_name);
			hammeterinfo.setCity_name(city_name);
			hammeterinfo.setArea_name(area_name);
			hammeterinfo.setProfit_id(profit_id);
			hammeterinfo.setDelete_state(1);
			hammeterinfo.setIs_payed(0);
			hammeterinfo.setAmmeterinfo_type(1);
			hammeterinfo.setProxy_flag(proxy_flag);
			if(!StringUtils.isNotBlank(province_code)&&!StringUtils.isNotBlank(city_code)&&!StringUtils.isNotBlank(area_code)){
				hammeterinfo = hareaService.setAreaCodeToAmmeterInfo(hammeterinfo);
			}
			
			hammeterinfoService.insertHAmmeterInfo(hammeterinfo);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHAmmeterInfo", method = RequestMethod.POST)
	public String updateHAmmeterInfo(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAmmeterInfo hammeterinfo = new HAmmeterInfo();
			
			Integer a_id = RequestHandler.getInteger(request, "a_id");
			hammeterinfo.setA_id(a_id);
			
			Integer c_id = RequestHandler.getInteger(request, "c_id");
			hammeterinfo.setC_id(c_id);
			
			Integer s_id = RequestHandler.getInteger(request, "s_id");
			hammeterinfo.setS_id(s_id);
			
			String ammeter_no = RequestHandler.getString(request, "ammeter_no");
			hammeterinfo.setAmmeter_no(ammeter_no);
			
			String ammeter_type = RequestHandler.getString(request, "ammeter_type");
			hammeterinfo.setAmmeter_type(ammeter_type);
			
			Integer last_pay_day = RequestHandler.getInteger(request, "last_pay_day");
			hammeterinfo.setLast_pay_day(last_pay_day);
			
			String pay_status = RequestHandler.getString(request, "pay_status");
			hammeterinfo.setPay_status(pay_status);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hammeterinfo.setCreate_time(create_time);
			
			hammeterinfo.setUpdate_time(new Date());
			String ammeter_name = RequestHandler.getString(request, "ammeter_name");
			hammeterinfo.setAmmeter_name(ammeter_name);
			ManageAdminUser adminUser = (ManageAdminUser) getSession(request, SessionName.ADMIN_USER);
			hammeterinfo.setOperator_id(adminUser.getAdminId());
			Integer fp_style = RequestHandler.getInteger(request, "fp_style");
			hammeterinfo.setFp_style(fp_style);
			String ammeter_address = RequestHandler.getString(request, "ammeter_address");
			hammeterinfo.setAmmeter_address(ammeter_address);
			String province_code = RequestHandler.getString(request, "province_code");
			hammeterinfo.setProvince_code(province_code);
			String city_code = RequestHandler.getString(request, "city_code");
			hammeterinfo.setCity_code(city_code);
			String area_code = RequestHandler.getString(request, "area_code");
			hammeterinfo.setArea_code(area_code);
			String province_name = RequestHandler.getString(request, "province_name");
			String city_name = RequestHandler.getString(request, "city_name");
			String area_name = RequestHandler.getString(request, "area_name");
			Integer profit_id= RequestHandler.getInteger(request, "profit_id");
			Integer proxy_flag= RequestHandler.getInteger(request, "proxy_flag");
			hammeterinfo.setProvince_name(province_name);
			hammeterinfo.setCity_name(city_name);
			hammeterinfo.setArea_name(area_name);
			hammeterinfo.setProfit_id(profit_id);
			hammeterinfo.setProxy_flag(proxy_flag);
			
			if(!StringUtils.isNotBlank(province_code)&&!StringUtils.isNotBlank(city_code)&&!StringUtils.isNotBlank(area_code)){
				hammeterinfo = hareaService.setAreaCodeToAmmeterInfo(hammeterinfo);
			}

			hammeterinfoService.updateHAmmeterInfo(hammeterinfo);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	//TODO 改为逻辑删除
	@RequestMapping(value = "/removeHAmmeterInfo", method = RequestMethod.POST)
	public String removeHAmmeterInfo(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAmmeterInfo hammeterinfo = new HAmmeterInfo();
			Integer a_id = RequestHandler.getInteger(request, "a_id");
			hammeterinfo.setA_id(a_id);
			hammeterinfoService.removeHAmmeterInfo(hammeterinfo);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHAmmeterInfo", method = RequestMethod.POST)
	public String removeAllHAmmeterInfo(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String a_ids = RequestHandler.getString(request, "a_ids");
			if(a_ids != null){
				String[] a_idStr = a_ids.split(",");
				if(a_idStr != null && a_idStr.length != 0){
					for (String a_id : a_idStr) {
						HAmmeterInfo hAmmeterInfo = new HAmmeterInfo();
						hAmmeterInfo.setA_id(Integer.valueOf(a_id));
						hammeterinfoService.removeHAmmeterInfo(hAmmeterInfo);
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
	
	@RequestMapping(value = "/saveFPStyle", method = RequestMethod.POST)
	public String saveFPStyle(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		Integer s_id = RequestHandler.getInteger(request, "s_id");
		Integer fpStyle = RequestHandler.getInteger(request, "fpStyle");
		String ammeter_no = RequestHandler.getString(request, "ammeter_no");
		try{
			HAmmeterInfo hAmmeterInfo = new HAmmeterInfo();
			hAmmeterInfo.setS_id(s_id);
			hAmmeterInfo.setAmmeter_no(ammeter_no);
			hAmmeterInfo.setPay_status("1");
			hAmmeterInfo.setDelete_state(1);
			hAmmeterInfo = hammeterinfoService.getHAmmeterInfo(hAmmeterInfo);
			hAmmeterInfo.setFp_style(fpStyle);
			hammeterinfoService.updateHAmmeterInfo(hAmmeterInfo);
			writeSuccessMsg("成功", null, response);
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
	public String exportExcel(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAmmeterInfo hammeterinfo = new HAmmeterInfo();
			
			Integer a_id = RequestHandler.getInteger(request, "a_id");
			hammeterinfo.setA_id(a_id);
			
			Integer c_id = RequestHandler.getInteger(request, "c_id");
			hammeterinfo.setC_id(c_id);
			
			Integer s_id = RequestHandler.getInteger(request, "s_id");
			hammeterinfo.setS_id(s_id);
			
			String ammeter_no = RequestHandler.getString(request, "ammeter_no");
			hammeterinfo.setAmmeter_no(ammeter_no);
			
			String adminName = RequestHandler.getString(request, "adminName");
			hammeterinfo.setAdminName(adminName);
			
			String contact_phone = RequestHandler.getString(request, "contact_phone");
			hammeterinfo.setContact_phone(contact_phone);
			
			String ammeter_type = RequestHandler.getString(request, "ammeter_type");
			hammeterinfo.setAmmeter_type(ammeter_type);

			String ammeter_name = RequestHandler.getString(request, "ammeter_name");
			hammeterinfo.setAmmeter_name(ammeter_name);
			
			String ammeter_address = RequestHandler.getString(request, "ammeter_address");
			hammeterinfo.setAmmeter_address(ammeter_address);
			
			Integer last_pay_day = RequestHandler.getInteger(request, "last_pay_day");
			hammeterinfo.setLast_pay_day(last_pay_day);
			
			Integer fp_style = RequestHandler.getInteger(request, "fp_style");
			hammeterinfo.setFp_style(fp_style);
			
			String pay_status = RequestHandler.getString(request, "pay_status");
			hammeterinfo.setPay_status(pay_status);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hammeterinfo.setCreate_time(create_time);
			
			Date update_time = RequestHandler.getDate(request, "update_time");
			hammeterinfo.setUpdate_time(update_time);
			String proCode = RequestHandler.getString(request, "province_code");
			hammeterinfo.setProvince_code(proCode);
			String cityCode = RequestHandler.getString(request, "city_code");
			hammeterinfo.setCity_code(cityCode);
			String areaCode = RequestHandler.getString(request, "area_code");
			hammeterinfo.setArea_code(areaCode);
			Integer delState = RequestHandler.getInteger(request, "delete_state");
			hammeterinfo.setDelete_state(delState);
			Integer proxy_flag = RequestHandler.getInteger(request, "proxy_flag");
			hammeterinfo.setProxy_flag(proxy_flag);
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hammeterinfo.setSortColumn(sortColumn);
			
			int hammeterinfoListCount = hammeterinfoService.getHAmmeterInfoListToExportCount(hammeterinfo);
			List<HAmmeterInfo> hammeterinfoList = null;
			if ( hammeterinfoListCount > 0 )
			{
				hammeterinfoList = hammeterinfoService.getHAmmeterInfoListToExport(hammeterinfo);
				LinkedList<HAmmeterInfo> list = new LinkedList<HAmmeterInfo>();
				list.addAll(hammeterinfoList);
				LinkedList<String> fields = new LinkedList<String>();
				/*str+= "<th scope=\"col\" class=\"check-col\"><input id=\"checkAllBtn\" type='checkbox' class='checkbox check-all' value='ON' onclick=\"checkAll('checkAllBtn','checkName');\" name='check-all'></th>";
		    	str+= "<th onselectstart='return false' scope=\"col\">序号</th>";
		    	str+= "<th class=\"sortTh\" id=\"th_ammeter_no\" column='ammeter_no' onselectstart='return false' scope=\"col\">缴费号</th>";
		    	str+= "<th class=\"sortTh\" id=\"th_ammeter_name\" column='ammeter_name' onselectstart='return false' scope=\"col\">客户名称</th>";
		    	str+= "<th class=\"sortTh\" id=\"th_ammeter_type\" column='ammeter_type' onselectstart='return false' scope=\"col\">电表类型</th>";
		    	str+= "<th class=\"sortTh\" id=\"th_last_pay_day\" column='last_pay_day' onselectstart='return false' scope=\"col\">最后缴费时间</th>";
		    	str+= "<th class=\"sortTh\" id=\"th_pay_status\" column='pay_status' onselectstart='return false' scope=\"col\">缴费状态</th>";
		    	str+= "<th class=\"sortTh\" id=\"th_fp_style\" column='fp_style' onselectstart='return false' scope=\"col\">发票类型</th>";
		    	str+= "<th class=\"sortTh\" id=\"th_ammeter_address\" column='ammeter_address' onselectstart='return false' scope=\"col\">客户详细地址</th>";
		    	str+= "<th class=\"sortTh\" id=\"th_province_name\" column='province_name' onselectstart='return false' scope=\"col\">省份</th>";
		    	str+= "<th class=\"sortTh\" id=\"th_city_name\" column='city_name' onselectstart='return false' scope=\"col\">城市</th>";
		    	str+= "<th class=\"sortTh\" id=\"th_area_name\" column='area_name' onselectstart='return false' scope=\"col\">地区</th>";
		    	str+= "<th class=\"sortTh\" id=\"th_profit_id\" column='profit_id' onselectstart='return false' scope=\"col\">一级代理分润</th>";
		    	str+= "<th class=\"sortTh\" id=\"th_profit_id\" column='profit_id' onselectstart='return false' scope=\"col\">二级代理分润</th>";
		    	str+= "<th class=\"sortTh\" id=\"th_profit_id\" column='profit_id' onselectstart='return false' scope=\"col\">服务人员分润</th>";
		    	str+= "<th class=\"sortTh\" id=\"th_delete_state\" column='delete_state' onselectstart='return false' scope=\"col\">是否删除</th>";*/
				fields.add("ammeter_no");
				fields.add("ammeter_name");
				fields.add("ammeter_type");
				fields.add("pay_status_str");
				fields.add("fp_style");
				fields.add("ammeter_address");
				fields.add("province_name");
				fields.add("city_name");
				fields.add("area_name");
				fields.add("oneAgentName");
				fields.add("profit_one");
				fields.add("twoAgentName");
				fields.add("profit_two");
				fields.add("servicerName");
				fields.add("profit_servicer");
				fields.add("delete_state_str");
				fields.add("adminName");
				fields.add("contact_phone");
				fields.add("companyName");
				LinkedList<String> titles = new LinkedList<String>();
				titles.add("缴费号");
				titles.add("客户名称");
				titles.add("电表类型");
				titles.add("缴费状态");
				titles.add("发票类型");
				titles.add("客户详细地址");
				titles.add("省份");
				titles.add("城市");
				titles.add("地区");
				titles.add("客户经理");
				titles.add("客户经理分润");
				titles.add("代理");
				titles.add("代理分润");
				titles.add("支持人员");
				titles.add("支持人员分润");
				titles.add("是否删除");
				titles.add("单位登录账户");
				titles.add("单位联系人手机");
				titles.add("单位名称");
				String path = hcommonservice.excleExport(list, fields, titles, HAmmeterInfo.class, "缴费号管理",request);
				writeSuccessMsg("成功", path, response);
			} else
			{
				writeErrorMsg("无可导出内容", null, response);
			}
			// 设置数据总数
			
			writeSuccessMsg("ok", hammeterinfoList, response);
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
}
