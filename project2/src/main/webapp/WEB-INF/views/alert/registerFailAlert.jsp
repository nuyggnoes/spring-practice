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
		alert("중복된 아이디 입니다. 다시 입력해주세요");
		window.location.href = CONTEXT_PATH + "/spring-project/register";
	}
</script>
<body>
	
</body>
</html>