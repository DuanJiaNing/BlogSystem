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

function checkLogin() {
    if (!bloggerLoginSignal) {
        // 显示登录对话框
        $('#loginDialog').modal('show');
        error('请先登录', 'loginErrorMsg', true);
        return false;
    } else return true;
}

function likeBlog(th) {
    if (!checkLogin()) return;

    var like = $(th);
    if (like.html() === '喜欢') {
        $.post(
            '/blogger/' + loginBloggerId + '/' + blogId + '/operate=like',
            null,
            function (result) {
                if (result.code === 0) {
                    splash('取消喜欢', like, true);
                    updateBlogCountStatistics();
                } else {
                    toast('出错啦：' + result.msg, 2000);
                }
            }
        )
    } else {
        $.ajax({
            url: '/blogger/' + loginBloggerId + '/' + blogId + '/operate=like',
            type: 'delete',
            success: function (result) {
                if (result.code === 0) {
                    splash('喜欢', like, true);
                    updateBlogCountStatistics();
                } else {
                    toast('出错啦：' + result.msg, 2000);
                }
            }
        });
    }

}

function collectBlog(th) {
    if (!checkLogin()) return;

    var collect = $(th);
    if (collect.html() === '收藏') {
        $.post(
            '/blogger/' + loginBloggerId + '/' + blogId + '/operate=collect',
            null,
            function (result) {
                if (result.code === 0) {
                    splash('取消收藏', collect, true);
                    updateBlogCountStatistics();
                } else {
                    toast('出错啦：' + result.msg, 2000);
                }
            }
        )
    } else {
        $.ajax({
            url: '/blogger/' + loginBloggerId + '/' + blogId + '/operate=collect',
            type: 'delete',
            success: function (result) {
                if (result.code === 0) {
                    splash('收藏', collect, true);
                    updateBlogCountStatistics();
                } else {
                    toast('出错啦：' + result.msg, 2000);
                }
            }
        });
    }

}

function updateBlogCountStatistics() {

}

function shareBlog() {

}

function complainBlog() {
    if (!checkLogin()) return;

}


$(document).ready(function () {
    initToolTip();
});
