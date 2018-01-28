<%--
    Document   : mapOggetto
    Created on : 3-ott-2017, 22.26.46
    Author     : andreafadi
--%>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB7LZyOZzvwxSWSLr6cwFw9CpTPT2iIFiw&callback=createMapStore" async defer></script>

<script>
    var latitude = ${indirizzo.getLatitudine()};
    var longitude = ${indirizzo.getLongitudine()};
    
    var map;
    var myCenter;
    function createMapStore() {
        myCenter = new google.maps.LatLng(latitude,longitude);
        
        var mapCanvas = document.getElementById("mapStore");
        
        var mapOptions = {
            zoom: 15,
            center: myCenter,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        
        map = new google.maps.Map(mapCanvas, mapOptions);
        
        var marker = new google.maps.Marker({
            position: myCenter
        });
        
        marker.setMap(map);
        
        $('#mapStore').css("height",$('#fotoNegozio').height());
    }

    $(window).resize(function(){
        $('#mapStore').css("height",$('#fotoNegozio').height());
        google.maps.event.trigger(map, 'resize');
        map.setZoom( 15 );
        map.setCenter( myCenter );
   });
</script>

<div class="text-center">
    <h3>Il negozio si trova in questa posizione</h3>
</div>
<div id="mapStore"></div>
