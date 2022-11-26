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
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Add a Song</title>
</head>
<body>
	<div class="main">
		<section class="signup">
			<div class="container">
				<div class="d-flex justify-content-end">
					<a href="/events/${event.id}" class="nav-link mb-3">Event</a>
					<a href="/dashboard" class="nav-link mb-3">Home</a>							
				</div>
				<div class="signup-content">				
					<div class="signup-form">
						<h2 class="form-title">Add a Song</h2>
						<form:form action="/songs/create/${event.id}" modelAttribute="song" method="POST" class="col-5 mt-4 p-3 song-form ">
							<div>
								<form:errors path="*" class="text-danger"/>
							</div>
							
							<div class="form-group">
								<form:label path="songTitle" ></form:label>
								<form:input path="songTitle" placeholder="Title" />
							</div>
							<div class="form-group">
								<form:label path="artist" ></form:label>
								<form:input path="artist" placeholder="Artist" />
							</div>
							<div class="form-group">
								<form:label path="lyrics" ></form:label>
								<form:textarea  path="lyrics" placeholder="Lyrics" />
							</div>
							<div class="form-row">
								<form:input type="hidden" path="event" value="${event.id}" class="form-control"/>
							</div>
							
							<div class="form-group form-button">
								<input type="submit"  class="form-submit" value="Submit" />
							</div>
						</form:form>
					</div>
					<div class="signup-image">
						<figure>
							<img src="/assets/imgs/create.png" alt="sing up image">
						</figure>
					</div>
				</div>	
			</div>
			<div class="container row mx-auto mt-3 p-4">
				<table class="table table-striped table-bordered caption-top ">
					<thead class="table-info">
						<tr class="align-middle">
							<th>Title</th>
							<th>Artist</th>							
							<th>Action</th>						
						</tr>
					</thead>
					<tbody>
						<c:forEach var="song" items="${unAssignedSongs}">
							<c:if test="${unAssignedSongs != event.eventSongs}">					
							<tr>
								<td>
									<a href="/songs/${song.id}" class="nav-link"><c:out value="${song.songTitle}"/></a>
								</td>													
								<td><c:out value="${song.artist}" /></td>
								
								<td>																		
									<a href="/songs/${song.id}/${event.id}/add" class="nav-link">Add Song</a>	
									<a href="/songs/${song.id}/${event.id}/delete" class="nav-link danger">Delete</a> 						
								</td>
								
							</tr>
							</c:if>
						</c:forEach>
					</tbody>				
				</table>
			</div>
		</section>
	</div>
</body>
</html>