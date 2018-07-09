package com.hxt.hFp.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hxt.hFp.dao.HFpDAO;
import com.hxt.hFp.model.HFp;
import com.base.utils.ResponseList;

/**
 * @author	yy
 * @time	2015年11月17日 23:58:32
 */
 @Service("hFpService")
public class HFpService {

	@Resource(name = "hFpDao")
    private HFpDAO hFpDAO;
    
    public ResponseList<HFp> getHFpList(HFp hFp) {
        return hFpDAO.getHFpList(hFp);
    }
    
    public List<HFp> getHFpBaseList(HFp hFp) {
        return hFpDAO.getHFpBaseList(hFp);
    }
    
    public int getHFpListCount(HFp hFp) {
        return hFpDAO.getHFpListCount(hFp);
    }

    public HFp getHFp(HFp hFp) { 
        return hFpDAO.getHFp(hFp);
    }

    public int insertHFp(HFp hFp) throws Exception {
        return hFpDAO.insertHFp(hFp);
    }

    public int updateHFp(HFp hFp) throws Exception {
        return hFpDAO.updateHFp(hFp);
    }
    
    public int removeHFp(HFp hFp) throws Exception {
        return hFpDAO.removeHFp(hFp);
    }
  
    public HFp getHfpByOrderNum(String num) {
        if(StringUtils.isNotBlank(num)){
        	HFp f = new HFp();
        	f.setOrderNumber(num);
        	List<HFp> list = hFpDAO.getHFpBaseList(f);
        	if(list!=null&&list.size()>0){
        		return list.get(0);
        	}else{
        		return null;
        	}
        }else{
        	return null;
        }
    }
	public List<HFp> getHFpTagList(HFp hfp) {
		return hFpDAO.getHFpTagBaseList(hfp);
	}
    
}
