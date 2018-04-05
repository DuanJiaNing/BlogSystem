<%--
  Created by IntelliJ IDEA.
  User: DuanJiaNing
  Date: 2018/4/5
  Time: 8:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <link rel="stylesheet" href="/css/dialog/download_all_blog_dialog.css">

</head>
<body>

<div class="modal fade" tabindex="-1" role="dialog" id="downloadAllBlogDialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content dialog-title-container">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title dialog-title">下载所有博文</h4>
            </div>

            <div class="modal-body dialog-body">
                <small style="color: gray">选择下载博文格式</small>
                <hr>
                <div class="row" style="padding-left: 16px;padding-right: 16px">
                    <div class="col-md-6 format-center file-format-choosed" onclick="switchFormat(this)">
                        <div class="format-center-text" value="md">
                            md
                        </div>
                    </div>
                    <div class="col-md-6 format-center file-format" onclick="switchFormat(this)">
                        <div class="format-center-text" value="html">
                            html
                        </div>
                    </div>
                </div>

                <form method="get" id="downloadFile"></form>

            </div>
            <div class="modal-footer dialog-footer">
                <p class="text-right">
                    <button class="button-save"
                            onclick="beginDownload(${sessionScope["bloggerId"]})">
                        开始下载
                    </button>&nbsp;&nbsp;&nbsp;&nbsp;
                </p>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script src="/js/dialog/download_all_blog_dialog.js"></script>


</body>
</html>
