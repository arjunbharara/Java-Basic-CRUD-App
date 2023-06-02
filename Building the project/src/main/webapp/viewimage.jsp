<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="arjun.udemy.hibernate.entity.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Image</title>
</head>
<body>
<%! Files file;String path; %>
<%file= (Files)request.getAttribute("file");
path=(String)request.getAttribute("path");

%>
File Id:<%=file.getId() %>|File Name:<%=file.getFilename() %>|
<%
if(file.getLabel()!=null){
	out.print("Label:"+file.getLabel());
}
%>
<%
if(file.getCaption()!=null){
	out.print("caption:"+file.getCaption());
}
%>
<%-- <a href="${pageContext.request.contextpath}">Home</a>
<a href="${pageContext.request.contextpath}/ImageUpload?action=ListingImages">List availabel image</a>--%>
<hr/>
<img src="<%=path+file.getFilename() %>">
</body>
</html>