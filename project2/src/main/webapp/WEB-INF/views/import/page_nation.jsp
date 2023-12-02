<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<c:set var="pageAmount" value="&amount=${pageMaker.criteriaAmount}"></c:set>
	<c:set var="displayPageAmount" value="&displayPageAmount=${pageMaker.displayPageAmount}"></c:set	>
	<div class="pagination">
		
		<ul>
		<c:if test="${pageMaker.prev}">
			<li><a href="${pageContext.servletContext.contextPath }/board/list?pageNo=${pageMaker.startPage-1}${pageAmount}${displayPageAmount}">prev</a></li>
		</c:if>
		<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage}" varStatus="status">
		<c:choose>
		<c:when test="${pageMaker.criteriaPageNum == status.index }">
			<li><a href="${pageContext.servletContext.contextPath }/board/list?pageNo=${status.index}${pageAmount}${displayPageAmount}" class="on">${status.index }</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${pageContext.servletContext.contextPath }/board/list?pageNo=${status.index}${pageAmount}${displayPageAmount}" class="">${status.index }</a></li>
		</c:otherwise>
		</c:choose>	
		</c:forEach>
		<c:if test="${ pageMaker.next }">
          <li>
            <a href="${ pageContext.servletContext.contextPath }/board/list?pageNo=${ pageMaker.endPage + 1}${pageAmount}${displayPageAmount}">next</a>
          </li>
        </c:if>
		</ul>
	</div>

</body>
</html>