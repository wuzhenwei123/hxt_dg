package com.hxt.hLbImg.dao;

import java.util.List;

import com.hxt.hLbImg.model.HLbImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	zhanglibo
 * @time	2015年09月22日 10:05:39
 */
 @Repository("hLbImgDao")
public class HLbImgDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HLbImg> getHLbImgList(HLbImg hLbImg) {
		List<HLbImg> list = sqlMapClient.queryForList("HLbImg.getHLbImgList", hLbImg);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HLbImg> getHLbImgBaseList(HLbImg hLbImg) {
		return sqlMapClient.queryForList("HLbImg.getHLbImg", hLbImg);
	}

	public int getHLbImgListCount(HLbImg hLbImg) {
		return (Integer)sqlMapClient.queryForObject("HLbImg.getHLbImgListCount", hLbImg);
	}
	
	public HLbImg getHLbImg(HLbImg hLbImg) {
		return (HLbImg)sqlMapClient.queryForObject("HLbImg.getHLbImg", hLbImg);
	}

    public int insertHLbImg(HLbImg hLbImg) throws Exception {
        return (Integer)sqlMapClient.insert("HLbImg.insertHLbImg", hLbImg);
    }

    public int updateHLbImg(HLbImg hLbImg) throws Exception {
        return sqlMapClient.update("HLbImg.updateHLbImg", hLbImg);
    }
    
    public int removeHLbImg(HLbImg hLbImg) throws Exception {
        return sqlMapClient.delete("HLbImg.removeHLbImg", hLbImg);
    }

}
