<%--
    Document   : mapOggetto
    Created on : 3-ott-2017, 22.26.46
    Author     : andreafadi
--%>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB7LZyOZzvwxSWSLr6cwFw9CpTPT2iIFiw&callback=createMapObject" async defer></script>

<script>
    var latitude = ${indirizzo.getLatitudine()};
    var longitude = ${indirizzo.getLongitudine()};
    
    var nomeNegozio = "${negozio.getNomeNegozio()}";
    var sitoNegozio = "${negozio.getLinkSito()}";
    var orarioNegozio = "${negozio.getOrarioNegozio()}";
    var indirizzoNegozio = "${indirizzo.getVia()} ${indirizzo.getnCivico()}, ${indirizzo.getCitta()}, ${indirizzo.getProvincia()}, ${indirizzo.getRegione()}, ${indirizzo.getStato()}";
    
    var map;
    var myCenter;
    function createMapObject() {
        myCenter = new google.maps.LatLng(latitude,longitude);
        
        var mapCanvas = document.getElementById("mapObject");
        
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
        
        var infoWindowContent = '<div><h3>' + nomeNegozio + '</h3><br/><h4>' + sitoNegozio + '</h4><br/><h5>' + indirizzoNegozio + '</h5><br/><h6>' + orarioNegozio + '</h6></div>';
        
        google.maps.event.addListener(marker,'click',function() {
            var infowindow = new google.maps.InfoWindow({
                content: infoWindowContent
            });
            
            infowindow.open(map, marker);
        });
        
        $('#mapObject').css("height",$('#fotoOggetto').height());
        $('#mapObject').css("width",$('#fotoOggetto').width());
    }

    $(window).resize(function(){
        $('#mapObject').css("height",$('#fotoOggetto').height());
        $('#mapObject').css("width",$('#fotoOggetto').width());
        google.maps.event.trigger(map, 'resize');
        map.setZoom( 15 );
        map.setCenter( myCenter );
   });
</script>
<div id="mapObject"></div>
