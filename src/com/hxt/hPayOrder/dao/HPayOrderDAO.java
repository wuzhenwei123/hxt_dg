package com.hxt.hPayOrder.dao;

import java.util.List;

import com.hxt.hPayOrder.model.HPayOrder;
import com.hxt.hUserAccountDetail.model.HUserAccountDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2015年11月24日 23:49:07
 */
 @Repository("hPayOrderDao")
public class HPayOrderDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HPayOrder> getHPayOrderList(HPayOrder hPayOrder) {
		List<HPayOrder> list = sqlMapClient.queryForList("HPayOrder.getHPayOrderList", hPayOrder);
		return buildResponseList(list);
	}
	@SuppressWarnings("unchecked")
	public ResponseList<HPayOrder> getHPayOrderListNew(HPayOrder hPayOrder) {
		List<HPayOrder> list = sqlMapClient.queryForList("HPayOrder.getHPayOrderListNew", hPayOrder);
		return buildResponseList(list);
	}
	public ResponseList<HPayOrder> getHPayOrderList1111(HPayOrder hPayOrder) {
		List<HPayOrder> list = sqlMapClient.queryForList("HPayOrder.getHPayOrderList1111", hPayOrder);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HPayOrder> getHPayOrderBaseList(HPayOrder hPayOrder) {
		return sqlMapClient.queryForList("HPayOrder.getHPayOrder", hPayOrder);
	}
	
	@SuppressWarnings("unchecked")
	public List<HPayOrder> getHPayOrderListPage(HPayOrder hPayOrder) {
		return sqlMapClient.queryForList("HPayOrder.getHPayOrderList", hPayOrder);
	}
	
	/**
	 * 查当前月份缴费的单位
	 * @param hPayOrder
	 * @return
	 */
	public List<HPayOrder> getHPayOrderCompany(HPayOrder hPayOrder) {
		return sqlMapClient.queryForList("HPayOrder.getHPayOrderCompany", hPayOrder);
	}

	public int getHPayOrderListCount(HPayOrder hPayOrder) {
		return (Integer)sqlMapClient.queryForObject("HPayOrder.getHPayOrderListCount", hPayOrder);
	}
	
	public int getHPayOrderListCountNew(HPayOrder hPayOrder) {
		return (Integer)sqlMapClient.queryForObject("HPayOrder.getHPayOrderListCountNew", hPayOrder);
	}
	
	public Long getHPayOrderListSum(HPayOrder hPayOrder) {
		return (Long)sqlMapClient.queryForObject("HPayOrder.getHPayOrderListSum", hPayOrder);
	}
	
	public Long getHPayOrderTotal(HPayOrder hPayOrder) {
		return (Long)sqlMapClient.queryForObject("HPayOrder.getHPayOrderTotal", hPayOrder);
	}
	
	public int getNoPayOrderCompanyCount(HPayOrder hPayOrder) {
		return (Integer)sqlMapClient.queryForObject("HPayOrder.getNoPayOrderCompanyCount", hPayOrder);
	}
	
	public HPayOrder getHPayOrder(HPayOrder hPayOrder) {
		return (HPayOrder)sqlMapClient.queryForObject("HPayOrder.getHPayOrder", hPayOrder);
	}

    public int insertHPayOrder(HPayOrder hPayOrder) throws Exception {
        return (Integer)sqlMapClient.insert("HPayOrder.insertHPayOrder", hPayOrder);
    }

    public int updateHPayOrder(HPayOrder hPayOrder) throws Exception {
        return sqlMapClient.update("HPayOrder.updateHPayOrder", hPayOrder);
    }
    
    public int removeHPayOrder(HPayOrder hPayOrder) throws Exception {
        return sqlMapClient.delete("HPayOrder.removeHPayOrder", hPayOrder);
    }

	public HPayOrder findTodayHPayOrder(HPayOrder hPayOrder) {
		return (HPayOrder)sqlMapClient.queryForObject("HPayOrder.findTodayHPayOrder", hPayOrder);
	}
	
	public HPayOrder getPayTotalFee(HPayOrder hPayOrder) {
		return (HPayOrder)sqlMapClient.queryForObject("HPayOrder.getPayTotalFee", hPayOrder);
	}

	public int getTransCountByCompany(Integer id) {
		return (Integer)sqlMapClient.queryForObject("HPayOrder.getTransCountByCompany", id);
	}

	public int getHPayOrderListCount_ForWX(HPayOrder param) {
		return (Integer)sqlMapClient.queryForObject("HPayOrder.getHPayOrderListCount_ForWX", param);
	}

	public List<HPayOrder> getHPayOrderList_ForWX(HPayOrder param) {
		return sqlMapClient.queryForList("HPayOrder.getHPayOrderList_ForWX", param);
	}

	public HPayOrder getAllDetailFee(HPayOrder param) {
		return (HPayOrder)sqlMapClient.queryForObject("HPayOrder.getAllDetailFee", param);
	}

	public HPayOrder getAllRateMoney(HPayOrder param) {
		return (HPayOrder)sqlMapClient.queryForObject("HPayOrder.getAllRateMoney", param);
	}
}
