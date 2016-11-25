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
            	<div class="panel-body"><!-- TODO testar isso tudo -->
                	<div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-8">
                        	<form class="form-group" method="post" action="cadastraRegistro">
                        	<br>
                        	<input id="pesquisaClientes" type="text" name="pesquisaClientes" placeholder="digite o nome de um cliente"  class="form-control" onkeyup="pesquisa(this.value)">
                        	Cliente:
                        	<div id="cliente-selector">
                        		<c:import url="cliente-selector.jsp"/>
                        	</div>
                        	<br>
                        	Software:
                        	<select name="software"  class="form-control" onchange="atualizaValor(this.value)">
                        		<c:forEach var="soft" items="${listaSoftwares}">
                        			<option id="${soft.id}" value="${soft.id}" >${soft.nome}</option>
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
                        	<select name="desconto" class="form-control" onchange="atualizaDesconto(this.value)">
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
	$(document).ready(function(){
		function pesquisa(pesquisa){
			$.post("pesquisarClienteParaRegistro",
					{'pesquisa':pesquisa},
					function (resposta){
						$("#cliente-selector").html(resposta);
					});
		}
		
		$("#valor").val($("#"+$("#soft").val()).val());
		
		function atualizaValor(valor){
			var desconto = $("#desconto").val();
			desconto = desconto/100;
			valor = valor - (valor * desconto);
			$("#valor").val(valor);
		}
		
		function atualizaDesconto(desconto){
			var valor = $("#software").val();
			valor = valor - (valor * desconto);
			$("#valor").val(valor);
		}
	});
</script>
</body>
</html>
