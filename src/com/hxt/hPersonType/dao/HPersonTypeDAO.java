package com.hxt.hPersonType.dao;

import java.util.List;

import com.hxt.hPersonType.model.HPersonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	zlb
 * @time	2016年08月06日 14:03:07
 */
 @Repository("hPersonTypeDao")
public class HPersonTypeDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HPersonType> getHPersonTypeList(HPersonType hPersonType) {
		List<HPersonType> list = sqlMapClient.queryForList("HPersonType.getHPersonTypeList", hPersonType);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HPersonType> getHPersonTypeBaseList(HPersonType hPersonType) {
		return sqlMapClient.queryForList("HPersonType.getHPersonType", hPersonType);
	}

	public int getHPersonTypeListCount(HPersonType hPersonType) {
		return (Integer)sqlMapClient.queryForObject("HPersonType.getHPersonTypeListCount", hPersonType);
	}
	
	public HPersonType getHPersonType(HPersonType hPersonType) {
		return (HPersonType)sqlMapClient.queryForObject("HPersonType.getHPersonType", hPersonType);
	}

    public int insertHPersonType(HPersonType hPersonType) throws Exception {
        return (Integer)sqlMapClient.insert("HPersonType.insertHPersonType", hPersonType);
    }

    public int updateHPersonType(HPersonType hPersonType) throws Exception {
        return sqlMapClient.update("HPersonType.updateHPersonType", hPersonType);
    }
    
    public int removeHPersonType(HPersonType hPersonType) throws Exception {
        return sqlMapClient.delete("HPersonType.removeHPersonType", hPersonType);
    }

}
