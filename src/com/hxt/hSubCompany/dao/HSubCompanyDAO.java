package com.hxt.hSubCompany.dao;

import java.util.List;

import com.hxt.hSubCompany.model.HSubCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2015年11月24日 10:42:32
 */
 @Repository("hSubCompanyDao")
public class HSubCompanyDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HSubCompany> getHSubCompanyList(HSubCompany hSubCompany) {
		List<HSubCompany> list = sqlMapClient.queryForList("HSubCompany.getHSubCompanyList", hSubCompany);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HSubCompany> getHSubCompanyBaseList(HSubCompany hSubCompany) {
		return sqlMapClient.queryForList("HSubCompany.getHSubCompany", hSubCompany);
	}

	public int getHSubCompanyListCount(HSubCompany hSubCompany) {
		return (Integer)sqlMapClient.queryForObject("HSubCompany.getHSubCompanyListCount", hSubCompany);
	}
	
	public HSubCompany getHSubCompany(HSubCompany hSubCompany) {
		return (HSubCompany)sqlMapClient.queryForObject("HSubCompany.getHSubCompany", hSubCompany);
	}

    public int insertHSubCompany(HSubCompany hSubCompany) throws Exception {
        return (Integer)sqlMapClient.insert("HSubCompany.insertHSubCompany", hSubCompany);
    }

    public int updateHSubCompany(HSubCompany hSubCompany) throws Exception {
        return sqlMapClient.update("HSubCompany.updateHSubCompany", hSubCompany);
    }
    
    public int removeHSubCompany(HSubCompany hSubCompany) throws Exception {
        return sqlMapClient.delete("HSubCompany.removeHSubCompany", hSubCompany);
    }

}
