package datastorage;

import exceptions.CarAlreadyExistsException;
import exceptions.UserAlreadyExistsException;
import model.Car;
import model.User;

import java.util.*;

public enum DataStorage {

    INSTANCE;

    private List<Car> cars = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    public String[] carBrands = {"Mercedes","BMW","Toyota","Audi","Tesla","Ford","Cadillac","Mazda","Buick",
            "Chevrolet","Lexus","Other"};
    public String[] carShapes = {"Sedan","SUV","Van","Hatchback","Truck","Crossover","Coupe","Convertible"};
    public Map<String, String>  cookieMap = new HashMap<>();

    public void loadDefaultData(){
        User u1 = new User(1,"silas", "kaggwa", "silakag@gmail.com",
                "034234234","1000N St. Maharishi", "silas","12345");
        User u2 = new User(2,"bishwa", "bishwa", "bishwa@gmail.com",
                "76463437747","1000N St. Goldfich", "bishwa","12345");
        User u3 = new User(3,"kiran", "kiran", "kiran@gmail.com",
                "455345345535","1000N St. Mum", "kiran","12345");

        users.add(u1);
        users.add(u2);
        users.add(u3);

        cars.add(new Car(1, "GNG 123","2000", carBrands[0], 7400, "red","Sedan", false,
                new Date(), 0, "resources/images/a.jpg", u1));
        cars.add(new Car(2, "GYT 543","2000", carBrands[1], 23000, "red","SUV", false,
                new Date(), 0, "resources/images/a.jpg", u1));
        cars.add(new Car(3, "GNG 654","2000", carBrands[2], 6700, "red","Van", false,
                new Date(), 0, "resources/images/a.jpg",u2));
        cars.add(new Car(4, "PYT 123","2000", carBrands[3], 19000, "red","Hatchback", false,
                new Date(), 0, "resources/images/a.jpg",u2));
        cars.add(new Car(5,"MGT 123","2000", carBrands[4], 43000, "red","Crossover", false,
                new Date(), 0, "resources/images/a.jpg",u3));
        cars.add(new Car(6, "CFT 123","2000", carBrands[5], 5900, "red","Coupe", false,
                new Date(), 0, "resources/images/a.jpg",u3));
        cars.add(new Car(7, "TGD 123","2000", carBrands[2], 29900, "red","Crossover", false,
                new Date(), 0, "resources/images/a.jpg",u3));
        cars.add(new Car(8, "RHI 123","2000", carBrands[1], 20000, "red","Convertible", false,
                new Date(), 0, "resources/images/a.jpg",u1));
        cars.add(new Car(9, "BPT 123","2000", carBrands[6], 52000, "red","Truck", false,
                new Date(), 0, "resources/images/a.jpg",u2));
        cars.add(new Car(10, "JVT 123","2000", carBrands[7], 25000, "red","Sedan", false,
                new Date(), 0, "resources/images/a.jpg",u2));

        System.out.println("=======>>>>data loaded");
    }

    public Car addCar(Car car) throws CarAlreadyExistsException {
        if (getCarByLicense(car.getLicense()) != null){
            throw new CarAlreadyExistsException("Car already exists");
        }
        cars.add(car);
        return car;
    }

    public User addUser(User user) throws UserAlreadyExistsException {
        if (getUserByUsername(user.getUserName()) != null){
            throw new UserAlreadyExistsException("User already exists");
        }
        users.add(user);
        return user;
    }

    public Car getCarById(int id){
        for (Car car : cars){
            if (car.getId() == id){
                return car;
            }
        }
        return null;
    }

    public List<Car> getAllCars(){
        return cars;
    }

    public User getUserById(int id){
        for (User user : users){
            if (user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public User getUserByUsername(String username){
        for (User user : users){
            if (user.getUserName() == username){
                return user;
            }
        }
        return null;
    }

    public User getUserByUsernameAndPw(String username, String password){
        for (User user : users){
            if (user.getUserName().equals(username)
                    && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    public Car getCarByLicense(String license){
        for (Car car : cars){
            if (car.getLicense() == license){
                return car;
            }
        }
        return null;
    }

}
