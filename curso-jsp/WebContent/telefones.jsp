<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Cadastro de telefones</title>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="resources/css/cadastro.css">
<link rel="stylesheet" type="text/css" href="resources/css/tabela.css">



<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>

</head>
<body>
	<a href="acessoLiberado.jsp">Voltar</a>
	<a href="/curso-jsp/">Sair</a>
	<h1>

		<center>Lista de telefones</center>
	</h1>
	<h3 style="color: red;">
		<center>${msg}</center>
		<center>${sha}</center>
	</h3>
	<form action="salvarTelefones" method="post" id="formUser"
		onsubmit="return validarCampos() ? true : false">
		<ul class="form-style-1">
			<li>

				<table>

					<tr>
						<td>User:</td>
						<td><input type="text" id="user" name="user" readonly="readonly" class="field-long"
							value="${userEscolhido.id}"></td>

						<td><input type="text" id="nome" name="nome" readonly="readonly"
							class="field-long" value="${userEscolhido.nome}"></td>

					</tr>

					<tr>
						<td>Número:</td>
						<td><input type="text" id="numero" name="numero">
						<td><select id="tipo" name="tipo">
								<option>casa</option>
								<option>celular</option>
								<option>contato</option>
						</select></td>
					</tr>






					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"> 
						<input
							type="submit" value="Cancelar"
							onclick="document.getElementById('formUser').action='salvarTelefones?acao=cancelar'"></td>
					</tr>
				</table>
			</li>
		</ul>
	</form>

	<!--  TABELA  -->
	<form action="consultarTodos" method="get" id="formUser">
		<div class="container">
			<table class="responsive-table">
				<caption>Usuários cadastrados</caption>
				<tr>
					<th>ID</th>
					<th>Número</th>
					<th>Tipo</th>
					<th>Excluir</th>


				</tr>

				<c:forEach items="${telefones}" var="fone">
					<tr>
						<td><c:out value="${fone.id}"></c:out></td>
						<td><c:out value="${fone.numero}"></c:out></td>
						<td><c:out value="${fone.tipo}"></c:out></td>

						<td><a href="salvarTelefones?user=${fone.usuario}&acao=deleteFone&foneId=${fone.id}" onclick="return confirm('Deseja realmente excluír número de telefone?');"><img
								src="resources/img/delete.png" title="Excluir" width="30"
								height="30"></a></td>

					</tr>
				</c:forEach>
			</table>
		</div>
		<!-- Campo de validação -->
				<script type="text/javascript">
			    if (document.getElementById('formUser').action != 'salvarTelefones?acao=cancelar') {
			    	if (document.getElementById("numero").value == '') {
			    	    alert('Informe o Número!');
			    	    return false;
			    	} 
			    	if (document.getElementById("tipo").value == '') {
			    	    alert('Informe o Tipo!');
			    	    return false;
			            }
			        }
			        return true;
			    }
		</script>

	</form>



</body>
</html>