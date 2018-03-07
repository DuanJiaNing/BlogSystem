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
</head>
<body>
<div class="modal fade" tabindex="-1" role="dialog" id="newCategoryDialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content dialog-title-container">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title dialog-title">新建类别</h4>
            </div>
            <div class="modal-body dialog-body">
                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-8">
                        <form>
                            <div class="form-group">
                                <label>名称</label><br>
                                <input type="text" id="categoryTitle" placeholder="类别名" class="form-input">
                            </div>
                            <div class="form-group">
                                <label>说明</label><br>
                                <textarea class="default-textarea" id="categoryBewrite" placeholder="类别说明"></textarea>
                            </div>
                        </form>
                        <span class="error-msg" id="categoryErrorMsg"></span>

                    </div>
                    <div class="col-md-2"></div>

                </div>
            </div>
            <div class="modal-footer dialog-footer">
                <p class="text-right">
                    <button class="button-success" id="newCategoryBtn"
                            onclick="createCategory(funWhenCreateCategorySuccess, funWhenCreateCategoryFail)">创建
                    </button>&nbsp;&nbsp;&nbsp;&nbsp;
                </p>

            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script type="application/javascript" src="/js/dialog/new_category_dialog.js"></script>
</body>
</html>
