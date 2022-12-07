<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/base?command=initializePassengerRegistration" />
<head>
    <title>Passenger registration</title>
</head>
<body>
<form action="/PIS2_war/base">
    <input type="hidden" name="command" value="registerPassenger">
    <input type = "text" name = "first_name" placeholder="First name"><br/>
    <input type = "text" name = "last_name" placeholder="Last Name"><br/>
    <input type = "text" name = "ship_id" placeholder="Ship id"><br/>
    <br/>
    <input type="submit" value="Register new passenger">
</form>

<div>
    <table>
        <tr>
            <th>Ship id</th>
            <th>Route</th>
            <th>visited ports</th>
            <th>capacity</th>
            <th>duration</th>
        </tr>
        <c:forEach var="ship" items="${shipList}">
            <tr>
                <td>${ship.id}</td>
                <td>${ship.route}</td>
                <td>${ship.portsCount}</td>
                <td>${ship.capacity}</td>
                <td>${ship.duration}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
