<%--
  Created by IntelliJ IDEA.
  User: DuanJiaNing
  Date: 2017/12/11
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<!doctype html>
<html lang="en">
<head>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <title>博主首页</title>

    <style>
        body {
            padding-top: 70px;
        }
    </style>
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
            <b><a class="navbar-brand" style="color: orangered;">BLOG</a></b>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <ul class="nav navbar-nav navbar-left">
                <li><a></a></li>
                <li><a></a></li>
                <li><a></a></li>
                <li><a></a></li>
                <li><a style="font-size: 1.3em;color: #888888"><span
                        class="glyphicon glyphicon-lamp glyphicon-align-left"></span>&nbsp;&nbsp;博文</a></li>
                <li><a style="font-size: 1.3em;color: #888888"><span
                        class="glyphicon glyphicon-piggy-bank glyphicon-align-left"></span>&nbsp;&nbsp;收藏</a></li>
                <li><a style="font-size: 1.3em;color: #888888"><span
                        class="glyphicon glyphicon-scale glyphicon-align-left"></span>&nbsp;&nbsp;喜欢</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li>
                    <form class="navbar-form ">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="搜索博文">
                        </div>
                        <button type="submit" class="btn btn-default">搜索</button>
                    </form>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">DuanJiaNing <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                    </ul>
                </li>

                <li>
                    <button type="button" class="btn btn-default navbar-btn">写博文</button>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="container">
    <!-- Content here -->
    <div class="row">
        <div class="col-md-9">
            <p>
            <h3>&nbsp;&nbsp;&nbsp;&nbsp;一句话简介在此</h3>
            </p>
        </div>
        <div class="col-md-3">
            <br>
            <h4>
                <small>54篇博文&nbsp;&nbsp;|&nbsp;&nbsp;9834字&nbsp;&nbsp;|&nbsp;&nbsp;收获32个喜欢</small>
            </h4>
        </div>
    </div>
    <hr>

    <div class="col-md-9">
        <ul class="list-group">
            <li class="list-group-item" style="border: 0px;">
                <p>
                <h3 class="list-group-item-heading" style="color: #3478B5;">javaEE - 同源策略 Jsonp & CORS</h3></p>
                <h4>
                    <small class="list-group-item-text"><b>Jan 23,2017</b>&nbsp;&nbsp;23收藏&nbsp;&nbsp;344浏览&nbsp;&nbsp;3喜欢
                    </small>
                </h4>
                <p class="list-group-item-text" style="font-size: 1.1em;padding-top: 10px;line-height: 23px">使用同源策略的原因
                    Cookie失窃，非法网页通过javascript访问其他应用的服务器，获取到Cookie后伪装成用户进行非法操作。
                    通过iframe伪装form，将信息提交到恶意action，隐私数据提交到了不法网站，信息被盗取。</p>
                <h4>
                    <small class="list-group-item-text" style="color: #3478B5;"><u><i>阿萨德</i></u>&nbsp;&nbsp;23评论
                    </small>
                </h4>
                <hr>
            </li>
            <li class="list-group-item" style="border: 0px;">
                <p>
                <h3 class="list-group-item-heading" style="color: #3478B5;">javaEE - 同源策略 Jsonp & CORS</h3></p>
                <h4>
                    <small class="list-group-item-text"><b>Jan 23,2017</b>&nbsp;&nbsp;23收藏&nbsp;&nbsp;344浏览&nbsp;&nbsp;3喜欢
                    </small>
                </h4>
                <p class="list-group-item-text" style="font-size: 1.1em;padding-top: 10px;line-height: 23px">使用同源策略的原因
                    Cookie失窃，非法网页通过javascript访问其他应用的服务器，获取到Cookie后伪装成用户进行非法操作。
                    通过iframe伪装form，将信息提交到恶意action，隐私数据提交到了不法网站，信息被盗取。</p>
                <h4>
                    <small class="list-group-item-text" style="color: #3478B5;"><u><i>阿萨德</i></u>&nbsp;&nbsp;23评论
                    </small>
                </h4>
                <hr>
            </li>
            <li class="list-group-item" style="border: 0px;">
                <p>
                <h3 class="list-group-item-heading" style="color: #3478B5;">javaEE - 同源策略 Jsonp & CORS</h3></p>
                <h4>
                    <small class="list-group-item-text"><b>Jan 23,2017</b>&nbsp;&nbsp;23收藏&nbsp;&nbsp;344浏览&nbsp;&nbsp;3喜欢
                    </small>
                </h4>
                <p class="list-group-item-text" style="font-size: 1.1em;padding-top: 10px;line-height: 23px">使用同源策略的原因
                    Cookie失窃，非法网页通过javascript访问其他应用的服务器，获取到Cookie后伪装成用户进行非法操作。
                    通过iframe伪装form，将信息提交到恶意action，隐私数据提交到了不法网站，信息被盗取。</p>
                <h4>
                    <small class="list-group-item-text" style="color: #3478B5;"><u><i>阿萨德</i></u>&nbsp;&nbsp;23评论
                    </small>
                </h4>
                <hr>
            </li>

        </ul>
        <div class="row">

            <div class="col-md-3"></div>
            <div class="col-md-6">
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
            <div class="col-md-3"></div>
        </div>
    </div>

    <div class="col-md-3">

        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">收藏/喜欢的博文</h3>
            </div>
            <div class="panel-body">
                Panel content
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">类别</h3>
            </div>
            <div class="panel-body">
                Panel content
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">与我联系</h3>
            </div>
            <div class="panel-body">
                Panel content
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-body">
                标签在此
            </div>
        </div>
    </div>

</div>
<br>
<br>
<br>
<div class="container" style="height: 100px;border-top: solid 1px #3478B5">
    <div class="row">
        <div class="col-md-5"></div>
        <div class="col-md-2" style="background-color:darkseagreen;height: 130px"></div>
        <div class="col-md-5"></div>

    </div>
</div>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>
