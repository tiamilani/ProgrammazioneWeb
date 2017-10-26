<%-- 
    Document   : userJsp
    Created on : 07-oct-2017, 16.32.00
    Author     : FBrug
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <title>Add new user</title>
    </head>
    <body>
        <!-- Bean per l'utente e la lista dei suoi indirizzi -->
        <jsp:useBean id="addr" class="it.progettoWeb.java.database.Model.indirizzo.ModelloIndirizzo" scope="request" />
        
                
        <div class="container">
            <%@include file="../Header/NavBar/navBar.jsp" %>
            <hr>
        </div>
            
        <div class="container" style="text-align: center">
            <form method="POST" action='<c:out value="${pageContext.request.contextPath}"/>/IndirizzoController?userId=<c:out value="${userId}"/>' name="frmAddUser">                
                <div class="container" style="text-align: left">
                    
                    <b>Id (Lasciare vuoto per nuovi indirizzi):</b>
                    <span style="padding-left: 2em">
                        <input style="padding-left: 2em" type="text" name="addrid" value="<c:out value="${addr.idI}" />"/>
                    </span><hr>
                    <b>Via</b>
                    <span style="padding-left: 2em">
                        <input style="padding-left: 2em" type="text" name="via" value="<c:out value="${addr.via}" />"/>
                    </span><br/>
                    <b>Numero Civico</b>
                    <span style="padding-left: 2em">
                        <input style="padding-left: 2em" type="text" name="nCivico" value="<c:out value="${addr.nCivico}" />"/>
                    </span><br/>
                    <b>Interno</b>
                    <span style="padding-left: 2em">
                        <input style="padding-left: 2em" type="text" name="interno" value="<c:out value="${addr.interno}" />"/>
                    </span><br/>
                    <b>Citta'</b>
                    <span style="padding-left: 2em">
                        <input style="padding-left: 2em" type="text" name="citta" value="<c:out value="${addr.citta}" />"/>
                    </span><br/>
                    <b>Provincia:</b>
                    <span style="padding-left: 2em">
                        <input style="padding-left: 2em" type="text" name="provincia" value="<c:out value="${addr.provincia}" />"/>
                    </span><br/>
                    <b>Regione:</b>
                    <span style="padding-left: 2em">
                        <input style="padding-left: 2em" type="text" name="regione" value="<c:out value="${addr.regione}" />"/>
                    </span><br/>
                    <b>Stato:</b>
                    <span style="padding-left: 2em">
                        <input style="padding-left: 2em" type="text" name="stato" value="<c:out value="${addr.stato}" />"/>
                    </span><hr>
                        
                    <input type="submit" value="SAVE CHANGES" />
                </div>
            </form>
        </div>
                
        <div class="container">
            <hr>
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>


 <!--
                <div class="row">
    <div class="col-sm-3" style="background-color:lavender;">.col-sm-4<br/><hr>ciao</div>
    <div class="col-sm-1" style="background-color:lavender;">.col-sm-1</div>
    <div class="col-sm-4" style="background-color:lavenderblush;">.col-sm-4</div>
    <div class="col-sm-4" style="background-color:lavender;">.col-sm-4</div>
  </div>
                -->
                
                
<!--
<-jsp:useBean id="user" class="it.progettoWeb.java.database.Model.Utente.ModelloUtente" scope="request" />
        <-jsp:useBean id="listaIndirizzi" class="it.progettoWeb.java.database.Model.indirizzo.ModelloListeIndirizzo" scope="request" />
        
<-c:set var ="iterator" scope="page" value="${0}"/>
                    <-c:forEach items="${listaIndirizzi.getList()}" var="ind">
                        
                        <h4>Indirizzo <-c:out value="${iterator}"/>:</h4>
                        <b>Via</b>
                        <span style="padding-left: 2em">
                            <input style="padding-left: 2em" type="text" name="via" value="<c:out value="${ind.via}" />"/>
                        </span><br/>
                        <b>Numero Civico</b>
                        <span style="padding-left: 2em">
                            <input style="padding-left: 2em" type="text" name="nCivico" value="<c:out value="${ind.nCivico}" />"/>
                        </span><br/>
                        <b>Interno</b>
                        <span style="padding-left: 2em">
                            <input style="padding-left: 2em" type="text" name="interno" value="<c:out value="${ind.interno}" />"/>
                        </span><br/>
                        <b>Citta'</b>
                        <span style="padding-left: 2em">
                            <input style="padding-left: 2em" type="text" name="citta" value="<c:out value="${ind.citta}" />"/>
                        </span><br/>
                        <b>Provincia:</b>
                        <span style="padding-left: 2em">
                            <input style="padding-left: 2em" type="text" name="provincia" value="<c:out value="${ind.provincia}" />"/>
                        </span><br/>
                        <b>Regione:</b>
                        <span style="padding-left: 2em">
                            <input style="padding-left: 2em" type="text" name="regione" value="<c:out value="${ind.regione}" />"/>
                        </span><br/>
                        <b>Stato:</b>
                        <span style="padding-left: 2em">
                            <input style="padding-left: 2em" type="text" name="stato" value="<c:out value="${ind.stato}" />"/>
                        </span><hr>
                        
                        <-c:set var ="iterator" value="${iterator + 1}"/>
                        
                    <-/c:forEach>
-->