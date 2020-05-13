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
    <title>结算业务</title>
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

        function fetchData(val) {
            // alert(val);
            var cardNo = document.getElementById("cardNo").value;
            var contexts = {"param" : val, "cardNo" : cardNo};
            var url = "charge/balaJson";
            var con = "";
            var end = "";
            
            if(val == "体检缴费") {
                title = "体检缴费项目如下：<br/>\n";
            }else{
                title = "体检退费项目如下：<br/>\n";
            }

            var heads = "<table border='1' class='table' align='center' width='100%'>\n" +
                "<tr>\n" +
                "    <th>序号</th>\n" +
                "    <th>流水号</th>\n" +
                "    <th>卡片编号</th>\n" +
                "    <th>体检人</th>\n" +
                "    <th>项目名称</th>\n" +
                "    <th>性质</th>\n" +
                "    <th>价格</th>\n" +
                "    <th>是否缴费</th>\n" +
                "    <th>是否完成</th>\n" +
                "    <th>操作</th>\n" +
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
                            "<td>" + callback[i].batches.batchNo + "</td>\n" +
                            "<td>" + callback[i].batches.person.cards.cardNo + "</td>\n" +
                            "<td>" + callback[i].batches.person.perName + "</td>\n" +
                            "<td>" + callback[i].listName + "</td>\n" +
                            "<td>" + callback[i].listType + "</td>\n" +
                            "<td>" + callback[i].listPrice + "</td>\n" +
                            "<td>" + callback[i].batches.batchPay + "</td>\n" +
                            "<td>" + callback[i].batches.batchCmp + "</td>\n";
                        var addr;
                        if(val == "体检缴费") {
                            addr = "<td><a href='charge/balance/2?listId=" + callback[i].listId +
                                "&batchId=" + callback[i].batchId +
                                "&cardId=" + callback[i].batches.person.cards.cardNo  +"'>缴费</a></td></tr>\n";
                            // addr = "<input type='button' value='缴费' class='ui_input_btn01' " +
                            //     "onclick='fetchData2(this.value, " + callback[i].listId + "," + callback[i].batchId + ")'/>";
                        }else{
                            addr = "<td><a href='charge/balance/3?listId=" + callback[i].listId +
                                "&batchId=" + callback[i].batchId +
                                "&cardId=" + callback[i].batches.person.cards.cardNo  +"'>退费</a></td></tr>\n";
                            // addr = "<input type='button' value='退费' class='ui_input_btn01' " +
                            //     "onclick='fetchData2(this.value, " + callback[i].listId + "," + callback[i].batchId + ")'/>";
                        }
                        con = con + addr;
                    }
                    end =  "</table>\n";
                    $("#showTxt").html(title + heads + con + end);

                }
            });

        }

        // 缴费、退费
        function fetchData2(val, listId, batchId) {
            // alert(val);
            var cardNo = document.getElementById("cardNo").value;
            var contexts = {"param" : val, "listId" : listId, "batchId" : batchId};
            var url = "charge/balaJson";
            var con = "";
            var end = "";

            if(val == "体检缴费") {
                title = "体检缴费项目如下：<br/>\n";
            }else{
                title = "体检退费项目如下：<br/>\n";
            }

            var heads = "<table border='1' class='table' align='center' width='100%'>\n" +
                "<tr>\n" +
                "    <th>序号</th>\n" +
                "    <th>流水号</th>\n" +
                "    <th>卡片编号</th>\n" +
                "    <th>体检人</th>\n" +
                "    <th>项目名称</th>\n" +
                "    <th>性质</th>\n" +
                "    <th>价格</th>\n" +
                "    <th>是否缴费</th>\n" +
                "    <th>是否完成</th>\n" +
                "    <th>操作</th>\n" +
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
                            "<td>" + callback[i].batches.batchNo + "</td>\n" +
                            "<td>" + callback[i].batches.person.cards.cardNo + "</td>\n" +
                            "<td>" + callback[i].batches.person.perName + "</td>\n" +
                            "<td>" + callback[i].listName + "</td>\n" +
                            "<td>" + callback[i].listType + "</td>\n" +
                            "<td>" + callback[i].listPrice + "</td>\n" +
                            "<td>" + callback[i].batches.batchPay + "</td>\n" +
                            "<td>" + callback[i].batches.batchCmp + "</td>\n";
                        var addr;
                        if(val == "体检缴费") {
                            addr = "<td><a href='balance/2?listId=" + callback[i].listId + "'>缴费</a></td></tr>\n";
                                // "&batchId=" + callback[i].batchId + "'>缴费</a></td></tr>\n";
                            // addr = "<input type='button' value='缴费' class='ui_input_btn01' " +
                            //     "onclick='fetchData2(this.value, " + callback[i].listId + "," + callback[i].batchId + ")'/>";
                        }else{
                            addr = "<td><a href='balance/2?listId=" + callback[i].listId + "'>退费</a></td></tr>\n";
                                // "&batchId=" + callback[i].batchId + "'>退费</a></td></tr>\n";
                            // addr = "<input type='button' value='退费' class='ui_input_btn01' " +
                            //     "onclick='fetchData2(this.value, " + callback[i].listId + "," + callback[i].batchId + ")'/>";
                        }
                        con = con + addr;
                    }
                    end =  "</table>\n";
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
<form id="submitForm" name="submitForm" action="check/guide/2" method="post">
    <input type="hidden" name="allIDCheck" value="" id="allIDCheck"/>
    <input type="hidden" name="fangyuanEntity.fyXqName" value="" id="fyXqName"/>
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_bottom">
                        <span id="showTxt2">${message}</span>
                        卡片编号：
                        <input type="text" name="cardNo" id = "cardNo" class="ui_input_txt02" value="${cardNo}"/>
                        <input type="button" value="体检缴费" class="ui_input_btn01" onclick="fetchData(this.value);"/>
                        <input type="button" value="体检退费" class="ui_input_btn01"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<div id="showTxt" style="width: 95%; float:left; overflow: scroll; height: 500px" >

</div>
<%--            <form id="assoForm">--%>
<%--                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">--%>
<%--                    <tr>--%>
<%--                        <th>序号</th>--%>
<%--                        <th>卡片编号</th>--%>
<%--                        <th>流水号</th>--%>
<%--                        <th>姓名</th>--%>
<%--                        <th>是否缴费</th>--%>
<%--                        <th>打印</th>--%>
<%--                    </tr>--%>
<%--                    <c:if test="${!empty(guideList)}">--%>
<%--                        <c:forEach var="guide"  items="${guideList}">--%>
<%--                            <tr>--%>
<%--                                <td>${guide.batchId}</td>--%>
<%--                                <td>${guide.person.cards.cardNo}</td>--%>
<%--                                <td>${guide.batchNo}</td>--%>
<%--                                <td>${guide.person.perName}</td>--%>
<%--                                <td>${guide.batchPay}</td>--%>
<%--                                <td><a href="check/guide/3?batchId=${guide.batchId}" target="_blank">打印</a></td>--%>
<%--                            </tr>--%>
<%--                        </c:forEach>--%>
<%--                    </c:if>--%>
<%--                </table>--%>
<%--            </form>--%>
<%--        </div>--%>


<%--</form>--%>

</body>

</html>

