<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<link rel="stylesheet"
   href="<%=request.getContextPath()%>/resources/css/style.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<div class="nav">
			<div class="nav__column">
				<a href='main'>Spring Project</a>
			</div>
			<div class="nav__column">
				<form action="${request.getContextPath()}/spring-project/main" method="post">
					<input type="text" placeholder="Search with place or category.."
						name="keyword" />
					<button>S</button>
				</form>
			</div>
			<div class="nav__column">
				<form action="${request.getContextPath()}/spring-project/myPage" method="get">
					<button>${user.username} 님, 환영합니다.</button>
				</form>
				<form action="${request.getContextPath()}/spring-project/logout" method="get">
					<button>logOut</button>
				</form>
			</div>
		</div>
	</header>
    <main id="main-screen">
      <div class="main">
      	<form action="${pageContext.request.contextPath}/spring-project/main" method="get">
			<button class="backBtn">뒤로가기</button>
		</form>
      	<div class="main__detailBox">
          <div class="main__detailBox__column" >
				<img class="recommend__img" width="100%" height="100%" id ="profileImg" src = "/upload/displayFile?fileName=${keyword.image_name}">
          </div>
          <div class="main__detailBox__column">
          	<div class="main__detailBox__title">${keyword.name}</div>
          	<div class="main__detailBox__category">category : ${keyword.category}</div>
          	<div class="main__detailBox__elements">
          		위치 - ${keyword.location}<br><br>
          		가격 - ${keyword.price}원<br><br>
          	</div>
          </div>
          <div class="main__detailBox__column">
          	<form action="${request.getContextPath()}/spring-project/detail" method="post" class="reservationForm">
				<p>체크인 날짜<input type="date" name="check_in"></p>
				<p>체크아웃 날짜<input type="date" name="check_out"></p>
				<button type="submit" name="action" value="login">예약하기</button>
			</form>
          </div>
        </div>
      </div>
    </main>
</body>
</html>