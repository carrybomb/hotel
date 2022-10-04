<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Dongle:wght@700&display=swap" rel="stylesheet">
<link href="Header.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>Header</title>
</head>
<body>
	<header class="main_header">
		<div class="header_content">
			<p id="header_title" onclick="location.href='index.html'">야 여기어때?</p>
			<p id="header_hotel">🏨 Hotel</p>
			<div class="logout_box">
				<form method="post" name="logOut">
					<input type="submit" id="logout" name="command" value="Log Out" formaction="login">	
					<input type="submit" id="mypage" name="command" value="MyPage" formaction="member">
				</form>
			</div>	
		</div>
	</header>
	
	<script type="text/javascript">
	</script>
</body>
</html>