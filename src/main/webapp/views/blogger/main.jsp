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
    <link rel="stylesheet" href="/css/blogger/main.css">
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/paging.css">

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
        var bloggerLoginSignal = ${not empty sessionScope['bloggerLoginSignal']};
        var blogCount = ${statistics["blogCount"]};
        <c:if test="${not empty sessionScope['bloggerLoginSignal']}">
        var loginBloggerId = ${sessionScope["bloggerId"]};
        </c:if>

    </script>

    <script type="application/javascript" src="/js/paging.js"></script>
    <script type="application/javascript" src="/js/common.js"></script>
    <script type="application/javascript" src="/js/blogger/main.js"></script>

</head>
<body>

<button id="scroll-to-top" data-toggle="tooltip" data-placement="left" title="回到顶部">TOP</button>

<jsp:include page="/views/nav.jsp"/>
<jsp:include page="/views/dialog/new_label_dialog.jsp"/>
<jsp:include page="/views/dialog/new_category_dialog.jsp"/>
<jsp:include page="/views/dialog/new_link_dialog.jsp"/>
<jsp:include page="/views/dialog/upload_image_dialog.jsp"/>

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
                <small>${statistics["blogCount"]}篇博文&nbsp;&nbsp;<span class="vertical-line">|</span>
                    &nbsp;&nbsp;${statistics["wordCount"]}字&nbsp;&nbsp;<span class="vertical-line">|</span>
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
                <br>
                <c:choose>
                    <c:when test="${sessionScope['bloggerId'] == pageOwnerBloggerId}">
                        <%--头像--%>
                        <div class="avatar">
                            <a class="avatar-edit" id="editAvatar" style="display: none">点击更换头像</a>

                            <img src="/image/${pageOwnerBloggerId}/type=public/${avatarId}"
                                 class="img-rounded avatar-img avatar-img-editable"
                                 onmouseenter="if(isPageOwnerBloggerLogin())$('#editAvatar').show()"
                                 onmouseleave="if(isPageOwnerBloggerLogin())$('#editAvatar').hide()"
                                 onclick="editAvatar()">
                        </div>
                        <%--用户名--%>
                        <p class="text-center blogger-name">
                            <a>${pageOwnerBloggerName}</a>
                        </p>
                    </c:when>
                    <c:otherwise>
                        <%--头像--%>
                        <div class="avatar">
                            <img src="/image/${pageOwnerBloggerId}/type=public/${avatarId}"
                                 class="img-rounded avatar-img">
                        </div>
                        <%--用户名--%>
                        <p class="text-center blogger-name">${pageOwnerBloggerName}</p>
                    </c:otherwise>
                </c:choose>
            </div>
            <hr>
            <p class="lead blogger-aboutme">${aboutMe}</p>
            <br>
            <br>

            <%--标签--%>
            <div onmouseenter="if(isPageOwnerBloggerLogin()){$('#labelNewBtn').fadeToggle('fast','linear');$('#labelEditBtn').fadeToggle('fast','linear')}"
                 onmouseleave="if(isPageOwnerBloggerLogin()){$('#labelNewBtn').fadeToggle('fast','linear');$('#labelEditBtn').fadeToggle('fast','linear')}">
                <div class="row">
                    <div class="col-md-6">
                        <h4 class="default-h4"><b>标签</b></h4>
                    </div>
                    <div class="col-md-6">
                        <br>
                        <span class="button-edit" style="display: none" id="labelEditBtn"
                              data-target="#newLabelDialog" data-toggle="modal">编辑</span>

                        <span class="button-edit-new" style="display: none" id="labelNewBtn"
                              data-target="#newLabelDialog" data-toggle="modal">新建</span>
                    </div>
                </div>
                <hr class="default-line">
                <p class="blogger-label" id="blogLabel">
                </p>
            </div>
            <br>

            <%--创建的类别--%>
            <div onmouseenter="if(isPageOwnerBloggerLogin())$('#categoryNewBtn').fadeToggle('fast','linear')"
                 onmouseleave="if(isPageOwnerBloggerLogin())$('#categoryNewBtn').fadeToggle('fast','linear')">
                <div class="row">
                    <div class="col-md-8">
                        <h4 class="default-h4"><b>类别</b></h4>
                    </div>
                    <div class="col-md-4">
                        <br>
                        <span class="button-edit-new" id="categoryNewBtn" style="display: none"
                              data-target="#newCategoryDialog"
                              data-toggle="modal">新建</span>
                    </div>
                </div>
                <hr class="default-line">
                <div class="list-group" id="blogCategory">
                </div>
            </div>
            <br>

            <%--联系我--%>
            <div onmouseenter="if(isPageOwnerBloggerLogin()){$('#linkNewBtn').fadeToggle('fast','linear');$('#linkEditBtn').fadeToggle('fast','linear')}"
                 onmouseleave="if(isPageOwnerBloggerLogin()){$('#linkNewBtn').fadeToggle('fast','linear');$('#linkEditBtn').fadeToggle('fast','linear')}">
                <div class="row">
                    <div class="col-md-6">
                        <h4 class="default-h4"><b>联系我</b></h4></div>
                    <div class="col-md-6">
                        <br>
                        <span class="button-edit" id="linkEditBtn" style="display: none"
                              data-target="#newLinkDialog" data-toggle="modal">编辑</span>

                        <span class="button-edit-new" id="linkNewBtn" style="display: none"
                              data-target="#newLinkDialog" data-toggle="modal">新建</span>
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
<jsp:include page="/views/footer.jsp"/>
</body>
</html>
