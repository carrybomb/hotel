<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="bookingForm.css" rel="stylesheet" type="text/css">
<title>bookingForm.jsp</title>

</head>
<body>
<%@include file ="./Header.jsp" %>


<form action="member" name="command" method="post">
<table align="center" border="0" cellpadding="5" cellspacing="2" width="100%" bordercolordark="white" bordercolorlight="black">


   <h3 class="info">예약일자 : ${requestScope.bookingForm[0].checkIn} ~ ${requestScope.bookingForm[0].checkOut}</h3>
   
   <h3 class="info"><방 선택></h3>
   <c:forEach items="${requestScope.bookingForm}" var="bookingForm" varStatus="status">
          <article class="info">
              <div>
                 <p>
                    <input type="radio" name="radio" value="${bookingForm.roomName}">${bookingForm.roomName}
                    <input type="hidden" name="category" value="${bookingForm.category}">
                    <input type="hidden" name="roomNum" value="${bookingForm.roomNum }">
                    <input type="hidden" name="checkIn" value="${bookingForm.checkIn }">
                    <input type="hidden" name="checkOut" value="${bookingForm.checkOut }">
                 </p>
              </div>

      </article>       
   </c:forEach> 
   <div class="info">
	     예약인원 : 
	   <select name="guestNum" class="option">
	      <c:forEach var="i" begin="1" end="${requestScope.bookingForm[0].maxGuestNum}">
	         <option value="${i}">${i}명</option>
	      </c:forEach>
	   </select>
   </div>
   <div class="button">
            <input type="hidden" name="command" value="booking">
         	<input class="booking" type="submit" value="예약하기">
   </div>
</table>
</form>

</body>
</html>