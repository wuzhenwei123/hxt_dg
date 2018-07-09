package com.hxt.hNewNotice.dao;

import java.util.List;

import com.hxt.hNewNotice.model.HNewNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	zhangyiyang
 * @time	2016年08月01日 21:15:50
 */
 @Repository("hNewNoticeDao")
public class HNewNoticeDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HNewNotice> getHNewNoticeList(HNewNotice hNewNotice) {
		List<HNewNotice> list = sqlMapClient.queryForList("HNewNotice.getHNewNoticeList", hNewNotice);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HNewNotice> getHNewNoticeBaseList(HNewNotice hNewNotice) {
		return sqlMapClient.queryForList("HNewNotice.getHNewNotice", hNewNotice);
	}

	public int getHNewNoticeListCount(HNewNotice hNewNotice) {
		return (Integer)sqlMapClient.queryForObject("HNewNotice.getHNewNoticeListCount", hNewNotice);
	}
	
	public HNewNotice getHNewNotice(HNewNotice hNewNotice) {
		return (HNewNotice)sqlMapClient.queryForObject("HNewNotice.getHNewNotice", hNewNotice);
	}

    public int insertHNewNotice(HNewNotice hNewNotice) throws Exception {
        return (Integer)sqlMapClient.insert("HNewNotice.insertHNewNotice", hNewNotice);
    }

    public int updateHNewNotice(HNewNotice hNewNotice) throws Exception {
        return sqlMapClient.update("HNewNotice.updateHNewNotice", hNewNotice);
    }
    
    public int removeHNewNotice(HNewNotice hNewNotice) throws Exception {
        return sqlMapClient.delete("HNewNotice.removeHNewNotice", hNewNotice);
    }
    
    public List<HNewNotice> getNoticeList(HNewNotice hNewNotice) {
		return sqlMapClient.queryForList("HNewNotice.getHNewNoticeList", hNewNotice);
	}

}
