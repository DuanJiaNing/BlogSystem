<%--
  Created by IntelliJ IDEA.
  User: DuanJiaNing
  Date: 2018/2/13
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="/css/nav/nav.css">
    <link rel="stylesheet" href="/css/nav/nav_edit_blog.css">
    <script type="application/javascript" src="/js/nav/nav.js"></script>

    <script>
        // 登录博主id
        var bloggerId = ${sessionScope['bloggerId']};

        // 编辑博文时有值
        var labelId = '${labelIds}';
        var categoryId = '${categoryId}';

    </script>

</head>
<body>

<jsp:include page="/views/dialog/toast_dialog.jsp"/>

<%--立即发布--%>
<div class="modal fade" tabindex="-1" role="dialog" id="releaseBlogDialog">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content dialog-title-container">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title dialog-title">立即发布</h4>
            </div>
            <div class="modal-body dialog-body">
                <div class="form-group">
                    <label>标题</label><br>
                    <input type="text" id="editBlogTitle" placeholder="博文标题"
                           class="form-input" value="${blogTitle}"><br><br>
                </div>

                <div class="form-group">
                    <label>摘要</label><br>
                    <textarea class="default-textarea" id="editBlogSummary"
                              style="border: solid 1px lightgray;height: 123px;width: 70%"
                              placeholder="博文摘要">${blogSummary}</textarea>
                </div>

                <div class="form-group">
                    <label>选择类别</label><br>
                    <p id="editBlogCategory" style="line-height: 28px;max-height: 100px"></p>
                </div>
                <div class="form-group">
                    <label>选择标签</label><br>
                    <p id="editBlogLabel" style="line-height: 28px;max-height: 100px"></p>
                </div>

                <br>
                <p>
                    <small style="color: gray;">设置为私有</small>
                    <c:choose>
                        <c:when test="${blogIsPrivate} not empty">
                            <input type="checkbox" id="editSetPrivate" style="margin-top: 3px;padding-top: 5px"
                                   checked="checked">
                        </c:when>
                        <c:otherwise>
                            <input type="checkbox" id="editSetPrivate" style="margin-top: 3px;padding-top: 5px">
                        </c:otherwise>
                    </c:choose>
                </p>

                <span class="error-msg" id="editBlogErrorMsg"></span>
            </div>
            <div class="modal-footer dialog-footer">
                <p class="text-right">
                    <a id="complexFilterBtnReset">清空
                    </a>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="button-success" id="editReleaseBtn">发布
                    </button>&nbsp;&nbsp;
                </p>
            </div>
        </div><!-- /.modal-content -->
    </div>
</div>

<nav class="navbar navbar-default navbar-fixed-top" style="background-color: white">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <b><a class="navbar-brand os-name BLOG">BLOG</a></b>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-haspopup="true"
                       aria-expanded="false">${sessionScope["bloggerName"]}<span
                            class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li class="blogger-option"><a href="/${sessionScope['bloggerName']}/archives">主页</a>
                        </li>
                        <li class="blogger-option"><a href="#">收藏的博文&nbsp;<span class="count">(12)</span></a>
                        </li>
                        <li class="blogger-option"><a href="#">喜欢的博文&nbsp;<span class="count">(0)</span></a>
                        </li>
                        <li class="blogger-option"><a href="#">管理</a></li>
                        <li class="blogger-option"><a href="#">反馈</a></li>
                        <li role="separator" class="divider"></li>
                        <li class="blogger-option"><a onclick="logout(bloggerId)"><span
                                class="quit">退出</span></a></li>
                    </ul>
                </li>
                <li>
                    &nbsp;
                    &nbsp;
                    <button type="button" class="button-top-main"
                            data-toggle="modal"
                            data-target="#releaseBlogDialog">立即发布
                    </button>
                </li>


            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<script type="text/javascript" src="/js/nav/nav_edit_blog.js"></script>

</body>
</html>
