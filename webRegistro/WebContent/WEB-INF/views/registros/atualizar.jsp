<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<!DOCTYPE html>
<html lang="en">
<head>

<mtag:meta/>

<mtag:favicon/>

<title>WebRegistro - Atualizar Registro de Software</title>

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
                	<h3>Atualizar Registro de Software para Cliente</h3>
                </div>
            	<div class="panel-body">
                	<div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-8">
                        	<form class="form-group" method="post" action="atualizaRegistro">
                        		<input type="hidden" name="id" value="${registro.id}">
                        		<br>
                        		Cliente:
                        		<input id="pesquisaClientes" type="text" placeholder="Digite o nome de um cliente para facilitar a pesquisa"  class="form-control" onkeyup="pesquisa()">
                        		<div id="cliente-selector">
                        			<c:import url="cliente-selector.jsp"/>
                        		</div>
                        		<br>
                        		Software:
                        		<select id="software" name="software"  class="form-control" onchange="atualizaValor()">
                        			<option id="${regitro.software.id}" value="${registro.software.nome}" >${registro.software.nome}</option>
                        			<c:forEach var="soft" items="${listaSoftwares}">
                        				<option id="${soft.id}" value="${soft.nome}" >${soft.nome}</option>
                        			</c:forEach>
                        		</select>
                        		<br>
                        		Plano:
                        		<select id="plano" name="plano" class="form-control" onchange="atualizaValor()">
                        			<option id="atual" value="${registro.plano}">${registro.fmtPlano}</option>
                        			<option id="mensal" value="1">Mensal</option>
                        			<option id="trimestral" value="3">Trimestral</option>
                        			<option id="semestral" value="6">Semestral</option>
                        			<option id="anual" value="12">Anual</option>
                        		</select>
                        		<c:forEach var="soft" items="${listaSoftwares}">
                        			<input type="hidden" id="${soft.nome}" value="${soft.valor}">
                        		</c:forEach>
                        		<br>
                        		Valor
                        		<input id="valor" type="text" name="valor" class="form-control" readonly>
                        		<br>
                        		Desconto
                        		<select id="desconto" name="desconto" class="form-control" onchange="atualizaValor()">
                        			<c:forEach var="desc" items="${listaPercentual}">
                        				<option id="${desc.valor}" value="${desc.valorAsString}" >${desc.valorAsString}%</option>
                        			</c:forEach>
                        		</select>
                        		<br>
                        		Data de Validade:
                        		<input type="text" id="validade" name="validade" class="form-control" placeholder="Validade" value="${registro.fmtValidade}" readonly required/>
                        		<br>
                        		Observações:
                        		<textarea rows="3" cols="5" name="observacoes" class="form-control" placeholder="Observações">${registro.observacoes}</textarea>
                            	<br>
                            	Ativo:
                            	<select id="ativo" name="ativo" class="form-control">
                            		<c:if test="${registro.ativo eq true}">
                            			<option value="true">Sim</option>
                            			<option value="false">Não</option>
                            		</c:if>
                            		<c:if test="${registro.ativo eq false}">
                            			<option value="false">Não</option>
                            			<option value="true">Sim</option>
                            		</c:if>
                            	</select>
                            	<br>
                            	<button class="btn-block btn-white form-control info" type="submit">Atualizar Registro</button>
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
<mtag:campoData campo="validade"/>
<script type="text/javascript">
function pesquisa(){
	var pesquisa = $("#pesquisaClientes").val();
	$.post("pesquisarClienteParaRegistro",
			{'pesquisa':pesquisa},
			function (resposta){
				$("#cliente-selector").html(resposta);
			});
}

function atualizaValor(){
	var id_escolhido = $("#software").val();
	var valor = Number($("#"+id_escolhido).val()).toFixed(2);
	var plano = $("#plano").val();
	valor = valor * plano;
	var desconto = Number($("#desconto").val()).toFixed(2);
	desconto = desconto/100;
	valor = valor - (valor * desconto);
	var strvalor = ""+Number(valor).toFixed(2);
	$("#valor").val(strvalor);
}

$(document).ready(function(){
	atualizaValor();
});
</script>
</body>
</html>
