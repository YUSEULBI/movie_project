<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<link href="/movie/css/admin/expected.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/header.jsp" %>
	
	<div class="modal">
		<div class="modal_box">
			<h1 class="modal_title"></h1>
			<div class="modal_content">
				<div class="modalimgbox"></div>
				<div class="modaloverview"></div>
			</div>
			<div class="modal_btns">
				<button class="btn btn-outline-info" type="button" onclick="closeModal()">닫기</button>
			</div>
		</div>
	</div> 
	
	<div class="wrap">
		<center><h2>개봉예정영화</h2></center>
		<div class="movielist"></div>
	</div>
	
	
	
	
	
	
	
	
	
	<%@ include file="/footer.jsp" %>
	<!-- 부트스트랩 js  -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
	<script src="/movie/js/admin/expected.js" type="text/javascript"></script>
</body>
</html>