package com.hxt.hReviewUser.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hReviewUser.dao.HReviewUserDAO;
import com.hxt.hReviewUser.model.HReviewUser;
import com.base.utils.ResponseList;

/**
 * @author	zhangyiyang
 * @time	2016年08月01日 22:41:42
 */
 @Service("hReviewUserService")
public class HReviewUserService {

	@Resource(name = "hReviewUserDao")
    private HReviewUserDAO hReviewUserDAO;
    
    public ResponseList<HReviewUser> getHReviewUserList(HReviewUser hReviewUser) {
        return hReviewUserDAO.getHReviewUserList(hReviewUser);
    }
    
    public List<HReviewUser> getHReviewUserBaseList(HReviewUser hReviewUser) {
        return hReviewUserDAO.getHReviewUserBaseList(hReviewUser);
    }
    
    public int getHReviewUserListCount(HReviewUser hReviewUser) {
        return hReviewUserDAO.getHReviewUserListCount(hReviewUser);
    }

    public HReviewUser getHReviewUser(HReviewUser hReviewUser) { 
        return hReviewUserDAO.getHReviewUser(hReviewUser);
    }

    public int insertHReviewUser(HReviewUser hReviewUser) throws Exception {
        return hReviewUserDAO.insertHReviewUser(hReviewUser);
    }

    public int updateHReviewUser(HReviewUser hReviewUser) throws Exception {
        return hReviewUserDAO.updateHReviewUser(hReviewUser);
    }
    
    public int removeHReviewUser(HReviewUser hReviewUser) throws Exception {
        return hReviewUserDAO.removeHReviewUser(hReviewUser);
    }
    public int haveDefault(HReviewUser hReviewUser) throws Exception {
    	return hReviewUserDAO.haveDefault(hReviewUser);
    }
    
}
