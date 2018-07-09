package com.hxt.hPayurlCheck.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hPayurlCheck.dao.HPayurlCheckDAO;
import com.hxt.hPayurlCheck.model.HPayurlCheck;
import com.base.utils.ResponseList;

/**
 * @author	wuzhenwei
 * @time	2015年11月28日 22:43:26
 */
 @Service("hPayurlCheckService")
public class HPayurlCheckService {

	@Resource(name = "hPayurlCheckDao")
    private HPayurlCheckDAO hPayurlCheckDAO;
    
    public ResponseList<HPayurlCheck> getHPayurlCheckList(HPayurlCheck hPayurlCheck) {
        return hPayurlCheckDAO.getHPayurlCheckList(hPayurlCheck);
    }
    
    public List<HPayurlCheck> getHPayurlCheckBaseList(HPayurlCheck hPayurlCheck) {
        return hPayurlCheckDAO.getHPayurlCheckBaseList(hPayurlCheck);
    }
    
    public int getHPayurlCheckListCount(HPayurlCheck hPayurlCheck) {
        return hPayurlCheckDAO.getHPayurlCheckListCount(hPayurlCheck);
    }

    public HPayurlCheck getHPayurlCheck(HPayurlCheck hPayurlCheck) { 
        return hPayurlCheckDAO.getHPayurlCheck(hPayurlCheck);
    }

    public int insertHPayurlCheck(HPayurlCheck hPayurlCheck) throws Exception {
        return hPayurlCheckDAO.insertHPayurlCheck(hPayurlCheck);
    }

    public int updateHPayurlCheck(HPayurlCheck hPayurlCheck) throws Exception {
        return hPayurlCheckDAO.updateHPayurlCheck(hPayurlCheck);
    }
    
    public int removeHPayurlCheck(HPayurlCheck hPayurlCheck) throws Exception {
        return hPayurlCheckDAO.removeHPayurlCheck(hPayurlCheck);
    }

    public HPayurlCheck findNowHPayurlCheck(HPayurlCheck hPayurlCheck) {
        return hPayurlCheckDAO.findNowHPayurlCheck(hPayurlCheck);
    }
    
}
