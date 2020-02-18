<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de usuário</title>
<link rel="stylesheet" href="resources/css/cadastro.css">
<link rel="stylesheet" href="resources/css/tabela.css"> 
<meta charset="ISO-8859-1">
</head>
<body>
	<h1>Cadastro de usuário</h1>
	<form action="salvarUsuario" method="post">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Codigo:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${user.id }" class="field-long"></td>
					</tr>
					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login"
							value="${user.login }"></td>
					</tr>

					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha"
							value="${user.senha }"></td>
					</tr>
				</table> <input type="submit" value="Salvar">
			</li>
		</ul>
	</form>
	
<!--  TABELA  -->
	<div class="container">
		<table class="responsive-table">
			<caption> Usuários cadastrados	</caption>
<tr>
				<c:forEach items="${usuarios}" var="user">
					<tr>
						<td style="width: 150px"><c:out value="${user.id}"></c:out>
						</td>
						<td style="width: 150px"><c:out value="${user.login}"></c:out>
						</td>
						<td><c:out value="${user.senha}"></c:out>
						</td>

						<td><a href="salvarUsuario?acao=delete&user=${user.login}">Excluir</a>
						</td>
						<td><a href="salvarUsuario?acao=editar&user=${user.login }">Editar</a>
						</td>
					</tr>
				</c:forEach>
		</table>
	</div>
  <!-- TABELA -->
</body>
</html>
