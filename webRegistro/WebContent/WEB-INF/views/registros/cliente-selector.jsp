<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<select name="cliente" class="form-control">
	<c:forEach var="cli" items="${listaClientes}">
		<option id="${cli.id}" value="${cli.id}" >${cli.nome}</option>
	</c:forEach>
</select>