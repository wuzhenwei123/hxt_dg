package com.hxt.hOperator.dao;

import java.util.List;

import com.hxt.hOperator.model.HOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	zlb
 * @time	2016年08月05日 14:30:12
 */
 @Repository("hOperatorDao")
public class HOperatorDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HOperator> getHOperatorList(HOperator hOperator) {
		List<HOperator> list = sqlMapClient.queryForList("HOperator.getHOperatorList", hOperator);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HOperator> getHOperatorBaseList(HOperator hOperator) {
		return sqlMapClient.queryForList("HOperator.getHOperator", hOperator);
	}

	public int getHOperatorListCount(HOperator hOperator) {
		return (Integer)sqlMapClient.queryForObject("HOperator.getHOperatorListCount", hOperator);
	}
	
	public HOperator getHOperator(HOperator hOperator) {
		return (HOperator)sqlMapClient.queryForObject("HOperator.getHOperator", hOperator);
	}

    public int insertHOperator(HOperator hOperator) throws Exception {
        return (Integer)sqlMapClient.insert("HOperator.insertHOperator", hOperator);
    }

    public int updateHOperator(HOperator hOperator) throws Exception {
        return sqlMapClient.update("HOperator.updateHOperator", hOperator);
    }
    
    public int removeHOperator(HOperator hOperator) throws Exception {
        return sqlMapClient.delete("HOperator.removeHOperator", hOperator);
    }

}
