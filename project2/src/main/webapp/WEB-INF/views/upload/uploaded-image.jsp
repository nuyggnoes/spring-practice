<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
   <meta charset="UTF-8">
   <title>uploaded-image</title>
</head>
<body>
   <h1>uploaded image 파일 불러오기</h1>
   <!-- fileName=/lion.gif -->
   <div>
      <img id ="profileImg" src = "/upload/displayFile?fileName=${fileName}" style = "border-radius:0%; padding-top : 10px; height:100px; width:100px;">
   </div>
</body>
</html>
