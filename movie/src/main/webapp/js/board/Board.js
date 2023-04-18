
console.log("board.js확인")
board_print();
function board_print(){
	
	$.ajax({
		url : "/movie/Board/comment",
		method : "get" ,
		data : {"type" : 1},
		success : (r)=>{
			console.log("board_print확인 : " + r)
			console.log(JSON.parse(JSON.stringify( r)))
			
			let html = ``;
			
			r.forEach((o,i)=>{
				
				
				html += `	
							<table class="print_table">
							<tr>
								<td class="img${i}" width="20%"></td>
								<td width="30%">${o.bcontent}</td>
								<td width="10%">${o.bscore} / 10점</td>
								<td width="10%"> ${o.mid} </td>
								<td width="10%">${o.bdate}</td>
								<td width="10%"><button type="button" onclick="reply_view(${o.bno})" class="re_btn"> 더보기 </button></td>
							</tr>
							</table>
							<div class="replyviewbox${o.bno} css_replybox">
							</div>
							`;
				
			
			img_find(o.bmovie , i)
			})
			document.querySelector('.print_list').innerHTML = html;
		}
		})
		
	
}


function img_find(bmovie , i){
	
	$.ajax({
		url : "/movie/SearchServlet",
		method : "get" ,
		data : {"bmovie" : bmovie,
				"type" : 4 },
		success : (r)=>{
			console.log("board_print확인 : " + r)
			
			let html = `<img  src="${r}" width="25%">`;
			
			document.querySelector(`.img${i}`).innerHTML = html;
		}
	})
	
}



function reply_view(bno){
	console.log(bno)
	let html = `<table>
				`;
	$.ajax({
		url : "/movie/Board/comment",
		method : "get" ,
		data : {"type" : 2,
			"bno" : bno},
		success : (r)=>{
			console.log('reply_view리턴값 : ' + r)
			console.log(JSON.parse(JSON.stringify( r)))
			
			
			r.forEach((o,i)=>{
				html += `
					
					<tr class="print_table2 css_reply_view">
						<td width="21%" class="icon"><i class="fa fa-arrow-right"></i></td>
						<td width="40%">${o.rcontent}</td>
						<td width="10%">${o.mid}</td>
						<td width="10%">${o.rdate}</td>
						<td width="10%></td>
						<td width="10%"></td>
					</tr>
					`
			})
			html += `</table>`
			document.querySelector(`.replyviewbox${bno}`).innerHTML = html;
			document.getElementsByClassName("css_replybox").style.backgroundColor = "#00456B";
			}
			
	})
		
}












