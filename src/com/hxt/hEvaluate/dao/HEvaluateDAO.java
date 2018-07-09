package com.hxt.hEvaluate.dao;

import java.util.List;

import com.hxt.hEvaluate.model.HEvaluate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	zhangyiyang
 * @time	2016年08月22日 10:55:38
 */
 @Repository("hEvaluateDao")
public class HEvaluateDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HEvaluate> getHEvaluateList(HEvaluate hEvaluate) {
		List<HEvaluate> list = sqlMapClient.queryForList("HEvaluate.getHEvaluateList", hEvaluate);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HEvaluate> getHEvaluateBaseList(HEvaluate hEvaluate) {
		return sqlMapClient.queryForList("HEvaluate.getHEvaluate", hEvaluate);
	}

	public int getHEvaluateListCount(HEvaluate hEvaluate) {
		return (Integer)sqlMapClient.queryForObject("HEvaluate.getHEvaluateListCount", hEvaluate);
	}
	
	public HEvaluate getHEvaluate(HEvaluate hEvaluate) {
		return (HEvaluate)sqlMapClient.queryForObject("HEvaluate.getHEvaluate", hEvaluate);
	}

    public int insertHEvaluate(HEvaluate hEvaluate) throws Exception {
        return (Integer)sqlMapClient.insert("HEvaluate.insertHEvaluate", hEvaluate);
    }

    public int updateHEvaluate(HEvaluate hEvaluate) throws Exception {
        return sqlMapClient.update("HEvaluate.updateHEvaluate", hEvaluate);
    }
    
    public int removeHEvaluate(HEvaluate hEvaluate) throws Exception {
        return sqlMapClient.delete("HEvaluate.removeHEvaluate", hEvaluate);
    }

}
