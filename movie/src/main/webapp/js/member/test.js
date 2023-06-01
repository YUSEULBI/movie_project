console.log('test.js연결')

// kobixApi BoxOffice구하기
kobisBoxOffice();
function kobisBoxOffice(){
	console.log("kobisBoxOffice실행")
	// 오늘기준 하루전 구하기 /////////////////////////////////////////////////
	let today = new Date();
	//console.log("today : " + today)
	let yesterday = new Date(today.setDate(today.getDate()-1));
	//console.log("yesterday : "+yesterday)
	
	let year = yesterday.getFullYear();
	let month = yesterday.getMonth()+1;
	if ( month < 10 ){ month = "0"+month }
	let date = yesterday.getDate();
	
	let targetDt = year+month+date
	console.log("targetDt : "+ targetDt)
	
	let url = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=e4bef537aa0ae3d7f267d6c813f6cbf3&targetDt="+targetDt;
	console.log(url)
	$.ajax({
		url:url,
		method : "get",
		success : (r)=>{
			console.log("api실행")
			console.log(r.boxOfficeResult.dailyBoxOfficeList)
			//dailyBoxOfficeList
			
		}
		
	})
}

//seleniumtest();
function seleniumtest(){
	console.log("selenium테스트")
	$.ajax({
		url:"/movie/movie/selenium" ,
		method : "get" ,
		success: (r)=>{
			console.log(r)
			
		}
	})
}


// htmlUnit 테스트
//htmlUnitTest();
function htmlUnitTest(){
	console.log("htmlUnit테스트")
	$.ajax({
		url:"/movie/htmlunit" ,
		method : "get" ,
		success: (r)=>{
			console.log(r)
		}
	})
}


//test();
function test(){
	console.log("test함수실행")
	$.ajax({
		url : "/movie/testcrawling",
		method : "get" ,
		data : { type : 1} ,
		success : (r)=>{
			console.log(r)
		} 
	})
}


//testkofic();
function testkofic(){
	console.log("testkofic함수실행")
	$.ajax({
		url : "/movie/testcrawling",
		method : "get" ,
		data : { type : 2} ,
		success : (r)=>{
			console.log(r)
		} 
	})
}

//kobisprint();
function kobisprint(){
	$.ajax({
		url : "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json"+
					"?key=e4bef537aa0ae3d7f267d6c813f6cbf3"+
					"&targetDt=20230423"
				 ,
		method : "get" ,
		success : (r)=>{
			console.log(r)
		}
	})
}

//kobismoviesearch();
let movielist = [] 
function kobismoviesearch(){
	$.ajax({
		url : "http://kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json?key=e4bef537aa0ae3d7f267d6c813f6cbf3&openStartDt=2023&itemPerPage=100" ,
		method : "get" ,
		success : (r)=>{
			//console.log(r)
			//console.log(r.movieListResult.movieList)
			r.movieListResult.movieList.forEach((m)=>{ if( !m.genreAlt.includes("에로") ){ movielist.push(m)	}	})
			console.log(movielist)
		}
	
	})
}