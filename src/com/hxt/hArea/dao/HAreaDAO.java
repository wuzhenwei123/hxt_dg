package com.hxt.hArea.dao;

import java.util.List;

import com.hxt.hArea.model.HArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	zhangyiyang
 * @time	2016年08月02日 22:11:11
 */
 @Repository("hAreaDao")
public class HAreaDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HArea> getHAreaList(HArea hArea) {
		List<HArea> list = sqlMapClient.queryForList("HArea.getHAreaList", hArea);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HArea> getHAreaBaseList(HArea hArea) {
		return sqlMapClient.queryForList("HArea.getHArea", hArea);
	}

	public int getHAreaListCount(HArea hArea) {
		return (Integer)sqlMapClient.queryForObject("HArea.getHAreaListCount", hArea);
	}
	
	public HArea getHArea(HArea hArea) {
		return (HArea)sqlMapClient.queryForObject("HArea.getHArea", hArea);
	}

    public int insertHArea(HArea hArea) throws Exception {
        return (Integer)sqlMapClient.insert("HArea.insertHArea", hArea);
    }

    public int updateHArea(HArea hArea) throws Exception {
        return sqlMapClient.update("HArea.updateHArea", hArea);
    }
    
    public int removeHArea(HArea hArea) throws Exception {
        return sqlMapClient.delete("HArea.removeHArea", hArea);
    }

}
