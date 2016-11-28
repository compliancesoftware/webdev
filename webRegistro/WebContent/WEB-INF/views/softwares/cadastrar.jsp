<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<!DOCTYPE html>
<html lang="en">
<head>

<mtag:meta/>

<mtag:favicon/>

<title>WebRegistro - Cadastrar Software</title>

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
                	<h3>Cadastro de Software</h3>
                </div>
            	<div class="panel-body">
                	<div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-8">
                        	<form class="form-group" method="post" action="cadastraSoftware">
                        		<br>
                        		Nome:
                        		<input name="nome" type="text" placeholder="Nome do novo plano"  class="form-control" required>
                        		<br>
                        		Valor
                        		<input id="valor" type="text" name="valor" class="form-control" placeholder="Valor em R$" required>
                        		<br>
                        		<button class="btn-block btn-white form-control info" type="submit">Cadastrar Novo Software</button>
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
<mtag:inputdecimal campo="valor" tamanho="7"/>
</body>
</html>
