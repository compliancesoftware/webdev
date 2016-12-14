<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="map" style="height:400px;width:100%;"></div>

<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCNgo7zFt-FiFjZtuPzhuDz7VUu87kIFhU&callback=initMap" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
  
	var map;
	var marker;
	var myPos = {lat: -8.190219817622724, lng: -34.92728375330353};
	
	function initMap() {
		map = new google.maps.Map(document.getElementById('map'), {
		center: myPos,
		zoom: 18
		});
	
		marker = new google.maps.Marker({
			position: myPos,
			map: map,
			draggable: false,
			animation: google.maps.Animation.DROP
		});
  
		function drop() {
			for (var i = 0; i < markerArray.length; i++) {
				setTimeout(function() {
				addMarkerMethod();
				}, i * 200);
			}
		}
		
		marker.addListener('click', function() {
			var infowindow = new google.maps.InfoWindow({
				content: "<div><h1>Compliance Software LTDA</h1><h4>Empresa focada na criação de softwares web e Android</h4></div>"
			});
			infowindow.open(map, marker);
		});

	}
	</script>