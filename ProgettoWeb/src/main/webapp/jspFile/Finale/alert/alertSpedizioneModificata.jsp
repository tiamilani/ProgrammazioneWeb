<c:choose>
    <c:when test="${spedizioneModificata == 0}">
        <div class="alert alert-success alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Successo!</strong> Operazione di modifca della spedizione portata a termine con successo.
        </div>
    </c:when>
    <c:when test="${spedizioneModificata == 1}">
        <div class="alert alert-warning alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Attenzione!</strong> Devi scrivere il nome della spedizione che vuoi eliminare nella textbox specifica.
        </div>
    </c:when>
    <c:when test="${spedizioneModificata == 2}">
        <div class="alert alert-danger alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Errore!</strong> Il nome da te inserito non corrisponde a quello della spedizione.
        </div>
    </c:when>
    <c:when test="${spedizioneModificata == 3}">
        <div class="alert alert-success alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Successo!</strong> Operazione di eliminazione portata a termine con successo.
        </div>
    </c:when>
    <c:when test="${spedizioneModificata == 4}">
        <div class="alert alert-warning alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Attenzione!</strong> Impossibile modificare la spedizione a causa di un errore.
        </div>
    </c:when>
    <c:when test="${spedizioneModificata == 5}">
        <div class="alert alert-warning alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Attenzione!</strong> Impossibile eliminare il metodo di spedizione, devi sempre avere almeno 1 metodo di spedizione.
        </div>
    </c:when>
    <c:when test="${spedizioneModificata == 6}">
        <div class="alert alert-warning alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Attenzione!</strong> Impossibile eliminare il metodo di spedizione, Hai oggetti con questo metodo di spedizione assegnato.
        </div>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>