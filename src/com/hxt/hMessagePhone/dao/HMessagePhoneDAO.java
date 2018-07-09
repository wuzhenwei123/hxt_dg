package com.hxt.hMessagePhone.dao;

import java.util.List;

import com.hxt.hMessagePhone.model.HMessagePhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2017年06月22日 14:48:07
 */
 @Repository("hMessagePhoneDao")
public class HMessagePhoneDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HMessagePhone> getHMessagePhoneList(HMessagePhone hMessagePhone) {
		List<HMessagePhone> list = sqlMapClient.queryForList("HMessagePhone.getHMessagePhoneList", hMessagePhone);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HMessagePhone> getHMessagePhoneBaseList(HMessagePhone hMessagePhone) {
		return sqlMapClient.queryForList("HMessagePhone.getHMessagePhone", hMessagePhone);
	}

	public int getHMessagePhoneListCount(HMessagePhone hMessagePhone) {
		return (Integer)sqlMapClient.queryForObject("HMessagePhone.getHMessagePhoneListCount", hMessagePhone);
	}
	
	public HMessagePhone getHMessagePhone(HMessagePhone hMessagePhone) {
		return (HMessagePhone)sqlMapClient.queryForObject("HMessagePhone.getHMessagePhone", hMessagePhone);
	}

    public int insertHMessagePhone(HMessagePhone hMessagePhone) throws Exception {
        return (Integer)sqlMapClient.insert("HMessagePhone.insertHMessagePhone", hMessagePhone);
    }

    public int updateHMessagePhone(HMessagePhone hMessagePhone) throws Exception {
        return sqlMapClient.update("HMessagePhone.updateHMessagePhone", hMessagePhone);
    }
    
    public int removeHMessagePhone(HMessagePhone hMessagePhone) throws Exception {
        return sqlMapClient.delete("HMessagePhone.removeHMessagePhone", hMessagePhone);
    }

}
