<%-- 
    Document   : users
    Created on : 2-set-2017, 17.32.13
    Author     : mattia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="my" uri="/tags/usersQueryTagLib"%>

<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UserdbFunction</title>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css"/>
    <link rel="stylesheet" type="text/css" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css"/>
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="//code.jquery.com/jquery-1.12.4.js"></script>
    </head>
    <body>
        <h1>${my:hello()}&nbsp;</h1>
        
        
        ${my:listaNegoziVenditoreId("7")}&nbsp;
        <br><h1>Results: </h1>
        <sql:query dataSource = "jdbc/Access" var = "result">
            ${my:listaNegoziVenditoreId("7")}
        </sql:query>
    
        <table id="TabellaUtente" class="table" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>Id</th>
				<th>IdVenditore</th>
				<th>NomeNegozio</th>
				<th>Valutazione</th>
				<th>Attivo</th>
                                <th>IdI</th>
				<th>DataApertura</th>
				<th>LinkSito</th>
				<th>OrarioNegozio</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="row" items="${result.rows}">
				<tr>
					<td>${row.id}</td>
					<td>${row.idVenditore}</td>
					<td>${row.nomeNegozio}</td>
					<td>${row.valutazione}</td>
					<td>${row.attivo}</td>
                                        <td>${row.idI}</td>
					<td>${row.dataApertura}</td>
					<td>${row.linkSito}</td>
					<td>${row.orarioNegozio}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
    </body>
</html>
