<%-- 
    Document   : recensioni
    Created on : 5-ott-2017, 17.48.54
    Author     : andreafadi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${pageContext.request.contextPath}/jspFile/Finale/Pagination/jquery.twbsPagination.js"></script>

<div class="wrapper">
    <div class="container">
        
        <div id="page-content" class="page-content"></div>
        <ul id="pagination-demo" class="pagination-sm"></ul>
        
        <c:set var="numPage" value="${1}"/>
        <c:forEach var="i" begin="${0}" end="${recensioni.getList().size() -1}" step="3">
            <c:choose>
                <c:when test="${((recensioni.getList().size() % 3) == 0) or (i == (recensioni.getList().size() - 1)) or (i == (recensioni.getList().size() - 2))}">
                    <c:if test="${(recensioni.getList().size() % 3) == 0}">
                        <div id="page-content${numPage}" class="page-content" style="display: none;">
                            <c:set var="numPage" value="${numPage+1}"/>
                            <div class="jumbotron" style="padding: 1rem 1rem">
                                <a href="#review${i}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioni.get(i).getData()}</h1></a>
                                <div id="review${i}" class="collapse">
                                    <p class="lead">Valutazione: ${recensioni.get(i).getValutazione()} / 5</p>
                                    <hr class="my-2">
                                    <p>${recensioni.get(i).getTesto()}</p>
                                </div>
                            </div>
                            <div class="jumbotron" style="padding: 1rem 1rem">
                                <a href="#review${i+1}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioni.get(i+1).getData()}</h1></a>
                                <div id="review${i+1}" class="collapse">
                                    <p class="lead">Valutazione: ${recensioni.get(i+1).getValutazione()} / 5</p>
                                    <hr class="my-2">
                                    <p>${recensioni.get(i+1).getTesto()}</p>
                                </div>
                            </div>
                            <div class="jumbotron" style="padding: 1rem 1rem">
                                <a href="#review${i+2}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioni.get(i+2).getData()}</h1></a>
                                <div id="review${i+2}" class="collapse">
                                    <p class="lead">Valutazione: ${recensioni.get(i+2).getValutazione()} / 5</p>
                                    <hr class="my-2">
                                    <p>${recensioni.get(i+2).getTesto()}</p>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${i == (recensioni.getList().size() - 1)}">
                        <div id="page-content${numPage}" class="page-content" style="display: none;">
                            <c:set var="numPage" value="${numPage+1}"/>
                            <div class="jumbotron" style="padding: 1rem 1rem">
                                <a href="#review${i}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioni.get(i).getData()}</h1></a>
                                <div id="review${i}" class="collapse">
                                    <p class="lead">Valutazione: ${recensioni.get(i).getValutazione()} / 5</p>
                                    <hr class="my-2">
                                    <p>${recensioni.get(i).getTesto()}</p>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${i == (recensioni.getList().size() - 2)}">
                        <div id="page-content${numPage}" class="page-content" style="display: none;">
                            <c:set var="numPage" value="${numPage+1}"/>
                            <div class="jumbotron" style="padding: 1rem 1rem">
                                <a href="#review${i}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioni.get(i).getData()}</h1></a>
                                <div id="review${i}" class="collapse">
                                    <p class="lead">Valutazione: ${recensioni.get(i).getValutazione()} / 5</p>
                                    <hr class="my-2">
                                    <p>${recensioni.get(i).getTesto()}</p>
                                </div>
                            </div>
                            <div class="jumbotron" style="padding: 1rem 1rem">
                                <a href="#review${i+1}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioni.get(i+1).getData()}</h1></a>
                                <div id="review${i+1}" class="collapse">
                                    <p class="lead">Valutazione: ${recensioni.get(i+1).getValutazione()} / 5</p>
                                    <hr class="my-2">
                                    <p>${recensioni.get(i+1).getTesto()}</p>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <div id="page-content${numPage}" class="page-content" style="display: none;">
                        <c:set var="numPage" value="${numPage+1}"/>
                        <div class="jumbotron" style="padding: 1rem 1rem">
                            <a href="#review${i}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioni.get(i).getData()}</h1></a>
                            <div id="review${i}" class="collapse">
                                <p class="lead">Valutazione: ${recensioni.get(i).getValutazione()} / 5</p>
                                <hr class="my-2">
                                <p>${recensioni.get(i).getTesto()}</p>
                            </div>
                        </div>
                        <div class="jumbotron" style="padding: 1rem 1rem">
                            <a href="#review${i+1}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioni.get(i+1).getData()}</h1></a>
                            <div id="review${i+1}" class="collapse">
                                <p class="lead">Valutazione: ${recensioni.get(i+1).getValutazione()} / 5</p>
                                <hr class="my-2">
                                <p>${recensioni.get(i+1).getTesto()}</p>
                            </div>
                        </div>
                        <div class="jumbotron" style="padding: 1rem 1rem">
                            <a href="#review${i+2}" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${recensioni.get(i+2).getData()}</h1></a>
                            <div id="review${i+2}" class="collapse">
                                <p class="lead">Valutazione: ${recensioni.get(i+2).getValutazione()} / 5</p>
                                <hr class="my-2">
                                <p>${recensioni.get(i+2).getTesto()}</p>
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
</style>

<script>
    $('#pagination-demo').twbsPagination({
        totalPages: Math.ceil(${recensioni.getList().size() / 3}),
        visiblePages: 5,
        next: 'Next',
        prev: 'Prev',
        onPageClick: function (event, page) {
            $('#page-content').html($('#page-content' + page).html());
        }
    });
</script>