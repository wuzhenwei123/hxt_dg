package com.base.interceptor;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.base.utils.FileUploadConstants;
import com.base.utils.SessionName;
import com.sys.manageAdminUser.model.ManageAdminUser;

@Repository
public class SystemInterceptor extends HandlerInterceptorAdapter {
	private List<String> paramList;
	Logger log = Logger.getLogger(SystemInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();

		// 请求的虚拟目录
		String basePath = request.getContextPath();
		String requestPath = request.getServletPath();
		try {
			if (!requestPath.contains(".")) {
				log.info(">>" + requestPath);

				// 判断当前页是否是重定向以后的登录页面页面，如果是就不做session的判断，防止出现死循环
				if (session == null || session.getAttribute(SessionName.ADMIN_USER_ID) == null) {// 未登录
					if (paramList.contains(requestPath) || requestPath.startsWith("/public/news/") || requestPath.startsWith("/public/notice/")) {
						return super.preHandle(request, response, handler);
					} else {
						// 未登录  
	                    PrintWriter out = response.getWriter();  
	                    StringBuilder builder = new StringBuilder();  
	                    builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");  
//	                    builder.append("alert(\"页面过期，请重新登录\");");  
	                    builder.append("window.top.location.href=\"");  
	                    builder.append(basePath);  
	                    builder.append("/index/first\";</script>");  
	                    out.print(builder.toString());  
	                    out.close();  
	                    return false;  
//						response.sendRedirect(basePath + "/manageAdminUser/toLogin");// 登陆页面
					}
				} else {// 已登录
					ManageAdminUser adminUser = (ManageAdminUser) request.getSession().getAttribute(SessionName.ADMIN_USER);
					boolean flage = false;//是否拦截
					Integer currentRoleId = adminUser.getRoleId();
//					if(!currentRoleId.equals(Integer.valueOf(FileUploadConstants.COMPANY_ROLE_ID))){
//						flage = true;
//					}else if(adminUser.getCompanyId()==null){
//						flage = true;
//					}
//					if(flage){
//						request.getSession().removeAttribute(SessionName.ADMIN_USER_NAME);
//						request.getSession().removeAttribute(SessionName.ADMIN_USER_ID);
//						request.getSession().removeAttribute(SessionName.ADMIN_USER);
//						PrintWriter out = response.getWriter();  
//	                    StringBuilder builder = new StringBuilder();  
//	                    builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");  
//		                builder.append("alert(\"该账号没有权限，请重新登录\");");  
//	                    builder.append("window.top.location.href=\"");  
//	                    builder.append(basePath);  
//	                    builder.append("/index/first\";</script>");  
//	                    out.print(builder.toString());  
//	                    out.close();  
//	                    return false;  
//					}else{
						request.setAttribute("requestPath", requestPath);
						return super.preHandle(request, response, handler);
//					}
				}
			} else {
				return super.preHandle(request, response, handler);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public void setParamList(List<String> paramList) {
		this.paramList = paramList;
	}
}
