<%@ page import="java.util.List" %>
<%@ page import="model.Result" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<html lang="ru">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table>
    <tr>
        <th scope="col">X</th>
        <th scope="col">Y</th>
        <th scope="col">R</th>
        <th scope="col">Результат</th>
        <th scope="col">Время</th>
        <th scope="col">Время работы скрипта</th>
    </tr>
    <tbody id="answerValues">
        <jsp:useBean id="results" scope="session" class="model.Results"/>
        <% for (Result result: results.getEntries()
                ) {
            out.print("<tr class=\"table-row\">");
            out.print("<td>" + result.getX() + "</td>");
            out.print("<td>" + result.getY() + "</td>");
            out.print("<td>" + result.getR() + "</td>");
            out.print("<td>" + (result.isInside() ? "<div style=\"color: green\">Внутри</div>" :
                    "<div style=\"color: red\">Снаружи</div>"+ "</td>"));
            out.print("<td>" + results.getSimpleDateFormat().format(result.getQueryTime()) + "</td>");
            out.print("<td>" + result.getResultTime() + "</td>");
            out.print("</tr>");
                } %>
        <%--        <c:forEach var="entry" items="${results.entries}">--%>
<%--            <tr class="table-row">--%>
<%--                <td>${entry.x}</td>--%>
<%--                <td>${entry.y}</td>--%>
<%--                <td>${entry.r}</td>--%>
<%--                <td>${results.simpleDateFormat.format(entry.queryTime)}</td>--%>
<%--                <td>${entry.result ? "<div style=\"color: green\">Внутри</div>" :--%>
<%--                        "<div style=\"color: red\">Снаружи</div>"}</td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
    </tbody>
</table>