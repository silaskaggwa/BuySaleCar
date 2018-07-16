package controllers;

import com.google.gson.Gson;
import datastorage.DataStorage;
import exceptions.CarAlreadyExistsException;
import model.Car;
import model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import services.CarService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String filePath = getServletContext().getRealPath(getServletContext().getInitParameter("file-upload"));

       // System.out.println("filePath -->"+filePath);
        boolean isMultipart;
        int maxFileSize = 50 * 1024;
        int maxMemSize = 4 * 1024;
        File file ;

        isMultipart = ServletFileUpload.isMultipartContent(req);

        if (isMultipart){
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // maximum size that will be stored in memory
            factory.setSizeThreshold(maxMemSize);

            // Location to save data that is larger than maxMemSize.
            factory.setRepository(new File("./"));

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // maximum file size to be uploaded.
            upload.setSizeMax( maxFileSize );

            try {
                // Parse the request to get file items.
                List fileItems = upload.parseRequest(req);

                // Process the uploaded file items
                Iterator i = fileItems.iterator();
                Map<String, String> map = new HashMap<>();
                while ( i.hasNext () ) {
                    FileItem fi = (FileItem)i.next();
                    if ( !fi.isFormField () ) {
                        // Get the uploaded file parameters
                        //String fieldName = fi.getFieldName();
                        String fileName = fi.getName() +"_"+new Date().getDate()+"_"+new Date().getTime();
                       // String contentType = fi.getContentType();
                       // boolean isInMemory = fi.isInMemory();
                       // long sizeInBytes = fi.getSize();

                        // Write the file
                        if( fileName.lastIndexOf("\\") >= 0 ) {
                            file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
                        } else {
                            file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                        }

                        System.out.println("Abs path = "+file.getAbsolutePath());

                        fi.write( file ) ;

                        System.out.println(">>>> "+ filePath);
                     //   out.println("Uploaded Filename: " + fileName + "<br>");*/
                        map.put("image", "resources/images/"+file.getName());
                    }
                    else{
                        String value = fi.getString("UTF-8");
                        String names = fi.getFieldName();
                        map.put(names, value);

                    }
                }

               // System.out.println(map);
                User user = DataStorage.INSTANCE.getUserByUsername("kiran");
                Car newCar =   CarService.INSTANCE.createCar(map,user);

                PrintWriter out = resp.getWriter();
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                out.print(new Gson().toJson(newCar));
                out.flush();

            } catch(Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
