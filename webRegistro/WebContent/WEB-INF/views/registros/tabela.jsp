<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="table table-responsive" summary="Tabela de clientes registrados e seus softwares no sistema">
  				<caption>Sistemas registrados</caption>
  				<tbody>
    				<tr>
      					<th scope="col">Cliente</th>
            	        <th scope="col">Software</th>
      					<th scope="col">Validade</th>
      					<th scope="col">Valor</th>
      					<th scope="col">Plano</th>
    				</tr>
            	    <c:forEach var="registro" items="${listaRegistros}">
            	     <tr>
            	      <td>${registro.cliente.nome}</td>
            	      <td>${registro.software.nome}</td>
            	      <td>${registro.fmtValidade}</td>
            	      <td>${registro.fmtValorComUnidade}</td>
            	      <td>${registro.fmtPlano}</td>
            	      <td>
            	         <a href="atualizarRegistro?id=${registro.id}">Atualizar</a>
            	         <br>
            	         <a href="#" data-toggle="modal" data-target="#myModal${registro.id}">Remover</a>
            	         <div id="myModal${registro.id}" class="modal fade" role="dialog">
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
  						 				<a href="removerRegistro?id=${registro.id}"><button class="btn btn-info">Sim</button></a>
  						 			</div>
  						 		</div>
  						 	</div>
  						 </div>
            	      </td>
            	     </tr>
            	    </c:forEach>
  				</tbody>
			</table>