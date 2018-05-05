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
<%@ include file="/views/dialog/blog_import.jsp" %>
<%@ include file="/views/dialog/download_all_blog_dialog.jsp" %>
<%@ include file="/views/dialog/confirm_dialog.jsp" %>

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
    <div class="row" style="padding: 5% 10%;">
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
                <%--<button type="button" class="list-group-item btn-item" onclick="showDiv('divStatistic',this)"--%>
                <%--style="border: 0;">--%>
                <%--<img class="img32px" style="opacity: 0.5" src="/images/icon/icons8-combo-chart-80.png">--%>
                <%--&nbsp;&nbsp;统计数据--%>
                <%--</button>--%>
            </div>

            <br>
            <div style="padding-left: 16px;">
                <a href="/help-feedback">帮助与反馈</a>
                <hr>
                <small style="color: gray">
                    修改之后记得点击&nbsp;<b>保存</b><br>
                    使修改生效。
                </small>
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
                        <input class="jianshu-style-input" type="text" value="${sessionScope["bloggerName"]}"
                               id="modifyBloggerName">
                    </li>
                    <li class="list-group-item">
                        <span class="li-title">电子邮件</span>
                        &nbsp;&nbsp;
                        <input class="jianshu-style-input" type="email" value="${profile.email}"
                               id="modifyProfileEmail">
                    </li>
                    <li class="list-group-item">
                        <span class="li-title">手机</span>
                        &nbsp;&nbsp;
                        <input class="jianshu-style-input" value="${profile.phone}"
                               id="modifyProfilePhone">
                    </li>

                    <li class="list-group-item">
                        <span class="li-title">主页布局</span><br><br>
                        <div>
                            <c:choose>
                                <c:when test="${setting.mainPageNavPos eq 0}">

                                    <img style="height: 20%;width: auto;border-radius: 5px;border-bottom: solid 2px #09B2E3"
                                         id="mainPageNavLeft"
                                         class="shadow-border"
                                         onclick="chooseManNavPos(this,0)"
                                         src="/images/img/main-page-nav-left.jpg">
                                    &nbsp;&nbsp;&nbsp;
                                    <img style="height: 20%;width: auto;border-radius: 5px" id="mainPageNavRight"
                                         class="shadow-border"
                                         onclick="chooseManNavPos(this,1)"
                                         src="/images/img/main-page-nav-right.jpg">
                                </c:when>
                                <c:otherwise>

                                    <img style="height: 20%;width: auto;border-radius: 5px" id="mainPageNavLeft"
                                         class="shadow-border"
                                         onclick="chooseManNavPos(this,0)"
                                         src="/images/img/main-page-nav-left.jpg">
                                    &nbsp;&nbsp;&nbsp;
                                    <img style="height: 20%;width: auto;border-radius: 5px;border-bottom: solid 2px #09B2E3"
                                         id="mainPageNavRight"
                                         class="shadow-border"
                                         onclick="chooseManNavPos(this,1)"
                                         src="/images/img/main-page-nav-right.jpg">
                                </c:otherwise>
                            </c:choose>
                        </div>

                    </li>
                </ul>

                <button class="button-save" id="settingBtnBase" onclick="saveBaseDiv()">保存</button>
            </div>

            <div style="display: none" id="divProfile">
                <ul class="list-group group">
                    <li class="list-group-item" style="border: 0;">
                        <span class="li-title">主页 title</span>
                        &nbsp;&nbsp;
                        <input class="jianshu-style-input" style="width: 80%" type="text" value="${profile.intro}"
                               id="modifyProfileIntro">
                        <br>
                        <br>
                        <p class="text-right">
                            <small style="color: darkgray">显示在个人主页&nbsp;&nbsp;&nbsp;</small>
                        </p>

                    </li>
                    <li class="list-group-item">
                        <div class="vertical-center">
                            <span class="li-title">博主自述</span>
                            &nbsp;&nbsp;
                            <textarea style="width: 80%" class="jianshu-style-textarea"
                                      id="modifyProfileAboutMe">${profile.aboutMe}</textarea>
                        </div>
                    </li>
                </ul>

                <button class="button-save" id="settingBtnProfile" onclick="saveProfileDiv()">保存</button>
            </div>

            <div style="display: none" id="divAccount">
                <ul class="list-group group">
                    <li class="list-group-item" style="border: 0;">
                        <span class="li-title">批量导入博文</span>
                        &nbsp;&nbsp;
                        <span class="button-edit-new" data-target="#fileUploadDialog"
                              data-toggle="modal">导入</span>
                    </li>
                    <li class="list-group-item">
                        <span class="li-title">打包下载</span>
                        &nbsp;&nbsp;
                        <span class="button-edit-check" data-target="#downloadAllBlogDialog"
                              data-toggle="modal">下载所有博文</span>
                    </li>
                    <li>
                        <br>

                        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                            <div class="panel panel-default">
                                <div class="panel-heading shadow-border-for-setting" role="tab" id="deleteAccountDiv"
                                     style="background-color: transparent;" data-toggle="collapse"
                                     data-parent="#accordion"
                                     href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    <h4 class="panel-title">
                                        删除账号
                                    </h4>
                                </div>
                                <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel"
                                     aria-labelledby="deleteAccountDiv">
                                    <div class="panel-body">
                                        <br>
                                        <h4>永久删除帐号</h4>
                                        <br>
                                        该操作将会清空您的所有博文和博文相关数据，创建的类别、标签，发表的评论，收藏和喜欢的博文以及账号相关数据，在此之前你可能需要
                                        &nbsp;<a data-target="#downloadAllBlogDialog" data-toggle="modal">打包下载博文</a>&nbsp;。
                                        <hr class="default-line">
                                        如果你对&nbsp;<i>BLOG</i>&nbsp;的某些内容、功能不满意，你可以在&nbsp;<a
                                            href="/help-feedback">帮助与反馈</a>&nbsp;页向我提出。
                                        <hr class="default-line">
                                        删除帐号是不可逆的操作，删除后将无法恢复。
                                        <br>
                                        <br>
                                        <button class="button-dangerous" data-target="#confirmDialog"
                                                data-toggle="modal">删除
                                        </button>&nbsp;
                                        <small style="color: darkgray;">期待下次注册</small>
                                        <br>
                                        <br>
                                    </div>
                                </div>
                            </div>
                            <br>
                            <div class="panel panel-default">
                                <div class="panel-heading shadow-border-for-setting" role="tab" id="updatePwd"
                                     style="background-color: transparent;" data-toggle="collapse"
                                     data-parent="#accordion" href="#collapseTwo" aria-expanded="false"
                                     aria-controls="collapseTwo">
                                    <h4 class="panel-title">
                                        修改密码
                                    </h4>
                                </div>
                                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel"
                                     aria-labelledby="updatePwd">
                                    <div class="panel-body">
                                        <br>
                                        <div class="form-group">
                                            <label>新密码</label><br>
                                            <input type="password" class="form-input" id="newPwd"
                                                   placeholder="输入新密码">
                                        </div>
                                        <div class="form-group">
                                            <label>验证码</label><br>
                                            <div>
                                                <table>
                                                    <tr>
                                                        <td><input type="number" class="form-input"
                                                                   id="phoneCode"
                                                                   placeholder="验证码"></td>
                                                        <td>
                                                            &nbsp;&nbsp;<button class="button-info"
                                                                                onclick="sendPhoneCode()"
                                                                                id="sendPhoneCodeBtn">获取验证码
                                                        </button>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>

                                            <br>

                                            <button class="button-save" id="saveNewPwd" onclick="updatePwd()">修改
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <span class="error-msg" id="errorMsgOperAccount"></span>
                    </li>

                </ul>
            </div>

            <div style="display: none" id="divStatistic">
            </div>

            <br>
            <span class="error-msg" id="settingErrorMsg"></span>

        </div>
    </div>

</div>

<br>

<jsp:include page="/views/footer/footer.jsp"/>

<script type="application/javascript">
    var bloggerId = ${sessionScope["bloggerId"]};

    // baseSetting
    var bloggerName = '${sessionScope["bloggerName"]}';
    var email = '${profile.email}';
    var phone = '${profile.phone}';
    var mainNavPos = ${setting.mainPageNavPos};

    // Profile
    var intro = '${profile.intro}';
    var aboutMe = '${profile.aboutMe}';

</script>

<script type="application/javascript" src="/js/paging.js"></script>
<script type="application/javascript" src="/js/common.js"></script>
<script type="application/javascript" src="/js/blogger/setting.js"></script>

</body>
</html>
