<%--
    Document   : oggettiCorrelati
    Created on : 3-ott-2017, 22.32.00
    Author     : andreafadi
--%>
<div class="carouselSpacing">
    <div id="negoziCorrelati" class="carousel slide" data-ride="carousel">
        <c:if test="${listaNegozi.getList().size() > 1}">
            <ol class="carousel-indicators">
                <c:forEach var="i" begin="${0}" end="${listaNegozi.getList().size() -1}" step="2">
                    <c:choose>
                        <c:when test="${i == 0}">
                            <li data-target="#fotoOggetto" data-slide-to="${i}" class="active"></li>
                        </c:when>
                        <c:otherwise>
                            <li data-target="#fotoOggetto" data-slide-to="${i}"></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ol>
        </c:if>
        <div class="carousel-inner">
            <c:forEach var="i" begin="${0}" end="${listaNegozi.getList().size() -1}" step="2">
                <c:choose>
                    <c:when test="${i == 0}">
                        <div class="carousel-item active bg-white" style="height: auto; margin: 0 auto;">
                    </c:when>
                    <c:otherwise>
                        <div class="carousel-item bg-white" style="height: auto; margin: 0 auto;">
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${(listaNegozi.getList().size() % 2) == 0}">
                        <div class="row">
                            <div class="col-6">
                                <c:url value="/UserController" var="storeUrl">
                                    <c:param name="action" value="DescrizioneNegozio" />
                                    <c:param name="idNegozio" value="${listaNegozi.get(i).getId()}" />
                                </c:url>
                                <a href="${storeUrl}" class="card cardSmall" style="box-shadow: none; width: 70%; margin-right: 10%; margin-left: auto;">
                                    <img class="imgCard" src="${listaImmagini.get(i).getSrc()}" alt="IMAGE NOT LOADED" style="width: auto; height: 300px; object-fit: cover;"> <!--add object-fit: contain;-->
                                    <div class="container">
                                        <h4><b><c:out value="${listaNegozi.get(i).getNomeNegozio()}" /></b></h4>
                                        <p><c:out value="${listaNegozi.get(i).getLinkSito()}" /></p>
                                    </div>
                                </a>
                            </div>
                            <div class="col-6">
                                <c:url value="/UserController" var="storeUrl">
                                    <c:param name="action" value="DescrizioneNegozio" />
                                    <c:param name="idNegozio" value="${listaNegozi.get(i+1).getId()}" />
                                </c:url>
                                <a href="${storeUrl}" class="card cardSmall" style="box-shadow: none; width: 70%; margin-right: auto; margin-left: 10%;">
                                    <img class="imgCard" src="${listaImmagini.get(i+1).getSrc()}" alt="IMAGE NOT LOADED" style="width: auto; height: 300px; object-fit: cover;"> <!--add object-fit: contain;-->
                                    <div class="container">
                                        <h4><b><c:out value="${listaNegozi.get(i+1).getNomeNegozio()}" /></b></h4>
                                        <p><c:out value="${listaNegozi.get(i+1).getLinkSito()}" /></p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${i == (listaNegozi.getList().size() - 1)}">
                                <div class="row">
                                    <div class="col-12">
                                        <c:url value="/UserController" var="storeUrl">
                                            <c:param name="action" value="DescrizioneNegozio" />
                                            <c:param name="idNegozio" value="${listaNegozi.get(i).getId()}" />
                                        </c:url>
                                        <a href="${storeUrl}" class="card cardSmall" style="box-shadow: none; width: 70%; margin-right: auto; margin-left: auto;">
                                            <img class="imgCard" src="${listaImmagini.get(i).getSrc()}" alt="IMAGE NOT LOADED" style="width: auto; height: 300px; object-fit: cover;"> <!--add object-fit: contain;-->
                                            <div class="container">
                                                <h4><b><c:out value="${listaNegozi.get(i).getNomeNegozio()}" /></b></h4>
                                                <p><c:out value="${listaNegozi.get(i).getLinkSito()}" /></p>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="row">
                                    <div class="col-6">
                                        <c:url value="/UserController" var="storeUrl">
                                            <c:param name="action" value="DescrizioneNegozio" />
                                            <c:param name="idNegozio" value="${listaNegozi.get(i).getId()}" />
                                        </c:url>
                                        <a href="${storeUrl}" class="card cardSmall" style="box-shadow: none; width: 70%; margin-right: 10%; margin-left: auto;">
                                            <img class="imgCard" src="${listaImmagini.get(i).getSrc()}" alt="IMAGE NOT LOADED" style="width: auto; height: 300px; object-fit: cover;"> <!--add object-fit: contain;-->
                                            <div class="container">
                                                <h4><b><c:out value="${listaNegozi.get(i).getNomeNegozio()}" /></b></h4>
                                                <p><c:out value="${listaNegozi.get(i).getLinkSito()}" /></p>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="col-6">
                                        <c:url value="/UserController" var="storeUrl">
                                            <c:param name="action" value="DescrizioneNegozio" />
                                            <c:param name="idNegozio" value="${listaNegozi.get(i+1).getId()}" />
                                        </c:url>
                                        <a href="${storeUrl}" class="card cardSmall" style="box-shadow: none; width: 70%; margin-right: auto; margin-left: 10%;">
                                            <img class="imgCard" src="${listaImmagini.get(i+1).getSrc()}" alt="IMAGE NOT LOADED" style="width: auto; height: 300px; object-fit: cover;"> <!--add object-fit: contain;-->
                                            <div class="container">
                                                <h4><b><c:out value="${listaNegozi.get(i+1).getNomeNegozio()}" /></b></h4>
                                                <p><c:out value="${listaNegozi.get(i+1).getLinkSito()}" /></p>
                                            </div>
                                        </a>
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
        <c:if test="${listaNegozi.getList().size() > 1}">
            <a class="carousel-control-prev" href="#negoziCorrelati" role="button" data-slide="prev">
                <i class="fa fa-chevron-left" aria-hidden="true" style="color: rgb(225, 100, 50); font-size: 200%;"></i>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#negoziCorrelati" role="button" data-slide="next">
                <i class="fa fa-chevron-right" aria-hidden="true" style="color: rgb(225, 100, 50); font-size: 200%;"></i>
                <span class="sr-only">Next</span>
            </a>
        </c:if>
    </div>
</div>