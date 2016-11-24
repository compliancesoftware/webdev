<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<!DOCTYPE html>
<html lang="en">
<head>

<mtag:meta/>

<mtag:favicon/>

<title>WebRegistro - Configurações de perfil</title>

<mtag:cssJsHead/>

</head>                      
<body id="pagina" background="<c:url value="resources/images/main_backimage_large.png" />">
<mtag:navbar alertas="${alertas}" logado="${logged.id}" permissao="${logged.permissao}" index="false"/>
<div id="corpo" class="container-fluid nav-padded">
  <div class="row">
	<div class="col-md-6 col-md-offset-3">
    	<div class="row">
        	<hr>
            <div id="form" class="panel panel-default nav-padded">
                <mtag:mensagem msg="${mensagem}"/>
            	<div class="panel-heading text-center">
                	<h3>Configurações de perfil</h3>
                </div>
            	<div class="panel-body">
                	<div class="row">
                        <form class="form-group" method="post" action="atualizaPerfil" enctype="multipart/form-data">
                       	    <div class="col-md-6">
                           	    <div class="fileupload">
                                    <input name="id" type="hidden" value="${perfilAlterar.id}">
                               	    <mtag:loginImage src="mostraFoto?id=${perfilAlterar.id}"/>
                                    <input type="file" id="foto" name="image">
                                </div>
                            </div>
                 		    <div class="col-md-6">
                        	    <br>
                            	<input name="nome" type="text" class="form-control" placeholder="Nome" value="${perfilAlterar.nome}" required autofocus>
                            	<input name="email" type="text" class="form-control" placeholder="E-mail" value="${perfilAlterar.email}" required>
                            	<input name="contato" type="number" class="form-control" placeholder="Contato" value="${perfilAlterar.contato}" required>
                            	<input name="senha" type="password" class="form-control" placeholder="Senha" value="${perfilAlterar.senha}" required>
                                <mtag:permissao value="${perfilAlterar.permissao}"/>
                            	<input type="text" class="form-control" placeholder="Último acesso" value="${perfilAlterar.ultAcesso}" readonly>
                            	<br>
                            	<button class="btn-block btn-white form-control info" type="submit">Atualizar perfil</button>
                           </div>
                         </form>
                     </div>
                </div>
            </div>
			<mtag:footerHtml/>
        </div>
    </div>
  </div>
</div>

<mtag:cssJsFoot/>

<mtag:carregaImagem/>

</body>
</html>