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

<%@ include file="/views/nav/nav.jsp" %>
<%@ include file="/views/dialog/new_label_dialog.jsp" %>
<%@ include file="/views/dialog/new_category_dialog.jsp" %>
<%@ include file="/views/dialog/new_link_dialog.jsp" %>
<%@ include file="/views/dialog/modify_label_dialog.jsp" %>
<%@ include file="/views/dialog/modify_category_dialog.jsp" %>
<%@ include file="/views/dialog/modify_link_dialog.jsp" %>
<%@ include file="/views/dialog/confirm_dialog.jsp" %>

<html>
<head>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">

    <link rel="stylesheet" href="/css/blogger/main.css">
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

    <title>${pageOwnerBloggerName}-主页</title>

</head>
<body>

<%--只能动态导入，否则出错--%>
<jsp:include page="/views/dialog/upload_avatar_dialog.jsp"/>

<div class="operation-container border clickable-gray" onclick="scrollToTop();" style="padding: 11px">
    <%--<button id="scroll-to-top" title="回到顶部">TOP</button>--%>
    <img class="img24px" src="/images/icon/icons8-collapse-arrow-64.png" title="回到顶部">
</div>

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
                        <p id="complexFilterCategory" style="line-height: 28px"></p><br>
                    </div>

                    <div class="form-group">
                        <label>限定标签</label><br>
                        <p id="complexFilterLabel" style="line-height: 28px"></p><br>
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
                <p class="text-right">
                    <a id="complexFilterBtnReset" onclick="resetComplexFilter()">重置
                    </a>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="button-success" id="complexFilterBtn" onclick="complexFilter()">检索
                    </button>&nbsp;&nbsp;&nbsp;&nbsp;
                </p>
            </div>
        </div><!-- /.modal-content -->
    </div>
</div>

<div class="sec-nav">
    <div class="container" style=";width: 82%">
        <table style="height: 100%;width: 100%">
            <tr style="height: 100%">
                <td valign="middle">
                    <h4>${blogName}
                        <small>
                            &nbsp;&nbsp;
                            <span id="blogCount">${ownerBgStat["blogCount"]}&nbsp;篇博文</span>
                            <span class="vertical-line">&nbsp;|&nbsp;</span>
                            ${ownerBgStat["wordCount"]}&nbsp;字<span
                                class="vertical-line">&nbsp;|&nbsp;</span>
                            收获&nbsp;${ownerBgStat["likeCount"]}&nbsp;个喜欢
                        </small>
                    </h4>
                </td>
                <td align="right">
                    <div class="input-group search navbar-btn text-right search-container">
                        <input type="text" class="form-control input-search" placeholder="搜索博文" id="searchBlog"
                               aria-describedby="basic-addon2">
                        <span class="input-group-addon input-search-btn" id="basic-addon2"
                              onclick="filterBlogByKeyWord()">
                            <img class="img24px" src="/images/icon/icons8-search-50.png">&nbsp;

                        </span>

                    </div>
                </td>
                <td>
                    &nbsp;
                    <button data-toggle="modal" class="complex-filter-btn"
                            data-target="#complexFilterDialog">高级
                    </button>
                </td>

            </tr>
        </table>
    </div>
</div>

<div class="container" style="width: 82%">

    <br>

    <div class="row">

        <c:choose>
            <c:when test="${setting.mainPageNavPos eq 0}">

                <%--左侧--%>
                <div class="col-md-3">

                        <%--头像--%>
                    <div class="blogger-profile border">
                        <c:choose>
                            <c:when test="${sessionScope['bloggerId'] == pageOwnerBloggerId}">
                                <%--头像--%>
                                <div class="avatar">
                                    <a class="avatar-edit" id="editAvatar" style="display: none">点击更换头像</a>

                                    <img src="/image/${pageOwnerBloggerId}/type=public/${avatarId}?default=13"
                                         class="avatar-img avatar-img-editable"
                                         id="bloggerAvatar"
                                         onmouseenter="if(isPageOwnerBloggerLogin())$('#editAvatar').show()"
                                         onmouseleave="if(isPageOwnerBloggerLogin())$('#editAvatar').hide()"
                                         onclick="editAvatar()">
                                </div>
                                <%--用户名--%>
                                <p class="text-center blogger-name">
                                        ${pageOwnerBloggerName}
                                </p>
                            </c:when>
                            <c:otherwise>
                                <%--头像--%>
                                <div class="avatar">
                                    <img src="/image/${pageOwnerBloggerId}/type=public/${avatarId}?default=13"
                                         class="avatar-img">
                                </div>
                                <%--用户名--%>
                                <p class="text-center blogger-name">${pageOwnerBloggerName}</p>
                            </c:otherwise>
                        </c:choose>
                        <hr>
                        <p class="text-center blogger-aboutme">${aboutMe}</p>
                    </div>
                    <br>

                        <%--他的喜欢，收藏--%>
                    <div class="border" style="background-color: white">
                        <br>
                        <div class="list-group">
                            <a class="list-group-item vertical-center blogger-favourite"
                               onclick="initBlog()">
                                <img class="img24px" src="/images/icon/icons8-news-80.png">&nbsp;
                                所有文章
                                &nbsp;<span class="count" id="subBlogCount">(${ownerBgStat["blogCount"]})</span>
                            </a>
                            <a class="list-group-item vertical-center blogger-favourite"
                               href="/${pageOwnerBloggerName}/blog/favourite/like">
                                    <%--<i class="material-icons icons">favorite_border</i>&nbsp;--%>
                                <img class="img24px" src="/images/icon/icons8-heart-outline-80.png">&nbsp;
                                <c:choose>
                                    <c:when test="${sessionScope['bloggerId'] == pageOwnerBloggerId}">
                                        我喜欢的文章
                                    </c:when>
                                    <c:otherwise>
                                        他喜欢的文章
                                    </c:otherwise>

                                </c:choose>
                                &nbsp;<span class="count">(${ownerBgStat.likedCount})</span>
                            </a>
                            <a class="list-group-item vertical-center blogger-favourite"
                               href="/${pageOwnerBloggerName}/blog/favourite/collect">
                                <img class="img24px" src="/images/icon/icons8-collect-100.png">&nbsp;
                                <c:choose>
                                    <c:when test="${sessionScope['bloggerId'] == pageOwnerBloggerId}">
                                        我收藏的文章
                                    </c:when>
                                    <c:otherwise>
                                        他收藏的文章
                                    </c:otherwise>
                                </c:choose>

                                &nbsp;<span class="count">(${ownerBgStat.collectCount})</span>
                            </a>
                        </div>
                    </div>
                    <br>

                        <%--标签--%>
                    <div onmouseenter="if(isPageOwnerBloggerLogin())$('#bloggerLabelContainer').slideToggle()"
                         onmouseleave="if(isPageOwnerBloggerLogin())$('#bloggerLabelContainer').slideToggle()"
                         class="blogger-profile border">

                        <p class="text-center blogger-profile-title">
                            标签&nbsp;<small id="labelCount" style="color: darkgray">(${ownerBgStat.labelCount})</small>
                        </p>

                        <p class="text-center" style="display: none" id="bloggerLabelContainer">
                    <span class="button-edit" id="labelEditBtn"
                          data-target="#modifyLabelDialog" data-toggle="modal">编辑</span>
                            <span class="button-edit-new" id="labelNewBtn"
                                  data-target="#newLabelDialog" data-toggle="modal">新建</span>
                        </p>

                        <hr class="default-line">
                        <p class="blogger-label-content" id="blogLabel">
                        </p>

                    </div>
                    <br>

                        <%--创建的类别--%>
                    <div onmouseenter="if(isPageOwnerBloggerLogin())$('#bloggerCategoryContainer').slideToggle()"
                         onmouseleave="if(isPageOwnerBloggerLogin())$('#bloggerCategoryContainer').slideToggle()"
                         class="blogger-profile border">
                        <p class="text-center blogger-profile-title">
                            类别&nbsp;<small id="categoryCount" style="color: darkgray">(${ownerBgStat.categoryCount})
                        </small>
                        </p>

                        <p class="text-center" style="display: none" id="bloggerCategoryContainer">
                    <span class="button-edit" id="categoryEditBtn"
                          data-target="#modifyCategoryDialog" data-toggle="modal">编辑</span>
                            <span class="button-edit-new" id="categoryNewBtn"
                                  data-target="#newCategoryDialog"
                                  data-toggle="modal">新建</span>
                        </p>
                        <hr class="default-line">
                        <div class="list-group category-list-group" id="blogCategory"></div>
                    </div>
                    <br>

                        <%--联系我--%>
                    <div onmouseenter="if(isPageOwnerBloggerLogin())$('#bloggerLinkContainer').slideToggle()"
                         onmouseleave="if(isPageOwnerBloggerLogin())$('#bloggerLinkContainer').slideToggle()"
                         class="blogger-profile border">
                        <p class="text-center blogger-profile-title">
                            联系我&nbsp;<small id="linkCount" style="color: darkgray">(${ownerBgStat.linkCount})</small>
                        </p>

                        <p class="text-center" style="display: none" id="bloggerLinkContainer">
                    <span class="button-edit" id="linkEditBtn"
                          data-target="#modifyLinkDialog" data-toggle="modal">编辑</span>
                            <span class="button-edit-new" id="linkNewBtn"
                                  data-target="#newLinkDialog" data-toggle="modal">新建</span>
                        </p>
                        <hr class="default-line">
                        <p class="blogger-link" id="bloggerLink"></p>
                    </div>
                </div>

                <%--博文列表部分--%>
                <div class="col-md-9">
                    <div id="blogList"></div>
                    <div class="box" id="box"></div>
                </div>

            </c:when>
            <c:otherwise>

                <%--博文列表部分--%>
                <div class="col-md-9">
                    <div id="blogList"></div>
                    <div class="box" id="box"></div>
                </div>

                <%--右侧--%>
                <div class="col-md-3">

                        <%--头像--%>
                    <div class="blogger-profile border">
                        <c:choose>
                            <c:when test="${sessionScope['bloggerId'] == pageOwnerBloggerId}">
                                <%--头像--%>
                                <div class="avatar">
                                    <a class="avatar-edit" id="editAvatar" style="display: none">点击更换头像</a>

                                    <img src="/image/${pageOwnerBloggerId}/type=public/${avatarId}?default=13"
                                         class="avatar-img avatar-img-editable"
                                         id="bloggerAvatar"
                                         onmouseenter="if(isPageOwnerBloggerLogin())$('#editAvatar').show()"
                                         onmouseleave="if(isPageOwnerBloggerLogin())$('#editAvatar').hide()"
                                         onclick="editAvatar()">
                                </div>
                                <%--用户名--%>
                                <p class="text-center blogger-name">
                                        ${pageOwnerBloggerName}
                                </p>
                            </c:when>
                            <c:otherwise>
                                <%--头像--%>
                                <div class="avatar">
                                    <img src="/image/${pageOwnerBloggerId}/type=public/${avatarId}?default=13"
                                         class="avatar-img">
                                </div>
                                <%--用户名--%>
                                <p class="text-center blogger-name">${pageOwnerBloggerName}</p>
                            </c:otherwise>
                        </c:choose>
                        <hr>
                        <p class="text-center blogger-aboutme">${aboutMe}</p>
                    </div>
                    <br>

                        <%--他的喜欢，收藏--%>
                    <div class="border" style="background-color: white">
                        <br>
                        <div class="list-group">
                            <a class="list-group-item vertical-center blogger-favourite"
                               onclick="initBlog()">
                                <img class="img24px" src="/images/icon/icons8-news-80.png">&nbsp;
                                所有文章
                                &nbsp;<span class="count" id="subBlogCount">(${ownerBgStat["blogCount"]})</span>
                            </a>
                            <a class="list-group-item vertical-center blogger-favourite"
                               href="/${pageOwnerBloggerName}/blog/favourite/like">
                                    <%--<i class="material-icons icons">favorite_border</i>&nbsp;--%>
                                <img class="img24px" src="/images/icon/icons8-heart-outline-80.png">&nbsp;
                                <c:choose>
                                    <c:when test="${sessionScope['bloggerId'] == pageOwnerBloggerId}">
                                        我喜欢的文章
                                    </c:when>
                                    <c:otherwise>
                                        他喜欢的文章
                                    </c:otherwise>

                                </c:choose>
                                &nbsp;<span class="count">(${ownerBgStat.likedCount})</span>
                            </a>
                            <a class="list-group-item vertical-center blogger-favourite"
                               href="/${pageOwnerBloggerName}/blog/favourite/collect">
                                <img class="img24px" src="/images/icon/icons8-collect-100.png">&nbsp;
                                <c:choose>
                                    <c:when test="${sessionScope['bloggerId'] == pageOwnerBloggerId}">
                                        我收藏的文章
                                    </c:when>
                                    <c:otherwise>
                                        他收藏的文章
                                    </c:otherwise>
                                </c:choose>

                                &nbsp;<span class="count">(${ownerBgStat.collectCount})</span>
                            </a>
                        </div>
                    </div>
                    <br>

                        <%--标签--%>
                    <div onmouseenter="if(isPageOwnerBloggerLogin())$('#bloggerLabelContainer').slideToggle()"
                         onmouseleave="if(isPageOwnerBloggerLogin())$('#bloggerLabelContainer').slideToggle()"
                         class="blogger-profile border">

                        <p class="text-center blogger-profile-title">
                            标签&nbsp;<small id="labelCount" style="color: darkgray">(${ownerBgStat.labelCount})</small>
                        </p>

                        <p class="text-center" style="display: none" id="bloggerLabelContainer">
                    <span class="button-edit" id="labelEditBtn"
                          data-target="#modifyLabelDialog" data-toggle="modal">编辑</span>
                            <span class="button-edit-new" id="labelNewBtn"
                                  data-target="#newLabelDialog" data-toggle="modal">新建</span>
                        </p>

                        <hr class="default-line">
                        <p class="blogger-label-content" id="blogLabel">
                        </p>

                    </div>
                    <br>

                        <%--创建的类别--%>
                    <div onmouseenter="if(isPageOwnerBloggerLogin())$('#bloggerCategoryContainer').slideToggle()"
                         onmouseleave="if(isPageOwnerBloggerLogin())$('#bloggerCategoryContainer').slideToggle()"
                         class="blogger-profile border">
                        <p class="text-center blogger-profile-title">
                            类别&nbsp;<small id="categoryCount" style="color: darkgray">(${ownerBgStat.categoryCount})
                        </small>
                        </p>

                        <p class="text-center" style="display: none" id="bloggerCategoryContainer">
                    <span class="button-edit" id="categoryEditBtn"
                          data-target="#modifyCategoryDialog" data-toggle="modal">编辑</span>
                            <span class="button-edit-new" id="categoryNewBtn"
                                  data-target="#newCategoryDialog"
                                  data-toggle="modal">新建</span>
                        </p>
                        <hr class="default-line">
                        <div class="list-group category-list-group" id="blogCategory"></div>
                    </div>
                    <br>

                        <%--联系我--%>
                    <div onmouseenter="if(isPageOwnerBloggerLogin())$('#bloggerLinkContainer').slideToggle()"
                         onmouseleave="if(isPageOwnerBloggerLogin())$('#bloggerLinkContainer').slideToggle()"
                         class="blogger-profile border">
                        <p class="text-center blogger-profile-title">
                            联系我&nbsp;<small id="linkCount" style="color: darkgray">(${ownerBgStat.linkCount})</small>
                        </p>

                        <p class="text-center" style="display: none" id="bloggerLinkContainer">
                    <span class="button-edit" id="linkEditBtn"
                          data-target="#modifyLinkDialog" data-toggle="modal">编辑</span>
                            <span class="button-edit-new" id="linkNewBtn"
                                  data-target="#newLinkDialog" data-toggle="modal">新建</span>
                        </p>
                        <hr class="default-line">
                        <p class="blogger-link" id="bloggerLink"></p>
                    </div>
                </div>

            </c:otherwise>
        </c:choose>

    </div>
</div>

<br>
<br>
<br>
<jsp:include page="/views/footer/footer.jsp"/>

<script type="application/javascript">
    var pageOwnerBloggerId = ${pageOwnerBloggerId};
    var pageOwnerBloggerName = '${pageOwnerBloggerName}';
    var bloggerLoginSignal = ${not empty sessionScope['bloggerLoginSignal']};
    var blogCount = ${ownerBgStat["blogCount"]};
    <c:if test="${not empty sessionScope['bloggerLoginSignal']}">
    var loginBloggerId = ${sessionScope["bloggerId"]};
    </c:if>
</script>

<script type="application/javascript" src="/js/paging.js"></script>
<script type="application/javascript" src="/js/common.js"></script>
<script type="application/javascript" src="/js/blogger/main.js"></script>

</body>
</html>
