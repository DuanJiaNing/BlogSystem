function chooseFileChange(th) {
    $('#showChoosedFileName').html(th.files[0].name);
}

var process = false;

function importBlog() {

    if (process) {
        error('正在处理', 'blogImportErrorMsg', true, 1000);
        return;
    }

    var name = $('#showChoosedFileName').html();
    if (isStrEmpty_(name)) {
        error('请先选择文件', 'blogImportErrorMsg', true, 1000);
        return false;
    }
    if (name.indexOf('.zip') !== name.length - 4) {
        error('文件格式不正确，请重新选择 zip 文件', 'blogImportErrorMsg', true, 3000);
        return false;
    }

    $('#progressbar').addClass('active');
    $('#processStatus').html('正在上传...');
    process = true;
    //从默认的 1% -> 60% 上传时间
    countDown(59, 20, function (count) {
        $('#progressbar').css('width', (60 - count) + '%');
    });

    $("#patchImportForm").submit(function(message){
        alert(message);
    });

    // 60% -> 100% 处理时间

    process = false;
    $('#progressbar').removeClass('active');
    $('#processStatus').html('导入成功');

    return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}