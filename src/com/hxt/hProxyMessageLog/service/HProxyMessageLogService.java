package com.hxt.hProxyMessageLog.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hProxyMessageLog.dao.HProxyMessageLogDAO;
import com.hxt.hProxyMessageLog.model.HProxyMessageLog;
import com.base.utils.ResponseList;

/**
 * @author	wuzhenwei
 * @time	2016年11月03日 09:29:09
 */
 @Service("hProxyMessageLogService")
public class HProxyMessageLogService {

	@Resource(name = "hProxyMessageLogDao")
    private HProxyMessageLogDAO hProxyMessageLogDAO;
    
    public ResponseList<HProxyMessageLog> getHProxyMessageLogList(HProxyMessageLog hProxyMessageLog) {
        return hProxyMessageLogDAO.getHProxyMessageLogList(hProxyMessageLog);
    }
    
    public List<HProxyMessageLog> getHProxyMessageLogBaseList(HProxyMessageLog hProxyMessageLog) {
        return hProxyMessageLogDAO.getHProxyMessageLogBaseList(hProxyMessageLog);
    }
    
    public int getHProxyMessageLogListCount(HProxyMessageLog hProxyMessageLog) {
        return hProxyMessageLogDAO.getHProxyMessageLogListCount(hProxyMessageLog);
    }

    public HProxyMessageLog getHProxyMessageLog(HProxyMessageLog hProxyMessageLog) { 
        return hProxyMessageLogDAO.getHProxyMessageLog(hProxyMessageLog);
    }

    public int insertHProxyMessageLog(HProxyMessageLog hProxyMessageLog) throws Exception {
        return hProxyMessageLogDAO.insertHProxyMessageLog(hProxyMessageLog);
    }

    public int updateHProxyMessageLog(HProxyMessageLog hProxyMessageLog) throws Exception {
        return hProxyMessageLogDAO.updateHProxyMessageLog(hProxyMessageLog);
    }
    
    public int removeHProxyMessageLog(HProxyMessageLog hProxyMessageLog) throws Exception {
        return hProxyMessageLogDAO.removeHProxyMessageLog(hProxyMessageLog);
    }
    
}
