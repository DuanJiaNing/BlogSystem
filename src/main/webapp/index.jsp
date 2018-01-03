<html>

<head>
    <%--<meta http-equiv="content-type" content="text/html; charset=UTF-8">--%>
    <meta charset="UTF-8">
</head>
<body>
<h2>Hello World!</h2>

<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/image/1" method="post">
    <input type="file" name="image">
    <input type="number" name="category" value="1">
    <input type="submit">
</form>

</body>
</html>
