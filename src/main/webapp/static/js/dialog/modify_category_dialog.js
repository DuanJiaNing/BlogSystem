function disChooseCategory(th) {
    var did = $(th).attr('did');
    $(th).remove('span[did="' + did + '"]');
}

// 1 编辑 2 删除
selectCategoryModel = 1;

function addModifyCategoryChoose(th) {
    var dom = $('#showChoosedCategory');
    var did = $(th).attr('did');

    var bewrite = $(th).attr('bewrite');
    var bewriteHtml = '';
    if (!isStrEmpty_(bewrite))
        bewriteHtml += '<small>' + bewrite + '</small>';
    else bewriteHtml += '无';
    $('#showChoosedCategoryBewrite').html(bewriteHtml);

    if ($(dom).find('span[did="' + did + '"]').length !== 0) {
        return;
    }

    var title = $(th).html();
    var span = '<span did="' + did + '" class="modify-item-choosed" ' +
        'onclick="disChooseCategory(this)">' + title + ' &nbsp;x</span>';

    if (selectCategoryModel === 1 && dom.html() !== '') {
        // 编辑时只能选择一个
        dom.html(span);
    } else dom.html(dom.html() + span);
}

function exeCategoryUpdate(th, bloggerId, funWhenEditCategorySuccess) {
    if (checkHtmlEmpty('showChoosedCategory')) {
        error('请选择类别', 'modifyCategoryErrorMsg', true, 2000)
        return;
    }

    var newTitle = $('#editCategoryTitle').val();
    var newBewrite = $('#editCategoryBewrite').val();
    if (isStrEmpty(newTitle) && isStrEmpty(newBewrite)) {
        error('请至少更新标题和说明其中之一', 'modifyCategoryErrorMsg', true, 2000);
        return;
    }

    var data;
    if (!isStrEmpty(newTitle) && !isStrEmpty(newBewrite)) {
        data = {
            title: newTitle,
            bewrite: newBewrite
        };
    } else if (!isStrEmpty(newTitle)) {
        data = {
            title: newTitle
        };
    } else {
        data = {
            bewrite: newBewrite
        };
    }

    var id = $('#showChoosedCategory > span').attr('did');
    $.ajax({
        url: '/blogger/' + bloggerId + '/category/' + id,
        data: data,
        type: 'put',
        success: function (result) {
            if (result.code === 0) {
                disableButton(false, 'modifyEditCategoryBtn', '修改成功', "button-disable");

                setTimeout(function () {
                    disableButton(true, 'modifyEditCategoryBtn', '提交', "button-disable");

                    funWhenEditCategorySuccess();

                    $('#modifyCategoryDialog').modal('hide');

                    $('#editCategoryTitle').val('');
                    $('#editCategoryBewrite').val('');
                    clearDiv('showChoosedCategoryBewrite');
                    clearDiv('showChoosedCategory');
                }, 1000);

            } else {
                error(result.msg, 'modifyCategoryErrorMsg', false, 3000);
            }
        }
    });

}

function exeCategoryDelete(th, bloggerId, funWhenDeleteCategorySuccess) {
    if (checkHtmlEmpty('showChoosedCategory')) {
        error('请选择类别', 'modifyCategoryErrorMsg', true, 2000)
        return;
    }

    var doms = $('#showChoosedCategory > span');

    disableButton(false, 'modifyDeleteCategoryBtn', '正在删除...', "button-disable");
    var i = 0;
    var fail = false;
    var msg = '';
    for (; i < doms.length; i++) {
        var id = $(doms[i]).attr('did');

        $.ajax({
            url: '/blogger/' + bloggerId + '/category/' + id,
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
            error(msg, 'modifyCategoryErrorMsg', true, 2000);
            return;
        }
    }

    disableButton(false, 'modifyDeleteCategoryBtn', '删除成功', "button-disable");
    setTimeout(function () {
        disableButton(true, 'modifyDeleteCategoryBtn', '删除', "button-disable");
        funWhenDeleteCategorySuccess();

        $('#modifyCategoryDialog').modal('hide');

        $('#editCategoryTitle').val('');
        $('#editCategoryBewrite').val('');
        clearDiv('showChoosedCategoryBewrite');
        clearDiv('showChoosedCategory');
    }, 1000);

}