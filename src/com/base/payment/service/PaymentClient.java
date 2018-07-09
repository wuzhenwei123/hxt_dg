package com.base.payment.service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Random;

import com.base.utils.DateFormatToString;
import com.base.utils.FileUploadConstants;

public interface PaymentClient {
//	static final String MAC_KEY = FileUploadConstants.SOCKET_MAC_KEY;
//	static final String HOST = FileUploadConstants.SOCKET_HOST;
//	static final int PORT = Integer.valueOf(FileUploadConstants.SOCKET_PORT);
	/**
	 * 新增
	 * @param contractNo  合同（协议）号
	 * @param userCode 用户编号
	 * @param userName 用户名称
	 * @param phone  联系人电话
	 * @param ywCate 业务种类
	 * @param fkNo 付款行行号
	 * @param fkAccount 付款人账号
	 * @param fkName 付款人名称
	 * @return returnCode 服务器返回码（含义请查看手册）
	 * @throws IOException
	 * @throws UnknownHostException 远程地址异常
	 */
	public String add(String contractNo, String userCode, String userName, String phone, String ywCate, String fkNo,
			String fkAccount, String fkName) throws UnknownHostException, Exception;
	/**
	 * 变更
	 * @param contractNo   原合同（协议）号
	 * @param userCode  原用户编号
	 * @param userName  原用户名称
	 * @param ywCate 业务种类
	 * @param fkNo  原付款行行号
	 * @param fkAccount  原付款人账号
	 * @param fkName  原付款人名称
	 * @return returnCode 服务器返回码（含义请查看手册）
	 * @throws IOException
	 * @throws UnknownHostException 远程地址异常
	 */
	public String update(String contractNo, String userCode, String userName,String payPhoneNumber, String ywCate,
			String fkNo, String fkAccount, String fkName) throws UnknownHostException, Exception;
	
	/**
	 * 撤销
	 * @param contractNo  合同（协议）号
	 * @param userCode 用户编号
	 * @param userName 用户名称
	 * @param ywCate 业务种类
	 * @param fkNo 付款行行号
	 * @param fkAccount 付款人账号
	 * @param fkName 付款人名称
	 * @return returnCode 服务器返回码（含义请查看手册）
	 * @throws IOException
	 * @throws UnknownHostException 远程地址异常
	 */
	public String revoke(String contractNo, String userCode, String userName, String ywCate,
			String fkNo, String fkAccount, String fkName) throws UnknownHostException, Exception;
	/**
	 * 代收
	 * @param userCode 用户编号
	 * @param sendNo	发起方开户行行号（收费机构开户行行号）
	 * @param sendAccount	发起方开户账号（收费机构开户账号）
	 * @param receiveNo	接收方代码（付款清算行行号）（长度12）
	 * @param receiveBranch	接收方分支代码（付款人开户行行号，长度12）选填
	 * @param fkAccount	付款人账号
	 * @param fkName	付款人名称
	 * @param ywCate	业务种类(长度5)
	 * @param contractNo	合同（协议）号
	 * @param moneyType	交易货币代码 （长度3）
	 * @param moneyStr	金额
	 * @return returnCode 服务器返回码（含义请查看手册）
	 * @throws IOException
	 * @throws UnknownHostException 远程地址异常
	 */
	public String daishou(String userCode,String sendNo,String sendAccount,String receiveNo,String receiveBranch,String fkAccount,String fkName,String ywCate,String contractNo,String moneyType,String moneyStr,String sqe) throws UnknownHostException, Exception;
	/**
	 * 冲正
	 * @param receiveNo 接收方代码（付款清算行行号）
	 * @param receiveBranch	接收方分支代码（付款人开户行行号）
	 * @param dataSource	原数据元
	 * @return returnCode 服务器返回码（含义请查看手册）
	 * @throws IOException
	 * @throws UnknownHostException 远程地址异常
	 */
	public String correct(String receiveNo,String receiveBranch,String dataSource)throws UnknownHostException, Exception;
	/**
	 * 代付
	 * @param userCode	用户编号
	 * @param sendNo	发起方开户行行号（付费机构开户行行号）
	 * @param sendAccount	发起方开户账号（付费机构开户账号）
	 * @param receiveNo	接收方代码（收款清算行行号）
	 * @param receiveBranch	接收方分支代码（收款人开户行行号）
	 * @param fkAccount	收款人账号
	 * @param fkName	收款人名称
	 * @param ywCate	业务种类
	 * @param contractNo	合同（协议）号
	 * @param pubSign	对公对私标识
	 * @param moneyType	交易货币代码
	 * @param moneyStr	金额
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public String daifu(String userCode,String sendNo,String sendAccount,String receiveNo,String receiveBranch,String fkAccount,String fkName,String ywCate,String contractNo,String pubSign,String moneyType,String moneyStr)throws UnknownHostException, Exception;
	
	
	/**
	 * 获取交易序号
	 * 
	 * @return
	 */
	public String getSQE();
	
	
	public String autoFill(String s, int num);
}
