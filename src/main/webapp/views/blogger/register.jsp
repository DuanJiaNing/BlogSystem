<%--
  Created by IntelliJ IDEA.
  User: DuanJiaNing
  Date: 2018/2/8
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <link rel="stylesheet" href="/css/blogger/register.css">
    <link rel="stylesheet" href="/css/common.css">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/jquery/3.3.1/core.js"></script>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <script type="application/javascript" src="/js/blogger/register.js"></script>

    <title>注册</title>
</head>
<body>

<%--登录框--%>
<div class="modal fade" tabindex="-1" role="dialog" id="signInDialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content dialog-title-container">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title dialog-title">登录</h4>
            </div>
            <div class="modal-body dialog-body">
                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-8">
                        <p class="text-center lead">
                            <small class="dialog-sign-in-indicator" id="siginName"
                                   onclick="showNameDiv()"
                            >用户名登录
                            </small>&nbsp;&nbsp;|&nbsp;&nbsp;<small
                                class="dialog-sign-in-indicator" style="font-weight: bold" id="siginPhone"
                                onclick="showPhoneDiv()"
                        >
                            手机验证码登录
                        </small>
                        </p>

                        <div id="useUserName" style="display: none;">
                            <form>
                                <div class="form-group">
                                    <label>用户名</label><br>
                                    <input type="text" id="userName" placeholder="用户名" class="form-input">
                                </div>
                                <div class="form-group">
                                    <label>密码</label><br>
                                    <input type="password" class="form-input" id="password" placeholder="密码">
                                </div>
                            </form>
                        </div>

                        <div id="useUserPhone">
                            <form>
                                <div class="form-group">
                                    <label>电话</label><br>
                                    <input type="number" class="form-input" id="phone" placeholder="电话号码">
                                </div>
                                <div class="form-group">
                                    <label>验证码</label><br>
                                    <div>
                                        <table>
                                            <tr>
                                                <td><input type="password" class="form-input" id="code"
                                                           placeholder="验证码"></td>
                                                <td>
                                                    &nbsp;&nbsp;<button class="default-button-info">获取验证码</button>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <span class="error-msg" id="loginErrorMsg"></span>

                    </div>
                    <div class="col-md-2"></div>

                </div>
            </div>
            <div class="modal-footer dialog-footer">
                <button type="submit" class="button-success" id="signInBtn" onclick="signIn()">登入</button>
                <p class="text-right"><a>忘记密码？</a></p>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="container-">
    <div class="row">
        <div class="col-md-5 register-left con">
            <p class="BLOG-title">
                BLOG
            </p>
        </div>
        <div class="col-md-1 con"></div>
        <div class="col-md-4 con">

            <div style="height: 20%">
                <br>
                <br>
                <p>
                <h1>欢迎注册</h1>
                </p>
                <br>

                <table>
                    <tr>
                        <td class="step-title-choose"><span class="step-choose">1</span>&nbsp;注册账户&nbsp;<span>————&nbsp;&nbsp;</span>
                        </td>
                        <td class="step-title" id="stepTitle2"><span id="stepTitle2_" class="step">2</span>&nbsp;补全信息&nbsp;<span>————&nbsp;&nbsp;</span>
                        </td>
                        <td class="step-title" id="stepTitle3"><span id="stepTitle3_" class="step">3</span>&nbsp;完成
                        </td>
                    </tr>
                </table>
            </div>

            <div style="height: 50%">

                <br>
                <br>
                <br>

                <div id="inputAccount">

                    <form>
                        <input type="text" id="registerUserName" placeholder="用户名" class="form-input"
                               onblur="checkInputEmpty('registerUserName')"><br><br><br>
                        <input type="password" class="form-input" id="registerPassword" placeholder="密码"
                               onblur="checkInputEmpty('registerPassword')"><br><br><br>
                        <input type="password" class="form-input" id="conformPassword" placeholder="确认密码"
                               onblur="checkInputEmpty('conformPassword')">
                    </form>

                </div>

                <div id="inputProfile" style="display: none;">

                    <form>
                        <input type="text" id="registerPhone" placeholder="电话" class="form-input"
                               onblur="checkInputEmpty('registerPhone')"><br><br><br>
                        <input type="email" class="form-input" id="registerEmail" placeholder="email"
                               onblur="checkInputEmpty('registerEmail')"><br><br><br>
                        <input type="text" class="form-input" id="registerIntro" placeholder="博客标题"
                               onblur="checkInputEmpty('registerIntro')"><br><br><br>
                        <textarea class="default-textarea" id="registerAboutMe" placeholder="博主自述"
                                  onblur="checkInputEmpty('registerAboutMe')"></textarea>
                    </form>

                </div>

                <div id="inputFinish" style="display: none;">
                    <br>
                    <h3 id="finalInfo">
                    </h3>

                </div>
            </div>

            <div style="height: 20%">
                <span class="error-msg" id="registerErrorMsg"></span>
                <br>
                <br>
                <button class="button-success" style="padding: 8px 64px;" onclick="nextStep()" id="nextStep">下一步
                </button>
            </div>
            <div style="height: 10%">
                <span class="powered-by">Powered by DuanJiaNing</span>
            </div>

        </div>
        <div class="col-md-1 con"></div>
        <div class="col-md-1 con">
            <p class="text-right">
                <br>
                <br>
                <a data-toggle="modal"
                   data-target="#signInDialog">登录</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </p>
        </div>
    </div>
</div>

</body>
</html>
