<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="text-center table table-responsive" summary="Tabela de clientes registrados e seus softwares no sistema">
  				<caption>Sistemas registrados</caption>
  				<tbody>
    				<tr>
      					<th scope="col">Cliente</th>
            	        <th scope="col">Software</th>
      					<th scope="col">Validade</th>
      					<th scope="col">Valor</th>
    				</tr>
            	    <c:forEach var="registro" items="${listaRegistros}">
            	     <tr>
            	      <td>${registro.cliente.nome}</td>
            	      <td>${registro.software.nome}</td>
            	      <td>${registro.fmtValidade}</td>
            	      <td>${registro.fmtValor}</td>
            	      <td>
            	         <a href="atualizarRegistro?id=${cliente.id}">Atualizar</a>
            	         <br>
            	         <a href="removerRegistro?id=${cliente.id}">Remover</a>
            	      </td>
            	     </tr>
            	    </c:forEach>
  				</tbody>
			</table>