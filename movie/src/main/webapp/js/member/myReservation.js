console.log('myReservation js 실행')


// 포스터저장하기
//let posterlist = []
//getposter();
//function getposter(){
//		$.ajax({
//			url : "/movie/boxoffice/servlet" ,
//			method : "get" ,
//			async :false ,
//			success : (r)=>{
//				console.log(r)
//				r.forEach((o,i)=>{
//					let poster = { "title":o.title , "pimg": o.pimg}
//					posterlist.push(poster)
//				})
//				console.log(posterlist)
//			}
//		})			
//}

//아이디출력
midprint();
function midprint(){
	document.querySelector('.mypagemid').innerHTML = memberInfo.mid;
}


// 예매조회//////////////////////////////////////////////////////////

// 예약조회페이지 예매한 영화타이틀 list (출력순서대로저장)
let movietitlelist = []
// 영화타이틀로 영화이미지url 구하기
function posterprint(){
	movietitlelist.forEach((title,i)=>{
		$.ajax({
			url : "/movie/search/poster" ,
			method : "get" ,
			data : { "title":title } ,
			success : (r)=>{
				console.log('--------------------------------------------------------------------')
				console.log(title+' 포스터검색결과 r : '+r)
				let classname = (title.split(' ').join(''))+i
				console.log('찾을 클래스명 : '+classname)
				console.log('--------------------------------------------------------------------')
				//document.querySelector('.'+classname).src = r
				document.querySelector('.'+classname).style.backgroundImage= `url(${r})`	
			}
		})
	})
			
}

// 예매출력
myReservationPrint();
function myReservationPrint(){
	$.ajax({
		url : "/movie/myreservation" ,
		method : "get" ,
		async :false ,
		success : (r)=>{
			console.log('통신')
			console.log(r)
						
			
			// o.rno / o.seatnum / o.mno / o.plistdto
		
			// o.plistdto.pno / o.plistdto.pstate / o.plistdto.pseat 
			// o.plistdto.pprice / o.plistdto.playtime 
			// o.plistdto.moviedto / o.plistdto.screendto
			
			// o.plistdto.moviedto.mno / o.plistdto.moviedto.title / o.plistdto.moviedto.openDt 
			// o.plistdto.moviedto.directors / o.plistdto.moviedto.genre / o.plistdto.moviedto.audience
			// o.plistdto.moviedto.mstate

			// o.plistdto.screendto.seat / o.plistdto.screendto.sno
			
			// pno만 담기 ( 중복pno제거 )
			let pnolist = []
			r.forEach((o)=>{
				//배열명.includes(데이터) :  찾을데이터의 존재 여부 true / 없으면 false
				if ( !(pnolist.includes(o.plistdto.pno)) ){
					pnolist.push(o.plistdto.pno)	
				}
			})	
			console.log(pnolist)
			// 동일한 상영정보 별로 묶을 rlist / pno가 같으면 같은예매(영화,상영시간,상영관이 같음) / 한번에 여러명 예매했을 경우를 생각해서 상영정보별로 묶은 rlist
			let rlist = []
			pnolist.forEach((o)=>{ // pnolist forEach
				let rlistone = [] // rlist 배열에 담길 예약정보r 배열
				// pnolist 돌때
				r.forEach((r)=>{
					if ( o == r.plistdto.pno ){
						rlistone.push(r)		
					}
				})	
				rlist.push(rlistone)
			}) 
			console.log(rlist)
			
			// 좌석출력
			function seatnumprint( o ){ // o는 동일한 상영정보를 가진 예약리스트
				let seatnumlist = []
				o.forEach((n)=>{
					seatnumlist.push(n.seatnum) 
				})
				return seatnumlist;
			}
			// 결제금액출력
			function ppriceprint(o){
				let pprice = 0;
				o.forEach((p)=>{
					console.log("1건 주문금액 : "+p.plistdto.pprice)
					pprice = pprice + p.plistdto.pprice
				})
				console.log("pprice : "+pprice)
				return pprice;
			}
			
			
	
			
			// html 출력
			let html = ''
			rlist.forEach((o,i)=>{
				movietitlelist.push((o[0].plistdto.moviedto.title))
				// o안에는 영화정보별 arraylist , for문을 안에 더 돌려주거나 인덱스로 호출해야함.
				console.log(o[0].plistdto.moviedto.title)
				console.log(o[0].plistdto.playtime)
				console.log(o[0].plistdto.screendto.sno)
				console.log(o[0].seatnum)
				console.log(o[0].plistdto.pprice.toLocaleString())
				
				//<div class="pimg"><img class="poster , ${(o[0].plistdto.moviedto.title+i).split(' ').join('')}" src=""></div>  
				html += `
						<div class="oneReservation , ${(o[0].plistdto.moviedto.title+i).split(' ').join('')}" style="background-image: URL();">
							<img class="reinfogradient" onclick="onpenReCancelModal(${o[0].plistdto.pno})" src="/movie/member/img/mypage/gradientblack.png">
							<div class="reinfo">
								<div class="title">${o[0].plistdto.moviedto.title}</div>
								<div class="rinfotext playtime">${o[0].plistdto.playtime}</div>
								<div class="screeninfo">
									<div class="rinfotext sno">${o[0].plistdto.screendto.sno}관&nbsp;·&nbsp;</div>
									
									<div class="rinfotext seatnum">${seatnumprint(o)}</div>
								</div>
								<div class="rinfotext pprice">${ppriceprint(o).toLocaleString()}원</div>
							</div>
						</div> 
						`
				
			})
			document.querySelector('.reservationDiv').innerHTML = html
			console.log('movietitlelist : '+movietitlelist)
			console.log('movietitlelist.length : '+movietitlelist.length)
			posterprint();	
			recountcheck(); // 예매내역없으면 	
			
		}
		
	})
}


// if 예매내역없으면
function recountcheck(){
	if ( movietitlelist.length == 0 ){
		document.querySelector('.reservation').style.display = 'none'
		document.querySelector('.reservation').style.height = '100px'
		document.querySelector('.reservation').innerHTML = '예매내역이 없습니다.'
	}else{
		document.querySelector('.reservation').style.display = 'block';
	}
}

//////////////////////////////////////////////////////////////////
// 예매취소
function canclereservation(){
	let pno = document.querySelector('.cancelpno').value
	console.log("pno : "+pno)
	$.ajax({
		url : "/movie/myreservation" ,
		method : "delete" ,
		data : { "pno":pno } ,
		success : (r)=>{
			console.log(r)
			if ( r == 'true' ){
				location.href="/movie/member/mypage.jsp?page=1"
			}else{
				console.log('예매취소 실패 - 관리자문의')
			}
		}
	})
}

///////////////////////////////////////////////////////////////////

// 슬라이더움직이기
// 슬라이더 position 값 설정
// position absolute 구역 가져오기
let reservationDiv = document.querySelector('.reservationDiv')
// position 위치 전역변수 선언
let positionleft = 0;
//let positiontop = 0;

// position 위치 슬라이더에 적용
reservationDiv.style.left = positionleft+"px";
//boxofficeinfobox.style.top = positiontop+"px";

//버튼출력유무체크
btncheck()
// left 값에 따라서 left버튼 생김,숨김
function btncheck(){
	if ( positionleft == 0 ){
		document.querySelector('.sliderleftbtn').style.display = 'none'
		document.querySelector('.leftmouseover').style.display = 'none'
		
	}else{
		document.querySelector('.sliderleftbtn').style.display = 'block'
		document.querySelector('.leftmouseover').style.display = 'block'
	}
	

	
	if ( positionleft == (-((movietitlelist.length-4)*305)) || movietitlelist.length <= 4){
		document.querySelector('.sliderrightbtn').style.display = 'none'
		document.querySelector('.rightmouseover').style.display = 'none'
	}else{
		document.querySelector('.sliderrightbtn').style.display = 'block'
		document.querySelector('.rightmouseover').style.display = 'block'
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
	reservationDiv.style.left = positionleft+"px";
	// 변경된 위치에 따른 버튼출력유무체크
	btncheck()
	// 변경된 현재 left 위치
	console.log( '현재 left값 : '+ positionleft+"px");
}


/* 마우스오버div에 마우스오버했을때 */
let leftmouseover = document.querySelector('.leftmouseover')
leftmouseover.addEventListener('mouseover',()=>{
	console.log("mouseover실행")
	document.querySelector('.leftshadow').style.display = 'block'
	document.querySelector('.sliderleftbtn').style.opacity = '100%'
})

leftmouseover.addEventListener('mouseout',()=>{
	console.log("mouseover실행")
	document.querySelector('.leftshadow').style.display = 'none'
	document.querySelector('.sliderleftbtn').style.opacity = '50%'
})

let rightmouseover = document.querySelector('.rightmouseover')
rightmouseover.addEventListener('mouseover',()=>{
	console.log("mouseover실행")
	document.querySelector('.rightshadow').style.display = 'block'
	document.querySelector('.sliderrightbtn').style.opacity = '100%'
})

rightmouseover.addEventListener('mouseout',()=>{
	console.log("mouseover실행")
	document.querySelector('.rightshadow').style.display = 'none'
	document.querySelector('.sliderrightbtn').style.opacity = '50%'
})


function reservationpagebtn(){
	console.log('reservationpagebtn 함수실행')
	if ( document.querySelector('.reservation').style.display == 'none' ){
		document.querySelector('.reservation').style.display = 'block';	
	}else{
		document.querySelector('.reservation').style.display = 'none';		
	}
	
}

// 회원정보조회 //////////////////////////////////////////////////
document.querySelector('.signupForm').style.display = 'none';
function updatememberpagebtn(){
	if ( document.querySelector('.signupForm').style.display == 'none' ){
		document.querySelector('.signupForm').style.display = 'block';	
	}else{
		document.querySelector('.signupForm').style.display = 'none';		
	}
	
}


// 회원정보페이지 요청시 회원정보 펼치기
mypageOpen();
function mypageOpen(){
	let mypage = document.querySelector('.mypage').value
	if ( mypage == 2 ){
		document.querySelector('.signupForm').style.display = 'block';
		document.querySelector('.reservation').style.display = 'none';				
	}	
}
