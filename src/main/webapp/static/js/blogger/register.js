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
        error('<small>请输入&nbsp;</small>' + va.attr('placeholder'));
        return true;
    } else {
        error('');
        return false;
    }
}

var pwdRegex = "^(?:(?=.*[A-z])(?=.*[0-9])).{6,12}$";
var emailRegex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
var phoneRegex = ["^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0-9]))\\d{8}$", "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$"];

function checkInputAccount() {
    if (checkInputEmpty('registerUserName') ||
        checkInputEmpty('registerPassword') ||
        checkInputEmpty('conformPassword')) {
        return false;
    }

    // 检查密码格式规范
    var pwd = $('#registerPassword').val();
    if (pwd.match(pwdRegex) == null) {
        error('密码格式不正确，<small>密码由 6-12 位字母和数字组成</small>');
        return false;
    }

    // 检查两次密码是否一致
    var pwdc = $('#conformPassword').val();
    if (pwd !== pwdc) {
        error('两次密码不一致');
        return false;
    }

    // 检查用户名是否存在
    $.ajax({
        url: '/blogger/check=username?username=' + $('#registerUserName').val(),
        async: false,
        success: function (result) {
            if (result.code === 18) {
                error('用户名已被占用');
            } else {
                error('');
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
    if (phone.match(phoneRegex[0]) == null && phone.match(phoneRegex[1]) == null) {
        error('电话号码格式不正确')
        return false;
    }

    // 正则校验邮箱
    if ($('#registerEmail').val().match(emailRegex) == null) {
        error('邮箱格式不正确');
        return false;
    }

    return true;
}


function error(msg) {
    var dom = $('#registerErrorMsg');
    dom.html(msg);
    dom.css('background-color', 'red');
    dom.css('color', 'white');

    var s = function () {
        dom.css('background-color', 'transparent');
        dom.css('color', 'red');
    };
    setTimeout(s, 200);
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

            function jump() {
                location.href = '/' + userName + '/archives';
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
                            $('#finalInfo').html('<small> 注册成功，3秒后将进入</small><a>个人主页</a>');
                            setTimeout(jump, 3000);
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
