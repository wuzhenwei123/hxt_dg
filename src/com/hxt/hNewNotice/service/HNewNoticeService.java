package com.hxt.hNewNotice.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hNewNotice.dao.HNewNoticeDAO;
import com.hxt.hNewNotice.model.HNewNotice;
import com.base.utils.ResponseList;

/**
 * @author	zhangyiyang
 * @time	2016年08月01日 21:15:53
 */
 @Service("hNewNoticeService")
public class HNewNoticeService {

	@Resource(name = "hNewNoticeDao")
    private HNewNoticeDAO hNewNoticeDAO;
    
    public ResponseList<HNewNotice> getHNewNoticeList(HNewNotice hNewNotice) {
        return hNewNoticeDAO.getHNewNoticeList(hNewNotice);
    }
    
    public List<HNewNotice> getHNewNoticeBaseList(HNewNotice hNewNotice) {
        return hNewNoticeDAO.getHNewNoticeBaseList(hNewNotice);
    }
    
    public int getHNewNoticeListCount(HNewNotice hNewNotice) {
        return hNewNoticeDAO.getHNewNoticeListCount(hNewNotice);
    }

    public HNewNotice getHNewNotice(HNewNotice hNewNotice) { 
        return hNewNoticeDAO.getHNewNotice(hNewNotice);
    }

    public int insertHNewNotice(HNewNotice hNewNotice) throws Exception {
        return hNewNoticeDAO.insertHNewNotice(hNewNotice);
    }

    public int updateHNewNotice(HNewNotice hNewNotice) throws Exception {
        return hNewNoticeDAO.updateHNewNotice(hNewNotice);
    }
    
    public int removeHNewNotice(HNewNotice hNewNotice) throws Exception {
        return hNewNoticeDAO.removeHNewNotice(hNewNotice);
    }
    
    public List<HNewNotice> getNoticeList(HNewNotice hNewNotice) {
    	return hNewNoticeDAO.getNoticeList(hNewNotice);
    }
    
}
