<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<link href="Login.css" rel="stylesheet" type="text/css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Dongle:wght@700&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header class="main_header">
		<div class="header_content">
			<p id="header_title">야 여기어때?</p>
			<p id="header_hotel">🏨 Hotel</p>		
		</div>
	</header>
	
	<form name="loginForm">
	<input type="hidden" name="command" value="login">
		<h1 id="form_title">Log In</h1>
		<div class="login_input">
			<span class="input_text">아이디 : </span><input type="text" class="id_input" name="id" placeholder="ID를 입력하세요"> <br>
			<span class="input_text">비밀번호 :</span> <input type="password" class="pw_input" name="pwd" placeholder="PASSWORD를 입력하세요">		
			<button class="submit" type="button" onclick="checkValue()">Log In</button>
		</div> 
	</form>
	<div class="other">
		<button onclick="location='sign_up.jsp'" class="sign_up">회원가입</button> &nbsp; &nbsp;
		<button onclick="location='find_pw.jsp'" class="find_pw">비밀번호 찾기</button>
	</div>
	<script type="text/javascript">
		function checkValue() {
			var loginForm = document.loginForm;
			var id = loginForm.id.value;
			var pwd = loginForm.pwd.value;
			
			if(id.length == 0 || id == "") {
				alert("아이디를 입력해주세요.");
				loginForm.id.focus();
			} else if(pwd.length == 0 || pwd == "") {
				alert("비밀번호를 입력해주세요.");
				loginForm.pwd.focus();
			} else {
				loginForm.method="post";
				loginForm.action="login";
				loginForm.submit();
			}
		}
	</script>
</body>
</html>