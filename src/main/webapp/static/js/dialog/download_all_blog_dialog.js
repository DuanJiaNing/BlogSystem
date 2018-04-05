function switchFormat(th) {

    if ($(th).hasClass('file-format-choosed')) {
        $('.file-format').addClass('file-format-choosed');
        $('.file-format').removeClass('file-format');

        $(th).removeClass('file-format-choosed');
        $(th).addClass('file-format');
    } else {
        $('.file-format-choosed').addClass('file-format');
        $('.file-format-choosed').removeClass('file-format-choosed');

        $(th).removeClass('file-format');
        $(th).addClass('file-format-choosed');
    }
}

function beginDownload(bloggerId) {

    var format = $('.file-format-choosed > div').attr('value');

    var form = $('#downloadFile');
    form.attr('action', '/blogger/' + bloggerId + '/blog/download-type=' + format);
    form.submit();

}

