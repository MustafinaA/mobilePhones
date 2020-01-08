package com.inno.servlet;

import com.inno.dao.DataDao;
import com.inno.pojo.Mobile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deletemobile")
public class DeleteMobileServlet extends HttpServlet {
    private DataDao mobileDao;

    @Override
    public void init() throws ServletException {
        mobileDao = (DataDao) getServletContext().getAttribute("daoM");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        int id = Integer.parseInt(req.getParameter("id"));
        Mobile mobile = new Mobile(id);
        boolean status = mobileDao.delete(mobile);
        if(status) {
            resp.sendRedirect(req.getContextPath() + "/allmobiles");
        }else{
            out.println("Sorry! Unable to delete mobile");
        }
    }
}
