<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="modify.css" rel="stylesheet" type="text/css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Dongle:wght@700&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 class="title">My Info</h1>
	<form name="updateForm">
	<input type="hidden" name="command" value="update">
			<table class="myInfo_table">
				<tr class="tr">
					<td class="left">아이디</td>
					<td class="right"><input type="hidden" name="id" value="${ member.id }">${ member.id }</td>
				</tr>
				<tr>
					<td class="left">이름</td>
					<td class="right"><input class="input" type="text" name="name" value="${ member.memberName }" required="required"></td>
				</tr>
				<tr>
					<td class="left">전화번호</td>
					<td class="right"><input class="input" type="text" name="tel" value="${ member.tel }" required="required"></td>
				</tr>
				<tr>
					<td class="left">이메일</td>
					<td class="right"><input class="input" type="text" name="email" value="${ member.email }" required="required"></td>
				</tr>
				<tr>
					<td class="left">등급</td>
					<td class="right"><input type="hidden" name="grade" value="${ member.memberGrade }">${ member.memberGrade }성</td>
				</tr>
			</table>
		<input class="modify" type="button" onclick="update()" value="수정하기">
	</form>
	<script type="text/javascript">
		function update() {
			const updateForm = document.updateForm;
			const name = updateForm.name.value;
			const tel = updateForm.tel.value;
			const email = updateForm.email.value;
			
			if(name.length == 0 || name == "") {
				alert("이름을 입력해주세요.");
				updateForm.name.focus();
			} else if(tel.length == 0 || tel == "") {
				alert("전화번호를 입력해주세요.");
				updateForm.tel.focus();
			} else if(email.lenght == 0 || email == "") {
				alert("emaild을 입력해주세요.");
				updateForm.email.focus();
			} else {
				updateForm.method = "post";
				updateForm.action = "member";
				updateForm.submit();
			}
		}
	</script>
</body>
</html>