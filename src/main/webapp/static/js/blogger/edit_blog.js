function initEditormd() {

    var editormdContainer = $(function () {
        editormd("editormd-container", {
            width: "100%",
            height: 680,
            //markdown : md,
            codeFold: true,
            syncScrolling: "single",
            //你的lib目录的路径
            path: "/plugin/editormd/lib/",
            imageUpload: true,//关闭图片上传功能
            /*  theme: "dark",//工具栏主题
             previewTheme: "dark",//预览主题
             editorTheme: "pastel-on-dark",//编辑主题 */
            emoji: true,
            taskList: true,
            tocm: true,         // Using [TOCM]
            tex: true,                   // 开启科学公式TeX语言支持，默认关闭
            flowChart: true,             // 开启流程图支持，默认关闭
            sequenceDiagram: true,       // 开启时序/序列图支持，默认关闭,
            //这个配置在simple.html中并没有，但是为了能够提交表单，使用这个配置可以让构造出来的HTML代码直接在第二个隐藏的textarea域中，方便post提交表单。
            saveHTMLToTextarea: true,
            toolbarIcons: function () {
                // return editormd.toolbarModes['simple']; // full, simple, mini
                // Using "||" set icons align right.
                return [
                    "undo", "redo",
                    "|", "bold", "del", "italic", "quote", "ucwords", "uppercase", "lowercase",
                    "|", "h1", "h2", "h3", "h4", "h5", "h6",
                    "|", "list-ul", "list-ol", "hr",
                    "|", "link", "reference-link", "image", "code", "preformatted-text", "code-block", "table", "datetime", "emoji", "html-entities", "pagebreak",
                    "|", "watch", "preview", "clear", "search",
                    "|", "info"];
            }
        });

    });

}

$(document).ready(function () {
    initEditormd();

    loadLabel();
    loadCategory();
});


function loadLabel() {
    $.get(
        '/blogger/' + bloggerId + '/label',
        {offset: 0, rows: 20},
        function (result) {
            if (result.code === 0) {
                var array = result.data;
                // 填充立即发布对话框数据
                setLabel(array);
            }
        }, 'json'
    )
}

function loadCategory() {
    $.get(
        '/blogger/' + bloggerId + '/category',
        {offset: 0, rows: 1000},
        function (result) {
            if (result.code === 0) {
                var array = result.data;
                // 填充立即发布对话框数据
                setCategory(array);
            }
        }, 'json'
    )
}

var funAfterReleaseBlogSuccess = function (title) {
    // 将编辑页面替换为发布文章的阅读页面
    location.href = '/' + bloggerName + '/blog/' + title;
};

