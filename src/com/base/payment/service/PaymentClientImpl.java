package com.base.payment.service;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.catalina.core.ApplicationContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.base.utils.ApplicationContextHolder;
import com.base.utils.DateFormatToString;
import com.base.utils.FileUploadConstants;
import com.hxt.hProxySendLog.dao.HProxySendLogDAO;
import com.hxt.hProxySendLog.model.HProxySendLog;
import com.hxt.hProxySendLog.service.HProxySendLogService;
import com.sys.adminRoleMethod.service.AdminRoleMethodService;
import com.wx.utils.EncryptUtil;
//@Service("paymentClient")
public class PaymentClientImpl implements PaymentClient{
	
	private static Logger logger = Logger.getLogger(PaymentClientImpl.class); //Logger
	
//	@Resource(name = "hProxySendLogDao")
//    private HProxySendLogDAO hProxySendLogService;
//	WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
//	HProxySendLogDAO hProxySendLogService = wac.getBean(HProxySendLogDAO.class);
	
	HProxySendLogService hProxySendLogService = (HProxySendLogService) ApplicationContextHolder.getBean("hProxySendLogService");

	@Override
	public String add(String contractNo, String userCode, String userName, String phone, String ywCate, String fkNo,
			String fkAccount, String fkName) throws UnknownHostException, Exception {
		StringBuffer mesBuf = new StringBuffer();// 报文体
		String cate = "400101";// 交易种类n6
		mesBuf.append(cate);
		String type = "1202";// 报文类型n4
		mesBuf.append(type);
		String sqe = getSQE();// 交易序号n16
		mesBuf.append(sqe);
		String entrustDate = DateFormatToString.getToday12();// 委托日期n8
		mesBuf.append(entrustDate);
		String oprateSign = "100";// 协议操作标识n3
		mesBuf.append(oprateSign);
		String orgCode = FileUploadConstants.ORGANIZATION_CODE + getSpace(3);// 收费机构代码 an12
		mesBuf.append(orgCode);
		String orgBranch = getSpace(12);// 机构分支代码an12
		mesBuf.append(orgBranch);
		String yw_bill = getZero(16);// 业务处理方流水n16,补全0
		mesBuf.append(yw_bill);
		String return_code = getSpace(8);// 返回码an8
		mesBuf.append(return_code);
		String contract_no = autoFill(contractNo, 60);// 合同（协议）号需要给,计算方式如下 an60
		mesBuf.append(contract_no);
		/*
		 * 1、第1-2位（共2位）为借贷标志，01为定期借记业务、02为定期贷记业务。
		 * 2、第3-6位（共4位）为定期借（贷）记委托单位所在地区的代码，采用支付系统中规定的城市代码，4位数字，全国标准，如广州5810。
		 * 3、第7-15位（共9位）为定期借（贷）记委托单位的组织机构代码，9位数字或字母字符串，全国标准。
		 * 4、第16-20位（共5位）为业务种类代码，采用人民银行总行标准，为5位数字，编码规则详见附录6.1。
		 * 5、第21-23位（共3位）为付款银行行别代码，采用人民银行总行的行号标准，为3位数字，如工商银行102、农业银行103，
		 * 各付款银行行别代码详见附录6.3。 6、第24-55位（最长32位可变长）为付款人账号，为变长数字，最长使用32位账号。
		 */
		String user_code = autoFill(userCode, 18);// 用户编号 ans18
		mesBuf.append(user_code);
		String user_name = autoFill(userName, 60); // 用户名称g60
		mesBuf.append(user_name);
		String lx_name = getSpace(60);// 联系人名称g60
		mesBuf.append(lx_name);
		String lx_address = getSpace(60);// 联系人地址g60
		mesBuf.append(lx_address);
		String lx_post = getZero(6);// 联系人地址邮编n6
		mesBuf.append(lx_post);
		String lx_phone = autoFill(phone, 25);// 联系人电话ams25
		mesBuf.append(lx_phone);

		String yw_cate = ywCate;// 业务种类n5,传来必须5位
		mesBuf.append(yw_cate);

		String fk_no = autoFill(fkNo, 12);// 原付款行行号an12，必须12位
		mesBuf.append(fk_no);
		String fk_account = autoFill(fkAccount, 32);// 付款人账号ans32，自动补全
		mesBuf.append(fk_account);
		String fk_name = autoFill(fkName, 60);// 付款人名称g60，自动补全
		mesBuf.append(fk_name);

		String fk_type = getSpace(1);// 付款人证件类型 an1
		mesBuf.append(fk_type);
		String fk_card = getSpace(18);// 付款人证件号码ans18
		mesBuf.append(fk_card);
		String fk_mobile = getSpace(16);// 付款手机ans16
		mesBuf.append(fk_mobile);
		String fk_email = getSpace(30);// 付款人电子邮箱ans30
		mesBuf.append(fk_email);

		String fy = getSpace(60);// 附言 g60
		mesBuf.append(fy);

		String body = mesBuf.toString();
		String head = autoZero(length(body) + "", 8, 1);
		String footer = getMac(body + FileUploadConstants.SOCKET_MAC_KEY);

		String message = head + body + footer;
		HProxySendLog hProxySendLog = new HProxySendLog();
		hProxySendLog.setContent(message);
		hProxySendLog.setContractNumber(contractNo);
		hProxySendLog.setUserNumber(userCode);
		hProxySendLog.setBank_number(fkNo);
		hProxySendLog.setPayBankNumber(fkAccount);
		hProxySendLog.setSendStyle(1);
		hProxySendLog.setStyle(1);
		hProxySendLog.setRemark1(userName);
		hProxySendLog.setRemark2(phone);
		hProxySendLogService.insertHProxySendLog(hProxySendLog);
		
		logger.info("发送："+message);
		String returnCode = "";
		String ret = send(message);
		logger.info("接收："+ret);
		HProxySendLog hProxySendLog1 = new HProxySendLog();
		hProxySendLog1.setContent(ret);
		hProxySendLog1.setContractNumber(contractNo);
		hProxySendLog1.setUserNumber(userCode);
		hProxySendLog1.setBank_number(fkNo);
		hProxySendLog1.setPayBankNumber(fkAccount);
		hProxySendLog1.setSendStyle(2);
		hProxySendLog1.setStyle(1);
		hProxySendLog1.setRemark1(userName);
		hProxySendLog1.setRemark2(phone);
		hProxySendLogService.insertHProxySendLog(hProxySendLog1);
		if("CS".equals(ret)){//超时
			returnCode = "CS";
		}else{
			returnCode = ret.substring(85, 93);// 截取返回码
			logger.info("返回码：" + returnCode);
		}
		return returnCode;
	}

	@Override
	public String update(String contractNo, String userCode, String userName,String payPhoneNumber, String ywCate, String fkNo,
			String fkAccount, String fkName) throws UnknownHostException, Exception {
		StringBuffer mesBuf = new StringBuffer();// 报文体
		String cate = "400101";// 交易种类n6
		mesBuf.append(cate);
		String type = "1202";// 报文类型n4
		mesBuf.append(type);
		String sqe = getSQE();// 交易序号n16
		mesBuf.append(sqe);
		String entrustDate = DateFormatToString.getToday12();// 委托日期n8
		mesBuf.append(entrustDate);
		String oprateSign = "101";// 协议操作标识n3
		mesBuf.append(oprateSign);
		String orgCode = FileUploadConstants.ORGANIZATION_CODE + getSpace(3);// 收费机构代码 an12
		mesBuf.append(orgCode);
		String orgBranch = getSpace(12);// 机构分支代码an12
		mesBuf.append(orgBranch);
		String yw_bill = getZero(16);// 业务处理方流水n16,补全0
		mesBuf.append(yw_bill);
		String return_code = getSpace(8);// 返回码an8
		mesBuf.append(return_code);
		String contract_no = autoFill(contractNo, 60);// 原合同（协议）号需要给,计算方式如下 an60
		mesBuf.append(contract_no);
		/*
		 * 1、第1-2位（共2位）为借贷标志，01为定期借记业务、02为定期贷记业务。
		 * 2、第3-6位（共4位）为定期借（贷）记委托单位所在地区的代码，采用支付系统中规定的城市代码，4位数字，全国标准，如广州5810。
		 * 3、第7-15位（共9位）为定期借（贷）记委托单位的组织机构代码，9位数字或字母字符串，全国标准。
		 * 4、第16-20位（共5位）为业务种类代码，采用人民银行总行标准，为5位数字，编码规则详见附录6.1。
		 * 5、第21-23位（共3位）为付款银行行别代码，采用人民银行总行的行号标准，为3位数字，如工商银行102、农业银行103，
		 * 各付款银行行别代码详见附录6.3。 6、第24-55位（最长32位可变长）为付款人账号，为变长数字，最长使用32位账号。
		 */
		String user_code = autoFill(userCode, 18);// 原用户编号 ans18
		mesBuf.append(user_code);
		String user_name = autoFill(userName, 60); // 原用户名称g60
		mesBuf.append(user_name);
		
		String payPhoneNumber1 = autoFill(payPhoneNumber, 25);// 手机号n25,补全0
//		mesBuf.append(payPhoneNumber1);
		
		String lx_name = getSpace(60);// 联系人名称g60
		mesBuf.append(lx_name);
		String lx_address = getSpace(60);// 联系人地址g60
		mesBuf.append(lx_address);
		String lx_post = getZero(6);// 联系人地址邮编n6
		mesBuf.append(lx_post);
//		String lx_phone = getSpace(25);// 联系人电话ams25
		mesBuf.append(payPhoneNumber1);

		String yw_cate = ywCate;// 原业务种类n5,传来必须5位
		mesBuf.append(yw_cate);

		String fk_no = autoFill(fkNo, 12);// 原付款行行号an12，必须12位
		mesBuf.append(fk_no);
		String fk_account = autoFill(fkAccount, 32);// 原付款人账号ans32，自动补全
		mesBuf.append(fk_account);
		String fk_name = autoFill(fkName, 60);// 原付款人名称g60，自动补全
		mesBuf.append(fk_name);

		String fk_type = getZero(1);// 付款人证件类型 an1
		mesBuf.append(fk_type);
		String fk_card = getZero(18);// 付款人证件号码ans18
		mesBuf.append(fk_card);
		String fk_mobile = getSpace(16);// 付款手机ans16
		mesBuf.append(fk_mobile);
		String fk_email = getSpace(30);// 付款人电子邮箱ans30
		mesBuf.append(fk_email);

		String fy = getSpace(60);// 附言 g60
		mesBuf.append(fy);

		String body = mesBuf.toString();
		String head = autoZero(length(body) + "", 8, 1);
		String footer = getMac(body + FileUploadConstants.SOCKET_MAC_KEY);

		String message = head + body + footer;
		HProxySendLog hProxySendLog = new HProxySendLog();
		hProxySendLog.setContent(message);
		hProxySendLog.setContractNumber(contractNo);
		hProxySendLog.setUserNumber(userCode);
		hProxySendLog.setBank_number(fkNo);
		hProxySendLog.setPayBankNumber(fkAccount);
		hProxySendLog.setSendStyle(1);
		hProxySendLog.setStyle(2);
		hProxySendLog.setRemark1(userName);
		hProxySendLogService.insertHProxySendLog(hProxySendLog);
		logger.info("发送："+message);
		String ret = send(message);
		logger.info("接收："+ret);
		HProxySendLog hProxySendLog1 = new HProxySendLog();
		hProxySendLog1.setContent(ret);
		hProxySendLog1.setContractNumber(contractNo);
		hProxySendLog1.setUserNumber(userCode);
		hProxySendLog1.setBank_number(fkNo);
		hProxySendLog1.setPayBankNumber(fkAccount);
		hProxySendLog1.setSendStyle(2);
		hProxySendLog1.setStyle(2);
		hProxySendLog1.setRemark1(userName);
		hProxySendLogService.insertHProxySendLog(hProxySendLog1);
		String returnCode = ret.substring(85, 93);// 截取返回码
		logger.info("返回码：" + returnCode);
		return returnCode;
	}

	@Override
	public String revoke(String contractNo, String userCode, String userName, String ywCate, String fkNo,
			String fkAccount, String fkName) throws UnknownHostException, Exception {
		StringBuffer mesBuf = new StringBuffer();// 报文体
		String cate = "400101";// 交易种类n6
		mesBuf.append(cate);
		String type = "1202";// 报文类型n4
		mesBuf.append(type);
		String sqe = getSQE();// 交易序号n16
		mesBuf.append(sqe);
		String entrustDate = DateFormatToString.getToday12();// 委托日期n8
		mesBuf.append(entrustDate);
		String oprateSign = "102";// 协议操作标识n3
		mesBuf.append(oprateSign);
		String orgCode = FileUploadConstants.ORGANIZATION_CODE + getSpace(3);// 收费机构代码 an12
		mesBuf.append(orgCode);
		String orgBranch = getSpace(12);// 机构分支代码an12
		mesBuf.append(orgBranch);
		String yw_bill = getZero(16);// 业务处理方流水n16,补全0
		mesBuf.append(yw_bill);
		String return_code = getSpace(8);// 返回码an8
		mesBuf.append(return_code);
		String contract_no = autoFill(contractNo, 60);// 原合同（协议）号需要给,计算方式如下 an60
		mesBuf.append(contract_no);
		/*
		 * 1、第1-2位（共2位）为借贷标志，01为定期借记业务、02为定期贷记业务。
		 * 2、第3-6位（共4位）为定期借（贷）记委托单位所在地区的代码，采用支付系统中规定的城市代码，4位数字，全国标准，如广州5810。
		 * 3、第7-15位（共9位）为定期借（贷）记委托单位的组织机构代码，9位数字或字母字符串，全国标准。
		 * 4、第16-20位（共5位）为业务种类代码，采用人民银行总行标准，为5位数字，编码规则详见附录6.1。
		 * 5、第21-23位（共3位）为付款银行行别代码，采用人民银行总行的行号标准，为3位数字，如工商银行102、农业银行103，
		 * 各付款银行行别代码详见附录6.3。 6、第24-55位（最长32位可变长）为付款人账号，为变长数字，最长使用32位账号。
		 */
		String user_code = autoFill(userCode, 18);// 原用户编号 ans18
		mesBuf.append(user_code);
		String user_name = autoFill(userName, 60); // 原用户名称g60
		mesBuf.append(user_name);
		String lx_name = autoFill("00",60);// 联系人名称g60
		mesBuf.append(lx_name);
		String lx_address = autoFill("00",60);// 联系人地址g60
		mesBuf.append(lx_address);
		String lx_post = getZero(6);// 联系人地址邮编n6
		mesBuf.append(lx_post);
		String lx_phone = autoFill("00",25);// 联系人电话ams25
		mesBuf.append(lx_phone);
		
		String yw_cate = ywCate;// 原业务种类n5,传来必须5位
		mesBuf.append(yw_cate);
		
		String fk_no = autoFill(fkNo, 12);// 原付款行行号an12，必须12位
		mesBuf.append(fk_no);
		String fk_account = autoFill(fkAccount, 32);// 原付款人账号ans32，自动补全
		mesBuf.append(fk_account);
		String fk_name = autoFill(fkName, 60);// 原付款人名称g60，自动补全
		mesBuf.append(fk_name);
		
		String fk_type = getZero(1);// 付款人证件类型 an1
		mesBuf.append(fk_type);
		String fk_card = autoFill("00", 18);// 付款人证件号码ans18
		mesBuf.append(fk_card);
		String fk_mobile = autoFill("00", 16);// 付款手机ans16
		mesBuf.append(fk_mobile);
		String fk_email = autoFill("00", 30);// 付款人电子邮箱ans30
		mesBuf.append(fk_email);
		
		String fy = getSpace(60);// 附言 g60
		mesBuf.append(fy);
		
		String body = mesBuf.toString();
		String head = autoZero(length(body) + "", 8, 1);
		String footer = getMac(body + FileUploadConstants.SOCKET_MAC_KEY);
		
		String message = head + body + footer;
		HProxySendLog hProxySendLog = new HProxySendLog();
		hProxySendLog.setContent(message);
		hProxySendLog.setContractNumber(contractNo);
		hProxySendLog.setUserNumber(userCode);
		hProxySendLog.setBank_number(fkNo);
		hProxySendLog.setPayBankNumber(fkAccount);
		hProxySendLog.setSendStyle(1);
		hProxySendLog.setStyle(3);
		hProxySendLog.setRemark1(userName);
		hProxySendLogService.insertHProxySendLog(hProxySendLog);
		
		logger.info("发送："+message);
		String ret = send(message);
		logger.info("接收："+ret);
		
		HProxySendLog hProxySendLog1 = new HProxySendLog();
		hProxySendLog1.setContent(ret);
		hProxySendLog1.setContractNumber(contractNo);
		hProxySendLog1.setUserNumber(userCode);
		hProxySendLog1.setBank_number(fkNo);
		hProxySendLog1.setPayBankNumber(fkAccount);
		hProxySendLog1.setSendStyle(2);
		hProxySendLog1.setStyle(3);
		hProxySendLog1.setRemark1(userName);
		hProxySendLogService.insertHProxySendLog(hProxySendLog1);
		String returnCode = ret.substring(85, 93);// 截取返回码
		logger.info("返回码：" + returnCode);
		return returnCode;
	}

	@Override
	public String daishou(String userCode, String sendNo, String sendAccount, String receiveNo, String receiveBranch,
			String fkAccount, String fkName, String ywCate, String contractNo, String moneyType, String moneyStr,String sqe) throws UnknownHostException, Exception {
		StringBuffer mesBuf = new StringBuffer();// 报文体
		String cate = "100301";// 交易种类n6
		mesBuf.append(cate);
		String type = "1202";// 报文类型n4
		mesBuf.append(type);
		
		mesBuf.append(sqe);
		String entrustDate = DateFormatToString.getToday12();// 委托日期n8
		mesBuf.append(entrustDate);
		
		String clearDate = getZero(8);//清算日期n8
		mesBuf.append(clearDate);
		String user_code = autoFill(userCode, 18);// 原用户编号 ans18
		mesBuf.append(user_code);
		
		String send_org_code = autoFill(FileUploadConstants.ORGANIZATION_CODE, 12);//		发起方代码（收费机构代码）an12
		mesBuf.append(send_org_code);
		String orgBranch = getSpace(12);// 机构分支代码an12
		mesBuf.append(orgBranch);
		
		String send_no = autoFill(sendNo, 12);// 发起方开户行行号（收费机构开户行行号）an12
		mesBuf.append(send_no);
		String send_account = autoFill(sendAccount, 35);// 发起方开户账号（收费机构开户账号）ans35
		mesBuf.append(send_account);
		String receive_no = receiveNo;// 接收方代码（付款清算行行号）an12
		mesBuf.append(receive_no);
		String receive_branch = autoFill(receiveBranch, 12);// 接收方分支代码（付款人开户行行号）an12 选填
		mesBuf.append(receive_branch);
		
		String fk_account = autoFill(fkAccount, 35);//付款人账号ans35
		mesBuf.append(fk_account);
		String fk_name = autoFill(fkName, 60);//付款人名称g60
		mesBuf.append(fk_name);
		String yw_cate = ywCate;// 原业务种类n5,传来必须5位
		mesBuf.append(yw_cate);
		
		String contract_no = autoFill(contractNo, 60);// 原合同（协议）号需要给,计算方式如下 an60
		mesBuf.append(contract_no);
		String money_type = moneyType ;//交易货币代码a3
		mesBuf.append(money_type);
		String money = autoZero(moneyStr, 15, 1);//金额 n15
		mesBuf.append(money);
		
		String sh_fee = getZero(15);//手续费n15
		mesBuf.append(sh_fee);
		String fy = getSpace(60);// 附言 g60
		mesBuf.append(fy);
		String yw_bill =  getZero(16);//业务处理方流水n16
		mesBuf.append(yw_bill);
		String jiqequ = mesBuf.toString();
		String return_code = getSpace(8);// 返回码an8
		mesBuf.append(return_code);
		String jiaofei = getZero(1);//主动缴费标识n1
		mesBuf.append(jiaofei);
		String info_len = getZero(8);//变长定制信息长度n8
		mesBuf.append(info_len);
		
		String body = mesBuf.toString();
		String head = autoZero(length(body) + "", 8, 1);
		String footer = getMac(body + FileUploadConstants.SOCKET_MAC_KEY);
		
		String message = head + body + footer;
		
		HProxySendLog hProxySendLog = new HProxySendLog();
		hProxySendLog.setContent(message);
		hProxySendLog.setContractNumber(contractNo);
		hProxySendLog.setUserNumber(userCode);
		hProxySendLog.setBank_number(sendNo);
		hProxySendLog.setPayBankNumber(fkAccount);
		hProxySendLog.setSendStyle(1);
		hProxySendLog.setStyle(6);
		hProxySendLog.setRemark1(fkName);
		hProxySendLogService.insertHProxySendLog(hProxySendLog);
		
		logger.info("发送："+message);
		String returnCode = "";
		String ret = send(message);
		
		HProxySendLog hProxySendLog1 = new HProxySendLog();
		hProxySendLog1.setContent(ret);
		hProxySendLog1.setContractNumber(contractNo);
		hProxySendLog1.setUserNumber(userCode);
		hProxySendLog1.setBank_number(sendNo);
		hProxySendLog1.setPayBankNumber(fkAccount);
		hProxySendLog1.setSendStyle(2);
		hProxySendLog1.setStyle(6);
		hProxySendLog1.setRemark1(fkName);
		hProxySendLog1.setRemark3(sqe);
		hProxySendLogService.insertHProxySendLog(hProxySendLog1);
		
		logger.info("----------------jiqequ--------------------："+jiqequ);
		logger.info("-----------------ret-------------："+ret);
		if("CS".equals(ret)){//超时
			returnCode = "CS";
		}else{
//			returnCode = ret.substring(429, 437);// 截取返回码
//			int len = length(jiqequ);
//			returnCode = splitString(ret,8,len+8);// 截取返回码
//			//截取日期
//			String datestr = ret.substring(22, 26);
			returnCode = ret.substring(jiqequ.length()+8, jiqequ.length()+16);// 截取返回码
			//截取日期
			String datestr = ret.substring(46, 50);
			logger.info("返回码：" + returnCode);
			returnCode = returnCode + "_" + datestr;
		}
		return returnCode;
	}

	@Override
	public String correct(String receiveNo, String receiveBranch, String dataSource) throws UnknownHostException, Exception {
		StringBuffer mesBuf = new StringBuffer();// 报文体
		String cate = "300201";// 交易种类n6
		mesBuf.append(cate);
		String type = "1202";// 报文类型n4
		mesBuf.append(type);
		String sqe = getSQE();// 交易序号n16
		mesBuf.append(sqe);
		String entrustDate = DateFormatToString.getToday12();// 委托日期n8
		mesBuf.append(entrustDate);
		String clearDate = getZero(8);//清算日期n8
		mesBuf.append(clearDate);
		
		String send_org_code = autoFill(FileUploadConstants.ORGANIZATION_CODE, 12);//		发起方代码（收费机构代码）an12
		mesBuf.append(send_org_code);
		String orgBranch = getSpace(12);// 机构分支代码an12,选填
		mesBuf.append(orgBranch);
		
		String receive_no = receiveNo;// 接收方代码（付款清算行行号）an12
		mesBuf.append(receive_no);
		String receive_branch = autoFill(receiveBranch, 12);// 接收方分支代码（付款人开户行行号）an12 选填
		mesBuf.append(receive_branch);
		
		
		String data_source = autoFill(dataSource, 42);// 原数据元  an42
		mesBuf.append(data_source);
		/*
		 * 是。由交易种类（6）+机构代码（12）+委托日期（8）+交易序号（16）按顺序组成。
		 */
		String yw_bill =  getZero(16);//业务处理方流水n16
		mesBuf.append(yw_bill);
		String return_code = getSpace(8);// 返回码an8
		mesBuf.append(return_code);
		String info_len = getZero(8);//变长定制信息长度n8
		mesBuf.append(info_len);
		
		String body = mesBuf.toString();
		String head = autoZero(length(body) + "", 8, 1);
		String footer = getMac(body + FileUploadConstants.SOCKET_MAC_KEY);
		
		String message = head + body + footer;
		logger.info("发送："+message);
		
		HProxySendLog hProxySendLog = new HProxySendLog();
		hProxySendLog.setContent(message);
		hProxySendLog.setSendStyle(1);
		hProxySendLog.setStyle(5);
		hProxySendLog.setRemark3(dataSource);
		hProxySendLogService.insertHProxySendLog(hProxySendLog);
		
		String returnCode = "";
		String ret = send(message);
		logger.info("接收："+ret);
		
		HProxySendLog hProxySendLog1 = new HProxySendLog();
		hProxySendLog1.setContent(message);
		hProxySendLog1.setSendStyle(2);
		hProxySendLog1.setStyle(5);
		hProxySendLog1.setRemark3(dataSource);
		hProxySendLogService.insertHProxySendLog(hProxySendLog1);
		
		if("CS".equals(ret)){//超时
			returnCode = "CS";
		}else{
			returnCode = ret.substring(156, 164);// 截取返回码
			logger.info("返回码：" + returnCode);
		}
		return returnCode;
	}

	@Override
	public String daifu(String userCode, String sendNo, String sendAccount, String receiveNo, String receiveBranch,
			String fkAccount, String fkName, String ywCate, String contractNo, String pubSign, String moneyType,
			String moneyStr) throws UnknownHostException, Exception {
		StringBuffer mesBuf = new StringBuffer();// 报文体
		String cate = "100403";// 交易种类n6
		mesBuf.append(cate);
		String type = "1202";// 报文类型n4
		mesBuf.append(type);
		String sqe = getSQE();// 交易序号n16
		mesBuf.append(sqe);
		String entrustDate = DateFormatToString.getToday12();// 委托日期n8
		mesBuf.append(entrustDate);
		String clearDate = getZero(8);//清算日期n8
		mesBuf.append(clearDate);
		
		String user_code = autoFill(userCode, 18);// 原用户编号 ans18
		mesBuf.append(user_code);
		String send_org_code = autoFill(FileUploadConstants.ORGANIZATION_CODE, 12);//		发起方代码（付费机构代码）an12
		mesBuf.append(send_org_code);
		String orgBranch = getSpace(12);// 机构分支代码an12
		mesBuf.append(orgBranch);
		String send_no = autoFill(sendNo, 12);// 发起方开户行行号（付费机构开户行行号）an12
		mesBuf.append(send_no);
		String send_account = autoFill(sendAccount, 35);// 发起方开户账号（付费机构开户账号）ans35
		mesBuf.append(send_account);
		String receive_no = receiveNo;// 接收方代码（收款清算行行号）an12
		mesBuf.append(receive_no);
		String receive_branch = autoFill(receiveBranch, 12);// 接收方分支代码（收款人开户行行号）an12 选填
		mesBuf.append(receive_branch);
		String fk_account = autoFill(fkAccount, 35);//收款人账号ans35
		mesBuf.append(fk_account);
		String fk_name = autoFill(fkName, 60);//收款人名称g60
		mesBuf.append(fk_name);
		String yw_cate = autoZero(ywCate, 5, 1);// 原业务种类n5,传来必须5位
		mesBuf.append(yw_cate);
		String contract_no = autoFill(contractNo, 60);// 原合同（协议）号需要给,计算方式如下 an60
		mesBuf.append(contract_no);
		
		String pub_sign = pubSign;//对公对私标识,对公“G”，对私“S”
		mesBuf.append(pub_sign);
		
		String money_type = moneyType ;//交易货币代码a3
		mesBuf.append(money_type);
		String money = autoZero(moneyStr, 15, 1);//金额 n15
		mesBuf.append(money);
		
		String sh_fee = getZero(15);//手续费n15
		mesBuf.append(sh_fee);
		String fy = getSpace(60);// 附言 g60
		mesBuf.append(fy);
		String yw_bill =  getZero(16);//业务处理方流水n16
		mesBuf.append(yw_bill);
		String return_code = getSpace(8);// 返回码an8
		mesBuf.append(return_code);
		String info_len = getZero(8);//变长定制信息长度n8
		mesBuf.append(info_len);
		
		String body = mesBuf.toString();
		String head = autoZero(length(body) + "", 8, 1);
		String footer = getMac(body + FileUploadConstants.SOCKET_MAC_KEY);
		
		String message = head + body + footer;
		logger.info("发送："+message);
		
		HProxySendLog hProxySendLog = new HProxySendLog();
		hProxySendLog.setContent(message);
		hProxySendLog.setContractNumber(contractNo);
		hProxySendLog.setUserNumber(userCode);
		hProxySendLog.setBank_number(sendNo);
		hProxySendLog.setPayBankNumber(fkAccount);
		hProxySendLog.setSendStyle(1);
		hProxySendLog.setStyle(4);
		hProxySendLog.setRemark1(fkName);
		hProxySendLogService.insertHProxySendLog(hProxySendLog);
		
		String ret = send(message);
		logger.info("接收："+ret);
		
		HProxySendLog hProxySendLog1 = new HProxySendLog();
		hProxySendLog1.setContent(ret);
		hProxySendLog1.setContractNumber(contractNo);
		hProxySendLog1.setUserNumber(userCode);
		hProxySendLog1.setBank_number(sendNo);
		hProxySendLog1.setPayBankNumber(fkAccount);
		hProxySendLog1.setSendStyle(2);
		hProxySendLog1.setStyle(4);
		hProxySendLog1.setRemark1(fkName);
		hProxySendLogService.insertHProxySendLog(hProxySendLog1);
		
		String returnCode = ret.substring(421, 429);// 截取返回码
		logger.info("返回码：" + returnCode);
		return returnCode;
	}
	private String send(String proMessage) {
		String ret = null;
		try{
			Socket socket = new Socket(FileUploadConstants.SOCKET_HOST, Integer.valueOf(FileUploadConstants.SOCKET_PORT));
			socket.setSoTimeout(180000);//2分钟超时
			// 读取服务器端数据
			DataInputStream input = new DataInputStream(socket.getInputStream());
			// 向服务器端发送数据
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.write(proMessage.getBytes("gb2312"));

			InputStreamReader inputStreamReader = new InputStreamReader(input,"gb2312");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String line = "";
			StringBuffer mesBuf = new StringBuffer();
			while ((line = bufferedReader.readLine()) != null) {
				mesBuf.append(line);
			}
			ret = mesBuf.toString();
			socket.close();
		}catch (SocketTimeoutException e) {
			return "CS";
		}catch(Exception e){
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 自动补充空格
	 * 
	 * @param s
	 * @param num
	 * @return
	 */
	public String autoFill(String s, int num) {
		if(s == null)
			s = "";
		int i = length(s);
		if (i < num) {
			return s + getSpace(num - i);
		} else {
			return s;
		}
	}

	/**
	 * 自动补全0
	 * 
	 * @param s
	 * @param num
	 * @param type
	 *            1前补0，2后补0
	 * @return
	 */
	private String autoZero(String s, int num, int type) {
		int i = length(s);
		if (i < num) {
			if (type == 1) {
				return getZero(num - i) + s;
			} else {
				return s + getZero(num - i);
			}
		} else {
			return s;
		}
	}

	/**
	 * 获取空格
	 * 
	 * @param count
	 *            数量
	 * @return
	 */
	private String getSpace(int count) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < count; i++) {
			sb.append(" ");
		}
		return sb.toString();
	}

	/**
	 * 或者0字符串
	 * 
	 * @param count
	 *            数量
	 * @return
	 */
	private String getZero(int count) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < count; i++) {
			sb.append("0");
		}
		return sb.toString();
	}

	/**
	 * 获取交易序号
	 * 
	 * @return
	 */
	public String getSQE() {
		String sysTime = DateFormatToString.getToday11();
		Random random = new Random();
		int i1 = random.nextInt(10);
		int i2 = random.nextInt(10);
		return FileUploadConstants.PHONE_TOU+sysTime.substring(3, sysTime.length()) + i1 + i2;
	}
	public static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}

	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	 * 
	 * @param String
	 *            s 需要得到长度的字符串
	 * @return int 得到的字符串长度
	 */
	public static int length(String s) {
		if (s == null)
			return 0;
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
	}
	public static String getMac(String body){
		return EncryptUtil.md5GB2312(body).toLowerCase();
	}
	
	public static String splitString(String src, int len,int start) {  
        int byteNum = 0;  
        String subStrx = "";
        if (null == src) {  
            System.out.println("The source String is null!");  
            return "";  
        }  
   
        byteNum = src.length();  
        byte bt[] = src.getBytes(); // 将String转换成byte字节数组  
   
        if (len > byteNum) {  
            len = byteNum;  
        }  
   
        // 判断是否出现了截半，截半的话字节对于的ASC码是小于0的值  
        if (bt[len] < 0) {  
            subStrx = new String(bt, start, --len);  
        } else {  
            subStrx = new String(bt, start, len);  
        }
        return subStrx;
    }  
	
	public static int getWordCount(String s)
    {

        s = s.replaceAll("[^\\x00-\\xff]", "**");
        int length = s.length();
        return length;
    }
	
	public static void main(String args[]){
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String jiqequ = "100301120200161209165647882016120900000000001305655100000002742305655               10558101001344050140010100000187               313100000013            6029693033409430                   丁辉                                                        00100011000742305655001003136029693033409430                     CNY000000000270286000000000000000                                                            0000000000000000";
		String ret = "    00000473100301121200161209165647882016120920161210001305655100000002742305655               10558101001344050140010100000187               313100000013            6029693033409430                   丁辉                                                        00100000000000000000000000000000000000000000000000000000000000000CNY000000000270286000000000000000                                                            0000000000000000CT1O90010000000001bdc9b62f163971837352266912d1350";
		int len = length("  0000047310030112120016120916564788201612092016");
		System.out.println("0000047310030112120016120916564788201612092016".length());
		System.out.println(getWordCount(ret));
		
//		String returnCode = splitString(ret,8,len+8);// 截取返回码
		String returnCode = ret.substring(jiqequ.length()+8, jiqequ.length()+16);// 截取返回码
		//截取日期
		String datestr = ret.substring(46, 50);
		logger.info("返回码：" + returnCode);
		returnCode = returnCode + "_" + datestr;
		logger.info("返回码：" + returnCode);
	}
}
