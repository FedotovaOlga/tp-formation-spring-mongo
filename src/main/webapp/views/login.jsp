<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Authentification</title>
</head>
<body>
	<h1>Page d'authentification</h1>
	<form action="${ pageContext.request.contextPath }/login" method="post">
		<div>
			Nom d'utilisateur <input type="text" name="nomUtilisateur">
		</div>
		<div>
			Mot de passe <input type="password" name="motDePasse">
		</div>
		<div>
			<button>Se connecter</button>
		</div>
	</form>
	<c:if test="${param.error ne null}">Identifiants incorrects</c:if>
</body>
</html>