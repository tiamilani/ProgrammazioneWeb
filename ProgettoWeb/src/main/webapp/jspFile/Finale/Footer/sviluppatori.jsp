<%--
    Document   : sviluppatori.jsp
    Created on : 4-ott-2017, 15.32.58
    Author     : damiano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <title>ShopEro Developer</title>
        <link rel="stylesheet" href="sviluppatori.css">
        <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
    </head>

    <body>
        <div class="container-fluid">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
            <div class="row justify-content-center">
                <div class="col-6 description">
                    <h1>Ciao</h1>
                    <p>
                        Siamo un gruppo di studenti di Informatica all'Università degli Studi di Trento.<br>
                        Benvenuto nel nostro nuovo sito di e-commerce
                    </p>
                </div>
            </div>
            <div class="content container-fluid">
                <div class="card-deck">
                    <div class="col col-xs-12 col-sm-6 col-md-3 col-lg-3">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title text-center"> Andrea Fadi</h4>
                                <img class="rounded-circle img-thumbnail d-block mx-auto" src="../Img/Andrea_Fadi.jpg" alt="Andrea Fadi">
                                <h6 class="card-subtitle text-muted text-center">Full Stack Developer &AMP; Web Designer</h6>
                            </div>
                            <div class="card-social">
                                    <a href="http://www.facebook.com/iAndrea96?fref=ts" class="fa fa-facebook"></a>
                                    <a href="http://www.linkedin.com/in/in/andreafadi" class="fa fa-linkedin"></a>
                                    <a href="http://github.com/iAndrea" class="fa fa-github"></a>
                            </div>
                        </div>
                    </div>
                    <div class="col col-xs-12 col-sm-6 col-md-3 col-lg-3">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title text-center">Damiano Sartori</h4>
                                <img class="rounded-circle img-thumbnail  d-block mx-auto" src="../Img/Damiano_Sartori.jpg" alt="Damiano Sartori">
                                <h6 class="card-subtitle text-muted text-center">Full Stack Developer &AMP; Data Modelling</h6>
                            </div>
                            <div class="card-social">
                                    <a href="http://www.facebook.com//damiano.sartori.96" class="fa fa-facebook"></a>
                                    <a href="http://www.linkedin.com/in/damiano-sartori-5b3a4b130" class="fa fa-linkedin"></a>
                                    <a href="http://www.github.com/DAS-96" class="fa fa-github"></a>
                            </div>
                        </div>
                    </div>
                    <div class="col col-xs-12 col-sm-6 col-md-3 col-lg-3">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title text-center">Federico Brugiolo</h4>
                                <img class="rounded-circle img-thumbnail d-block mx-auto" src="../Img/Federico_Brugiolo.jpg" alt="Federico Brugiolo">
                                <h6 class="card-subtitle text-muted text-center">Full Stack Developer &AMP; Web Designer</h6>
                            </div>
                            <div class="card-social">
                                    <a href="http://www.facebook.com/federico.brugiolo?fref=ts" class="fa fa-facebook"></a>
                                    <a href="http://www.github.com/Brugix" class="fa fa-github"></a>
                            </div>
                        </div>
                    </div>
                    <div class="col col-xs-12 col-sm-6 col-md-3 col-lg-3">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title text-center">Mattia Milani</h4>
                                <img class="rounded-circle img-thumbnail d-block mx-auto" src="../Img/Mattia_Milani.jpg" alt="Mattia Milani">
                                <h6 class="card-subtitle text-muted text-center">Full Stack Developer &AMP; Data Modelling</h6>
                            </div>
                            <div class="card-social">
                                    <a href="http://www.facebook.com/mattia.milani.54?fref=ts" class="fa fa-facebook"></a>
                                    <a href="http://www.linkedin.com/in/mattia-milani-20931b133" class="fa fa-linkedin"></a>
                                    <a href="http://www.github.com/tiamilani" class="fa fa-github"></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div>
                <div class="row justify-content-center">
                    <div class="col-6 description">
                        <h1 id="findUs">Dove Trovarci</h1>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div id="mapObject" style="height: 400px; width: 600px"></div>
                </div>
            </div>

            <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyATC6NxeSsKDBncZwJ4aDivvvs6_5iusSc&callback=createMapAboutUs" async defer></script>
            <script type="text/javascript">
                var myCenter;
                function createMapAboutUs() {
                    myCenter = new google.maps.LatLng(46.066993034254345, 11.149814128875732);
                    var mapCanvas = document.getElementById("mapObject");

                    var mapOptions = {
                        center: myCenter,
                        zoom: 16,
                        mapType: google.maps.MapTypeId.ROADMAP
                    };

                    var map = new google.maps.Map(mapCanvas, mapOptions);

                    var marker = new google.maps.Marker({
                        position: myCenter
                    });

                    marker.setMap(map);
                }
            </script>

        </div>

        <%@include file="../Footer/footer.jsp"%>

    </body>
</html>
