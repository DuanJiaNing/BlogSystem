function disChooseLabel(th) {
    var did = $(th).attr('did');
    $(th).remove('span[did="' + did + '"]');
}

// 1 编辑 2 删除
selectLabelModel = 1;

function addModifyLabelChoose(th) {
    var dom = $('#showChoosedLabel');
    var did = $(th).attr('did');

    if ($(dom).find('span[did="' + did + '"]').length !== 0) {
        return;
    }

    // 编辑时只能选择一个
    if (selectLabelModel === 1 && dom.html() !== '') {
        return;
    }

    var title = $(th).html();
    dom.html(dom.html() + '<span did="' + did + '" class="modify-label-choosed" ' +
        'onclick="disChooseLabel(this)">' + title + ' <span style="opacity: 0.5">x</span></span>');
}

function exeLabelUpdate(th, bloggerId, funWhenEditLabelSuccess) {
    if (checkHtmlEmpty('showChoosedLabel')) {
        error('请选择标签', 'modifyLabelErrorMsg', true)
        return;
    }

    var newName = $('#chooseEditLabel > div > input').val();
    if (newName === '') {
        error('请输入新的标签名', 'modifyLabelErrorMsg', true);
        return;
    }

    var labelId = $('#showChoosedLabel > span').attr('did');
    $.ajax({
        url: '/blogger/' + bloggerId + '/label/' + labelId,
        data: {title: newName},
        type: 'put',
        success: function (result) {
            if (result.code === 0) {
                disableButton(false, 'modifyLabelBtn', '修改成功', "button-disable");

                setTimeout(function () {
                    disableButton(true, 'modifyLabelBtn', '提交', "button-disable");
                    funWhenEditLabelSuccess();

                    $('#modifyLabelDialog').modal('hide');
                    $('#chooseEditLabel > div > input').val('');
                    clearDiv('showChoosedLabel');
                }, 1000);

            } else {
                error(result.msg, 'modifyLabelErrorMsg', false);
            }
        }
    });

}