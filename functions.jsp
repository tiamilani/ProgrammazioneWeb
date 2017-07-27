<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="my" uri="functions"%>

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
      <form action="functions.jsp" method="GET">
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
            <td>\${my:inverti(param["foo"])}</td>
            <td>${my:inverti(fn:escapeXml(param["foo"]))}&nbsp;</td>
          </tr>
          <tr>
            <td>\${my:inverti(my:inverti(param["foo"]))}</td>
            <td>${my:inverti(my:inverti(fn:escapeXml(param["foo"])))}&nbsp;</td>
          </tr>
          <tr>
            <td>\${my:contaVocali(param["foo"])}</td>
            <td>${my:contaVocali(fn:escapeXml(param["foo"]))}&nbsp;</td>
          </tr>
          <tr>
            <td>\${my:maiuscolo(param["foo"])}</td>
            <td>${my:maiuscolo(fn:escapeXml(param["foo"]))}&nbsp;</td>
          </tr>
        </table>
      </code>
    </blockquote>
  </body>
</html>
