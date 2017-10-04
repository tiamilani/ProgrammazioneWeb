<%-- 
    Document   : TestaListaOggetto
    Created on : 26-set-2017, 21.13.33
    Author     : mattia
--%>

<div class="row rowListaOggetto">
    <h2>Oggetti che potrebbero piacerti</h2>
</div>
<div class="card-group">
    <c:set var = "ogg" scope = "page" value = "${LsitaOggetti.getList().get(1)}"/>
        <%@include file="../../Oggetto/oggetto.jsp" %>
    <c:set var = "ogg" scope = "page" value = "${LsitaOggetti.getList().get(2)}"/>
        <%@include file="../../Oggetto/oggetto.jsp" %>
    <c:set var = "ogg" scope = "page" value = "${LsitaOggetti.getList().get(3)}"/>
        <%@include file="../../Oggetto/oggetto.jsp" %>
</div>
<div class="card-group">
    <c:set var = "ogg" scope = "page" value = "${LsitaOggetti.getList().get(1)}"/>
        <%@include file="../../Oggetto/oggetto.jsp" %>
    <c:set var = "ogg" scope = "page" value = "${LsitaOggetti.getList().get(2)}"/>
        <%@include file="../../Oggetto/oggetto.jsp" %>
    <c:set var = "ogg" scope = "page" value = "${LsitaOggetti.getList().get(3)}"/>
        <%@include file="../../Oggetto/oggetto.jsp" %>
</div>
