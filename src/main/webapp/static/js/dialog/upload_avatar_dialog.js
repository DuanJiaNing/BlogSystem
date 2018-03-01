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

//裁剪后的处理
function saveAvatar(bloggerId) {
    if ($("#tailoringImg").attr("src") == null) {
        error('还未选择图像', 'editAvatarErrorMsg', true);
        return false;
    } else {
        var cas = $('#tailoringImg').cropper('getCroppedCanvas');//获取被裁剪后的canvas
        var base64url = cas.toDataURL('image/png'); //转换为base64地址形式
        sendAvatarData(base64url, bloggerId);
    }
}

function sendAvatarData(base64url, bloggerId) {

    // 去除 data URL 中的头部说明
    var img64 = base64url.replace(/^data:image\/(png|jpg);base64,/, "");

    // 使用函数 atob 将字符串形式的内容转为二进制形式的数据
    var binaryImg = atob(img64);

    // 创建 ArrayBuffer 并使用 Uint8 的方式给它赋值（ArrayBuffer 的使用方式有点怪异）
    var arrayBuffer = new ArrayBuffer(binaryImg.length);
    var uint8 = new Uint8Array(arrayBuffer);
    for (var i = 0; i < length; i++) {
        uint8[i] = binaryImg.charCodeAt(i);
    }

    // 使用 ArrayBuffer 对象生成  Blob
    // var blob = new Blob([arrayBuffer], {type: 'image/png'});
    var blob = new Blob([arrayBuffer]);

    // 构造 Formdata，准备上传 Blob
    var form = new FormData();

    // 将 Blob 对象加入 form data 中，注意属性的名称与 server 端的变量名称一致
    // 二进制数据文件名必须标明为图片类型(后端要求)
    form.append("image", blob, 'blogger-' + bloggerId + '-avatar.png');

    $.ajax({
        url: '/image/' + bloggerId,
        type: 'POST',
        cache: false,
        data: form,
        processData: false,
        contentType: false,
        dataType: "json",
        beforeSend: function () {
            disableButton(false, 'editAvatarBtn', '正在上传...', 'button-disable');
        },
        success: function (result) {
            if (result.code === 0) {
                disableButton(false, 'editAvatarBtn', '上传成功', 'button-disable');

                setTimeout(function () {
                    var imgId = result.data;
                    var url = '/image/' + bloggerId + '/type=private/' + imgId;
                    $('#bloggerAvatar').attr('src', url);

                    disableButton(true, 'editAvatarBtn', '上传', "button-disable");
                    $('#tailoringImg').attr('src', '');
                    $('.preview-image').html('');

                    $('#editAvatarDialog').modal('toggle');
                }, 1000);

            } else {
                error('上传失败', 'editAvatarErrorMsg', true);
                disableButton(true, 'editAvatarBtn', '上传', "button-disable");
            }
        }
    });

}
