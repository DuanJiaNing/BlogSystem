<%@ page pageEncoding="utf-8" %>
<html>
<head>
    <%--<meta http-equiv="content-type" content="text/html; charset=UTF-8">--%>
    <%--<meta charset="UTF-8">--%>

    <script type="text/javascript">

        function onSubmit() {
            var method = document.getElementById('method').value;
            var action = document.getElementById('action').value;
            var form = document.getElementById('form');
            form.method = method;
            form.action = action;
            form.submit();
        }

    </script>

</head>
<body>
<h2>Hello World!</h2>
<ul>
    <li>method:<input type="text" id="method" value="post"></li>
    <li>action:<input type="text" id="action" value="http://localhost:8080/blogger/1/blog"></li>
</ul>
<hr>

<form id="form">
    <ol>
        <li>cids:<input type="text" name="cids"></li>
        <li>lids:<input type="text" name="lids"></li>
        <li>title:<input type="text" name="title"></li>
        <li>content:<input type="text" name="content"/></li>
        <li>summary:<input type="text" name="summary"></li>
        <li>keyWords:<input type="text" name="keyWords"></li>
    </ol>
    <input type="button" onclick="onSubmit()" value="提交">
</form>

</body>
</html>
