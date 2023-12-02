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
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	const CONTEXT_PATH = `${pageContext.request.contextPath}`;

	const INDEX_INPUT_TITLE = 0;
	const INDEX_INPUT_NAME = 1;
	const INDEX_INPUT_REG_DATE = 2;
	const INDEX_INPUT_CONTENT = 3;

	
	function moveToList(){
		location.href = `${CONTEXT_PATH}/board/list`;
	}
	
	
	function register() {
		console.log("register()");

		const inputElements = document.getElementsByClassName('data-inputs');

		/* if (!checkInputElementValue(INDEX_INPUT_TITLE)) {
			return;
		} else if (!checkInputElementValue(INDEX_INPUT_NAME)) {
			return;
		} else if (!checkInputElementValue(INDEX_INPUT_REG_DATE)) {
			return;
		} else if (!checkInputElementValue(INDEX_INPUT_CONTENT)) {
			return;
		} */
		if (confirm("정말 등록하시겠습니까?")) {
			const form = document.myForm;
			console.log(form);
			form.submit();
		}
	}

	function checkInputElementValue(index) {
		const inputElements = document.getElementsByClassName('data-inputs');
		let result = false;
		if (inputElements[index].value == '') {
			let msg = inputElements[index].id + "작성하시오!";
			alert(msg);
			inputElements[index].focus();
			return result;
		}
		return result;
	}

	function getBoardEntity() {
		const id = document.getElementById("id").value;
		const name = document.getElementById("name").value;
		const title = document.getElementById("title").value;
		const content = document.getElementById("content").value;
		const reg_date = document.getElementById("reg_date").value;
		const up_date = document.getElementById("up_date").value;
		//const custid = document.getElementById("custId").value;

		const jsonObj = {
			name : name,
			title : title,
			content : content,
			id : id,
			reg_date : reg_date,
			up_date : up_date
		}
		return jsonObj
	}


	function reset() {
		const inputElements = document.getElementsByTag("input");
		console.log(inputElements);
	}
</script>
</head>
<body id="board-content__body">
	<%@ include file="/WEB-INF/views/import/header.jsp"%>
	<form name="myForm"
		action="${pageContext.request.contextPath}/board/register" method="post">
		<main>
			<h2 class="title">${board.title }</h2>

			<table>
				<tr>
					<td>제목</td>
					<td colspan="3"><input class="data-inputs" type="text"
						id="title" name="title" value=""></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td><input type="text" class="data-inputs" id="name"
						name="name" value=""></td>
					<td>등록일</td>
					<td><input class="data-inputs" type="datetime-local"
						id="reg_date" name="reg_dates" value=""></td>
				</tr>
				<tr>
					<td>내용</td>
					<td colspan="3"><input class="data-inputs" type="text"
						id="content" name="content" value=""></td>
				</tr>
			</table>
			<div class="button-bar">
				<button type="button" onclick="register();">등록</button>
				<button type="reset">초기화</button> <!-- onclick="reset();" -->
				<button type="button" onclick="moveToList();">목록</button>
			</div>
		</main>
	</form>
</body>
</html>