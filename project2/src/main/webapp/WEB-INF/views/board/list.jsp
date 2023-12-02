<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<link rel="stylesheet"
   href="<%=request.getContextPath()%>/resources/css/common.css">
<link rel="stylesheet"
   href="<%=request.getContextPath()%>/resources/css/table.css">
<link rel="stylesheet"
   href="<%=request.getContextPath()%>/resources/css/pagination.css">
<meta charset="UTF-8">
<title>board_list</title>
<script
  src="https://code.jquery.com/jquery-3.7.1.js"
  integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
  crossorigin="anonymous"></script>
<script>
	const CONTEXT_PATH = `${pageContext.request.contextPath}`;
	const message = `${requestScope.message}`;
	window.onload = function(){
		if(!(message == '')){
			alert(message);
		}
	}
	
	function getPublicApi(){
		const url = 'http://apis.data.go.kr/B552584/EvCharger/getChargerInfo';
		const queryString = '';
		}
	function ajaxSend(method, dataJson){

		console.log(dataJson);
		console.log(dataJson);
		$.ajax( {
			url:url+queryString,
			method: "get",
			headers:{"Content-Type": "application/json;charset=UTF-8"},
			//dataType:"text",
			//data : JSON.stringify(dataJson),
			success:function(data,textStatus,jqXHR){
				console.log("success()");
				console.log(data);
				console.log(typeof data);										
			},
			error:function(jqXHR, textStatus, errorThrown){
				console.log("error()");
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
			}
			
		} );
	}
	
	function removeList(){
		const checkboxElements = document.getElementsByName("data-selected");
		console.log(checkboxElements);
		const jsonArray = new Array();
		
		for(var i = 0; i < checkboxElements.length; i++){
			if(checkboxElements[i].checked == true){
				console.log(checkboxElements[i]);
				jsonArray.push(checkboxElements[i].value);
				checkboxElements[i].parentElement.parentElement.remove();
				i--;
			}
		}
		/*checkbox 선택 안됐을 때 로직*/
		console.log(jsonArray);
		
		
		
		const dataJson = {list:jsonArray};
		console.log(dataJson);
		ajaxSend("DELETE", dataJson);
	}
	
	function ajaxSend(method, dataJson){

		console.log(method);
		console.log(dataJson);
		$.ajax( {
			url:CONTEXT_PATH + "/board/remove-list",
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
	<%@ include file ="/WEB-INF/views/import/header.jsp" %>
	<main>
	<table border="1">
	<div>
		<button type="button" onclick="removeList();">선택 삭제</button>
		<button type="button" onclick="location.href=`${pageContext.request.contextPath}/board/register`">등록</button>
		<button type="button" onclick="getPublicApi">공공데이터</button>
	</div>
    <thead>
        <tr>
        	<th>선택</th>
            <th>게시글 번호</th>
            <th>제목</th>
            <th>내용</th>
            <th>등록일</th>
            <!-- <th>up_date</th> -->
        </tr>
    </thead>
    <tbody>
        <c:forEach var="board" items="${board_list}">
            <tr>
            	<td><input type="checkbox" name="data-selected" value="${board.id}" /></td>
                <td>${board.id}</td>
                <td><a href="${contextPath}/board/read?id=${board.id}">${board.title}</a></td>
                <td>${board.content}</td>
                <td>${board.reg_date}</td>
                <%-- <td>${board.up_date}</td> --%>
            </tr>
        </c:forEach>
    </tbody>
</table>
</main>
<%@ include file ="/WEB-INF/views/import/page_nation.jsp" %>
</body>
</html>