<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<link href="./listAll.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>update.jsp</title>
<script language=javascript>
function checkValid() {
    var f = window.document.addHotel;
		
	if ( f.name.value == "") {
	    alert( "호텔명을 입력해 주세요." );
		return false;
    }
	if ( f.img.value == "" ) {
		alert( "이미지 주소를 입력해 주세요." );
		return false;
	}
	if ( f.location.value == "" ) {
		alert( "위치를 입력해 주세요." );
		return false;
	}
	if ( f.grade.value == "" ) {
        alert( "등급을 입력해 주세요" );
        return false;
    }
    return true;
}

</script>
</head>
<body>
<%@ include file="./Header.jsp" %>
<div class="senter_wrapper">
<%@ include file="./Menu.jsp" %>
<form name="addHotel" method="post" action="admin" onSubmit='return checkValid()'>

<input type="hidden" name="command" value="addHotel">

<table align="center" cellpadding="5" cellspacing="2" width="600" class="add_table">

    <tr>
        <td width="1220" height="20" colspan="2" bgcolor="F8EDE3">
            <p align="center"><font color="BE8C63" size="3"><b>호텔 추가</b></font></p>
        </td>
    </tr>
    <tr>
        <td width="150" height="20">
            <p align="right"><b><span style="font-size:9pt;">이름</span></b></p>
        </td>
        <td width="450" height="20"><b><span style="font-size:9pt;">
		<input type=text name="name" size="30"></span></b></td>
    </tr>
     <tr>
        <td width="150" height="20" >
            <p align="right"><b><span style="font-size:9pt;">이미지 주소</span></b></p>
        </td>
        <td width="450" height="20"><b><span style="font-size:9pt;">
		<input type=text name="img" size="30"></span></b></td>
    </tr>
    <tr>
        <td width="150" height="20" >
            <p align="right"><b><span style="font-size:9pt;">위치</span></b></p>
        </td>
        <td width="450" height="20"><b><span style="font-size:9pt;">
		<input type=text name="location" size="30"></span></b></td>
    </tr>
    <tr>
        <td width="150" height="20">
            <p align="right"><b><span style="font-size:9pt;">등급</span></b></p>
        </td>
        <td width="450" height="20" ><b><span style="font-size:9pt;">
		<input type=text name="grade" size="50"></span></b></td>
    </tr>
        <tr >
        <td width="1220" height="20" colspan="2" border=0>
        <p align="center">
            <input class="add_btn" type=submit value="호텔 추가"> 
        <input class="add_btn" type=reset value=다시쓰기></p>
        </td>
    </tr>
</table>

</form>
</div>
</body>
</html>