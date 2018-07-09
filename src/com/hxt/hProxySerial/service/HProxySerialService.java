package com.hxt.hProxySerial.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hProxySerial.dao.HProxySerialDAO;
import com.hxt.hProxySerial.model.HProxySerial;
import com.base.utils.FileUploadConstants;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;

/**
 * @author	wuzhenwei
 * @time	2016年09月08日 22:18:17
 */
 @Service("hProxySerialService")
public class HProxySerialService {

	@Resource(name = "hProxySerialDao")
    private HProxySerialDAO hProxySerialDAO;
    
    public ResponseList<HProxySerial> getHProxySerialList(HProxySerial hProxySerial) {
        return hProxySerialDAO.getHProxySerialList(hProxySerial);
    }
    
    public List<HProxySerial> getHProxySerialBaseList(HProxySerial hProxySerial) {
        return hProxySerialDAO.getHProxySerialBaseList(hProxySerial);
    }
    
    public int getHProxySerialListCount(HProxySerial hProxySerial) {
        return hProxySerialDAO.getHProxySerialListCount(hProxySerial);
    }

    public HProxySerial getHProxySerial(HProxySerial hProxySerial) { 
        return hProxySerialDAO.getHProxySerial(hProxySerial);
    }

    public int insertHProxySerial(HProxySerial hProxySerial) throws Exception {
        return hProxySerialDAO.insertHProxySerial(hProxySerial);
    }

    public int updateHProxySerial(HProxySerial hProxySerial) throws Exception {
        return hProxySerialDAO.updateHProxySerial(hProxySerial);
    }
    
    public int removeHProxySerial(HProxySerial hProxySerial) throws Exception {
        return hProxySerialDAO.removeHProxySerial(hProxySerial);
    }
    
    public HProxySerial insertNumber(String bank_number,String payBankNumber){
    	HProxySerial hProxySerial = new HProxySerial();
    	try{
    		hProxySerial.setBank_number(bank_number);
    		hProxySerial.setPayBankNumber(payBankNumber);
//    		HProxySerial hProxySerial1 = hProxySerialDAO.getHProxySerial(hProxySerial);
//    		if(hProxySerial1==null){
    			
    			String cityCode = bank_number.substring(3, 7);
    			String bankCode = bank_number.substring(0, 3);
    			
    			String contractNumber = FileUploadConstants.LOAN_CODE + cityCode + FileUploadConstants.ORGANIZATION_CODE + FileUploadConstants.SERVICE_ELECTRICITY_CODE + bankCode + payBankNumber;
    			
    			hProxySerial.setContractNumber(contractNumber);
    			int id = hProxySerialDAO.insertHProxySerial(hProxySerial);
    			Long userNumber = 100000000L + id;
    			hProxySerial.setId(id);
//    			hProxySerial.setUserNumber(FileUploadConstants.ORGANIZATION_CODE+userNumber);
    			hProxySerial.setUserNumber(FileUploadConstants.PHONE_TOU+FileUploadConstants.ORGANIZATION_CODE.substring(3, FileUploadConstants.ORGANIZATION_CODE.length())+userNumber);
    			hProxySerialDAO.updateHProxySerial(hProxySerial);
//    		}else{
//    			return hProxySerial1;
//    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return hProxySerial;
    }
    public HProxySerial updateNumber(String bank_number,String payBankNumber,String userNumber1){
    	try{
    		HProxySerial hProxySerial = new HProxySerial();
    		hProxySerial.setUserNumber(userNumber1);
    		hProxySerial = hProxySerialDAO.getHProxySerial(hProxySerial);
    		if(hProxySerial!=null){
    			
    			String cityCode = bank_number.substring(3, 7);
    			String bankCode = bank_number.substring(0, 3);
    			
    			String contractNumber = FileUploadConstants.LOAN_CODE + cityCode + FileUploadConstants.ORGANIZATION_CODE + FileUploadConstants.SERVICE_ELECTRICITY_CODE + bankCode + payBankNumber;
    			
    			hProxySerial.setContractNumber(contractNumber);
    			hProxySerialDAO.updateHProxySerial(hProxySerial);
    			return hProxySerial;
    		}else{
    			hProxySerial = new HProxySerial();
    			String cityCode = bank_number.substring(3, 7);
    			String bankCode = bank_number.substring(0, 3);
    			
    			String contractNumber = FileUploadConstants.LOAN_CODE + cityCode + FileUploadConstants.ORGANIZATION_CODE + FileUploadConstants.SERVICE_ELECTRICITY_CODE + bankCode + payBankNumber;
    			hProxySerial.setBank_number(bank_number);
        		hProxySerial.setPayBankNumber(payBankNumber);
    			hProxySerial.setContractNumber(contractNumber);
    			int id = hProxySerialDAO.insertHProxySerial(hProxySerial);
    			Long userNumber = 100000000L + id;
    			hProxySerial.setId(id);
    			hProxySerial.setUserNumber(FileUploadConstants.PHONE_TOU+FileUploadConstants.ORGANIZATION_CODE.substring(3, FileUploadConstants.ORGANIZATION_CODE.length())+userNumber);
    			hProxySerialDAO.updateHProxySerial(hProxySerial);
    			
    			hProxySerial = hProxySerialDAO.getHProxySerial(hProxySerial);
    			return hProxySerial;
    			
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return null;
    }
    
}
