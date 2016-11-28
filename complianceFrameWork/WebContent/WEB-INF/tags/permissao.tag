<%@ attribute name="value" required="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${value eq 'Administrador'}">
<select name="permissao" class="form-control">
    <option value = "Administrador">Usuário Administrador</option>
    <option value = "Comum">Usuário Comum</option>
    <option value = "Desativado">Usuário Desativado</option>
</select>
</c:if>
<c:if test="${value eq 'Comum'}">
<select name="permissao" class="form-control">
    <option value = "Comum">Usuário Comum</option>
    <option value = "Administrador">Usuário Administrador</option>
    <option value = "Desativado">Usuário Desativado</option>
</select>
</c:if>
<c:if test="${value eq 'Desativado'}">
<select name="permissao" class="form-control">
    <option value = "Desativado">Usuário Desativado</option>
    <option value = "Administrador">Usuário Administrador</option>
    <option value = "Comum">Usuário Comum</option>
</select>
</c:if>