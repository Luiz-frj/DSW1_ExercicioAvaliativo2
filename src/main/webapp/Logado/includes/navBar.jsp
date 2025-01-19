<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-lg bg-warning">
	<div class="container-fluid">
		<a class="me-3" href="<%= request.getContextPath() %>/logado.do?action=pageCadastroUsuario">Novo Usuário</a>
		<a class="me-3" href="<%= request.getContextPath() %>/logado.do?action=pageCadastroPedido">Novo Pedido</a>
		<a class="me-3" href="<%= request.getContextPath() %>/logado.do?action=listarPedidos">Relatório</a>
		<a class="me-3" href="<%= request.getContextPath() %>/logado.do?action=logout">Sair</a>
	</div>
</nav>
