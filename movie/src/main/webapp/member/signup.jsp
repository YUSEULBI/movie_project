<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/movie/css/member/signup.css" rel="stylesheet">
</head>
<body>
	<%@include file="/header.jsp" %>
	<div class="wrap">
		<div class="wrapdiv">
			<div class="titlediv">
				<h3 class="title">회원가입</h3>
			</div>
			<div class="formdiv">
				<form class="signupForm">
					<div class="inputtitle">아이디</div> 
					<div class="inputdiv">
						<input onkeyup="idcheck()" class="mid inputcss" name="mid" type="text"> <br>
					</div>
					<div class="confirminput"></div>
					
					<div class="inputtitle">비밀번호</div>
					<div class="inputdiv">
						<input onkeyup="pwdcheck()" class="mpwd inputcss" name="mpwd" type="password">  <br>
					</div>
					<div class="confirminput"></div>
					
					<div class="inputtitle">비밀번호확인</div>
					<div class="inputdiv">
						<input onkeyup="repwdcheck()" class="rempwd inputcss" name="rempwd" type="password">  <br>
					</div>
					<div class="confirminput"></div>
					
					<div class="inputtitle">이메일</div>
					<div class="inputdiv"> 
						<input onkeyup="emailcheck()" class="memail inputcss" name="memail" type="text">  <br>
					</div>
					<div class="confirminput"></div>
					
					<div class="inputtitle">프로필</div>
					<div class="inputdiv"> 
						<input onchange="premimg(this)" class="mimg inputcss" name="mimg" type="file">  <br>
						<div><img style="width:100px"  class="newmimg" alt="" src="/movie/member/img/default.webp"></div>
					</div>
					
					<div class="btndiv">
						<button class="signupbtn btncss" type="button" onclick="signup()"> 회원가입 </button>
					</div>
		
				</form>
			</div>
		</div>
	</div>
	<%@include file="/footer.jsp" %>
	
	<script src="/movie/js/member/signup.js" type="text/javascript"></script>
</body>
</html>