<%-- 
    Document   : oggetto
    Created on : 28-set-2017, 16.36.53
    Author     : mattia
--%>
<c:url value="/objectSelectedController" var="objUrl" >
    <c:param name="idOggetto" value="${ListaOggetti.get(i).getId()}" />
</c:url>
<a href="${objUrl}" class="card cardSmall" style="box-shadow: none;">
  <img class="imgCard" src="${listaImmaginiOggetto.get(i).getSrc()}" alt="IMAGE NOT LOADED" style="width: auto; height: 250px; object-fit: cover;"> <!--add object-fit: contain;-->
  <div class="container">
      <h4><b><c:out value="${ListaOggetti.get(i).getNome()}" /></b></h4>
    <p>Prezzo: <c:out value="${ListaOggetti.get(i).getPrezzo()}" /></p>
  </div>
</a>