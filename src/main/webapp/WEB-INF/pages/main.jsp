<%--
  Created by IntelliJ IDEA.
  User: Luke
  Date: 2020/3/12
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>SCT-后台系统</title>
    <base href="<%=basePath%>">
    <link href="<%=basePath%>style/authority/main_css.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>style/authority/zTreeStyle.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery-1.7.1.js"></script>
    <script type="text/javascript" src="<%=basePath%>scripts/zTree/jquery.ztree.core-3.2.js"></script>
    <script type="text/javascript" src="<%=basePath%>scripts/authority/commonAll.js"></script>
    <script type="text/javascript">
        /**退出系统**/
        function logout(){
            if(confirm("您确定要退出本系统吗？")){
                window.location.href = "user/logout";
            }
        }

        /**获得当前日期**/
        function  getDate01(){
            var time = new Date();
            var myYear = time.getFullYear();
            var myMonth = time.getMonth()+1;
            var myDay = time.getDate();
            if(myMonth < 10){
                myMonth = "0" + myMonth;
            }
            document.getElementById("yue_fen").innerHTML =  myYear + "." + myMonth;
            document.getElementById("day_day").innerHTML =  myYear + "." + myMonth + "." + myDay;
        }

        /**加入收藏夹**/
        function addfavorite(){
            var ua = navigator.userAgent.toLowerCase();
            if (ua.indexOf("360se") > -1){
                art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'由于360浏览器功能限制，加入收藏夹功能失效', ok:true,});
            }else if (ua.indexOf("msie 8") > -1){
                window.external.AddToFavoritesBar('${dynamicURL}/authority/loginInit.action','西宁市公共租赁住房信息管理系统管理');//IE8
            }else if (document.all){
                window.external.addFavorite('${dynamicURL}/authority/loginInit.action','西宁市公共租赁住房信息管理系统管理');
            }else{
                art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'添加失败，请用ctrl+D进行添加', ok:true,});
            }
        }
    </script>
    <script type="text/javascript">
        /* zTree插件加载目录的处理  */
        var zTree;

        var setting = {
            view: {
                dblClickExpand: false,
                showLine: false,
                expandSpeed: ($.browser.msie && parseInt($.browser.version)<=6)?"":"fast"
            },
            data: {
                key: {
                    name: "resourceName"
                },
                simpleData: {
                    enable:true,
                    idKey: "resourceID",
                    pIdKey: "parentID",
                    rootPId: ""
                }
            },
            callback: {
                // 				beforeExpand: beforeExpand,
                // 				onExpand: onExpand,
                onClick: zTreeOnClick
            }
        };

        var curExpandNode = null;
        function beforeExpand(treeId, treeNode) {
            var pNode = curExpandNode ? curExpandNode.getParentNode():null;
            var treeNodeP = treeNode.parentTId ? treeNode.getParentNode():null;
            for(var i=0, l=!treeNodeP ? 0:treeNodeP.children.length; i<l; i++ ) {
                if (treeNode !== treeNodeP.children[i]) {
                    zTree.expandNode(treeNodeP.children[i], false);
                }
            }
            while (pNode) {
                if (pNode === treeNode) {
                    break;
                }
                pNode = pNode.getParentNode();
            }
            if (!pNode) {
                singlePath(treeNode);
            }

        }
        function singlePath(newNode) {
            if (newNode === curExpandNode) return;
            if (curExpandNode && curExpandNode.open==true) {
                if (newNode.parentTId === curExpandNode.parentTId) {
                    zTree.expandNode(curExpandNode, false);
                } else {
                    var newParents = [];
                    while (newNode) {
                        newNode = newNode.getParentNode();
                        if (newNode === curExpandNode) {
                            newParents = null;
                            break;
                        } else if (newNode) {
                            newParents.push(newNode);
                        }
                    }
                    if (newParents!=null) {
                        var oldNode = curExpandNode;
                        var oldParents = [];
                        while (oldNode) {
                            oldNode = oldNode.getParentNode();
                            if (oldNode) {
                                oldParents.push(oldNode);
                            }
                        }
                        if (newParents.length>0) {
                            for (var i = Math.min(newParents.length, oldParents.length)-1; i>=0; i--) {
                                if (newParents[i] !== oldParents[i]) {
                                    zTree.expandNode(oldParents[i], false);
                                    break;
                                }
                            }
                        }else {
                            zTree.expandNode(oldParents[oldParents.length-1], false);
                        }
                    }
                }
            }
            curExpandNode = newNode;
        }

        function onExpand(event, treeId, treeNode) {
            curExpandNode = treeNode;
        }

        /** 用于捕获节点被点击的事件回调函数  **/
        function zTreeOnClick(event, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("dleft_tab1");
            zTree.expandNode(treeNode, null, null, null, true);
            // 		zTree.expandNode(treeNode);
            // 规定：如果是父类节点，不允许单击操作
            if(treeNode.isParent){
                // 			alert("父类节点无法点击哦...");
                return false;
            }
            // 如果节点路径为空或者为"#"，不允许单击操作
            if(treeNode.accessPath=="" || treeNode.accessPath=="#"){
                //alert("节点路径为空或者为'#'哦...");
                return false;
            }
            // 跳到该节点下对应的路径, 把当前资源ID(resourceID)传到后台，写进Session
            rightMain(treeNode.accessPath);

            if( treeNode.isParent ){
                $('#here_area').html('当前位置：'+treeNode.getParentNode().resourceName+'&nbsp;>&nbsp;<span style="color:#1A5CC6">'+treeNode.resourceName+'</span>');
            }else{
                $('#here_area').html('当前位置：系统&nbsp;>&nbsp;<span style="color:#1A5CC6">'+treeNode.resourceName+'</span>');
            }
        };

        /* 上方菜单 */
        function switchTab(tabpage,tabid){
            var oItem = document.getElementById(tabpage).getElementsByTagName("li");
            for(var i=0; i<oItem.length; i++){
                var x = oItem[i];
                x.className = "";
            }
            if('left_tab1' == tabid){
                $(document).ajaxStart(onStart).ajaxSuccess(onStop);
                // 异步加载"业务模块"下的菜单
                loadMenu('YEWUMOKUAI', 'dleft_tab1');
            }else  if('left_tab2' == tabid){
                $(document).ajaxStart(onStart).ajaxSuccess(onStop);
                // 异步加载"系统管理"下的菜单
                loadMenu('XITONGMOKUAI', 'dleft_tab1');
            }else  if('left_tab3' == tabid){
                $(document).ajaxStart(onStart).ajaxSuccess(onStop);
                // 异步加载"其他"下的菜单
                loadMenu('QITAMOKUAI', 'dleft_tab1');
            }
        }


        $(document).ready(function(){
            $(document).ajaxStart(onStart).ajaxSuccess(onStop);
            /** 默认异步加载"业务模块"目录  **/
            loadMenu('YEWUMOKUAI', "dleft_tab1");
            // 默认展开所有节点
            if( zTree ){
                // 默认展开所有节点
                zTree.expandAll(true);
            }
        });

        function loadMenu(resourceType, treeObj){
            <%--$.ajax({--%>
            <%--    type:"POST",--%>
            <%--    url:"${dynamicURL}/authority/modelPart.action?resourceType=" + resourceType,--%>
			<%--	dataType : "json",--%>
			<%--	success:function(data){--%>
			<%--		// 如果返回数据不为空，加载"业务模块"目录--%>
			<%--		if(data != null){--%>
			<%--			// 将返回的数据赋给zTree--%>
			<%--			$.fn.zTree.init($("#"+treeObj), setting, data);--%>
 			<%--			alert(treeObj);--%>
			<%--			zTree = $.fn.zTree.getZTreeObj(treeObj);--%>
			<%--			if( zTree ){--%>
			<%--				// 默认展开所有节点--%>
			<%--				zTree.expandAll(true);--%>
			<%--			}--%>
			<%--		}--%>
			<%--	}--%>
			<%--});--%>
            <%--  data属性说明："accessPath"是需要跳转的页面地址属性，在accessPath后面的引号中输入需要跳转的地址或Controller中的映射地址即可  --%>
            <%-- "parentId"是当前菜单的父菜单的属性，它的值的设定如果与其它菜单项目的"resourceID"值匹配，则与其匹配的菜单项即成为此菜单的父菜单--%>
            <%-- "resourceGrade"是菜单所在级别属性，同一级的菜单需要值设置相同 --%>
            <%--var content = "";--%>
            <%--<c:if test="${!empty(menuList)}">--%>
            <%--<c:forEach items="${menuList}" var="menu" varStatus="stauts">--%>
            <%--<c:if test="${stauts.last}">--%>
            <%--content = content + '{"accessPath":"${menu.menuPath}","checked":true,"delFlag":0,"parentID":${menu.menuParId},"resourceCode":"","resourceDesc":"","resourceGrade":${menu.menuGrpId},"resourceID":${menu.menuResId},"resourceName":"${menu.menuName}","resourceOrder":0,"resourceType":""}'--%>
            <%--</c:if>--%>
            <%--content = content + '{"accessPath":"${menu.menuPath}","checked":true,"delFlag":0,"parentID":${menu.menuParId},"resourceCode":"","resourceDesc":"","resourceGrade":${menu.menuGrpId},"resourceID":${menu.menuResId},"resourceName":"${menu.menuName}","resourceOrder":0,"resourceType":""},'--%>

            <%--</c:forEach>--%>

            <%--</c:if>--%>
            <%--data = [--%>
            <%--    content--%>


            <%--];--%>
            data = [];
            <c:if test="${empty(menuList)}">
                data = [
                    {"accessPath":"","checked":false,"delFlag":0,"parentID":14,"resourceCode":"","resourceDesc":"","resourceGrade":3,"resourceID":26,"resourceName":"系统管理","resourceOrder":0,"resourceType":""},
                    {"accessPath":"manage/user/1","checked":false,"delFlag":0,"parentID":26,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":43,"resourceName":"人员设置","resourceOrder":0,"resourceType":""},
                    {"accessPath":"","checked":false,"delFlag":0,"parentID":26,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":44,"resourceName":"角色配置","resourceOrder":0,"resourceType":""},
                    {"accessPath":"manage/prio/1","checked":false,"delFlag":0,"parentID":26,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":45,"resourceName":"权限配置","resourceOrder":0,"resourceType":""},
                    {"accessPath":"manage/menu/1","checked":false,"delFlag":0,"parentID":26,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":46,"resourceName":"菜单配置","resourceOrder":0,"resourceType":""},
                    {"accessPath":"manage/office/1","checked":false,"delFlag":0,"parentID":26,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":47,"resourceName":"科室设置","resourceOrder":0,"resourceType":""},
                    {"accessPath":"manage/subItem/1","checked":false,"delFlag":0,"parentID":26,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":49,"resourceName":"细项配置","resourceOrder":0,"resourceType":""},
                    {"accessPath":"manage/item/1","checked":false,"delFlag":0,"parentID":26,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":50,"resourceName":"项目配置","resourceOrder":0,"resourceType":""},
                ];

            </c:if>
            <c:if test="${!empty(menuList)}">
                <c:forEach items="${menuList}" var="menu" varStatus="status">

                    data.push({"accessPath":"${menu.menuPath}","checked":true,"delFlag":0,"parentID":${menu.menuParId},"resourceCode":"","resourceDesc":"","resourceGrade":${menu.menuGrpId},"resourceID":${menu.menuResId},"resourceName":"${menu.menuName}","resourceOrder":0,"resourceType":""});


                </c:forEach>
            </c:if>

            // data2 = [
            //     {"accessPath":"","checked":true,"delFlag":0,"parentID":1,"resourceCode":"","resourceDesc":"","resourceGrade":2,"resourceID":11,"resourceName":"体检工作站","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"","checked":false,"delFlag":0,"parentID":1,"resourceCode":"","resourceDesc":"","resourceGrade":2,"resourceID":12,"resourceName":"收费工作站","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"","checked":false,"delFlag":0,"parentID":1,"resourceCode":"","resourceDesc":"","resourceGrade":2,"resourceID":13,"resourceName":"医生工作站","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"","checked":false,"delFlag":0,"parentID":1,"resourceCode":"","resourceDesc":"","resourceGrade":2,"resourceID":14,"resourceName":"管理工作站","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"","checked":false,"delFlag":0,"parentID":1,"resourceCode":"","resourceDesc":"","resourceGrade":2,"resourceID":15,"resourceName":"通用功能","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"","checked":false,"delFlag":0,"parentID":12,"resourceCode":"","resourceDesc":"","resourceGrade":3,"resourceID":23,"resourceName":"收费工作","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"","checked":false,"delFlag":0,"parentID":11,"resourceCode":"","resourceDesc":"","resourceGrade":3,"resourceID":21,"resourceName":"体检工作","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"","checked":false,"delFlag":0,"parentID":11,"resourceCode":"","resourceDesc":"","resourceGrade":3,"resourceID":22,"resourceName":"统计查询","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"","checked":false,"delFlag":0,"parentID":13,"resourceCode":"","resourceDesc":"","resourceGrade":3,"resourceID":24,"resourceName":"医生工作","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"","checked":false,"delFlag":0,"parentID":14,"resourceCode":"","resourceDesc":"","resourceGrade":3,"resourceID":25,"resourceName":"卡片管理","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"","checked":false,"delFlag":0,"parentID":14,"resourceCode":"","resourceDesc":"","resourceGrade":3,"resourceID":26,"resourceName":"系统管理","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"","checked":false,"delFlag":0,"parentID":15,"resourceCode":"","resourceDesc":"","resourceGrade":3,"resourceID":27,"resourceName":"辅助功能","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"check/list/1","checked":false,"delFlag":0,"parentID":21,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":31,"resourceName":"开单","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"check/guide/1","checked":false,"delFlag":0,"parentID":21,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":32,"resourceName":"打印导检单和条码","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"check/report/1","checked":false,"delFlag":0,"parentID":21,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":33,"resourceName":"体检报告打印","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"check/compre/1","checked":false,"delFlag":0,"parentID":22,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":34,"resourceName":"体检综合查询","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"check/persons/1","checked":false,"delFlag":0,"parentID":22,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":35,"resourceName":"体检人员查询","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"charge/createCards/1","checked":false,"delFlag":0,"parentID":23,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":36,"resourceName":"建卡","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"charge/balance/1","checked":false,"delFlag":0,"parentID":23,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":37,"resourceName":"结算","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"assets/finConfig","checked":false,"delFlag":0,"parentID":24,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":38,"resourceName":"项目接收","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"assets/finConfig","checked":false,"delFlag":0,"parentID":24,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":39,"resourceName":"体检小结","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"assets/finConfig","checked":false,"delFlag":0,"parentID":24,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":40,"resourceName":"体检总结","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"manage/initCard/1","checked":false,"delFlag":0,"parentID":25,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":41,"resourceName":"体检卡初始化","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"manage/cardQuery/1","checked":false,"delFlag":0,"parentID":25,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":42,"resourceName":"体检卡查询","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"assets/finConfig","checked":false,"delFlag":0,"parentID":26,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":43,"resourceName":"人员设置","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"assets/finConfig","checked":false,"delFlag":0,"parentID":26,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":44,"resourceName":"角色配置","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"manage/prio/1","checked":false,"delFlag":0,"parentID":26,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":45,"resourceName":"权限配置","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"manage/menu/1","checked":false,"delFlag":0,"parentID":26,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":46,"resourceName":"菜单配置","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"assets/finConfig","checked":false,"delFlag":0,"parentID":26,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":47,"resourceName":"科室设置","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"manage/subItem/1","checked":false,"delFlag":0,"parentID":26,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":49,"resourceName":"细项配置","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"manage/item/1","checked":false,"delFlag":0,"parentID":26,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":50,"resourceName":"项目配置","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"assets/finConfig","checked":false,"delFlag":0,"parentID":27,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":51,"resourceName":"登录","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"manage/logout","checked":false,"delFlag":0,"parentID":27,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":52,"resourceName":"退出","resourceOrder":0,"resourceType":""},
            //     {"accessPath":"assets/finConfig","checked":false,"delFlag":0,"parentID":27,"resourceCode":"","resourceDesc":"","resourceGrade":4,"resourceID":53,"resourceName":"修改密码","resourceOrder":0,"resourceType":""}
            //     // {"accessPath":"assets/intro","checked":false,"delFlag":0,"parentID":24,"resourceCode":"","resourceDesc":"","resourceGrade":3,"resourceID":59,"resourceName":"系统介绍","resourceOrder":0,"resourceType":""}
            //     // {"accessPath":"","checked":false,"delFlag":0,"parentID":24,"resourceCode":"","resourceDesc":"","resourceGrade":3,"resourceID":42,"resourceName":"退出系统","resourceOrder":0,"resourceType":""},
             //   ];
            // 如果返回数据不为空，加载"业务模块"目录
            if(data != null){
                // 将返回的数据赋给zTree
                $.fn.zTree.init($("#"+treeObj), setting, data);
//              alert(treeObj);
                zTree = $.fn.zTree.getZTreeObj(treeObj);
                if( zTree ){
                    // 默认展开所有节点
                    zTree.expandAll(true);
                }
            }
        }

        //ajax start function
        function onStart(){
            $("#ajaxDialog").show();
        }

        //ajax stop function
        function onStop(){
            // 		$("#ajaxDialog").dialog("close");
            $("#ajaxDialog").hide();
        }
    </script>
</head>
<body onload="getDate01()">
<div id="top">
    <div id="top_logo">
<%--        <img alt="logo" src="images/common/logo.jpg" width="274" height="49" style="vertical-align:middle;">--%>
        <img alt="logo" src="" width="274" height="49" style="vertical-align:middle;">
    </div>
    <div id="top_links">
        <div id="top_op">
            <ul>
                <li>
                    <img alt="当前用户" src="images/common/user.jpg">：
                    <span>${user}</span>
                </li>
                <li>
                    <img alt="事务月份" src="images/common/month.jpg">：
                    <span id="yue_fen"></span>
                </li>
                <li>
                    <img alt="今天是" src="images/common/date.jpg">：
                    <span id="day_day"></span>
                </li>
            </ul>
        </div>
        <div id="top_close">
            <a href="javascript:void(0);" onclick="logout();" target="_parent">
                <img alt="退出系统" title="退出系统" src="images/common/close.jpg" style="position: relative; top: 10px; left: 25px;">
            </a>
        </div>
    </div>
</div>
<!-- side menu start -->
<div id="side">
    <div id="left_menu">
        <ul id="TabPage2" style="height:200px; margin-top:50px;">
            <li id="left_tab1" class="selected" onClick="javascript:switchTab('TabPage2','left_tab1');" title="健康体检系统">
                <img alt="业务模块" title="健康体检系统" src="images/common/1_hover.jpg" width="33" height="31">
            </li>
            <li id="left_tab2" onClick="javascript:switchTab('TabPage2','left_tab2');" title="系统管理">
                <img alt="系统管理" title="系统管理" src="images/common/2.jpg" width="33" height="31">
            </li>
            <li id="left_tab3" onClick="javascript:switchTab('TabPage2','left_tab3');" title="其他">
                <img alt="其他" title="其他" src="images/common/3.jpg" width="33" height="31">
            </li>
        </ul>


        <div id="nav_show" style="position:absolute; bottom:0px; padding:10px;">
            <a href="javascript:;" id="show_hide_btn">
                <img alt="显示/隐藏" title="显示/隐藏" src="images/common/nav_hide.png" width="35" height="35">
            </a>
        </div>
    </div>
    <div id="left_menu_cnt">
        <div id="nav_module">
            <img src="images/common/module_1.png" width="210" height="58"/>
        </div>
        <div id="nav_resource">
            <ul id="dleft_tab1" class="ztree"></ul>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function(){
        $('#TabPage2 li').click(function(){
            var index = $(this).index();
            $(this).find('img').attr('src', 'images/common/'+ (index+1) +'_hover.jpg');
            $(this).css({background:'#fff'});
            $('#nav_module').find('img').attr('src', 'images/common/module_'+ (index+1) +'.png');
            $('#TabPage2 li').each(function(i, ele){
                if( i!=index ){
                    $(ele).find('img').attr('src', 'images/common/'+ (i+1) +'.jpg');
                    $(ele).css({background:'#044599'});
                }
            });
            // 显示侧边栏
            switchSysBar(true);
        });

        // 显示隐藏侧边栏
        $("#show_hide_btn").click(function() {
            switchSysBar();
        });
    });

    /**隐藏或者显示侧边栏**/
    function switchSysBar(flag){
        var side = $('#side');
        var left_menu_cnt = $('#left_menu_cnt');
        if( flag==true ){	// flag==true
            left_menu_cnt.show(500, 'linear');
            side.css({width:'280px'});
            $('#top_nav').css({width:'77%', left:'304px'});
            $('#main').css({left:'280px'});
        }else{
            if ( left_menu_cnt.is(":visible") ) {
                left_menu_cnt.hide(10, 'linear');
                side.css({width:'60px'});
                $('#top_nav').css({width:'100%', left:'60px', 'padding-left':'28px'});
                $('#main').css({left:'60px'});
                $("#show_hide_btn").find('img').attr('src', 'images/common/nav_show.png');
            } else {
                left_menu_cnt.show(500, 'linear');
                side.css({width:'280px'});
                $('#top_nav').css({width:'77%', left:'304px', 'padding-left':'0px'});
                $('#main').css({left:'280px'});
                $("#show_hide_btn").find('img').attr('src', 'images/common/nav_hide.png');
            }
        }
    }
</script>
<!-- side menu start -->
<div id="top_nav">
    <span id="here_area">当前位置：系统&nbsp;>&nbsp;系统介绍</span>
</div>
<div id="main">
    <iframe name="right" id="rightMain" src="user/top" frameborder="no" scrolling="auto" width="100%" height="100%" allowtransparency="true"/>
</div>

</body>
</html>

