package com.hxt.hArea.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hxt.hAmmeterInfo.model.HAmmeterInfo;
import com.hxt.hArea.dao.HAreaDAO;
import com.hxt.hArea.model.HArea;
import com.base.utils.ResponseList;

/**
 * @author	zhangyiyang
 * @time	2016年08月02日 22:11:12
 */
 @Service("hAreaService")
public class HAreaService {

	@Resource(name = "hAreaDao")
    private HAreaDAO hAreaDAO;
    
    public ResponseList<HArea> getHAreaList(HArea hArea) {
        return hAreaDAO.getHAreaList(hArea);
    }
    
    public List<HArea> getHAreaBaseList(HArea hArea) {
        return hAreaDAO.getHAreaBaseList(hArea);
    }
    
    public int getHAreaListCount(HArea hArea) {
        return hAreaDAO.getHAreaListCount(hArea);
    }

    public HArea getHArea(HArea hArea) { 
        return hAreaDAO.getHArea(hArea);
    }

    public int insertHArea(HArea hArea) throws Exception {
        return hAreaDAO.insertHArea(hArea);
    }

    public int updateHArea(HArea hArea) throws Exception {
        return hAreaDAO.updateHArea(hArea);
    }
    
    public int removeHArea(HArea hArea) throws Exception {
        return hAreaDAO.removeHArea(hArea);
    }
    
    public HAmmeterInfo setAreaCodeToAmmeterInfo(HAmmeterInfo hAmmeterInfo){
    	hAmmeterInfo.setProvince_code("110000");
		hAmmeterInfo.setProvince_name("北京市");
		hAmmeterInfo.setCity_code("110100");
		hAmmeterInfo.setCity_name("北京市");
		if(hAmmeterInfo!=null&&StringUtils.isNotBlank(hAmmeterInfo.getAmmeter_address())){
			if(hAmmeterInfo.getAmmeter_address().contains("东城")){
				hAmmeterInfo.setArea_code("110101");
				hAmmeterInfo.setArea_name("东城区");
			}else if(hAmmeterInfo.getAmmeter_address().contains("西城")){
				hAmmeterInfo.setArea_code("110102");
				hAmmeterInfo.setArea_name("西城区");
			}else if(hAmmeterInfo.getAmmeter_address().contains("崇文")){
				hAmmeterInfo.setArea_code("110103");
				hAmmeterInfo.setArea_name("崇文区");
			}else if(hAmmeterInfo.getAmmeter_address().contains("宣武")){
				hAmmeterInfo.setArea_code("110104");
				hAmmeterInfo.setArea_name("宣武区");
			}else if(hAmmeterInfo.getAmmeter_address().contains("朝阳")){
				hAmmeterInfo.setArea_code("110105");
				hAmmeterInfo.setArea_name("朝阳区");
			}else if(hAmmeterInfo.getAmmeter_address().contains("丰台")){
				hAmmeterInfo.setArea_code("110106");
				hAmmeterInfo.setArea_name("丰台区");
			}else if(hAmmeterInfo.getAmmeter_address().contains("石景山")){
				hAmmeterInfo.setArea_code("110107");
				hAmmeterInfo.setArea_name("石景山区");
			}else if(hAmmeterInfo.getAmmeter_address().contains("海淀")){
				hAmmeterInfo.setArea_code("110108");
				hAmmeterInfo.setArea_name("海淀区");
			}else if(hAmmeterInfo.getAmmeter_address().contains("门头沟")){
				hAmmeterInfo.setArea_code("110109");
				hAmmeterInfo.setArea_name("门头沟区");
			}else if(hAmmeterInfo.getAmmeter_address().contains("房山")){
				hAmmeterInfo.setArea_code("110111");
				hAmmeterInfo.setArea_name("房山区");
			}else if(hAmmeterInfo.getAmmeter_address().contains("通州")){
				hAmmeterInfo.setArea_code("110112");
				hAmmeterInfo.setArea_name("通州区");
			}else if(hAmmeterInfo.getAmmeter_address().contains("顺义")){
				hAmmeterInfo.setArea_code("110113");
				hAmmeterInfo.setArea_name("顺义区");
			}else if(hAmmeterInfo.getAmmeter_address().contains("昌平")){
				hAmmeterInfo.setArea_code("110114");
				hAmmeterInfo.setArea_name("昌平区");
			}else if(hAmmeterInfo.getAmmeter_address().contains("大兴")){
				hAmmeterInfo.setArea_code("110115");
				hAmmeterInfo.setArea_name("大兴区");
			}else if(hAmmeterInfo.getAmmeter_address().contains("怀柔")){
				hAmmeterInfo.setArea_code("110116");
				hAmmeterInfo.setArea_name("怀柔区");
			}else if(hAmmeterInfo.getAmmeter_address().contains("平谷")){
				hAmmeterInfo.setArea_code("110117");
				hAmmeterInfo.setArea_name("平谷区");
			}
		}
		return hAmmeterInfo;
    }
    
}
