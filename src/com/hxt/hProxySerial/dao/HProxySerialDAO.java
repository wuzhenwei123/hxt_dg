package com.hxt.hProxySerial.dao;

import java.util.List;

import com.hxt.hProxySerial.model.HProxySerial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2016年09月08日 22:18:12
 */
 @Repository("hProxySerialDao")
public class HProxySerialDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HProxySerial> getHProxySerialList(HProxySerial hProxySerial) {
		List<HProxySerial> list = sqlMapClient.queryForList("HProxySerial.getHProxySerialList", hProxySerial);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HProxySerial> getHProxySerialBaseList(HProxySerial hProxySerial) {
		return sqlMapClient.queryForList("HProxySerial.getHProxySerial", hProxySerial);
	}

	public int getHProxySerialListCount(HProxySerial hProxySerial) {
		return (Integer)sqlMapClient.queryForObject("HProxySerial.getHProxySerialListCount", hProxySerial);
	}
	
	public HProxySerial getHProxySerial(HProxySerial hProxySerial) {
		return (HProxySerial)sqlMapClient.queryForObject("HProxySerial.getHProxySerial", hProxySerial);
	}

    public int insertHProxySerial(HProxySerial hProxySerial) throws Exception {
        return (Integer)sqlMapClient.insert("HProxySerial.insertHProxySerial", hProxySerial);
    }

    public int updateHProxySerial(HProxySerial hProxySerial) throws Exception {
        return sqlMapClient.update("HProxySerial.updateHProxySerial", hProxySerial);
    }
    
    public int removeHProxySerial(HProxySerial hProxySerial) throws Exception {
        return sqlMapClient.delete("HProxySerial.removeHProxySerial", hProxySerial);
    }

}
