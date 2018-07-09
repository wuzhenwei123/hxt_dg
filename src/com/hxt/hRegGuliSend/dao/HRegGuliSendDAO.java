package com.hxt.hRegGuliSend.dao;

import java.util.List;

import com.hxt.hRegGuliSend.model.HRegGuliSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2017年04月06日 18:01:33
 */
 @Repository("hRegGuliSendDao")
public class HRegGuliSendDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HRegGuliSend> getHRegGuliSendList(HRegGuliSend hRegGuliSend) {
		List<HRegGuliSend> list = sqlMapClient.queryForList("HRegGuliSend.getHRegGuliSendList", hRegGuliSend);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HRegGuliSend> getHRegGuliSendBaseList(HRegGuliSend hRegGuliSend) {
		return sqlMapClient.queryForList("HRegGuliSend.getHRegGuliSend", hRegGuliSend);
	}

	public int getHRegGuliSendListCount(HRegGuliSend hRegGuliSend) {
		return (Integer)sqlMapClient.queryForObject("HRegGuliSend.getHRegGuliSendListCount", hRegGuliSend);
	}
	
	public HRegGuliSend getHRegGuliSend(HRegGuliSend hRegGuliSend) {
		return (HRegGuliSend)sqlMapClient.queryForObject("HRegGuliSend.getHRegGuliSend", hRegGuliSend);
	}

    public int insertHRegGuliSend(HRegGuliSend hRegGuliSend) throws Exception {
        return (Integer)sqlMapClient.insert("HRegGuliSend.insertHRegGuliSend", hRegGuliSend);
    }

    public int updateHRegGuliSend(HRegGuliSend hRegGuliSend) throws Exception {
        return sqlMapClient.update("HRegGuliSend.updateHRegGuliSend", hRegGuliSend);
    }
    
    public int removeHRegGuliSend(HRegGuliSend hRegGuliSend) throws Exception {
        return sqlMapClient.delete("HRegGuliSend.removeHRegGuliSend", hRegGuliSend);
    }

}
