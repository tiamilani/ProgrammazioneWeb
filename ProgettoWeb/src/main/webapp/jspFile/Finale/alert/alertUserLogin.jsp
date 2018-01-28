<c:choose>
    <c:when test="${utenteLoginError == 1}">
        <div class="alert alert-warning alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Attenzione!</strong> Email o password non riconosciuti
        </div>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>
