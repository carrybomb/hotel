<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<link href="./listAll.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>update.jsp</title>
<script language=javascript>
function checkValid() {
    var f = window.document.addAdmin;
		
	if ( f.name.value == "") {
	    alert( "이름을 입력해 주세요." );
		return false;
    }
	if ( f.id.value == "" ) {
		alert( "아이디를 입력해 주세요." );
		return false;
	}
	if ( f.pw.value == "" ) {
		alert( "비밀번호를 입력해 주세요." );
		return false;
	}
	if ( f.tel.value == "" ) {
        alert( "전화번호를 입력해 주세요" );
        return false;
    }
	if ( f.email.value == "" ) {
        alert( "이메일을 입력해 주세요" );
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
<form name="addAdmin" method="post" action="admin" onSubmit='return checkValid()'>

<input type="hidden" name="command" value="addAdmin">

<table align="center" cellpadding="5" cellspacing="2" width="600" class="add_table">

    <tr>
        <td width="1220" height="20" colspan="2" bgcolor="F8EDE3">
            <p align="center"><font color="BE8C63" size="3"><b>관리자 생성</b></font></p>
        </td>
    </tr>
    <tr>
        <td width="150" height="20">
            <p align="center"><b><span style="font-size:9pt;">이름</span></b></p>
        </td>
        <td width="450" height="20"><b><span style="font-size:9pt;">
		<input type=text name="name" class="text_none"></span></b></td>
    </tr>
     <tr>
        <td width="150" height="20" >
            <p align="center"><b><span style="font-size:9pt;">아이디</span></b></p>
        </td>
        <td width="450" height="20"><b><span style="font-size:9pt;">
		<input type=text name="id" class="text_none"></span></b></td>
    </tr>
    <tr>
        <td width="150" height="20" >
            <p align="center"><b><span style="font-size:9pt;">비밀번호</span></b></p>
        </td>
        <td width="450" height="20"><b><span style="font-size:9pt;">
		<input type=text name="pw" class="text_none"></span></b></td>
    </tr>
    <tr>
        <td width="150" height="20">
            <p align="center"><b><span style="font-size:9pt;">전화번호</span></b></p>
        </td>
        <td width="450" height="20" ><b><span style="font-size:9pt;">
		<input type=text name="tel" class="text_none"></span></b></td>
    </tr>
    <tr>
        <td width="150" height="20">
            <p align="center"><b><span style="font-size:9pt;">이메일</span></b></p>
        </td>
        <td width="450" height="20" ><b><span style="font-size:9pt;">
		<input type=text name="email" class="text_none"></span></b></td>
    </tr>

    <tr >
        <td width="1220" height="20" colspan="2">
        <p align="center">
            <input class="add_btn" type=submit value="관리자 생성"> 
        <input class="add_btn" type=reset value=다시쓰기></p>
        </td>
    </tr>
</table>

</form>
</div>
</body>
</html>