package com.hxt.hCity.dao;

import java.util.List;

import com.hxt.hCity.model.HCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	zhangyiyang
 * @time	2016年08月02日 22:11:23
 */
 @Repository("hCityDao")
public class HCityDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HCity> getHCityList(HCity hCity) {
		List<HCity> list = sqlMapClient.queryForList("HCity.getHCityList", hCity);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HCity> getHCityBaseList(HCity hCity) {
		return sqlMapClient.queryForList("HCity.getHCity", hCity);
	}

	public int getHCityListCount(HCity hCity) {
		return (Integer)sqlMapClient.queryForObject("HCity.getHCityListCount", hCity);
	}
	
	public HCity getHCity(HCity hCity) {
		return (HCity)sqlMapClient.queryForObject("HCity.getHCity", hCity);
	}

    public int insertHCity(HCity hCity) throws Exception {
        return (Integer)sqlMapClient.insert("HCity.insertHCity", hCity);
    }

    public int updateHCity(HCity hCity) throws Exception {
        return sqlMapClient.update("HCity.updateHCity", hCity);
    }
    
    public int removeHCity(HCity hCity) throws Exception {
        return sqlMapClient.delete("HCity.removeHCity", hCity);
    }

}
