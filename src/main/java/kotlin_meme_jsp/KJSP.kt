package kotlin_meme_jsp

import model.Result
import model.Results
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.math.roundToInt

fun makeKotlinTest(req: HttpServletRequest, resp: HttpServletResponse) {
    resp.writer.println("<p>OH MY KOTLIN!</p>")
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