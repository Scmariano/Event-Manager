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
<title>Login and Registration</title>
</head>
<body>
	<div class="main">
		<!-- Sign up form -->
		<div class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title">Sign up</h2>
						<form:form  class="register-form" action="/register" method="POST" modelAttribute = "newUser">
							<div>
								<form:errors path="*" class="text-danger"/>
							</div>
								<div class="form-group">
									<form:label path="name" ></form:label>
									<form:input path="name" placeholder="Your Name" />
								</div>
								<div class="form-group">
									<form:label path="email" ></form:label>
									<form:input path="email" placeholder="Your Email"/>
								</div>
								<div class="form-group">
									<form:label path="password" ></form:label>
									<form:input path="password" type= "password" placeholder="Password"/>
								</div>
								<div class="form-group">
									<form:label path="confirm" ></form:label>
									<form:input path="confirm" type="password" placeholder="Confirm your password"/>
								</div>
								<div class="form-group form-button">
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Register" />
								</div>
						</form:form>
						
					</div>
					<div class="signup-image">
						<figure>
							<img src="/assets/imgs/141.jpg" alt="sing up image">
						</figure>
						<a href="/login" class="signup-image-link">I am already
							member</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>