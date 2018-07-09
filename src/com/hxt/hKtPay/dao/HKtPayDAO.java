package com.hxt.hKtPay.dao;

import java.util.List;

import com.hxt.hKtPay.model.HKtPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2016年11月28日 18:52:36
 */
 @Repository("hKtPayDao")
public class HKtPayDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HKtPay> getHKtPayList(HKtPay hKtPay) {
		List<HKtPay> list = sqlMapClient.queryForList("HKtPay.getHKtPayList", hKtPay);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HKtPay> getHKtPayBaseList(HKtPay hKtPay) {
		return sqlMapClient.queryForList("HKtPay.getHKtPay", hKtPay);
	}

	public int getHKtPayListCount(HKtPay hKtPay) {
		return (Integer)sqlMapClient.queryForObject("HKtPay.getHKtPayListCount", hKtPay);
	}
	
	public HKtPay getHKtPay(HKtPay hKtPay) {
		return (HKtPay)sqlMapClient.queryForObject("HKtPay.getHKtPay", hKtPay);
	}

    public int insertHKtPay(HKtPay hKtPay) throws Exception {
        return (Integer)sqlMapClient.insert("HKtPay.insertHKtPay", hKtPay);
    }

    public int updateHKtPay(HKtPay hKtPay) throws Exception {
        return sqlMapClient.update("HKtPay.updateHKtPay", hKtPay);
    }
    
    public int removeHKtPay(HKtPay hKtPay) throws Exception {
        return sqlMapClient.delete("HKtPay.removeHKtPay", hKtPay);
    }

}
