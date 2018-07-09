package com.sys.manageAdminUser.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.sys.adminUserRole.dao.AdminUserRoleDAO;
import com.sys.adminUserRole.model.AdminUserRole;
import com.sys.manageAdminUser.dao.ManageAdminUserDAO;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.base.utils.FileUploadConstants;
import com.base.utils.MD5;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SendMsgUtil;
import com.base.utils.SessionName;
import com.hxt.hMessageLog.dao.HMessageLogDAO;
import com.hxt.hMessageLog.model.HMessageLog;

/**
 * @author	keeny
 * @time	2015年02月04日 11:09:21
 */
 @Service("manageAdminUserService")
public class ManageAdminUserService {

	@Resource(name = "manageAdminUserDao")
    private ManageAdminUserDAO manageAdminUserDAO;
	@Resource(name = "adminUserRoleDao")
    private AdminUserRoleDAO adminUserRoleDAO;
	@Resource(name = "hMessageLogDao")
    private HMessageLogDAO hMessageLogDAO;
    
    public ResponseList<ManageAdminUser> getManageAdminUserList(ManageAdminUser manageAdminUser) {
        return manageAdminUserDAO.getManageAdminUserList(manageAdminUser);
    }
    
    public ResponseList<ManageAdminUser> getManageAdminUserPP(ManageAdminUser manageAdminUser) {
    	return manageAdminUserDAO.getManageAdminUserPP(manageAdminUser);
    }
    
    public List<ManageAdminUser> getManageAdminUserBaseList(ManageAdminUser manageAdminUser) {
        return manageAdminUserDAO.getManageAdminUserBaseList(manageAdminUser);
    }
    
    public List<ManageAdminUser> getServiceUser(ManageAdminUser manageAdminUser) {
    	return manageAdminUserDAO.getServiceUser(manageAdminUser);
    }
    
    public int getManageAdminUserListCount(ManageAdminUser manageAdminUser) {
        return manageAdminUserDAO.getManageAdminUserListCount(manageAdminUser);
    }
    
    public int checkAdminName(ManageAdminUser manageAdminUser) {
    	return manageAdminUserDAO.checkAdminName(manageAdminUser);
    }
    
    public int getManageAdminUserPPCount(ManageAdminUser manageAdminUser) {
    	return manageAdminUserDAO.getManageAdminUserPPCount(manageAdminUser);
    }

    public List<ManageAdminUser> getOtherManageUserList(ManageAdminUser manageAdminUser) {
    	return manageAdminUserDAO.getOtherManageUserList(manageAdminUser);
    }
    
    public ManageAdminUser getManageAdminUser(ManageAdminUser manageAdminUser) { 
        return manageAdminUserDAO.getManageAdminUser(manageAdminUser);
    }
    
    public ManageAdminUser getAdminUserByLogin(ManageAdminUser manageAdminUser) { 
    	return manageAdminUserDAO.getAdminUserByLogin(manageAdminUser);
    }

    public int insertManageAdminUser(ManageAdminUser manageAdminUser) throws Exception {
        return manageAdminUserDAO.insertManageAdminUser(manageAdminUser);
    }

    public int updateDefaultYLGl(ManageAdminUser manageAdminUser) throws Exception {
        return manageAdminUserDAO.updateDefaultYLGl(manageAdminUser);
    }
    
    public int updateManageAdminUser(ManageAdminUser manageAdminUser) throws Exception {
    	return manageAdminUserDAO.updateManageAdminUser(manageAdminUser);
    }
    
    public int updateDefaultSJGl(ManageAdminUser manageAdminUser) throws Exception {
    	return manageAdminUserDAO.updateDefaultSJGl(manageAdminUser);
    }
    
    public int removeManageAdminUser(ManageAdminUser manageAdminUser) throws Exception {
        return manageAdminUserDAO.removeManageAdminUser(manageAdminUser);
    }
    
    /**
     * 生成代理机构管理员
     * @param adminName(手机号)
     * @param nickName(机构名称)
     * @param twoAgentOpenId(机构)
     * @param oneAgentOpenId(机构名称)
     * @param roleId
     * @return
     */
    public int saveAgentManage(String adminName,String nickName,String oneAgentOpenId,String twoAgentOpenId,Integer roleId,Integer createrId,String oneAgentOpenName,String twoAgentOpenName){
    	Integer adminId = 0;
    	try{
    		ManageAdminUser manageadminuser = new ManageAdminUser();
			manageadminuser.setAdminName(adminName);
			manageadminuser.setNickName(nickName+"管理员");
			if(StringUtils.isNotBlank(adminName)){
				String passwd = adminName.substring(adminName.length()-6, adminName.length());
				manageadminuser.setPasswd(new MD5().getMD5ofStr(passwd));
			}
			manageadminuser.setRealName(nickName+"管理员");
			manageadminuser.setMobile(adminName);
			manageadminuser.setPhone(adminName);
			manageadminuser.setTwoAgentOpenId(twoAgentOpenId);
			manageadminuser.setOneAgentOpenId(oneAgentOpenId);
			manageadminuser.setState(1);
			manageadminuser.setIsFirst(0);
			manageadminuser.setOneAgentName(oneAgentOpenName);
			manageadminuser.setTwoAgentName(twoAgentOpenName);
			manageadminuser.setCreateTime(new Date());
			manageadminuser.setCreaterId(createrId);
			
			ManageAdminUser manageadminuser1 = new ManageAdminUser();
			manageadminuser1.setAdminName(adminName);
			int count = manageAdminUserDAO.getManageAdminUserListCount(manageadminuser1);// 用户名验证
			if (count != 0) {
				return -1;
			}else{
				adminId = manageAdminUserDAO.insertManageAdminUser(manageadminuser);

				if (roleId != null) {
					AdminUserRole adminUserRole = new AdminUserRole();
					adminUserRole.setAdminId(adminId);
					AdminUserRole adminUserRole2 = adminUserRoleDAO.getAdminUserRole(adminUserRole);
					adminUserRole.setRoleId(roleId);
					if (adminUserRole2 == null) {
						adminUserRoleDAO.insertAdminUserRole(adminUserRole);
					} else {
						adminUserRole2.setRoleId(roleId);
						adminUserRoleDAO.updateAdminUserRole(adminUserRole2);
					}
				}
			}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return adminId;
    }
    /**
     * 
     * @param mobile 手机号
     * @param adminName(登录账号)
     * @param nickName(机构名称)
     * @param oneAgentOpenId(一级机构)
     * @param twoAgentOpenId（二级机构）
     * @param roleId
     * @param createrId
     * @param oneAgentOpenName
     * @param twoAgentOpenName
     * @return
     */
    public int saveAgentManage2(String mobile,String adminName,String nickName,String oneAgentOpenId,String twoAgentOpenId,Integer roleId,Integer createrId,String oneAgentOpenName,String twoAgentOpenName){
    	Integer adminId = 0;
    	try{
    		ManageAdminUser manageadminuser = new ManageAdminUser();
    		manageadminuser.setAdminName(adminName);
    		manageadminuser.setNickName(nickName+"管理员");
    		if(StringUtils.isNotBlank(adminName)){
    			String passwd = mobile.substring(mobile.length()-6, mobile.length());
    			manageadminuser.setPasswd(new MD5().getMD5ofStr(passwd));
    		}
    		manageadminuser.setRealName(nickName+"管理员");
    		manageadminuser.setMobile(mobile);
    		manageadminuser.setPhone(mobile);
    		manageadminuser.setTwoAgentOpenId(twoAgentOpenId);
    		manageadminuser.setOneAgentOpenId(oneAgentOpenId);
    		manageadminuser.setState(1);
    		manageadminuser.setIsFirst(0);
    		manageadminuser.setOneAgentName(oneAgentOpenName);
    		manageadminuser.setTwoAgentName(twoAgentOpenName);
    		manageadminuser.setCreateTime(new Date());
    		manageadminuser.setCreaterId(createrId);
    		
    		ManageAdminUser manageadminuser1 = new ManageAdminUser();
    		manageadminuser1.setAdminName(adminName);
    		int count = manageAdminUserDAO.getManageAdminUserListCount(manageadminuser1);// 用户名验证
    		if (count != 0) {
    			return -1;
    		}else{
    			adminId = manageAdminUserDAO.insertManageAdminUser(manageadminuser);
    			
    			if (roleId != null) {
    				AdminUserRole adminUserRole = new AdminUserRole();
    				adminUserRole.setAdminId(adminId);
    				AdminUserRole adminUserRole2 = adminUserRoleDAO.getAdminUserRole(adminUserRole);
    				adminUserRole.setRoleId(roleId);
    				if (adminUserRole2 == null) {
    					adminUserRoleDAO.insertAdminUserRole(adminUserRole);
    				} else {
    					adminUserRole2.setRoleId(roleId);
    					adminUserRoleDAO.updateAdminUserRole(adminUserRole2);
    				}
    			}
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return adminId;
    }
    
    /**
     * 生成代理机构管理员
     * @param adminName(手机号)
     * @param nickName(机构名称)
     * @param twoAgentOpenId(机构)
     * @param oneAgentOpenId(机构名称)
     * @param roleId
     * @return
     */
    public int saveAgentManage1(String adminName,String nickName,String oneAgentOpenId,String twoAgentOpenId,Integer roleId,Integer createrId,String oneAgentOpenName,String twoAgentOpenName,Integer companyId,String card){
    	Integer adminId = 0;
    	try{
    		ManageAdminUser manageadminuser = new ManageAdminUser();
    		manageadminuser.setAdminName(adminName+"8");
//    		manageadminuser.setNickName(nickName+"8");
    		if(StringUtils.isNotBlank(adminName)){
    			String passwd = card.substring(card.length()-6, card.length());
    			manageadminuser.setPasswd(new MD5().getMD5ofStr(passwd));
    		}
    		manageadminuser.setRealName(nickName);
    		manageadminuser.setMobile(adminName);
    		manageadminuser.setPhone(adminName);
    		manageadminuser.setTwoAgentOpenId(twoAgentOpenId);
    		manageadminuser.setOneAgentOpenId(oneAgentOpenId);
    		manageadminuser.setState(1);
    		manageadminuser.setIsFirst(0);
    		manageadminuser.setOneAgentName(oneAgentOpenName);
    		manageadminuser.setTwoAgentName(twoAgentOpenName);
    		manageadminuser.setCreateTime(new Date());
    		manageadminuser.setCreaterId(createrId);
    		manageadminuser.setCompanyId(companyId);
    		
    		ManageAdminUser manageadminuser1 = new ManageAdminUser();
    		manageadminuser1.setAdminName(adminName+"8");
    		int count = manageAdminUserDAO.getManageAdminUserListCount(manageadminuser1);// 用户名验证
    		if (count != 0) {
    			return -1;
    		}else{
    			adminId = manageAdminUserDAO.insertManageAdminUser(manageadminuser);
    			
    			if (roleId != null) {
    				AdminUserRole adminUserRole = new AdminUserRole();
    				adminUserRole.setAdminId(adminId);
    				AdminUserRole adminUserRole2 = adminUserRoleDAO.getAdminUserRole(adminUserRole);
    				adminUserRole.setRoleId(roleId);
    				if (adminUserRole2 == null) {
    					adminUserRoleDAO.insertAdminUserRole(adminUserRole);
    				} else {
    					adminUserRole2.setRoleId(roleId);
    					adminUserRoleDAO.updateAdminUserRole(adminUserRole2);
    				}
    			}
    			
    			String content = nickName + "，您单位在企业缴费平台上申请的手机缴费服务已通过审核，登录账号为："+adminName+"8"+"，密码为您身份证号的后六位，关注“恒信通企业服务”微信公众号后，即可通过手机为您单位缴费啦，详情可咨询010-96199.";
    			int s = SendMsgUtil.sendMsg(adminName,content);
//    			if(s>0){
//    				HMessageLog hMessageLog = new HMessageLog();
//    				hMessageLog.setContent(content);
//    				hMessageLog.setCreateTime(new Date());
//    				hMessageLog.setPhone(adminName);
////    				hMessageLog.setIp(this.getIpAddr(request));
//    				hMessageLogDAO.insertHMessageLog(hMessageLog);
//    			}
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return adminId;
    }
    
    
    public ResponseList<ManageAdminUser> getManageAdminUserList1(ManageAdminUser manageAdminUser) {
        return manageAdminUserDAO.getManageAdminUserList1(manageAdminUser);
    }
    
    public List<ManageAdminUser> getManageAdminUserBaseList1(ManageAdminUser manageAdminUser) {
        return manageAdminUserDAO.getManageAdminUserBaseList1(manageAdminUser);
    }
    
    public int getManageAdminUserListCount1(ManageAdminUser manageAdminUser) {
        return manageAdminUserDAO.getManageAdminUserListCount1(manageAdminUser);
    }

    public ManageAdminUser getManageAdminUser1(ManageAdminUser manageAdminUser) { 
        return manageAdminUserDAO.getManageAdminUser1(manageAdminUser);
    }
    
    /**
	 * 取消关注，解除绑定
	 * @param manageAdminUser
	 * @return
	 */
	public Integer unBindWx(ManageAdminUser manageAdminUser){
		return manageAdminUserDAO.unBindWx(manageAdminUser);
	}
}
