package com.hxt.hCity.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hCity.dao.HCityDAO;
import com.hxt.hCity.model.HCity;
import com.base.utils.ResponseList;

/**
 * @author	zhangyiyang
 * @time	2016年08月02日 22:11:23
 */
 @Service("hCityService")
public class HCityService {

	@Resource(name = "hCityDao")
    private HCityDAO hCityDAO;
    
    public ResponseList<HCity> getHCityList(HCity hCity) {
        return hCityDAO.getHCityList(hCity);
    }
    
    public List<HCity> getHCityBaseList(HCity hCity) {
        return hCityDAO.getHCityBaseList(hCity);
    }
    
    public int getHCityListCount(HCity hCity) {
        return hCityDAO.getHCityListCount(hCity);
    }

    public HCity getHCity(HCity hCity) { 
        return hCityDAO.getHCity(hCity);
    }

    public int insertHCity(HCity hCity) throws Exception {
        return hCityDAO.insertHCity(hCity);
    }

    public int updateHCity(HCity hCity) throws Exception {
        return hCityDAO.updateHCity(hCity);
    }
    
    public int removeHCity(HCity hCity) throws Exception {
        return hCityDAO.removeHCity(hCity);
    }
    
}
