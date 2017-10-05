<%-- 
    Document   : TestaListaOggetto
    Created on : 26-set-2017, 21.13.33
    Author     : mattia
--%>

<div class="row rowListaOggetto">
    <h2>Oggetti che potrebbero piacerti</h2>
</div>

<c:set var ="iterator" scope="page" value="${0}"/>
<c:forEach items="${LsitaOggetti.getList()}" var="ogg">
    <c:if test ="${iterator == 0}">
        <div class="card-group">
    </c:if>
    <%@include file="../../Oggetto/oggetto.jsp" %>
    <c:set var ="iterator" value="${iterator + 1}"/>
    <c:if test ="${iterator == 3}">
        <c:set var ="iterator" value="${0}"/>
        </div>
    </c:if>
</c:forEach>
<c:if test ="${iterator < 3}">
    </div>
</c:if>
<%--
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
</div>--%>
