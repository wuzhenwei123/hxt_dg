package com.hxt.hBank.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hBank.dao.HBankDAO;
import com.hxt.hBank.model.HBank;
import com.base.utils.ResponseList;

/**
 * @author	wuzhenwei
 * @time	2016年08月31日 00:17:11
 */
 @Service("hBankService")
public class HBankService {

	@Resource(name = "hBankDao")
    private HBankDAO hBankDAO;
    
    public ResponseList<HBank> getHBankList(HBank hBank) {
        return hBankDAO.getHBankList(hBank);
    }
    
    public List<HBank> getHBankBaseList(HBank hBank) {
        return hBankDAO.getHBankBaseList(hBank);
    }
    
    public int getHBankListCount(HBank hBank) {
        return hBankDAO.getHBankListCount(hBank);
    }

    public HBank getHBank(HBank hBank) { 
        return hBankDAO.getHBank(hBank);
    }

    public int insertHBank(HBank hBank) throws Exception {
        return hBankDAO.insertHBank(hBank);
    }

    public int updateHBank(HBank hBank) throws Exception {
        return hBankDAO.updateHBank(hBank);
    }
    
    public int removeHBank(HBank hBank) throws Exception {
        return hBankDAO.removeHBank(hBank);
    }
    
}
