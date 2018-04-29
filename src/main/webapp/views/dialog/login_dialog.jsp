<%--
  Created by IntelliJ IDEA.
  User: DuanJiaNing
  Date: 2018/2/16
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/css/dialog/login_dialog.css">

</head>
<body>

<%--登录框--%>
<div class="modal fade" tabindex="-1" role="dialog" id="loginDialog">
    <div class="modal-dialog" role="document">
        <%--需要/css/common.css样式文件--%>
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
                            <small class="indicator" style="font-weight: bold" id="loginByName"
                                   onclick="toggleDivState('useUserName','useUserPhone','loginByName','loginByPhone','loginErrorMsg','')"
                            >用户名登录
                            </small>&nbsp;&nbsp;<span class="vertical-line">|</span>&nbsp;&nbsp;<small
                                class="indicator" id="loginByPhone"
                                onclick="toggleDivState('useUserPhone','useUserName','loginByPhone','loginByName','loginErrorMsg','')">
                            手机验证码登录
                        </small>
                        </p>

                        <div id="useUserName">
                            <div class="form-group">
                                <label>用户名</label><br>
                                <input type="text" id="loginUserName" placeholder="用户名" class="form-input">
                            </div>
                            <div class="form-group">
                                <label>密码</label><br>
                                <input type="password" class="form-input" id="loginPassword" placeholder="密码">
                            </div>
                        </div>

                        <div id="useUserPhone" style="display: none;">
                            <div class="form-group">
                                <label>电话</label><br>
                                <input type="text" class="form-input" id="loginPhone" placeholder="电话号码">
                            </div>
                            <div class="form-group">
                                <label>验证码</label><br>
                                <div>
                                    <table>
                                        <tr>
                                            <td><input type="number" class="form-input" id="loginPhoneCode"
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
                            </div>
                        </div>
                        <span class="error-msg" id="loginErrorMsg"></span>

                    </div>
                    <div class="col-md-2"></div>

                </div>
            </div>
            <div class="modal-footer dialog-footer">
                <p class="text-right">
                    <%--<a>忘记密码？</a>&nbsp;&nbsp;&nbsp;&nbsp;--%>
                    <button class="button-success" id="loginBtn"
                            onclick="login(funAfterLoginSuccess,funAfterLoginFail)">
                        登入
                    </button>&nbsp;&nbsp;&nbsp;&nbsp;
                </p>

            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script type="application/javascript" src="/js/dialog/login_dialog.js"></script>

</body>
</html>
