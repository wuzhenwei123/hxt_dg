package com.hxt.hReferee.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.base.utils.FileUploadConstants;
import com.base.utils.ResponseList;
import com.hxt.hAgent.dao.HAgentDAO;
import com.hxt.hAgent.model.HAgent;
import com.hxt.hAgentTwo.dao.HAgentTwoDAO;
import com.hxt.hAgentTwo.model.HAgentTwo;
import com.hxt.hEwm.dao.HEwmDAO;
import com.hxt.hEwm.model.HEwm;
import com.hxt.hReferee.dao.HRefereeDAO;
import com.hxt.hReferee.model.HReferee;
import com.hxt.hUserAccount.service.HUserAccountService;
import com.sys.manageAdminUser.dao.ManageAdminUserDAO;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.wx.service.WeiXinService;

/**
 * @author	wuzhenwei
 * @time	2015年09月04日 14:33:55
 */
 @Service("hRefereeService")
public class HRefereeService {

	@Resource(name = "hRefereeDao")
    private HRefereeDAO hRefereeDAO;
	@Resource(name = "hEwmDao")
    private HEwmDAO hEwmDAO;
	@Resource(name = "hAgentTwoDao")
    private HAgentTwoDAO hAgentTwoDAO;
	@Resource(name = "hAgentDao")
    private HAgentDAO hAgentDAO;
	@Resource(name = "manageAdminUserDao")
    private ManageAdminUserDAO manageAdminUserDAO;
	@Autowired
	private HUserAccountService huseraccountService = null;
	@Autowired
	private WeiXinService weiXinService;
    
    public ResponseList<HReferee> getHRefereeList(HReferee hReferee) {
        return hRefereeDAO.getHRefereeList(hReferee);
    }
    
    public List<HReferee> getHRefereeBaseList(HReferee hReferee) {
        return hRefereeDAO.getHRefereeBaseList(hReferee);
    }
    
    public int getHRefereeListCount(HReferee hReferee) {
        return hRefereeDAO.getHRefereeListCount(hReferee);
    }

    public HReferee getHReferee(HReferee hReferee) { 
        return hRefereeDAO.getHReferee(hReferee);
    }

    public int insertHReferee(HReferee hReferee) throws Exception {
        return hRefereeDAO.insertHReferee(hReferee);
    }

    public int updateHReferee(HReferee hReferee) throws Exception {
        return hRefereeDAO.updateHReferee(hReferee);
    }
    
    public int removeHReferee(HReferee hReferee) throws Exception {
        return hRefereeDAO.removeHReferee(hReferee);
    }
    
    /**
     * 绑定推荐人和被推荐人
     * @param ticket 推荐人的凭证
     * @param openId 被推荐人的openId
     */
    public void bindTuijian(String ticket,String openId){
    	try{
    		//判断是否已经绑定过推荐人
    		HReferee hReferee1 = new HReferee();
    		hReferee1.setBeituijianId(openId);
    		hReferee1.setStatus(1);
//    		HReferee re = hRefereeDAO.getHReferee(hReferee1);
    		
    		//根据凭证获取当前推荐人
    		HEwm hEwm = new HEwm();
    		hEwm.setScene_id(ticket);
    		HEwm model = hEwmDAO.getHEwm(hEwm);
    		boolean b = true;
    		//判断是否机构二维码，如果是，机构状态是否正常
    		if(model.getStyle()==2){
    			HAgentTwo hAgentTwo1 = new HAgentTwo();
				hAgentTwo1.setOpenId(model.getOpenId());
				HAgentTwo hAgentTwo = hAgentTwoDAO.getHAgentTwo(hAgentTwo1);
				if(hAgentTwo!=null&&hAgentTwo.getId()>0){//推荐人是否是二级代理商
					if(hAgentTwo.getStatus()!=1){
						b = false;
					}
				}else{
					HAgent hAgent1 = new HAgent();
					hAgent1.setOpenId(model.getOpenId());
					HAgent hAgent = hAgentDAO.getHAgent(hAgent1);
					if(hAgent.getStatus()!=1){
						b = false;
					}
				}
    		}
    		
    		List<HReferee> listRe = hRefereeDAO.getHRefereeBaseList(hReferee1);
    		if(listRe!=null&&listRe.size()>0){
    			if(listRe.size()==1){//目前只有一个推荐人
    				HReferee re = listRe.get(0);
    				//判断原来推荐人类型
    				if(re.getStyle()==1){//原来推荐人是临时推荐
    					if(model.getStyle()==1){//当前也是临时
    						 re.setStatus(Integer.valueOf(0));
    						 this.hRefereeDAO.updateHReferee(re);

    						 HReferee hReferee = new HReferee();
    						 hReferee.setTjrId(model.getOpenId());
    						 hReferee.setBeituijianId(openId);
    						 hReferee.setCreateTime(new Date());
    						 hReferee.setTicket(ticket);
    						 hReferee.setStatus(Integer.valueOf(1));
    						 hReferee.setStyle(model.getStyle());
    						 this.hRefereeDAO.insertHReferee(hReferee);
    					}else{//当前也是永久的,将机构信息存入用户
    						 HReferee hReferee = new HReferee();
    						 hReferee.setTjrId(model.getOpenId());
    						 hReferee.setBeituijianId(openId);
    						 hReferee.setCreateTime(new Date());
    						 hReferee.setTicket(ticket);
    						 hReferee.setStatus(Integer.valueOf(1));
    						 hReferee.setStyle(model.getStyle());
    						 this.hRefereeDAO.insertHReferee(hReferee);
    						 if(b){
    							HAgentTwo hAgentTwo1 = new HAgentTwo();
	        					hAgentTwo1.setOpenId(model.getOpenId());
	        					HAgentTwo hAgentTwo = hAgentTwoDAO.getHAgentTwo(hAgentTwo1);
	        					if(hAgentTwo!=null&&hAgentTwo.getId()>0){//推荐人是否是二级代理商
	        						ManageAdminUser manageadminuser = new ManageAdminUser();
	        						manageadminuser.setOpenId(openId);
	        						manageadminuser.setState(1);
	        						manageadminuser.setRoleId(Integer.valueOf(FileUploadConstants.COMPANY_ROLE_ID));
	        						ManageAdminUser adminuser = manageAdminUserDAO.getManageAdminUser(manageadminuser);
	        						adminuser.setOneAgentOpenId(hAgentTwo.getCreate_openId());
	        						adminuser.setTwoAgentOpenId(hAgentTwo.getOpenId());
	        						adminuser.setScanTime(new Date());
	        						manageAdminUserDAO.updateManageAdminUser(adminuser);
	        						//发送关注消息
//	        						if(hAgentTwo.getStatus()==1){
//	        							ManageAdminUser userxxx = new ManageAdminUser();
//		    							userxxx.setTwoAgentOpenId(model.getOpenId());
//		    							userxxx.setRoleId(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
//		    							userxxx = manageAdminUserDAO.getManageAdminUser(userxxx);
//		        						huseraccountService.sendZCTempltMsg(userxxx.getOpenId(), adminuser.getNickName());
//	        						}
	        					}else{//是否是一级
	        						HAgent hAgent1 = new HAgent();
	        						hAgent1.setOpenId(model.getOpenId());
	        						System.out.println("=================================="+model.getOpenId());
	        						HAgent hAgent = hAgentDAO.getHAgent(hAgent1);
	        						ManageAdminUser manageadminuser = new ManageAdminUser();
	        						manageadminuser.setOpenId(openId);
	        						manageadminuser.setState(1);
	        						manageadminuser.setRoleId(Integer.valueOf(FileUploadConstants.COMPANY_ROLE_ID));
	        						ManageAdminUser adminuser = manageAdminUserDAO.getManageAdminUser(manageadminuser);
	        						adminuser.setOneAgentOpenId(hAgent.getOpenId());
	        						adminuser.setScanTime(new Date());
	        						manageAdminUserDAO.updateManageAdminUser(adminuser);
	        						//发送关注消息
//	        						if(hAgent!=null&&hAgent.getStatus()==1){
//	        							ManageAdminUser userxxx = new ManageAdminUser();
//		    							userxxx.setOneAgentOpenId(model.getOpenId());
//		    							userxxx.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
//		    							userxxx = manageAdminUserDAO.getManageAdminUser(userxxx);
//		        						huseraccountService.sendZCTempltMsg(userxxx.getOpenId(), adminuser.getNickName());
//	        						}
	        					}
    						 }
    					}
    				}else{//原来推荐人是机构
    					if(model.getStyle()==1){//当前是临时
    						HReferee hReferee = new HReferee();
	   						hReferee.setTjrId(model.getOpenId());
	   						hReferee.setBeituijianId(openId);
	   						hReferee.setCreateTime(new Date());
	   						hReferee.setTicket(ticket);
	   						hReferee.setStatus(Integer.valueOf(1));
	   						hReferee.setStyle(hEwm.getStyle());
	   						this.hRefereeDAO.insertHReferee(hReferee);
    					}
    				}
    			}else{//目前有俩可用的推荐人
    				if(model.getStyle()==1){//当前是临时,把临时的改成新的
    					for(HReferee sssssss:listRe){
    						if(sssssss.getStyle()==1){
    							sssssss.setStatus(0);//停用临时
    							hRefereeDAO.updateHReferee(sssssss);
    						}
    					}
    					HReferee hReferee = new HReferee();
   						hReferee.setTjrId(model.getOpenId());
   						hReferee.setBeituijianId(openId);
   						hReferee.setCreateTime(new Date());
   						hReferee.setTicket(ticket);
   						hReferee.setStatus(Integer.valueOf(1));
   						hReferee.setStyle(hEwm.getStyle());
   						this.hRefereeDAO.insertHReferee(hReferee);
    				}
    			}
    		}else{
    			//根据凭证获取推荐人
        		HReferee hReferee = new HReferee();
        		hReferee.setTjrId(model.getOpenId());//推荐人的openid
        		hReferee.setBeituijianId(openId);
        		hReferee.setCreateTime(new Date());
        		hReferee.setTicket(ticket);
        		hReferee.setStatus(1);
        		hReferee.setStyle(model.getStyle());
        		hRefereeDAO.insertHReferee(hReferee);
        		if(model.getStyle()==2){
        			if(b){
        				HAgentTwo hAgentTwo1 = new HAgentTwo();
    					hAgentTwo1.setOpenId(model.getOpenId());
    					HAgentTwo hAgentTwo = hAgentTwoDAO.getHAgentTwo(hAgentTwo1);
    					if(hAgentTwo!=null&&hAgentTwo.getId()>0){//推荐人是二级代理商
    						ManageAdminUser manageadminuser = new ManageAdminUser();
    						manageadminuser.setOpenId(openId);
    						manageadminuser.setState(1);
    						manageadminuser.setRoleId(Integer.valueOf(FileUploadConstants.COMPANY_ROLE_ID));
    						ManageAdminUser adminuser = manageAdminUserDAO.getManageAdminUser(manageadminuser);
    						adminuser.setOneAgentOpenId(hAgentTwo.getCreate_openId());
    						adminuser.setTwoAgentOpenId(hAgentTwo.getOpenId());
    						adminuser.setScanTime(new Date());
    						manageAdminUserDAO.updateManageAdminUser(adminuser);
    						//发送关注消息
    						ManageAdminUser userxxx = new ManageAdminUser();
							userxxx.setTwoAgentOpenId(model.getOpenId());
							userxxx.setRoleId(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
							userxxx = manageAdminUserDAO.getManageAdminUser(userxxx);
//							if(hAgentTwo.getStatus()==1){
//								huseraccountService.sendZCTempltMsg(userxxx.getOpenId(), adminuser.getNickName());
//							}
    						//发送一级代理关注消息
							HAgent hAgent1 = new HAgent();
    						hAgent1.setOpenId(userxxx.getOneAgentOpenId());
    						HAgent hAgent = hAgentDAO.getHAgent(hAgent1);
//							if(hAgent!=null&&hAgent.getStatus()==1){
//								ManageAdminUser userxxx1 = new ManageAdminUser();
//	    						userxxx1.setOneAgentOpenId(userxxx.getOneAgentOpenId());
//	    						userxxx1.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
//	    						userxxx1 = manageAdminUserDAO.getManageAdminUser(userxxx1);
//	    						huseraccountService.sendZCTempltMsg(userxxx1.getOpenId(), adminuser.getNickName());
//							}
    					}else{//是一级
    						HAgent hAgent1 = new HAgent();
    						hAgent1.setOpenId(model.getOpenId());
    						HAgent hAgent = hAgentDAO.getHAgent(hAgent1);
    						ManageAdminUser manageadminuser = new ManageAdminUser();
    						manageadminuser.setOpenId(openId);
    						manageadminuser.setState(1);
    						manageadminuser.setRoleId(Integer.valueOf(FileUploadConstants.COMPANY_ROLE_ID));
    						ManageAdminUser adminuser = manageAdminUserDAO.getManageAdminUser(manageadminuser);
    						adminuser.setOneAgentOpenId(hAgent.getOpenId());
    						adminuser.setScanTime(new Date());
    						manageAdminUserDAO.updateManageAdminUser(adminuser);
    						//发送关注消息
//    						if(hAgent!=null&&hAgent.getStatus()==1){
//    							ManageAdminUser userxxx = new ManageAdminUser();
//    							userxxx.setOneAgentOpenId(model.getOpenId());
//    							userxxx.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
//    							userxxx = manageAdminUserDAO.getManageAdminUser(userxxx);
//        						huseraccountService.sendZCTempltMsg(userxxx.getOpenId(), adminuser.getNickName());
//    						}
    					}
        			}
        		}
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    /**
     * 绑定推荐人和被推荐人
     * @param ticket 推荐人的凭证
     * @param openId 被推荐人的openId
     */
    public void bindTuijian1(String ticket,String openId){
    	try{
    		//判断是否已经绑定过推荐人
    		HReferee hReferee1 = new HReferee();
    		hReferee1.setBeituijianId(openId);
    		hReferee1.setStatus(1);
    		hReferee1.setStyle(2);
//    		HReferee re = hRefereeDAO.getHReferee(hReferee1);
    		
    		//根据凭证获取当前推荐人
    		HEwm hEwm = new HEwm();
    		hEwm.setScene_id(ticket);
    		HEwm model = hEwmDAO.getHEwm(hEwm);
//    		boolean b = true;
    		//判断是否机构二维码，如果是，机构状态是否正常
//    		if(model.getStyle()==2){
//    			HAgentTwo hAgentTwo1 = new HAgentTwo();
//    			hAgentTwo1.setOpenId(model.getOpenId());
//    			HAgentTwo hAgentTwo = hAgentTwoDAO.getHAgentTwo(hAgentTwo1);
//    			if(hAgentTwo!=null&&hAgentTwo.getId()>0){//推荐人是否是二级代理商
//    				if(hAgentTwo.getStatus()!=1){
//    					b = false;
//    				}
//    			}else{
//    				HAgent hAgent1 = new HAgent();
//    				hAgent1.setOpenId(model.getOpenId());
//    				HAgent hAgent = hAgentDAO.getHAgent(hAgent1);
//    				if(hAgent.getStatus()!=1){
//    					b = false;
//    				}
//    			}
//    		}
    		
    		List<HReferee> listRe = hRefereeDAO.getHRefereeBaseList(hReferee1);
    		if(listRe!=null&&listRe.size()>0){
    			
    		}else{
    			//根据凭证获取推荐人
    			HReferee hReferee = new HReferee();
    			hReferee.setTjrId(model.getOpenId());//推荐人的openid
    			hReferee.setBeituijianId(openId);
    			hReferee.setCreateTime(new Date());
    			hReferee.setTicket(ticket);
    			hReferee.setStatus(1);
    			hReferee.setStyle(model.getStyle());
    			hRefereeDAO.insertHReferee(hReferee);
    			if(model.getStyle()==2){
//    				if(b){
    					String accessToken = weiXinService.getAccessToken(FileUploadConstants.APPID,FileUploadConstants.APPSECRET);
    					String jsonInfo = weiXinService.getFromUserMess(accessToken, openId);
    					JSONObject json = JSONObject.parseObject(jsonInfo);
    					String nick = weiXinService.getChinese(json.getString("nickname"));
    					HAgentTwo hAgentTwo1 = new HAgentTwo();
    					hAgentTwo1.setOpenId(model.getOpenId());
    					HAgentTwo hAgentTwo = hAgentTwoDAO.getHAgentTwo(hAgentTwo1);
    					if(hAgentTwo!=null&&hAgentTwo.getId()>0){//推荐人是二级代理商
    						//发送关注消息
    						ManageAdminUser userxxx = new ManageAdminUser();
    						userxxx.setTwoAgentOpenId(model.getOpenId());
    						userxxx.setRoleId(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
    						userxxx = manageAdminUserDAO.getManageAdminUser(userxxx);
//    						if(hAgentTwo.getStatus()==1){
//    							huseraccountService.sendZCTempltMsg(userxxx.getOpenId(), nick);
//    						}
    						//发送一级代理关注消息
    						HAgent hAgent1 = new HAgent();
    						hAgent1.setOpenId(userxxx.getOneAgentOpenId());
    						HAgent hAgent = hAgentDAO.getHAgent(hAgent1);
//    						if(hAgent!=null&&hAgent.getStatus()==1){
//    							ManageAdminUser userxxx1 = new ManageAdminUser();
//    							userxxx1.setOneAgentOpenId(userxxx.getOneAgentOpenId());
//    							userxxx1.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
//    							userxxx1 = manageAdminUserDAO.getManageAdminUser(userxxx1);
//    							huseraccountService.sendZCTempltMsg(userxxx1.getOpenId(), nick);
//    						}
    					}else{//是一级
    						HAgent hAgent1 = new HAgent();
    						hAgent1.setOpenId(model.getOpenId());
    						HAgent hAgent = hAgentDAO.getHAgent(hAgent1);
    						//发送关注消息
//    						if(hAgent!=null&&hAgent.getStatus()==1){
//    							ManageAdminUser userxxx = new ManageAdminUser();
//    							userxxx.setOneAgentOpenId(model.getOpenId());
//    							userxxx.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
//    							userxxx = manageAdminUserDAO.getManageAdminUser(userxxx);
//    							huseraccountService.sendZCTempltMsg(userxxx.getOpenId(), nick);
//    						}
    					}
//    				}
    			}
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    /**
     * 扫描二维码，如果扫描机构二维码，对机构状态进行判断，临时二维码不做判断
     * @param ticket
     * @param openId
     * @return
     */
    public boolean checkAgent(String ticket,String openId){
    	boolean b = true;
    	try{
    		//根据凭证获取当前推荐人
    		HEwm hEwm = new HEwm();
    		hEwm.setScene_id(ticket);
    		HEwm model = hEwmDAO.getHEwm(hEwm);
    		//判断是否机构二维码，如果是，机构状态是否正常
    		if(model.getStyle()==2){
    			HAgentTwo hAgentTwo1 = new HAgentTwo();
				hAgentTwo1.setOpenId(model.getOpenId());
				HAgentTwo hAgentTwo = hAgentTwoDAO.getHAgentTwo(hAgentTwo1);
				if(hAgentTwo!=null&&hAgentTwo.getId()>0){//推荐人是否是二级代理商
					if(hAgentTwo.getStatus()!=1){
						b = false;
					}
				}else{
					HAgent hAgent1 = new HAgent();
					hAgent1.setOpenId(model.getOpenId());
					HAgent hAgent = hAgentDAO.getHAgent(hAgent1);
					if(hAgent.getStatus()!=1){
						b = false;
					}
				}
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return b;
    }
    
    /**
     * 获取推荐人手机号
     * @param openId
     * @return
     */
    public String getRefereePhone(String openId){
    	String phone = null;
    	try{
    		//判断是否已经绑定过推荐人
    		HReferee hReferee1 = new HReferee();
    		hReferee1.setBeituijianId(openId);
    		hReferee1.setStatus(1);
    		hReferee1.setStyle(2);
    		List<HReferee> listRe = hRefereeDAO.getHRefereeBaseList(hReferee1);
    		if(listRe!=null&&listRe.size()>0){
        		HEwm hEwm = new HEwm();
        		hEwm.setScene_id(listRe.get(0).getTicket());
        		HEwm model = hEwmDAO.getHEwm(hEwm);
        		if(model.getStyle()==2){
        			HAgentTwo hAgentTwo1 = new HAgentTwo();
    				hAgentTwo1.setOpenId(model.getOpenId());
    				HAgentTwo hAgentTwo = hAgentTwoDAO.getHAgentTwo(hAgentTwo1);
    				if(hAgentTwo!=null&&hAgentTwo.getId()>0){//推荐人是否是二级代理商
    					phone = hAgentTwo.getMobile1();
    				}else{
    					HAgent hAgent1 = new HAgent();
    					hAgent1.setOpenId(model.getOpenId());
    					HAgent hAgent = hAgentDAO.getHAgent(hAgent1);
    					if(hAgent!=null&&hAgent.getId()>0){
    						phone = hAgent.getMobile1();
    					}
    				}
        		}
    			
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return phone;
    }
    
}
