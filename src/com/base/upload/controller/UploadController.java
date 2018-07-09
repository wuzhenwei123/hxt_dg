package com.base.upload.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.base.controller.UploadAction;

/**
 * @author keeny
 * @time 2015年02月04日 21:27:20
 */
@Controller
@RequestMapping("/upload")
public class UploadController extends UploadAction{

	// private static Logger logger =
	// Logger.getLogger(AdminRoleController.class); //Logger

	/***************** 页面中转 *********************/
	@RequestMapping(value = "/exec")
	public String exec(@RequestParam("file")MultipartFile file,HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		JSONObject reObject = new JSONObject();
		reObject.put("code", -1);
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		try {
			reList = super.uploads(file, request);
			reObject.put("code", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		reObject.put("list", reList);
		write(reObject, response);
		return null;
	}
}
