<%--
  Created by IntelliJ IDEA.
  User: DuanJiaNing
  Date: 2018/3/21
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/views/nav/nav_setting.jsp" %>
<%@ include file="/views/dialog/toast_dialog.jsp" %>

<html>
<head>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <link rel="stylesheet" href="/css/blogger/setting.css">
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/paging.css">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 要在最前面引入-->
    <script src="https://cdn.bootcss.com/jquery/3.3.1/core.js"></script>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <title>${sessionScope.bloggerName}-设置</title>
</head>
<body>

<jsp:include page="/views/dialog/upload_avatar_dialog.jsp"/>

<div class="container border" style="min-height: 100%;background-color: white">
    <div class="row" style="padding: 48px 128px;">
        <div class="col-md-3">
            <div class="list-group">
                <button type="button" class="list-group-item btn-item" onclick="showDiv('divBase',this)"
                        style="border: 0;color: orangered;">
                    <img class="img32px" style="opacity: 0.5" src="/images/icon/icons8-tune-80.png">
                    &nbsp;&nbsp;基础设置
                </button>
                <button type="button" class="list-group-item btn-item" onclick="showDiv('divProfile',this)"
                        style="border: 0;">
                    <img class="img32px" style="opacity: 0.5" src="/images/icon/icons8-profile-100.png">
                    &nbsp;&nbsp;资料
                </button>
                <button type="button" class="list-group-item btn-item" onclick="showDiv('divAccount',this)"
                        style="border: 0;">
                    <img class="img32px" style="opacity: 0.5" src="/images/icon/icons8-user-80.png">
                    &nbsp;&nbsp;账号
                </button>
                <button type="button" class="list-group-item btn-item" onclick="showDiv('divStatistic',this)"
                        style="border: 0;">
                    <img class="img32px" style="opacity: 0.5" src="/images/icon/icons8-combo-chart-80.png">
                    &nbsp;&nbsp;统计数据
                </button>
            </div>
        </div>
        <div class="col-md-9">

            <div id="divBase">
                <img src="/image/${sessionScope.bloggerId}/type=public/${profile.avatarId}?default=13"
                     id="blogAvatar"
                     class="avatar-img">
                &nbsp;&nbsp;&nbsp;&nbsp;
                <span class="button-edit-new" data-target="#editAvatarDialog"
                      data-toggle="modal">更改头像</span>

                <br>
                <br>
                <ul class="list-group group">
                    <li class="list-group-item" style="border: 0;">
                        <span class="li-title">用户名</span>
                        &nbsp;&nbsp;
                        <input class="li-input" type="text" value="${sessionScope["bloggerName"]}">
                    </li>
                    <li class="list-group-item">
                        <span class="li-title">电子邮件</span>
                        &nbsp;&nbsp;
                        <input class="li-input" type="email" value="${profile.email}">
                    </li>
                    <li class="list-group-item">
                        <span class="li-title">手机</span>
                        &nbsp;&nbsp;
                        <input class="li-input" type="number" value="${profile.phone}">
                    </li>

                    <li class="list-group-item">
                        <span class="li-title">主页布局</span><br><br>
                        <div>
                            <img style="height: 20%;width: auto" class="shadow-border"
                                 onclick="chooseManNavPos(this,0)"
                                 src="/images/img/main-page-nav-left.jpg">
                            &nbsp;&nbsp;&nbsp;
                            <img style="height: 20%;width: auto" class="shadow-border"
                                 onclick="chooseManNavPos(this,1)"
                                 src="/images/img/main-page-nav-right.jpg">
                        </div>

                    </li>
                </ul>

                <br>
                <br>
                <br>
                <button class="button-save">保存</button>
            </div>

            <div style="display: none" id="divProfile">
                <ul class="list-group group">
                    <li class="list-group-item" style="border: 0;">
                        <span class="li-title">主页 title</span>
                        &nbsp;&nbsp;
                        <input class="li-input" style="width: 80%" type="text" value="${profile.intro}">
                        <br>
                        <br>
                        <p class="text-right">
                            <small style="color: darkgray">显示在个人主页&nbsp;&nbsp;&nbsp;</small>
                        </p>

                    </li>
                    <li class="list-group-item">
                        <div class="vertical-center">
                            <span class="li-title">个人简介</span>
                            &nbsp;&nbsp;
                            <textarea style="width: 80%" class="li-textarea">${profile.aboutMe}</textarea>
                        </div>
                    </li>
                </ul>

                <br>
                <br>
                <br>
                <button class="button-save">保存</button>
            </div>

            <div style="display: none" id="divAccount">
                <ul class="list-group group">
                    <li class="list-group-item" style="border: 0;">
                        <span class="li-title">批量导入博文</span>
                        &nbsp;&nbsp;
                        <span class="button-edit-new" data-target="#editAvatarDialog"
                              data-toggle="modal">下载</span>
                    </li>
                    <li class="list-group-item">
                        <span class="li-title">打包下载</span>
                        &nbsp;&nbsp;
                        <span class="button-edit-check" data-target="#editAvatarDialog"
                              data-toggle="modal">下载所有博文</span>
                    </li>
                    <li class="list-group-item">
                        <a>修改密码</a>
                    </li>
                </ul>
            </div>

            <div style="display: none" id="divStatistic">
            </div>

        </div>
    </div>

</div>

<br>

<jsp:include page="/views/footer.jsp"/>

<script type="application/javascript">
    var bloggerId = ${sessionScope["bloggerId"]};
</script>

<script type="application/javascript" src="/js/paging.js"></script>
<script type="application/javascript" src="/js/common.js"></script>
<script type="application/javascript" src="/js/blogger/setting.js"></script>

</body>
</html>
