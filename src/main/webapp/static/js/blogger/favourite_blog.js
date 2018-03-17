// 博文检索条件
var filterData = {
    sort: 'release_date',
    order: 'desc'
};

function filterBloggerBlog(offset, rows, refreshPageIndicator, toTop, refreshTotalRealCount) {
    $.get(
        '/blogger/' + pageOwnerBloggerId + '/' + type,
        {
            offset: offset,
            rows: rows,
            sort: filterData.sort,
            order: filterData.order
        },
        function (result) {

            setBlogs(result.data, '<br><br><br><p class="text-center lead">没有博文</p><br><br><br>');

            if (refreshPageIndicator) {
                setPageIndicator(0);
            }

            // if (refreshTotalRealCount)
            // $('#blogCount').html(result.data.length + '篇博文');

            if (toTop) {
                scrollToTop();
            }

            initToolTip();

        }
    );
}

// ------------------------------------------------------------------------------------------------------ 登录对话框回调
function funAfterLoginSuccess(result, name) {
    location.reload();
}

function funAfterLoginFail(result) {
}

function initBlog() {
    filterBloggerBlog(0, defaultBlogCount, true, true, true);
}

// 判断当前博客是否为登陆博主主页
function isPageOwnerBloggerLogin() {
    return bloggerLoginSignal && pageOwnerBloggerId === loginBloggerId;
}

var defaultBlogCount = 10;

// 刷新分页插件
function setPageIndicator(initIndex) {
    var init = true;
    $.get(
        '/blogger/' + pageOwnerBloggerId + '/' + type + '/count',
        null,
        function (result) {
            if (result.code === 0) {
                var count = result.data;
                $('#blogCount').html('共&nbsp;' + count + '&nbsp;篇');
                if (type === 'like') {
                    $('#favouriteLikeCount').html('(' + count + ')');
                } else {
                    $('#favouriteCollectCount').html('(' + count + ')');
                }

                $('#box').paging({
                    initPageNo: initIndex, // 初始页码
                    totalPages: Math.ceil(count / defaultBlogCount), //总页数
                    totalCount: count + '条', // 条目总数
                    slideSpeed: 600, // 缓动速度。单位毫秒
                    jump: true, //是否支持跳转
                    callback: function (page) { // 回调函数
                        if (init && page === 1) {
                            init = false;
                            return;
                        }

                        filterBloggerBlog((page - 1) * defaultBlogCount, defaultBlogCount, false, true, false);
                    }
                })
            }
        }, 'json'
    );

}

function setBlogs(array, defaulz) {

    if (array == null || array.length === 0) {
        $('#blogList').html(defaulz);
    } else {

        var html = '';
        for (var index in array) {
            var item = array[index];

            var avatar = item.author.avatar.path;
            var name = item.author.username;

            var date = dateFormat_(item.date);

            var title = item.blog.title;
            var summary = item.blog.summary;

            var likeCount = item.blog.likeCount;
            var collectCount = item.blog.collectCount;
            var commentCount = item.blog.commentCount;
            var viewCount = item.blog.viewCount;

            html += '<div class="row"' +
                'onmouseenter="if(isPageOwnerBloggerLogin()) $(this).find(\'.button-edit-delete\').fadeToggle(\'fast\',\'linear\')" ' +
                'onmouseleave="if(isPageOwnerBloggerLogin()) $(this).find(\'.button-edit-delete\').fadeToggle(\'fast\',\'linear\')" ' +
                '>' +
                '                <div class="col-md-3 item-avatar-container">' +
                '                    <table>' +
                '                        <tr>' +
                '                            <td rowspan="4">' +
                '                                <img class="img-circle blogger-avatar" style="cursor: pointer" onclick="gotoBloggerMainPage(\'' + name + '\')" src="' + avatar + '">' +
                '                            </td>' +
                '                            <td></td>' +
                '                        </tr>' +
                '                        <tr>' +
                '                            <td class="item-blogger-name" onclick="gotoBloggerMainPage(\'' + name + '\')">' + name + '</td>' +
                '                        </tr>' +
                '                        <tr>' +
                '                            <td>' +
                '                                <span class="item-date">' + date + '</span>' +
                '                            </td>' +
                '                        </tr>' +
                '                        <tr>' +
                '                            <td></td>' +
                '                        </tr>' +
                '                    </table>' +
                '                </div>' +
                '                <div class="col-md-9">' +
                '                    <h4>' +
                '                     <span  class="item-blog-title" onclick="window.open(\'/' + name + '/blog/' + title + '\',\'_blank\')">' + title + '</span>' +
                '                     &nbsp;<span style="display: none" class="button-edit-delete" onclick="removeFavourite(' + item.blog.id + ')">移除</span>' +
                '                    </h4>' +
                '                    <p class="item-blog-summary">' + summary +
                '                    </p>' +
                '                    <p>' +
                '                        <small style="color: darkgray">' +
                '                            喜欢&nbsp;' + likeCount +
                '                            <span class="vertical-line">&nbsp;&nbsp;|&nbsp;&nbsp;</span>' +
                '                            收藏&nbsp;' + collectCount +
                '                            <span class="vertical-line">&nbsp;&nbsp;|&nbsp;&nbsp;</span>' +
                '                            留言&nbsp;' + commentCount +
                '                            <span class="vertical-line">&nbsp;&nbsp;|&nbsp;&nbsp;</span>' +
                '                            ' + viewCount + '&nbsp;次浏览' +
                '                        </small>' +
                '                    </p>' +
                '                    <hr><br>' +
                '                </div>' +
                '            </div>';

        }

        if (html === '') html = defaulz;

        $('#blogList').html(html);

    }
}

function gotoBloggerMainPage(name) {
    window.open('/' + name + '/archives', '_blank');
}

function removeFavourite(blogId) {
    $.ajax({
        url: '/blogger/' + loginBloggerId + '/' + blogId + '/operate=' + type,
        type: 'delete',
        success: function (result) {
            if (result.code === 0) {
                filterBloggerBlog(0, defaultBlogCount, true, false, true);
                toast('已移除', 500);

            } else {
                toast('出错啦：' + result.msg, 2000);
            }
        }
    });

}

$(document).ready(function () {
    initBlog();
});
