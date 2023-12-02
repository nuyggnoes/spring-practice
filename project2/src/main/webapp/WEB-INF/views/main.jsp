<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="spring" uri="http://www.springframework.org/tags" %> 
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
   href="<%=request.getContextPath()%>/resources/css/style.css">
<meta charset="UTF-8">
<title>Spring Project</title>
</head>
<body>
	<header>
		<div class="nav">
			<div class="nav__column">
				<a href='main'>Spring Project</a>
			</div>
			<div class="nav__column">
				<form action="${contextPath}/spring-project/main" method="post">
					<input type="text" placeholder="Search with place or category.."
						name="keyword" />
					<button>S</button>	
				</form>
			</div>
			<div class="nav__column">
				<form action="${request.getContextPath()}/spring-project/myPage?user_id=${user.user_id}" method="get">
					<button type="submit">${user.username} 님, 환영합니다.</button>
				</form>
				<form action="${request.getContextPath()}/spring-project/logout" method="get">
					<button>logOut</button>
				</form>
			</div>
		</div>
	</header>
	<main id="main-screen">
		<div class="main">
			<c:choose>
				<c:when test="${empty accList}">
					<div class="noInformation">검색결과가 없습니다.</div>
				</c:when>
				<c:otherwise>
				<c:forEach items="${accList}" var="acc">
					<button class="main__resultBox" type="button"
						onclick='location.href="${request.getContextPath()}/spring-project/detail?acc_id=${acc.acc_id}";'>
						<div class="main__resultBox__column">
							<img class="recommend__img" width="100%" height="width" id ="profileImg" src = "/upload/displayFile?fileName=${acc.image_name}">	
						</div>
						<div class="main__resultBox__column">
							<div class="main__resultBox__title">${acc.name}</div>
							<br>
							<div class="main__resultBox__descript">
								가격 : ${acc.price}원<br>
								<br> 위치 : ${acc.location}<br>
							</div>
						</div>
						<div class="main__resultBox__column">
							<div class="main__resultBox__category">
								${acc.category}
							</div>
							<div class="main__resultBox__reserv">
								<div>예약하러가기 ></div>
							</div>
						</div>
					</button>
			</c:forEach>
			</c:otherwise>
		</c:choose>
		</div>
	</main>
</body>
</html>