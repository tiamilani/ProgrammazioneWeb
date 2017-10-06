<%--
    Document   : oggettiCorrelati
    Created on : 3-ott-2017, 22.32.00
    Author     : andreafadi
--%>
<div class="carouselSpacing">
    <div id="oggettiCorrelati" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <c:set var="i" scope="page" value="${0}"/>
            <c:forEach items="${randomOggetti.getList()}" var="img">
                <c:choose>
                    <c:when test="${i == 0}">
                        <li data-target="#fotoOggetto" data-slide-to="${i}" class="active"></li>
                    </c:when>
                    <c:otherwise>
                        <li data-target="#fotoOggetto" data-slide-to="${i}"></li>
                    </c:otherwise>
                </c:choose>
                <c:set var="i" value="${i+1}"/>
            </c:forEach>
        </ol>
        <div class="carousel-inner">
            <c:forEach var="i" begin="${0}" end="${randomOggetti.getList().size() -1}" step="2">
                <c:choose>
                    <c:when test="${i == 0}">
                        <div class="carousel-item active bg-white" style="height: auto; margin: 0 auto;">
                    </c:when>
                    <c:otherwise>
                        <div class="carousel-item bg-white" style="height: auto; margin: 0 auto;">
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${(randomOggetti.getList().size() % 2) == 0}">
                        <div class="row">
                            <div class="col-6">
                                <c:set var="ogg" value="${randomOggetti.get(i)}"/>
                                <%@include file="../Oggetto/oggetto.jsp" %>
                                <c:remove var="ogg"/>
                            </div>
                            <div class="col-6">
                                <c:set var="ogg" value="${randomOggetti.get(i+1)}"/>
                                <%@include file="../Oggetto/oggetto.jsp" %>
                                <c:remove var="ogg"/>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${i == (randomOggetti.getList().size() - 1)}">
                                <div class="row">
                                    <div class="col-12">
                                        <c:set var="ogg" value="${randomOggetti.get(i)}"/>
                                        <%@include file="../Oggetto/oggetto.jsp" %>
                                        <c:remove var="ogg"/>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="row">
                                    <div class="col-6">
                                        <c:set var="ogg" value="${randomOggetti.get(i)}"/>
                                        <%@include file="../Oggetto/oggetto.jsp" %>
                                        <c:remove var="ogg"/>
                                    </div>
                                    <div class="col-6">
                                        <c:set var="ogg" value="${randomOggetti.get(i+1)}"/>
                                        <%@include file="../Oggetto/oggetto.jsp" %>
                                        <c:remove var="ogg"/>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
                <c:set var="i" value="${i+2}"/>
            </div>
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