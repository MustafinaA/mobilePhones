package com.inno.servlet;

import com.inno.dao.AppUtils;
import com.inno.dao.DataDao;
import com.inno.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private DataDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = (DataDao) getServletContext().getAttribute("daoU");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("PageTitle", "Login");
        req.setAttribute("PageBody", "login.jsp");
        req.getRequestDispatcher("/layout.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User findUser = new User(login, password);
        Optional user= userDao.get(findUser);

        if (!user.isPresent()) {
            String errorMessage = "Invalid login or Password";
            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("PageTitle", "Login");
            req.setAttribute("PageBody", "login.jsp");
            req.getRequestDispatcher("/layout.jsp").forward(req, resp);
            return;
        }

        AppUtils.storeLoginedUser(req.getSession(), user);
        int redirectId = -1;
        try {
            redirectId = Integer.parseInt(req.getParameter("redirectId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String requestUri = AppUtils.getRedirectAfterLoginUrl(req.getSession(), redirectId);
        if (requestUri != null) {
            resp.sendRedirect(requestUri);
        } else {
            // По умолчанию после успешного входа в систему перенаправить на страницу /userInfo
            resp.sendRedirect(req.getContextPath() + "/allmobiles");
        }
    }
}
