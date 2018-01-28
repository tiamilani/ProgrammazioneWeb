<c:choose>
    <c:when test="${utenteLoginError == 1}">
        <div class="alert alert-warning alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Attenzione!</strong> Email o password non riconosciuti.
        </div>
    </c:when>
    <c:when test="${utenteLoginError == 2}">
        <div class="alert alert-warning alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Attenzione!</strong> Non hai ancora verificato la tua email, se non hai ricevuto nessuna email dopo la registrazione contatta l'assistenza.
        </div>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>
