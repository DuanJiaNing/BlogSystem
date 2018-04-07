function loadBlogStatistics(blogId) {
    $.get(
        '/blog/' + blogId + '/statistics',
        null,
        function (result) {
            if (result.code === 0) {
                setData(result.data);
            } else {
                $('#blogStatisticsDialog').modal('hide');
                toast(result.msg, 2000);
            }
        }
    )
}

// ------------------------------------------------------------------------------------------------------ 登录对话框回调
function funAfterLoginSuccess(result, name) {
    location.reload();
}

function funAfterLoginFail(result) {
}

var blogName;

function setData(data) {
    $('#blogStatistics-title').html('<b>博文:&nbsp;</b>' + data.title);
    $('#blogStatistics-releaseDate').html(dateFormat_(data.releaseDate));
    $('#blogStatistics-nearestModifyDate').html(dateFormat_(data.nearestModifyDate));
    $('#blogStatistics-viewCount').html(data.statistics.viewCount);
    $('#blogStatistics-wordCount').html(data.wordCount);
    $('#blogStatistics-likeCount').html(data.statistics.likeCount);
    $('#blogStatistics-collectCount').html(data.statistics.collectCount);
    $('#blogStatistics-commentCount').html('(' + data.statistics.commentCount + ')');
    blogName = data.title;

    var colC = 6; // 每行6个头像
    var rowC = 3; // 最多三行
    var itemDiv = '<div class="col-md-2">\n' +
        '            <a href="/bloggerName/archives" class="thumbnail">\n' +
        '                <img src="avatar" data-toggle="tooltip" title="bloggerName" data-placement="bottom">\n' +
        '            </a>\n' +
        '        </div>';
    var html = '';

    // 喜欢
    var index = 0;
    if (data.likes.length > 0) {
        out:
            for (var i = 0; i < rowC; i++) {
                for (var j = 0; j < colC; j++) {
                    if (index >= data.likes.length) {
                        break out;
                    }

                    var name = data.likes[index].username;
                    var avatar = data.likes[index].avatar.path;
                    var it = itemDiv.replace(/bloggerName/g, name);
                    var t = it.replace('avatar', avatar);
                    html += t;

                    index++;
                }
            }

        $('#blogStatistics-liker').html(html);
    }

    // 收藏
    html = '';
    index = 0;
    if (data.collects.length > 0) {
        out:
            for (var i = 0; i < rowC; i++) {
                for (var j = 0; j < colC; j++) {
                    if (index >= data.collects.length) {
                        break out;
                    }

                    var name = data.collects[index].username;
                    var avatar = data.collects[index].avatar.path;
                    var it = itemDiv.replace(/bloggerName/g, name);
                    var t = it.replace('avatar', avatar);
                    html += t;

                    index++;
                }
            }
        $('#blogStatistics-collector').html(html);
    }

    initToolTip();
}

function setComment(data) {

    var html = '';
    for (var index in data) {
        var item = data[index];
        var cotentCount = 20;
        var cotent = item.content.length > cotentCount ? item.content.substr(0, cotentCount) + '...' : item.content;
        html += '&nbsp;&nbsp;&nbsp;&nbsp;<img src="' + item.spokesman.avatar.path + '" ' +
            'class="img-circle" style="width: 20px;height: 20px;cursor: pointer" ' +
            'data-toggle="tooltip" title="' + item.spokesman.username + '" data-placement="left"' +
            ' onclick="window.open(\"/' + item.spokesman.username + '/archives\",\"_blank\")" />' +
            '<span class="vertical-line">&nbsp;&nbsp;|&nbsp;&nbsp;</span><small>' + dateFormat_(item.releaseDate) +
            '</small>&nbsp;&nbsp;' +
            '<span class="blog-comment">' + cotent + '</span><hr>';
    }

    $('#blogStatistics-comment').html(html);
    initToolTip();

}

function loadBlogComment(blogId) {

    var count = 40; // 默认加载40条
    $.get(
        '/blog/' + blogId + '/comment',
        {
            rows: count,
            offset: 0
        },
        function (result) {
            if (result.code === 0) {
                setComment(result.data);
            } else if (result.code === 14) {
                $('#blogStatistics-comment').html('<h4>还没有评论，去&nbsp;<a onclick="goCheckBlog()">查看</a>&nbsp;并发表评论。</h4>');
            }
        }
    );
}

function goCheckBlog() {
    location.href = '/' + bloggerName + '/blog/' + blogName;
}

$(document).ready(function () {
    loadBlogStatistics(blogId);
    loadBlogComment(blogId);

});