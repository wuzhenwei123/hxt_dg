package com.hxt.hLoginLog.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hLoginLog.dao.HLoginLogDAO;
import com.hxt.hLoginLog.model.HLoginLog;
import com.base.utils.ResponseList;

/**
 * @author	wuzhenwei
 * @time	2016年09月19日 11:08:42
 */
 @Service("hLoginLogService")
public class HLoginLogService {

	@Resource(name = "hLoginLogDao")
    private HLoginLogDAO hLoginLogDAO;
    
    public ResponseList<HLoginLog> getHLoginLogList(HLoginLog hLoginLog) {
        return hLoginLogDAO.getHLoginLogList(hLoginLog);
    }
    
    public List<HLoginLog> getHLoginLogBaseList(HLoginLog hLoginLog) {
        return hLoginLogDAO.getHLoginLogBaseList(hLoginLog);
    }
    
    public int getHLoginLogListCount(HLoginLog hLoginLog) {
        return hLoginLogDAO.getHLoginLogListCount(hLoginLog);
    }

    public HLoginLog getHLoginLog(HLoginLog hLoginLog) { 
        return hLoginLogDAO.getHLoginLog(hLoginLog);
    }

    public int insertHLoginLog(HLoginLog hLoginLog) throws Exception {
        return hLoginLogDAO.insertHLoginLog(hLoginLog);
    }

    public int updateHLoginLog(HLoginLog hLoginLog) throws Exception {
        return hLoginLogDAO.updateHLoginLog(hLoginLog);
    }
    
    public int removeHLoginLog(HLoginLog hLoginLog) throws Exception {
        return hLoginLogDAO.removeHLoginLog(hLoginLog);
    }
    
}
