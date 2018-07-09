package com.hxt.hSubOrder.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hSubOrder.dao.HSubOrderDAO;
import com.hxt.hSubOrder.model.HSubOrder;
import com.base.utils.ResponseList;

/**
 * @author	wuzhenwei
 * @time	2015年12月02日 00:01:04
 */
 @Service("hSubOrderService")
public class HSubOrderService {

	@Resource(name = "hSubOrderDao")
    private HSubOrderDAO hSubOrderDAO;
    
    public ResponseList<HSubOrder> getHSubOrderList(HSubOrder hSubOrder) {
        return hSubOrderDAO.getHSubOrderList(hSubOrder);
    }
    
    public List<HSubOrder> getHSubOrderBaseList(HSubOrder hSubOrder) {
        return hSubOrderDAO.getHSubOrderBaseList(hSubOrder);
    }
    
    public int getHSubOrderListCount(HSubOrder hSubOrder) {
        return hSubOrderDAO.getHSubOrderListCount(hSubOrder);
    }

    public HSubOrder getHSubOrder(HSubOrder hSubOrder) { 
        return hSubOrderDAO.getHSubOrder(hSubOrder);
    }

    public int insertHSubOrder(HSubOrder hSubOrder) throws Exception {
        return hSubOrderDAO.insertHSubOrder(hSubOrder);
    }

    public int updateHSubOrder(HSubOrder hSubOrder) throws Exception {
        return hSubOrderDAO.updateHSubOrder(hSubOrder);
    }
    public int updateHSubOrder1(HSubOrder hSubOrder) throws Exception {
    	return hSubOrderDAO.updateHSubOrder1(hSubOrder);
    }
    
    public int removeHSubOrder(HSubOrder hSubOrder) throws Exception {
        return hSubOrderDAO.removeHSubOrder(hSubOrder);
    }
    
}
