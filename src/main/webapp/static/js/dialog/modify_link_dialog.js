function disChooseLink(th) {
    var did = $(th).attr('did');
    $(th).remove('span[did="' + did + '"]');
}

// 1 编辑 2 删除
selectLinkModel = 1;

function addModifyLinkChoose(th) {
    var dom = $('#showChoosedLink');
    var did = $(th).attr('did');

    if ($(dom).find('span[did="' + did + '"]').length !== 0) {
        return;
    }

    // 编辑时只能选择一个
    if (selectLinkModel === 1 && dom.html() !== '') {
        return;
    }

    var title = $(th).html();
    dom.html(dom.html() + '<span did="' + did + '" class="modify-label-choosed" ' +
        'onclick="disChooseLink(this)">' + title + ' <span style="opacity: 0.5">x</span></span>');
}