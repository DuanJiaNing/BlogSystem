function nextStep() {
    if (checkInputAccount()) {
        $('#nextStep').on('click', null, null, function () {
            if (checkInputProfile() && $('#nextStep').attr('disable') !== 'disable') {
                $('#nextStep').css('display', 'none');
                $('#nextStep').attr('disable', 'disable');

                $('#inputAccount').css('display', 'none');
                $('#inputProfile').css('display', 'none');
                $('#inputFinish').css('display', 'block');

                $('#stepTitle3').removeClass('step-title');
                $('#stepTitle3').addClass('step-title-choose');
                $('#stepTitle3_').removeClass('step');
                $('#stepTitle3_').addClass('step-choose');

                register();
            }
        });

        $('#inputAccount').css('display', 'none');
        $('#inputProfile').css('display', 'block');
        $('#inputFinish').css('display', 'none');

        $('#stepTitle2').removeClass('step-title');
        $('#stepTitle2').addClass('step-title-choose');
        $('#stepTitle2_').removeClass('step');
        $('#stepTitle2_').addClass('step-choose');

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

function checkInputAccount() {
    if (checkInputEmpty('registerUserName') ||
        checkInputEmpty('registerPassword') ||
        checkInputEmpty('conformPassword')) {
        return false;
    }

    // 检查密码格式规范
    var pwd = $('#registerPassword').val();
    if (!isPassword(pwd)) {
        errorInfo('密码格式不正确，<small>密码由 6-12 位字母和数字组成</small>');
        return false;
    }

    // 检查两次密码是否一致
    var pwdc = $('#conformPassword').val();
    if (pwd !== pwdc) {
        errorInfo('两次密码不一致');
        return false;
    }

    // 检查用户名是否存在
    $.ajax({
        url: '/blogger/check=username?username=' + $('#registerUserName').val(),
        async: false,
        success: function (result) {
            if (result.code === 18) {
                errorInfo('用户名已被占用');
            } else {
                errorInfo('');
            }
        }
    });

    if ($('#registerErrorMsg').html() === '') return true;
    else return false;
}

function checkInputProfile() {
    if (checkInputEmpty('registerPhone') ||
        checkInputEmpty('registerEmail') ||
        checkInputEmpty('registerIntro') ||
        checkInputEmpty('registerAboutMe')) {
        return false;
    }

    // 正则校验电话
    var phone = $('#registerPhone').val();
    if (!isPhone(phone)) {
        errorInfo('电话号码格式不正确');
        return false;
    }

    // 正则校验邮箱
    if (!isEmail($('#registerEmail').val())) {
        errorInfo('邮箱格式不正确');
        return false;
    }

    return true;
}


function errorInfo(msg) {
    error(msg, 'registerErrorMsg');
}


function register() {
    var userName = $('#registerUserName').val();
    var password = $('#registerPassword').val();

    var phone = $('#registerPhone').val();
    var email = $('#registerEmail').val();
    var intro = $('#registerIntro').val();
    var aboutMe = $('#registerAboutMe').val();

    info('正在注册...');

    $.post(
        '/blogger',
        {
            username: userName,
            password: password
        },
        function (result) {

            if (result.code === 0) {
                login(result.data);
            } else {
                failInfo(result.msg);
            }

            function addProfile(id) {
                $.post(
                    '/blogger/' + id + '/profile',
                    {
                        phone: phone,
                        email: email,
                        aboutMe: aboutMe,
                        intro: intro
                    },
                    function (result) {
                        if (result.code === 0) {
                            countDown(3, 1000, function (c) {
                                if (c === 0) {
                                    location.href = '/' + userName + '/archives';
                                    return true;
                                } else {
                                    $('#finalInfo').html('<small> 注册成功，' + c + '秒后将进入</small><a>个人主页</a>');
                                    return false;
                                }
                            });
                        }
                    }, 'json'
                )
            }

            function login(id) {
                $.post(
                    '/blogger/login/way=name',
                    {
                        username: userName,
                        password: password
                    }, function (result) {
                        if (result.code === 0) {
                            addProfile(id);
                        } else {
                            failInfo(result.msg);
                        }
                    }, 'json');
            }

        }, 'json');

    function info(info) {
        $('#finalInfo').html('&nbsp;&nbsp;' + info);
    }

    function failInfo(info) {
        $('#finalInfo').html('&nbsp;&nbsp;<small>注册失败' + info + '</small>，<a href="/blogger/register">重试</a>');
    }

}

// ----------------------------- 登录对话框回调
function funAfterLoginSuccess() {
    if ($('#useUserName').css('display') === 'block') {
        // 用户名登录
        location.href = '/' + $('#loginUserName').val() + '/archives';
    } else {
        // 电话验证码登录
        // TODO
    }
}

function funAfterLoginFail(result) {
}