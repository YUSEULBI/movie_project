console.log('expected js 시작')

let movielist = []

start()
function start(){
	for(let i=1;i<=2;i++){
		$.ajax({
			url : "https://api.themoviedb.org/3/movie/upcoming?api_key=fc5bcf6e9c88d59559fafe20b6032a0e",
			method : "get",
			data : {
				"language" : "ko-KR",
				"page" : i,
				"region" : "KR",
				"release_date": "2023-03-00T00:00:00.000Z"
			},
			async : false,
			success : (r)=>{
				let list = r.results
				list.forEach((o)=>{
					if(o.title=='레디 플레이어 원'){
						o.release_date = '2023-04-12'
					}
					movielist.push(o)
				}) // for e
			} // success e
		}) // ajax e
	} // for e
	console.log(movielist)
	dateprint()
}


function dateprint(){
	let html = '';
	let datelist = []
	// 가져온 리스트를 날짜 순서대로 정렬
	movielist = movielist.sort((a, b) => new Date(a.release_date) - new Date(b.release_date))
	movielist.forEach((o,i)=>{
		if(datelist.indexOf(o.release_date)<0){
			datelist.push(o.release_date)
		} //if e
	}) // for e
	datelist.forEach((o)=>{
		let getDate = new Date(o)
		let month = getDate.getMonth()+1
		let date = getDate.getDate()
		let getday = new Date(o)
		let today = new Date()
		getday.setHours(getday.getHours()+9,0,0);
		today.setHours(today.getHours()+9,0,0);
		let togo=today.getTime()-getday.getTime();
		let days=Math.abs(Math.floor(togo/(1000*60*60*24)));
		html += `<div class="dateList rounded-3">
					<div class="dateinfo">
						<h5>${month}월</h5>
						<h3>${date}일</h3>
					</div>
					<div class="dday"><span class="fontD">D-</span>${days}</div>
					<div class="date${o} movieblock"></div>
				</div>`
	})
	document.querySelector('.movielist').innerHTML = html
	moiveprint()
} // dateprint e

function moiveprint(){
	movielist.forEach((o,i)=>{
		let html = '';
		html = `<div onclick="openmodal(${i})" class="moviebox">
					<div class="imgbox">
						<img class="mimg" src="https://image.tmdb.org/t/p/w500/${o.poster_path}">
					</div>
					<div>
						<h4>${o.title}</h4>
					</div>
				</div>`
		document.querySelector('.date'+o.release_date).innerHTML += html
	})
}

function getcastinfo(movieCd){
	if(movieCd==null){
		return null;
	}
	let movieinfo = null;
	$.ajax({
		url : "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json",
		method : "get",
		async : false,
		data : {
			"key" : "879c7d6c72e8cf797dba5949ba07c616",
			"movieCd" : movieCd,
		},
		success : (r)=>{
			if(r!=null){
				movieinfo = r.movieInfoResult.movieInfo
			}
		}
	})
	return movieinfo
}

function getmovieinfo(title){
	let movieinfo = null;
	$.ajax({
		url : "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json",
		method : "get",
		async : false,
		data : {
			"key" : "879c7d6c72e8cf797dba5949ba07c616",
			"movieNm" : title,
			"itemPerPage" : 100,
			"openStartDt" : 2023
		},
		success : (r)=>{
			console.log(r)
			if(r.movieListResult.totCnt!=0){
				movieinfo = getcastinfo(r.movieListResult.movieList[0].movieCd)
			}else{
				movieinfo = { "movieCd" : 12345}
			}
		}
	})
	return movieinfo
}

function openmodal(i){
	let movieinfo = getmovieinfo(movielist[i].title)
	console.log(movieinfo)
	document.querySelector('.modal').style.display = 'block'
	document.querySelector('.modal_title').innerHTML = `${movielist[i].title}`
	document.querySelector('.modalimgbox').innerHTML = 
	`<img class="modalimg" src="https://image.tmdb.org/t/p/w500/${movielist[i].poster_path}">`
	document.querySelector('.modaloverview').innerHTML = movieinfo.movieCd == 12345? 
	`	
		<h3>줄거리</h3>
		<div>${movielist[i].overview}</div>
	`
	:
	`	
		<h3>감독</h3>
		<div>${movieinfo.directors[0]==null? "-" : movieinfo.directors[0].peopleNm}</div>
		<h3>출연</h3>
		<div>${movieinfo.actors[0]==null? "-" : movieinfo.actors[0].peopleNm+","+movieinfo.actors[1].peopleNm}</div>
		<h3>줄거리</h3>
		<div>${movielist[i].overview}</div>
	`
}

function closeModal(){	document.querySelector('.modal').style.display = 'none'}

window.addEventListener("keyup",(e)=>{
	if(e.keyCode=='27'){
		closeModal()
	}
})
