<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>update.jsp</title>
<script language=javascript>
function sendUpdate(){
		document.requestForm.command.value ="memberupdate";
		document.requestForm.submit();
}

function sendDelete(){
		var password = prompt("삭제할 게시물의 비밀번호를 입력하세요");	
		if(password){
			document.requestForm.command.value ="memberdelete";
			document.requestForm.password.value = password;
			document.requestForm.submit();			
		}else{
			return false;
		}
}	
</script>
</head>
<body>
	<form name="requestForm" method=post action="admin">
		<table align="center" cellpadding="5" cellspacing="1" width="600" border="1">
			<tr>
				<td width="1220" height="20" colspan="2" bgcolor="#336699">
					<p align="center">
						<font color="white" size="3"><b>
								${resultContent.memberNum}번 멤버 수정하기</b></font>
					</p>
				</td>
			</tr>
			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">이름</span></b>
					</p>
				</td>
				<td width="450" height="20"><b><span
						style="font-size: 9pt;"> <input type=text name="name"
							size="30" value="${resultContent.memberName}"></span></b></td>
			</tr>
			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">아이디</span></b>
					</p>
				</td>
				<td width="450" height="20"><b><span
						style="font-size: 9pt;"> <input type=text name="id"
							size="30" value="${resultContent.id}"></span></b></td>
			</tr>
			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">비밀번호</span></b>
					</p>
				</td>
				<td width="450" height="20"><b><span
						style="font-size: 9pt;"> <input type=text name="pw"
							size="50" value="${resultContent.pw}"></span></b></td>
			</tr>
			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">전화번호</span></b>
					</p>
				</td>
				<td width="450" height="20"><b><span
						style="font-size: 9pt;"> <input type=text name="tel"
							size="50" value="${resultContent.tel}"></span></b></td>
			</tr>
			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">이메일</span></b>
					</p>
				</td>
				<td width="450" height="20"><b><span
						style="font-size: 9pt;"> <input type=text name="email"
							size="50" value="${resultContent.email}"></span></b></td>
			</tr>
			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">등급</span></b>
					</p>
				</td>
				<td width="450" height="20"><b><span
						style="font-size: 9pt;"> <input type=text name="grade"
							size="50" value="${resultContent.memberGrade}"></span></b></td>
			</tr>
			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">역할</span></b>
					</p>
				</td>
				<td width="450" height="20"><b><span
						style="font-size: 9pt;"> <input type=text name="authority"
							size="50" value="${resultContent.authority}"></span></b></td>
			</tr>

			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">&nbsp;</span></b>
					</p>
				</td>
				<td height="20" colspan="3">
				<input type=hidden name=num	value="${requestScope.resultContent.memberNum}"> 
				<input type=hidden name="command" value=""> 
				<input type=hidden name="password" value=""> 
				<input type=button value="수정하기" onClick="sendUpdate()">
				<input type=button value="삭제하기" onClick="sendDelete()"></td>
			</tr>

		</table>
	</form>
</body>
</html>