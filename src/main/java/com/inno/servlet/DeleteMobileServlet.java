package com.inno.servlet;

import com.inno.dao.MobileDao;
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
    private MobileDao mobileDao;

    @Override
    public void init() throws ServletException {
        mobileDao = (MobileDao) getServletContext().getAttribute("dao");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        boolean status = mobileDao.deleteMobileById(id);
        if(status) {
            resp.sendRedirect(req.getContextPath() + "/allmobiles");
        }else{
            out.println("Sorry! Unable to delete mobile");
        }
    }
}
