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
    <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css"/>
	<script type="text/javascript" src="//cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
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
