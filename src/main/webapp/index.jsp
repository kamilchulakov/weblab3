<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.nio.file.Path" %>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<html lang="ru">
<head>
    <meta charset="UTF-16">
    <meta content="Лабораторная работа №2 по дисциплине веб-программирование." name="description">
    <meta content="Чулаков Камиль Фаридович" name="author">
    <meta content="P3215" name="group">
    <meta content="15020" name="variant">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/stylesheets/style.css">
    <title>Web Lab2</title>
</head>
<body id="grad">
<div>
    <header>
        <span>Чулаков Камиль Фаридович, P3215, 15020</span>
    </header>
    <div class="svg-wrapper">
    <svg id="svgField">
        <!--            fill elements are here to make everything look nice-->
        <polygon fill="#1976d2" fill-opacity="1" points="150,210 270,150 150,150"></polygon>
        <rect fill="#1976d2" fill-opacity="1" x="150" y="90" height="60" width="120"></rect>
        <g transform="translate(150,150)">
            <path d="M0 0 -120 0 A120 115 0 0 1 0 -120" fill="#1976d2"/>
        </g>
        <line stroke="black" x1="0" x2="300" y1="150" y2="150"></line>
        <line stroke="black" x1="150" x2="150" y1="0" y2="300"></line>

        <text x="275" y="143">R</text>
        <line x1="270" x2="270" y1="148" y2="152"></line>

        <text x="215" y="143">R/2</text>
        <line x1="210" x2="210" y1="148" y2="152"></line>

        <text x="90" y="143">-R/2</text>
        <line x1="90" x2="90" y1="148" y2="152"></line>

        <text x="30" y="143">-R</text>
        <line x1="30" x2="30" y1="148" y2="152"></line>

        <text x="150" y="26">R</text>
        <line x1="148" x2="152" y1="30" y2="30"></line>

        <text x="150" y="86">R/2</text>
        <line x1="148" x2="152" y1="90" y2="90"></line>

        <text x="150" y="210">-R/2</text>
        <line x1="148" x2="152" y1="210" y2="210"></line>

        <text x="150" y="280">-R</text>
        <line x1="148" x2="152" y1="270" y2="270"></line>

        <circle r="2" stroke="black" cx="150" cy="150"></circle>


        <polygon fill="black" points="300,150 295,145 295,155" stroke="black"></polygon>
        <polygon fill="black" points="150,0  145,5   155,5" stroke="black"></polygon>

        <jsp:include page="pointmaker.jsp"/>

<%--        <circle cx="150" cy="150" id="pointer" r="5"></circle>--%>
    </svg>
    </div>
    <p id="status" class="status"></p>
    <form id="this_form" class="content" action="./">
        <table class="bordered">
            <thead>
            <tr>
                <th scope="col">Переменная</th>
                <th scope="col">Значение</th>
            </tr>
            </thead>

            <tbody>
            <tr>
                <td><label>X:</label></td>
                <td>
                    <div class="x-radios">
                        <label><input type="radio" class="x-radio" id='x-radio-1' value="-2" name="x-radio">-2</label>
                        <label><input type="radio" class="x-radio" id='x-radio-2' value="-1.5" name="x-radio">-1.5</label>
                        <label><input type="radio" class="x-radio" id='x-radio-3' value="-1" name="x-radio">-1</label>
                        <label><input type="radio" class="x-radio" id='x-radio-4' value="-0.5" name="x-radio">-0.5</label>
                        <label><input type="radio" class="x-radio" id='x-radio-5' value="0" name="x-radio">0</label>
                        <label><input type="radio" class="x-radio" id='x-radio-6' value="0.5" name="x-radio">0.5</label>
                        <label><input type="radio" class="x-radio" id='x-radio-7' value="1" name="x-radio">1</label>
                        <label><input type="radio" class="x-radio" id='x-radio-8' value="1.5" name="x-radio">1.5</label>
                        <label><input type="radio" class="x-radio" id='x-radio-9' value="2" name="x-radio">2</label>
                    </div>
                </td>
            </tr>
            <tr>
                <td>Y:</td>
                <td>
                    <label>
                        <input id="Y-text" placeholder="(-3 ... 5)" type="text" maxlength="5">
                    </label>
                </td>
            </tr>
            <tr>
                <td>R:</td>
                <td>
                    <div class="r-button">
                        <input class="buttonR" type="button" id='r-btn-1' value="1">
                        <input class="buttonR" type="button" id='r-btn-2' value="2">
                        <input class="buttonR" type="button" id='r-btn-3' value="3">
                        <input class="buttonR" type="button" id='r-btn-4' value="4">
                        <input class="buttonR" type="button" id='r-btn-5' value="5">
                    </div>
                </td>
            </tr>

            <tr>
                <td>
                    <button class="clear-button" id="clearButton" type="button">Очистить</button>
                <td>
                    <button class="send-button" id="submitButton" type="button">Отправить</button>
                </td>
            </tr>

            </tbody>
        </table>
    </form>

    <div class="content" id="answerTable">
        <jsp:include page="tablebody.jsp"/>
    </div>
    <img id="cat" src="https://http.cat/521.jpg" alt="" width="10%" height="10%" hidden="hidden">
    <script src="<%= request.getContextPath() %>/scripts/script.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <footer>
        <a href="https://itmo.ru"><img src="https://duckduckgo.com/i/d491c9b6.png" alt="Красивая картинка"></a>
    </footer>
</div>

</body>
</html>