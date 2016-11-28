<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<!DOCTYPE html>
<html lang="en">
<head>

<mtag:meta/>

<mtag:favicon/>

<title>Compliance Software Framework</title>

<mtag:cssJsHead/>

</head>
<body id="pagina" background="<c:url value="resources/images/main_backimage_large.png" />">
<mtag:navbar alertas="${alertas}" logado="${logged.id}" permissao="${logged.permissao}" index="true"/>
<div id="corpo" class="container-fluid nav-padded">
  <div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-6">
    	<div class="row">
            <mtag:mensagem msg="${mensagem}"/>
            <mtag:alertasNaoLidos alertas="${alertas}"/>
        	<hr>
        	<h1>Bem vindo!</h1>
            <h2>Compliance Software Framework</h2>
            <hr>
        </div>
    </div>
    <div class="col-md-3"></div>
  </div>
  <mtag:footerHtml/>
</div>

<mtag:cssJsFoot/>

</body>
</html>
