<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>update.jsp</title>
<script language=javascript>
function sendUpdate(){
		document.requestForm.command.value ="roomupdate";
		document.requestForm.submit();
}

function sendDelete(){
		document.requestForm.command.value ="roomdelete";
		document.requestForm.submit();			
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
								${resultContent.roomNum}번 방 수정하기</b></font>
					</p>
				</td>
			</tr>

			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">방 호수</span></b>
					</p>
				</td>
				<td width="450" height="20"><b><span
						style="font-size: 9pt;"> <input type=text name="roomName"
							size="30" value="${resultContent.roomName}"></span></b></td>
			</tr>
			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">방 이미지</span></b>
					</p>
				</td>
				<td width="450" height="20"><b><span
						style="font-size: 9pt;"> <input type=text name="roomImage"
							size="50" value="${resultContent.roomImage}"></span></b></td>
			</tr>

			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">최대 손님 수</span></b>
					</p>
				</td>
				<td width="450" height="20"><b><span
						style="font-size: 9pt;"> <input type=text name="maxGuestNum"
							size="50" value="${resultContent.maxGuestNum}"></span></b></td>
			</tr>
			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">가격</span></b>
					</p>
				</td>
				<td width="450" height="20"><b><span
						style="font-size: 9pt;"> <input type=text name="price"
							size="50" value="${resultContent.price}"></span></b></td>
			</tr>
			
				<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">카테고리</span></b>
					</p>
				</td>
				<td width="450" height="20"><b><span
						style="font-size: 9pt;"> <input type=text name="category"
							size="50" value="${resultContent.category}"></span></b></td>
			</tr>

			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">&nbsp;</span></b>
					</p>
				</td>
				<td height="20" colspan="3">
				<input type=hidden name=num	value="${requestScope.resultContent.roomNum}"> 
				<input type=hidden name="command" value=""> 
				<input type=button value="수정하기" onClick="sendUpdate()">
				<input type=button value="삭제하기" onClick="sendDelete()">
				</td>
			</tr>

		</table>
	</form>
</body>
</html>