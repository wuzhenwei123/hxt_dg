package com.hxt.hPublic.controller;

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

import com.hxt.hArea.model.HArea;
import com.hxt.hArea.service.HAreaService;
import com.hxt.hCity.model.HCity;
import com.hxt.hCity.service.HCityService;
import com.hxt.hOperator.model.HOperator;
import com.hxt.hOperator.service.HOperatorService;
import com.hxt.hOperatorDot.model.HOperatorDot;
import com.hxt.hOperatorDot.service.HOperatorDotService;
import com.hxt.hProvince.model.HProvince;
import com.hxt.hProvince.service.HProvinceService;
import com.base.utils.DateFormatToString;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SessionName;
import com.base.controller.BaseController;
/**
 * @author	zlb
 * @time	2016年08月06日 21:52:19
 */
@Controller
@RequestMapping("/public/dot")
public class OperatorDotController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HOperatorDotController.class); //Logger
	
	@Autowired
	private HOperatorDotService hoperatordotService = null;
	@Autowired
	private HOperatorService hoperatorService = null;//营业商
	@Autowired
	private HProvinceService hprovinceService = null;
	@Autowired
	private HCityService hcityService = null;
	@Autowired
	private HAreaService hareaService = null;
	/*****************页面中转*********************/
	
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String getHOperatorDotList(HttpServletRequest request, HttpServletResponse response, Model model,Integer p)
	{
		try
		{
			if( p == null){
				p = 1;
			}
			HOperatorDot hoperatordot = new HOperatorDot();
			String operator_name = RequestHandler.getString(request, "operator_name");
			hoperatordot.setOperator_name(operator_name);
			
			Integer operator_id = RequestHandler.getInteger(request, "operator_id");
			hoperatordot.setOperator_id(operator_id);
			
			String operator_level = RequestHandler.getString(request, "operator_level");
			hoperatordot.setOperator_level(operator_level);
			
			String window_name = RequestHandler.getString(request, "window_name");
			hoperatordot.setWindow_name(window_name);
			
			String name = RequestHandler.getString(request, "name");
			hoperatordot.setName(name);
			
			String province_name = RequestHandler.getString(request, "province_name");
			hoperatordot.setProvince_name(province_name);
			
			String province_code = RequestHandler.getString(request, "province_code");
			hoperatordot.setProvince_code(province_code);
			
			String city_code = RequestHandler.getString(request, "city_code");
			hoperatordot.setCity_code(city_code);
			
			String city_name = RequestHandler.getString(request, "city_name");
			hoperatordot.setCity_name(city_name);
			
			String area_code = RequestHandler.getString(request, "area_code");
			hoperatordot.setArea_code(area_code);
			
			String area_name = RequestHandler.getString(request, "area_name");
			hoperatordot.setArea_name(area_name);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hoperatordot.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hoperatordot.setCreateTime(createTime);
			
			Integer full_invoice = RequestHandler.getInteger(request, "full_invoice");
			hoperatordot.setFull_invoice(full_invoice);
			
			Integer add_invoice = RequestHandler.getInteger(request, "add_invoice");
			hoperatordot.setAdd_invoice(add_invoice);
			
			Integer adminId = RequestHandler.getInteger(request, "adminId");
			hoperatordot.setAdminId(adminId);
			
			Date lastTime = RequestHandler.getDate(request, "lastTime");
			hoperatordot.setLastTime(lastTime);
			
			Integer lastAdminId = RequestHandler.getInteger(request, "lastAdminId");
			hoperatordot.setLastAdminId(lastAdminId);
			
			String mobile = RequestHandler.getString(request, "mobile");
			hoperatordot.setMobile(mobile);
			
			String phone = RequestHandler.getString(request, "phone");
			hoperatordot.setPhone(phone);
			
			String phone_post = RequestHandler.getString(request, "phone_post");
			hoperatordot.setPhone_post(phone_post);
			
			Date work_start = RequestHandler.getDate(request, "work_start");
			hoperatordot.setWork_start(work_start);
			
			Date work_end = RequestHandler.getDate(request, "work_end");
			hoperatordot.setWork_end(work_end);
			
			String address = RequestHandler.getString(request, "address");
			hoperatordot.setAddress(address);
			

			// 分页开始
			// 分页开始
			Integer rowCount = 10;
			int from = RequestHandler.getFromByPage(p, rowCount);
			hoperatordot.setRowStart(from);
			hoperatordot.setRowCount(rowCount);
			// 分页结束
			// 排序
//			String sortColumn = RequestHandler.getString(request, "sortColumn");
//			hoperatordot.setSortColumn(sortColumn);
			
			int hoperatordotListCount = hoperatordotService.getHOperatorDotListCount(hoperatordot);
			ResponseList<HOperatorDot> hoperatordotList = null;
			if ( hoperatordotListCount > 0 )
			{
				hoperatordotList = hoperatordotService.getHOperatorDotList(hoperatordot);
			} else
			{
				hoperatordotList = new ResponseList<HOperatorDot>();
			}
			// 设置数据总数
			hoperatordotList.setTotalResults(hoperatordotListCount);
			
			model.addAttribute("newsList", hoperatordotList);
			model.addAttribute("newsCount", hoperatordotListCount);
			model.addAttribute("p", p);
			model.addAttribute("nav", "fuwu");
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return "/public/map";
	}
	
	@RequestMapping(value = "/getHOperatorDotBaseList", method = RequestMethod.GET)
	public String getHOperatorDotBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HOperatorDot hoperatordot = new HOperatorDot();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hoperatordot.setId(id);
			
			String operator_name = RequestHandler.getString(request, "operator_name");
			hoperatordot.setOperator_name(operator_name);
			
			Integer operator_id = RequestHandler.getInteger(request, "operator_id");
			hoperatordot.setOperator_id(operator_id);
			
			String operator_level = RequestHandler.getString(request, "operator_level");
			hoperatordot.setOperator_level(operator_level);
			
			String window_name = RequestHandler.getString(request, "window_name");
			hoperatordot.setWindow_name(window_name);
			
			String name = RequestHandler.getString(request, "name");
			hoperatordot.setName(name);
			
			String province_name = RequestHandler.getString(request, "province_name");
			hoperatordot.setProvince_name(province_name);
			
			String province_code = RequestHandler.getString(request, "province_code");
			hoperatordot.setProvince_code(province_code);
			
			String city_code = RequestHandler.getString(request, "city_code");
			hoperatordot.setCity_code(city_code);
			
			String city_name = RequestHandler.getString(request, "city_name");
			hoperatordot.setCity_name(city_name);
			
			String area_code = RequestHandler.getString(request, "area_code");
			hoperatordot.setArea_code(area_code);
			
			String area_name = RequestHandler.getString(request, "area_name");
			hoperatordot.setArea_name(area_name);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hoperatordot.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hoperatordot.setCreateTime(createTime);
			
			Integer full_invoice = RequestHandler.getInteger(request, "full_invoice");
			hoperatordot.setFull_invoice(full_invoice);
			
			Integer add_invoice = RequestHandler.getInteger(request, "add_invoice");
			hoperatordot.setAdd_invoice(add_invoice);
			
			Integer adminId = RequestHandler.getInteger(request, "adminId");
			hoperatordot.setAdminId(adminId);
			
			Date lastTime = RequestHandler.getDate(request, "lastTime");
			hoperatordot.setLastTime(lastTime);
			
			Integer lastAdminId = RequestHandler.getInteger(request, "lastAdminId");
			hoperatordot.setLastAdminId(lastAdminId);
			
			String mobile = RequestHandler.getString(request, "mobile");
			hoperatordot.setMobile(mobile);
			
			String phone = RequestHandler.getString(request, "phone");
			hoperatordot.setPhone(phone);
			
			String phone_post = RequestHandler.getString(request, "phone_post");
			hoperatordot.setPhone_post(phone_post);
			
			Date work_start = RequestHandler.getDate(request, "work_start");
			hoperatordot.setWork_start(work_start);
			
			Date work_end = RequestHandler.getDate(request, "work_end");
			hoperatordot.setWork_end(work_end);
			
			String address = RequestHandler.getString(request, "address");
			hoperatordot.setAddress(address);
			
			List<HOperatorDot> hoperatordotList = hoperatordotService.getHOperatorDotBaseList(hoperatordot);
		
			writeSuccessMsg("ok", hoperatordotList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHOperatorDot", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HOperatorDot hoperatordot = new HOperatorDot();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hoperatordot.setId(id);
			Integer operator_id = RequestHandler.getInteger(request, "operator_id");
			hoperatordot.setOperator_id(operator_id);
			
			if(operator_id != null){
				HOperator hOperator = new HOperator();
				hOperator.setId(operator_id);
				HOperator hOperator1 = hoperatorService.getHOperator(hOperator);
				
				String operator_name = hOperator1.getName();
				hoperatordot.setOperator_name(operator_name);
			}
			String operator_level = RequestHandler.getString(request, "operator_level");
			hoperatordot.setOperator_level(operator_level);
			String window_name = RequestHandler.getString(request, "window_name");
			hoperatordot.setWindow_name(window_name);
			String name = RequestHandler.getString(request, "name");
			hoperatordot.setName(name);
			String province_code = RequestHandler.getString(request, "province_code");
			hoperatordot.setProvince_code(province_code);
			if(province_code!=null){
				HProvince hProvince = new HProvince();
				hProvince.setProvinceCode(province_code);
				List<HProvince> plist = hprovinceService.getHProvinceBaseList(hProvince);
				if(plist!=null && plist.size() == 1){
					String province_name = plist.get(0).getProvinceName();
					hoperatordot.setProvince_name(province_name);
				}
			}
			hoperatordot.setProvince_code(province_code);
			String city_code = RequestHandler.getString(request, "city_code");
			hoperatordot.setCity_code(city_code);
			if(city_code!=null){
				HCity hCity = new HCity();
				hCity.setCityCode(city_code);
				List<HCity> clist = hcityService.getHCityBaseList(hCity);
				if(clist!=null && clist.size() == 1){
					String city_name = clist.get(0).getCityName();
					hoperatordot.setCity_name(city_name);
				}
			}
			String area_code = RequestHandler.getString(request, "area_code");
			hoperatordot.setArea_code(area_code);
			if(area_code!=null){
				HArea hArea = new HArea();
				hArea.setAreaCode(area_code);
				List<HArea> alist = hareaService.getHAreaBaseList(hArea);
				if(alist!=null && alist.size() == 1){
					String area_name = alist.get(0).getAreaName();
					hoperatordot.setArea_name(area_name);
				}
			}
			Integer state = RequestHandler.getInteger(request, "state");
			hoperatordot.setState(state);
			hoperatordot.setCreateTime(new Date());
			Integer full_invoice = RequestHandler.getInteger(request, "full_invoice");
			hoperatordot.setFull_invoice(full_invoice);
			Integer add_invoice = RequestHandler.getInteger(request, "add_invoice");
			hoperatordot.setAdd_invoice(add_invoice);
			
			Integer adminId = Integer.valueOf(getSession(request, SessionName.ADMIN_USER_ID).toString());
			hoperatordot.setAdminId(adminId);
			hoperatordot.setAdminName(getSession(request, SessionName.ADMIN_USER_NAME).toString());
			hoperatordot.setLastTime(new Date());
			hoperatordot.setLastAdminId(adminId);
			hoperatordot.setLastAdminName(getSession(request, SessionName.ADMIN_USER_NAME).toString());
			
			String mobile = RequestHandler.getString(request, "mobile");
			hoperatordot.setMobile(mobile);
			String phone = RequestHandler.getString(request, "phone");
			hoperatordot.setPhone(phone);
			String phone_post = RequestHandler.getString(request, "phone_post");
			hoperatordot.setPhone_post(phone_post);
			
			String work_start1 = RequestHandler.getString(request, "work_start");
			if(work_start1!=null){
				Date work_start = DateFormatToString.getDateByString(work_start1);
				hoperatordot.setWork_start(work_start);
			}
			String work_end1 = RequestHandler.getString(request, "work_end");
			if(work_end1!=null){
				
				Date work_end = DateFormatToString.getDateByString(work_end1);
				hoperatordot.setWork_end(work_end);
			}
			String address = RequestHandler.getString(request, "address");
			hoperatordot.setAddress(address);
				
			hoperatordotService.insertHOperatorDot(hoperatordot);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHOperatorDot", method = RequestMethod.POST)
	public String updateHOperatorDot(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HOperatorDot hoperatordot = new HOperatorDot();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hoperatordot.setId(id);
			
			Integer operator_id = RequestHandler.getInteger(request, "operator_id");
			hoperatordot.setOperator_id(operator_id);
			if(operator_id != null){
				HOperator hOperator = new HOperator();
				hOperator.setId(operator_id);
				HOperator hOperator1 = hoperatorService.getHOperator(hOperator);
				
				String operator_name = hOperator1.getName();
				hoperatordot.setOperator_name(operator_name);
			}
			
			String operator_level = RequestHandler.getString(request, "operator_level");
			hoperatordot.setOperator_level(operator_level);
			
			String window_name = RequestHandler.getString(request, "window_name");
			hoperatordot.setWindow_name(window_name);
			
			String name = RequestHandler.getString(request, "name");
			hoperatordot.setName(name);
			
			String province_code = RequestHandler.getString(request, "province_code");
			hoperatordot.setProvince_code(province_code);
			if(province_code!=null){
				HProvince hProvince = new HProvince();
				hProvince.setProvinceCode(province_code);
				List<HProvince> plist = hprovinceService.getHProvinceBaseList(hProvince);
				if(plist!=null && plist.size() == 1){
					String province_name = plist.get(0).getProvinceName();
					hoperatordot.setProvince_name(province_name);
				}
			}
			hoperatordot.setProvince_code(province_code);
			String city_code = RequestHandler.getString(request, "city_code");
			hoperatordot.setCity_code(city_code);
			if(city_code!=null){
				HCity hCity = new HCity();
				hCity.setCityCode(city_code);
				List<HCity> clist = hcityService.getHCityBaseList(hCity);
				if(clist!=null && clist.size() == 1){
					String city_name = clist.get(0).getCityName();
					hoperatordot.setCity_name(city_name);
				}
			}
			String area_code = RequestHandler.getString(request, "area_code");
			hoperatordot.setArea_code(area_code);
			if(area_code!=null){
				HArea hArea = new HArea();
				hArea.setAreaCode(area_code);
				List<HArea> alist = hareaService.getHAreaBaseList(hArea);
				if(alist!=null && alist.size() == 1){
					String area_name = alist.get(0).getAreaName();
					hoperatordot.setArea_name(area_name);
				}
			}
			
			Integer state = RequestHandler.getInteger(request, "state");
			hoperatordot.setState(state);
			
			Integer full_invoice = RequestHandler.getInteger(request, "full_invoice");
			hoperatordot.setFull_invoice(full_invoice);
			
			Integer add_invoice = RequestHandler.getInteger(request, "add_invoice");
			hoperatordot.setAdd_invoice(add_invoice);
			
			Integer adminId = Integer.valueOf(getSession(request, SessionName.ADMIN_USER_ID).toString());
			hoperatordot.setLastTime(new Date());
			hoperatordot.setLastAdminId(adminId);
			hoperatordot.setLastAdminName(getSession(request, SessionName.ADMIN_USER_NAME).toString());
			
			String mobile = RequestHandler.getString(request, "mobile");
			hoperatordot.setMobile(mobile);
			
			String phone = RequestHandler.getString(request, "phone");
			hoperatordot.setPhone(phone);
			
			String phone_post = RequestHandler.getString(request, "phone_post");
			hoperatordot.setPhone_post(phone_post);
			
			String work_start1 = RequestHandler.getString(request, "work_start");
			if(work_start1!=null){
				Date work_start = DateFormatToString.getDateByString(work_start1);
				hoperatordot.setWork_start(work_start);
			}
			String work_end1 = RequestHandler.getString(request, "work_end");
			if(work_end1!=null){
				
				Date work_end = DateFormatToString.getDateByString(work_end1);
				hoperatordot.setWork_end(work_end);
			}
			
			String address = RequestHandler.getString(request, "address");
			hoperatordot.setAddress(address);
			

			hoperatordotService.updateHOperatorDot(hoperatordot);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHOperatorDot", method = RequestMethod.POST)
	public String removeHOperatorDot(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HOperatorDot hoperatordot = new HOperatorDot();
			Integer id = RequestHandler.getInteger(request, "id");
			hoperatordot.setId(id);

			hoperatordotService.removeHOperatorDot(hoperatordot);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHOperatorDot", method = RequestMethod.POST)
	public String removeAllHOperatorDot(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HOperatorDot hOperatorDot = new HOperatorDot();
						hOperatorDot.setId(Integer.valueOf(id));
						hoperatordotService.removeHOperatorDot(hOperatorDot);
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
