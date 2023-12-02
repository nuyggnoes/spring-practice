<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
	const CONTEXT_PATH = `${pageContext.request.contextPath}`;
	window.onload=function(){
		alert("아이디와 비밀번호를 다시 확인해주세요!");
		window.location.href = CONTEXT_PATH + "/spring-project/login";
	}
</script>
<body>
	
</body>
</html>