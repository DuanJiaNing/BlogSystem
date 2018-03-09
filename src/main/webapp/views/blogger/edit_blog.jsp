<%--
  Created by IntelliJ IDEA.
  User: DuanJiaNing
  Date: 2018/2/13
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/views/nav/nav_edit_blog.jsp" %>

<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <script type="application/javascript">
        var bloggerName = '${bloggerName}';
    </script>

    <c:choose>
        <c:when test="${not empty blogContentMd}">
            <title>${bloggerName}-编辑博文</title>
            <script type="application/javascript">
                // 编辑模式
                var editMode = 1;
                var blogId = ${blogId};
            </script>
        </c:when>
        <c:otherwise>
            <title>${bloggerName}-创作博文</title>
            <script type="application/javascript">
                // 创作模式
                var editMode = 2;
            </script>
        </c:otherwise>
    </c:choose>

    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <link rel="stylesheet" href="/plugin/editormd/css/editormd.min.css"/>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/blogger/edit_blog.css">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 要在最前面引入-->
    <script src="https://cdn.bootcss.com/jquery/3.3.1/core.js"></script>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

</head>
<body>
<div class="editormd" id="editormd-container">
    <textarea class="editormd-markdown-textarea" name="test-editormd-markdown-doc"
              id="editormd"></textarea>

    <!-- 第二个隐藏文本域，用来构造生成的HTML代码，方便表单POST提交，这里的name可以任意取，后台接受时以这个name键为准 -->
    <!-- html textarea 需要开启配置项 saveHTMLToTextarea == true -->
    <textarea class="editormd-html-textarea" name="editorhtml"
              id="editorHtml"></textarea>
</div>

<script type="text/javascript" src="/plugin/editormd/editormd.min.js"></script>
<script type="application/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/blogger/edit_blog.js"></script>
<script>
    $('#editormd').html("${blogContentMd}");
</script>

</body>
</html>
