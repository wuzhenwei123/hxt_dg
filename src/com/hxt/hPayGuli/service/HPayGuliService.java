package com.hxt.hPayGuli.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hPayGuli.dao.HPayGuliDAO;
import com.hxt.hPayGuli.model.HPayGuli;
import com.base.utils.ResponseList;

/**
 * @author	wuzhenwei
 * @time	2017年04月08日 17:00:50
 */
 @Service("hPayGuliService")
public class HPayGuliService {

	@Resource(name = "hPayGuliDao")
    private HPayGuliDAO hPayGuliDAO;
    
    public ResponseList<HPayGuli> getHPayGuliList(HPayGuli hPayGuli) {
        return hPayGuliDAO.getHPayGuliList(hPayGuli);
    }
    
    public List<HPayGuli> getHPayGuliBaseList(HPayGuli hPayGuli) {
        return hPayGuliDAO.getHPayGuliBaseList(hPayGuli);
    }
    
    public int getHPayGuliListCount(HPayGuli hPayGuli) {
        return hPayGuliDAO.getHPayGuliListCount(hPayGuli);
    }
    
    public int checkDefault(HPayGuli hPayGuli) {
    	return hPayGuliDAO.checkDefault(hPayGuli);
    }

    public HPayGuli getHPayGuli(HPayGuli hPayGuli) { 
        return hPayGuliDAO.getHPayGuli(hPayGuli);
    }

    public int insertHPayGuli(HPayGuli hPayGuli) throws Exception {
        return hPayGuliDAO.insertHPayGuli(hPayGuli);
    }

    public int updateHPayGuli(HPayGuli hPayGuli) throws Exception {
        return hPayGuliDAO.updateHPayGuli(hPayGuli);
    }
    
    public int updateHPayGuli1(HPayGuli hPayGuli) throws Exception {
    	return hPayGuliDAO.updateHPayGuli1(hPayGuli);
    }
    
    public int removeHPayGuli(HPayGuli hPayGuli) throws Exception {
        return hPayGuliDAO.removeHPayGuli(hPayGuli);
    }
    
}
