<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib  prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<jsp:include page="/base?command=init" />
<head>
    <title>Main page</title>
    <link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<body>
<jsp:useBean id="now" class="java.util.Date" />


<div>

        <br/>
        <a href="/PIS2_war/registration">Register new passenger</a>
        <br/>
        <br/>

        <table>
            <tr>
                <th>First name and Last name</th>
            </tr>
            <c:forEach var="passenger" items="${passengersList}">
                <tr>
                    <td>${passenger.firstName} ${passenger.lastName}</td>
                    <td><a href="/PIS2_war/base?command=initializeExcursionManagementCommand&id=${passenger.id}">manage excursions</a></td>
                    <td><a href="/PIS2_war/base?command=deletePassenger&id=${passenger.id}">Delete</a></td>
                </tr>

            </c:forEach>
        </table>


    <br/>

</div>


<%--    <a href="/newhomework?user_id=${user.id}">Add homework</a>--%>
<%--    <div id="homeworksGiven">--%>
<%--        <c:forEach var="homework" items="${homeworkList}">--%>
<%--            <div class="homework">--%>
<%--                <p>${homework.name}</p>--%>
<%--                <p>From ${homework.userID.lastName} ${homework.userID.firstName}</p>--%>
<%--                <p>${homework.description}</p>--%>
<%--                <p><a href="/base?command=openPDF&id=${homework.id}&from=homework">open pdf file</a></p>--%>
<%--                <p><a href="/base?command=showDataInHomeworkForm&homework_id=${homework.id}&name=${homework.name}&description=${homework.description}&deadline=${homework.deadline}&user_id=${homework.userID.id}&group_id=${homework.groupID.id}">Update</a></p>--%>
<%--                <p><a href="/base?command=deleteHomework&id=${homework.id}">Delete</a></p>--%>
<%--            </div>--%>
<%--        </c:forEach>--%>
<%--    </div>--%>

<%--    <div id="allSubmits">--%>
<%--        <c:forEach var="submit" items="${submitList}">--%>
<%--            <c:if test="${submit.grade == -1}">--%>
<%--                <div class="submit">--%>
<%--                    <p>${submit.homework_id.name}</p>--%>
<%--                    <p>From ${submit.user_id.lastName}</p>--%>
<%--                    <p><a href="/base?command=openPDF&id=${submit.id}&from=submit">open pdf homework</a></p>--%>
<%--                    <form action="/base">--%>
<%--                        <input type="hidden" name="command" value="setGrade">--%>
<%--                        <input type="hidden" name="submit_id" value="${submit.id}">--%>
<%--                        <c:forEach var="i" begin="1" end="12">--%>
<%--                            <input type = "radio" name = "grade" id = "${i}" value="${i}"> <label for = "${i}">${i}</label> <br/>--%>
<%--                        </c:forEach>--%>
<%--                        <input type="submit" value="set grade">--%>
<%--                    </form>--%>
<%--                </div>--%>
<%--            </c:if>--%>
<%--        </c:forEach>--%>
<%--    </div>--%>


<%--<c:if test="${user.typo == 'student'}">--%>
<%--    <div id="homeworksGiven">--%>

<%--        <c:forEach var="homework" items="${groupHomeworkList}">--%>
<%--            <fmt:parseDate value="${homework.deadline}" var="parsedExpDate" pattern="yyyy-MM-dd" />--%>
<%--            <div>--%>
<%--                <div class="homework">--%>
<%--                    <p>${homework.name}</p>--%>
<%--                    <p>From ${homework.userID.lastName} ${homework.userID.firstName}</p>--%>
<%--                    <p>${homework.description}</p>--%>
<%--                    <p><a href="/base?command=openPDF&id=${homework.id}&from=homework">open pdf file</a></p>--%>
<%--                    <c:choose>--%>
<%--                        <c:when test="${now.time gt parsedExpDate.time}">--%>
<%--                            <p>Overdue homework</p>--%>
<%--                        </c:when>--%>
<%--                        <c:otherwise>--%>
<%--                            <p>Up to ${homework.deadline}</p>--%>
<%--                        </c:otherwise>--%>
<%--                    </c:choose>--%>
<%--                </div>--%>
<%--                <div class="homework">--%>
<%--                    <c:set var = "did_submit" value="f"/>--%>
<%--                    <c:forEach var="submit" items="${submitList}">--%>
<%--                        <c:if test="${submit.homework_id.id == homework.id}">--%>
<%--                            <c:set var = "did_submit" value="t"/>--%>
<%--                            <c:choose>--%>
<%--                                <c:when test="${submit.grade == '-1'}">--%>
<%--                                    <p>this already submitted</p>--%>
<%--                                </c:when>--%>
<%--                                <c:otherwise>--%>
<%--                                    <p>grade: ${submit.grade}</p>--%>
<%--                                </c:otherwise>--%>
<%--                            </c:choose>--%>
<%--                        </c:if>--%>
<%--                    </c:forEach>--%>
<%--                    <c:if test="${did_submit == \"f\"}">--%>
<%--                        <form method="post" enctype="multipart/form-data" action="/base">--%>
<%--                            <input type="hidden" name="command" value="addSubmit">--%>
<%--                            <input type="hidden" name="homework" value="${homework.id}">--%>
<%--                            <label for="file_upload">Load pdf file here:</label>--%>
<%--                            <input type="file" accept="application/pdf" id="file_upload" name="file_upload">--%>
<%--                            <br/>--%>
<%--                            <input type="submit" name = "s1" value="submit homework">--%>
<%--                        </form>--%>
<%--                    </c:if>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </c:forEach>--%>
<%--    </div>--%>
<%--</c:if>--%>

</body>
</html>