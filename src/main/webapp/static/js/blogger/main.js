var defaultBlogCount = 10;

// 博文检索条件
var filterData = {
    cids: null,
    lids: null,
    kword: null,
    sort: null,
    order: null
};

var cidsArray = [];
var lidsArray = [];

function loadLabel() {
    $.get(
        '/blogger/' + pageOwnerBloggerId + '/label',
        {offset: 0, rows: 20},
        function (result) {
            var html = '';
            if (result.code === 0) {
                var array = result.data;
                setComplexFilterLabel(array);

                for (var index in array) {
                    var size = 0.4 + 1.5 * Math.random();
                    var label = array[index];
                    html += '<a style="font-size: ' + size + 'em" onclick="filterBlogByLabel(' + label.id + ')">' +
                        label.title + '</a>&nbsp;&nbsp;';
                }
            }

            if (html === '') {
                setLabelWhenEmpty('blogLabel');
            } else {
                $('#blogLabel').html(html);
            }

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
                setComplexFilterCategory(array);

                for (var index in array) {
                    var ca = array[index];
                    html += '<a class="list-group-item blogger-category" ' +
                        'onclick="filterBlogByCategory(' + ca.id + ')"' +
                        'onmouseenter="if(isPageOwnerBloggerLogin()) $(this).find(\'.badge\').fadeToggle(\'fast\',\'linear\');"' +
                        'onmouseleave="if(isPageOwnerBloggerLogin()) $(this).find(\'.badge\').fadeToggle(\'fast\',\'linear\');">'
                        + ca.title + '<span class="count">&nbsp;(' + ca.count + ')</span>' +
                        '<span class="badge button-edit" style="display: none" onclick="editCategory()">编辑</span></a>'
                }
            }

            if (html === '') {
                setCategoryWhenEmpty('blogCategory');
            } else {
                $('#blogCategory').html('<a class="list-group-item blogger-category" onclick="initBlog()">' +
                    '全部<span class="count">&nbsp;(' + blogCount + ')</span> </a>' + html);
            }

        }, 'json'
    )
}

function editCategory() {
    alert('dsaf');
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
                    ;
                }
            }

            if (html === '') {
                setContactWhenEmpty("bloggerLink");
            } else {
                $('#bloggerLink').html(html);
            }

        }, 'json'
    );
}

function loadSortRule() {

    $.get(
        '/sort-rule/rule',
        null,
        function (result) {
            if (result.code === 0) {
                var html = '';
                var array = result.data;
                for (var index in array) {
                    var item = array[index];
                    html += '<li><a onclick="setSortRule(\'' + item.key + '\',\'' + item.title + '\')">' + item.title + '</a></li>';
                }
                $('#complexFilterSortRuleShow').html(array[0].title + '<span class="caret"></span>&nbsp;');
                $('#complexFilterSortRule').html(html);
            }
        }, 'json');

    $.get(
        '/sort-rule/order',
        null,
        function (result) {
            if (result.code === 0) {
                var html = '';
                var array = result.data;
                for (var index in array) {
                    var item = array[index];
                    html += '<li><a onclick="setSortOrder(\'' + item.key + '\',\'' + item.title + '\')">' + item.title + '</a></li>';
                }
                $('#complexFilterSortOrderShow').html(array[0].title + '<span class="caret"></span>');
                $('#complexFilterSortOrder').html(html);
            }
        }, 'json');

}

function setSortRule(key, title) {
    filterData.sort = key;
    $('#complexFilterSortRuleShow').html(title + '<span class="caret"></span>&nbsp;');
}

function setSortOrder(key, title) {
    filterData.order = key;
    $('#complexFilterSortOrderShow').html(title + '<span class="caret"></span>');
}

function setContactWhenEmpty(id) {
    var html = '<p class="text-center"><small>没有链接&nbsp;</small>';
    if (isPageOwnerBloggerLogin())
        html += '<a data-toggle="modal" data-target="#newLinkDialog">新建链接</a></p>';

    $('#' + id).html(html);

}

function setCategoryWhenEmpty(id) {
    var html = '<p class="text-center"><small>没有类别&nbsp;</small>';
    if (isPageOwnerBloggerLogin())
        html += '<a data-toggle="modal" data-target="#newCategoryDialog">新建类别</a></p>';

    $('#' + id).html(html);
}

function setLabelWhenEmpty(id) {
    var html = '<p class="text-center"><small>没有标签&nbsp;</small>';
    if (isPageOwnerBloggerLogin())
        html += '<a data-toggle="modal" data-target="#newLabelDialog">新建标签</a></p>';

    $('#' + id).html(html);
}

function setComplexFilterLabel(array) {

    var html = '';

    for (var index in array) {
        var item = array[index];
        html += '<span class="blog-filter-default" onclick="toggleLabelClass(this)" key="' + item.id + '">' + item.title + '</span>&nbsp;&nbsp;';
        if (index % 5 === 0) {
            html += '<br>';
        }
    }

    if (html === '') {
        setLabelWhenEmpty('complexFilterLabel');
    } else {
        $('#complexFilterLabel').html(html);
    }

}

function setComplexFilterCategory(array) {

    var html = '';

    for (var index in array) {
        var item = array[index];
        html += '<span class="blog-filter-default" onclick="toggleCategoryClass(this)" key="' + item.id + '">' + item.title + '</span>&nbsp;&nbsp;';
        if (index % 4 === 0) {
            html += '<br>';
        }
    }

    if (html === '') {
        setLabelWhenEmpty('complexFilterCategory');
    } else {
        $('#complexFilterCategory').html(html);
    }

}

function toggleLabelClass(t) {
    var th = $(t);
    var key = th.attr('key');

    if (th.hasClass('blog-filter-default')) {
        th.removeClass('blog-filter-default');
        th.addClass('blog-filter-label-choose');

        lidsArray.push(key);

    } else {
        th.addClass('blog-filter-default');
        th.removeClass('blog-filter-label-choose');

        var index = getArrayIndex(lidsArray, key);
        lidsArray.splice(index, 1);
    }
}

function toggleCategoryClass(t) {
    var th = $(t);
    var key = th.attr('key');

    if (th.hasClass('blog-filter-default')) {
        th.removeClass('blog-filter-default');
        th.addClass('blog-filter-category-choose');

        cidsArray.push(key);

    } else {
        th.addClass('blog-filter-default');
        th.removeClass('blog-filter-category-choose');

        var index = getArrayIndex(cidsArray, key);
        cidsArray.splice(index, 1);
    }
}

function complexFilter() {
    var keyword = $('#keyWord').val();
    setFilterData(cidsArray.join(','), lidsArray.join(','), keyword, filterData.sort, filterData.order);
    filterBloggerBlog(0, defaultBlogCount, true, true);
    $('#complexFilterDialog').modal('toggle');
}

function resetComplexFilter() {
    setFilterData(null, null, null, null, null);
    cidsArray = [];
    lidsArray = [];

    // filterBloggerBlog(0, defaultBlogCount, true, true);
    $('#keyWord').val('');

    $('.blog-filter-category-choose').each(function (index, item) {
        $(item).removeClass('blog-filter-category-choose');
        $(item).addClass('blog-filter-default');
    });

    $('.blog-filter-label-choose').each(function (index, item) {
        $(item).removeClass('blog-filter-label-choose');
        $(item).addClass('blog-filter-default');
    });

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

function isPageOwnerBloggerLogin() {
    return bloggerLoginSignal && pageOwnerBloggerId === loginBloggerId;
}

// 加载初始博文列表
function initBlog() {
    // 将会加载两次
    setFilterData(null, null, null, null, null);
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
                cates += '<span class="blog-category" onclick="filterBlogByCategory(' + c.id + ')" ' +
                    'data-toggle="tooltip" data-placement="bottom"';
                if (!isStrEmpty_(c.bewrite))
                    cates += ' title="' + c.bewrite + '"';

                cates += '>' + c.title + '</span>&nbsp;&nbsp;';
            }

            var labels = '';
            var label = item.labels;
            for (var l in label) {
                var lb = label[l];
                labels += '<span title="标签" class="blog-link" onclick="filterBlogByLabel(' + lb.id + ')">#' + lb.title + '</span>&nbsp;&nbsp;';
            }

            html += '<li ' +
                'onmouseenter="if(isPageOwnerBloggerLogin()) $(this).find(\'.col-md-3 > p\').fadeToggle(\'fast\',\'linear\')" ' +
                'onmouseleave="if(isPageOwnerBloggerLogin()) $(this).find(\'.col-md-3 > p\').fadeToggle(\'fast\',\'linear\')" class="list-group-item blog-list-item">' +
                '<div class="row">' +
                '<div class="col-md-9">' +
                '<p>' +
                '<h3 class="list-group-item-heading"><span class="blog-list-item-title" title="' + item.title + '">' + item.title + '</span></h3>' +
                '</p>' +
                '</div>' +
                '<div class="col-md-3">' +
                '<p style="display: none;" class="text-right">' +
                '<span class="button-edit">编辑</span>&nbsp;&nbsp;<span class="button-edit-check">数据</span>&nbsp;&nbsp;<span class="button-edit-delete">删除</span>' +
                '</p>' +
                '</div>' +
                '</div>' +

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

function setFilterData(cids, lids, kword, sort, order) {
    filterData.cids = cids;
    filterData.lids = lids;
    filterData.kword = kword;
    filterData.sort = sort;
    filterData.order = order;
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
            kword: filterData.kword,
            sort: filterData.sort,
            order: filterData.order
        },
        function (result) {

            var ins = '';
            if (isPageOwnerBloggerLogin())
                ins = '，去<a style="font-size: x-large" href="/write_blog" target="_blank">写博文</a>';

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
    setFilterData(null, id, null, null, null);
    filterBloggerBlog(0, defaultBlogCount, true, true);
}

function filterBlogByCategory(id) {
    setFilterData(id, null, null, null, null);
    filterBloggerBlog(0, defaultBlogCount, true, true);
}

function filterBlogByKeyWord() {
    var word = $('#searchBlog').val();
    if (word !== '') {
        setFilterData(null, null, word, null, null);
        filterBloggerBlog(0, defaultBlogCount, true, true);
    } else {
        initBlog();
    }
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

// 修改头像
function editAvatar() {
    $('#editAvatarDialog').modal('show')
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

    // 加载高级检索的排序规则部分
    loadSortRule();
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

// ---------------------------------------创建标签时回调
var funWhenCreateLabelSuccess = function (id) {
    loadLabel();
};

var funWhenCreateLabelFail = function (result) {
};

// ---------------------------------------创建类别时回调
var funWhenCreateCategorySuccess = function (id) {
    loadCategory();
};

var funWhenCreateCategoryFail = function (result) {
};

// ---------------------------------------创建链接时回调
var funWhenCreateLinkSuccess = function (id) {
    loadContact();
};

var funWhenCreateLinkFail = function (result) {
};
