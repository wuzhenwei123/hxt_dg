package com.hxt.hProvince.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hProvince.dao.HProvinceDAO;
import com.hxt.hProvince.model.HProvince;
import com.base.utils.ResponseList;

/**
 * @author	zhangyiyang
 * @time	2016年08月02日 22:11:34
 */
 @Service("hProvinceService")
public class HProvinceService {

	@Resource(name = "hProvinceDao")
    private HProvinceDAO hProvinceDAO;
    
    public ResponseList<HProvince> getHProvinceList(HProvince hProvince) {
        return hProvinceDAO.getHProvinceList(hProvince);
    }
    
    public List<HProvince> getHProvinceBaseList(HProvince hProvince) {
        return hProvinceDAO.getHProvinceBaseList(hProvince);
    }
    
    public int getHProvinceListCount(HProvince hProvince) {
        return hProvinceDAO.getHProvinceListCount(hProvince);
    }

    public HProvince getHProvince(HProvince hProvince) { 
        return hProvinceDAO.getHProvince(hProvince);
    }

    public int insertHProvince(HProvince hProvince) throws Exception {
        return hProvinceDAO.insertHProvince(hProvince);
    }

    public int updateHProvince(HProvince hProvince) throws Exception {
        return hProvinceDAO.updateHProvince(hProvince);
    }
    
    public int removeHProvince(HProvince hProvince) throws Exception {
        return hProvinceDAO.removeHProvince(hProvince);
    }
    
}
