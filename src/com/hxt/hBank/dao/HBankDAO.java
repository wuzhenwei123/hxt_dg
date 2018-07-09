package com.hxt.hBank.dao;

import java.util.List;

import com.hxt.hBank.model.HBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2016年08月31日 00:17:09
 */
 @Repository("hBankDao")
public class HBankDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HBank> getHBankList(HBank hBank) {
		List<HBank> list = sqlMapClient.queryForList("HBank.getHBankList", hBank);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HBank> getHBankBaseList(HBank hBank) {
		return sqlMapClient.queryForList("HBank.getHBank", hBank);
	}

	public int getHBankListCount(HBank hBank) {
		return (Integer)sqlMapClient.queryForObject("HBank.getHBankListCount", hBank);
	}
	
	public HBank getHBank(HBank hBank) {
		return (HBank)sqlMapClient.queryForObject("HBank.getHBank", hBank);
	}

    public int insertHBank(HBank hBank) throws Exception {
        return (Integer)sqlMapClient.insert("HBank.insertHBank", hBank);
    }

    public int updateHBank(HBank hBank) throws Exception {
        return sqlMapClient.update("HBank.updateHBank", hBank);
    }
    
    public int removeHBank(HBank hBank) throws Exception {
        return sqlMapClient.delete("HBank.removeHBank", hBank);
    }

}
