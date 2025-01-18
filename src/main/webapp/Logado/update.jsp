<%--
  Created by IntelliJ IDEA.
  User: junior
  Date: 18/01/25
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Pedidos"%>
<!DOCTYPE html>
<!-- Página de edição de pedido. -->
<html>
<jsp:include page="../includes/head.html"/>
<body>
<jsp:include page="../includes/navBar.jsp"/>
<hr>

<%
  var pedido = (Pedidos) request.getAttribute("pedido");
  if(pedido == null){
    response.sendRedirect(request.getContextPath()+"/Logado/relatorio.jsp");
  }else{
%>

<form action="logado.do?action=commitUpdate" method="post">
  <label for="nomeCliente">Nome do Cliente</label>
  <input type="text" id="nomeCliente" name="textNomeCliente"
         placeholder="Digite o nome do cliente." required="required" value="<%= pedido.getNomeCliente() %>">

  <br/><br/>

  <label for="enderecoEntrega">Endereco de Entrega</label>
  <input type="text" id="enderecoEntrega" name="textEnderecoEntrega"
         placeholder="Digite o endereço de entrega." value="<%= pedido.getEnderecoEntrega() %>">

  <br/><br/>

  <label for="valor">Valor</label>
  <input type="number" id="valor" name="textValor"
         placeholder="Digite o valor." required="required" min="0" step="0.01" value="<%= pedido.getValor() %>">

  <br/><br/>

  <label for="descricao">Descricao</label>
  <input type="text" id="descricao" name="textDescricao"
         placeholder="Digite o endereço de entrega." value="<%= pedido.getDescricao() %>">

  <br/><br/>

  <input type="hidden" name="textId" value=<%=pedido.getIdPedido() %>>
  <button type="submit" style="text-align: center;">Atualizar</button>
</form>
<%} %>
</body>
</html>
