package com.hxt.hHelp.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hHelp.dao.HHelpDAO;
import com.hxt.hHelp.model.HHelp;
import com.base.utils.ResponseList;

/**
 * @author	wuzhenwei
 * @time	2015年09月19日 18:16:53
 */
 @Service("hHelpService")
public class HHelpService {

	@Resource(name = "hHelpDao")
    private HHelpDAO hHelpDAO;
    
    public ResponseList<HHelp> getHHelpList(HHelp hHelp) {
        return hHelpDAO.getHHelpList(hHelp);
    }
    
    public List<HHelp> getHHelpBaseList(HHelp hHelp) {
        return hHelpDAO.getHHelpBaseList(hHelp);
    }
    
    public int getHHelpListCount(HHelp hHelp) {
        return hHelpDAO.getHHelpListCount(hHelp);
    }

    public HHelp getHHelp(HHelp hHelp) { 
        return hHelpDAO.getHHelp(hHelp);
    }

    public int insertHHelp(HHelp hHelp) throws Exception {
        return hHelpDAO.insertHHelp(hHelp);
    }

    public int updateHHelp(HHelp hHelp) throws Exception {
        return hHelpDAO.updateHHelp(hHelp);
    }
    
    public int removeHHelp(HHelp hHelp) throws Exception {
        return hHelpDAO.removeHHelp(hHelp);
    }
    
}
