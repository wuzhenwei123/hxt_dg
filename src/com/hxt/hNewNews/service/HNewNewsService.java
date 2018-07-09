package com.hxt.hNewNews.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hNewNews.dao.HNewNewsDAO;
import com.hxt.hNewNews.model.HNewNews;
import com.base.utils.ResponseList;

/**
 * @author	zhangyiyang
 * @time	2016年08月01日 21:15:27
 */
 @Service("hNewNewsService")
public class HNewNewsService {

	@Resource(name = "hNewNewsDao")
    private HNewNewsDAO hNewNewsDAO;
    
    public ResponseList<HNewNews> getHNewNewsList(HNewNews hNewNews) {
        return hNewNewsDAO.getHNewNewsList(hNewNews);
    }
    
    public List<HNewNews> getHNewNewsBaseList(HNewNews hNewNews) {
        return hNewNewsDAO.getHNewNewsBaseList(hNewNews);
    }
    
    public int getHNewNewsListCount(HNewNews hNewNews) {
        return hNewNewsDAO.getHNewNewsListCount(hNewNews);
    }

    public HNewNews getHNewNews(HNewNews hNewNews) { 
        return hNewNewsDAO.getHNewNews(hNewNews);
    }

    public int insertHNewNews(HNewNews hNewNews) throws Exception {
        return hNewNewsDAO.insertHNewNews(hNewNews);
    }

    public int updateHNewNews(HNewNews hNewNews) throws Exception {
        return hNewNewsDAO.updateHNewNews(hNewNews);
    }
    
    public int removeHNewNews(HNewNews hNewNews) throws Exception {
        return hNewNewsDAO.removeHNewNews(hNewNews);
    }
    
    public List<HNewNews> getNewsList(HNewNews hNewNews) {
    	return hNewNewsDAO.getNewsList(hNewNews);
    }
    
}
