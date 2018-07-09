package com.hxt.hDispatchRecordC.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hDispatchRecordC.dao.HDispatchRecordCDAO;
import com.hxt.hDispatchRecordC.model.HDispatchRecordC;
import com.base.utils.ResponseList;

/**
 * @author	wuzhenwei
 * @time	2016年07月21日 11:17:01
 */
 @Service("hDispatchRecordCService")
public class HDispatchRecordCService {

	@Resource(name = "hDispatchRecordCDao")
    private HDispatchRecordCDAO hDispatchRecordCDAO;
    
    public ResponseList<HDispatchRecordC> getHDispatchRecordCList(HDispatchRecordC hDispatchRecordC) {
        return hDispatchRecordCDAO.getHDispatchRecordCList(hDispatchRecordC);
    }
    
    public List<HDispatchRecordC> getHDispatchRecordCBaseList(HDispatchRecordC hDispatchRecordC) {
        return hDispatchRecordCDAO.getHDispatchRecordCBaseList(hDispatchRecordC);
    }
    
    public int getHDispatchRecordCListCount(HDispatchRecordC hDispatchRecordC) {
        return hDispatchRecordCDAO.getHDispatchRecordCListCount(hDispatchRecordC);
    }

    public HDispatchRecordC getHDispatchRecordC(HDispatchRecordC hDispatchRecordC) { 
        return hDispatchRecordCDAO.getHDispatchRecordC(hDispatchRecordC);
    }

    public int insertHDispatchRecordC(HDispatchRecordC hDispatchRecordC) throws Exception {
        return hDispatchRecordCDAO.insertHDispatchRecordC(hDispatchRecordC);
    }

    public int updateHDispatchRecordC(HDispatchRecordC hDispatchRecordC) throws Exception {
        return hDispatchRecordCDAO.updateHDispatchRecordC(hDispatchRecordC);
    }
    
    public int removeHDispatchRecordC(HDispatchRecordC hDispatchRecordC) throws Exception {
        return hDispatchRecordCDAO.removeHDispatchRecordC(hDispatchRecordC);
    }
    
}
