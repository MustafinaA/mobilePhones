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

@WebServlet("/addmobile")
public class AddMobileServlet extends HttpServlet {

    private MobileDao mobileDao;

    @Override
    public void init() throws ServletException {
        mobileDao = (MobileDao) getServletContext().getAttribute("dao");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("PageTitle", "New Mobiles");
        req.setAttribute("PageBody", "form.jsp");
        req.getRequestDispatcher("/layout.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        req.setCharacterEncoding("utf-8");
        String model = req.getParameter("model");
        String price = req.getParameter("price");
        String manufacturer = req.getParameter("manufacturer");
        Mobile mobile = new Mobile(null, model, Integer.valueOf(price), manufacturer);
        boolean status = mobileDao.addMobile(mobile);
        if(status){
            out.print("<p>Mobile saved successfully!</p>");
            resp.sendRedirect(req.getContextPath() + "/allmobiles");
        }else{
            out.println("Sorry! Unable to save mobile");
        }
    }
}