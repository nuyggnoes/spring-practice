<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet"
   href="<%=request.getContextPath()%>/resources/css/style.css">
</head>
<body>
	<header>
		<div class="login__nav">
			<div class="login__nav__text">
					<a href='login'>로그인</a>
			</div>
		</div>
	</header>
	<main id="main-screen">
	<form action="${pageContext.request.contextPath}/spring-project/login" method="post" class="loginForm">
		<p>아이디:<input type="text" name="username" required></p>
		<p>비밀번호:<input type="password" name="password" required></p>
		<button type="submit" name="action" value="login">로그인</button>
		<button type="button" name="action" value="register" onclick="location.href=`${pageContext.request.contextPath}/spring-project/register`">회원가입</button>
	</form>
	</main>
</body>
</html>