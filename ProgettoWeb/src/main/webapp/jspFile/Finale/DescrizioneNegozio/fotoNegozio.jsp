<%-- 
    Document   : fotoNegozio
    Created on : 28-gen-2018, 11.03.14
    Author     : andreafadi
--%>

<div class="carouselSpacing">
    <div id="fotoNegozio" class="carousel slide" data-ride="carousel">
        <c:if test="${listaImmagini.getList().size() > 1}">
            <ol class="carousel-indicators">
                <c:forEach var="i" begin="${0}" end="${listaImmagini.getList().size() -1}" step="1">
                    <c:choose>
                        <c:when test="${i == 0}">
                            <li data-target="#fotoNegozio" data-slide-to="${i}" class="active"></li>
                        </c:when>
                        <c:otherwise>
                            <li data-target="#fotoNegozio" data-slide-to="${i}"></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ol>
        </c:if>
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
                            <img class="d-block w-100" src="${img.getSrc()}" alt="IMAGE NOT LOADED" style="width: auto; height: auto; object-fit: contain;">
                        </div>
                <c:set var="i" value="${i+1}"/>
            </c:forEach>
        </div>
        <c:if test="${listaImmagini.getList().size() > 1}">
            <a class="carousel-control-prev" href="#fotoNegozio" role="button" data-slide="prev">
                <i class="fa fa-chevron-left" aria-hidden="true" style="color: rgb(225, 100, 50); font-size: 200%;"></i>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#fotoNegozio" role="button" data-slide="next">
                <i class="fa fa-chevron-right" aria-hidden="true" style="color: rgb(225, 100, 50); font-size: 200%;"></i>
                <span class="sr-only">Next</span>
            </a>
        </c:if>
    </div>
</div>
