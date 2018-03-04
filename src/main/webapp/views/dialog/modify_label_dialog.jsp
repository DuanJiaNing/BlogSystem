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
    <title>编辑标签</title>

</head>
<body>

<div class="modal fade" tabindex="-1" role="dialog" id="modifyLabelDialog">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content dialog-title-container">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title dialog-title">编辑标签</h4>
            </div>

            <div class="modal-body dialog-body">
                <div class="row">
                    <div class="col-md-4">
                        <span class="glyphicon glyphicon glyphicon-chevron-up center-icon" aria-hidden="true"
                              onclick="$(this).animate({scrollTop: 0}, 500);"></span>
                        <br>
                        <br>
                        <div class="list-group" id="modifyLabelListGroup"></div>
                        <span class="glyphicon glyphicon glyphicon-chevron-down center-icon" aria-hidden="true"
                              onclick="$(this).animate({scrollTop: $(this).css('height')}, 500);"></span>
                    </div>

                    <div class="col-md-8">
                        <p class="text-center lead">
                            <small class="indicator" style="font-weight: bold" id="modifyLabelAsEdit"
                                   onclick="toggleDivState('chooseEditLabel','chooseDeleteLabel','modifyLabelAsEdit','modifyLabelAsDelete','modifyLabelErrorMsg','')"
                            >编辑
                            </small>&nbsp;&nbsp;<span class="vertical-line">|</span>&nbsp;&nbsp;<small
                                class="indicator" id="modifyLabelAsDelete"
                                onclick="toggleDivState('chooseDeleteLabel','chooseEditLabel','modifyLabelAsDelete','modifyLabelAsEdit','modifyLabelErrorMsg','')">
                            删除
                        </small>
                        </p>

                        <div id="chooseEditLabel">
                            <div class="form-group">
                            </div>
                        </div>

                        <div id="chooseDeleteLabel" style="display: none;">
                            <div class="form-group">
                            </div>
                        </div>
                    </div>
                </div>

                <span class="error-msg" id="modifyLabelErrorMsg"></span>

            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script src="/js/dialog/modify_label_dialog.js"></script>
</body>
</html>
