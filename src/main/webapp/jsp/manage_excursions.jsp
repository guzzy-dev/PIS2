<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ppro1
  Date: 07-Dec-22
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<jsp:include page="/base?command=initializeExcursionManagementCommand" />--%>
<html>
<head>
    <title>manage excursions</title>
</head>
<body>
<p>Passenger id: "${passenger_id}"</p>
<p>First name: "${first_name}"</p>
<p>Last name: "${last_name}"</p>


<form action="/PIS2_war/base">
    <input type="hidden" name="command" value="addExcursion">
    <input type="hidden" name="passenger_id" value="${passenger_id}">
    <input type = "text" name = "excursion_list" value="${passenger_excursions}"><br/>
    <br/>
    <input type="submit" value="Update excursions">
</form>



<div>

  <table>
    <tr>
      <th>Excursion id</th>
      <th>Title</th>
      <th>price</th>

    </tr>
    <c:forEach var="excursion" items="${excursionList}">
      <tr>
        <td>${excursion.id}</td>
        <td>${excursion.title}</td>
        <td>${excursion.price}</td>
      </tr>
    </c:forEach>
  </table>
</div>
</body>
</html>
