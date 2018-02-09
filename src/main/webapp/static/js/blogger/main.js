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
        '/blogger/' + bloggerId + '/label',
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
                html = '<p class="text-center"><small>还没有标签&nbsp;</small>';
                if (checkLogin())
                    html += '<a data-toggle="modal" data-target=".bs-example-modal-sm">新建标签</a></p>';
            }
            $('.blogger-label').html(html);

        }, 'json'
    )
}

function loadCategory() {
    $.get(
        '/blogger/' + bloggerId + '/category',
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
                html = '<p class="text-center"><small>还没有类别&nbsp;</small>';
                if (checkLogin())
                    html += '<a data-toggle="modal" data-target="#newCategoryDialog">新建类别</a></p>';
            }
            $('#blogCategory').html(html);

        }, 'json'
    )
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
        '/blogger/' + bloggerId + '/label',
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
        '/blogger/' + bloggerId + '/category',
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

function checkLogin() {
    return bloggerLoginSignal == null;
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

}

// 加载联系方式
function initContact() {

}

function filterBlogByLabel(id) {

}

function filterBlogByCategory(id) {

}

$(document).ready(function () {
    // 加载初始博文列表
    initBlog();

    // 加载标签
    loadLabel();

    // 加载类别
    loadCategory();

    // 加载联系方式
    initContact();
});
