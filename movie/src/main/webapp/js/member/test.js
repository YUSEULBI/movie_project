console.log('test.js연결')

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


testkofic();
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