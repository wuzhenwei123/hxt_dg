package com.hxt.hFuwu.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hFuwu.dao.HFuwuDAO;
import com.hxt.hFuwu.model.HFuwu;
import com.base.utils.ResponseList;

/**
 * @author	zlb
 * @time	2015年09月19日 19:01:41
 */
 @Service("hFuwuService")
public class HFuwuService {

	@Resource(name = "hFuwuDao")
    private HFuwuDAO hFuwuDAO;
    
    public ResponseList<HFuwu> getHFuwuList(HFuwu hFuwu) {
        return hFuwuDAO.getHFuwuList(hFuwu);
    }
    
    public List<HFuwu> getHFuwuBaseList(HFuwu hFuwu) {
        return hFuwuDAO.getHFuwuBaseList(hFuwu);
    }
    
    public int getHFuwuListCount(HFuwu hFuwu) {
        return hFuwuDAO.getHFuwuListCount(hFuwu);
    }

    public HFuwu getHFuwu(HFuwu hFuwu) { 
        return hFuwuDAO.getHFuwu(hFuwu);
    }

    public int insertHFuwu(HFuwu hFuwu) throws Exception {
        return hFuwuDAO.insertHFuwu(hFuwu);
    }

    public int updateHFuwu(HFuwu hFuwu) throws Exception {
        return hFuwuDAO.updateHFuwu(hFuwu);
    }
    
    public int removeHFuwu(HFuwu hFuwu) throws Exception {
        return hFuwuDAO.removeHFuwu(hFuwu);
    }
    
}
