<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Monuments</title>


<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

<script
	src='https://static-assets.mapbox.com/gl-pricing/dist/mapbox-gl.js'></script>
<link href='https://api.mapbox.com/mapbox-gl-js/v0.53.0/mapbox-gl.css'
	rel='stylesheet' />

<link rel="stylesheet"
	href="https://unpkg.com/leaflet@1.4.0/dist/leaflet.css"
	integrity="sha512-puBpdR0798OZvTTbP4A8Ix/l+A4dHDD0DGqYW6RQ+9jxkRFclaxxQb/SJAWZfWAkuyeQUytO7+7N4QKrDh+drA=="
	crossorigin="" />

<script src="https://unpkg.com/leaflet@1.4.0/dist/leaflet.js"
	integrity="sha512-QVftwZFqvtRNi0ZyCtsznlKSWOStnDORoefr1enyq5mVL4tmKB3S/EnC3rRJcxCPavG10IcrVGSmPh6Qw5lwrg=="
	crossorigin=""></script>
<script src="https://unpkg.com/leaflet.markercluster@1.4.1/dist/leaflet.markercluster.js"></script>
<link rel="stylesheet" href="https://unpkg.com/leaflet.markercluster@1.4.1/dist/MarkerCluster.Default.css"></link>
<link rel="stylesheet" href="https://unpkg.com/leaflet.markercluster@1.4.1/dist/MarkerCluster.css"></link>
<script src='https://api.mapbox.com/mapbox.js/plugins/leaflet-fullscreen/v1.0.1/Leaflet.fullscreen.min.js'></script>
<link href='https://api.mapbox.com/mapbox.js/plugins/leaflet-fullscreen/v1.0.1/leaflet.fullscreen.css' rel='stylesheet' />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
  $("#city,#century").change(function(){
    ajaxfunction();
  });
});
$(document).ready(function(){
	$("#type").change(function(){
		//AJAX Function:
		 ajaxfunction();
	});
});
$(document).ready(function(){
	$("#typedWord").on('input', function(){		
	      //AJAX Function:
	      ajaxfunction();
	      
	});
});
var foundElements = [];
function ajaxfunction(){
	$.post("${pageContext.request.contextPath}/main/listByType?type="+$('#type').val()+
  		  "&city="+$('#city').val()+
  		  "&century="+$('#century').val()+
  		  "&typedWord="+$('#typedWord').val(),	      
    function(data, status, jqXHR) {
		$('#monumentsTable').find("tr:gt(1)").remove();
		markers.clearLayers();
		if(!$.trim(data)){
			$("#monumentsTable").append("<tr><td>Nəticə tapılmadı </td></tr>")
		}else{
  	  $.each(data, function(index, currMonument) {
  		  //Fill the table dynamically  		  
  		  $("#monumentsTable").append("<tr><td>" + currMonument.name + "</td><td>" + currMonument.monumentCreatedHistory + "</td><td>" + currMonument.locateName + "</td></tr>");
  		  foundElements.push(currMonument);  
  		  putMarker(currMonument);
  	  }); 
  	  addRowHandlers();
  	  
		}
	}, 'json').done(function() {  })
	  .fail(function(jqxhr, settings, ex) { alert('failed, ' + ex); });
}
function addRowHandlers() {
	  var table = document.getElementById("monumentsTable");
	  var rows = table.getElementsByTagName("tr");
	  
	  for (i = 0; i < rows.length; i++) {
	    var currentRow = table.rows[i];
	    var createClickHandler = function(row) {
	      return function() {
	    	  var cell = row.getElementsByTagName("td")[0];
	          var name = cell.innerHTML;
	          var i;
	          for(i=0; i<foundElements.length; i++){
	        	  if((foundElements[i].name.localeCompare(name))==0){
	        		  handleMapClicks(foundElements[i].x,foundElements[i].y, name);
	        		  break;
	        	  }
	          }
	          
	      };
	    };
	    currentRow.onclick = createClickHandler(currentRow);
	  }
	}

</script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
* {
	box-sizing: border-box;
}

/* Create two equal columns that floats next to each other */
.leftcolumn {
	float: left;
	width: 60%;
}

.rightcolumn {
	float: Left;
	padding-left: 5px;
	width: 40%;
}

#type {
	width: 39vw;
}

#city {
	width: 13vw;
}

#century {
	width: 13vw;
}
</style>

</head>
<body>

    <header id="mainHeader">TARİXİ ABİDƏLƏR</header>

	<div class="row">

		<!-- This is left column, and LeafLet Map -->
		<div class="leftcolumn">

			<div id='map' style='height: 600px;'></div>

			<script>
		
		var mymap = L.map('map').setView([40.3955, 49.83], 11);

		//FullScreen button and control
		mymap.addControl(new L.Control.Fullscreen());
		
		L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
		    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
		    maxZoom: 18,
		    id: 'mapbox.streets',
		    accessToken: 'pk.eyJ1Ijoibm92cnV6MjEiLCJhIjoiY2p1bWo2bzc3MmlldjRmcGdzemJhYWNkMCJ9.sh2KxuiBognBUk-ZMzZSUg'
		}).addTo(mymap);
		//Adding different maps as overlays
		var eaglemap = L.tileLayer('http://172.16.35.237:8484/map?x={x}&y={y}&z={z}&map=8');
        var osm  = L.tileLayer('http://tile.openstreetmap.org/{z}/{x}/{y}.png');	          
        var googleMap  = L.tileLayer('http://mt1.google.com/vt/lyrs=y&x={x}&y={y}&z={z}&hl=az-AZ');
        var google  = L.tileLayer('http://mt1.google.com/vt/lyrs=m@129&hl=az&x={x}&y={y}&z={z}&s=Galileo');
		var overlays = {"Google Hybrid": googleMap,
						"Google": google,
						"OpenStreetMap": osm,
						"EagleMap": eaglemap};
		L.control.layers(overlays).addTo(mymap);
		var markers = L.markerClusterGroup({
			spiderfyOnMaxZoom: true,
			animateAddingMarkers: true,
			showCoverageOnHover: true,
			zoomToBoundsOnClick: true
		});
		
				
		var LeafIcon = L.Icon.extend({
		    options: {
		        iconSize:     [38, 38],
		        shadowSize:   [50, 64],
		        iconAnchor:   [19, 0],
		        shadowAnchor: [4, 62],
		        popupAnchor:  [-3, -6]
		    }
		});		
		
		var icons = [];
		<c:forEach var="image" items="${typeImages}">
			var icon = new LeafIcon({iconUrl: 'data:image/png;base64,'+'${image.path}'});
			icons.push(icon);
		</c:forEach>
		L.icon = function (options) {
		    return new L.Icon(options);
		};
		<c:forEach var="monument" items="${allMonuments}">
			var x = Number(${monument.x});
			var y = Number(${monument.y});
			var marker = L.marker([x, y], {icon: icons[${monument.monumentTypeId}]})
			markers.addLayer(marker);
		</c:forEach>
		mymap.addLayer(markers);
			
						
	</script>

		</div>


		<!-- This is right column -->
		<div class="rightcolumn">

			<div id="wrapper">
				<div id="header">
					<h2>Axtarış</h2>
				</div>


				<form id="typeForm">
					<select name="type" id="type" width="60px">
						<option value="">Kateqoriyanı seçin</option>
						<c:forEach var="type" items="${types}">
							<option value="${type.name}">${type.name}</option>
						</c:forEach>

					</select>

				</form>
			</div>

			<script>
		function getSelectedValue(){
			var selectedValue = document.getElementById("city").value;
		}
		
		function putMarker(data){
			var x = Number(data.x);
			var y = Number(data.y);
			var marker = L.marker([x, y], {icon: icons[data.monumentTypeId]})
			markers.addLayer(marker);			
			mymap.addLayer(markers);
		}
		
		function handleMapClicks(x, y, n){
			L.popup().setLatLng([x+0.0001, y])
			.setContent("<b>"+n+"</b>")
			.openOn(mymap);
			mymap.setView([x, y], 15);
		}
		
	</script>

			<!-- Three columns are starting here -->
			<div id="container">
				<div id="content" style="overflow-x: auto;">
					<table id="monumentsTable">

						<!-- Header of table which is fixed -->
						<thead>
						<!-- The first row -->
						<tr>
							<th>Abidənin adı</th>
							<th>Yaradılma tarixi</th>
							<th>Yerləşdiyi yer</th>
						</tr>

						<!-- The second row -->
						<tr>
							<!-- Name Search -->
							<td height="30">
								<form>
									<div class="small-3 columns">
										<input type="text" id="typedWord" name="typedWord"
											placeholder="Axtar">
									</div>

								</form>
							</td>

							<!-- Century Dropdown -->
							<form id="myForm">
								<td><select name="century" id="century">
										<option value="">Tarix</option>
										<c:forEach var="number" items="${centuries}">
											<option value="${number}">${number}</option>
										</c:forEach>

								</select></td>

								<!-- City Dropdown -->
								<td><select name="city" id="city"
									onchange="getSelectedValue()">
										<option value="">Yer</option>
										<c:forEach var="city" items="${cities}">
											<option value="${city}">${city}</option>
										</c:forEach>
								</select></td>
							</form>
						</tr>
						</thead>
						<!-- Body of the table which is scrolling -->
						<tbody>

						<!-- The third row -->
						<c:forEach var="monument" items="${allMonuments}"
							varStatus="status">
							<tr id="itemsRow"
								onclick='handleMapClicks(${monument.x}, ${monument.y}, "${monument.name}")'>
								<td>${monument.name}</td>
								<td>${monument.monumentCreatedHistory}</td>
								<td>${monument.locateName}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

		</div>

	</div>

</body>
</html>