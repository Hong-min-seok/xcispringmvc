<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<table border="1">
		<caption>게시물 리스트</caption>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>이름</th>
			<th>날짜</th>
			<th>조회수</th>

		</tr>
		<c:forEach var="dto" items="${list}">
		<tr>
		
			<td>${dto.no}</td>
			<td><a href="detail?no=${dto.no}">${dto.title}</a></td>
			<td>${dto.name}</td>
			<td>${dto.regdate}</td>
			<td>${dto.readcount}</td>
		</tr>
		</c:forEach>
	</table>


	<c:if test="${pagingDTO.startPage == 1}">
		<a class="page-link" href="#">Previous</a>
	</c:if>
	<c:if test="${pagingDTO.startPage != 1}">
		<a class="page-link" href="list?page=${pagingDTO.startPage-1}">Previous</a>
	</c:if>
   	
   	<c:forEach var="i" begin="${pagingDTO.startPage}" end="${pagingDTO.endPage}">
		<c:if test="${pagingDTO.page == i}">
	    	<a class="page-link" href="#">${i}</a>
		</c:if>
		<c:if test="${pagingDTO.page != i}">
			<a class="page-link" href="list?page=${i}">${i}</a>
		</c:if>
	</c:forEach>
   	

   	<c:if test="${pagingDTO.endPage == pagingDTO.totalPage}">
		<a class="page-link" href="#">Next</a>
	</c:if>
	<c:if test="${pagingDTO.endPage != pagingDTO.totalPage}">
		<a class="page-link" href="list?page=${pagingDTO.endPage+1}">Next</a>
	</c:if>
    
    <br> 
	<a href="insert">글쓰기</a>
</body>
</html>