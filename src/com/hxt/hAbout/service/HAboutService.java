package com.hxt.hAbout.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hAbout.dao.HAboutDAO;
import com.hxt.hAbout.model.HAbout;
import com.base.utils.ResponseList;

/**
 * @author	keeny
 * @time	2015年08月21日 13:26:17
 */
 @Service("hAboutService")
public class HAboutService {

	@Resource(name = "hAboutDao")
    private HAboutDAO hAboutDAO;
    
    public ResponseList<HAbout> getHAboutList(HAbout hAbout) {
        return hAboutDAO.getHAboutList(hAbout);
    }
    
    public List<HAbout> getHAboutBaseList(HAbout hAbout) {
        return hAboutDAO.getHAboutBaseList(hAbout);
    }
    
    public int getHAboutListCount(HAbout hAbout) {
        return hAboutDAO.getHAboutListCount(hAbout);
    }

    public HAbout getHAbout(HAbout hAbout) { 
        return hAboutDAO.getHAbout(hAbout);
    }

    public int insertHAbout(HAbout hAbout) throws Exception {
        return hAboutDAO.insertHAbout(hAbout);
    }

    public int updateHAbout(HAbout hAbout) throws Exception {
        return hAboutDAO.updateHAbout(hAbout);
    }
    
    public int removeHAbout(HAbout hAbout) throws Exception {
        return hAboutDAO.removeHAbout(hAbout);
    }
    
}
