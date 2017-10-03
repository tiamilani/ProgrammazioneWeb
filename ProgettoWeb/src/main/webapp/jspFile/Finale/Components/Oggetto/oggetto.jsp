<%-- 
    Document   : oggetto
    Created on : 28-set-2017, 16.36.53
    Author     : mattia
--%>

<div class="card cardSmall">
  <img class="imgCard" src="http://localhost:8084/ProgettoWeb/jspFile/Finale/Img/square.png" alt="Avatar" height="100px" width="100px">
  <div class="container">
      <h4><b><c:out value="${ogg.getNome()}" /></b></h4>
    <p>prezzo: <c:out value="${ogg.getPrezzo()}" /></p>
  </div>
</div>
