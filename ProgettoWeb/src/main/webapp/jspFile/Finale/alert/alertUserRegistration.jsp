<c:choose>
    <c:when test="${addUser == 0}">
        <div class="alert alert-success alert-dismissable ">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <h4 class="alert-heading">Complimenti!</h4>
            <p>Ti sei registrato con successo su ShopEro :)</p>
            <p class="mb-0">Per completare la registrazione però devi clickare sul link ti abbiamo mandato per email.</p>
            <p class="mb-0">Se Non hai ricevuto nessuna mail controlla lo spam, o contatta l'assisttenza.</p>
        </div>
    </c:when>
    <c:when test="${addUser == 1}">
        <div class="alert alert-danger alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Errore!</strong> Per poter creare un account devi compilare correttamente il captcha
        </div>
    </c:when>
    <c:when test="${addUser == 2}">
        <div class="alert alert-warning alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Attenzione!</strong> Email già utilizzata da un altro account
        </div>
    </c:when>
    <c:when test="${addUser == 3}">
        <div class="alert alert-warning alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Attenzione!</strong> La password e la conferma della password devono corrispondere
        </div>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>