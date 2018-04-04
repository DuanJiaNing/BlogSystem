<%--
  Created by IntelliJ IDEA.
  User: DuanJiaNing
  Date: 2018/4/3
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <link rel="stylesheet" href="/css/dialog/blog_import.css">

</head>
<body>

<div class="modal fade" tabindex="-1" role="dialog" id="fileUploadDialog">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content dialog-title-container">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title dialog-title">批量导入博文</h4>
            </div>

            <div class="modal-body dialog-body">
                <div class="row">
                    <div class="col-md-8 vertical-center">
                        <a class="file">选择文件
                            <input type="file" accept=".zip" id="zipFile" onchange="chooseFileChange(this)">
                        </a>
                        &nbsp;&nbsp;<small style="color: gray;">请将&nbsp;<b>md</b>&nbsp;为后缀的博文文件打包为&nbsp;<b>zip</b>&nbsp;格式再上传
                    </small>
                    </div>
                    <div class="col-md-4 vertical-center">
                        <p class="text-right" style="width: 100%;margin-top: 10px">
                            <button onclick="importBlog(${sessionScope["bloggerId"]})" class="button-edit-check">
                                上传
                            </button>
                        </p>
                    </div>
                </div>

                <hr>
                <div class="progress">
                    <div class="progress-bar progress-bar-danger progress-bar-striped" id="progressbar"
                         role="progressbar"
                         aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 0%">
                        <span class="sr-only">80% Complete (danger)</span>
                    </div>
                </div>

                <br>
                <small id="processStatus"></small>&nbsp;&nbsp;
                <span id="showChoosedFileName"></span>&nbsp;&nbsp;
                <span class="error-msg" id="blogImportErrorMsg"></span>

                <br>
                <br>
                <div id="importSucc" style="max-height: 230px;overflow: auto">

                </div>

            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script src="/js/dialog/blog_import.js"></script>

</body>
</html>
