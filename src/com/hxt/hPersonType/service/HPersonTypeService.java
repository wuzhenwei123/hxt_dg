package com.hxt.hPersonType.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hPersonType.dao.HPersonTypeDAO;
import com.hxt.hPersonType.model.HPersonType;
import com.base.utils.ResponseList;

/**
 * @author	zlb
 * @time	2016年08月06日 14:03:08
 */
 @Service("hPersonTypeService")
public class HPersonTypeService {

	@Resource(name = "hPersonTypeDao")
    private HPersonTypeDAO hPersonTypeDAO;
    
    public ResponseList<HPersonType> getHPersonTypeList(HPersonType hPersonType) {
        return hPersonTypeDAO.getHPersonTypeList(hPersonType);
    }
    
    public List<HPersonType> getHPersonTypeBaseList(HPersonType hPersonType) {
        return hPersonTypeDAO.getHPersonTypeBaseList(hPersonType);
    }
    
    public int getHPersonTypeListCount(HPersonType hPersonType) {
        return hPersonTypeDAO.getHPersonTypeListCount(hPersonType);
    }

    public HPersonType getHPersonType(HPersonType hPersonType) { 
        return hPersonTypeDAO.getHPersonType(hPersonType);
    }

    public int insertHPersonType(HPersonType hPersonType) throws Exception {
        return hPersonTypeDAO.insertHPersonType(hPersonType);
    }

    public int updateHPersonType(HPersonType hPersonType) throws Exception {
        return hPersonTypeDAO.updateHPersonType(hPersonType);
    }
    
    public int removeHPersonType(HPersonType hPersonType) throws Exception {
        return hPersonTypeDAO.removeHPersonType(hPersonType);
    }
    
}
