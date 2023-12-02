<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/common.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/table.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/pagination.css">
<script
  src="https://code.jquery.com/jquery-3.7.1.js"
  integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
  crossorigin="anonymous"></script>
	<script>
		const CONTEXT_PATH = `${pageContext.request.contextPath}`;
		const INDEX_MODIFY_DELETE_LIST_BUTTON = 0;
		const INDEX_COMPLETE_CANCEL_BUTTON = 1;
		
		function ajaxSend(method, dataJson){

			console.log(dataJson);
			console.log(JSON.stringify(dataJson));
			$.ajax( {
				url:CONTEXT_PATH + "/board/modify",
				method: method,
				headers:{"Content-Type": "application/json;charset=UTF-8"},
				dataType:"text",
				data : JSON.stringify(dataJson),
				success:function(data,textStatus,jqXHR){
					console.log("success()");
					console.log(data);
					data = JSON.parse(data);
					alert(data.message);
					
					const h2Element = document.getElementById("h2Title");
					h2Element.textContent = data.title;
					
					reset();
				},
				error:function(jqXHR, textStatus, errorThrown){
					console.log("error()");
					console.log(jqXHR);
					console.log(textStatus);
					console.log(errorThrown);
				}
				
			} );
		}
		function reset(){
			cancel();
		}
		
		function getBoardEntity(){
			const id = document.getElementById("id").value;
			const name = document.getElementById("name").value;
			const title = document.getElementById("title").value;
			const content = document.getElementById("content").value;
			const reg_date = document.getElementById("reg_date").value;
			const up_date = document.getElementById("up_date").value;
			//const custid = document.getElementById("custId").value;

			const jsonObj = {
					name: name,
	   			    title: title,
	   			    content: content,
	   			    id: id,
	   			    reg_date: reg_date,
	   			    up_date: up_date
	   		 }
			return jsonObj
		}
		
		function completeModify(){
			
			var jsonObj = {key:'key-value'};
			
			jsonObj = getBoardEntity();
			ajaxSend("PUT",jsonObj);
		}
		function modify(){
			/*const button_bar = $(".button-bar");
			console.log(button_bar);
			button_bar.eq(INDEX_MODIFY_DELETE_LIST_BUTTON).hide();
			button_bar.eq(INDEX_COMPLETE_CANCEL_BUTTON).show();*/
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
			const titleElement = document.getElementsByName("title");
			const contentElement = document.getElementsByName("content");
			titleElement[0].disabled = disabled;
			contentElement[0].disabled = disabled;
		}
		
		function deleteContent(){
			const id = document.getElementById("id").value;
			console.log(id);
			const jsonObj = {
	   			    id: id,
	   		 }
			ajaxSend2("DELETE", jsonObj);
		}
		
		function ajaxSend2(method, dataJson){

			console.log(dataJson);
			$.ajax( {
				url:CONTEXT_PATH + "/board/remove",
				method: method,
				headers:{"Content-Type": "application/json;charset=UTF-8"},
				dataType:"text",
				//data : JSON.stringify(dataJson),
				success:function(data,textStatus,jqXHR){
					console.log("success()");
					//console.log(data);
					//data = JSON.parse(data);
					alert(data.message);
					
					reset();
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
<meta charset="UTF-8">
<title>board_content</title>
</head>
<body id="board-content__body">
	<%@ include file="/WEB-INF/views/import/header.jsp"%>
	<main>
		<h2 class="title" id="h2Title">${board.title }</h2>

		<table>
			<tr>
				<td>제목</td>
				<td colspan="3"><input type="text" id="title" name="title"
					value="${board.title }" disabled="disabled"></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" id="name" name="name" value="${board.name }"
					disabled="disabled"></td>
				<td>등록일</td>
				<td><input type="datetime-local" id="reg_date" name="reg_date"
					value="${board.reg_date }" disabled="disabled"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td colspan="3"><textarea id="content" name="content"
					 disabled="disabled">${board.content }</textarea></td>
				<td style="display:none"><input type="hidden" id="id" name="id" value="${board.id }"/></td>
				<td style="display:none"><input type="hidden" id="cust_id" name="custid" value="${board.id }"/></td>
				<td style="display:none"><input type="hidden" id="up_date" name="up_date" value="${board.up_date }"/></td>
			</tr>
		</table>
		<div class="button-bar">
			<button onclick="modify();">수정</button>
			<button onclick="deleteContent();">삭제</button>
			<button type="button" onclick="moveToList();">목록</button>
		</div>
		<div class="button-bar" style="display: none;">
			<button type="submit" onclick="return completeModify();">완료</button>
			<button onclick="cancel();">취소</button>
		</div>
	</main>
</body>
</html>