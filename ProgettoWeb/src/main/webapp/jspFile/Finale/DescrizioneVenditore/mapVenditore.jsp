<%--
    Document   : mapOggetto
    Created on : 3-ott-2017, 22.26.46
    Author     : andreafadi
--%>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB7LZyOZzvwxSWSLr6cwFw9CpTPT2iIFiw&callback=createMapSeller" async defer></script>

<script>
    var addresses = [];
    var stores = [];
    
    <c:forEach items="${listaNegozi.getList()}" var="neg">
        stores.push(["${neg.getNomeNegozio()}", "${neg.getLinkSito()}", "${neg.getOrarioNegozio()}"]);
    </c:forEach>
    
    <c:forEach items="${listaIndirizzi.getList()}" var="ind">
        addresses.push(["${ind.getLatitudine()}", "${ind.getLongitudine()}", "${ind.getVia()} ${ind.getnCivico()}", "${ind.getCitta()}", "${ind.getProvincia()}", "${ind.getRegione()}", "${ind.getStato()}"]);
    </c:forEach>
    
    var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    var labelIndex = 0;
    function createMapSeller() {
        var mapCanvas = document.getElementById("mapSeller");
        
        var mapOptions = {
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        
        var map = new google.maps.Map(mapCanvas, mapOptions);
        
        setMarkers(map, addresses, stores);
        
        var autoZoom = new google.maps.LatLngBounds();
        for (var i = 0; i < addresses.length; i++) {
            autoZoom.extend(new google.maps.LatLng(addresses[i][0], addresses[i][1]));
        }
        map.fitBounds(autoZoom);
        
        $('#mapSeller').css("height",$('#descrizione').height() * 2);
    }
    
    function setMarkers(map, addresses, stores) {

        var marker, i;
        for (i = 0; i < addresses.length; i++) {

            latlngset = new google.maps.LatLng(addresses[i][0], addresses[i][1]);

            var marker = new google.maps.Marker({
                map: map,
                label: labels[labelIndex++ % labels.length],
                position: latlngset
            });
            map.setCenter(marker.getPosition());

            var content = '<div><h3>' + stores[i][0] + '</h3><br/><h4>' + stores[i][1] + '</h4><br/><h5>' + addresses[i][2] + ', ' + addresses[i][3] + ', ' + addresses[i][4] + ', ' + addresses[i][5] + ', ' + addresses[i][6] + '</h5><br/><h6>' + stores[i][2] + '</h6></div>';

            var infowindow = new google.maps.InfoWindow();
               
            google.maps.event.addListener(marker,'click', (function(marker,content,infowindow){ 
                return function() {
                    infowindow.setContent(content);
                    infowindow.open(map,marker);
                };
            })(marker,content,infowindow));
        }
    }
    
    $(window).resize(function(){
        $('#mapSeller').css("height",$('#descrizione').height() * 2);
        google.maps.event.trigger(map, 'resize');
    });
</script>

<div class="text-center">
    <h3>Il venditore possiede questi punti vendita</h3>
</div>
<div id="mapSeller"></div>
