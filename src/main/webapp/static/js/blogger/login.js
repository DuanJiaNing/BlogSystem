/**
 * 登录
 * @param funAfterLoginSuccess 登录成功时回调，参数为result
 * @param funAfterLoginFail 登录失败时回调，参数为错误码和错误信息
 */
function login() {
    // 用户名登录

    var pwd = $('#loginPassword').val();
    var name = $('#loginUserName').val();

    if (checkInputEmptyWhenLogin('loginUserName') ||
        checkInputEmptyWhenLogin('loginPassword')) {
        return;
    }

    if (!isPassword(pwd)) {
        errorInfoWhenLogin('密码格式不正确，<small>密码由 6-12 位字母和数字组成</small>');
        return;
    }

    disableButton(false, 'loginBtn', '正在登录...', "button-disable");
    $.post(
        '/blogger/login/way=name',
        {
            username: name,
            password: pwd
        },
        function (result) {
            if (result.code === 0) {
                disableButton(false, 'loginBtn', '登录成功', "button-disable");

                setTimeout(function () {
                    disableButton(true, 'loginBtn', '登录', "button-disable");

                    window.location.href = '/' + name + '/archives';
                }, 1000);

            } else {
                errorInfoWhenLogin(result.msg);
                disableButton(true, 'loginBtn', '登录', "button-disable");

            }
        }
    );

}

// 为 '' 返回true
function checkInputEmptyWhenLogin(id) {
    var va = $('#' + id);

    if (va.val() === '') {
        errorInfoWhenLogin('<small>请输入&nbsp;</small>' + va.attr('placeholder'));
        return true;
    } else {
        errorInfoWhenLogin('');
        return false;
    }
}

function errorInfoWhenLogin(msg) {
    error(msg, 'loginErrorMsg', true, 3000);
}

$(document).ready(function () {
    initToolTip();

    $.adaptiveBackground.run()

    $('.grid').masonry({
        // options
        itemSelector: '.grid-item',
        columnWidth: 15,
        fitWidth: true
    });
});