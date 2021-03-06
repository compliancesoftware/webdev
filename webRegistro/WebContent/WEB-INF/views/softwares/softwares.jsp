<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<!DOCTYPE html>
<html lang="en">
<head>

<mtag:meta/>

<mtag:favicon/>

<title>WebRegistro - Softwares</title>

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
        <a href="cadastrarSoftware"> <button class="btn-block btn-white info" type="button">Cadastrar novo software</button></a>
    </div>
  </div>
  <hr>
  <div class="row">
  	<div class="col-md-3"></div>
  	<div class="col-md-6">
  		Nome:
  		<input id="pesquisa" name="pesquisa" class="form-control" type="text" placeholder="Nome de software" onkeyup="pesquisa()">
  	</div>
  	<div class="col-md-3"></div>
  </div>
  <mtag:footerHtml/>
</div>
<!-- /body -->
<mtag:cssJsFoot/>
<script type="text/javascript">
	function pesquisa(){
		var pesquisa = $("#pesquisa").val();
		
		$.post("pesquisarSoftwares",{'pesquisa':pesquisa},
			function (resposta){
				$("#tabela").html(resposta);
			});
	}
</script>
</body>
</html>
