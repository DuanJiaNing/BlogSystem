function showNameDiv() {
    var useUserName = $('#useUserName');
    var useUserPhone = $('#useUserPhone');
    useUserName.css('display', 'block');
    useUserPhone.css('display', 'none');

    var loginByName = $('#loginByName');
    var loginByPhone = $('#loginByPhone');
    loginByName.css('font-weight', 'bold');
    loginByPhone.css('font-weight', 'normal');

}

function showPhoneDiv() {
    var useUserName = $('#useUserName');
    var useUserPhone = $('#useUserPhone');
    useUserName.css('display', 'none');
    useUserPhone.css('display', 'block');

    var loginByName = $('#loginByName');
    var loginByPhone = $('#loginByPhone');
    loginByName.css('font-weight', 'normal');
    loginByPhone.css('font-weight', 'bold');
}

// 登录
function login() {
    if ($('#useUserName').css('display') === 'block') {
        // 用户名登录

        var pwd = $('#loginPassword').val();
        var name = $('#loginUserName').val();

        if (checkInputEmpty('loginUserName') ||
            checkInputEmpty('loginPassword')) {
            return;
        }

        if (!isPassword(pwd)) {
            errorInfo('密码格式不正确，<small>密码由 6-12 位字母和数字组成</small>');
            return;
        }

        $.post(
            '/blogger/login/way=name',
            {
                username: name,
                password: pwd
            },
            function (result) {
                if (result.code === 0) {
                    location.reload();
                } else {
                    errorInfo(result.msg);
                }
            }
        );

    } else {
        // 电话验证码登录
        // TODO
    }
}

// 为 '' 返回true
function checkInputEmpty(id) {
    var va = $('#' + id);

    if (va.val() === '') {
        errorInfo('<small>请输入&nbsp;</small>' + va.attr('placeholder'));
        return true;
    } else {
        errorInfo('');
        return false;
    }
}

function errorInfo(msg) {
    error(msg, 'loginErrorMsg');
}