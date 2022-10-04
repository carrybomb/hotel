<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="find_pw.css" rel="stylesheet" type="text/css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Dongle:wght@700&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<header class="main_header">
		<div class="header_content">
			<p id="header_title">야 여기어때?</p>
			<p id="header_hotel">🏨 Hotel</p>		
		</div>
	</header>
	
	<h1 class="find_pw_title">비밀번호 찾기</h1>
	<div class="find_pw_box">
		<form class="find_pw_form" name="findForm">
		<input type="hidden" name="command" value="findPw">
			<div class="input_box">
				<span class="input_span">아이디 : </span><input class="input" name="id" type="text" placeholder="아이디를 입력해주세요">
				<br/>
				<span class="input_span">이메일 : </span><input class="input" name="email" type="text" placeholder="이메일을 입력해주세요">
				<br/>
				<input type="button" class="submit_button" onclick="checkValue()" value="비밀번호 찾기">
			</div>
		</form>
	</div>
	<script type="text/javascript">
	async function checkValue() {
		const findForm = document.findForm;
		const member_id = findForm.id.value;
		const member_email = findForm.email.value;
		const result = await axios.get("http://localhost:8080/step10_TeamProject/login?command=findPw&id="+member_id+"&email="+member_email)
		.then(res => res.headers.result);
		
		if(result != "") {
			alert("당신의 비밀번호는 " + result + " 입니다.");
		} else {
			alert("아이디 정보나 이메일 정보가 존재하지 않거나 일치하지 않습니다.");
		}
	}
	</script>
</body>
</html>