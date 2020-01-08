package com.inno.servlet;

import com.inno.dao.DataDao;
import com.inno.pojo.Mobile;
import com.inno.pojo.User;
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
    private DataDao<Mobile> mobileDao;
    @Inject
    private DataDao<User> userDao;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String isDao = servletContext.getInitParameter("isDao");
        if (isDao.equals("true")) {
            servletContext.setAttribute("daoM", mobileDao);
            servletContext.setAttribute("daoU", userDao);
            logger.info("Added attribute DAO");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        servletContext.removeAttribute("daoM");
        servletContext.removeAttribute("daoU");
        logger.info("Removed attribute DAO");
    }
}