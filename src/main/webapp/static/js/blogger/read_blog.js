// ------------------------------------------------------------------------------------------------------ 登录对话框回调
function funAfterLoginSuccess(result, name) {
    location.reload();
}

function funAfterLoginFail(result) {
}

function checkLogin() {
    if (!bloggerLoginSignal) {
        // 显示登录对话框
        $('#loginDialog').modal('show');
        error('请先登录', 'loginErrorMsg', true, 3000);
        return false;
    } else return true;
}

function likeBlog(th) {
    if (!checkLogin()) return;

    var like = $(th);
    if (like.attr('title') === '点击添加至 [喜欢]') {
        $.post(
            '/blogger/' + loginBloggerId + '/' + blogId + '/operate=like',
            null,
            function (result) {
                if (result.code === 0) {
                    like.attr('title', '你已喜欢，点击取消喜欢');
                    toast('已喜欢', 1000);
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
                    like.attr('title', '点击添加至 [喜欢]');
                    toast('已取消喜欢', 1000);
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
    if (collect.attr('title') === '点击添加至 [收藏]') {
        $.post(
            '/blogger/' + loginBloggerId + '/' + blogId + '/operate=collect',
            null,
            function (result) {
                if (result.code === 0) {
                    collect.attr('title', '你已收藏，点击可取消收藏');
                    toast('已收藏', 1000);
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
                    collect.attr('title', '点击添加至 [收藏]');
                    toast('已取消收藏', 1000);
                    updateBlogCountStatistics();
                } else {
                    toast('出错啦：' + result.msg, 2000);
                }
            }
        });
    }

}

function updateBlogCountStatistics() {
    $.get(
        '/blog/' + blogId + '/statistics/count',
        function (result) {
            if (result.code === 0) {
                $('#blogLikeCount').html(result.data.likeCount);

                $('#blogCollectCount').html(result.data.collectCount);

                $('#blogCommentCount').html(result.data.commentCount);
                $('#commentCount').html(result.data.commentCount);

                $('#blogViewCount').html(result.data.viewCount);
            }
        }
    )
}

function shareBlog() {

}

function complainBlog() {
    if (!checkLogin()) return;

}

function loadComment() {

    $.get(
        '/blog/' + blogId + '/comment',
        {
            rows: 1000,
            offset: 0
        },
        function (result) {

            var container = $('#commentContainer');
            var html = '';

            if (result.code === 0) {

                var array = result.data;
                $('#commentCount').html(array.length);

                for (var index in array) {
                    var comment = array[index];
                    var del = '';

                    if (isLoginBloggerComment(comment.spokesman.id)) {
                        del = '<span class=" vertical-line">&nbsp;&nbsp;|&nbsp;&nbsp;</span>' +
                            '<span style="cursor: pointer" onclick="deleteComment(' + comment.id + ')">删除</span>';
                    }

                    var bgname = comment.spokesman.username;
                    html += '<div class="comment">' +

                        '<b style="cursor: pointer" data-toggle="tooltip" title="' + comment.spokesman.profile.aboutMe +
                        '"data-placement="top"' +
                        'onclick="window.open(\'/' + bgname + '/archives\',\'_blank\')">' + bgname +
                        '</b>' +
                        '&nbsp;&nbsp;<small style="color: gray">' + dateFormat_(comment.releaseDate) + del +
                        '</small>' +

                        '<hr class="default-line">' +
                        '<dl class="dl-horizontal">' +

                        '<dt><br>' +
                        '<img style="cursor: pointer" class="img-circle img64px" src="' + comment.spokesman.avatar.path +
                        '" onclick="window.open(\'/' + bgname + '/archives\',\'_blank\')">' +
                        '&nbsp;&nbsp;&nbsp;&nbsp;</dt>' +

                        '<dd style="word-wrap: break-word"><br>' +
                        comment.content + '</dd>' +
                        '</dl>' +
                        '</div>';
                }

            } else if (result.code === 14) {
                $('#commentCount').html('0');

                if (bloggerLoginSignal) {
                    html = '<h4>还没有留言，留下一个留言吧！</h4>';
                } else {
                    html = '<h4>还没有留言，<a onclick="$(\'#loginDialog\').modal(\'show\')">登录</a>&nbsp;' +
                        '或&nbsp;<a href="/register">注册</a>&nbsp;然后留下一个留言吧！</h4>';
                }
            } else {
                html += result.msg;
            }

            container.html(html);
            initToolTip();

        }
    )

}

// 登录博主的留言
function isLoginBloggerComment(bgid) {
    if (typeof(loginBloggerId) === "undefined") return false;
    else return loginBloggerId === bgid;
}

// 删除留言
function deleteComment(bgid) {
    $.ajax({
        url: '/blogger/' + loginBloggerId + '/comment/' + bgid + '?blogId=' + blogId,
        async: false,
        type: 'delete',
        success: function (result) {
            if (result.code === 0) {
                loadComment();
            }
        }
    });
}

// 留言字数上限
var commentMaxLimit = 400;

function leaveAComment() {
    var dom = $('#leaveAComment');
    var comment = dom.val();

    if (isStrEmpty(comment)) {
        error('留言不能为空', 'commentErrorMsg', true, 3000);
        return;
    }

    if (comment.length > commentMaxLimit) {
        error('留言字数最多为400字', 'commentErrorMsg', true, 3000);
        return;
    }

    disableButton(false, 'commentBtn', '正在留言...', "button-disable");
    $.post(
        '/blogger/' + loginBloggerId + '/comment',
        {
            blogId: blogId,
            content: comment,
            listenerId: blogOwnerBloggerId
        },
        function (result) {
            if (result.code === 0) {
                disableButton(false, 'commentBtn', '留言成功', "button-disable");

                setTimeout(function () {
                    disableButton(true, 'commentBtn', '提交', "button-disable");
                    loadComment();
                    dom.val('');
                }, 1000);

            } else {
                error('留言出错：' + result.code, 'commentErrorMsg', true, 3000);
                disableButton(true, 'loginBtn', '提交', "button-disable");
            }
        }
    )

}

$(document).ready(function () {
    initToolTip();
    loadComment();
});
