package com.hxt.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.hxt.hProxyMessage.model.HProxyMessage;
import com.hxt.hProxyMessage.service.HProxyMessageService;
import com.hxt.hSubCompany.service.HSubCompanyService;
import com.hxt.hUserAccount.service.HUserAccountService;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.sys.manageAdminUser.service.ManageAdminUserService;

@Service
public class TaskJob {
	
	Logger log = Logger.getLogger(TaskJob.class);
	
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
	@Autowired
	private HProxyMessageService hproxymessageService = null;
	@Autowired
	private ManageAdminUserService manageadminuserService = null;// 用户
	@Autowired
	private HUserAccountService huseraccountService = null;
	
	
	public void job1() {
		try{
			SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
			//查询所有有效单位
			HCompany hCompany = new HCompany();
			hCompany.setStatus(1);
			hCompany.setVerify_status(1);
			int countC = hcompanyService.getHCompanyListCount(hCompany);
			if(countC>0){
				int pageCount = countC/10000;//分页查询电表号避免溢出
				int yu = countC%10000;
				if(yu>0){
					pageCount = pageCount + 1;
				}
				for(int i=0;i<pageCount;i++){
					hCompany.setRowCount(10000);
					hCompany.setRowStart(i*10000);
					List<HCompany> listC = hcompanyService.getHCompanyListByTask(hCompany);
					if(listC!=null&&listC.size()>0){
						for(HCompany com:listC){//遍历每个单位
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
//								String content = tel + "，贵单位在企业缴费网站登记缴费号下的后付费电表已完成抄表，欠费共计人民币"+totalFeeStr1+"元。请您按时缴费，缴费请登录http://qiye.chinaepay.com。发送时间：" + sf.format(new Date());
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
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
//	public void job1() {
//		try{
//			StringBuffer sb1 = new StringBuffer();
//			SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
//			//查询所有单位
//			HCompany hCompany = new HCompany();
//			hCompany.setStatus(1);
//			int countC = hcompanyService.getHCompanyListCount(hCompany);
//			if(countC>0){
//				int pageCount = countC/10000;//分页查询电表号避免溢出
//				int yu = countC%10000;
//				if(yu>0){
//					pageCount = pageCount + 1;
//				}
//				for(int i=0;i<pageCount;i++){
//					hCompany.setRowCount(10000);
//					hCompany.setRowStart(i*10000);
//					List<HCompany> listC = hcompanyService.getHCompanyListByTask(hCompany);
//					if(listC!=null&&listC.size()>0){
//						for(HCompany com:listC){
//							//查询单位下面电表
//							Integer cTotalFee = 0;
//							Integer xTotalFee = 0;
//							String cname = null;
//							String tel = null;
//							boolean b = true;
//							HAmmeterInfo hAmmeterInfo = new HAmmeterInfo();
//							hAmmeterInfo.setPay_status("1");
//							hAmmeterInfo.setC_status(1);
//							hAmmeterInfo.setC_verify_status(1);
//							hAmmeterInfo.setC_id(com.getId());
//							cname = com.getName();
//							tel = com.getContact_phone();
//							System.out.println("------tel--------"+tel);
//							//查询有效的电表号
//							List<HAmmeterInfo> listHAmmeterInfo = hammeterinfoService.getHAmmeterInfoListSql(hAmmeterInfo);
//							if(listHAmmeterInfo!=null&&listHAmmeterInfo.size()>0){
//								for(HAmmeterInfo info:listHAmmeterInfo){
//									//调单
//									JSONObject json = hCommonService.hXTServiceQuery(info.getAmmeter_no(), "127.0.0.1");
//									if(json.getString("totalFee")!=null){
//										Integer totalFeeStr = Integer.valueOf(json.getString("totalFeeStr"));//单位是分
//										log.info("-------totalFeeStr---------->"+totalFeeStr);
//										xTotalFee = xTotalFee + totalFeeStr;
//										log.info("-------totalFeeStr---------->"+totalFeeStr%10000);
//										if(totalFeeStr>0&&totalFeeStr%10000==0){
//											b = false;
//										}
//									}
//									if("00".equals(json.getString("resultCode"))){//调单成功
//										if(!"0.00".equals(json.getString("totalFee"))){//调单欠费
//											if(info.getTotalFee()==null){//数据库无欠费信息，则写入库
//												HDispatchRecord hDispatchRecord = new HDispatchRecord();
//												String resultInfo = json.getString("resultInfo");
//												JSONObject json1 = JSONObject.parseObject(resultInfo);
//												hDispatchRecord.setAccountName(json1.getString("accountName"));
//												hDispatchRecord.setAddress(json1.getString("address"));
//												hDispatchRecord.setAccountTime(json1.getString("accountTime"));
//												hDispatchRecord.setLateFee(json1.getString("lateFee"));
//												hDispatchRecord.setAccountFee(json1.getString("accountFee"));
//												hDispatchRecord.setAmmeter_no(info.getAmmeter_no());
//												hDispatchRecord.setRemark1(com.getId()+"");
//												hDispatchRecord.setCreateTime(new Date());
//												if(b){
//													hDispatchRecord.setStatus(0);
//												}else{
//													hDispatchRecord.setStatus(1);
//												}
//												hDispatchRecord.setTotalFee(Integer.valueOf(json.getString("totalFeeStr")));
//												int id = hdispatchrecordService.insertHDispatchRecord(hDispatchRecord);
//												if(id>0){
//													cTotalFee = cTotalFee + Integer.valueOf(json.getString("totalFeeStr"));
//												}
//											}else{//数据库已经存在欠费信息
//												//对比欠费金额
//												Integer b_Total = info.getTotalFee();//数据库中的欠费信息
//												log.info("-------b_Total---------->"+b_Total);
//												Integer a_Total = Integer.valueOf(json.getString("totalFeeStr"));//调单的欠费信息
//												log.info("-------a_Total---------->"+a_Total);
//												log.info("-------(a_Total-b_Total)---------->"+(a_Total-b_Total));
//												if((a_Total-b_Total)==0){//如果欠费金额相等
//													//比较欠费时间是否超过设置的最后缴费时间
//													Calendar ca = Calendar.getInstance();
//													ca.setTime(info.getDispatch_create_time());
//													ca.add(Calendar.DATE, info.getLast_pay_day());
//													Date time = ca.getTime();
//													if(sf1.format(new Date()).equals(sf1.format(time))){//发送短信通知
//														cTotalFee = cTotalFee + Integer.valueOf(json.getString("totalFeeStr"));
//													}
//												}else{//金额不相等
//													HDispatchRecord hDispatchRecord = new HDispatchRecord();
//													String resultInfo = json.getString("resultInfo");
//													JSONObject json1 = JSONObject.parseObject(resultInfo);
//													hDispatchRecord.setAccountName(json1.getString("accountName"));
//													hDispatchRecord.setAddress(json1.getString("address"));
//													hDispatchRecord.setAccountTime(json1.getString("accountTime"));
//													hDispatchRecord.setLateFee(json1.getString("lateFee"));
//													hDispatchRecord.setAccountFee(json1.getString("accountFee"));
//													hDispatchRecord.setAmmeter_no(info.getAmmeter_no());
//													hDispatchRecord.setCreateTime(new Date());
//													hDispatchRecord.setRemark1(com.getId()+"");
//													hDispatchRecord.setStatus(0);
//													hDispatchRecord.setTotalFee(Integer.valueOf(json.getString("totalFeeStr")));
//													int idsss = hdispatchrecordService.insertHDispatchRecord(hDispatchRecord);
//													if(idsss>0){
//														cTotalFee = cTotalFee + Integer.valueOf(json.getString("totalFeeStr"));
//													}
//													//把原来记录修改为1
//													HDispatchRecord hDispatchRecord1 = new HDispatchRecord();
//													hDispatchRecord1.setId(info.getDispatch_id());
//													hDispatchRecord1.setStatus(1);
//													hdispatchrecordService.updateHDispatchRecord(hDispatchRecord1);
//												}
//											}
//										}else{//调单不欠费。
//											if(info.getTotalFee()!=null){//数据库中仍显示欠费，说明已经交费了，将记录状态置为1
//												HDispatchRecord hDispatchRecord = new HDispatchRecord();
//												hDispatchRecord.setStatus(1);
//												hDispatchRecord.setAmmeter_no(info.getAmmeter_no());
//												hDispatchRecord.setUpdateTime(new Date());
//												hDispatchRecord.setRemark1(com.getId()+"");
//												hdispatchrecordService.updateHDispatchRecordByNo(hDispatchRecord);
//											}
//										}
//									}else{//将调单不成功的电表号记录文本
//										sb1.append(info.getC_name()).append("|").append(info.getSub_name()).append("|").append(info.getAmmeter_no()).append("\r\n");
//									}
//								}
//								if(StringUtils.isNotBlank(sb1.toString())){//如果有
//									SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
//									File tmp = new File(basePath + "/upload/txt");
//									if(!tmp.exists()){
//										tmp.mkdirs();
//									}
//									StringBuffer sb = new StringBuffer();
//									sb.append("单位名称|子单位名称|电表号").append("\r\n");
//									FileOutputStream fOut = new FileOutputStream(basePath + "/upload/txt/"+sf.format(new Date())+".txt");
//									fOut.write(sb.append(sb1.toString()).toString().getBytes());
//									fOut.flush();
//									fOut.close();
//								}
//							}
//							log.info("-------发送短信cTotalFee----"+tel+"------>"+cTotalFee);
//							if(cTotalFee>0&&b){
//								SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//								String totalFeeStr1 = null;
//								if(xTotalFee>0){
//									if(String.valueOf(xTotalFee).length()>2){
//										int fen = xTotalFee%100;
//										if(fen>0&&fen<10){
//											totalFeeStr1 = xTotalFee/100 + ".0" + fen;
//										}else if(fen>=10){
//											totalFeeStr1 = xTotalFee/100 + "." + fen;
//										}else{
//											totalFeeStr1 = xTotalFee/100 + ".00";
//										}
//									}else if(String.valueOf(xTotalFee).length()==1){
//										totalFeeStr1 = "0.0"+ xTotalFee;
//									}else{
//										totalFeeStr1 = "0."+ xTotalFee;
//									}
//								}else{
//									totalFeeStr1 = "0.00";
//								}
//								String content = cname + "，贵单位在企业缴费网站登记缴费号下的后付费电表已完成抄表，欠费共计人民币"+totalFeeStr1+"元整。请您按时缴费，缴费请登录http://qiye.chinaepay.com。发送时间：" + sf.format(new Date());
//								log.info("-------发送短信-"+sf.format(new Date())+"---"+tel+"------>"+totalFeeStr1);
//								SendMsgUtil.sendMsg(tel,content);
//							}
//						}
//					}
//				}
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * 代扣调单
	 */
	public void job2() {
		try{
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//查询没有过期的代扣登记记录
			HProxyMessage hProxyMessage = new HProxyMessage();
//			hProxyMessage.setNowDate(new Date());
			hProxyMessage.setState(1);
//			hProxyMessage.setCheckState(1);
			List<HProxyMessage> list = hproxymessageService.getHProxyMessageBaseList(hProxyMessage);
			if(list!=null&&list.size()>0){
				for(HProxyMessage pm:list){
					if(pm.getCheckState()==1||pm.getCheckState()==6||pm.getCheckState()==7||pm.getCheckState()==8){
						//查询客户状态
						HCompany hCompany = new HCompany();
						hCompany.setId(pm.getCId());
						hCompany = hcompanyService.getHCompany(hCompany);
						if(hCompany!=null&&hCompany.getStatus()==1&&hCompany.getVerify_status()==1){
							//判断是否在设置的日期内
							String d = sf.format(new Date()).substring(8,10);
							if(Integer.valueOf(d)>=pm.getRemindStartDate()&&Integer.valueOf(d)<=pm.getRemindEndDate()){
								//生产订单
//								JSONObject json = hCommonService.createOrderByCompnay(pm.getCId(),"127.0.0.1");
								//获取单位登记有效电表数
								HAmmeterInfo am = new HAmmeterInfo();
								am.setC_id(pm.getCId());
								am.setPay_status("1");
								am.setDelete_state(1);
								am.setProxy_flag(1);
								List<HAmmeterInfo> listInfo = hammeterinfoService.getHAmmeterInfoBaseList(am);
								int mony = 0;
								int ammeterCount = 0;
								int porxyCount = 0;
								boolean b = true;
								if(listInfo!=null&&listInfo.size()>0){
									ammeterCount = listInfo.size();
									for(HAmmeterInfo info:listInfo){
										JSONObject result = hCommonService.hXTServiceQuery(info.getAmmeter_no(), "127.0.0.1");
										if("00".equals(result.get("resultCode"))){
											Integer fee = Integer.valueOf(result.get("totalFeeStr") == null ? "0" : result.get("totalFeeStr").toString());
											if(fee>0&&fee%10000==0){
												b = false;
											}
											porxyCount = porxyCount + 1;
											mony = mony + fee;
										}
									}
								}
								//推送信息
								ManageAdminUser manageAdminUser = new ManageAdminUser();
								manageAdminUser.setAdminId(pm.getUserId());
								manageAdminUser = manageadminuserService.getAdminUserByLogin(manageAdminUser);
								if(manageAdminUser!=null&&StringUtils.isNotBlank(manageAdminUser.getOpenId())){
									if(ammeterCount>0){
										if(manageAdminUser.getState()==1&&b){
											huseraccountService.sendProxyTempltMsg(manageAdminUser.getOpenId(),pm.getSex(), pm.getProxyName(), pm.getId(), "", hCompany.getName(),ammeterCount,porxyCount,mony);
										}
									}else{
										log.info(sf.format(new Date())+"-------没有设置代扣缴费号------->"+hCompany.getId()+"----"+hCompany.getName());
									}
								}else{
									log.info(sf.format(new Date())+"-------账户状态异常------->"+hCompany.getId()+"----"+hCompany.getName());
								}
							}
							
						}else{
//							log.info(sf.format(new Date())+"-------机构状态异常------->"+hCompany.getId()+"----"+hCompany.getName());
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void job3(){
//		try {
//			Socket s = new Socket(SOCKET_HOST,SOCKET_PORT);
//			System.out.println("----------11111------------");
//			SOCKET_FLAG = false;
//			SOCKET_COUNT = 0;
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("----------SOCKET_COUNT------------"+SOCKET_COUNT);
//			SOCKET_FLAG = true;
//			SOCKET_COUNT = SOCKET_COUNT + 1;
//			while(SOCKET_FLAG){
//				try {
//					Thread.sleep(2000);
//					if(SOCKET_COUNT==10){
//						SOCKET_FLAG = false;
//						SOCKET_COUNT = 0;
//						Thread.sleep(200000);
//						System.out.println("----------------------");
//					}
//					job3();
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//			}
//		}		
	}
	
}
