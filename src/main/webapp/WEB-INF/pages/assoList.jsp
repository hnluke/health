<%--
  Created by IntelliJ IDEA.
  User: Luke
  Date: 2020/5/3
  Time: 21:22
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
    <title>套餐配置管理</title>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery-1.7.1.js"></script>
    <link href="<%=basePath%>style/authority/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath%>style/authority/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=basePath%>scripts/authority/commonAll.js"></script>
    <script type="text/javascript" src="<%=basePath%>scripts/fancybox/jquery.fancybox-1.3.4.js"></script>
    <script type="text/javascript" src="<%=basePath%>scripts/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>style/authority/jquery.fancybox-1.3.4.css" media="screen"></link>
    <script type="text/javascript" src="<%=basePath%>scripts/artDialog/artDialog.js?skin=default"></script>

    <script type="text/javascript">
        $(document).ready(function(){
            /** 新增   **/
            $("#addBtn").fancybox({
                'href'  : 'house_edit.html',
                'width' : 733,
                'height' : 530,
                'type' : 'iframe',
                'hideOnOverlayClick' : false,
                'showCloseButton' : false,
                'onClosed' : function() {
                    window.location.href = 'house_list.html';
                }
            });

            /** 导入  **/
            $("#importBtn").fancybox({
                'href'  : '/xngzf/archives/importFangyuan.action',
                'width' : 633,
                'height' : 260,
                'type' : 'iframe',
                'hideOnOverlayClick' : false,
                'showCloseButton' : false,
                'onClosed' : function() {
                    window.location.href = 'house_list.html';
                }
            });

            /**编辑   **/
            $("a.edit").fancybox({
                'width' : 733,
                'height' : 530,
                'type' : 'iframe',
                'hideOnOverlayClick' : false,
                'showCloseButton' : false,
                'onClosed' : function() {
                    window.location.href = 'house_list.html';
                }
            });
        });
        /** 用户角色   **/
        var userRole = '';

        /** 模糊查询来电用户  **/
        function search(){
            $("#submitForm").attr("action", "house_list.html?page=" + 1).submit();
        }

        /** 新增   **/
        function add(){
            $("#submitForm").attr("action", "/xngzf/archives/luruFangyuan.action").submit();
        }

        /** Excel导出  **/
        function exportExcel(){
            if( confirm('您确定要导出吗？') ){
                var fyXqCode = $("#fyXq").val();
                var fyXqName = $('#fyXq option:selected').text();
//	 		alert(fyXqCode);
                if(fyXqCode=="" || fyXqCode==null){
                    $("#fyXqName").val("");
                }else{
//	 			alert(fyXqCode);
                    $("#fyXqName").val(fyXqName);
                }
                $("#submitForm").attr("action", "/xngzf/archives/exportExcelFangyuan.action").submit();
            }

        }

        /** 删除 **/
        function del(fyID){
            // 非空判断
            if(fyID == '') return;
            if(confirm("您确定要删除吗？")){
                $("#submitForm").attr("action", "/xngzf/archives/delFangyuan.action?fyID=" + fyID).submit();
            }
        }

        /** 批量删除 **/
        function batchDel(){
            if($("input[name='IDCheck']:checked").size()<=0){
                art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'至少选择一条', ok:true,});
                return;
            }
            // 1）取出用户选中的checkbox放入字符串传给后台,form提交
            var allIDCheck = "";
            $("input[name='IDCheck']:checked").each(function(index, domEle){
                bjText = $(domEle).parent("td").parent("tr").last().children("td").last().prev().text();
// 			alert(bjText);
                // 用户选择的checkbox, 过滤掉“已审核”的，记住哦
                if($.trim(bjText)=="已审核"){
// 				$(domEle).removeAttr("checked");
                    $(domEle).parent("td").parent("tr").css({color:"red"});
                    $("#resultInfo").html("已审核的是不允许您删除的，请联系管理员删除！！！");
// 				return;
                }else{
                    allIDCheck += $(domEle).val() + ",";
                }
            });
            // 截掉最后一个","
            if(allIDCheck.length>0) {
                allIDCheck = allIDCheck.substring(0, allIDCheck.length-1);
                // 赋给隐藏域
                $("#allIDCheck").val(allIDCheck);
                if(confirm("您确定要批量删除这些记录吗？")){
                    // 提交form
                    $("#submitForm").attr("action", "/xngzf/archives/batchDelFangyuan.action").submit();
                }
            }
        }

        /** 普通跳转 **/
        function jumpNormalPage(page){
            $("#submitForm").attr("action", "house_list.html?page=" + page).submit();
        }

        /** 输入页跳转 **/
        function jumpInputPage(totalPage){
            // 如果“跳转页数”不为空
            if($("#jumpNumTxt").val() != ''){
                var pageNum = parseInt($("#jumpNumTxt").val());
                // 如果跳转页数在不合理范围内，则置为1
                if(pageNum<1 | pageNum>totalPage){
                    art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'请输入合适的页数，\n自动为您跳到首页', ok:true,});
                    pageNum = 1;
                }
                $("#submitForm").attr("action", "house_list.html?page=" + pageNum).submit();
            }else{
                // “跳转页数”为空
                art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'请输入合适的页数，\n自动为您跳到首页', ok:true,});
                $("#submitForm").attr("action", "house_list.html?page=" + 1).submit();
            }
        }

        function importPrio() {
            window.location.href="manage/prio/2";


        }

        // function openWin() {
        //     window.open ("check/guide/3", "newwindow",
        //         "left=200 top= 300 height=500, width=600, toolbar=no,menubar=no, scrollbars=no, resizable=no, location=no, status=no")
        // }


    </script>
    <style>
        .alt td{ background:black !important;}
    </style>

</head>
<body>

<input type="hidden" name="allIDCheck" value="" id="allIDCheck"/>
<input type="hidden" name="fangyuanEntity.fyXqName" value="" id="fyXqName"/>
<div id="container">
    <div class="ui_content">
        <div class="ui_text_indent">
            <div id="box_border">
                <div id="box_bottom">
                    <input type="button" value="导入" class="ui_input_btn01" onclick="importPrio()"/>
                </div>

            </div>
        </div>
    </div>
    <form id="submitForm" name="submitForm" action="manage/prio/3" method="post">
        <div class="ui_content">


            请选择权限：
            <select name="prioNameStrId">
                <option value="0">--权限--</option>
                <c:if test="${!empty(priorityList)}">
                    <c:forEach var="names"  items="${priorityList}">
                        <option value="${names.prioId}">${names.prioName}</option>
                    </c:forEach>

                </c:if>

            </select>
            (分配权限)
            <input type="submit" value="分配" class="ui_input_btn01"/>

        </div>
        <br>
        请配置限相应的菜单:
        <div class="ui_content">
            <div id = "guide" style="width: 100%; float:left; overflow: scroll; height: 260px" >
                <form id="assoForm">
                    <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="1">
                        <c:if test="${!empty(menusList)}">
                            <tr>
                            <c:forEach var="menu"  items="${menusList}" varStatus="status">

                                <c:if test="${(status.index % 6) == 0}">
                                    <tr>
                                </c:if>
                                <td>
                                    <input type="checkbox"  name="menuNameId" value="${menu.menuId}">${menu.menuName}

                                </td>
                                <c:if test="${(status.count % 6) == 0}">
                                    </tr>
                                </c:if>
                            </c:forEach>
                            <c:if test="${(status.count % 4) != 0}">
                                </tr>
                            </c:if>
                        </c:if>
                    </table>
                </form>

            </div>
            <div id = "guide" style="width: 100%; float:left; overflow: scroll; height: 300px" >
                配置信息如下：
                <c:if test="${!empty(prioMenuList)}">
                    <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="1">
                        <tr>
                            <td>序号</td>
                            <td>权限名</td>
                            <td>权限描述</td>
                            <td>菜单名</td>
                            <td>操作</td>

                        </tr>
                        <c:forEach var="prio" items="${prioMenuList}" varStatus="status">

                            <tr>
                                <td>${status.index + 1 }</td>
                                <td>${prio.prioName}</td>
                                <td>${prio.prioDesc}</td>
                                <td></td>
                            </tr>
                            <c:if test="${!empty(prio.menuPrioList)}">
                                <c:forEach var="mePr"  items="${prio.menuPrioList}">
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td>${mePr.menus.menuName}</td>
                                        <td><a href="manage/prio/4?prmeId=${mePr.prmeId}">删除</a></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
            <%--            <div id ="showTxt" style="width: 100%; float:left; overflow: scroll; height: 300px">--%>

            <%--            </div>--%>
        </div>
    </form>
</div>


</body>

</html>

