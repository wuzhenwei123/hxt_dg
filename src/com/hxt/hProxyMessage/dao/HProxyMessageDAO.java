package com.hxt.hProxyMessage.dao;

import java.util.List;

import com.hxt.hProxyMessage.model.HProxyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2016年09月08日 18:43:55
 */
 @Repository("hProxyMessageDao")
public class HProxyMessageDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HProxyMessage> getHProxyMessageList(HProxyMessage hProxyMessage) {
		List<HProxyMessage> list = sqlMapClient.queryForList("HProxyMessage.getHProxyMessageList", hProxyMessage);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HProxyMessage> getHProxyMessageBaseList(HProxyMessage hProxyMessage) {
		return sqlMapClient.queryForList("HProxyMessage.getHProxyMessage", hProxyMessage);
	}

	public int getHProxyMessageListCount(HProxyMessage hProxyMessage) {
		return (Integer)sqlMapClient.queryForObject("HProxyMessage.getHProxyMessageListCount", hProxyMessage);
	}
	
	public int checkProxyPhone(HProxyMessage hProxyMessage) {
		return (Integer)sqlMapClient.queryForObject("HProxyMessage.checkProxyPhone", hProxyMessage);
	}
	
	public HProxyMessage getHProxyMessage(HProxyMessage hProxyMessage) {
		return (HProxyMessage)sqlMapClient.queryForObject("HProxyMessage.getHProxyMessage", hProxyMessage);
	}

    public int insertHProxyMessage(HProxyMessage hProxyMessage) throws Exception {
        return (Integer)sqlMapClient.insert("HProxyMessage.insertHProxyMessage", hProxyMessage);
    }

    public int updateHProxyMessage(HProxyMessage hProxyMessage) throws Exception {
        return sqlMapClient.update("HProxyMessage.updateHProxyMessage", hProxyMessage);
    }
    public int updateHProxyMessageBH(HProxyMessage hProxyMessage) throws Exception {
    	return sqlMapClient.update("HProxyMessage.updateHProxyMessageBH", hProxyMessage);
    }
    
    public int updateHProxyMessageZZ(HProxyMessage hProxyMessage) throws Exception {
    	return sqlMapClient.update("HProxyMessage.updateHProxyMessageZZ", hProxyMessage);
    }
    
    public int removeHProxyMessage(HProxyMessage hProxyMessage) throws Exception {
        return sqlMapClient.delete("HProxyMessage.removeHProxyMessage", hProxyMessage);
    }

}
