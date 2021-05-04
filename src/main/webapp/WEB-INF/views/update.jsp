<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="dto" value="${articleDTO}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert.jsp</title>
</head>
<body>
	<form action="updateAction" method="get">
		<table>
			<caption>게시물 수정</caption>
			<tr>
				<th>번호</th>
				<td>${dto.no}<input type = "hidden" name ="no" value="${dto.no}"/></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type = "text" name ="title" autofocus="autofocus" required ="required" value="${dto.title}"/></td>
			</tr>
			
			<tr>
				<th>이름</th>
				<td> <input type = "text" name ="name" value="${dto.name}"/></td>
				
			</tr>
			
			<tr>
				<th>비밀번호</th>
				<td> <input  type ="password" name ="password"/><br>
					* 처음 글 입력시 비밀번호를 재 입력하세요.
				</td>
			</tr>
			
			<tr>
				<th>내용</th>
				<td><textarea name ="content" rows="5" cols="40">${dto.content}</textarea></td>
			</tr>
			
			<tr>
				<td colspan="2"  align ="center" ><input type ="submit" value="완료"> </td>
			</tr>
		</table>
	</form>
</body>
</html>