package com.inno.servlet;

import com.inno.dao.AppUtils;
import com.inno.pojo.User;
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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class LogFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init logFilter");
    }

    /*@Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,
            ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String uri = "Requested Uri::" + req.getRequestURI();
        logger.info(uri);
        String method = "Requested Method::" + req.getMethod();
        logger.info(method);
        filterChain.doFilter(servletRequest, servletResponse);
    }*/
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,
            ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String uri = "Requested Uri::" + req.getRequestURI();
        logger.info(uri);
        String method = "Requested Method::" + req.getMethod();
        logger.info(method);
        String servletPath = req.getServletPath();
        // Информация пользователя сохранена в Session (После успешного входа в систему).
        User loginedUser = AppUtils.getLoginedUser(req.getSession());

        if (servletPath.equals("/login")) {
            filterChain.doFilter(req, resp);
            return;
        }

        // Если пользователь еще не вошел в систему, Redirect (перенаправить) к странице логина.
        if (loginedUser == null) {
            String requestUri = req.getRequestURI();
            // Сохранить текущую страницу для перенаправления (redirect) после успешного входа в систему.
            int redirectId = AppUtils.storeRedirectAfterLoginUrl(req.getSession(), requestUri);

            resp.sendRedirect(req.getContextPath() + "/login?redirectId=" + redirectId);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("destroy logFilter");
    }
}