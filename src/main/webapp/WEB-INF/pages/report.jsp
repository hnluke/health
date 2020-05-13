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
    <title>健康体检报告</title>
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
            <c:if test="${!empty(reportData)}">
                ${reportData.get(0).briefCardNo}
            </c:if>
        </td>
        <td>
            姓名:
            <c:if test="${!empty(reportData)}">
                ${reportData.get(0).briefPerson}
            </c:if>
        </td>
        <td>
            日期:
            <c:if test="${!empty(reportData)}">
                ${reportData.get(0).briefDate}
            </c:if>
        </td>

    </tr>

</table>

<hr>
<h2 align="center">科室检查结果</h2>
<table border="0" width="100%">
    <tr >
<%--        <td align="center" bgcolor="gainsboro" style="font-size: large;font-weight: 600;">--%>
<%--            健康体检导检表--%>
<%--        </td>--%>
    </tr>
</table>
<hr>
<table width="100%" align="left">
    <c:if test="${!empty(reportData)}">
        <c:forEach var="report"  items="${reportData}">
            <c:if test="${report.briefType.equals('检查类')}">
                <tr>
                    <td colspan="6" style="background-color: #9d9d9d; text-align: left">
                            ${report.briefItemName}
                    </td>
                </tr>
                <tr>
                    <td>项目名称</td>
                    <td></td>
                    <td>检查结果</td>
                    <td>提示</td>
                    <td>参考范围</td>
                    <td>单位</td>
                </tr>
            </c:if>
            <c:if test="${report.briefType.equals('B超类')}">
                <tr>
                    <td>体检编号:</td>
                    <td></td>
                    <td>姓名:</td>
                    <td></td>
                    <td>检查日期：</td>
                    <td></td>
                </tr>
            </c:if>

                <c:forEach var="det" items="${report.listDetails}">
                    <c:if test="${report.briefType.equals('检查类')}">
                        <tr>
                            <td>${det.detItemName}</td>
                            <td></td>
                            <td>${det.detResult}</td>
                            <td>${det.detPrompt}</td>
                            <td>${det.detRefer}</td>
                            <td>${det.detUnit}</td>
                        </tr>
                    </c:if>
                    <c:if test="${report.briefType.equals('B超类')}">
                        <tr>
                            <td colspan="6">

                            <img src="image/2.jpg" width="500" height="241">
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
                <tr>
                    <td colspan="6" style="text-align: left"><hr></td>
                </tr>
                <tr>
                    <td>小结:</td>
                    <td>${report.briefDesc}</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td>检查医生:${report.briefUser}</td>
                    <td>检查日期:${report.briefDate}</td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td colspan="6"><hr></td>
                </tr>


                <tr>
                    <td colspan="6">

                    </td>
                </tr>
                <tr>
                    <td colspan="6"></td>
                </tr>
                <tr>
                    <td colspan="6"></td>
                </tr>
<%--            </c:if>--%>
        </c:forEach>
    </c:if>
</table>
</div>
<button onclick="prt()">打印</button>
<%--            <tr>--%>
<%--                <td style="text-align: left;">--%>
<%--                    <p>项目名称:${guide.briefItemName}</p>--%>
<%--                </td>--%>
<%--                <td></td>--%>
<%--                <td></td>--%>
<%--                <td></td>--%>
<%--                <td >科室：${guide.briefName}</td>--%>
<%--                <td></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td style="text-align: left;">--%>
<%--                    是否完成:<input type="checkbox" />--%>
<%--                </td>--%>
<%--                <td></td>--%>
<%--                <td></td>--%>
<%--                <td></td>--%>
<%--                <td>医生签名：</td>--%>
<%--                <td></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td colspan="6"><hr></td>--%>
<%--            </tr>--%>






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

