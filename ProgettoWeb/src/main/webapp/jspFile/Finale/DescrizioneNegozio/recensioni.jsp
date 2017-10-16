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
        <c:forEach var="i" begin="${0}" end="${recensioniNegozi.getL().size() -1}" step="3">
            <c:choose>
                <c:when test="${((recensioniNegozi.getL().size() % 3) == 0) or (i == (recensioniNegozi.getL().size() - 1)) or (i == (recensioniNegozi.getL().size() - 2))}">
                    <c:if test="${(recensioniNegozi.getL().size() % 3) == 0}">
                        <div id="page-content${numPage}" class="page-content" style="display: none;">
                            <c:set var="numPage" value="${numPage+1}"/>
                            <div class="jumbotron" style="padding: 1rem 1rem">
                                <a href="#review${i}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioniNegozi.getR().get(i).getCognome()} ${recensioniNegozi.getR().get(i).getNome()}</h1></a>
                                <div id="review${i}" class="collapse">
                                    <p class="lead">Data: ${recensioniNegozi.getL().get(i).getData()}</p>
                                    <hr class="my-2">
                                    <p class="lead">Valutazione: ${recensioniNegozi.getL().get(i).getValutazione()} / 5</p>
                                    <hr class="my-2">
                                    <p class="lead">${recensioniNegozi.getL().get(i).getTesto()}</p>
                                </div>
                            </div>
                            <div class="jumbotron" style="padding: 1rem 1rem">
                                <a href="#review${i+1}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioniNegozi.getR().get(i+1).getCognome()} ${recensioniNegozi.getR().get(i+1).getNome()}</h1></a>
                                <div id="review${i+1}" class="collapse">
                                    <p class="lead">Data: ${recensioniNegozi.getL().get(i+1).getData()}</p>
                                    <hr class="my-2">
                                    <p class="lead">Valutazione: ${recensioniNegozi.getL().get(i+1).getValutazione()} / 5</p>
                                    <hr class="my-2">
                                    <p class="lead">${recensioniNegozi.getL().get(i+1).getTesto()}</p>
                                </div>
                            </div>
                            <div class="jumbotron" style="padding: 1rem 1rem">
                                <a href="#review${i+2}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioniNegozi.getR().get(i+2).getCognome()} ${recensioniNegozi.getR().get(i+2).getNome()}</h1></a>
                                <div id="review${i+2}" class="collapse">
                                    <p class="lead">Data: ${recensioniNegozi.getL().get(i+2).getData()}</p>
                                    <hr class="my-2">
                                    <p class="lead">Valutazione: ${recensioniNegozi.getL().get(i+2).getValutazione()} / 5</p>
                                    <hr class="my-2">
                                    <p class="lead">${recensioniNegozi.getL().get(i+2).getTesto()}</p>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${i == (recensioniNegozi.getL().size() - 1)}">
                        <div id="page-content${numPage}" class="page-content" style="display: none;">
                            <c:set var="numPage" value="${numPage+1}"/>
                            <div class="jumbotron" style="padding: 1rem 1rem">
                                <a href="#review${i}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioniNegozi.getR().get(i).getCognome()} ${recensioniNegozi.getR().get(i).getNome()}</h1></a>
                                <div id="review${i}" class="collapse">
                                    <p class="lead">Data: ${recensioniNegozi.getL().get(i).getData()}</p>
                                    <hr class="my-2">
                                    <p class="lead">Valutazione: ${recensioniNegozi.getL().get(i).getValutazione()} / 5</p>
                                    <hr class="my-2">
                                    <p class="lead">${recensioniNegozi.getL().get(i).getTesto()}</p>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${i == (recensioniNegozi.getL().size() - 2)}">
                        <div id="page-content${numPage}" class="page-content" style="display: none;">
                            <c:set var="numPage" value="${numPage+1}"/>
                            <div class="jumbotron" style="padding: 1rem 1rem">
                                <a href="#review${i}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioniNegozi.getR().get(i).getCognome()} ${recensioniNegozi.getR().get(i).getNome()}</h1></a>
                                <div id="review${i}" class="collapse">
                                    <p class="lead">Data: ${recensioniNegozi.getL().get(i).getData()}</p>
                                    <hr class="my-2">
                                    <p class="lead">Valutazione: ${recensioniNegozi.getL().get(i).getValutazione()} / 5</p>
                                    <hr class="my-2">
                                    <p class="lead">${recensioniNegozi.getL().get(i).getTesto()}</p>
                                </div>
                            </div>
                            <div class="jumbotron" style="padding: 1rem 1rem">
                                <a href="#review${i+1}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioniNegozi.getR().get(i+1).getCognome()} ${recensioniNegozi.getR().get(i+1).getNome()}</h1></a>
                                <div id="review${i+1}" class="collapse">
                                    <p class="lead">Data: ${recensioniNegozi.getL().get(i+1).getData()}</p>
                                    <hr class="my-2">
                                    <p class="lead">Valutazione: ${recensioniNegozi.getL().get(i+1).getValutazione()} / 5</p>
                                    <hr class="my-2">
                                    <p class="lead">${recensioniNegozi.getL().get(i+1).getTesto()}</p>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <div id="page-content${numPage}" class="page-content" style="display: none;">
                        <c:set var="numPage" value="${numPage+1}"/>
                        <div class="jumbotron" style="padding: 1rem 1rem">
                            <a href="#review${i}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioniNegozi.getR().get(i).getCognome()} ${recensioniNegozi.getR().get(i).getNome()}</h1></a>
                            <div id="review${i}" class="collapse">
                                <p class="lead">Data: ${recensioniNegozi.getL().get(i).getData()}</p>
                                <hr class="my-2">
                                <p class="lead">Valutazione: ${recensioniNegozi.getL().get(i).getValutazione()} / 5</p>
                                <hr class="my-2">
                                <p class="lead">${recensioniNegozi.getL().get(i).getTesto()}</p>
                            </div>
                        </div>
                        <div class="jumbotron" style="padding: 1rem 1rem">
                            <a href="#review${i+1}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioniNegozi.getR().get(i+1).getCognome()} ${recensioniNegozi.getR().get(i+1).getNome()}</h1></a>
                            <div id="review${i+1}" class="collapse">
                                <p class="lead">Data: ${recensioniNegozi.getL().get(i+1).getData()}</p>
                                <hr class="my-2">
                                <p class="lead">Valutazione: ${recensioniNegozi.getL().get(i+1).getValutazione()} / 5</p>
                                <hr class="my-2">
                                <p class="lead">${recensioniNegozi.getL().get(i+1).getTesto()}</p>
                            </div>
                        </div>
                        <div class="jumbotron" style="padding: 1rem 1rem">
                            <a href="#review${i+2}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioniNegozi.getR().get(i+2).getCognome()} ${recensioniNegozi.getR().get(i+2).getNome()}</h1></a>
                            <div id="review${i+2}" class="collapse">
                                <p class="lead">Data: ${recensioniNegozi.getL().get(i+2).getData()}</p>
                                <hr class="my-2">
                                <p class="lead">Valutazione: ${recensioniNegozi.getL().get(i+2).getValutazione()} / 5</p>
                                <hr class="my-2">
                                <p class="lead">${recensioniNegozi.getL().get(i+2).getTesto()}</p>
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

.jumbotron {
    margin: 0;
    margin-bottom: 5%;
}
</style>

<script>
    $('#pagination-demo').twbsPagination({
        totalPages: Math.ceil(${recensioniNegozi.getL().size() / 3}),
        visiblePages: 5,
        next: 'Next',
        prev: 'Prev',
        onPageClick: function (event, page) {
            $('#page-content').html($('#page-content' + page).html());
        }
    });
</script>