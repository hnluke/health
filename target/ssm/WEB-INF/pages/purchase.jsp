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

        function selected() {
            //alert("Ok");
             $("#showTxt2").html("");
        }

        function pop() {
            window.open("check/list2", "newwindow", "left=300, top=200, height=500, width=600, toolbar=no,menubar=no, scrollbars=no, resizable=no, location=no, status=no");
        }
    </script>
    <style>
        .alt td{ background:black !important;}
    </style>

</head>
<body>
<form id="submitForm" name="submitForm" action="check/list" method="post">
    <input type="hidden" name="allIDCheck" value="" id="allIDCheck"/>
    <input type="hidden" name="fangyuanEntity.fyXqName" value="" id="fyXqName"/>
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_bottom">
                        <span id="showTxt2">${flag}</span>
                        <button onclick="pop()">弹出窗口</button>
                        <input type="button" value="导入采购单" class="ui_input_btn01" id="importBtn2"
                               onclick = "location.href='assets/purchase/im'"/>
                        <input type="submit" value="资产入库" class="ui_input_btn01"/>
                        <input type="button" value="导出" class="ui_input_btn01"
                               onclick="location.href='assets/purchase/ex'" />
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all" onclick="selectOrClearAllCheckbox(this);" />
                        </th>
                        <th>采购定单</th>
                        <th>资产名</th>
                        <th>资产类别</th>
                        <th>规格型号</th>
                        <th>单位</th>
                        <th>数量</th>
                        <th>总价</th>
                        <th>凭证号</th>
<%--                        <th>操作</th>--%>

                    </tr>
                    <c:if test="${!empty(purchase_list)}">
                    <c:forEach var="pur"  items="${purchase_list}">

                    <tr>
                        <td><input type="checkbox" name="IDCheck" value="${pur.purId}" class="acb" onclick = "selected();"/></td>
                        <td>${pur.purCode}</td>
                        <td>${pur.purAssname}</td>
                        <td>${pur.purType}</td>
                        <td>${pur.purModel}</td>
                        <td>${pur.purUnit}</td>
                        <td>${pur.purNum}</td>
                        <td>${pur.purPrices}</td>
                        <td>${pur.purVouno}</td>


<%--                        <td>--%>
<%--                            <a href="house_edit.html?fyID=14458579642011" class="edit">编辑</a>--%>
<%--                            <a href="javascript:del('14458579642011');">删除</a>--%>
<%--                        </td>--%>
                    </tr>
                    </c:forEach>

                    </c:if>

                </table>
            </div>
            <div class="ui_tb_h30">
<%--                <div class="ui_flt" style="height: 30px; line-height: 30px;">--%>
<%--                    共有--%>
<%--                    <span class="ui_txt_bold04">1</span>--%>
<%--                    条记录，当前第--%>
<%--                    <span class="ui_txt_bold04">1--%>
<%--						/--%>
<%--						1</span>--%>
<%--                    页--%>
<%--                </div>--%>
                <div class="ui_frt">
                    <!--    如果是第一页，则只显示下一页、尾页 -->

<%--                    <input type="button" value="首页" class="ui_input_btn01" />--%>
<%--                    <input type="button" value="上一页" class="ui_input_btn01" />--%>
<%--                    <input type="button" value="下一页" class="ui_input_btn01"--%>
<%--                           onclick="jumpNormalPage(2);" />--%>
<%--                    <input type="button" value="尾页" class="ui_input_btn01"--%>
<%--                           onclick="jumpNormalPage(9);" />--%>



                    <!--     如果是最后一页，则只显示首页、上一页 -->

<%--                    转到第<input type="text" id="jumpNumTxt" class="ui_input_txt01" />页--%>
<%--                    <input type="button" class="ui_input_btn01" value="跳转" onclick="jumpInputPage(9);" />--%>
                </div>
            </div>
        </div>
    </div>
</form>

</body>

</html>

