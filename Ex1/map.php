<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<link rel="icon" type="image/jpg" href="https://png.icons8.com/color/1600/google-maps.png">
	<title> Mark on GG Maps</title>

  	<style>
  		*{
  			margin: 0;
  			padding: 0;
  		}
		#map{
			height: 766px;
			width: 100%;
		}
	</style>
</head>

<body>
	<div id="map"></div>

	<script>
		function initMap(){
			var latitude1 = Number("<?php echo $_POST['latitude']; ?>");
			var longitude1 = Number("<?php echo $_POST['longitude']; ?>");
     			var pos1 = new google.maps.LatLng(latitude1, longitude1);

     			var latitude2 = Number("<?php echo $_POST['slat']; ?>");
			var longitude2 = Number("<?php echo $_POST['slng']; ?>");
			var pos2 = new google.maps.LatLng(latitude2, longitude2);



			var R = 6371; //kilometers
			var dLat = (latitude2-latitude1)*Math.PI/180;
			var dLng = (longitude2-longitude1)*Math.PI/180;
			var a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(latitude1*Math.PI/180)*Math.cos(latitude2*Math.PI/180)*Math.sin(dLng/2)*Math.sin(dLng/2);
			var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
			var d = R * c;


			var options = {
				zoom: 15,
				center: pos1
			};
			var map = new google.maps.Map(document.getElementById('map'), options); 


			var marker = new google.maps.Marker({
				position: pos1,
				map:map
			});

			var html1 = "Position: " +"(" + latitude1 + ", " + longitude1 +")";
			var html2 = "Distance: " + d;

			var infowindow = new google.maps.InfoWindow();

			if("<?php echo $_POST['slat']; ?>" == "" && "<?php echo $_POST['slng']; ?>" == ""){
				infowindow.setContent(html1);
			}
			else
			 	infowindow.setContent(html2);

    			infowindow.open(map, marker);


    			// google.maps.event.addListener(map, 'click', function() {
       //   				 infowindow.open(map, marker);
       //  			});
    		}
	</script>

	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAb5zrSI-3Ib9OMZ8noiU2ATUmbf_BA6Xo&callback=initMap"></script>

</body>

</html>