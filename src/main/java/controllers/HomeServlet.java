package controllers;

import datastorage.DataStorage;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


public class HomeServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("Initilization...");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie cookie = new Cookie("persistentlogintoken", req.getSession().getId());
        cookie.setMaxAge(10000); //in seconds
        resp.addCookie(cookie);

        DataStorage.INSTANCE.cookieMap.put(cookie.getValue(), "kiran");
        req.getRequestDispatcher("home.jsp").forward(req,resp);
    }


}
