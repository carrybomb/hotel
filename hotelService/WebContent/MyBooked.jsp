<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.Date, java.util.ArrayList, java.util.HashMap" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%-- <%
	Date nowTime = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	String time = (String)sf.format(nowTime);
	
	String dbDate = (String)((HashMap)((ArrayList)request.getAttribute("myBooked")).get(0)).get("checkinDate");
	out.print( ((HashMap)((ArrayList)request.getAttribute("myBooked")).get(0)).get("checkinDate") );
%> --%>
<!DOCTYPE html>
<html>
<head>
<link href="MyBooked.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script language=javascript>

function sendCancel(){
	document.requestForm.command.value ="cancel";
	document.requestForm.submit();
}	

</script>
</head>
<body>
<%@include file ="./Header.jsp" %>
<h3 align="center" style="color: #BE8C63;">나의 예약 내역</h3>
<hr/>
<form name="requestForm" method=post action="member">
<table align="center" border="0" cellpadding="5" cellspacing="2" width="100%" bordercolordark="white" bordercolorlight="black">
	<tr>
        <td style="background: #F8EDE3;">
            <p align="center"><font color="#BE8C63"><b><span style="font-size:9pt;">예약번호</span></b></font></p>
        </td>
        <td style="background: #F8EDE3;">
            <p align="center"><font color="#BE8C63"><b><span style="font-size:9pt;">호텔명</span></b></font></p>
        </td>
        <td style="background: #F8EDE3;">
            <p align="center"><font color="#BE8C63"><b><span style="font-size:9pt;">객실등급</span></b></font></p>
        </td>
        <td style="background: #F8EDE3;">
            <p align="center"><font color="#BE8C63"><b><span style="font-size:9pt;">객실번호</span></b></font></p>
        </td>
        <td style="background: #F8EDE3;">
            <p align="center"><font color="#BE8C63"><b><span style="font-size:9pt;">호텔위치</span></b></font></p>
        </td>
        <td style="background: #F8EDE3;">
            <p align="center"><font color="#BE8C63"><b><span style="font-size:9pt;">체크인 날짜</span></b></font></p>
        </td>
        <td style="background: #F8EDE3;">
            <p align="center"><font color="#BE8C63"><b><span style="font-size:9pt;">체크아웃 날짜</span></b></font></p>
        </td>
        <td style="background: #F8EDE3;">
            <p align="center"><font color="#BE8C63"><b><span style="font-size:9pt;">예약신청날짜</span></b></font></p>
        </td>
        <td style="background: #F8EDE3;">
            <p align="center"><font color="#BE8C63"><b><span style="font-size:9pt;">예약 취소</span></b></font></p>
        </td>
    </tr>
    
<%--  	<c:if test="${empty bookingList || fn:length(bookingList) == 0}">
		<tr>
	        <td colspan="8">
	            <p align="center"><b><span style="font-size:9pt;">등록된 예약일정이 없습니다</span></b></p>
	        </td>
	    </tr>
	</c:if> --%>

	<%-- ArrayList에  GuestBookBean 객체를 하나하나 data라는 반복 대입해서 사용 --%>
	<c:forEach items="${requestScope.myBooked}" var="data" varStatus="loop">
		    <tr>
		    	
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt; color: #BE8C63;" >${data.bookingNum}</span></p>
		        </td>
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt; color: #BE8C63;">${data.hotelName}</span></p>
		        </td>
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt; color: #BE8C63;">${data.category}</span></p>
		        </td>
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt; color: #BE8C63;">${data.roomName}</span></p>
		        </td>
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt; color: #BE8C63;">${data.hotelLocation}</span></p>
		        </td>
		        <td bgcolor="">
					<p align="center"><span style="font-size:9pt; color: #BE8C63;">${data.checkinDate}</span></p>
		        </td>
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt; color: #BE8C63;">${data.checkoutDate}</span></p>
		        </td>
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt; color: #BE8C63;">${data.bookedDate}</span></p>
		        </td>
		        <td bgcolor="">
		        	<p align="center">
		        		<c:choose>
		        			<c:when test="${data.cancelFlag == 'true'}">
		        				<input type=hidden name=bookingNum value="${data.bookingNum}">
		        				<input type=hidden name="command" value="cancel">
								<input style=background-color:red;color:white type=submit value="예약 취소">
							</c:when>
							<c:otherwise>
								<input readonly="readonly" style="background-color:grey;color:white" type=button value="예약 취소"/>
							</c:otherwise>
		        			
		        		</c:choose>
		            	
		            </p>
		        </td>
		    </tr>
	</c:forEach>
</table>
</form>
</body>
</html>