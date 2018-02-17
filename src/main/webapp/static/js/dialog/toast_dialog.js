/**
 * 显示信息提示框
 * @param text 提示信息
 * @param time 显示时间，到时间后自动消失，不设置时不自动消失
 */
function toast(text, time) {
    $('#toastText').html(text);
    $('#toastDialog').modal('show');

    if (time !== undefined) {
        setTimeout(function () {
            $('#toastText').html('');
            $('#toastDialog').modal('hide');
        }, time);
    }

}