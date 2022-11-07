async function gugunList(sidoCode){
	let url = '/maps/gugun/' + sidoCode;

	let fetchOptions = {
		method: 'GET',
	}
	
	try{
		let response = await fetch( url);
		let data = await response.json();
		
		makeGugunListHtml( data );	
	}catch( error ){
		console.log(error);
	}
}

function makeGugunListHtml(list){
	let listHtml = ``;
	
	list.forEach( el => {
		let gugun = el;
		
		listHtml +=
			'<option value=' + gugun.code + '>' + gugun.name + '</option>';
	});
	
	document.querySelector("#gugun-select").innerHTML = listHtml;
}

//GET
async function dongList(gugunCode){
	let url = '/maps/dong/' + gugunCode;

	let fetchOptions = {
		method: 'GET',
	}
	
	let response = await fetch( url);
	
	if(response.ok){
		let data = await response.json();
		makeDongListHtml( data );	
	} else{
		console.log(error);		
	}
	
}

function makeDongListHtml(list){
	let listHtml = ``;
	
	list.forEach( el => {
		let dong = el;
		
		listHtml +=
			'<option value=' + dong.name + '>' + dong.name + '</option>';
	});
	
	document.querySelector("#dong-select").innerHTML = listHtml;
}
