//图像上传
function selectImg(file) {
    if (!file.files || !file.files[0]) {
        return;
    }
    var reader = new FileReader();
    reader.onload = function (evt) {
        var replaceSrc = evt.target.result;
        //更换cropper的图片
        $('#tailoringImg').cropper('replace', replaceSrc, false);//默认false，适应高度，不失真
    };
    reader.readAsDataURL(file.files[0]);
}


//裁剪后的处理
function saveAvatar(bloggerId) {
    if ($("#tailoringImg").attr("src") == null) {
        error('还未选择图像', 'editAvatarErrorMsg', true, 2000);
        return false;
    } else {
        var cas = $('#tailoringImg').cropper('getCroppedCanvas');//获取被裁剪后的canvas
        var base64url = cas.toDataURL('image/png'); //转换为base64地址形式
        sendAvatarData(base64url, bloggerId);
    }
}

function sendAvatarData(base64url, bloggerId) {

    $.ajax({
        url: '/blogger/' + bloggerId + '/profile/avatar',
        type: 'POST',
        cache: false,
        // 修改tomcat对post请求的长度限制，默认2M，maxPostSize=-1，tomcat 6及以下版本修改为0
        data: {avatarBaseUrlData: base64url},
        dataType: "json",
        beforeSend: function () {
            disableButton(false, 'editAvatarBtn', '正在上传...', 'button-disable');
        },
        success: function (result) {
            if (result.code === 0) {
                disableButton(false, 'editAvatarBtn', '上传成功', 'button-disable');

                setTimeout(function () {
                    var imgId = result.data;
                    funAfterAvatarUpdateSuccess(imgId);

                    disableButton(true, 'editAvatarBtn', '上传', "button-disable");

                    $('#editAvatarDialog').modal('toggle');
                }, 1000);

            } else {
                error('上传失败', 'editAvatarErrorMsg', true, 2000);
                disableButton(true, 'editAvatarBtn', '上传', "button-disable");
            }
        }
    });

}

function initCropper() {
    //cropper图片裁剪
    $('#tailoringImg').cropper({
        aspectRatio: 1 / 1,//默认比例
        preview: '.preview-image',//预览视图
        guides: false,  //裁剪框的虚线(九宫格)
        autoCropArea: 0.5,  //0-1之间的数值，定义自动剪裁区域的大小，默认0.8
        movable: false, //是否允许移动图片
        dragCrop: true,  //是否允许移除当前的剪裁框，并通过拖动来新建一个剪裁框区域
        movable: true,  //是否允许移动剪裁框
        resizable: true,  //是否允许改变裁剪框的大小
        zoomable: false,  //是否允许缩放图片大小
        mouseWheelZoom: false,  //是否允许通过鼠标滚轮来缩放图片
        touchDragZoom: true,  //是否允许通过触摸移动来缩放图片
        rotatable: true,  //是否允许旋转图片
        crop: function (e) {
            // 输出结果数据裁剪图像。
        }
    });

    //旋转
    $(".cropper-rotate-btn").on("click", function () {
        $('#tailoringImg').cropper("rotate", 90);
    });

    //复位
    $(".cropper-reset-btn").on("click", function () {
        $('#tailoringImg').cropper("reset");
    });

    //换向
    var flagX = true;
    $(".cropper-scaleX-btn").on("click", function () {
        if (flagX) {
            $('#tailoringImg').cropper("scaleX", -1);
            flagX = false;
        } else {
            $('#tailoringImg').cropper("scaleX", 1);
            flagX = true;
        }
        flagX != flagX;
    });
}