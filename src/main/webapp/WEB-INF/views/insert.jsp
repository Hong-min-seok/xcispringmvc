<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert.jsp</title>
</head>
<body>
	<form action="insertAction" method="get">
		<table>
			<caption>게시물 쓰기</caption>
			<tr>
				<th>제목</th>
				<td><input type = "text" name ="title" autofocus="autofocus" required ="required"/></td>
			</tr>
			
			<tr>
				<th>이름</th>
				<td> <input type = "text" name ="name"/></td>
				
			</tr>
			
			<tr>
				<th>비밀번호</th>
				<td> <input  type ="password" name ="password"/></td>
			</tr>
			
			<tr>
				<th>내용</th>
				<td><textarea name ="content" rows="5" cols="40"></textarea></td>
			</tr>
			
			<tr>
				<td colspan="2"  align ="center" ><input type ="submit" value="완료"> </td>
			</tr>
		</table>
	</form>
</body>
</html>