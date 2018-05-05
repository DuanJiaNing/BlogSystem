function setLabel(array) {

    var html = '';

    for (var index in array) {
        var item = array[index];
        var id = item.id + '';

        var clasz = 'blog-select-default';
        if (labelId !== '' && labelId.indexOf(id, 0) !== -1) {
            clasz = 'blog-select-label-choose';
            lidsArray.push(id);
        }

        html += '<span class="' + clasz + '" onclick="toggleLabelClass(this)" key="' + item.id + '">' + item.title + '</span>&nbsp;&nbsp;';
    }

    if (html === '') {
    } else {
        $('#editBlogLabel').html(html);
    }

}

function setCategory(array) {

    var html = '';

    for (var index in array) {
        var item = array[index];
        var id = item.id + '';

        var clasz = 'blog-select-default';
        if (categoryId !== '' && categoryId.indexOf(id, 0) !== -1) {
            clasz = 'blog-select-category-choose';
            cidsArray.push(id);
        }

        html += '<span class="' + clasz + '" onclick="toggleCategoryClass(this)" key="' + item.id + '">' + item.title + '</span>&nbsp;&nbsp;';
    }

    if (html === '') {
    } else {
        $('#editBlogCategory').html(html);
    }

}

// 选中的类别和标签id
var cidsArray = [];
var lidsArray = [];
// 最多选择3个类别
var categoryMaxCount = 3;
// 最多选择5个标签
var labelMaxCount = 5;

function errorInfoWhenRelease(msg) {
    error(msg, 'editBlogErrorMsg', true, 3000);
}

function toggleLabelClass(t) {
    var th = $(t);
    var key = th.attr('key');

    if (th.hasClass('blog-select-default')) {

        if (lidsArray.length === labelMaxCount) {
            errorInfoWhenRelease('最多选择' + labelMaxCount + ' 个标签');
        } else {
            th.removeClass('blog-select-default');
            th.addClass('blog-select-label-choose');
            lidsArray.push(key);
        }
    } else {
        th.addClass('blog-select-default');
        th.removeClass('blog-select-label-choose');

        var index = getArrayIndex(lidsArray, key);
        lidsArray.splice(index, 1);
    }
}

function toggleCategoryClass(t) {
    var th = $(t);
    var key = th.attr('key');

    if (th.hasClass('blog-select-default')) {

        if (cidsArray.length === categoryMaxCount) {
            errorInfoWhenRelease('最多选择 ' + categoryMaxCount + ' 个类别');
        } else {
            th.removeClass('blog-select-default');
            th.addClass('blog-select-category-choose');
            cidsArray.push(key);
        }

    } else {
        th.addClass('blog-select-default');
        th.removeClass('blog-select-category-choose');

        var index = getArrayIndex(cidsArray, key);
        cidsArray.splice(index, 1);
    }
}

function clearBlogData() {
    $('#editBlogTitle').val('');
    $('#editBlogSummary').val('');

    var cc = $('.blog-select-category-choose');
    for (var i = 0; i < cc.length; i++) {
        var dom = $(cc[i]);
        dom.addClass('blog-select-default');
        dom.removeClass('blog-select-category-choose');
        cidsArray = [];
    }

    var lc = $('.blog-select-label-choose');
    for (var i = 0; i < lc.length; i++) {
        var dom = $(lc[i]);
        dom.addClass('blog-select-default');
        dom.removeClass('blog-select-label-choose');
        lidsArray = [];
    }

}

var maxBlogTitleLen = 40;
var maxBlogSummaryLen = 200;

function releaseBlog(editMode, funAfterReleaseBlogSuccess) {
    var title = $('#editBlogTitle').val();
    var summary = $('#editBlogSummary').val();
    var contentMd = $('#editormd').val();
    var content = $('#editorHtml').val();

    if (isStrEmpty(contentMd)) {
        errorInfoWhenRelease('博文内容不能为空');
        return;
    }

    if (isStrEmpty(title)) {
        errorInfoWhenRelease('标题不能为空');
        return;
    }

    if (isStrEmpty(summary)) {
        errorInfoWhenRelease('摘要不能为空');
        return;
    }

    if (title.length > maxBlogTitleLen) {
        errorInfoWhenRelease('标题长度不能超过' + maxBlogTitleLen + '个字符');
        return;
    }

    if (summary.length > maxBlogSummaryLen) {
        errorInfoWhenRelease('摘要长度不能超过' + maxBlogSummaryLen + '个字符');
        return;
    }

    // 编辑模式
    disableButton(false, 'editReleaseBtn', '正在发布...', "button-disable");

    if (editMode === 1) {
        var data = '';
        if (cidsArray.length > 0) {
            data += 'cids=' + cidsArray.join(',') + '&';
        }

        if (lidsArray.length > 0) {
            data += 'lids=' + lidsArray.join(',') + '&';
        }

        if (!isStrEmpty(title)) {
            data += 'title=' + title + '&';
        }

        if (!isStrEmpty(contentMd)) {
            data += 'content=' + stringToUnicode(content) + '&' + 'contentMd=' + stringToUnicode(contentMd) + '&';
        }

        if (!isStrEmpty(summary)) {
            data += 'summary=' + summary + '&';
        }

        data = data.substr(0, data.length - 1);
        $.ajax({
            url: '/blogger/' + bloggerId + '/blog/' + blogId,
            data: data,
            type: 'put',
            success: function (result) {
                afterReleaseSuccess(result);
            }
        });

    } else if (editMode === 2) {
        // 创作模式
        $.post(
            '/blogger/' + bloggerId + '/blog',
            {
                cids: cidsArray.length === 0 ? '' : cidsArray.join(','),
                lids: lidsArray.length === 0 ? '' : lidsArray.join(','),
                title: title,
                content: stringToUnicode(content),
                contentMd: stringToUnicode(contentMd),
                summary: summary,
                keywords: ''
            },
            function (result) {
                afterReleaseSuccess(result);
            }
        );
    }

    function afterReleaseSuccess(result) {
        if (result.code === 0) {
            disableButton(false, 'editReleaseBtn', '发布成功', "button-disable");

            setTimeout(function () {
                disableButton(true, 'editReleaseBtn', '发布', "button-disable");
                $('#releaseBlogDialog').modal('hide');

                funAfterReleaseBlogSuccess(title);
            }, 1000);

        } else {
            disableButton(true, 'editReleaseBtn', '发布', "button-disable");
            if (result.code = 18) {
                errorInfoWhenRelease('存在同名博文');
            } else {
                errorInfoWhenRelease('发布失败：' + result.msg);
            }
        }
    }

}
