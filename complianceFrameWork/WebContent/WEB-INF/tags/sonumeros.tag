<%@ attribute name="campo" required="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
$('#${campo}').blur(function(){
	sonumeros(this);
});

function sonumeros(campo){
	var valor = campo.value;
	campo.value = (valor.replace(/\D/g,''));
};
</script>