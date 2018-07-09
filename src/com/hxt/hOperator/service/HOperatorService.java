package com.hxt.hOperator.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hOperator.dao.HOperatorDAO;
import com.hxt.hOperator.model.HOperator;
import com.base.utils.ResponseList;

/**
 * @author	zlb
 * @time	2016年08月05日 14:30:14
 */
 @Service("hOperatorService")
public class HOperatorService {

	@Resource(name = "hOperatorDao")
    private HOperatorDAO hOperatorDAO;
    
    public ResponseList<HOperator> getHOperatorList(HOperator hOperator) {
        return hOperatorDAO.getHOperatorList(hOperator);
    }
    
    public List<HOperator> getHOperatorBaseList(HOperator hOperator) {
        return hOperatorDAO.getHOperatorBaseList(hOperator);
    }
    
    public int getHOperatorListCount(HOperator hOperator) {
        return hOperatorDAO.getHOperatorListCount(hOperator);
    }

    public HOperator getHOperator(HOperator hOperator) { 
        return hOperatorDAO.getHOperator(hOperator);
    }

    public int insertHOperator(HOperator hOperator) throws Exception {
        return hOperatorDAO.insertHOperator(hOperator);
    }

    public int updateHOperator(HOperator hOperator) throws Exception {
        return hOperatorDAO.updateHOperator(hOperator);
    }
    
    public int removeHOperator(HOperator hOperator) throws Exception {
        return hOperatorDAO.removeHOperator(hOperator);
    }
    
}
