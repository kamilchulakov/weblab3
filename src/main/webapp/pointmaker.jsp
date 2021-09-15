<%@ page import="model.Result" %>
<%@ page import="static kotlin_meme_jsp.KJSPKt.makePoints" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%--is needed for foreach tag (https://www.educba.com/jsp-foreach/), btw jstl is needed too--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="results" scope="session" class="model.Results"/>
<%
    makePoints(request, response, results);
%>