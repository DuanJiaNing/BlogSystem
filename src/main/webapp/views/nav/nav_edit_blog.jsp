<%--
  Created by IntelliJ IDEA.
  User: DuanJiaNing
  Date: 2018/2/13
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ include file="/views/dialog/release_blog_dialog.jsp" %>

<html>
<head>
    <link rel="stylesheet" href="/css/nav/nav.css">
    <script type="application/javascript" src="/js/nav/nav.js"></script>

</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top"
     style="background-color: white;padding-top: 8px;padding-bottom: 8px">
    <div class="container" style="height: 50px;width: 82%">

        <table style="height: 100%;width: 100%">
            <tr style="height: 100%">
                <td valign="middle">
                    <%--<b class="navbar-brand BLOG ">BLOG</b>--%>
                    <img class="img58px website-logo" src="/images/logo/logo.png" onclick="gotoRegister()">

                </td>
                <td class="text-right" style="vertical-align: middle">

                    &nbsp;&nbsp;
                    <a class="operation"
                       href="/${sessionScope['bloggerName']}/archives">主页</a>
                    &nbsp;&nbsp;

                    &nbsp;&nbsp;
                    <a class="operation" href="/${sessionScope["bloggerName"]}/blog/favourite/collect">收藏
                        <%--<span class="count">(${loginBgStat.collectCount})</span>--%>
                    </a>
                    &nbsp;&nbsp;

                    &nbsp;&nbsp;
                    <a class="operation" href="/${sessionScope["bloggerName"]}/blog/favourite/like">喜欢
                        <%--<span class="count">(${loginBgStat.likedCount})</span>--%>
                    </a>
                    &nbsp;&nbsp;

                    &nbsp;&nbsp;
                    <a class="line-sperate">|</a>
                    &nbsp;&nbsp;

                    &nbsp;&nbsp;
                    <a class="operation" href="/${sessionScope["bloggerName"]}/setting">设置</a>
                    &nbsp;&nbsp;

                    &nbsp;&nbsp;
                    <button class="button-success" data-toggle="modal"
                            data-target="#releaseBlogDialog">立即发布
                    </button>
                    &nbsp;&nbsp;

                    &nbsp;&nbsp;
                    <button onclick="logout(${sessionScope['bloggerId']},'${sessionScope['bloggerName']}')" class="quit">退出
                    </button>
                    &nbsp;&nbsp;

                </td>
            </tr>
        </table>

    </div><!-- /.container-fluid -->
</nav>

</body>
</html>
