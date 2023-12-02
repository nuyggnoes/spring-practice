<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
   <meta charset="UTF-8">
   <title>upload-action</title>
</head>
<body>
   <h1>${msg }</h1>
   <h1>${originalFileName}</h1>
   <form action="${request.getContextPath()}/upload/uploaded-image?fileName=${originalFileName}" method="get">
   	<button>등록된 이미지 보기</button>
   </form>
</body>
</html>
