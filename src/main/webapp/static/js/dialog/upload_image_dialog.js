var jcropApi;

$(document).ready(function () {

    $('#avatarPicture').Jcrop({}, function () {
        // Store the API in the jcrop_api variable
        jcropApi = this;
    });


});

function uploadImage(bloggerId) {

    $.ajax({
        url: '/image/' + bloggerId,
        type: 'POST',
        cache: false,
        data: new FormData($('#uploadAvatarImageForm')[0]),
        processData: false,
        contentType: false,
        dataType: "json",
        beforeSend: function () {
            $('#uploadMsg').html('正在上传...');
        },
        success: function (result) {
            if (result.code === 0) {
                $('#uploadMsg').html('上传成功');
                var imgId = result.data;
                var url = '/image/' + bloggerId + '/type=private/' + imgId;
                jcropApi.setImage(url);
                $('#preview').attr('src', url);

            } else {
                $('#uploadMsg').html(result.msg);
            }
        }
    });

}