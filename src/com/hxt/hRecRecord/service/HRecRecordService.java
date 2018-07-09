package com.hxt.hRecRecord.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hRecRecord.dao.HRecRecordDAO;
import com.hxt.hRecRecord.model.HRecRecord;
import com.base.utils.ResponseList;

/**
 * @author	wuzhenwei
 * @time	2016年09月05日 17:15:24
 */
 @Service("hRecRecordService")
public class HRecRecordService {

	@Resource(name = "hRecRecordDao")
    private HRecRecordDAO hRecRecordDAO;
    
    public ResponseList<HRecRecord> getHRecRecordList(HRecRecord hRecRecord) {
        return hRecRecordDAO.getHRecRecordList(hRecRecord);
    }
    
    public List<HRecRecord> getHRecRecordBaseList(HRecRecord hRecRecord) {
        return hRecRecordDAO.getHRecRecordBaseList(hRecRecord);
    }
    
    public int getHRecRecordListCount(HRecRecord hRecRecord) {
        return hRecRecordDAO.getHRecRecordListCount(hRecRecord);
    }

    public HRecRecord getHRecRecord(HRecRecord hRecRecord) { 
        return hRecRecordDAO.getHRecRecord(hRecRecord);
    }

    public int insertHRecRecord(HRecRecord hRecRecord) throws Exception {
        return hRecRecordDAO.insertHRecRecord(hRecRecord);
    }

    public int updateHRecRecord(HRecRecord hRecRecord) throws Exception {
        return hRecRecordDAO.updateHRecRecord(hRecRecord);
    }
    
    public int removeHRecRecord(HRecRecord hRecRecord) throws Exception {
        return hRecRecordDAO.removeHRecRecord(hRecRecord);
    }
    
}
