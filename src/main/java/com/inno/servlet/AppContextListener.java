package com.inno.servlet;

import com.inno.dao.MobileDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    private Logger logger = LoggerFactory.getLogger(AppContextListener.class);
    @Inject
    private MobileDao mobileDao;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String isDao = servletContext.getInitParameter("isDao");
        if (isDao.equals("true")) {
            servletContext.setAttribute("dao", mobileDao);
            logger.info("Added attribute DAO");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        servletContext.removeAttribute("dao");
        logger.info("Removed attribute DAO");
    }
}