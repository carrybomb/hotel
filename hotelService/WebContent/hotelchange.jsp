<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link href="./listAll.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>update.jsp</title>
<script language=javascript>
function sendUpdate(){
		document.requestForm.command.value ="hotelupdate";
		document.requestForm.submit();
}

function sendDelete(){
		document.requestForm.command.value ="hoteldelete";
		document.requestForm.submit();			
}	

function sendRoom(){
		document.requestForm.command.value ="roomList";
		document.requestForm.submit();			
}	
</script>
</head>
<body>
	<%@ include file="./Header.jsp"%>

	<div class="senter_wrapper">
		<%@ include file="./Menu.jsp"%>
	<form name="requestForm" method=post action="admin">
		<table align="center" cellpadding="5" cellspacing="1" width="600" border="1">
			<tr>
				<td width="1220" height="20" colspan="2" bgcolor="F8EDE3">
					<p align="center"><font color="BE8C63" size="3"><b>
								${resultContent.hotelNum}번 호텔 수정하기</b></font>
					</p>
				</td>
			</tr>
			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">호텔 이름</span></b>
					</p>
				</td>
				<td width="450" height="20"><b><span
						style="font-size: 9pt;"> <input type=text name="name"
							size="30" value="${resultContent.hotelName}"></span></b></td>
			</tr>
			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">호텔 이미지</span></b>
					</p>
				</td>
				<td width="450" height="20"><b><span
						style="font-size: 9pt;"> <input type=text name="img"
							size="30" value="${resultContent.hotelImagePath}"></span></b></td>
			</tr>
			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">평점</span></b>
					</p>
				</td>
				<td width="450" height="20"><b><span
						style="font-size: 9pt;"> <input type=text name="star"
							size="50" value="${resultContent.star}"></span></b></td>
			</tr>
			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">호텔 위치</span></b>
					</p>
				</td>
				<td width="450" height="20"><b><span
						style="font-size: 9pt;"> <input type=text name="location"
							size="50" value="${resultContent.hotelLocation}"></span></b></td>
			</tr>
			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">호텔 등급</span></b>
					</p>
				</td>
				<td width="450" height="20"><b><span
						style="font-size: 9pt;"> <input type=text name="hotelgrade"
							size="50" value="${resultContent.hotelGrade}"></span></b></td>
			</tr>


			<tr >
        <td width="1220" height="20" colspan="2" border=0>
        <p align="center">
            <input type=hidden name=num	value="${requestScope.resultContent.hotelNum}"> 
				<input type=hidden name="command" value=""> 
				<input class="add_btn" type=button value="수정하기" onClick="sendUpdate()">
				<input class="add_btn" type=button value="삭제하기" onClick="sendDelete()">
				<input class="add_btn" type=button value="방정보" onClick="sendRoom()">
        </td>
    </tr>

		</table>
	</form>
	</div>
</body>
</html>