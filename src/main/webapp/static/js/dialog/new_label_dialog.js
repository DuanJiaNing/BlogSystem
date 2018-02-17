/**
 * 创建标签
 * @param funWhenCreateLabelSuccess 创建成功时回调，回传参数为新标签id
 * @param funWhenCreateLabelFail 创建失败时回调，参数为错误码和错误信息
 */
function createLabel(funWhenCreateLabelSuccess, funWhenCreateLabelFail) {
    var name = $('#labelName').val();

    // 需要common.js
    if (isStrEmpty(name)) {
        error('标签名称不能为空', 'labelErrorMsg');
        return;
    } else {
        $('#labelErrorMsg').html(' ');
    }

    disableButton(false, 'newLabelBtn', '正在创建...');
    $.post(
        '/blogger/' + pageOwnerBloggerId + '/label',
        {title: name},
        function (result) {
            if (result.code === 0) {
                disableButton(false, 'newLabelBtn', '创建成功');
                funWhenCreateLabelSuccess(result.data);

                setTimeout(function () {
                    disableButton(true, 'newLabelBtn', '创建');
                    $('#newLabelDialog').modal('toggle');
                }, 1000);

            } else {
                disableButton(true, 'newLabelBtn', '创建');
                error(result.msg, 'labelErrorMsg');

                funWhenCreateLabelFail(result);
            }
        }, 'json'
    );
}
