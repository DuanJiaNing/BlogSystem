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
    <title>上传图片</title>
    <script type="application/javascript" src="/js/dialog/upload_image_dialog.js"></script>
    <script src="/js/jquery.Jcrop.js"></script>

    <link rel="stylesheet" href="/css/jquery.Jcrop.css">
    <link rel="stylesheet" href="/css/dialog/upload_image_dialog.css">

</head>
<body>

<div class="modal fade" tabindex="-1" role="dialog" id="editAvatarDialog">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content dialog-title-container">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title dialog-title">上传图片</h4>
            </div>
            <div class="modal-body dialog-body">

                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-8">
                        <form enctype="multipart/form-data" id="uploadAvatarImageForm">
                            <table>
                                <tr>
                                    <td>
                                        <input type="file" name="image">
                                    </td>
                                    <td>
                                        <span class="button-upload" onclick="uploadImage(${sessionScope["bloggerId"]})">上传</span>
                                    </td>
                                    <td>
                                        &nbsp;&nbsp;&nbsp;<small id="uploadMsg" class="upload-msg"></small>
                                    </td>
                                </tr>
                            </table>
                        </form>
                        <hr>
                        <img id="avatarPicture" class="avatar-image" src="/images/pool.jpg">

                        <p><br>上传图片，<b>使用鼠标来选定区域，</b>点击确定以使用新头像。<br>
                            <small class="upload-msg">支持格式：.jpg .gif .png .bmp .jpeg</small>
                        </p>
                    </div>
                    <div class="col-md-3">

                    </div>

                </div>
            </div>
            <div class="modal-footer dialog-footer">
                <button class="button-success" id="editAvatarBtn">确定
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</body>
</html>
