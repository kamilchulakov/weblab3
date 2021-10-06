package servlets

import model.WelcomeBean
import javax.servlet.FilterChain
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebFilter("*")
class LogsFilter: HttpFilter() {
    override fun doFilter(req: HttpServletRequest?, res: HttpServletResponse?, chain: FilterChain?) {
        System.out.printf("Request Url:%s Method:%s Session:%s Data:%s\n", req?.requestURL, req?.method,
            req?.session?.id, req?.parameterMap?.values?.size
        ); // Does it look terrible, doesn't it? :)
        chain?.doFilter(req, res)
    }
}