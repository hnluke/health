<%--
  Created by IntelliJ IDEA.
  User: Luke
  Date: 2020/5/5
  Time: 10:37
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
    <title>体检人员信息</title>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery-1.7.1.js"></script>
    <link href="<%=basePath%>style/authority/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath%>style/authority/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=basePath%>scripts/authority/commonAll.js"></script>
    <script type="text/javascript" src="<%=basePath%>scripts/fancybox/jquery.fancybox-1.3.4.js"></script>
    <script type="text/javascript" src="<%=basePath%>scripts/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>style/authority/jquery.fancybox-1.3.4.css" media="screen"></link>
    <script type="text/javascript" src="<%=basePath%>scripts/artDialog/artDialog.js?skin=default"></script>
    <style>
        .titles {
            width: 10%;
            font-weight: bold;
        }
    </style>

</head>
<body style="text-align: center">
<table width="100%">
    <tr style="font-size: small;">
        <td>xxx中心医院</td>
        <td></td>
        <td>
            体检编号:
            <c:if test="${!empty(perData)}">
                ${perData.get(0).cardNo}
            </c:if>
        </td>
        <td>
            姓名:
            <c:if test="${!empty(perData)}">
                ${perData.get(0).person.perName}
            </c:if>
        </td>
        <td>
            性别:
            <c:if test="${!empty(perData)}">
                ${perData.get(0).person.perSex}
            </c:if>
        </td>
    </tr>

</table>

<hr>
<h2>体检人员信息表</h2>
<hr>

<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="3">
    <tr>
        <td class="titles">卡片编号</td>
        <td style="width: 16%">
            <c:if test="${!empty(perData)}">
                ${perData.get(0).cardNo}
            </c:if>
        </td>
        <td class="titles"></td>
        <td style="width: 16%"></td>
        <td class="titles">余额</td>
        <td style="width: 16%">
            <c:if test="${!empty(perData)}">
                ${perData.get(0).cardMoney}
            </c:if>
        </td>
    </tr>
    <tr>
        <td class="titles">姓名</td>
        <td>
            <c:if test="${!empty(perData)}">
                ${perData.get(0).person.perName}
            </c:if>
        </td>
        <td class="titles">性别</td>
        <td>
            <c:if test="${!empty(perData)}">
                ${perData.get(0).person.perSex}
            </c:if>
        </td>
        <td class="titles">年龄</td>
        <td>
            <c:if test="${!empty(perData)}">
                ${perData.get(0).person.perAge}
            </c:if>
        </td>
    </tr>
    <tr>
        <td class="titles">出生年月</td>
        <td>
            <c:if test="${!empty(perData)}">
                ${perData.get(0).person.perBorn}
            </c:if>
        </td>
        <td></td>
        <td></td>
        <td class="titles">血型</td>
        <td>
            <c:if test="${!empty(perData)}">
                ${perData.get(0).person.perBlood}
            </c:if>
        </td>
    </tr>
    <tr>
        <td class="titles">手机号码</td>
        <td>
            <c:if test="${!empty(perData)}">
                ${perData.get(0).person.perTele}
            </c:if>
        </td>
        <td></td>
        <td></td>
        <td class="titles">地址</td>
        <td>
            <c:if test="${!empty(perData)}">
                ${perData.get(0).person.perAddr}
            </c:if>
        </td>
    </tr>
</table>

</body>
</html>
