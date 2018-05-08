function logout(bloggerId, bloggerName) {

    $.post(
        '/blogger/' + bloggerId + '/logout',
        function (result) {
            if (result.code === 0) {
                location.href = '/' + bloggerName + '/archives';
            } else {
                toast(result.msg, 2000);
            }
        }
    );
}

function gotoRegister() {
    window.location = '/login';
}