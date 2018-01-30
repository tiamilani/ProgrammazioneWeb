<%-- 
    Document   : TestaListaOggetto
    Created on : 26-set-2017, 21.13.33
    Author     : mattia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="iterator" scope="page" value="${0}" />
<c:if test="${listaOggetti.getList().size() > 0}">
    <div class="card-deck row no-gutters">
        <c:forEach var="i" begin="${0}" end="${listaOggetti.getList().size()-1}" step="${1}">
            <!--c:forEach var="j" begin="${0}" end="${limitColum - 1}" step="1"-->
                <c:choose>
                    <c:when test="${iterator < listaOggetti.getList().size()}">
                        <%@include file="../../Oggetto/oggetto.jsp" %>
                    </c:when>
                    <c:otherwise>
                        <div class="card">    
                            <div class="card-block">
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
                <c:set var ="iterator" scope="page" value="${iterator + 1}"/>
            <!--/c:forEach-->
        </c:forEach>
    </div>
</c:if>



<%--
<c:set var ="iterator" scope="page" value="${0}"/>
<c:forEach var="i" begin="${0}" end="${listaOggetti.getList().size()-1}" step="1">
    <c:if test ="${(i%limitColum == 1 && i != 1) || i == 0}">
        <div class="card-deck">
    </c:if>
    <%@include file="../../Oggetto/oggetto.jsp" %>
    <c:set var ="iterator" scope="page" value="${iterator + 1}"/>
    <c:if test ="${i%limitColum == 0 && i > 0}">
        <c:set var ="iterator" scope="page" value="${0}"/>
        </div>
    </c:if>
</c:forEach>
<c:if test ="${iterator>=0}">
    <c:forEach var="j" begin="${iterator}" end="${limitColumn}" step="1">
    <div class="card">    
        
        <div class="card-block">
            
        </div>
        <div class="card-footer">
            
        </div> 
    </div>

    </c:forEach>
    </div>
</c:if>

--%>
