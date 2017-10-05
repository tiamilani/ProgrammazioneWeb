<%-- 
    Document   : oggettiCorrelati
    Created on : 3-ott-2017, 22.32.00
    Author     : andreafadi
--%>
<div class="carouselSpacing">
    <div id="fotoOggetto" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <c:set var="i" scope="page" value="${0}"/>
            <c:forEach items="${listaImmagini.getList()}" var="img">
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
            <c:set var="i" scope="page" value="${0}"/>
            <c:forEach items="${listaImmagini.getList()}" var="img">
                <c:choose>
                    <c:when test="${i == 0}">
                        <div class="carousel-item active">
                    </c:when>
                    <c:otherwise>
                        <div class="carousel-item">
                    </c:otherwise>
                </c:choose>
                            <img class="d-block w-100" src="${img.getSrc()}" alt="http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/square.png" style="width: auto; height: auto; object-fit: contain;">
                        </div>
                <c:set var="i" value="${i+1}"/>
            </c:forEach>
        </div>
        <a class="carousel-control-prev" href="#fotoOggetto" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#fotoOggetto" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>
