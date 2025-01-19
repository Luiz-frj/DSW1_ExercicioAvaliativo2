<%--
  Created by IntelliJ IDEA.
  User: junior
  Date: 19/01/25
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="includes/head.html"/>
  <body>
    <jsp:include page="includes/navBar.jsp"/>

    <h1>Cadastrar usuario</h1>
    <br><br>
    <hr>
    <br><br>

    <%
      String message = (String) request.getAttribute("message");
      if(message != null){
    %> <h3><%=message%></h3>
    <%
      }
    %>

    <br><br>

    <form action="logado.do?action=cadastroUsuario" method="post">
      <label for="login">Login</label>
      <input type="text" id="login" name="login" required="required" placeholder="digite o seu login"><br><br>

      <label for="senha">Senha</label>
      <input type="password" id="senha" name="senha" required="required" placeholder="digite a sua senha"><br><br>

      <button type="submit">Enviar</button>
    </form>

    <br><br>
    <hr>
    <br><br>
    <p><a href="logado.do?action=pageHome">Voltar</a></p>

  </body>
  <jsp:include page="/includes/scripts.html" />
</html>
