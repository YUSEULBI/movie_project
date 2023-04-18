<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화의 모든 것 CINEVERSE</title>
	<link href="/movie/css/header.css" rel="stylesheet">
</head>
<body>

	<div class="wrap">
		<div class="header">
		
			<div class="logodiv" >
				<div class="logo"> <a href="/movie/index.jsp"><img alt="" src="/movie/img/logo.png"></a> </div>
				<div class="profileimgdiv"></div>
				<div class="dropdown_menu">
					<ul>
						<li class="dropdown_li"><a href="/movie/member/mypage.jsp?page=0">마이페이지</a></li>
						<li class="dropdown_li"><a href="/movie/member/mypage.jsp?page=1">예약조회</a></li>
						<li class="dropdown_li"><a href="/movie/member/mypage.jsp?page=2#update">회원정보수정</a></li>
						<li class="dropdown_li"><a href="/movie/member/logout.jsp">로그아웃</a></li>
					</ul>
				</div>
			</div>
			
<!-- 			<div class="line"></div> -->
			
			<div class="menu">
				<div class="mainmenu">
					<div class="mainmenuleft">
					<a href="/movie/reservation/reservation.jsp?mno=0"> 예매 </a> <!-- 2023-04-07 링크수정  -->
					<span class="verticalBar">|</span>
					<a href="/movie/board/Search.jsp"> 영화검색 </a>
					<span class="verticalBar">|</span>
					<a href="/movie/admin/expected.jsp"> 개봉예정작 </a>
					<span class="verticalBar">|</span>
					<a href="/movie/board/Board.jsp"> 커뮤니티 </a>
					</div>
					<div class="mainmenuright">
						<a href="/movie/admin/allplayinglist.jsp"> 관리자 </a>
					</div>

				</div>
				<div class="submenu"></div>
			</div>
			
			
			
		</div>
	
	</div>
	
	
	
	<!-- jquery -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<!-- 폰트어썸 -->
	<script src="https://kit.fontawesome.com/44a4c3357b.js" crossorigin="anonymous"></script>
	<!-- 부트스트랩 -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
	<!-- 사용자 정의 -->
	<script src="/movie/js/header.js" type="text/javascript"></script>
</body>
</html>