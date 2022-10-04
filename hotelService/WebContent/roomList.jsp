<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>roomList.jsp</title>
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<link href="roomList.css" rel="stylesheet" type="text/css">
<script>
$(function() {
  $('input[name="daterange"]').daterangepicker({
    opens: 'left'
  }, function(start, end, label) {
    console.log("A new date selection was made: " + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
  });
});
</script>
</head>
<body>
<table align="center" border="0" cellpadding="5" cellspacing="2" width="100%" bordercolordark="white" bordercolorlight="black">
	<!-- <tr>
        <td bgcolor="#336699">
            <p align="center">
            <font color="white"><b><span style="font-size:9pt;">번 호</span></b></font></p>
        </td>
        <td bgcolor="#336699">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">제 목</span></b></font></p>
        </td>
        <td bgcolor="#336699">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">작 성 자</span></b></font></p>
        </td>
        <td bgcolor="#336699">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">작 성 일</span></b></font></p>
        </td>
        <td bgcolor="#336699">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">조 회 수</span></b></font></p>
        </td>
    </tr> -->
    
 	<%-- <c:if test="${empty list || fn:length(list) == 0}">
		<tr>
	        <td colspan="5">
	            <p align="center"><b><span style="font-size:9pt;">등록된 방명록이 없습니다.</span></b></p>
	        </td>
	    </tr>
	</c:if> --%>
<%@include file ="./Header.jsp" %>
	<%-- ArrayList에  GuestBookBean 객체를 하나하나 data라는 반복 대입해서 사용 --%>
	
	<c:forEach items="${requestScope.room}" var="room">
		    <article class="list_box">
		        <div>
		            <p align="left">
		            <img alt="" src="${room.roomImage}">
		            </p>
		            
		        </div>
		        <div>
		        	<p>
			        	<a class="text">등급 : ${room.category}</a><br/>
			            <a class="text">최대인원 : ${room.maxGuestNum}</a><br/>
			            <a class="text">금액 : ${room.price}</a><br/>
			            <form action="member" name="command" method="post">
			            	<input type="hidden" name="command" value="bookingForm">
			            	<input type="hidden" name="category" value="${room.category}">
			            	<input type="hidden" name="hotelNum" value="${room.hotelNum }">
							<a class="text">입/퇴실 입자 선택 : </a><input class="date_box" type="text" name="daterange" value="" />
							<input class="submit" type="submit" value="예약하기">
						</form>
		        	</p>
		        </div>
		</article>	    
	</c:forEach>
	
 	
    <!-- <tr>
        <td bgcolor="">
            <p align="center"><span style="font-size:9pt;"></span></p>
        </td>
        <td bgcolor="">
			<p><span style="font-size:9pt;"><a href="ReadAContent.jsp?num= "></a></span></p>
        </td>
        <td bgcolor="">
            <p align="center"><span style="font-size:9pt;">
				<a href="mailto:"></a>
			</span></p>
        </td>
        <td bgcolor="">
            <p align="center"><span style="font-size:9pt;"></span></p>
        </td>
        <td bgcolor="">
            <p align="center"><span style="font-size:9pt;"></span></p>
        </td>
    </tr> -->

</table>
<hr>
<div align=right>
<!-- <span style="font-size:9pt;">&lt;<a href="write.html">글쓰기</a>&gt;</span></div> -->
</body>
</html>
