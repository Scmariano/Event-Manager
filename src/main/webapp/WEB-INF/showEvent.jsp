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
<link rel="preconnect" href="https://fonts.gstatic.com" >
<link href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300&family=Mukta:wght@300&family=Open+Sans:wght@500&family=Tajawal&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/css/index.css">
<title>View Event List</title>
</head>
<body>
	<div class="main">
		<div class="container-fluid">
			<div class=" mx-auto mt-4">
				<header class=" header-b row justify-content-between align-items-center p-4">
					<div class="text-start">
						<h3>${event.eventName} Event List</h3>
					</div>
					<div class=" d-flex justify-content-end ">
						<a href="/dashboard" class="nav-link image-link">Home</a>
						<c:if test="${event.user.id == user.id}">
							<a href="/songs/new/${event.id }" class="nav-link image-link">Add Song</a>
						</c:if>
						<c:if test="${event.user.id == user.id}">
							<a href="/musicians/new/${event.id}" class="nav-link image-link">Add Musician</a>
						</c:if>
						<c:if test="${event.user.id == user.id}">
							<a href="/events/edit/${event.id}" class="nav-link  danger">Edit Event</a>
						</c:if>
						<c:if test="${event.user.id == user.id}">
							<a href="/events/${event.id}/delete" class="nav-link  danger">Delete Set</a>
						</c:if>
						<a href="/logout" class="nav-link mb-3 danger">Logout</a>
					</div>
				</header>
				<hr>
				<div>
						<h5>This Event is liked by:</h5>
						<ul>
							<c:forEach var="like" items="${event.likers}">
								<li><c:out value="${like.name}" /></li>
							</c:forEach>
						</ul>
				</div>
				<div class=" d-flex justify-content-between m-4">
					<main class="col-5 px-4 py-4 box">
						<h3 class = "text-center p-3">Song List</h3>
						<div>
							<ul>
								<c:forEach var="song" items="${assignedSongs}">
									<li class=" d-flex justify-content-between">
										<a href="/songs/${song.id}" class="nav-link"><c:out value="${song.songTitle}" /></a>
										<c:if test="${event.user.id == user.id}"> 
											<a href="/songs/${song.id}/${event.id}/remove" class="nav-link text-danger">Remove</a>
										</c:if>
									</li>
								</c:forEach>
							</ul>
						</div>
					</main>
				
					<main class="col-5 px-4 py-4 box">
						<h3 class = "text-center p-3">Band Members</h3>
						<div>
							<ul>
								<c:forEach var="musician" items="${assignedMusicians}">
									<li class=" d-flex justify-content-between">
										<c:out value="${musician.name}" /> - <c:out value="${musician.instrument}" />
										<c:if test="${event.user.id == user.id}"> 
											<a href="/musicians/${musician.id}/${event.id}/remove" class="nav-link text-danger">Remove</a>
										</c:if>
									</li><hr>
								</c:forEach>
							</ul>
						</div>
					</main>				
				</div>	
			</div>
		</div>
	</div>
</body>
</html>