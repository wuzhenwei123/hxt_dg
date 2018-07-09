package com.hxt.hOperatorDot.dao;

import java.util.List;

import com.hxt.hOperatorDot.model.HOperatorDot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	zlb
 * @time	2016年08月06日 21:52:18
 */
 @Repository("hOperatorDotDao")
public class HOperatorDotDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HOperatorDot> getHOperatorDotList(HOperatorDot hOperatorDot) {
		List<HOperatorDot> list = sqlMapClient.queryForList("HOperatorDot.getHOperatorDotList", hOperatorDot);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HOperatorDot> getHOperatorDotBaseList(HOperatorDot hOperatorDot) {
		return sqlMapClient.queryForList("HOperatorDot.getHOperatorDot", hOperatorDot);
	}

	public int getHOperatorDotListCount(HOperatorDot hOperatorDot) {
		return (Integer)sqlMapClient.queryForObject("HOperatorDot.getHOperatorDotListCount", hOperatorDot);
	}
	
	public HOperatorDot getHOperatorDot(HOperatorDot hOperatorDot) {
		return (HOperatorDot)sqlMapClient.queryForObject("HOperatorDot.getHOperatorDot", hOperatorDot);
	}

    public int insertHOperatorDot(HOperatorDot hOperatorDot) throws Exception {
        return (Integer)sqlMapClient.insert("HOperatorDot.insertHOperatorDot", hOperatorDot);
    }

    public int updateHOperatorDot(HOperatorDot hOperatorDot) throws Exception {
        return sqlMapClient.update("HOperatorDot.updateHOperatorDot", hOperatorDot);
    }
    
    public int removeHOperatorDot(HOperatorDot hOperatorDot) throws Exception {
        return sqlMapClient.delete("HOperatorDot.removeHOperatorDot", hOperatorDot);
    }

}
