package com.hxt.hAddress.dao;

import java.util.List;

import com.hxt.hAddress.model.HAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	zhanglibo
 * @time	2015年09月20日 11:56:58
 */
 @Repository("hAddressDao")
public class HAddressDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HAddress> getHAddressList(HAddress hAddress) {
		List<HAddress> list = sqlMapClient.queryForList("HAddress.getHAddressList", hAddress);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HAddress> getHAddressBaseList(HAddress hAddress) {
		return sqlMapClient.queryForList("HAddress.getHAddress", hAddress);
	}

	public int getHAddressListCount(HAddress hAddress) {
		return (Integer)sqlMapClient.queryForObject("HAddress.getHAddressListCount", hAddress);
	}
	
	public HAddress getHAddress(HAddress hAddress) {
		return (HAddress)sqlMapClient.queryForObject("HAddress.getHAddress", hAddress);
	}

    public int insertHAddress(HAddress hAddress) throws Exception {
        return (Integer)sqlMapClient.insert("HAddress.insertHAddress", hAddress);
    }

    public int updateHAddress(HAddress hAddress) throws Exception {
        return sqlMapClient.update("HAddress.updateHAddress", hAddress);
    }
    
    public int removeHAddress(HAddress hAddress) throws Exception {
        return sqlMapClient.delete("HAddress.removeHAddress", hAddress);
    }

}
