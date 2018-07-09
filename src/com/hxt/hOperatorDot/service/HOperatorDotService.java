package com.hxt.hOperatorDot.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hOperatorDot.dao.HOperatorDotDAO;
import com.hxt.hOperatorDot.model.HOperatorDot;
import com.base.utils.ResponseList;

/**
 * @author	zlb
 * @time	2016年08月06日 21:52:20
 */
 @Service("hOperatorDotService")
public class HOperatorDotService {

	@Resource(name = "hOperatorDotDao")
    private HOperatorDotDAO hOperatorDotDAO;
    
    public ResponseList<HOperatorDot> getHOperatorDotList(HOperatorDot hOperatorDot) {
        return hOperatorDotDAO.getHOperatorDotList(hOperatorDot);
    }
    
    public List<HOperatorDot> getHOperatorDotBaseList(HOperatorDot hOperatorDot) {
        return hOperatorDotDAO.getHOperatorDotBaseList(hOperatorDot);
    }
    
    public int getHOperatorDotListCount(HOperatorDot hOperatorDot) {
        return hOperatorDotDAO.getHOperatorDotListCount(hOperatorDot);
    }

    public HOperatorDot getHOperatorDot(HOperatorDot hOperatorDot) { 
        return hOperatorDotDAO.getHOperatorDot(hOperatorDot);
    }

    public int insertHOperatorDot(HOperatorDot hOperatorDot) throws Exception {
        return hOperatorDotDAO.insertHOperatorDot(hOperatorDot);
    }

    public int updateHOperatorDot(HOperatorDot hOperatorDot) throws Exception {
        return hOperatorDotDAO.updateHOperatorDot(hOperatorDot);
    }
    
    public int removeHOperatorDot(HOperatorDot hOperatorDot) throws Exception {
        return hOperatorDotDAO.removeHOperatorDot(hOperatorDot);
    }
    
}
