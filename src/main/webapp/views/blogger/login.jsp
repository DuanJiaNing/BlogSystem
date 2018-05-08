<%--
  Created by IntelliJ IDEA.
  User: DuanJiaNing
  Date: 2017/12/11
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">

    <link rel="stylesheet" href="/css/blogger/login.css">
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

    <title>登录-Blog</title>

</head>
<body style="padding: 3% 4%">
<div class="row">
    <div class="col-md-5" style="padding-left: 64px">
        <div class=" text-center">
            <br>
            <small style="color: gray;font-size: 1.3em" class="lead">请输入您的用户名和密码</small>
            <br>
            <br>
            <hr class="default-line">
            <br>

            <input type="text" id="loginUserName" placeholder="用户名" class="jianshu-style-input login-input">
            <br>
            <br>

            <input type="password" class="jianshu-style-input login-input" id="loginPassword"
                   placeholder="密码">
            <br>
            <br>

            <button class="button-success" style="width: 80%;height: 45px" id="loginBtn" onclick="login()">登录
            </button>
            <br>
            <br>
            <span class="error-msg" id="loginErrorMsg"></span>
            <br>
            <p style="opacity: 0.5;" class="text-center">还没账号，去<a href="/register">&nbsp;注册</a></p>
        </div>
    </div>
    <div class="col-md-7 text-right" style="padding-right: 100px;padding-left: 50px">
        <img src="/images/logo/logo.png" class="logo website-logo">

        <br>
        <hr class="default-line">
        <br>

        <p style="padding-top: 16px;line-height:25px;padding-left:32px;padding-right: 16px;color: dimgray"
           class="text-right">
            创建自己的博文类别和标签，使用 Markdown 语法创作博文，将博文分类，贴上标签既可发布<br>
            只要知道你的用户名，不论他/她是否注册，都能浏览和检索你的公开博文<br>
            注册成为博主，就能评论、喜欢和收藏别人的博文<br>
        </p>
        <br>
        <br>
        <br>
        <br>

        <p>
            去看看他们的主页&nbsp;&nbsp;<img class="scroll-down" src="/images/icon/icons8-scroll-down-80.png"
                                     onclick="$('html,body').animate({scrollTop:500},300);">
        </p>
    </div>

</div>
<br>
<br>

<div class="grid">

    <c:forEach items="${briefs}" var="brief">
        <div class="grid-item grid-item-sub">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8">
                    <div class="image-bg">
                        <img class="grid-item-img img-circle" src="${brief.blogger.avatar.path}"
                             data-adaptive-background='1' title="查看主页"
                             onclick="location.href = '/${brief.blogger.username}/archives'">
                    </div>
                </div>
                <div class="col-md-2"></div>
            </div>

            <p class="text-center">
                <br>
                <span class="check-blog" title="查看主页"
                      onclick="location.href = '/${brief.blogger.username}/archives'">${brief.blogger.username}</span>
            </p>
            <hr>

            <p class="text-center">
                <small>
                    <span>${brief.statistics.blogCount}&nbsp;篇博文</span>
                    <span class="vertical-line">&nbsp;&nbsp;|&nbsp;&nbsp;</span>
                    被喜欢&nbsp;<span>${brief.statistics.likeCount}&nbsp;次</span>
                    <span class="vertical-line">&nbsp;&nbsp;|&nbsp;&nbsp;</span>
                    共写了&nbsp;<span>${brief.statistics.wordCount}&nbsp;字</span>
                </small>
            </p>
            <p class="text-left about-me lead">
                    ${brief.blogger.profile.aboutMe}
            </p>

        </div>
    </c:forEach>

</div>

<br>
<br>
<a href="/dev_help.jsp">开发者入口</a>
<script type="application/javascript" src="/js/common.js"></script>
<script type="application/javascript" src="/js/blogger/login.js"></script>
<script type="application/javascript" src="/plugin/masonry/masonry.pkgd.min.js"></script>
<script type="text/javascript" src='/plugin/adaptiveBgColor/jquery.adaptive-backgrounds.js'></script>

<script>
    var show = false;
</script>


</body>
</html>
