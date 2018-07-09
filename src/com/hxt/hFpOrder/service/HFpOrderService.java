package com.hxt.hFpOrder.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.utils.ResponseList;
import com.hxt.hFpOrder.dao.HFpOrderDAO;
import com.hxt.hFpOrder.model.HFpOrder;

/**
 * @author	wuzhenwei
 * @time	2016年01月12日 22:02:35
 */
 @Service("hFpOrderService")
public class HFpOrderService {

	@Resource(name = "hFpOrderDao")
    private HFpOrderDAO hFpOrderDAO;
    
    public ResponseList<HFpOrder> getHFpOrderList(HFpOrder hFpOrder) {
        return hFpOrderDAO.getHFpOrderList(hFpOrder);
    }
    
    public List<HFpOrder> getHFpOrderBaseList(HFpOrder hFpOrder) {
        return hFpOrderDAO.getHFpOrderBaseList(hFpOrder);
    }
    
    public int getHFpOrderListCount(HFpOrder hFpOrder) {
        return hFpOrderDAO.getHFpOrderListCount(hFpOrder);
    }

    public HFpOrder getHFpOrder(HFpOrder hFpOrder) { 
        return hFpOrderDAO.getHFpOrder(hFpOrder);
    }

    public int insertHFpOrder(HFpOrder hFpOrder) throws Exception {
        return hFpOrderDAO.insertHFpOrder(hFpOrder);
    }
    public int insertHFpOrder(List<HFpOrder> list) throws Exception {
    	try{
    		if(list!=null&&list.size()>0){
    			for(HFpOrder f : list){
    				List list1 = hFpOrderDAO.getHFpOrderBaseList(f);
    				if(list1!=null&&list1.size()>0){
    					
    				}else{
    					hFpOrderDAO.insertHFpOrder(f);
    				}
    			}
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		return -1;
    	}
        return 1;
    }

    public int updateHFpOrder(HFpOrder hFpOrder) throws Exception {
        return hFpOrderDAO.updateHFpOrder(hFpOrder);
    }
    
    public int removeHFpOrder(HFpOrder hFpOrder) throws Exception {
        return hFpOrderDAO.removeHFpOrder(hFpOrder);
    }
    
}
