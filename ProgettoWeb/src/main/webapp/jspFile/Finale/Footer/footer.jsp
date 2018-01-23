<%--
    Document   : footer
    Created on : 26-set-2017, 21.10.19
    Author     : mattia
--%>

<div class="container-fluid" id="footer-container">
    <footer class="footer footer-fixed-bottom bg-light" id='footer'>
        <center>
                <div class="col-9 row no-gutters footer-head">
                    <div class="footer-head col-4">Vieni a conoscerci</div>
                    <div class="footer-head col-4">Diventa venditore</div>
                    <div class="footer-head col-4">Privacy e Condizioni</div>
                </div>
               <div class="col-9 row no-gutters">
                    <div class="col-4"> <a href="http://localhost:8080/ProgettoWeb/jspFile/Finale/Footer/sviluppatori.jsp">Chi siamo</a> </div>
                    <div class="col-4"> link a pagina venditore </div>
                    <div class="col-4"> <a href="http://localhost:8080/ProgettoWeb/jspFile/Finale/Policy/Privacy/privacyPolicy.jsp">Privacy policies</a> </div>
               </div>
                <div class="col-9 row no-gutters">
                    <div class="col-4"> <a href="http://localhost:8080/ProgettoWeb/jspFile/Finale/Footer/sviluppatori.jsp#findUs">Dove siamo</a>  </div>
                    <div class="col-4"> </div>
                    <div class="col-4"> <a href="http://localhost:8080/ProgettoWeb/jspFile/Finale/Policy/Regolamento/condizioniGeneraliDiVendita.jsp">Condizioni Generali di Vendita</a> </div>
                </div>
            <div class='col-11' id='footer-copyright'>
                &COPY; 2017 ShopHero
            </div>
        </center>
    </footer>
</div>

<script>
    $(document).ready(function(){
        var altezza = $('body').height();
        var schermo = $(window).height();

        if (altezza >= schermo){
            $('#footer').css({position: "relative"});
        }else{
            $('#footer').css({position: "absolute"});
        }
    });
</script>
