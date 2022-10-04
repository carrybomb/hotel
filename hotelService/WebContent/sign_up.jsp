<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="sign_up.css" rel="stylesheet" type="text/css">
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
	
	<h1 class="sign_up_title">회원가입</h1>
	<div class="sign_up">
		<form id="signUpForm" name="joinForm">
		<input type="hidden" name="command" value="join">
			<div class="input_box">
				<span class="text">아이디 : </span><input class="id" type="text" id="userId" name="user_id"> 
				<span class="check_id" id="checkId" onclick="checkId()">중복확인</span>
				<input type="hidden" name="idDuplication" value="idUncheck">
				<br/>
				<span class="text">비밀번호 : </span><input class="pw" type="password" name="pw"> <br/>
				<span class="text">이름 : </span><input class="name" type="text" name="name"> <br/>
				<span class="text">전화번호 : </span><input class="tel" type="text" name="tel"> <br/>
				<span class="text">이메일 : </span><input class="email" type="text" name="email"> <br/>
				<input class="join" type="button" onclick="checkValue()" value="회원가입">
			</div> 		
 		</form>	
	</div>
	<script type="text/javascript">
		async function checkId() {
			const joinForm = document.joinForm;
			const userId = document.getElementById("userId").value;
			if(userId.length == 0 || userId == "") {
				alert("아이디를 입력해주세요.");
				joinForm.userId.focus();
				return;
			}
			const result = await axios.get("http://localhost:8080/step10_TeamProject/login?command=checkId&user_id="+userId)
			.then(res => res.headers.result);
			
			if(result === "true"){
				alert("사용가능한 ID입니다.");
				joinForm.idDuplication.value= "idCheck";
			}else {
				alert("사용불가능한 ID입니다.");
			}
		}
		
		function checkValue() {
			const joinForm = document.joinForm;
			const id = joinForm.user_id.value;
			const pwd = joinForm.pw.value;
			const name = joinForm.name.value;
			const tel = joinForm.tel.value;
			const email = joinForm.email.value;
			
			if(id.length == 0 || id == "") {
				alert("아이디를 입력해주세요.");
				joinForm.id.focus();
			} else if(joinForm.idDuplication.value != "idCheck") {
				alert("아이디 중복체크를 해주세요.");
			} else if(pwd.length == 0 || pwd == "") {
				alert("비밀번호를 입력해주세요.");
				joinForm.pwd.focus();
			} else if(name.length == 0 || name == "") {
				alert("이름을 입력해주세요.");
				joinForm.name.focus();
			} else if(tel.length == 0 || tel == "") {
				alert("전화번호를 입력해주세요.");
				joinForm.tel.focus();
			} else if(email.length == 0 || email == "") {
				alert("이메일을 입력해주세요.");
				joinForm.email.focus();
			} else { 
				joinForm.method="post";
				joinForm.action="login";
				joinForm.submit();
			}
			
		}
	</script>
</body>
</html>