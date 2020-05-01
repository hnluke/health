<%--
  Created by IntelliJ IDEA.
  User: Luke
  Date: 2020/4/20
  Time: 8:11
  To change this template use File | Settings | File Templates.
<%--
  Created by IntelliJ IDEA.
  User: Luke
  Date: 2020/3/19
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>SCT-后台系统</title>
    <base href="<%=basePath%>">
    <link href="style/authority/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="style/authority/common_style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">
    <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center"  border="0">
        <tr>
            <th colspan="2">系统版本</th>
        </tr>
        <tr>
            <td width="100" height="30">当前版本<span class="TableRow2"></span></td>
            <td style="text-align:left">健康体检系统</td>
        </tr>
    </table>
    <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
        <tr>
            <th colspan="2">系统开发 </th>
        </tr>
        <tr>
            <td width="100" height="30">程序制作</td>
            <td style="text-align:left">SSM项目小组</td>
        </tr>
        <tr>
            <td width="100" height="30">联系方式</td>
            <td style="text-align:left">
                <a href="mailto:hn_luke@163.com">hn_luke@163.com</a>
            </td>
        </tr>
    </table>
</div>

</body>
</html>


