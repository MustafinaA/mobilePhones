package com.inno.servlet;

import com.inno.dao.DataDao;
import com.inno.pojo.Mobile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/showmobile")
public class ShowMobileServlet extends HttpServlet {

    private DataDao mobileDao;

    @Override
    public void init() throws ServletException {
        mobileDao = (DataDao) getServletContext().getAttribute("daoM");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mobileId = req.getParameter("id");
        if (mobileId == null) {
            throw new ServletException("Missing parameter id");
        }
        Mobile mobile = new Mobile(Integer.valueOf(mobileId));
        Mobile mobileShow = (Mobile) mobileDao.get(mobile);
        if (mobileShow==null) {
            resp.setStatus(404);
            req.getRequestDispatcher("/notfound.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("mobile", mobileShow);
        req.getRequestDispatcher("/showmobile.jsp").forward(req, resp);
    }
}