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
        error("名称不能为空", 'linkErrorMsg');
        return;
    }

    if (isStrEmpty(url) || !isUrl(url)) {
        error("url不正确", 'linkErrorMsg');
        return;
    }

    error("", 'linkErrorMsg');

    disableButton(false, 'newLinkBtn', '正在创建...');
    $.post(
        '/blogger/' + pageOwnerBloggerId + '/link',
        {title: title, url: url, bewrite: bewrite},
        function (result) {
            if (result.code === 0) {
                disableButton(false, 'newLinkBtn', '创建成功');
                funWhenCreateLinkSuccess(result.data);

                setTimeout(function () {
                    disableButton(true, 'newLinkBtn', '创建');
                    $('#newLinkDialog').modal('toggle');
                }, 1000);

            } else {
                disableButton(true, 'newLinkBtn', '创建');
                error(result.msg, 'linkErrorMsg');

                funWhenCreateLinkFail(result);
            }
        }, 'json'
    );

}
