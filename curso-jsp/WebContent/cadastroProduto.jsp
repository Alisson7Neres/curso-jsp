<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de produtos</title>
<link rel="stylesheet" type="text/css" href="resources/css/cadastro.css">
<link rel="stylesheet" type="text/css" href="resources/css/tabela.css">
<script src="resources/javascript/jquery.min.js" type="text/javascript"></script>
<script src="resources/javascript/jquery.maskMoney.min.js"
	type="text/javascript"></script>
</head>
<body>
	<a href="acessoLiberado.jsp">Voltar</a>
	<a href="/curso-jsp/">Sair</a>
	<h1>
		<center>Produtos Cadastrados</center>
	</h1>
	<h3 style="color: red">
		<center>${msg}</center>
	</h3>


	<form action="salvarProduto" method="post" id="formProduto"
		onsubmit="return validarCampos() ? true : false">
		<ul class="form-style-1">
			<table>
				<tr>
					<td>Codigo</td>
					<td><input type="text" readonly="readonly" id="id" name="id"
						value="${produto.id}" class="field-long"></td>
				</tr>
				<tr>
					<td>Nome:</td>
					<td><input type="text" id="nome" name="nome"
						value="${produto.nome}"></td>
				<tr>
					<td>Quantidade:</td>
					<td><input type="number" id="quantidade" name="quantidade"
						maxlength="4" size="4" value="${produto.quantidade}"></td>
				</tr>
				<tr>
					<td>Valor:</td>
					<td><input type="text" id="valor" name="valor"
						data-thousands="." data-decimal="," value="${produto.valor}"
						maxlength="12"></td>
				<tr>
					<td>Categoria:</td>
					<td><select id="categorias" name="categoria_id">
							<c:forEach items="${categorias}" var="cat">
								<option value="${cat.id}" id="${cat.id}"
									<c:if test="${cat.id == produto.categoria_id }">
								<c:out value= "selected=selected"/>
								</c:if>>
									${cat.nome}
									</option>
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<td></td>
					<td><input type="submit" value="Salvar"> <input
						type="submit" value="Cancelar"
						onclick="document.getElementById('formProduto').action='salvarProduto?acao=reset'">
					</td>

				</tr>
			</table>
		</ul>

	</form>

	<form action="listar" method="get" id="formProduto">
		<div class="container">
			<table class="responsive-table">
				<caption>Produtos cadastrados</caption>
				<tr>
					<th>Código</th>
					<th>Nome</th>
					<th>Quantidade</th>
					<th>Valor</th>
					<th>Excluir</th>
					<th>Editar</th>
				</tr>

				<c:forEach items="${produtos}" var="produto">
					<tr>
						<td><c:out value="${produto.id}"></c:out></td>
						<td><c:out value="${produto.nome}"></c:out></td>
						<td><c:out value="${produto.quantidade}"></c:out></td>
						<td><c:out value="${produto.valor}"></c:out></td>

						<td><a href="salvarProduto?acao=delete&produto=${produto.id}"
							onclick="return confirm('Deseja excluír produto cadastrado?');"><img
								src="resources/img/delete.png" title="Excluir" width="30"
								height="30"></a></td>
						<td><a href="salvarProduto?acao=editar&produto=${produto.id}"><img
								src="resources/img/read.png" title="Editar" width="30"
								height="30"></a></td>
				</c:forEach>
			</table>
		</div>
		<script type="text/javascript">
		function validarCampos()
		if (document.getElementById('formUser').action != 'salvarProduto?acao=reset'){
			if(document.getElementById("nome").value == ""){
				alert("Preencha o nome do produto");
				return false;
			}
			else if(document.getElementById("quantidade").value == ""){
				alert("Preencha a quantidade");
				return false;
			}
			else if(document.getElementById("valor").value == ""){
				alert("Preencha o valor do produto");
				return false;
			}
			return true;
		}
		</script>
		<script>
  $(function() {
    $('#valor').maskMoney();
  })
</script>
	</form>
</body>

</html>