<c:choose>
    <c:when test="${oggettoInserito == 0}">
        <div class="alert alert-success alert-dismissable">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Successo!</strong> Oggetto inserito con successo.
        </div>
    </c:when>
    <c:when test="${oggettoInserito == 1}">
        <div class="alert alert-danger alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Attenzione!</strong> Oggetto non inserito.
        </div>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>