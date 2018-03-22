// ------------------------------------------------------------------------------------------------------ 头像修改成功后回调回调
function funAfterAvatarUpdateSuccess(imgId) {
    var url = '/image/' + bloggerId + '/type=private/' + imgId;
    $('#blogAvatar').attr('src', url);
}


var currentShowDiv = 'divBase';

function showDiv(id) {

    if (currentShowDiv !== id) {
        $('#' + currentShowDiv).fadeOut("fast", function () {
            $('#' + id).slideDown('fast');
            currentShowDiv = id;
        });
    }


}

$(document).ready(function () {

    // 初始化修改头像模态框
    initCropper();
});
