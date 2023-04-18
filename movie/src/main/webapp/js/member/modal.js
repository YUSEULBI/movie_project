console.log('modal.js 실행')

function onpenModal( ){
	document.querySelector('.modal_wrap_deletemember').style.display ='flex';
}
function closeModal(){
	document.querySelector('.modal_wrap_deletemember').style.display ='none';
	document.querySelector('.deletecheck').value = '';
}

function onpenReCancelModal(pno){
	document.querySelector('.modal_wrap_recancel').style.display ='flex';
	document.querySelector('.cancelpno').value = pno;
}
function closeReCancelModal(){
	document.querySelector('.modal_wrap_recancel').style.display ='none';
	document.querySelector('.cancelpno').value = '';
}