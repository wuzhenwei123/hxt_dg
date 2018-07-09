package com.hxt.hAmmeterQueryLog.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hAmmeterQueryLog.dao.HAmmeterQueryLogDAO;
import com.hxt.hAmmeterQueryLog.model.HAmmeterQueryLog;
import com.base.utils.ResponseList;

/**
 * @author	wuzhenwei
 * @time	2016年09月20日 12:15:06
 */
 @Service("hAmmeterQueryLogService")
public class HAmmeterQueryLogService {

	@Resource(name = "hAmmeterQueryLogDao")
    private HAmmeterQueryLogDAO hAmmeterQueryLogDAO;
    
    public ResponseList<HAmmeterQueryLog> getHAmmeterQueryLogList(HAmmeterQueryLog hAmmeterQueryLog) {
        return hAmmeterQueryLogDAO.getHAmmeterQueryLogList(hAmmeterQueryLog);
    }
    
    public List<HAmmeterQueryLog> getHAmmeterQueryLogBaseList(HAmmeterQueryLog hAmmeterQueryLog) {
        return hAmmeterQueryLogDAO.getHAmmeterQueryLogBaseList(hAmmeterQueryLog);
    }
    
    public int getHAmmeterQueryLogListCount(HAmmeterQueryLog hAmmeterQueryLog) {
        return hAmmeterQueryLogDAO.getHAmmeterQueryLogListCount(hAmmeterQueryLog);
    }

    public HAmmeterQueryLog getHAmmeterQueryLog(HAmmeterQueryLog hAmmeterQueryLog) { 
        return hAmmeterQueryLogDAO.getHAmmeterQueryLog(hAmmeterQueryLog);
    }

    public int insertHAmmeterQueryLog(HAmmeterQueryLog hAmmeterQueryLog) throws Exception {
        return hAmmeterQueryLogDAO.insertHAmmeterQueryLog(hAmmeterQueryLog);
    }

    public int updateHAmmeterQueryLog(HAmmeterQueryLog hAmmeterQueryLog) throws Exception {
        return hAmmeterQueryLogDAO.updateHAmmeterQueryLog(hAmmeterQueryLog);
    }
    
    public int removeHAmmeterQueryLog(HAmmeterQueryLog hAmmeterQueryLog) throws Exception {
        return hAmmeterQueryLogDAO.removeHAmmeterQueryLog(hAmmeterQueryLog);
    }
    
}
