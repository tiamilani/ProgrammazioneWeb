<c:choose>
    <c:when test="${aggiungiSepdizione == 0}">
        <div class="alert alert-success alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Successo!</strong> Operazione di modifca della spedizione portata a termine con successo.
        </div>
    </c:when>
    <c:when test="${aggiungiSepdizione == 1}">
        <div class="alert alert-warning alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Attenzione!</strong> Devi scrivere il nome della spedizione che vuoi eliminare nella textbox specifica.
        </div>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>