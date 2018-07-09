package com.hxt.hPay.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hPay.dao.HPayDAO;
import com.hxt.hPay.model.HPay;
import com.base.utils.ResponseList;

/**
 * @author	wuzhenwei
 * @time	2016年10月20日 09:14:24
 */
 @Service("hPayService")
public class HPayService {

	@Resource(name = "hPayDao")
    private HPayDAO hPayDAO;
    
    public ResponseList<HPay> getHPayList(HPay hPay) {
        return hPayDAO.getHPayList(hPay);
    }
    
    public List<HPay> getHPayBaseList(HPay hPay) {
        return hPayDAO.getHPayBaseList(hPay);
    }
    
    public int getHPayListCount(HPay hPay) {
        return hPayDAO.getHPayListCount(hPay);
    }

    public HPay getHPay(HPay hPay) { 
        return hPayDAO.getHPay(hPay);
    }

    public int insertHPay(HPay hPay) throws Exception {
        return hPayDAO.insertHPay(hPay);
    }

    public int updateHPay(HPay hPay) throws Exception {
        return hPayDAO.updateHPay(hPay);
    }
    
    public int removeHPay(HPay hPay) throws Exception {
        return hPayDAO.removeHPay(hPay);
    }
    
}
