<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>영화의 모든 것 CINEVERSE</title>
	<!-- 부트스트랩 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <!-- 사용자 정의 CSS -->
    <link href="/movie/css/admin/newplayinglist.css" rel="stylesheet">
</head>
<body>
	
	<%@ include file="/header.jsp" %>
	<%
		String fromyear = null;
		String frommonth = null;
		String fromdate = null;
		if(request.getParameter("year")!=null){
			fromyear = request.getParameter("year");
			frommonth = request.getParameter("month");
			fromdate = request.getParameter("date");
		}
	%>
	
	<div class="fromyear" hidden="hidden"><%=fromyear %></div>
	<div class="frommonth" hidden="hidden"><%=frommonth %></div>
	<div class="fromdate" hidden="hidden"><%=fromdate %></div>
	
	<div class="wrap">
		<div class="infobox">
			<div class="selectbox">
				<div class="selectinfo">
					<h3>현재 상영중인 영화</h3>
					<select onchange="selectMovie()" class="selectMovie form-select"></select>
					<!-- 영화관 선택창 -->
					<div class="selectScreen">
						<h3>영화관 선택</h3>
						<select onchange="setScreen()" class="screen form-select">
							<option value=1>1관</option>
							<option value=2>2관</option>
							<option value=3>3관</option>
							<option value=4>4관</option>
						</select>
					</div> <!-- selectScreen e -->
					<!-- 가격 선택창 -->
					<div class="selectPrice">
						<h3>가격 선택</h3>
						<select onchange="setPrice()" class="price form-select">
							<option value=10000>10,000</option>
							<option value=14000>14,000</option>
							<option value=16000>16,000</option>
						</select>
					</div>
					<div class="selectday">
						<h3> 날짜 선택 </h3>
						<div class="selectdate"> <!-- 날짜 선택 창 -->
							<select class="year form-select"></select>
							<select onchange="monthchange(),setSelectday()" class="month form-select"></select>
							<select onchange="setSelectday()" class="date form-select"></select>
						</div>
						<h3> 시간 선택 </h3>
						<div class="selecttime"><!-- 시간, 분 선택 창 -->
							<select onchange="setSelecttime()" class="hour form-select"></select>
							<select onchange="setSelecttime()" class="minute form-select"></select>
						</div>
					</div><!-- selectday e -->
				</div>
				
			</div> <!-- selectbox e -->
			<div class="selectedinfo">
				<h3>입력정보</h3>
				<table class="table">
					<tr>
						<td>영화제목</td>
						<td class="receivetitle"></td>
					</tr>
					<tr>
						<td>상영일시</td>
						<td class="receivedate"></td>
					</tr>
					<tr>
						<td>상영관</td>
						<td class="receivescreen"></td>
					</tr>
					<tr>
						<td>가격</td>
						<td class="receiveprice"></td>
					</tr>
					<tr>
						<td>등록여부</td>
						<td class="possible">상영가능</td>
					</tr>
				</table>
				<button onclick="newplayinglist()" class="btn">상영등록</button>
			</div>
			<div class="playlistinfo">
				<div class="movielist">
					<!-- 현재 선택 내용 창 -->
					<h3>선택한 날짜의 상영표</h3>
					<table class="table movielisttable"></table>
				</div> <!-- movielist e -->
			</div>
			
			
		
		</div>
		
		
		
		
		
		
	 
	</div>
	
	<%@ include file="/footer.jsp" %>
	<!-- 부트스트랩 JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
	<!-- 사용자 정의 JS -->
	<script src="/movie/js/admin/newplayinglist.js" type="text/javascript"></script>
</body>
</html>