package com.hxt.hCompany.dao;

import java.util.List;

import com.hxt.hCompany.model.HCompany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	yy
 * @time	2015年11月17日 23:59:30
 */
 @Repository("hCompanyDao")
public class HCompanyDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HCompany> getHCompanyList(HCompany hCompany) {
		List<HCompany> list = sqlMapClient.queryForList("HCompany.getHCompanyList", hCompany);
		return buildResponseList(list);
	}
	public ResponseList<HCompany> getHCompanyList2(HCompany hCompany) {
		List<HCompany> list = sqlMapClient.queryForList("HCompany.getHCompanyList2", hCompany);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HCompany> getHCompanyBaseList(HCompany hCompany) {
		return sqlMapClient.queryForList("HCompany.getHCompany", hCompany);
	}
	
	@SuppressWarnings("unchecked")
	public List<HCompany> getCompanyListByOneAgent(HCompany hCompany) {
		return sqlMapClient.queryForList("HCompany.getCompanyListByOneAgent", hCompany);
	}
	
	@SuppressWarnings("unchecked")
	public List<HCompany> getCompanyListByOther(HCompany hCompany) {
		return sqlMapClient.queryForList("HCompany.getCompanyListByOther", hCompany);
	}
	
	@SuppressWarnings("unchecked")
	public List<HCompany> getHCompanyList1(HCompany hCompany) {
		return sqlMapClient.queryForList("HCompany.getHCompanyList", hCompany);
	}

	public int getHCompanyListCount(HCompany hCompany) {
		return (Integer)sqlMapClient.queryForObject("HCompany.getHCompanyListCount", hCompany);
	}
	public int getHCompanyListCount2(HCompany hCompany) {
		return (Integer)sqlMapClient.queryForObject("HCompany.getHCompanyListCount2", hCompany);
	}
	
	public int getCompanyListByOneAgentCount(HCompany hCompany) {
		return (Integer)sqlMapClient.queryForObject("HCompany.getCompanyListByOneAgentCount", hCompany);
	}
	
	public int getCompanyListByOtherCount(HCompany hCompany) {
		return (Integer)sqlMapClient.queryForObject("HCompany.getCompanyListByOtherCount", hCompany);
	}
	
	public HCompany getHCompany(HCompany hCompany) {
		return (HCompany)sqlMapClient.queryForObject("HCompany.getHCompany", hCompany);
	}

    public int insertHCompany(HCompany hCompany) throws Exception {
        return (Integer)sqlMapClient.insert("HCompany.insertHCompany", hCompany);
    }

    public int updateHCompany(HCompany hCompany) throws Exception {
        return sqlMapClient.update("HCompany.updateHCompany", hCompany);
    }
    
    public int updateServicerName(HCompany hCompany) throws Exception {
    	return sqlMapClient.update("HCompany.updateServicerName", hCompany);
    }
    
    public int updateOneAgentName(HCompany hCompany) throws Exception {
    	return sqlMapClient.update("HCompany.updateOneAgentName", hCompany);
    }
    
    public int updateTwoAgentName(HCompany hCompany) throws Exception {
    	return sqlMapClient.update("HCompany.updateTwoAgentName", hCompany);
    }
    
    public int updateHCompany1(HCompany hCompany) throws Exception {
    	return sqlMapClient.update("HCompany.updateHCompany1", hCompany);
    }
    
    public int updateHCompany2(HCompany hCompany) throws Exception {
    	return sqlMapClient.update("HCompany.updateHCompany2", hCompany);
    }
    
    public int removeHCompany(HCompany hCompany) throws Exception {
        return sqlMapClient.delete("HCompany.removeHCompany", hCompany);
    }
    
    public List<HCompany> getHCompanyListByTask(HCompany hCompany) {
    	return sqlMapClient.queryForList("HCompany.getHCompanyList", hCompany);
    }
    public List<HCompany> getUnPayHCompany(HCompany hCompany) {
    	return sqlMapClient.queryForList("HCompany.getUnPayHCompany", hCompany);
    }
    public List<HCompany> getUnPayHCompany1(HCompany hCompany) {
    	return sqlMapClient.queryForList("HCompany.getUnPayHCompany1", hCompany);
    }

}
