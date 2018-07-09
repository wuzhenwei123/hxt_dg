package com.hxt.hFp.dao;

import java.util.List;

import com.hxt.hFp.model.HFp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	yy
 * @time	2015年11月17日 23:58:31
 */
 @Repository("hFpDao")
public class HFpDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HFp> getHFpList(HFp hFp) {
		List<HFp> list = sqlMapClient.queryForList("HFp.getHFpList", hFp);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HFp> getHFpBaseList(HFp hFp) {
		return sqlMapClient.queryForList("HFp.getHFp", hFp);
	}

	public int getHFpListCount(HFp hFp) {
		return (Integer)sqlMapClient.queryForObject("HFp.getHFpListCount", hFp);
	}
	
	public HFp getHFp(HFp hFp) {
		return (HFp)sqlMapClient.queryForObject("HFp.getHFp", hFp);
	}

    public int insertHFp(HFp hFp) throws Exception {
        return (Integer)sqlMapClient.insert("HFp.insertHFp", hFp);
    }

    public int updateHFp(HFp hFp) throws Exception {
        return sqlMapClient.update("HFp.updateHFp", hFp);
    }
    
    public int removeHFp(HFp hFp) throws Exception {
        return sqlMapClient.delete("HFp.removeHFp", hFp);
    }

	@SuppressWarnings("unchecked")
	public List<HFp> getHFpTagBaseList(HFp hfp) {
		return sqlMapClient.queryForList("HFp.getHFpTag", hfp);
	}

}
