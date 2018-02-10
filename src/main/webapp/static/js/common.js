function isStrEmpty(str) {
    return str == null || str === undefined || str === '';
}

function isUrl(url) {
    var regex = "((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}" +
        "\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";
    var re = url.match(regex);
    return re != null;
}

function dateFormat(date) {
    var d = new Date(date);
    return d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
}

function scrollToTop() {
    $("html,body").animate({scrollTop: 0}, 500);
}