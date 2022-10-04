<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<link href="./listAll.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>bookingList.jsp</title>
</head>
<body>
<%@ include file="./Header.jsp" %>
<div class="senter_wrapper">
<%@ include file="./Menu.jsp" %>
	
	<div class="member_search">
		<form name="search_member_list" method="post" action="admin">
		    <div class="search_flex">
		    <input type="hidden" name="command" value="hotelList">
		 		<input class="search_text" type=text name="name" size="50" placeholder="이름입력"></span></b></td>
               	<input class="search_text" type=text name="location" size="50" placeholder="지역 입력"></span></b></td>
		   		<input class="search_btn" type="submit" value="검색">
		    </div>
		</form>
	</div>
	
<table align="center" border="0" cellpadding="5" cellspacing="2" width="100%" bordercolordark="white" bordercolorlight="black">
	<tr class="table_title">
         <td>
            <p align="center" class="text_color"><b><span style="font-size:9pt;">호텔 이름</span></b></font></p>
        </td>
         <td>
            <p align="center" class="text_color"><b><span style="font-size:9pt;">호텔 이미지</span></b></font></p>
        </td>
        <td>
            <p align="center" class="text_color"><b><span style="font-size:9pt;">호텔 등급</span></b></font></p>
        </td>
        <td>
            <p align="center" class="text_color"><b><span style="font-size:9pt;">호텔위치</span></b></font></p>
        </td>
        <td>
            <p align="center" class="text_color"><b><span style="font-size:9pt;">평점</span></b></font></p>
        </td>
         <td>
            <p align="center" class="text_color"><b><span style="font-size:9pt;">수정하기</span></b></font></p>
        </td>
    </tr>
    
 	<c:if test="${empty hotelList || fn:length(hotelList) == 0}">
		<tr>
	        <td colspan="8">
	            <p align="center"><b><span style="font-size:9pt;">등록된 호텔이 없습니다</span></b></p>
	        </td>
	    </tr>
	</c:if>

	<%-- ArrayList에  GuestBookBean 객체를 하나하나 data라는 반복 대입해서 사용 --%>
	<c:forEach items="${requestScope.hotelList}" var="data">
		    <tr>
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt;">
		            ${data.hotelName}</span></p>
		        </td>
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt;"><img alt="호텔이미지" src="${data.hotelImagePath}" class="img_size"></span></p>
		        </td>
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt;">${data.hotelGrade}</span></p>
		        </td>
		        
		        <td bgcolor="">
					<p><span style="font-size:9pt;">${data.hotelLocation}</span></p>
		        </td>
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt;">${data.star}</span></p>
		        </td>
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt;">
		            <a href="admin?command=hotelchange&num=${data.hotelNum}">수정하기</a></span></p>
		        </td>
		    </tr>
	</c:forEach>
</table>
<hr>
<div class="right_bot"><button class="add_btn" onclick="location.href='addHotel.jsp'">호텔 추가</button></div>
</div>
</body>
</html>