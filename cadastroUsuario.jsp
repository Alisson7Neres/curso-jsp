<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Cadastro de usuário</title>
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

		<center>Cadastro de usuário</center>
	</h1>
	<h3 style="color: red;">
		<center>${msg}</center>
		<center>${sha}</center>
	</h3>
	<form action="salvarUsuario" method="post" id="formUser"
		onsubmit="return validarCampos() ? true : false"
		enctype="multipart/form-data">
		<ul class="form-style-1">
			<li>

				<table>

					<tr>
						<td>Codigo:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${user.id }" class="field-long"></td>


						<td>CEP:</td>
						<td><input type="text" id="cep" name="cep"
							value="${user.cep }" onblur="consultarCep();"></td>
					</tr>
					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login"
							value="${user.login }"></td>

						<td>Rua:</td>
						<td><input type="text" id="rua" name="rua"
							value="${user.rua }">
					</tr>

					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha"
							value="${user.senha }"></td>

						<td>Bairro:</td>
						<td><input type="text" id="bairro" name="bairro"
							value="${user.bairro }">
					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${user.nome }"></td>

						<td>Cidade:</td>
						<td><input type="text" id="cidade" name="cidade"
							value="${user.cidade }">
					</tr>
					<tr>
						<td>Email:</td>
						<td><input type="text" id="email" name="email"
							value="${user.email }"></td>

						<td>Estado:</td>
						<td><input type="text" id="estado" name="estado"
							value="${user.estado }">
					</tr>

					<tr>

						<td>Foto:</td>
						<td><input type="file" name="foto"> <input
							type="text" style="display: none;" name="fotoTemp"
							readonly="readonly" value="${user.fotoBase64}"> <input
							type="text" style="display: none;" name="contentTypeTemp"
							readonly="readonly" value="${user.contentType}"></td>

					</tr>

					<tr>
						<td>Curriculo:</td>
						<td><input type="file" name="curriculo" value="curriculo">
							<input type="text" style="display: none;" name="fotoTempPDF"
							readonly="readonly" value="${user.curriculoBase64}"> <input
							type="text" style="display: none;" name="contentTypeTempPDF"
							readonly="readonly" value="${user.contentTypeCurriculo}">
						</td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"> <input
							type="submit" value="Cancelar"
							onclick="document.getElementById('formUser').action='salvarUsuario?acao=reset'"></td>

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
					<th>Login</th>
					<th>Foto</th>
					<th>Curriculo</th>
					<th>Nome</th>
					<th>Email</th>
					<th>Estado</th>
					<th>Cidade</th>
					<th>Bairro</th>
					<th>Rua</th>
					<th>Fone</th>
					<th>Editar</th>
					<th>Deletar</th>

				</tr>

				<c:forEach items="${usuarios}" var="user">
					<tr>
						<td><c:out value="${user.id}"></c:out></td>
						<td><c:out value="${user.login}"></c:out></td>

						<c:if
							test="${!user.fotoBase64Miniatura.isEmpty() && user.fotoBase64Miniatura != null}">
							<td><a
								href="salvarUsuario?acao=download&tipo=image&user=${user.id}"><img
									src='<c:out value="${user.fotoBase64Miniatura }"/>'
									alt="Imagem User" title="imagemuser" width="32" height="32"></a></td>
						</c:if>

						<c:if
							test="${user.fotoBase64Miniatura.isEmpty() || user.fotoBase64Miniatura == null}">
							<td><img alt="image User" src="resources/img/user_view.png"
								width="32" height="32" onclick="alert('SEM FOTO')"></td>
						</c:if>

						<c:if
							test="${!user.curriculoBase64.isEmpty() && user.curriculoBase64 != null }">
							<td><a
								href="salvarUsuario?acao=download&tipo=curriculo&user=${user.id}">
									<img alt="CurriculoImage"
									src="resources/img/download-pdf-icon-png-10.png" width="32"
									height="32">
							</a></td>
						</c:if>

						<c:if
							test="${user.curriculoBase64.isEmpty() || user.curriculoBase64 == null }">
							<td><img alt="CurriculoImageNotFound"
								src="resources/img/not-found.png" width="32" height="32"
								onclick="alert('CURRICULO VÁZIO')"></td>
						</c:if>

						<td><c:out value="${user.nome}"></c:out></td>
						<td><c:out value="${user.email}"></c:out></td>
						<td><c:out value="${user.estado }"></c:out></td>
						<td><c:out value="${user.cidade }"></c:out></td>
						<td><c:out value="${user.bairro }"></c:out></td>
						<td><c:out value="${user.rua }"></c:out></td>

						<td><a href="salvarTelefones?acao=addFone&user=${user.id}"><img
								src="resources/img/ringing-phone.png" title="Telefones"
								width="30" height="30"></a></td>
						<td><a href="salvarUsuario?acao=editar&user=${user.id}"><img
								src="resources/img/read.png" title="Editar" width="30"
								height="30"></a></td>
						<td><a href="salvarUsuario?acao=delete&user=${user.id}"><img
								src="resources/img/delete.png" title="Excluir" width="30"
								height="30"></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<script type="text/javascript">
		
			function validarCampos()
			//if (document.getElementById('formUser').action != 'salvarUsuario?acao=reset'){
				if (document.getElementById("login").value == "") {
					alert("Preencha o login");
					return false;
				}

				else if (document.getElementById("senha").value == "") {
					alert("Preencha a senha");
					return false
				} else if (document.getElementById("nome").value == "") {
					alert("Preencha o nome");
					return false;
				} else if (document.getElementById("cep").value == "") {
					alert("Preenchimento do CEP é obrigatório!")
					return false;
				}
				return true;
			}

			function consultarCep(){
				var cep = $("#cep").val();
				alert(cep);
				//Consulta o webservice viacep.com.br/
				$.getJSON("https://viacep.com.br/ws/" + cep
						+ "/json/?callback=?", function(dados) {
					alert(dados);

					if (!("erro" in dados)) {
						//Atualiza os campos com os valores da consulta.
						$("#rua").val(dados.logradouro);
						$("#bairro").val(dados.bairro);
						$("#cidade").val(dados.localidade);
						$("#estado").val(dados.uf);
						//$("#ibge").val(dados.ibge);
					} //end if.
					else {
						//CEP pesquisado não foi encontrado.
						$("#cep").val("");
						$("#rua").val("");
						$("#bairro").val("");
						$("#cidade").val("");
						$("#estado").val("");
						//$("#ibge").val('');
						alert("CEP não encontrado.");
					}
				});

			}
		</script>
	</form>

</body>
</html>