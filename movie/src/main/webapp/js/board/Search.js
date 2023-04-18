console.log('search 확인')


if(memberInfo==null){
	alert('로그인해주세요.')
	location.href="/movie/member/login.jsp"
}
console.log('회원아이디 확인' + memberInfo.mid)
console.log('회원번호 확인' + memberInfo.mno)



addEventListener("keyup" , (e)=>{
	if(e.key.includes('Enter')){
		search_m();
	}
})




Search();

// 영화검색페이지 시작시 박스오피스탑10 / 넷플릭스 영화 순위 불러오기
function Search(){
	$.ajax({
		url : "/movie/SearchServlet",
		method : "get" ,
		data : {"type" : 1},   
		success : (r)=>{
			console.log(r); // 리턴값 확인
			
			let html = '';
			let html2='';
			r.forEach((o,i)=>{
				
				if(i<10){ // 인덱스 0~9까지  =  박스오피스 탑10
					html +=`
						<div class="m_wrap css_m_wrap"  onclick="openModal('${o.link}')">
						<a  href="#">
						<div>
						<img class="img-fluid" src="${o.pimg}"><br/>
						</div>
						<div>
						<span class="m_title">${o.title}</span><br/>
						<span class="m_title">${o.year}</span><br/>
						<span class="m_count">${o.count}</span>
						</div>
						</a>
						</div>
						`
				}
				else{ // 인덱스 10~19까지  =  넷플릭스 탑10
					html2 +=`
						<div class="m_wrap css_m_wrap" onclick="openModal('${o.link}')">
						<a href="#">
						<div>
						<img class="img-fluid" src="${o.pimg}"><br/>
						</div>
						<div>
						<span class="m_title">${o.title}</span><br/>
						<span class="m_title">${o.year}</span><br/>
						</div>
						</a>
						</div>
						`
				}	
			})
			document.querySelector('.m_p').innerHTML = html;
			document.querySelector('.m_p2').innerHTML = html2;
			}
		
	})
}

// 검색한 영화 불러오기
function search_m(){
	
	// 검색한 단어 가져오기
	let search = document.querySelector('.search_m').value;
	
	
	$.ajax({
		url : "/movie/SearchServlet",
		method : "get" ,
		data : {"type" : 2 ,
				"search" : search},
		success : (r)=>{
			console.log(r ); // 리턴값 확인
			let list = JSON.parse(JSON.stringify( r)); // 형변환
			console.log(list) // 형변환 확인
			
			let html = `<div class="searchtitle">
					<h2> "${search}" 검색 결과 </h2>
				</div>
				<div class = "search_m css_m_p">`;
			
			list.forEach((o , i) =>{
				
				html += `
						<div class="m_wrap css_m_wrap" onclick="openModal('${o.link}')">
						<a href="#">
						<div>
						<img class="img-fluid" src="${o.pimg}"><br/>
						</div>
						<div>
						<span class="m_title">${o.title}</span><br/>
						<span class="m_title">${o.year}</span><br/>
						</div>
						</a>
						</div>
						`
			})
			html += `</div>`
			document.querySelector('.searchresult').innerHTML = html;
			}
	})
	
}

// 영화 정보 저장
let bmovie = '';
// 모달 오픈/클로즈
function openModal(e){ // e = link주소
	console.log(e);	// 들어왔는지 확인
	// 영화정보 저장
	bmovie = e;
	document.querySelector('.modal_wrap').style.display = 'flex';
	let movie = bmovie.split("/")[5];
			
	console.log(movie);
	movie.submit;
	
	modal_select(e); // 모달 열리면 modal_select()함수에 e값을 보내주고 실행
	bprint();
	
	
}
function closeModal(){
	document.querySelector('.modal_wrap').style.display = 'none';
	// 모달 닫을시 내용 초기화
	document.querySelector('.modal_title').innerHTML ='';
	document.querySelector('.modal_content').innerHTML ='';
	bmovie = '';
}


// 선택한 영화 상세보기
function modal_select(select){
	console.log('modal : ' + select); // 넘겨받은 인수 확인
	
	$.ajax({
		url : "/movie/SearchServlet",
		method : "get" ,
		data : {"type" : 3 ,
				"select" : select},
		success : (r)=>{
			console.log('select') // 통신확인
			console.log(r)		 // 리턴값 확인
			let html = ``;
			
			r.forEach((o,i)=>{
				html +=`
						<img src=${o.pimg}><br/>
						<span> ${o.title}</span><br/>
						<span> ${o.year}</span><br/>
						<span> ${o.count}</span><br/>
						`
				
				
				document.querySelector('.modal_title').innerHTML = html;
				
				html = '';
				
				html+=`
						<div>
							<div>제목 : ${o.infotitle}</div>
							<div>제작연도 : ${o.infoyear}</div>
							<div>국가 : ${o.infocountry}</div>
							<div>장르 : ${o.infogenre}</div>
							<div>상영시간 : ${o.infotime}</div>
							<div>연령등급 : ${o.infoold}</div>
							<div>내용 : ${o.infocontent}</div>
						</div>
				`;
				document.querySelector('.modal_content').innerHTML = html;
			})
		}
	})
}



// 작성
function bwrite() {
	
	let bscore = document.getElementById("bscore").value;
	let bcontent = document.getElementById("bcontent").value;
	let reg = /^[0-9]*$/
	if(!reg.test(bscore) || bscore > 10){
		alert("10이하의 정수만 입력가능합니다.")
		location.href="/movie/board/Search.jsp"
		return false;
	}
	let info = {
		
		bmovie : bmovie ,
		bcontent : bcontent,
		bscore : bscore,
		mno : memberInfo.mno,
		type : 1
	}
	console.log(info)
	
	
	$.ajax({
		url : "/movie/Board/Bwrite",
		method : "post" ,
		data : info,
		success : (r)=>{
			console.log('bwrite리턴값 : ' + r)
			alert('작성 성공')
			
			document.querySelector('.bscore').innerHTML = '';
			document.querySelector('.bcontent').innerHTML = '';
			bprint();
		}
	})
}


let re_bno = 0;
// 출력
function bprint(){
	console.log(bmovie)
	$.ajax({
		url : "/movie/Board/Bwrite",
		method : "get" ,
		data : {"bmovie" : bmovie,
				"type" : 1 },
		success : (r)=>{
			console.log('bprint리턴값 : ' + r)
			let list = JSON.parse(JSON.stringify( r)); // 형변환
			console.log(list)
			
			
			let html = '';
			
			list.forEach((o,i)=>{
				
				html += `<div>
							<div class="review_list">
								<div class="b_mid">ID:${o.mid}</div>
								<div class="b_bscore">${o.bscore}/10점</div>
								<div class="b_bdate">${o.bdate}</div>
							</div>
							<div class="b_bcontent">${o.bcontent}</div>
							<button type="button" onclick="openModal2(${o.bno})" class="css_btn">댓글달기</button>
							<div class="reply${o.bno}"></div>
							
						</div>
						`
			
			reply_print(o.bno);
			})
			document.querySelector('.review_print').innerHTML = html;
			
			
		}
	})
}



function openModal2(bno){ // e = link주소
	document.querySelector('.modal_wrap2').style.display = 'flex';
	console.log("bno : " + bno)
	reply_view(bno)
	
}

function closeModal2(){
	document.querySelector('.modal_wrap2').style.display = 'none';
}

function reply_view(bno){
	$.ajax({
		url : "/movie/Board/Bwrite",
		method : "get" ,
		data : {"type" : 2 , 
				"bno" : bno},
		success : (r)=>{
			console.log('replyview리턴값 : ' + r)
			let list = JSON.parse(JSON.stringify(r));
			console.log(list)
			
			let html = ``;
			
			list.forEach((o,i)=>{
				
				html+=`<div>
							<input type="hidden" class="r_bno" value="${o.bno}">
							<input type="hidden" class="rmovie" value="${o.bmovie}">
							<div class="review_list">
								<div class="b_mid">ID:${o.mid}</div>
								<div class="b_bscore">${o.bscore}/10점</div>
								<div class="b_bdate">${o.bdate}</div>
							</div>
							<div class="b_bcontent">내용 : ${o.bcontent}</div>
						</div>
						<div>
							<textarea rows="10" cols="55" id="rcontent" class="css_textarea"></textarea>
						</div>
						`
						;
				
			})
			document.querySelector('.modal_content2').innerHTML = html;
		}
	})
	
}


function reply_write(){
	let rcontent = document.getElementById("rcontent").value;
	
	let info = {
		bno : document.querySelector('.r_bno').value,
		rmovie : document.querySelector('.rmovie').value,
		rcontent : rcontent,
		mno : memberInfo.mno,
		mid : memberInfo.mid,
		type : 2
	}
	console.log(info)
	
	$.ajax({
		url : "/movie/Board/Bwrite",
		method : "post" ,
		data : info,
		success : (r)=>{
			alert('작성 성공')
			closeModal2();
			bprint();
			
		}
	})
	
}


function reply_print(bno){
	$.ajax({
		url : "/movie/Board/Bwrite",
		method : "get" ,
		data : {"type" : 3,
				"movie" : bmovie},
		success : (r)=>{
			console.log('reply_print리턴값 : ' + r)
			console.log(JSON.parse(JSON.stringify( r)));
			
				
			let html = ``;
			
			r.forEach((o,i)=>{
					
					if(bno == o.bno){
						html += `
						<div class="css_review_list">
						<div class="review_list">
								<div class="find_user">${o.mid}</div>
								<div class="find_rdate">${o.rdate}</div>
						</div>
						<div>${o.rcontent}</div>
						</div>
							`;
					}
			})
			document.querySelector(`.reply${bno}`).innerHTML = html;
			
		}
		
	})
			
}





/*

						
*/
































