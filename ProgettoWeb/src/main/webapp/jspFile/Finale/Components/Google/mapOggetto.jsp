<%-- 
    Document   : mapOggetto
    Created on : 3-ott-2017, 22.26.46
    Author     : andreafadi
--%>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB7LZyOZzvwxSWSLr6cwFw9CpTPT2iIFiw"></script>
<script>
    var map;
    function initialize() {
      var mapOptions = {
        zoom: 15,
        center: new google.maps.LatLng(45.766667, 11.733333)
      };
      map = new google.maps.Map(document.getElementById('map-canvas'),
          mapOptions);
          
      $('#map-canvas').css("height",$(window).height() / 2);
      $('#map_canvas').css("width",$(window).width());
    }

    google.maps.event.addDomListener(window, 'load', initialize);
    
    $(window).resize(function(){
        $('#map-canvas').css("height",$(window).height() / 2);
        $('#map_canvas').css("width",$(window).width());
        google.maps.event.trigger(map, 'resize');
        map.setZoom( map.getZoom() );
   });
</script>
<div id="map-canvas" style="z-index: 9; margin-left:auto; margin-right:auto;"></div>
