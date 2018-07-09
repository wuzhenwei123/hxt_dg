package com.hxt.hRecRecord.dao;

import java.util.List;

import com.hxt.hRecRecord.model.HRecRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2016年09月05日 17:15:23
 */
 @Repository("hRecRecordDao")
public class HRecRecordDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HRecRecord> getHRecRecordList(HRecRecord hRecRecord) {
		List<HRecRecord> list = sqlMapClient.queryForList("HRecRecord.getHRecRecordList", hRecRecord);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HRecRecord> getHRecRecordBaseList(HRecRecord hRecRecord) {
		return sqlMapClient.queryForList("HRecRecord.getHRecRecord", hRecRecord);
	}

	public int getHRecRecordListCount(HRecRecord hRecRecord) {
		return (Integer)sqlMapClient.queryForObject("HRecRecord.getHRecRecordListCount", hRecRecord);
	}
	
	public HRecRecord getHRecRecord(HRecRecord hRecRecord) {
		return (HRecRecord)sqlMapClient.queryForObject("HRecRecord.getHRecRecord", hRecRecord);
	}

    public int insertHRecRecord(HRecRecord hRecRecord) throws Exception {
        return (Integer)sqlMapClient.insert("HRecRecord.insertHRecRecord", hRecRecord);
    }

    public int updateHRecRecord(HRecRecord hRecRecord) throws Exception {
        return sqlMapClient.update("HRecRecord.updateHRecRecord", hRecRecord);
    }
    
    public int removeHRecRecord(HRecRecord hRecRecord) throws Exception {
        return sqlMapClient.delete("HRecRecord.removeHRecRecord", hRecRecord);
    }

}
