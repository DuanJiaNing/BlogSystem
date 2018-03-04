function disChooseCategory(th) {
    var did = $(th).attr('did');
    $(th).remove('span[did="' + did + '"]');
}

// 1 编辑 2 删除
selectCategoryModel = 1;

function addModifyCategoryChoose(th) {
    var dom = $('#showChoosedCategory');
    var did = $(th).attr('did');

    if ($(dom).find('span[did="' + did + '"]').length !== 0) {
        return;
    }

    // 编辑时只能选择一个
    if (selectCategoryModel === 1 && dom.html() !== '') {
        return;
    }

    var title = $(th).html();
    dom.html(dom.html() + '<span did="' + did + '" class="modify-label-choosed" ' +
        'onclick="disChooseCategory(this)">' + title + ' <span style="opacity: 0.5">x</span></span>');
}