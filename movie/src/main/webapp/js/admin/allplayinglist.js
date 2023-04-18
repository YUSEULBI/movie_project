console.log('allplayinglist js 시작')
if(memberInfo==null){
	alert('관리자가 아닙니다.')
	location.href = "/movie/member/login.jsp"
}
if(memberInfo.mid!='admin'){
	alert('관리자가 아닙니다.')
	location.href = "/movie/member/login.jsp"
}
// 사용하는 전역변수
let today = new Date()
let year = today.getFullYear() // 이번 년도
let month = today.getMonth()+1 // 이번 달 (표시할때는 +1 해야함)
let date = today.getDate()	// 오늘 날짜
let day = today.getDay() //  오늘 요일 ( 0: 일요일~~6:토요일)
let selectday = year+"-"+(month<10 ? "0"+month : month)+"-01";
let selecttime = null;
let selectmoviinfo = null;




// 영화 예매 차트 출력
printChart1();
function printChart1(){
	document.querySelector('.canvasbox').innerHTML = `<canvas id="myChart1"></canvas>`
	let ctx1 = document.getElementById('myChart1');
	$.ajax({
		url : "/movie/admin/test",
		method : "post",
		data : {"type" : 1},
		success : (r)=>{
			console.log(r)
			console.log(Object.keys(r))
			console.log(Object.values(r))
		
			new Chart(ctx1, {
				type: 'bar',	// bar : 막대차트 / line : 선 차트
				data: {
				  labels: Object.keys(r),
				  datasets: [{
					backgroundColor: 'rgb(0, 168, 210)',
				    label: '예매좌석수',
				    data: Object.values(r),
				    borderWidth: 1
				  }]
				},
				options: {
				  scales: {
				    y: {
				      beginAtZero: true
				    },
		            x: {
		              beginAtZero: true,
		              ticks:{ // x축 줄당 표시 값
                			stepSize:5
              			}
		            }
				  }
				} // options e
			}); // chart e
		} // success e
	}) // ajax e
} // printchart1 e

function printChart2(){
	document.querySelector('.canvasbox').innerHTML = `<canvas id="myChart2"></canvas>`
	let ctx2 = document.getElementById('myChart2');
	$.ajax({
		url : "/movie/admin/test",
		method : "post",
		data : {"type" : 2},
		success : (r)=>{
			console.log(r)
			console.log(Object.keys(r))
			console.log(Object.values(r))
		
			new Chart(ctx2, {
				type: 'bar',	// bar : 막대차트 / line : 선 차트
				data: {
				  	labels: Object.keys(r),
					datasets: [{
						backgroundColor: 'rgb(179, 255, 0)',
					  label: '영화별 매출',
					  data: Object.values(r),
					  borderWidth: 0.5
					}]
				},
				options: {
				  scales: {
				    y: {
				      beginAtZero: true
				    }
				  }
				} // options e
			}); // chart e
		} // success e
	}) // ajax e 
} // printchart1 e


startweb()
function startweb(){
	// 특정 달의 첫번째 날의 요일 가져오기
	let firstday = new Date(year, month-1, 1).getDay();
	// 특정 달의 마지막 날 가져오기
	let lastDay = new Date(year, month, 0).getDate();
	let html = '';
	// 년,월,일 셀렉트 출력
	for(let i=year-1;i<=year+1;i++){
		if(i==year){
			html += `<option selected>
					${i}
					</option>`
		}else{
			html += `<option>
					${i}
					</option>`
		}
	}
	document.querySelector('.year').innerHTML = html;
	html = '';
	for(i=1;i<=12;i++){
		if(i==month){
			html += `<option selected>
					${i}
					</option>`
		}else{
			html += `<option>
					${i}
					</option>`
		}
	}
	document.querySelector('.month').innerHTML = html;
	// 달력 첫줄에 요일 출력
	html = `
		<div class="day sunday">일</div>
		<div class="day">월</div>
		<div class="day">화</div>
		<div class="day">수</div>
		<div class="day">목</div>
		<div class="day">금</div>
		<div class="day saturday">토</div>
		`
	// 첫날 전에 빈칸 출력
	for(let i=0;i<firstday;i++){
		html += `<div class="date"> </div>`
	}
	// 마지막 날까지 출력
	for(let i=1;i<=lastDay;i++){
		// 날짜에 맞는 상영정보를 받기 위해 div안에 onclick= dateinfo
		if(i==date){
			html += `<div class="date dateinfo${i<10? "0"+i : i} selectedDate" onclick="dateinfo(${i})">
					${i}
					</div>`
		}else{
			html += `<div class="date dateinfo${i<10? "0"+i : i}" onclick="dateinfo(${i})">
					${i}
					</div>`
		}
	}
	document.querySelector('.selectdate').innerHTML = html;
	// 달력 출력 후 그 달의 모든 영화 상영정보 가져오기
	getallmovielist()
}


// 영화 상영정보 가져오기
function getallmovielist(){
	console.log('selectday : ' + selectday)
	$.ajax({
		url : "/movie/admin/movieList",
		method : "get",
		data : { "type" : 1, "selectday" : selectday},
		success : (r)=>{
			console.log(r)
			selectmoviinfo = r;
			r.forEach((o,i)=>{
				let html = ``;
				// playtime이 2222-01-01 16:30:00 이런식으로 들어오기 때문에 -기준으로 나눈뒤 년 월 일로 나누기
				let getyear = o.playtime.split("-")[0]
				let getmonth = o.playtime.split("-")[1]
				let getdate = o.playtime.split("-")[2].split(" ")[0]
				if(getyear==year&&getmonth==month){
					html += `<div class="movieinfo no${i}" value="${i}">
								${o.title}
							</div>`
				}
				document.querySelector('.dateinfo'+(getdate) ).innerHTML += html
				
			})
		}// success e
	}) // ajax e
}

// 선택한 날의 모든 상영정보를 가져오는 함수
function dateinfo(i){
	document.querySelector('.modal1').style.display = 'block'
	document.querySelector('.modal1_title').innerHTML = year+"년 "+month+"월 "+i+"일"
	// div안에 들어가야 하는 onclick 값 onclick="getplayinginfo(${date})""
	$.ajax({
		url : "/movie/admin/movieList",
		method : "get",
		data : {
			"type" : 3,
			"selectday" : year+"-"+month+"-"+ (i<10?"0"+i:i),
			"tomorrow" : year+"-"+month+"-"+ ((i+1)<10?"0"+(i+1):(i+1))
		},
		success : (r)=>{
			console.log(r)
			selectmoviinfo = r
			let html = `<table class="movietable table table-bordered border-primary">
							<tr>
								<th scope="col">영화제목</th>
								<th scope="col">상영관</th>
								<th scope="col">상영시간</th>
								<th scope="col">예약</th>
								<th scope="col">잔여</th>
								<th scope="col">비고</th>
							</tr>`;
			r.forEach((o,i)=>{
				html += `<tr>
							<td>${o.title}</td>
							<td>${o.sno}관</td>
							<td>${o.playtime.substr(11, 5)}</td>
							<td>${o.pseat}</td>
							<td>${o.pstate ? o.rseat+"석" : "만석"}</td>
							<td><button class="btn btn-outline-info" onclick="getplayinginfo(${i})" type="button">상세</button></td>
						</tr>
						`
			}) // for e
			html += `</table>`
			document.querySelector('.modal1_content').innerHTML = html
			html = `<button onclick="addplayinglist(${i})" class="btn btn-outline-info" type="button">상영 추가</button>
					<button onclick="closeModal1()" class="modal_cencel btn btn-outline-info" type="button">닫기</button>
					`;
			document.querySelector('.modal1_btns').innerHTML = html;
		} // success e
	}) // ajax e
} // dateinfo e


// 선택한 상영정보를 모달에 띄우는 함수
function getplayinginfo(i){
	console.log('버튼 눌림')
	let playingmovie = selectmoviinfo[i]
	console.log(playingmovie)
	let playtime = playingmovie.playtime
	let movieimg = null;
	console.log(playingmovie.title)
	$.ajax({
		url : "https://api.themoviedb.org/3/search/movie?api_key=fc5bcf6e9c88d59559fafe20b6032a0e",
		method : "get",
		async : false,
		data : {
			"query" : playingmovie.title,
			"language" : "ko-KR"
		},
		success : (r)=>{
			console.log(r)
			let equalslist = [];
			r.results.forEach((o)=>{
				if(o.title==playingmovie.title){
					console.log(o)
					equalslist.push(o)
				}
			}) // for e
			movieimg = equalslist[0].poster_path
		} // success e
	}) // ajax e
	console.log(movieimg)
	document.querySelector('.modal2').style.display = 'block'
	document.querySelector('.modal2_title').innerHTML = playingmovie.title
	let html = `<img class="movieimg" src="https://image.tmdb.org/t/p/w500/${movieimg}">
				<table class="modaltable">
					<tr>
						<th scope="col">상영일자</th>
						<td>${playtime.substring(0,10)}</td>
					</tr>
					<tr>
						<th scope="col">상영시간</th>
						<td>${playtime.substring(11,16)}</td>
					</tr>
					<tr>
						<th scope="col">예약현황</th>
						<td>${playingmovie.pseat}</td>
					</tr>
					<tr>
						<th scope="col">잔여좌석</th>
						<td>${playingmovie.rseat}</td>
					</tr>
				</table>
				<button onclick="pdelete(${playingmovie.pno})" class="btn btn-outline-info" type="button">삭제</button>
				`;
	document.querySelector('.modal2_content').innerHTML = html
	
	
}

// 모달 닫기
function closeModal1(){	document.querySelector('.modal1').style.display = 'none'}
function closeModal2(){	document.querySelector('.modal2').style.display = 'none'}
// 버튼 함수
function minusYear(){
	year--; 
	startweb()
}
function plusYear(){
	year++;	
	startweb()
}
function minusMonth(){
	month--;
	if(month<=0){
		year--;
		month = 12;
	}	
	startweb();}
	
function plusMonth(){
	month++;
	if(month>12){
		year++;	
		month=1;
	}	
	startweb()
}

// 셀렉트 함수
function clickyear(){year = ((document.querySelector('.year').value)*1);	startweb()}
function clickmonth(){month = ((document.querySelector('.month').value)*1);	startweb();}
// 상영 추가 버튼 클릭 함수
function addplayinglist(i){
	location.href="/movie/admin/newplayinglist.jsp?year="+year+"&month="+month+"&date="+(i<10? "0"+i : i)
}
// 상영정보 삭제함수
function pdelete(pno){
	$.ajax({
		url : "/movie/admin/movieList",
		method : "delete",
		data : { "pno" : pno},
		success : (r)=>{
			console.log(r)
			if(r=='true'){
				alert('삭제되었습니다.');
				closeModal2();
				closeModal1();
				startweb();
			}
		}
	})
}


window.addEventListener("keyup",(e)=>{
	if(e.keyCode=='27'){
		closeModal1()
		closeModal2()
	}
})

