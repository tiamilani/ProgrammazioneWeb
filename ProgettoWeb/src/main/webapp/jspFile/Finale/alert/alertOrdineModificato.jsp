<c:choose>
    <c:when test="${ordineModificato == 0}">
        <div class="alert alert-success alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Successo!</strong> Operazione di modifca dell'ordine portata a termine con successo
        </div>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>
