package com.hxt.hFuwu.dao;

import java.util.List;

import com.hxt.hFuwu.model.HFuwu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	zlb
 * @time	2015年09月19日 19:01:41
 */
 @Repository("hFuwuDao")
public class HFuwuDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HFuwu> getHFuwuList(HFuwu hFuwu) {
		List<HFuwu> list = sqlMapClient.queryForList("HFuwu.getHFuwuList", hFuwu);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HFuwu> getHFuwuBaseList(HFuwu hFuwu) {
		return sqlMapClient.queryForList("HFuwu.getHFuwu", hFuwu);
	}

	public int getHFuwuListCount(HFuwu hFuwu) {
		return (Integer)sqlMapClient.queryForObject("HFuwu.getHFuwuListCount", hFuwu);
	}
	
	public HFuwu getHFuwu(HFuwu hFuwu) {
		return (HFuwu)sqlMapClient.queryForObject("HFuwu.getHFuwu", hFuwu);
	}

    public int insertHFuwu(HFuwu hFuwu) throws Exception {
        return (Integer)sqlMapClient.insert("HFuwu.insertHFuwu", hFuwu);
    }

    public int updateHFuwu(HFuwu hFuwu) throws Exception {
        return sqlMapClient.update("HFuwu.updateHFuwu", hFuwu);
    }
    
    public int removeHFuwu(HFuwu hFuwu) throws Exception {
        return sqlMapClient.delete("HFuwu.removeHFuwu", hFuwu);
    }

}
