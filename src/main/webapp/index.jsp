<%@ page pageEncoding="utf-8" %>
<html>
<head>
    <%--<meta http-equiv="content-type" content="text/html; charset=UTF-8">--%>
    <%--<meta charset="UTF-8">--%>
</head>
<body>
<h2>Hello World!</h2>

<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/image/2" method="post">
    <input type="file" name="image"><br>
    category:<input type="number" name="category" value="1"><br>
    bewrite:<input type="text" name="bewrite"><br>
    title:<input type="text" name="title"><br>
    <input type="submit">
</form>

</body>
</html>
