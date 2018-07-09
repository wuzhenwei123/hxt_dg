package com.hxt.hReviewUser.controller;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.hxt.hReviewUser.model.HReviewUser;
import com.hxt.hReviewUser.service.HReviewUserService;
import com.sys.manageAdminUser.model.ManageAdminUser;
/**
 * @author	zhangyiyang
 * @time	2016年08月01日 22:41:41
 */
@Controller
@RequestMapping("/hReviewUser")
public class HReviewUserController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HReviewUserController.class); //Logger
	
	@Autowired
	private HReviewUserService hreviewuserService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hReviewUser/hReviewUserIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hReviewUser/hReviewUserAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HReviewUser hreviewuser1 = new HReviewUser();
		hreviewuser1.setId(id);
		HReviewUser hreviewuser = hreviewuserService.getHReviewUser(hreviewuser1);
		model.addAttribute("hreviewuser", hreviewuser);
		
		return "/hReviewUser/hReviewUserUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHReviewUserList", method = RequestMethod.GET)
	public String getHReviewUserList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HReviewUser hreviewuser = new HReviewUser();
			
			//角色条件处理
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			if(adminUser.getRoleId().toString().equals(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID)){
				hreviewuser.setOneAgentOpenId(adminUser.getOneAgentOpenId());
			}else if(adminUser.getRoleId().toString().equals(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID)){
				hreviewuser.setTwoAgentOpenID(adminUser.getTwoAgentOpenId());
			}else if(adminUser.getRoleId().toString().equals(FileUploadConstants.SERVICER_MANAGE_ROLEID)){
				hreviewuser.setServicerId(adminUser.getServicerId());
			}
			
			Integer id = RequestHandler.getInteger(request, "id");
			hreviewuser.setId(id);
			
			Integer isDefault = RequestHandler.getInteger(request, "isDefault");
			hreviewuser.setIsDefault(isDefault);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hreviewuser.setState(state);
			
			String userLabel = RequestHandler.getString(request, "userLabel");
			hreviewuser.setUserLabel(userLabel);
			
			String userName = RequestHandler.getString(request, "userName");
			hreviewuser.setUserName(userName);
			
			Integer sex = RequestHandler.getInteger(request, "sex");
			hreviewuser.setSex(sex);
			
			String mobil = RequestHandler.getString(request, "mobil");
			hreviewuser.setMobil(mobil);
			
			Integer msgSwitch = RequestHandler.getInteger(request, "msgSwitch");
			hreviewuser.setMsgSwitch(msgSwitch);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hreviewuser.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hreviewuser.setCreateId(createId);
			
			Date updateTime = RequestHandler.getDate(request, "updateTime");
			hreviewuser.setUpdateTime(updateTime);
			
			Integer updateId = RequestHandler.getInteger(request, "updateId");
			hreviewuser.setUpdateId(updateId);
			
			String remark = RequestHandler.getString(request, "remark");
			hreviewuser.setRemark(remark);
			String contact_name = RequestHandler.getString(request, "contact_name");
			String contact_phone = RequestHandler.getString(request, "contact_phone");
			hreviewuser.setContact_name(contact_name);
			hreviewuser.setContact_phone(contact_phone);
			
			Integer companyId = RequestHandler.getInteger(request, "companyId");
			hreviewuser.setCompanyId(companyId);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hreviewuser.setRowStart(from);
			hreviewuser.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hreviewuser.setSortColumn(sortColumn);
			
			int hreviewuserListCount = hreviewuserService.getHReviewUserListCount(hreviewuser);
			ResponseList<HReviewUser> hreviewuserList = null;
			if ( hreviewuserListCount > 0 )
			{
				hreviewuserList = hreviewuserService.getHReviewUserList(hreviewuser);
			} else
			{
				hreviewuserList = new ResponseList<HReviewUser>();
			}
			// 设置数据总数
			hreviewuserList.setTotalResults(hreviewuserListCount);
			
			writeSuccessMsg("ok", hreviewuserList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHReviewUserBaseList", method = RequestMethod.GET)
	public String getHReviewUserBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HReviewUser hreviewuser = new HReviewUser();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hreviewuser.setId(id);
			
			Integer isDefault = RequestHandler.getInteger(request, "isDefault");
			hreviewuser.setIsDefault(isDefault);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hreviewuser.setState(state);
			
			String userLabel = RequestHandler.getString(request, "userLabel");
			hreviewuser.setUserLabel(userLabel);
			
			String userName = RequestHandler.getString(request, "userName");
			hreviewuser.setUserName(userName);
			
			Integer sex = RequestHandler.getInteger(request, "sex");
			hreviewuser.setSex(sex);
			
			String mobil = RequestHandler.getString(request, "mobil");
			hreviewuser.setMobil(mobil);
			
			Integer msgSwitch = RequestHandler.getInteger(request, "msgSwitch");
			hreviewuser.setMsgSwitch(msgSwitch);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			hreviewuser.setCreateTime(createTime);
			
			Integer createId = RequestHandler.getInteger(request, "createId");
			hreviewuser.setCreateId(createId);
			
			Date updateTime = RequestHandler.getDate(request, "updateTime");
			hreviewuser.setUpdateTime(updateTime);
			
			Integer updateId = RequestHandler.getInteger(request, "updateId");
			hreviewuser.setUpdateId(updateId);
			
			String remark = RequestHandler.getString(request, "remark");
			hreviewuser.setRemark(remark);
			
			Integer companyId = RequestHandler.getInteger(request, "companyId");
			hreviewuser.setCompanyId(companyId);
			
			List<HReviewUser> hreviewuserList = hreviewuserService.getHReviewUserBaseList(hreviewuser);
		
			writeSuccessMsg("ok", hreviewuserList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHReviewUser", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HReviewUser hreviewuser = new HReviewUser();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hreviewuser.setId(id);
			Integer isDefault = RequestHandler.getInteger(request, "isDefault");
			hreviewuser.setIsDefault(isDefault);
			Integer state = RequestHandler.getInteger(request, "state");
			hreviewuser.setState(state);
			String userLabel = RequestHandler.getString(request, "userLabel");
			hreviewuser.setUserLabel(userLabel);
			String userName = RequestHandler.getString(request, "userName");
			hreviewuser.setUserName(userName);
			Integer sex = RequestHandler.getInteger(request, "sex");
			hreviewuser.setSex(sex);
			String mobil = RequestHandler.getString(request, "mobil");
			Pattern p = Pattern.compile("^1(3|4|5|7|8|9)\\d{9}");
			Matcher ma = p.matcher(mobil);
			if(!ma.matches()){
				writeErrorMsg("手机号码不正确！", null, response);
				return null;
			}
			hreviewuser.setMobil(mobil);
			Integer msgSwitch = RequestHandler.getInteger(request, "msgSwitch");
			hreviewuser.setMsgSwitch(msgSwitch);
			hreviewuser.setCreateTime(new Date());
			ManageAdminUser adminUser = (ManageAdminUser) getSession(request, SessionName.ADMIN_USER);
			hreviewuser.setCreateId(adminUser.getAdminId());
			String remark = RequestHandler.getString(request, "remark");
			hreviewuser.setRemark(remark);
			Integer companyId = RequestHandler.getInteger(request, "companyId");
			hreviewuser.setCompanyId(companyId);
			Integer hasDefault = hreviewuserService.haveDefault(hreviewuser);
			if(hasDefault >0){
				writeErrorMsg("该公司已存在默认发送短信对象！", null, response);
				return null;
			}
			hreviewuserService.insertHReviewUser(hreviewuser);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHReviewUser", method = RequestMethod.POST)
	public String updateHReviewUser(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HReviewUser hreviewuser = new HReviewUser();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hreviewuser.setId(id);
			
			Integer isDefault = RequestHandler.getInteger(request, "isDefault");
			hreviewuser.setIsDefault(isDefault);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hreviewuser.setState(state);
			
			String userLabel = RequestHandler.getString(request, "userLabel");
			hreviewuser.setUserLabel(userLabel);
			
			String userName = RequestHandler.getString(request, "userName");
			hreviewuser.setUserName(userName);
			
			Integer sex = RequestHandler.getInteger(request, "sex");
			hreviewuser.setSex(sex);
			
			String mobil = RequestHandler.getString(request, "mobil");
			hreviewuser.setMobil(mobil);
			
			Integer msgSwitch = RequestHandler.getInteger(request, "msgSwitch");
			hreviewuser.setMsgSwitch(msgSwitch);
			
			hreviewuser.setUpdateTime(new Date());
			
			ManageAdminUser adminUser = (ManageAdminUser) getSession(request, SessionName.ADMIN_USER);
			hreviewuser.setUpdateId(adminUser.getAdminId());
			
			String remark = RequestHandler.getString(request, "remark");
			hreviewuser.setRemark(remark);
			
			Integer companyId = RequestHandler.getInteger(request, "companyId");
			hreviewuser.setCompanyId(companyId);
			

			hreviewuserService.updateHReviewUser(hreviewuser);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHReviewUser", method = RequestMethod.POST)
	public String removeHReviewUser(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HReviewUser hreviewuser = new HReviewUser();
			Integer id = RequestHandler.getInteger(request, "id");
			hreviewuser.setId(id);

			hreviewuserService.removeHReviewUser(hreviewuser);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHReviewUser", method = RequestMethod.POST)
	public String removeAllHReviewUser(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HReviewUser hReviewUser = new HReviewUser();
						hReviewUser.setId(Integer.valueOf(id));
						hreviewuserService.removeHReviewUser(hReviewUser);
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
	@RequestMapping(value = "/getCompanReviewer", method = RequestMethod.GET)
	public String getCompanReviewer(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HReviewUser hreviewuser = new HReviewUser();
			Integer companyId = RequestHandler.getInteger(request, "cid");
			hreviewuser.setCompanyId(companyId);
			hreviewuser = hreviewuserService.getHReviewUser(hreviewuser);
			writeSuccessMsg("ok", hreviewuser, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
}
