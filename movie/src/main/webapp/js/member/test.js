console.log('test.js연결')

let title = "웅남이"
$.ajax({
	url : "/movie/search/poster" ,
	method : "get" ,
	data : { "title":title } ,
	success : (r)=>{
		console.log(r)
	}
})