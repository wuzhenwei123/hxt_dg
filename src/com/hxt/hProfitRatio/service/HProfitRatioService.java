package com.hxt.hProfitRatio.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hProfitRatio.dao.HProfitRatioDAO;
import com.hxt.hProfitRatio.model.HProfitRatio;
import com.base.utils.ResponseList;

/**
 * @author	zlb
 * @time	2016年08月04日 21:43:46
 */
 @Service("hProfitRatioService")
public class HProfitRatioService {

	@Resource(name = "hProfitRatioDao")
    private HProfitRatioDAO hProfitRatioDAO;
    
    public ResponseList<HProfitRatio> getHProfitRatioList(HProfitRatio hProfitRatio) {
        return hProfitRatioDAO.getHProfitRatioList(hProfitRatio);
    }
    
    public List<HProfitRatio> getHProfitRatioBaseList(HProfitRatio hProfitRatio) {
        return hProfitRatioDAO.getHProfitRatioBaseList(hProfitRatio);
    }
    
    public int getHProfitRatioListCount(HProfitRatio hProfitRatio) {
        return hProfitRatioDAO.getHProfitRatioListCount(hProfitRatio);
    }

    public HProfitRatio getHProfitRatio(HProfitRatio hProfitRatio) { 
        return hProfitRatioDAO.getHProfitRatio(hProfitRatio);
    }

    public int insertHProfitRatio(HProfitRatio hProfitRatio) throws Exception {
    	if(hProfitRatio.getIs_default() == 1){//只有一个默认比例
    		HProfitRatio hProfitRatio1 = new HProfitRatio();
    		hProfitRatio1.setIs_default(0);
    		hProfitRatioDAO.updateAllDefaultRatio(hProfitRatio1);
    	}
    	
        return hProfitRatioDAO.insertHProfitRatio(hProfitRatio);
    }

    public int updateHProfitRatio(HProfitRatio hProfitRatio) throws Exception {
    	if(hProfitRatio.getIs_default() == 1){//只有一个默认比例
    		HProfitRatio hProfitRatio1 = new HProfitRatio();
    		hProfitRatio1.setIs_default(0);
    		hProfitRatioDAO.updateAllDefaultRatio(hProfitRatio1);
    	}
        return hProfitRatioDAO.updateHProfitRatio(hProfitRatio);
    }
    
    public int removeHProfitRatio(HProfitRatio hProfitRatio) throws Exception {
        return hProfitRatioDAO.removeHProfitRatio(hProfitRatio);
    }
    
}
