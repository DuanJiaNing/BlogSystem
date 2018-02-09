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

<!doctype html>
<html lang="en">
<head>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="css/blogger/main.css">
    <link rel="stylesheet" href="css/common.css">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/jquery/3.3.1/core.js"></script>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <title>博主首页</title>

    <script type="application/javascript">
        var bloggerId = ${bloggerId};
        var bloggerName = '${bloggerName}';
        var bloggerLoginSignal = ${not empty bloggerLoginSignal};
    </script>

    <script type="application/javascript" src="js/common.js"></script>
    <script type="application/javascript" src="js/blogger/main.js"></script>

</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <b><a class="navbar-brand os-name BLOG">BLOG</a></b>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <ul class="nav navbar-nav navbar-right">
                <li>
                    <form class="navbar-form ">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="搜索博文">
                        </div>
                        <button type="submit" class="btn btn-default">搜索</button>
                    </form>
                </li>

                <c:choose>
                    <c:when test="${empty bloggerLoginSignal}">
                        <li><a data-toggle="modal"
                               data-target="#signInDialog">登录</a></li>
                        <li><a href="/register">注册</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-haspopup="true"
                               aria-expanded="false">${bloggerName} <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li class="blogger-option"><a href="#">主页</a></li>
                                <li class="blogger-option"><a href="#">收藏的博文&nbsp;<span class="count">(12)</span></a>
                                </li>
                                <li class="blogger-option"><a href="#">喜欢的博文&nbsp;<span class="count">(0)</span></a>
                                </li>
                                <li class="blogger-option"><a href="#">管理</a></li>
                                <li class="blogger-option"><a href="#">反馈</a></li>
                                <li role="separator" class="divider"></li>
                                <li class="blogger-option"><a href="#"><span class="quit">退出</span></a></li>
                            </ul>
                        </li>

                        <li>
                            <button type="button" class="btn btn-default navbar-btn">写博文</button>
                        </li>
                    </c:otherwise>
                </c:choose>

            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

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
                                   onclick="showNameDiv()">用户名登录
                            </small>&nbsp;&nbsp;|&nbsp;&nbsp;<small
                                class="dialog-sign-in-indicator" style="font-weight: bold" id="siginPhone"
                                onclick="showPhoneDiv()">
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
                &nbsp;&nbsp;&nbsp;&nbsp;<small>还没账号？</small>&nbsp;
                <a href="/register">注册</a>
                <p class="text-right"><a>忘记密码？</a></p>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<%--新建标签框--%>
<div class="modal fade bs-example-modal-sm dialog-middle" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content dialog-title-container-middle">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title dialog-title">新建标签</h4>
            </div>
            <div class="modal-body dialog-body">
                <form>
                    <div class="form-group">
                        <label>标签名</label><br>
                        <input type="text" id="labelName" placeholder="标签名" class="form-input">
                    </div>
                </form>
                <span class="error-msg" id="labelErrorMsg"></span>
            </div>
            <div class="modal-footer dialog-footer">
                <button class="button-success" onclick="newLabelAndReload()">创建</button>
            </div>

        </div>
    </div>
</div>

<%--新建类别框--%>
<div class="modal fade" tabindex="-1" role="dialog" id="newCategoryDialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content dialog-title-container">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title dialog-title">新建类别</h4>
            </div>
            <div class="modal-body dialog-body">
                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-8">
                        <form>
                            <div class="form-group">
                                <label>名称</label><br>
                                <input type="text" id="categoryTitle" placeholder="类别名" class="form-input">
                            </div>
                            <div class="form-group">
                                <label>说明</label><br>
                                <textarea class="default-textarea" id="categoryBewrite" placeholder="类别说明"></textarea>
                            </div>
                        </form>
                        <span class="error-msg" id="categoryErrorMsg"></span>

                    </div>
                    <div class="col-md-2"></div>

                </div>
            </div>
            <div class="modal-footer dialog-footer">
                <button type="submit" class="button-success" id="newCategoryBtn" onclick="newCategoryAndReload()">创建
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="container">
    <!-- Content here -->
    <div class="row">
        <div class="col-md-9">
            <p>
            <h3>&nbsp;&nbsp;${blogName}</h3>
            </p>
        </div>
        <div class="col-md-3">
            <br>
            <h4>
                <small>54篇博文&nbsp;&nbsp;|&nbsp;&nbsp;9834字&nbsp;&nbsp;|&nbsp;&nbsp;收获32个喜欢</small>
            </h4>
        </div>
    </div>

    <p class="text-left blog-filter">
        <span class="blog-filter-text">高级检索</span>
    </p>

    <div class="row">
        <%--博文列表部分--%>
        <div class="col-md-9">

            <%--博文列表--%>
            <ul class="list-group">
                <li class="list-group-item blog-list-item">
                    <p>
                    <h3 class="list-group-item-heading blog-list-item-title"><a>javaEE - 同源策略 Jsonp & CORS</a>
                    </h3></p>
                    <h4>
                        <small class="list-group-item-text"><b>Jan 23,2017</b>&nbsp;&nbsp;23收藏&nbsp;&nbsp;344浏览&nbsp;&nbsp;3喜欢
                        </small>
                    </h4>
                    <p class="list-group-item-text blog-list-item-summary">
                        使用同源策略的原因
                        Cookie失窃，非法网页通过javascript访问其他应用的服务器，获取到Cookie后伪装成用户进行非法操作。
                        通过iframe伪装form，将信息提交到恶意action，隐私数据提交到了不法网站，信息被盗取。</p>
                    <h4>
                        <small class="list-group-item-text blog-list-item-title"><u><i>阿萨德</i></u>&nbsp;&nbsp;23评论
                        </small>
                    </h4>
                    <hr>
                </li>
                <li class="list-group-item blog-list-item">
                    <p>
                    <h3 class="list-group-item-heading blog-list-item-title"><a>javaEE - 同源策略 Jsonp & CORS</a>
                    </h3></p>
                    <h4>
                        <small class="list-group-item-text"><b>Jan 23,2017</b>&nbsp;&nbsp;23收藏&nbsp;&nbsp;344浏览&nbsp;&nbsp;3喜欢
                        </small>
                    </h4>
                    <p class="list-group-item-text blog-list-item-summary">
                        使用同源策略的原因
                        Cookie失窃，非法网页通过javascript访问其他应用的服务器，获取到Cookie后伪装成用户进行非法操作。
                        通过iframe伪装form，将信息提交到恶意action，隐私数据提交到了不法网站，信息被盗取。</p>
                    <h4>
                        <small class="list-group-item-text blog-list-item-title"><u><i>阿萨德</i></u>&nbsp;&nbsp;23评论
                        </small>
                    </h4>
                    <hr>
                </li>
                <li class="list-group-item blog-list-item">
                    <p>
                    <h3 class="list-group-item-heading blog-list-item-title"><a>javaEE - 同源策略 Jsonp & CORS</a>
                    </h3></p>
                    <h4>
                        <small class="list-group-item-text"><b>Jan 23,2017</b>&nbsp;&nbsp;23收藏&nbsp;&nbsp;344浏览&nbsp;&nbsp;3喜欢
                        </small>
                    </h4>
                    <p class="list-group-item-text blog-list-item-summary">
                        使用同源策略的原因
                        Cookie失窃，非法网页通过javascript访问其他应用的服务器，获取到Cookie后伪装成用户进行非法操作。
                        通过iframe伪装form，将信息提交到恶意action，隐私数据提交到了不法网站，信息被盗取。</p>
                    <h4>
                        <small class="list-group-item-text blog-list-item-title"><u><i>阿萨德</i></u>&nbsp;&nbsp;23评论
                        </small>
                    </h4>
                    <hr>
                </li>
                <li class="list-group-item blog-list-item">
                    <p>
                    <h3 class="list-group-item-heading blog-list-item-title"><a>javaEE - 同源策略 Jsonp & CORS</a>
                    </h3></p>
                    <h4>
                        <small class="list-group-item-text"><b>Jan 23,2017</b>&nbsp;&nbsp;23收藏&nbsp;&nbsp;344浏览&nbsp;&nbsp;3喜欢
                        </small>
                    </h4>
                    <p class="list-group-item-text blog-list-item-summary">
                        使用同源策略的原因
                        Cookie失窃，非法网页通过javascript访问其他应用的服务器，获取到Cookie后伪装成用户进行非法操作。
                        通过iframe伪装form，将信息提交到恶意action，隐私数据提交到了不法网站，信息被盗取。</p>
                    <h4>
                        <small class="list-group-item-text blog-list-item-title"><u><i>阿萨德</i></u>&nbsp;&nbsp;23评论
                        </small>
                    </h4>
                    <hr>
                </li>
                <li class="list-group-item blog-list-item">
                    <p>
                    <h3 class="list-group-item-heading blog-list-item-title"><a>javaEE - 同源策略 Jsonp & CORS</a>
                    </h3></p>
                    <h4>
                        <small class="list-group-item-text"><b>Jan 23,2017</b>&nbsp;&nbsp;23收藏&nbsp;&nbsp;344浏览&nbsp;&nbsp;3喜欢
                        </small>
                    </h4>
                    <p class="list-group-item-text blog-list-item-summary">
                        使用同源策略的原因
                        Cookie失窃，非法网页通过javascript访问其他应用的服务器，获取到Cookie后伪装成用户进行非法操作。
                        通过iframe伪装form，将信息提交到恶意action，隐私数据提交到了不法网站，信息被盗取。</p>
                    <h4>
                        <small class="list-group-item-text blog-list-item-title"><u><i>阿萨德</i></u>&nbsp;&nbsp;23评论
                        </small>
                    </h4>
                    <hr>
                </li>
                <li class="list-group-item blog-list-item">
                    <p>
                    <h3 class="list-group-item-heading blog-list-item-title"><a>javaEE - 同源策略 Jsonp & CORS</a>
                    </h3></p>
                    <h4>
                        <small class="list-group-item-text"><b>Jan 23,2017</b>&nbsp;&nbsp;23收藏&nbsp;&nbsp;344浏览&nbsp;&nbsp;3喜欢
                        </small>
                    </h4>
                    <p class="list-group-item-text blog-list-item-summary">
                        使用同源策略的原因
                        Cookie失窃，非法网页通过javascript访问其他应用的服务器，获取到Cookie后伪装成用户进行非法操作。
                        通过iframe伪装form，将信息提交到恶意action，隐私数据提交到了不法网站，信息被盗取。</p>
                    <h4>
                        <small class="list-group-item-text blog-list-item-title"><u><i>阿萨德</i></u>&nbsp;&nbsp;23评论
                        </small>
                    </h4>
                    <hr>
                </li>
                <li class="list-group-item blog-list-item">
                    <p>
                    <h3 class="list-group-item-heading blog-list-item-title"><a>javaEE - 同源策略 Jsonp & CORS</a>
                    </h3></p>
                    <h4>
                        <small class="list-group-item-text"><b>Jan 23,2017</b>&nbsp;&nbsp;23收藏&nbsp;&nbsp;344浏览&nbsp;&nbsp;3喜欢
                        </small>
                    </h4>
                    <p class="list-group-item-text blog-list-item-summary">
                        使用同源策略的原因
                        Cookie失窃，非法网页通过javascript访问其他应用的服务器，获取到Cookie后伪装成用户进行非法操作。
                        通过iframe伪装form，将信息提交到恶意action，隐私数据提交到了不法网站，信息被盗取。</p>
                    <h4>
                        <small class="list-group-item-text blog-list-item-title"><u><i>阿萨德</i></u>&nbsp;&nbsp;23评论
                        </small>
                    </h4>
                    <hr>
                </li>
                <li class="list-group-item blog-list-item">
                    <p>
                    <h3 class="list-group-item-heading blog-list-item-title"><a>javaEE - 同源策略 Jsonp & CORS</a>
                    </h3></p>
                    <h4>
                        <small class="list-group-item-text"><b>Jan 23,2017</b>&nbsp;&nbsp;23收藏&nbsp;&nbsp;344浏览&nbsp;&nbsp;3喜欢
                        </small>
                    </h4>
                    <p class="list-group-item-text blog-list-item-summary">
                        使用同源策略的原因
                        Cookie失窃，非法网页通过javascript访问其他应用的服务器，获取到Cookie后伪装成用户进行非法操作。
                        通过iframe伪装form，将信息提交到恶意action，隐私数据提交到了不法网站，信息被盗取。</p>
                    <h4>
                        <small class="list-group-item-text blog-list-item-title"><u><i>阿萨德</i></u>&nbsp;&nbsp;23评论
                        </small>
                    </h4>
                    <hr>
                </li>
                <li class="list-group-item blog-list-item">
                    <p>
                    <h3 class="list-group-item-heading blog-list-item-title"><a>javaEE - 同源策略 Jsonp & CORS</a>
                    </h3></p>
                    <h4>
                        <small class="list-group-item-text"><b>Jan 23,2017</b>&nbsp;&nbsp;23收藏&nbsp;&nbsp;344浏览&nbsp;&nbsp;3喜欢
                        </small>
                    </h4>
                    <p class="list-group-item-text blog-list-item-summary">
                        使用同源策略的原因
                        Cookie失窃，非法网页通过javascript访问其他应用的服务器，获取到Cookie后伪装成用户进行非法操作。
                        通过iframe伪装form，将信息提交到恶意action，隐私数据提交到了不法网站，信息被盗取。</p>
                    <h4>
                        <small class="list-group-item-text blog-list-item-title"><u><i>阿萨德</i></u>&nbsp;&nbsp;23评论
                        </small>
                    </h4>
                    <hr>
                </li>
                <li class="list-group-item blog-list-item">
                    <p>
                    <h3 class="list-group-item-heading blog-list-item-title"><a>javaEE - 同源策略 Jsonp & CORS</a>
                    </h3></p>
                    <h4>
                        <small class="list-group-item-text"><b>Jan 23,2017</b>&nbsp;&nbsp;23收藏&nbsp;&nbsp;344浏览&nbsp;&nbsp;3喜欢
                        </small>
                    </h4>
                    <p class="list-group-item-text blog-list-item-summary">
                        使用同源策略的原因
                        Cookie失窃，非法网页通过javascript访问其他应用的服务器，获取到Cookie后伪装成用户进行非法操作。
                        通过iframe伪装form，将信息提交到恶意action，隐私数据提交到了不法网站，信息被盗取。</p>
                    <h4>
                        <small class="list-group-item-text blog-list-item-title"><u><i>阿萨德</i></u>&nbsp;&nbsp;23评论
                        </small>
                    </h4>
                    <hr>
                </li>
            </ul>

            <%--分页控件--%>
            <div style="text-align: center;">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <li>
                            <a href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li>
                            <a href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>

        <%--右侧--%>
        <div class="col-md-3">

            <%--头像--%>
            <div>
                <%--头像--%>
                <br>
                <div class="avatar">
                    <img src="images/Blue eyed_&_2e216493-5d50-4e39-8b9d-db2c2874c003.jpg"
                         class="img-rounded avatar-img">
                </div>
                <%--用户名--%>
                <p class="text-center blogger-name">${bloggerName}</p>
            </div>
            <hr>
            <p class="lead blogger-aboutme">${aboutMe}</p>
            <p class="blogger-like">我喜欢的博文</p>
            <br>
            <br>

            <%--标签--%>
            <div class="row" onmouseenter="$('#labelNewBtn').show()"
                 onmouseleave="$('#labelNewBtn').hide()">
                <div class="col-md-9">
                    <h4 class="default-h4"><b>标签</b></h4>
                </div>
                <div class="col-md-3">
                    <br>
                    <span class="button-new" id="labelNewBtn" style="display: none">+</span>
                </div>
            </div>
            <hr class="default-line">
            <p class="blogger-label">
            </p>
            <br>

            <%--创建的类别--%>
            <div class="row" onmouseenter="$('#categoryNewBtn').show()"
                 onmouseleave="$('#categoryNewBtn').hide()">
                <div class="col-md-9">
                    <h4 class="default-h4"><b>类别</b></h4>
                </div>
                <div class="col-md-3">
                    <br>
                    <span class="button-new" id="categoryNewBtn" style="display: none">+</span>
                </div>
            </div>
            <hr class="default-line">
            <div class="list-group" id="blogCategory">
            </div>
            <br>

            <%--联系我--%>
            <div class="row" onmouseenter="$('#linkNewBtn').show()" onmouseleave="$('#linkNewBtn').hide()">
                <div class="col-md-9">
                    <h4 class="default-h4"><b>联系我</b></h4></div>
                <div class="col-md-3">
                    <br>
                    <span class="button-new" id="linkNewBtn" style="display: none">+</span>
                </div>
            </div>
            <hr class="default-line">
            <p class="blogger-link">
                <a class="blogger-link-item">GitHub</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a class="blogger-link-item">CSDN</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a class="blogger-link-item">Gmail</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a class="blogger-link-item">微信</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a class="blogger-link-item">微博</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </p>
        </div>

    </div>
</div>

<br>
<br>
<br>
<jsp:include page="../footer.jsp"/>
</body>
</html>
