<%-- 
    Document   : assistenza
    Created on : 9-ott-2017, 18.16.23
    Author     : damiano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <link type="stylesheet" href="assistenza.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ShopEro: Assistenza</title>
        
        <script type="text/javascript">
            function personalForm(){
                if($('.personal').is(':checked')){
                    $('.personal-info').show();
                    if($('.product').is(':checked')){
                        $('.product').prop('checked', false);
                        productForm();
                    }  
                }
                else
                    $('.personal-info').hide();
            }
            
            function productForm(){
                if($('.product').is(':checked')){
                    $('.product-info').show();
                    if($('.personal').is(':checked')){
                        $('.personal').prop('checked', false);
                        personalForm();
                    }    
                }
                else
                    $('.product-info').hide();
            }
        </script>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="../Header/NavBar/navBar.jsp" %>

            <h4>Benvenuto nella pagina di assistenza</h4>
            <br>
            <h6>Cosa puoi fare in questa pagina:</h6>
            <p>In questa pagina potrai effettuare tutte le richieste di assistenza.<br>
               Inizialmente dovrai selezionare la tipologia di assistenza che devi richiedere. In base al valore da te inserito
               sapremo che informazioni sono necessarie per poter procedere alla creazione della tua domanda di assistenza.
            </p>
                
            <form class="form">
                <input type="checkbox" name="group" class="personal" onchange="personalForm()">Assistenza profilo personale<br>
                <input type="checkbox" name="group" class="product" onchange="productForm()">Assistenza su un prodotto<br>
            </form>
            
            <div class="personal-info" type="hidden" style="display: none">
                <h4>Assistenza profilo personale</h4>
                <p>
                    Per poter procedere con la richiesta di assistenza personale è necessario completare il form seguente,
                    specificando in modo dettagliato il problema per il quale si richiede assistenza.
                </p>
            </div>
            <div class="product-info" type="hidden" style="display: none">
                <h4>Assistenza su un prodotto</h4>
                <p>
                    Per poter procedere con la richiesta di assistenza riguardante un prodotto è necessario completare il form seguente,
                    specificando in modo dettagliato il prodotto per il quale si richiede assistenza e indicando i motivi principali
                    della richiesta.
                </p>
            </div>
            
        </div>
    </body>
</html>
