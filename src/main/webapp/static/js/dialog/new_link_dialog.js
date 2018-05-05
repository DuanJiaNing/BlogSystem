/**
 * 创建标签
 * @param funWhenCreateLinkSuccess 创建成功时回调，回传参数为新标签id
 * @param funWhenCreateLinkFail 创建失败时回调，参数为错误码和错误信息
 */
function createLink(funWhenCreateLinkSuccess, funWhenCreateLinkFail) {

    var title = $('#linkTitle').val();
    var url = $('#linkUrl').val();
    var bewrite = $('#linkBewrite').val();

    if (isStrEmpty(title)) {
        error("名称不能为空", 'linkErrorMsg', true, 2000);
        return;
    }

    if (isStrEmpty(url) || !isUrl(url)) {
        error("url不正确", 'linkErrorMsg', true, 2000);
        return;
    }

    error("", 'linkErrorMsg', true, 2000);

    disableButton(false, 'newLinkBtn', '正在创建...', "button-disable");
    $.post(
        '/blogger/' + pageOwnerBloggerId + '/link',
        {title: title, url: url, bewrite: bewrite},
        function (result) {
            if (result.code === 0) {
                disableButton(false, 'newLinkBtn', '创建成功', "button-disable");
                funWhenCreateLinkSuccess(result.data);

                setTimeout(function () {
                    disableButton(true, 'newLinkBtn', '创建', "button-disable");
                    $('#linkTitle').val('');
                    $('#linkUrl').val('');
                    $('#linkBewrite').val('');

                    $('#newLinkDialog').modal('toggle');
                }, 1000);

            } else {
                disableButton(true, 'newLinkBtn', '创建', "button-disable");
                error(result.msg, 'linkErrorMsg', true, 3000);

                funWhenCreateLinkFail(result);
            }
        }, 'json'
    );

}
