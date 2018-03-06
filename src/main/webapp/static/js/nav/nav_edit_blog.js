$(document).ready(function () {
    loadLabel();
    loadCategory();
});

function setLabel(array) {

    var html = '';

    for (var index in array) {
        var item = array[index];
        var id = item.id + '';

        var clasz = 'blog-select-default';
        if (labelId !== '' && labelId.indexOf(id, 0) !== -1) {
            clasz = 'blog-select-label-choose';
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
        }

        html += '<span class="' + clasz + '" onclick="toggleCategoryClass(this)" key="' + item.id + '">' + item.title + '</span>&nbsp;&nbsp;';
    }

    if (html === '') {
    } else {
        $('#editBlogCategory').html(html);
    }

}

function loadLabel() {
    $.get(
        '/blogger/' + bloggerId + '/label',
        {offset: 0, rows: 20},
        function (result) {
            if (result.code === 0) {
                var array = result.data;
                setLabel(array);
            }
        }, 'json'
    )
}

function loadCategory() {
    $.get(
        '/blogger/' + bloggerId + '/category',
        {offset: 0, rows: 1000},
        function (result) {
            if (result.code === 0) {
                var array = result.data;
                setCategory(array);
            }
        }, 'json'
    )
}

// 选中的类别和标签id
var cidsArray = [];
var lidsArray = [];

function toggleLabelClass(t) {
    var th = $(t);
    var key = th.attr('key');

    if (th.hasClass('blog-select-default')) {
        th.removeClass('blog-select-default');
        th.addClass('blog-select-label-choose');

        lidsArray.push(key);

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
        th.removeClass('blog-select-default');
        th.addClass('blog-select-category-choose');

        cidsArray.push(key);

    } else {
        th.addClass('blog-select-default');
        th.removeClass('blog-select-category-choose');

        var index = getArrayIndex(cidsArray, key);
        cidsArray.splice(index, 1);
    }
}
