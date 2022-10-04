<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="hotelServiceDTO.memberDTO" %>
<!DOCTYPE html>
<html>
<head>
<link href="MyPage.css" rel="stylesheet" type="text/css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Dongle:wght@700&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 	<% HttpSession session = request.getSession();
		response.getWriter().print(session.getAttribute("member"));
		memberDTO member = (memberDTO)session.getAttribute("member");
		session.setAttribute("test", member);
	%> --%>
	
	<header class="main_header">
		<div class="header_content">
			<p id="header_title" onclick="location.href='index.html'">야 여기어때?</p>
			<p id="header_hotel">🏨 Hotel</p>
			<form class="logout_form" action="login" method="post">
				<input id="logOut" type="submit" name="command" value="Log Out">
			</form>	
		</div>
	</header>
	
	<h1 class="title">My Info</h1>
	<form method="post">
			<table class="myInfo_table">
				<tr class="tr">
					<td class="left">아이디</td>
					<td class="right"><input type="hidden" name="id" value="${ member.id }">${ member.id }</td>
				</tr>
				<tr>
					<td class="left">이름</td>
					<td class="right"><input type="hidden" name="name" value="${ member.memberName }">${ member.memberName }</td>
				</tr>
				<tr>
					<td class="left">전화번호</td>
					<td class="right"><input type="hidden" name="tel" value="${ member.tel }">${ member.tel }</td>
				</tr>
				<tr>
					<td class="left">이메일</td>
					<td class="right"><input type="hidden" name="email" value="${ member.email }">${ member.email }</td>
				</tr>
				<tr>
					<td class="left">등급</td>
					<td class="right"><input type="hidden" name="grade" value="${ member.memberGrade }">${ member.memberGrade }성</td>
				</tr>
			</table>
		<input class="modify" type="submit" name="command" value="정보수정" formaction="member">
		<input class="delete" type="submit" name="command" value="회원탈퇴" formaction="member">
		<input class="mybooked" type="submit" name="command" value="예약정보" formaction="member">
	</form>
</body>
</html>