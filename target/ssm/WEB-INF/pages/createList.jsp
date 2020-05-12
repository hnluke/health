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
    <title>企业资产管理系统</title>
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
        // 开单按键
        function creList(){
            if($("#cardNo").val() == "" || $.trim($("#cardNo").val()).length == 0) {
                alert("请输入体检人卡号");
            }else{
                $("#submitForm").attr("action", "check/list/2");
                $("#submitForm").attr("method", "post");
                $("#submitForm").submit();
            }
        }

        function selected(names, id) {
            var chk;
            if ($('#assoChk' + id).prop('checked')) {
                chk = "1";
            } else {
                chk = "0";
            }
            var url = "check/jsondata";
            var contexts = {"assoName": names, "chk" : chk, "assoId" : id};
            var con = "";
            var end = "";
            var title = "您选择的项目如下：<br/>\n";
            var heads = "<table border='1' class='table' align='center' width='100%'>\n" +
                "<tr>\n" +
                "    <th>序号</th>\n" +
                "    <th>项目名称</th>\n" +
                "    <th>项目类别</th>\n" +
                "    <th>所属科室</th>\n" +
                "    <th>价格</th>\n" +
                "</tr>\n";
            $.ajax({
                type: "post",
                url: url,
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                data: JSON.stringify(contexts),
                success: function (callback) {

                    for(var i=0; i < callback.length; i++) {
                        con = con +
                            "<tr><td>" + (i+1) + "</td>\n" +
                            "<td>" + callback[i].selItemName + "</td>\n" +
                            "<td>" + callback[i].selType + "</td>\n" +
                            "<td>" + callback[i].selOff + "</td>\n" +
                            "<td>" + callback[i].selPrice + "</td></tr>\n";
                    }
                    end =  "</table>\n";

                     $("#showTxt").html(title + heads + con + end);
                }
            });
        }

        function selected2(names, id) {
            var chk;

            if ($('#itemChk'+ id).prop('checked')) {
                chk = "1";
            } else {
                chk = "0";
            }
            var url = "check/jsondata";
            var contexts = {"itemName": names, "chk" : chk, "itemId" : id};
            var con = "";
            var end = "";
            var title = "您选择的项目如下：<br/>\n";
            var heads =
                "<table border='1' class='table' align='center' width='100%'>\n" +
                "<tr>\n" +
                "    <th>序号</th>\n" +
                "    <th>项目名称</th>\n" +
                "    <th>项目类别</th>\n" +
                "    <th>所属科室</th>\n" +
                "    <th>价格</th>\n" +
                "</tr>\n";


            $.ajax({
                type: "post",
                url: url,
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                data: JSON.stringify(contexts),
                success: function (callback) {

                    for(var i=0; i < callback.length; i++) {
                        con = con +
                            "<tr><td>" + callback[i].selId + "</td>\n" +
                            "<td>" + callback[i].selItemName + "</td>\n" +
                            "<td>" + callback[i].selType + "</td>\n" +
                            "<td>" + callback[i].selOff + "</td>\n" +
                            "<td>" + callback[i].selPrice + "</td></tr>\n";
                    }
                    end = "</table>\n";

                    $("#showTxt").html(title + heads + con + end);
                }
            });
        }
    </script>
    <style>
        .alt td{ background:black !important;}
    </style>

</head>
<body>
<form id="submitForm" name="submitForm" action="check/list/2" method="post">
    <input type="hidden" name="allIDCheck" value="" id="allIDCheck"/>
    <input type="hidden" name="fangyuanEntity.fyXqName" value="" id="fyXqName"/>
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_bottom">

                        <span id="showTxt2" style="font-weight: bold; color: #2b542c">[${message}]</span>
                        请输入体检人卡片编号：
                        <input type="text" name="cardNo" class="ui_input_txt02" id="cardNo" value="${cardNo}"/>
                        <input type="button" value="开单" class="ui_input_btn01" onclick="creList()"/>
<%--                        <input type="button" value="导出" class="ui_input_btn01"--%>
<%--                               onclick="location.href='assets/purchase/ex'" />--%>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div id = "asso" style="width: 45%; float:left; overflow: scroll; height: 200px" >
                请选择你需要的套餐:
                <form id="assoForm">
                    <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                        <tr>
                            <th>选择</th>
                            <th>套餐名称</th>
                            <th>价格</th>
                        </tr>
                        <c:if test="${!empty(assoList)}">
                            <c:forEach var="asso"  items="${assoList}">
                                <tr>
                                    <td><input type="checkbox" id="assoChk${asso.assoId}" name="IDCheck2" value="${asso.assoId}" class="acb" onchange= "selected('${asso.assoName}','${asso.assoId}');"/></td>
                                    <td>${asso.assoName}</td>
                                    <td>${asso.assoPrice}</td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </table>
                </form>

            </div>
            <div id = "items" style="width: 55%; float:left; overflow: scroll; height: 200px">
                请选择你需要的项目:
                <form id = "itemsForm">
                    <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                        <tr>
                            <th>选择</th>
                            <th>项目名称</th>
                            <th>项目类别</th>
                            <th>所属科室</th>
                            <th>价格</th>
                        </tr>
                        <c:if test="${!empty(itemList)}">
                            <c:forEach var="items"  items="${itemList}">
                                <tr>
                                    <td>
                                        <input type="checkbox" name="IDCheck" id="itemChk${items.itemId}" value="${items.itemId}" class="acb" onchange= "selected2('${items.itemName}','${items.itemId}');"/>
                                    </td>
                                    <td>${items.itemName}</td>
                                    <td>${items.itemType.typeName}</td>
                                    <td>${items.office.offName}</td>
                                    <td>${items.itemPrice}</td>
                                </tr>
                            </c:forEach>

                        </c:if>

                    </table>
                </form>

            </div>
            <div id ="showTxt" style="width: 100%; float:left; overflow: scroll; height: 300px">

            </div>
        </div>
    </div>
</form>

</body>

</html>

