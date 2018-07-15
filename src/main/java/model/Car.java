package model;

import java.util.Date;

public class Car {

    private int id;
    private String license;
    private String model;
    private String brand;
    private double price;
    private String color;
    private String shape;
    private boolean isSold;
    private Date date;
    private double discount;
    private String image;
    private User owner;

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Car(int id, String license, String model, String brand, double price, String color, String shape,
               boolean isSold, Date date, double discount, String image, User owner) {
        this.id = id;
        this.license = license;
        this.model = model;
        this.brand = brand;
        this.price = price;
        this.color = color;
        this.shape = shape;
        this.isSold = isSold;

        this.date = date;
        this.discount = discount;
        this.image = image;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getOwner(){
        return this.owner;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }
}
