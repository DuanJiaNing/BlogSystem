<%--
  Created by IntelliJ IDEA.
  User: DuanJiaNing
  Date: 2017/12/11
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>登录</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/blogger/login/name" method="post">
    用户名：<input type="text" name="username"><br>
    密码：<input type="text" name="password"><br>
    <input type="submit">
</form>

<h2>${errorMsg}</h2>

</body>
</html>
