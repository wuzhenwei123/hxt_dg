package com.hxt.hBankInfo.dao;

import java.util.List;

import com.hxt.hBankInfo.model.HBankInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2016年10月20日 17:50:19
 */
 @Repository("hBankInfoDao")
public class HBankInfoDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HBankInfo> getHBankInfoList(HBankInfo hBankInfo) {
		List<HBankInfo> list = sqlMapClient.queryForList("HBankInfo.getHBankInfoList", hBankInfo);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HBankInfo> getHBankInfoBaseList(HBankInfo hBankInfo) {
		return sqlMapClient.queryForList("HBankInfo.getHBankInfo", hBankInfo);
	}

	public int getHBankInfoListCount(HBankInfo hBankInfo) {
		return (Integer)sqlMapClient.queryForObject("HBankInfo.getHBankInfoListCount", hBankInfo);
	}
	
	public HBankInfo getHBankInfo(HBankInfo hBankInfo) {
		return (HBankInfo)sqlMapClient.queryForObject("HBankInfo.getHBankInfo", hBankInfo);
	}

    public int insertHBankInfo(HBankInfo hBankInfo) throws Exception {
        return (Integer)sqlMapClient.insert("HBankInfo.insertHBankInfo", hBankInfo);
    }

    public int updateHBankInfo(HBankInfo hBankInfo) throws Exception {
        return sqlMapClient.update("HBankInfo.updateHBankInfo", hBankInfo);
    }
    
    public int removeHBankInfo(HBankInfo hBankInfo) throws Exception {
        return sqlMapClient.delete("HBankInfo.removeHBankInfo", hBankInfo);
    }

}
