let s_movie ; 	//선택한 영화
let s_year; 	// 선택한 년
let s_month ; 	//선택한 월
let s_day; 		// 선택한 일
let s_date; // 선택한 년-월-일
 
let s_mno;  //선택한 영화의 번호

let s_pno // 선택한 상영번호 
let s_sno

let p_mno = parseInt( document.querySelector('.hid').value); //다른페이지로 접속시 가져온 값
/* ---------------- 로그인 유효성 검사 ------------------ */
if( memberInfo ==  null ){
		alert('로그인후 이용 가능합니다.')
		location.href= `/movie/index.jsp`;
}
/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 현재 상영중인 영화 제목 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/
let pmovie=[]; //상영중인 전체 영화
$.ajax({
	url:"/movie/playing/moive",
	method:"get",
	success:((r)=>{
	 	let html = ``;
	 	pmovie=r; //모든 상영중 영화를 저장후 출력
	 	pmovie.forEach((o)=>{
			html += `<div onclick="select_Pmovie(${o.mno})" class="movie_select_${o.mno} movie_select_one">
						<div class="movie_img_${o.mno} movie_img"></div>
						<div class="one_movie_title m_${o.mno}"> ${o.title} </div>
					 </div>`	 
		 })
	 	document.querySelector('.movie_title').innerHTML = html;
	 	img_print() // 상영중인 영화를 먼저 추출한뒤 영화제목을 뽑아야함
	})
})


/* -------------------------- 상영중인 영화 이미지 출력 ---------------------- */
let movieimg = '';

function img_print(){ 
	let html = '';
		pmovie.forEach((o)=>{  //현재 상영중인 영화(o)와 api추출한 데이터 이미지(r,i) 뽑기
			$.ajax({
			   url : "https://api.themoviedb.org/3/search/movie?api_key=fc5bcf6e9c88d59559fafe20b6032a0e",
			   method : "get",
			   async:"false",
			   data : {
			      "query" : o.title,
			      "language" : "ko-KR"
			   },
			   success : (r)=>{
			
			      r.results.forEach((i)=>{
			         if(i.title== o.title){

				         html =	`<img class="movie img" src="https://image.tmdb.org/t/p/w500/${i.poster_path}">`

				         document.querySelector(`.movie_img_${o.mno}`).innerHTML = html;
			         }//if e
			         
			      }) // for e
			      select_Pmovie(p_mno) //영화 선택시
			   } // success e
			}) // ajax e
		 })// forEach
}

/* -------------------------- 영화선택시 ----------------------*/

function select_Pmovie(mno){ //선택한 영화는 검정색 테두리 다른건 원래대로
	if(mno == 0){
		return;
	}
	s_mno = mno; 
	let movie_select_one = document.querySelectorAll('.movie_select_one');
	
	let title = document.querySelector(`.m_${mno}`).innerHTML
	document.querySelector(`.movie_select_font`).innerHTML =  title
	
	for(let i = 0 ; i < movie_select_one.length ; i++){
		movie_select_one[i].style.border=""
	}
	
	document.querySelector(`.movie_select_${mno}`).style.border="3px solid black"
	s_movie = document.querySelector(`.m_${mno}`).innerHTML
	
	// 다른영화 클릭시 날짜초기화 + 상영관 초기화
	
	//모든날짜 가져오기
	let date_day = document.querySelectorAll('.date_day');
	
	for(let i = 0 ; i < date_day.length ; i++){ //모든날짜 정상적으로
		date_day[i].style.color="black"
		date_day[i].style.backgroundColor="white"
	}
	
	 document.querySelector('.date_form').innerHTML = '';
	 document.querySelector('.s_movie_title').innerHTML = '';
}



/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ날짜 구하기 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
//현재 년도, 월, 일 구하기
let year = (new Date().getFullYear()) 
let month = new Date().getMonth()   //0~11 => 1~12
let date = new Date().getDate();


//말일, 요일
let last = new Date(year,month+1,0).getDate(); //+1인채로
let week = ['일', '월', '화', '수', '목', '금', '토'];
let week_day = week[new Date(year,month,date).getDay()]


let monthCount = 0;
let month_kor =  month+1+'월'; // 최초 'O월' 한번은 뜨고 시작
/*ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 달력 함수 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ  */
//현재날짜에서 하루씩 증가하는 함수
function dateCalculate(){
	date++; 
	if(date > last){ 	//말일이 지나면 
		date = 1;  		//1일부터
		monthCount++;	// 
		month+= monthCount;
		month_kor = month+1+'월'
		
		if(month>=13){ // 13월이된다면 1년++
			month%=12;
			year++;
		} 
		
	}else{  //2일부턴 월이 안나오게
		month_kor = '';
	}
	week_day =  week[new Date(year,month,date).getDay()];
}


/*ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 달력 출력 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
//날짜 출력
dayPrint()
function dayPrint(){

	let html = `<div class="carousel-inner">`;

	for(let i = 0 ; i < 3 ; i++){ //총 3주 출력
		if( i == 0 ){
			html += `<div class="carousel-item active">	
					   <ul class="date_week_li">`
		}else{
			html += `<div class="carousel-item">	
					   <ul class="date_week_li">`
		}
		for(let j = 0 ; j < 7 ; j++){ // 한번에 7일만 보이게
			
			html+= `<li onclick="select_date(${year},${month}, ${date})"> 
						 <span class="date_month">${month_kor}</span> 
						 <span class="date_day D${month}_${date}"> ${date} </span> 
						 <span class="date_week_day" ${week_day === '토'|| week_day === '일' ? 'style="color:red"' : 'style="color:black"'}> 
						 ${date == new Date().getDate() ? '오늘' : week_day} </span> 
					</li>`
			dateCalculate() // 1일씩 증가하는 함수
		}// for j e	
		
		html +=`	</ul>
				</div>` //ul과 캐러셀 하나 닫기
	}// for i e


	html +=`</div>
			<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			 </button>
			`;
	
	document.querySelector('#carouselExampleControls').innerHTML = html;
}

/*---------------- 달력클릭시 배경검은색으로 ---------------------*/
function select_date(year, month, date){
	
	if(s_movie == null){
		alert('영화를 먼저 선택해주세요'); return;	
	}
	
	//모든날짜 가져오기
	let date_day = document.querySelectorAll('.date_day');
	
	for(let i = 0 ; i < date_day.length ; i++){ //모든날짜 정상적으로
		date_day[i].style.color="black"
		date_day[i].style.backgroundColor="white"
	}
	
	//선택한 날짜만 배경 검은색으로
	document.querySelector(`.D${month}_${date}`).style.color="white"
	document.querySelector(`.D${month}_${date}`).style.backgroundColor="black"
	document.querySelector(`.D${month}_${date}`).style.borderRadius= "14px";
	
	//선택시 선택한 날짜 전역변수에 저장
	s_day = date ;   s_month = month+1;  s_year = year;
	
	//월,일이 한자리 숫자면 두자리 숫자로
	if(s_month.toString().length <=1){
		s_month = String(s_month).padStart(2,"0")
	}
	if(s_day.toString().length <=1){
		s_day = String(s_day).padStart(2,"0")
	}
	
	s_date = s_year+'-'+s_month+ '-'+s_day // 선택한 년-월-일
	
	document.querySelector('.date_font').innerHTML = s_year+'년 ' + s_month + '월 ' + s_day + '일'
	
	screen_print(); //상영관 출력
}

/*----------------------------- 상영관 출력 -------------------------*/
function screen_print(){
	let html = ``;
	
	document.querySelector('.s_movie_title').innerHTML = s_movie
	
	$.ajax({
	url:"/movie/playing/moive",
	method:"put",
	data: { mno:s_mno , s_date:s_date },
	success:((r)=>{
		console.log(r)
		if(r.length == 0){html=`<div class="none_date">다른 날짜를 확인해주세요</div>`}
		else{ 
			r.forEach((o)=>{
		//이용가능한 좌석이 0 이면 
			html += `<div class="date_box"` 
			
			o.aseat>0 ? html +=` onclick="select_screen('${o.pno}')"> ` : html +='onclick="no()">'	
			
			html +=		`<div class="date_time">${o.playtime.split(' ')[1].substr(0,5)}</div> <!-- 상영시간 -->
								
							<div class="date_text">
								<span class="date_seat"> <span class="available${o.pno} available">${o.aseat} / </span><span class="all2_seat${o.pno}">${o.s_seat}</span></span>	<!-- 남은좌석 -->
								<span class="date_sno">${o.sno}관</span>	<!-- 상영관 위치 -->
							</div>
						</div>
					</div>
					`
			})
			
		 }
		document.querySelector('.date_form').innerHTML = html
	})//success e
	 	
	})
}

function no(){
	alert('이용가능한 좌석이 없습니다!.')
}

 
/* --------------------- 좌석클릭시 모달 출력 -------------------- */
function select_screen(pno){ 
	s_pno=pno;
	document.querySelector('.modal_wrap').style.display = 'flex';
	document.querySelector('.modal_title').innerHTML = s_movie;
	document.querySelector('.a_seat').innerHTML = document.querySelector(`.available${pno}`).innerHTML
	document.querySelector('.all_seat').innerHTML = document.querySelector(`.all2_seat${pno}`).innerHTML
}

function closeModal(){
	document.querySelector('.modal_wrap').style.display = 'none';
}

function reservation_screen(){
	location.href=`/movie/reservation/ticket.jsp?pno=${s_pno}`
}