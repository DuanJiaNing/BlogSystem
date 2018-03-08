<%--
  Created by IntelliJ IDEA.
  User: DuanJiaNing
  Date: 2018/3/7
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<html>
<head>
    <link rel="stylesheet" href="/css/dialog/release_blog_dialog.css">

</head>
<body>

<%--立即发布--%>
<div class="modal fade" tabindex="-1" role="dialog" id="releaseBlogDialog">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content dialog-title-container">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title dialog-title">立即发布</h4>
            </div>
            <div class="modal-body dialog-body">
                <div class="form-group">
                    <label>标题</label><br>
                    <input type="text" id="editBlogTitle" placeholder="博文标题"
                           class="form-input" value="${blogTitle}"><br><br>
                </div>

                <div class="form-group">
                    <label>摘要</label><br>
                    <textarea class="default-textarea" id="editBlogSummary"
                              style="border: solid 1px lightgray;height: 123px;width: 70%"
                              placeholder="博文摘要">${blogSummary}</textarea>
                </div>

                <div class="form-group">
                    <label>选择类别</label><br>
                    <p id="editBlogCategory" style="line-height: 28px;max-height: 100px"></p>
                </div>
                <div class="form-group">
                    <label>选择标签</label><br>
                    <p id="editBlogLabel" style="line-height: 28px;max-height: 100px"></p>
                </div>

                <br>
                <span class="error-msg" id="editBlogErrorMsg"></span>
            </div>
            <div class="modal-footer dialog-footer">
                <p class="text-right">
                    <a id="editBlogBtnReset" onclick="clearBlogData()">清空
                    </a>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="button-success" id="editReleaseBtn"
                            onclick="releaseBlog(editMode,funAfterReleaseBlogSuccess)">发布
                    </button>&nbsp;&nbsp;
                </p>
            </div>
        </div><!-- /.modal-content -->
    </div>
</div>

<script>
    // 登录博主id
    var bloggerId = ${sessionScope['bloggerId']};

    // 编辑博文时有值
    var labelId = '${labelIds}';
    var categoryId = '${categoryId}';

</script>
<script type="application/javascript" src="/js/dialog/release_blog_dialog.js"></script>

</body>
</html>
