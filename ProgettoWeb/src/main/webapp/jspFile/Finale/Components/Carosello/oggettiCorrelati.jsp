<%-- 
    Document   : oggettiCorrelati
    Created on : 3-ott-2017, 22.32.00
    Author     : andreafadi
--%>
<div class="carouselSpacing">
    <div id="oggettiCorrelati" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <c:set var="i" scope="page" value="${0}"/>
            <c:forEach items="${randomOggetti.getList()}" var="ogg">
                <c:choose>
                    <c:when test="${i == 0}">
                        <li data-target="#oggettiCorrelati" data-slide-to="${i}" class="active"></li>
                    </c:when>
                    <c:otherwise>
                        <li data-target="#oggettiCorrelati" data-slide-to="${i}"></li>
                    </c:otherwise>
                </c:choose>
                <c:set var="i" value="${i+1}"/>
            </c:forEach>
        </ol>
        <div class="carousel-inner">
            <c:set var="i" scope="page" value="${0}"/>
            <c:forEach items="${randomOggetti.getList()}" var="ogg">
                <c:choose>
                    <c:when test="${i == 0}">
                        <div class="carousel-item active bg-white">
                    </c:when>
                    <c:otherwise>
                        <div class="carousel-item bg-white">
                    </c:otherwise>
                </c:choose>
                            <div class="row">
                                <div class="col-6">
                                    <%@include file="../Oggetto/oggetto.jsp" %>
                                </div>
                                <div class="col-6">
                                    <%@include file="../Oggetto/oggetto.jsp" %>
                                </div>
                            </div>
                        </div>
                <c:set var="i" value="${i+1}"/>
            </c:forEach>
        </div>
        <a class="carousel-control-prev" href="#oggettiCorrelati" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#oggettiCorrelati" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>
