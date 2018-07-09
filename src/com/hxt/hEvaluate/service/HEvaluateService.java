package com.hxt.hEvaluate.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hEvaluate.dao.HEvaluateDAO;
import com.hxt.hEvaluate.model.HEvaluate;
import com.base.utils.ResponseList;

/**
 * @author	zhangyiyang
 * @time	2016年08月22日 10:55:38
 */
 @Service("hEvaluateService")
public class HEvaluateService {

	@Resource(name = "hEvaluateDao")
    private HEvaluateDAO hEvaluateDAO;
    
    public ResponseList<HEvaluate> getHEvaluateList(HEvaluate hEvaluate) {
        return hEvaluateDAO.getHEvaluateList(hEvaluate);
    }
    
    public List<HEvaluate> getHEvaluateBaseList(HEvaluate hEvaluate) {
        return hEvaluateDAO.getHEvaluateBaseList(hEvaluate);
    }
    
    public int getHEvaluateListCount(HEvaluate hEvaluate) {
        return hEvaluateDAO.getHEvaluateListCount(hEvaluate);
    }

    public HEvaluate getHEvaluate(HEvaluate hEvaluate) { 
        return hEvaluateDAO.getHEvaluate(hEvaluate);
    }

    public int insertHEvaluate(HEvaluate hEvaluate) throws Exception {
        return hEvaluateDAO.insertHEvaluate(hEvaluate);
    }

    public int updateHEvaluate(HEvaluate hEvaluate) throws Exception {
        return hEvaluateDAO.updateHEvaluate(hEvaluate);
    }
    
    public int removeHEvaluate(HEvaluate hEvaluate) throws Exception {
        return hEvaluateDAO.removeHEvaluate(hEvaluate);
    }
    
}
