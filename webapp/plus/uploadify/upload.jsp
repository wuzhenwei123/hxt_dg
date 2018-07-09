<%@page import="net.sf.json.JSONArray"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="com.base.utils.UploadUtil,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<Map<String, Object>> re = UploadUtil.uploadfile(request);
	String str = JSONArray.fromObject(re).toString();
	out.print(str);
%>