package controllers;

import datastorage.DataStorage;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
                    out.print("true");
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
