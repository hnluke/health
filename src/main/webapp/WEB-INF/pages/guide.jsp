<%--
  Created by IntelliJ IDEA.
  User: Luke
  Date: 2020/5/3
  Time: 23:35
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
    <title>健康体检导检表</title>
    <base href="<%=basePath%>">

</head>
<body>
<table width="100%">
    <tr style="font-size: small;">
        <td>xxx中心医院</td>
        <td></td>
        <td>
            体检编号:
            <c:if test="${!empty(guideData)}">
                ${guideData.get(0).briefCardNo}
            </c:if>
        </td>
        <td>
            姓名:
            <c:if test="${!empty(guideData)}">
                ${guideData.get(0).briefPerson}
            </c:if>
        </td>
        <td>
            日期:
            <c:if test="${!empty(guideData)}">
                ${guideData.get(0).briefDate}
            </c:if>
        </td>
    </tr>

</table>

<hr>
<div id="myDiv">
<table border="0" width="100%">
    <tr >
        <td align="center" bgcolor="gainsboro" style="font-size: large;font-weight: 600;">
            健康体检导检表
        </td>
    </tr>
    <tr>
        <td align="center">
            一维码:
            <c:if test="${!empty(guideData)}">
                ${guideData.get(0).briefBatchNo}
            </c:if>

        </td>
    </tr>
</table>
<hr>
<table width="100%" align="left">
    <c:if test="${!empty(guideData)}">
        <c:forEach var="guide"  items="${guideData}">
            <tr>
                <td style="text-align: left;">
                    <p>项目名称:${guide.briefItemName}</p>
                </td>
                <td></td>
                <td></td>
                <td></td>
                <td >科室：${guide.briefName}</td>
                <td></td>
            </tr>
            <tr>
                <td style="text-align: left;">
                    是否完成:<input type="checkbox" />
                </td>
                <td></td>
                <td></td>
                <td></td>
                <td>医生签名：</td>
                <td></td>
            </tr>
            <tr>
                <td colspan="6"><hr></td>
            </tr>

        </c:forEach>
    </c:if>

</table >
</div>

<%--<table width="100%" border="0">--%>
<%--    <tr>--%>
<%--        <td style="width: 30%;"></td>--%>
<%--        <td>总检医生:</td>--%>
<%--        <td>总审医生:</td>--%>
<%--    </tr>--%>
<%--    <tr>--%>
<%--        <td></td>--%>
<%--        <td>总检日期:</td>--%>
<%--        <td>总审日期:</td>--%>
<%--    </tr>--%>
<button onclick = "prt()">打印</button>


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
