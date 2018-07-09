package com.hxt.wap.controller;

import com.base.utils.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hxt.hPayurlCheck.model.HPayurlCheck;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/excle")
public class ExcleManagerController {
    public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();    // gson解析对象。


    public static void main(String[] args) throws Exception {

        excleExport();
    }

    /**
     * 导出excle
     * @return url
     * @throws IOException 
     */
    @RequestMapping(value = "/exportExcle")
    public String exportExcle(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
        Map<String, Object> resultMap = new HashMap<String, Object>();//返回数据
        try{
            String owedAmmeterSum = RequestHandler.getString(request, "owedAmmeterSum");
//            String ammeterSum = RequestHandler.getString(request, "ammeterSum");
            String paysum = RequestHandler.getString(request, "paysum");
            String rowsTotal = RequestHandler.getString(request, "rowsTotal");
            String data = RequestHandler.getString(request, "data");
            List list = gson.fromJson(data,List.class);
            StringBuffer informationSb = new StringBuffer();
            DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
            String nowDate = df.format(new Date());
            
            int ammeterSum = 0;
            
            Long paysum1 = 0L;
            
//            String informationStr = "截止到 "+nowDate+"，您登记的电表共有 "+ammeterSum+" 个，其中 "+owedAmmeterSum+" 个电表欠费共计："+totalFeeStr1+" 元。\n" +
//                    "请您在完成制单后，及时提示复核人员登录网银复核订单，超过当日再复核订单可能造成交费失败!\n" +
//                    "具体的电表欠费信息如下:";

            int currentRow = 0;//当前行数
            int currentRank = 0;//当前列数
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("单位电表费用信息");
            sheet.autoSizeColumn(1, true);
            //欠费综合信息样式
            HSSFCellStyle informationStyle = wb.createCellStyle(); // 样式对象
            //为了能够使用换行，您需要设置单元格的样式 wrap=true
            informationStyle.setWrapText(true);
            informationStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
            informationStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 水平

            //子单位信息样式
            HSSFCellStyle subStyle = wb.createCellStyle(); // 样式对象
            //为了能够使用换行，您需要设置单元格的样式 wrap=true
            subStyle.setWrapText(true);
            subStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
            subStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 水平

            HSSFCellStyle centerStyle = wb.createCellStyle(); // 样式对象
            //为了能够使用换行，您需要设置单元格的样式 wrap=true
            centerStyle.setWrapText(true);
            centerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
            centerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平


            //字体
            HSSFFont font11 = wb.createFont();
            font11.setFontName("仿宋_GB2312");
            font11.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
            font11.setFontHeightInPoints((short) 11);
            centerStyle.setFont(font11);
            //字体
            HSSFFont font12 = wb.createFont();
            font12.setFontName("仿宋_GB2312");
            font12.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
            font12.setFontHeightInPoints((short) 12);
            subStyle.setFont(font12);
            //字体
            HSSFFont font13 = wb.createFont();
            font13.setFontName("仿宋_GB2312");
            font13.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
            font13.setFontHeightInPoints((short) 13);
            informationStyle.setFont(font13);

            //设置边框
            HSSFCellStyle cellStyle = wb.createCellStyle(); // 样式对象
            cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
            cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
            cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
            cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框


            sheet.setColumnWidth(0,"序号".getBytes().length*3*200);
            sheet.setColumnWidth(1,"业务类型".getBytes().length*3*200);
            sheet.setColumnWidth(2,"客户编号".getBytes().length*3*200);
            sheet.setColumnWidth(3,"用电客户名称".getBytes().length*3*600);
            sheet.setColumnWidth(4,"欠费金额".getBytes().length*3*600);


            //第一行 电费欠费信息 占用2行
            HSSFRow row = sheet.createRow((short) currentRow);
            row.setHeightInPoints(60);
            currentRow = currentRow+2;
            // 单元格合并
            // 四个参数分别是：起始行，起始列，结束行，结束列
            sheet.addMergedRegion(new Region(0, (short)0, currentRow-1,
                    (short) 4));
            HSSFCell cell = row.createCell((short) 0);
            
            cell.setCellStyle(informationStyle); // 样式
            //预收
            BigDecimal yuFee = new BigDecimal("0");
            String yuNo = "";
            //子单位电费信息开始 循环遍历
            for (Object obj:list) {
                String invoice_title = "";
                String consignee = "";
                String sub_name = "";
                String c_name = "";
                String consignee_phone = "";
                String consignee_address = "";
                Map map = (Map) obj;
                invoice_title = map.get("invoice_title").toString();
                consignee = map.get("consignee").toString();
                sub_name = map.get("sub_name").toString();
                c_name = map.get("c_name").toString();
                consignee_phone = map.get("consignee_phone").toString();
                consignee_address = map.get("consignee_address").toString();
//                String subInfo = "子单位名称：" + c_name + "　发票台头：" + invoice_title + "　发票收件人：" + consignee + "\n" +
//                        "手机：" + consignee_phone + "　收件人地址：" + consignee_address;
                String subInfo = "分组名称：" + sub_name + "　发票收件人：" + consignee + "\n" +
                		"手机：" + consignee_phone + "　收件人地址：" + consignee_address;

                //子单位信息 占用2行
                HSSFRow row1 = sheet.createRow((short) currentRow);
                row1.setHeightInPoints(45);
                // 四个参数分别是：起始行，起始列，结束行，结束列
                sheet.addMergedRegion(new Region(currentRow, (short) 0, currentRow + 1, (short) 4));
                currentRow = currentRow + 2;
                HSSFCell fapiaoCell = row1.createCell((short) 0);
                fapiaoCell.setCellValue(subInfo); // 跨单元格显示的数据
                fapiaoCell.setCellStyle(subStyle); // 样式

                List ammetrList = (List)map.get("ammeters");
                //电表字段
                HSSFRow row2 = sheet.createRow((short) currentRow);
                currentRow = currentRow + 1;
                HSSFCell xh = row2.createCell((short) 0);
                xh.setCellValue("序号"); // 跨单元格显示的数据
                xh.setCellStyle(centerStyle); // 样式
                HSSFCell ywlx = row2.createCell((short) 1);
                ywlx.setCellValue("业务类型"); // 跨单元格显示的数据
                ywlx.setCellStyle(centerStyle); // 样式
                HSSFCell khbh = row2.createCell((short) 2);
                khbh.setCellValue("缴费号"); // 跨单元格显示的数据
                khbh.setCellStyle(centerStyle); // 样式
                HSSFCell ydkhmc = row2.createCell((short) 3);
                ydkhmc.setCellValue("用电客户名称"); // 跨单元格显示的数据
                ydkhmc.setCellStyle(centerStyle); // 样式
                HSSFCell qfje = row2.createCell((short) 4);
                qfje.setCellValue("欠费金额"); // 跨单元格显示的数据
                qfje.setCellStyle(centerStyle); // 样式

                //遍历电表信息
                int xhNum = 1;
                for(Object ammeterObj:ammetrList){
                	
                	ammeterSum = ammeterSum + 1;
                	
                    Map ammeterMap = (Map)ammeterObj;
                    String ammeter_type = ammeterMap.get("ammeter_type")==null?"":ammeterMap.get("ammeter_type").toString();
                    if("B".equals(ammeter_type)){
                        ammeter_type = "智能电";
                    }else {
                        ammeter_type = "抄表电";
                    }
                    String ammeter_no = ammeterMap.get("ammeter_no")==null?"":ammeterMap.get("ammeter_no").toString();
                    String ammeter_name = ammeterMap.get("ammeter_name")==null?"":ammeterMap.get("ammeter_name").toString();
                    String totalFee = ammeterMap.get("totalFee")==null?"":ammeterMap.get("totalFee").toString();
                    
                    double f21 = 0.0;
                    if(StringUtils.isNotBlank(totalFee)){
                    	paysum1 = paysum1 + Long.valueOf(totalFee.substring(0, totalFee.indexOf(".")));
                    	BigDecimal bg11 = new BigDecimal(Double.valueOf(totalFee));
         		        f21 = bg11.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    }
                   
    		        
    		        int fee = (int)f21;
                    
                    String totalFeeStr = null;
                    if(StringUtils.isNotBlank(fee+"")){
                    	if((fee+"").length()>2){
            				int fen = fee%100;
            				if(fen>0&&fen<10){
            					totalFeeStr = fee/100 + ".0" + fen;
            				}else if(fen>=10){
            					totalFeeStr = fee/100 + "." + fen;
            				}else{
            					totalFeeStr = fee/100 + ".00";
            				}
            			}else if((fee+"").length()==1){
            				totalFeeStr = "0.0"+fee;
            			}else{
            				totalFeeStr = "0."+fee;
            			}
                    }else{
                    	totalFeeStr = "0.00";
                    }
                    BigDecimal tmpTotal = new BigDecimal(totalFeeStr);
                    if((tmpTotal.compareTo(BigDecimal.ZERO)==1)&&tmpTotal.divideAndRemainder(new BigDecimal("100"))[1].setScale(2,BigDecimal.ROUND_HALF_DOWN).equals((new BigDecimal("0").setScale(2,BigDecimal.ROUND_HALF_DOWN)))){
                    	yuFee = yuFee.add(tmpTotal);
                    	yuNo +=ammeter_no+",";
                    }
//                    String totalFeeStr = ammeterMap.get("totalFeeStr")==null?"":ammeterMap.get("totalFeeStr").toString();
                    
                    
                    HSSFRow row3 = sheet.createRow((short) currentRow);
                    currentRow = currentRow + 1;
                    HSSFCell xhValue = row3.createCell((short) 0);
                    xhValue.setCellValue(xhNum++); // 跨单元格显示的数据
                    xhValue.setCellStyle(centerStyle); // 样式
                    HSSFCell ywlxValue = row3.createCell((short) 1);
                    ywlxValue.setCellValue(ammeter_type); // 跨单元格显示的数据
                    ywlxValue.setCellStyle(centerStyle); // 样式
                    HSSFCell khbhValue = row3.createCell((short) 2);
                    khbhValue.setCellValue(ammeter_no); // 跨单元格显示的数据
                    khbhValue.setCellStyle(centerStyle); // 样式
                    HSSFCell ydkhmcValue = row3.createCell((short) 3);
                    ydkhmcValue.setCellValue(ammeter_name); // 跨单元格显示的数据
                    ydkhmcValue.setCellStyle(centerStyle); // 样式
                    HSSFCell qfjeValue = row3.createCell((short) 4);
                    qfjeValue.setCellValue(totalFeeStr); // 跨单元格显示的数据
                    qfjeValue.setCellStyle(centerStyle); // 样式
                }

            }
            
            
            String totalFeeStr1 = null;
            
            paysum = String.valueOf(paysum1);
            
            if(StringUtils.isNotBlank(paysum)){
            	if(paysum.length()>2){
    				int fen = Integer.valueOf(paysum)%100;
    				if(fen>0&&fen<10){
    					totalFeeStr1 = Integer.valueOf(paysum)/100 + ".0" + fen;
    				}else if(fen>=10){
    					totalFeeStr1 = Integer.valueOf(paysum)/100 + "." + fen;
    				}else{
    					totalFeeStr1 = Integer.valueOf(paysum)/100 + ".00";
    				}
    			}else if(paysum.length()==1){
    				totalFeeStr1 = "0.0"+paysum;
    			}else{
    				totalFeeStr1 = "0."+paysum;
    			}
            }else{
            	totalFeeStr1 = "0.00";
            }
            
            String informationStr = "您绑定了"+ammeterSum+"个抄表缴费号，应该缴费金额为："+totalFeeStr1+"元";
            if(StringUtils.isNotBlank(yuNo)){
            	informationStr+=",您的缴费号:"+yuNo+"目前的欠费是预收费，共计："+yuFee.setScale(2,BigDecimal.ROUND_HALF_DOWN).toString()+"元";
            }
            cell.setCellValue(informationStr); // 跨单元格显示的数据
            
            String contextPath = request.getSession().getServletContext().getRealPath("/");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
			String datedir = sdf.format(new Date());
			String realPath = "/" + "upload" + "/" + datedir;
			String uuid = UUID.randomUUID().toString().replace("-", "");
			if(FileUploadConstants.mkdirs(contextPath+realPath)){
				 wb.write(new FileOutputStream(contextPath + realPath + uuid +".xls"));
			}
            resultMap.put("status", "success");
            resultMap.put("url", realPath + uuid +".xls");
            resultMap.put("filename", uuid +".xls");
            resultMap.put("msg", "导出excle成功!");

        }catch(Exception e){
            e.printStackTrace();
            resultMap.put("status", "fail");
            resultMap.put("msg", "导出excle失败!");
        }
        ResponseUtil.responseText(response, gson.toJson(resultMap));
        return null;
    }


    public static void excleExport() {

        String desc = "截止到 2015年12月08日，您登记的电表共有 13 个，其中 9 个电表欠费共计：50271.93 元。\n" +
                "超过本日缴费有可能会产生滞纳金，请您尽快在当天提交缴费申请并在21：00点前完成付款。\n" +
                "具体的电表欠费信息如下:";
        String fapiao = "子单位名称：北京　发票台头：北京易付通金服技术股份有限公司　发票收件人：李总\n" +
                "手机：13800138000　收件人地址：北京易付通金服技术股份有限公司";

        int currentRow = 0;//当前行数
        int currentRank = 0;//当前列数
        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("电表信息");
            sheet.autoSizeColumn(1, true);
            //欠费综合信息样式
            HSSFCellStyle informationStyle = wb.createCellStyle(); // 样式对象
            //为了能够使用换行，您需要设置单元格的样式 wrap=true
            informationStyle.setWrapText(true);
            informationStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
            informationStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 水平

            //子单位信息样式
            HSSFCellStyle subStyle = wb.createCellStyle(); // 样式对象
            //为了能够使用换行，您需要设置单元格的样式 wrap=true
            subStyle.setWrapText(true);
            subStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
            subStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 水平

            HSSFCellStyle centerStyle = wb.createCellStyle(); // 样式对象
            //为了能够使用换行，您需要设置单元格的样式 wrap=true
            centerStyle.setWrapText(true);
            centerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
            centerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平


            //字体
            HSSFFont font11 = wb.createFont();
            font11.setFontName("仿宋_GB2312");
            font11.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
            font11.setFontHeightInPoints((short) 11);
            centerStyle.setFont(font11);
            //字体
            HSSFFont font12 = wb.createFont();
            font12.setFontName("仿宋_GB2312");
            font12.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
            font12.setFontHeightInPoints((short) 12);
            subStyle.setFont(font12);
            //字体
            HSSFFont font13 = wb.createFont();
            font13.setFontName("仿宋_GB2312");
            font13.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
            font13.setFontHeightInPoints((short) 13);
            informationStyle.setFont(font13);

            //设置边框
            HSSFCellStyle cellStyle = wb.createCellStyle(); // 样式对象
            cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
            cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
            cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
            cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

            //第一行 电费欠费信息 占用2行
            HSSFRow row = sheet.createRow((short) currentRow);
            row.setHeightInPoints(50);
            currentRow = currentRow+2;
            // 单元格合并
            // 四个参数分别是：起始行，起始列，结束行，结束列
            sheet.addMergedRegion(new Region(0, (short)0, currentRow-1,
                    (short) 4));
            HSSFCell cell = row.createCell((short) 0);
            cell.setCellValue(desc); // 跨单元格显示的数据
            cell.setCellStyle(informationStyle); // 样式


            //子单位电费信息开始 循环遍历

            for (int i=0;i<=3;i++){
                //子单位信息 占用2行
                HSSFRow row1 = sheet.createRow((short) currentRow);
                row1.setHeightInPoints(40);
                // 四个参数分别是：起始行，起始列，结束行，结束列
                sheet.addMergedRegion(new Region(currentRow, (short) 0, currentRow+1, (short) 4));
                currentRow = currentRow+2;
                HSSFCell fapiaoCell = row1.createCell((short) 0);
                fapiaoCell.setCellValue(fapiao); // 跨单元格显示的数据
                fapiaoCell.setCellStyle(subStyle); // 样式

                //遍历电表信息
                for(int j=0;j<=4;j++){
                    //电表信息开始
                    if(j==0){
                        HSSFRow row2 = sheet.createRow((short) currentRow);
                        currentRow = currentRow+1;
                        HSSFCell xh = row2.createCell((short) 0);
                        xh.setCellValue("序号"); // 跨单元格显示的数据
                        xh.setCellStyle(centerStyle); // 样式
                        HSSFCell ywlx = row2.createCell((short) 1);
                        ywlx.setCellValue("业务类型"); // 跨单元格显示的数据
                        ywlx.setCellStyle(centerStyle); // 样式
                        HSSFCell khbh = row2.createCell((short) 2);
                        khbh.setCellValue("客户编号"); // 跨单元格显示的数据
                        khbh.setCellStyle(centerStyle); // 样式
                        HSSFCell ydkhmc = row2.createCell((short) 3);
                        ydkhmc.setCellValue("用电客户名称"); // 跨单元格显示的数据
                        ydkhmc.setCellStyle(centerStyle); // 样式
                        HSSFCell qfje = row2.createCell((short) 4);
                        qfje.setCellValue("欠费金额"); // 跨单元格显示的数据
                        qfje.setCellStyle(centerStyle); // 样式

                    }else{



                    }

                }

            }





            sheet.setColumnWidth(0,"序号".getBytes().length*3*128);
            sheet.setColumnWidth(1,"业务类型".getBytes().length*3*128);
            sheet.setColumnWidth(2,"客户编号".getBytes().length*3*128);
            sheet.setColumnWidth(3,"用电客户名称".getBytes().length*3*128);
            sheet.setColumnWidth(4,"欠费金额".getBytes().length*3*128);



//            int num = 0;
//            for (int i = 0; i < 9; i++) { // 循环9次，每一次都要跨单元格显示
//                // 计算从那个单元格跨到那一格
//                int celln = 0;
//                int celle = 0;
//                if (i == 0) {
//                    celln = 0;
//                    celle = 1;
//                } else {
//                    celln = (i * 2);
//                    celle = (i * 2 + 1);
//                }
//                // 单元格合并
//                // 四个参数分别是：起始行，起始列，结束行，结束列
//                sheet.addMergedRegion(new Region(0, (short) (celln + 1), 0,
//                        (short) (celle + 1)));
//                HSSFCell cell = row.createCell((short) (celln + 1));
//                cell.setCellValue("merging" + i); // 跨单元格显示的数据
//                cell.setCellStyle(style); // 样式
//                // 不跨单元格显示的数据，如：分两行，上一行分别两格为一格，下一行就为两格，“数量”，“金额”
//                HSSFCell cell1 = row2.createCell((short) celle);
//                HSSFCell cell2 = row2.createCell((short) (celle + 1));
////                cell1.setEncoding(HSSFCell.ENCODING_UTF_16);
//                cell1.setCellValue("数量");
//                cell1.setCellStyle(style);
////                cell2.setEncoding(HSSFCell.ENCODING_UTF_16);
//                cell2.setCellValue("金额");
//                cell2.setCellStyle(style);
//                num++;
//            }
            // 在后面加上合计百分比
            // 合计 在最后加上，还要跨一个单元格
//            sheet.addMergedRegion(new Region(0, (short) (2 * num + 1), 0,
//                    (short) (2 * num + 2)));
//            HSSFCell cell = row.createCell((short) (2 * num + 1));
////            cell.setEncoding(HSSFCell.ENCODING_UTF_16);
//            cell.setCellValue("合计");
//            cell.setCellStyle(style);
//            HSSFCell cell1 = row2.createCell((short) (2 * num + 1));
//            HSSFCell cell2 = row2.createCell((short) (2 * num + 2));
////            cell1.setEncoding(HSSFCell.ENCODING_UTF_16);
//            cell1.setCellValue("数量");
//            cell1.setCellStyle(style);
////            cell2.setEncoding(HSSFCell.ENCODING_UTF_16);
//            cell2.setCellValue("金额");
//            cell2.setCellStyle(style);
//            // 百分比 同上
//            sheet.addMergedRegion(new Region(0, (short) (2 * num + 3), 0,
//                    (short) (2 * num + 4)));
//            HSSFCell cellb = row.createCell((short) (2 * num + 3));
////            cellb.setEncoding(HSSFCell.ENCODING_UTF_16);
//
//            cellb.setCellValue("百分比");
//            cellb.setCellStyle(style);
//
//            HSSFCell cellb1 = row2.createCell((short) (2 * num + 3));
//            HSSFCell cellb2 = row2.createCell((short) (2 * num + 4));
////            cellb1.setEncoding(HSSFCell.ENCODING_UTF_16);
//            cellb1.setCellValue("数量");
//            cellb1.setCellStyle(style);
////            cellb2.setEncoding(HSSFCell.ENCODING_UTF_16);
//            cellb2.setCellValue("金额");
//            cellb2.setCellStyle(style);
            /***这里是问题的关键，将这个工作簿写入到一个流中就可以输出相应的名字，这里需要写路径就ok了。
             FileOutputStream fileOut = new FileOutputStream("workbook.xls");
             wb.write(fileOut);
             fileOut.close();
              **/


            /**第二种是输出到也面中的excel名称
              * pName="栏目统计表"; 
             response.reset(); 
             response.setContentType("application/x-msdownload"); 
             response.setHeader("Content-Disposition","attachment; filename="+new String(pName.getBytes("gb2312"),"ISO-8859-1")+".xls"); 
             ServletOutputStream outStream=null; 

             try{ 
              outStream = response.getOutputStream(); 
              wb.write(outStream); 
             }catch(Exception e) 
             { 
             e.printStackTrace(); 
             }finally{ 
              outStream.close(); 
             } 
              * */
            wb.write(new FileOutputStream("/Users/coolme/Downloads/excle.xls"));
            System.out.print("OK");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void excleprots(){
        //内存中
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle cellStyle = wb.createCellStyle();

        HSSFSheet sheet = wb.createSheet("电表信息");

        String desc = "截止到 2015年12月08日，您登记的电表共有 13 个，其中 9 个电表欠费共计：50271.93 元。\n" +
                "超过本日缴费有可能会产生滞纳金，请您尽快在当天提交缴费申请并在21：00点前完成付款。\n" +
                "具体的电表欠费信息如下:";
        for (int i = 0; i <= 3; i++) {
            if (i == 0) {//第一行数据
                //创建行
                HSSFRow row = sheet.createRow(i);
                row.createCell(0);
                row.createCell(1);
                row.createCell(2);
                row.createCell(3);
                row.createCell(4);
                row.createCell(5);
                sheet.autoSizeColumn((short) 0); //调整第一列宽度
                sheet.autoSizeColumn((short) 1); //调整第一列宽度
                sheet.autoSizeColumn((short) 2); //调整第一列宽度
                sheet.autoSizeColumn((short) 3); //调整第一列宽度
                sheet.autoSizeColumn((short) 4); //调整第一列宽度
                sheet.autoSizeColumn((short) 5); //调整第一列宽度
            }
            if (i == 1) {
                //创建行
                HSSFRow row = sheet.createRow(i);
                HSSFCell cell = row.createCell(1);
                cell.setCellValue(new HSSFRichTextString(desc));

//                sheet.autoSizeColumn((short)0,true); //调整第一列宽度
            }


        }


//        row.createCell(1).setCellValue(desc);
//        row.createCell(2).setCellValue(new Date());
//        row.createCell(3).setCellValue(1234567890.9870654f);
//        row.createCell(4).setCellValue(new HSSFRichTextString(desc));

        //合並單元格,下標從0開始
        sheet.addMergedRegion(new CellRangeAddress(1, (short) 1, 1, (short) 5));
        //设置边框
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

//        //格式化数据
//        HSSFDataFormat format = wb.createDataFormat();//创建格式对象
//        HSSFCellStyle style = wb.createCellStyle();//创建样式对象
//
//        //设置格式
//        style.setDataFormat(format.getFormat("yyyy-MM-dd hh:mm:ss"));
//        cell = row.getCell(1);
//        cell.setCellStyle(style);//对cell应用样式
//        row.getCell(2).setCellStyle(style);
//
//        //设置列宽
//        sheet.setColumnWidth(1, 5000);//单位:1/20
//        sheet.autoSizeColumn(2);
//
//        //数字格式化???
//        style = wb.createCellStyle();
//        style.setDataFormat(format.getFormat("#,###.0000"));
//        row.getCell(3).setCellStyle(style);
//
//        //文本自动换行
//        sheet.setColumnWidth(4, 5000);
//        style = wb.createCellStyle();
//        style.setWrapText(true);//回绕文本
//        row.getCell(4).setCellStyle(style);
//
//        //设置文本对齐方式
//        sheet.setColumnWidth(0, 5000);
//        row = sheet.createRow(1);
//        row.createCell(0).setCellValue("左上");
//        row.createCell(1).setCellValue("中中");
//        row.createCell(2).setCellValue("右下");
//
//        //对齐方式--左上
//        style = wb.createCellStyle();
//        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);//左对齐
//        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);//上对齐
//        row.getCell(0).setCellStyle(style);
//
//        //对齐方式--中中
//        style = wb.createCellStyle();
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//左对齐
//        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上对齐
//        row.getCell(1).setCellStyle(style);
//
//        //对齐方式--右下
//        style = wb.createCellStyle();
//        style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);//左对齐
//        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);//上对齐
//        row.getCell(2).setCellStyle(style);
//        //设置行高
//        row.setHeightInPoints(50);
//
//        //设置字体
//        style = row.getCell(1).getCellStyle();
//        HSSFFont font = wb.createFont();
//        font.setFontName("宋体");
//        font.setFontHeightInPoints((short)18);
//        font.setColor(HSSFColor.RED.index);
//        style.setFont(font);
//
//        //文本旋转
//        style.setRotation((short)-30);
//
//        //设置边框
//        row = sheet.createRow(2);
//        cell = row.createCell(0);
//        style = wb.createCellStyle();
//        style.setBorderTop(HSSFCellStyle.BORDER_DASH_DOT_DOT);
//        style.setTopBorderColor(HSSFColor.BLUE.index);
//        cell.setCellStyle(style);
//
//        //计算列
//        row = sheet.createRow(3);
//        row.createCell(0).setCellValue(20);
//        row.createCell(1).setCellValue(34.78);
//        row.createCell(2).setCellValue(45.98);
//        row.createCell(3).setCellFormula("sum(A4:C4)");
//
//        //整体移动行
//        sheet.shiftRows(1, 3, 2);
//
//        //拆分窗格
//        //1000:左侧窗格的宽度
//        //2000:上侧窗格的高度
//        //3:右侧窗格开始显示的列的索引
//        //4:下侧窗格开始显示的行的索引
//        //1:激活的哪个面板区
//        sheet.createSplitPane(1000, 2000, 3, 4, 1);
//
//        //冻结窗口
//        sheet.createFreezePane(1, 2, 3, 4);
        try {
            wb.write(new FileOutputStream("/Users/coolme/Downloads/poi.xls"));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            HSSFWorkbook wb = new HSSFWorkbook();
//            HSSFSheet sheet = wb.createSheet("new   sheet");
//            HSSFCellStyle style = wb.createCellStyle(); // 样式对象
//
//            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
//            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
//            HSSFRow row = sheet.createRow((short) 0);
//            HSSFRow row2 = sheet.createRow((short) 1);
//
//            sheet.addMergedRegion(new Region(0, (short) 0, 1, (short) 0));
//            HSSFCell ce = row.createCell((short) 0);
//            ce.setEncoding(HSSFCell.ENCODING_UTF_16);// 中文处理
//            ce.setCellValue("项目\\日期"); // 表格的第一行第一列显示的数据
//            ce.setCellStyle(style); // 样式，居中
//            int num = 0;
//            for (int i = 0; i < 9; i++) { // 循环9次，每一次都要跨单元格显示
//                // 计算从那个单元格跨到那一格
//                int celln = 0;
//                int celle = 0;
//                if (i == 0) {
//                    celln = 0;
//                    celle = 1;
//                } else {
//                    celln = (i * 2);
//                    celle = (i * 2 + 1);
//                }
//                // 单元格合并
//                // 四个参数分别是：起始行，起始列，结束行，结束列
//                sheet.addMergedRegion(new Region(0, (short) (celln + 1), 0,
//                        (short) (celle + 1)));
//                HSSFCell cell = row.createCell((short) (celln + 1));
//                cell.setCellValue("merging" + i); // 跨单元格显示的数据
//                cell.setCellStyle(style); // 样式
//                // 不跨单元格显示的数据，如：分两行，上一行分别两格为一格，下一行就为两格，“数量”，“金额”
//                HSSFCell cell1 = row2.createCell((short) celle);
//                HSSFCell cell2 = row2.createCell((short) (celle + 1));
//                cell1.setEncoding(HSSFCell.ENCODING_UTF_16);
//                cell1.setCellValue("数量");
//                cell1.setCellStyle(style);
//                cell2.setEncoding(HSSFCell.ENCODING_UTF_16);
//                cell2.setCellValue("金额");
//                cell2.setCellStyle(style);
//                num++;
//            }
//
//            // 在后面加上合计百分比
//
//            // 合计 在最后加上，还要跨一个单元格
//            sheet.addMergedRegion(new Region(0, (short) (2 * num + 1), 0,
//                    (short) (2 * num + 2)));
//            HSSFCell cell = row.createCell((short) (2 * num + 1));
//            cell.setEncoding(HSSFCell.ENCODING_UTF_16);
//            cell.setCellValue("合计");
//            cell.setCellStyle(style);
//            HSSFCell cell1 = row2.createCell((short) (2 * num + 1));
//            HSSFCell cell2 = row2.createCell((short) (2 * num + 2));
//            cell1.setEncoding(HSSFCell.ENCODING_UTF_16);
//            cell1.setCellValue("数量");
//            cell1.setCellStyle(style);
//            cell2.setEncoding(HSSFCell.ENCODING_UTF_16);
//            cell2.setCellValue("金额");
//            cell2.setCellStyle(style);
//
//            // 百分比 同上
//            sheet.addMergedRegion(new Region(0, (short) (2 * num + 3), 0,
//                    (short) (2 * num + 4)));
//            HSSFCell cellb = row.createCell((short) (2 * num + 3));
//            cellb.setEncoding(HSSFCell.ENCODING_UTF_16);
//
//            cellb.setCellValue("百分比");
//            cellb.setCellStyle(style);
//
//            HSSFCell cellb1 = row2.createCell((short) (2 * num + 3));
//            HSSFCell cellb2 = row2.createCell((short) (2 * num + 4));
//            cellb1.setEncoding(HSSFCell.ENCODING_UTF_16);
//            cellb1.setCellValue("数量");
//            cellb1.setCellStyle(style);
//            cellb2.setEncoding(HSSFCell.ENCODING_UTF_16);
//            cellb2.setCellValue("金额");
//            cellb2.setCellStyle(style);
//
//            /***这里是问题的关键，将这个工作簿写入到一个流中就可以输出相应的名字，这里需要写路径就ok了。
//             FileOutputStream fileOut = new FileOutputStream("workbook.xls");
//             wb.write(fileOut);
//             fileOut.close();
//             **/
//
//
//            /**第二种是输出到也面中的excel名称
//             * pName="栏目统计表";
//             response.reset();
//             response.setContentType("application/x-msdownload");
//             response.setHeader("Content-Disposition","attachment; filename="+new String(pName.getBytes("gb2312"),"ISO-8859-1")+".xls");
//             ServletOutputStream outStream=null;
//
//             try{
//             outStream = response.getOutputStream();
//             wb.write(outStream);
//             }catch(Exception e)
//             {
//             e.printStackTrace();
//             }finally{
//             outStream.close();
//             }
//             * */
//            System.out.print("OK");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
    }
}

