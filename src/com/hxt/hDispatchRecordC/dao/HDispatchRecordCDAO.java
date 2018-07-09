package com.hxt.hDispatchRecordC.dao;

import java.util.List;

import com.hxt.hDispatchRecordC.model.HDispatchRecordC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2016年07月21日 11:17:00
 */
 @Repository("hDispatchRecordCDao")
public class HDispatchRecordCDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HDispatchRecordC> getHDispatchRecordCList(HDispatchRecordC hDispatchRecordC) {
		List<HDispatchRecordC> list = sqlMapClient.queryForList("HDispatchRecordC.getHDispatchRecordCList", hDispatchRecordC);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HDispatchRecordC> getHDispatchRecordCBaseList(HDispatchRecordC hDispatchRecordC) {
		return sqlMapClient.queryForList("HDispatchRecordC.getHDispatchRecordC", hDispatchRecordC);
	}

	public int getHDispatchRecordCListCount(HDispatchRecordC hDispatchRecordC) {
		return (Integer)sqlMapClient.queryForObject("HDispatchRecordC.getHDispatchRecordCListCount", hDispatchRecordC);
	}
	
	public HDispatchRecordC getHDispatchRecordC(HDispatchRecordC hDispatchRecordC) {
		return (HDispatchRecordC)sqlMapClient.queryForObject("HDispatchRecordC.getHDispatchRecordC", hDispatchRecordC);
	}

    public int insertHDispatchRecordC(HDispatchRecordC hDispatchRecordC) throws Exception {
        return (Integer)sqlMapClient.insert("HDispatchRecordC.insertHDispatchRecordC", hDispatchRecordC);
    }

    public int updateHDispatchRecordC(HDispatchRecordC hDispatchRecordC) throws Exception {
        return sqlMapClient.update("HDispatchRecordC.updateHDispatchRecordC", hDispatchRecordC);
    }
    
    public int removeHDispatchRecordC(HDispatchRecordC hDispatchRecordC) throws Exception {
        return sqlMapClient.delete("HDispatchRecordC.removeHDispatchRecordC", hDispatchRecordC);
    }

}
