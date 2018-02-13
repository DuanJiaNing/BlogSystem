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
    <link rel="stylesheet" href="css/paging.css">

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
        var pageOwnerBloggerId = ${pageOwnerBloggerId};
        var pageOwnerBloggerName = '${pageOwnerBloggerName}';
        var bloggerLoginSignal = ${not empty bloggerLoginSignal};
        var blogCount = ${statistics["blogCount"]};
        <c:if test="${not empty bloggerLoginSignal}">
        var loginBloggerId = ${bloggerId};
        </c:if>

    </script>

    <script type="application/javascript" src="js/paging.js"></script>
    <script type="application/javascript" src="js/common.js"></script>
    <script type="application/javascript" src="js/blogger/main.js"></script>

</head>
<body>

<button id="scroll-to-top" data-toggle="tooltip" data-placement="left" title="回到顶部">TOP</button>

<jsp:include page="../nav.jsp"/>

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
                &nbsp;&nbsp;&nbsp;&nbsp;<small>还没账号？</small>&nbsp;
                <a href="/register">注册</a>
                <p class="text-right"><a>忘记密码？</a></p>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<%--新建标签框--%>
<div class="modal fade bs-example-modal-sm dialog-middle" tabindex="-1" role="dialog" id="newLabelDialog"
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

<%--新建链接框--%>
<div class="modal fade" tabindex="-1" role="dialog" id="newLinkDialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content dialog-title-container">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title dialog-title">新建联系方式</h4>
            </div>
            <div class="modal-body dialog-body">
                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-8">
                        <form>
                            <div class="form-group">
                                <label>名称</label><br>
                                <input type="text" id="linkTitle" placeholder="名称" class="form-input">
                            </div>
                            <div class="form-group">
                                <label>URL</label><br>
                                <input type="text" id="linkUrl" placeholder="url" class="form-input">
                            </div>
                            <div class="form-group">
                                <label>说明</label><br>
                                <textarea class="default-textarea" id="linkBewrite" placeholder="说明"></textarea>
                            </div>
                        </form>
                        <span class="error-msg" id="linkErrorMsg"></span>

                    </div>
                    <div class="col-md-2"></div>

                </div>
            </div>
            <div class="modal-footer dialog-footer">
                <button type="submit" class="button-success" id="newLinkBtn" onclick="newLinkAndReload()">创建
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<%--高级检索--%>
<div class="modal fade" tabindex="-1" role="dialog" id="complexFilterDialog">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content dialog-title-container">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title dialog-title">高级检索</h4>
            </div>
            <div class="modal-body dialog-body">
                <form>
                    <div class="form-group">
                        <label>关键字</label><br>
                        <input type="text" id="keyWord" placeholder="关键字，匹配博文标题、摘要及内容" class="form-input"><br><br>
                    </div>

                    <div class="form-group">
                        <label>限定类别</label><br>
                        <p id="complexFilterCategory"></p><br>
                    </div>

                    <div class="form-group">
                        <label>限定标签</label><br>
                        <p id="complexFilterLabel"></p><br>
                    </div>

                    <div class="form-group">
                        <label>排序规则</label><br>
                        <p id="complexFilterSortRule_">
                        <table>
                            <tr>
                                <td>
                                    <div class="dropdown">
                                        按博文
                                        <a class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
                                           style="cursor: pointer;font-size: medium"
                                           aria-expanded="true" id="complexFilterSortRuleShow">
                                        </a>
                                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1"
                                            id="complexFilterSortRule">
                                        </ul>
                                    </div>
                                </td>
                                <td>
                                    <div class="dropdown">
                                        <a class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
                                           style="cursor: pointer;font-size: medium"
                                           aria-expanded="true" id="complexFilterSortOrderShow">
                                        </a>
                                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1"
                                            id="complexFilterSortOrder">
                                        </ul>
                                        排序
                                    </div>
                                </td>
                            </tr>
                        </table>
                        </p>
                    </div>

                </form>

                <span class="error-msg" id="complexFilterErrorMsg"></span>
            </div>
            <div class="modal-footer dialog-footer">
                <button class="button-success" id="complexFilterBtn" onclick="complexFilter()">检索
                </button>&nbsp;&nbsp;&nbsp;&nbsp;
                <a id="complexFilterBtnReset" onclick="resetComplexFilter()">重置
                </a>
            </div>
        </div><!-- /.modal-content -->
    </div>
</div>

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
                <small>${statistics["blogCount"]}篇博文&nbsp;&nbsp;|&nbsp;&nbsp;${statistics["wordCount"]}字&nbsp;&nbsp;|
                    &nbsp;&nbsp;收获${statistics["likeCount"]}个喜欢
                </small>
            </h4>
        </div>
    </div>

    <p class="text-left blog-filter">
        <span class="blog-filter-text" data-toggle="modal"
              data-target="#complexFilterDialog">高级检索</span>
    </p>

    <div class="row">
        <%--博文列表部分--%>
        <div class="col-md-9">
            <div id="blogList"></div>
            <div class="box" id="box"></div>
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
            <%--<p class="blogger-like-collect">他<a style="font-size: medium"--%>
            <%--onclick="filterBlogByLike(0,defaultBlogCount,true,true)">--%>
            <%--&nbsp;喜欢&nbsp;</a>/<a style="font-size: medium"--%>
            <%--onclick="filterBlogByCollect(0,defaultBlogCount,true,true)">&nbsp;收藏&nbsp;</a>的博文--%>
            <%--</p>--%>
            <br>
            <br>

            <%--标签--%>
            <div onmouseenter="if(isPageOwnerBloggerLogin())$('#labelNewBtn').show()"
                 onmouseleave="if(isPageOwnerBloggerLogin())$('#labelNewBtn').hide()">
                <div class="row">
                    <div class="col-md-8">
                        <h4 class="default-h4"><b>标签</b></h4>
                    </div>
                    <div class="col-md-4">
                        <br>
                        <a class="button-new" id="labelNewBtn" style="display: none"
                           data-target="#newLabelDialog" data-toggle="modal">新建</a>
                    </div>
                </div>
                <hr class="default-line">
                <p class="blogger-label" id="blogLabel">
                </p>
            </div>
            <br>

            <%--创建的类别--%>
            <div onmouseenter="if(isPageOwnerBloggerLogin())$('#categoryNewBtn').show()"
                 onmouseleave="if(isPageOwnerBloggerLogin())$('#categoryNewBtn').hide()">
                <div class="row">
                    <div class="col-md-8">
                        <h4 class="default-h4"><b>类别</b></h4>
                    </div>
                    <div class="col-md-4">
                        <br>
                        <a class="button-new" id="categoryNewBtn" style="display: none" data-target="#newCategoryDialog"
                           data-toggle="modal">新建</a>
                    </div>
                </div>
                <hr class="default-line">
                <div class="list-group" id="blogCategory">
                </div>
            </div>
            <br>

            <%--联系我--%>
            <div onmouseenter="if(isPageOwnerBloggerLogin())$('#linkNewBtn').show()"
                 onmouseleave="if(isPageOwnerBloggerLogin())$('#linkNewBtn').hide()">
                <div class="row">
                    <div class="col-md-8">
                        <h4 class="default-h4"><b>联系我</b></h4></div>
                    <div class="col-md-4">
                        <br>
                        <a class="button-new" id="linkNewBtn" style="display: none"
                           data-target="#newLinkDialog" data-toggle="modal">新建</a>
                    </div>
                </div>
                <hr class="default-line">
                <p class="blogger-link" id="bloggerLink">
                </p>
            </div>
        </div>

    </div>
</div>

<br>
<br>
<br>
<jsp:include page="../footer.jsp"/>
</body>
</html>
