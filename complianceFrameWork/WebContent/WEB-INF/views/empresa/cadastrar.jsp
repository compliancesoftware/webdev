<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<!DOCTYPE html>
<html lang="en">
<head>

<mtag:meta/>

<mtag:favicon/>

<title>CmplianceFrameWork - Cadastrar Empresa</title>

<mtag:cssJsHead/>

</head>
<body id="pagina" background="<c:url value="resources/images/main_backimage_large.png" />">
<!-- body -->
<div id="corpo" class="container-fluid">
  <div class="row">
	<div class="col-md-6 col-md-offset-3">
    	<div class="row">
        	<hr>
            <div id="form" class="panel panel-default nav-padded">
                <div class="panel-heading text-center">
                	<h3>Cadastro de empresa</h3>
                </div>
            	<div class="panel-body">
                	<div class="row">
                        <form class="form-group" method="post" action="cadastraEmpresa">
                        	<br>
                        	<input name="nome" type="text" class="form-control" placeholder="Nome" required autofocus>
                        	<input id="codigo" name="codigo" type="text" class="form-control" placeholder="Código (CPF ou CNPJ)" required>
                            <br>
                            <button class="btn-block btn-white form-control info" type="submit">Cadastrar empresa</button>
                        </form>
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
<mtag:buscaCep/>
<mtag:sonumeros campo="codigo"/>
</body>
</html>
