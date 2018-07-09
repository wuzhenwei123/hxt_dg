package com.hxt.openingBank.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.openingBank.dao.OpeningBankDAO;
import com.hxt.openingBank.model.OpeningBank;
import com.base.utils.ResponseList;

/**
 * @author	wuzhenwei
 * @time	2016年09月09日 00:01:19
 */
 @Service("openingBankService")
public class OpeningBankService {

	@Resource(name = "openingBankDao")
    private OpeningBankDAO openingBankDAO;
    
    public ResponseList<OpeningBank> getOpeningBankList(OpeningBank openingBank) {
        return openingBankDAO.getOpeningBankList(openingBank);
    }
    
    public List<OpeningBank> getOpeningBankBaseList(OpeningBank openingBank) {
        return openingBankDAO.getOpeningBankBaseList(openingBank);
    }
    
    public int getOpeningBankListCount(OpeningBank openingBank) {
        return openingBankDAO.getOpeningBankListCount(openingBank);
    }

    public OpeningBank getOpeningBank(OpeningBank openingBank) { 
        return openingBankDAO.getOpeningBank(openingBank);
    }

    public int insertOpeningBank(OpeningBank openingBank) throws Exception {
        return openingBankDAO.insertOpeningBank(openingBank);
    }

    public int updateOpeningBank(OpeningBank openingBank) throws Exception {
        return openingBankDAO.updateOpeningBank(openingBank);
    }
    
    public int removeOpeningBank(OpeningBank openingBank) throws Exception {
        return openingBankDAO.removeOpeningBank(openingBank);
    }
    
}
