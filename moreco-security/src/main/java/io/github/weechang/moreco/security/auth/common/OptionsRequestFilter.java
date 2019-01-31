package io.github.weechang.moreco.security.auth.common;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhangwei
 * date 2019/1/30
 * time 22:06
 */
public class OptionsRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filter)
            throws ServletException, IOException {
        if (req.getMethod().equals("OPTIONS")) {
            res.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,HEAD");
            res.setHeader("Access-Control-Allow-Headers", res.getHeader("Access-Control-Request-Headers"));
            return;
        }
        filter.doFilter(req, res);
    }
}
