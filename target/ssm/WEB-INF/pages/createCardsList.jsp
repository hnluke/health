<%--
  Created by IntelliJ IDEA.
  User: Luke
  Date: 2020/3/17
  Time: 22:32
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
    <title>用户建卡</title>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="scripts/jquery/jquery-1.7.1.js"></script>
    <link href="style/authority/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="style/authority/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="scripts/authority/commonAll.js"></script>
    <script type="text/javascript" src="scripts/fancybox/jquery.fancybox-1.3.4.js"></script>
    <script type="text/javascript" src="scripts/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
    <link rel="stylesheet" type="text/css" href="style/authority/jquery.fancybox-1.3.4.css" media="screen"></link>
    <script type="text/javascript" src="scripts/artDialog/artDialog.js?skin=default"></script>
    <script src="<%=basePath%>My97DatePicker/WdatePicker.js"></script>

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
    </script>
    <style>
        .alt td{ background:black !important;}
    </style>
</head>
<body>
<form id="submitForm" name="submitForm" action="charge/createCards/2" method="post">
    <input type="hidden" name="allIDCheck" value="" id="allIDCheck"/>
    <input type="hidden" name="fangyuanEntity.fyXqName" value="" id="fyXqName"/>
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">建卡</div>

                    <div id="box_center" style="text-align: center;font-weight: bold;font-size: large;">
                        用户建卡

                    </div>
                    <div class="ui_content">
                        <div class="ui_tb" align="center">
                            <table align="center">
                                <tr>
                                    <td>姓名:</td>
                                    <td><input type="text" id="perName" name="perName" class="ui_input_txt02" autocomplete="off"/></td>
                                    <td>性别:</td>
                                    <td>
                                        <select name="perSex" class ="ui_select01">
                                            <option value="男">男</option>
                                            <option value="女">女</option>
                                        </select>
                                    </td>
                                    <td>出生年月:</td>
                                    <td><input type="text" id="perBorn" name="perBorn"
                                               class="ui_input_txt02" autocomplete="off" onclick="WdatePicker()"/></td>
                                </tr>
                                <tr>
                                    <td>血型:</td>
                                    <td>
                                        <select name="perBlood" class ="ui_select01">
                                            <option value="未知">--请选择--</option>
                                            <option value="A型">A型</option>
                                            <option value="B型">B型</option>
                                            <option value="AB型">AB型</option>
                                            <option value="O型">O型</option>
                                            <option value="RH型">RH型</option>
                                        </select>

                                    </td>
                                    <td>电话:</td>
                                    <td><input type="text" id="perTele" name="perTele" class="ui_input_txt02" autocomplete="off"/></td>
                                    <td>卡号:</td>
                                    <td>
                                        <input type="text" id="cardNo" name="cardNo" class="ui_input_txt02" disabled="disabled"/>
                                        <input type="hidden" name="cardId" id="cardId" value="123" />
                                        <button type="button" onclick="fetchCardNo()">获取卡号</button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>体检人住址:</td>
                                    <td colspan="5"><input type="text" name="perAddr" id="perAddr"
                                                           class="ui_input_txt02" style="width: 90%" autocomplete="off"/></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br/>
    <div style="text-align: center">
        <input type="submit" value="新增" class="ui_input_btn01" />
        <input type="reset" value="复位" class="ui_input_btn01"/>
    </div>
</form>

<div id = "guide" style="width: 100%; float:left; overflow: scroll; height: 300px" >
    体检人建卡信息：
    <c:if test="${!empty(cardsList)}">
        <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="2">
            <tr>
                <td>序号</td>
                <td>卡号</td>
                <td>姓名</td>
                <td>性别</td>
                <td>血型</td>
                <td>出生年月</td>
                <td>电话</td>
                <td>住址</td>
<%--                <td>操作</td>--%>

            </tr>
            <c:forEach var="cards" items="${cardsList}" varStatus="status">

                <tr>
                    <td>${status.index + 1 }</td>
                    <td>${cards.cardNo}</td>
                    <td>${cards.person.perName}</td>
                    <td>${cards.person.perSex}</td>
                    <td>${cards.person.perBlood}</td>
                    <td>${cards.person.perBorn}</td>
                    <td>${cards.person.perTele}</td>
                    <td>${cards.person.perAddr}</td>
<%--                    <td><a href="/charge/createCards/3?cardNo=${cards.cardNo}">删除</a></td>--%>
                </tr>

            </c:forEach>
        </table>
    </c:if>
</div>

</body>
<script type="text/javascript">
    function fetchCardNo() {
        var contexts = {};
        var url = "charge/jsondata";
        $.ajax({
            type: "post",
            url: url,
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(contexts),
            success: function (callback) {
                $("#cardNo").val(callback[0].cardNo);
                $("#cardId").val(callback[0].cardId);

            }
        });

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
</script>
</html>


