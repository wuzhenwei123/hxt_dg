package com.hxt.openingBank.dao;

import java.util.List;

import com.hxt.openingBank.model.OpeningBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2016年09月09日 00:01:06
 */
 @Repository("openingBankDao")
public class OpeningBankDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<OpeningBank> getOpeningBankList(OpeningBank openingBank) {
		List<OpeningBank> list = sqlMapClient.queryForList("OpeningBank.getOpeningBankList", openingBank);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<OpeningBank> getOpeningBankBaseList(OpeningBank openingBank) {
		return sqlMapClient.queryForList("OpeningBank.getOpeningBank", openingBank);
	}

	public int getOpeningBankListCount(OpeningBank openingBank) {
		return (Integer)sqlMapClient.queryForObject("OpeningBank.getOpeningBankListCount", openingBank);
	}
	
	public OpeningBank getOpeningBank(OpeningBank openingBank) {
		return (OpeningBank)sqlMapClient.queryForObject("OpeningBank.getOpeningBank", openingBank);
	}

    public int insertOpeningBank(OpeningBank openingBank) throws Exception {
        return (Integer)sqlMapClient.insert("OpeningBank.insertOpeningBank", openingBank);
    }

    public int updateOpeningBank(OpeningBank openingBank) throws Exception {
        return sqlMapClient.update("OpeningBank.updateOpeningBank", openingBank);
    }
    
    public int removeOpeningBank(OpeningBank openingBank) throws Exception {
        return sqlMapClient.delete("OpeningBank.removeOpeningBank", openingBank);
    }

}
