package com.hxt.hUserAccountDetail.dao;

import java.util.List;

import com.hxt.hUserAccountDetail.model.HUserAccountDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	zlb
 * @time	2016年08月16日 10:58:41
 */
 @Repository("hUserAccountDetailDao")
public class HUserAccountDetailDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HUserAccountDetail> getHUserAccountDetailList(HUserAccountDetail hUserAccountDetail) {
		List<HUserAccountDetail> list = sqlMapClient.queryForList("HUserAccountDetail.getHUserAccountDetailList", hUserAccountDetail);
		return buildResponseList(list);
	}
	@SuppressWarnings("unchecked")
	public List<HUserAccountDetail> getHUserAccountDetailListWx(HUserAccountDetail hUserAccountDetail) {
		List<HUserAccountDetail> list = sqlMapClient.queryForList("HUserAccountDetail.getHUserAccountDetailList", hUserAccountDetail);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<HUserAccountDetail> getHUserAccountDetailBaseList(HUserAccountDetail hUserAccountDetail) {
		return sqlMapClient.queryForList("HUserAccountDetail.getHUserAccountDetail", hUserAccountDetail);
	}

	public int getHUserAccountDetailListCount(HUserAccountDetail hUserAccountDetail) {
		return (Integer)sqlMapClient.queryForObject("HUserAccountDetail.getHUserAccountDetailListCount", hUserAccountDetail);
	}
	
	public HUserAccountDetail getHUserAccountDetail(HUserAccountDetail hUserAccountDetail) {
		return (HUserAccountDetail)sqlMapClient.queryForObject("HUserAccountDetail.getHUserAccountDetail", hUserAccountDetail);
	}

    public int insertHUserAccountDetail(HUserAccountDetail hUserAccountDetail) throws Exception {
        return (Integer)sqlMapClient.insert("HUserAccountDetail.insertHUserAccountDetail", hUserAccountDetail);
    }

    public int updateHUserAccountDetail(HUserAccountDetail hUserAccountDetail) throws Exception {
        return sqlMapClient.update("HUserAccountDetail.updateHUserAccountDetail", hUserAccountDetail);
    }
    
    public int removeHUserAccountDetail(HUserAccountDetail hUserAccountDetail) throws Exception {
        return sqlMapClient.delete("HUserAccountDetail.removeHUserAccountDetail", hUserAccountDetail);
    }

	public HUserAccountDetail getAllDetailFee(HUserAccountDetail param) {
		return (HUserAccountDetail)sqlMapClient.queryForObject("HUserAccountDetail.getAllDetailFee", param);
	}
	public HUserAccountDetail getAllDetailFee1(HUserAccountDetail param) {
		return (HUserAccountDetail)sqlMapClient.queryForObject("HUserAccountDetail.getAllDetailFee1", param);
	}
	public HUserAccountDetail getAllRateMoney(HUserAccountDetail param) {
		return (HUserAccountDetail)sqlMapClient.queryForObject("HUserAccountDetail.getAllRateMoney", param);
	}

}
