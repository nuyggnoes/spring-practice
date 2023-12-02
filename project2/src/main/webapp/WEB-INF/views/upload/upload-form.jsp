<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
   <meta charset="UTF-8">
   <title>Insert title here</title>
<title>upload-form</title>
<link rel="stylesheet"
   href="<%=request.getContextPath()%>/resources/css/style.css">
</head>
<body>
	<header>
		<div class="nav">
			<div class="nav__column">
				<a href='${request.getContextPath()}/spring-project/main'>Spring Project</a>
			</div>
			<div class="nav__column">
				<div class="nav__text">
					숙소등록
				</div>
			</div>
			<div class="nav__column">
				<form action="${request.getContextPath()}/spring-project/myPage?mem_id=${user.user_id}" method="get">
					<button>마이페이지</button>
				</form>
				<form action="${request.getContextPath()}/spring-project/logout" method="get">
					<button>logOut</button>
				</form>
			</div>
		</div>
	</header>
	<main id="main-screen">
      <!-- name, category, price, location, roomCnt, toilCnt, image_name -->
   <form action="${pageContext.servletContext.contextPath }/upload/uploadFormAction" method="post" enctype="multipart/form-data" class="loginForm">
   	  <a href="${pageContext.request.contextPath}/spring-project/myPage?user_id=${user.user_id}" id="a">< back</a>
      <input type="hidden" name="user_id" value="${user.user_id}"/>
      <p>숙소 이름:<input type="text" name="accName" required></p>
      <p>카테고리:<input type="text" name="category" required></p>
      <p>가격:<input type="number" name="price"></p>
      <p>위치:<input type="text" name="location"></p>
      <p>roomCnt:<input type="number" name="roomCnt"></p>
      <p>toilCnt:<input type="number" name="toilCnt"></p>
      <input type="file" name="uploadFile" multiple="multiple"/>
      <input type="submit"  value="숙소 등록"/>
   </form>
   </main>
</body>
</html>
