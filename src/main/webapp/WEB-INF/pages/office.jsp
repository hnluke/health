<%--
  Created by IntelliJ IDEA.
  User: 陈萍
  Date: 2020/5/2
  Time: 22:30
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
    <title>用户登录界面</title>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="scripts/jquery/jquery-1.7.1.js"></script>
    <link href="style/authority/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="style/authority/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="scripts/authority/commonAll.js"></script>
    <script type="text/javascript" src="scripts/fancybox/jquery.fancybox-1.3.4.js"></script>
    <script type="text/javascript" src="scripts/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
    <link rel="stylesheet" type="text/css" href="style/authority/jquery.fancybox-1.3.4.css" media="screen"></link>
    <script type="text/javascript" src="scripts/artDialog/artDialog.js?skin=default"></script>

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
        // function add(){
        //     $("#
        //     ").attr("action", "/xngzf/archives/luruFangyuan.action").submit();
        // }

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

        /** 删除科室 **/
        function del(offId){
            alert("OK");
            // 非空判断
            <%--if(offId == '') return;--%>
            <%--if(confirm("您确定要删除吗？")){--%>
            <%--    window.location.href = "${pageContext.request.contextPath}/office/deleteOffice?offId=" + offId;--%>
            <%--}--%>
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
            indexs = ${current};
            if(page == 2){
                indexs = ${current} + 1;

            }else{
                if(indexs > 1) {
                    indexs =  indexs - 1;
                }
            }

            $("#submitForm").attr("action", "${pageContext.request.contextPath}/FinConfirmServlet?co=1&page=" + indexs).submit();
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
<form id="submitForm" name="submitForm" action="" method="post">
    <input type="hidden" name="allIDCheck" value="" id="allIDCheck"/>
    <input type="hidden" name="fangyuanEntity.fyXqName" value="" id="fyXqName"/>
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_bottom">
                        <input type="button" value="增加新科室"  id="importBtn" style="margin-right: 100px"
                               onclick="location.href='${pageContext.request.contextPath}/office/toAddOffice'"/>


<%--                        请输入凭证号：<input type="text" name="vou" id="jumpNumTxt" class="ui_input_txt02"/>--%>
<%--                        <input type="button" value="确定" class="ui_input_btn01" id="importBtn" name="vouBut"--%>
<%--                            onclick="conAssets();"/>--%>
<%--                        ${finCon}--%>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"  onclick="selectOrClearAllCheckbox(this);" />
                        </th>
                        <th>科室</th>
                        <th>是否删除</th>
                    </tr>
                    <c:if test="${!empty(officeList)}">
                        <c:forEach var="pur"  items="${officeList}">

                            <tr>
                                <td><input type="checkbox" name="IDCheck" value="${pur.offId}" class="acb" /></td>
                                <td>${pur.offName}</td>
                                <td>
<%--                                    <input type="hidden" name="offId" value="${pur.offId}">--%>
<%--                                    <input type="submit" value="删除" >--%>
<%--                                         <a href="javascript:void(0);" onclick="del(${pur.offId});" >删除</a>--%>
<%--&lt;%&ndash;                                        <a href="office/deleteOffice?offId=${pur.offId}"  >删除</a>&ndash;%&gt;--%>

                                   <a href="javascript:if(confirm('确实要删除吗?'))
                                                location='office/deleteOffice?offId=${pur.offId}'">删除</a>

                                </td>
                            </tr>
                        </c:forEach>

                    </c:if>

                </table>
            </div>
            <div class="ui_tb_h30">
                <div class="ui_frt">
                </div>            </div>
        </div>
    </div>
</form>

</body>
<script>
    function conAssets() {
        var ad_avno;
        ad_avno = $.trim($("#jumpNumTxt").val());
        if( ad_avno == ""){
            alert("请输入凭证号");
        }else{
            location.href="${pageContext.request.contextPath}/FinConfirmServlet?co=2&page=1&txt=" + ad_avno;
        }
    }
</script>
</html>

