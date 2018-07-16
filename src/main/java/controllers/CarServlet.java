package controllers;

import com.google.gson.Gson;
import datastorage.DataStorage;
import services.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class CarServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        String[] brands = request.getParameterValues("brand[]");
        String[] shapes = request.getParameterValues("shape[]");

        double minPriceQuery = 0;
        double maxPriceQuery = Double.MAX_VALUE;
        String[] brandsQuery = DataStorage.INSTANCE.carBrands;
        String[] shapesQuery = DataStorage.INSTANCE.carShapes;


        if (minPrice != null) {
            try {
                minPriceQuery = Double.parseDouble(minPrice);
            } catch (Exception ignored){}
        }

        if (maxPrice != null) {
            try {
                maxPriceQuery = Double.parseDouble(maxPrice);
            } catch (Exception ignored){}
        }

        if (brands != null && !Arrays.asList(brands).contains("Any")) brandsQuery = brands;

        if (shapes != null && !Arrays.asList(shapes).contains("Any")) shapesQuery = shapes;

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(new Gson().toJson(CarService.INSTANCE.filterCars(minPriceQuery, maxPriceQuery, brandsQuery, shapesQuery)));
        out.flush();

    }
}
