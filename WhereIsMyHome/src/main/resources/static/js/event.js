
let query = window.location.search;
let param = new URLSearchParams(query);
let eventKey = param.get('eventKey');

document.querySelector("#eventBtn").onclick = function(){
	boardAttend(eventKey);		
}


async function boardAttend(eventKey){
	let url = '/WhereIsMyHome/board/boardAttend';
	let urlParams = '?eventKey=' + eventKey;
let fetchOptions = {
			method: 'GET',
	}
	
	try {
		console.log("html-boardAttend")
		let response = await fetch( url + urlParams, fetchOptions );
        let data = await response.json(); //==Json.parse();
	console.log(data)
        if (data.result == "success") {
        	//alert('이벤트 참여 완료')
	    	alertify.alert('','이벤트 참여 완료');
        } else if (data.result == "fail") {
      	  	//alert('이미 참여한 이벤트입니다')
      	  	alertify.alert('','이미 참여한 이벤트입니다');

        }
    }catch( error ) {
		console.log('이벤트 참여 과정에서 문제가 생겼습니다.');

	}
}