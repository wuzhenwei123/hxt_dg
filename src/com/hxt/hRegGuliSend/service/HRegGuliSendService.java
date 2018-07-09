package com.hxt.hRegGuliSend.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hRegGuliSend.dao.HRegGuliSendDAO;
import com.hxt.hRegGuliSend.model.HRegGuliSend;
import com.base.utils.ResponseList;

/**
 * @author	wuzhenwei
 * @time	2017年04月06日 18:01:33
 */
 @Service("hRegGuliSendService")
public class HRegGuliSendService {

	@Resource(name = "hRegGuliSendDao")
    private HRegGuliSendDAO hRegGuliSendDAO;
    
    public ResponseList<HRegGuliSend> getHRegGuliSendList(HRegGuliSend hRegGuliSend) {
        return hRegGuliSendDAO.getHRegGuliSendList(hRegGuliSend);
    }
    
    public List<HRegGuliSend> getHRegGuliSendBaseList(HRegGuliSend hRegGuliSend) {
        return hRegGuliSendDAO.getHRegGuliSendBaseList(hRegGuliSend);
    }
    
    public int getHRegGuliSendListCount(HRegGuliSend hRegGuliSend) {
        return hRegGuliSendDAO.getHRegGuliSendListCount(hRegGuliSend);
    }

    public HRegGuliSend getHRegGuliSend(HRegGuliSend hRegGuliSend) { 
        return hRegGuliSendDAO.getHRegGuliSend(hRegGuliSend);
    }

    public int insertHRegGuliSend(HRegGuliSend hRegGuliSend) throws Exception {
        return hRegGuliSendDAO.insertHRegGuliSend(hRegGuliSend);
    }

    public int updateHRegGuliSend(HRegGuliSend hRegGuliSend) throws Exception {
        return hRegGuliSendDAO.updateHRegGuliSend(hRegGuliSend);
    }
    
    public int removeHRegGuliSend(HRegGuliSend hRegGuliSend) throws Exception {
        return hRegGuliSendDAO.removeHRegGuliSend(hRegGuliSend);
    }
    
}
