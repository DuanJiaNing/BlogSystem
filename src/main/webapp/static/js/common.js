/**
 * 判断字符串是否为空
 * @param str 字符串
 * @returns {boolean} 为 null,undefined 或 ''时返回true
 */
function isStrEmpty(str) {
    return str == null || str === undefined || str === '';
}

/**
 * 判断字符串是否为空
 * @param str 字符串
 * @returns {boolean} 为 null,undefined,'null'或 ''时返回true
 */
function isStrEmpty_(str) {
    return str == null || str === undefined || str === '' || str === 'null';
}

/**
 * 判断字符串是否为url
 * @param url 字符串
 * @returns {boolean} 是返回true
 */
function isUrl(url) {
    var regex = "((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}" +
        "\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";
    var re = url.match(regex);
    return re != null;
}

/**
 * 判断字符串是否为邮箱
 * @param email 字符串
 * @returns {boolean} 是返回true
 */
function isEmail(email) {
    var emailRegex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

    var re = email.match(emailRegex);
    return re != null;
}

/**
 * 判断字符串是否为电话号码
 * @param phone 字符串
 * @returns {boolean} 是返回true
 */
function isPhone(phone) {
    var phoneRegex = ["^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0-9]))\\d{8}$", "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$"];

    var re = phone.match(phoneRegex[0]);
    var re1 = phone.match(phoneRegex[1]);
    return re != null || re1 != null;
}

/**
 * 判断字符串是否为密码（6-12 位字母和数字组成）
 * @param str 字符串
 * @returns {boolean} 是返回true
 */
function isPassword(str) {

    if (isStrEmpty(str)) return false;

    var pwdRegex = "^(?:(?=.*[A-z])(?=.*[0-9])).{6,12}$";

    var re = str.match(pwdRegex);
    return re != null;
}

/**
 * 将日期格式为 2017-02-13 的格式
 * @param date long（java）型日期
 * @returns {string} 字符串
 */
function dateFormat(date) {
    var d = new Date(date);
    return d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
}

/**
 * 将日期格式为 2017-02-13 14:11 的格式
 * @param date long（java）型日期
 * @returns {string} 字符串
 */
function dateFormat_(date) {
    var d = new Date(date);
    return d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes();
}

/**
 * 滚动到网页顶部
 */
function scrollToTop() {
    $("html,body").animate({scrollTop: 0}, 500);
}

/**
 * 获取数组中指定元素下标
 * @param array 数组
 * @param item 元素
 * @returns {*} 下标，不存在返回-1
 */
function getArrayIndex(array, item) {
    for (var index in array) {
        if (array[index] === item)
            return index;
    }

    return -1;
}

/**
 * @param count 数字，倒数起始值，大于0
 * @param cir 回调间隔（毫秒）
 * @param callback 回调函数，如果需要结束倒计数（默认倒数到0时结束），返回true，否则返回false
 */
function countDown(count, cir, callback) {
    var timer;

    var down = function () {
        if (count < 0 || callback(count)) {
            clearInterval(timer);
        } else {
            count--;
        }
    };

    timer = setInterval(down, cir);
}

/**
 * 在指定id处显示错误信息（有闪动效果）
 * @param msg 错误信息
 * @param id id
 * @param disappear 错误信息消失
 * @param disTime 错误信息显示时长
 */
function error(msg, id, disappear, disTime) {
    var dom = $('#' + id);

    dom.html(msg);
    // dom.css('background-color', 'red');
    dom.css('color', 'red');
    if (msg === '')
        dom.css('padding', '4px 0');

    dom.slideDown('fast', function () {
        if (disappear)
            setTimeout(function () {
                dom.slideUp('fast');
            }, disTime);
    });

}

function splash(msg, $dom, color, splashColor) {
    $dom.html(msg);
    $dom.css('color', splashColor);

    var s = function () {
        $dom.css('color', color);
    };

    setTimeout(s, 200);
}

/**
 * 改变按钮可用状态
 * @param enable true为可用
 * @param id 按钮id
 * @param content 按钮内容
 * @param disableClass 按钮不可用时的id
 */
function disableButton(enable, id, content, disableClass) {

    var button = $('#' + id);
    button.html(content);

    if (!enable && !button.hasClass(disableClass)) {
        button.addClass(disableClass);
    } else if (enable && button.hasClass(disableClass)) {
        button.removeClass(disableClass);
    }
}

/**
 *  反转div的显示
 * @param blockId 要显示的id
 * @param noneId 隐藏的id
 * @param boldId 指示文字粗体
 * @param normalId 指示文字普通
 * @param errorMsgId 错误id
 * @param msg 错误信息
 */
function toggleDivState(blockId, noneId, boldId, normalId, errorMsgId, msg) {
    $('#' + blockId).css('display', 'block');
    $('#' + noneId).css('display', 'none');

    $('#' + boldId).css('font-weight', 'bold');
    $('#' + normalId).css('font-weight', 'normal');

    error(msg, errorMsgId, true, 2000);
}

/**
 * 清空dom
 * @param id
 */
function clearDiv(id) {
    $('#' + id).html('');
}

/**
 * 检查dom内容是否为空
 * @param id
 * @returns {boolean} 为空返回true
 */
function checkHtmlEmpty(id) {
    if ($('#' + id).html() === '') return true;
    else return false;
}

/**
 * 将字符串转码为 16 进制(Unicode  \u1b24)
 * @param str 字符串
 * @returns {string}
 */
function stringToUnicode(str) {
    var val = "";
    for (var i = 0; i < str.length; i++) {
        val += '\\u' + str.charCodeAt(i).toString(16);
    }
    return val;
}

// 初始化所有的 tip
function initToolTip() {
    $('[data-toggle="tooltip"]').tooltip();
}