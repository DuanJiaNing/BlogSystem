var defaultBlogCount = 10;

// 博文检索条件
var filterData = {
    cids: null,
    lids: null,
    kword: null
};

function showNameDiv() {
    var name = $('#useUserName');
    var phone = $('#useUserPhone');
    var siginName = $('#siginName');
    var siginPhone = $('#siginPhone');

    siginName.css('font-weight', 'bold');
    siginPhone.css('font-weight', 'normal');
    name.css('display', 'block');
    phone.css('display', 'none');
}

function showPhoneDiv() {
    var name = $('#useUserName');
    var phone = $('#useUserPhone');
    var siginName = $('#siginName');
    var siginPhone = $('#siginPhone');

    siginName.css('font-weight', 'normal');
    siginPhone.css('font-weight', 'bold');
    name.css('display', 'none');
    phone.css('display', 'block');
}

function loadLabel() {
    $.get(
        '/blogger/' + pageOwnerBloggerId + '/label',
        {offset: 0, rows: 20},
        function (result) {
            var html = '';
            if (result.code === 0) {
                var array = result.data;
                for (var index in array) {
                    var size = 0.4 + 1.5 * Math.random();
                    var label = array[index];
                    html += '<a style="font-size: ' + size + 'em" onclick="filterBlogByLabel(' + label.id + ')">' +
                        label.title + '</a>&nbsp;&nbsp;';
                }
            }

            if (html === '') {
                html = '<p class="text-center"><small>没有标签&nbsp;</small>';
                if (isPageOwnerBloggerLogin())
                    html += '<a data-toggle="modal" data-target="#newLabelDialog">新建标签</a></p>';
            }
            $('.blogger-label').html(html);

        }, 'json'
    )
}

function loadCategory() {
    $.get(
        '/blogger/' + pageOwnerBloggerId + '/category',
        {offset: 0, rows: 1000},
        function (result) {
            var html = '';
            if (result.code === 0) {
                var array = result.data;
                for (var index in array) {
                    var ca = array[index];
                    html += '<a class="list-group-item blogger-category" onclick="filterBlogByCategory(' + ca.id + ')">'
                        + ca.title + '<span class="count">&nbsp;(' + ca.count + ')</span> </a>'
                }
            }

            if (html === '') {
                html = '<p class="text-center"><small>没有类别&nbsp;</small>';
                if (isPageOwnerBloggerLogin())
                    html += '<a data-toggle="modal" data-target="#newCategoryDialog">新建类别</a></p>';

                $('#blogCategory').html(html);
            } else {
                $('#blogCategory').html('<a class="list-group-item blogger-category" onclick="initBlog()">' +
                    '全部<span class="count">&nbsp;(' + blogCount + ')</span> </a>' + html);
            }

        }, 'json'
    )
}

function loadContact() {
    $.get(
        '/blogger/' + pageOwnerBloggerId + '/link',
        {offset: 0, rows: 20},
        function (result) {
            var html = '';
            if (result.code === 0) {
                var array = result.data;
                for (var index in array) {
                    var link = array[index];
                    html += '<a class="blogger-link-item" target="_blank" href="' + link.url + '">' + link.title + '</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
                }
            }

            if (html === '') {
                html = '<p class="text-center"><small>没有链接&nbsp;</small>';
                if (isPageOwnerBloggerLogin())
                    html += '<a data-toggle="modal" data-target="#newLinkDialog">新建链接</a></p>';
            }
            $('.blogger-link').html(html);

        }, 'json'
    );
}

// 创建标签并重新加载 标签栏
function newLabelAndReload() {
    var name = $('#labelName').val();

    if (isStrEmpty(name)) {
        $('#labelErrorMsg').html('标签名称不能为空');
        return;
    } else {
        $('#labelErrorMsg').html(' ');
    }
    $.post(
        '/blogger/' + pageOwnerBloggerId + '/label',
        {title: name},
        function (result) {
            if (result.code === 0) {
                loadLabel();
                $('.bs-example-modal-sm').modal('toggle');
            } else {
                $('#labelErrorMsg').html(result.msg);
            }
        }, 'json'
    );
}

// 创建类别并重新加载 类别栏
function newCategoryAndReload() {
    var title = $('#categoryTitle').val();
    var bewrite = $('#categoryBewrite').val();

    if (isStrEmpty(title)) {
        $('#categoryErrorMsg').html('类别名称不能为空');
        return;
    } else {
        $('#categoryErrorMsg').html(' ');
    }

    $.post(
        '/blogger/' + pageOwnerBloggerId + '/category',
        {title: title, bewrite: bewrite},
        function (result) {
            if (result.code === 0) {
                loadCategory();
                $('#newCategoryDialog').modal('toggle');
            } else {
                $('#categoryErrorMsg').html(result.msg);
            }
        }, 'json'
    );

}

// 新建链接并重新加载 链接栏（与我联系）
function newLinkAndReload() {
    var title = $('#linkTitle').val();
    var url = $('#linkUrl').val();
    var bewrite = $('#linkBewrite').val();

    var error = $('#linkErrorMsg');
    if (isStrEmpty(title)) {
        error.html("名称不能为空");
        return;
    }

    if (isStrEmpty(url) || !isUrl(url)) {
        error.html("url不正确");
        return;
    }
    error.html("");

    $.post(
        '/blogger/' + pageOwnerBloggerId + '/link',
        {title: title, url: url, bewrite: bewrite},
        function (result) {
            if (result.code === 0) {
                loadContact();
                $('#newLinkDialog').modal('toggle');
            } else {
                $('#linkErrorMsg').html(result.msg);
            }
        }, 'json'
    );

}

function isPageOwnerBloggerLogin() {
    return bloggerLoginSignal && pageOwnerBloggerId === loginBloggerId;
}

// 登录
function signIn() {
    //TODO 电话验证码登录方式
    var btn = $('#signInBtn');
    var name = $('#userName').val();
    var pwd = $('#password').val();

    btn.html('登录中...');
    $.post(
        '/blogger/login/way=name',
        {username: name, password: pwd},
        function (result) {
            if (result.code !== 0) {
                $('#loginErrorMsg').html(result.msg);
            }

            btn.html('登录');
        }, 'json'
    )


}

// 加载初始博文列表
function initBlog() {
    // 将会加载两次
    setFilterData(null, null, null);
    filterBloggerBlog(0, defaultBlogCount, true, false);
}

function setBlogs(array, defaulz) {

    if (array == null || array.length === 0) {
        $('#blogList').html(defaulz);
    } else {

        var html = '<ul class="list-group">';
        for (var index in array) {
            var item = array[index];

            var cates = '';
            var cate = item.categories;
            for (var i in cate) {
                var c = cate[i];
                cates += '<small class="blog-category" onclick="filterBlogByCategory(' + c.id + ')" ' +
                    'data-toggle="tooltip" title="' + c.bewrite + '" data-placement="bottom">' + c.title + '</small>&nbsp;&nbsp;';
            }

            var labels = '';
            var label = item.labels;
            for (var l in label) {
                var lb = label[l];
                labels += '<small title="标签" class="blog-link" onclick="filterBlogByLabel(' + lb.id + ')">#' + lb.title + '</small>&nbsp;&nbsp;';
            }

            html += '<li class="list-group-item blog-list-item">' +
                '<p>' +
                '<h3 class="list-group-item-heading" ><span class="blog-list-item-title">' + item.title +
                '</span></h3></p>' +
                '<h4>' +
                '<small class="list-group-item-text"><b>' + dateFormat(item.releaseDate) + '</b>&nbsp;&nbsp;' +
                item.collectCount + '收藏&nbsp;&nbsp;' + item.viewCount + '浏览&nbsp;&nbsp;' + item.likeCount + '喜欢&nbsp;&nbsp;' + item.commentCount + '评论' +
                '</small>' +
                '</h4>' +
                '<p class="list-group-item-text blog-list-item-summary">' + item.summary + '</p><br>' +
                '<table>' +
                '  <tr>' +
                '    <td style="color: gray">' + cates + '&nbsp;&nbsp;</td>' +
                (labels === '' ? '' : '<td style="color: gray">' + labels + '</td>') +
                '  </tr>' +
                '</table>' +
                '<hr>' +
                '</li>'
        }

        html += '</ul>';

        $('#blogList').html(html);

    }
}

function setFilterData(cids, lids, kword) {
    filterData.cids = cids;
    filterData.lids = lids;
    filterData.kword = kword;
}

// 检索博文
function filterBloggerBlog(offset, rows, refreshPageIndicator, toTop) {
    $.get(
        '/blog',
        {
            bloggerId: pageOwnerBloggerId,
            offset: offset,
            rows: rows,
            cids: filterData.cids,
            lids: filterData.lids,
            kword: filterData.kword
        },
        function (result) {

            var ins = '';
            if (isPageOwnerBloggerLogin())
                ins = '，去<a style="font-size: x-large">写博文</a>';

            setBlogs(result.data, '<br><br><br><p class="text-center lead">没有博文' + ins + '</p><br><br><br>');

            if (refreshPageIndicator) {
                setPageIndicator(0);
            }

            if (toTop) {
                scrollToTop();
            }

            initToolTip();

        }, 'json'
    );
}

function filterBlogByLabel(id) {
    setFilterData(null, id, null);
    filterBloggerBlog(0, defaultBlogCount, true, true);
}

function filterBlogByCategory(id) {
    setFilterData(id, null, null);
    filterBloggerBlog(0, defaultBlogCount, true, true);
}


// 刷新分页插件
function setPageIndicator(initIndex) {
    $.get(
        '/blog/count',
        null,
        function (result) {
            if (result.code === 0) {
                var count = result.data;
                $('#box').paging({
                    initPageNo: initIndex, // 初始页码
                    totalPages: Math.ceil(count / defaultBlogCount), //总页数
                    totalCount: count + '条', // 条目总数
                    slideSpeed: 600, // 缓动速度。单位毫秒
                    jump: true, //是否支持跳转
                    callback: function (page) { // 回调函数
                        filterBloggerBlog((page - 1) * defaultBlogCount, defaultBlogCount, false, true);
                    }
                })
            }
        }, 'json'
    );

}

$(document).ready(function () {
    // 加载初始博文列表
    initBlog();

    // 加载标签
    loadLabel();

    // 加载类别
    loadCategory();

    // 加载联系方式
    loadContact();
});

$(function () {
    $("#scroll-to-top").click(function () {
        scrollToTop();
        $("#scroll-to-top").tooltip('hide');
    });
});

function initToolTip() {
    $('[data-toggle="tooltip"]').tooltip();
}