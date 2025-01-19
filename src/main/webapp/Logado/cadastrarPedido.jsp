<%--
  Created by IntelliJ IDEA.
  User: junior
  Date: 19/01/25
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="../includes/head.html"/>
    <jsp:include page="includes/navBar.jsp"/>
    <body>
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

        <form action="logado.do?action=cadastroPedido" method="post">
            <label for="nomeCliente">Nome do cliente</label>
            <input type="text" id="nomeCliente" name="nomeCliente" required="required" placeholder="Digite o nome do cliente"><br><br>

            <label for="endereco">Endereço</label>
            <input type="text" id="endereco" name="endereco" required="required" placeholder="Digite o endereço do pedido"><br><br>

            <label for="valor">Valor</label>
            <input type="number" id="valor" name="valor" required="required" placeholder="Digite o valor do pedido"><br><br>

            <label for="descricao">Descrição</label>
            <input type="text" id="descricao" name="descricao" required="required" placeholder="Digite a descrição do pedido"><br><br>

            <button type="submit">Enviar</button>
        </form>

        <br><br>
        <hr>
        <br><br>
        <p><a href="logado.do?action=pageHome">Voltar</a></p>

    </body>
    <jsp:include page="/includes/scripts.html" />
</html>
