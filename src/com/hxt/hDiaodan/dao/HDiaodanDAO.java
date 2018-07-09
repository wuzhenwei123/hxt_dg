package com.hxt.hDiaodan.dao;

import java.util.List;

import com.hxt.hDiaodan.model.HDiaodan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2016年09月22日 15:07:03
 */
 @Repository("hDiaodanDao")
public class HDiaodanDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HDiaodan> getHDiaodanList(HDiaodan hDiaodan) {
		List<HDiaodan> list = sqlMapClient.queryForList("HDiaodan.getHDiaodanList", hDiaodan);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HDiaodan> getHDiaodanBaseList(HDiaodan hDiaodan) {
		return sqlMapClient.queryForList("HDiaodan.getHDiaodan", hDiaodan);
	}

	public int getHDiaodanListCount(HDiaodan hDiaodan) {
		return (Integer)sqlMapClient.queryForObject("HDiaodan.getHDiaodanListCount", hDiaodan);
	}
	
	public HDiaodan getHDiaodan(HDiaodan hDiaodan) {
		return (HDiaodan)sqlMapClient.queryForObject("HDiaodan.getHDiaodan", hDiaodan);
	}

    public int insertHDiaodan(HDiaodan hDiaodan) throws Exception {
        return (Integer)sqlMapClient.insert("HDiaodan.insertHDiaodan", hDiaodan);
    }

    public int updateHDiaodan(HDiaodan hDiaodan) throws Exception {
        return sqlMapClient.update("HDiaodan.updateHDiaodan", hDiaodan);
    }
    
    public int removeHDiaodan(HDiaodan hDiaodan) throws Exception {
        return sqlMapClient.delete("HDiaodan.removeHDiaodan", hDiaodan);
    }

}
