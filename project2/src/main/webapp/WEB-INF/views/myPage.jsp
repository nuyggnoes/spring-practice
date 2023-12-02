<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
   href="<%=request.getContextPath()%>/resources/css/style.css">
<script
  src="https://code.jquery.com/jquery-3.7.1.js"
  integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
  crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Spring Project</title>
<script>
	const CONTEXT_PATH = `${pageContext.request.contextPath}`;
	const INDEX_MODIFY_DELETE_LIST_BUTTON = 0;
	const INDEX_COMPLETE_CANCEL_BUTTON = 1;
	
	function getUserEntity(){
		const user_id = document.getElementById("user_id").value;
		const username = document.getElementById("username").value;
		const age = document.getElementById("age").value;
		const phone = document.getElementById("phone").value;
		const address = document.getElementById("address").value;
		const email = document.getElementById("email").value;
		//const profile_img = document.getElementById("${user.profile_img}").id;
		const element = document.getElementById("${user.profile_img}");
		const profile_img = element ? element.id : null;
		
		const jsonObj = {
				user_id: user_id,
				username: username,
   			    age: age,
   			    phone: phone,
   			    address: address,
   			    email: email,
   			    profile_img: profile_img
   		 }
		return jsonObj
	}
	
	function deleteUser(){
		if (confirm("회원탈퇴를 하시겠습니까?")) {
			jsonObj = getUserEntity();
			ajaxDeleteSend("DELETE",jsonObj);
		}
	}
	
	function modify(){
		displayButtonBar("none","");
		disabledElement(false);
	}
	
	function cancel(){
		displayButtonBar("","none");
		disabledElement(true);
	}
	
	function displayButtonBar(modifyBtnBar, completeBtnBar){
		const button_bar = document.getElementsByClassName("button-bar");
		button_bar[INDEX_MODIFY_DELETE_LIST_BUTTON].style.display=modifyBtnBar;
		button_bar[INDEX_COMPLETE_CANCEL_BUTTON].style.display=completeBtnBar;
	}
	
	function disabledElement(disabled){
		const ageElement = document.getElementsByName("age");
		const phoneElement = document.getElementsByName("phone");
		const addressElement = document.getElementsByName("address");
		const emailElement = document.getElementsByName("email");
		ageElement[0].disabled = disabled;
		phoneElement[0].disabled = disabled;
		addressElement[0].disabled = disabled;
		emailElement[0].disabled = disabled;
	}
	
	function completeModify(){
		
		var jsonObj = {key:'key-value'};
		
		jsonObj = getUserEntity();
		ajaxSend("PUT",jsonObj);
	}
	
	function ajaxSend(method, dataJson){

		console.log(dataJson);
		console.log(JSON.stringify(dataJson));
		$.ajax( {
			url:CONTEXT_PATH + "/spring-project/modify",
			method: method,
			headers:{"Content-Type": "application/json;charset=UTF-8"},
			dataType:"text",
			data : JSON.stringify(dataJson),
			success:function(data,textStatus,jqXHR){
				console.log("success()");
				console.log(data);
				data = JSON.parse(data);
				alert(data.message);
				
				cancel();
			},
			error:function(jqXHR, textStatus, errorThrown){
				console.log("error()");
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
			}
			
		} );
	}
	
	function ajaxDeleteSend(method, dataJson){

		$.ajax( {
			url:CONTEXT_PATH + "/spring-project/remove-user",
			method: method,
			headers:{"Content-Type": "application/json;charset=UTF-8"},
			dataType:"text",
			data : JSON.stringify(dataJson),
			success:function(data,textStatus,jqXHR){
				console.log("success()");
				console.log(data);
				data = JSON.parse(data);
				alert(data.message);
				window.location.href = CONTEXT_PATH + "/spring-project/login";
			},
			error:function(jqXHR, textStatus, errorThrown){
				console.log("error()");
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
			}
			
		} );
	}
	
	function ajaxImgDeleteSend(method, dataJson){

		$.ajax( {
			url:CONTEXT_PATH + "/spring-project/remove-profile_img",
			method: method,
			headers:{"Content-Type": "application/json;charset=UTF-8"},
			dataType:"text",
			data : JSON.stringify(dataJson),
			success:function(data,textStatus,jqXHR){
				console.log("success()");
				data = JSON.parse(data);
				window.location.href = CONTEXT_PATH + "/spring-project/login";
			},
			error:function(jqXHR, textStatus, errorThrown){
				console.log("error()");
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
			}
			
		} );
	}
	
	function editImg(event){
		if (confirm("프로필 이미지를 삭제하시겠습니까?")) {
			var jsonObj = {key:'key-value'};
			
			jsonObj = getUserEntity();
			ajaxImgDeleteSend("DELETE",jsonObj);
		}
	}
	
</script>
</head>
<body>
	<header>
		<div class="nav">
			<div class="nav__column">
				<a href='main'>Spring Project</a>
			</div>
			<div class="nav__column">
				<div class="nav__text">
					마이페이지
				</div>
			</div>
			<div class="nav__column">
				<form action="${request.getContextPath()}/spring-project/myPage?mem_id=${user.user_id}" method="get">
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
			<div class="main__myInfo">
				<div class="main__myInfo__column">
					<div class="main__myInfo__img">
						<c:choose>
							<c:when test="${user.profile_img == null}">
							<form id="imgForm" action="${pageContext.servletContext.contextPath }/upload/uploadProfileImg?user_id=${user.user_id}" method="post" enctype="multipart/form-data">
								<input id="imgInput1" type="file" name="uploadFile" multiple="multiple"/>
		      					<input id="imgInput2" type="submit" value="프로필 사진 등록"/>
							</form>					
							</c:when>
						<c:otherwise>
							<img class="profile__img" id="${user.profile_img}" src="/upload/displayFile?fileName=${user.profile_img}" onclick="editImg();" >					
						</c:otherwise>
						</c:choose>
					</div>
					<div class="main__myInfo__img">
						<h1 style="font-size: 40px; font-weight: bolder;">${user.username}</h1>
					</div>
				</div>
				<div class="main__myInfo__column">
				<h1 style="font-size: 40px; font-weight: bolder;">프로필</h1><br><br>
					<input type="hidden" id="user_id" value="${user.user_id}"/>
					<input type="hidden" id="profile_img" value="${user.profile_img }"/>
					<p>아이디 : <input type="text" id="username" name="username" value="${user.username}" disabled="disabled"></p>
					<p>나이 : <input type="text" id="age" name="age" value="${user.age}" disabled="disabled"></p>
					<p>휴대폰 : <input type="text" id="phone" name="phone" value="${user.phone}" disabled="disabled"></p>
					<p>주소 : <input type="text" id="address" name="address" value="${user.address}" disabled="disabled"></p>
					<p>이메일 : <input type="text" id="email" name="email" value="${user.email}" disabled="disabled"></p>
					<div class="main__changeInfo">
						<div class="button-bar">
							<button onclick="modify();">수정</button>
							<button onclick="deleteUser();">회원탈퇴</button>
						</div>
						<div class="button-bar" style="display: none;">
							<button type="submit" onclick="return completeModify();">완료</button>
							<button onclick="cancel();">취소</button>
							<button onclick="deleteUser();">회원탈퇴</button>
						</div>
					</div>
				</div>
			</div>
			<div class="mypage__div">
				<form action="${request.getContextPath()}/spring-project/reservedAccommodation?user_id=${user.user_id}" method="get">
					<button type="submit">• 예약숙소조회</button>
				</form>
			</div>
			<div class="mypage__div">
				<form action="${request.getContextPath()}/spring-project/registeredAccommodation?user_id=${user.user_id}" method="get">
					<button>• 내가 등록한 숙소조회</button>
				</form>
			</div>
			<div class="mypage__div">
				<form action="${request.getContextPath()}/upload/uploadForm" method="get">
					<button>• 숙소등록</button>
				</form>
			</div>
		</div>
	</main>
</body>
</html>