
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
