var keys = {
	112: "#/compromissos/novo",
	113: "#/tipos/novo",
	114: "#/categorias/novo",
	115: "#/contatos/novo",
	118: "#/usuarios/novo",
};
document.onkeydown = function(event) {
	var keyCode = event.keyCode;
	if(keys[keyCode])event.preventDefault();
};
document.onkeyup = function(event) {
	var keyCode = event.keyCode;
	if(!keys[keyCode])return null;
	var url = window.location.href,
	pos = url.indexOf("//"),
	ini = url.substring(0,pos+2);
	url = url.substring(pos+2,url.length);
	url = ini + url.substring(0,url.indexOf("/")+1) + keys[keyCode];
	window.location.href = url;
	var intervalCode = setInterval(function(){
    	var inputs = document.getElementsByTagName('input');
    	if(inputs.length && inputs[0].getAttribute("type")=="text"){
    		inputs[0].focus();
    		clearInterval(intervalCode);
    	}
    },100);
};