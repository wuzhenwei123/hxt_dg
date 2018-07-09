package com.base.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class ResponseUtil {

	private static Logger logger = Logger.getLogger(ResponseUtil.class);

	private static HttpURLConnection getConnection(URL url, String method,
			String ctype) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(method);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Accept", "text/xml,text/javascript");
		conn.setRequestProperty("User-Agent", "getop");
		conn.setRequestProperty("Content-Type", ctype);
		return conn;
	}
	
	private static boolean areNotEmpty(String... values) {
		boolean result = true;
		if (values == null || values.length == 0) {
			result = false;
		} else {
			for (String value : values) {
				result &= !isEmpty(value);
			}
		}
		return result;
	}
	
	private static boolean isEmpty(String value) {
		int strLen;
		if (value == null || (strLen = value.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(value.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}
	
	private static String buildQuery(Map<String, String> params, String charset) throws IOException {
		if (params == null || params.isEmpty()) {
			return null;
		}

		StringBuilder query = new StringBuilder();
		Set<Entry<String, String>> entries = params.entrySet();
		boolean hasParam = false;

		for (Entry<String, String> entry : entries) {
			String name = entry.getKey();
			String value = entry.getValue();
			// 忽略参数名或参数值为空的参数
			if (areNotEmpty(name, value)) {
				if (hasParam) {
					query.append("&");
				} else {
					hasParam = true;
				}

				query.append(name).append("=").append(URLEncoder.encode(value, charset));
			}
		}

		return query.toString();
	}

	/**
	 * 执行HTTP POST请求。
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param charset
	 *            字符集，如UTF-8, GBK, GB2312
	 * @return 响应字符串
	 * @throws IOException
	 */
	public static String doPost(String url, Map<String, String> params)
			throws IOException {
		HttpURLConnection conn = null;
		OutputStream out = null;
		InputStream in = null;
		String rsp = null;
		String charset = "UTF-8";
		try {
			String ctype = "application/x-www-form-urlencoded;charset="
					+ charset;
			conn = getConnection(new URL(url), "GET", ctype);
			out = conn.getOutputStream();
			if (params != null) {
				String query = buildQuery(params, charset);
				out.write(query.getBytes(charset));
			}
			in = conn.getInputStream();
			rsp = getResponseAsString(in,
					getResponseCharset(conn.getContentType()));
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}

		return rsp;
	}
	
	private static String getResponseAsString(InputStream in, String charset) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, charset));
		StringWriter writer = new StringWriter();

		char[] chars = new char[512];
		int count = 0;
		while ((count = reader.read(chars)) > 0) {
			writer.write(chars, 0, count);
		}

		return writer.toString();
	}
	
	private static String getResponseCharset(String ctype) {
		String charset = "UTF-8";

		if (!isEmpty(ctype)) {
			String[] params = ctype.split(";");
			for (String param : params) {
				param = param.trim();
				if (param.startsWith("charset")) {
					String[] pair = param.split("=", 2);
					if (pair.length == 2) {
						if (!isEmpty(pair[1])) {
							charset = pair[1].trim();
						}
					}
					break;
				}
			}
		}

		return charset;
	}

	/**
	 * 读取网络资源
	 * 
	 * @throws IOException
	 */
	public static String readHttpResource(String url, Map<String, String> param)
			throws IOException {
		HttpURLConnection httpConn = null;
		BufferedReader rd = null;
		try {
			StringBuilder sb = new StringBuilder();

			StringBuilder params = new StringBuilder();
			params.append("?");
			Set<String> keys = param.keySet();
			for (String key : keys) {
				params.append(key);
				params.append("=");
				params.append(param.get(key));
				params.append("&");
			}
			params.deleteCharAt(params.length() - 1);

			URL realUrl = new URL(url + params.toString());
			httpConn = (HttpURLConnection) realUrl.openConnection();
			int responseCode = httpConn.getResponseCode();
			if (responseCode == 200) {
				rd = new BufferedReader(new InputStreamReader(
						httpConn.getInputStream(), "gbk"));
				String line;
				while ((line = rd.readLine()) != null) {
					sb.append(line);
				}
				return sb.toString();
			}
		} catch (IOException e) {
			logger.error("url is :" + url);
			throw e;
		} finally {
			if (rd != null)
				try {
					rd.close();
				} catch (Exception e) {
				}
			if (httpConn != null)
				try {
					httpConn.disconnect();
				} catch (Exception e) {
				}
		}

		return null;
	}

	/**
	 * 读取网络资源
	 * 
	 * @throws IOException
	 */
	public static String readHttpResource(String url,
			Map<String, String> param, String charset) throws IOException {
		HttpURLConnection httpConn = null;
		BufferedReader rd = null;
		try {
			StringBuilder sb = new StringBuilder();

			StringBuilder params = new StringBuilder("");
			if(param != null){
				params.append("?");
				Set<String> keys = param.keySet();
				for (String key : keys) {
					params.append(key);
					params.append("=");
					params.append(param.get(key));
					params.append("&");
				}
				params.deleteCharAt(params.length() - 1);
			}
			URL realUrl = new URL(url + params.toString());
			httpConn = (HttpURLConnection) realUrl.openConnection();
			int responseCode = httpConn.getResponseCode();
			if (responseCode == 200) {
				rd = new BufferedReader(new InputStreamReader(
						httpConn.getInputStream(), charset));
				String line;
				while ((line = rd.readLine()) != null) {
					sb.append(line);
				}
				return sb.toString();
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (rd != null)
				try {
					rd.close();
				} catch (Exception e) {
				}
			if (httpConn != null)
				try {
					httpConn.disconnect();
				} catch (Exception e) {
				}
		}

		return null;
	}

	/**
	 * 检测字符串
	 * 
	 * @param value
	 * @return
	 */
	public static boolean checkString(String value) {

		if (value != null && !"".equals(value)) {
			return true;
		}
		return false;
	}

	public static void responseText(HttpServletResponse response, String result) {
		responseText(response, result, null);
	}

	/**
	 * ajax相应
	 * 
	 * @param response
	 * @param result
	 * @param callback
	 */
	public static void responseText(HttpServletResponse response,
			String result, String callback) {

		// 指定内容类型
		response.setContentType("text/html;charset=UTF-8");
		// 禁止缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = null;
		try {
			if (result == null) {
				result = "";
			}
			out = response.getWriter();
			if (callback != null && !"".equals(callback))
				out.print(callback + "(" + result + ")");
			else
				out.print(result);

			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

}
