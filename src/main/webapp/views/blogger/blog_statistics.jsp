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
    <link rel="stylesheet" href="/css/blogger/blog_statistics.css">

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
<div class="container" style="min-height: 80%">

    <%--标题--%>
    <%--字数、首次发布日期、最近修改日期--%>
    <%--浏览次数--%>

    <%--头像：评论、喜欢、收藏--%>

    <p class="lead" id="blogStatistics-title"></p>
    <hr>

    <div class="row">
        <div class="col-md-7">
            <div class="panel panel-default shadow">
                <div class="panel-body" style="margin: 32px;">

                    <br>
                    <p>
                        <b>作者：</b>
                        <a target="_blank" href="/${bloggerName}/archives"
                           class="button-info blog-author">${bloggerName}</a>
                    </p>
                    <br>
                    <p>
                        博文首次发布于&nbsp;
                        <mark id="blogStatistics-releaseDate"></mark>
                        <span class="vertical-line">&nbsp;&nbsp;|&nbsp;&nbsp;</span>
                        最近修改时间&nbsp;
                        <mark id="blogStatistics-nearestModifyDate"></mark>
                    </p>

                    <p class="vertical-center">
                        浏览次数&nbsp;<span class="count" id="blogStatistics-viewCount"></span>
                        <span class="vertical-line">&nbsp;&nbsp;|&nbsp;&nbsp;</span>
                        字数&nbsp;<span class="count" id="blogStatistics-wordCount"></span>
                    </p>

                    <br>
                    <p>
                        <span class="count" id="blogStatistics-likeCount"></span>&nbsp;<b>名博主喜欢了这篇博文</b>
                    <hr class="default-line">
                    <br>

                    <div class="row" id="blogStatistics-liker">
                    </div>
                    </p>

                    <p>
                        <span class="count" id="blogStatistics-collectCount"></span>&nbsp;<b>名博主收藏了这篇博文</b>
                    <hr class="default-line">
                    <br>

                    <div class="row" id="blogStatistics-collector">
                    </div>
                    </p>

                    <button class="button-save" id="newLinkBtn" onclick="goCheckBlog()">前往查看
                    </button>
                    <br><br>
                </div>
            </div>
        </div>
        <div class="col-md-5">
            <p>
                <b>精彩评论</b>
                <span class="count" id="blogStatistics-commentCount"></span>
            <hr>

            <div id="blogStatistics-comment" style="overflow: auto;max-height: 60%;">
            </div>
            </p>

        </div>
    </div>

</div>

<br>
<jsp:include page="/views/footer/footer.jsp"/>

<script type="application/javascript">
    var blogId = ${blogId};
    var bloggerName = '${bloggerName}';
</script>

<script type="application/javascript" src="/js/common.js"></script>
<script type="application/javascript" src="/js/blogger/blog_statistics.js"></script>


</body>
</html>
