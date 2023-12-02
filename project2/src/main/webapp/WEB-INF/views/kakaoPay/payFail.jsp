<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>결제에 실패하였습니다. 다시 시도해주세요</h1>
	<a href='${request.getContextPath()}/spring-project/main' class="backToMain"> < 메인화면으로</a>
</body>
</html>