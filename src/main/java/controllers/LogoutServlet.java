package controllers;

import datastorage.DataStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DataStorage.INSTANCE.cookieMap.remove(req.getSession().getId());
        Cookie cookie = new Cookie("persistentlogintoken", req.getSession().getId());
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
        req.getSession().invalidate();
        PrintWriter out = resp.getWriter();
        out.write("true");
    }
}
