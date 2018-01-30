<c:choose>
    <c:when test="${changedPassword == 0}">
        <div class="alert alert-danger alert-dismissable ">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Errore!</strong> Il link per la modifica della password è scaduto. Richiedine un altro!
        </div>
    </c:when>
    <c:when test="${changedPassword == 1}">
        <div class="alert alert-success alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            La tua password è stata <strong>reimpostata</strong> come richiesto.
        </div>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>
