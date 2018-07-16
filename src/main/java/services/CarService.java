package services;

import com.google.gson.Gson;
import datastorage.DataStorage;
import exceptions.CarAlreadyExistsException;
import model.Car;
import model.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public enum CarService {

    INSTANCE;

    public List<Car> filterCars(double minPrice, double maxPrice, String[] brands, String[] shapes){
        return DataStorage.INSTANCE.getAllCars().stream()
                .filter(c -> c.getPrice() >= minPrice)
                .filter(c -> c.getPrice() <= maxPrice)
                .filter(c -> Arrays.asList(brands).contains(c.getBrand()))
                .filter(c -> Arrays.asList(shapes).contains(c.getShape()))
                .collect(Collectors.toList());
    }
    public Car createCar(Map<String, String> carDict, User user) throws CarAlreadyExistsException, ParseException {
        String number = carDict.get("number");
        String model = carDict.get("model");
        String brand =carDict.get("brand");
        double price =Double.parseDouble(carDict.get("price"));
        String color =carDict.get("color");
        boolean isSold = false;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

           Date startDate = df.parse(carDict.get("date"));
            String newDateString = df.format(startDate);
            System.out.println(newDateString);

        double discount =Double.parseDouble( carDict.get("discount"));
        String image = carDict.get("image");
        String shape = carDict.get("shape");

        Car car = new Car((int)(Math.random() * (1000-10)) + 10, number,model,brand,price,color,shape,isSold,startDate,discount,image,user);
        DataStorage.INSTANCE.addCar(car);
        return car;
    }
}
