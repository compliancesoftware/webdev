<%@ attribute name="campo" required="true" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<script>
$(document).ready(function(){
	$(function(){
		$("#${campo}").datepicker({
			dateFormat: 'dd/mm/yy',
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