<%@ page import="java.util.List" %>
<%@ page import="model.Result" %>
<%@ page import="static kotlin_meme_jsp.KJSPKt.getTable" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<html lang="ru">
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
        <% out.print(getTable(request, response, results)); %>
    </tbody id="answerValues">
</table>