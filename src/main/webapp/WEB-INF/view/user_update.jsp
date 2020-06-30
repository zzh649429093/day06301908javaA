<%--
  Created by IntelliJ IDEA.
  User: 张智化
  Date: 2020/4/12
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="updateUser.do" method="post">
    <h1>修改员工页面</h1>
    <input type="text" value="${userBean.id}" name="id" readonly="readonly"><br>
<input type="text" name="username" value="${userBean.username}" placeholder="请输入姓名"><br>
<input type="text" name="password" value="${userBean.password}" placeholder="请输入密码"><br>
<input type="text" name="age" value="${userBean.age}" placeholder="请输入年龄"><br>
<input type="date" name="birthday" value="${userBean.birthday}" placeholder="请输入生日"><br>
<input type="text" name="deptid" value="${userBean.deptid}" placeholder="请输入生日"><br>
<input type="text" name="rid" value="${userBean.rid}" placeholder="请输入生日"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
