package com.hxt.hDispatchRecord.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hDispatchRecord.dao.HDispatchRecordDAO;
import com.hxt.hDispatchRecord.model.HDispatchRecord;
import com.base.utils.ResponseList;

/**
 * @author	wuzhenwei
 * @time	2015年11月18日 22:41:23
 */
 @Service("hDispatchRecordService")
public class HDispatchRecordService {

	@Resource(name = "hDispatchRecordDao")
    private HDispatchRecordDAO hDispatchRecordDAO;
    
    public ResponseList<HDispatchRecord> getHDispatchRecordList(HDispatchRecord hDispatchRecord) {
        return hDispatchRecordDAO.getHDispatchRecordList(hDispatchRecord);
    }
    
    public List<HDispatchRecord> getHDispatchRecordBaseList(HDispatchRecord hDispatchRecord) {
        return hDispatchRecordDAO.getHDispatchRecordBaseList(hDispatchRecord);
    }
    
    public int getHDispatchRecordListCount(HDispatchRecord hDispatchRecord) {
        return hDispatchRecordDAO.getHDispatchRecordListCount(hDispatchRecord);
    }

    public HDispatchRecord getHDispatchRecord(HDispatchRecord hDispatchRecord) { 
        return hDispatchRecordDAO.getHDispatchRecord(hDispatchRecord);
    }

    public int insertHDispatchRecord(HDispatchRecord hDispatchRecord) throws Exception {
        return hDispatchRecordDAO.insertHDispatchRecord(hDispatchRecord);
    }

    public int updateHDispatchRecord(HDispatchRecord hDispatchRecord) throws Exception {
        return hDispatchRecordDAO.updateHDispatchRecord(hDispatchRecord);
    }
    
    public int updateHDispatchRecordByNo(HDispatchRecord hDispatchRecord) throws Exception {
    	return hDispatchRecordDAO.updateHDispatchRecordByNo(hDispatchRecord);
    }
    
    public int removeHDispatchRecord(HDispatchRecord hDispatchRecord) throws Exception {
        return hDispatchRecordDAO.removeHDispatchRecord(hDispatchRecord);
    }
    
}
