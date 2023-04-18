 //상영되는 영화 고유번호 
let pno = document.querySelector('.pno').value
let a_num = 0// adult num 성인 인원 
let t_num = 0// teen num 청소년 인원 
let b_num = 0// baby num 시니어 인원

let seat_num=0 // 선택한 좌석
let pprice = 0 //영화 1인 결제 가격 

let s_seat=[]; // 선택한 좌석 select_seat
let title ; //선택한영화 제목

/* ---------------- 로그인 유효성 검사 ------------------ */
if( memberInfo ==  null ){
		alert('로그인후 이용 가능합니다.')
		location.href= `/movie/index.jsp`;
}

//----------------------- 영화 정보 출력 -----------------
movie_print()
function movie_print(){ 
	$.ajax({
		url:"/movie/ticket",
		method:"get",
		async : false,
		data: {"pno":pno},
		success:(r)=>{
			console.log(r)
			title = r.title
			document.querySelector('.movie_title').innerHTML = r.title;
			document.querySelector('.date').innerHTML = r.playtime.split(" ")[0];
			document.querySelector('.time').innerHTML = r.playtime.split(" ")[1].substr(0, 5);
			document.querySelector('.screen_box').innerHTML = r.sno +'관'
			pprice = r.pprice
			
			//동시에 2곳 적용안됨
			document.querySelector('.modal_title').innerHTML = r.title;
			document.querySelector('.modal_date').innerHTML = r.playtime.split(" ")[0];
			document.querySelector('.modal_time').innerHTML = r.playtime.split(" ")[1].substr(0, 5);
			document.querySelector('.modal_screen').innerHTML = r.sno +'관'
			img_print()
		}
		
	})
}

/*----------------------------------------------- 영화 이미지 출력----------------------------*/
function img_print(){ 
	let html = '';
			$.ajax({
			   url : "https://api.themoviedb.org/3/search/movie?api_key=fc5bcf6e9c88d59559fafe20b6032a0e",
			   method : "get",
			   async:"false",
			   data : {
			      "query" : title ,
			      "language" : "ko-KR"
			   },
			   success : (r)=>{
			
			      r.results.forEach((i)=>{
			         if(i.title== title ){

				         html =	`<img class="movie img" src="https://image.tmdb.org/t/p/w500/${i.poster_path}">`
						 console.log(html)
				         document.querySelector(`.movie_img`).innerHTML = html;
				         document.querySelector(`.movie_info2_img`).innerHTML = html;
			         }//if e
			         
			      }) // for e
			   } // success e
			}) // ajax e
}


/* ----------------------- 영화 인원 증가 ------------------*/
function p_up(human){
	p_max()
	if(human == 1){
		a_num++
		document.querySelector('.a_num').innerHTML= a_num;
	}
	else if (human == 2){
		t_num++;
		document.querySelector('.t_num').innerHTML= t_num;
	}
	else if (human == 3 ){
		b_num++;
		document.querySelector('.b_num').innerHTML= b_num;
	}
	pay_money() // 결제금액 계산
}

/* ----------------------- 영화 인원 감소 ------------------*/
function p_down(human){
	
	if(human == 1){
		if(a_num == 0 ){
			alert('0명 이하로 감소할 수 없습니다.'); return;
		}
		a_num--
		document.querySelector('.a_num').innerHTML= a_num;
	}
	else if (human == 2){
		if(t_num == 0 ){
			alert('0명 이하로 감소할 수 없습니다.'); return;
		}
		t_num--;
		document.querySelector('.t_num').innerHTML= t_num;
	}
	else if (human == 3 ){
		if(b_num == 0 ){
			alert('0명 이하로 감소할 수 없습니다.'); return;
		}
		b_num--;
		document.querySelector('.b_num').innerHTML= b_num;
	}
	pay_money() // 결제금액 계산
}

/* 인원 유효성 검사 (최대 8명 )*/
function p_max(){
	if(a_num+t_num+b_num >= 8){
		alert('총 8명까지 예매할 수 있습니다!').
		return;
	}
}


/*-------------------------좌석 선택란------------------*/
select_screen();
function select_screen(){
	let html = '';
	for(index = 97; index<=105 ; index++ ){ //아스키코트 a~i //fromCharCode(number) 숫자를 아스키코드로
		html += `<div class="seat_text"> 
					<span class="col"> ${String.fromCharCode(index)} </span>`;	
		
			html += `<div class="left">`
		for(let i =1; i <= 4 ; i++){
			html += `<span class="seat" id="s${index}_${i}"
					 onclick="click_seat(${index}, ${i})"> 
					 ${String.fromCharCode(index)+i} </span>`
		}
			html += `</div>`
		
			html += `<div class="center">`
		for(let j =5; j <= 12 ; j++){
			html += `<span class="seat"  id="s${index}_${j}"
					 onclick="click_seat(${index}, ${j})"> 
					 ${String.fromCharCode(index)+j} </span>`
		}
			html += `</div>`
		
			html += `<div class="right">`
		for(let k =13; k <= 16 ; k++){
			html += `<span class="seat"  id="s${index}_${k}"
					 onclick="click_seat(${index}, ${k})"> 
					 ${String.fromCharCode(index)+k} </span>`
		}
			html += `</div>`
		
		
		html += `</div> </div>`
		
	}
	document.querySelector('.select_seat').innerHTML = html;
	r_seat(); //예약된 좌석 출력

	
}

//----------------------- 예약된 좌석 출력 -----------------

function r_seat(){ 
	$.ajax({
			url:"/movie/ticket",
			method:"put",
			data: {"pno":pno},
			async:"false",
			success:(r)=>{
				
				//예약된 좌석은 비활성화 
				r.forEach((o,i)=>{ 
					document.querySelector(`#s${ o.seatnum.substr(0,1).charCodeAt(0)}_${ o.seatnum.substr(1,2)}`).disabled = true;
					document.querySelector(`#s${ o.seatnum.substr(0,1).charCodeAt(0)}_${ o.seatnum.substr(1,2)}`).style.backgroundColor = "#363939";
					document.querySelector(`#s${ o.seatnum.substr(0,1).charCodeAt(0)}_${ o.seatnum.substr(1,2)}`).style.cursor = "default";
					document.querySelector(`#s${ o.seatnum.substr(0,1).charCodeAt(0)}_${ o.seatnum.substr(1,2)}`).onclick = null;
					document.querySelector(`#s${ o.seatnum.substr(0,1).charCodeAt(0)}_${ o.seatnum.substr(1,2)}`).innerHTML = "X";
				})
			}
	})
	
}


//----------------------- 좌석 클릭시  이벤트 ------------------
function click_seat(eng,num){
		
	//좌석배열넣기
	let seat = String.fromCharCode(eng)+num
	
	//똑같은 좌석을 두번 클릭시 삭제
	if(s_seat.includes(seat)){
		s_seat.splice(s_seat.indexOf(seat),1)
		document.querySelector(`#s${eng}_${num}`).style.backgroundColor = "#e8e8e8";
		document.querySelector(`#s${eng}_${num}`).style.color = "black";
		seat_num--; return;
	}else{
		s_seat.push(seat);
	}
	
	//현재 인원과 선택한 좌석 유효성검사
	if(a_num+t_num+b_num <= seat_num ){
		alert('인원을 다시 확인해주세요!')
		return ;
	}
	seat_num++;
	
	//선택된 좌석 빨갛게
	document.querySelector(`#s${eng}_${num}`).style.backgroundColor = "red";
	document.querySelector(`#s${eng}_${num}`).style.color = "white";
}

// -------------------------- 결제금액 계산 --------------------
function pay_money(){
	document.querySelector('.pay_money').innerHTML =  (pprice* (a_num+t_num+b_num)).toLocaleString()	
}

//----------------------- 결제버튼 --------------------
function pay_modal(){
	if(a_num+t_num+b_num == 0 || seat_num==0  || a_num+t_num+b_num != seat_num    ){
		alert('인원과 좌석을 다시한번 확인해주세요'); return;
	}	
	
	let html = '';

	if( a_num>0 ){ 
		html+= `성인 ${a_num} 명`
		if( t_num >0 || b_num >0){
			html+=`, `
		}
	}
	
	if( t_num>0 ){
		html+= ` 청소년 ${t_num} 명`
		if(  b_num >0){
			html+=`, `
		}
	}
	if( b_num>0 ){
		html+= ` 시니어 ${b_num} 명 `
	}
	
	let s_html='';
	
	s_seat.forEach((o,i)=>{
		s_html+= `${o} `
		if(i<s_seat.length-1 ){
			s_html+=`,`
		}
			
		
	})
	
	document.querySelector('.pay_people2').innerHTML= html;
	document.querySelector('.pay_money2').innerHTML=  (pprice* (a_num+t_num+b_num)).toLocaleString()+' 원';
	document.querySelector('.s_seat2').innerHTML='좌석 '+ s_html;
	document.querySelector('.modal_wrap').style.display = 'flex';

}

function closeModal(){
	document.querySelector('.modal_wrap').style.display = 'none';
} 

/*------------------ 모달창 결제버튼 (포트윈 api) -----------------------*/
   var IMP = window.IMP; 
  IMP.init("imp45847583"); // 예: imp00000000a

function requestPay() {
    IMP.request_pay({
      pg: 'html5_inicis',
      pay_method: "card",
      merchant_uid: "ORD20180131-0000011",   // 주문번호
      name: title,
      amount: pprice* (a_num+t_num+b_num),                         // 숫자 타입
      
      
      buyer_email: "gildong@gmail.com",
      buyer_name: "홍길동",
      buyer_tel: "010-4242-4242",
      buyer_addr: "서울특별시 강남구 신사동",
      buyer_postcode: "01181"
    }, function (rsp) { // callback
      if (rsp.success) {
        
      } else {
       
        
        let seatString = ''
        s_seat.forEach( (s,i) =>{
			console.log("좌색: "+s + " 인덱스: "+ i)
			seatString += s ;
			if( i == ( s_seat.length-1) ){
			} else{
				seatString += "," ; 
			} 
			
		})
        
        $.ajax({
			url:"/movie/ticket",
			method:"post",
			data: { "pno" : pno , "s_seat" : seatString },
			async:"false",
			success:(r)=>{
				if(r=='true'){
				 alert('예약되었습니다! ') 
				 location.href=`/movie/member/mypage.jsp?page=0`
				 }
			}//success	
		})//$.ajax e
		}//else e
		})// rsp
	
        
        
} //requestPay() 
 

