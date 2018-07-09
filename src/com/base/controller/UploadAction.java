package com.base.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.base.utils.DateFormatToString;
import com.base.utils.PicCompression;

public class UploadAction extends BaseController{
	private static Logger logger = Logger.getLogger(UploadAction.class); //Logger
	private long maxfilesize =  1024*1027*10;
	public List<Map<String, Object>> uploads(MultipartFile[] files,HttpServletRequest request) throws Exception {
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
        String proVal = request.getParameter("proVal");
        String filetype = request.getParameter("filetype");
        try {
            String fileToday = DateFormatToString.getToday1();
            String saveFilePath = getSavePath(proVal);
            for (MultipartFile file : files) {
                String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);//扩展名
                if(file.getSize() > maxfilesize){
                    throw new Exception("您上传的文件大小已经超出范围");
                }
                String newFileName = File.separator + fileToday + File.separator + getUUID() + "." + suffix;// 新路径
                File file1 = new File(saveFilePath + newFileName);
				if (!file1.getParentFile().exists()) {
					file1.getParentFile().mkdirs();
				}
				file.transferTo(file1);
				file1.createNewFile();
				logger.info(file1.getAbsolutePath());
				Map<String, Object> rtnMap = new HashMap<String, Object>();
				if (StringUtils.isNotBlank(filetype) && filetype.equals("pic")) {
					PicCompression.resize(file1, new File(getSPic(file1.getAbsolutePath(), "_l")));
					PicCompression.resize(file1, new File(getSPic(file1.getAbsolutePath(), "_m")), 200);
					PicCompression.resize(file1, new File(getSPic(file1.getAbsolutePath(), "_s")), 100);
					newFileName = getSPic(newFileName, "_l");
				}
				rtnMap.put("newName", newFileName.replace("\\", "/"));
				rtnMap.put("oldName", file.getOriginalFilename());
				reList.add(rtnMap);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return reList;
    }
	public List<Map<String, Object>> uploads(MultipartFile file,HttpServletRequest request) throws Exception {
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		String proVal = request.getParameter("proVal");
//		if (StringUtils.isBlank(proVal)) {
//			return reList;
//		}
		String filetype = request.getParameter("filetype");
		try {
			String fileToday = DateFormatToString.getToday1();
			String contextPath = request.getSession().getServletContext().getRealPath("/");
			String saveFilePath = contextPath;
				String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);//扩展名
				if(file.getSize() > maxfilesize){
					throw new Exception("您上传的文件大小已经超出范围");
				}
				String newFileName = getSavePath(proVal)+File.separator + fileToday + File.separator + getUUID() + "." + suffix;// 新路径
				File file1 = new File(saveFilePath + newFileName);
				if (!file1.getParentFile().exists()) {
					file1.getParentFile().mkdirs();
				}
				file.transferTo(file1);
				file1.createNewFile();
				
				if (StringUtils.isNotBlank(filetype) && filetype.equals("pic")) {
					PicCompression.resize(file1, new File(getSPic(file1.getAbsolutePath(), "_l")));
					PicCompression.resize(file1, new File(getSPic(file1.getAbsolutePath(), "_m")), 200);
					PicCompression.resize(file1, new File(getSPic(file1.getAbsolutePath(), "_s")), 100);
					newFileName = getSPic(newFileName, "_l");
				}
				
				Map<String, Object> rtnMap = new HashMap<String, Object>();
				rtnMap.put("newName", newFileName.replace("\\", "/"));
				rtnMap.put("oldName", new String(file.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8"));
				reList.add(rtnMap);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return reList;
	}
	private String getSPic(String path, String exName) {

		if (path != null) {
			String ex = path.substring(path.lastIndexOf("."), path.length());
			String fp = path.substring(0, path.lastIndexOf(".")) + exName;
			return fp + ex;
		}
		return null;
	}
	private String getUUID(){
		String uuName = System.currentTimeMillis() + UUID.randomUUID().toString().split("-")[0];// 新文件名
		return uuName;
	}
	private String getSavePath(String proVal){
		String realPath = "/" + "upload";
		if(StringUtils.isNotBlank(proVal)){
			realPath += "/"+proVal;
		}
		return realPath;
	}
}
