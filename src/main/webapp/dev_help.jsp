<%@ page pageEncoding="utf-8" %>
<html>
<head>

    <script src="https://cdn.bootcss.com/jquery/3.3.1/core.js"></script>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>

    <style>
        .step {
            padding: 2%;
            border: solid 1px gray;
            border-radius: 5px;
            margin-top: 16px;
            margin-bottom: 32px;
            background-color: white;
        }

        li {
            margin-top: 5px;
        }
    </style>
</head>
<body style="padding: 5% 10%;background-color: rgba(49,255,233,0.04)">
<h1>BlogSystem&nbsp;系统运行搭建前必看</h1>

<div class="step">
    <h2>Step 1：添加博主默认头像</h2>
    <ol>
        <li>
            测试系统前需前往系统&nbsp;<b><a href="/register">注册页面</a></b>&nbsp;注册一位博主，<b>确保数据库blogger_account表中其对应的主键id为1</b>（
            conf.properties中配置了默认的图片管理员为id为1的博主）
        </li>
        <li>
            id 为 1 的博主<b>登录</b>
        </li>
        <li>
            在下方&nbsp;<b>上传博主默认头像</b>&nbsp;栏中上传博主默认头像
        </li>
        <li>
            可以前往主页访问了:&nbsp;
            <span style="color: whitesmoke;background-color: #00b4a5">http://.../<b>博主用户名</b>/archives</span>
        </li>
    </ol>

    <br>
    <h3>登录</h3>
    <hr>
    <form action="/blogger/login/way=name" method="post">
        <table>
            <tr>
                <td>用户名</td>
                <td>
                    <input type="text" name="username">
                </td>
            </tr>
            <tr>
                <td>密码</td>
                <td>
                    <input type="text" name="password">
                </td>
            </tr>
        </table>

        <br>
        <input type="submit" value="登录">
    </form>

    <br>
    <h3>上传博主默认头像</h3>
    <hr>
    <form enctype="multipart/form-data" action="/image/1" method="post">
        <input type="file" name="image" accept="image/*">
        <input type="hidden" name="category" value="13">
        <br>
        <br>
        <input type="submit" value="上传">
    </form>
</div>


<div class="step">
    <h2>Step 2：enjoy it</h2>
    <br>
    <input type="text" id="username">&nbsp;&nbsp;
    <button value="输入用户名" onclick="location.href='/'+$('#username').val()+'/archives'">前往主页</button>

</div>

</body>
</html>
