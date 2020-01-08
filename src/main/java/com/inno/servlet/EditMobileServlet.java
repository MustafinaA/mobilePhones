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

@WebServlet("/editmobile")
public class EditMobileServlet extends HttpServlet {
    private MobileDao mobileDao;

    @Override
    public void init() throws ServletException {
        mobileDao = (MobileDao) getServletContext().getAttribute("dao");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Mobile editingMobile = mobileDao.getMobileById(id);
        req.setAttribute("PageTitle", "Edit Mobile");
        req.setAttribute("PageBody", "form.jsp");
        req.setAttribute("editingMobile", editingMobile);
        req.getRequestDispatcher("/layout.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        req.setCharacterEncoding("utf-8");
        String model = req.getParameter("model");
        String price = req.getParameter("price");
        String manufacturer = req.getParameter("manufacturer");
        Mobile mobile = new Mobile(id, model, Integer.valueOf(price), manufacturer);
        boolean status = mobileDao.updateMobileById(mobile);
        if(status){
            out.print("<p>Mobile saved successfully!</p>");
            resp.sendRedirect(req.getContextPath() + "/allmobiles");
        }else{
            out.println("Sorry! Unable to save mobile");
        }
    }
}
