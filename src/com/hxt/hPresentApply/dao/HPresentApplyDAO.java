package com.hxt.hPresentApply.dao;

import java.util.List;

import com.hxt.hPresentApply.model.HPresentApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	zhangyiyang
 * @time	2016年08月29日 23:04:33
 */
 @Repository("hPresentApplyDao")
public class HPresentApplyDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HPresentApply> getHPresentApplyList(HPresentApply hPresentApply) {
		List<HPresentApply> list = sqlMapClient.queryForList("HPresentApply.getHPresentApplyList", hPresentApply);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HPresentApply> getHPresentApplyBaseList(HPresentApply hPresentApply) {
		return sqlMapClient.queryForList("HPresentApply.getHPresentApply", hPresentApply);
	}

	public int getHPresentApplyListCount(HPresentApply hPresentApply) {
		return (Integer)sqlMapClient.queryForObject("HPresentApply.getHPresentApplyListCount", hPresentApply);
	}
	
	public HPresentApply getHPresentApply(HPresentApply hPresentApply) {
		return (HPresentApply)sqlMapClient.queryForObject("HPresentApply.getHPresentApply", hPresentApply);
	}

    public int insertHPresentApply(HPresentApply hPresentApply) throws Exception {
        return (Integer)sqlMapClient.insert("HPresentApply.insertHPresentApply", hPresentApply);
    }

    public int updateHPresentApply(HPresentApply hPresentApply) throws Exception {
        return sqlMapClient.update("HPresentApply.updateHPresentApply", hPresentApply);
    }
    
    public int unBindPresentApply(HPresentApply hPresentApply) throws Exception {
    	return sqlMapClient.update("HPresentApply.unBindPresentApply", hPresentApply);
    }
    
    public int removeHPresentApply(HPresentApply hPresentApply) throws Exception {
        return sqlMapClient.delete("HPresentApply.removeHPresentApply", hPresentApply);
    }

}
