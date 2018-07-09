package com.hxt.hSubCompany.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hSubCompany.dao.HSubCompanyDAO;
import com.hxt.hSubCompany.model.HSubCompany;
import com.base.utils.ResponseList;

/**
 * @author	wuzhenwei
 * @time	2015年11月24日 10:42:57
 */
 @Service("hSubCompanyService")
public class HSubCompanyService {

	@Resource(name = "hSubCompanyDao")
    private HSubCompanyDAO hSubCompanyDAO;
    
    public ResponseList<HSubCompany> getHSubCompanyList(HSubCompany hSubCompany) {
        return hSubCompanyDAO.getHSubCompanyList(hSubCompany);
    }
    
    public List<HSubCompany> getHSubCompanyBaseList(HSubCompany hSubCompany) {
        return hSubCompanyDAO.getHSubCompanyBaseList(hSubCompany);
    }
    
    public int getHSubCompanyListCount(HSubCompany hSubCompany) {
        return hSubCompanyDAO.getHSubCompanyListCount(hSubCompany);
    }

    public HSubCompany getHSubCompany(HSubCompany hSubCompany) { 
        return hSubCompanyDAO.getHSubCompany(hSubCompany);
    }

    public int insertHSubCompany(HSubCompany hSubCompany) throws Exception {
        return hSubCompanyDAO.insertHSubCompany(hSubCompany);
    }

    public int updateHSubCompany(HSubCompany hSubCompany) throws Exception {
        return hSubCompanyDAO.updateHSubCompany(hSubCompany);
    }
    
    public int removeHSubCompany(HSubCompany hSubCompany) throws Exception {
        return hSubCompanyDAO.removeHSubCompany(hSubCompany);
    }
    
}
