<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/movie/css/member/findIdPw.css" rel="stylesheet">
	<link href="/movie/css/member/signup.css" rel="stylesheet">
</head>
<body>
	<%@include file="/header.jsp" %>
	<div class="wrap">
		<div class="findidpwdiv">
		
			
			
			<!-- 아이디비번찾기 입력구역 -->
			<div class="finddiv">
				
				<div class="findiddiv">
					<div class="findpwmenudiv"> 
						<div class="findidmenu">아이디 찾기</div>
					</div>
					<div class="inputtitle">이메일</div>
					<div class="findinputdiv">
						<input class="emailInputFindId inputcss" type="text"> <br>
					</div>
					<button class="btncss" onclick="findid()" type="button">아이디찾기</button>
					<div class="findidresult"></div>
				</div>
				
				<div class="findpwdiv">
					<div class="findpwmenudiv">
						<div class="findpwmenu">비밀번호 찾기</div>
						<div class="findtext">회원정보의 이메일로 임시비밀번호를 발송합니다.</div>
					</div>
					<div class="inputtitle">아이디</div>
					<div class="findinputdiv"> 
						<input class="idInputFindPw inputcss" type="text"> <br>
					</div>
					<div class="inputtitle">이메일</div>
					<div class="findinputdiv"> 
						<input class="emailInputFindPw inputcss"  type="text"> <br>
					</div>
					<button class="btncss" onclick="findpw()" type="button">비밀번호찾기</button>
				</div>
				
			</div> <!-- 아이디비번찾기 입력구역 end -->
			
		</div>
	</div>
	<%@include file="/footer.jsp" %>
	<script src="/movie/js/member/findIdPw.js" type="text/javascript"></script>
</body>
</html>