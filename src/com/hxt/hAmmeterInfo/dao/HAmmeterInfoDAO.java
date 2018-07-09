package com.hxt.hAmmeterInfo.dao;

import java.util.List;

import com.hxt.hAmmeterInfo.model.HAmmeterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	zhanglibo
 * @time	2015年11月18日 00:54:59
 */
 @Repository("hAmmeterInfoDao")
public class HAmmeterInfoDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HAmmeterInfo> getHAmmeterInfoList(HAmmeterInfo hAmmeterInfo) {
		List<HAmmeterInfo> list = sqlMapClient.queryForList("HAmmeterInfo.getHAmmeterInfoList", hAmmeterInfo);
		return buildResponseList(list);
	}
	@SuppressWarnings("unchecked")
	public ResponseList<HAmmeterInfo> getHAmmeterInfoList1(HAmmeterInfo hAmmeterInfo) {
		List<HAmmeterInfo> list = sqlMapClient.queryForList("HAmmeterInfo.getHAmmeterInfoList1", hAmmeterInfo);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HAmmeterInfo> getHAmmeterInfoBaseList(HAmmeterInfo hAmmeterInfo) {
		return sqlMapClient.queryForList("HAmmeterInfo.getHAmmeterInfo", hAmmeterInfo);
	}
	
	@SuppressWarnings("unchecked")
	public List<HAmmeterInfo> getHAmmeterInfoBaseList1(HAmmeterInfo hAmmeterInfo) {
		return sqlMapClient.queryForList("HAmmeterInfo.getHAmmeterInfo1", hAmmeterInfo);
	}
	
	@SuppressWarnings("unchecked")
	public List<HAmmeterInfo> getHAmmeterInfoListToExport(HAmmeterInfo hAmmeterInfo) {
		return sqlMapClient.queryForList("HAmmeterInfo.getHAmmeterInfoListToExport", hAmmeterInfo);
	}

	public int getHAmmeterInfoListCount(HAmmeterInfo hAmmeterInfo) {
		return (Integer)sqlMapClient.queryForObject("HAmmeterInfo.getHAmmeterInfoListCount", hAmmeterInfo);
	}
	
	public int getHAmmeterInfoListCount1(HAmmeterInfo hAmmeterInfo) {
		return (Integer)sqlMapClient.queryForObject("HAmmeterInfo.getHAmmeterInfoListCount1", hAmmeterInfo);
	}
	
	public int getHAmmeterInfoListToExportCount(HAmmeterInfo hAmmeterInfo) {
		return (Integer)sqlMapClient.queryForObject("HAmmeterInfo.getHAmmeterInfoListToExportCount", hAmmeterInfo);
	}
	
	public HAmmeterInfo getHAmmeterInfo(HAmmeterInfo hAmmeterInfo) {
		return (HAmmeterInfo)sqlMapClient.queryForObject("HAmmeterInfo.getHAmmeterInfo", hAmmeterInfo);
	}
	
	public HAmmeterInfo getHAmmeterInfo1(HAmmeterInfo hAmmeterInfo) {
		return (HAmmeterInfo)sqlMapClient.queryForObject("HAmmeterInfo.getHAmmeterInfo1", hAmmeterInfo);
	}

    public int insertHAmmeterInfo(HAmmeterInfo hAmmeterInfo) throws Exception {
        return (Integer)sqlMapClient.insert("HAmmeterInfo.insertHAmmeterInfo", hAmmeterInfo);
    }

    public int updateHAmmeterInfo(HAmmeterInfo hAmmeterInfo) throws Exception {
        return sqlMapClient.update("HAmmeterInfo.updateHAmmeterInfo", hAmmeterInfo);
    }
    
    public int removeHAmmeterInfo(HAmmeterInfo hAmmeterInfo) throws Exception {
        return sqlMapClient.delete("HAmmeterInfo.removeHAmmeterInfo", hAmmeterInfo);
    }

    @SuppressWarnings("unchecked")
	public List<HAmmeterInfo> getHAmmeterInfoListSql(HAmmeterInfo hAmmeterInfo) {
		return sqlMapClient.queryForList("HAmmeterInfo.getHAmmeterInfoListSql", hAmmeterInfo);
	}

	public int getHAmmeterInfoListCountSql(HAmmeterInfo hAmmeterInfo) {
		return (Integer)sqlMapClient.queryForObject("HAmmeterInfo.getHAmmeterInfoListCountSql", hAmmeterInfo);
	}
    
}
