<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Dongle:wght@700&display=swap" rel="stylesheet">
<link href="./Menu.css" rel="stylesheet" type="text/css">
<meta charset="utf-8">
<title>Header</title>
</head>
<body>
	<nav>
		<ul class="menu_content">
			<li><a href="admin?command=memberList">사용자관리</a></li>
			<li><a href="admin?command=bookingList">예약관리</a></li>
			<li><a href="admin?command=hotelList">호텔관리</a></li>
		</ul>	
	</nav>
</body>
</html>