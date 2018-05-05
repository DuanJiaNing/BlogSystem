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
    <link rel="stylesheet" href="/css/dialog/modify_label_dialog.css">

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
                              onclick="$('#modifyLabelListGroup').animate({scrollTop: 0}, 300);"></span>
                        <br>
                        <br>
                        <div class="list-group" id="modifyLabelListGroup"
                             style="max-height: 300px;overflow: auto;margin: 0 50px;"></div>
                        <br>

                        <span class="glyphicon glyphicon glyphicon-chevron-down center-icon" aria-hidden="true"
                              onclick="$('#modifyLabelListGroup').animate({scrollTop: $('#modifyLabelListGroup').css('height')}, 300);"></span>
                    </div>

                    <div class="col-md-8" style="min-height: 400px">
                        <p class="text-center lead">
                            <small class="indicator" style="font-weight: bold" id="modifyLabelAsEdit"
                                   onclick="toggleDivState('chooseEditLabel','chooseDeleteLabel','modifyLabelAsEdit',
                                   'modifyLabelAsDelete','modifyLabelErrorMsg','');clearDiv('showChoosedLabel');selectLabelModel=1;"
                            >编辑
                            </small>&nbsp;&nbsp;<span class="vertical-line">|</span>&nbsp;&nbsp;<small
                                class="indicator" id="modifyLabelAsDelete"
                                onclick="toggleDivState('chooseDeleteLabel','chooseEditLabel','modifyLabelAsDelete',
                                'modifyLabelAsEdit','modifyLabelErrorMsg','');clearDiv('showChoosedLabel');selectLabelModel=2;">
                            删除
                        </small>
                        </p>

                        <div class="row">
                            <div class="col-md-7">
                                <small style="color: darkgray;">已选标签</small>
                                <hr class="default-line">
                                <p id="showChoosedLabel" style="line-height: 24px;min-height: 120px"></p>

                            </div>
                            <div class="col-md-5">
                            </div>
                        </div>

                        <div id="chooseEditLabel">
                            <div class="form-group">
                                <label>重命名</label><br>
                                <input type="text" placeholder="标签名" class="form-input">
                            </div>
                            <br>
                            <button class="button-success" id="modifyEditLabelBtn"
                                    onclick="exeLabelUpdate(this,${sessionScope['bloggerId']},funWhenEditLabelSuccess)">
                                提交
                            </button>
                        </div>

                        <div id="chooseDeleteLabel" style="display: none;">
                            <br>
                            <p>
                                <small>可选择</small>
                                <b>&nbsp;多个&nbsp;</b>
                                <small>标签。</small>
                            </p>
                            <button class="button-dangerous" id="modifyDeleteLabelBtn"
                                    onclick="exeLabelDelete(this,${sessionScope['bloggerId']},funWhenDeleteLabelSuccess)">
                                删除
                            </button>
                        </div>

                        <br>
                        <span class="error-msg" id="modifyLabelErrorMsg"></span>

                    </div>
                </div>

            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script src="/js/dialog/modify_label_dialog.js"></script>
</body>
</html>
