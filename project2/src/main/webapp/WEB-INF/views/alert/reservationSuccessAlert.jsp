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
		alert("예약을 완료하였습니다. 마이페이지에서 결제버튼을 눌러주세요.");
		window.location.href = CONTEXT_PATH + "/spring-project/main";
	}
</script>
<body>
	
</body>
</html>