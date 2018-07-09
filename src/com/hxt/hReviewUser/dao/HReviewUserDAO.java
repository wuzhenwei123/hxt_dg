package com.hxt.hReviewUser.dao;

import java.util.List;

import com.hxt.hReviewUser.model.HReviewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	zhangyiyang
 * @time	2016年08月01日 22:41:40
 */
 @Repository("hReviewUserDao")
public class HReviewUserDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HReviewUser> getHReviewUserList(HReviewUser hReviewUser) {
		List<HReviewUser> list = sqlMapClient.queryForList("HReviewUser.getHReviewUserList", hReviewUser);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HReviewUser> getHReviewUserBaseList(HReviewUser hReviewUser) {
		return sqlMapClient.queryForList("HReviewUser.getHReviewUser", hReviewUser);
	}

	public int getHReviewUserListCount(HReviewUser hReviewUser) {
		return (Integer)sqlMapClient.queryForObject("HReviewUser.getHReviewUserListCount", hReviewUser);
	}
	
	public HReviewUser getHReviewUser(HReviewUser hReviewUser) {
		return (HReviewUser)sqlMapClient.queryForObject("HReviewUser.getHReviewUser", hReviewUser);
	}

    public int insertHReviewUser(HReviewUser hReviewUser) throws Exception {
        return (Integer)sqlMapClient.insert("HReviewUser.insertHReviewUser", hReviewUser);
    }

    public int updateHReviewUser(HReviewUser hReviewUser) throws Exception {
        return sqlMapClient.update("HReviewUser.updateHReviewUser", hReviewUser);
    }
    
    public int removeHReviewUser(HReviewUser hReviewUser) throws Exception {
        return sqlMapClient.delete("HReviewUser.removeHReviewUser", hReviewUser);
    }

	public int haveDefault(HReviewUser hReviewUser) {
		return (Integer)sqlMapClient.queryForObject("HReviewUser.haveDefault", hReviewUser);
	}

}
