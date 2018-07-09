package com.hxt.hNews.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hNews.dao.HNewsDAO;
import com.hxt.hNews.model.HNews;
import com.base.utils.ResponseList;

/**
 * @author	zlb
 * @time	2015年09月19日 19:45:50
 */
 @Service("hNewsService")
public class HNewsService {

	@Resource(name = "hNewsDao")
    private HNewsDAO hNewsDAO;
    
    public ResponseList<HNews> getHNewsList(HNews hNews) {
        return hNewsDAO.getHNewsList(hNews);
    }
    
    public List<HNews> getHNewsBaseList(HNews hNews) {
        return hNewsDAO.getHNewsBaseList(hNews);
    }
    
    public int getHNewsListCount(HNews hNews) {
        return hNewsDAO.getHNewsListCount(hNews);
    }

    public HNews getHNews(HNews hNews) { 
        return hNewsDAO.getHNews(hNews);
    }

    public int insertHNews(HNews hNews) throws Exception {
        return hNewsDAO.insertHNews(hNews);
    }

    public int updateHNews(HNews hNews) throws Exception {
        return hNewsDAO.updateHNews(hNews);
    }
    
    public int removeHNews(HNews hNews) throws Exception {
        return hNewsDAO.removeHNews(hNews);
    }
    
}
