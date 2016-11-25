<%@ attribute name="campo" required="true" %>
<%@ attribute name="tamanho" required="true" %>

<script>
$(document).ready(function(){
	var campo = $("#${campo}");
    var digitos = "1234567890";
    campo.val("0.00");
    campo.keyup(function(event){
    	var test = campo.val().length;
    	var tamanho = "${tamanho}";
    	if(test > tamanho){
        	campo.val("0.00");
        }
    });
    campo.keypress(function(event){
    	var valor = Number(campo.val());
    	var keycode = event.which;
        
        if(keycode == 8){
        	var novo = Number("0.00").toFixed(2);
        	campo.val(novo);
            event.preventDefault();
        }
        else{
        	var key = String.fromCharCode(keycode);
            var conv = "0.0"+key
            var decimal = Number(conv);
        	if(digitos.search(key) >= 0){
            	valor = valor*10;
                valor = valor + decimal;
                campo.val(valor.toFixed(2));
                event.preventDefault();
        	}
        	else{
        		event.preventDefault();
        	}
        }
    });
});
</script>