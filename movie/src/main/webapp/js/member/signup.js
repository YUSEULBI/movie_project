console.log('signup js실행')



function signup(){
	console.log('signupbtn함수 실행')
	
	if ( pwdcheckno == 1 && repwdcheckno == 1 && emailcheckno == 1 ){
		
		let signupForm = document.querySelectorAll('.signupForm')[0];
		let signupFormData = new FormData( signupForm );
		console.log(signupFormData)
		
		$.ajax({
			url : "/movie/member/info",
			method : "post" ,
			data : signupFormData ,
			contentType : false , 
			processData : false ,
			success : (r)=>{
				console.log('통신')
				console.log(r)
				if ( r == 'true'){
					alert('회원가입 성공')
					location.href="/movie/index.jsp"
				}else{
					alert('회원가입 실패')
				}
			}
		})
	}else{
		alert('회원정보를 올바르게 입력해주세요.')
	}
}

// 전역변수 
let confirminput = document.querySelectorAll('.confirminput')
console.log(confirminput)

function idcheck(){
	// 5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.
	let mid = document.querySelector('.mid').value;
	let midj = /^(?=.*[a-z])(?=.*[0-9])[a-z0-9_-]{5,20}$/
	if ( midj.test(mid)){
		$.ajax({
			url : "/movie/midconfirm" ,
			method : "get" ,
			data : {"mid":mid} ,
			success : (r)=>{
				console.log('통신')
				console.log(r)
				if ( r == 'true'){
					console.log('사용중인 아이디 X')
					confirminput[0].innerHTML = "사용중인 아이디 입니다."
				}else{
					console.log('사용가능 아이디 O')
					confirminput[0].innerHTML = "사용가능한 아이디 입니다."
				}
				
			}
		})
	}else{
		console.log('5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.')
		confirminput[0].innerHTML = "5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다."	
	}
}
let pwdcheckno = 0;
let repwdcheckno = 0;
let emailcheckno = 0;

function pwdcheck(){
	// 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.
	let mpwd = document.querySelector('.mpwd').value;
	let pwdj = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@\#\$%\^&*\(\)_\-+\=])[a-zA-Z0-9!@\#\$%\^&*\(\)_\-+\=]{8,16}$/
	if( pwdj.test(mpwd)){
		console.log('사용가능 비밀번호 O')
		confirminput[1].innerHTML = "사용가능한 비밀번호 입니다."
		pwdcheckno = 1;
	}else{
		console.log('8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.')
		confirminput[1].innerHTML = "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요."
		pwdcheckno = 0;
	}
}

function repwdcheck(){
	let mpwd = document.querySelector('.mpwd').value;
	let rempwd = document.querySelector('.rempwd').value;
	
	if ( mpwd != rempwd ){
		console.log('비밀번호가 일치하지 않습니다.')
		confirminput[2].innerHTML = "비밀번호가 일치하지 않습니다."
		repwdcheckno = 0;
	}else{
		console.log('비밀번호 일치 O')
		confirminput[2].innerHTML = "비밀번호가 일치합니다."
		repwdcheckno = 1;
	}
}

function emailcheck(){
	let memail = document.querySelector('.memail').value;
	let memailj = /^[a-zA-Z0-9+-_.]*@[a-zA-Z0-9-]*\.[a-zA-Z]{2,3}$/
	if ( memailj.test(memail)){
		console.log('이메일 확인 O')
		confirminput[3].innerHTML = "이메일 형식이 확인되었습니다."
		emailcheckno = 1;
	}else{
		console.log('이메일 주소를 다시 확인해주세요.')
		confirminput[3].innerHTML = "이메일 주소를 다시 확인해주세요."
		emailcheckno = 0;
	}
}

// 프로필이미지 미리보기
function premimg( object ){
	
	let file = new FileReader();
	file.readAsDataURL( object.files[0])
	file.onload = (e)=>{
		//console.log(e.target.result)
		document.querySelector('.newmimg').src = e.target.result;
	}
	
}
