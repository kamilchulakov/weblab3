package kotlin_meme_jsp

import model.Result
import model.Results
import java.lang.StringBuilder
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.math.roundToInt

fun makeKotlinTest(req: HttpServletRequest, resp: HttpServletResponse) {
    resp.writer.println("<p>stable version</p>")
}

fun makePoints(req: HttpServletRequest, resp: HttpServletResponse, results: Results) {
    for (result: Result in results.entries) {
        var color = "red";
        if (result.result) color = "green";
        resp.writer.print(String.format("<circle r=\"5\" cx=%d cy=%d" +
                " id=\"pointer\" fill=%s></circle>", ((result.x * 2) / result.r * 50 + 300 / 2.0).roundToInt(),
            (300 / 2.0 - (result.y * 2) / result.r * 50).roundToInt(), color
        ));
    }
}

fun getTable(req: HttpServletRequest, resp: HttpServletResponse, results: Results): String {
    val stringBuilder = StringBuilder("")
    for (result: Result in results.entries
    ) {
        stringBuilder.append("<tr>");
        stringBuilder.append("<td>" + result.getX() + "</td>");
        stringBuilder.append("<td>" + result.getY() + "</td>");
        stringBuilder.append("<td>" + result.getR() + "</td>");
        stringBuilder.append("<td>" + ( if (result.isInside) "<div style=\"color: green\">In</div>" else
        "<div style=\"color: red\">Out</div>"+ "</td>"));
        stringBuilder.append("<td>" + results.simpleDateFormat.format(result.queryTime) + "</td>");
        stringBuilder.append("<td>" + result.resultTime + "</td>");
        stringBuilder.append("</tr>");
    }
//    resp.writer.print(stringBuilder.toString())
    return stringBuilder.toString();
}