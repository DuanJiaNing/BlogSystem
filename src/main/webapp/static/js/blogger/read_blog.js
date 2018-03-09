// ------------------------------------------------------------------------------------------------------ 登录对话框回调
function funAfterLoginSuccess(result, name) {
    location.reload();
}

// 回到顶部
$(function () {
    $("#goto-top").click(function () {
        scrollToTop();
        $("#goto-top").tooltip('hide');
    });
});

function funAfterLoginFail(result) {
}

// 初始化所有的 tip
function initToolTip() {
    $('[data-toggle="tooltip"]').tooltip();
}

$(document).ready(function () {
    initToolTip();
});
