<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="text-center table table-responsive" summary="Tabela de clientes registrados e seus softwares no sistema">
  				<caption>Sistemas registrados</caption>
  				<tbody>
    				<tr>
      					<th scope="col">Cliente</th>
            	        <th scope="col">Software</th>
      					<th scope="col">Validade</th>
      					<th scope="col">E-mail</th>
      					<th scope="col">Responsável</th>
            	        <th scope="col">Operações</th>
    				</tr>
            	    <c:forEach var="cliente" items="${listaClientes}">
            	     <tr>
            	      <td>${cliente.nome}</td>
            	      <td>${cliente.fmtCodigo}</td>
            	      <td>${cliente.fmtContato}</td>
            	      <td><a href="enviaEmail?email='${cliente.email}'">${cliente.email}</a></td>
            	      <td>${cliente.responsavel}</td>
            	      <td>
            	         <a href="visualizarRegistros?id=${cliente.id}">Visualizar registros</a>
            	         <br>
            	         <a href="atualizarCliente?id=${cliente.id}">Atualizar</a>
            	         <br>
            	         <a href="removerCliente?id=${cliente.id}">Remover</a>
            	      </td>
            	     </tr>
            	    </c:forEach>
  				</tbody>
			</table>