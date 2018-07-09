package com.hxt.hMessagePhone.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hMessagePhone.dao.HMessagePhoneDAO;
import com.hxt.hMessagePhone.model.HMessagePhone;
import com.base.utils.ResponseList;
import com.base.utils.SendMsgUtil;

/**
 * @author	wuzhenwei
 * @time	2017年06月22日 14:48:26
 */
 @Service("hMessagePhoneService")
public class HMessagePhoneService {

	@Resource(name = "hMessagePhoneDao")
    private HMessagePhoneDAO hMessagePhoneDAO;
    
    public ResponseList<HMessagePhone> getHMessagePhoneList(HMessagePhone hMessagePhone) {
        return hMessagePhoneDAO.getHMessagePhoneList(hMessagePhone);
    }
    
    public List<HMessagePhone> getHMessagePhoneBaseList(HMessagePhone hMessagePhone) {
        return hMessagePhoneDAO.getHMessagePhoneBaseList(hMessagePhone);
    }
    
    public int getHMessagePhoneListCount(HMessagePhone hMessagePhone) {
        return hMessagePhoneDAO.getHMessagePhoneListCount(hMessagePhone);
    }

    public HMessagePhone getHMessagePhone(HMessagePhone hMessagePhone) { 
        return hMessagePhoneDAO.getHMessagePhone(hMessagePhone);
    }

    public int insertHMessagePhone(HMessagePhone hMessagePhone) throws Exception {
        return hMessagePhoneDAO.insertHMessagePhone(hMessagePhone);
    }

    public int updateHMessagePhone(HMessagePhone hMessagePhone) throws Exception {
        return hMessagePhoneDAO.updateHMessagePhone(hMessagePhone);
    }
    
    public int removeHMessagePhone(HMessagePhone hMessagePhone) throws Exception {
        return hMessagePhoneDAO.removeHMessagePhone(hMessagePhone);
    }
    
    
    public void sendMessage(String sq,String name){
    	try{
    		List<HMessagePhone> list = hMessagePhoneDAO.getHMessagePhoneBaseList(new HMessagePhone());
    		if(list!=null&&list.size()>0){
    			for(HMessagePhone p:list){
    				String content = "交易流水号："+sq+"，账户名称："+name+"，请及时处理。";
    	    		SendMsgUtil.sendMsg(p.getPhone(),content);//发送短信
    			}
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
}
