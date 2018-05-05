// ------------------------------------------------------------------------------------------------------ 头像修改成功后回调回调
function funAfterAvatarUpdateSuccess(imgId) {
    var url = '/image/' + bloggerId + '/type=private/' + imgId;
    $('#blogAvatar').attr('src', url);
}


var currentShowDiv = 'divBase';

function showDiv(id, th) {

    var btn = $('.btn-item');
    for (var index in btn) {
        $(btn[index]).css('color', 'gray');
    }

    $(th).css('color', 'orangered');

    if (currentShowDiv !== id) {
        $('#' + currentShowDiv).fadeOut("fast", function () {
            $('#' + id).slideDown('fast');
            currentShowDiv = id;
        });
    }

}

function chooseManNavPos(th, pos) {
    mMainNavPos = pos;

    if ($(th).attr('id') === 'mainPageNavLeft') {
        $("#mainPageNavRight").css('border-bottom', 'solid 1px #DBE2E8');
        $('#mainPageNavLeft').css('border-bottom', 'solid 2px #09B2E3');
    } else {
        $('#mainPageNavLeft').css('border-bottom', 'solid 1px #DBE2E8');
        $('#mainPageNavRight').css('border-bottom', 'solid 2px #09B2E3');
    }

}

var mMainNavPos = mainNavPos;

var maxIntroCount = 25;
var maxAboutMeCount = 180;

function saveProfileDiv() {
    var mIntro = $('#modifyProfileIntro').val();
    var mAboutMe = $('#modifyProfileAboutMe').val();
    var btnId = 'settingBtnProfile';

    if (mIntro !== intro || mAboutMe !== aboutMe) {

        // 检查 intro 字数
        if (mIntro.length > maxIntroCount) {
            error('主页 title 字数不能超过 ' + maxIntroCount + ' 个，当前 '
                + mIntro.length + ' 字', 'settingErrorMsg', true, 3000);
            return;
        }

        // 检查 aboutMe 字数
        if (mAboutMe.length > maxAboutMeCount) {
            error('博主自述字数不能超过 ' + maxAboutMeCount + ' 个，当前 '
                + mAboutMe.length + ' 字', 'settingErrorMsg', true, 3000);
            return;
        }

        disableButton(false, btnId, '正在修改...', "button-disable");

        $.ajax({
            url: '/blogger/' + bloggerId + '/profile',
            data: {
                intro: mIntro,
                aboutMe: mAboutMe
            },
            async: false,
            type: 'put',
            success: function (result) {
                if (result.code === 0) {
                    disableButton(false, btnId, '修改成功', "button-disable");
                    toast('修改成功', 1000);
                    setTimeout(function () {
                        disableButton(true, btnId, '保存', "button-disable");
                    }, 1000);

                    intro = mIntro;
                    aboutMe = mAboutMe;
                } else {
                    error(result.msg, 'settingErrorMsg', true, 3000);
                }
            }
        });

    } else {
        error('未修改', 'settingErrorMsg', true, 3000);
    }

}

function saveBaseDiv() {
    var mBloggerName = $('#modifyBloggerName').val();
    var mEmail = $('#modifyProfileEmail').val();
    var mPhone = $('#modifyProfilePhone').val();
    var btnId = 'settingBtnBase';
    var bloggerNameModify = false;

    var editSucc = false;

    // 若用户名修改发起 Account 修改请求
    // 修改用户名需要刷新网页
    if (mBloggerName !== bloggerName) {
        disableButton(false, btnId, '正在修改...', "button-disable");

        $.ajax({
            url: '/blogger/' + bloggerId + '/item=name',
            data: {username: mBloggerName},
            async: false,
            type: 'put',
            success: function (result) {
                if (result.code === 0) {
                    bloggerName = mBloggerName;
                    bloggerNameModify = true;
                    editSucc = true;
                } else {
                    editSucc = false;
                    error(result.msg, 'settingErrorMsg', true, 3000);
                }
            }
        });
    }

    // 若 email ，phone 任一项修改，发起 Profile 修改请求
    if (mEmail !== email || mPhone !== phone) {
        disableButton(false, btnId, '正在修改...', "button-disable");

        $.ajax({
            url: '/blogger/' + bloggerId + '/profile',
            data: {
                phone: mPhone,
                email: mEmail
            },
            async: false,
            type: 'put',
            success: function (result) {
                if (result.code === 0) {
                    email = mEmail;
                    phone = mPhone;
                    editSucc = true;
                } else {
                    editSucc = false;
                    error(result.msg, 'settingErrorMsg', true, 3000);
                }
            }
        });
    }

    // 若 mMainNavPos 修改，发起 Setting 请求
    if (mMainNavPos !== mainNavPos) {
        sendReq = true;
        disableButton(false, btnId, '正在修改...', "button-disable");

        $.ajax({
            url: '/blogger/' + bloggerId + '/setting/item=mainPageNavPos',
            data: {mainPageNavPos: mMainNavPos},
            async: false,
            type: 'put',
            success: function (result) {
                if (result.code === 0) {
                    editSucc = true;
                    mainNavPos = mMainNavPos;
                } else {
                    editSucc = false;
                    error(result.msg, 'settingErrorMsg', true, 3000);
                }
            }
        });
    }

    if (editSucc) {
        disableButton(false, btnId, '修改成功', "button-disable");
        toast('修改成功', 1000);
        setTimeout(function () {
            disableButton(true, btnId, '保存', "button-disable");
            if (bloggerNameModify)
                location.href = '/' + bloggerName + '/setting';
        }, 1000);
    } else {
        error('未修改', 'settingErrorMsg', true, 3000);
    }


}


function initImportBlogListener() {

    $('#fileUploadDialog').on('hidden.bs.modal', function (e) {

        $('#progressbar').css('width', '0%');
        $('#progressbar').removeClass('active');

        $('#processStatus').html('');
        $('#importSucc').html('');
        $('#showChoosedFileName').html('');
        $('#zipFile').val('');

    });
}


function confirmExe() {

    disableButton(false, 'confirmBtn', '正在删除...', 'button-disable');

    $.ajax({
        url: '/blogger/' + bloggerId,
        async: false,
        type: 'delete',
        success: function (result) {
            if (result.code === 0) {
                disableButton(false, 'confirmBtn', '删除成功', 'button-disable');

                setTimeout(function () {
                    location.href = '/register';
                }, 1000);

            } else {
                error(result.msg, 'confirmErrorMsg', true, 3000);
                disableButton(true, 'confirmBtn', '删除', 'button-disable');
            }
        }
    });
}


// 发送短信验证码
function sendPhoneCode() {

    if (!isPassword($('#newPwd').val())) {
        error('密码格式不正确，<small>密码由 6-12 位字母和数字组成</small>', 'errorMsgOperAccount', true, 2000);
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
            content: '【BLOG】 你的验证码是: ' + phoneCode + ' ,此验证码用于重置登录密码，10分钟内有效。'
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
                error('验证码无法发送', 'errorMsgOperAccount', true, 3000);
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
    phoneCode = code + '';
}

function initDeleteAccountConfirmDialog() {
    $('#confirmText').html('确认永久删除账号');
}

function updatePwd() {
    var code = $('#phoneCode').val();
    if (isStrEmpty(code)) {
        error('请输入验证码', 'errorMsgOperAccount', true, 3000);
        return;
    }

    if (code !== phoneCode) {
        error('验证码错误', 'errorMsgOperAccount', true, 3000);
        return;
    }

    // TODO

}


$(document).ready(function () {

    // 初始化修改头像模态框
    initCropper();

    initImportBlogListener();

    initDeleteAccountConfirmDialog()
});

