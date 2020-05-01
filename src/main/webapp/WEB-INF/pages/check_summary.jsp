<%--
  Created by IntelliJ IDEA.
  User: Luke
  Date: 2020/3/13
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>健康体检总结报告</title>
		<base href="<%=basePath%>">
	</head>
	<body>
		<table width="100%">
			<tr style="font-size: small;">
				<td>xxx中心医院</td>
				<td></td>
				<td>体检编号:</td>
				<td>姓名:</td>
				<td>性别:</td>
				<td>年龄:</td>
			</tr>
		
		</table>
		
		<hr>
	
		<table border="0" width="100%">
			<tr >
				<td align="center" bgcolor="gainsboro" style="font-size: large;font-weight: 600;">
					总检健康评估
				</td>
			</tr>
			<tr>
				<td style="text-align: left;">
					<p style="font-weight: 800">疾病诊断及阳性发现:</p>
					<p style="font-weight: 200;height: 50px;">小结综合</p>
				</td>
			</tr>
			<tr>
				<td style="text-align: left;">
					<p style="font-weight: 800">健康指导:</p>
					<p style="font-weight: 200;height: 300px;">小结综合指导</p>
				</td>
			</tr>
		</table >
		<hr>
		<table width="100%" border="0">
			<tr>
				<td style="width: 30%;"></td>
				<td>总检医生:</td>
				<td>总审医生:</td>
			</tr>
			<tr>
				<td></td>
				<td>总检日期:</td>
				<td>总审日期:</td>
			</tr>
		</table>
		<hr>
		
		
	</body>
</html>
