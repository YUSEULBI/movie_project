console.log('test.js연결')

test();
function test(){
	console.log("test함수실행")
	$.ajax({
		url : "/movie/testcrawling",
		method : "get" ,
		success : (r)=>{
			console.log(r)
		} 
	})
}