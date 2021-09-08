<%@ page import="model.Result" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%--is needed for foreach tag (https://www.educba.com/jsp-foreach/), btw jstl is needed too--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="results" scope="session" class="model.Results"/>
<%--<c:forEach var="entry" items="${results.entries}">--%>
<%--&lt;%&ndash;    <circle r="5" cx=${(entry.x * 2) / entry.r * 50 + 300 / 2} cy=${300 / 2 - (entry.y * 2) / entry.r * 50} id="pointer" fill=${entry.result ? "#00796B" : "#d32f2f"}></circle>&ndash;%&gt;--%>
<%--</c:forEach>--%>
<% for (Result result: results.getEntries()
) {
    out.print(String.format("<circle r=\"5\" cx=%f cy=%f" +
            " id=\"pointer\" fill=%s></circle>", (result.x * 2) / result.r * 50 + 300 / 2,
            300 / 2 - (result.y * 2) / result.r * 50, result.result ? "#d32f2f" : "#00796B"
            ));
} %>