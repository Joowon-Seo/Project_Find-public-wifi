<%@ page import="com.example.project.ApiExplorer" %>
<%@ page import="com.example.project.DbControl" %>
<%@ page import="java.io.IOException" %><%--
  Created by IntelliJ IDEA.
  User: tjwndnjs
  Date: 2022-07-16
  Time: 오전 1:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta charset="UTF-8">
<head>
    <%
        for (int i = 0; i < 15; i ++) {
            String json = null;
            try {
                json = ApiExplorer.getJson(i*1000 + 1,(i+1)*1000);
                ApiExplorer.parsingJson(json);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    %>
</head>
<body>
<%
    DbControl getCount = new DbControl();
%>
    <h1><%= getCount.selectCount() +"개의 WIFI 정보를 정상적으로 저장하였습니다." %></h1>

    <a style="text-align:center" href="index.jsp">홈으로 가기 </a>


</body>
</html>
