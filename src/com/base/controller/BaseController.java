package com.base.controller;

import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.utils.FileUploadConstants;
import com.base.utils.MD5;
import com.base.utils.ResultRsp;
import com.base.utils.StandardOutput;
import com.wx.service.WeiXinService;

/**
 * 
 * @author zhanglib
 *
 */
public class BaseController {
	
	@Autowired
	private WeiXinService weiXinService;
	
	protected static MD5 MD5 = new MD5();

	protected void writeSuccessMsg(String message, Object data, HttpServletResponse paramHttpServletResponse) {
		ResultRsp localObject = new ResultRsp("1", message, data);
		StandardOutput.printObject(paramHttpServletResponse, localObject);
	}

	protected void writeErrorMsg(String message, Object data, HttpServletResponse paramHttpServletResponse) {
		ResultRsp localObject = new ResultRsp("-1", message, data);
		StandardOutput.printObject(paramHttpServletResponse, localObject);
	}

	protected void addSession(HttpServletRequest request, String key, Object value) {
		if (key != null) {
			request.getSession().setAttribute(key, value);
		}
	}
	protected void removeSession(HttpServletRequest request, String key) {
		if (key != null) {
			request.getSession().removeAttribute(key);
		}
	}
	protected Object getSession(HttpServletRequest request, String key) {
		if (key != null) {
			return request.getSession().getAttribute(key);
		}
		return null;
	}
	protected void write(Object data, HttpServletResponse paramHttpServletResponse) {
		StandardOutput.printObject(paramHttpServletResponse, data);
	}
	
	/**
	 * 微信分享获取的参数
	 * @param request
	 */
	protected void getJsticket(HttpServletRequest request){
		String   url  = request.getScheme()+"://"; //请求协议 http 或 https  
		url+=request.getHeader("host");  // 请求服务器  
		url+=request.getRequestURI();     // 工程名    
		if(request.getQueryString()!=null) //判断请求参数是否为空
		url+="?"+request.getQueryString();   // 参数 
		System.out.println("url================="+url);
		try {
			Map<String,String> map = weiXinService.getJsticket(FileUploadConstants.APPID, FileUploadConstants.APPSECRET, url);
			request.setAttribute("noncestr", map.get("noncestr"));
			request.setAttribute("timestamp", map.get("timestamp"));
			request.setAttribute("signature", map.get("signature"));
			request.setAttribute("appid", FileUploadConstants.APPID);
			request.setAttribute("server_href", FileUploadConstants.URL_PATH);
			String sceneId = "";
			for(int i=0;i<32;i++){
				sceneId += (new Random().nextInt(9)+1);
			}
			request.setAttribute("scene_id", sceneId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getMoney(Integer money){
		String totalFeeStr1 = null;
		if(money!=null&&money>0){
			if(String.valueOf(money).length()>2){
				int fen = money%100;
				if(fen>0&&fen<10){
					totalFeeStr1 = money/100 + ".0" + fen;
				}else if(fen>=10){
					totalFeeStr1 = money/100 + "." + fen;
				}else{
					totalFeeStr1 = money/100 + ".00";
				}
			}else if(String.valueOf(money).length()==1){
				totalFeeStr1 = "0.0"+money;
			}else{
				totalFeeStr1 = "0."+money;
			}
		}else{
			totalFeeStr1 = "0.00";
		}
		return totalFeeStr1;
	}
	
	public String getMoneyL(Long money){
		String totalFeeStr1 = null;
		if(money!=null&&money>0){
			if(String.valueOf(money).length()>2){
				Long fen = money%100;
				if(fen>0&&fen<10){
					totalFeeStr1 = money/100 + ".0" + fen;
				}else if(fen>=10){
					totalFeeStr1 = money/100 + "." + fen;
				}else{
					totalFeeStr1 = money/100 + ".00";
				}
			}else if(String.valueOf(money).length()==1){
				totalFeeStr1 = "0.0"+money;
			}else{
				totalFeeStr1 = "0."+money;
			}
		}else{
			totalFeeStr1 = "0.00";
		}
		return totalFeeStr1;
	}
	
	/**
	 * 获取登陆这的IP地址
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }
}
