<%-- 
    Document   : alertOverQuantity
    Created on : Jan 29, 2018, 11:54:28 AM
    Author     : FBrug
--%>

<c:choose>
    <c:when test="${erroreQuantita == 1}">
        <div class="alert alert-warning alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Attenzione!</strong> Alcuni oggetti nel carrello superano la quantita' massima disponibile.
        </div>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>
