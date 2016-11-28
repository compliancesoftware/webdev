<%@ attribute name="id" required="false" %>
<%@ attribute name="src" required="true" %>
<%@ attribute name="alt" required="true" %>
<%@ attribute name="classe" required="false" %>
<%@ attribute name="name" required="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<img id="${id}" name="${name}" class="${classe}" src="<c:url value="/resources/images/${src}" />" alt="${alt}">