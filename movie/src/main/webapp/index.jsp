<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화의 모든 것 CINEVERSE</title>
	<link href="/movie/css/index.css" rel="stylesheet">
</head>
<body>
	<%@include file="/header.jsp" %>
	
	<div class="wrap">
		<div class="boxoffice">
<!-- 			<div onclick="slidermove( 243 )" class="sliderleftbtn"> < </div> -->
<!-- 			<div onclick="slidermove( -243 )" class="sliderrightbtn"> > </div> -->
<!-- 			<div class="indextitle">박스오피스</div> -->
			<div class="boxofficeSlider">
				<div class="boxofficeinfobox">				
				</div> <!-- boxofficeinfobox end -->
			</div>
			<div class="boxofficebardiv">
				
				<div class="boxofficebar boxofficebar0" onclick="slidermovebar(0)"></div>
				<div class="boxofficebar boxofficebar5" onclick="slidermovebar(5)"></div>
	
			</div>
		</div>  
		
		<!-- 넷플릭스 top10 -->
		<div class="netflixrankdiv">
			<div class="netflixtitlediv">
				<img class="netflixlogo" src="/movie/img/index/netflixlogo.png">
				<div class="netflixtitle">넷플릭스 TOP 10</div>
			</div>
			<div class="netflixrankoneinfo">
				<div class="netflixrankno"></div>
				<div class="netflixrankname"></div>
				
			</div>
			<div class="netflixsliderleftbtn" onclick="netflixslidermove(1)"> <div> < </div> </div>
			<div class="netflixsliderrightbtn" onclick="netflixslidermove(2)"> <div> > </div> </div>
			<div class="netflixtoptendiv">
				<!-- 영화포스터 출력 -->
			</div>
			
		</div> <!-- 넷플릭스 top10 end -->
		
		<!-- 개봉예정작 출력 -->
		<div class="upcomingmoviediv">
		</div>
		
	</div> <!-- wrap end -->
	<!-- 배경조명 -->
	<div class="upcomingbackground" 
		style="background-image: URL(/movie/img/index/backgroundlight2.png);">
	</div>
	
	
	<div class="backgroundcolor">
	</div>
	<div style="background-image: "></div>
	<%@include file="/footer.jsp" %>
	<script src="/movie/js/index.js" type="text/javascript"></script>
	
</body>
</html>




<!-- <div class="onemovie"> -->
<!-- 	<div class="mposter"> -->
<!-- 		<img alt="" src="/movie/img/indextest/poster/poster1.jpg"> -->
<!-- 		<div class="mrank">1</div> -->
<!-- 	</div>    -->
<!-- 	<div class="moviename">스즈메의 문단속</div> -->
	
<!-- 	<div class="movieinfo"> -->
<!-- 		<div class="mrate">예매율 <span class="mrateno">43.4</span>%</div> -->
<!-- 		<div> -->
<!-- 			<span><i class="fa-solid fa-star"></i> <span class="moviegrade">9.3</span> </span> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->



<!-- 
<div class="upcomingmovie appear">
				<div class="upcomingposterdiv">
					<img class="upcomingposter" src="/movie/img/index/poster/poster1.jpg">
				</div>
				<div class="upcominginfo">
					<div class="upcomingtitlediv">
						<div class="upcomingtitle">슈퍼 마리오 브라더스</div>
						<div class="upcomingdday">D-19</div>
					</div>
					<div class="upcomingdate">개봉일 | <span>2023-04-26</span></div>
					<div class="upcomingtext">
						따단-딴-따단-딴 ♫ 전 세계를 열광시킬 올 타임 슈퍼 어드벤처의 등장! 뉴욕의 평범한 배관공 형제 '마리오'와 ‘루이지’는 배수관 고장으로 위기에 빠진 도시를 구하려다 미스터리한 초록색 파이프 안으로 빨려 들어가게 된다. 파이프를 통해 새로운 세상으로 차원 이동하게 된 형제. 형 '마리오'는 뛰어난 리더십을 지닌 '피치'가 통치하는 버섯왕국에 도착하지만 동생 '루이지'는 빌런 '쿠파'가 있는 다크랜드로 떨어지며 납치를 당하고 ‘마리오’는 동생을 구하기 위해 ‘피치’와 ‘키노피오’의 도움을 받아 '쿠파'에 맞서기로 결심한다. 그러나 슈퍼스타로 세상을 지배하려는 그의 강력한 힘 앞에 이들은 예기치 못한 위험에 빠지게 되는데...!"					
					</div>
					<a href="/movie/admin/expected.jsp"> 
						<div class="upcomingpagebtn">개봉예정작 보러가기 ></div>
					</a>
				</div> 
			</div> 
 -->