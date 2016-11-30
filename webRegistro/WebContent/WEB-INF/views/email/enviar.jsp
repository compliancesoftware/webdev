<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<!DOCTYPE html>
<html lang="en">
<head>

<mtag:meta/>

<mtag:favicon/>

<title>WebRegistro - Notificar Cliente</title>

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
                	<h3>Notificação para clientes</h3>
                </div>
            	<div class="panel-body">
                	<div class="row">
                        <form class="form-group" method="post" action="enviaEmail">
                        	<input type="hidden" name="emails" value="${emails}">
                        	<br>
                        	E-mails a serem notificados:
                        	<textarea rows="3" cols="5" class="form-control" readonly>
                        		<c:forEach var="email" items="${emails}">
                        			${email}
                        		</c:forEach>
                        	</textarea>
                            <br>
                            Assunto:
                            <input type="text" class="form-control" name="assunto" placeholder="Determine o assunto do e-mail" required>
                            <br>
                            E-mail:
                            <textarea rows="3" cols="5" class="form-control" name="corpo" placeholder="Escreva aqui o seu e-mail" required></textarea>
                            <button class="btn-block btn-white form-control info" type="submit">Enviar</button>
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
</body>
</html>
