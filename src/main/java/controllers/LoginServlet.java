package controllers;

import datastorage.DataStorage;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
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

                    String rememberMe = req.getParameter("rememberMe");
                    if(rememberMe != null && rememberMe != "" && rememberMe.equals("true")){
                        Cookie cookie = new Cookie("persistentlogintoken", req.getSession().getId());
                        cookie.setMaxAge(3600); //in seconds
                        resp.addCookie(cookie);

                        DataStorage.INSTANCE.cookieMap.put(cookie.getValue(), username);
                    }

                    out.print(username);
//
                } else {
                  //  req.setAttribute("msg", "Invalid Credentials");
                    out.print("false");
                }

            }else {
               // req.setAttribute("msg", "Provide both username and password");
                out.print("false");
            }

        }
        catch (Exception ex){
            ex.printStackTrace();
           // req.setAttribute("msg", "An error occured while processing login");
            out.print("false");
        }

    }
}
