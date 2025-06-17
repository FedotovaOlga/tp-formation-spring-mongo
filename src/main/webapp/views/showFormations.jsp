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
	<h1>Liste des formations</h1>
	<form action="${ pageContext.request.contextPath }/admin" method="POST">
		<fieldset>
			<legend>Recherche selon le nom</legend>
			Nom : <input type="text" name="nom">
			<button>Chercher</button>
		</fieldset>
	</form>
	<ul>
		<c:forEach items="${ formations }" var="formation">
			<li><c:out value="${ formation.nom }"></c:out>, commence <c:out
					value="${ formation.dateDebut }"></c:out>, <c:out
					value="${ formation.nbrJours }"></c:out> jours, à <c:out
					value="${ formation.ville }"></c:out> <a
				href="${ pageContext.request.contextPath }/admin/editFormation/${formation.id}">Modifier</a>
			</li>
		</c:forEach>
	</ul>
	<ul>
		<li><a
			href="${ pageContext.request.contextPath }/admin/addFormation">Ajouter
				une formation</a></li>
		<li><a
			href="${ pageContext.request.contextPath }/admin/sortFormationsByName">Trier
				par nom</a></li>
		<li><a
			href="${ pageContext.request.contextPath }/admin/sortFormationsByDate">Trier
				par date de début</a></li>
		<li><a href="${ pageContext.request.contextPath }/admin">Liste
				de formations</a></li>
	</ul>
</body>
</html>