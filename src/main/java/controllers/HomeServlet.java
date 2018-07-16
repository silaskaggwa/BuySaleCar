package controllers;

import datastorage.DataStorage;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie cookie = new Cookie("persistentlogintoken", req.getSession().getId());
        cookie.setMaxAge(10000); //in seconds
        resp.addCookie(cookie);

        DataStorage.INSTANCE.cookieMap.put(cookie.getValue(), "kiran");
        req.getRequestDispatcher("home.jsp").forward(req,resp);
    }
}
