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
                    intro = mIntro;
                    aboutMe = mAboutMe;
                } else {
                    error(result.msg, 'settingErrorMsg', true, 2000);
                }
            }
        });

        disableButton(false, btnId, '修改成功', "button-disable");
        toast('修改成功', 1000);
        setTimeout(function () {
            disableButton(true, btnId, '保存', "button-disable");
        }, 1000);
    } else {
        error('未修改', 'settingErrorMsg', true, 1000);
    }

}

function saveBaseDiv() {
    var mBloggerName = $('#modifyBloggerName').val();
    var mEmail = $('#modifyProfileEmail').val();
    var mPhone = $('#modifyProfilePhone').val();
    var btnId = 'settingBtnBase';
    var sendReq = false, bloggerNameModify = false;

    // 若用户名修改发起 Account 修改请求
    // 修改用户名需要刷新网页
    if (mBloggerName !== bloggerName) {
        sendReq = true;
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
                } else {
                    error(result.msg, 'settingErrorMsg', true, 2000);
                }
            }
        });
    }

    // 若 email ，phone 任一项修改，发起 Profile 修改请求
    if (mEmail !== email || mPhone !== phone) {
        sendReq = true;
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
                } else {
                    error(result.msg, 'settingErrorMsg', true, 2000);
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
                    mainNavPos = mMainNavPos;
                } else {
                    error(result.msg, 'settingErrorMsg', true, 2000);
                }
            }
        });
    }

    if (sendReq) {
        disableButton(false, btnId, '修改成功', "button-disable");
        toast('修改成功', 1000);
        setTimeout(function () {
            disableButton(true, btnId, '保存', "button-disable");
            if (bloggerNameModify)
                location.href = '/' + bloggerName + '/setting';
        }, 1000);
    } else {
        error('未修改', 'settingErrorMsg', true, 1000);
    }

}


$(document).ready(function () {

    // 初始化修改头像模态框
    initCropper();
});
