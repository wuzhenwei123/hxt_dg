package com.hxt.hNotice.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hNotice.dao.HNoticeDAO;
import com.hxt.hNotice.model.HNotice;
import com.base.utils.ResponseList;

/**
 * @author	zlb
 * @time	2015年09月19日 20:15:21
 */
 @Service("hNoticeService")
public class HNoticeService {

	@Resource(name = "hNoticeDao")
    private HNoticeDAO hNoticeDAO;
    
    public ResponseList<HNotice> getHNoticeList(HNotice hNotice) {
        return hNoticeDAO.getHNoticeList(hNotice);
    }
    
    public List<HNotice> getHNoticeBaseList(HNotice hNotice) {
        return hNoticeDAO.getHNoticeBaseList(hNotice);
    }
    
    public int getHNoticeListCount(HNotice hNotice) {
        return hNoticeDAO.getHNoticeListCount(hNotice);
    }

    public HNotice getHNotice(HNotice hNotice) { 
        return hNoticeDAO.getHNotice(hNotice);
    }

    public int insertHNotice(HNotice hNotice) throws Exception {
        return hNoticeDAO.insertHNotice(hNotice);
    }

    public int updateHNotice(HNotice hNotice) throws Exception {
        return hNoticeDAO.updateHNotice(hNotice);
    }
    
    public int removeHNotice(HNotice hNotice) throws Exception {
        return hNoticeDAO.removeHNotice(hNotice);
    }
    
}
