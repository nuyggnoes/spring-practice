<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
   href="<%=request.getContextPath()%>/resources/css/style.css">
<meta charset="UTF-8">
<title>Anywhere in Korea</title>
<script
  src="https://code.jquery.com/jquery-3.7.1.js"
  integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
  crossorigin="anonymous"></script>
<script>
	
	const CONTEXT_PATH = `${pageContext.request.contextPath}`;
	
	function removeAccommodation(event){
		console.log("remove()");
		var clickedButton = event.target;
		console.log(clickedButton.parentElement.parentElement);
		var div = clickedButton.parentElement.parentElement;
		const inputAccHidden = div.getElementsByClassName("acc_id");
		const inputUserHidden = div.getElementsByClassName("user_id");
		const acc_id = inputAccHidden[0].value;
        const user_id = inputUserHidden[0].value;
		const dataJson={
				user_id: user_id,
				acc_id: acc_id
		};
		console.log(dataJson);
		div.parentElement.parentElement.remove();
		ajaxSend("DELETE", dataJson);
	}
	
	function getUserAndAccId(){
		
	}
	
	function ajaxSend(method, dataJson){

		console.log(method);
		console.log(dataJson);
		$.ajax( {
			url:CONTEXT_PATH + "/spring-project/remove-registeredAcc",
			method: method,
			headers:{"Content-Type": "application/json;charset=UTF-8"},
			dataType:"text",
			data : JSON.stringify(dataJson),
			success:function(data,textStatus,jqXHR){
				console.log("success()");
				console.log(data);
				data = JSON.parse(data);
				alert(data.message);
			},
			error:function(jqXHR, textStatus, errorThrown){
				console.log("error()");
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
			}
		} );
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
					등록한 숙소조회
				</div>
			</div>
			<div class="nav__column">
				<form action="${pageContext.request.contextPath}/spring-project/myPage?user_id=${user.user_id}" method="get">
					<button>${user.username} 님, 환영합니다.</button>
				</form>
				<form action="${pageContext.request.contextPath}/spring-project/logout" method="get">
					<button>logOut</button>
				</form>
			</div>
		</div>
	</header>
	<main id="main-screen">
		<div class="main">
			<form action="${pageContext.request.contextPath}/spring-project/myPage?user_id=${user.user_id}" method="get">
				<button class="backBtn">< back</button>
			</form>
					<c:choose>
					    <c:when test="${empty registeredList}">
					        <div class="noInformation">등록한 숙소가 없습니다.</div>
					    </c:when>
					    <c:otherwise>
					        <c:forEach items="${registeredList}" var="acc">
					        	<%-- <form action="${pageContext.request.contextPath}/spring-project/myPage?user_id=${user.user_id}" method="get">
									<button class="backBtn">뒤로가기</button>
								</form> --%>
					            <div class="main__reserved__row">
					                <div class="main__reserved__column">
					                    <%-- <img src="<c:url value='ImageServlet'/>?imageId=${acc.image_id}"
					                        alt="Image" class="recommend__img" width="100%" height="width">  --%>
					                    <img class="recommend__img" width="100%" height="width" id ="profileImg" src = "/upload/displayFile?fileName=${acc.image_name}">   
					                </div>
					                <div class="main__reserved__column">
					                    <div class="main__reserved__title">${acc.name}</div>
					                    <div class="main__reserved__script">
					                    	<input class="acc_id" type="hidden" value="${acc.acc_id}"/>
					                    	<input class="user_id" type="hidden" value="${acc.user_id}"/>
					                        위치 : ${acc.location}<br>
					                        가격 : ${acc.price}<br>
					                        <div>
							                    <button type="button" name="cancel" onclick="removeAccommodation(event);">숙소삭제</button>
							            	</div>                      
					                    </div>
					                </div>
					            </div>
					            
					        </c:forEach>
					    </c:otherwise>
					</c:choose>
		</div>
	</main>
</body>
</html>