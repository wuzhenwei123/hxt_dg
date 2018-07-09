package com.hxt.hPresentApply.service;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.hxt.hPresentApply.dao.HPresentApplyDAO;
import com.hxt.hPresentApply.model.HPresentApply;
import com.base.utils.ResponseList;

/**
 * @author	zhangyiyang
 * @time	2016年08月29日 23:04:40
 */
 @Service("hPresentApplyService")
public class HPresentApplyService {

	@Resource(name = "hPresentApplyDao")
    private HPresentApplyDAO hPresentApplyDAO;
    
    public ResponseList<HPresentApply> getHPresentApplyList(HPresentApply hPresentApply) {
        return hPresentApplyDAO.getHPresentApplyList(hPresentApply);
    }
    
    public List<HPresentApply> getHPresentApplyBaseList(HPresentApply hPresentApply) {
        return hPresentApplyDAO.getHPresentApplyBaseList(hPresentApply);
    }
    
    public int getHPresentApplyListCount(HPresentApply hPresentApply) {
        return hPresentApplyDAO.getHPresentApplyListCount(hPresentApply);
    }

    public HPresentApply getHPresentApply(HPresentApply hPresentApply) { 
        return hPresentApplyDAO.getHPresentApply(hPresentApply);
    }

    public int insertHPresentApply(HPresentApply hPresentApply) throws Exception {
        return hPresentApplyDAO.insertHPresentApply(hPresentApply);
    }

    public int updateHPresentApply(HPresentApply hPresentApply) throws Exception {
        return hPresentApplyDAO.updateHPresentApply(hPresentApply);
    }
    
    public int unBindPresentApply(HPresentApply hPresentApply) throws Exception {
    	return hPresentApplyDAO.unBindPresentApply(hPresentApply);
    }
    
    public int removeHPresentApply(HPresentApply hPresentApply) throws Exception {
        return hPresentApplyDAO.removeHPresentApply(hPresentApply);
    }
    

	public String exportExecl(String basePath,List<HPresentApply> list){
		String filename = null;
		try{
			
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMddHHmmss");
			
			filename = sf1.format(new Date())+"提现申请.xls";
			String OnputimagePath = basePath + "/upload/xls/"+filename;
			File file = new File(basePath + "/upload/xls/");
			if(!file.exists()){
				file.mkdirs();
			}
			HSSFWorkbook hssfwork = new HSSFWorkbook();
			HSSFSheet sheet = hssfwork.createSheet("提现申请表");
			hssfwork.setSheetName(0, "提现申请表" );
			HSSFCellStyle style = hssfwork.createCellStyle();
			HSSFFont font = hssfwork.createFont();
			HSSFFont headfont = hssfwork.createFont();
			style = hssfwork.createCellStyle();
			font = hssfwork.createFont();
			font.setFontHeightInPoints((short) 11);
			headfont.setFontHeightInPoints((short) 12);
			font.setFontName("宋体");
			headfont.setFontName("宋体");
			font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
			headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			sheet.setColumnWidth((short) 0, (short) 7000);
			sheet.setColumnWidth((short) 1, (short) 7000);
			sheet.setColumnWidth((short) 2, (short) 7000);
			sheet.setColumnWidth((short) 3, (short) 7000);
			sheet.setColumnWidth((short) 4, (short) 7000);
			sheet.setColumnWidth((short) 5, (short) 7000);
			sheet.setColumnWidth((short) 6, (short) 7000);
			sheet.setColumnWidth((short) 7, (short) 7000);
			sheet.setColumnWidth((short) 8, (short) 7000);
			sheet.setColumnWidth((short) 9, (short) 7000);
			sheet.setColumnWidth((short) 10, (short) 7000);
//			sheet.setColumnWidth((short) 11, (short) 7000);
			style.setFont(font);
			//生成头部
			//第一行
			HSSFRow headtwo = sheet.createRow((short) 0);
//			HSSFCell cell11 = headtwo.createCell((short)0);
//			cell11.setCellStyle(style);
////			cell11.setCellValue("批次号");
			HSSFCell cell13 = headtwo.createCell((short)0);
			cell13.setCellStyle(style);
			cell13.setCellValue("申请时间");
			HSSFCell cell21 = headtwo.createCell((short)1);
			cell21.setCellStyle(style);
			cell21.setCellValue("申请金额");
			
			
			HSSFCell cell33 = headtwo.createCell((short)2);
			cell33.setCellStyle(style);
			cell33.setCellValue("审核状态");
			HSSFCell cell41 = headtwo.createCell((short)3);
			cell41.setCellStyle(style);
			cell41.setCellValue("类型");
			HSSFCell cell31 = headtwo.createCell((short)4);
			cell31.setCellStyle(style);
			cell31.setCellValue("角色");
			
			HSSFCell cell23 = headtwo.createCell((short)5);
			cell23.setCellStyle(style);
			cell23.setCellValue("名称");
			
			
			HSSFCell cell43 = headtwo.createCell((short)6);
			cell43.setCellStyle(style);
			cell43.setCellValue("个人身份证号");
			HSSFCell cell51 = headtwo.createCell((short)7);
			cell51.setCellStyle(style);
			cell51.setCellValue("手机号");
			HSSFCell cell52 = headtwo.createCell((short)8);
			cell52.setCellStyle(style);
			cell52.setCellValue("持卡人名称");
			HSSFCell cell53 = headtwo.createCell((short)9);
			cell53.setCellStyle(style);
			cell53.setCellValue("开会行名称");
			HSSFCell cell54 = headtwo.createCell((short)10);
			cell54.setCellStyle(style);
			cell54.setCellValue("个人银行卡卡号/公司账号");
			
			for(int i=0;i<list.size();i++){
				HPresentApply apply = list.get(i);
				HSSFRow newrow = sheet.createRow((short) (i + 1));
//				HSSFCell newcell1 = newrow.createCell((short) 0);
//				newcell1.setCellStyle(style);
//				newcell1.setCellValue(apply.getBatchCode());
				
				HSSFCell newcell2 = newrow.createCell((short) 0);
				newcell2.setCellStyle(style);
				newcell2.setCellValue(sf.format(apply.getCreateTime()));
				
				HSSFCell newcell3 = newrow.createCell((short) 1);
				newcell3.setCellStyle(style);
				newcell3.setCellValue(apply.getTotalFee()+"");
				
				HSSFCell newcell6 = newrow.createCell((short) 2);
				newcell6.setCellStyle(style);
				if(apply.getStatus()==1){
					newcell6.setCellValue("已审核");
				}else{
					newcell6.setCellValue("未审核");
				}
				
				if(StringUtils.isNotBlank(apply.getTwoAgentOpenId())){
					HSSFCell newcell4 = newrow.createCell((short) 3);
					newcell4.setCellStyle(style);
					
					if(apply.getAgentTwoStyle().intValue()==1){
		        		newcell4.setCellValue("公司");
		        	}else{
		        		newcell4.setCellValue("个人");
		        	}
					
					HSSFCell newcell5 = newrow.createCell((short) 4);
					newcell5.setCellStyle(style);
	        	 	newcell5.setCellValue("代理");
	        	 	HSSFCell newcell7 = newrow.createCell((short) 5);
					newcell7.setCellStyle(style);
			        newcell7.setCellValue(apply.getTwoName());
			        HSSFCell newcell8 = newrow.createCell((short) 6);
					newcell8.setCellStyle(style);
			        newcell8.setCellValue(apply.getCard_no());
			        
			        HSSFCell newcell9 = newrow.createCell((short) 7);
					newcell9.setCellStyle(style);
					newcell9.setCellValue(apply.getTwoMobile1());
					
					HSSFCell newcell10 = newrow.createCell((short) 8);
					newcell10.setCellStyle(style);
			        newcell10.setCellValue(apply.getTwoName());
					
					HSSFCell newcell11 = newrow.createCell((short) 9);
					newcell11.setCellStyle(style);
			        newcell11.setCellValue(apply.getTwoBank_name());
					
					HSSFCell newcell12 = newrow.createCell((short) 10);
					newcell12.setCellStyle(style);
					newcell12.setCellValue(apply.getTwoBank_account());
				}else if(apply.getServicerId()!=null){
					HSSFCell newcell4 = newrow.createCell((short) 3);
					newcell4.setCellStyle(style);
		        	newcell4.setCellValue("");
					
					HSSFCell newcell5 = newrow.createCell((short) 4);
					newcell5.setCellStyle(style);
	        	 	newcell5.setCellValue("服务人员");
	        	 	HSSFCell newcell7 = newrow.createCell((short) 5);
					newcell7.setCellStyle(style);
			        newcell7.setCellValue("");;
			        HSSFCell newcell8 = newrow.createCell((short) 6);
					newcell8.setCellStyle(style);
			        newcell8.setCellValue("");
			        
			        HSSFCell newcell9 = newrow.createCell((short) 7);
					newcell9.setCellStyle(style);
					newcell9.setCellValue("");
					
					HSSFCell newcell10 = newrow.createCell((short) 8);
					newcell10.setCellStyle(style);
			        newcell10.setCellValue("");
					
					HSSFCell newcell11 = newrow.createCell((short) 9);
					newcell11.setCellStyle(style);
			        newcell11.setCellValue("");
					
					HSSFCell newcell12 = newrow.createCell((short) 10);
					newcell12.setCellStyle(style);
					newcell12.setCellValue("");
				}else{
					HSSFCell newcell4 = newrow.createCell((short) 3);
					newcell4.setCellStyle(style);
					
					if(apply.getAgentOneStyle()!=null&&apply.getAgentOneStyle().intValue()==1){
		        		newcell4.setCellValue("公司");
		        	}else{
		        		newcell4.setCellValue("个人");
		        	}
					
					HSSFCell newcell5 = newrow.createCell((short) 4);
					newcell5.setCellStyle(style);
	        	 	newcell5.setCellValue("客户经理");
	        	 	HSSFCell newcell7 = newrow.createCell((short) 5);
					newcell7.setCellStyle(style);
			        newcell7.setCellValue(apply.getOneName());
			        HSSFCell newcell8 = newrow.createCell((short) 6);
					newcell8.setCellStyle(style);
			        newcell8.setCellValue(apply.getCard_no_1());
			        
			        HSSFCell newcell9 = newrow.createCell((short) 7);
					newcell9.setCellStyle(style);
					newcell9.setCellValue(apply.getOneMobile1());
					
					HSSFCell newcell10 = newrow.createCell((short) 8);
					newcell10.setCellStyle(style);
			        newcell10.setCellValue(apply.getOneName());
					
					HSSFCell newcell11 = newrow.createCell((short) 9);
					newcell11.setCellStyle(style);
			        newcell11.setCellValue(apply.getOneBank_name());
					
					HSSFCell newcell12 = newrow.createCell((short) 10);
					newcell12.setCellStyle(style);
					newcell12.setCellValue(apply.getOneBank_account());
				}
			}
			
			FileOutputStream fOut = new FileOutputStream(OnputimagePath);
			hssfwork.write(fOut);
			fOut.flush();
			fOut.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/upload/xls/"+filename;
	}
    
}
