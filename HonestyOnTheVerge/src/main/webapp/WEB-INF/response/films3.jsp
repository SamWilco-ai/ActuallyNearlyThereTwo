<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="user" items="${films}">
						<tr>
							<td><c:out value="${user.id}" /></td>
							<td><c:out value="${user.title}" /></td>
							<td><c:out value="${user.year}" /></td>
							<td><c:out value="${user.director}" /></td>
							<td><c:out value="${user.stars}" /></td>
							<td><c:out value="${user.review}" /></td>
							<td><a href="edit?id=<c:out value='${user.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${user.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
</html>