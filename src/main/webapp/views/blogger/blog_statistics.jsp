<%--
  Created by IntelliJ IDEA.
  User: DuanJiaNing
  Date: 2018/3/30
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/views/nav/nav_none_selected.jsp" %>
<%@ include file="/views/dialog/toast_dialog.jsp" %>
<html>
<head>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">

    <link rel="stylesheet" href="/css/common.css">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 要在最前面引入-->
    <script src="https://cdn.bootcss.com/jquery/3.3.1/core.js"></script>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <title>博文统计数据</title>

</head>
<body>
<div class="container">

    <p class="lead" id="blogStatistics-title"></p>
    <hr>
    <p>
        博文首次发布于&nbsp;
        <mark id="blogStatistics-releaseDate"></mark>
        <span class="vertical-line">&nbsp;&nbsp;|&nbsp;&nbsp;</span>
        最近修改时间&nbsp;
        <mark id="blogStatistics-nearestModifyDate"></mark>
    </p>

    <p class="vertical-center">
        浏览次数&nbsp;<mark id="blogStatistics-viewCount"></mark>
        <span class="vertical-line">&nbsp;&nbsp;|&nbsp;&nbsp;</span>
        字数&nbsp;<mark id="blogStatistics-wordCount"></mark>
    </p>

    <br>
    <p>
        <mark id="blogStatistics-likeCount"></mark>&nbsp;<b>名博主喜欢了该篇博文</b>
    <hr class="default-line">
    <br>

    <div class="row" id="blogStatistics-liker">
    </div>
    </p>

    <p>
        <mark id="blogStatistics-collectCount"></mark>&nbsp;<b>名博主收藏了该篇博文</b>
    <hr class="default-line">
    <br>

    <div class="row" id="blogStatistics-collector">
    </div>
    </p>

    <hr>
    <p>
        <b>精彩评论</b>(
        <mark id="blogStatistics-commentCount"></mark>
        )
    <hr class="default-line">

    <div class="row" id="blogStatistics-comment">
    </div>
    </p>


    <%--标题--%>
    <%--字数、首次发布日期、最近修改日期--%>
    <%--浏览次数--%>

    <%--头像：评论、喜欢、收藏--%>


</div>

<script type="application/javascript" src="/js/common.js"></script>
<script type="application/javascript" src="/js/blogger/blog_statistics.js"></script>

<script type="application/javascript">
    var blogId = ${blogId};
</script>


</body>
</html>
