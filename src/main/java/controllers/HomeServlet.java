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
        req.getRequestDispatcher("home.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try{
            String username = req.getParameter("userName");
            String password = req.getParameter("passWord");

            if( username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
                User optUser = DataStorage.INSTANCE.getUserByUsernameAndPw(username, password);

                if (optUser != null) {
                    HttpSession sess = req.getSession();
                    sess.setAttribute("userName", username);
                    out.print("true");
//                    Cookie cUsr = new Cookie("username", username);
//                    Cookie cPswd = new Cookie("password", password);
//                    cUsr.setMaxAge(5000);
//                    cPswd.setMaxAge(5000);
//                    resp.addCookie(cUsr);
//                    resp.addCookie(cPswd);

                  //  resp.sendRedirect("home");
                } else {
                    req.setAttribute("msg", "Invalid Credentials");
                    //req.getRequestDispatcher("home.jsp").forward(req, resp);
                    out.print("false");
                }

            }else {
                req.setAttribute("msg", "Provide both username and password");
                //req.getRequestDispatcher("home.jsp").forward(req, resp);
                out.print("false");
            }

        }
        catch (Exception ex){
            ex.printStackTrace();
            req.setAttribute("msg", "An error occured while processing login");
           // req.getRequestDispatcher("home.jsp").forward(req, resp);
            out.print("false");
        }


        //resp.setContentType("application/json");
        //resp.setCharacterEncoding("UTF-8");



    }
}
