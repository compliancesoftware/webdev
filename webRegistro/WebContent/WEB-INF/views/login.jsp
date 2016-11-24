<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<!DOCTYPE html>
<html lang="en">
<head>

<mtag:meta/>

<mtag:favicon/>

<title>WebRegistro - Login</title>

<mtag:cssJsHead/>

</head>
<body background="<c:url value="resources/images/main_backimage_large.png" />">
<div class="container-fluid">
  <br>
  <br>
  <div class="row">
    <div class="col-md-4 col-md-offset-4">
      <div class="panel">
      	<div class="panel-heading center-block">
            <mtag:logoImage/>
        </div>
        <div class="panel-body">
        	<form class="form-group" method="post" action="entrar">
                <mtag:mensagem msg="${mensagem}"/>
            	<input name="nome" type="text" class="form-control" placeholder="Login ou E-mail" required autofocus>
                <input name="senha" type="password" class="form-control" placeholder="Senha" required>
                <button class="btn-block btn-white form-control info" type="submit">Entrar</button>
            </form>
        </div>
      </div>
    </div>
  </div>
  <mtag:footerHtml/>
</div>

<mtag:cssJsFoot/>

</body>
</html>
