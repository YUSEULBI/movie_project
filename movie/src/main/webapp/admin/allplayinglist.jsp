<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title><!-- 부트스트랩 css -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<link href="/movie/css/admin/allplayinglist.css" rel="stylesheet">
	
</head>
<body>
	
	<!-- 일자에 포함된 모든 상영정보를 출력하는 모달 -->
	<div class="modal1">
		<div class="modal1_box">
			<div class="modal1_btns"></div>
			<h3 class="modal1_title">
				<!-- 제목이 들어가는 자리  -->
			</h3>
			<div class="modal1_content">
				<!-- 내용이 들어가는 자리  -->
			</div>
			
		</div>
	</div> 
	
	<div class="modal2">
		<div class="modal2_box">
			<div class="modal2_btns">
				<button onclick="closeModal2()" class="btn btn-outline-info" type="button">닫기</button>
			</div>
			<h3 class="modal2_title">
				<!-- 제목이 들어가는 자리  -->
			</h3>
			<div class="modal2_content">
				<!-- 내용이 들어가는 자리  -->
			</div>
			
		</div>
	</div>
	
	
	<%@ include file="/header.jsp" %>
	
	<div class="wrap">
		<div class="container">
			<center><h2>통계</h2></center>
			<div class="canvasbtn">
				<button onclick="printChart1()" type="button">예매량</button>
				<button onclick="printChart2()" type="button">영화별 매출</button>
			</div>
			
			<div class="canvasbox">
				<canvas id="myChart"></canvas>
			</div>
		</div>
		<center><h2>상영정보</h2></center>
		<div class="calender">
			<div class="selectbox">
				<div class="selectYear"> <!-- 년도 선택부분 -->
					<button class="btn" onclick="minusYear()"><</button>
					<select onchange="clickyear()" class="year form-select"></select>
					<button class="btn" onclick="plusYear()">></button>
				</div>
				<div class="selectmonth">
					<button class="btn" onclick="minusMonth()"><</button>
					<select onchange="clickmonth()" class="month form-select"></select>
					<button class="btn" onclick="plusMonth()">></button>
				</div>
			</div>
			
			<div class="selectdate">
				
			</div>
		</div>
	</div>	
	
	<%@ include file="/footer.jsp" %>
	<!-- chart js -->
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<!-- 부트스트랩 js  -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
	<script src="/movie/js/admin/allplayinglist.js" type="text/javascript"></script>
</body>
</html>