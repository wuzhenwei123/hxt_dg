package com.hxt.hHelp.dao;

import java.util.List;

import com.hxt.hHelp.model.HHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2015年09月19日 18:16:53
 */
 @Repository("hHelpDao")
public class HHelpDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HHelp> getHHelpList(HHelp hHelp) {
		List<HHelp> list = sqlMapClient.queryForList("HHelp.getHHelpList", hHelp);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HHelp> getHHelpBaseList(HHelp hHelp) {
		return sqlMapClient.queryForList("HHelp.getHHelp", hHelp);
	}

	public int getHHelpListCount(HHelp hHelp) {
		return (Integer)sqlMapClient.queryForObject("HHelp.getHHelpListCount", hHelp);
	}
	
	public HHelp getHHelp(HHelp hHelp) {
		return (HHelp)sqlMapClient.queryForObject("HHelp.getHHelp", hHelp);
	}

    public int insertHHelp(HHelp hHelp) throws Exception {
        return (Integer)sqlMapClient.insert("HHelp.insertHHelp", hHelp);
    }

    public int updateHHelp(HHelp hHelp) throws Exception {
        return sqlMapClient.update("HHelp.updateHHelp", hHelp);
    }
    
    public int removeHHelp(HHelp hHelp) throws Exception {
        return sqlMapClient.delete("HHelp.removeHHelp", hHelp);
    }

}
