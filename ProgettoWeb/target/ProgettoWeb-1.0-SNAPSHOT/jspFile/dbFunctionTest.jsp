<%-- 
    Document   : dbFunctionTet
    Created on : 1-set-2017, 13.58.47
    Author     : mattia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="my" uri="/tags/mytaglib"%>

<html>
  <head>
    <title>JSP 2.0 Expression Language - Functions</title>
  </head>
  <body>
    <h1>JSP 2.0 Expression Language - Functions</h1>
    <hr>
    An upgrade from the JSTL expression language, the JSP 2.0 EL also
    allows for simple function invocation.  Functions are defined
    by tag libraries and are implemented by a Java programmer as
    static methods.

    <blockquote>
      <u><b>Change Parameter</b></u>
      <form action="dbFunctionTest.jsp" method="GET">
          foo = <input type="text" name="foo" value="${fn:escapeXml(param["foo"])}">
          <input type="submit">
      </form>
      <br>
      <code>
        <table border="1">
          <thead>
            <td><b>EL Expression</b></td>
            <td><b>Result</b></td>
          </thead>
          <tr>
            <td>\${param["foo"]}</td>
            <td>${fn:escapeXml(param["foo"])}&nbsp;</td>
          </tr>
          <tr>
            <td>\${my:reverse(param["foo"])}</td>
            <td>${my:reverse(fn:escapeXml(param["foo"]))}&nbsp;</td>
          </tr>
          <tr>
            <td>\${my:reverse(my:reverse(param["foo"]))}</td>
            <td>${my:reverse(my:reverse(fn:escapeXml(param["foo"])))}&nbsp;</td>
          </tr>
          <tr>
            <td>\${my:countVowels(param["foo"])}</td>
            <td>${my:countVowels(fn:escapeXml(param["foo"]))}&nbsp;</td>
          </tr>
        </table>
      </code>
    </blockquote>
  </body>
</html>
