package com.hxt.hAddress.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hAddress.dao.HAddressDAO;
import com.hxt.hAddress.model.HAddress;
import com.base.utils.ResponseList;

/**
 * @author	zhanglibo
 * @time	2015年09月20日 11:57:01
 */
 @Service("hAddressService")
public class HAddressService {

	@Resource(name = "hAddressDao")
    private HAddressDAO hAddressDAO;
    
    public ResponseList<HAddress> getHAddressList(HAddress hAddress) {
        return hAddressDAO.getHAddressList(hAddress);
    }
    
    public List<HAddress> getHAddressBaseList(HAddress hAddress) {
        return hAddressDAO.getHAddressBaseList(hAddress);
    }
    
    public int getHAddressListCount(HAddress hAddress) {
        return hAddressDAO.getHAddressListCount(hAddress);
    }

    public HAddress getHAddress(HAddress hAddress) { 
        return hAddressDAO.getHAddress(hAddress);
    }

    public int insertHAddress(HAddress hAddress) throws Exception {
        return hAddressDAO.insertHAddress(hAddress);
    }

    public int updateHAddress(HAddress hAddress) throws Exception {
        return hAddressDAO.updateHAddress(hAddress);
    }
    
    public int removeHAddress(HAddress hAddress) throws Exception {
        return hAddressDAO.removeHAddress(hAddress);
    }
    
}
