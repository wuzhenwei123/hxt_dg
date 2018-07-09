package com.hxt.hPay.dao;

import java.util.List;

import com.hxt.hPay.model.HPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2016年10月20日 09:13:45
 */
 @Repository("hPayDao")
public class HPayDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HPay> getHPayList(HPay hPay) {
		List<HPay> list = sqlMapClient.queryForList("HPay.getHPayList", hPay);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HPay> getHPayBaseList(HPay hPay) {
		return sqlMapClient.queryForList("HPay.getHPay", hPay);
	}

	public int getHPayListCount(HPay hPay) {
		return (Integer)sqlMapClient.queryForObject("HPay.getHPayListCount", hPay);
	}
	
	public HPay getHPay(HPay hPay) {
		return (HPay)sqlMapClient.queryForObject("HPay.getHPay", hPay);
	}

    public int insertHPay(HPay hPay) throws Exception {
        return (Integer)sqlMapClient.insert("HPay.insertHPay", hPay);
    }

    public int updateHPay(HPay hPay) throws Exception {
        return sqlMapClient.update("HPay.updateHPay", hPay);
    }
    
    public int removeHPay(HPay hPay) throws Exception {
        return sqlMapClient.delete("HPay.removeHPay", hPay);
    }

}
