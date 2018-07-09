package com.hxt.hSubOrder.dao;

import java.util.List;

import com.hxt.hSubOrder.model.HSubOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2015年12月02日 00:01:03
 */
 @Repository("hSubOrderDao")
public class HSubOrderDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HSubOrder> getHSubOrderList(HSubOrder hSubOrder) {
		List<HSubOrder> list = sqlMapClient.queryForList("HSubOrder.getHSubOrderList", hSubOrder);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HSubOrder> getHSubOrderBaseList(HSubOrder hSubOrder) {
		return sqlMapClient.queryForList("HSubOrder.getHSubOrder", hSubOrder);
	}

	public int getHSubOrderListCount(HSubOrder hSubOrder) {
		return (Integer)sqlMapClient.queryForObject("HSubOrder.getHSubOrderListCount", hSubOrder);
	}
	
	public HSubOrder getHSubOrder(HSubOrder hSubOrder) {
		return (HSubOrder)sqlMapClient.queryForObject("HSubOrder.getHSubOrder", hSubOrder);
	}

    public int insertHSubOrder(HSubOrder hSubOrder) throws Exception {
        return (Integer)sqlMapClient.insert("HSubOrder.insertHSubOrder", hSubOrder);
    }

    public int updateHSubOrder(HSubOrder hSubOrder) throws Exception {
        return sqlMapClient.update("HSubOrder.updateHSubOrder", hSubOrder);
    }
    public int updateHSubOrder1(HSubOrder hSubOrder) throws Exception {
    	return sqlMapClient.update("HSubOrder.updateHSubOrder1", hSubOrder);
    }
    
    public int removeHSubOrder(HSubOrder hSubOrder) throws Exception {
        return sqlMapClient.delete("HSubOrder.removeHSubOrder", hSubOrder);
    }

}
