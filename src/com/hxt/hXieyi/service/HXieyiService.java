package com.hxt.hXieyi.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hXieyi.dao.HXieyiDAO;
import com.hxt.hXieyi.model.HXieyi;
import com.base.utils.ResponseList;

/**
 * @author	zhanglibo
 * @time	2016年08月29日 10:31:37
 */
 @Service("hXieyiService")
public class HXieyiService {

	@Resource(name = "hXieyiDao")
    private HXieyiDAO hXieyiDAO;
    
    public ResponseList<HXieyi> getHXieyiList(HXieyi hXieyi) {
        return hXieyiDAO.getHXieyiList(hXieyi);
    }
    
    public List<HXieyi> getHXieyiBaseList(HXieyi hXieyi) {
        return hXieyiDAO.getHXieyiBaseList(hXieyi);
    }
    
    public int getHXieyiListCount(HXieyi hXieyi) {
        return hXieyiDAO.getHXieyiListCount(hXieyi);
    }

    public HXieyi getHXieyi(HXieyi hXieyi) { 
        return hXieyiDAO.getHXieyi(hXieyi);
    }

    public int insertHXieyi(HXieyi hXieyi) throws Exception {
        return hXieyiDAO.insertHXieyi(hXieyi);
    }

    public int updateHXieyi(HXieyi hXieyi) throws Exception {
        return hXieyiDAO.updateHXieyi(hXieyi);
    }
    
    public int removeHXieyi(HXieyi hXieyi) throws Exception {
        return hXieyiDAO.removeHXieyi(hXieyi);
    }
    
}
