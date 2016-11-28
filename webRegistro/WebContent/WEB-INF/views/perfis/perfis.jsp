<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<!DOCTYPE html>
<html lang="en">
<head>

<mtag:meta/>

<mtag:favicon/>

<title>WebRegistro - Perfis</title>

<mtag:cssJsHead/>

</head>
<body background="<c:url value="resources/images/main_backimage_large.png" />">
<mtag:navbar alertas="${alertas}" logado="${logged.id}" permissao="${logged.permissao}" index="false"/>
<!-- body -->
<div id="corpo" class="container-fluid nav-padded">
  <div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-6">
        <mtag:mensagem msg="${mensagem}"/>
    	<table class="table table-responsive" summary="Tabela de perfis para serem gerenciados pelo Administrador">
         <caption>Perfis cadastrados no sistema</caption>
         <tbody>
         <tr>
          <th scope="col">Foto</th>
          <th scope="col">Nome</th>
          <th scope="col">Permissão</th>
          <th scope="col">Último Acesso</th>
          <th scope="col">Atualizar</th>
         </tr>
         <c:forEach var="p" items="${listaPerfis}">
         <tr>
          <td><img class="login-img img-circle" src="mostraFoto?id=${p.id}" alt="botão de configurações de login"></td>
          <td>${p.nome}</td>
          <c:if test="${p.permissao eq 'Administrador'}">
           <td>Usuário Administrador</td>
          </c:if>
          <c:if test="${p.permissao eq 'Comum'}">
           <td>Usuário Comum</td>
          </c:if>
          <c:if test="${p.permissao eq 'Desativado'}">
           <td>Usuário Desativado</td>
          </c:if>
          <td>${p.ultAcesso }</td>
          <td><a href="gerenciarPerfil?id=${p.id}">Atualizar</a></td>
        </tr>
       </c:forEach>
      </tbody>
     </table>
     <br>
     <a href="cadastrarNovoPerfil"><button class="btn-block btn-white info" type="button">Cadastrar novo perfil</button></a>
    </div>
    <div class="col-md-3"></div>
  </div>
  <mtag:footerHtml/>
</div>
<!-- /body -->

<mtag:cssJsFoot/>

</body>
</html>
