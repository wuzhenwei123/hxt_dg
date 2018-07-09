package com.hxt.hRegGuli.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hRegGuli.dao.HRegGuliDAO;
import com.hxt.hRegGuli.model.HRegGuli;
import com.base.utils.ResponseList;

/**
 * @author	wuzhenwei
 * @time	2017年04月06日 17:50:28
 */
 @Service("hRegGuliService")
public class HRegGuliService {

	@Resource(name = "hRegGuliDao")
    private HRegGuliDAO hRegGuliDAO;
    
    public ResponseList<HRegGuli> getHRegGuliList(HRegGuli hRegGuli) {
        return hRegGuliDAO.getHRegGuliList(hRegGuli);
    }
    
    public List<HRegGuli> getHRegGuliBaseList(HRegGuli hRegGuli) {
        return hRegGuliDAO.getHRegGuliBaseList(hRegGuli);
    }
    
    public int getHRegGuliListCount(HRegGuli hRegGuli) {
        return hRegGuliDAO.getHRegGuliListCount(hRegGuli);
    }
    
    public int checkDefault(HRegGuli hRegGuli) {
    	return hRegGuliDAO.checkDefault(hRegGuli);
    }

    public HRegGuli getHRegGuli(HRegGuli hRegGuli) { 
        return hRegGuliDAO.getHRegGuli(hRegGuli);
    }

    public int insertHRegGuli(HRegGuli hRegGuli) throws Exception {
        return hRegGuliDAO.insertHRegGuli(hRegGuli);
    }

    public int updateHRegGuli(HRegGuli hRegGuli) throws Exception {
        return hRegGuliDAO.updateHRegGuli(hRegGuli);
    }
    
    public int removeHRegGuli(HRegGuli hRegGuli) throws Exception {
        return hRegGuliDAO.removeHRegGuli(hRegGuli);
    }
    
}
