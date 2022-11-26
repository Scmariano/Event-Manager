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
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" >
<link href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300&family=Mukta:wght@300&family=Open+Sans:wght@500&family=Tajawal&display=swap" rel="stylesheet">
<title>Login and Registration</title>
</head>
<body>
	<div class="main">
		<div class="sign-in">
			<div class="container">
				<div class="signin-content">
					<div class="signin-image">
							<figure>
								<img src="/assets/imgs/music.png" alt="sing up image">
							</figure>
							<a href="/" class="signup-image-link">Create an
								account</a>
					</div>
					<div class="signup-form">
						<h2 class="form-title">Log In</h2>
						<form:form  class="register-form" id="register-form" action="/login" method="POST" modelAttribute = "newLogin">
							<div>
								<form:errors path="*" class="text-danger"/>
							</div>
								
								<div class="form-group">
									<form:label path="email" ></form:label>
									<form:input path="email" placeholder="Your Email"/>
								</div>
								<div class="form-group">
									<form:label path="password" ></form:label>
									<form:input path="password" type= "password" placeholder="Password"/>
								</div>								
								<div class="form-group form-button">
									<input type="submit" name="signin" id="signin"
									class="form-submit" value="Log in" />
								</div>
						</form:form>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
	