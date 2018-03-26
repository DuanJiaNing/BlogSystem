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

}


$(document).ready(function () {

    // 初始化修改头像模态框
    initCropper();
});
