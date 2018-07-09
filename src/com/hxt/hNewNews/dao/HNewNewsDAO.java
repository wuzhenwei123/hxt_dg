package com.hxt.hNewNews.dao;

import java.util.List;

import com.hxt.hNewNews.model.HNewNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	zhangyiyang
 * @time	2016年08月01日 21:15:25
 */
 @Repository("hNewNewsDao")
public class HNewNewsDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HNewNews> getHNewNewsList(HNewNews hNewNews) {
		List<HNewNews> list = sqlMapClient.queryForList("HNewNews.getHNewNewsList", hNewNews);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HNewNews> getHNewNewsBaseList(HNewNews hNewNews) {
		return sqlMapClient.queryForList("HNewNews.getHNewNews", hNewNews);
	}

	public int getHNewNewsListCount(HNewNews hNewNews) {
		return (Integer)sqlMapClient.queryForObject("HNewNews.getHNewNewsListCount", hNewNews);
	}
	
	public HNewNews getHNewNews(HNewNews hNewNews) {
		return (HNewNews)sqlMapClient.queryForObject("HNewNews.getHNewNews", hNewNews);
	}

    public int insertHNewNews(HNewNews hNewNews) throws Exception {
        return (Integer)sqlMapClient.insert("HNewNews.insertHNewNews", hNewNews);
    }

    public int updateHNewNews(HNewNews hNewNews) throws Exception {
        return sqlMapClient.update("HNewNews.updateHNewNews", hNewNews);
    }
    
    public int removeHNewNews(HNewNews hNewNews) throws Exception {
        return sqlMapClient.delete("HNewNews.removeHNewNews", hNewNews);
    }
    
    public List<HNewNews> getNewsList(HNewNews hNewNews) {
		return sqlMapClient.queryForList("HNewNews.getHNewNewsList", hNewNews);
	}

}
