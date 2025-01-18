<%--
  Created by IntelliJ IDEA.
  User: junior
  Date: 18/01/25
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="../includes/head.html"/>
<body>
<jsp:include page="includes/navBar.jsp"/>
<hr>
<%
    String msg = (String) request.getAttribute("message");
    if (msg != null ) {
%>
<h1><%=msg%></h1>
<hr>
<%} %>
<form action="logado.do?action=createUser" method="post">
    <label for="login">Login</label>
    <input type="text" id="login" name="textLogin"
           placeholder="Digite o login." required="required">

    <br/><br/>

    <label for="password">Senha</label>
    <input type="password" id="password" name="textSenha"
           placeholder="Digite a senha." required="required">

    <br/>

    <button type="submit" style="text-align: center;">Cadastrar</button>
</form>
</body>
</html>
