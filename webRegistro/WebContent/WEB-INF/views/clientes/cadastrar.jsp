<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<!DOCTYPE html>
<html lang="en">
<head>

<mtag:meta/>

<mtag:favicon/>

<title>WebRegistro - Cadastrar Cliente</title>

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
                	<h3>Cadastro de cliente</h3>
                </div>
            	<div class="panel-body">
                	<div class="row">
                        <form class="form-group" method="post" action="cadastraCliente">
                        	<br>
                        	<input name="nome" type="text" class="form-control" placeholder="Nome" required autofocus>
                        	<input id="codigo" name="codigo" type="text" class="form-control" placeholder="Código (CPF ou CNPJ)" required>
                            <input name="email" type="text" class="form-control" placeholder="E-mail" required>
                            <input id="contato" name="contato" type="text" class="form-control" placeholder="Contato" required>
                            <input name="responsavel" type="text" class="form-control" placeholder="Responsável pela empresa" required>
                            <input id="cep" name="cep" type="text" class="form-control" placeholder="CEP" onblur="pesquisacep(this)" required>
                            <input id="endereco" name="endereco" type="text" class="form-control" placeholder="Endereço (digite o número da residencia também)" required>
                            <input id="bairro" name="bairro" type="text" class="form-control" placeholder="Bairro" required>
                            <input id="cidade" name="cidade" type="text" class="form-control" placeholder="Cidade" required>
                            <input id="estado" name="estado" type="text" class="form-control" placeholder="Estado" required>
                            <br>
                            <button class="btn-block btn-white form-control info" type="submit">Cadastrar cliente</button>
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
<mtag:sonumeros campo="contato"/>
</body>
</html>
