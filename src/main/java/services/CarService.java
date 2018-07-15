package services;

import datastorage.DataStorage;
import model.Car;
import java.util.Arrays;
import java.util.List;
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
}
