package com.hxt.hDiaodan.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hDiaodan.dao.HDiaodanDAO;
import com.hxt.hDiaodan.model.HDiaodan;
import com.base.utils.ResponseList;

/**
 * @author	wuzhenwei
 * @time	2016年09月22日 15:07:03
 */
 @Service("hDiaodanService")
public class HDiaodanService {

	@Resource(name = "hDiaodanDao")
    private HDiaodanDAO hDiaodanDAO;
    
    public ResponseList<HDiaodan> getHDiaodanList(HDiaodan hDiaodan) {
        return hDiaodanDAO.getHDiaodanList(hDiaodan);
    }
    
    public List<HDiaodan> getHDiaodanBaseList(HDiaodan hDiaodan) {
        return hDiaodanDAO.getHDiaodanBaseList(hDiaodan);
    }
    
    public int getHDiaodanListCount(HDiaodan hDiaodan) {
        return hDiaodanDAO.getHDiaodanListCount(hDiaodan);
    }

    public HDiaodan getHDiaodan(HDiaodan hDiaodan) { 
        return hDiaodanDAO.getHDiaodan(hDiaodan);
    }

    public int insertHDiaodan(HDiaodan hDiaodan) throws Exception {
        return hDiaodanDAO.insertHDiaodan(hDiaodan);
    }

    public int updateHDiaodan(HDiaodan hDiaodan) throws Exception {
        return hDiaodanDAO.updateHDiaodan(hDiaodan);
    }
    
    public int removeHDiaodan(HDiaodan hDiaodan) throws Exception {
        return hDiaodanDAO.removeHDiaodan(hDiaodan);
    }
    
}
