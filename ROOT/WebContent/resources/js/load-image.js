// JavaScript Document
var file;

function ler(arquivo, id){
		var reader = new FileReader();
	
    	reader.onload = function(){
			var dataURL = reader.result;
			var output = document.getElementById(id);
			output.src = dataURL;
			};
		reader.readAsDataURL(arquivo);
	}
	
function handleFileSelect(evt) {
	
	file = evt.target.files[0]; // Lista os objetos selecionados e pega o primeiro.
	
	if(file.size > 1000000){
		alert('O tamanho desta imagem é superior a 1Mb');
		return;
	}
	
	if (!file.type.match('image.*')){
        return;
    }
	
	ler(file,'image');
  }

  document.getElementById('foto').addEventListener('change', handleFileSelect, false);