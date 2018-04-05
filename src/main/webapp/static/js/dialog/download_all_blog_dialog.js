function switchFormat(th) {

    if (!$(th).hasClass('file-format-choosed')) {
        $('.file-format-choosed').addClass('file-format');
        $('.file-format-choosed').removeClass('file-format-choosed');

        $(th).removeClass('file-format');
        $(th).addClass('file-format-choosed');
    }
}

function beginDownload(bloggerId) {

    var format = $('.file-format-choosed > div').attr('value');

    var ref = '/blogger/' + bloggerId + '/blog/download-type=' + format;
    // $.get(ref);
    location.href = ref;

    $('#downloadAllBlogDialog').modal('hide');
}

