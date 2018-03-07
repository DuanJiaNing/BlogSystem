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
                <p class="text-right">
                    <button class="button-success" id="newLabelBtn"
                            onclick="createLabel(funWhenCreateLabelSuccess, funWhenCreateLabelFail)">
                        创建
                    </button>&nbsp;&nbsp;&nbsp;&nbsp;
                </p>
            </div>

        </div>
    </div>
</div>

<script type="application/javascript" src="/js/dialog/new_label_dialog.js"></script>

</body>
</html>
