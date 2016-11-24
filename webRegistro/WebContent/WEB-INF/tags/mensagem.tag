<%@ attribute name="msg" required="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:if test="${not empty msg}">
 <c:if test="${fn:contains(msg, '>Logout')}">
  <div id="alert-closeable" class="alert alert-info">
   <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
   ${msg}
  </div>
 </c:if>
 <c:if test="${fn:contains(msg, '>OK')}">
  <div id="alert-closeable" class="alert alert-success">
   <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
   ${msg}
  </div>
 </c:if>
 <c:if test="${fn:contains(msg, '>Erro')}">
  <div id="alert-closeable" class="alert alert-danger">
   <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
   ${msg}
  </div>
 </c:if>
 <c:if test="${fn:contains(msg, '>Info')}">
  <div id="alert-closeable" class="alert alert-info">
   <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
   ${msg}
  </div>
 </c:if>
</c:if>