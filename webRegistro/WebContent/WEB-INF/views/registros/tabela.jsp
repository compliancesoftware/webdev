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
            	         <a href="#" data-toggle="modal" data-target="#myModal${cliente.id}">Remover</a>
            	         <div id="myModal${cliente.id}" class="modal fade" role="dialog">
  						 	<div class="modal-dialog">
  						 		<div class="modal-content">
  						 			<div class="modal-header">
  						 				<button type="button" class="close" data-dismiss="modal">&times;</button>
  						 				<h4 class="modal-title">Tem certeza?</h4>
  						 			</div>
  						 			<div class="modal-body">
  						 				<p>Deseja realmente remover este registro?.</p>
  						 			</div>
  						 			<div class="modal-footer">
  						 				<button type="button" class="btn btn-default" data-dismiss="modal" onclick="remove(${cliente.id})">Sim</button>
  						 			</div>
  						 		</div>
  						 	</div>
  						 </div>
            	      </td>
            	     </tr>
            	    </c:forEach>
  				</tbody>
			</table>