<c:choose>
    <c:when test="${accountVerificato == 0}">
        <div class="alert alert-success alert-dismissable ">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Complimenti!</strong> Ora hai verificato il tuo account
        </div>
    </c:when>
    <c:when test="${accountVerificato == 1}">
        <div class="alert alert-danger alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Errore!</strong> Account non verificato
        </div>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>
