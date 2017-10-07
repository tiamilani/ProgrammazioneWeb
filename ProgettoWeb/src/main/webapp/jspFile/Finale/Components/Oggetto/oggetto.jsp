<%-- 
    Document   : oggetto
    Created on : 28-set-2017, 16.36.53
    Author     : mattia
--%>
<c:url value="/objectSelectedController" var="objUrl" >
    <c:param name="idOggetto" value="${ogg.getId()}" />
</c:url>
<a href="${objUrl}" class="card cardSmall">
  <img class="imgCard" src="http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/square.png" alt="imgCard" height="100px" width="100px">
  <div  style="text-align: center">
        <h4><b><c:out value="${ogg.getNome()}" /></b></h4>
        <p>Prezzo: <c:out value="${ogg.getPrezzo()}" /></p>
  </div>
</a>