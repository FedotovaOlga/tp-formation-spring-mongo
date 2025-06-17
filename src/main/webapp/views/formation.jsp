<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${verbe} une formation</title>
</head>
<body>
	<h1>${verbe } une formation</h1>
	<form:form action="${pageContext.request.contextPath }/admin/${action}"
		method="post" modelAttribute="formation">
		<form:input type="hidden" path="id" />
		<div>
			<form:label path="nom">Nom: </form:label>
			<form:input path="nom" />
			<form:errors path="nom" />
		</div>
		<div>
			<form:label path="dateDebut">Date de début: </form:label>
			<form:input path="dateDebut" />
			<form:errors path="dateDebut" />
		</div>
		<div>
			<form:label path="nbrJours">Nombre de jours: </form:label>
			<form:input path="nbrJours" />
			<form:errors path="nbrJours" />
		</div>
		<div>
			<form:label path="ville">Ville: </form:label>
			<form:input path="ville" />
			<form:errors path="ville" />
		</div>
		<div>
			<input type="submit" value="${verbe}" />
		</div>
	</form:form>
	<a href="${ pageContext.request.contextPath }/admin">Liste
		de formations</a>

</body>
</html>