
// 登录
/**
 * 登录
 * @param funAfterLoginSuccess 登录成功时回调，参数为result
 * @param funAfterLoginFail 登录失败时回调，参数为错误码和错误信息
 */
function login(funAfterLoginSuccess, funAfterLoginFail) {
    if ($('#useUserName').css('display') === 'block') {
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
                        funAfterLoginSuccess(result,name);
                    }, 1000);

                } else {
                    errorInfoWhenLogin(result.msg);
                    disableButton(true, 'loginBtn', '登录', "button-disable");

                    funAfterLoginFail(result);
                }
            }
        );

    } else {
        // 电话验证码登录

        if (checkInputEmptyWhenLogin('loginPhone') ||
            checkInputEmptyWhenLogin('loginPhoneCode')) {
            return;
        }

        var phone = $('#loginPhone').val();
        var inputPhoneCode = $('#loginPhoneCode').val();

        if (!isPhone(phone)) {
            errorInfoWhenLogin('电话号码不符合规范');
            return;
        }

        if (inputPhoneCode === phoneCode) {
            disableButton(false, 'loginBtn', '正在登录...', "button-disable");
            $.post(
                '/blogger/login/way=phone',
                {phone: phone},
                function (result) {
                    if (result.code === 0) {
                        disableButton(false, 'loginBtn', '登录成功', "button-disable");

                        setTimeout(function () {
                            disableButton(true, 'loginBtn', '登录', "button-disable");
                            funAfterLoginSuccess(result);
                        }, 1000);

                    } else {
                        errorInfoWhenLogin(result.msg);
                        disableButton(true, 'loginBtn', '登录', "button-disable");

                        funAfterLoginFail(result);
                    }
                }
            );
        } else {
            errorInfoWhenLogin('验证码错误');
        }

    }
}

// 发送短信验证码
function sendPhoneCode() {

    if (checkInputEmptyWhenLogin('loginPhone')) {
        return;
    }

    var phone = $('#loginPhone').val();
    if (!isPhone(phone)) {
        errorInfoWhenLogin('电话错误');
        return;
    }

    createPhoneCode();

    // 10分钟后验证码失效
    setTimeout(function () {
        phoneCode = null;
    }, 10 * 60 * 1000);

    $.post(
        '/sms',
        {
            phone: phone,
            content: '【BLOG】 你的验证码是: ' + phoneCode + ' ,此验证码用于登录 BLOG，10分钟内有效。'
        },
        function (result) {
            if (result.code === 0) {
                countDown(60, 1000, function (c) {
                    if (c === 0) {
                        return true;
                    } else {
                        disableButton(false, 'sendPhoneCodeBtn', c + ' 秒后重新发送', "button-info-disable");
                        return false;
                    }
                });
            } else {
                errorInfoWhenLogin('验证码无法发送');
            }
        }, 'json');
}

// 短信验证码
var phoneCode;

function createPhoneCode() {
    var code = '';
    for (var i = 0; i < 6; i++) {
        var n = Math.floor(Math.random() * 10);//输出1～10之间的随机整数
        code += n;
    }
    phoneCode = code;
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
