<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formations</title>
</head>
<body>
	<h1>Liste des inscriptions</h1>
	<ul>
		<c:forEach items="${ formations }" var="formation">
			<li><c:out value="${ formation.nom }"></c:out>, commence <c:out
					value="${ formation.dateDebut }"></c:out>, <c:out
					value="${ formation.nbrJours }"></c:out> jours, à <c:out
					value="${ formation.ville }"></c:out>
					<a href="">S'inscrire</a>
			</li>
			
		</c:forEach>
	</ul>
	<ul>

	</ul>
</body>
</html>