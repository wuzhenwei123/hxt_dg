package com.hxt.hLbImg.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hLbImg.dao.HLbImgDAO;
import com.hxt.hLbImg.model.HLbImg;
import com.base.utils.ResponseList;

/**
 * @author	zhanglibo
 * @time	2015年09月22日 10:05:40
 */
 @Service("hLbImgService")
public class HLbImgService {

	@Resource(name = "hLbImgDao")
    private HLbImgDAO hLbImgDAO;
    
    public ResponseList<HLbImg> getHLbImgList(HLbImg hLbImg) {
        return hLbImgDAO.getHLbImgList(hLbImg);
    }
    
    public List<HLbImg> getHLbImgBaseList(HLbImg hLbImg) {
        return hLbImgDAO.getHLbImgBaseList(hLbImg);
    }
    
    public int getHLbImgListCount(HLbImg hLbImg) {
        return hLbImgDAO.getHLbImgListCount(hLbImg);
    }

    public HLbImg getHLbImg(HLbImg hLbImg) { 
        return hLbImgDAO.getHLbImg(hLbImg);
    }

    public int insertHLbImg(HLbImg hLbImg) throws Exception {
        return hLbImgDAO.insertHLbImg(hLbImg);
    }

    public int updateHLbImg(HLbImg hLbImg) throws Exception {
        return hLbImgDAO.updateHLbImg(hLbImg);
    }
    
    public int removeHLbImg(HLbImg hLbImg) throws Exception {
        return hLbImgDAO.removeHLbImg(hLbImg);
    }
    
}
