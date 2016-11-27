<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<!DOCTYPE html>
<html lang="en">
<head>

<mtag:meta/>

<mtag:favicon/>

<title>WebRegistro - Registros</title>

<mtag:cssJsHead/>

</head>
<body background="<c:url value="resources/images/main_backimage_large.png" />">

<mtag:navbar alertas="${alertas}" logado="${logged.id}" permissao="${logged.permissao}" index="false"/>

<!-- body -->
<div id="corpo" class="container-fluid nav-padded">
   <div class="row">
    <mtag:mensagem msg="${mensagem}"/>
  	<div class="col-md-12">
    	<div id="tabela">
    		<c:import url="tabela.jsp"/>
    	</div>
        <br>
        <a href="cadastrarRegistro"> <button class="btn-block btn-white info" type="button">Efetuar um novo Registro</button></a>
    </div>
  </div>
  <hr>
  <div class="row">
  	<div class="col-md-3"></div>
  	<div class="col-md-6">
  		<form>
  			Cliente:
  			<input name="cliente" class="form-control" type="text" placeholder="Nome de cliente" onkeyup="pesquisa()">
  			Software:
  			<input name="software" class="form-control" type="text" placeholder="Nome do software" onkeyup="pesquisa()">
  			Valor:
  			<input id="valor" name="valor" class="form-control" type="text" placeholder="Valor" onkeyup="pesquisa()">
  			Desconto:
  			<select name="desconto" class="form-control" onchange="pesquisa()">
  				<option id="semfiltro" value="-1.00">Sem Filtro</option>
  				<c:forEach var="d" items="${listaPercentual}">
  					<option id="${d.valor}" value="${d.valor}">${d.valorAsString}%</option>
  				</c:forEach>
  			</select>
  			Validade entre:
  			<input type="text" id="validadeInicio" name="validadeInicio" class="form-control" placeholder="Validade(inicio)" onchange="pesquisa()" readonly="readonly" />
  			e:
        	<input type="text" id="validadeFim" name="validadeFim" class="form-control" placeholder="Validade(fim)" onchange="pesquisa()" readonly="readonly" />
        	Ativos:
        	<select name="ativo" class="form-control" onchange="pesquisa()">
        		<option value=2>Sem filtro</option>
        		<option value=0>Desativados</option>
        		<option value=1>Ativos</option>
        	</select>
        	<br>
        	<button class="btn-block btn-white form-control info" type="button" onclick="pesquisa()">Pesquisar</button>
  		</form>
  	</div>
  	<div class="col-md-3"></div>
  </div>
  <mtag:footerHtml/>
</div>
<!-- /body -->
<mtag:cssJsFoot/>
<mtag:inputdecimal campo="valor" tamanho="7"/>
<mtag:campoData campo="validadeInicio"/>
<mtag:campoData campo="validadeFim"/>
<script type="text/javascript">
	function pesquisa(){
		var cliente = $("input[name='cliente']").val();
		var software = $("input[name='software']").val();
		var valor = $("input[name='valor']").val();
		var desconto = $("select[name='desconto']").val();
		var validadeInicio = $("input[name='validadeInicio']").val();
		var validadeFim = $("input[name='validadeFim']").val();
		var ativo = $("select[name='ativo']").val();
		
		$.post("pesquisarRegistros",{'cliente':cliente,
			'software':software,
			'valor':valor,
			'desconto':desconto,
			'validadeInicio':validadeInicio,
			'validadeFim':validadeFim,
			'ativo':ativo},
			function (resposta){
				$("#tabela").html(resposta);
			});
	}
</script>
</body>
</html>
