function sendFeedback(bloggerId) {

    var adviceOrOpinion = $('#adviceOrOpinion').val();
    var contactInfo = $('#contactInfo').val();

    if (isStrEmpty(adviceOrOpinion)) {
        error('请输入问题或建议', 'sendFeedbackErrorMsg', true, 3000);
        return;
    }

    var data = 'content=' + adviceOrOpinion +
        (isStrEmpty_(contactInfo) ? '' : '&contact=' + contactInfo) +
        (bloggerId === undefined ? '' : '&bloggerId=' + bloggerId);

    disableButton(false, 'sendFeedbackBtn', '正在提交...', "button-disable");
    $.post(
        '/email/feedback',
        data,
        function (result) {
            if (result.code === 0) {
                disableButton(false, 'sendFeedbackBtn', '提交成功', "button-disable");

                setTimeout(function () {
                    disableButton(true, 'sendFeedbackBtn', '提交', "button-disable");

                    $('#adviceOrOpinion').val('');
                    $('#contactInfo').val('');
                }, 1000);

            } else if (result.code === 18) {
                disableButton(true, 'sendFeedbackBtn', '提交', "button-disable");
                error('发送失败', 'sendFeedbackErrorMsg', true, 3000);
            } else {
                disableButton(true, 'sendFeedbackBtn', '提交', "button-disable");
                error(result.msg, 'sendFeedbackErrorMsg', true, 3000);
            }
        }
    )

}

// ------------------------------------------------------------------------------------------------------ 登录对话框回调
function funAfterLoginSuccess(result, name) {
    location.href = '/' + name + '/archives';
}

function funAfterLoginFail(result) {
}
