
<jsp:useBean id="calcula" class="beans.BeanCursoJsp"
	type="beans.BeanCursoJsp" scope="page"></jsp:useBean>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="myprefix" uri="WEB-INF/TesteTag.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JDEV Curso de Java Server Pages</title>
<link rel="stylesheet" href="resources/css/estilo.css">
</head>

<body>

	<div class="login-page">
		<center><h2>JSP+Servlet+JDBC</h2></center>
		<div class="form">
			<form action="LoginServlet" method="post" class="login-from">
						Login:<input type="text" id="login" name="login"><br/>
						Senha:<input type="password" id="senha" name="senha"><br/>
					
						<button type="submit" value="enviar">Logar</button>
				
			</form>
		</div>
	</div>
</body>
		<footer><b>Github </b><a style="text-decoration: none" href="https://github.com/Alisson7Neres/" target="_blank">acesso</a> </footer>
</html>