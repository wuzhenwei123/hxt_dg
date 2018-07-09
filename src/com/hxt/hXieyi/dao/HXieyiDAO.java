package com.hxt.hXieyi.dao;

import java.util.List;

import com.hxt.hXieyi.model.HXieyi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	zhanglibo
 * @time	2016年08月29日 10:31:36
 */
 @Repository("hXieyiDao")
public class HXieyiDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HXieyi> getHXieyiList(HXieyi hXieyi) {
		List<HXieyi> list = sqlMapClient.queryForList("HXieyi.getHXieyiList", hXieyi);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HXieyi> getHXieyiBaseList(HXieyi hXieyi) {
		return sqlMapClient.queryForList("HXieyi.getHXieyi", hXieyi);
	}

	public int getHXieyiListCount(HXieyi hXieyi) {
		return (Integer)sqlMapClient.queryForObject("HXieyi.getHXieyiListCount", hXieyi);
	}
	
	public HXieyi getHXieyi(HXieyi hXieyi) {
		return (HXieyi)sqlMapClient.queryForObject("HXieyi.getHXieyi", hXieyi);
	}

    public int insertHXieyi(HXieyi hXieyi) throws Exception {
        return (Integer)sqlMapClient.insert("HXieyi.insertHXieyi", hXieyi);
    }

    public int updateHXieyi(HXieyi hXieyi) throws Exception {
        return sqlMapClient.update("HXieyi.updateHXieyi", hXieyi);
    }
    
    public int removeHXieyi(HXieyi hXieyi) throws Exception {
        return sqlMapClient.delete("HXieyi.removeHXieyi", hXieyi);
    }

}
