<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>list.jsp</title>
<style>
     .main_image {
       position: relative;
       display: flex; 
       justify-content: center;
       width : 80%;
     }
     .main_image_text {
       position: absolute;
       top: 50%;
       left: 50%;
       transform: translate( -50%, -50% );
       color: white;
     }
     
     .main_image_text2 {
       position: absolute;
       top: 70%;
       left: 50%;
       transform: translate( -50%, -50% );
       color: white;
     }
     
     .main_image_text3 {
       position: absolute;
       top: 80%;
       left: 50%;
       transform: translate( -50%, -50% );
       color: white;
     }
        
        

    </style>

</head>
<body>

<%@include file ="./Header.jsp" %>
   
   <c:forEach items="${requestScope.hotel}" var="hotel">

          
          <div class="main_image" style="margin: 0 auto;">
               <img src="${hotel.hotelImagePath}" onclick="location.href='hotel?command=room&hotelNum=${hotel.hotelNum}'" width="100%" height="500px">

             <h1 class="main_image_text" style="font-size:50px">${hotel.hotelName}</h1>
            <h2 class="main_image_text2"> ${hotel.hotelLocation}</h2>
            <h3 class="main_image_text3"> 별점 : ${hotel.star}&nbsp;&nbsp;등급 : ${hotel.hotelGrade}성</h3>
         </div>
   </c:forEach>
    
   
</body>
</html>
