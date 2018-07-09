package com.hxt.hPayOrder.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hPayOrder.dao.HPayOrderDAO;
import com.hxt.hPayOrder.model.HPayOrder;
import com.hxt.hUserAccount.dao.HUserAccountDAO;
import com.hxt.hUserAccount.model.HUserAccount;
import com.hxt.hUserAccountDetail.dao.HUserAccountDetailDAO;
import com.hxt.hUserAccountDetail.model.HUserAccountDetail;
import com.base.utils.FileUploadConstants;
import com.base.utils.ResponseList;

/**
 * @author	wuzhenwei
 * @time	2015年11月24日 23:49:11
 */
 @Service("hPayOrderService")
public class HPayOrderService {

	@Resource(name = "hPayOrderDao")
    private HPayOrderDAO hPayOrderDAO;
	@Resource(name = "hUserAccountDetailDao")
	private HUserAccountDetailDAO hUserAccountDetailDao;
	@Resource(name = "hUserAccountDao")
	private HUserAccountDAO hUserAccountDao;
    
    public ResponseList<HPayOrder> getHPayOrderList(HPayOrder hPayOrder) {
        return hPayOrderDAO.getHPayOrderList(hPayOrder);
    }
    public ResponseList<HPayOrder> getHPayOrderListNew(HPayOrder hPayOrder) {
    	return hPayOrderDAO.getHPayOrderListNew(hPayOrder);
    }
    public ResponseList<HPayOrder> getHPayOrderList1111(HPayOrder hPayOrder) {
    	return hPayOrderDAO.getHPayOrderList1111(hPayOrder);
    }
    
    public List<HPayOrder> getHPayOrderBaseList(HPayOrder hPayOrder) {
        return hPayOrderDAO.getHPayOrderBaseList(hPayOrder);
    }
    public List<HPayOrder> getHPayOrderListPage(HPayOrder hPayOrder) {
    	return hPayOrderDAO.getHPayOrderListPage(hPayOrder);
    }
    
    public int getHPayOrderListCount(HPayOrder hPayOrder) {
        return hPayOrderDAO.getHPayOrderListCount(hPayOrder);
    }
    
    public Long getHPayOrderListSum(HPayOrder hPayOrder) {
    	return hPayOrderDAO.getHPayOrderListSum(hPayOrder);
    }
    
    public Long getHPayOrderTotal(HPayOrder hPayOrder) {
    	return hPayOrderDAO.getHPayOrderTotal(hPayOrder);
    }
    
    public int getNoPayOrderCompanyCount(HPayOrder hPayOrder) {
    	return hPayOrderDAO.getNoPayOrderCompanyCount(hPayOrder);
    }
    
    public int getHPayOrderListCountNew(HPayOrder hPayOrder) {
    	return hPayOrderDAO.getHPayOrderListCountNew(hPayOrder);
    }

    public HPayOrder getHPayOrder(HPayOrder hPayOrder) { 
        return hPayOrderDAO.getHPayOrder(hPayOrder);
    }

    public int insertHPayOrder(HPayOrder hPayOrder) throws Exception {
        return hPayOrderDAO.insertHPayOrder(hPayOrder);
    }

    public int updateHPayOrder(HPayOrder hPayOrder) throws Exception {
        return hPayOrderDAO.updateHPayOrder(hPayOrder);
    }
    
    public int removeHPayOrder(HPayOrder hPayOrder) throws Exception {
        return hPayOrderDAO.removeHPayOrder(hPayOrder);
    }

    public HPayOrder findTodayHPayOrder(HPayOrder hPayOrder) {
        return hPayOrderDAO.findTodayHPayOrder(hPayOrder);
    }
    
    public HPayOrder getPayTotalFee(HPayOrder hPayOrder) {
    	return hPayOrderDAO.getPayTotalFee(hPayOrder);
    }
    
    /**
	 * 查当前月份缴费的单位
	 * @param hPayOrder
	 * @return
	 */
    public List<HPayOrder> getHPayOrderCompany(HPayOrder hPayOrder) {
    	return hPayOrderDAO.getHPayOrderCompany(hPayOrder);
    }

	public int getTransCountByCompany(Integer c_id) {
		return hPayOrderDAO.getTransCountByCompany(c_id);
	}

	public int getHPayOrderListCount_ForWX(HPayOrder param) {
		return hPayOrderDAO.getHPayOrderListCount_ForWX(param);
	}

	public List<HPayOrder> getHPayOrderList_ForWX(HPayOrder param) {
		 return hPayOrderDAO.getHPayOrderList_ForWX(param);
	}

	public HPayOrder getAllDetailFee(HPayOrder param) {
		return hPayOrderDAO.getAllDetailFee(param);
	}

	public HPayOrder getAllRateMoney(HPayOrder param) {
		return hPayOrderDAO.getAllRateMoney(param);
	}
    
	public double udpateAccount(HUserAccount agentAccount,HPayOrder hPayOrderQry,Double rate) throws Exception{
		//分润比例
		BigDecimal profitRate = new BigDecimal(rate);
//		//订单支付金额(电表欠费金额 单位 分)
		BigDecimal totalFee111 = new BigDecimal(hPayOrderQry.getAmount()).divide(new BigDecimal("100"));
		BigDecimal subFee = profitRate.multiply(totalFee111).setScale(2, BigDecimal.ROUND_HALF_UP);
		//账户明细
		HUserAccountDetail detail = new HUserAccountDetail();
		detail.setUserAccountId(agentAccount.getId());
		detail.setType(1);
		detail.setTotalFee(subFee);
		detail.setCreateTime(hPayOrderQry.getPay_time());
		detail.setRate(rate.floatValue());
		detail.setOrderId(hPayOrderQry.getO_id());
		detail.setAmmeterNum(hPayOrderQry.getElectric_number());
		detail.setStyle(2);
		detail.setPay_style(Integer.valueOf(hPayOrderQry.getPay_type()));
		detail.setOrderDetailMoney(totalFee111);
		//子订单
		hUserAccountDetailDao.insertHUserAccountDetail(detail);
		//更新账户
		agentAccount.setTotalFee(agentAccount.getTotalFee().add(subFee));
		
		double feeTotal = subFee.doubleValue();
		
		hUserAccountDao.updateHUserAccount(agentAccount);
		
		return feeTotal;
	}
}
