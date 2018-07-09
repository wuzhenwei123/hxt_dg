package com.hxt.hVerificate.dao;

import java.util.List;

import com.hxt.hVerificate.model.HVerificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2015年09月30日 14:11:19
 */
 @Repository("hVerificateDao")
public class HVerificateDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HVerificate> getHVerificateList(HVerificate hVerificate) {
		List<HVerificate> list = sqlMapClient.queryForList("HVerificate.getHVerificateList", hVerificate);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HVerificate> getHVerificateBaseList(HVerificate hVerificate) {
		return sqlMapClient.queryForList("HVerificate.getHVerificate", hVerificate);
	}

	public int getHVerificateListCount(HVerificate hVerificate) {
		return (Integer)sqlMapClient.queryForObject("HVerificate.getHVerificateListCount", hVerificate);
	}
	
	public HVerificate getHVerificate(HVerificate hVerificate) {
		return (HVerificate)sqlMapClient.queryForObject("HVerificate.getHVerificate", hVerificate);
	}

    public int insertHVerificate(HVerificate hVerificate) throws Exception {
        return (Integer)sqlMapClient.insert("HVerificate.insertHVerificate", hVerificate);
    }

    public int updateHVerificate(HVerificate hVerificate) throws Exception {
        return sqlMapClient.update("HVerificate.updateHVerificate", hVerificate);
    }
    
    public int removeHVerificate(HVerificate hVerificate) throws Exception {
        return sqlMapClient.delete("HVerificate.removeHVerificate", hVerificate);
    }

}
