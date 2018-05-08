<%--
  Created by IntelliJ IDEA.
  User: DuanJiaNing
  Date: 2018/2/13
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<%@ include file="/views/dialog/login_dialog.jsp" %>

<html>
<head>
    <link rel="stylesheet" href="/css/nav/nav.css">
    <script type="application/javascript" src="/js/nav/nav.js"></script>

</head>
<body>

<nav class="navbar navbar-default navbar-static-top"
     style="background-color: white;padding-top: 8px;padding-bottom: 8px">
    <div class="container" style="height: 50px;width: 82%">

        <table style="height: 100%;width: 100%">
            <tr style="height: 100%">
                <td valign="middle">
                    <%--<b class="navbar-brand BLOG ">BLOG</b>--%>
                    <img class="img58px website-logo" src="/images/logo/logo.png" onclick="gotoRegister()">
                </td>
                <td class="text-right" style="vertical-align: middle">

                    <c:choose>
                        <c:when test="${empty bloggerLoginSignal}">

                            &nbsp;&nbsp;
                            <a class="operation " href="/register">注册</a>
                            &nbsp;&nbsp;

                            &nbsp;&nbsp;
                            <button class="button-success" data-toggle="modal"
                                    data-target="#loginDialog">登录
                            </button>
                            &nbsp;&nbsp;

                        </c:when>
                        <c:otherwise>

                            &nbsp;&nbsp;
                            <a class="operation"
                               href="/${sessionScope['bloggerName']}/archives">主页</a>
                            &nbsp;&nbsp;

                            <c:choose>
                                <c:when test="${type eq 'like'}">

                                    &nbsp;&nbsp;
                                    <a class="operation" href="/${sessionScope["bloggerName"]}/blog/favourite/collect">收藏
                                            <%--<span class="count">(${loginBgStat.collectCount})</span>--%>
                                    </a>
                                    &nbsp;&nbsp;

                                    &nbsp;&nbsp;
                                    <c:choose>
                                        <c:when test="${pageOwnerBloggerId eq sessionScope.bloggerId}">
                                            <a class="operation" style="color: #00CBBA;"
                                               href="/${sessionScope["bloggerName"]}/blog/favourite/like">喜欢</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="operation"
                                               href="/${sessionScope["bloggerName"]}/blog/favourite/like">喜欢</a>
                                        </c:otherwise>
                                    </c:choose>
                                    &nbsp;&nbsp;

                                </c:when>
                                <c:otherwise>

                                    &nbsp;&nbsp;
                                    <c:choose>
                                        <c:when test="${pageOwnerBloggerId eq sessionScope.bloggerId}">
                                            <a class="operation" style="color: #00CBBA;"
                                            href="/${sessionScope["bloggerName"]}/blog/favourite/collect">收藏</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="operation"
                                               href="/${sessionScope["bloggerName"]}/blog/favourite/collect">收藏</a>
                                        </c:otherwise>
                                    </c:choose>
                                    &nbsp;&nbsp;

                                    &nbsp;&nbsp;
                                    <a class="operation"
                                       href="/${sessionScope["bloggerName"]}/blog/favourite/like">喜欢
                                            <%--<span class="count">(${loginBgStat.likedCount})</span>--%>
                                    </a>
                                    &nbsp;&nbsp;

                                </c:otherwise>
                            </c:choose>


                            &nbsp;&nbsp;
                            <a class="line-sperate">|</a>
                            &nbsp;&nbsp;

                            &nbsp;&nbsp;
                            <a class="operation" href="/${sessionScope["bloggerName"]}/setting">设置</a>
                            &nbsp;&nbsp;

                            &nbsp;&nbsp;
                            <button class="button-success"
                                    onclick="window.open('/edit_blog?bid=${sessionScope['bloggerId']}') ">写博文
                            </button>
                            &nbsp;&nbsp;

                            &nbsp;&nbsp;
                            <button onclick="logout(${sessionScope['bloggerId']},'${sessionScope['bloggerName']}')" class="quit">退出
                            </button>
                            &nbsp;&nbsp;

                        </c:otherwise>
                    </c:choose>

                </td>
            </tr>
        </table>

    </div><!-- /.container-fluid -->
</nav>

</body>
</html>
