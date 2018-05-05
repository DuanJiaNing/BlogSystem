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
function checkInputEmptyWhenRegister(id) {
    var va = $('#' + id);

    if (va.val() === '') {
        errorInfoWhenRegister('<small>请输入&nbsp;</small>' + va.attr('placeholder'));
        return true;
    } else {
        errorInfoWhenRegister('');
        return false;
    }
}

function checkInputAccount() {
    if (checkInputEmptyWhenRegister('registerUserName') ||
        checkInputEmptyWhenRegister('registerPassword') ||
        checkInputEmptyWhenRegister('conformPassword')) {
        return false;
    }

    // 检查密码格式规范
    var pwd = $('#registerPassword').val();
    if (!isPassword(pwd)) {
        errorInfoWhenRegister('密码格式不正确，<small>密码由 6-12 位字母和数字组成</small>');
        return false;
    }

    // 检查两次密码是否一致
    var pwdc = $('#conformPassword').val();
    if (pwd !== pwdc) {
        errorInfoWhenRegister('两次密码不一致');
        return false;
    }

    // 检查用户名是否存在
    $.ajax({
        url: '/blogger/check=username?username=' + $('#registerUserName').val(),
        async: false,
        success: function (result) {
            if (result.code === 18) {
                errorInfoWhenRegister('用户名已被占用');
            } else {
                errorInfoWhenRegister('');
            }
        }
    });

    if ($('#registerErrorMsg').html() === '') return true;
    else return false;
}

var maxIntroCount = 25;
var maxAboutMeCount = 180;

function checkInputProfile() {
    if (checkInputEmptyWhenRegister('registerPhone') ||
        checkInputEmptyWhenRegister('registerEmail') ||
        checkInputEmptyWhenRegister('registerIntro') ||
        checkInputEmptyWhenRegister('registerAboutMe')) {
        return false;
    }

    // 检查 intro 字数
    var intro = $('#registerIntro').val();
    if (intro.length > maxIntroCount) {
        error('博客标题字数不能超过 ' + maxIntroCount + ' 个，当前 '
            + intro.length + ' 字', 'registerErrorMsg', true, 3000);
        return false;
    }

    // 检查 aboutMe 字数
    var aboutMe = $('#registerAboutMe').val();
    if (aboutMe.length > maxAboutMeCount) {
        error('博主自述字数不能超过 ' + maxAboutMeCount + ' 个，当前 '
            + aboutMe.length + ' 字', 'registerErrorMsg', true, 3000);
        return false;
    }

    // 正则校验电话
    var phone = $('#registerPhone').val();
    if (!isPhone(phone)) {
        errorInfoWhenRegister('电话号码格式不正确');
        return false;
    }

    // 正则校验邮箱
    if (!isEmail($('#registerEmail').val())) {
        errorInfoWhenRegister('邮箱格式不正确');
        return false;
    }

    //检查电话号码重复
    $.ajax({
        url: '/blogger/check=phone?phone=' + phone,
        async: false,
        success: function (result) {
            if (result.code === 18) {
                errorInfoWhenRegister('该手机号已经被注册');
            } else {
                errorInfoWhenRegister('');
            }
        }
    });

    if ($('#registerErrorMsg').html() === '') return true;
    else return false;
}


function errorInfoWhenRegister(msg) {
    error(msg, 'registerErrorMsg', true, 3000);
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
                                    info('<small> 注册成功，' + c + '秒后将进入</small><a>个人主页</a>');
                                    return false;
                                }
                            });
                        } else {
                            failInfo(result.msg);
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
        $('#finalInfo').html('&nbsp;&nbsp;<small>注册失败，' + info + '</small>，<a href="/blogger/register">重试</a>');
    }

}

// ----------------------------- 登录对话框回调
function funAfterLoginSuccess(result, name) {
    location.href = '/' + name + '/archives';
}

function funAfterLoginFail(result) {
}
