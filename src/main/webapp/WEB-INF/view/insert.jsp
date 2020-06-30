<%--
  Created by IntelliJ IDEA.
  User: 张智化
  Date: 2020/4/12
  Time: 9:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="addUser.do" method="post">
        <h1>员工添加页面</h1>
<input type="text" name="username" placeholder="请输入姓名"><br>
<input type="text" name="password" placeholder="请输入密码"><br>
<input type="text" name="age" placeholder="请输入年龄"><br>
<input type="text" name="birthday" placeholder="请输入生日"><br>
<input type="text" name="deptid" placeholder="请输入部门id"><br>
<input type="text" name="rid" placeholder="请输入员工id"><br>
<input type="submit" value="提交">
</form>
</body>
</html>
