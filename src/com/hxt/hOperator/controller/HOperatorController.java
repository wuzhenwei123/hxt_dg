package com.hxt.hOperator.controller;

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
import com.hxt.hProvince.model.HProvince;
import com.hxt.hProvince.service.HProvinceService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SessionName;
import com.base.controller.BaseController;
/**
 * @author	zlb
 * @time	2016年08月05日 14:30:13
 */
@Controller
@RequestMapping("/hOperator")
public class HOperatorController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HOperatorController.class); //Logger
	
	@Autowired
	private HOperatorService hoperatorService = null;
	@Autowired
	private HProvinceService hprovinceService = null;
	@Autowired
	private HCityService hcityService = null;
	@Autowired
	private HAreaService hareaService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hOperator/hOperatorIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hOperator/hOperatorAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HOperator hoperator1 = new HOperator();
		hoperator1.setId(id);
		HOperator hoperator = hoperatorService.getHOperator(hoperator1);
		model.addAttribute("hoperator", hoperator);
		
		return "/hOperator/hOperatorUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHOperatorList", method = RequestMethod.GET)
	public String getHOperatorList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HOperator hoperator = new HOperator();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hoperator.setId(id);
			
			String full_name = RequestHandler.getString(request, "full_name");
			hoperator.setFull_name(full_name);
			
			String name = RequestHandler.getString(request, "name");
			hoperator.setName(name);
			
			String province_name = RequestHandler.getString(request, "province_name");
			hoperator.setProvince_name(province_name);
			
			String province_code = RequestHandler.getString(request, "province_code");
			hoperator.setProvince_code(province_code);
			
			String city_code = RequestHandler.getString(request, "city_code");
			hoperator.setCity_code(city_code);
			
			String city_name = RequestHandler.getString(request, "city_name");
			hoperator.setCity_name(city_name);
			
			String area_code = RequestHandler.getString(request, "area_code");
			hoperator.setArea_code(area_code);
			
			String area_name = RequestHandler.getString(request, "area_name");
			hoperator.setArea_name(area_name);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hoperator.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hoperator.setCreateTime(createTime);
			
			Integer adminId = RequestHandler.getInteger(request, "adminId");
			hoperator.setAdminId(adminId);
			
			Date lastTime = RequestHandler.getDate(request, "lastTime");
			hoperator.setLastTime(lastTime);
			
			Integer lastAdminId = RequestHandler.getInteger(request, "lastAdminId");
			hoperator.setLastAdminId(lastAdminId);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hoperator.setRowStart(from);
			hoperator.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hoperator.setSortColumn(sortColumn);
			
			int hoperatorListCount = hoperatorService.getHOperatorListCount(hoperator);
			ResponseList<HOperator> hoperatorList = null;
			if ( hoperatorListCount > 0 )
			{
				hoperatorList = hoperatorService.getHOperatorList(hoperator);
			} else
			{
				hoperatorList = new ResponseList<HOperator>();
			}
			// 设置数据总数
			hoperatorList.setTotalResults(hoperatorListCount);
			
			writeSuccessMsg("ok", hoperatorList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHOperatorBaseList", method = RequestMethod.GET)
	public String getHOperatorBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HOperator hoperator = new HOperator();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hoperator.setId(id);
			
			String full_name = RequestHandler.getString(request, "full_name");
			hoperator.setFull_name(full_name);
			
			String name = RequestHandler.getString(request, "name");
			hoperator.setName(name);
			
			String province_name = RequestHandler.getString(request, "province_name");
			hoperator.setProvince_name(province_name);
			
			String province_code = RequestHandler.getString(request, "province_code");
			hoperator.setProvince_code(province_code);
			
			String city_code = RequestHandler.getString(request, "city_code");
			hoperator.setCity_code(city_code);
			
			String city_name = RequestHandler.getString(request, "city_name");
			hoperator.setCity_name(city_name);
			
			String area_code = RequestHandler.getString(request, "area_code");
			hoperator.setArea_code(area_code);
			
			String area_name = RequestHandler.getString(request, "area_name");
			hoperator.setArea_name(area_name);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hoperator.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hoperator.setCreateTime(createTime);
			
			Integer adminId = RequestHandler.getInteger(request, "adminId");
			hoperator.setAdminId(adminId);
			
			Date lastTime = RequestHandler.getDate(request, "lastTime");
			hoperator.setLastTime(lastTime);
			
			Integer lastAdminId = RequestHandler.getInteger(request, "lastAdminId");
			hoperator.setLastAdminId(lastAdminId);
			
			List<HOperator> hoperatorList = hoperatorService.getHOperatorBaseList(hoperator);
		
			writeSuccessMsg("ok", hoperatorList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHOperator", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HOperator hoperator = new HOperator();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hoperator.setId(id);
			String full_name = RequestHandler.getString(request, "full_name");
			hoperator.setFull_name(full_name);
			String name = RequestHandler.getString(request, "name");
			hoperator.setName(name);
			String province_code = RequestHandler.getString(request, "province_code");
			hoperator.setProvince_code(province_code);
			if(province_code!=null){
				HProvince hProvince = new HProvince();
				hProvince.setProvinceCode(province_code);
				List<HProvince> plist = hprovinceService.getHProvinceBaseList(hProvince);
				if(plist!=null && plist.size() == 1){
					String province_name = plist.get(0).getProvinceName();
					hoperator.setProvince_name(province_name);
				}
			}
			
			String city_code = RequestHandler.getString(request, "city_code");
			hoperator.setCity_code(city_code);
			if(city_code!=null){
				HCity hCity = new HCity();
				hCity.setCityCode(city_code);
				List<HCity> clist = hcityService.getHCityBaseList(hCity);
				if(clist!=null && clist.size() == 1){
					String city_name = clist.get(0).getCityName();
					hoperator.setCity_name(city_name);
				}
			}
			String area_code = RequestHandler.getString(request, "area_code");
			hoperator.setArea_code(area_code);
			if(area_code!=null){
				HArea hArea = new HArea();
				hArea.setAreaCode(area_code);
				List<HArea> alist = hareaService.getHAreaBaseList(hArea);
				if(alist!=null && alist.size() == 1){
					String area_name = alist.get(0).getAreaName();
					hoperator.setArea_name(area_name);
				}
			}
			Integer state = RequestHandler.getInteger(request, "state");
			hoperator.setState(state);
			
			hoperator.setCreateTime(new Date());
			Integer adminId = Integer.valueOf(getSession(request, SessionName.ADMIN_USER_ID).toString());
			hoperator.setAdminId(adminId);
			hoperator.setLastTime(new Date());
			hoperator.setLastAdminId(adminId);
				
			hoperatorService.insertHOperator(hoperator);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHOperator", method = RequestMethod.POST)
	public String updateHOperator(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HOperator hoperator = new HOperator();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hoperator.setId(id);
			
			String full_name = RequestHandler.getString(request, "full_name");
			hoperator.setFull_name(full_name);
			
			String name = RequestHandler.getString(request, "name");
			hoperator.setName(name);
			
			String province_code = RequestHandler.getString(request, "province_code");
			hoperator.setProvince_code(province_code);
			if(province_code!=null){
				HProvince hProvince = new HProvince();
				hProvince.setProvinceCode(province_code);
				List<HProvince> plist = hprovinceService.getHProvinceBaseList(hProvince);
				if(plist!=null && plist.size() == 1){
					String province_name = plist.get(0).getProvinceName();
					hoperator.setProvince_name(province_name);
				}
			}
			
			String city_code = RequestHandler.getString(request, "city_code");
			hoperator.setCity_code(city_code);
			if(city_code!=null){
				HCity hCity = new HCity();
				hCity.setCityCode(city_code);
				List<HCity> clist = hcityService.getHCityBaseList(hCity);
				if(clist!=null && clist.size() == 1){
					String city_name = clist.get(0).getCityName();
					hoperator.setCity_name(city_name);
				}
			}
			String area_code = RequestHandler.getString(request, "area_code");
			hoperator.setArea_code(area_code);
			if(area_code!=null){
				HArea hArea = new HArea();
				hArea.setAreaCode(area_code);
				List<HArea> alist = hareaService.getHAreaBaseList(hArea);
				if(alist!=null && alist.size() == 1){
					String area_name = alist.get(0).getAreaName();
					hoperator.setArea_name(area_name);
				}
			}
			
			Integer state = RequestHandler.getInteger(request, "state");
			hoperator.setState(state);
			
			hoperator.setLastTime(new Date());
			Integer lastAdminId = Integer.valueOf(getSession(request, SessionName.ADMIN_USER_ID).toString());
			hoperator.setLastAdminId(lastAdminId);
			

			hoperatorService.updateHOperator(hoperator);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHOperator", method = RequestMethod.POST)
	public String removeHOperator(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HOperator hoperator = new HOperator();
			Integer id = RequestHandler.getInteger(request, "id");
			hoperator.setId(id);

			hoperatorService.removeHOperator(hoperator);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHOperator", method = RequestMethod.POST)
	public String removeAllHOperator(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HOperator hOperator = new HOperator();
						hOperator.setId(Integer.valueOf(id));
						hoperatorService.removeHOperator(hOperator);
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
