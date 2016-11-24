<%@ attribute name="graficoPorMes" required="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
// JavaScript Document
$(function(){
 Morris.Line({
  element: 'gCadastradosMes',
  data: [
  <c:forEach var="ponto" items="${graficoPorMes}">
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