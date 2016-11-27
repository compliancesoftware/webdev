<%@ attribute name="permissao" required="true" %>
<%@ attribute name="alertas" required="true" %>
<%@ attribute name="logado" required="true" %>
<%@ attribute name="index" required="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<!-- navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
 <!-- .container-fluid -->
  <div class="container-fluid"> 
    <!-- agrupa Brand e Toggle para melhor visualização em dispositivos menores -->
    <!-- .navbar-header -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navBar">
      <span class="sr-only">Alternar Navegação</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand logo" href="home"><mtag:logoImage/></a>
    </div>
    <!-- /.navbar-header -->
    <!-- .navbar-collapse -->
    <div class="collapse navbar-collapse" id="navBar">
     <ul class="nav navbar-nav">
     	<c:if test="${index eq true}">
     		<li><a href="#corpo">Home</a></li>
     	</c:if>
        <c:if test="${index eq false}">
     		<li><a href="home">Home</a></li>
     	</c:if>
         <c:if test="${permissao eq 'Administrador'}">
         	<li><a href="gerenciarSoftwares">Softwares</a></li>
         	<li><a href="gerenciarClientes">Clientes</a></li>
         	<li><a href="gerenciarRegistros">Registros</a></li>
         </c:if>
         <c:if test="${index eq true}">
         	<li class="dropdown" data-toggle="tooltip" title="Dashboard">
            	<a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                	Dashboard<span class="caret white"></span></a>
          		<ul class="dropdown-menu" role="menu">
            		<li><a href="#cadastrados">Clientes cadastrados no mês</a></li>
            		<li><a href="#registrados">Regitrados por mês</a></li>
                    <li class="divider"></li>
                    <li><a href="#atrasados">Atrasados x Em Dia</a></li>
          		</ul>
        	</li>
         </c:if>
     </ul>
     <ul class="nav navbar-nav navbar-right">
     	<c:if test="${permissao eq 'Administrador'}">
     		<li><a href="logs" data-toggle="tooltip" title="Logs do sistema">
     				<span class="glyphicon glyphicon-list-alt white"></span>
     			</a>
     		</li>
     	</c:if>
        <li class="dropdown" data-toggle="tooltip" title="Gerenciamento de notificações">
             <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                 <span class="glyphicon glyphicon-envelope white"></span>
                    <span class="caret white"></span></a>
            <ul class="dropdown-menu" role="menu">
               <li><a href="notificaAtrasados"><span class="glyphicon glyphicon-thumbs-down"></span> Notificar clientes atrasados</a></li>
              <li class="divider"></li>
              <li><a href="mensagens"><span class="glyphicon glyphicon-envelope"></span> Enviar mensagens</a></li>
            </ul>
         </li>
            <mtag:alertasLi alertas="${alertas}"/>
            <li class="dropdown" data-toggle="tooltip" title="Configurações de perfil">
             <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                 <mtag:loginIcon src="mostraFoto?id=${logado}"/></a>
            <ul class="dropdown-menu" role="menu">
              <li><a href="gerenciarPerfis"><span class="glyphicon glyphicon-user"></span> Gerenciar perfil</a></li>
                    <li class="divider"></li>
              <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
            </ul>
            </li>
       </ul>
    </div>
    <!-- /.navbar-collapse --> 
  </div>
  <!-- /.container-fluid --> 
</nav>
<!-- /navbar -->