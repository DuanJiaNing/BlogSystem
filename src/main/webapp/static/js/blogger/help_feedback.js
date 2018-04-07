function sendFeedback(bloggerId) {

    var adviceOrOpinion = $('#adviceOrOpinion').val();
    var contactInfo = $('#contactInfo').val();

    if (isStrEmpty(adviceOrOpinion)) {
        error('请输入问题或建议', 'sendFeedbackErrorMsg', true, 2000);
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

            } else {
                disableButton(true, 'sendFeedbackBtn', '提交', "button-disable");
                error(result.msg, 'sendFeedbackErrorMsg', true, 2000);
            }
        }
    )

}