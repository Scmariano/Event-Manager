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
<title>Edit Song</title>
</head>
<body>
	<div class="main">
		<section class="signup">
			<div class="container">
				<div class="text-end">
					<a href="/dashboard" class="nav-link mb-3">Home</a>
				</div>
				<div class="signup-content">				
					<div class="signup-form">
						<h2 class="form-title">Edit a Song</h2>
						<form:form action="/songs/update/${song.id}" modelAttribute="song" method="POST"  class="col-5 mt-4 p-3 song-form ">
							<input type="hidden" name="_method" value="PUT" />
							<div>
								<form:errors path="*" class="text-danger"/>
							</div>
							<div class="mb-3">
								<form:label path="songTitle" ></form:label>
								<form:input path="songTitle"  placeholder="Title" />
							</div>
							<div class="form-group">
								<form:label path="artist" ></form:label>
								<form:input path="artist"  placeholder="Artist" />
							</div>
							<div class="form-group">
								<form:label path="lyrics" ></form:label>
								<form:textarea path="lyrics"  placeholder="Lyrics" />
							</div>
							<div class="form-row">
								<form:input type="hidden" path="songsEvent" value="${event.id}" class="form-control"/>
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
		</section>
	</div>
</body>
</html>