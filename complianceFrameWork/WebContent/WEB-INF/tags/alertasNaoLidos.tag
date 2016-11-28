<%@ attribute name="alertas" required="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${alertas > 0}">
 <div id="alert-closeable" class="alert alert-info">
   <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
   <a href="alertas">Há alertas não lidos no sistema.</a>
 </div>
</c:if>