<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-lg bg-warning">
	<a href="<%= request.getContextPath() %>/logado.do?action=logged">ARQDSW1</a><br>
	<a href="<%= request.getContextPath() %>/logado.do?action=newUser">Novo Usuário</a><br>
	<a href="<%= request.getContextPath() %>/logado.do?action=newOrder">Novo Pedido</a><br>
	<a href="<%= request.getContextPath() %>/logado.do?action=orders">Relatório</a><br>
	<a href="<%= request.getContextPath() %>/logado.do?action=logout">Sair</a><br>
</nav>