<%@ attribute name="alertas" required="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<li>
 <a href="alertas" data-toggle="tooltip" title="Alertas do sistema">
  <span class="glyphicon glyphicon-alert white"></span>
  <c:if test="${alertas > 0}">
   <span class="badge">${alertas}</span>
  </c:if>
 </a>
</li>