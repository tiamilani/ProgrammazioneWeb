<%-- 
    Document   : fixedMenu
    Created on : 3-ott-2017, 22.23.11
    Author     : andreafadi
--%>

<style>
    .shopping-cart {
        margin: 20px 0;
        right: 0;
        left: auto;
        float: right;
        background: white;
        width: 320px;
        position: fixed;
        border-radius: 3px;
        padding: 20px;

        .shopping-cart-items {
            padding-top: 20px;
            
            .item-name-left {
                float:left;
                display: block;
                padding-top: 10px;
                font-size: 16px;
            }
            
            .item-name-right {
                float:right;
                display: block;
                padding-top: 10px;
                font-size: 16px;
            }
        }
    }

    .button {
        background-color: #6394F8;
        color: white;
        text-align: center;
        padding: 12px;
        text-decoration: none;
        display: block;
        border-radius: 3px;
        font-size: 16px;
        margin: 25px 0 15px 0;
    }


    .button:hover {
        background-color: #3b7af7;
        color: white;
    }

    a:hover, a:visited, a:link, a:active {
        text-decoration: none;
    }
</style>

<div class="shopping-cart">
    <div class="shopping-cart-items">
        <div class="row">
            <span class="col-6 item-name-left">Prezzo:</span>
            <span class="col-6 item-name-right">${oggetto.getPrezzo()} Euro</span>
        </div>
        <p class="lead"/>
        <c:if test="${oggetto.getSconto() > 0}">
            <div class="row">
                <span class="col-6 item-name-left">Sconto:</span>
                <span class="col-6 item-name-right">${oggetto.getSconto()}</span>
            </div>
            <div class="row">
                <span class="col-12 text-center">Valido fino al: ${oggetto.getDataFineSconto()}</span>
            </div>
        </c:if>
        <p class="lead"/>
        <div class="row">
            <span class="col-6 item-name-left">Quantità:</span>
            <input type="number" class="item-name-right col-5 form-control" min="1" max="${oggetto.getDisponibilita()}" value="1"/>
        </div>
        <%--<p class="lead"/>
        <div class="row">
            <select class="form-control col-12"/>
            <c:forEach items="${listaTipiSpedizione.getList()}" var="ship">
                <option selected>${ship.}</option>
            </c:forEach>
        </div>--%>
    </div>

    <a href="#" class="button">Acquista</a>
</div>