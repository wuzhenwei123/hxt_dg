package com.hxt.hKtPay.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hKtPay.dao.HKtPayDAO;
import com.hxt.hKtPay.model.HKtPay;
import com.base.utils.ResponseList;

/**
 * @author	wuzhenwei
 * @time	2016年11月28日 18:53:15
 */
 @Service("hKtPayService")
public class HKtPayService {

	@Resource(name = "hKtPayDao")
    private HKtPayDAO hKtPayDAO;
    
    public ResponseList<HKtPay> getHKtPayList(HKtPay hKtPay) {
        return hKtPayDAO.getHKtPayList(hKtPay);
    }
    
    public List<HKtPay> getHKtPayBaseList(HKtPay hKtPay) {
        return hKtPayDAO.getHKtPayBaseList(hKtPay);
    }
    
    public int getHKtPayListCount(HKtPay hKtPay) {
        return hKtPayDAO.getHKtPayListCount(hKtPay);
    }

    public HKtPay getHKtPay(HKtPay hKtPay) { 
        return hKtPayDAO.getHKtPay(hKtPay);
    }

    public int insertHKtPay(HKtPay hKtPay) throws Exception {
        return hKtPayDAO.insertHKtPay(hKtPay);
    }

    public int updateHKtPay(HKtPay hKtPay) throws Exception {
        return hKtPayDAO.updateHKtPay(hKtPay);
    }
    
    public int removeHKtPay(HKtPay hKtPay) throws Exception {
        return hKtPayDAO.removeHKtPay(hKtPay);
    }
    
}
