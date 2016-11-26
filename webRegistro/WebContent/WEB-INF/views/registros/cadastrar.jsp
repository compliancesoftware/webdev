<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<!DOCTYPE html>
<html lang="en">
<head>

<mtag:meta/>

<mtag:favicon/>

<title>WebRegistro - Registrar Software</title>

<mtag:cssJsHead/>

</head>
<body id="pagina" background="<c:url value="resources/images/main_backimage_large.png" />">
<mtag:navbar alertas="${alertas}" logado="${logged.id}" permissao="${logged.permissao}" index="false"/>
<!-- body -->
<div id="corpo" class="container-fluid nav-padded">
  <div class="row">
	<div class="col-md-6 col-md-offset-3">
    	<div class="row">
        	<hr>
            <div id="form" class="panel panel-default nav-padded">
                <mtag:mensagem msg="${mensagem}"/>
            	<div class="panel-heading text-center">
                	<h3>Registro de Software para Cliente</h3>
                </div>
            	<div class="panel-body">
                	<div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-8">
                        	<form class="form-group" method="post" action="cadastraRegistro">
                        		<br>
                        		<input id="pesquisaClientes" type="text" placeholder="digite o nome de um cliente"  class="form-control" onkeyup="pesquisa()">
                        		Cliente:
                        		<div id="cliente-selector">
                        			<c:import url="cliente-selector.jsp"/>
                        		</div>
                        		<br>
                        		Software:
                        		<select id="software" name="software"  class="form-control" onchange="atualizaValor()">
                        			<c:forEach var="soft" items="${listaSoftwares}">
                        				<option id="${soft.id}" value="${soft.nome}" >${soft.nome}</option>
                        			</c:forEach>
                        		</select>
                        		<c:forEach var="soft" items="${listaSoftwares}">
                        			<input type="hidden" id="${soft.nome}" value="${soft.valor}">
                        		</c:forEach>
                        		<br>
                        		Valor
                        		<input id="valor" type="text" name="valor"  class="form-control" readonly>
                        		<br>
                        		Desconto
                        		<select id="desconto" name="desconto" class="form-control" onchange="atualizaDesconto()">
                        			<c:forEach var="desc" items="${listaPercentual}">
                        				<option id="${desc}" value="${desc}" >${desc}%</option>
                        			</c:forEach>
                        		</select>
                            	<br>
                            	<button class="btn-block btn-white form-control info" type="submit">Registrar Novo Software</button>
                        	</form>
                        </div>
                        <div class="col-md-2"></div>
                     </div>
                </div>
            </div>
			<mtag:footerHtml/>
        </div>
    </div>
  </div>
</div>
<!-- /body -->
<mtag:cssJsFoot/>
<script type="text/javascript">
function pesquisa(){
	var pesquisa = $("#pesquisaClientes").val();
	$.post("pesquisarClienteParaRegistro",
			{'pesquisa':pesquisa},
			function (resposta){
				$("#cliente-selector").html(resposta);
			});
}

var id = $("#software").val();
var pValor = $("#"+id).val();
$("#valor").val(pValor);

function atualizaValor(){
	var id_escolhido = $("#software").val();
	var valor = Number($("#"+id_escolhido).val()).toFixed(2);
	var desconto = Number($("#desconto").val()).toFixed(2);
	desconto = desconto/100;
	valor = valor - (valor * desconto);
	var strvalor = ""+Number(valor).toFixed(2);
	$("#valor").val(strvalor);
}

function atualizaDesconto(){
	var desconto = Number($("#desconto").val()).toFixed(2);
	desconto = desconto/100;
	var id_escolhido = $("#software").val();
	var valor = Number($("#"+id_escolhido).val()).toFixed(2);
	valor = valor - (valor * desconto);
	var strvalor = ""+Number(valor).toFixed(2);
	$("#valor").val(strvalor);
}
</script>
</body>
</html>
