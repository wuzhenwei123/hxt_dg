package com.hxt.hReferee.dao;

import java.util.List;

import com.hxt.hReferee.model.HReferee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2015年09月04日 14:33:54
 */
 @Repository("hRefereeDao")
public class HRefereeDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HReferee> getHRefereeList(HReferee hReferee) {
		List<HReferee> list = sqlMapClient.queryForList("HReferee.getHRefereeList", hReferee);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HReferee> getHRefereeBaseList(HReferee hReferee) {
		return sqlMapClient.queryForList("HReferee.getHReferee", hReferee);
	}

	public int getHRefereeListCount(HReferee hReferee) {
		return (Integer)sqlMapClient.queryForObject("HReferee.getHRefereeListCount", hReferee);
	}
	
	public HReferee getHReferee(HReferee hReferee) {
		return (HReferee)sqlMapClient.queryForObject("HReferee.getHReferee", hReferee);
	}

    public int insertHReferee(HReferee hReferee) throws Exception {
        return (Integer)sqlMapClient.insert("HReferee.insertHReferee", hReferee);
    }

    public int updateHReferee(HReferee hReferee) throws Exception {
        return sqlMapClient.update("HReferee.updateHReferee", hReferee);
    }
    
    public int removeHReferee(HReferee hReferee) throws Exception {
        return sqlMapClient.delete("HReferee.removeHReferee", hReferee);
    }

}
