
console.log('header js 실행')

let memberInfo = null; // <-- 전역변수

// 로그인한 회원정보 호출
getLogin();
function getLogin(){
	$.ajax({
		url : "/movie/login",
		async : false ,
		method : "get" ,
		success : (r)=>{
			console.log('통신')
			console.log(r)
			memberInfo = r;
			let html = ''
			if ( r == null ){ // 로그인 안함
				html += `
						<span class="submenumargin"> <a href="/movie/member/signup.jsp">회원가입</a> </span>
						<span class="submenumargin"> <a href="/movie/member/login.jsp">로그인</a> <span>
						`
			}else{ // 로그인 // memberInfo에 mno , mid , memail , mimg 있음
				if ( r.mid == 'admin' ){
					document.querySelector('.mainmenuright').style.display = "flex";
				}
				
				html +=`
						<img src="/movie/member/img/${r.mimg == null ? 'default.webp' : r.mimg }" class="profileimg">
						`
			}
			document.querySelector('.profileimgdiv').innerHTML = html;
			
		}
	})
}

// 드롭다운 제이쿼리
$(".profileimg").click(function() {

        $(".dropdown_menu").stop().slideToggle(500)

      })