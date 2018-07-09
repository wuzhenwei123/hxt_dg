package com.base.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

public class UploadUtil {

	/**
	 * 文件上传操作
	 * 
	 * @param request
	 * @param basePath
	 *            指定文件存放路径
	 * @param serverPath
	 *            指定服务器存放文件路径
	 * @return
	 */
	public static List<Map<String, Object>> uploadfile(HttpServletRequest request) {

		// 获取文件扩展名
		String proVal = request.getParameter("proVal");
		String type = request.getParameter("type");
		UUID.randomUUID();

		// String millToday = DateFormatToString.getToday6();//新文件名
		String millToday = System.currentTimeMillis() + UUID.randomUUID().toString().split("-")[0];// 新文件名
		String fileToday = DateFormatToString.getToday1();// 用来建目录

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(8192);// 每次内存读取字节数

		// 获取保存文件的最终路径
		String saveFilePath = getSavePath(proVal);
		if (saveFilePath == null) {
			return null;
		}

		factory.setRepository(new File(saveFilePath));
		ServletFileUpload upload = new ServletFileUpload(factory);
		List uploadlist = null;
		try {
			uploadlist = upload.parseRequest(request);
		} catch (FileUploadException e1) {
			factory = null;
			upload = null;
			uploadlist = null;
			e1.printStackTrace();
			// 如果是上传出现异常，删除文件
			// FileUtil.del(saveFilePath);
			return null;
		}

		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();

		String oldFileName = "", newFileName = "";
		try {
			for (int i = 0; i < uploadlist.size(); i++) {
				FileItem item = (FileItem) uploadlist.get(i);
				if (!item.isFormField()) {// 处理文件上传域// 忽略其他不是文件域的所有表单信息
					try {

						String fullPath = item.getName();// 取到客户端完整 路径+文件名
						oldFileName = FilenameUtils.getName(fullPath);// 取到文件名
						String ext = FilenameUtils.getExtension(fullPath);// 扩展名
						newFileName = fileToday + "/" + millToday + "." + ext;// 新路径
						File file = new File(saveFilePath + newFileName);
						if (!file.getParentFile().exists()) {
							file.getParentFile().mkdirs();
						}
						item.write(file);// 保存文件路径

						if (type.equals("pic")) {
							PicCompression.resize(file, new File(getSPic(file.getAbsolutePath(), "_l")));
							PicCompression.resize(file, new File(getSPic(file.getAbsolutePath(), "_m")), 200);
							PicCompression.resize(file, new File(getSPic(file.getAbsolutePath(), "_s")), 100);
						}

					} catch (Exception e) {
						e.printStackTrace();
						throw e;
					}
					item.delete();

					break;
				}
				item.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory = null;
			upload = null;
			uploadlist = null;
		}

		Map<String, Object> rtnMap = new HashMap<String, Object>();
		rtnMap.put("newName", newFileName);
		rtnMap.put("oldName", oldFileName);

		reList.add(rtnMap);
		return reList;
	}

	public static String getSPic(String path, String exName) {

		if (path != null) {
			String ex = path.substring(path.lastIndexOf("."), path.length());
			String fp = path.substring(0, path.lastIndexOf(".")) + exName;
			return fp + ex;
		}
		return null;
	}

	/**
	 * @param propPath
	 *            : 上传的根路径
	 * @param uploadPath
	 *            ： 上传文件存储临时路径
	 * @param newFileNameNoExt
	 *            ：存放文件的文件名（不带扩展名）
	 * @param fileType
	 *            ：文件扩展名（带.）
	 * @return
	 */

	private static String getSavePath(String proVal) {

		if (proVal != null && proVal.equals("")) {
			proVal = "";
		}
		String saveFilePath = FileUploadConstants.UPLOAD_FILE_ROOT + FileUploadConstants.getPropValue(proVal);
		if (saveFilePath == null || saveFilePath.equals("")) {
			return null;
		}
		saveFilePath += "/";
		if (!saveFilePath.endsWith("/"))
			saveFilePath += "/";

		// 生成文件保存路径

		File aSaveFile = new File(saveFilePath);
		if (!aSaveFile.isDirectory())
			aSaveFile.mkdirs();

		return saveFilePath;
	}

	public static void main(String[] args) {
		String millToday = System.currentTimeMillis() + UUID.randomUUID().toString().split("-")[0];// 新文件名
		System.out.println(millToday);
	}
}
