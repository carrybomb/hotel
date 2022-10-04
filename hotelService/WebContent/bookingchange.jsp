<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>update.jsp</title>
<script language=javascript>
function sendDelete(){
		document.requestForm.command.value ="bookingdelete";
		document.requestForm.submit();			
		return false;
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
								${resultContent.bookingNum}번 예약 수정하기</b></font>
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
						style="font-size: 9pt;"> <input type=text name="membername"
							size="30" value="${resultContent.memberName}"></span></b></td>
			</tr>
			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">방코드</span></b>
					</p>
				</td>
				<td width="450" height="20"><b><span
						style="font-size: 9pt;"> <input type=text name="roomNum"
							size="30" value="${resultContent.roomNum}"></span></b></td>
			</tr>
			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">체크인날짜</span></b>
					</p>
				</td>
				<td width="450" height="20"><b><span
						style="font-size: 9pt;"> <input type=text name="checkin_date"
							size="50" value="${resultContent.checkin_date}"></span></b></td>
			</tr>
			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">체크아웃날짜</span></b>
					</p>
				</td>
				<td width="450" height="20"><b><span
						style="font-size: 9pt;"> <input type=text name="checkout_Num"
							size="50" value="${resultContent.checkout_date}"></span></b></td>
			</tr>
			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">예약신청일자</span></b>
					</p>
				</td>
				<td width="450" height="20"><b><span
						style="font-size: 9pt;"> <input type=text name="booked_date"
							size="50" value="${resultContent.booked_date}"></span></b></td>
			</tr>
			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">예약취소일자</span></b>
					</p>
				</td>
				<td width="450" height="20"><b><span
						style="font-size: 9pt;"> <input type=text name="booked_cancel_date"
							size="50" value="${resultContent.booked_cancel_date}"></span></b></td>
			</tr>
			

			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">&nbsp;</span></b>
					</p>
				</td>
				<td height="20" colspan="3">
				<input type=hidden name=num	value="${requestScope.resultContent.bookingNum}"> 
				<input type=hidden name="command" value=""> 
				<input type=button value="삭제하기" onClick="sendDelete()"></td>
			</tr>

		</table>
	</form>
</body>
</html>