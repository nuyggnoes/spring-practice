<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.backToMain{
		color:black;
		text-decoration: none;
		font-weight: bolder;
		font-size: 20px;
	}
	.backToMain:hover{
		color: #3cb4ff;
	}
</style>
</head>
<body>
	<div class="content">
		<h1>결제가 완료되었습니다. </h1>
		<p>상품명 : ${param.item_name} </p>
		<p>결제금액 : ${param.item_price}원</p>
	</div>
	<%-- <form action="${request.getContextPath()}/spring-project/main" method="get">
		<button class="backBtn">돌아가기</button>
	</form> --%>
	<a href='${request.getContextPath()}/spring-project/main' class="backToMain"> < 메인화면으로</a>
</body>
</html>