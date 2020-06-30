<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/8
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="../../js/calendarTime.js"></script>
    <script type="text/javascript">

        //添加
        function insertUser(){
            location.href="<%=request.getContextPath()%>/toinsert.do";
        }

        //修改
        function upda(id) {
            location.href="<%=request.getContextPath()%>/toupdate.do?id="+id;
        }

        //删除
        function deleteId(id) {
            location.href="<%=request.getContextPath()%>/deleteuser.do?id="+id;
        }

        //根据id查询权限
        function queryAll(id) {
            location.href="<%=request.getContextPath()%>/queryAll.do?id="+id;
        }

    </script>
</head>
<body>
        <table>
            <tr>
                <td colspan="10">
                    <form action="getUserList.do" method="post">
                    按照用户名：<input type="text" name="username">
                    <input type="submit" value="查询">
                    按照年龄：<input type="text" name="sbirthday" onclick="setDay(this)">--至--
                    <input type="text" name="ebirthday" onclick="setDay(this)">
                    <input type="submit" value="查询">
                    <input type="button" value="添加员工" onclick="insertUser()">
                    </form>
                </td>
            </tr>
            <tr>
                <th>编码</th>
                <th>姓名</th>
                <th>密码</th>
                <th>年龄</th>
                <th>生日</th>
                <th>部门</th>
                <th>角色</th>
                <th>操作</th>
            </tr>
            <c:forEach var="u" items="${list}">
                <tr>
                    <td>
                        ${u.id}
                    </td>
                    <td>
                            ${u.username}
                    </td>
                    <td>
                            ${u.password}
                    </td>
                    <td>
                            ${u.age}
                    </td>
                    <td>
                        <fmt:formatDate value="${u.birthday}" pattern="yyyy-MM-dd"></fmt:formatDate>
                    </td>
                    <td>
                            ${u.deptBean.dname}
                    </td>
                    <td>
                            ${u.roleBean.rname}
                    </td>
                    <td>
                        <a href="toUserDeptRole.do?id=${u.id}"><button>给员工分配部门和角色</button></a>
                        <input type="button" value="查看权限" onclick="queryAll(${u.id })">
                        <input type="button" onclick="deleteId(${u.id })" value="删除">
                        <input type="button" onclick="upda(${u.id })" value="修改">
                    </td>
                </tr>
            </c:forEach>
        </table>
        <%--<jsp:include page="../../page/page.jsp"></jsp:include>--%>

        <a href="getUserList.do"><button>第一页</button> </a>
        <a href="getUserList.do?pageNum=${currentPage-1 }">上一页</a>
        <a href="getUserList.do?pageNum=${currentPage+1 }">下一页</a>
        <a href="getUserList.do?pageNum=${totalPage }">最后一页</a>

        当前第${currentPage }页，共${totalPage }页，总条数为${count }

</body>
</html>
