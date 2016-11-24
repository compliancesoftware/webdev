<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<!DOCTYPE html>
<html lang="en">
<head>

<mtag:meta/>

<mtag:favicon/>

<title>WebRegistro - Alertas</title>

<mtag:cssJsHead/>

</head>                      
<body id="pagina" background="<c:url value="resources/images/main_backimage_large.png" />">
<mtag:navbar alertas="${alertas}" logado="${logged.id}" permissao="${logged.permissao}" index="false"/>
<!-- body -->
<div id="corpo" class="container-fluid nav-padded">
  <div class="row">
  	<div class="col-md-1"></div>
    <div class="col-md-10">
        <mtag:mensagem msg="${mensagem}"/>
    	<table class="text-center table table-responsive" summary="Alertas do sistema.">
  			<caption>Registro de alertas do sistema</caption>
  			<tbody>
    			<tr>
      				<th scope="col">Mensagem</th>
                    <th scope="col">Data</th>
      				<th scope="col">Modificar Status</th>
    			</tr>
    			<c:forEach var="alerta" items="${listaAlertas}">
                 <tr>
                  <td>${alerta.mensagem}</td>
                  <td>${alerta.fmtData}</td>
                  <c:if test="${alerta.visto eq true}">
                   <td><a href="atualizaVisibilidadeAlerta?id=${alerta.id}">Marcar como não lido</a></td>
                  </c:if>
                  <c:if test="${alerta.visto eq false}">
                   <td><a href="atualizaVisibilidadeAlerta?id=${alerta.id}">Marcar como lido</a></td>
                  </c:if>
                 </tr>
                </c:forEach>
  			</tbody>
		</table>
    </div>
    <div class="col-md-1"></div>
  </div>
  <!-- filtro -->
  <hr>
  <div class="row" id="filtro">
  <div class="col-md-4"></div>
  <div class="col-md-4">
   <div class="panel">
    	<div class="panel-heading text-center">
        	<h4>Filtrar alertas</h4>
        </div>
        <div class="panel-body">
        	<form class="form-group" method="post" action="alertasFiltro">
    			<input name="inicio" type="date" class="form-control">
        		<input name="fim" type="date" class="form-control">
        		<select name="lido" class="form-control">
        			<option value = "false">Não</option>
            		<option value = "true">Sim</option>
        		</select>
        		<br>
        		<button class="btn-block btn-white form-control info" type="submit">Aplicar filtro</button>
    		</form>
        </div>
    </div>
  </div>
  <div class="col-md-4"></div>
  </div>
  <!-- /filtro -->
  <mtag:footerHtml/>
</div>
<!-- /body -->
<mtag:cssJsFoot/>
</body>
</html>
