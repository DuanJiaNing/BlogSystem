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

    var title = $(th).html();
    var span = '<span did="' + did + '" class="modify-item-choosed" ' +
        'onclick="disChooseLabel(this)">' + title + ' &nbsp;x</span>';
    if (selectLabelModel === 1 && dom.html() !== '') {
        // 编辑时只能选择一个
        dom.html(span);
    } else dom.html(dom.html() + span);
}

function exeLabelUpdate(th, bloggerId, funWhenEditLabelSuccess) {
    if (checkHtmlEmpty('showChoosedLabel')) {
        error('请选择标签', 'modifyLabelErrorMsg', true, 2000)
        return;
    }

    var newName = $('#chooseEditLabel > div > input').val();
    if (newName === '') {
        error('请输入新的标签名', 'modifyLabelErrorMsg', true, 2000);
        return;
    }

    var labelId = $('#showChoosedLabel > span').attr('did');
    $.ajax({
        url: '/blogger/' + bloggerId + '/label/' + labelId,
        data: {title: newName},
        type: 'put',
        success: function (result) {
            if (result.code === 0) {
                disableButton(false, 'modifyEditLabelBtn', '修改成功', "button-disable");

                setTimeout(function () {
                    disableButton(true, 'modifyEditLabelBtn', '提交', "button-disable");

                    funWhenEditLabelSuccess();
                    $('#modifyLabelDialog').modal('hide');

                    clearDiv('showChoosedLabel');
                    $('#chooseEditLabel > div > input').val('');
                }, 1000);

            } else {
                error(result.msg, 'modifyLabelErrorMsg', false, 3000);
            }
        }
    });

}

function exeLabelDelete(th, bloggerId, funWhenDeleteLabelSuccess) {
    if (checkHtmlEmpty('showChoosedLabel')) {
        error('请选择标签', 'modifyLabelErrorMsg', true, 2000)
        return;
    }

    var doms = $('#showChoosedLabel > span');

    disableButton(false, 'modifyDeleteLabelBtn', '正在删除...', "button-disable");
    var i = 0;
    var fail = false;
    var msg = '';
    for (; i < doms.length; i++) {
        var id = $(doms[i]).attr('did');

        $.ajax({
            url: '/blogger/' + bloggerId + '/label/' + id,
            async: false,
            type: 'delete',
            success: function (result) {
                if (result.code !== 0) {
                    fail = true;
                    msg = result.msg;
                }
            }
        });

        if (fail) {
            error(msg, 'modifyLabelErrorMsg', true, 3000);
            return;
        }
    }

    disableButton(false, 'modifyDeleteLabelBtn', '删除成功', "button-disable");
    setTimeout(function () {
        disableButton(true, 'modifyDeleteLabelBtn', '删除', "button-disable");

        funWhenDeleteLabelSuccess();
        $('#modifyLabelDialog').modal('hide');

        $('#chooseEditLabel > div > input').val('');
        clearDiv('showChoosedLabel');
    }, 1000);

}