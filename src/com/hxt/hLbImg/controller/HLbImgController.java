package com.hxt.hLbImg.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import sun.reflect.misc.FieldUtil;

import com.hxt.hLbImg.model.HLbImg;
import com.hxt.hLbImg.service.HLbImgService;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.base.utils.FileUploadConstants;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SessionName;
import com.base.controller.BaseController;
/**
 * @author	zhanglibo
 * @time	2015年09月22日 10:05:40
 */
@Controller
@RequestMapping("/hLbImg")
public class HLbImgController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HLbImgController.class); //Logger
	
	@Autowired
	private HLbImgService hlbimgService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hLbImg/hLbImgIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hLbImg/hLbImgAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HLbImg hlbimg1 = new HLbImg();
		hlbimg1.setId(id);
		HLbImg hlbimg = hlbimgService.getHLbImg(hlbimg1);
		model.addAttribute("hlbimg", hlbimg);
		
		return "/hLbImg/hLbImgUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHLbImgList", method = RequestMethod.GET)
	public String getHLbImgList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HLbImg hlbimg = new HLbImg();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hlbimg.setId(id);
			
			String img_name = RequestHandler.getString(request, "img_name");
			hlbimg.setImg_name(img_name);
			
			String img_path = RequestHandler.getString(request, "img_path");
			hlbimg.setImg_path(img_path);
			
			String link_address = RequestHandler.getString(request, "link_address");
			hlbimg.setLink_address(link_address);
			
			Integer add_user_id = RequestHandler.getInteger(request, "add_user_id");
			hlbimg.setAdd_user_id(add_user_id);
			
			Date add_time = RequestHandler.getDate(request, "add_time");
			hlbimg.setAdd_time(add_time);
			
			Integer status = RequestHandler.getInteger(request, "status");
			hlbimg.setStatus(status);
			
			Integer sort_id = RequestHandler.getInteger(request, "sort_id");
			hlbimg.setSort_id(sort_id);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hlbimg.setRowStart(from);
			hlbimg.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hlbimg.setSortColumn(" a.sort_id asc ");
			
			int hlbimgListCount = hlbimgService.getHLbImgListCount(hlbimg);
			ResponseList<HLbImg> hlbimgList = null;
			if ( hlbimgListCount > 0 )
			{
				hlbimgList = hlbimgService.getHLbImgList(hlbimg);
			} else
			{
				hlbimgList = new ResponseList<HLbImg>();
			}
			// 设置数据总数
			hlbimgList.setTotalResults(hlbimgListCount);
			
			writeSuccessMsg("ok", hlbimgList, response);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/getHLbImgBaseList", method = RequestMethod.GET)
	public String getHLbImgBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HLbImg hlbimg = new HLbImg();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hlbimg.setId(id);
			
			String img_name = RequestHandler.getString(request, "img_name");
			hlbimg.setImg_name(img_name);
			
			String img_path = RequestHandler.getString(request, "img_path");
			hlbimg.setImg_path(img_path);
			
			String link_address = RequestHandler.getString(request, "link_address");
			hlbimg.setLink_address(link_address);
			
			Integer add_user_id = RequestHandler.getInteger(request, "add_user_id");
			hlbimg.setAdd_user_id(add_user_id);
			
			Date add_time = RequestHandler.getDate(request, "add_time");
			hlbimg.setAdd_time(add_time);
			
			Integer status = RequestHandler.getInteger(request, "status");
			hlbimg.setStatus(status);
			
			Integer sort_id = RequestHandler.getInteger(request, "sort_id");
			hlbimg.setSort_id(sort_id);
			
			List<HLbImg> hlbimgList = hlbimgService.getHLbImgBaseList(hlbimg);
		
			writeSuccessMsg("ok", hlbimgList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHLbImg", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			ManageAdminUser manageadminuser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			
			HLbImg hlbimg = new HLbImg();
			
			String img_name = RequestHandler.getString(request, "img_name");
			hlbimg.setImg_name(img_name);
			String img_path = RequestHandler.getString(request, "img_path");
			hlbimg.setImg_path(img_path);
			String link_address = RequestHandler.getString(request, "link_address");
			hlbimg.setLink_address(link_address);
			hlbimg.setAdd_user_id(manageadminuser.getAdminId());
			hlbimg.setAdd_time(new Date());
			Integer status = RequestHandler.getInteger(request, "status");
			hlbimg.setStatus(status);
			Integer sort_id = RequestHandler.getInteger(request, "sort_id");
			hlbimg.setSort_id(sort_id);
				
			int id = hlbimgService.insertHLbImg(hlbimg);
			if(id>0){
				hlbimg.setId(id);
				hlbimg.setSort_id(id);
				hlbimgService.updateHLbImg(hlbimg);
			}
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHLbImg", method = RequestMethod.POST)
	public String updateHLbImg(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			ManageAdminUser manageadminuser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			
			HLbImg hlbimg = new HLbImg();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hlbimg.setId(id);
			
			String img_name = RequestHandler.getString(request, "img_name");
			hlbimg.setImg_name(img_name);
			
			String img_path = RequestHandler.getString(request, "img_path");
			hlbimg.setImg_path(img_path);
			
			String link_address = RequestHandler.getString(request, "link_address");
			hlbimg.setLink_address(link_address);
			
			hlbimg.setAdd_user_id(manageadminuser.getAdminId());
			
			Integer status = RequestHandler.getInteger(request, "status");
			hlbimg.setStatus(status);
			
			Integer sort_id = RequestHandler.getInteger(request, "sort_id");
			hlbimg.setSort_id(sort_id);
			

			hlbimgService.updateHLbImg(hlbimg);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHLbImg", method = RequestMethod.POST)
	public String removeHLbImg(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HLbImg hlbimg = new HLbImg();
			Integer id = RequestHandler.getInteger(request, "id");
			hlbimg.setId(id);

			hlbimgService.removeHLbImg(hlbimg);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHLbImg", method = RequestMethod.POST)
	public String removeAllHLbImg(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HLbImg hLbImg = new HLbImg();
						hLbImg.setId(Integer.valueOf(id));
						hlbimgService.removeHLbImg(hLbImg);
					}
				}
			}
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/uploadPic")
	public String uploadPic(HttpServletRequest req, HttpServletResponse response, Model model){
		JSONObject json = new JSONObject();

		try {
	
			if(req instanceof MultipartHttpServletRequest){
				
				MultipartHttpServletRequest request = (MultipartHttpServletRequest)req;
				MultipartFile imgFile = request.getFile("file");
				
				String type = request.getParameter("type");

				
				if(null == type || "".equals(type)){
					type = "jpg";
				}
				if(type.toLowerCase().contains("jpg")||type.toLowerCase().contains("jpeg")){
					type = "jpg";
				}
				if(type.toLowerCase().contains("gif")){
					type = "gif";
				}
				if(type.toLowerCase().contains("bmp")){
					type = "bmp";
				}
				if(type.toLowerCase().contains("png")){
					type = "png";
				}
				
				String uuid = UUID.randomUUID().toString().replace("-", "");	// 惟一ID
				
				String saveFile = uuid + "."+ type.toLowerCase();		// 图片名称
				Long times = System.currentTimeMillis();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
			
				String datedir = sdf.format(new Date(times));
				
				//等于配置的路径  加 当前日期格式化
				String realPath = "/" + "upload" + "/" + datedir;
				String contextPath = request.getSession().getServletContext().getRealPath("/");
//				String contextPath = FileUploadConstants.UPLOAD_FILE_ROOT+File.separator+FileUploadConstants.PIC_PATH;
				if(FileUploadConstants.mkdirs(contextPath+realPath)){
					String filePath = FileUploadConstants.parseToPath(contextPath + "/" + realPath +"/" + saveFile);
					FileUploadConstants.saveFile(imgFile.getInputStream(), filePath);
					
					json.put("serverfileName", realPath +"/" + saveFile);
					
					//json.put("filename", imgFile.getOriginalFilename());
					
				}else{
					System.out.println("------路径不存在----------");
				}
				
			}
			response.getWriter().write(json.toString());
		} catch (Exception e) {
			System.out.println("上传图片出现异常：");
			e.printStackTrace();
		}
		 return null;
	}
	
	@RequestMapping(value = "/moveLb")
	public String moveLb(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			Integer ID1 = RequestHandler.getInteger(request, "id1");
			HLbImg hLbImg1 = new HLbImg();
			hLbImg1.setId(ID1);
			hLbImg1 = hlbimgService.getHLbImg(hLbImg1);
			
			
			
			Integer ID = RequestHandler.getInteger(request, "id");
			HLbImg hLbImg = new HLbImg();
			hLbImg.setId(ID);
			hLbImg = hlbimgService.getHLbImg(hLbImg);
			
			
			Integer sortId = hLbImg.getSort_id();
			hLbImg.setSort_id(hLbImg1.getSort_id());
			hLbImg1.setSort_id(sortId);
			
			hlbimgService.updateHLbImg(hLbImg);
			hlbimgService.updateHLbImg(hLbImg1);
			
			writeSuccessMsg("操作成功", null, response);
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getAllHLbImg", method = RequestMethod.GET)
	public String getAllHLbImg(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HLbImg hlbimg = new HLbImg();
			hlbimg.setStatus(1);
			hlbimg.setSortColumn(" a.sort_id asc ");
			List<HLbImg> hlbimgList = hlbimgService.getHLbImgBaseList(hlbimg);
			model.addAttribute("hlbimgList", hlbimgList);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return "/front/lb";
	}
}
