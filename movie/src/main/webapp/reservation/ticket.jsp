<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<title>좌석 예약</title> 
	
	<!-- 사용자 설정 css -->
	<link href="/movie/css/reservation/ticket.css" rel="stylesheet">

</head>
<body>
	<%@include file="/header.jsp" %>
	
	<% 
		int pno = Integer.parseInt( request.getParameter("pno"));
	%>
	<input type="hidden" value=<%=pno%> class="pno">
	 
	
	<div class="t_wrap"> 
		
		
		<div class="ticket_top"> <!-- 상단 -->
			<div class="top_title"> 인원/좌석 선택</div>
			
			<div class="top_content"> <!-- 상단 중심 -->
				<div class="movie_img"></div>	<!-- 영화 이미지 -->
				
				
				<div class="movie_info"> <!-- 영화 정보 -->
					<div class="movie_title"> </div>
					<span class="date"> </span>    |<!-- 날짜 -->
					<span class="time"> </span>   <!-- 시간 -->
					<div class="screen_box">  </div>	<!-- 상영관 -->
				</div>	<!-- movie_info e -->
				
				<div class="movie_people">	<!-- 성인 인원선택 -->
						<div class="age"> 성인 </div>
						<div class="people">
							<button type="button" onclick="p_up(1)"> + </button>
							<span class="a_num"> 0 </span>
							<button type="button" onclick="p_down(1)"> - </button>
						</div>
				</div>	<!-- movie_people e -->
				
				<div class="movie_people">	<!-- 성인 인원선택 -->
					<div class="age"> 청소년 </div>
					<div class="people">
						<button type="button" onclick="p_up(2)" > + </button>
						<span class="t_num"> 0 </span>
						<button type="button" onclick="p_down(2)"> - </button>
					</div>
				</div>	<!-- movie_people e -->
				
				<div class="movie_people">	<!-- 성인 인원선택 -->
					<div class="age"> 시니어 </div>
					<div class="people">
						<button type="button" onclick="p_up(3)"> + </button>
						<span class="b_num"> 0 </span>
						<button type="button" onclick="p_down(3)"> - </button>
					</div>
				</div>	<!-- movie_people e -->
				
				
			</div> <!-- top_content e -->
		</div>	 <!-- ticket_top e --> 
		 
		<div class="middle"> 
			<div class="screen">
				SCREEN
			</div>	<!-- screen -->
			
			<div class="select_seat" id="select_seat">
			</div>	<!-- select_screen e -->
			
			
		</div><!-- middle -->
		
		<div class="paybox">	<!-- 결제칸 -->
			<div class="pay_text">
				총 합계 <span class="pay_money"> 0 </span>원
			</div>
				<button onclick="pay_modal()" type="button" class="paybtn">결제하기</button>
		</div>	<!-- paybox -->
		
	</div>
	
	<!-- 결제모달 -->
	<div class="modal_wrap">
		<div class="modal_box">
			  
			<div class="modal_content">
				
				<div class="movie_info2_img"></div> <!-- 모달영화 이미지 -->
				
				<div class="movie_info2"> <!-- 영화 정보 -->
				
					<div class="movie_title modal_title"> </div>
					<div class="span1">
						<span class="date modal_date"> </span>    |<!-- 날짜 -->
						<span class="time  modal_time"> </span>   <!-- 시간 -->
						<div class="screen_box modal_screen">  </div>	<!-- 상영관 -->
						
						<div class="pay_people"> 인원  <span class="pay_people2"></span></div>
						<div class="s_seat"> 시트  <span class="s_seat2"></span></div>
						<div class="pay_money3"> 금액  <span class="pay_money2"></span></div>
					</div>
				</div>	<!-- movie_info e -->
				

			
			</div>	<!-- modal_content e -->

			<div class="modal_btns">
				<button onclick="requestPay()" class="modal_check" 	type="button"> 결제하기 </button>
				<button onclick="closeModal()" class="modal_cencel" type="button">닫기</button>
			</div>
		</div>
	</div>
	
	
	<%@include file="/footer.jsp" %>
	
	<!-- jQuery -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
    <!-- iamport.payment.js -->
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
	<!-- 포트원 -->
	<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
	<!-- 사용자 js -->
	<script src="/movie/js/reservation/ticket.js"></script>
</body>
</html>