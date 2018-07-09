package com.hxt.hAbout.dao;

import java.util.List;

import com.hxt.hAbout.model.HAbout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	keeny
 * @time	2015年08月21日 13:26:17
 */
 @Repository("hAboutDao")
public class HAboutDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HAbout> getHAboutList(HAbout hAbout) {
		List<HAbout> list = sqlMapClient.queryForList("HAbout.getHAboutList", hAbout);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HAbout> getHAboutBaseList(HAbout hAbout) {
		return sqlMapClient.queryForList("HAbout.getHAbout", hAbout);
	}

	public int getHAboutListCount(HAbout hAbout) {
		return (Integer)sqlMapClient.queryForObject("HAbout.getHAboutListCount", hAbout);
	}
	
	public HAbout getHAbout(HAbout hAbout) {
		return (HAbout)sqlMapClient.queryForObject("HAbout.getHAbout", hAbout);
	}

    public int insertHAbout(HAbout hAbout) throws Exception {
        return (Integer)sqlMapClient.insert("HAbout.insertHAbout", hAbout);
    }

    public int updateHAbout(HAbout hAbout) throws Exception {
        return sqlMapClient.update("HAbout.updateHAbout", hAbout);
    }
    
    public int removeHAbout(HAbout hAbout) throws Exception {
        return sqlMapClient.delete("HAbout.removeHAbout", hAbout);
    }

}
