<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<link href="/movie/css/board/search.css" rel="stylesheet">
</head>
<body>
	<%@include file="../header.jsp" %>

	<div class="container">
		<div class="css_1">
			<div class="css_warp">
			<div class="m_list">
			<div class="input-group mb-2 css_search">
			  	<input type="text" class="search_m inputcss2" placeholder="영화검색" aria-label="Recipient's username" aria-describedby="button-addon2">
			 	<button class="css_btn" type="button" id="button-addon2" onclick="search_m()"><i class="fa-solid fa-magnifying-glass fa-beat-fade"></i></button>
			</div>
		
			<div class="searchresult">
				
			</div>
			
			<div class="m_title">
				<h1>박스오피스 탑 10</h1>
			</div>
			<div class="m_p css_m_p">
			</div>
		
			<div class="m_title">
				<h1>넷플릭스 영화 순위</h1>
			</div>
			<div class="m_p2 css_m_p">
			</div>
			
		</div>
		
		
		
		<!-- 모달 HTML -->
		<div class="modal_wrap">
		<div class="overflow_modal">
		<div class="modal_box">
			
			<div class="movie_info">
				<h3 class="modal_title">
				<!-- 제목이 들어가는 자리  -->
			</h3>
				<div class="modal_content">
				<!-- 내용이 들어가는 자리  -->
			</div>
			</div>
			
			
			<div class="review_info">
			<!-- 리뷰 출력 자리 -->
			<h3> 리뷰 </h3>
			<div class="review_print">
				
		</div>
			
		
			<!-- 댓글 작성자리 -->
			<div>
				<h3> 리뷰 작성 </h3>
			</div>
			<div class="review_container">
				
				<form class="writeForm">   
				<div>
					평점 : <input type="text" class="bscore inputcss" id="bscore">/10
				</div>
				<div>
					<textarea class="bcontent css_textarea" rows="5" cols="48" id="bcontent"></textarea>
					<button class="bwrite css_btn" onclick="bwrite()" type="button">작성하기</button>
				</div>
				</form>
			</div>
			
			</div>
			
			
			<div class="modal_btns">
				<button onclick="closeModal()" class="modal_cencel" type="button"><i class="fa-regular fa-circle-xmark fa-beat"></i></button>
			</div>
		</div>
		</div>
	</div>
		
	</div>
	</div>
	</div>
	
	<!-- 모달 HTML2 -->
		<div class="modal_wrap2 overflow-auto">
		<div class="modal_box2">
			<h3 class="modal_title2">
				댓글작성하기
			</h3>
			<div class="modal_content2">
				<!-- 내용이 들어가는 자리  -->
			</div>
			<div class="modal_btns2">
			<button class="bwrite" onclick="reply_write()" type="button">작성</button>	
			<button onclick="closeModal2()" class="modal_cencel" type="button">닫기</button>
			</div>
		</div>
			
		</div>
	<%@include file="/footer.jsp" %>
	<script src="/movie/js/index.js" type="text/javascript"></script>

<!-- 부트스트랩 -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
	<script src="https://kit.fontawesome.com/601e7abb65.js" crossorigin="anonymous"></script>
	<script type="text/javascript" src="/movie/js/board/Search.js"></script>
</body>
</html>