<%--
  Created by IntelliJ IDEA.
  User: DuanJiaNing
  Date: 2018/2/17
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<div class="modal fade bs-example-modal-sm dialog-middle" tabindex="-1" role="dialog" id="confirmDialog"
     aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content dialog-title-container-middle">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title dialog-title">提示</h4>
            </div>
            <div class="modal-body dialog-body">
                <h4 id="confirmText" style="color: gray"></h4>
                <br>
                <span class="error-msg" id="confirmErrorMsg"></span>
            </div>
            <div class="modal-footer dialog-footer">
                <p class="text-right">
                    <button class="button-dangerous" id="confirmBtn"
                            onclick="confirmExe()">
                        确认
                    </button>&nbsp;&nbsp;&nbsp;&nbsp;
                </p>
            </div>
        </div>
    </div>
</div>

<script type="application/javascript" src="/js/dialog/toast_dialog.js"></script>

</body>
</html>
