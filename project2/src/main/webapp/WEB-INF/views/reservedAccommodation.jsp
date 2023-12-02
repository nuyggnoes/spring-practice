<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
   href="<%=request.getContextPath()%>/resources/css/style.css">
<meta charset="UTF-8">
<title>Spring Project</title>
<script
  src="https://code.jquery.com/jquery-3.7.1.js"
  integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
  crossorigin="anonymous"></script>
<script>
	
	const CONTEXT_PATH = `${pageContext.request.contextPath}`;
	
	function cancelReservation(event){
		console.log("cancel()");
		var clickedButton = event.target;
		console.log(clickedButton.parentElement.parentElement);
		var div = clickedButton.parentElement.parentElement;
		const inputAccHidden = div.getElementsByClassName("acc_id");
		const inputUserHidden = div.getElementsByClassName("customer_id");
		const acc_id = inputAccHidden[0].value;
        const customer_id = inputUserHidden[0].value;
		const dataJson={
				customer_id: customer_id,
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
			url:CONTEXT_PATH + "/spring-project/remove-reservation",
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
	
	
	function paySeq(event){
		
		var clickedButton = event.target;
		console.log(clickedButton.parentElement.parentElement);
		var div = clickedButton.parentElement.parentElement;
		
		const accNameElements = div.getElementsByClassName("main__reserved__title");
		const accPriceElements = div.getElementsByClassName("acc_price");
		const inputAccHidden = div.getElementsByClassName("acc_id");
		const cust_idElement = document.getElementsByClassName("customer_id");
		
		
		const acc_name = accNameElements[0].textContent;
		const acc_price = accPriceElements[0].textContent;
		const acc_id = inputAccHidden[0].value;
		const cust_id = cust_idElement[0].value;

		
		
		$.ajax({
			url:'/kakao/kakaopay',
			data:{
				acc_name:acc_name,
				acc_price:acc_price,
				acc_id:acc_id,
				cust_id : cust_id
				},
			dataType:'json',
			success:function(response){
				console.log(response);
				console.log(response.created_at);
				
				/* var box = response.next_redirect_pc_url;
				window.open(box); */
				location.href = response.next_redirect_pc_url;
			},
			error:function(error){
				alert(error);
			}
		});
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
					예약숙소조회
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
					    <c:when test="${empty reservedList}">
					        <div class="noInformation">예약된 숙소가 없습니다.</div>
					    </c:when>
					    <c:otherwise>
					        <c:forEach items="${reservedList}" var="acc">
					            <div class="main__reserved__row">
					                <div class="main__reserved__column">
					                    <img class="recommend__img" width="100%" height="width" id ="profileImg" src = "/upload/displayFile?fileName=${acc.image_name}">   
					                </div>
					                <div class="main__reserved__column">
					                    <div class="main__reserved__title" id="acc_name">${acc.name}</div>
					                    <div class="main__reserved__script">
					                    	<input class="acc_id" type="hidden" value="${acc.acc_id}"/>
					                    	<input class="customer_id" type="hidden" value="${acc.customer_id}"/>
					                        	위치 : <span>${acc.location}</span><br>
					                        	가격 : <span class="acc_price">${acc.price}</span>원<br>
					                        	예약날짜 : <div> ${acc.check_in} ~ ${acc.check_out}</div>
					                        <div>
							                    <button type="button" name="cancel" onclick="cancelReservation(event);">예약취소</button>
							            	</div>                      
					                    </div>
 					                    <div>
					                    	<button onclick="paySeq(event);">카카오페이로 결제</button>
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