<%--
  Created by IntelliJ IDEA.
  User: DuanJiaNing
  Date: 2018/2/17
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/css/dialog/upload_avatar_dialog.css">
    <link rel="stylesheet" href="/plugin/cropper/css/cropper.min.css">

</head>
<body>

<div class="modal fade" tabindex="-1" role="dialog" id="editAvatarDialog">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content dialog-title-container">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title dialog-title">修改头像</h4>
            </div>

            <div class="modal-body dialog-body">
                <label title="上传图片" for="chooseImg" class="button-operation">
                    <input type="file" accept="image/jpg,image/jpeg,image/png" name="file" id="chooseImg"
                           class="hidden" onChange="selectImg(this)"> 选择图片
                </label>

                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <button class="button-edit cropper-reset-btn">复位</button>&nbsp;
                <button class="button-edit cropper-rotate-btn">旋转</button>&nbsp;
                <button class="button-edit cropper-scaleX-btn">换向</button>&nbsp;

                <hr>
                <div class="row">
                    <div class="col-md-8">
                        <div class="panel panel-default">
                            <img id="tailoringImg" class="tailoring-image">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div>
                            <p>头像预览</p>
                            <hr>
                            <div class="preview-image"></div>
                        </div>
                    </div>
                </div>

                <p>上传图片，<b>调整选框来选定区域</b>，点击上传以使用新头像。<br>
                    <small class="upload-msg">支持格式：.jpg .jpeg .png</small>
                </p>

                <span class="error-msg" id="editAvatarErrorMsg"></span>

            </div>

            <div class="modal-footer dialog-footer">
                <p class="text-right">
                    <button class="button-success" id="editAvatarBtn"
                            onclick="saveAvatar(${sessionScope["bloggerId"]})">确定
                    </button>&nbsp;&nbsp;&nbsp;&nbsp;
                </p>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script src="/plugin/cropper/js/cropper.min.js"></script>
<script src="/js/dialog/upload_avatar_dialog.js"></script>

</body>
</html>
