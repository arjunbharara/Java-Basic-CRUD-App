<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="arjun.udemy.hibernate.entity.Files" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listing Images</title>
</head>
<body>
<%! String form; int fileId;%>
<%
%>
<h1>Listing Images</h1>
<table border="3">
<tr>
<th>Preveiw</th>
<th>Available info</th>
<th>Update info</th>
<th>Available Action</th>
<%
String path =(String)request.getAttribute("path");
List<Files>files= (List<Files>)request.getAttribute("files");
for(Files file:files){
	out.print("<tr><td><img src="+path+file.getFilename()+"height='200'></td>");
	out.print("<td><ul>"+
	"<li>File ID:"+file.getId()+"</li>"+
	"<li>File Name:"+file.getFilename()+"</li>"+
	"<li>File Label:"+file.getLabel()+"</li>"+
	"<li>File Caption:"+file.getCaption()+"</li>"+
	"</ul></td>");
	fileId=file.getId();
	String form="<form action='ImageUpload' method='post'>"+
			"Label:<input type='text' name='label'/><br/>"+
			"Caption :<input type='text' name='caption'/><br/>"+
			"<input type='hidden' name='fileId' value='"+fileId+"'/>"+
			"<input type='hidden' name='action' value='updateInfo'/>"+
			"<input type='hidden' name='fileName' value='"+file.getFilename()+"'/>"+
			"<input type='submit' value='Update'>"+
			"</form>";
	  out.print("<td>"+form+"</td>");
	  out.print("<td><ul><li><a href='"+request.getContextPath()+"/ImageUpload?action=viewImage&fileId="+
			  file.getId()+"'>View Image</a></li>");
	  
	  out.print("<li><a href='"+request.getContextPath()+"/ImageUpload?action=deleteImage&fileId="+
			  file.getId()+"'onclick=\"if(!confirm('Are you sure to delete the user?')) return false\">Delete Image</a></li></ul></td></tr>");
}
%>
</table>
</body>
</html>