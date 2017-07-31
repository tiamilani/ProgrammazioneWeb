<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<sql:query var="rs" dataSource="jdbc/TestDB">
select * from Utente
</sql:query>

<html>
  <head>
    <title>DB Test</title>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs/dt-1.10.15/fc-3.2.2/fh-3.1.2/r-2.1.1/datatables.min.css"/>
	<script type="text/javascript" src="https://cdn.datatables.net/v/bs/dt-1.10.15/fc-3.2.2/fh-3.1.2/r-2.1.1/datatables.min.js"></script>
  </head>
  <body>

  <h2>Results</h2>

	<table id="TabellaUtente" class="table">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Cognome</th>
				<th>Mail</th>
				<th>Password</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="row" items="${rs.rows}">
				<tr>
					<td>${row.id}</td>
					<td>${row.nome}</td>
					<td>${row.cognome}</td>
					<td>${row.mail}</td>
					<td>${row.password}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script>
		$(document).ready(function() {
			$("#usersTable").dataTable();
		});
	</script>

  </body>
</html>
