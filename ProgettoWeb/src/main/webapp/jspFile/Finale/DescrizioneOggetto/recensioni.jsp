<%-- 
    Document   : recensioni
    Created on : 5-ott-2017, 17.48.54
    Author     : andreafadi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${pageContext.request.contextPath}/jspFile/Finale/Pagination/jquery.twbsPagination.js"></script>

<div class="wrapper" style="justify-content: center; align-items: center;">
    <div class="container">

        <ul id="pagination-demo" class="pagination-sm" style="display: block;"></ul>
        <div id="page-content" class="page-content" style="background: transparent;"></div>


        <c:set var="numPage" value="${1}"/>
        <c:forEach var="i" begin="${0}" end="${recensioniUtenteImmagini.getL().size() -1}" step="3">
            <c:choose>
                <c:when test="${((recensioniUtenteImmagini.getL().size() % 3) == 0) or (i == (recensioniUtenteImmagini.getL().size() - 1)) or (i == (recensioniUtenteImmagini.getL().size() - 2))}">
                    <c:if test="${(recensioniUtenteImmagini.getL().size() % 3) == 0}">
                        <div id="page-content${numPage}" class="page-content" style="display: none;">
                            <c:set var="numPage" value="${numPage+1}"/>
                            <div class="jumbotron" style="padding: 1rem 1rem">
                                <a href="#review${i}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioniUtenteImmagini.getR().get(i).getCognome()} ${recensioniUtenteImmagini.getR().get(i).getNome()}</h1></a>
                                <div id="review${i}" class="collapse">
                                    <p class="lead">Data: ${recensioniUtenteImmagini.getL().get(i).getData()}</p>
                                    <hr class="my-2">
                                    <p class="lead">Valutazione: ${recensioniUtenteImmagini.getL().get(i).getValutazione()} / 5</p>
                                    <hr class="my-2">
                                    <p class="lead">${recensioniUtenteImmagini.getL().get(i).getTesto()}</p>
                                    <c:if test="${recensioniUtenteImmagini.getC().get(i).getList().size() > 0}">
                                        <div id="carousel">
                                            <c:forEach items="${recensioniUtenteImmagini.getC().get(i).getList()}" var="img">
                                                <div class="slide">
                                                    <img src="${img.getSrc()}" alt="IMAGE NOT LOADED" style="width: 200px; height: 300px; object-fit: cover;">
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </c:if>
                                    <c:if test="${risposteOggetto.getL().size() > 0}">
                                        <div class="row" style="padding-left: 10%;">
                                            <div class="col-2">
                                                <i class="fa fa-chevron-right" aria-hidden="true" style="display: inline-block;"></i>
                                            </div>
                                            <div class="col-8">
                                                <p class="lead">${risposteOggetto.getL().get(0).getTesto()}</p>
                                            </div>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                            <div class="jumbotron" style="padding: 1rem 1rem">
                                <a href="#review${i+1}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioniUtenteImmagini.getR().get(i+1).getCognome()} ${recensioniUtenteImmagini.getR().get(i+1).getNome()}</h1></a>
                                <div id="review${i+1}" class="collapse">
                                    <p class="lead">Data: ${recensioniUtenteImmagini.getL().get(i+1).getData()}</p>
                                    <hr class="my-2">
                                    <p class="lead">Valutazione: ${recensioniUtenteImmagini.getL().get(i+1).getValutazione()} / 5</p>
                                    <hr class="my-2">
                                    <p class="lead">${recensioniUtenteImmagini.getL().get(i+1).getTesto()}</p>
                                    <c:if test="${recensioniUtenteImmagini.getC().get(i+1).getList().size() > 0}">
                                        <div id="carousel">
                                            <c:forEach items="${recensioniUtenteImmagini.getC().get(i+1).getList()}" var="img">
                                                <div class="slide">
                                                    <img src="${img.getSrc()}" alt="IMAGE NOT LOADED" style="width: 200px; height: 300px; object-fit: cover;">
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </c:if>
                                    <c:if test="${risposteOggetto.getL().size() > 0}">
                                        <div class="row" style="padding-left: 10%;">
                                            <div class="col-2">
                                                <i class="fa fa-chevron-right" aria-hidden="true" style="display: inline-block;"></i>
                                            </div>
                                            <div class="col-8">
                                                <p class="lead">${risposteOggetto.getL().get(0).getTesto()}</p>
                                            </div>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                            <div class="jumbotron" style="padding: 1rem 1rem">
                                <a href="#review${i+2}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioniUtenteImmagini.getR().get(i+2).getCognome()} ${recensioniUtenteImmagini.getR().get(i+2).getNome()}</h1></a>
                                <div id="review${i+2}" class="collapse">
                                    <p class="lead">Data: ${recensioniUtenteImmagini.getL().get(i+2).getData()}</p>
                                    <hr class="my-2">
                                    <p class="lead">Valutazione: ${recensioniUtenteImmagini.getL().get(i+2).getValutazione()} / 5</p>
                                    <hr class="my-2">
                                    <p class="lead">${recensioniUtenteImmagini.getL().get(i+2).getTesto()}</p>
                                    <c:if test="${recensioniUtenteImmagini.getC().get(i+2).getList().size() > 0}">
                                        <div id="carousel">
                                            <c:forEach items="${recensioniUtenteImmagini.getC().get(i+2).getList()}" var="img">
                                                <div class="slide">
                                                    <img src="${img.getSrc()}" alt="IMAGE NOT LOADED" style="width: 200px; height: 300px; object-fit: cover;">
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </c:if>
                                    <c:if test="${risposteOggetto.getL().size() > 0}">
                                        <div class="row" style="padding-left: 10%;">
                                            <div class="col-2">
                                                <i class="fa fa-chevron-right" aria-hidden="true" style="display: inline-block;"></i>
                                            </div>
                                            <div class="col-8">
                                                <p class="lead">${risposteOggetto.getL().get(0).getTesto()}</p>
                                            </div>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${i == (recensioniUtenteImmagini.getL().size() - 1)}">
                        <div id="page-content${numPage}" class="page-content" style="display: none;">
                            <c:set var="numPage" value="${numPage+1}"/>
                            <div class="jumbotron" style="padding: 1rem 1rem">
                                <a href="#review${i}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioniUtenteImmagini.getR().get(i).getCognome()} ${recensioniUtenteImmagini.getR().get(i).getNome()}</h1></a>
                                <div id="review${i}" class="collapse">
                                    <p class="lead">Data: ${recensioniUtenteImmagini.getL().get(i).getData()}</p>
                                    <hr class="my-2">
                                    <p class="lead">Valutazione: ${recensioniUtenteImmagini.getL().get(i).getValutazione()} / 5</p>
                                    <hr class="my-2">
                                    <p class="lead">${recensioniUtenteImmagini.getL().get(i).getTesto()}</p>
                                    <c:if test="${recensioniUtenteImmagini.getC().get(i).getList().size() > 0}">
                                        <div id="carousel">
                                            <c:forEach items="${recensioniUtenteImmagini.getC().get(i).getList()}" var="img">
                                                <div class="slide">
                                                    <img src="${img.getSrc()}" alt="IMAGE NOT LOADED" style="width: 200px; height: 300px; object-fit: cover;">
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </c:if>
                                    <c:if test="${risposteOggetto.getL().size() > 0}">
                                        <div class="row" style="padding-left: 10%;">
                                            <div class="col-2">
                                                <i class="fa fa-chevron-right" aria-hidden="true" style="display: inline-block;"></i>
                                            </div>
                                            <div class="col-8">
                                                <p class="lead">${risposteOggetto.getL().get(0).getTesto()}</p>
                                            </div>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${i == (recensioniUtenteImmagini.getL().size() - 2)}">
                        <div id="page-content${numPage}" class="page-content" style="display: none;">
                            <c:set var="numPage" value="${numPage+1}"/>
                            <div class="jumbotron" style="padding: 1rem 1rem">
                                <a href="#review${i}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioniUtenteImmagini.getR().get(i).getCognome()} ${recensioniUtenteImmagini.getR().get(i).getNome()}</h1></a>
                                <div id="review${i}" class="collapse">
                                    <p class="lead">Data: ${recensioniUtenteImmagini.getL().get(i).getData()}</p>
                                    <hr class="my-2">
                                    <p class="lead">Valutazione: ${recensioniUtenteImmagini.getL().get(i).getValutazione()} / 5</p>
                                    <hr class="my-2">
                                    <p class="lead">${recensioniUtenteImmagini.getL().get(i).getTesto()}</p>
                                    <c:if test="${recensioniUtenteImmagini.getC().get(i).getList().size() > 0}">
                                        <div id="carousel">
                                            <c:forEach items="${recensioniUtenteImmagini.getC().get(i).getList()}" var="img">
                                                <div class="slide">
                                                    <img src="${img.getSrc()}" alt="IMAGE NOT LOADED" style="width: 200px; height: 300px; object-fit: cover;">
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </c:if>
                                    <c:if test="${risposteOggetto.getL().size() > 0}">
                                        <div class="row" style="padding-left: 10%;">
                                            <div class="col-2">
                                                <i class="fa fa-chevron-right" aria-hidden="true" style="display: inline-block;"></i>
                                            </div>
                                            <div class="col-8">
                                                <p class="lead">${risposteOggetto.getL().get(0).getTesto()}</p>
                                            </div>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                            <div class="jumbotron" style="padding: 1rem 1rem">
                                <a href="#review${i+1}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioniUtenteImmagini.getR().get(i+1).getCognome()} ${recensioniUtenteImmagini.getR().get(i+1).getNome()}</h1></a>
                                <div id="review${i+1}" class="collapse">
                                    <p class="lead">Data: ${recensioniUtenteImmagini.getL().get(i+1).getData()}</p>
                                    <hr class="my-2">
                                    <p class="lead">Valutazione: ${recensioniUtenteImmagini.getL().get(i+1).getValutazione()} / 5</p>
                                    <hr class="my-2">
                                    <p class="lead">${recensioniUtenteImmagini.getL().get(i+1).getTesto()}</p>
                                    <c:if test="${recensioniUtenteImmagini.getC().get(i+1).getList().size() > 0}">
                                        <div id="carousel">
                                            <c:forEach items="${recensioniUtenteImmagini.getC().get(i+1).getList()}" var="img">
                                                <div class="slide">
                                                    <img src="${img.getSrc()}" alt="IMAGE NOT LOADED" style="width: 200px; height: 300px; object-fit: cover;">
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </c:if>
                                    <c:if test="${risposteOggetto.getL().size() > 0}">
                                        <div class="row" style="padding-left: 10%;">
                                            <div class="col-2">
                                                <i class="fa fa-chevron-right" aria-hidden="true" style="display: inline-block;"></i>
                                            </div>
                                            <div class="col-8">
                                                <p class="lead">${risposteOggetto.getL().get(0).getTesto()}</p>
                                            </div>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <div id="page-content${numPage}" class="page-content" style="display: none;">
                        <c:set var="numPage" value="${numPage+1}"/>
                        <div class="jumbotron" style="padding: 1rem 1rem">
                            <a href="#review${i}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioniUtenteImmagini.getR().get(i).getCognome()} ${recensioniUtenteImmagini.getR().get(i).getNome()}</h1></a>
                            <div id="review${i}" class="collapse">
                                <p class="lead">Data: ${recensioniUtenteImmagini.getL().get(i).getData()}</p>
                                <hr class="my-2">
                                <p class="lead">Valutazione: ${recensioniUtenteImmagini.getL().get(i).getValutazione()} / 5</p>
                                <hr class="my-2">
                                <p class="lead">${recensioniUtenteImmagini.getL().get(i).getTesto()}</p>
                                <c:if test="${recensioniUtenteImmagini.getC().get(i).getList().size() > 0}">
                                    <div id="carousel">
                                        <c:forEach items="${recensioniUtenteImmagini.getC().get(i).getList()}" var="img">
                                            <div class="slide">
                                                <img src="${img.getSrc()}" alt="IMAGE NOT LOADED" style="width: 200px; height: 300px; object-fit: cover;">
                                            </div>
                                        </c:forEach>
                                    </div>
                                </c:if>
                                <c:if test="${risposteOggetto.getL().size() > 0}">
                                    <div class="row" style="padding-left: 10%;">
                                        <div class="col-2">
                                            <i class="fa fa-chevron-right" aria-hidden="true" style="display: inline-block;"></i>
                                        </div>
                                        <div class="col-8">
                                            <p class="lead">${risposteOggetto.getL().get(0).getTesto()}</p>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <div class="jumbotron" style="padding: 1rem 1rem">
                            <a href="#review${i+1}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioniUtenteImmagini.getR().get(i+1).getCognome()} ${recensioniUtenteImmagini.getR().get(i+1).getNome()}</h1></a>
                            <div id="review${i+1}" class="collapse">
                                <p class="lead">Data: ${recensioniUtenteImmagini.getL().get(i+1).getData()}</p>
                                <hr class="my-2">
                                <p class="lead">Valutazione: ${recensioniUtenteImmagini.getL().get(i+1).getValutazione()} / 5</p>
                                <hr class="my-2">
                                <p class="lead">${recensioniUtenteImmagini.getL().get(i+1).getTesto()}</p>
                                <c:if test="${recensioniUtenteImmagini.getC().get(i+1).getList().size() > 0}">
                                    <div id="carousel">
                                        <c:forEach items="${recensioniUtenteImmagini.getC().get(i+1).getList()}" var="img">
                                            <div class="slide">
                                                <img src="${img.getSrc()}" alt="IMAGE NOT LOADED" style="width: 200px; height: 300px; object-fit: cover;">
                                            </div>
                                        </c:forEach>
                                    </div>
                                </c:if>
                                <c:if test="${risposteOggetto.getL().size() > 0}">
                                    <div class="row" style="padding-left: 10%;">
                                        <div class="col-2">
                                            <i class="fa fa-chevron-right" aria-hidden="true" style="display: inline-block;"></i>
                                        </div>
                                        <div class="col-8">
                                            <p class="lead">${risposteOggetto.getL().get(0).getTesto()}</p>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <div class="jumbotron" style="padding: 1rem 1rem">
                            <a href="#review${i+2}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioniUtenteImmagini.getR().get(i+2).getCognome()} ${recensioniUtenteImmagini.getR().get(i+2).getNome()}</h1></a>
                            <div id="review${i+2}" class="collapse">
                                <p class="lead">Data: ${recensioniUtenteImmagini.getL().get(i+2).getData()}</p>
                                <hr class="my-2">
                                <p class="lead">Valutazione: ${recensioniUtenteImmagini.getL().get(i+2).getValutazione()} / 5</p>
                                <hr class="my-2">
                                <p class="lead">${recensioniUtenteImmagini.getL().get(i+2).getTesto()}</p>
                                <c:if test="${recensioniUtenteImmagini.getC().get(i+2).getList().size() > 0}">
                                    <div id="carousel">
                                        <c:forEach items="${recensioniUtenteImmagini.getC().get(i+2).getList()}" var="img">
                                            <div class="slide">
                                                <img src="${img.getSrc()}" alt="IMAGE NOT LOADED" style="width: 200px; height: 300px; object-fit: cover;">
                                            </div>
                                        </c:forEach>
                                    </div>
                                </c:if>
                                <c:if test="${risposteOggetto.getL().size() > 0}">
                                    <div class="row" style="padding-left: 10%;">
                                        <div class="col-2">
                                            <i class="fa fa-chevron-right" aria-hidden="true" style="display: inline-block;"></i>
                                        </div>
                                        <div class="col-8">
                                            <p class="lead">${risposteOggetto.getL().get(0).getTesto()}</p>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
</div>

<style>
    .wrapper{
  margin: 60px auto;
  text-align: center;
}
h1{
  margin-bottom: 1.25em;
}
#pagination-demo{
  display: inline-block;
  margin-bottom: 1.75em;
}
#pagination-demo li{
  display: inline-block;
}

.page-content{
  background: #eee;
  display: inline-block;
  padding: 10px;
  width: 100%;
  max-width: 660px;
}

.card {
    background-color: transparent;
}

.jumbotron {
    margin: 0;
    margin-bottom: 5%;
}

#carousel {
    width: 100%;
    height: 300px;

    overflow-x: auto;
    overflow-y: auto;
    white-space:nowrap;
}

#carousel .slide {
    display: inline-block;
}
</style>

<script>
    $('#pagination-demo').twbsPagination({
        totalPages: Math.ceil(${recensioniUtenteImmagini.getL().size() / 3}),
        visiblePages: 5,
        next: 'Next',
        prev: 'Prev',
        onPageClick: function (event, page) {
            $('#page-content').html($('#page-content' + page).html());
        }
    });
</script>
