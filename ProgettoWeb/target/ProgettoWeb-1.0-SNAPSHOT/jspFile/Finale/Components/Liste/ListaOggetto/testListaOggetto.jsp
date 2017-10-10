<%-- 
    Document   : TestaListaOggetto
    Created on : 26-set-2017, 21.13.33
    Author     : mattia
--%>

<c:set var ="iterator" scope="page" value="${0}"/>
<c:forEach items="${ListaOggetti.getList()}" var="ogg">
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
