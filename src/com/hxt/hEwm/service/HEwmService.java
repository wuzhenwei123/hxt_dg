package com.hxt.hEwm.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hxt.hEwm.dao.HEwmDAO;
import com.hxt.hEwm.model.HEwm;
import com.wx.service.WeiXinService;
import com.wx.utils.https.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.utils.ResponseList;
import com.base.utils.https.HttpUtils;

/**
 * @author	wuzhenwei
 * @time	2015年08月31日 19:04:33
 */
 @Service("hEwmService")
public class HEwmService {
	 
	@Resource(name = "hEwmDao")
    private HEwmDAO hEwmDAO;
	@Autowired
	private WeiXinService weiXinService;
    
    public ResponseList<HEwm> getHEwmList(HEwm hEwm) {
        return hEwmDAO.getHEwmList(hEwm);
    }
    
    public List<HEwm> getHEwmBaseList(HEwm hEwm) {
        return hEwmDAO.getHEwmBaseList(hEwm);
    }
    
    public int getHEwmListCount(HEwm hEwm) {
        return hEwmDAO.getHEwmListCount(hEwm);
    }

    public HEwm getHEwm(HEwm hEwm) { 
        return hEwmDAO.getHEwm(hEwm);
    }

    public int insertHEwm(HEwm hEwm) throws Exception {
        return hEwmDAO.insertHEwm(hEwm);
    }

    public int updateHEwm(HEwm hEwm) throws Exception {
        return hEwmDAO.updateHEwm(hEwm);
    }
    
    public int removeHEwm(HEwm hEwm) throws Exception {
        return hEwmDAO.removeHEwm(hEwm);
    }

	/**
	 * 获取accessToken
	 */
    public static Map<String, JSONObject> TOKEN_MAP = null;
	public String getAccessToken(String appid, String secret) throws Exception {
		if (TOKEN_MAP == null) {
			TOKEN_MAP = new HashMap<String, JSONObject>();
		} else {
			JSONObject tokenJson = TOKEN_MAP.get(appid);
			if (tokenJson != null) {
				int expires_in = (Integer) tokenJson.getInteger("expires_in");// token时间
				Long time = (new Date()).getTime() / 1000;
				if (time.intValue() < expires_in) {
					return tokenJson.getString("access_token");
				}
			}
		}
//		HttpGet get = HttpClientConnectionManager.getGetMethod(WxApiURL.ACCESSTOKEN_URL + "&appid=" + appid + "&secret=" + secret);
//		HttpResponse response = httpclient.execute(get);
//		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		
//		String jsonStr = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token", "grant_type=client_credential&appid=" + appid + "&secret=" + secret);
		
		String jsonStr = HttpUtils.httpsRequest("https://api.weixin.qq.com/cgi-bin/token", "GET", "grant_type=client_credential&appid=" + appid + "&secret=" + secret);
		
		JSONObject object = JSON.parseObject(jsonStr);
		if (object.getInteger("expires_in") != null) {
			Long time = (new Date()).getTime() / 1000;
			int expires_in = time.intValue() + 7000;
			object.put("expires_in", expires_in);
			TOKEN_MAP.put(appid, object);
		}
		return object.getString("access_token");
	}
    /**
     * 生成关注二维码(永久)
     * @param appid   公众号ＩＤ
     * @param secret  公众号密码
     * @param scene_str  场景编码
     * @return imgurl  二维码地址
     */
	public Map<String,String> getEWMYj(String appid, String secret,String scene_str){
		Map<String,String> map = new HashMap<String,String>();
		try{
			String accessToken = weiXinService.getAccessToken(appid, secret);
			String json = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\":\""+scene_str+"\"}}}";
			
			URL url = new URL("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+accessToken);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			byte[] buf = json.getBytes();

			httpConn.setRequestProperty("Content-Length", String.valueOf(buf.length));
			httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			httpConn.setRequestMethod("POST");
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			OutputStream out = httpConn.getOutputStream();
			out.write(buf);
			out.close();

			byte[] datas = readInputStream(httpConn.getInputStream());
			String jsonStr = new String(datas,"utf-8");
			
			JSONObject object = JSON.parseObject(jsonStr);
			String ticket = object.getString("ticket");
			if(StringUtils.isNotBlank(ticket)){
				String imgurl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+URLEncoder.encode(ticket);
				map.put("imgurl", imgurl);
				map.put("ticket", ticket);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
    /**
     * 保存永久二维码
     * @param imgurl
     * @param ticket
     * @param openId
     * @return
     */
    public int saveEwmYj(String imgurl,String ticket,String openId){
    	int id= 0;
    	try{
    		//保存数据库
			HEwm hEwm = new HEwm();
			hEwm.setOpenId(openId);
			hEwm.setImgUrl(imgurl);
			hEwm.setCreateTime(new Date());
			hEwm.setScene_id(ticket);
			hEwm.setStyle(2);
			id = hEwmDAO.insertHEwm(hEwm);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return id;
    }
    
    /**
     * 根据openId获取二维码（适用于永久二维码获取）
     * @param openId
     * @return 
     */
    public HEwm getHEwmByOpenId(String openId){
    	HEwm hEwm = null;
    	try{
    		HEwm hEwm1 = new HEwm();
			hEwm1.setOpenId(openId);
			hEwm1.setStyle(2);
			hEwm = hEwmDAO.getHEwm(hEwm1);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return hEwm;
    }
	/**
	 * 从输入流中读取数据
	 * 
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();// 网页的二进制数据
		outStream.close();
		inStream.close();
		return data;
	}
}
