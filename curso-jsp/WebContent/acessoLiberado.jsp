 <jsp:useBean id="calcula" class="beans.BeanCursoJsp" type="beans.BeanCursoJsp" scope="page"></jsp:useBean>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title> </title>
<link rel="stylesheet" href="resources/css/boas-vindas.css">
</head>
<body>
<div class="body-1">
		<form>
		<center>
			<h2>Seja bem-vindo ao <i>JSP</i></h2>
			<h3>Escolha uma das duas opções abaixo</h3>
			<p>Cadastro de usuários</p>
			<a href="salvarUsuario?acao=listarTodos"><img src="resources/img/icon-cadastroClientes.png" title="Cadastro de usuários" width="95" height="95"></a>
			<p>Cadastro de produtos</p>	
			<a href="salvarProduto?acao=listar"><img src="resources/img/produto.png" title="Produtos" width="95" height="95"></a>
		</form>
		<footer><a href="http://localhost:8080/curso-jsp/"><b>Clique para encerrar</b></a></footer>
			</center>
	</div>
</body>
</html>