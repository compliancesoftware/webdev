<%@ attribute name="campo" required="true" %>

<script type="text/javascript">
$(document).ready(function(){
	$("#${campo}").keydown(function(){
		formata(this);
	});

	function formata(campo){
		var valor = campo.value;
		valor = valor.replace(/\D/g,'');
		if(valor.match("^0")){
			valor = "";
		}
		else{
			var len = valor.length;
			
			if(len == 1){
				valor = "0.0"+valor;
			}
			if(len == 2){
				valor = "0."+valor;
			}
			if(len > 2){
				var offset = len - 2;
				var antedecimal = valor.substring(0,offset);
				var posdecimal = valor.substring(offset,len);
				valor = antedecimal+"."+posdecimal;
			}
			if(len > 6){
				var off = len - 6;
				valor = valor.substring(off,len);
			}
		}
		
		campo.value = (valor);
	};
});
</script>