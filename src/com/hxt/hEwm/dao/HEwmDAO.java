package com.hxt.hEwm.dao;

import java.util.List;

import com.hxt.hEwm.model.HEwm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2015年08月31日 19:04:33
 */
 @Repository("hEwmDao")
public class HEwmDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HEwm> getHEwmList(HEwm hEwm) {
		List<HEwm> list = sqlMapClient.queryForList("HEwm.getHEwmList", hEwm);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HEwm> getHEwmBaseList(HEwm hEwm) {
		return sqlMapClient.queryForList("HEwm.getHEwm", hEwm);
	}

	public int getHEwmListCount(HEwm hEwm) {
		return (Integer)sqlMapClient.queryForObject("HEwm.getHEwmListCount", hEwm);
	}
	
	public HEwm getHEwm(HEwm hEwm) {
		return (HEwm)sqlMapClient.queryForObject("HEwm.getHEwm", hEwm);
	}

    public int insertHEwm(HEwm hEwm) throws Exception {
        return (Integer)sqlMapClient.insert("HEwm.insertHEwm", hEwm);
    }

    public int updateHEwm(HEwm hEwm) throws Exception {
        return sqlMapClient.update("HEwm.updateHEwm", hEwm);
    }
    
    public int removeHEwm(HEwm hEwm) throws Exception {
        return sqlMapClient.delete("HEwm.removeHEwm", hEwm);
    }

}
