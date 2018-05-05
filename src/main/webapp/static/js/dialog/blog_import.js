function chooseFileChange(th) {
    $('#showChoosedFileName').html(th.files[0].name);

    $('#progressbar').css('width', '0%');
    $('#progressbar').removeClass('active');

    $('#processStatus').html('');
    $('#importSucc').html('');

}

var process = false;

function importBlog(bloggerId) {

    if (process) {
        // 正在执行上传或解析操作
        error('正在处理', 'blogImportErrorMsg', true, 2000);
        return;
    }

    var name = $('#showChoosedFileName').html();
    if (isStrEmpty_(name)) {
        error('请先选择文件', 'blogImportErrorMsg', true, 2000);
        return;
    }
    if (name.indexOf('.zip') !== name.length - 4) {
        error('文件格式不正确，请重新选择 zip 文件', 'blogImportErrorMsg', true, 3000);
        return;
    }

    $('#progressbar').addClass('active');
    $('#processStatus').html('正在上传...');
    process = true;

    //从默认的 0% -> 60% 上传时间
    var stopSuc = false;
    var stopFail = false;
    countDown(60, 20, function (count) {
        if (stopFail) {
            $('#progressbar').css('width', '0%');
            return true;
        }

        if (stopSuc) {
            $('#progressbar').css('width', '60%');
            return true;
        }
        $('#progressbar').css('width', (60 - count) + '%');
    });

    var data = new FormData();
    data.append('zipFile', $('#zipFile').prop('files')[0]);

    $.ajax({
        url: '/blogger/' + bloggerId + '/blog/patch',
        type: 'POST',
        data: data,
        cache: false,
        processData: false,
        contentType: false,
        success: function (result) {

            if (result.code === 0) {
                stopSuc = true;

                // 60% -> 100% 处理时间
                $('#processStatus').html('正在解析...');
                countDown(40, 20, function (count) {
                    if (count === 0) {
                        process = false;
                        $('#progressbar').css('width', '100%');

                        $('#progressbar').removeClass('active');

                        $('#processStatus').html('');
                        $('#showChoosedFileName').html('');
                        $('#zipFile').val('');

                        handleImportSucc(result.data);
                        return true;
                    } else {
                        $('#progressbar').css('width', (100 - count) + '%');
                    }
                });

            } else {
                process = false;
                stopFail = true;

                $('#processStatus').html('');
                $('#showChoosedFileName').html('');
                $('#zipFile').val('');

                $('#progressbar').css('width', '0%');
                $('#progressbar').removeClass('active');
                error(result.msg, 'blogImportErrorMsg', true, 3000);
            }

        }
    });
}

function handleImportSucc(data) {
    var html = '';
    if (data.length === 0 || data === '') {
        html = '<3>没有导入博文</h3>';
    } else {
        $('#processStatus').html('成功导入以下博文');

        for (var index in data) {
            var item = data[index];
            html += item.title + '<hr class="default-line" style="margin-bottom: 16px">';
        }
    }

    $('#importSucc').html(html);
}
