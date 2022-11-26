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
<link rel="stylesheet" type="text/css" href="/css/index.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300&family=Mukta:wght@300&family=Open+Sans:wght@500&family=Tajawal&display=swap" rel="stylesheet">
<title>View Song</title>
</head>
<body>
	<div class="main">
		<div class="container-fluid">
			<div class="mx-auto mt-4">
				<header class="row justify-content-between align-items-center">
					<div class="text-start">
						<h2>Title: ${song.songTitle}</h2>
					</div>
					<div class=" d-flex justify-content-end">						
						<a href="/dashboard" class="nav-link image-link">Home</a>
						<a href="/songs/edit/${song.id}" class="nav-link image-link">Edit</a>
						<a href="/logout" class="nav-link danger">Logout</a>
					</div>
					<div class="text-start">
						<h4>Artist: <c:out value="${song.artist}" /></h4>
					</div>
				</header>
				<main class="col-5 px-4 py-4 container">
					<div>
						<h3>Lyrics:</h3>
						<p class="lyrics"><c:out value="${song.lyrics}" /></p>
					</div>
				</main>
			</div>
		</div>
	</div>
	
</body>
</html>