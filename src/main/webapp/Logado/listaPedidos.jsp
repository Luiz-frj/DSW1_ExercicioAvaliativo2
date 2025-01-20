<%@ page import="br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Pedidos" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: junior
  Date: 19/01/25
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="includes/head.html"/>
<body>
  <jsp:include page="includes/navBar.jsp"/>

  <h1>Tabela de pedidos</h1>


  <%
    List<Pedidos> pedidos = (List<Pedidos>) request.getAttribute("pedidos");
  %>

  <%
    String message = (String) request.getAttribute("message");
    if(message != null){
  %> <h3><%=message%></h3>
  <%
    }
  %>

  <%
    String messageUpdated = (String) request.getAttribute("messageUpdated");
    if(messageUpdated != null){
  %> <h3><%=messageUpdated%></h3>
  <%
    }
  %>

  <br><br>

  <form action="logado.do?action=pesquisa" method="post">
    <input type="text" name="nome_cliente" id="nome_cliente" placeholder="Pesquise pelo nome do cliente">
    <button type="submit">Pesquisar</button>
  </form>

  <br><br><br>

  <table border="1">
    <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">ID</th>
      <th scope="col">Nome do cliente</th>
      <th scope="col">Endereço</th>
      <th scope="col">Valor (R$)</th>
      <th scope="col">Descrição</th>
      <th scope="col">Editar</th>
      <th scope="col">Deletar</th>
    </tr>
    </thead>
    <tbody>
    <%
      int i = 1;
      for(Pedidos pedido : pedidos){
    %>

    <tr>
      <th scope="row"><%=i%></th>
      <td><%=pedido.getId()%></td>
      <td><%=pedido.getNomeCliente()%></td>
      <td><%=pedido.getEnderecoEntrega()%></td>
      <td><%=pedido.getValor()%></td>
      <td><%=pedido.getDescricao()%></td>
      <td><a href="logado.do?action=pageUpdate&id_pedidos=<%=pedido.getId()%>">Editar pedido</a></td>
      <td><a href="logado.do?action=delete&id=<%=pedido.getId()%>">Deletar pedido</a></td>
    </tr>

    <%i++;
    } %>
    </tbody>
  </table>

  <br><br>
  <hr>
  <br><br>
  <p><a href="logado.do?action=pageHome">Voltar</a></p>

</body>
</html>
