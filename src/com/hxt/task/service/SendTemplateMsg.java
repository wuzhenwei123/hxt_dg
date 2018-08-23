package com.hxt.task.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.base.utils.SendMsgUtil;
import com.hxt.hAmmeterInfo.model.HAmmeterInfo;
import com.hxt.hAmmeterInfo.service.HAmmeterInfoService;
import com.hxt.hCommon.service.HCommonService;
import com.hxt.hCompany.model.HCompany;
import com.hxt.hCompany.service.HCompanyService;
import com.hxt.hDispatchRecord.service.HDispatchRecordService;
import com.hxt.hDispatchRecordC.model.HDispatchRecordC;
import com.hxt.hDispatchRecordC.service.HDispatchRecordCService;
import com.hxt.hSubCompany.service.HSubCompanyService;

@Component
public class SendTemplateMsg {
	
	Logger log = Logger.getLogger(SendTemplateMsg.class);
	
	@Autowired
	private TaskExecutor taskExecutor;
	@Autowired
	private HSubCompanyService hsubcompanyService = null;
	@Autowired
	private HAmmeterInfoService hammeterinfoService = null;
	@Autowired
	private HCommonService hCommonService = null;
	@Autowired
	private HDispatchRecordService hdispatchrecordService = null;
	@Autowired
	private HCompanyService hcompanyService = null;
	@Autowired
	private HDispatchRecordCService hdispatchrecordcService = null;

	public void filesMng(HCompany hCompany){
		this.taskExecutor.execute(new CutPayLogThread(hCompany));
	}
	
	
	private class CutPayLogThread implements Runnable {
		
		private HCompany com;

		private CutPayLogThread(HCompany hCompany) {
			super();
			this.com = hCompany;
		}

		@Override
		public void run() {
			try {
				SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
				//遍历每个单位
				Integer did = 0;
				//查询单位下面电表
				Integer xTotalFee = 0;//欠费总金额
				String ammeter_no = null;//电表号
				String tel = null;
				boolean b = true;
				boolean b1 = true;
				HAmmeterInfo hAmmeterInfo = new HAmmeterInfo();
				hAmmeterInfo.setPay_status("1");
				hAmmeterInfo.setC_status(1);
				hAmmeterInfo.setC_verify_status(1);
				hAmmeterInfo.setC_id(com.getId());
				hAmmeterInfo.setDelete_state(1);
				tel = com.getContact_phone();
				System.out.println("------tel--------"+tel);
				//查询有效的电表号
				int totalAmmeterInfo = 0;
				int moenyAmmeterInfo = 0;
				int zehgnshu = 0;
				List<HAmmeterInfo> listHAmmeterInfo = hammeterinfoService.getHAmmeterInfoBaseList(hAmmeterInfo);
				if(listHAmmeterInfo!=null&&listHAmmeterInfo.size()>0){
					totalAmmeterInfo = listHAmmeterInfo.size();
					for(HAmmeterInfo info:listHAmmeterInfo){//对每个有效的电表号进行调单
						Thread.sleep(5000);
						JSONObject json = hCommonService.hXTServiceQuery(info.getAmmeter_no(), "127.0.0.1");
						if(json.getString("totalFee")!=null){
							Integer totalFeeStr = Integer.valueOf(json.getString("totalFeeStr"));//单位是分
							log.info("-------totalFeeStr---------->"+totalFeeStr);
							log.info("-------totalFeeStr---------->"+totalFeeStr%10000);
							if(totalFeeStr>0&&totalFeeStr%10000==0){//是否是100元的整数倍,如果是，不发送短息
								zehgnshu = zehgnshu + 1;
							}else{
								xTotalFee = xTotalFee + totalFeeStr;
								if(totalFeeStr>0){
									moenyAmmeterInfo  = moenyAmmeterInfo + 1;
								}
							}
						}
						if(ammeter_no==null){
							ammeter_no = info.getAmmeter_no();
						}else{
							ammeter_no = ammeter_no + "," + info.getAmmeter_no();
						}
					}
					
					if(zehgnshu==totalAmmeterInfo){//所有电表都是100的整数倍，不发
						b = false;
					}
				}else{//不存在有效电表号，不发送短信
					b = false;
				}
				
				
				if(b){//公司存在有效的电表号，并且所有电表号欠费金额都不是100元的整数倍
					//查询数据库是否有该公司的记录
					HDispatchRecordC hDispatchRecordC = new HDispatchRecordC();
					hDispatchRecordC.setC_id(com.getId());
					hDispatchRecordC.setStatus(0);
					hDispatchRecordC.setContact_phone(com.getContact_phone());
					hDispatchRecordC = hdispatchrecordcService.getHDispatchRecordC(hDispatchRecordC);
					if(hDispatchRecordC!=null){//有记录
						Long totalFee = hDispatchRecordC.getTotalFee();//记录总金额
						log.info("-------xTotalFee---------->"+xTotalFee);//本次欠费总金额
						log.info("-------totalFee---------->"+totalFee);
						if(xTotalFee-totalFee==0){//记录总金额和本次调单欠费总金额相等
							Calendar ca = Calendar.getInstance();
							ca.setTime(hDispatchRecordC.getCreateTime());
							ca.add(Calendar.DATE, 7);
							Date time = ca.getTime();
							if(sf1.format(new Date()).equals(sf1.format(time))){//判断是否七天，发送短信通知
								//把上次记录修改状态
								hDispatchRecordC.setStatus(1);
								hDispatchRecordC.setUpdateTime(new Date());
								hdispatchrecordcService.updateHDispatchRecordC(hDispatchRecordC);
								//添加本次记录
								HDispatchRecordC hDispatchRecordC1 = new HDispatchRecordC();
								hDispatchRecordC1.setC_id(com.getId());
								hDispatchRecordC1.setStatus(0);
								hDispatchRecordC1.setContact_phone(com.getContact_phone());
								hDispatchRecordC1.setCreateTime(new Date());
								hDispatchRecordC1.setTotalFee(Long.valueOf(xTotalFee));
								hDispatchRecordC1.setAmmeter_no(ammeter_no);
								did = hdispatchrecordcService.insertHDispatchRecordC(hDispatchRecordC1);
							}else{//不是七天。不发
								b1 = false;
							}
						}else{//记录总金额和本次调单欠费总金额不相同
							if(xTotalFee>0){//本次有欠费，发送短息
								HDispatchRecordC hDispatchRecordC1 = new HDispatchRecordC();
								hDispatchRecordC1.setC_id(com.getId());
								hDispatchRecordC1.setStatus(0);
								hDispatchRecordC1.setContact_phone(com.getContact_phone());
								hDispatchRecordC1.setCreateTime(new Date());
								hDispatchRecordC1.setTotalFee(Long.valueOf(xTotalFee));
								hDispatchRecordC1.setAmmeter_no(ammeter_no);
								did = hdispatchrecordcService.insertHDispatchRecordC(hDispatchRecordC1);
								//把上次记录修改状态
								hDispatchRecordC.setStatus(1);
								hDispatchRecordC.setUpdateTime(new Date());
								hdispatchrecordcService.updateHDispatchRecordC(hDispatchRecordC);
							}else{//之前有欠费记录，本次不欠费。将记录修改状态。不发送短息
								b1 = false;
								hDispatchRecordC.setStatus(1);
								hDispatchRecordC.setUpdateTime(new Date());
								hdispatchrecordcService.updateHDispatchRecordC(hDispatchRecordC);
							}
						}
					}else{//不存在记录
						if(Long.valueOf(xTotalFee)>0){//本次有欠费金额，存入库，发送短信
							HDispatchRecordC hDispatchRecordC1 = new HDispatchRecordC();
							hDispatchRecordC1.setC_id(com.getId());
							hDispatchRecordC1.setStatus(0);
							hDispatchRecordC1.setContact_phone(com.getContact_phone());
							hDispatchRecordC1.setCreateTime(new Date());
							hDispatchRecordC1.setTotalFee(Long.valueOf(xTotalFee));
							hDispatchRecordC1.setAmmeter_no(ammeter_no);
							did = hdispatchrecordcService.insertHDispatchRecordC(hDispatchRecordC1);
						}else{//本次公司所有电表欠费金额是0，不发送短息
							b1 = false;
						}
					}
				}
						
					
				log.info("-------发送短信cTotalFee----"+tel+"------>"+xTotalFee);
				if(b1&&b){
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String totalFeeStr1 = null;
					if(xTotalFee>0){
						if(String.valueOf(xTotalFee).length()>2){
							int fen = xTotalFee%100;
							if(fen>0&&fen<10){
								totalFeeStr1 = xTotalFee/100 + ".0" + fen;
							}else if(fen>=10){
								totalFeeStr1 = xTotalFee/100 + "." + fen;
							}else{
								totalFeeStr1 = xTotalFee/100 + ".00";
							}
						}else if(String.valueOf(xTotalFee).length()==1){
							totalFeeStr1 = "0.0"+ xTotalFee;
						}else{
							totalFeeStr1 = "0."+ xTotalFee;
						}
					}else{
						totalFeeStr1 = "0.00";
					}
//					String content = tel + "，贵单位在企业缴费网站登记缴费号下的后付费电表已完成抄表，欠费共计人民币"+totalFeeStr1+"元。请您按时缴费，缴费请登录http://qiye.chinaepay.com。发送时间：" + sf.format(new Date());
					String content = tel + "，贵单位在企业缴电费网站共登记了"+totalAmmeterInfo+"个缴费号，其中"+moenyAmmeterInfo+"个缴费号已完成抄表，电费共计人民币"+totalFeeStr1+"元，详情请登录https://qiye.chinaepay.com 查看。如您对电费有疑问，请直接联系用电地址所属供电所处理或拨打恒信通客服电话96199协调处理。发送时间： " + sf.format(new Date());
					if(totalAmmeterInfo==1){
						content = tel + "，贵单位在企业缴电费网站共登记了"+totalAmmeterInfo+"个缴费号已完成抄表，电费共计人民币"+totalFeeStr1+"元，详情请登录https://qiye.chinaepay.com 查看。如您对电费有疑问，请直接联系用电地址所属供电所处理或拨打恒信通客服电话96199协调处理。发送时间： " + sf.format(new Date());
					}
					log.info("-------发送短信-"+sf.format(new Date())+"---"+tel+"------>"+totalFeeStr1);
					SendMsgUtil.sendMsg(tel,content);//发送短信
					HDispatchRecordC hDispatchRecordC2 = new HDispatchRecordC();
					hDispatchRecordC2.setId(did);
					hDispatchRecordC2.setContent(content);
					hdispatchrecordcService.updateHDispatchRecordC(hDispatchRecordC2);//短信内容记录入库
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
			
}
