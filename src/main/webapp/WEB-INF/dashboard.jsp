<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" >
<link href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300&family=Mukta:wght@300&family=Open+Sans:wght@500&family=Tajawal&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/css/index.css">

<title>Event List</title>
</head>
<body>
	<div class="main">
		<div class="container-fluid">
			<div class="  mx-auto mt-4">
				<header class="header-b row justify-content-between align-items-center p-3">
					<div class="text-start">
						<h4>Hello, ${user.name}!</h4>
					</div>
					<div class="col-5 text-start">
						<p>This is all the event!</p>
					</div>
					<div class="col-5 d-flex justify-content-end">
						<a href="/events/new" class="nav-link image-link">Add Event</a>
						<a href="/logout" class="nav-link danger">Logout</a>
					</div>
				</header><hr>
			</div>
			<div class="container row mx-auto mt-3 p-4">
				<table class="table table-striped table-bordered caption-top ">
					<thead class="table-info">
						<tr class="align-middle">
							<th>Event Name</th>
							<th>Date</th>
							<th>Address</th>
							<th>Lead</th>
							<th>Total Likes</th>
							<th>Like/Unlike</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="event" items="${events}">
							<tr>
								<td><a href="/events/${event.id}" class="nav-link"><c:out value="${event.eventName}"/></a></td>
								<td><fmt:formatDate value="${event.eventDate}" pattern="MMMM dd-yyyy"/>						
								<td><c:out value="${event.address}" /></td>
								<td><c:out value="${event.user.name}" /></td>
								<td class= "text-center">${event.likers.size()}</td>
								<td>
									<c:choose>
										<c:when test="${event.likers.contains(user)}">
											<a href="/events/${event.id}/unLike" class="nav-link danger">Unlike</a>
										</c:when>
										<c:otherwise>
											<a href="/events/${event.id}/like" class="nav-link danger">Like</a>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>