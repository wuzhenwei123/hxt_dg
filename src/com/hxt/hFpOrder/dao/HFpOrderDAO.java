package com.hxt.hFpOrder.dao;

import java.util.List;

import com.hxt.hFpOrder.model.HFpOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2016年01月12日 22:02:33
 */
 @Repository("hFpOrderDao")
public class HFpOrderDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HFpOrder> getHFpOrderList(HFpOrder hFpOrder) {
		List<HFpOrder> list = sqlMapClient.queryForList("HFpOrder.getHFpOrderList", hFpOrder);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HFpOrder> getHFpOrderBaseList(HFpOrder hFpOrder) {
		return sqlMapClient.queryForList("HFpOrder.getHFpOrder", hFpOrder);
	}

	public int getHFpOrderListCount(HFpOrder hFpOrder) {
		return (Integer)sqlMapClient.queryForObject("HFpOrder.getHFpOrderListCount", hFpOrder);
	}
	
	public HFpOrder getHFpOrder(HFpOrder hFpOrder) {
		return (HFpOrder)sqlMapClient.queryForObject("HFpOrder.getHFpOrder", hFpOrder);
	}

    public int insertHFpOrder(HFpOrder hFpOrder) throws Exception {
        return (Integer)sqlMapClient.insert("HFpOrder.insertHFpOrder", hFpOrder);
    }

    public int updateHFpOrder(HFpOrder hFpOrder) throws Exception {
        return sqlMapClient.update("HFpOrder.updateHFpOrder", hFpOrder);
    }
    
    public int removeHFpOrder(HFpOrder hFpOrder) throws Exception {
        return sqlMapClient.delete("HFpOrder.removeHFpOrder", hFpOrder);
    }

}
