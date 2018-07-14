package controllers;

import com.google.gson.Gson;
import datastorage.DataStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CarServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PrintWriter out = response.getWriter();

        String myJsonString = "{name: '"+DataStorage.INSTANCE.getUserByUsername("bishwa").getPassword() +"'}";

        out.print(new Gson().toJson(DataStorage.INSTANCE.getAllCars()));
        //out.flush();
    }
}
