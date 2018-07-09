package com.hxt.hUserAccount.dao;

import java.util.List;

import com.hxt.hUserAccount.model.HUserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	zlb
 * @time	2016年08月16日 10:58:51
 */
 @Repository("hUserAccountDao")
public class HUserAccountDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HUserAccount> getHUserAccountList(HUserAccount hUserAccount) {
		List<HUserAccount> list = sqlMapClient.queryForList("HUserAccount.getHUserAccountList", hUserAccount);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HUserAccount> getHUserAccountBaseList(HUserAccount hUserAccount) {
		return sqlMapClient.queryForList("HUserAccount.getHUserAccount", hUserAccount);
	}

	public int getHUserAccountListCount(HUserAccount hUserAccount) {
		return (Integer)sqlMapClient.queryForObject("HUserAccount.getHUserAccountListCount", hUserAccount);
	}
	
	public HUserAccount getHUserAccount(HUserAccount hUserAccount) {
		return (HUserAccount)sqlMapClient.queryForObject("HUserAccount.getHUserAccount", hUserAccount);
	}

    public int insertHUserAccount(HUserAccount hUserAccount) throws Exception {
        return (Integer)sqlMapClient.insert("HUserAccount.insertHUserAccount", hUserAccount);
    }

    public int updateHUserAccount(HUserAccount hUserAccount) throws Exception {
        return sqlMapClient.update("HUserAccount.updateHUserAccount", hUserAccount);
    }
    
    public int unBindUserAccount(HUserAccount hUserAccount) throws Exception {
    	return sqlMapClient.update("HUserAccount.unBindUserAccount", hUserAccount);
    }
    
    public int removeHUserAccount(HUserAccount hUserAccount) throws Exception {
        return sqlMapClient.delete("HUserAccount.removeHUserAccount", hUserAccount);
    }

}
