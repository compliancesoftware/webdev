<%@ attribute name="src" required="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<img id="image" class="center-block" src="<c:url value="/resources/images/login-icon.png" />" alt="Foto do perfil">

<script>
//JavaScript Document
	var source = "${src}";
	if(source != null && source != ""){
		var output = document.getElementById('image');
		output.src = source;
	}
</script>