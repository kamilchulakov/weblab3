package kotlin_meme_jsp

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

fun makeKotlinTest(req: HttpServletRequest, resp: HttpServletResponse) {
    resp.writer.println("<p>OH MY KOTLIN!</p>")
}