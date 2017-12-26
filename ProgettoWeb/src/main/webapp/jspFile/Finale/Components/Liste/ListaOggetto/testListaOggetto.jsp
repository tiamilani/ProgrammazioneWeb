<%-- 
    Document   : TestaListaOggetto
    Created on : 26-set-2017, 21.13.33
    Author     : mattia
--%>

<c:set var ="iterator" scope="page" value="${0}"/>
<c:forEach var="i" begin="${0}" end="${ListaOggetti.getList().size()-1}" step="1">
    <c:if test ="${(limitColum == 1 && i!=1) || i==0}">
        <div class="card-group">
    </c:if>
    <%@include file="../../Oggetto/oggetto.jsp" %>
    <c:set var ="iterator" scope="page" value="${iterator + 1}"/>
    <c:if test ="${limitColum == 0 && i>0}">
        <c:set var ="iterator" scope="page" value="${0}"/>
        </div>
    </c:if>
</c:forEach>
<c:if test ="${iterator>0}">
    </div>
</c:if>