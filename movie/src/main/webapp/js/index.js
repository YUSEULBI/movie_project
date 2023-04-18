console.log('index.js 연결')

// 슬라이더 position 값 설정
// position absolute 구역 가져오기
let boxofficeinfobox = document.querySelector('.boxofficeinfobox')
// position 위치 전역변수 선언
let positionleft = 0;
let positiontop = 0;
// position 위치 슬라이더에 적용
boxofficeinfobox.style.left = positionleft+"px";
boxofficeinfobox.style.top = positiontop+"px";

//버튼출력유무체크
//btncheck()
// left 값에 따라서 left버튼 생김,숨김
function btncheck(){
	if ( positionleft == 0 ){
		document.querySelector('.sliderleftbtn').style.display = 'none'
	}else{
		document.querySelector('.sliderleftbtn').style.display = 'block'
	}
	
	if ( positionleft == -1215 ){
		document.querySelector('.sliderrightbtn').style.display = 'none'
	}else{
		document.querySelector('.sliderrightbtn').style.display = 'block'
	}
}

// 왼쪽,오른쪽버튼
// -1025px씩 옮기면 됨
//rightmove( -1025 )
// left값 오른쪽으로 옮기는 함수
// 전역변수 left값 저장 , 실제 left값 변경
function slidermove( px ){
	// 현재위치 + 매개변수값
	positionleft = positionleft + px
	// 변경된 위치값으로 left값 변경
	boxofficeinfobox.style.left = positionleft+"px";
	// 변경된 위치에 따른 버튼출력유무체크
	// btncheck()
	// 변경된 현재 left 위치
	console.log( '현재 left값 : '+ positionleft+"px");
}

/* */
slidermovebar( 0 )
function slidermovebar( no ){
	positionleft = (no*(-243))
	if( positionleft >= (-1215) ){
		boxofficeinfobox.style.left = positionleft+"px";
		let boxofficebar = document.querySelectorAll('.boxofficebar');
		console.log(boxofficebar)
		boxofficebar.forEach((o)=>{
			o.style.opacity = "0.3";
		})
		document.querySelector('.boxofficebar'+no).style.opacity = "1";
	}
		
	console.log( '현재 left값 : '+ positionleft+"px");
}

/////////3초마다 박스오피스 슬라이드 넘기기
let barcount = 0;
function changebar(){
	if ( barcount == 0 ){
		barcount = 5;
	}else{
		barcount = 0;
	}
	slidermovebar(barcount)
}

setInterval(()=>{
	changebar();	
},4000)

/////////////////////////////////////

// 박스오피스 영화정보 출력
BoxOfficePrint();
function BoxOfficePrint(){
	$.ajax({
		url : "/movie/boxoffice/servlet" ,
		method : "get" ,
		data : {"type":1} ,
		success : (r)=>{
			console.log(r)
			html = ''
			r.forEach((o,i)=>{
				html += `
					<div class="onemovie">
						<div class="mposter">
							<img class="boxofficegradient" src="/movie/member/img/mypage/boxofficeshadow.png">
							<img alt="" src="${o.pimg}">
							<div class="mrank">${i+1}</div>
						</div>   
						<div class="moviename">${o.title}</div>
					
						<div class="movieinfo">
							<div>${o.count}</div>
							<div>${o.year}</div>
						</div>
					</div>
						
						`
			})
			document.querySelector('.boxofficeinfobox').innerHTML = html;
		}
	})
}




// 넷플릭스top10 영화정보 가져오기
let netflixtopten = null;
getnetflixtopten();
function getnetflixtopten(){
	$.ajax({
		url : "/movie/boxoffice/servlet" ,
		method : "get" ,
		data : {"type":2} ,
		success : (r)=>{
			console.log(r)
			netflixtopten = r;
			
			netflixtopten.forEach((o,i)=>{
				// 객체명.새로운속성명 = 데이터
				o.rank = i+1;	
			})
			console.log(netflixtopten)
			netflixtoptenprint();
		}
	})
}


function netflixslidermove(no){
	// no=1 왼쪽버튼 / no=2 오른쪽버튼
	
	if( no == 1 ){
		// 마지막 인덱스 구하기
		// 마지막인덱스 삭제전 보관
		let lastnetflixmovie = netflixtopten[netflixtopten.length-1]
		// 마지막인덱스 요소 삭제
		//배열명.splice(삭제할인덱스 , 1)  : 해당인덱스 1개 삭제
		netflixtopten.splice(netflixtopten.length-1 , 1)
		
		//배열명.splice( 인덱스 , 0 , 새로운요소 )  : 해당 인덱스 위치에 새로운 요소
		netflixtopten.splice(0,0,lastnetflixmovie)
		console.log(netflixtopten)
		// 출력
		netflixtoptenprint();
	}
	if( no == 2 ){
		let firstnetflixmovie = netflixtopten[0];
		//배열명.splice(삭제할인덱스 , 1)  : 해당인덱스 1개 삭제
		netflixtopten.splice(0,1);
		console.log('테스트')
		console.log(netflixtopten)
		
		//배열명.push(추가할 새로운 요소) : 마지막 인덱스 뒤로추가
		netflixtopten.push(firstnetflixmovie)
		// 출력
		netflixtoptenprint();	
	}
	
}

function netflixtoptenprint(){
	let html = '';
	netflixtopten.forEach((o,i)=>{
		html += `
				<div class="netflixonemovie netflix${i+1}">
					<img class="netflixposter" src="${o.pimg}">
				</div>
				`
	})
	document.querySelector('.netflixtoptendiv').innerHTML = html;
	document.querySelector('.netflixrankname').innerHTML = netflixtopten[0].title;
	document.querySelector('.netflixrankno').innerHTML = netflixtopten[0].rank;	
}

let upcomingmoivelist = []
/* 개봉예정작 출력 */
getupcomingmovie();
function getupcomingmovie(){
	
	for(let i=1;i<=2;i++){
		$.ajax({
			url : "https://api.themoviedb.org/3/movie/upcoming?api_key=fc5bcf6e9c88d59559fafe20b6032a0e",
			method : "get",
			data : {
				"language" : "ko-KR",
				"page" : i,
				"region" : "KR"
			},
//			async : false,
			success : (r)=>{
				//let list = r.results
				console.log('expect테스트')
				r.results.forEach((o)=>{
					upcomingmoivelist.push(o)	
				})
				console.log(upcomingmoivelist)
				console.log('i: '+i)
				if ( i == 1 ){
					upcomingmovieprint(0)	
				}
					
				
			} // success e
		}) // ajax e
		
	} // for e
	

}

// 개봉예정작 출력
function upcomingmovieprint(){
	
	let today = new Date(); // 오늘날짜
	console.log(today)
	let o = upcomingmoivelist[0]
	
	
	 
	console.log(o)
	console.log(o.release_date);
	let upcomingdate = new Date(String(o.release_date))
	console.log(upcomingdate)
	
	let time = upcomingdate.getTime() - today.getTime(); // 개봉일과 오늘을 밀리초단위로 차이계산
	let dday = Math.ceil( time/(1000*60*60*24)); // 밀리초단위를 1000으로 나누면 1초단위 -> 60초로나누면 1분단위 -> 60분으로 나누면 1시간단위 -> 24시간으로 나누면 하루단위 -> Math.ceil()로 소수점 올림. 두 날짜 간의 일수차이 정수값 
	
	let html = `
					<div class="upcomingmovie appear">
						<div class="upcomingposterdiv">
							<img class="upcomingposter" src="https://image.tmdb.org/t/p/w500/${o.poster_path}">
						</div>
						<div class="upcominginfo">
							<div class="upcomingtitlediv">
								<div class="upcomingtitle">${o.title}</div>
								<div class="upcomingdday">D-${dday}</div>
							</div>
							<div class="upcomingdate">개봉일 | <span>${o.release_date}</span></div>
							<div class="upcomingtext">
								${o.overview}					
							</div>
							<a href="/movie/admin/expected.jsp"> 
								<div class="upcomingpagebtn">개봉예정작 보러가기 ></div>
							</a>
						</div> <!-- upcominginfo end -->
					</div> <!-- upcomingmovie end -->
				`
	document.querySelector('.upcomingmoviediv').innerHTML = html;
	
}

setInterval(()=>{
	// 첫번재 개봉예정작 따로 저장
	firstmovie = upcomingmoivelist[0]; 
	// 첫번째 개봉예정작 삭제
	upcomingmoivelist.splice(0,1)
	// 마지막순서로 첫번째개봉예정작 다시 추가
	upcomingmoivelist.push(firstmovie);
	// 개봉예정작 출력
	upcomingmovieprint()
},10000)
//let upcomingmovie = document.querySelector('.upcomingmovie')
//if (upcomingmovie.classList.contains('appear')) {
//    upcomingmovie.classList.add('disappear');
//    setTimeout(function(){ upcomingmovie.classList.remove('appear')},1001);
//}