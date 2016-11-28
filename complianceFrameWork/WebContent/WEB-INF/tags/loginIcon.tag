<%@ attribute name="src" required="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<img id="icon" class="login-img img-circle" src="<c:url value="/resources/images/login-icon.png" />" alt="botão de configurações de login">

<script>
//JavaScript Document
	var source = "${src}";
	if(source != null){
		var output = document.getElementById('icon');
		output.src = source;
	}
</script>