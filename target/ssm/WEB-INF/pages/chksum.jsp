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
	<div id="myDiv">
		<table width="100%">
			<tr style="font-size: small;">
				<td>xxx中心医院</td>
				<td></td>
				<td>
					体检编号:
					<c:if test="${!empty(sumData)}">
						${sumData.get(0).person.cards.cardNo}
					</c:if>
				</td>
				<td>
					姓名:
					<c:if test="${!empty(sumData)}">
						${sumData.get(0).person.perName}
					</c:if>
				</td>
				<td>
					性别:
					<c:if test="${!empty(sumData)}">
						${sumData.get(0).person.perSex}
					</c:if>
				</td>
				<td>
					年龄:
					<c:if test="${!empty(sumData)}">
						${sumData.get(0).person.perAge}
					</c:if>
				</td>
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
					<p style="font-weight: 200;height: 50px;">
						<c:if test="${!empty(sumData)}">
							${sumData.get(0).summary.sumDesc}
						</c:if>
					</p>
				</td>
			</tr>
			<tr>
				<td style="text-align: left;">
					<p style="font-weight: 800">健康指导:</p>
					<p style="font-weight: 200;height: 300px;">
						<c:if test="${!empty(sumData)}">
							${sumData.get(0).summary.sumGuide}
						</c:if>
					</p>
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
	</div>
		<hr>
		<button onclick="prt()">打印</button>
		
	</body>
	<script type="text/javascript">
		function prt() {
			var printHtml = document.getElementById("myDiv").innerHTML;
			var oldstr = document.body.innerHTML;
			window.document.body.innerHTML = printHtml;
			window.print();
			document.body.innerHTML = oldstr;
		}

	</script>
</html>
