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

function setData(data) {
    $('#blogStatistics-title').html('<b>博文:&nbsp;</b>' + data.title);
    $('#blogStatistics-releaseDate').html(dateFormat_(data.releaseDate));
    $('#blogStatistics-nearestModifyDate').html(dateFormat_(data.nearestModifyDate));
    $('#blogStatistics-viewCount').html(data.statistics.viewCount);
    $('#blogStatistics-wordCount').html(data.wordCount);
    $('#blogStatistics-likeCount').html(data.statistics.likeCount);
    $('#blogStatistics-collectCount').html(data.statistics.collectCount);
    $('#blogStatistics-commentCount').html(data.statistics.commentCount);

    var colC = 12;
    var rowC = 3; // 最多三行
    var itemDiv = '<div class="col-md-1">\n' +
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
        html += '<b>' + item.spokesman.username + '</b>' +
            '<span class="vertical-line">&nbsp;&nbsp;|&nbsp;&nbsp;</span><small>' + dateFormat_(item.releaseDate) +
            '</small><br><hr class="default-line"><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
            item.content + '</p><br>';
    }

    $('#blogStatistics-comment').html(html);
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
            }
        }
    );
}

$(document).ready(function () {
    loadBlogStatistics(blogId);
    loadBlogComment(blogId);

});