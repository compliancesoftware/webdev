<%@ attribute name="campo" required="true" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<script>
$(document).ready(function(){
	$(function(){
		$("#${campo}").datepicker({
			dateFormat: 'dd/mm/yy',
			dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
		    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
		    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
		    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
		    nextText: 'Próximo',
		    prevText: 'Anterior',
			showWeek: true,
			firstDay: 1,
			showOtherMonths: true,
			selectOtherMonths: true,
			showAnim: "bounce",
			changeMonth: true,
			changeYear: true
			});
		});
});
</script>