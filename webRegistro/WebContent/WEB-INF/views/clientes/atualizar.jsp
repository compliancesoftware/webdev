<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<!DOCTYPE html>
<html lang="en">
<head>

<mtag:meta/>

<mtag:favicon/>

<title>WebRegistro - Atualizar Cliente</title>

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
                	<h3>Atualização de cadastro de cliente</h3>
                </div>
            	<div class="panel-body">
                	<div class="row">
                        <form class="form-group" method="post" action="atualizaCliente">
                        	<br>
                        	<input name="id" type="hidden" value="${cliente.id}">
                        	<input name="nome" type="text" class="form-control" placeholder="Nome" value="${cliente.nome}" required autofocus>
                        	<input id="codigo" name="codigo" type="text" class="form-control" placeholder="Código (CPF ou CNPJ)" value="${cliente.codigo}" required>
                            <input name="email" type="text" class="form-control" placeholder="E-mail" value="${cliente.email}" required>
                            <input id="contato" name="contato" type="text" class="form-control" placeholder="Contato" value="${cliente.contato}" required>
                            <input name="responsavel" type="text" class="form-control" placeholder="Responsável pela empresa" value="${cliente.responsavel}" required>
                            <input id="cep" name="cep" type="text" class="form-control" placeholder="CEP" onblur="pesquisacep(this)" value="${cliente.cep}" required>
                            <input id="endereco" name="endereco" type="text" class="form-control" placeholder="Endereço (digite o número da residencia também)" value="${cliente.endereco}" required>
                            <input id="bairro" name="bairro" type="text" class="form-control" placeholder="Bairro" value="${cliente.bairro}" required>
                            <input id="cidade" name="cidade" type="text" class="form-control" placeholder="Cidade" value="${cliente.cidade}" required>
                            <input id="estado" name="estado" type="text" class="form-control" placeholder="Estado" value="${cliente.estado}" required>
                            <input type="text" class="form-control" value="Modificado em ${cliente.fmtDtInclusao}" readonly>
                            <input type="text" class="form-control" value="Modificado por ${cliente.quemIncluiu.nome}" readonly>
                            <br>
                            <button class="btn-block btn-white form-control info" type="submit">Atualizar cliente</button>
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
