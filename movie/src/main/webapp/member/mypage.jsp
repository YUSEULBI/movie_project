<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/movie/css/member/modal.css" rel="stylesheet">
	<link href="/movie/css/member/mypage.css" rel="stylesheet">
	<link href="/movie/css/member/signup.css" rel="stylesheet">
</head>
<body>
	<%@include file="/header.jsp" %>
	<%
		int mypage = Integer.parseInt(request.getParameter("page"));
	%>
	<input type="hidden" class="mypage" value="<%=mypage%>">
	<div class="wrap">
		<div class="mypagediv">
			<div class="mypageinfo">
				<div> <span class="mypagemid">admin</span>님 반가워요! </div>				
			</div>
			
			<div class="mypageinfo mypagesubbanner">
				<h3 class="mypagesubtitle">예매조회</h3>
				<div class="" onclick="reservationpagebtn()">▼</div>				
			</div>	
			<div class="reservation">
				
				<div  class="sliderleftbtn"> < </div>
				<div  class="sliderrightbtn"> > </div>
				<img class="leftshadow" src="/movie/member/img/mypage/leftshadow.png">
				<img class="rightshadow" src="/movie/member/img/mypage/rightshadow.png">
				<div onclick="slidermove(305)" class="leftmouseover"></div>
				<div onclick="slidermove(-305)" class="rightmouseover"></div>
				
				<div class="reservationDiv">
					<!-- 출력할예약정보 : 영화제목/상영시간/상영관/좌석번호/결제금액 -->
					
				</div>
				<br>
				
			</div>
			
			<div>
				<a name="update"></a>
				<div class="mypageinfo mypagesubbanner">
					<h3 class="mypagesubtitle">회원정보수정</h3>
					<div onclick="updatememberpagebtn()">▼</div>				
				</div>
				<div class="updatememberflex">
					<div class="updatememberdiv">
						<form class="signupForm">
						
							<div class="inputtitle">프로필</div>
							<div class="inputdiv"> 
								<input onchange="premimg(this)" class="newmimg inputcss" name="newmimg" type="file">
								<div><img class="mimgimg newmimg" style="width:100px" alt="" src=""> </div>
							</div>
							
							<div class="inputtitle">현재비밀번호</div>
							<div class="inputdiv">
								<input name="mpwd " class="inputcss" type="password">
							</div>
							
							<div class="inputtitle">비밀번호</div>
							<div class="inputdiv">
								<input name="newmpwd" class="newmpwd inputcss" type="password">
							</div>
							
							<div class="inputtitle">비밀번호확인</div>
							<div class="inputdiv"> 
								<input onkeyup="repwdcheck()"  name="newmpwdcheck" class="newmpwdcheck inputcss" type="password">
							</div>
							<div class="confirminput"></div>
							
							<div class="inputtitle">이메일</div>
							<div class="inputdiv">
								<input class="memail inputcss" name="memail" type="text">
							</div>
							
							
				
							<button class="updatebtn btncss" type="button" onclick="update()"> 수정 </button>
							<button class="btncss" onclick="onpenModal()" type="button">회원탈퇴</button>
						</form>
					</div>
				</div>
			</div>
		</div> <!-- mypagediv end -->
	</div> <!-- wrap end -->
	
	
		
	<!-- 탈퇴 모달 HTML -->
	<div class="modal_wrap modal_wrap_deletemember">
		<div class="modal_box">
			<h3 class="modal_title">
				정말 탈퇴하시겠습니까?
			</h3>
			<div class="modal_content">
				<span class="inputtitle">비밀번호 확인</span><input class="deletecheck" type="password">
			</div>
			<div class="modal_btns">
				<button onclick="deleteMember()" type="button">탈퇴</button>
				<button onclick="closeModal()" class="modal_cencel" type="button">취소</button>
			</div>
		</div>
		
	</div> 
	
	<!-- 예매취소 모달 HTML -->
	<div class="modal_wrap modal_wrap_recancel">
		<div class="modal_box">
			<h3 class="modal_title">
				예매를 취소하시겠습니까?
			</h3>
			<input type="hidden" class="cancelpno" value="">
			<div class="modal_btns">
				<button onclick="canclereservation()" type="button">예매취소</button>
				<button onclick="closeReCancelModal()" class="modal_cencel" type="button">닫기</button>
			</div>
		</div>
		
	</div> 
	
	<%@include file="/footer.jsp" %>
	<script src="/movie/js/member/update.js" type="text/javascript"></script>
	<script src="/movie/js/member/modal.js" type="text/javascript"></script>
	<script src="/movie/js/member/myReservation.js" type="text/javascript"></script>
</body>
</html>



<!-- 출력할예약정보 : 영화제목/상영시간/상영관/좌석번호/결제금액 -->

<!-- 				<div class="oneReservation">
					
						<div class="title">더 퍼스트 슬램덩크</div>
						<div class="playtime">2023-03-22</div>
						<div class="sno">2관</div>
						<div class="seatnum">a1</div>
						<div class="pprice">14000원</div>
						
					</div>			
 --> 