package com.hxt.hBankInfo.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hBankInfo.dao.HBankInfoDAO;
import com.hxt.hBankInfo.model.HBankInfo;
import com.base.utils.ResponseList;

/**
 * @author	wuzhenwei
 * @time	2016年10月20日 17:51:00
 */
 @Service("hBankInfoService")
public class HBankInfoService {

	@Resource(name = "hBankInfoDao")
    private HBankInfoDAO hBankInfoDAO;
    
    public ResponseList<HBankInfo> getHBankInfoList(HBankInfo hBankInfo) {
        return hBankInfoDAO.getHBankInfoList(hBankInfo);
    }
    
    public List<HBankInfo> getHBankInfoBaseList(HBankInfo hBankInfo) {
        return hBankInfoDAO.getHBankInfoBaseList(hBankInfo);
    }
    
    public int getHBankInfoListCount(HBankInfo hBankInfo) {
        return hBankInfoDAO.getHBankInfoListCount(hBankInfo);
    }

    public HBankInfo getHBankInfo(HBankInfo hBankInfo) { 
        return hBankInfoDAO.getHBankInfo(hBankInfo);
    }

    public int insertHBankInfo(HBankInfo hBankInfo) throws Exception {
        return hBankInfoDAO.insertHBankInfo(hBankInfo);
    }

    public int updateHBankInfo(HBankInfo hBankInfo) throws Exception {
        return hBankInfoDAO.updateHBankInfo(hBankInfo);
    }
    
    public int removeHBankInfo(HBankInfo hBankInfo) throws Exception {
        return hBankInfoDAO.removeHBankInfo(hBankInfo);
    }
    
}
