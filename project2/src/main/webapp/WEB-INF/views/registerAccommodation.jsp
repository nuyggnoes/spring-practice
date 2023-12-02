<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
   href="<%=request.getContextPath()%>/resources/css/style.css">
</head>
<body>
<header>
		<div class="nav">
			<div class="nav__column">
				<a href='main'>Spring Project</a>
			</div>
			<div class="nav__column">
				<div class="nav__text">
					숙소등록
				</div>
			</div>
			<div class="nav__column">
				<form action="${request.getContextPath()}/spring-project/myPage?mem_id=${user.user_id}" method="get">
					<button>${user.username} 님, 환영합니다.</button>
				</form>
				<form action="${request.getContextPath()}/spring-project/logout" method="get">
					<button>logOut</button>
				</form>
			</div>
		</div>
	</header>
	<main id="main-screen">
		<h1>숙소등록 페이지</h1>
		<div>
		<form action="/spring-project/registerAccommodation"  method="post">
			<input type="file" name="imgFile" />
			<input type="submit" value="이미지저장"/>
		</form>
		</div>
	</main>
</body>
</html>