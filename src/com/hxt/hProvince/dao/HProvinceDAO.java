package com.hxt.hProvince.dao;

import java.util.List;

import com.hxt.hProvince.model.HProvince;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	zhangyiyang
 * @time	2016年08月02日 22:11:34
 */
 @Repository("hProvinceDao")
public class HProvinceDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HProvince> getHProvinceList(HProvince hProvince) {
		List<HProvince> list = sqlMapClient.queryForList("HProvince.getHProvinceList", hProvince);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HProvince> getHProvinceBaseList(HProvince hProvince) {
		return sqlMapClient.queryForList("HProvince.getHProvince", hProvince);
	}

	public int getHProvinceListCount(HProvince hProvince) {
		return (Integer)sqlMapClient.queryForObject("HProvince.getHProvinceListCount", hProvince);
	}
	
	public HProvince getHProvince(HProvince hProvince) {
		return (HProvince)sqlMapClient.queryForObject("HProvince.getHProvince", hProvince);
	}

    public int insertHProvince(HProvince hProvince) throws Exception {
        return (Integer)sqlMapClient.insert("HProvince.insertHProvince", hProvince);
    }

    public int updateHProvince(HProvince hProvince) throws Exception {
        return sqlMapClient.update("HProvince.updateHProvince", hProvince);
    }
    
    public int removeHProvince(HProvince hProvince) throws Exception {
        return sqlMapClient.delete("HProvince.removeHProvince", hProvince);
    }

}
