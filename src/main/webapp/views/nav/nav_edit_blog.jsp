<%--
  Created by IntelliJ IDEA.
  User: DuanJiaNing
  Date: 2018/2/13
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/views/dialog/release_blog_dialog.jsp" %>

<html>
<head>
    <link rel="stylesheet" href="/css/nav/nav.css">
    <script type="application/javascript" src="/js/nav/nav.js"></script>

</head>
<body>

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
            <b><a class="navbar-brand BLOG">BLOG</a></b>
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
                        <li class="blogger-option"><a href="/${sessionScope['bloggerName']}/archives">我的主页</a>
                        </li>
                        <li class="blogger-option"><a href="#">收藏的文章&nbsp;<span
                                class="count">(${loginBgStat.collectCount})</span></a>
                        </li>
                        <li class="blogger-option"><a href="#">喜欢的文章&nbsp;<span
                                class="count">(${loginBgStat.likedCount})</span></a>
                        </li>
                        <li class="blogger-option"><a href="#">设置</a></li>
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

</body>
</html>
