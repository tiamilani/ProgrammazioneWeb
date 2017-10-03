<%-- 
    Document   : TestaListaOggetto
    Created on : 26-set-2017, 21.13.33
    Author     : mattia
--%>

<div class="row" style="margin-left:25%; margin-right:25%;">
    <h2>Categoria...</h2>
</div>
<div class="card-group">
    <c:set var = "ogg" scope = "page" value = "${LsitaOggetti.getList().get(1)}"/>
        <%@include file="../../Oggetto/oggetto.jsp" %>
    <c:set var = "ogg" scope = "page" value = "${LsitaOggetti.getList().get(2)}"/>
        <%@include file="../../Oggetto/oggetto.jsp" %>
    <c:set var = "ogg" scope = "page" value = "${LsitaOggetti.getList().get(3)}"/>
        <%@include file="../../Oggetto/oggetto.jsp" %>
</div>


        <%@include file="../../Oggetto/longObject.jsp" %>
