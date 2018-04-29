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

                $('#labelCount').html('(' + array.length + ')');

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
                $('#categoryCount').html('(' + array.length + ')');

                for (var index in array) {
                    var ca = array[index];
                    html += '<a class="list-group-item vertical-center border0" ' +
                        'onclick="filterBlogByCategory(' + ca.id + ')"' +
                        'onmouseenter="if(isPageOwnerBloggerLogin()) $(this).find(\'.badge\').fadeToggle(\'fast\',\'linear\');"' +
                        'onmouseleave="if(isPageOwnerBloggerLogin()) $(this).find(\'.badge\').fadeToggle(\'fast\',\'linear\');">'
                        + ca.title + '<span class="count">&nbsp;(' + ca.count + ')</span></a>'
                }
            }

            if (html === '') {
                setCategoryWhenEmpty('blogCategory');
            } else {
                $('#blogCategory').html(html);
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
                $('#linkCount').html('(' + array.length + ')');

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
    filterBloggerBlog(0, defaultBlogCount, true, true, false);
    $('#complexFilterDialog').modal('toggle');
}

/**
 * 重新加载博文列表
 * @param offset
 * @param rows
 * @param refreshPageIndicator 刷新列表分页插件（博文列表重新加载时一般都需要刷新）
 * @param toTop 滑动到顶部
 * @param refreshTotalRealCount 刷新总博文数量（博文新增或删除时才需刷新）
 */
function filterBloggerBlog(offset, rows, refreshPageIndicator, toTop, refreshTotalRealCount) {

    if (toTop) {
        scrollToTop();
    }

    $('#blogList').html('<br><br><br><p class="text-center lead">正在加载...</p><br><br><br>');

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

            if (refreshTotalRealCount) {
                $('#blogCount').html(result.data.length + '&nbsp;篇博文');
                $('#subBlogCount').html('(' + result.data.length + ')');

            }

            initToolTip();

        }, 'json'
    );
}

function filterBlogByLabel(id) {
    setFilterData(null, id, null, null, null);
    filterBloggerBlog(0, defaultBlogCount, true, true, false);
}

function filterBlogByCategory(id) {
    setFilterData(id, null, null, null, null);
    filterBloggerBlog(0, defaultBlogCount, true, true, false);
}

function filterBlogByKeyWord() {
    var word = $('#searchBlog').val();
    if (word !== '') {
        setFilterData(null, null, word, null, null);
        filterBloggerBlog(0, defaultBlogCount, true, true, false);
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
                labels += '<span title="标签" class="blog-link" onclick="filterBlogByLabel(' + lb.id + ')">#' + lb.title
                    + '</span>&nbsp;&nbsp;';
            }

            var blogImg = item.img;
            var colmd = 9, colmd2 = 3;
            if (isStrEmpty_(blogImg)) {
                colmd = 12;
                colmd2 = 0;
            }

            html += '<li ' +
                'onmouseenter="$(this).find(\'.button-edit-check\').fadeToggle(\'fast\');if(isPageOwnerBloggerLogin()) $(this).find(\'.loginNeedEdit\').fadeToggle(\'fast\')" ' +
                'onmouseleave="$(this).find(\'.button-edit-check\').fadeToggle(\'fast\');if(isPageOwnerBloggerLogin()) $(this).find(\'.loginNeedEdit\').fadeToggle(\'fast\')" ' +
                'class="list-group-item blog-list-item shadow-border">' +
                '<div class="row">' +
                '<div class="col-md-9">' +
                '<p>' +
                '<h3 class="list-group-item-heading"><span onclick="window.open(\'/' + pageOwnerBloggerName + '/blog/' +
                item.title + '\',\'_blank\')" class="blog-list-item-title" title="' + item.title + '">' +
                item.title + '</span></h3>' +
                '</p>' +
                '</div>' +

                '<div class="col-md-3">' +
                '<p class="text-right">' +
                '<span style="display: none;" class="button-edit loginNeedEdit" onclick="window.open(\'/edit_blog?bid=' + pageOwnerBloggerId + '&blogId=' + item.id + '\',\'_blank\')">编辑</span>&nbsp;&nbsp;' +
                '<span style="display: none;" class="button-edit-delete loginNeedEdit" onclick="showDeleteConfirmDialog(' + item.id + ')">删除</span>&nbsp;&nbsp;' +
                '<span style="display: none;" class="button-edit-check" onclick="showBlogStatisticsDialog(' + item.id + ')">数据</span>' +
                '</p>' +
                '</div>' +

                '</div>' +

                '<div class="row" style="height: 155px">' +
                '<div class="col-md-' + colmd + '">' +

                '<h4>' +
                '<small style="color: black">' + dateFormat(item.releaseDate) + '</small>' +
                '<small class="list-group-item-count-text">&nbsp;&nbsp;' +
                '喜欢&nbsp;' + item.likeCount + '<span class="vertical-line">&nbsp;&nbsp;|&nbsp;&nbsp;</span>' +
                '收藏&nbsp;' + item.collectCount + '<span class="vertical-line">&nbsp;&nbsp;|&nbsp;&nbsp;</span>' +
                '<span class="text-clickable"' +
                ' onclick="if (' + item.commentCount + ' > 0) ' +
                'window.open(\'/' + pageOwnerBloggerName + '/blog/' + item.title + '/#comment\',\'_blank\')" >' +
                '留言&nbsp;' + item.commentCount + '</span>' +
                '<span class="vertical-line">&nbsp;&nbsp;|&nbsp;&nbsp;</span>' +
                item.viewCount + '&nbsp;次浏览' +
                '</small>' +
                '</h4>' +

                '<p class="blog-list-item-summary">' + item.summary + '</p><br>' +

                '<div class="vertical-center">' +
                cates + '&nbsp;&nbsp;' +
                (labels === '' ? '' : labels + '&nbsp;&nbsp;') +
                '</div>' +
                '</div>' +

                // (!isStrEmpty_(blogImg) ? '' +
                //     '<div style="height: 100%;background-size: contain;background: url(' + blogImg + ') center no-repeat" class="vertical-center img-thumbnail col-md-' + colmd2 + '" ></div>' +
                //     '' : '') +

                '<div style="height: 100%;" class="vertical-center col-md-' + colmd2 + '">' +
                (!isStrEmpty_(blogImg) ? '<img class="img-thumbnail" style="width: auto;height: 100px;overflow: hidden" src="' + blogImg + '">' : '') +
                // (!isStrEmpty_(blogImg) ? '' : '') +
                '</div>' +
                '</div>' +

                '</li><br>'

        }

        html += '</ul>';

        setTimeout(function () {

            // 手动延伸
            $('#blogList').html(html);
            $('#blogList').css('display', 'none');

            $('#blogList').slideToggle('slow');
            // UPDATE 缩短手动延时
        }, 500);

    }

}

var confirmDeleteBlogId = -1;

function showDeleteConfirmDialog(blogId) {
    $('#confirmDialog').modal('show');
    $('#confirmText').html('确认删除，删除后将无法恢复');
    confirmDeleteBlogId = blogId;
}

function showBlogStatisticsDialog(blogId) {
    window.open('/' + pageOwnerBloggerName + '/blog-statistics?blogId=' + blogId, '_blank');
}

// -------------------------------------------------------------------------------------------------------- 确认删除博文
function confirmExe() {

    disableButton(false, 'confirmBtn', '正在删除...', "button-disable");
    $.ajax({
        url: '/blogger/' + loginBloggerId + '/blog/' + confirmDeleteBlogId,
        async: false,
        type: 'delete',
        success: function (result) {
            if (result.code === 0) {
                confirmDeleteBlogId = -1;
                disableButton(false, 'confirmBtn', '删除成功', "button-disable");
                filterBloggerBlog(0, defaultBlogCount, true, false, true);

                setTimeout(function () {
                    disableButton(true, 'confirmBtn', '确认', "button-disable");

                    $('#confirmDialog').modal('hide');
                    clearDiv('confirmText');

                }, 1000);

            } else {
                disableButton(true, 'confirmBtn', '确认', "button-disable");
                error('删除失败：' + result.msg, 'confirmErrorMsg', true, 3000);
            }
        }
    });
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
    setFilterData(null, null, null, "release_date", "desc");
    filterBloggerBlog(0, defaultBlogCount, true, true, false);
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
    var init = true;
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

// 显示 修改头像 框
function editAvatar() {
    $('#editAvatarDialog').modal('show')
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
    filterBloggerBlog(0, defaultBlogCount, true, false, false);
};

// -------------------------------------------------------------------------------------------------------编辑类别后回调
var funWhenEditCategorySuccess = function () {
    loadCategory();
    filterBloggerBlog(0, defaultBlogCount, true, false, false);
};

// -------------------------------------------------------------------------------------------------------编辑链接后回调
var funWhenEditLinkSuccess = function () {
    loadContact();
};

// -------------------------------------------------------------------------------------------------------删除标签后回调
var funWhenDeleteLabelSuccess = function () {
    loadLabel();
    filterBloggerBlog(0, defaultBlogCount, true, false, false);
};

// -------------------------------------------------------------------------------------------------------删除类别后回调
var funWhenDeleteCategorySuccess = function () {
    loadCategory();
    filterBloggerBlog(0, defaultBlogCount, true, false, false);
};

// -------------------------------------------------------------------------------------------------------删除链接后回调
var funWhenDeleteLinkSuccess = function () {
    loadContact();
};

// ------------------------------------------------------------------------------------------------------ 登录对话框回调
function funAfterLoginSuccess(result, name) {
    location.href = '/' + name + '/archives';
}

// ------------------------------------------------------------------------------------------------------ 头像修改成功后回调回调
function funAfterAvatarUpdateSuccess(imgId) {
    var url = '/image/' + loginBloggerId + '/type=private/' + imgId;
    $('#bloggerAvatar').attr('src', url);
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

    // 初始化修改头像模态框
    initCropper();
});
