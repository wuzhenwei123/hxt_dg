package com.hxt.hDispatchRecord.dao;

import java.util.List;

import com.hxt.hDispatchRecord.model.HDispatchRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2015年11月18日 22:41:22
 */
 @Repository("hDispatchRecordDao")
public class HDispatchRecordDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HDispatchRecord> getHDispatchRecordList(HDispatchRecord hDispatchRecord) {
		List<HDispatchRecord> list = sqlMapClient.queryForList("HDispatchRecord.getHDispatchRecordList", hDispatchRecord);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HDispatchRecord> getHDispatchRecordBaseList(HDispatchRecord hDispatchRecord) {
		return sqlMapClient.queryForList("HDispatchRecord.getHDispatchRecord", hDispatchRecord);
	}

	public int getHDispatchRecordListCount(HDispatchRecord hDispatchRecord) {
		return (Integer)sqlMapClient.queryForObject("HDispatchRecord.getHDispatchRecordListCount", hDispatchRecord);
	}
	
	public HDispatchRecord getHDispatchRecord(HDispatchRecord hDispatchRecord) {
		return (HDispatchRecord)sqlMapClient.queryForObject("HDispatchRecord.getHDispatchRecord", hDispatchRecord);
	}

    public int insertHDispatchRecord(HDispatchRecord hDispatchRecord) throws Exception {
        return (Integer)sqlMapClient.insert("HDispatchRecord.insertHDispatchRecord", hDispatchRecord);
    }

    public int updateHDispatchRecord(HDispatchRecord hDispatchRecord) throws Exception {
        return sqlMapClient.update("HDispatchRecord.updateHDispatchRecord", hDispatchRecord);
    }
    
    public int updateHDispatchRecordByNo(HDispatchRecord hDispatchRecord) throws Exception {
    	return sqlMapClient.update("HDispatchRecord.updateHDispatchRecordByNo", hDispatchRecord);
    }
    
    public int removeHDispatchRecord(HDispatchRecord hDispatchRecord) throws Exception {
        return sqlMapClient.delete("HDispatchRecord.removeHDispatchRecord", hDispatchRecord);
    }

}
