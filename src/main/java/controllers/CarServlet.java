package controllers;

import com.google.gson.Gson;
import datastorage.DataStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class CarServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        //Arrays.stream(request.getParameterValues("brand")).forEach(s->System.out.println());

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(new Gson().toJson(DataStorage.INSTANCE.getAllCars()));
        out.flush();
    }
}
