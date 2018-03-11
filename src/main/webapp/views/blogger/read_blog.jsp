<%--
  Created by IntelliJ IDEA.
  User: DuanJiaNing
  Date: 2018/3/7
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/views/nav/nav_read_blog.jsp" %>
<%@ include file="/views/dialog/toast_dialog.jsp" %>

<html>
<head>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/blogger/read_blog.css">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 要在最前面引入-->
    <script src="https://cdn.bootcss.com/jquery/3.3.1/core.js"></script>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <script type="application/javascript" src="/js/common.js"></script>

    <title>${main["title"]}</title>

</head>
<body>

<div class="operation-container shadow-border">
    <button id="goto-top" title="回到顶部">TOP</button>
    <br>

    <button title="分享" onclick="shareBlog()">分享</button>
    <br>

    <button title="投诉" onclick="complainBlog()">投诉</button>
    <br>
    <c:choose>
        <c:when test="${not empty collectState}">
            <button title="取消收藏" onclick="collectBlog(this)">取消收藏</button>
        </c:when>
        <c:otherwise>
            <button title="收藏" onclick="collectBlog(this)">收藏</button>
        </c:otherwise>
    </c:choose>
    <br>

    <c:choose>
        <c:when test="${not empty likeState}">
            <button title="取消喜欢" onclick="likeBlog(this)">取消喜欢</button>
        </c:when>
        <c:otherwise>
            <button title="喜欢" onclick="likeBlog(this)">喜欢</button>
        </c:otherwise>
    </c:choose>
    <br>
</div>

<div class="container" style="min-height: 100%">
    <div class="shadow-border blog-content">

        <p class="text-center blog-title">
            ${main["title"]}
        </p>
        <p class="text-center">
            <small style="color: gray">
                发表于&nbsp;<script>document.write(dateFormat(new Date('${main.releaseDate}')))</script>
                <span class="vertical-line">&nbsp;&nbsp;|&nbsp;&nbsp;</span>
                喜欢&nbsp;${stat.likeCount}
                <span class="vertical-line">&nbsp;&nbsp;|&nbsp;&nbsp;</span>
                收藏&nbsp;${stat.collectCount}
                <span class="vertical-line">&nbsp;&nbsp;|&nbsp;&nbsp;</span>
                评论&nbsp;${stat.commentCount}
                <span class="vertical-line">&nbsp;&nbsp;|&nbsp;&nbsp;</span>
                ${main["wordCount"]}&nbsp;字
            </small>
        </p>
        <hr>

        <p class="text-center">
            ${main["content"]}
        </p>
    </div>
</div>
<br>
<br>

<jsp:include page="/views/footer.jsp"/>

<script type="application/javascript">
    <%--var pageOwnerBloggerId = ${pageOwnerBloggerId};--%>
    <%--var pageOwnerBloggerName = '${pageOwnerBloggerName}';--%>

    var bloggerLoginSignal = ${not empty sessionScope['bloggerLoginSignal']};
    var blogId = ${main.id};
    <c:if test="${not empty sessionScope['bloggerLoginSignal']}">
    var loginBloggerId = ${sessionScope["bloggerId"]};
    </c:if>
</script>

<script type="application/javascript" src="/js/blogger/read_blog.js"></script>

</body>
</html>
