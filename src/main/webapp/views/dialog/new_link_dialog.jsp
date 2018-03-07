<%--
  Created by IntelliJ IDEA.
  User: DuanJiaNing
  Date: 2018/2/17
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>

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
                <p class="text-right">
                    <button class="button-success" id="newLinkBtn"
                            onclick="createLink(funWhenCreateLinkSuccess, funWhenCreateLinkFail)">创建
                    </button>&nbsp;&nbsp;&nbsp;&nbsp;
                </p>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script type="application/javascript" src="/js/dialog/new_link_dialog.js"></script>

</body>
</html>
