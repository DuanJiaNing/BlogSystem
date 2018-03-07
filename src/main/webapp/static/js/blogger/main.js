var defaultBlogCount = 10;

// 博文检索条件
var filterData = {
    cids: null,
    lids: null,
    kword: null,
    sort: null,
    order: null
};

// 高级检索时保存id
var cidsArray = [];
var lidsArray = [];

// -----------------------------------------------------------------------------------------------------------------加载
function loadLabel() {
    $.get(
        '/blogger/' + pageOwnerBloggerId + '/label',
        {offset: 0, rows: 20},
        function (result) {
            var html = '';
            if (result.code === 0) {
                var array = result.data;
                // 设置高级筛选框内容
                setComplexFilterLabel(array);
                // 设置标签编辑框内容
                setModifyLabel(array);

                for (var index in array) {
                    var size = 0.4 + 1.5 * Math.random();
                    var label = array[index];
                    html += '<a class="blog-list-item-label" style="font-size: ' + size + 'em" onclick="filterBlogByLabel(' + label.id + ')">' +
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
                // 设置高级筛选框内容
                setComplexFilterCategory(array);
                // 设置类别编辑框内容
                setModifyCategory(array);

                for (var index in array) {
                    var ca = array[index];
                    html += '<a class="list-group-item border0" ' +
                        'onclick="filterBlogByCategory(' + ca.id + ')"' +
                        'onmouseenter="if(isPageOwnerBloggerLogin()) $(this).find(\'.badge\').fadeToggle(\'fast\',\'linear\');"' +
                        'onmouseleave="if(isPageOwnerBloggerLogin()) $(this).find(\'.badge\').fadeToggle(\'fast\',\'linear\');">'
                        + ca.title + '<span class="count">&nbsp;(' + ca.count + ')</span></a>'
                }
            }

            if (html === '') {
                setCategoryWhenEmpty('blogCategory');
            } else {
                $('#blogCategory').html('<a class="list-group-item border0" onclick="initBlog()">' +
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
                setModifyLink(array);

                for (var index in array) {
                    var link = array[index];
                    html += '<a class="blogger-link-item"';

                    var bewrite = link.bewrite;
                    if (!isStrEmpty_(bewrite))
                        html += ' data-toggle="tooltip" title="' + bewrite + '" data-placement="bottom"';

                    html += ' target="_blank" href="' + link.url + '">' + link.title + '</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
                }

            }

            if (html === '') {
                setContactWhenEmpty("bloggerLink");
            } else {
                $('#bloggerLink').html(html);
                initToolTip();
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

// ----------------------------------------------------------------------------------------------------------加载-编辑框
function setModifyLabel(array) {
    var html = '';
    for (var index in array) {
        var label = array[index];
        html += '<a class="list-group-item border0" style="text-align: center" create="' + label.createDate + '" ' +
            'did="' + label.id + '" onclick="addModifyLabelChoose(this)">' + label.title + '</a>';
    }

    if (html === '') {
        setLabelWhenEmpty('modifyLabelListGroup');
    } else {
        $('#modifyLabelListGroup').html(html);
    }

}

function setModifyLink(array) {
    var html = '';
    for (var index in array) {
        var link = array[index];
        html += '<a class="list-group-item border0" style="text-align: center" url="' + link.url + '" bewrite="' + link.bewrite + '" ' +
            'onclick="addModifyLinkChoose(this)" did="' + link.id + '">' + link.title + '</a>';
    }

    if (html === '') {
        setLabelWhenEmpty('modifyLinkListGroup');
    } else {
        $('#modifyLinkListGroup').html(html);
    }

}

function setModifyCategory(array) {
    var html = '';
    for (var index in array) {
        var category = array[index];
        html += '<a class="list-group-item border0" style="text-align: center"' +
            'onclick="addModifyCategoryChoose(this)" did="' + category.id + '" bewrite="' + category.bewrite + '">' + category.title + '</a>';
    }

    if (html === '') {
        setCategoryWhenEmpty('modifyCategoryListGroup');
    } else {
        $('#modifyCategoryListGroup').html(html);
    }

}

// ---------------------------------------------------------------------------------------------加载-高级检索框-排序规则
function setSortRule(key, title) {
    filterData.sort = key;
    $('#complexFilterSortRuleShow').html(title + '<span class="caret"></span>&nbsp;');
}

function setSortOrder(key, title) {
    filterData.order = key;
    $('#complexFilterSortOrderShow').html(title + '<span class="caret"></span>');
}

// -------------------------------------------------------------------------------------------------------加载时内容为空
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

// --------------------------------------------------------------------------------------------------------加载-高级检索
function setComplexFilterLabel(array) {

    var html = '';

    for (var index in array) {
        var item = array[index];
        html += '<span class="blog-filter-default" onclick="toggleLabelClass(this)" key="' + item.id + '">' + item.title + '</span>&nbsp;&nbsp;';
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
    }

    if (html === '') {
        setLabelWhenEmpty('complexFilterCategory');
    } else {
        $('#complexFilterCategory').html(html);
    }

}

// ----------------------------------------------------------------------------------------反转高级检索标签/类别选中状态
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

// -------------------------------------------------------------------------------------------------------------检索博文
function complexFilter() {
    var keyword = $('#keyWord').val();
    setFilterData(cidsArray.join(','), lidsArray.join(','), keyword, filterData.sort, filterData.order);
    filterBloggerBlog(0, defaultBlogCount, true, true);
    $('#complexFilterDialog').modal('toggle');
}

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
                ins = '，去<a style="font-size: x-large" href="/edit_blog?bid=' + loginBloggerId + '" target="_blank">写博文</a>';

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

// ---------------------------------------------------------------------------------------------------------装载博文列表
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
                'onmouseleave="if(isPageOwnerBloggerLogin()) $(this).find(\'.col-md-3 > p\').fadeToggle(\'fast\',\'linear\')" ' +
                'class="list-group-item blog-list-item shadow-border">' +
                '<div class="row">' +
                '<div class="col-md-9">' +
                '<p>' +
                '<h3 class="list-group-item-heading"><span class="blog-list-item-title" title="' + item.title + '">' + item.title + '</span></h3>' +
                '</p>' +
                '</div>' +
                '<div class="col-md-3">' +
                '<p style="display: none;" class="text-right">' +
                '<span class="button-edit" onclick="window.open(\'/edit_blog?bid=' + pageOwnerBloggerId + '&blogId=' + item.id + '\',\'_blank\')">编辑</span>&nbsp;&nbsp;<span class="button-edit-check">数据</span>&nbsp;&nbsp;<span class="button-edit-delete">删除</span>' +
                '</p>' +
                '</div>' +
                '</div>' +

                '<h4>' +
                '<small class="list-group-item-count-text"><b>' + dateFormat(item.releaseDate) + '</b>&nbsp;&nbsp;' +
                item.collectCount + '收藏&nbsp;&nbsp;' + item.viewCount + '浏览&nbsp;&nbsp;' + item.likeCount + '喜欢&nbsp;&nbsp;' + item.commentCount + '评论' +
                '</small>' +
                '</h4>' +
                '<p class="blog-list-item-summary">' + item.summary + '</p><br>' +
                '<table>' +
                '  <tr>' +
                '    <td style="color: gray">' + cates + '&nbsp;&nbsp;</td>' +
                (labels === '' ? '' : '<td style="color: gray">' + labels + '</td>') +
                '  </tr>' +
                '</table>' +
                '</li>'
        }

        html += '</ul>';

        $('#blogList').html(html);

    }
}

// -----------------------------------------------------------------------------------------------------重置高级检索条件
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

// 判断当前博客是否为登陆博主主页
function isPageOwnerBloggerLogin() {
    return bloggerLoginSignal && pageOwnerBloggerId === loginBloggerId;
}

// 加载初始博文列表
function initBlog() {
    // 将会加载两次
    setFilterData(null, null, null, null, null);
    filterBloggerBlog(0, defaultBlogCount, true, false);
}

// 填充检索条件
function setFilterData(cids, lids, kword, sort, order) {
    filterData.cids = cids;
    filterData.lids = lids;
    filterData.kword = kword;
    filterData.sort = sort;
    filterData.order = order;
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

// 显示 修改头像 框
function editAvatar() {
    $('#editAvatarDialog').modal('show')
}

// 回到顶部
$(function () {
    $("#scroll-to-top").click(function () {
        scrollToTop();
        $("#scroll-to-top").tooltip('hide');
    });
});

// 初始化所有的 tip
function initToolTip() {
    $('[data-toggle="tooltip"]').tooltip();
}

// -------------------------------------------------------------------------------------------------------创建标签时回调
var funWhenCreateLabelSuccess = function (id) {
    loadLabel();
};

var funWhenCreateLabelFail = function (result) {
};

// -------------------------------------------------------------------------------------------------------创建类别时回调
var funWhenCreateCategorySuccess = function (id) {
    loadCategory();
};

var funWhenCreateCategoryFail = function (result) {
};

// -------------------------------------------------------------------------------------------------------创建链接时回调
var funWhenCreateLinkSuccess = function (id) {
    loadContact();
};

var funWhenCreateLinkFail = function (result) {
};

// -------------------------------------------------------------------------------------------------------编辑标签后回调
var funWhenEditLabelSuccess = function () {
    loadLabel();
    filterBloggerBlog(0, defaultBlogCount, true, false);
};

// -------------------------------------------------------------------------------------------------------编辑类别后回调
var funWhenEditCategorySuccess = function () {
    loadCategory();
    filterBloggerBlog(0, defaultBlogCount, true, false);
};

// -------------------------------------------------------------------------------------------------------编辑链接后回调
var funWhenEditLinkSuccess = function () {
    loadContact();
};

// -------------------------------------------------------------------------------------------------------删除标签后回调
var funWhenDeleteLabelSuccess = function () {
    loadLabel();
    filterBloggerBlog(0, defaultBlogCount, true, false);
};

// -------------------------------------------------------------------------------------------------------删除类别后回调
var funWhenDeleteCategorySuccess = function () {
    loadCategory();
    filterBloggerBlog(0, defaultBlogCount, true, false);
};

// -------------------------------------------------------------------------------------------------------删除链接后回调
var funWhenDeleteLinkSuccess = function () {
    loadContact();
};

// ------------------------------------------------------------------------------------------------------ 登录对话框回调
function funAfterLoginSuccess(result, name) {
    location.href = '/' + name + '/archives';
}

function funAfterLoginFail(result) {
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
