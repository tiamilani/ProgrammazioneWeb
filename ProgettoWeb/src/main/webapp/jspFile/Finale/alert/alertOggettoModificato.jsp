<c:choose>
    <c:when test="${oggettoModificato == 0}">
        <div class="alert alert-success alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Successo!</strong> modifica del catalogo terminata con successo.
        </div>
    </c:when>
    <c:when test="${oggettoModificato == 1}">
        <div class="alert alert-danger alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Attenzione!</strong> modifica al catalogo non apportata a causa di un errore.
        </div>
    </c:when>
    <c:when test="${oggettoModificato == 2}">
        <div class="alert alert-warning alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Attenzione!</strong> Devi prima selezionare un oggetto da modificare tra quelli disponibili.
        </div>
    </c:when>
    <c:when test="${oggettoModificato == 3}">
        <div class="alert alert-warning alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Attenzione!</strong> Devi scrivere il nome dell'oggetto he vuoi eliminare nella textbox specifica
        </div>
    </c:when>
    <c:when test="${oggettoModificato == 4}">
        <div class="alert alert-danger alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Errore!</strong> Il nome da te inserito non corrisponde a quello dell'oggetto
        </div>
    </c:when>
    <c:when test="${oggettoModificato == 6}">
        <div class="alert alert-danger alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Errore!</strong> Impossibile eliminare l'oggetto
        </div>
    </c:when>
    <c:when test="${oggettoModificato == 5}">
        <div class="alert alert-success alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Successo!</strong> Operazione di eliminazione portata a termine con successo
        </div>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>