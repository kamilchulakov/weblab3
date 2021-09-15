<%@ page import="model.Result" %>
<%@ page import="static kotlin_meme_jsp.KJSPKt.makePoints" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%--is needed for foreach tag (https://www.educba.com/jsp-foreach/), btw jstl is needed too--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="results" scope="session" class="model.Results"/>
<% for (Result result: results.getEntries()) {
    String color = "red";
    if (result.result) color = "green";
    out.print(String.format("<circle r=\"5\" cx=%d cy=%d" +
                    " id=\"pointer\" fill=%s></circle>", Math.round((result.x * 2) / result.r * 50 + 300 / 2.0),
            Math.round(300 / 2.0 - (result.y * 2) / result.r * 50), color
    ));
} %>