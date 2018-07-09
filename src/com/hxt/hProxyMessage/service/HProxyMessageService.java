package com.hxt.hProxyMessage.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hProxyMessage.dao.HProxyMessageDAO;
import com.hxt.hProxyMessage.model.HProxyMessage;
import com.hxt.hProxyMessage.model.Location;
import com.base.utils.ResponseList;

/**
 * @author	wuzhenwei
 * @time	2016年09月08日 18:43:55
 */
 @Service("hProxyMessageService")
public class HProxyMessageService {

	@Resource(name = "hProxyMessageDao")
    private HProxyMessageDAO hProxyMessageDAO;
    
    public ResponseList<HProxyMessage> getHProxyMessageList(HProxyMessage hProxyMessage) {
        return hProxyMessageDAO.getHProxyMessageList(hProxyMessage);
    }
    
    public List<HProxyMessage> getHProxyMessageBaseList(HProxyMessage hProxyMessage) {
        return hProxyMessageDAO.getHProxyMessageBaseList(hProxyMessage);
    }
    
    public int getHProxyMessageListCount(HProxyMessage hProxyMessage) {
        return hProxyMessageDAO.getHProxyMessageListCount(hProxyMessage);
    }

    public HProxyMessage getHProxyMessage(HProxyMessage hProxyMessage) { 
        return hProxyMessageDAO.getHProxyMessage(hProxyMessage);
    }

    public int insertHProxyMessage(HProxyMessage hProxyMessage) throws Exception {
        return hProxyMessageDAO.insertHProxyMessage(hProxyMessage);
    }
    
    public int checkProxyPhone(HProxyMessage hProxyMessage) {
    	return hProxyMessageDAO.checkProxyPhone(hProxyMessage);
    }

    public int updateHProxyMessage(HProxyMessage hProxyMessage) throws Exception {
        return hProxyMessageDAO.updateHProxyMessage(hProxyMessage);
    }
    
    public int updateHProxyMessageBH(HProxyMessage hProxyMessage) throws Exception {
    	return hProxyMessageDAO.updateHProxyMessageBH(hProxyMessage);
    }
    
    public int updateHProxyMessageZZ(HProxyMessage hProxyMessage) throws Exception {
    	return hProxyMessageDAO.updateHProxyMessageZZ(hProxyMessage);
    }
    
    public int removeHProxyMessage(HProxyMessage hProxyMessage) throws Exception {
        return hProxyMessageDAO.removeHProxyMessage(hProxyMessage);
    }
    
    public List<Location> getLocation(HProxyMessage hProxyMessage){
    	List<Location> list = new ArrayList<Location>();
    	try{
    		Location location = new Location();
			location.setName(hProxyMessage.getContractNumber());
			location.setX(230);
			location.setY(81);
			list.add(location);
			
			Location location1 = new Location();
			location1.setName(hProxyMessage.getUserNumber());
			location1.setX(230);
			location1.setY(101);
			list.add(location1);
			
			Location location2 = new Location();
			location2.setName(hProxyMessage.getProxyName());
			location2.setX(230);
			location2.setY(123);
			list.add(location2);
			
			Location location3 = new Location();
			location3.setName(hProxyMessage.getBank_name());
			location3.setX(230);
			location3.setY(145);
			list.add(location3);
			
			Location location4 = new Location();
			location4.setName(hProxyMessage.getPayAccountName());
			location4.setX(230);
			location4.setY(166);
			list.add(location4);
			
			Location location5 = new Location();
			location5.setName(hProxyMessage.getPayBankNumber());
			location5.setX(230);
			location5.setY(188);
			list.add(location5);
			
			Location location6 = new Location();
			location6.setName(hProxyMessage.getPayName());
			location6.setX(230);
			location6.setY(208);
			list.add(location6);
			
			Location location7 = new Location();
			location7.setName(hProxyMessage.getPayMail());
			location7.setX(230);
			location7.setY(230);
			list.add(location7);
			
			Location location8 = new Location();
			location8.setName(hProxyMessage.getProxyAddress());
			location8.setX(230);
			location8.setY(252);
			list.add(location8);
			
			Location location9 = new Location();
			location9.setName(hProxyMessage.getPayPhoneNumber());
			location9.setX(520);
			location9.setY(210);
			list.add(location9);
			
			Location location10 = new Location();
			location10.setName(hProxyMessage.getProxyCode());
			location10.setX(520);
			location10.setY(230);
			list.add(location10);
			
			Location location11 = new Location();
			location11.setName(hProxyMessage.getPayName());
			location11.setX(290);
			location11.setY(335);
			list.add(location11);
			
			Location location12 = new Location();
			location12.setName(hProxyMessage.getPayCard());
			location12.setX(403);
			location12.setY(335);
			list.add(location12);
			
			SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
			Location location13 = new Location();
			location13.setName(sf.format(new Date()));
			location13.setX(550);
			location13.setY(900);
			list.add(location13);
			
//			Location location14 = new Location();
//			location14.setName(hProxyMessage.getProxyName());
//			location14.setX(108);
//			location14.setY(295);
//			list.add(location14);
			
			
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
    }
    
    public List<Location> getLocation1(HProxyMessage hProxyMessage){
    	List<Location> list = new ArrayList<Location>();
    	try{
    		Location location = new Location();
			location.setName(hProxyMessage.getContractNumber());
			location.setX(230);
			location.setY(80);
			list.add(location);
			
			Location location1 = new Location();
			location1.setName(hProxyMessage.getUserNumber());
			location1.setX(230);
			location1.setY(102);
			list.add(location1);
			
			Location location2 = new Location();
			location2.setName(hProxyMessage.getProxyName());
			location2.setX(230);
			location2.setY(124);
			list.add(location2);
			
			Location location3 = new Location();
			location3.setName(hProxyMessage.getBank_name());
			location3.setX(230);
			location3.setY(145);
			list.add(location3);
			
			Location location4 = new Location();
			location4.setName(hProxyMessage.getPayAccountName());
			location4.setX(230);
			location4.setY(166);
			list.add(location4);
			
			Location location5 = new Location();
			location5.setName(hProxyMessage.getPayBankNumber());
			location5.setX(230);
			location5.setY(187);
			list.add(location5);
			
			Location location6 = new Location();
			location6.setName(hProxyMessage.getPayName());
			location6.setX(230);
			location6.setY(209);
			list.add(location6);
			
			Location location7 = new Location();
			location7.setName(hProxyMessage.getPayMail());
			location7.setX(230);
			location7.setY(229);
			list.add(location7);
			
			Location location8 = new Location();
			location8.setName(hProxyMessage.getProxyAddress());
			location8.setX(230);
			location8.setY(251);
			list.add(location8);
			
			Location location9 = new Location();
			location9.setName(hProxyMessage.getPayPhoneNumber());
			location9.setX(520);
			location9.setY(209);
			list.add(location9);
			
			Location location10 = new Location();
			location10.setName(hProxyMessage.getProxyCode());
			location10.setX(520);
			location10.setY(229);
			list.add(location10);
			
//			Location location11 = new Location();
//			location11.setName(hProxyMessage.getProxyName());
//			location11.setX(122);
//			location11.setY(320);
//			list.add(location11);
//			
//			Location location12 = new Location();
//			location12.setName(hProxyMessage.getPayCard());
//			location12.setX(395);
//			location12.setY(361);
//			list.add(location12);
			
			SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
			Location location13 = new Location();
			location13.setName(sf.format(new Date()));
			location13.setX(550);
			location13.setY(700);
			list.add(location13);
			
			
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
    }
    
    public List<Location> getLocation2(HProxyMessage hProxyMessage,HProxyMessage hProxyMessageBG){
    	List<Location> list = new ArrayList<Location>();
    	try{
    		Location location = new Location();
			location.setName(hProxyMessage.getContractNumber());
			location.setX(207);
			location.setY(111);
			list.add(location);
			
			Location location1 = new Location();
			location1.setName(hProxyMessage.getUserNumber());
			location1.setX(207);
			location1.setY(131);
			list.add(location1);
			
			Location location2 = new Location();
			location2.setName(hProxyMessage.getProxyName());
			location2.setX(207);
			location2.setY(153);
			list.add(location2);
			
			Location location3 = new Location();
			location3.setName(hProxyMessage.getBank_name());
			location3.setX(207);
			location3.setY(175);
			list.add(location3);
			
			Location location4 = new Location();
			location4.setName(hProxyMessage.getPayAccountName());
			location4.setX(207);
			location4.setY(196);
			list.add(location4);
			
			Location location5 = new Location();
			location5.setName(hProxyMessage.getPayBankNumber());
			location5.setX(207);
			location5.setY(218);
			list.add(location5);
			
			Location location6 = new Location();
			location6.setName(hProxyMessageBG.getPayName());
			location6.setX(207);
			location6.setY(238);
			list.add(location6);
			
			Location location7 = new Location();
			location7.setName(hProxyMessageBG.getPayMail());
			location7.setX(207);
			location7.setY(260);
			list.add(location7);
			
			Location location8 = new Location();
			location8.setName(hProxyMessageBG.getProxyAddress());
			location8.setX(207);
			location8.setY(282);
			list.add(location8);
			
			Location location9 = new Location();
			location9.setName(hProxyMessageBG.getPayPhoneNumber());
			location9.setX(517);
			location9.setY(240);
			list.add(location9);
			
			Location location10 = new Location();
			location10.setName(hProxyMessageBG.getProxyCode());
			location10.setX(517);
			location10.setY(260);
			list.add(location10);
			
//			Location location11 = new Location();
//			location11.setName(hProxyMessage.getPayName());
//			location11.setX(267);
//			location11.setY(365);
//			list.add(location11);
//			
//			Location location12 = new Location();
//			location12.setName(hProxyMessage.getPayCard());
//			location12.setX(380);
//			location12.setY(365);
//			list.add(location12);
			
			SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
			Location location13 = new Location();
			location13.setName(sf.format(new Date()));
			location13.setX(550);
			location13.setY(900);
			list.add(location13);
			
//			Location location14 = new Location();
//			location14.setName(hProxyMessage.getProxyName());
//			location14.setX(108);
//			location14.setY(295);
//			list.add(location14);
			
			
			Location location15 = new Location();
			location15.setName(hProxyMessage.getPayName());
			location15.setX(207);
			location15.setY(325);
			list.add(location15);
			
			Location location16 = new Location();
			location16.setName(hProxyMessage.getPayMail());
			location16.setX(207);
			location16.setY(346);
			list.add(location16);
			
			Location location17 = new Location();
			location17.setName(hProxyMessage.getProxyAddress());
			location17.setX(207);
			location17.setY(368);
			list.add(location17);
			
			Location location18 = new Location();
			location18.setName(hProxyMessage.getPayPhoneNumber());
			location18.setX(517);
			location18.setY(326);
			list.add(location18);
			
			Location location19 = new Location();
			location19.setName(hProxyMessage.getProxyCode());
			location19.setX(517);
			location19.setY(346);
			list.add(location19);
			
			
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
    }
    
}
