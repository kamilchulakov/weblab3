package servlets

import java.math.BigDecimal
import javax.servlet.FilterChain
import javax.servlet.http.HttpFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class PerformanceFilter: HttpFilter() {
    override fun doFilter(req: HttpServletRequest?, res: HttpServletResponse?, chain: FilterChain?) {
        val start = System.nanoTime()
        val big = 1000000000.0f
        chain?.doFilter(req, res)
        System.out.printf("Chain uptime: %.3f s\n",(System.nanoTime() - start) / big)
    }
}