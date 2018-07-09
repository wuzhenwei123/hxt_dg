package com.hxt.hAmmeterInfo.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hAmmeterInfo.dao.HAmmeterInfoDAO;
import com.hxt.hAmmeterInfo.model.HAmmeterInfo;
import com.base.utils.ResponseList;

/**
 * @author	zhanglibo
 * @time	2015年11月18日 00:55:00
 */
 @Service("hAmmeterInfoService")
public class HAmmeterInfoService {

	@Resource(name = "hAmmeterInfoDao")
    private HAmmeterInfoDAO hAmmeterInfoDAO;
    
    public ResponseList<HAmmeterInfo> getHAmmeterInfoList(HAmmeterInfo hAmmeterInfo) {
        return hAmmeterInfoDAO.getHAmmeterInfoList(hAmmeterInfo);
    }
    
    public ResponseList<HAmmeterInfo> getHAmmeterInfoList1(HAmmeterInfo hAmmeterInfo) {
    	return hAmmeterInfoDAO.getHAmmeterInfoList1(hAmmeterInfo);
    }
    
    public List<HAmmeterInfo> getHAmmeterInfoBaseList(HAmmeterInfo hAmmeterInfo) {
        return hAmmeterInfoDAO.getHAmmeterInfoBaseList(hAmmeterInfo);
    }
    
    public List<HAmmeterInfo> getHAmmeterInfoBaseList1(HAmmeterInfo hAmmeterInfo) {
    	return hAmmeterInfoDAO.getHAmmeterInfoBaseList1(hAmmeterInfo);
    }
    
    public List<HAmmeterInfo> getHAmmeterInfoListToExport(HAmmeterInfo hAmmeterInfo) {
    	return hAmmeterInfoDAO.getHAmmeterInfoListToExport(hAmmeterInfo);
    }
    
    public int getHAmmeterInfoListCount(HAmmeterInfo hAmmeterInfo) {
        return hAmmeterInfoDAO.getHAmmeterInfoListCount(hAmmeterInfo);
    }
    
    public int getHAmmeterInfoListCount1(HAmmeterInfo hAmmeterInfo) {
    	return hAmmeterInfoDAO.getHAmmeterInfoListCount1(hAmmeterInfo);
    }
    
    public int getHAmmeterInfoListToExportCount(HAmmeterInfo hAmmeterInfo) {
    	return hAmmeterInfoDAO.getHAmmeterInfoListToExportCount(hAmmeterInfo);
    }

    public HAmmeterInfo getHAmmeterInfo(HAmmeterInfo hAmmeterInfo) { 
        return hAmmeterInfoDAO.getHAmmeterInfo(hAmmeterInfo);
    }
    
    public HAmmeterInfo getHAmmeterInfo1(HAmmeterInfo hAmmeterInfo) { 
    	return hAmmeterInfoDAO.getHAmmeterInfo1(hAmmeterInfo);
    }

    public int insertHAmmeterInfo(HAmmeterInfo hAmmeterInfo) throws Exception {
        return hAmmeterInfoDAO.insertHAmmeterInfo(hAmmeterInfo);
    }

    public int updateHAmmeterInfo(HAmmeterInfo hAmmeterInfo) throws Exception {
        return hAmmeterInfoDAO.updateHAmmeterInfo(hAmmeterInfo);
    }
    
    public int removeHAmmeterInfo(HAmmeterInfo hAmmeterInfo) throws Exception {
        return hAmmeterInfoDAO.removeHAmmeterInfo(hAmmeterInfo);
    }
    
    public List<HAmmeterInfo> getHAmmeterInfoListSql(HAmmeterInfo hAmmeterInfo) {
    	return hAmmeterInfoDAO.getHAmmeterInfoListSql(hAmmeterInfo);
    }
    
    public int getHAmmeterInfoListCountSql(HAmmeterInfo hAmmeterInfo) {
    	return hAmmeterInfoDAO.getHAmmeterInfoListCountSql(hAmmeterInfo);
    }
}
