let openCloseNum = 0;

function openNav() {
  document.getElementById("mySidepanel").style.transform = "translateX(0%)";
  document.getElementById("localInfoTest").classList.remove('bi-chevron-compact-right');
  document.getElementById("localInfoTest").classList.add('bi-chevron-compact-left');
  document.querySelector("#localInfoTest > path").setAttribute('d',  "M9.224 1.553a.5.5 0 0 1 .223.67L6.56 8l2.888 5.776a.5.5 0 1 1-.894.448l-3-6a.5.5 0 0 1 0-.448l3-6a.5.5 0 0 1 .67-.223z")
}

function closeNav() {
  document.getElementById("mySidepanel").style.transform = "translateX(-100%)";
  document.getElementById("localInfoTest").classList.remove('bi-chevron-compact-left');
  document.getElementById("localInfoTest").classList.add('bi-chevron-compact-right');
  document.querySelector("#localInfoTest > path").setAttribute('d', "M6.776 1.553a.5.5 0 0 1 .671.223l3 6a.5.5 0 0 1 0 .448l-3 6a.5.5 0 1 1-.894-.448L9.44 8 6.553 2.224a.5.5 0 0 1 .223-.671z")
}

function sidePannelControll(){
	if(openCloseNum == 0){
		openCloseNum = 1;
		openNav();
	}else{
		openCloseNum = 0;
		closeNav();
	}
}