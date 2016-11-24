<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<!DOCTYPE html>
<html lang="en">
<head>

<mtag:meta/>

<mtag:favicon/>

<title>WebRegistro</title>

<mtag:cssJsHead/>
<mtag:morrisGraph/>

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
            <div id="cadastrados" class="panel panel-default nav-padded">
            	<div class="panel-heading text-center">
                	<h3>Clientes cadastrados por mês</h3>
                </div>
            	<div class="panel-body">
                	<div id="gCadastradosMes" style="height:250px"></div>
                </div>
                <div class="panel-footer text-center">
                    <a href="gerenciarClientes"><button class="btn-block btn-white info" type="button">Gerenciar Clientes</button></a>
                </div>
            </div>
            <hr>
            <div id="registrados" class="panel panel-default nav-padded">
            	<div class="panel-heading text-center">
                	<h3>Softwares registrados por mês</h3>
                </div>
            	<div class="panel-body">
                	<div id="gRegistradosMes" style="height:250px"></div>
                </div>
                <div class="panel-footer text-center">
                    <a href="gerenciarRegistros"><button class="btn-block btn-white info" type="button">Gerenciar Registros</button></a>
                </div>
            </div>
            <hr>
            <div id="atrasados" class="panel panel-default nav-padded">
            	<div class="panel-heading text-center">
                	<h3>Clientes Atrasados x Clientes Em Dia</h3>
                </div>
            	<div class="panel-body">
                	<div id="gAtrasadosEmDia" style="height:250px"></div>
                </div>
                <div class="panel-footer text-center">
                    <a href="gerenciarRegistros"><button class="btn-block btn-white info" type="button">Gerenciar Registros</button></a>
                </div>
            </div>
            <hr>
        </div>
    </div>
    <div class="col-md-3"></div>
  </div>
  <mtag:footerHtml/>
</div>

<mtag:cssJsFoot/>

<mtag:morrisGraphFoot/>

<!-- Gráfico gCadastradosMes -->
<script type="text/javascript">
// JavaScript Document
$(function(){
 Morris.Line({
  element: 'gCadastradosMes',
  data: [
  <c:forEach var="ponto" items="${gCadastradosMes}">
    { mes: '${ponto.mes}', cadastrados: '${ponto.valor}' },
  </c:forEach>
  ],
  xkey: 'mes',
  xLabelFormat: function(d) {
   var mon = d.getMonth()+1;
   var s_mon = 'Janeiro';
   
   if(mon == 2)
	   s_mon = 'Fevereiro';
   if(mon == 3)
	   s_mon = 'Março';
   if(mon == 4)
	   s_mon = 'Abril';
   if(mon == 5)
	   s_mon = 'Maio';
   if(mon == 6)
	   s_mon = 'Junho';
   if(mon == 7)
	   s_mon = 'Julho';
   if(mon == 8)
	   s_mon = 'Agosto';
   if(mon == 9)
	   s_mon = 'Setembro';
   if(mon == 10)
	   s_mon = 'Outubro';
   if(mon == 11)
	   s_mon = 'Novembro';
   if(mon == 12)
	   s_mon = 'Dezembro';
   
   return s_mon; 
  },
  ykeys: ['cadastrados'],
  labels: ['Cadastrados'],
  hoverCallback: function (index, options, content, row) {
   var dat = toDate(row.mes);
   
   return row.cadastrados + " clientes cadastrados em " + dat;
  },
  xLabels:'month',
  xLabelAngle: '60',
  resize: 'true'
  });
 function toDate(d){
	 var mon = ''+d.charAt(5)+d.charAt(6);;
	 var s_mon = 'Janeiro';
	 
	 if(mon == '02')
		 s_mon = 'Fevereiro';
	 if(mon == '03')
		 s_mon = 'Março';
	 if(mon == '04')
		 s_mon = 'Abril';
	 if(mon == '05')
		 s_mon = 'Maio';
	 if(mon == '06')
		 s_mon = 'Junho';
	 if(mon == '07')
		 s_mon = 'Julho';
	 if(mon == '08')
		 s_mon = 'Agosto';
	 if(mon == '09')
		 s_mon = 'Setembro';
	 if(mon == '10')
		 s_mon = 'Outubro';
	 if(mon == '11')
		 s_mon = 'Novembro';
	 if(mon == '12')
		 s_mon = 'Dezembro';
	 
	 return s_mon;
 }
});
</script>

<!-- Gráfico gRegistradosMes -->
<script type="text/javascript">
// JavaScript Document
$(function(){
 Morris.Line({
  element: 'gRegistradosMes',
  data: [
  <c:forEach var="ponto" items="${gRegistradosMes}">
    { mes: '${ponto.mes}', registrados: '${ponto.valor}' },
  </c:forEach>
  ],
  xkey: 'mes',
  xLabelFormat: function(d) {
   var mon = d.getMonth()+1;
   var s_mon = 'Janeiro';
   
   if(mon == 2)
    s_mon = 'Fevereiro';
   if(mon == 3)
    s_mon = 'Março';
   if(mon == 4)
    s_mon = 'Abril';
   if(mon == 5)
    s_mon = 'Maio';
   if(mon == 6)
    s_mon = 'Junho';
   if(mon == 7)
    s_mon = 'Julho';
   if(mon == 8)
    s_mon = 'Agosto';
   if(mon == 9)
    s_mon = 'Setembro';
   if(mon == 10)
    s_mon = 'Outubro';
   if(mon == 11)
    s_mon = 'Novembro';
   if(mon == 12)
    s_mon = 'Dezembro';
   
   return s_mon; 
  },
  ykeys: ['registrados'],
  labels: ['Registrados'],
  hoverCallback: function (index, options, content, row) {
   var dat = toDate(row.mes);
   
   return row.registrados + " softwares registrados em " + dat;
  },
  xLabels:'month',
  xLabelAngle: '60',
  resize: 'true'
  });
 function toDate(d){
  var mon = ''+d.charAt(5)+d.charAt(6);;
  var s_mon = 'Janeiro';
  
  if(mon == '02')
   s_mon = 'Fevereiro';
  if(mon == '03')
   s_mon = 'Março';
  if(mon == '04')
   s_mon = 'Abril';
  if(mon == '05')
   s_mon = 'Maio';
  if(mon == '06')
   s_mon = 'Junho';
  if(mon == '07')
   s_mon = 'Julho';
  if(mon == '08')
   s_mon = 'Agosto';
  if(mon == '09')
   s_mon = 'Setembro';
  if(mon == '10')
   s_mon = 'Outubro';
  if(mon == '11')
   s_mon = 'Novembro';
  if(mon == '12')
   s_mon = 'Dezembro';
  
  return s_mon;
 }
});
</script>

<!-- Gráfico gAtrasadosEmDia -->
<script type="text/javascript">
//JavaScript Document
$(function(){
	Morris.Donut({
		element: 'gAtrasadosEmDia',
		data: [
		{label: "Atrasados", value: '${clientes.atrasados}', perc: '${clientes.atrasadosPercentual}'},
		{label: "Em Dia", value: '${clientes.emDia}', perc: '${clientes.emDiaPercentual}'}
		],
		resize: 'true',
		formatter: function(y,data){
			var msg = data.value + "("+ data.perc+"%)";
			return msg;
			}
		});
});
</script>

</body>
</html>
