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
				<h3 class="title">로그인</h3>
			</div>
			<div class="contentdiv">
				<div class="inputtitle">아이디</div>
				<div class="inputdiv"> 
					<input class="mid inputcss" type="text">
				</div>
				
				<div class="inputtitle">비밀번호</div> 
				<div class="inputdiv">
					<input class="mpwd inputcss" type="password">
				</div>
				
				<div class="finddiv">
					<a class="findatag" href="/movie/member/findIdPw.jsp">아이디찾기</a>
					<a class="findatag" href="/movie/member/findIdPw.jsp">비밀번호찾기</a>
				</div>
				<div class="btndiv">
					<button class="loginbtn btncss" type="button" onclick="login()"> 로그인 </button>
				</div>
			</div>
		</div>
	</div>
	
	<%@include file="/footer.jsp" %>
	<script src="/movie/js/member/login.js" type="text/javascript"></script>
</body>
</html>