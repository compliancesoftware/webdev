<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="table table-responsive" summary="Tabela de softwares disponíveis no sistema">
  				<caption>Softwares Disponíveis</caption>
  				<tbody>
    				<tr>
      					<th scope="col">Nome</th>
            	        <th scope="col">Valor</th>
    				</tr>
            	    <c:forEach var="software" items="${listaSoftwares}">
            	     <tr>
            	      <td>${software.nome}</td>
            	      <td>${software.fmtValorComUnidade}</td>
            	      <td>
            	         <a href="#" data-toggle="modal" data-target="#myModal${software.id}">Remover</a>
            	         <div id="myModal${software.id}" class="modal fade" role="dialog">
  						 	<div class="modal-dialog">
  						 		<div class="modal-content">
  						 			<div class="modal-header">
  						 				<button type="button" class="close" data-dismiss="modal">&times;</button>
  						 				<h4 class="modal-title">Tem certeza?</h4>
  						 			</div>
  						 			<div class="modal-body">
  						 				<p>Deseja realmente remover este software?.</p>
  						 			</div>
  						 			<div class="modal-footer">
  						 				<a href="removerSoftware?id=${software.id}"><button class="btn btn-info">Sim</button></a>
  						 			</div>
  						 		</div>
  						 	</div>
  						 </div>
            	      </td>
            	     </tr>
            	    </c:forEach>
  				</tbody>
			</table>