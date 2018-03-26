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

    if ($(th).attr('id') === 'mainPageNavLeft') {
        $("#mainPageNavRight").css('border-bottom', 'solid 1px #DBE2E8');
        $('#mainPageNavLeft').css('border-bottom', 'solid 2px #09B2E3');
    } else {
        $('#mainPageNavLeft').css('border-bottom', 'solid 1px #DBE2E8');
        $('#mainPageNavRight').css('border-bottom', 'solid 2px #09B2E3');
    }

}

function saveBaseDiv(th) {
    var modifyBloggerName = $('#modifyBloggerName').val();
    var modifyProfileEmail = $('#modifyProfileEmail').val();
    var modifyProfilePhone = $('#modifyProfilePhone').val();



}


$(document).ready(function () {

    // 初始化修改头像模态框
    initCropper();
});
