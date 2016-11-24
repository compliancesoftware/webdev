<%@ attribute name="id" required="true" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="value" required="true" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="mtag" %>

<label for="${id}">${label}</label><br />
<input type="text" id="${id}" name="${id}" value="${value}" readonly="readonly" />

<script>
$(function(){
	$("#${id}").datepicker({
		dateFormat: 'dd/mm/yy',
		showWeek: true,
		firstDay: 1,
		showOtherMonths: true,
		selectOtherMonths: true,
		showAnim: "bounce",
		changeMonth: true,
		changeYear: true
		});
	$("#${id}").mask("99/99/9999");
	});
</script>