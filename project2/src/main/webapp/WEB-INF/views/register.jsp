<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet"
   href="<%=request.getContextPath()%>/resources/css/style.css">
<script
  src="https://code.jquery.com/jquery-3.7.1.js"
  integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
  crossorigin="anonymous"></script>
 <style>
	.username_ok{
		color:var(--blue);
		display: none;
	}

	.username_exist{
		color:#FF0000; 
		display: none;
	}
</style> 
  
</head>
<script>
	const CONTEXT_PATH = `${pageContext.request.contextPath}`;
	function register() {
		console.log("register()");
	
		const inputElements = document.getElementsByClassName('data-inputs');

		if (confirm("위 정보로 회원가입하시겠습니까?")) {
			const form = document.registerForm;
			form.submit();
		}
	}
	
	function cursorOut(){
		const username = document.forms['registerForm']['username'].value;

		$.ajax({
            url:CONTEXT_PATH + "/spring-project/usernameCheck",
            type:"POST",
            data:{username:username},
            success:function(cnt){
                if(cnt == 0){ 
                    $('.username_ok').css("display","inline-block"); 
                    $('.username_exist').css("display", "none");
                } else {
                    $('.username_exist').css("display","inline-block");
                    $('.username_ok').css("display", "none");
                }
            },
            error:function(){
                alert("에러입니다");
            }
        });
	}
	

</script>
<body>
	<header>
		<div class="login__nav">
			<div class="login__nav__text">
					<a href='register'>회원가입</a>
			</div>
		</div>
	</header>
	<main id="main-screen">
	<form action="${pageContext.request.contextPath}/spring-project/register" method="post" name = "registerForm" class="loginForm">
		<p>아이디:<input type="text" name="username" required  maxlength="25" oninput="cursorOut();"></p>
		<span class="username_ok">사용 가능한 아이디입니다.</span>
		<span class="username_exist">중복된 아이디입니다.</span>
		<p>비밀번호:<input type="password" name="password" required></p>
		<p>나이:<input type="number" name="age"></p>
		<p>phone:<input type="text" name="phone"></p>
		<p>address:<input type="text" name="address"></p>
		<p>email:<input type="email" name="email"></p>
		<button type="button" name="action" onclick="register();">회원가입</button>
		<button type="button" name="action" 
			onclick='location.href="<%=request.getContextPath()%>/spring-project/login";'>로그인하러 가기</button>
	</form>
	</main>
</body>
</html>