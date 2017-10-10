<%-- 
    Document   : recensioni
    Created on : 5-ott-2017, 17.48.54
    Author     : andreafadi
--%>

<c:forEach items="${recensioni.getList()}" var="ogg">
    <div class="jumbotron" style="padding: 1rem 1rem">
        <a href="#review" data-toggle="collapse" id="collapReview"><h1 class="h1-responsive">${ogg.getData()}</h1></a>
        <div id="review" class="collapse">
            <p class="lead">Valutazione: ${ogg.getValutazione()} / 5</p>
            <hr class="my-2">
            <p>${ogg.getTesto()}</p>
        </div>
    </div>
</c:forEach>
