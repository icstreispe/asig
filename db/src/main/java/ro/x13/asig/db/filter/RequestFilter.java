package ro.x13.asig.db.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(2)
@Slf4j
public class RequestFilter implements Filter {


    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        log.info(
                "Request:  {} {}", req.getMethod(),
                req.getRequestURI());
        long start = System.currentTimeMillis();
        chain.doFilter(request, response);
        log.info(
                "Response: {} {}ms",
                res.getContentType(), System.currentTimeMillis() - start);
    }

    // other methods
}
