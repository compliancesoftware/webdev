<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<!DOCTYPE html>
<html lang="en">
<head>

<mtag:meta/>

<mtag:favicon/>

<title>WebRegistro - Logs</title>

<mtag:cssJsHead/>

</head>
<body background="<c:url value="resources/images/main_backimage_large.png" />">
<mtag:navbar alertas="${alertas}" logado="${logged.id}" permissao="${logged.permissao}" index="false"/>
<!-- body -->
<div id="corpo" class="container-fluid nav-padded">
  <div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <mtag:mensagem msg="${mensagem}"/>
    	<table class="table table-responsive" summary="Tabela de registro de ações de perfis do sistema">
         <caption>Logs do sistema (registro de ações dos perfis)</caption>
         <tbody>
         <tr>
          <th scope="col">Foto</th>
          <th scope="col">Perfil</th>
          <th scope="col">Ação</th>
          <th scope="col">Data</th>
         </tr>
         <c:forEach var="log" items="${listaLogs}">
         <tr>
          <td><img class="login-img img-circle" src="mostraFoto?id=${log.autor.id}" alt="botão de configurações de login"></td>
          <td>${log.autor.nome}</td>
          <td>${log.acao}</td>
          <td>${log.fmtData}</td>
        </tr>
       </c:forEach>
      </tbody>
     </table>
    </div>
    <div class="col-md-1"></div>
  </div>
  <mtag:footerHtml/>
</div>
<!-- /body -->

<mtag:cssJsFoot/>

</body>
</html>
