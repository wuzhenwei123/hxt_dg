package com.hxt.hNotice.dao;

import java.util.List;

import com.hxt.hNotice.model.HNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	zlb
 * @time	2015年09月19日 20:15:21
 */
 @Repository("hNoticeDao")
public class HNoticeDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HNotice> getHNoticeList(HNotice hNotice) {
		List<HNotice> list = sqlMapClient.queryForList("HNotice.getHNoticeList", hNotice);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HNotice> getHNoticeBaseList(HNotice hNotice) {
		return sqlMapClient.queryForList("HNotice.getHNotice", hNotice);
	}

	public int getHNoticeListCount(HNotice hNotice) {
		return (Integer)sqlMapClient.queryForObject("HNotice.getHNoticeListCount", hNotice);
	}
	
	public HNotice getHNotice(HNotice hNotice) {
		return (HNotice)sqlMapClient.queryForObject("HNotice.getHNotice", hNotice);
	}

    public int insertHNotice(HNotice hNotice) throws Exception {
        return (Integer)sqlMapClient.insert("HNotice.insertHNotice", hNotice);
    }

    public int updateHNotice(HNotice hNotice) throws Exception {
        return sqlMapClient.update("HNotice.updateHNotice", hNotice);
    }
    
    public int removeHNotice(HNotice hNotice) throws Exception {
        return sqlMapClient.delete("HNotice.removeHNotice", hNotice);
    }

}
