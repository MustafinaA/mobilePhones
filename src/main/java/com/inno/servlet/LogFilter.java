package com.inno.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LogFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init logFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,
            ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String uri = "Requested Uri::" + req.getRequestURI();
        logger.info(uri);
        String method = "Requested Method::" + req.getMethod();
        logger.info(method);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("destroy logFilter");
    }
}