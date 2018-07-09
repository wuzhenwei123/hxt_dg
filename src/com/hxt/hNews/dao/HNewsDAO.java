package com.hxt.hNews.dao;

import java.util.List;

import com.hxt.hNews.model.HNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	zlb
 * @time	2015年09月19日 19:45:50
 */
 @Repository("hNewsDao")
public class HNewsDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HNews> getHNewsList(HNews hNews) {
		List<HNews> list = sqlMapClient.queryForList("HNews.getHNewsList", hNews);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HNews> getHNewsBaseList(HNews hNews) {
		return sqlMapClient.queryForList("HNews.getHNews", hNews);
	}

	public int getHNewsListCount(HNews hNews) {
		return (Integer)sqlMapClient.queryForObject("HNews.getHNewsListCount", hNews);
	}
	
	public HNews getHNews(HNews hNews) {
		return (HNews)sqlMapClient.queryForObject("HNews.getHNews", hNews);
	}

    public int insertHNews(HNews hNews) throws Exception {
        return (Integer)sqlMapClient.insert("HNews.insertHNews", hNews);
    }

    public int updateHNews(HNews hNews) throws Exception {
        return sqlMapClient.update("HNews.updateHNews", hNews);
    }
    
    public int removeHNews(HNews hNews) throws Exception {
        return sqlMapClient.delete("HNews.removeHNews", hNews);
    }

}
