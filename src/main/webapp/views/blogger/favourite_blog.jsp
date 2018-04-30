<%--
  Created by IntelliJ IDEA.
  User: DuanJiaNing
  Date: 2018/3/14
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/views/nav/nav_favourite_blog.jsp" %>
<%@ include file="/views/dialog/toast_dialog.jsp" %>

<html>
<head>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/paging.css">
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/blogger/favourite_blog.css">

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

    <c:choose>
        <c:when test="${type eq 'like'}">
            <title>${pageOwnerBloggerName}-喜欢的博文</title>
        </c:when>
        <c:otherwise>
            <title>${pageOwnerBloggerName}-收藏的博文</title>
        </c:otherwise>
    </c:choose>
</head>
<body>

<div class="operation-container border clickable-gray" onclick="scrollToTop();" style="padding: 11px">
    <img class="img24px" src="/images/icon/icons8-collapse-arrow-64.png" title="回到顶部">
</div>

<div class="container" style="min-height: 100%">
    <!-- Content here -->
    <div class="row">
        <p>
        <h3>&nbsp;&nbsp;
            <c:choose>
                <c:when test="${type eq 'like'}">
                    <b><a href="/${pageOwnerBloggerName}/archives">${pageOwnerBloggerName}</a></b>&nbsp;喜欢的博文
                </c:when>
                <c:otherwise>
                    <b><a href="/${pageOwnerBloggerName}/archives">${pageOwnerBloggerName}</a></b>&nbsp;收藏的博文
                </c:otherwise>
            </c:choose>
            <small id="blogCount"></small>
        </h3>
        </p>
    </div>

    <br>
    <p style="border-bottom: solid 1px lightgray"></p>
    <br>

    <div class="border blog-container">
        <div id="blogList">
        </div>
        <div class="box" id="box"></div>
    </div>
    <br>
    <br>

</div>

<jsp:include page="/views/footer/footer.jsp"/>

<script type="application/javascript" src="/js/paging.js"></script>
<script type="application/javascript" src="/js/blogger/favourite_blog.js"></script>
<script type="application/javascript">
    var type = '${type}';

    var pageOwnerBloggerId = ${pageOwnerBloggerId};
    var pageOwnerBloggerName = '${pageOwnerBloggerName}';
    var bloggerLoginSignal = ${not empty sessionScope['bloggerLoginSignal']};
    <c:if test="${not empty sessionScope['bloggerLoginSignal']}">
    var loginBloggerId = ${sessionScope["bloggerId"]};
    </c:if>
</script>

</body>
</html>
