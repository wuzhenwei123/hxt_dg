package com.base.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 获取配置文件
 * 
 * @author ll
 *
 */
public class FileUploadConstants {

	/** 文件上传根目录* */
	public static String UPLOAD_FILE_ROOT = "";
	/** 头像上传路径 **/
	public static String HEAD_PATH = "";
	public static String PIC_PATH = "";
	
	/** 项目目录 **/
	public static String PROJECT_PATH = "";
	/** 客户roleId**/
	public static String COMPANY_ROLE_ID = "";
	/** 业务员roleid**/
	public static String YWY_ROLE_ID = "";
	/** 支付回调前台**/
	public static String FRONT_URL = "";
	/** 支付回调后台**/
	public static String BACK_URL = "";
	/** 支付提交网关**/
	public static String GATE_WAY = "";
	/** 调单地址**/
	public static String END_POINT = "";
	/** 调单查询**/
	public static String QUERY_ACTION = "";
	/** 调单销账**/
	public static String PAY_ACTION = "";
	/** 调单key**/
	public static String PRI_KEY = "";
	/** 终端ＩＤ**/
	public static String TERMINAL_ID = "";
	/** 终端Key**/
	public static String KEY_ID = "";
	/** 访问地址**/
	public static String URL_PATH = "";
	/** appid**/
	public static String APPID = null;
	/** appsecret**/
	public static String APPSECRET = null;
	
	/** 一级代理商管理员角色ID **/
	public static String ONE_AGENT_MANAGE_ROLEID = null;
	/** 二级代理商管理员角色ID **/
	public static String TWO_AGENT_MANAGE_ROLEID = null;
	/** 服务人员角色ID **/
	public static String SERVICER_MANAGE_ROLEID = null;
	/** 代扣人员角色ID **/
	public static String PROXY_ROLEID = null;
	/** 税率小于1**/
	public static String TAX_RATE = null;
	/** 收费机构组织代码**/
	public static String ORGANIZATION_CODE = null;
	/** 借贷标志**/
	public static String LOAN_CODE = null;
	/** 费用类型代码（电费）**/
	public static String SERVICE_ELECTRICITY_CODE = null;
	/** 代付地址**/
	public static String SOCKET_HOST = null;
	/** 代付端口**/
	public static String SOCKET_PORT = null;
	/** 代付key**/
	public static String SOCKET_MAC_KEY = null;
	/** 收费机构开户行行号**/
	public static String SF_BANK_NUMBER = null;
	/** 收费机构银行账户**/
	public static String SF_BANK_ACCOUNT = null;
	/** 图文信息地址**/
	public static String TW_PATH = null;
	/** token文件**/
	public static String TOKEN_FILE = null;
	/** 手机支付开头**/
	public static String PHONE_TOU = null;
	
	private static Properties prop = null;
	static {
		String path = "/com/conf/system_windows.properties";
		if (isLinux()) {
			path = "/com/conf/system_linux.properties";
		}
		InputStream in = FileUploadConstants.class.getResourceAsStream(path);
		if (in != null) {
			prop = new Properties();
			try {
				prop.load(in);
				UPLOAD_FILE_ROOT = (String) prop.get("upload_file_root");
				HEAD_PATH = (String) prop.get("head_path");
				PIC_PATH = (String) prop.get("pic_path");
				PROJECT_PATH = (String) prop.get("project_path");
				COMPANY_ROLE_ID = (String) prop.get("company_role_id");
				YWY_ROLE_ID = (String) prop.get("yewuyuan_role_id");
				FRONT_URL = (String) prop.get("frontUrl");
				BACK_URL = (String) prop.get("backUrl");
				GATE_WAY = (String) prop.get("gate_way");
				END_POINT = (String) prop.get("endpoint");
				QUERY_ACTION = (String) prop.get("queryActionString");
				PAY_ACTION = (String) prop.get("payActionString");
				PRI_KEY = (String) prop.get("priKey");
				TERMINAL_ID = (String) prop.get("TerminalID");
				KEY_ID = (String) prop.get("KeyID");
				URL_PATH = (String) prop.get("urlPath");
				TW_PATH = (String) prop.get("tw_path");
				
				ONE_AGENT_MANAGE_ROLEID = (String) prop.get("one_agent_manage_roleId");
				TWO_AGENT_MANAGE_ROLEID = (String) prop.get("two_agent_manage_roleId");
				SERVICER_MANAGE_ROLEID = (String) prop.get("servicer_manage_roleId");
				APPID = (String) prop.get("appid");
				APPSECRET = (String) prop.get("appsecret");
				TAX_RATE = (String) prop.get("tax_rate");
				PROXY_ROLEID = (String) prop.get("proxy_roleId");
				ORGANIZATION_CODE = (String) prop.get("organization_code");
				LOAN_CODE = (String) prop.get("loan_code");
				SERVICE_ELECTRICITY_CODE = (String) prop.get("service_electricity_code");
				SOCKET_HOST = (String) prop.get("socket_host");
				SOCKET_PORT = (String) prop.get("socket_port");
				SOCKET_MAC_KEY = (String) prop.get("socket_mac_key");
				SF_BANK_NUMBER = (String) prop.get("sf_bank_number");
				SF_BANK_ACCOUNT = (String) prop.get("sf_bank_account");
				TOKEN_FILE = (String) prop.get("token_file");
				PHONE_TOU = (String) prop.get("phone_tou");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static String getPropValue(String key) {
		String path = "/com/conf/system_windows.properties";
		String val = null;
		if (isLinux()) {
			path = "/com/conf/system_linux.properties";
		}
		InputStream in = FileUploadConstants.class.getResourceAsStream(path);
		if (in != null) {
			prop = new Properties();
			try {
				prop.load(in);
				val = prop.getProperty(key);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("=====================get property file error!");
			}
		}
		return val;
	}

	public static boolean isLinux() {
		String osType = System.getProperties().getProperty("os.name").toLowerCase();
		if (osType.startsWith("windows")) {
			return false;
		} else {
			return true;
		}
	}
	
	public  static boolean mkdirs(String path) {
		boolean result = false;
		File file = new File(path);
		// 如果文件存在直接返回
		if (file.exists()) {
			result = true;
		}
		// 否则创建路径
		else {
			result = file.mkdirs();
		}
		return result;
	}
	
	public static String parseToPath(String str) {
		str = str.replace('\\', '/');
		str = str.replaceAll("/{2,}","/");
		return str;
	 }	 
	 
	 public static  void saveFile(InputStream inputStream, String fileName) throws IOException {
		FileOutputStream fos = new FileOutputStream(new File(fileName));

		byte[] buff = new byte[1024*4];
		int flag = 0;

		while ((flag = inputStream.read(buff, 0, buff.length)) != -1) {
			fos.write(buff, 0, flag);
		}

		fos.close();
	}
}
