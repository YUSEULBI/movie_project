console.log('newplayinglist js 열림')

if(memberInfo==null){
	alert('관리자가 아닙니다.')
	location.href = "/movie/member/login.jsp"
}
if(memberInfo.mid!='admin'){
	alert('관리자가 아닙니다.')
	location.href = "/movie/member/login.jsp"
}

/*
	// Date에 두 date 사이의 날짜 가격을 찾아주는 함수추가
	Date.prototype.getInterval = function (otherDate) {
	    var interval;
	 
	    if(this > otherDate)
	        interval = this.getTime() - otherDate.getTime();
	    else
	        interval = otherDate.getTime() - this.getTime();
	 
	    return Math.floor(interval / (1000*60*60*24));
	}

*/

// 사용하는 전역변수
let today = new Date();
let year = document.querySelector('.fromyear').innerHTML == 'null'? today.getFullYear() : (document.querySelector('.fromyear').innerHTML)*1 // 이번 년도
let month = document.querySelector('.frommonth').innerHTML == 'null'? today.getMonth()+1 : (document.querySelector('.frommonth').innerHTML)*1 // 이번 달 (표시할때는 +1 해야함)
let date = document.querySelector('.fromdate').innerHTML == 'null'? today.getDate() : (document.querySelector('.fromdate').innerHTML)*1 	// 오늘 날짜
let day = today.getDay() //  오늘 요일 ( 0: 일요일~~6:토요일)
let selectday = year==null? null : year+(month<10?"0"+month:month)+(date<10?"0"+date:date);
let selecttime = null;
let tomorrow = year==null? null : year+(month<10?"0"+month:month)+((date+1)<10?"0"+(date+1):(date+1));


// 웹페이지가 열렸을때 실행 될 함수
startweb()
setSelectday()
function startweb(){
	// 특정 날짜의 마지막 날 가져오기
	let lastDay = new Date(year, month, 0).getDate();
	let html = '';
	// 년,월,일 셀렉트 출력
	for(let i=year;i<=year+1;i++){
		html += `<option>
				${i}
				</option>`
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
	html = ';'
	for(let i=1;i<=lastDay;i++){
		if(i==date){
			html += `<option selected>
					${i}
					</option>`
		}else{
			html += `<option>
					${i}
					</option>`
		}
	}
	document.querySelector('.date').innerHTML = html;
	// 시간 셀렉트 출력
	html = ``;
	for(let i =6;i<=22;i++){
		if(i==12){
			html += `<option selected>
						${i}
					</option>`
			
		}else{
			html += `<option>
					${i<10? "0"+i : i}
				</option>`
		}
		
	}
	document.querySelector('.hour').innerHTML = html;
	html = '';
	for(let i =0;i<=50;i+=10){
		html += `<option>
					${i<10? "0"+i : i}
				</option>`
	}
	document.querySelector('.minute').innerHTML = html;
	
	// 현재 상영중인 영화 출력
	html = `<tr>
				<th>제목</th>
			</tr>`;
	$.ajax({
		url : "/movie/admin/movie",
		method : "get",
		success : (r)=>{
			console.log(r)
			r.forEach((o)=>{
				html += `<option value="${o.mno},${o.title}">
							${o.title}
						 </option>`
			})
			document.querySelector('.selectMovie').innerHTML = html;
		}
	})
	
	
}
// 달이 변경되었을때 함수
function monthchange(){
	month = document.querySelector('.month').value
	let html = '';
	let lastDay = new Date(year, month, 0).getDate();
	for(let i=1;i<=lastDay;i++){
		html += `<option>
				${i}
				</option>`
	}
	document.querySelector('.date').innerHTML = html;
}


//날짜선택버튼을 눌렀을때
function setSelectday(){
	let selectyear = document.querySelector('.year').value
	let selectmonth = document.querySelector('.month').value
	let selectdate = (document.querySelector('.date').value)*1
	console.log(typeof(selectdate))
	selectday = selectyear+ "-"+
				`${ selectmonth<10? "0"+selectmonth : selectmonth}`+ "-"
			+`${selectdate<10? "0"+selectdate : selectdate}`;
	tomorrow = selectyear+ "-"+
				`${ selectmonth<10? "0"+selectmonth : selectmonth}`+ "-"
			+`${(selectdate+1)<10? "0"+(selectdate+1) : (selectdate+1)}`;
	console.log(selectday);
	document.querySelector('.receivedate').innerHTML = selectday
	getMovieList()
}
// 시간 선택버튼을 눌렀을때
function setSelecttime(){
	let selecthour = document.querySelector('.hour').value
	let selectminute = document.querySelector('.minute').value
	selecttime = selecthour +":"+selectminute
	if(selectday!=null){
		console.log(selecttime)
		document.querySelector('.receivedate').innerHTML = selectday+" "+selecttime
		getMovieList()
	}else{
		alert('날짜를 먼저 선택해주세요');
		return;
	}
}

//가격을 선택했을때
setPrice()
function setPrice(){
	let price = parseInt(document.querySelector('.price').value) 
	console.log(typeof(price))
	document.querySelector('.receiveprice').innerHTML = price.toLocaleString()+"원"
}


// 영화 선택 함수
function selectMovie(){
	let movieinfo = document.querySelector('.selectMovie').value
	let mno = movieinfo.split(",")[0]
	let title = movieinfo.split(",")[1]
	console.log(mno)
	console.log(title)
	let html = `<span class="selectTitle" value="${mno}">${title}</span>`
	document.querySelector('.receivetitle').innerHTML = html;
}

// 영화관 선택 함수
setScreen()
function setScreen(){
	let screen = document.querySelector('.screen').value
	document.querySelector('.receivescreen').innerHTML = screen+"관"
}
// 상영정보 가져오기
getMovieList()
function getMovieList(){
	console.log('getmovielist 시작')
	if(selecttime==null){ // 날짜는 선택하고 시간은 선택 안했을때
	console.log(selectday)
	console.log(tomorrow)
		$.ajax({
			url : "/movie/admin/movieList",
			method : "get",
			data : { 
				"type" : 3, 
				"selectday" : selectday, 
				"tomorrow" : tomorrow
			},
			success : (r)=>{
				console.log(r)
				result(r)
			}// success e
		}) // ajax e
	} // else if e
	else{
		console.log(selectday)
		console.log(tomorrow)
		$.ajax({
			url : "/movie/admin/movieList",
			method : "get",
			data : { 
				"type" : 3,
				"selectday" : selectday,
				"tomorrow" : tomorrow
			},
			success : (r)=>{
				console.log(r)
				result(r)
			}// success e
		}) // ajax e
	}
} // getMovieList e

// 검색결과 출력
function result(r){
	let html = `<tr>
					<th>영화제목</th>
					<th>상영관</th>
					<th>상영시간</th>
					<th>예약</th>
					<th>잔여</th>
					<th>비고</th>
				</tr>`;
	r.forEach((o)=>{
		html +=`<tr>
					<td>${o.title}</td>
					<td>${o.sno}관</td>
					<td>${o.playtime}</td>
					<td>${o.pseat}</td>
					<td>${o.pstate ? o.rseat+"석" : "만석"}</td>
					<td>비고</td>
				</tr>`
	})
	document.querySelector('.movielisttable').innerHTML = html
	
}

// 영화등록 함수
function newplayinglist(){
	let pprice = document.querySelector('.price').value
	let playtime = document.querySelector('.receivedate').innerHTML==null? null : (document.querySelector('.receivedate').innerHTML)+":00";
	let mno = document.querySelector('.selectMovie').value.split(",")[0]
	let sno = document.querySelector('.screen').value
	if(document.querySelector('.receiveprice').innerHTML==""||selecttime==null
		||document.querySelector('.receivetitle').innerHTML==""||document.querySelector('.receivescreen').innerHTML==""){
		alert('정보를 모두 선택해주세요')
		return;
	}
	
	$.ajax({
		url : "/movie/admin/movieList",
		method : "post",
		data : {
			"pprice" : pprice,
			"playtime" : playtime,
			"mno" : mno,
			"sno" : sno
		},
		success : (r)=>{
			console.log(r)
			if(r=='true'){
				getMovieList()
			}
		}
	})
	
}

