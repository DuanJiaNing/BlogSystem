function logout(bloggerId) {

    $.post(
        '/blogger/' + bloggerId + '/logout',
        function (result) {
            if (result.code === 0) {
                location.reload();
            } else {
                toast(result.msg, 2000);
            }
        }
    );
}
