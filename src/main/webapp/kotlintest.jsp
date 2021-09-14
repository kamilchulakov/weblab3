<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String footer = "<p>Can Kotlin be used?</p>";
  out.print(footer);
  String test_result = "<p>Not directly!</p>";
  // var nullable: String? = null
  // this was red :|
  out.print(test_result);
  // I don't really want to write Kotlin class for printing html :|
%>
